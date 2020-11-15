package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;

public interface IDetalleCertificacionesSistemasDomain {

	//RCR
	public SreDetalleCertificacionesSistemas registraDetalleCertificacionesSistemas(SreDetalleCertificacionesSistemas pSolicitudSistema);
	
	public List<SreDetalleCertificacionesSistemas> obtenerListaDetalleCertificacionSistemas(long pSolicitudCertificacionId, long pSistemaId);
	
	public SreDetalleCertificacionesSistemas obtenerDetalleCertificacionSistemas(long pDetalleCertificacionSistemaId);
}
