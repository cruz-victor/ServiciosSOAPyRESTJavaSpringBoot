package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

public interface IRegistrarContactoCertificacionDomain {

//IASC
public boolean registrarContactoCertificacion(SreSolicitudCertificacion pSolicitud, List<SolicitudContactosCertificacionesDto> plistaContactos);

}