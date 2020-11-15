package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreFacSegmentaciones;

public interface IConsultaFacturacionDomain {
	
	SreFacSegmentaciones obtenerPorNit(long nit);
}
