package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IContribuyentesModalidadesDao;
import bo.gob.sin.sre.fac.domain.IContribuyenteModalidadConsultaDomain;
import bo.gob.sin.sre.fac.model.SreContribuyentesModalidades;

@Service
@Transactional
public class ContribuyenteModalidadConsultaDomainImpl implements IContribuyenteModalidadConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ContribuyenteModalidadConsultaDomainImpl.class);

	@Autowired
	IContribuyentesModalidadesDao iContribuyentesModalidadesDao;
	
	//IASC - Obtiene la modalidad del contribuyente definida en Normativa - 23/11/2018 
	@Override
	public long obtieneModalidadContribuyenteNormativa(Long pNit, Integer pModalidadId) {
		LOG.info("Ingresando obtieneModalidadContribuyenteNormativa ");
		long vRespuesta = iContribuyentesModalidadesDao.obtenerModalidadContribuyenteNormativa(pNit, pModalidadId);	
		return vRespuesta;
	}
	
	@Override
	public SreContribuyentesModalidades obtieneModalidadContribuyente(Long pNit) {
		LOG.info("Ingresando obtieneModalidadContribuyenteNormativa ");
		SreContribuyentesModalidades vRespuesta = iContribuyentesModalidadesDao.obtenerModalidadContribuyente(pNit);	
		return vRespuesta;
	}
	
}
