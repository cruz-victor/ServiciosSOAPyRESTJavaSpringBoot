package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreRegistrosCufd;

public interface IGenerarCufdDomain {
	
	//IASC
	public String generarCodigoCufd(Integer pSucursalId, Long pNitEmisor, String pCuis);
	
	//IASC
	public String cryptMD5(String textoPlano);
	
	//IASC
	//public RespuestaCufdDto registrarCufd(SreRegistrosCufd pSolicitud);
	/**
	    * Objetivo: Actualiza el CUFD cuando se solicita uno nuevo.
	    * Creado por: Ivan Salas.
	    * Fecha: 25/06/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	  */
	public SreRegistrosCufd registrarCufd(SreRegistrosCufd pSolicitud);
	
	/**
	    * Objetivo: Actualiza el CUFD cuando se solicita uno nuevo.
	    * Creado por: Ivan Salas.
	    * Fecha: 25/06/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	    * Modificado por: Peter Flores
	    * Fecha de Modificacion: 24/08/2018
	  */
	public SreRegistrosCufd registrarCufdWeb(SreRegistrosCufd pSolicitud);
	
	//FRRA
	public Long verificaCufd(Long sistemaId, String cufd, Integer sucursal, Integer estadoId);
	
	//IASC
	public Long verificaCufdPorNitSucursal(Long nit, String cufd, Integer sucursal, Integer estadoId);
	
	 /**
	    * Objetivo: Obtener ultimo registro cufd activo
	    * Creado por: Wilson Limachi.
	    * Fecha: 18/05/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	 */
	public SreRegistrosCufd recuperCufdPorNitSucursalSistema(Long pNit, Integer pSucursalId, Long pSistemaId);
	
	/**
	    * Objetivo: Actualiza el CUFD cuando se solicita uno nuevo.
	    * Creado por: Ivan Salas.
	    * Fecha: 25/06/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	  */
	public boolean actualizarCufd(SreRegistrosCufd pSolicitud, Long pUsuarioId);

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
