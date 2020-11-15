package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.sre.fac.dao.IDetalleSolicitudesCertificacionesDao;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

//IASC
@Repository
@Transactional
public class DetalleSolicitudesCertificacionesDaoImpl extends AbstractGenericDao<SreDetalleSolicitudesCertificaciones>implements IDetalleSolicitudesCertificacionesDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(DetalleSolicitudesCertificacionesDaoImpl.class);

	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 14/11/2018
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return Devuelve objeto SreDetalleSolicitudesCertificaciones.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreDetalleSolicitudesCertificaciones> obtenerListaDetalleSolicitudCertificacionId(long pSolicitudCertificacionId) {
		List<SreDetalleSolicitudesCertificaciones> vEntidad = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try {
			String vhql = "FROM SreDetalleSolicitudesCertificaciones p WHERE p.solicitudCertificacionId=:pSolicitudCertificacionId and p.estadoId= 'AC' ";
			vEntidad = getSession().createQuery(vhql).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		} catch (NoResultException e) {
			return new ArrayList<SreDetalleSolicitudesCertificaciones>();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
	
	/**
	 * Recuperar Lista Detalle de Tipos documentos Sistemas por SolicitudCertificacionId.
	 * @author: Wilson Limachi Choque
	 * @Fecha: 17/07/2019
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return Devuelve objeto Lista SreDetalleSolicitudesCertificaciones.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreTiposDocumentosSistemas> obtenerListaTipoDocumentosSistema(long pSolicitudCertificacionId) 
	{
		List<SreTiposDocumentosSistemas> vEntidad = new ArrayList<>();
		try {
			String vhql = "FROM SreTiposDocumentosSistemas p WHERE p.solicitudCertificacionId=:pSolicitudCertificacionId and p.estadoId= 'AC' ";
			vEntidad = getSession().createQuery(vhql).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		} 
		catch (NoResultException e) 
		{
			return new ArrayList<SreTiposDocumentosSistemas>();
		} 
		catch (Exception e) 
		{
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
	
	/**
	 * @author rosario.garcia
	 * @fecha 27/11/2018
	 * @param pSistemId es el Id del sistema
	 * @modifiicado 29/11/2018 
	 * @return lista de solicitud de certificacion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreDetalleSolicitudesCertificaciones> recuperaSolicitudesCertificacionPorIdSistema(Long pSistemId) {
		List<SreDetalleSolicitudesCertificaciones> vEntidad = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try {
			String vhql = "FROM SreDetalleSolicitudesCertificaciones p WHERE p.sistemaId = :pSistemId and p.estadoId = 'AC'"; 
			vEntidad = getSession().createQuery(vhql).setParameter("pSistemId", pSistemId).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		}catch (NoResultException e) {
			return new ArrayList<SreDetalleSolicitudesCertificaciones>();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
	
	/**
	 * @author wilson.limachi
	 * @fecha 03/09/2019
	 * @param pSistemId es el Id del sistema
	 * @param pIdProveedor es el Id
	 * @return lista de SreDetalleSolicitudesCertificaciones
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreDetalleSolicitudesCertificaciones> recuperaSolicitudesDetalleCertificacionPorIdSistema(Long pSistemId, Long pIdContribuyenteProveedor) 
	{
		List<SreDetalleSolicitudesCertificaciones> vEntidad = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		try 
		{
			String vhql = "select d "
					    + "FROM SreSolicitudCertificacion s inner join SreDetalleSolicitudesCertificaciones d on (s.solicitudCertificacionId = d.solicitudCertificacionId) "
					    + "WHERE d.estadoId = 'AC' and s.estadoId = 'AC' "
					    + "and s.sistemaId = :pSistemId "
					    + "and s.personaContribuyenteId = :pIdContribuyenteProveedor "
					    + "and s.estadoSolicitudCertificacionId = :pEstadoSolicitudCertificacionId "; 
			vEntidad = getSession().createQuery(vhql).setParameter("pSistemId", pSistemId).setParameter("pEstadoSolicitudCertificacionId", ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO).setParameter("pIdContribuyenteProveedor", pIdContribuyenteProveedor).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		}
		catch (NoResultException e) 
		{
			return new ArrayList<SreDetalleSolicitudesCertificaciones>();
		} 
		catch (Exception e) 
		{
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
}
