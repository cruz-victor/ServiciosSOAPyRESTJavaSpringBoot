package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISolicitudCertificacionDao;
import bo.gob.sin.sre.fac.domain.ISolicitudesCertificacionesTramitesConsultaDomain;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

@Service
@Transactional
public class SolicitudesCertificacionesTramitesConsultaDomainImpl implements ISolicitudesCertificacionesTramitesConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SolicitudesCertificacionesTramitesConsultaDomainImpl.class);

	@Autowired
	ISolicitudCertificacionDao iSolicitudCertificacionDao;
	
	//IASC - Recupera los datos del tramite - 25/11/2018
	@Override
	public SreSolicitudCertificacion obtenerDatosTramiteCertificacion(long pTramiteId) {
		LOG.info("Ingresando a obtenerDatosTramiteCertificacion");
		SreSolicitudCertificacion vRespuesta = new SreSolicitudCertificacion();
		try {
			vRespuesta = iSolicitudCertificacionDao.obtenerDatosTramiteCertificacion(pTramiteId);
		}catch(Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
		}
		LOG.info("Saliendo de obtenerDatosTramiteCertificacion");
		return vRespuesta;
	}
		
}
