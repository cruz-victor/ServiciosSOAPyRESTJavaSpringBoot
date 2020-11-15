package bo.gob.sin.sre.fac.service;

import java.util.List;
import java.util.Map;

import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaTamanioGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;

public interface IRegistroPruebasAutomaticasConsultaService {
	
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
	public RespuestaListaRegistroPruebasAutomaticasDto obtieneListadoPruebasAutomaticas(long pSolicitudId, long pTramiteId,
			long pSistemaId);
	
	
	
	
	
	/**
	 * Busca registros pruebas automaticas  y los muestra de forma paginada
	 * 
	 * @author: Levi Laura
	 * @Fecha: 04/12/2018
	 * @param pSolicitudId, id de solicitud
	 * @param pTramiteId, id de tramite
	 * @param pSistemaId,id de sistema
	 * @return   RespuestaListaRegistroPruebasAutomaticasDto  listado de pruebas automaticas
	 */
	public RespuestaListaRegistroPruebasAutomaticasDto obtieneListaPaginadaPruebasAutomaticas(SolicitudPruebasSistemasDto pSolicitudPruebasAutomaticas);
	
	
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
	
	public RespuestaTamanioGenericoDto obtieneTotalRegistrosPruebasAutomaticas(SolicitudPruebasSistemasDto pSolicitudPruebasAutomaticas);

}
