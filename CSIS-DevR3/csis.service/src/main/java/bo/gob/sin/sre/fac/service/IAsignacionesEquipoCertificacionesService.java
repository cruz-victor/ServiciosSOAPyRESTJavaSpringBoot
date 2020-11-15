package bo.gob.sin.sre.fac.service;



import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.EquiposCertificacionesDto;
import bo.gob.sin.sre.fac.dto.EquiposCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;

public interface IAsignacionesEquipoCertificacionesService {
	public RespuestaEstaRegistradoGenericoDto registrarAsignacionEquipoCertificacion(
			AsignacionesCertificacionesListaDto pAsignaciones);

	public AsignacionesCertificacionesListaDto obtenerAsignacionEquipoCertificacionPorTramite(long pTramite);

	public RespuestaEstaRegistradoGenericoDto modificarAsignacionEquipoCertificacion(
			AsignacionesCertificacionesListaDto pAsignacionesCertificacionesDto);

	public EquiposCertificacionesListaDto obtenerEquipoCertificacion(EquiposCertificacionesDto pEquipoCertificacion);
}
