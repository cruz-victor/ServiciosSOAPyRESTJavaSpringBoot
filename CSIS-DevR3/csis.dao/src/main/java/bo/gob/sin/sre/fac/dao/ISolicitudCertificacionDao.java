package bo.gob.sin.sre.fac.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;


public interface ISolicitudCertificacionDao extends GenericDao<SreSolicitudCertificacion>  {
	
	//IASC
	public SreSolicitudCertificacion obtenerDatosTramiteCertificacion(long pTramiteId);

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
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(long pPersonaContribuyenteId,int pEstadoSolicitudCertificacionId);
	
	public SreSolicitudCertificacion recuperaSolicitudesCertificaciones(Long pSolicitudCertificacionId, Integer pEstado);

	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyente(Long pContribuyenteId);

	public SreSolicitudCertificacion modificarEstadoSolicitudCertificacionSistema(Long pSolicitudCertificacionId,
			Date pFechaUltimaModificacion, Integer pEstadoSolicitudCertificacionId);

	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionContribuyenteOficina(Long pContribuyenteId,
			Integer pOficinaId);

	//19/11/2018
	public SreSolicitudCertificacion obtenerSolicitudCertificacion(Long pSolicitudCertificacionId);

	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionSistemaId(Long pSistemaId);

	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyenteEstado(Long pContribuyenteId,
			Integer pEstadoSolicitudCertificacionId);
	
	
	/***
	 * Retorna las solicitudes de certificacion por Id de contribuyente para autorizar 
	 * @author rosario.garcia
	 * @param pPersonaContribuyenteId, es el Id de la persona contribuyente
	 * @return retorna las solicitudes de certificacion con estado para autorizar
	 * @Fecha 19/11/2018
	 */
	public List<SreDetalleSolicitudesCertificaciones> obtenerSolicitudesCertificacionParaAutorizarContribuyente(long pPersonaContribuyenteId);

	//****************
	//TODO: IASC viene del archivo original 
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(Long pContribuyenteId);
	
	//sergio mallea
	public List<SreSolicitudCertificacion> listadoCertificados();
	
	// obtener lista de sistemas certificados paginado
	public int obtenerSistemasCertificacionPaginadoTotal(Map<String, Object> pFiltros);
	
	public List<RecuperaListaSistemasCertificacionDto> obtenerSistemasCertificacionPaginado(int pPrimerRegistro, int pTamanioPagina, String pCampoOrden,
			boolean pAscendente, Map<String, Object> pFiltros);	
}
