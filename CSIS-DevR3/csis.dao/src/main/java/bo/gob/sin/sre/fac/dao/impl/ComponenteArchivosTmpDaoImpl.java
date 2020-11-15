package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import javax.persistence.NoResultException;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.sre.fac.dao.IComponenteArchivosTmpDao;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class ComponenteArchivosTmpDaoImpl extends AbstractGenericDao<SreComponentesArchivosTmp> implements IComponenteArchivosTmpDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ComponenteArchivosTmpDaoImpl.class);
	
	/***
	 * Permite obtener la entidad SreComponentesArchivosTmp, a traves del Id
	 * 
	 * @author rosario.garcia
	 * @param pSistemaId = es el sistema ID de la tabla
	 * @return la lista de componentes certificados 
	 * @fecha 29/11/2018
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SreComponentesArchivosTmp obtenerComponentesArchivoTmp(Long pComponenteArchivoTmpId) {
		LOG.info("Ingresando a obtenerComponentesArchivoTmp.");
		SreComponentesArchivosTmp vEntidad = new SreComponentesArchivosTmp();
		try {
			String vhql = "select c "
					+ "FROM SreComponentesArchivosTmp c "
					+ "WHERE c.componenteArchivoTmpId = :pComponenteArchivoTmpId "
					+ "and c.estadoId = 'AC'";
			vEntidad = (SreComponentesArchivosTmp) getSession().createQuery(vhql).setParameter("pComponenteArchivoTmpId", pComponenteArchivoTmpId).getSingleResult();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		}catch (NoResultException e) {
			return new SreComponentesArchivosTmp();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
}
