package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.sre.fac.dao.IArchivosTmpDao;
import bo.gob.sin.sre.fac.model.SreArchivosTmp;

@Repository
@Transactional
public class ArchivosTmpDaoImpl extends AbstractGenericDao<SreArchivosTmp> implements IArchivosTmpDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
