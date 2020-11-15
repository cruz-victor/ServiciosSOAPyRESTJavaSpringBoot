package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.dto.SolicitudGeneracionCuisDto;

public interface IGenerarCodigoCuisDomain {

	//FRRA
	public SolicitudGeneracionCuisDto generarCuis(SolicitudGeneracionCuisDto pSolicitud);
	
	//RCR
	public String generarCodigoCuis(SolicitudGeneracionCuisDto pSolicitud);
	
}
