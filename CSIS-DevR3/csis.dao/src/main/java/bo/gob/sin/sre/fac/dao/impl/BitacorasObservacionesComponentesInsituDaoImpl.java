package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.sre.fac.dao.IBitacorasObservacionesComponentesInsituDao;
import bo.gob.sin.sre.fac.model.SreBitacorasObservacionesComponentesInsitu;

@Repository
@Transactional
public class BitacorasObservacionesComponentesInsituDaoImpl
		extends AbstractGenericDao<SreBitacorasObservacionesComponentesInsitu>
		implements IBitacorasObservacionesComponentesInsituDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(BitacorasObservacionesComponentesInsituDaoImpl.class);

	@Override
	public List<SreBitacorasObservacionesComponentesInsitu> obtenerDetalleObservacionesComponentesInsituPorCodigoObservacion(
			long pCodigoObservacionComponenteInSitu) {
		List<SreBitacorasObservacionesComponentesInsitu> vEntidad = new ArrayList<SreBitacorasObservacionesComponentesInsitu>();
		try {
			String vhql = "FROM SreBitacorasObservacionesComponentesInsitu p WHERE p.observacionComponenteInsituId=:pCodigoObservacionComponenteInSitu and p.estadoId= 'AC' ";
			vEntidad = getSession().createQuery(vhql)
					.setParameter("pCodigoObservacionComponenteInSitu", pCodigoObservacionComponenteInSitu)
					.getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		} catch (NoResultException e) {
			return new ArrayList<SreBitacorasObservacionesComponentesInsitu>();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

}
