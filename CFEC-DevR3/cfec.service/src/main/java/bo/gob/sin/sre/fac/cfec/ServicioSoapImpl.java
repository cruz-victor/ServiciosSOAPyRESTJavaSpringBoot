package bo.gob.sin.sre.fac.cfec;

import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;

public class ServicioSoapImpl extends AServiciosSoap {

	@Autowired
	IValidarRecepcion iValidarRecepcion;

	@Autowired
	IUtilitarios iutil;

	@Autowired
	AServiciosSoap aServiciosSoap;

	ServicioSoapImpl(IValidarRecepcion pValidarRecepcion, IUtilitarios pUtil) {
		iValidarRecepcion = pValidarRecepcion;
		iutil = pUtil;
	}

}