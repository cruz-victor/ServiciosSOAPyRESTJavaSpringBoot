package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IRegistroPruebasAutomaticasDao;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class RegistrosPruebasAutomaticasDaoImpl extends AbstractGenericDao<SreFacRegistrosPruebasAutomaticas>
		implements IRegistroPruebasAutomaticasDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasAutomaticasDaoImpl.class);

	@Override
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListadoPruebasAutomaticas(long pSolicitudId, long pTramiteId,
			long pSistemaId) {
		// LOG.info("Ingresando obtenerPruebasAutomaticasPorDocFiscal.");
		List<SreFacRegistrosPruebasAutomaticas> vListadoPruebasAuto = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		try {
			String vhql = "FROM SreFacRegistrosPruebasAutomaticas p WHERE p.solicitudCertificacionId = :pSolicitudId and"
					+ " p.tramiteId =:pTramiteId and p.sistemaId =:pSistemaId and p.estadoId = 'AC'";
			vListadoPruebasAuto = (List<SreFacRegistrosPruebasAutomaticas>) getSession().createQuery(vhql)
					.setParameter("pSolicitudId", pSolicitudId).setParameter("pTramiteId", pTramiteId)
					.setParameter("pSistemaId", pSistemaId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
		}
		return vListadoPruebasAuto;

	}
	
	@Override
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListadoRegistroPruebasAutomaticas(long pSolicitudCertificacionId) 
	{
		LOG.info("Ingresando obtieneListadoRegistroPruebasAutomaticas.");
		List<SreFacRegistrosPruebasAutomaticas> vListadoPruebasAuto = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		try 
		{
			String vhql = "FROM SreFacRegistrosPruebasAutomaticas p WHERE p.solicitudCertificacionId = :pSolicitudCertificacionId and "
					+ " p.estadoId = 'AC'";
			
			vListadoPruebasAuto = (List<SreFacRegistrosPruebasAutomaticas>) getSession().createQuery(vhql)
					.setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			
			LOG.info("Informacion recuperada.");
		}
		catch (Exception e) 
		{
			vListadoPruebasAuto = null;
			LOG.info("Informacion no recuperada.");
		}
		
		return vListadoPruebasAuto;
	}

	@Override
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListaPaginadaPruebasAutomaticas(long pSolicitudId,
			long pTramiteId, long pSistemaId, int pPrimerRegistro, int pTamanioPagina, String pCampoOrden,
			boolean pAscendente, Map<String, Object> pFiltros) {
		List<SreFacRegistrosPruebasAutomaticas> vResultado = new ArrayList<SreFacRegistrosPruebasAutomaticas>();

		List<SreFacRegistrosPruebasAutomaticas> vListadoPruebasAuto = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		try {
			String vhql = "SELECT p FROM SreFacRegistrosPruebasAutomaticas p"
					+ " left join fetch SrePruebasAutomaticas s on p.pruebaAutomaticaId = s.pruebaAutomaticaId"
					+ " WHERE p.solicitudCertificacionId = :pSolicitudId and"
					+ " p.tramiteId =:pTramiteId and p.sistemaId =:pSistemaId and p.estadoId = 'AC'"
					+ " and p.pruebaAutomaticaId = s.pruebaAutomaticaId";

			if (pFiltros != null && pFiltros.size() > 0) {
				vhql = vhql + " and lower(s.descripcion) like lower(:pDescripcion)  ";
				vhql = vhql.replace(":pDescripcion", "'%" + pFiltros.values().toArray()[0].toString().trim() + "%'");
			}

			vListadoPruebasAuto = (List<SreFacRegistrosPruebasAutomaticas>) getSession().createQuery(vhql)
					.setFirstResult(pPrimerRegistro).setMaxResults(pTamanioPagina)
					.setParameter("pSolicitudId", pSolicitudId).setParameter("pTramiteId", pTramiteId)
					.setParameter("pSistemaId", pSistemaId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
		}
		return vListadoPruebasAuto;

	}

	@Override
	public Long obtieneTotalRegistrosPruebasAutomaticas(long pSolicitudId, long pTramiteId, long pSistemaId,
			Map<String, Object> pFiltros) {
		List<SreFacRegistrosPruebasAutomaticas> vListadoPruebasAuto = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		Long vResultado = null;
		try {
			String vhql = "SELECT count (p.registroPruebaAutomaticaId) FROM SreFacRegistrosPruebasAutomaticas p"
					+ " left join fetch SrePruebasAutomaticas s on p.pruebaAutomaticaId = s.pruebaAutomaticaId"
					+ " WHERE p.solicitudCertificacionId = :pSolicitudId and"
					+ " p.tramiteId =:pTramiteId and p.sistemaId =:pSistemaId and p.estadoId = 'AC'"
					+ " and p.pruebaAutomaticaId = s.pruebaAutomaticaId";

			if (pFiltros != null && pFiltros.size() > 0) {
				vhql = vhql + " and lower(s.descripcion) like lower(:pDescripcion)  ";
				vhql = vhql.replace(":pDescripcion", "'%" + pFiltros.values().toArray()[0] + "%'");
			}

			vResultado = (Long) getSession().createQuery(vhql).setParameter("pSolicitudId", pSolicitudId)
					.setParameter("pTramiteId", pTramiteId).setParameter("pSistemaId", pSistemaId).uniqueResult();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
		}
		return vResultado;

	}

}
