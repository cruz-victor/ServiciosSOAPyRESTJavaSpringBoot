package bo.gob.sin.sre.fac.cfec.domain;

import java.util.List;

import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresCompras;

public interface ISreFacErrorCompraDomain {

	public long registrarErrorCompra(SreFacRecepcionesErroresCompras pJsonEtapa, List<Integer> pListaErrores);

}