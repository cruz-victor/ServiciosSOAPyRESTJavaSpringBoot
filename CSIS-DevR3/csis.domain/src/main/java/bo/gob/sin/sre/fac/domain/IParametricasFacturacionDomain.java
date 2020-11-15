package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreClasificadores;

public interface IParametricasFacturacionDomain {
	
	//IASC
	public SreClasificadores obtenerEntidadPorCodigoEquivalente(Integer pCodigoEquivalente, String pAgrupador);
}
