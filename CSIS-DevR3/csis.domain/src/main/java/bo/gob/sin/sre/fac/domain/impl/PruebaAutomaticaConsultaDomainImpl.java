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
import bo.gob.sin.sre.fac.dao.IPruebasAutomaticasDao;
import bo.gob.sin.sre.fac.domain.IPruebaAutomaticaConsultaDomain;
import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;

@Service
@Transactional(rollbackFor = Exception.class)
public class PruebaAutomaticaConsultaDomainImpl implements IPruebaAutomaticaConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PruebaAutomaticaConsultaDomainImpl.class);

	@Autowired
	IPruebasAutomaticasDao iPruebasAutomaticasDao;
	
	//IASC - Obtiene las pruebas automaticas por tipo de documento fiscal - 24/11/2018 
	@Override
	public List<SrePruebasAutomaticas> obtenerPruebasAutomaticasPorDocFiscal(Integer pTipoDocumentoFiscalId, Integer pModalidadId) {
		List<SrePruebasAutomaticas> respuestaRegistro = new ArrayList<>();
		LOG.info("ingresando obtenerPruebasAutomaticasPorDocFiscal");
		try {
			respuestaRegistro = iPruebasAutomaticasDao.obtenerPruebasAutomaticasPorDocFiscal(pTipoDocumentoFiscalId, pModalidadId);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar la lista de pruebas automaticas");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtenerPruebasAutomaticasPorDocFiscal", respuestaRegistro);
		return respuestaRegistro;
	}
	
}
