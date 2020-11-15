package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.str.cexc.LogExcepcion
;import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IContactosCertificacionesDao;
import bo.gob.sin.sre.fac.model.SreContactosCertificaciones;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

//IASC
@Repository
@Transactional
public class ContactosCertificacionesDaoImpl extends AbstractGenericDao<SreContactosCertificaciones> implements IContactosCertificacionesDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ContactosCertificacionesDaoImpl.class);
	
	//IASC - Para recuperar la lista de contactos de la solicitud - 23/11/2018
	@SuppressWarnings("unchecked")
	@Override
	public List<SreContactosCertificaciones> obtieneListaContactosSolicitud(Long pSolicitudCertificacionId) {
		List<SreContactosCertificaciones> vEntidad = new ArrayList<SreContactosCertificaciones>();
		LOG.info("Ingresando obtieneListaContactosSolicitud.");
		try {
			String vhql = "select c "
					+ "FROM SreContactosCertificaciones c "
					+ "WHERE c.solicitudCertificacionId = :pSolicitudCertificacionId "
					+ "and c.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
			vEntidad = null;
		}
		return vEntidad;
	}
}
