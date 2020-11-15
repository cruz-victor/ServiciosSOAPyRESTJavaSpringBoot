package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISolicitudCertificacionDao;
import bo.gob.sin.sre.fac.domain.IRegistrarSolicitudCertificacionDomain;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegistrarSolicitudCertificacionDomainImpl implements IRegistrarSolicitudCertificacionDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarSolicitudCertificacionDomainImpl.class);
	
	@Autowired
	ISolicitudCertificacionDao iSolicitudCertificacionDao;
	
	/**3
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	@Override
	public SreSolicitudCertificacion registrarSolicitudCertificacion(long pContribuyenteId, long pTramite, long pUsuario, long pSistemaId){

		SreSolicitudCertificacion vRegistroSolicitud = new SreSolicitudCertificacion();
		java.util.Date vFechaHoy = new Date();
		try {
			vRegistroSolicitud.setUsuarioRegistroId(pUsuario);
			vRegistroSolicitud.setUsuarioUltimaModficacionId(pUsuario);
			vRegistroSolicitud.setTramiteId(pTramite);
			vRegistroSolicitud.setPersonaContribuyenteId(pContribuyenteId);
			vRegistroSolicitud.setTipoSolicitudCertificacionId(ConstFacturacion.TIPO_SOLICITUD_ID_NUEVA);
			vRegistroSolicitud.setEstadoSolicitudCertificacionId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO);
			vRegistroSolicitud.setFechaSolicitud(vFechaHoy);
			vRegistroSolicitud.setFechaRegistro(vFechaHoy);
			vRegistroSolicitud.setFechaUltimaModificacion(vFechaHoy);
			vRegistroSolicitud.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			vRegistroSolicitud.setSistemaId(pSistemaId);
			vRegistroSolicitud.setEstadoPruebaSolicitudId(ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_PENDIENTE);
			iSolicitudCertificacionDao.save(vRegistroSolicitud);

			LOG.info("Datos de solicitud certificacion registrados.");

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSolicitud));
			LOG.info("Datos solicitud certificacion no registrados.");
			vRegistroSolicitud.setSolicitudCertificacionId(0L); 
		}
		return vRegistroSolicitud;
	}
	
	/**3
     * Objetivo: registrar recertificacion solicitud de sistemas.
     * Creado por: Wilson Limachi.
     * Fecha: 20/08/2019
     * Modificado por: Wilson Limachi
     * Fecha de Modificacion: 20/08/2019
     */
	@Override
	public SreSolicitudCertificacion registrarSolicitudRecertificacionCertificacion(long pContribuyenteId, long pTramite, long pUsuario, long pSistemaId){

		SreSolicitudCertificacion vRegistroSolicitud = new SreSolicitudCertificacion();
		java.util.Date vFechaHoy = new Date();
		try {
			vRegistroSolicitud.setUsuarioRegistroId(pUsuario);
			vRegistroSolicitud.setUsuarioUltimaModficacionId(pUsuario);
			vRegistroSolicitud.setTramiteId(pTramite);
			vRegistroSolicitud.setPersonaContribuyenteId(pContribuyenteId);
			vRegistroSolicitud.setTipoSolicitudCertificacionId(ConstFacturacion.TIPO_SOLICITUD_ID_ACTUALIZACION);
			vRegistroSolicitud.setEstadoSolicitudCertificacionId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO);
			vRegistroSolicitud.setFechaSolicitud(vFechaHoy);
			vRegistroSolicitud.setFechaRegistro(vFechaHoy);
			vRegistroSolicitud.setFechaUltimaModificacion(vFechaHoy);
			vRegistroSolicitud.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			vRegistroSolicitud.setSistemaId(pSistemaId);
			vRegistroSolicitud.setEstadoPruebaSolicitudId(ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_PENDIENTE);
			iSolicitudCertificacionDao.save(vRegistroSolicitud);

			LOG.info("Datos de solicitud certificacion registrados.");

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSolicitud));
			LOG.info("Datos solicitud certificacion no registrados.");
			vRegistroSolicitud.setSolicitudCertificacionId(0L); 
		}
		return vRegistroSolicitud;
	}
	
	/**
     * Objetivo: registrar recertificacion solicitud de sistemas.
     * Creado por: Wilson Limachi.
     * Fecha: 22/08/2019
     * Modificado por: Wilson Limachi
     */
	@Override
	public SreSolicitudCertificacion actualizarEstadoHistoricoCertificadoSolicitudCertificacion(long pSolicitudCertificaiconId, long pUsuario) 
	{		
		java.util.Date vFechaHoy = new Date();
		SreSolicitudCertificacion vRegistroSolicitud = new SreSolicitudCertificacion();
		
		try 
		{
			vRegistroSolicitud = iSolicitudCertificacionDao.findById(pSolicitudCertificaiconId);
			vRegistroSolicitud.setUsuarioUltimaModficacionId(pUsuario);
			vRegistroSolicitud.setFechaUltimaModificacion(vFechaHoy);
			vRegistroSolicitud.setEstadoSolicitudCertificacionId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO);
			
			iSolicitudCertificacionDao.saveOrUpdate(vRegistroSolicitud);
		
		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSolicitud));
			LOG.info("No se pudo actualizar.");
			vRegistroSolicitud.setSolicitudCertificacionId(0L); 
		}
		
		return vRegistroSolicitud;
	}

	@Override
	public SreSolicitudCertificacion registrarFechaCertificadoSolicitudCertificacion(long pUsuario, long pSolicitud, Date pFechaAprobacion) {		
		SreSolicitudCertificacion vRegistroSolicitud = new SreSolicitudCertificacion();
		vRegistroSolicitud = iSolicitudCertificacionDao.findById(pSolicitud);
		vRegistroSolicitud.setFechaAprobacion(pFechaAprobacion);
		vRegistroSolicitud.setFechaCertificado(pFechaAprobacion);
		vRegistroSolicitud.setFechaEmisionCertificado(pFechaAprobacion);
		vRegistroSolicitud.setUsuarioUltimaModficacionId(pUsuario);	
		iSolicitudCertificacionDao.saveOrUpdate(vRegistroSolicitud);
		return vRegistroSolicitud;
	}
}
