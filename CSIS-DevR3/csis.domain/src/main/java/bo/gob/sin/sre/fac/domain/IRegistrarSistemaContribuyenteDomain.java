package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;


public interface IRegistrarSistemaContribuyenteDomain {

	//IASC
	public SreSistemasContribuyentes registrarSistemasContribuyente(SreSistemas pSistemaACertificar, Long pContribuyenteId, Integer pModalidad);
	
}
