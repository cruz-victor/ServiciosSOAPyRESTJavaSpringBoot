package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISolicitudCertificacionDao;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionConsultaDomain;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacionListado;

@Service
@Transactional
public class SolicitudCertificacionConsultaDomainImpl implements ISolicitudCertificacionConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SolicitudCertificacionConsultaDomainImpl.class);

	@Autowired
	ISolicitudCertificacionDao iSolicitudCertificacionDao;
	
	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pContribuyenteId,número de identificacion del contribuyente
	 * @param pEstadoSolicitudCertificacionId, estado de certificacion
	 * @return   Devuelve objeto SreSolicitudCertificacion.
	 */
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(Long pContribuyenteId, Integer pEstadoCertificacion) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionParaCancelar ");
		List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();
		try {
			vListaRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionParaCancelar(pContribuyenteId, pEstadoCertificacion);	

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar recuperaSolicitudesCertificacionParaCancelar: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}
	
	/**
	 * Recuperar Lista Solicitud Certificación por pContribuyenteId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 20/06/2018
	 * @param pContribuyenteId, número de identificación del contribuyente
	 * @param pOficinaId, número de identificación de oficina
	 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
	 */
	@Override
	public List<SreDetalleSolicitudesCertificaciones> obtenerSolicitudesCertificacionParaAutorizarContribuyente(long pContribuyenteId) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionContribuyenteOficina ");
		List<SreDetalleSolicitudesCertificaciones> vListaRespuesta = new ArrayList<SreDetalleSolicitudesCertificaciones>();

		try {
			vListaRespuesta = iSolicitudCertificacionDao.obtenerSolicitudesCertificacionParaAutorizarContribuyente(pContribuyenteId);	

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar recuperaSolicitudesCertificacionContribuyenteOficina: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}
	
	/**
	 * Obtiene el registro de solicitud de certificacion por el ID.
	 * @author rosario garcia
	 * @param pSolicitudCertificacionId, el Id de la solicitud
	 * @return retorna la solicitud de certificacion segun el Id
	 * @Fecha 19/11/2018 
	 */
	@Override
	public SreSolicitudCertificacion obtenerSolicitudCertificacion(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando a recuperar SreSolicitudCertificacion");
		SreSolicitudCertificacion vRespuesta = new SreSolicitudCertificacion();
		try {
			vRespuesta = iSolicitudCertificacionDao.findById(pSolicitudCertificacionId);
		}catch(Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta = null;
		}
		LOG.info("Saliendo de recuperar SreSolicitudCertificacion");
		return vRespuesta;
	}
	
	/**
	 * Obtiene el registro de solicitud de certificacion por el ID.
	 * @author 
	 * @param pSolicitudCertificacionId, el Id de la solicitud
	 * @return retorna la solicitud de certificacion segun el Id
	 * @Fecha 13/12/2018
	 */
	
	@Override
	public List<SreSolicitudCertificacion> ListaSolicitudesCertificacion() {
		LOG.info("Ingresando ListaSolicitudesCertificacion ");
		List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();

		try {
			vListaRespuesta = iSolicitudCertificacionDao.listadoCertificados();	

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar ListaSolicitudesCertificacion: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}

	@Override
	public int obtenerSistemasCertificacionPaginadoTotal(Map<String, Object> pFiltros) {
		return iSolicitudCertificacionDao.obtenerSistemasCertificacionPaginadoTotal(pFiltros);
	}

	@Override
	public List<RecuperaListaSistemasCertificacionDto> obtenerSistemasCertificacionPaginado(int pPrimerRegistro, int pTamanioPagina,
			String pCampoOrden, boolean pAscendente, Map<String, Object> pFiltros) {
		return iSolicitudCertificacionDao.obtenerSistemasCertificacionPaginado(pPrimerRegistro, pTamanioPagina, pCampoOrden, pAscendente, pFiltros);
	}
		
}
