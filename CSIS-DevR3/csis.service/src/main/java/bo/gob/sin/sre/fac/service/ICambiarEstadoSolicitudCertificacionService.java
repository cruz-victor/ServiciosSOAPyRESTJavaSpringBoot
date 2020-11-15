package bo.gob.sin.sre.fac.service;



import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;

public interface ICambiarEstadoSolicitudCertificacionService {

	/**
	 * Registrar Documentos Solicitudes
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/11/2018
	 * @param pSolicitud, solicitud SolicitudSolicitudCertificacionDto
	 * @return Devuelve SreDocumentosSolicitudes.
	 */
	public RespuestaActualizacionGenericoDto cancelarCertificacion(SolicitudSolicitudCertificacionDto pSolicitud);
	
	public RespuestaActualizacionGenericoDto aprobarCertificacion(SolicitudSolicitudCertificacionDto pSolicitud);
	
	public RespuestaActualizacionGenericoDto rechazarCertificacion(SolicitudSolicitudCertificacionDto pSolicitud);

}
