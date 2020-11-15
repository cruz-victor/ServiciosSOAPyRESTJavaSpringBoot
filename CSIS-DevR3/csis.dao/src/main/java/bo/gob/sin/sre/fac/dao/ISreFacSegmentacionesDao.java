package bo.gob.sin.sre.fac.dao;



import bo.gob.sin.sre.fac.model.SreFacSegmentaciones;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ISreFacSegmentacionesDao extends GenericDao<SreFacSegmentaciones> {
	
	
		SreFacSegmentaciones obtenerPorNit(long nit);
	
}
