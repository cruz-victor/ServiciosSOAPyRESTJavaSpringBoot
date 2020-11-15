package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RespuestaVerificacionPruebasConcluidasDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;

public interface IPruebasAutomaticasService {

	

	public RespuestaVerificacionPruebasConcluidasDto verificaPruebasConcluidas(
			SolicitudPruebasSistemasDto pSolicitudPruebasSistemasDto);
}
