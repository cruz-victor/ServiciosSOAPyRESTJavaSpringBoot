package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreArchivosTmp;

public interface IArchivoTmpDomain {

	/** 
	 * Registrar Archivos
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto SreArchivos
	 */
	public SreArchivosTmp registrarArchivos(byte[] pArchivo);

	/***
	 * Actualiza la entidad SreComponentesArchivosTmp
	 * 
	 * @author rosario.garcia
	 * @return la entidad SreComponentesArchivosTmp
	 * @fecha 30/11/2018
	 */
	public SreArchivosTmp actualizarEstadoArchivoTmp(Long pArchivoId);
	
	/***
	 * Actualiza la entidad SreComponentesArchivos
	 * 
	 * @author wilson.limachi
	 * @return la entidad SreComponentesArchivos
	 * @fecha 24/09/2019
	 */
	 public SreArchivos actualizarEstadoArchivo(Long pArchivoId); 
}
