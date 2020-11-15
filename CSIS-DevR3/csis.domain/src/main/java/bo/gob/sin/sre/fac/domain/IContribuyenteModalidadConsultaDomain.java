package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreContribuyentesModalidades;

public interface IContribuyenteModalidadConsultaDomain {

	//IASC
	public long obtieneModalidadContribuyenteNormativa(Long pNit, Integer pModalidadId);
	
	public SreContribuyentesModalidades obtieneModalidadContribuyente(Long pNit);
}
