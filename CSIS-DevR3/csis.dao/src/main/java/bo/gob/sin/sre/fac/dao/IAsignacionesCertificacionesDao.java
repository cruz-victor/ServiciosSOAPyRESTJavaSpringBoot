package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;

public interface IAsignacionesCertificacionesDao extends GenericDao<SreAsignacionesCertificaciones> {
	public List<SreAsignacionesCertificaciones> obtenerAsignacionesEquipoCertificacionPorTramite(long pTramiteId);
}
