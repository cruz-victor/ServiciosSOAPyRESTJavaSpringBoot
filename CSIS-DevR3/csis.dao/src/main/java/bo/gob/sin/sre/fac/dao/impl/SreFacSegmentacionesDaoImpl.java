package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.ISreFacSegmentacionesDao;
import bo.gob.sin.sre.fac.model.SreFacSegmentaciones;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class SreFacSegmentacionesDaoImpl extends AbstractGenericDao<SreFacSegmentaciones> implements ISreFacSegmentacionesDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SreFacSegmentaciones obtenerPorNit(long pNit) {
		
		String vQuery = "SELECT a FROM SreFacSegmentaciones a WHERE a.nit = :pNit";
		SreFacSegmentaciones resultado =  (SreFacSegmentaciones)getSession().createQuery(vQuery).setParameter("pNit", pNit).getSingleResult();
		
		return resultado;
	}

}
