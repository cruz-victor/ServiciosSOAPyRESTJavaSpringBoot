package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.dao.IPruebasManualesDao;
import bo.gob.sin.sre.fac.dao.IRegistrosObservacionesInsituDao;
import bo.gob.sin.sre.fac.domain.IRegistrosObservacionesInsituDomain;
import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;

@Service
public class RegistrosObservacionesInsituDomainImpl implements IRegistrosObservacionesInsituDomain, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IRegistrosObservacionesInsituDao iRegistrosObservacionesInsituDao;
	
	
	@Override
	public List<SreFacRegistrosObservacionesInsitu> obtieneListaVerificacionInsitu(long pSolicitudCertificacionId) {
		
		List<SreFacRegistrosObservacionesInsitu> vRespueta = new ArrayList<>();
		
		vRespueta  =iRegistrosObservacionesInsituDao.obtieneListaVerificacionInsitu(pSolicitudCertificacionId);
		return vRespueta;
	}


	@Override
	public SreFacRegistrosObservacionesInsitu registroObservacionComponente(SreFacRegistrosObservacionesInsitu pRegObservacionComponente) {
		SreFacRegistrosObservacionesInsitu vResp;
		
		vResp=iRegistrosObservacionesInsituDao.registroObservacionComponente(pRegObservacionComponente);
		return vResp;
		
		
	}


}
