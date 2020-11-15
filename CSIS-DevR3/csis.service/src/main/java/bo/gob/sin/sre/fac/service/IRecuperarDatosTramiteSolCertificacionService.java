package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.DatosTramiteSolCertificacionDto;

public interface IRecuperarDatosTramiteSolCertificacionService {

	//IASC 
	public DatosTramiteSolCertificacionDto obtenerDatosTramiteCertificacion(long pTramiteId);
}
