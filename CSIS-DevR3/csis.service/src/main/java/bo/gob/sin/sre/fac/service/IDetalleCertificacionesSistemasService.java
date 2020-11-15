package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetalleCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;

public interface IDetalleCertificacionesSistemasService {

	// RCR
	public DetallesCertificacionesSistemasDto registraDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitudSistema);
	// RCR
	public RespuestaListaDetalleCertificacionesSistemasDto obtenerListaDetalleCertificacionSistemas(long pSolicitudCertificacionId, long pSistemaId);
	// RCR
	public  DetallesCertificacionesSistemasDto anularDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitud);
}
