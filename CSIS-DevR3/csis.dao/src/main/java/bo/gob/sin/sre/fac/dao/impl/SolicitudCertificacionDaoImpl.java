package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.ISolicitudCertificacionDao;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacionListado;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Repository
@Transactional
public class SolicitudCertificacionDaoImpl extends AbstractGenericDao<SreSolicitudCertificacion>
		implements ISolicitudCertificacionDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SolicitudCertificacionDaoImpl.class);

	// IASC - Recupera los datos del tramite - 25/11/2018
	@Override
	public SreSolicitudCertificacion obtenerDatosTramiteCertificacion(long pTramiteId) {
		SreSolicitudCertificacion vTramiteCertificacion = new SreSolicitudCertificacion();
		LOG.info("Ingresando obtenerDatosTramiteCertificacion.");
		try {
			String vhql = "FROM SreSolicitudCertificacion p WHERE p.tramiteId = :pTramiteId and p.estadoId = 'AC'";
			vTramiteCertificacion = (SreSolicitudCertificacion) getSession().createQuery(vhql)
					.setParameter("pTramiteId", pTramiteId).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vTramiteCertificacion));
			vTramiteCertificacion = null;
		}
		return vTramiteCertificacion;
	}

	/**
	 * Objetivo: recuperar solicitudes de certificacion por
	 * solicitudCertificacionId. Creado por: Reynaldo Chambi. Fecha: 03/04/2018
	 * Modificado por: Gualberto Condori Fecha de Modificacion: 19/06/2018
	 */
	// TODO
	@Override
	public SreSolicitudCertificacion recuperaSolicitudesCertificaciones(Long pSolicitudCertificacionId,
			Integer pEstado) {
		SreSolicitudCertificacion vSolicitudCertificacion = new SreSolicitudCertificacion();
		LOG.info("Ingresando recuperaSolicitudesCertificaciones.");

		try {
			String vhql = "FROM SreSolicitudCertificacion p WHERE p.solicitudCertificacionId = :id and p.estadoSolicitudCertificacionId != :estado and p.estadoId = 'AC'";
			vSolicitudCertificacion = (SreSolicitudCertificacion) getSession().createQuery(vhql)
					.setParameter("id", pSolicitudCertificacionId).setParameter("estado", pEstado).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vSolicitudCertificacion));
		}

		return vSolicitudCertificacion;
	}

	/**
	 * Recuperar Lista SolicitudCertificacion por contribuyenteId
	 * 
	 * @author: Reynaldo Chambi
	 * @Fecha:
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 02/08/2018
	 * @param pContribuyenteId,
	 *            número de identificación del contribuyente
	 * @return Devuelve objeto respuesta SreSolicitudCertificacion
	 */
	// TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyente(Long pContribuyenteId) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionIdContribuyente.");
		List<SreSolicitudCertificacion> vEntidad = new ArrayList<SreSolicitudCertificacion>();
		try {
			String vhql = "FROM SreSolicitudCertificacion p WHERE p.contribuyenteId = :pContribuyenteId and p.estadoId = 'AC' ";
			vEntidad = getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId)
					.getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	// TODO
	@Override
	public SreSolicitudCertificacion modificarEstadoSolicitudCertificacionSistema(Long pSolicitudCertificacionId,
			Date pFechaUltimaModificacion, Integer pEstadoSolicitudCertificacionId) {

		Integer estado = ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO;
		SreSolicitudCertificacion vResultadoEntidad = this.recuperaSolicitudesCertificaciones(pSolicitudCertificacionId,
				estado);
		LOG.info("Ingresando modificarEstadoSolicitudCertificacionSistema.");
		if (pSolicitudCertificacionId > 0 && pSolicitudCertificacionId != null && pFechaUltimaModificacion != null
				&& pEstadoSolicitudCertificacionId > 0 && pEstadoSolicitudCertificacionId != null) {
			try {
				vResultadoEntidad.setFechaUltimaModificacion(pFechaUltimaModificacion);
				vResultadoEntidad.setEstadoSolicitudCertificacionId(pEstadoSolicitudCertificacionId);

				getSession().save(vResultadoEntidad);
				LOG.info("Guardando modificarEstadoSolicitudCertificacionSistema.");
			} catch (Exception e) {
				LOG.info("Informacion no recuperada.");
				LogExcepcion.registrar(e, LOG, MethodSign.build(vResultadoEntidad));
			}
		} else {
			LOG.info("Informacion no recuperada.");
			LOG.info("False");
		}

		return vResultadoEntidad;
	}

	/**
	 * Objetivo: recuperar la lista de solicitud por contribuyente y oficina Creado
	 * por: Carmen Rosa Silva Fecha: 20/06/2018 Modificado por: Gualberto Condori
	 * Fecha de Modificacion: 03/08/2018 Modificado por: Ivan Salas - Se añadio
	 * estado - 27/09/2018
	 */
	// TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionContribuyenteOficina(Long pContribuyenteId,
			Integer pOficinaId) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionContribuyenteOficina.");
		List<SreSolicitudCertificacion> vEntidad = new ArrayList<SreSolicitudCertificacion>();
		try {
			String vQuery = "SELECT s FROM SreSolicitudCertificacion s "
					+ "WHERE s.personaContribuyenteId = :pContribuyenteId " + "and s.estadoSolicitudCertificacionId = "
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO
					+ " and s.estadoId = 'AC' ORDER BY s.fechaUltimaModificacion DESC";
			vEntidad = getSession().createQuery(vQuery).setParameter("pContribuyenteId", pContribuyenteId)
					.getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			e.printStackTrace();
		}
		return vEntidad;
	}

	/**
	 * Recuperar objeto Solicitud Certificación
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 27/06/2018
	 * @param pSistemaId,
	 *            número de identificación del sistema
	 * @param pContribuyenteId,
	 *            número de identificación del contribuyente
	 * @param pTipoSolicitudId,
	 *            número de identificación del tipo solicitud
	 * @return Devuelve objeto respuesta SreSolicitudCertificacion 19-11-2018
	 */
	// TODO
	@Override
	public SreSolicitudCertificacion obtenerSolicitudCertificacion(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando obtenerCuis.");
		SreSolicitudCertificacion vEntidad = new SreSolicitudCertificacion();
		try {
			String vQuery = "SELECT s FROM SreSolicitudCertificacion s WHERE s.solicitudCertificacionId = :pSolicitudCertificacionId and s.estadoId = 'AC' ";
			vEntidad = (SreSolicitudCertificacion) getSession().createQuery(vQuery)
					.setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			vEntidad = null;
		}
		return vEntidad;
	}

	/**
	 * Recuperar Lista Solicitud Certificación por pSistemaId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 06/07/2018
	 * @param pSistemaId,
	 *            número de identificación de sistema
	 * @return Devuelve lista de objetos respuesta SreSolicitudCertificacion
	 */
	// TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionSistemaId(Long pSistemaId) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionSistemaId.");
		List<SreSolicitudCertificacion> vEntidad = new ArrayList<SreSolicitudCertificacion>();
		try {
			String vQuery = "SELECT s FROM SreSolicitudCertificacion s WHERE s.sistemaId = :pSistemaId and s.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vQuery).setParameter("pSistemaId", pSistemaId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			e.printStackTrace();
		}
		return vEntidad;
	}

	/**
	 * Recuperar Lista SolicitudCertificacion por contribuyenteId y estado
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 10/08/2018
	 * @param pContribuyenteId,
	 *            número de identificación del contribuyente
	 * @param pEstadoSolicitudCertificacionId,
	 *            número de identificación del estado de solicitud de certificacion
	 * @return Devuelve objeto respuesta SreSolicitudCertificacion
	 */
	// TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyenteEstado(Long pContribuyenteId,
			Integer pEstadoSolicitudCertificacionId) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionIdContribuyenteEstado.");
		List<SreSolicitudCertificacion> vEntidad = new ArrayList<SreSolicitudCertificacion>();
		try {
			String vhql = "FROM SreSolicitudCertificacion p WHERE p.personaContribuyenteId = :pContribuyenteId and p.estadoSolicitudCertificacionId = :pEstadoSolicitudCertificacionId and p.estadoId = 'AC' ";
			vEntidad = getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId)
					.setParameter("pEstadoSolicitudCertificacionId", pEstadoSolicitudCertificacionId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pContribuyenteId,número
	 *            de identificacion del contribuyente
	 * @param pEstadoSolicitudCertificacionId,
	 *            estado de certificacion
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(long pPersonaContribuyenteId,
			int pEstadoSolicitudCertificacionId) {
		List<SreSolicitudCertificacion> vEntidadLista = new ArrayList<SreSolicitudCertificacion>();
		try {
			String vhql = "SELECT sol, cl.descripcion as descripcionEstadoPrueba, sis.nombreSistema, sis.version FROM SreSolicitudCertificacion sol "
					    + " INNER JOIN SreSistemas sis on (sol.sistemaId = sis.sistemaId and sis.estadoId = 'AC') "
					    + " INNER JOIN SrtClasificadores cl on (sol.estadoSolicitudCertificacionId = cl.clasificadorId and cl.estadoId = 'AC') "
						+ " WHERE sol.personaContribuyenteId = :pPersonaContribuyenteId and sol.estadoSolicitudCertificacionId <> :pEstadoSolicitudCertificacionId and sol.estadoId = 'AC' "
					    + " order by sol.fechaUltimaModificacion desc, sis.nombreSistema desc";
			
			List<Object[]> vResultadoQuery = getSession().createQuery(vhql).setParameter("pPersonaContribuyenteId", pPersonaContribuyenteId).setParameter("pEstadoSolicitudCertificacionId", pEstadoSolicitudCertificacionId).getResultList();
			
			if (vResultadoQuery.size() > 0) 
			{
				for ( Object[] object : vResultadoQuery ) 
				{
					if(object[0] instanceof SreSolicitudCertificacion)
					{
						SreSolicitudCertificacion vSreSolicitudCertificacion= new SreSolicitudCertificacion();
						vSreSolicitudCertificacion = (SreSolicitudCertificacion)object[0];
						
						vSreSolicitudCertificacion.setDescripcion((String)object[1]);
						vSreSolicitudCertificacion.setNombreSistema((String)object[2]);
						vSreSolicitudCertificacion.setVersion((String)object[3]);
						vEntidadLista.add(vSreSolicitudCertificacion);
					}
		        }
			}	
			
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidadLista));
		}
		return vEntidadLista;
	}

	/**
	 * Objetivo: recuperar la lista de solicitud por contribuyente, con estado para
	 * autorizar Creado por: Carmen Rosa Silva Fecha: 20/06/2018 Modificado por:
	 * Gualberto Condori Fecha de Modificacion: 03/08/2018 Modificado por: Ivan
	 * Salas - Se añadio estado - 27/09/2018 modificado rosario garcia 19/11/2018
	 */
	@Override
	public List<SreDetalleSolicitudesCertificaciones> obtenerSolicitudesCertificacionParaAutorizarContribuyente(
			long pPersonaContribuyenteId) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionContribuyenteOficina.");
		System.out.println("Datos " + pPersonaContribuyenteId);
		List<SreDetalleSolicitudesCertificaciones> vEntidad = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try {
			String vQuery = "SELECT r "
					+ "FROM SreSolicitudCertificacion as s, SreDetalleSolicitudesCertificaciones as r, SreSistemas as t "
					+ "WHERE s.personaContribuyenteId = :pPersonaContribuyenteId "
					+ "and s.estadoSolicitudCertificacionId = "
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO
					+ " and s.estadoId = 'AC' and s.solicitudCertificacionId = r.solicitudCertificacionId and r.sistemaId = t.sistemaId ORDER BY s.fechaUltimaModificacion DESC";
			vEntidad = getSession().createQuery(vQuery).setParameter("pPersonaContribuyenteId", pPersonaContribuyenteId)
					.getResultList();
			System.out.println("datos dao" + vQuery);
			LOG.info("Informacion recuperada." + vQuery);
		} catch (NoResultException e) {
			return vEntidad;
		} catch (Exception e) {
			LOG.error("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			vEntidad = null;
		}
		return vEntidad;
	}

	// IASC - Para obtener las solicitudes q se encuentran en proceso - 21/09/2018
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(Long pContribuyenteId) {
		List<SreSolicitudCertificacion> vEntidad = new ArrayList<SreSolicitudCertificacion>();
		try {
			String vhql = "FROM SreSolicitudCertificacion p WHERE p.contribuyenteId = :pContribuyenteId and p.estadoId = 'AC' and p.estadoSolicitudCertificacionId in ("
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO + ","
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_PARA_AUTORIZAR + ","
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_VERIFICACION + ")";
			vEntidad = getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId)
					.getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	/**
     * Objetivo: recuperar la lista de solicitud por contribuyente, con estado para autorizar
     * Creado por: Mallea Torrez Sergio
     * Fecha: 
     * @modificado: Noemi Mamani
     * @fechamodificacion: 14/12/2018
     * @descripcion: se modifico los estados
     * */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSolicitudCertificacion> listadoCertificados() {
		List<SreSolicitudCertificacion> vEntidad = new ArrayList<SreSolicitudCertificacion>();
		try {
			
			
			String vhql = "SELECT sol " 
						 +"FROM SreSolicitudCertificacion as sol,SreSistemas as s "
						 +"WHERE sol.sistemaId = s.sistemaId"
						 +" and s.tipoSistemaId = " + ConstFacturacion.TIPO_SISTEMA_PROVEEDOR
						 +" and s.estadoId = 'AC'"
						 +" and sol.estadoSolicitudCertificacionId not in ("
						 + ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_RECHAZADO + "," 
						 + ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_CANCELADO + ","
						 + ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO + ")"
						 +" and sol.estadoId = 'AC'"
						 +" order by sol.solicitudCertificacionId asc"; 					

			vEntidad = getSession().createQuery(vhql).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {	
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	@Override
	public int obtenerSistemasCertificacionPaginadoTotal(Map<String, Object> pFiltros) {
		CriteriaBuilder vCriteriBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> vCriteriaQuery = vCriteriBuilder.createQuery(Long.class);
		Root<SreSolicitudCertificacionListado> vEntidad = vCriteriaQuery.from(SreSolicitudCertificacionListado.class);
		List<Predicate> criterios = this.generarCriterios(vCriteriBuilder, vEntidad, pFiltros);
		vCriteriaQuery.where(vCriteriBuilder.and(criterios.toArray(new Predicate[] {})));
		vCriteriaQuery.select(vCriteriBuilder.count(vEntidad));
		return getSession().createQuery(vCriteriaQuery).getSingleResult().intValue();
	}

	@Override
	public List<RecuperaListaSistemasCertificacionDto> obtenerSistemasCertificacionPaginado(int pPrimerRegistro, int pTamanioPagina,
			String pCampoOrden, boolean pAscendente, Map<String, Object> pFiltros) {
		
		CriteriaBuilder vCriteriBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<RecuperaListaSistemasCertificacionDto> vCriteriaQuery = vCriteriBuilder.createQuery(RecuperaListaSistemasCertificacionDto.class);
		Root<SreSolicitudCertificacionListado> vEntidad = vCriteriaQuery.from(SreSolicitudCertificacionListado.class);
		List<Predicate> criterios = this.generarCriterios(vCriteriBuilder, vEntidad, pFiltros);
		
		vCriteriaQuery.where(vCriteriBuilder.and(criterios.toArray(new Predicate[] {})));
		if (pCampoOrden != null && !pCampoOrden.isEmpty()) {
			vCriteriaQuery.orderBy(pAscendente ? vCriteriBuilder.asc(vEntidad.get(pCampoOrden))
					: vCriteriBuilder.desc(vEntidad.get(pCampoOrden)));
		}
		CriteriaQuery<RecuperaListaSistemasCertificacionDto> vSelect = vCriteriaQuery.multiselect(
		vEntidad.get("solicitudCertificacionId"),
		vEntidad.get("nit"),
		vEntidad.get("razonSocial"),
		vEntidad.get("personaContribuyenteId"),
		vEntidad.get("nombreSistema"),
		vEntidad.get("version"),
		vEntidad.get("estadoCertificacion"),
		vEntidad.get("modalidad"));
		TypedQuery<RecuperaListaSistemasCertificacionDto> vQuery = getSession().createQuery(vSelect);
		vQuery.setFirstResult(pPrimerRegistro);
		vQuery.setMaxResults(pTamanioPagina);
		return vQuery.getResultList();
	}
	
	public List<Predicate> generarCriterios(CriteriaBuilder pCriteria, Root<SreSolicitudCertificacionListado> pEntidad, Map<String, Object> pFiltros) {
		List<Predicate> vPredicates = new ArrayList<>();
		if (pFiltros != null && pFiltros.size() > 0) {
			for (Map.Entry<String, Object> value : pFiltros.entrySet()) {
				if (value.getKey().equals("globalFilter")) {
						Predicate lc1 = pCriteria.like(pCriteria.upper(pEntidad.get("nombreSistema")), "%" + value.getValue().toString().toUpperCase() + "%");
						Predicate lc2 = pCriteria.like(pCriteria.upper(pEntidad.get("version")), "%" + value.getValue().toString().toUpperCase() + "%");
						Predicate lc3 = pCriteria.like(pCriteria.upper(pEntidad.get("estadoCertificacion")), "%" + value.getValue().toString().toUpperCase() + "%");
						Predicate lc4 = pCriteria.like(pCriteria.upper(pEntidad.get("modalidad")), "%" + value.getValue().toString().toUpperCase() + "%");
						Predicate lc5 = pCriteria.like(pCriteria.upper(pEntidad.get("razonSocial")), "%" + value.getValue().toString().toUpperCase() + "%");
						Predicate lc6 = pCriteria.like(pEntidad.get("nit").as(String.class), "%" + value.getValue().toString() + "%");
						vPredicates.add(pCriteria.or(lc1, lc2, lc3, lc4, lc5, lc6));
				}
			}
		}

		return vPredicates;
	}

}
