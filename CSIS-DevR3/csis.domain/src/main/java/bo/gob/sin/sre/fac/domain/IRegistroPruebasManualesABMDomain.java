package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SrePruebasManuales;
import bo.gob.sin.sre.fac.model.SreSistemas;

public interface IRegistroPruebasManualesABMDomain {

	//IASC
	public boolean registrarPruebasManuales(SreSistemas pSistema, long pSolicitudCertificacionId, List<SrePruebasManuales> pPruebas);
}