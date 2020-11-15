package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IObservacionesComponentesInsituDao;
import bo.gob.sin.sre.fac.model.SreObservacionesComponentesInsitu;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
@Repository
@Transactional
public class ObservacionesComponentesInsituDaoImpl extends AbstractGenericDao<SreObservacionesComponentesInsitu>
		implements IObservacionesComponentesInsituDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<SreObservacionesComponentesInsitu> obtenerObservacionesComponentesInsituPorCodigoCertificacion(
			long pCodigoCertificacion) {

		List<SreObservacionesComponentesInsitu> vResultado = new ArrayList<SreObservacionesComponentesInsitu>();
		try {
			String vhql = "FROM SreObservacionesComponentesInsitu a "
					+ "WHERE a.solicitudCertificacionId = :pCodigoCertificacion " + "and a.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pCodigoCertificacion", pCodigoCertificacion)
					.getResultList();

			return vResultado;
		} catch (NoResultException e) {
			return new ArrayList<SreObservacionesComponentesInsitu>();
		} catch (Exception e) {
			return null;
		}
	}

}
