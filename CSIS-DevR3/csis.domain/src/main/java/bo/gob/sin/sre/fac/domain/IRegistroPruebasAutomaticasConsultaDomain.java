package bo.gob.sin.sre.fac.domain;

import java.util.List;
import java.util.Map;

import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;

public interface IRegistroPruebasAutomaticasConsultaDomain {
	
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
	 * Busca registros pruebas automaticas  
	 * 
	 * @author: Levi Laura
	 * @Fecha: 04/12/2018
	 * @param pSolicitudId, id de solicitud
	 * @param pTramiteId, id de tramite
	 * @param pSistemaId,id de sistema
	 * @return   RespuestaListaRegistroPruebasAutomaticasDto  listado de pruebas automaticas
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

	/**
	 * Obtiene Listado de Registro de Pruebas Automaticas
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 18/07/2019
	 * @param  pRegistroPruebaAutomaticaId
	 * @param  pUsuario
  	 * @param  pEstadoId
	 * @return Devuelve objeto SreFacRegistrosPruebasAutomaticas
	 */
	public SreFacRegistrosPruebasAutomaticas modificaRegistroPruebasAutomaticas(long pRegistroPruebaAutomaticaId, long pUsuario, int pEstadoId);
}
