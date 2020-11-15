package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;

public interface IAsignacionesCertificacionesABMDomain {
	public boolean registrarAsignacionEquipoCertificacion(List<SreAsignacionesCertificaciones> pAsignaciones);
	
	public boolean modificarAsignacionEquipoCertificacion(List<SreAsignacionesCertificaciones> pAsignaciones);
}
