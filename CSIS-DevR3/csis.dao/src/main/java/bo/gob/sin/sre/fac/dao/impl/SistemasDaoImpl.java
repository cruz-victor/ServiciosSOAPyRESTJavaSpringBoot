package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.ISistemasDao;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
@Repository
@Transactional
public class SistemasDaoImpl extends AbstractGenericDao<SreSistemas> implements ISistemasDao, Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private static final Logger LOG = LoggerFactory.getLogger(SistemasDaoImpl.class);
	
	
	/**
	 * Recuperar Devolver los datos de un sistmas por sistemaId.
	 * 
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 12/06/2018
	 * @param pSistemaId, número de identificacion de un sistema
	 * @return   Devuelve objeto SreSistemas.
	 */
	@Override
	public SreSistemas obtenerSolicitudSistemaCertificado(Long pSistemaId){
		LOG.info("Ingresando obtenerSolicitudSistemaCertificado.");
		SreSistemas vEntidad = new SreSistemas();
		try {
			String vhql = "FROM SreSistemas p WHERE p.sistemaId=:pSistemaId and p.estadoId = 'AC'";
			vEntidad = (SreSistemas)getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
			vEntidad = null;
		}
		return vEntidad;
	}
	
	//IASC - Para verificaciónn de existencia de registro de sistema - 21/11/2018
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSistemas> verificacionSistemaRegistrado(String pNombreSistema, Integer pModalidadFacturacionId, Long pContribuyenteId) {
		List<SreSistemas> vEntidad = new ArrayList<SreSistemas>();
		LOG.info("Ingresando verificacionSistemaRegistrado.");
		try {
			String vhql = "select sis.nombreSistema, ds.modalidadFacturacionId "
					+ "FROM SreSistemas as sis, SreDetalleSolicitudesCertificaciones ds, SreSolicitudCertificacion as sol "
					+ "WHERE sis.nombreSistema = :pNombreSistema "
					+ "and ds.sistemaId = sis.sistemaId "
					+ "and ds.modalidadFacturacionId = :pModalidadFacturacionId "
					+ "and sol.solicitudCertificacionId = ds.solicitudCertificacionId "
					+ "and sol.personaContribuyenteId = :pContribuyenteId "
					+ "and sol.estadoSolicitudCertificacionId not in (" + ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_RECHAZADO + ","
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_CANCELADO + "," + ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO
					+ ")";
					//(612,613,342)";
			vEntidad = getSession().createQuery(vhql).setParameter("pNombreSistema", pNombreSistema).setParameter("pModalidadFacturacionId", pModalidadFacturacionId).setParameter("pContribuyenteId", pContribuyenteId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
			vEntidad = null;
		}
		return vEntidad;
	}
	
	//TODO
	//gca revuperar para devolver los adtos de un sistema por sistemaid
	@SuppressWarnings("unchecked")
	public List<SreSistemas> obtenerListaSistemasCertificado(Long pSistemaId) {
		LOG.info("Ingresando a obtenerListaSistemasCertificado");
		List<SreSistemas> vResultado = new ArrayList<SreSistemas>();
		if(pSistemaId > 0 && pSistemaId != null) {
			try {
				String vhql = "FROM SreSistemas p WHERE p.sistemaId=:siatemaId";
				vResultado = getSession().createQuery(vhql).setParameter("sistemaId", pSistemaId).getResultList();
				LOG.info("Informacion recuperada");
			} catch (Exception e) {
				LOG.info("Informacion no recuperada");
				LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
				
			}
		} else {
			LOG.info("Informacion no recuperada");
		}
		return vResultado;
		
	}
	//TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSistemas> obtenerListaSolicitudSistemasCertificados(Long pContribuyenteId){
		List<SreSistemas> vEntidad = new ArrayList<SreSistemas>();
		try {
			String vhql = "FROM SreSistemas p WHERE p.usuarioRegistroId=:contribuyenteId";
			vEntidad = getSession().createQuery(vhql).setParameter("contribuyenteId", pContribuyenteId).getResultList();

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}

	//TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSistemas> obtenerListaSistemasPorTipoSistema(Long pTipoSistemaId) {

		List<SreSistemas> vEntidad = new ArrayList<SreSistemas>();
		try {
			String vhql = "FROM SreSistemas p WHERE p.tipoSistemaId=:tipoSistemaId";
			vEntidad = getSession().createQuery(vhql).setParameter("tipoSistemaId", pTipoSistemaId).getResultList();

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;

	}

	//TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSistemas> obtenerListaSistemasPorModalidad(Long pModalidadFacturacionId) {
		List<SreSistemas> vEntidad = new ArrayList<SreSistemas>();
		try {
			String vhql = "FROM SreSistemas p WHERE p.modalidadFacturacionId=:modalidadFacturacionId and p.estadoId= 'AC' ";
			vEntidad = getSession().createQuery(vhql).setParameter("modalidadFacturacionId", pModalidadFacturacionId).getResultList();

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}


	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	    */
	@Override
	//TODO
	public SreSistemas actualizarEstadosSistemas(Long pSistemaId, Integer pEstadoSistemaId) {
		SreSistemas vResultadoActualizacion = new SreSistemas();
		SreSistemas vResultadoEntidad = this.obtenerSolicitudSistemaCertificado(pSistemaId);
		Date vFechaActual = new Date();
		LOG.info("Ingresando ActualizatEstadosSistemas.");
		if(pSistemaId > 0 && pSistemaId != null && pEstadoSistemaId > 0 && pEstadoSistemaId != null) {
			try {
				vResultadoEntidad.setFechaUltimaModificacion(vFechaActual);
				vResultadoEntidad.setEstadoSistemaId(pEstadoSistemaId);
				getSession().save(vResultadoEntidad);
				LOG.info("Guardando ActualizatEstadosSistemas.");
				
			} catch (Exception e) {
				LOG.info("Informacion no recuperada.");
				LogExcepcion.registrar(e, LOG, MethodSign.build(vResultadoEntidad));
			}
		}
		else {
			LOG.info("Informacion no recuperada.");
			LOG.info("False");
		}
		
		return vResultadoActualizacion;
	}
	
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 15/06/2018
	    */

	
	
	/**
	 * Obtener sistema por Id
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 27/06/2018
	 * @param pSistemaId, número identificador del sistema
	 * @return   Devuelve objeto SreSistemas.
	 */
	//TODO
	@Override
	public SreSistemas obtenerSistemaPorId(Long pSistemaId){
		LOG.info("Ingresando obtenerSistemaPorId.");
		SreSistemas vEntidad = new SreSistemas();
		try {
			String vhql = "FROM SreSistemas p WHERE p.sistemaId = :pSistemaId and p.estadoId = 'AC'";
			vEntidad = (SreSistemas)getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			vEntidad=null;
		}
		return vEntidad;
	}

	//IASC - Verifica si existe el sistema  - 22/07/2018
	@Override
	//TODO
	public Long verificaExisteSistema(Long pSistemaId){
		Long vCantidad;
		try {
			String vhql = "SELECT COUNT(p) FROM SreSistemas p WHERE p.sistemaId = :pSistemaId and p.estadoId = 'AC'";
			vCantidad = (Long)getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).getSingleResult();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			vCantidad = 0L;
		}
		return vCantidad;
	}	
	
}
