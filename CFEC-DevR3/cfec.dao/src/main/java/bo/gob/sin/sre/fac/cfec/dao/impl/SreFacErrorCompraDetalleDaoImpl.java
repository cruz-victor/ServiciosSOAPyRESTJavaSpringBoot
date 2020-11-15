package bo.gob.sin.sre.fac.cfec.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.ISreFacErrorCompraDetalleDao;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresComprasDetalles;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class SreFacErrorCompraDetalleDaoImpl extends AbstractGenericDao<SreFacRecepcionesErroresComprasDetalles> implements ISreFacErrorCompraDetalleDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
