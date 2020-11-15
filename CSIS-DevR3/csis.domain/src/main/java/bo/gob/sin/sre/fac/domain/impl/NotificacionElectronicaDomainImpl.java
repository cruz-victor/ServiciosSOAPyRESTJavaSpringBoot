package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.INotificacionElectronicaDao;
import bo.gob.sin.sre.fac.domain.INotificacionElectronicaDomain;
import bo.gob.sin.sre.fac.model.SadEntNotificacionElectronica;

@Service
@Transactional
public class NotificacionElectronicaDomainImpl implements INotificacionElectronicaDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private INotificacionElectronicaDao notificacionElectronicaDao;

	@Override
	public List<SadEntNotificacionElectronica> obtenerNotificacionElectronicaPorContribuyenteYActuado(
			long pPersonaContribuyenteId, long pActuadoId) {
		return notificacionElectronicaDao
				.obtenerNotificacionElectronicaPorContribuyenteYActuado(pPersonaContribuyenteId, pActuadoId);
	}

}
