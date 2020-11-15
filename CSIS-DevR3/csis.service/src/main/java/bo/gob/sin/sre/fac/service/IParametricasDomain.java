package bo.gob.sin.sre.fac.service;

import java.util.List;

import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

public interface IParametricasDomain {
	
	public ClasificadorDto obtenerDatosParametrica(Integer pClasificadorId);
	
	public List<ClasificadorDto> recuperarClasificadorPorTipo(String pTipo);
}
