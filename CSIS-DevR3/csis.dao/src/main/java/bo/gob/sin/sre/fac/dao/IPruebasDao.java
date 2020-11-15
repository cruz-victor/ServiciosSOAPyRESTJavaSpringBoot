package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SrePruebas;

public interface IPruebasDao extends GenericDao<SrePruebas> {

	public List<SrePruebas> obtenerListaPruebas(Integer pTipoPruebaId);

	public SrePruebas obtenerPrueba(Long pPruebaId);

	public SrePruebas obtenerListasPrueba(Integer pTipoPruebaId);
}
