package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarReCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;

public interface IRegistroReCertificacionSistemasService {

	public RespuestaRegistrarSolicitudCertificacionDto registrarRecertificacionSolicitudSistema(SolicitudRegistrarReCertificacionSistemasDto pSolicitud);
	
	public RespuestaEstaRegistradoGenericoDto verificacionSistemaRegistrado(SolicitudVerificacionSistemaRegistradoDto pSolicitud);
	
	public RespuestaRegistrarSolicitudCertificacionDto asignarFechaAprobacion(SolicitudRegistrarCertificacionSistemasDto pSolicitud);
}
