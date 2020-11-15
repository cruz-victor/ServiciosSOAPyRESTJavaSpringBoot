package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

public interface IRegistrarDetalleSolicitudesCertificacionDomain {

//IASC
public SreDetalleSolicitudesCertificaciones registrarDetalleSolicitudCertificacion(SreSolicitudCertificacion pSolicitud, long pSistemaId, int pModalidadId, String pCuis);

}