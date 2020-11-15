package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IPruebasManualesDao;
import bo.gob.sin.sre.fac.domain.IPruebaManualConsultaDomain;
import bo.gob.sin.sre.fac.model.SrePruebasManuales;

@Service
@Transactional
public class PruebaManualConsultaDomainImpl implements IPruebaManualConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PruebaManualConsultaDomainImpl.class);

	@Autowired
	IPruebasManualesDao iPruebasManualesDao;
	
	//IASC - Obtiene las pruebas manuales - 24/11/2018 
	@Override
	public List<SrePruebasManuales> obtenerPruebasManuales() {
		List<SrePruebasManuales> respuestaRegistro = new ArrayList<>();
		LOG.info("ingresando obtenerPruebasManuales");
		try {
			respuestaRegistro = iPruebasManualesDao.obtenerPruebasManuales();
		} catch (Exception e) {
			LOG.info("No se pudo recuperar la lista de pruebas manuales");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtenerPruebasManuales", respuestaRegistro);
		return respuestaRegistro;
	}

	
	
}
