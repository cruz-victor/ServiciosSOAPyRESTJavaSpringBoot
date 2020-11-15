package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IAsignacionesCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IAsignacionesCertificacionesABMDomain;
import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;

@Service
@Transactional
public class AsignacionesCertificacionesABMDomainImpl implements IAsignacionesCertificacionesABMDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AsignacionesCertificacionesABMDomainImpl.class);

	@Autowired
	private IAsignacionesCertificacionesDao iAsignacionesCertificacionesDao;

	@Override
	public boolean registrarAsignacionEquipoCertificacion(List<SreAsignacionesCertificaciones> pAsignaciones) {
		boolean vRespuesta = false;
		try {
			pAsignaciones.forEach(vEntidad -> iAsignacionesCertificacionesDao.save(vEntidad));
			vRespuesta = true;
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pAsignaciones));
			LOG.info("Datos de asignacion de certificacion no registrado.");
		}
		return vRespuesta;
	}

	@Override
	public boolean modificarAsignacionEquipoCertificacion(List<SreAsignacionesCertificaciones> pAsignaciones) {
		boolean vRespuesta = false;
		try {
			pAsignaciones.forEach(vEntidad -> iAsignacionesCertificacionesDao.saveOrUpdate(vEntidad));
			vRespuesta = true;
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pAsignaciones));
			LOG.info("Datos de asignacion de certificacion no modificado.");
		}
		return vRespuesta;
	}

}
