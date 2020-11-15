package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.ISolicitudCertificacionDao;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionABMDomain;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

@Service
@Transactional
public class SolicitudCertificacionABMDomainImpl implements ISolicitudCertificacionABMDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SolicitudCertificacionABMDomainImpl.class);

	@Autowired
	ISolicitudCertificacionDao iSolicitudCertificacionDao;
	
	/**
	 * Cambiar estado en Solicitud Certificacion 
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 19/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitudCertificacionId, id de la tabla solicitud certificacion
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de solicitud certificacion
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
	@Override
	public SreSolicitudCertificacion cambiarEstadoSolicitudCertificacion(long pSolicitudCertificacionId, long pUsuario, int pEstadoId) {
	
		SreSolicitudCertificacion vResultado = new SreSolicitudCertificacion();
		java.util.Date vFecha = new Date();
		try {
			vResultado = iSolicitudCertificacionDao.findById(pSolicitudCertificacionId);
			vResultado.setFechaUltimaModificacion(vFecha);
			vResultado.setUsuarioUltimaModficacionId(pUsuario);
			vResultado.setEstadoSolicitudCertificacionId(pEstadoId);

			iSolicitudCertificacionDao.saveOrUpdate(vResultado);
			
		} catch (Exception e) {
			return null;
		}
		return vResultado;
	}
}
