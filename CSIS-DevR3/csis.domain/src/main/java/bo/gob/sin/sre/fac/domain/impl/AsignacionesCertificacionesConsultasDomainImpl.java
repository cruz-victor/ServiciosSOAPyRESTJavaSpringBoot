package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IAsignacionesCertificacionesDao;
import bo.gob.sin.sre.fac.dao.IEquiposCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IAsignacionesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;
import bo.gob.sin.sre.fac.model.SreEquiposCertificaciones;

@Service
public class AsignacionesCertificacionesConsultasDomainImpl implements IAsignacionesCertificacionesConsultaDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AsignacionesCertificacionesConsultasDomainImpl.class);

	@Autowired
	private IAsignacionesCertificacionesDao iAsignacionesCertificacionesDao;

	@Autowired
	private IEquiposCertificacionesDao iEquiposCertificacionesDao;

	@Override
	public List<SreAsignacionesCertificaciones> obtenerAsignacionesEquipoCertificacionPorTramite(long pTramiteId) {

		List<SreAsignacionesCertificaciones> vRespuesta = new ArrayList<>();
		LOG.info("ingresando obtenerAsignacionesEquipoCertificacionPorTramite");
		try {
			vRespuesta = iAsignacionesCertificacionesDao.obtenerAsignacionesEquipoCertificacionPorTramite(pTramiteId);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar la lista de equipo asignado, existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtenerAsignacionesEquipoCertificacionPorTramite", vRespuesta);
		return vRespuesta;
	}

	@Override
	public List<SreEquiposCertificaciones> obtenerEquipoCertificacion(SreEquiposCertificaciones pEquipoCertificacion) {
		List<SreEquiposCertificaciones> vRespuesta = new ArrayList<>();
		LOG.info("ingresando obtenerEquipoCertificacion");
		try {
			vRespuesta = iEquiposCertificacionesDao.obtenerEquipoCertificacion(pEquipoCertificacion);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar la lista de equipos de certifiaccion, existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtenerEquipoCertificacion", vRespuesta);
		return vRespuesta;
	}
}
