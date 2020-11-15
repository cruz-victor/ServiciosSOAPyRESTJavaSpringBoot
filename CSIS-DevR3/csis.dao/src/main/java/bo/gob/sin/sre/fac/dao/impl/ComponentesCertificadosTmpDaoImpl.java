package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosTmpDao;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
//IASC
@Repository
@Transactional
public class ComponentesCertificadosTmpDaoImpl extends AbstractGenericDao<SreComponentesCertificadosTmp> implements IComponentesCertificadosTmpDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ComponentesCertificadosTmpDaoImpl.class);

	/***
	 * obtiene la lista de componentes certificad
	 * 
	 * @author rosario.garcia
	 * @param pSistemaId = es el sistema ID de la tabla
	 * @return la lista de componentes certificados 
	 * @fecha 29/11/2018
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreComponentesCertificadosTmp> obtenerComponentesCertificadosTmp(Long pSistemaId) {
		List<SreComponentesCertificadosTmp> vEntidad = new ArrayList<SreComponentesCertificadosTmp>();
		LOG.info("Ingresando obtieneComponenteCertificadoTmp.");
		try {
			String vhql = "select c "
					+ "FROM SreComponentesCertificadosTmp c "
					+ "WHERE c.sistemaId = :pSistemaId "
					+ "and c.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		}catch (NoResultException e) {
			return new ArrayList<SreComponentesCertificadosTmp>();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
}
