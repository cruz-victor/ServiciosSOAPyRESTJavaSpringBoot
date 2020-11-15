package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IPruebasEtapaCertificacionDao;
import bo.gob.sin.sre.fac.model.SrePruebasEtapaCertificacion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class PruebasEtapaCertificacionDaoImpl extends AbstractGenericDao<SrePruebasEtapaCertificacion> implements IPruebasEtapaCertificacionDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasSistemasDaoImpl.class);

	
	/**
	 * Obtiene el código unico de la entidad Prueba Etapa Certificación a través del código Etapa Certificación Sistemas
	 * 
	 * @author: junior.flores
	 * @Fecha: 06/06/2019
	 * @param pEtapaCertificacionSistemasId, código de la etapa de Certificación de Sistemas 
	 * @return Devuelve código unico de la entidad SrePruebasEtapaCertificacion
	 */
	@Override
	public Long obtenerPruebaEtapaCertificacionId(Long pEtapaCertificacionSistemasId) {
		LOG.info("Ingresando obtenerPruebasAutomaticasPorDocFiscal.");
		long vEntidad=0L;
		try {
			String vhql = "SELECT p.pruebaEtapaCertificacionid FROM SrePruebasEtapaCertificacion p WHERE p.etapaCertificacionSistemasId = :pEtapaCertificacionSistemasId and "					
					+ "p.estadoId = 'AC'";
			vEntidad = (long)getSession().createQuery(vhql).setParameter("pEtapaCertificacionSistemasId", pEtapaCertificacionSistemasId).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vEntidad;
	}
}
