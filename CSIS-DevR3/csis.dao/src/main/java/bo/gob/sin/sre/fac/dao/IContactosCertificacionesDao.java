package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreContactosCertificaciones;

//IASC
public interface IContactosCertificacionesDao extends GenericDao<SreContactosCertificaciones>{
	
	//IASC
	public List<SreContactosCertificaciones> obtieneListaContactosSolicitud(Long pSolicitudCertificacionId);
}
