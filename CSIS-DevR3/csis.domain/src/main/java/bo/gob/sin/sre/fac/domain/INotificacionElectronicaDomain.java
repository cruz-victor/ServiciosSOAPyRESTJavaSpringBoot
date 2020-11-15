package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SadEntNotificacionElectronica;

public interface INotificacionElectronicaDomain {
	public List<SadEntNotificacionElectronica> obtenerNotificacionElectronicaPorContribuyenteYActuado(
			long pPersonaContribuyenteId, long pActuadoId);
}
