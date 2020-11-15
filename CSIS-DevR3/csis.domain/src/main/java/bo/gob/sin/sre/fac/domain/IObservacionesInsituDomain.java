package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;

public interface IObservacionesInsituDomain 
{
	public List<SreFacObservacionesInsitu> recuperaListaObservacionesInSitu(long pSistemaId, long pSolicitudCertificacionId);
}
