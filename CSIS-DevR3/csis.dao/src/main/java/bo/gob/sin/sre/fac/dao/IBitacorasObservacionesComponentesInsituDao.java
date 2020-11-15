package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreBitacorasObservacionesComponentesInsitu;

public interface IBitacorasObservacionesComponentesInsituDao
		extends GenericDao<SreBitacorasObservacionesComponentesInsitu> {
	public List<SreBitacorasObservacionesComponentesInsitu> obtenerDetalleObservacionesComponentesInsituPorCodigoObservacion(
			long pCodigoObservacionComponenteInSitu);
}
