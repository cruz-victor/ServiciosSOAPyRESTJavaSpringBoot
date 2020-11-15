package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IClasificadoresDao;
import bo.gob.sin.sre.fac.domain.IParametricasFacturacionDomain;
import bo.gob.sin.sre.fac.model.SreClasificadores;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional
public class ParametricasFacturacionDomainImpl implements IParametricasFacturacionDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	IClasificadoresDao iClasificadoresDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(ParametricasFacturacionDomainImpl.class);
	
	//IASC
	@Override
	public SreClasificadores obtenerEntidadPorCodigoEquivalente(Integer pCodigoEquivalente, String pAgrupador) {
		SreClasificadores vEntidad = new SreClasificadores();
		try {
			vEntidad = iClasificadoresDao.obtenerEntidadPorCodigoEquivalente(pCodigoEquivalente, pAgrupador);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pCodigoEquivalente));
			vEntidad = null;
		}
		return vEntidad;
	}
}
