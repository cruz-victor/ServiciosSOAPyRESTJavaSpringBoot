package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.ISistemasDeCertificacionDao;
import bo.gob.sin.sre.fac.model.SreSistemasDeCertificacion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Repository
@Transactional

public class SistemasDeCertificacionImpl extends AbstractGenericDao<SreSistemasDeCertificacion>
		implements ISistemasDeCertificacionDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(SistemasDeCertificacionImpl.class);
	
	//private Class<T> persistentClass;
	
	@Override
	public List<SreSistemasDeCertificacion> recuperarSistemasCertificadosPorProveedor(long contribuyenteId) {

		List<SreSistemasDeCertificacion> vListaSistemasCertificadosPorProveedor = new ArrayList<>();
		try {
			String vHqlSistemasCertificados = "select s.sistemaId, s.nombreSistema, s.modalidadFacturacionId, sc.correoContacto "
					+ "from SreSolicitudCertificacion as sc, SreSistemas as s " + "where sc.sistemaId=s.sistemaId "
					+ "and s.estadoId='" + ConstFacturacion.ESTADO_ACTIVO + "' "
					+ "and sc.contribuyenteId=:contribuyenteId " + "and sc.estadoSolicitudCertificacionId="
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_AUTORIZADO + " " + "and s.tipoSistemaId="
					+ ConstFacturacion.TIPO_SISTEMA_PROVEEDOR + " " + "and s.estadoSistemaId="
					+ ConstFacturacion.ESTADO_SISTEMA_CERTIFICADO + " ";
			vListaSistemasCertificadosPorProveedor = getSession().createQuery(vHqlSistemasCertificados)
					.setParameter("contribuyenteId", contribuyenteId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vListaSistemasCertificadosPorProveedor;
	}	

	/**
	 * Recuperar Sistema En Proceso por Contribuyente
	 * 
	 * @author: Fabio Ramos
	 * @Fecha: 27/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 18/06/2018
	 * @param pContribuyenteId, número de id identificador en Padron
	 * @return   Devuelve una lista del tipo SreSistemasCertificacion.
	 */
	public List<SreSistemasDeCertificacion> recuperarSistemasEnProcesoPorContribuyente(Long contribuyenteId) {
		LOG.info("Ingresando recuperarSistemasEnProcesoPorContribuyente.");
		List<SreSistemasDeCertificacion> vEntidad = new ArrayList<SreSistemasDeCertificacion>();
		try {
			String vQuery = "select sc.sistemaId, s.estadoSistemaId, s.nombreSistema, s.version, s.modalidadFacturacionId, "
					+ "sc.solicitudCertificacionId, sc.cuis, sc.personaContacto, sc.telefonoContacto, sc.correoContacto, sc.modalidadId, sc.estadoSolicitudCertificacionId, sc.contribuyenteId "
					+ "from SreSolicitudCertificacion as sc, SreSistemas as s " + "where sc.sistemaId=s.sistemaId "
					+ "and s.estadoId='" + ConstFacturacion.ESTADO_ACTIVO + "' "
					+ "and sc.contribuyenteId=:contribuyenteId " + "and sc.estadoSolicitudCertificacionId='"
					+ ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO + "' " + "and (s.estadoSistemaId='"
					+ ConstFacturacion.ESTADO_SISTEMA_INICIADO + "' or s.estadoSistemaId='"
					+ ConstFacturacion.ESTADO_SISTEMA_OBSERVADO + "') ";
			vEntidad = getSession().createQuery(vQuery).setParameter("contribuyenteId", contribuyenteId).getResultList();
			
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			e.printStackTrace();
		}
		return vEntidad;
	}

	// FRA
	@Override
	public List<SreSistemasDeCertificacion> recuperarSistemasCertificadosAsociadosNit(Long contribuyenteId) {

		List<SreSistemasDeCertificacion> vListaSistemasCertificadosPorContribuyente = new ArrayList<SreSistemasDeCertificacion>();
		try {
			String vHqlSistemasCertificados = "select sc.sistemaId, s.estadoSistemaId, s.nombreSistema, s.version, s.modalidadFacturacionId, "
					+ "sc.solicitudCertificacionId, sc.cuis, sc.personaContacto, sc.telefonoContacto, sc.correoContacto, sc.modalidadId "
					+ "from SreSolicitudCertificacion as sc, SreSistemas as s where sc.sistemaId=s.sistemaId "
					+ "and s.estadoId='" + ConstFacturacion.ESTADO_ACTIVO + "' and sc.contribuyenteId=:contribuyenteId ";
			vListaSistemasCertificadosPorContribuyente = getSession().createQuery(vHqlSistemasCertificados).setParameter("contribuyenteId", contribuyenteId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vListaSistemasCertificadosPorContribuyente;
	}

//	protected List<T> findByCriteria(Criterion... criterion) {
//        Criteria crit = getSession().createCriteria(getPersistentClass());
//        for (Criterion c : criterion) {
//            crit.add(c);
//        }
//        return crit.list();
//    }
	
//	public Class<T> getPersistentClass() {
//		return persistentClass;
//	}
}
