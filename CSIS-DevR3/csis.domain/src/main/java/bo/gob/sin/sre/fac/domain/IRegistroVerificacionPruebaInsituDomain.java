package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.model.SreRegistroVerificacionPruebaInsitu;

public interface IRegistroVerificacionPruebaInsituDomain {

	public Long guardar(RegistroVerificacionInsituDto pSolicitud);
	
	public List<SreRegistroVerificacionPruebaInsitu> recuperarListaRegistroObservacionesInSitu(long pSolicitudCertificacionId) ;

	public Long guardarVisitaInsitu(RegistroVerificacionInsituDto pSolicitud);
	
	public Long actualizarVisitaInsitu(RegistroVerificacionInsituDto pSolicitud);
}
