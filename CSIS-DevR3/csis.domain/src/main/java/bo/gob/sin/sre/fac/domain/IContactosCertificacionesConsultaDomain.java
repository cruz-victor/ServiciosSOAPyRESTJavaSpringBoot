package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreContactosCertificaciones;

public interface IContactosCertificacionesConsultaDomain {

	//IASC
	public List<SreContactosCertificaciones> obtieneListaContactosSolicitud(Long pSolicitudCertificacionId);
}
