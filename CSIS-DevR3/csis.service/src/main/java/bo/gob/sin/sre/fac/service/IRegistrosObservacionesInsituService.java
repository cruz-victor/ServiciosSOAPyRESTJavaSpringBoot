package bo.gob.sin.sre.fac.service;

import java.util.List;

import bo.gob.sin.sre.fac.dto.RegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;

public interface IRegistrosObservacionesInsituService {
	/**
	 * Devuelve listado de verificacion realizadas insitu
	 * 
	 * @author: Levi Laura
	 * @Fecha: 29/11/2018
	 * @param pSolicitudCertificacionId
	 * @return Devuelve RespuestaRegistrosObservacionesInsituDto.
	 */
	public RespuestaRegistrosObservacionesInsituDto obtieneListaVerificacionInsitu(long pSolicitudCertificacionId);
	
	/**
	 * Registra una observacion de componente
	 * 
	 * @author: Levi Laura
	 * @Fecha: 29/11/2018
	 * @param SreFacRegistrosObservacionesInsitu
	 * @return SreFacRegistrosObservacionesInsitu
	 */
	public RespuestaRegistrosObservacionesInsituDto  registroObservacionComponente(RegistrosObservacionesInsituDto pRegObservacionComponente);
	
	/**
	 * Devuelve obtiene Registro ListaRegistro de Observaciones Insitu
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 22/10/2019
	 * @param pSistemaId, pSolicitudCertificacionId
	 * @return Devuelve RespuestaRegistrosObservacionesInsituDto.
	 */
	public RespuestaRegistroVerificacionInsituDto obtieneRegistroListaRegistroObservacionesInsitu(Long pSistemaId, Long pSolicitudCertificacionId, Long pUsuarioRegistro);
}
