package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

public interface IDetalleSolicitudesCertificacionesConsultaDomain {

	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * 
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 14/11/2018
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return   Devuelve objeto SreDetalleSolicitudesCertificaciones.
	 */
	public List<SreDetalleSolicitudesCertificaciones> obtenerListaDetalleSolicitudCertificacionId(long pSolicitudCertificacionId);

	public List<SreDetalleSolicitudesCertificaciones> obtenerSolicitudesCertificacionIdSistema(Long pSistemaId);
	
	public List<SreTiposDocumentosSistemas> obtenerListaTipoDocumentosSistema(long pSolicitudCertificacionId);
	
	public List<SreDetalleSolicitudesCertificaciones> recuperaSolicitudesDetalleCertificacionPorIdSistema(Long pSistemaId, Long pIdContribuyente);
}
