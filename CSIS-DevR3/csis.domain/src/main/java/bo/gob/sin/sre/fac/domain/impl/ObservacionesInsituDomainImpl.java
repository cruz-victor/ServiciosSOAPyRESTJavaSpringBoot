package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.dao.IObservacionesInsituDao;
import bo.gob.sin.sre.fac.dao.IPruebasManualesDao;
import bo.gob.sin.sre.fac.dao.IRegistrosObservacionesInsituDao;
import bo.gob.sin.sre.fac.domain.IObservacionesInsituDomain;
import bo.gob.sin.sre.fac.domain.IRegistrosObservacionesInsituDomain;
import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;

@Service
public class ObservacionesInsituDomainImpl implements IObservacionesInsituDomain, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IObservacionesInsituDao iObservacionesInsituDao;
	
	@Override
	public List<SreFacObservacionesInsitu> recuperaListaObservacionesInSitu(long pSistemaId, long pSolicitudCertificacionId) 
	{
		List<SreFacObservacionesInsitu> vResultado = new ArrayList<>();
		
		vResultado = iObservacionesInsituDao.recuperaListaObservacionesInSitu(pSistemaId, pSolicitudCertificacionId);
		
		return vResultado;
	}
}
