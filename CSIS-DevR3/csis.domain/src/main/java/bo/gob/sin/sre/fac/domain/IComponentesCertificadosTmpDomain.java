package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

public interface IComponentesCertificadosTmpDomain {
	
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
	public List<SreComponentesCertificadosTmp> obtenerComponenteCertificadoTmpPorSistemaId(Long pSistemaId);
	
	/***
	 * Actualiza la entidad SreComponentesCertificadosTmp
	 * 
	 * @author rosario.garcia
	 * @return la entidad SreComponentesCertificadosTmp
	 * @fecha 29/11/2018
	 */
	public SreComponentesCertificadosTmp actualizarEstadoComponenteCertificadoTmp(SreComponentesCertificadosTmp pSreComponentesCertificadosTmp);
	
	/***
	 * Actualiza la entidad SreComponentesCertificados
	 * 
	 * @author wilson.limachi
	 * @return la entidad SreComponentesCertificados
	 * @fecha 24/09/2019
	 */
	 public SreComponentesCertificados actualizarEstadoComponenteCertificado(SreComponentesCertificados pSreComponentesCertificados); 
}
