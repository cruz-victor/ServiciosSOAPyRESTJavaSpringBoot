package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroVerificacionInsituDto;

public interface IRegistroVerificacionPruebasInsituService {
	public RespuestaRegistroVerificacionInsituDto guardar(
			SolicitudRegistroVerificacionInsituDto pSolicitud);
}
