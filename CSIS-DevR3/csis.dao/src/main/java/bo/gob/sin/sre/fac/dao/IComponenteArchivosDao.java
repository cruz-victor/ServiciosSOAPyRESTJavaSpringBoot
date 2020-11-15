package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;

public interface IComponenteArchivosDao extends GenericDao<SreComponentesArchivos>{

	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author: Peter Flores
	 * @Fecha: 22/11/2018
	 * @param pSistemaId, Código unico de sistema
	 * @return Devuelve una lista de  SreComponentesArchivos.
	 */
	public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId);
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author: Wilson Limachi
	 * @Fecha: 18/09/2019
	 * @param pSistemaId, pSolicitudCertificacionId
	 * @return Devuelve una lista de  SreComponentesArchivos.
	 */
	public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId, Long pSolicitudCertificacionId);
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por ArchivoId.
	 * @author: Wilson Limachi
	 * @Fecha: 24/09/2019
	 * @param pSistemaId, pSolicitudCertificacionId
	 * @return Devuelve una lista de  SreComponentesArchivos.
	 */
	public List<SreComponentesArchivos> obtieneComponentesArchivosLista(Long pArchivoId);
}
