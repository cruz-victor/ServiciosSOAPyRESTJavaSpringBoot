package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;
import bo.gob.sin.sre.fac.model.SreEquiposCertificaciones;

public interface IAsignacionesCertificacionesConsultaDomain {
	public List<SreAsignacionesCertificaciones> obtenerAsignacionesEquipoCertificacionPorTramite(long pTramiteId);

	public List<SreEquiposCertificaciones> obtenerEquipoCertificacion(SreEquiposCertificaciones pEquipoCertificacion);
}
