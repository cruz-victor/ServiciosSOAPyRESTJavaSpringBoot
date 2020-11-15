package bo.gob.sin.sre.fac.domain;



import java.util.List;

import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.model.SreIniciosSistemas;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;

public interface ISistemasDomain {
	RespuestaActualizacionDto actualizarEstadoSistemaIniciado(Long pSistemaId);
	//RespuestaActualizacionDto actualizarEstadoSistemaCertificado(Long pSistemaId, Long pContribuyenteId, Long pSolicitudCertificacionId);
	SreSistemasContribuyentes actualizarEstadoSistemaCertificado(Long pSistemaId, Long pContribuyenteId,
			Long pSolicitudCertificacionId, int pModalidad);
	RespuestaActualizacionDto actualizarEstadoSistemaObservado(Long pSistemaId);
	RespuestaActualizacionDto actualizarEstadoSistemaBaja(Long pSistemaId);
		/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 15/06/2018
	    */
	List<SreSistemas> verificacionSistemaRegistrado(String pNombreSistema, Integer pModalidadFacturacionId,
			Long pContribuyenteId);
	/**
	 * Obtener version del sistema
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 22/06/2018
	 * @param pSistemaId, numero identificador de sistema
	 * @return   Devuelve el version sistema String.
	 */
	public String obtenerVersionSistema(long pSistemaId);
	/**
	 * Obtener descripcion del sistema
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 22/06/2018
	 * @param pSistemaId, numero identificador de sistema
	 * @return   Devuelve el descripcion sistema String.
	 */
	public String obtenerDescripcionSistema(long pSistemaId);
	/**
	 * Obtener codigo sistema asociado del sistema
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 27/06/2018
	 * @param pSistemaId, numero identificador de sistema
	 * @return   Devuelve el codigo sistema asociado sistema String.
	 */
	public String obtenerCodigoSistemaAsociado(long pSistemaId);
	public SreSistemas recuperarDatosSistemas(Long pSistemaId);
	public SreSistemas actualizarDatosSistemas(Long pSistemaId);
	
	//IASC
	public boolean verificaExisteSistema(Long pSistemaId);
	
	//IASC
	public SreSistemas actualizarEstadoSistemaCancelado(Long pSistemaId, Long pUsuario);
	
	
	
}
