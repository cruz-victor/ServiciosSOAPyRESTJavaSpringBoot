package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;
import bo.gob.sin.sre.fac.model.SreSistemas;

public interface IRegistroPruebasAutomaticasABMDomain {

	//IASC
	public boolean registrarPruebasAutomaticas(SreSistemas pSistema, long pSolicitudCertificacionId, List<SrePruebasAutomaticas> pPruebas);
}