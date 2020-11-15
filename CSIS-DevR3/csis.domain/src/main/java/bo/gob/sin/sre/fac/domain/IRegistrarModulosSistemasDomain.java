package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreSistemas;


public interface IRegistrarModulosSistemasDomain {

	//IASC
	public boolean registraModulosSistemaACertificar(SreSistemas pSolicitudSistema, Long pSolicitudCertificacionId, List<Integer> pTiposModulos);
	
}
