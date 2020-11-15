package bo.gob.sin.sre.fac.dao.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import bo.gob.sin.sre.fac.dao.IRegistrosResultadosSolicitudesDao;
import bo.gob.sin.sre.fac.model.SreRegistrosResultadosSolicitudes;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;


//IASC
@Repository
@Transactional
public class RegistrosResultadosSolicitudesDaoImpl extends AbstractGenericDao<SreRegistrosResultadosSolicitudes> implements IRegistrosResultadosSolicitudesDao {

}
