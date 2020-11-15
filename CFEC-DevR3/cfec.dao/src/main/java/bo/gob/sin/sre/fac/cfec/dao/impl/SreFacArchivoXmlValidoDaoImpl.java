package bo.gob.sin.sre.fac.cfec.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.ISreFacArchivoXmlValidoDao;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlValido;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class SreFacArchivoXmlValidoDaoImpl extends AbstractGenericDao<SreFacArchivoXmlValido> implements ISreFacArchivoXmlValidoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
