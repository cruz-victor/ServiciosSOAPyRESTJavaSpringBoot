package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreEquiposCertificaciones;

public interface IEquiposCertificacionesDao extends GenericDao<SreEquiposCertificaciones> {

	public List<SreEquiposCertificaciones> obtenerEquipoCertificacion(SreEquiposCertificaciones pEquipoCertificacion);
}
