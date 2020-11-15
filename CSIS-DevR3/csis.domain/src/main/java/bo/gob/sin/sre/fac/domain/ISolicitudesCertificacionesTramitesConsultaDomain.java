package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

public interface ISolicitudesCertificacionesTramitesConsultaDomain {

	//IASC
	public SreSolicitudCertificacion obtenerDatosTramiteCertificacion(long pTramiteId) ;
	
}
