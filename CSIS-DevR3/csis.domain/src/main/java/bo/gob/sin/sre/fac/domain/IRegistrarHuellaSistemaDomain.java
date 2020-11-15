package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;

public interface IRegistrarHuellaSistemaDomain {
	
	/**
	 * Registrar Componente Certificado
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto SreComponentesCertificados
	 */
	public SreComponentesCertificados registrarComponenteCertificado(Integer pTipoComponenteId,Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId, Long pSolicitudCertificacionId);
	
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
	public SreArchivos registrarArchivos(byte[] pArchivo);
	
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
	SreComponentesArchivos registrarComponentesArchivos(Long pUsuarioId,Long pArchivoId, String pMd5, String pSha2,String pCrc ,String pRuta,String pNombre ,String pMime);
	
	/** 
	 * Registrar Componente Archivo
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 20/09/2019 
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve entidad o null
	 */
	SreComponentesArchivos registrarComponentesArchivos(Long pUsuarioId,Long pArchivoId, String pMd5, String pSha2,String pCrc ,String pRuta,String pNombre ,String pMime, String pExtension);
	
	/**
	 * Registrar Componente Certificado
	 * 
	 * @author: Peter Flores
	 * @Fecha: 15/11/2018	 
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve un valor booleano que representa el estado de los registros que se desarrollo.
	 */
	public boolean registrarComponentesCertificados(List<Integer> pTipoComponenteId, Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId, Long pSolicitudCertificacionId);
	
	/**
	 * Listado de los componentes de archivos de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 20/11/2018
	 * @param pSistemaId, Código único de la entidad sistema.
	 * @return   Devuelve un listado de Dto de tipo Componentes archivos.
	 */
	public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId);
}


