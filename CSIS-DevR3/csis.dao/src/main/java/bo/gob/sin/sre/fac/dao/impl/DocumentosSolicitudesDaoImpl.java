package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IDocumentosSolicitudesDao;
import bo.gob.sin.sre.fac.model.SreDocumentosSolicitudes;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
@Repository
@Transactional
public class DocumentosSolicitudesDaoImpl extends AbstractGenericDao<SreDocumentosSolicitudes> implements IDocumentosSolicitudesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
