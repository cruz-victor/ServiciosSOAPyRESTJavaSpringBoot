package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreArchivosTmp;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

public interface IRegistrarHuellaSistemaTmpDomain {
	
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
	public SreComponentesCertificadosTmp registrarComponenteCertificado(Integer pTipoComponenteId,Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId);
	
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
	
	/**
	 * Registrar Componente Certificado
	 * 
	 * @author: Peter Flores
	 * @Fecha: 15/11/2018	 
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve un valor booleano que representa el estado de los registros que se desarrollo.
	 */
	public boolean registrarComponentesCertificados(List<Integer> pTipoComponenteId,  Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId);
	
	/**
	 * Obtiene lista de componentes certificados por sistemaId
	 * 
	 * @author rosario.garcia
	 * @Fecha: 29/11/2018
	 * @param pSistemaId, Id de la entidad sistema.
	 * @return   lista de componentes certificados.
	 */
	public List<SreComponentesCertificadosTmp> obtieneComponenteCertificadoTmpPorSistemaId(Long pSistemaId);
	
	public SreComponentesCertificadosTmp actualizarEstadoComponenteCertificadoTmp(SreComponentesCertificadosTmp pSreComponentesCertificadosTmp);
	
	public SreComponentesArchivosTmp actualizarEstadoComponenteArchivoTmp(Long pComponenteArchivoTmpId);
	
	public SreArchivosTmp actualizarEstadoArchivoTmp(Long pArchivoId);
	
	public SreComponentesArchivosTmp obtenerComponenteArchivoId(Long pComponenteArchivoTmpId);
		
}


