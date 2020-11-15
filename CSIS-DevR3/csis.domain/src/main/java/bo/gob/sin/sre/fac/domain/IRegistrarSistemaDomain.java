package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreSistemas;


public interface IRegistrarSistemaDomain {

	//IASC
	public String generarCodigoSistema(SreSistemas pSolicitud);
	
	//IASC
	public SreSistemas registrarSistemaACertificar(SreSistemas pSolicitud);
	
}
