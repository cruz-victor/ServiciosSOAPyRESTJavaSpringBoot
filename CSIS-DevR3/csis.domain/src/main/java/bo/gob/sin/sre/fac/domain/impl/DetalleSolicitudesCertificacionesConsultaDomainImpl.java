package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IDetalleSolicitudesCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IDetalleSolicitudesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

@Service
@Transactional
public class DetalleSolicitudesCertificacionesConsultaDomainImpl implements IDetalleSolicitudesCertificacionesConsultaDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(DetalleSolicitudesCertificacionesConsultaDomainImpl.class);
	
	@Autowired
	IDetalleSolicitudesCertificacionesDao iDetalleSolicitudesCertificacionesDao;
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * 
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 14/11/2018
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return   Devuelve objeto SreDetalleSolicitudesCertificaciones.
	 */
	@Override
	public List<SreDetalleSolicitudesCertificaciones> obtenerListaDetalleSolicitudCertificacionId(long pSolicitudCertificacionId) {
		LOG.info("Ingresando obtenerListaDetalleSolicitudCertificacionId pSolicitudCertificacionId ={} ", pSolicitudCertificacionId);
		List<SreDetalleSolicitudesCertificaciones> vResultado = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try {
			vResultado = iDetalleSolicitudesCertificacionesDao.obtenerListaDetalleSolicitudCertificacionId(pSolicitudCertificacionId);
		} catch (Exception e) {
			vResultado = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
		}
		LOG.info("Saliendo de obtenerListaDetalleSolicitudCertificacionId={}", vResultado);
		return vResultado;
	}
	
	/**
	 * Recuperar Lista Detalle de Tipos documentos Sistemas por SolicitudCertificacionId.
	 * @author: Wilson Limachi Choque
	 * @Fecha: 17/07/2019
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return Devuelve objeto Lista SreDetalleSolicitudesCertificaciones.
	 */
	@Override
	public List<SreTiposDocumentosSistemas> obtenerListaTipoDocumentosSistema(long pSolicitudCertificacionId) 
	{
		LOG.info("Ingresando obtenerListaTipoDocumentosSistema pSolicitudCertificacionId ={} ", pSolicitudCertificacionId);
		List<SreTiposDocumentosSistemas> vResultado = new ArrayList<SreTiposDocumentosSistemas>();
		try 
		{
			vResultado = iDetalleSolicitudesCertificacionesDao.obtenerListaTipoDocumentosSistema(pSolicitudCertificacionId);
		} 
		catch (Exception e) 
		{
			vResultado = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
		}
		
		LOG.info("Saliendo de obtenerListaDetalleSolicitudCertificacionId={}", vResultado);
		return vResultado;
	}
	
	/**
	 * @author rosario.garcia
	 * @fecha 27/11/2018
	 * @param pSistemaId
	 * @return  SreDetalleSolicitudesCertificaciones a traves del sistemaId
	 * @modificado 29/11/2018
	 */
	@Override 
	public List<SreDetalleSolicitudesCertificaciones> obtenerSolicitudesCertificacionIdSistema(Long pSistemaId){
		LOG.info("Ingresando a recuperar obtenerSolicitudesCertificacionIdSistema "+pSistemaId);
		List<SreDetalleSolicitudesCertificaciones> vRespuesta = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try {
			vRespuesta = iDetalleSolicitudesCertificacionesDao.recuperaSolicitudesCertificacionPorIdSistema(pSistemaId);
			return vRespuesta;
		}catch(Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			return null;
		}
	}
	
	/**
	 * @author wilson.limachi
	 * @fecha 04/09/2019
	 * @param pSistemaId
	 * @param pContribuyenId
	 * @return  List<SreDetalleSolicitudesCertificaciones> a traves del sistemaId
	 * @modificado 29/11/2018
	 */
	@Override 
	public List<SreDetalleSolicitudesCertificaciones> recuperaSolicitudesDetalleCertificacionPorIdSistema(Long pSistemaId, Long pIdContribuyente)
	{
		LOG.info("Ingresando a recuperar obtenerSolicitudesCertificacionIdSistema "+pSistemaId);
		List<SreDetalleSolicitudesCertificaciones> vRespuesta = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try 
		{
			vRespuesta = iDetalleSolicitudesCertificacionesDao.recuperaSolicitudesDetalleCertificacionPorIdSistema(pSistemaId, pIdContribuyente);
			return vRespuesta;
		}
		catch(Exception e) 
		{
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			return null;
		}
	}
}
