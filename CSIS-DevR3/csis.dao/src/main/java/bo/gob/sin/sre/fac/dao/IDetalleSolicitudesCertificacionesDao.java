package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

//IASC
public interface IDetalleSolicitudesCertificacionesDao extends GenericDao<SreDetalleSolicitudesCertificaciones>{

	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 14/11/2018
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return Devuelve objeto SreDetalleSolicitudesCertificaciones.
	 */
	public List<SreDetalleSolicitudesCertificaciones> obtenerListaDetalleSolicitudCertificacionId(long pSolicitudCertificacionId);
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author rosario.garcia
	 * @Fecha: 29/11/2018
	 * @param pSistemId = el identificador del sistema
	 * @return lista de SreDetalleSolicitudesCertificaciones.
	 */
	public List<SreDetalleSolicitudesCertificaciones>  recuperaSolicitudesCertificacionPorIdSistema(Long pSistemId);

	/**
	 * Recuperar Lista Detalle de Tipos documentos Sistemas por SolicitudCertificacionId.
	 * @author: Wilson Limachi Choque
	 * @Fecha: 17/07/2019
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return Devuelve objeto Lista SreDetalleSolicitudesCertificaciones.
	 */
	public List<SreTiposDocumentosSistemas> obtenerListaTipoDocumentosSistema(long pSolicitudCertificacionId); 
	
	public List<SreDetalleSolicitudesCertificaciones> recuperaSolicitudesDetalleCertificacionPorIdSistema(Long pSistemId, Long pIdContribuyenteProveedor);
}
