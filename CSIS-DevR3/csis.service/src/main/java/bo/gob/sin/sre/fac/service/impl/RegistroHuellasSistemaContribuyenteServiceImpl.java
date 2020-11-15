package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;

import bo.gob.sin.sre.fac.domain.IArchivoTmpDomain;
import bo.gob.sin.sre.fac.domain.IComponentesArchivosDomain;
import bo.gob.sin.sre.fac.domain.IComponentesArchivosTmpDomain;
import bo.gob.sin.sre.fac.domain.IComponentesCertificadosDomain;
import bo.gob.sin.sre.fac.domain.IComponentesCertificadosTmpDomain;
import bo.gob.sin.sre.fac.domain.IDetalleSolicitudesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISistemasConsultaDomain;
import bo.gob.sin.sre.fac.dto.ComponentesArchivosDto;
import bo.gob.sin.sre.fac.dto.ComponentsCertificadosDto;
import bo.gob.sin.sre.fac.dto.DatosReporteComponentesCtbteDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreArchivosTmp;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRegistroHuellasSistemaContribuyenteService;
import bo.gob.sin.sre.fac.service.validation.ValRegistroHuellaServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional

public class RegistroHuellasSistemaContribuyenteServiceImpl implements IRegistroHuellasSistemaContribuyenteService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroHuellasSistemaContribuyenteServiceImpl.class);

	@Autowired 
	IComponentesCertificadosTmpDomain iComponentesCertificadosTmpDomain;
	
	@Autowired
	IComponentesArchivosTmpDomain iComponentesArchivosTmpDomain;
	
	@Autowired 
	IComponentesCertificadosDomain iComponentesCertificadosDomain;
	
	@Autowired
	IComponentesArchivosDomain iComponentesArchivosDomain;
	
	@Autowired
	IArchivoTmpDomain iArchivoTmpDomain;
		
	@Autowired
	IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	IParametricasDomain iClasificadorDomain;
	
	@Autowired
	ISistemasConsultaDomain iSistemasConsultaDomain;
	
	@Autowired
	IDetalleSolicitudesCertificacionesConsultaDomain iDetalleSolicitudesCertificacionesConsultaDomain;
	
	/** 
	 * Registro de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores 
	 * @Fecha: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 * @modificado por rosario.garcia 01/12/2018
	 */
	@Override
	public RegistroHuellasDigitalesDto registrarHuellaDigitalSistemaContribuyente(RegistroHuellasDigitalesDto pSolicitud) {
		LOG.info("Ingresando registrarHuellaDigitalSistemaContribuyente"+pSolicitud.getCrc()+" "+pSolicitud.getArchivo()+ " ");
		RegistroHuellasDigitalesDto vRespuesta = new RegistroHuellasDigitalesDto();
		vRespuesta.setOk(false);
		try {				
			ValRegistroHuellaServiceImpl vValRegistroArchivo = new ValRegistroHuellaServiceImpl(mensajesDomain);
			vValRegistroArchivo.validarSolicitudRegistroHuellaDigital(pSolicitud);	
			if (vValRegistroArchivo.isValido()) {			
				SreArchivosTmp vResultadoArchivo = iArchivoTmpDomain.registrarArchivos(pSolicitud.getArchivo());
				System.out.println("Archivo registrado"+vResultadoArchivo.getArchivoId());
				if (vResultadoArchivo.getArchivoId() !=null && vResultadoArchivo.getArchivoId() != null) {
					SreComponentesArchivosTmp vResultadoComponenteArchivo = iComponentesArchivosTmpDomain.registrarComponentesArchivos(pSolicitud.getUsuarioId(),vResultadoArchivo.getArchivoId(), pSolicitud.getMd5(),pSolicitud.getSha2(), pSolicitud.getCrc(),pSolicitud.getRutaArchivo(),pSolicitud.getNombre(),"");
					System.out.println("Datos recuperados "+vResultadoComponenteArchivo.getComponenteArchivoTmpId());
				if (vResultadoComponenteArchivo.getComponenteArchivoTmpId() > 0 && vResultadoComponenteArchivo.getComponenteArchivoTmpId() != null) {						
					boolean vResultadoCertificado = iComponentesCertificadosTmpDomain.registrarComponentesCertificados(pSolicitud.getTipoComponenteId(), vResultadoComponenteArchivo.getComponenteArchivoTmpId(), pSolicitud.getUsuarioId(),pSolicitud.getSistemaId());
					if (vResultadoCertificado) {
							LOG.info("Registro exitoso");
							vRespuesta.setOk(true);
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
						} else {
							LOG.info("Registro NO exitoso");
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
						}
					} else {
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
					}
				} else {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
				}
			} else {
				vRespuesta.setMensajes(vValRegistroArchivo.getMensajes());
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Error al realizar el guardado de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
		}
		LOG.info("Saliendo registrarHuellaDigitalSistemaContribuyente vRespuesta={}", vRespuesta);
		return vRespuesta;
	}
	
	/**
	 * Registro del listado de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 16/11/2018
	 * @param pSolicitud, listado de la entidad huellas digitales de sistema.
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 * @modificado rosario.garcia 28/11/2018 
	 * @modificado rosario.garcia 03/12/2018 se agrrego la consulta para actualizar los archivos
	 */
	public RegistroHuellasDigitalesDto registrarHuellasDigitalesSistemas(ListaRegistroHuellasDigitalesDto pSolicitud) {
		LOG.info("Ingresando registrarHuellasDigitalesSistemaContribuyente >>"+pSolicitud.getListaRegistroHuellasDigitales().size());
		RegistroHuellasDigitalesDto vRespuesta = new RegistroHuellasDigitalesDto();
	
		if(pSolicitud.getListaRegistroHuellasDigitales().size() > 0) { 
			ValRegistroHuellaServiceImpl vValRegistroArchivo = new ValRegistroHuellaServiceImpl(mensajesDomain);
			vValRegistroArchivo.validarSolicitudRegistroHuellaDigital(pSolicitud.getListaRegistroHuellasDigitales().get(0));
			if(vValRegistroArchivo.isValido() ) {
				List<SreComponentesCertificadosTmp> vSreCompCertTmp = new ArrayList<>();
				vSreCompCertTmp = iComponentesCertificadosTmpDomain.obtenerComponenteCertificadoTmpPorSistemaId(pSolicitud.getListaRegistroHuellasDigitales().get(0).getSistemaId());
				if(vSreCompCertTmp != null && vSreCompCertTmp.size() > 0) {			
					System.out.println("Existen Registros "+vSreCompCertTmp.size() +" Sistema_id"+pSolicitud.getListaRegistroHuellasDigitales().get(0).getSistemaId());
					actualizarEstadoComponenteCertificado(vSreCompCertTmp);	
				}else {
					System.out.println("No existen registros en SreComponentesCertificadosTmp");
				}
				
				RegistroHuellasDigitalesDto registroHuellasDigitales = null;
				for (RegistroHuellasDigitalesDto registroHuellasDigitalesDto : pSolicitud.getListaRegistroHuellasDigitales()) {
					registroHuellasDigitales = registrarHuellaDigitalSistemaContribuyente(registroHuellasDigitalesDto);
					if(registroHuellasDigitales.isOk()) {
						vRespuesta.setOk(true);
					}
				}
			}else {
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		}else {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}	
		return vRespuesta;
	}
	
	//======================= ACTUALIZAR COMPONENTES CERTIFICADOS ========================================
	 /***
	  * Actualiza el estado de los componentes certificados, los archivos de componentes y los archivos, de un determinado sistema
	  * 
	  * @author rosario.garcia
	  * @param pListaComponenteCertificado, lista de los componentes
	  * @fecha 29/11/2018
	  */
	 @Override
	 public void actualizarEstadoComponenteCertificado(List<SreComponentesCertificadosTmp> pListaComponenteCertificado) {
		Date vFechaHoy = new Date();
		SreComponentesCertificadosTmp vSreComponentesCertificadosTmp = new SreComponentesCertificadosTmp();
		SreComponentesArchivosTmp vSreComponentesArchivosTmp = new SreComponentesArchivosTmp();
		SreArchivosTmp vSreArchivosTmp = new SreArchivosTmp();
		 LOG.info("Ingresando a actualizar actualizarEstadoComponenteCertificado: ");
		 for(SreComponentesCertificadosTmp vSreComponenteCertificado : pListaComponenteCertificado) {
			 vSreComponenteCertificado.setEstadoId("IA");
			 vSreComponenteCertificado.setFechaUltimaModificacion(vFechaHoy);
			 vSreComponentesCertificadosTmp = iComponentesCertificadosTmpDomain.actualizarEstadoComponenteCertificadoTmp(vSreComponenteCertificado);
			 vSreComponentesArchivosTmp = iComponentesArchivosTmpDomain.actualizarEstadoComponenteArchivoTmp(vSreComponentesCertificadosTmp.getComponenteArchivoTmpId());
			 vSreArchivosTmp = iArchivoTmpDomain.actualizarEstadoArchivoTmp(vSreComponentesArchivosTmp.getArchivoId());
		 }
	 }
	//======================= SERVICIO PARA GENERAR EL REPORTE ================

	/***
	 * Método que retorna los datos para el reporte de Componentes registrados
	 * 
	 * @return los datos para el reporte de componentes registrados por el Contribuyente 
	 * @fecha 30/11/2018
	 */
	 
	public ReporteComponentesRegistradosCtbteDto obtenerListaComponentesRegistradosPorContribuyente(Long pSistemaId) {
		ReporteComponentesRegistradosCtbteDto vRespuesta = new ReporteComponentesRegistradosCtbteDto();
		ClasificadorDto vClasificador= new ClasificadorDto();
		String vModalidad = "";
		String vNombreComponente = "";
		Date vFechaHoy = new Date();
		int vCont = 0;
		vRespuesta.setOk(false);
		
		if(pSistemaId != null && pSistemaId > 0) {
			List<SreDetalleSolicitudesCertificaciones> vSreDetalleSolicitudesCertificaciones= new ArrayList<SreDetalleSolicitudesCertificaciones>();
			vSreDetalleSolicitudesCertificaciones = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerSolicitudesCertificacionIdSistema(pSistemaId);
			if (!vSreDetalleSolicitudesCertificaciones.isEmpty()) {
				SreSistemas vEntidad = new SreSistemas(); 
				vEntidad = iSistemasConsultaDomain.recuperarDatosSistemas(pSistemaId);
				vRespuesta.setNombreSistema(vEntidad.getNombreSistema());
				vRespuesta.setVersion(vEntidad.getVersion());
				vRespuesta.setFechaRegistro(vFechaHoy);
				for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreDetalleSolicitudesCertificaciones) {
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());		
					if(vCont == 0) {
						vModalidad = vClasificador.getDescripcion();
						vCont = vCont + 1;
					}
					else {
						vModalidad = vModalidad + "/" + vClasificador.getDescripcion();	
					}	
				}
				vRespuesta.setDescripcionModalidadFacturacionId(vModalidad);
				List<SreComponentesCertificadosTmp> vSreCompCertTmp = new ArrayList<>();
				vSreCompCertTmp = iComponentesCertificadosTmpDomain.obtenerComponenteCertificadoTmpPorSistemaId(pSistemaId);
				
				List<DatosReporteComponentesCtbteDto> vlistaComp = new ArrayList<>();
				for(SreComponentesCertificadosTmp vSreCompCert : vSreCompCertTmp) {
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreCompCert.getTipoComponenteId());
					vNombreComponente = vClasificador.getDescripcion();
					DatosReporteComponentesCtbteDto dto = new DatosReporteComponentesCtbteDto();
					dto.setNombreComponente(vNombreComponente);			
					SreComponentesArchivosTmp vSreComponentesArchivosTmp = new SreComponentesArchivosTmp();
					vSreComponentesArchivosTmp = iComponentesArchivosTmpDomain.obtenerComponenteArchivoId(vSreCompCert.getComponenteArchivoTmpId());
					if(vSreComponentesArchivosTmp != null) {
						dto.setNombreArchivo(vSreComponentesArchivosTmp.getNombre());
						dto.setRuta(vSreComponentesArchivosTmp.getRuta());
						vlistaComp.add(dto);
						vRespuesta.setListaComponentesContribuyente(vlistaComp);
						vRespuesta.setOk(true);
					}
				}
				if(vRespuesta.isOk()) {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
				}else {
					
				}
			}else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		}else {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
		return vRespuesta;
	}
	
	/***
	 * Método que retorna los datos para el reporte de Componentes registrados
	 * 
	 * @return los datos para el reporte de componentes registrados por el Contribuyente 
	 * @fecha 30/11/2018
	 */
	@Override
	public ReporteComponentesRegistradosCtbteDto obtenerListaComponentesRegistradosPorContribuyente(Long pSistemaId, Long pIdContribuyente) 
	{
		ReporteComponentesRegistradosCtbteDto vRespuesta = new ReporteComponentesRegistradosCtbteDto();
		ClasificadorDto vClasificador= new ClasificadorDto();
		String vModalidad = "";
		String vNombreComponente = "";
		Date vFechaHoy = new Date();
		int vCont = 0;
		vRespuesta.setOk(false);
		
		if(pSistemaId != null && pSistemaId > 0) {
			List<SreDetalleSolicitudesCertificaciones> vSreDetalleSolicitudesCertificaciones= new ArrayList<SreDetalleSolicitudesCertificaciones>();
			vSreDetalleSolicitudesCertificaciones = iDetalleSolicitudesCertificacionesConsultaDomain.recuperaSolicitudesDetalleCertificacionPorIdSistema(pSistemaId, pIdContribuyente);
			if (!vSreDetalleSolicitudesCertificaciones.isEmpty()) {
				SreSistemas vEntidad = new SreSistemas(); 
				vEntidad = iSistemasConsultaDomain.recuperarDatosSistemas(pSistemaId);
				vRespuesta.setNombreSistema(vEntidad.getNombreSistema());
				vRespuesta.setVersion(vEntidad.getVersion());
				vRespuesta.setFechaRegistro(vFechaHoy);
				
				//for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreDetalleSolicitudesCertificaciones)
				for (int i =0;i< vSreDetalleSolicitudesCertificaciones.size();i++)
				{
					SreDetalleSolicitudesCertificaciones vSreDetalle = new SreDetalleSolicitudesCertificaciones();
					
					vSreDetalle = vSreDetalleSolicitudesCertificaciones.get(i);
					
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());		
					if(vCont == 0) {
						vModalidad = vClasificador.getDescripcion();
						vCont = vCont + 1;
					}
					else {
						vModalidad = vModalidad + "/" + vClasificador.getDescripcion();	
					}	
				}
				vRespuesta.setDescripcionModalidadFacturacionId(vModalidad);
				List<SreComponentesCertificadosTmp> vSreCompCertTmp = new ArrayList<>();
				vSreCompCertTmp = iComponentesCertificadosTmpDomain.obtenerComponenteCertificadoTmpPorSistemaId(pSistemaId);
				
				List<DatosReporteComponentesCtbteDto> vlistaComp = new ArrayList<>();
				for(SreComponentesCertificadosTmp vSreCompCert : vSreCompCertTmp) {
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreCompCert.getTipoComponenteId());
					vNombreComponente = vClasificador.getDescripcion();
					DatosReporteComponentesCtbteDto dto = new DatosReporteComponentesCtbteDto();
					dto.setNombreComponente(vNombreComponente);			
					SreComponentesArchivosTmp vSreComponentesArchivosTmp = new SreComponentesArchivosTmp();
					vSreComponentesArchivosTmp = iComponentesArchivosTmpDomain.obtenerComponenteArchivoId(vSreCompCert.getComponenteArchivoTmpId());
					if(vSreComponentesArchivosTmp != null) {
						dto.setNombreArchivo(vSreComponentesArchivosTmp.getNombre());
						dto.setRuta(vSreComponentesArchivosTmp.getRuta());
						vlistaComp.add(dto);
						vRespuesta.setListaComponentesContribuyente(vlistaComp);
						vRespuesta.setOk(true);
					}
				}
				if(vRespuesta.isOk()) {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
				}else {
					
				}
			}else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		}else {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
		return vRespuesta;
	}
	
	/***
	 * @Descripcion: Método que retorna los datos para el reporte de Componentes registrados.
	 * @return los datos para el reporte de componentes registrados por el Contribuyente 
	 * @fecha 19/09/2019
	 */
	@Override
	public ListaDetalleHuellaDto  obtieneComponentesArchivosCertificados(Long pSistemaId, Long pSolicitudCertificacionId, Long pContribuyenteId)
	{
		ListaDetalleHuellaDto vRespuesta = new ListaDetalleHuellaDto();
		List<RespuestaDetalleHuellaDto> vRespuestaDetalleHuella = new ArrayList<>();
		
		ModelMapper vMapper = new ModelMapper();
		
		//se recupera los componentes archivos
		List<SreComponentesArchivos> vComponentesArchivos = iComponentesArchivosDomain.obtieneComponentesArchivos(pSistemaId, pSolicitudCertificacionId);
		
		for (SreComponentesArchivos sreComponentesArchivos : vComponentesArchivos) 
		{
			RespuestaDetalleHuellaDto vRespuestaDetalleHuellaDto =new RespuestaDetalleHuellaDto();
			
			ComponentesArchivosDto vComponenteArchivo = new  ComponentesArchivosDto();
			
			Type type = new TypeToken<ComponentesArchivosDto>() {}.getType();
			vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			vComponenteArchivo = vMapper.map(sreComponentesArchivos, type);
			
			vRespuestaDetalleHuellaDto.setComponentesArchivos(vComponenteArchivo);
			
			//se recupera los componentes certificados
			List<SreComponentesCertificados> listaComponentesSre = new ArrayList<>();
			List<ComponentsCertificadosDto> listaComponentesCertificados = new ArrayList<>();
			
			listaComponentesSre = iComponentesCertificadosDomain.obtenerComponentesCertificados(sreComponentesArchivos.getComponenteArchivoId());						
			Type typeComponents = new TypeToken<List<ComponentsCertificadosDto>>() {}.getType();
			listaComponentesCertificados = vMapper.map(listaComponentesSre, typeComponents);
			
			vRespuestaDetalleHuellaDto.setListaComponentsCertificados(listaComponentesCertificados);
			
			vRespuestaDetalleHuella.add(vRespuestaDetalleHuellaDto);
		}
		
		vRespuesta.setListaDetalleHuella(vRespuestaDetalleHuella);
		vRespuesta.setOk(true);
		
		return vRespuesta;
	}
	
	/***
	 * @Descripcion: Cambia el estado de cancelado a los archivos componente certificados.
	 * @author wilson.limachi
	 * @return dto respuesta
	 * @fecha 24/09/2019
	 */
	@Override
	public RespuestaOperacionDto actualizaComponentesArchivosCertificados(Long pArchivoId, Long pUsuarioId)
	{
		RespuestaOperacionDto vRespuesta = new RespuestaOperacionDto();
		
		SreArchivos vSreArchivos = new SreArchivos ();
		vRespuesta.setOk(true);
		
		vSreArchivos = iArchivoTmpDomain.actualizarEstadoArchivo(pArchivoId);
		
		if(vSreArchivos!=null)
		{
			//se recupera los componentes archivos
			List<SreComponentesArchivos> vComponentesArchivos = iComponentesArchivosDomain.obtieneComponentesArchivosLista(pArchivoId);
			
			if(vComponentesArchivos!=null || vComponentesArchivos.size()!=0)
			{
				for (SreComponentesArchivos sreComponentesArchivos : vComponentesArchivos) 
				{
					SreComponentesArchivos vSreComponentesArchivos  = new SreComponentesArchivos();
					vSreComponentesArchivos = iComponentesArchivosTmpDomain.actualizarEstadoComponenteArchivo(sreComponentesArchivos.getComponenteArchivoId());
					
					if(vSreComponentesArchivos!=null)
					{
						List<SreComponentesCertificados> listaComponentesSre = new ArrayList<>();			
						listaComponentesSre = iComponentesCertificadosDomain.obtenerComponentesCertificados(sreComponentesArchivos.getComponenteArchivoId());
						
						if(listaComponentesSre!=null || listaComponentesSre.size()!=0)
						{
							for (SreComponentesCertificados sreComponentesCertificados : listaComponentesSre) 
							{
								sreComponentesCertificados.setUsuarioUltimaModificacionId(pUsuarioId);
								sreComponentesCertificados.setFechaUltimaModificacion(new Date());
								
								sreComponentesCertificados = iComponentesCertificadosTmpDomain.actualizarEstadoComponenteCertificado(sreComponentesCertificados);
								
								if(sreComponentesCertificados!=null)
								{
									vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.ACTUALIZAR_DATOS_SISTEMAS_EXITO));									
								}
								else
								{
									vRespuesta.setOk(false);
									vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
									break;
								}
							}
						}
						else
						{
							vRespuesta.setOk(false);
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
							break;
						}
					}
					else
					{
						vRespuesta.setOk(false);
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
					}
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
		
		return vRespuesta;
	}
}
