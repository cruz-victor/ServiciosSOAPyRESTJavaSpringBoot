package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.external.ServiciosParametricaRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@Service
@Transactional
public class ParametricasDomainImpl implements IParametricasDomain, Serializable 
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ParametricasDomainImpl.class);
	
	@Override
	public ClasificadorDto obtenerDatosParametrica(Integer pClasificadorId) {
		
		ServiciosParametricaRest vServiciosParametricaRest = new ServiciosParametricaRest();
		ClasificadorDto vClasificadorDto = new ClasificadorDto();
		
		vClasificadorDto = vServiciosParametricaRest.recuperarClasificadorPorId(pClasificadorId);
	
		
		return vClasificadorDto;
	}
	
	
	@Override
	public List<ClasificadorDto> recuperarClasificadorPorTipo(String pTipo)  {
		
		ServiciosParametricaRest vServiciosParametricaRest = new ServiciosParametricaRest();
		List<ClasificadorDto> vClasificadorDto = new ArrayList<>();
		
		vClasificadorDto = vServiciosParametricaRest.recuperarClasificadorPorTipo(pTipo);
	
		
		return vClasificadorDto;
	}
	
}
