package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;

public interface IPruebasAutomaticasDao extends GenericDao<SrePruebasAutomaticas> {

	
	//IASC
	public List<SrePruebasAutomaticas> obtenerPruebasAutomaticasPorDocFiscal(Integer pTipoDocumentoFiscalId, Integer pModalidadId);
	
	/**
	 * Recupera la entidad SrePruebasAutomaticas por su id principal
	 * 
	 * @author: Levi Laura Ramos
	 * @Fecha: 03/12/2018
	 * @param pPruebaAutomaticaId, número de identificacion de un sistema
	 * @return   Devuelve objeto SrePruebasAutomaticas.
	 */
	public SrePruebasAutomaticas obtenerPruebasAutomaticasPorId (long pPruebaAutomaticaId);
}