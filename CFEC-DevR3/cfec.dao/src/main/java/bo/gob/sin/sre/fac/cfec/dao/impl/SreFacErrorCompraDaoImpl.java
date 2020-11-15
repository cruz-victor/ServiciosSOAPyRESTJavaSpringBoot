package bo.gob.sin.sre.fac.cfec.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.ISreFacErrorCompraDao;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresCompras;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class SreFacErrorCompraDaoImpl extends AbstractGenericDao<SreFacRecepcionesErroresCompras> implements ISreFacErrorCompraDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
