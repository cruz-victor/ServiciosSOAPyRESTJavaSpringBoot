package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.sre.fac.dao.IAsignacionesCertificacionesDao;
import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;

@Repository
public class AsignacionesCertificacionesDaoImpl extends AbstractGenericDao<SreAsignacionesCertificaciones>
		implements IAsignacionesCertificacionesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(AsignacionesCertificacionesDaoImpl.class);

	@Override
	public List<SreAsignacionesCertificaciones> obtenerAsignacionesEquipoCertificacionPorTramite(long pTramiteId) {
		List<SreAsignacionesCertificaciones> vEntidad = new ArrayList<SreAsignacionesCertificaciones>();
		try {
			String vhql = "FROM SreAsignacionesCertificaciones p WHERE p.tramiteId=:pTramiteId and p.estadoId= 'AC' ";
			vEntidad = getSession().createQuery(vhql)
					.setParameter("pTramiteId", pTramiteId).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		} catch (NoResultException e) {
			return new ArrayList<SreAsignacionesCertificaciones>();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

}
