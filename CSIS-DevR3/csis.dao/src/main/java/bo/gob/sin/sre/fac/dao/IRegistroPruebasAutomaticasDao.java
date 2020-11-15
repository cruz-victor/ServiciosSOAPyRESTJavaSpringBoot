package bo.gob.sin.sre.fac.dao;

import java.util.List;
import java.util.Map;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutoDetalle;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;

public interface IRegistroPruebasAutomaticasDao extends GenericDao<SreFacRegistrosPruebasAutomaticas> {

	
	/**
	 * Recupera listado SrePruebasAutomaticas 
	 * 
	 * @author: Levi Laura Ramos
	 * @Fecha: 03/12/2018
	 * @param pSolicitudId
	 * @param  pTramiteId
	 * @param  pSistemaId
	 * @return   Devuelve objeto List<SreFacRegistrosPruebasAutomaticas>
	 */
	
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListadoPruebasAutomaticas(long pSolicitudId, long pTramiteId,
			long pSistemaId);
	
	/**
	 * Recupera listado SrePruebasAutomaticas de forma paginada 
	 * 
	 * @author: Levi Laura Ramos
	 * @Fecha: 03/12/2018
	 * @param pSolicitudId
	 * @param  pTramiteId
	 * @param  pSistemaId
	 * @return   Devuelve objeto List<SreFacRegistrosPruebasAutomaticas>
	 */
	
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListaPaginadaPruebasAutomaticas(long pSolicitudId, long pTramiteId,
			long pSistemaId,int pPrimerRegistro, int pTamanioPagina,  String pCampoOrden, boolean pAscendente, Map<String, Object> pFiltros);
	
	/**
	 * Recupera listado SrePruebasAutomaticas de forma paginada 
	 * 
	 * @author: Levi Laura Ramos
	 * @Fecha: 14/12/2018
	 * @param pSolicitudId
	 * @param  pTramiteId
	 * @param  pSistemaId
	 * @return   Devuelve Long
	 */
	
	public Long obtieneTotalRegistrosPruebasAutomaticas(long pSolicitudId, long pTramiteId,
			long pSistemaId, Map<String, Object> pFiltros);
	
	/**
	 * Obtiene Listado de Registro de Pruebas Automaticas
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 18/07/2019
	 * @param  pSolicitudCertificacionId
	 * @return   Devuelve List<SreFacRegistrosPruebasAutomaticas>
	 */
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListadoRegistroPruebasAutomaticas(long pSolicitudCertificacionId);
}
