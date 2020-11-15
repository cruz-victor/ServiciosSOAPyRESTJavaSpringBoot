package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;
import bo.gob.sin.sre.fac.model.SrePruebasManuales;

public interface IPruebasManualesDao extends GenericDao<SrePruebasManuales> {

	// IASC
	public List<SrePruebasManuales> obtenerPruebasManuales();

	public List<SreFacRegistrosPruebasManuDetalle> obtieneListaPruebasManuales(long pSolicitudId, long pTramiteId,
			long pSistemaId);

	
}