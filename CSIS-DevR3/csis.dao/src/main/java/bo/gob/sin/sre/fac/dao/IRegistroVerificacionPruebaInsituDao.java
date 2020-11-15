package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreRegistroVerificacionPruebaInsitu;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IRegistroVerificacionPruebaInsituDao extends GenericDao<SreRegistroVerificacionPruebaInsitu> 
{
	public List<SreRegistroVerificacionPruebaInsitu> recuperarListaRegistroObservacionesInSitu(long pSolicitudCertificacionId);
}
