package bo.gob.sin.sre.fac.cfec.domain;

import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresCompras;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresComprasDetalles;

public interface ISreFacErrorCompraDetalleDomain {

	public long registrarErrorCompraDetalle(SreFacRecepcionesErroresComprasDetalles pSolicitud);
}