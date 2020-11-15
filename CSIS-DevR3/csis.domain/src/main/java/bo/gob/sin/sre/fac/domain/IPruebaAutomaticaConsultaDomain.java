package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;

public interface IPruebaAutomaticaConsultaDomain {

	//IASC
	public List<SrePruebasAutomaticas> obtenerPruebasAutomaticasPorDocFiscal(Integer pTipoDocumentoFiscalId, Integer pModalidadId);
}
