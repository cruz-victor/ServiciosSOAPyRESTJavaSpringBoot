package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javax.xml.bind.DatatypeConverter;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import bo.gob.sin.sre.fac.dao.IComponentesCertificadosDao;
import bo.gob.sin.sre.fac.domain.IRegistrarHuellaSistemaDomain;
import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.service.IRegistroHuellasSistemaService;
import bo.gob.sin.sre.fac.service.validation.ValRegistroHuellaServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional

public class RegistroHuellasSistemaServiceImpl implements IRegistroHuellasSistemaService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroHuellasSistemaServiceImpl.class);
	
	@Autowired
	IRegistrarHuellaSistemaDomain iRegistroHuellaSistemaDomain;
	
	@Autowired
	IComponentesCertificadosDao iComponentesCertificadosDao;
	
	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	/** 
	 * Registro de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 */
	@Override
	public RegistroHuellasDigitalesDto registrarHuellaDigitalSistema(RegistroHuellasDigitalesDto pSolicitud) {
		LOG.info("Ingresando registrarHuellaDigitalSistema ");
		RegistroHuellasDigitalesDto vRespuesta = new RegistroHuellasDigitalesDto();
		vRespuesta.setOk(false);
		try {
			ValRegistroHuellaServiceImpl vValRegistroImprenta = new ValRegistroHuellaServiceImpl(mensajesDomain);
			vValRegistroImprenta.validarSolicitudRegistroHuellaDigital(pSolicitud);
			if (vValRegistroImprenta.isValido()) {			
				SreArchivos vResultadoArchivo = iRegistroHuellaSistemaDomain.registrarArchivos(pSolicitud.getArchivo());
				if (vResultadoArchivo.getArchivoId() !=null && vResultadoArchivo.getArchivoId() != null) {
					SreComponentesArchivos vResultadoComponenteArchivo = iRegistroHuellaSistemaDomain.registrarComponentesArchivos(pSolicitud.getUsuarioId(),vResultadoArchivo.getArchivoId(), pSolicitud.getMd5(),pSolicitud.getSha2(), pSolicitud.getCrc(),pSolicitud.getRutaArchivo(),pSolicitud.getNombre(),"");
				if (vResultadoComponenteArchivo.getComponenteArchivoId() > 0 && vResultadoComponenteArchivo.getComponenteArchivoId() != null) {
											
					//iRegistroHuellaSistemaDomain.registrarComponentesCertificados(pSolicitud.getTipoComponenteId(), vResultadoComponenteArchivo.getComponenteArchivoId(), pSolicitud.getUsuarioId(),pSolicitud.getSistemaId(), pSolicitud,0L);
					boolean vResultadoCertificado = false;
						if (vResultadoCertificado) {
							LOG.info("Registro existoso");
							vRespuesta.setOk(true);
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_EXITO));
						} else {
							LOG.info("Registro no existoso");
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
						}
					} else {
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
					}
				} else {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
				}
			} else {
				vRespuesta.setMensajes(vValRegistroImprenta.getMensajes());
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Error al realizar el guardado de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
		}
		LOG.info("Saliendo registrarHuellaDigitalSistema vRespuesta={}", vRespuesta);
		return vRespuesta;
	}
	
	//IASC - Genera el codigo SHA2, MD5 - 24/04/2018
	public String algoritmoHash(byte[] pArchivo, String algorithm) {
		String hashValue = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(pArchivo);
			byte[] digestedBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
		}
		catch (Exception e) {
			System.out.println("Error generando Hash");
		}
		return hashValue;
	}
	
	//IASC - Genera el codigo CRC32 - 25/04/2018
	public String calCrc32(byte[] data) {
	    Checksum checksum = new CRC32();
	    // update the current checksum with the specified array of bytes
	    checksum.update(data, 0, data.length);

	    // get the current checksum value
	    long checksumValue = checksum.getValue();
	    String hex = Long.toHexString(checksumValue).toUpperCase();
	    while (hex.length() < 8) {
	        hex = "0" + hex;
	    }
	    return hex;
	}
	
	/**
	 * Genera codigo SHA-256, MD5 y CRC32
	 * 
	 * @author: Peter Flores
	 * @Fecha: 14/11/2018
	 * @param pSolicitud, objeto de tipo generar huella digital de sistema
	 * @return   Devuelve objeto Dto de tipo GenerarHuellaSistemaDto.
	 */
	public RegistrarHuellaSistemaDto obtenerCifradoHuellaDigital(GenerarHuellaSistemaDto pSolicitud) {
		RegistrarHuellaSistemaDto vRespuesta = new RegistrarHuellaSistemaDto();
		vRespuesta.setOk(false);
		if (pSolicitud.getArchivo() != null) {
			try
			{   
				String vHash = algoritmoHash(pSolicitud.getArchivo(),"MD5");
				vRespuesta.setMd5(vHash);
				
				String vCrc = calCrc32(pSolicitud.getArchivo());
				vRespuesta.setMd5(vHash);
				vRespuesta.setCrc(vCrc);
				
				String vSha2 = algoritmoHash(pSolicitud.getArchivo(),"SHA-256");
				vRespuesta.setSha2(vSha2);
				if(StringUtils.hasText(vHash) && StringUtils.hasText(vCrc) && StringUtils.hasText(vSha2)) {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.RECUPERACION_HUELLA_EXITOSA));
					vRespuesta.setOk(true);	
				}
				else
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.GENERACION_HUELLA_SISTEMA_ERROR));
			}
			catch (Exception e) {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.GENERACION_HUELLA_SISTEMA_ERROR));
			}
		}
		else 
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		return vRespuesta;
	}	
	
	/**
	 * Registro del listado de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 16/11/2018
	 * @param pSolicitud, listado de la entidad huellas digitales de sistema.
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 */
	public RegistroHuellasDigitalesDto registrarHuellasDigitalesSistemas(ListaRegistroHuellasDigitalesDto pSolicitud) {
		LOG.info("Ingresando registrarHuellaSistema ");
		RegistroHuellasDigitalesDto vRespuesta = new RegistroHuellasDigitalesDto();
		RegistroHuellasDigitalesDto registroHuellasDigitales = null;
		for (RegistroHuellasDigitalesDto registroHuellasDigitalesDto : pSolicitud.getListaRegistroHuellasDigitales()) {
			registroHuellasDigitales = registrarHuellaDigitalSistema(registroHuellasDigitalesDto);
			vRespuesta.addMensaje(registroHuellasDigitales.getMensajes().get(0));
			vRespuesta.setOk(registroHuellasDigitales.isOk());
		}
		return vRespuesta; 
	}
	
	/**
	 * Obtiene el registro del listado de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 20/11/2018
	 * @param pSistemaId, listado de la entidad huellas digitales de sistema.
	 * @return   Devuelve una lista de tipo ListaRegistroHuellasDigitalesDto.
	 */
	@Override
	public ListaRegistroHuellasDigitalesDto obtieneComponentesArchivos(Long pSistemaId) {
		ListaRegistroHuellasDigitalesDto vResultado = new ListaRegistroHuellasDigitalesDto();
		LOG.info("ingresando a obtieneComponentesArchivos");
		vResultado.setOk(false);
		if(pSistemaId !=null && pSistemaId < 0) {
			vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.RECUPERACION_HUELLA_ERROR));
			
		} else {
			
			List<SreComponentesArchivos> listaComponentesArchivos = iRegistroHuellaSistemaDomain.obtieneComponentesArchivos(pSistemaId);
			if(listaComponentesArchivos.isEmpty()) {
				vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.RECUPERACION_HUELLA_ERROR));				
			} else {
				
				ModelMapper vModelMapper = new ModelMapper();											
				List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitales = new ArrayList<>();
				RegistroHuellasDigitalesDto registroHuellasDigitalesDto = null;
				try
				{ 
					List<SreComponentesCertificados> listaComponentesCertificados = iComponentesCertificadosDao.obtenerComponentesCertificados(listaComponentesArchivos.stream().map(f->f.getComponenteArchivoId()).collect(Collectors.toList()));	
					List<Integer> vTipoComponenteId=null;
					for (SreComponentesArchivos registroHuellasDigitales : listaComponentesArchivos) {
						registroHuellasDigitalesDto = new RegistroHuellasDigitalesDto();
						registroHuellasDigitalesDto = vModelMapper.map(registroHuellasDigitales,RegistroHuellasDigitalesDto.class);					
						vTipoComponenteId = listaComponentesCertificados.stream().filter(f->f.getComponenteArchivoId().equals(registroHuellasDigitales.getComponenteArchivoId())).collect(Collectors.toList()).stream().map(x->x.getTipoComponenteId()).collect(Collectors.toList());
						registroHuellasDigitalesDto.setTipoComponenteId(vTipoComponenteId);
						listaRegistroHuellasDigitales.add(registroHuellasDigitalesDto);
					}
					if(listaRegistroHuellasDigitales.size()>0)
					{
						vResultado.setListaRegistroHuellasDigitales(listaRegistroHuellasDigitales);
						vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.RECUPERACION_HUELLA_EXITOSA));
						vResultado.setOk(true);
					}
					else
						vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.GENERACION_HUELLA_SISTEMA_ERROR));
				}
				catch (Exception e) {
					vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.GENERACION_HUELLA_SISTEMA_ERROR));
				}	
			}
		}
		LOG.info("saliendo de obtieneComponentesArchivos");
		return vResultado;
	}
	
	/** 
	 * Registro de huellas digitales de sistema.  
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 20/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 */
	@Override
	public RespuestaOperacionDto registrarComponentesArchivos(RespuestaDetalleHuellaDto pSolicitud) 
	{
		LOG.info("Ingresando registrarComponentesArchivos");
		RespuestaOperacionDto vRespuesta = new RespuestaOperacionDto();
		vRespuesta.setOk(false);		
		
		try 
		{
			ValRegistroHuellaServiceImpl vValRegistroImprenta = new ValRegistroHuellaServiceImpl(mensajesDomain);
			vValRegistroImprenta.validarSolicitudRegistroHuellaDigital(pSolicitud);
			
			SreArchivos vResultadoArchivo = iRegistroHuellaSistemaDomain.registrarArchivos(pSolicitud.getComponentesArchivos().getArchivo());
			
			if(vResultadoArchivo != null)
			{
				SreComponentesArchivos vSreComponentesArchivos = new SreComponentesArchivos();
				
				vSreComponentesArchivos = iRegistroHuellaSistemaDomain.registrarComponentesArchivos(pSolicitud.getDatosEntradaUsuarioRegistro(),
																		  vResultadoArchivo.getArchivoId(), 
																		  "",
																		  "", 
																		  "",
																		  pSolicitud.getComponentesArchivos().getRuta(),
																		  pSolicitud.getComponentesArchivos().getNombre(),"",
																		  pSolicitud.getComponentesArchivos().getExtension());
				if(vSreComponentesArchivos!=null)
				{
					List<Integer> vTipoComponentes = new ArrayList<>();
					pSolicitud.getListaComponentsCertificados().stream().forEach((c) -> vTipoComponentes.add(c.getTipoComponenteId()));
					
					
					boolean vResultadoCertificado = iRegistroHuellaSistemaDomain.registrarComponentesCertificados(vTipoComponentes, vSreComponentesArchivos.getComponenteArchivoId(), pSolicitud.getDatosEntradaUsuarioRegistro(),pSolicitud.getDatosEntradaSistemaId(), pSolicitud.getDatosEntradaSolicitudCertificacion());
					
					if(vResultadoCertificado)
					{
						vRespuesta.setOk(true);
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_EXITO));
					}
					else
					{
						vRespuesta.setOk(false);
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.GENERACION_HUELLA_SISTEMA_ERROR));
					}
				}
				else
				{
					vRespuesta.setOk(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
				}
			}
			else
			{
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
			}
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Error al realizar el guardado de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
		}
		
		LOG.info("Saliendo registrarComponentesArchivos vRespuesta={}", vRespuesta);
		return vRespuesta;
	}
}
