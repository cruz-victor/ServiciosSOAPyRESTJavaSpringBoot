package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreRegistroVerificacionPruebaInsitu;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IObservacionesInsituDao extends GenericDao<SreFacObservacionesInsitu> 
{
	public List<SreFacObservacionesInsitu> recuperaListaObservacionesInSitu(long pSistemaId, long pSolicitudCertificacionId) ;
}
