package bo.gob.sin.sre.fac.domain;

public interface IDocumentosSolicitudesABMDomain {
	
	/**
	 * Registrar Documentos Solicitudes
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/11/2018
	 * @param pUsuarioRegistroId, código de identificacion del usuario
	 * @param pSolicitudCertificacionId, Número de id de solicitud certificacion
	 * @param pTramiteId, Número de Identificacion del tramite
	 * @param pCite, Cadena que identifica el CITE
	 * @param pCodigoDocumentoId, Número de codigo documento id
	 * @return Devuelve SreDocumentosSolicitudes.
	 */
	public boolean registrarDocumentoSolicitud(long pUsuarioRegistroId, long pSolicitudCertificacionId, long pTramiteId,
			long pCite, String pCodigoDocumentoId);


}
