package bo.gob.sin.sre.fac.domain;

import java.util.Date;

import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;


public interface IRegistrarSolicitudCertificacionDomain {

	//IASC
	public SreSolicitudCertificacion registrarSolicitudCertificacion(long pContribuyenteId, long pTramite, long pUsuario, long pSistemaId);
	
	public SreSolicitudCertificacion registrarFechaCertificadoSolicitudCertificacion(long pUsuario, long pSolicitud, Date pFechaAprobacion);
	
	/**3
     * Objetivo: registrar recertificacion solicitud de sistemas.
     * Creado por: Wilson Limachi.
     * Fecha: 20/08/2019
     * Modificado por: Wilson Limachi
     * Fecha de Modificacion: 20/08/2019
     */
	public SreSolicitudCertificacion registrarSolicitudRecertificacionCertificacion(long pContribuyenteId, long pTramite, long pUsuario, long pSistemaId);

	/**
     * Objetivo: registrar recertificacion solicitud de sistemas.
     * Creado por: Wilson Limachi.
     * Fecha: 22/08/2019
     * Modificado por: Wilson Limachi
     */
	public SreSolicitudCertificacion actualizarEstadoHistoricoCertificadoSolicitudCertificacion(long pSolicitudCertificaiconId, long pUsuario);
}
