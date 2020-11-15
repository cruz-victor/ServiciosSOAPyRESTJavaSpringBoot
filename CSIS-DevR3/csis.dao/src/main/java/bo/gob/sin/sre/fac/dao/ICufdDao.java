package bo.gob.sin.sre.fac.dao;



import bo.gob.sin.sre.fac.model.SreRegistrosCufd;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ICufdDao extends GenericDao<SreRegistrosCufd> {

	//FRRA
	public long verificaCufd(Long pSistemaId, String pCufd, Integer pSucursalId,  int pEstadoId);
	
	//IASC
	public long verificaCufdPorNitSucursal(Long pNit, String pCufd, Integer pSucursalId, int pEstadoId);
	
	 /**
	    * Objetivo: Obtener ultimo registro cufd activo
	    * Creado por: Wilson Limachi.
	    * Fecha: 17/05/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	 */
	public SreRegistrosCufd recuperCufdPorNitSucursalSistema(Long pNit, Integer pSucursalId, Long pSistemaId);
	
	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene registro cufd vigente
	 * @param pNit        Nit del emisor
	 * @param pSucursalId sucursal del emisor
	 * @return registro de tipo SreRegistrosCufd
	 * @fecha 03/10/2018
	 */
	public SreRegistrosCufd obtenerRegistroCufdVigente(Long pNit, Integer pSucursalId);
	
}
