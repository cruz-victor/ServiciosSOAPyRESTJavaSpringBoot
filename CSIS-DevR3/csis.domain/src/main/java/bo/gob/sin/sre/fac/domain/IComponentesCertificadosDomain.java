package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

public interface IComponentesCertificadosDomain
{
	public List<SreComponentesCertificados> obtenerComponentesCertificados(Long pComponenteArchivoId);
	
}
