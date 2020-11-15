package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.INotificacionElectronicaDao;
import bo.gob.sin.sre.fac.model.SadEntNotificacionElectronica;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
@Repository
@Transactional
public class NotificacionElectronicaDaoImpl extends AbstractGenericDao<SadEntNotificacionElectronica>
		implements INotificacionElectronicaDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(NotificacionElectronicaDaoImpl.class);

	@Override
	public List<SadEntNotificacionElectronica> obtenerNotificacionElectronicaPorContribuyenteYActuado(
			long pPersonaContribuyenteId, long pActuadoId) {
		LOG.info("Ingresando obtenerNotificacionElectronicaPorContribuyenteYActuado.");
		List<SadEntNotificacionElectronica> vEntidad = new ArrayList<SadEntNotificacionElectronica>();
		try {
			String vhql = "FROM SadEntNotificacionElectronica p WHERE p.documentoActuadoId =:pActuadoId and p.personaContribuyenteId =:pPersonaContribuyenteId and p.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).setParameter("pPersonaContribuyenteId", pPersonaContribuyenteId)
					.setParameter("pActuadoId", pActuadoId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vEntidad;
	}

}
