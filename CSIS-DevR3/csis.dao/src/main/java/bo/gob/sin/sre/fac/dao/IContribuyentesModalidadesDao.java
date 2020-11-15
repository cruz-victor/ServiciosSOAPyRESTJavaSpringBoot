package bo.gob.sin.sre.fac.dao;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreContribuyentesModalidades;

//IASC
public interface IContribuyentesModalidadesDao extends GenericDao<SreContribuyentesModalidades>{

	//IASC
	public long obtenerModalidadContribuyenteNormativa(Long pNit, Integer pModalidadId);
	
	//SDIM
	public boolean verificarContribuyenteRequiereVisitaInsitu(Long pNit);
	
	public SreContribuyentesModalidades obtenerModalidadContribuyente(Long pNit);
}
