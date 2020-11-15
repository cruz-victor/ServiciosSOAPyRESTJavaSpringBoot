package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreRutasServicios;

public interface IRutasServiciosDao extends GenericDao<SreRutasServicios> {

	//IASC
	public SreRutasServicios obtieneRutas(int pModalidadId, int pTipoDocumentoSectorId);
	
	//IASC
	public List<SreRutasServicios> obtieneRutasComunes(int pModalidadId, int pTipoDocumentoSectorId);
	
}