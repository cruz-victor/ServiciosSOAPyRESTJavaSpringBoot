package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;

public interface IComponentesArchivosDao extends GenericDao<SreComponentesArchivos>{

	public List<SreComponentesArchivos> obtieneComponenteArchivos(Long pSistemaId);
}
