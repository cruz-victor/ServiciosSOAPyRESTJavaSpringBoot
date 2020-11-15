package bo.gob.sin.sre.fac.dao;

import bo.gob.sin.sre.fac.model.SreClasificadores;
import bo.gob.sin.str.ccs.dao.GenericDao;


public interface IClasificadoresDao extends GenericDao<SreClasificadores>{
	
	//IASC
	public SreClasificadores obtenerEntidadPorCodigoEquivalente(Integer pCodigoEquivalente, String pAgrupador);

}
