package bo.gob.sin.sre.fac.domain.impl;



import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISreFacSegmentacionesDao;
import bo.gob.sin.sre.fac.domain.IConsultaFacturacionDomain;
import bo.gob.sin.sre.fac.model.SreFacSegmentaciones;

@Service
@Transactional
public class ConsultaFacturacionDomainImpl implements IConsultaFacturacionDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaFacturacionDomainImpl.class);
	
	@Autowired
	ISreFacSegmentacionesDao iSreFacSegmentacionesDao;

	@Override
	public SreFacSegmentaciones obtenerPorNit(long nit) {
		
		SreFacSegmentaciones resultado=new SreFacSegmentaciones();
		try {
		resultado=iSreFacSegmentacionesDao.obtenerPorNit(nit);
		
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(resultado));
		}
		
		return resultado;
	}
	
}
