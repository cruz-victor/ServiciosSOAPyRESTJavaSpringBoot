package bo.gob.sin.sre.fac.dao.impl;



import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import bo.gob.sin.sre.fac.dao.IArchivosDao;
import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;


//IASC
@Repository
@Transactional
public class ArchivosDaoImpl extends AbstractGenericDao<SreArchivos> implements IArchivosDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
