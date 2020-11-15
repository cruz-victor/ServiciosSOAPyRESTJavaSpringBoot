package bo.gob.sin.sre.fac.dao;

import bo.gob.sin.str.ccs.dao.GenericDao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;

public interface IRegistroPruebasManualesDao extends GenericDao<SreFacRegistrosPruebasManuales> {

	public SreFacRegistrosPruebasManuales modificaObservacionEstado(Long RegistroPruebaManualId, long pUsuario, int pEstado) ;
	
	public List<SreFacRegistrosPruebasManuales> obtieneListaRegistroPruebasManuales(long pSolicitudId);
	
	public boolean modificaObservacionEstado(SreFacRegistrosPruebasManuales pPruebaManual);
}
