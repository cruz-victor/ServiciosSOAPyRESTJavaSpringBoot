package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;

public interface IRegistrosObservacionesInsituDomain {
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
