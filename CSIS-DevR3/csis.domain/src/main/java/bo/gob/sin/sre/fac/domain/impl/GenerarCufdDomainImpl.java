package bo.gob.sin.sre.fac.domain.impl;



import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sau.ausu.dto.RespuestaServicioDto;
import bo.gob.sin.sre.fac.dao.ICufdDao;
import bo.gob.sin.sre.fac.domain.IGenerarCufdDomain;
import bo.gob.sin.sre.fac.model.SreRegistrosCufd;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.util.facturacion.Utiles;

@Service
@Transactional

public class GenerarCufdDomainImpl implements IGenerarCufdDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(GenerarCufdDomainImpl.class);
	
	private static final char[] HEXADECIMALES = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
	
	@Autowired
	ICufdDao iCufdDao;

	//IASC
	@Override
	public String generarCodigoCufd(Integer pSucursalId, Long pNitEmisor, String pCuis)
	{
		String vCufd = null;
		try {
			Utiles utiles = new Utiles();
			
			String vSucursal = String.valueOf(pSucursalId);
			String vNit = String.valueOf(pNitEmisor);
			
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMddhhmmssmmm");
			String vFecha = formateador.format(new Date());
			
			String input = utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_NIT, vNit) + vFecha + 
					       utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_CUIS, pCuis) + 
					       utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_SUCURSAL, vSucursal);
						
			vCufd = this.cryptMD5(input);	
			LOG.info("Cufd generado correctamente");

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pNitEmisor));
			LOG.info("Error, Excepcion al momento de generar");
		} 
		return vCufd;
	}
	
	//IASC	
	public String cryptMD5(String textoPlano)
	{
	    try
	    {
	       MessageDigest msgdgt = MessageDigest.getInstance("MD5");
	       byte[] bytes = msgdgt.digest(textoPlano.getBytes());
	       StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
	       for (int i = 0; i < bytes.length; i++)
	       {
	           int low = (int)(bytes[i] & 0x0f);
	           int high = (int)((bytes[i] & 0xf0) >> 4);
	           strCryptMD5.append(HEXADECIMALES[high]);
	           strCryptMD5.append(HEXADECIMALES[low]);
	       }
	       return strCryptMD5.toString();
	    } catch (NoSuchAlgorithmException e) {
	       return null;
	    }
	}
	
	//IASC
//	public RespuestaCufdDto registrarCufd(SreRegistrosCufd pSolicitud){
//		RespuestaCufdDto vRespuesta = new RespuestaCufdDto();
//		
//		SreRegistrosCufd vRegistro = new SreRegistrosCufd();
//		java.util.Date vFechaHoy = new Date();
//		int vEstadoId = ConstFacturacion.ESTADO_REGISTRO_CUFD_VIGENTE;
//		RespuestaServicioDto respuesta =new RespuestaServicioDto();
//		respuesta.setOk(false);
//		
//		try {
//				String vCufd = this.generarCodigoCufd(pSolicitud.getSucursalId(), pSolicitud.getNitEmisor(), pSolicitud.getCuis());
//				vRegistro.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
//				vRegistro.setUsuarioUltimaModficacion(pSolicitud.getUsuarioRegistroId());
//				vRegistro.setSistemaId(pSolicitud.getSistemaId());
//				vRegistro.setNitEmisor(pSolicitud.getNitEmisor());
//				vRegistro.setCufd(vCufd);
//				vRegistro.setFechaInicio(vFechaHoy);
//				
//				Calendar c = Calendar.getInstance();
//				c.setTime(vRegistro.getFechaInicio());
//				c.add(Calendar.DATE, 1);
//		
//				vRegistro.setFechaFin(c.getTime());
//				vRegistro.setSucursalId(pSolicitud.getSucursalId());
//				vRegistro.setFechaRegistro(vFechaHoy);
//				vRegistro.setFechaUltimaModificacion(vFechaHoy);
//				vRegistro.setEstadoId("AC");
//				vRegistro.setEstadoRegistroCufdId(vEstadoId);
//				vRegistro.setCuis(pSolicitud.getCuis());
//				
//				iCufdDao.save(vRegistro);
//					
//				vRespuesta.setOk(true);
//				vRespuesta.setNit(pSolicitud.getNitEmisor());
//				vRespuesta.setCufd(vCufd);
//				vRespuesta.setFechaFin(vRegistro.getFechaFin());
//				LOG.info("Generacion del CUFD exitosa");
//				
//		}catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud.getNitEmisor()));
//			LOG.info("CUFD no registrado.");
//			vRespuesta.setOk(false);
//		}
//		return vRespuesta;
//	}
	/**
	    * Objetivo: Actualiza el CUFD cuando se solicita uno nuevo.
	    * Creado por: Ivan Salas.
	    * Fecha: 25/06/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	  */
	public SreRegistrosCufd registrarCufd(SreRegistrosCufd pSolicitud){
		SreRegistrosCufd vRespuesta = new SreRegistrosCufd();
		LOG.info("Ingresando a registrarCufd");
		SreRegistrosCufd vRegistro = new SreRegistrosCufd();
		java.util.Date vFechaHoy = new Date();
		int vEstadoId = ConstFacturacion.ESTADO_REGISTRO_CUFD_VIGENTE;
		RespuestaServicioDto respuesta =new RespuestaServicioDto();
		respuesta.setOk(false);
		 
		try {
				String vCufd = this.generarCodigoCufd(pSolicitud.getSucursalId(), pSolicitud.getNitEmisor(), pSolicitud.getCuis());
				vRegistro.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
				vRegistro.setUsuarioUltimaModficacion(pSolicitud.getUsuarioRegistroId());
				vRegistro.setSistemaId(pSolicitud.getSistemaId());
				vRegistro.setNitEmisor(pSolicitud.getNitEmisor());
				vRegistro.setCufd(vCufd);
				vRegistro.setFechaInicio(vFechaHoy);
				
				Calendar c = Calendar.getInstance();
				c.setTime(vRegistro.getFechaInicio());
				c.add(Calendar.DATE, 1);
		
				vRegistro.setFechaFin(c.getTime());
				vRegistro.setSucursalId(pSolicitud.getSucursalId());
				vRegistro.setFechaRegistro(vFechaHoy);
				vRegistro.setFechaUltimaModificacion(vFechaHoy);
				vRegistro.setEstadoId("AC");
				vRegistro.setEstadoRegistroCufdId(vEstadoId);
				vRegistro.setCuis(pSolicitud.getCuis());
				
				iCufdDao.save(vRegistro);
				
				vRespuesta.setNitEmisor(pSolicitud.getNitEmisor());
				vRespuesta.setCufd(vCufd);
				vRespuesta.setFechaFin(vRegistro.getFechaFin());
				vRespuesta.setCufdId(vRegistro.getCufdId());
				LOG.info("Generacion del CUFD exitosa");
				
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud.getNitEmisor()));
			LOG.info("CUFD no registrado.");
			
		}
		LOG.info("Saliendo de registrarCufd");
		return vRespuesta;
	}
	
	/**
	    * Objetivo: Actualiza el CUFD cuando se solicita uno nuevo.
	    * Creado por: Ivan Salas.
	    * Fecha: 25/06/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	    * Creado por: Peter Flores.
	    * Fecha: 24/08/2018
	  */
	public SreRegistrosCufd registrarCufdWeb(SreRegistrosCufd pSolicitud){
		SreRegistrosCufd vRespuesta = new SreRegistrosCufd();
		LOG.info("Ingresando a registrarCufd");
		SreRegistrosCufd vRegistro = new SreRegistrosCufd();
		java.util.Date vFechaHoy = new Date();	
		try {
				String vCufd = this.generarCodigoCufd(pSolicitud.getSucursalId(), pSolicitud.getNitEmisor(), pSolicitud.getCuis());
				vRegistro.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
				vRegistro.setUsuarioUltimaModficacion(pSolicitud.getUsuarioRegistroId());
				vRegistro.setSistemaId(pSolicitud.getSistemaId());
				vRegistro.setNitEmisor(pSolicitud.getNitEmisor());
				vRegistro.setCufd(vCufd);
				vRegistro.setFechaInicio(vFechaHoy);
				
				Calendar c = Calendar.getInstance();
				c.setTime(vRegistro.getFechaInicio());
				c.add(Calendar.DATE, ConstFacturacion.DIAS_FECHA_FIN_REGISTRO_CUF);
		
				vRegistro.setFechaFin(c.getTime());
				vRegistro.setSucursalId(pSolicitud.getSucursalId());
				vRegistro.setFechaRegistro(vFechaHoy);
				vRegistro.setFechaUltimaModificacion(vFechaHoy);
				vRegistro.setEstadoId("AC");
				vRegistro.setEstadoRegistroCufdId(ConstFacturacion.ESTADO_REGISTRO_CUFD_VIGENTE);
				vRegistro.setCuis(pSolicitud.getCuis());
				
				iCufdDao.save(vRegistro);
				
				vRespuesta.setFechaFin(vRegistro.getFechaFin());
				vRespuesta.setCufdId(vRegistro.getCufdId());
				c=null;
				LOG.info("Generacion del CUFD exitosa");
				
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud.getNitEmisor()));
			LOG.info("CUFD no registrado.");
			
		}
		vRegistro=null;
		vFechaHoy=null;

		LOG.info("Saliendo de registrarCufd");
		return vRespuesta;
	}
	
	//FRRA
	@Override
	public Long verificaCufd(Long sistemaId, String cufd, Integer sucursal, Integer estadoId) {
		long vVerificacion=0;
		try {
				vVerificacion = iCufdDao.verificaCufd(sistemaId, cufd, sucursal, estadoId);
 			    
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(sistemaId));
		}
		return vVerificacion;
	}
	
	//IASC
	@Override
	public Long verificaCufdPorNitSucursal(Long nit, String cufd, Integer sucursal, Integer estadoId) {
		long vVerificacion=0;
		try {
				vVerificacion = iCufdDao.verificaCufdPorNitSucursal(nit, cufd, sucursal, estadoId);
 			    
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(nit));
		}
		return vVerificacion;
	}
	
	
	 /**
	    * Objetivo: Obtener ultimo registro cufd activo
	    * Creado por: Wilson Limachi.
	    * Fecha: 18/05/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	 */
	@Override
	public SreRegistrosCufd recuperCufdPorNitSucursalSistema(Long pNit, Integer pSucursalId, Long pSistemaId) 
	{		
		LOG.info("Ingresando a recuperarCufdPorNitSucursalSistema");
		SreRegistrosCufd vSreRegistrosCufd = new SreRegistrosCufd();
		try 
		{
			vSreRegistrosCufd = iCufdDao.recuperCufdPorNitSucursalSistema(pNit, pSucursalId, pSistemaId); 			    
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pNit));
			vSreRegistrosCufd = null;
		}
		LOG.info("saliendo de recuperarCufdPorNitSucursalSistema ");
		return vSreRegistrosCufd;
	}
	
	//IASC - Actualiza el CUFD cuando se solicita uno nuevo - 25/06/2018
	 /**
	    * Objetivo: Actualiza el CUFD cuando se solicita uno nuevo.
	    * Creado por: Ivan Salas.
	    * Fecha: 25/06/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	  */
	public boolean actualizarCufd(SreRegistrosCufd pSolicitud, Long pUsuarioId){
		LOG.info("Ingresando a actualizarCufd");
		//SreRegistrosCufd vRegistro = new SreRegistrosCufd();
		java.util.Date vFechaHoy = new Date();
		int vEstadoId = ConstFacturacion.ESTADO_REGISTRO_CUFD_NO_VIGENTE;
		boolean vRespuesta = false;
		
		try {
			pSolicitud.setUsuarioUltimaModficacion(pUsuarioId);
			pSolicitud.setFechaFin(vFechaHoy);
			pSolicitud.setFechaUltimaModificacion(vFechaHoy);
			pSolicitud.setEstadoRegistroCufdId(vEstadoId);
				
				iCufdDao.saveOrUpdate(pSolicitud);
				LOG.info("Actualizacion Exitosa de Cufd");
				vRespuesta = true;
				
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud.getNitEmisor()));
		}
		LOG.info("Saliendo de ActualizarCufd");
		return vRespuesta;
	}

	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene registro cufd vigente
	 * @param pNit        Nit del emisor
	 * @param pSucursalId sucursal del emisor
	 * @return registro de tipo SreRegistrosCufd
	 * @fecha 03/10/2018
	 */
	public SreRegistrosCufd obtenerRegistroCufdVigente(Long pNit, Integer pSucursalId) 
	{		
		return iCufdDao.obtenerRegistroCufdVigente(pNit, pSucursalId);		
	}
	
}