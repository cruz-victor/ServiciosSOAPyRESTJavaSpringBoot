package bo.gob.sin.sre.fac.csis.query;

import java.util.List;

import bo.gob.sin.str.cps.clas.model.StrCpsClasificador;

public interface IClasificadorQuery {

	/**
	 * Lista los clasificadores con por agrupador 
	 * @param agrupadorId - Id del agrupador a consultar
	 * @return Lista de clasificadores pertenecientes al grupo ID
	 */
	List<StrCpsClasificador> listarClasificadorPorTipo(String tipoClasificador);	
}
