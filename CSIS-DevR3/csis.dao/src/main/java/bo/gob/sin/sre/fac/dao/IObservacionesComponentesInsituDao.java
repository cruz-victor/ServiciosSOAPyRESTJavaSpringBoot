package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreObservacionesComponentesInsitu;

public interface IObservacionesComponentesInsituDao extends GenericDao<SreObservacionesComponentesInsitu> {
	public List<SreObservacionesComponentesInsitu> obtenerObservacionesComponentesInsituPorCodigoCertificacion(
			long pCodigoCertificacion);
}
