package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;

public interface IPruebasManualesDomain {
	public List<SreFacRegistrosPruebasManuDetalle> obtieneListaPruebasManuales(long pSolicitudId, long pTramiteId,
			long pSistemaId);
	public SreFacRegistrosPruebasManuales modificaObservacionEstado(Long pRegistroPruebaManualId, long pUsuario, int pEstado);
	
	public List<SreFacRegistrosPruebasManuales> obtieneListaRegistroPruebasManuales(long pSolicitudId);
	
	public boolean modificaObservacionEstado(SreFacRegistrosPruebasManuales pPruebaManual);
}
