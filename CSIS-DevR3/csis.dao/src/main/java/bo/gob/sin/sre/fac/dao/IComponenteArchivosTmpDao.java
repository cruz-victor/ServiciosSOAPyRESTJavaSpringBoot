package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;

public interface IComponenteArchivosTmpDao extends GenericDao<SreComponentesArchivosTmp>{

	public SreComponentesArchivosTmp obtenerComponentesArchivoTmp(Long pComponenteArchivoTmpId);
}
