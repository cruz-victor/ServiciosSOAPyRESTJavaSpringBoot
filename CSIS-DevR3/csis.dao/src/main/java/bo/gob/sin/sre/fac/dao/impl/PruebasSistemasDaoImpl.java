package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IPruebasSistemasDao;
import bo.gob.sin.sre.fac.model.SrePruebasSistemas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
@Repository
@Transactional
public class PruebasSistemasDaoImpl  extends AbstractGenericDao<SrePruebasSistemas> implements IPruebasSistemasDao, Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasSistemasDaoImpl.class);
	
	
	/**
	 * Cambiar estado en Pruebas sistemas
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 15/11/2018
	 * @param pSolicitudCertificacionId, numero de identificacion de solicitud certificacion
	 * @param pSistemasId, número de identificacion del sistema
	 * @return Devuelve un boolean true o false.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SrePruebasSistemas> obtenerListaPruebasSistemasPorCertificacion(Long pSolicitudCertificacionId, Long pSistemasId) {
		List<SrePruebasSistemas> vEntidad = new ArrayList<SrePruebasSistemas>();
		try {
			String vhql = "FROM SrePruebasSistemas a WHERE a.estadoId = 'AC' and a.solicitudCertificacionId= :solicitudCertificacionId and a.sistemaId =:sistemaId";
			vEntidad = getSession().createQuery(vhql).setParameter("solicitudCertificacionId", pSolicitudCertificacionId).setParameter("sistemaId", pSistemasId).getResultList();

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	//TODO
	//IASC - Se añadio actualizacion de los campos fechaUltimaModificacion, usuarioUltimaModificacion - 20/09/2018
	@Override
	public SrePruebasSistemas actualizarListaPruebasSistema(Long pPruebaSistemaId, String pObservaciones, Integer pEstadoPruebaId, Date pFechaFin, Long pUsuarioId) {
		SrePruebasSistemas vResultadoEntidad = this.recuperaPruebasSistemasPorId(pPruebaSistemaId);

		try {
			vResultadoEntidad.setObservaciones(pObservaciones);
			vResultadoEntidad.setEstadoPruebaId(pEstadoPruebaId);
			vResultadoEntidad.setFechaFin(pFechaFin);
			vResultadoEntidad.setFechaUltimaModificacion(new Date());
			vResultadoEntidad.setUsuarioUltimaModificacionId(pUsuarioId);
			getSession().save(vResultadoEntidad);
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultadoEntidad));
		}
		
		return vResultadoEntidad;
	}

	
	//TODO
	@Override
	//IASC
	public List<SrePruebasSistemas> obtenerListaPruebasSistemas(Long pSistemaId) {
		List<SrePruebasSistemas> vEntidad = new ArrayList<SrePruebasSistemas>();
		try {
			String vhql = "FROM SrePruebasSistemas a WHERE a.estadoId = 'AC' and sistemaId= :sistemaId ";
			vEntidad = getSession().createQuery(vhql).setParameter("sistemaId", pSistemaId).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vEntidad;
	}
	
	//TODO
	public SrePruebasSistemas recuperaPruebasSistemasPorId(Long pPruebaSistemaId) {
		SrePruebasSistemas vEntidad = new SrePruebasSistemas();
		try {
			String vhql = "FROM SrePruebasSistemas a WHERE pruebaSistemaId= :pruebaSistemaId";
			vEntidad = (SrePruebasSistemas) getSession().createQuery(vhql).setParameter("pruebaSistemaId", pPruebaSistemaId).getSingleResult();

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	/**
	 * Obtener Lista de Pruebas Sistemas de Certificación 
	 * 
	 * @author: Reynaldo Chambi
	 * @Fecha: 
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 19/06/2018
	 * @param pSistemaId, número de identificacion de un sistema.
	 * @param pSolicitudCertificacionId, número de solicitud de certificación
	 * @return   Devuelve objeto SrePruebasSistemas.
	 */
	//TODO
	@Override
	public List<SrePruebasSistemas> obtenerPruebasSistemasDeCertificacion(Long pSistemaId, Long pSolicitudCertificacionId) {
        LOG.info("Ingresando ObtenerPruebasSistemasDeCertificacion.");
        List<SrePruebasSistemas> vEntidad = new ArrayList<SrePruebasSistemas>();
        try {
               String vhql = "FROM SrePruebasSistemas a WHERE a.solicitudCertificacionId = :solicitudCertificacionId and a.sistemaId = :sistemaId order by a.fechaUltimaModificacion DESC";
               vEntidad = getSession().createQuery(vhql).setParameter("solicitudCertificacionId", pSolicitudCertificacionId).setParameter("sistemaId", pSistemaId).getResultList();
               LOG.info("Informacion recuperada.");
        } catch (Exception e) {
               LOG.info("Informacion no recuperada.");
               LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
        }
        return vEntidad;
  }


}
