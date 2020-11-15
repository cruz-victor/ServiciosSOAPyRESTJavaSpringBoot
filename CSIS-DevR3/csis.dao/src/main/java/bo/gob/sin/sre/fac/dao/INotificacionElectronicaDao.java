package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SadEntNotificacionElectronica;

public interface INotificacionElectronicaDao extends GenericDao<SadEntNotificacionElectronica> {
	List<SadEntNotificacionElectronica> obtenerNotificacionElectronicaPorContribuyenteYActuado(
			long pPersonaContribuyenteId, long pActuadoId);
}
