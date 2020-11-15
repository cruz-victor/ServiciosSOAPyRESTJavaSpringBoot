package bo.gob.sin.sre.fac.domain;

import java.util.List;
import java.util.Map;

import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacionListado;


public interface ISolicitudCertificacionConsultaDomain {

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
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(Long pContribuyenteId,Integer pEstadoCertificacion);
	
	/**
     * Objetivo: recuperar la lista de solicitudes por contribuyente, con estado para autorizar
     * Creado por: Carmen Rosa Silva
     * Fecha: 20/06/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/08/2018
     * Modificado por: Ivan Salas - Se añadio estado - 27/09/2018
     */ 
	public List<SreDetalleSolicitudesCertificaciones> obtenerSolicitudesCertificacionParaAutorizarContribuyente(long pContribuyenteId);
	
	public SreSolicitudCertificacion obtenerSolicitudCertificacion(Long pSolicitudCertificacionId);


	/**
	 * sergio mallea
	 * **/
	public List<SreSolicitudCertificacion> ListaSolicitudesCertificacion();
	
	/**
	 * freddy yuca
	 * **/
	public int obtenerSistemasCertificacionPaginadoTotal(Map<String, Object> pFiltros);
	
	public List<RecuperaListaSistemasCertificacionDto> obtenerSistemasCertificacionPaginado(int pPrimerRegistro, int pTamanioPagina, String pCampoOrden,
			boolean pAscendente, Map<String, Object> pFiltros);
	
}
