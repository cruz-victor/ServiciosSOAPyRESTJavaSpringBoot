package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreSistemas;

public interface IRegistrarTipoDocumentoSistemaDomain {

//IASC
public boolean registrarTipoDocumentoSistema(SreSistemas pSistema, Long pSolicitudCertificacionId, List<Integer> pSolicitud);

}