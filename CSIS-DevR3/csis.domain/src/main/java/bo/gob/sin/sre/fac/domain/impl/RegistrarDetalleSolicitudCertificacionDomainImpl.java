package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IDetalleSolicitudesCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IRegistrarDetalleSolicitudesCertificacionDomain;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

@Service
@Transactional(rollbackFor = Exception.class)

public class RegistrarDetalleSolicitudCertificacionDomainImpl implements IRegistrarDetalleSolicitudesCertificacionDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarDetalleSolicitudCertificacionDomainImpl.class);
	
	@Autowired
	private IDetalleSolicitudesCertificacionesDao iDetalleSolicitudesCertificacionesDao;
	
	//IASC - Registro detalle de la solicitud de certificación - 12/11/2018
	@Override
	public SreDetalleSolicitudesCertificaciones registrarDetalleSolicitudCertificacion(SreSolicitudCertificacion pSolicitud, long pSistemaId, int pModalidadId, String pCuis) {
		SreDetalleSolicitudesCertificaciones vRespuesta = new SreDetalleSolicitudesCertificaciones();
		
		try {
			vRespuesta.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
			vRespuesta.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioUltimaModficacionId());
			vRespuesta.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
			vRespuesta.setSistemaId(pSistemaId);
			vRespuesta.setModalidadFacturacionId(pModalidadId);
			vRespuesta.setCuis(pCuis);
			vRespuesta.setFechaRegistro(pSolicitud.getFechaRegistro());
			vRespuesta.setFechaUltimaModificacion(pSolicitud.getFechaUltimaModificacion());
			vRespuesta.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			iDetalleSolicitudesCertificacionesDao.save(vRespuesta);
			LOG.info("Datos contactos registrados.");
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return vRespuesta;
	}
}
