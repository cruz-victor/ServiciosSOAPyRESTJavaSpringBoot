package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.ContribuyentesModalidadesDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;

public interface IRegistroCertificacionSistemasService {

	public RespuestaRegistrarSolicitudCertificacionDto registrarSolicitudSistema(SolicitudRegistrarCertificacionSistemasDto pSolicitud);
	
	public RespuestaEstaRegistradoGenericoDto verificacionSistemaRegistrado(SolicitudVerificacionSistemaRegistradoDto pSolicitud);
	
	public RespuestaRegistrarSolicitudCertificacionDto asignarFechaAprobacion(SolicitudRegistrarCertificacionSistemasDto pSolicitud);
	
	public ContribuyentesModalidadesDto obtieneModalidadContribuyente(Long pNit);
}
