package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RegistrosPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasManualesDto;

public interface IPruebasManualesService {
	public RespuestaListaRegistroPruebasManualesDto obtieneListaPruebasManuales(long pSolicitudId, long pTramiteId,
			long pSistemaId);

	public RespuestaActualizacionGenericoDto modificaObservacionEstado(
			RegistrosPruebasManualesDto pRegistroPruebaManualDto);

	public RespuestaEstaRegistradoGenericoDto verificarPruebasManualesFinalizadas(long pSolicitudId, long pTramiteId,
			long pSistemaId);
}
