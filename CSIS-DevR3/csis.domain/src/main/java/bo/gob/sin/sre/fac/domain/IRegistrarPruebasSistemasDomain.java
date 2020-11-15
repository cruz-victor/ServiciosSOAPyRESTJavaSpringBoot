package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.dto.PruebasDto;
import bo.gob.sin.sre.fac.model.SreSistemas;


public interface IRegistrarPruebasSistemasDomain {

	//IASC
	public boolean registrarPruebasSistemas(SreSistemas pSistemaACertificar, Long pSolicitudCertificacionId,  List<PruebasDto> pResultadoListaPruebas);
	
}
