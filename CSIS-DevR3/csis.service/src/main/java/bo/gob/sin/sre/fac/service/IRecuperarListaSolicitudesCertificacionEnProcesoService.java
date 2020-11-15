package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.ObtenerListSistemaDto;
import bo.gob.sin.sre.fac.dto.ObtenerListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperarListaSolicitudCertificacion;
import bo.gob.sin.sre.fac.dto.RespuestaListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;

public interface IRecuperarListaSolicitudesCertificacionEnProcesoService {

	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO, para Cancelacion de Solicitud de Certificacion
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RespuestaSistemasDeCertificacionFuncionarioDto.
	 */
	public RecuperarListaSolicitudCertificacion recuperaSolicitudesCertificacionParaCancelar(
			SolicitudSolicitudCertificacionDto pSolicitud);

	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO, para  el registro de huellas de sistema - contribuyente
	 * 
	 * @author: Carmen Rosa
	 * @Fecha: 21/09/2018
	 * @modificadoPor: rosario.garcia
	 * @FechaModificacion: 23/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo ObtenerListSistemaDto.
	 */
	public ObtenerListSistemaDto obtenerSistemasEstadoEnProceso(ObtenerListSistemaDto pSolicitud);
	
	public ObtenerListaSistemasCertificacionDto recuperaListaSolicitudSistemasCertificacion();
	
	public RespuestaListadoSistemasDto obtenerSistemasCertificacionPaginado(SolicitudListadoSistemasDto pSolicitud);


}
