package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreRutasServicios;

public interface IRutasServiciosConsultaDomain {

	//IASC
	public SreRutasServicios obtieneRutasServicios(int pModalidadId, int pTipoDocumentoSectorId);
	
	//IASC
	public List<SreRutasServicios> obtieneRutasServiciosComunes(int pModalidadId, int pTipoDocumentoSectorId);

}
