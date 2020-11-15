package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;

public interface IRegistrosObservacionesInsituDao {
	/**
	 * Obtiene el listado de verificacion insitu
	 * 
	 * @author: Levi Laura
	 * @Fecha: 29/11/2018
	 * @param pSolicitudCertificacionId
	 * @return List<SreFacRegistrosObservacionesInsitu> 
	 */ 
	public List<SreFacRegistrosObservacionesInsitu> obtieneListaVerificacionInsitu(long pSolicitudCertificacionId);
	/**
	 * Registra una observacion de componente
	 * 
	 * @author: Levi Laura
	 * @Fecha: 29/11/2018
	 * @param SreFacRegistrosObservacionesInsitu
	 * @return SreFacRegistrosObservacionesInsitu
	 */
	
	public SreFacRegistrosObservacionesInsitu  registroObservacionComponente(SreFacRegistrosObservacionesInsitu pRegObservacionComponente);
	
	
}
