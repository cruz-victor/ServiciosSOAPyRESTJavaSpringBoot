package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IPruebasManualesDao;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;
import bo.gob.sin.sre.fac.model.SrePruebasManuales;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
@Repository
@Transactional
public class PruebasManualesDaoImpl extends AbstractGenericDao<SrePruebasManuales> implements IPruebasManualesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasManualesDaoImpl.class);

	// IASC - Recupera la lista de pruebas manuales - 24/11/2018
	@SuppressWarnings("unchecked")
	@Override
	public List<SrePruebasManuales> obtenerPruebasManuales() {
		LOG.info("Ingresando obtenerPruebasManuales.");
		List<SrePruebasManuales> vEntidad = new ArrayList<SrePruebasManuales>();
		try {
			String vhql = "FROM SrePruebasManuales p WHERE p.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vEntidad;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SreFacRegistrosPruebasManuDetalle> obtieneListaPruebasManuales(long pSolicitudId, long pTramiteId,
			long pSistemaId) {
	
		List<SreFacRegistrosPruebasManuDetalle> vListadoPruebasManu = new ArrayList<>();
		try {

			String vhql = "SELECT new bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle(a.pruebaManualId, c.nombre, d.descripcion,a.observaciones, a.estadoPruebaId, a.registroPruebaManualId)"

					+ " FROM SreFacRegistrosPruebasManuales a"
					+ " JOIN SreSolicitudCertificacion b ON a.solicitudCertificacionId=b.solicitudCertificacionId"
					+ " JOIN SrePruebasManuales c ON a.pruebaManualId=c.pruebaManualId"
					+ " JOIN StrCpsClasificador d ON a.estadoPruebaId = d.clasificadorId"
					+ " WHERE a.solicitudCertificacionId =:pSolicitudId and a.tramiteId =:pTramiteId and a.sistemaId =:pSistemaId"
					+ " and a.estadoId = 'AC' ORDER BY d.clasificadorId ASC, a.observaciones ASC";

			vListadoPruebasManu = (List<SreFacRegistrosPruebasManuDetalle>) getSession().createQuery(vhql)
					.setParameter("pSolicitudId", pSolicitudId).setParameter("pTramiteId", pTramiteId)
					.setParameter("pSistemaId", pSistemaId).getResultList();
	

		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListadoPruebasManu));
		}

		return vListadoPruebasManu;

	}

}
