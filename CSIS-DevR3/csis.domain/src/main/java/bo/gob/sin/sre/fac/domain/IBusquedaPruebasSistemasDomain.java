package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.dto.RespuestaListaPruebasDto;


public interface IBusquedaPruebasSistemasDomain {

	//IASC
	public RespuestaListaPruebasDto recuperaListaPruebasPorTipo(Integer pSolicitud);
	
}
