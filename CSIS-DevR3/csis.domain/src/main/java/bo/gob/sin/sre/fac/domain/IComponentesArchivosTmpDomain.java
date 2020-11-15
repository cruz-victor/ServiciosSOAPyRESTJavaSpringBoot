package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;

public interface IComponentesArchivosTmpDomain {

	/** 
	 * Registrar Componente Archivo
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve true y false
	 */
	SreComponentesArchivosTmp registrarComponentesArchivos(Long pUsuarioId,Long pArchivoId, String pMd5, String pSha2,String pCrc ,String pRuta,String pNombre ,String pMime);
	
	/***
	 * Actualiza estadoId de la entidad SreComponentesArchivosTmp
	 * 
	 * @author rosario.garcia
	 * @return la entidad SreComponentesArchivosTmp
	 * @fecha 01/12/2018
	 */
	public SreComponentesArchivosTmp actualizarEstadoComponenteArchivoTmp(Long pComponenteArchivoTmpId);
	
	 /***
	  * Obtiene el registro de la entidad de acuerdo al Id
	  * 
	  * @param pComponenteArchivoTmpId, id de la entidad
	  * @return
	  */
	public SreComponentesArchivosTmp obtenerComponenteArchivoId(Long pComponenteArchivoTmpId);
	
	 /***
		 * Actualiza estadoId de la entidad SreComponentesArchivos
		 * 
		 * @author wilson.limachi
		 * @return la entidad SreComponentesArchivos
		 * @fecha 24/09/2019
		 */
		 public SreComponentesArchivos actualizarEstadoComponenteArchivo(Long pComponenteArchivoId);
	
}
