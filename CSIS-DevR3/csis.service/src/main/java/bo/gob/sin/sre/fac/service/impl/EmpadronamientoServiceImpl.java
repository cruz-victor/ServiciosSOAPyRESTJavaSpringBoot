package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;
import bo.gob.sin.sre.fac.service.IEmpadronamientoService;
import bo.gob.sin.sre.fac.service.external.ServiciosPadronRestClient;

@Service
@Transactional
public class EmpadronamientoServiceImpl implements IEmpadronamientoService , Serializable
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(EmpadronamientoServiceImpl.class);
	
	@Override
	public ContribuyenteDto obtenerDatosBasicosXIFC(Long pIFC) {
		
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		ContribuyenteDto vNitDatosBasicosDto = new ContribuyenteDto();
		
		vNitDatosBasicosDto = vServiciosPadronRestClient.ObtenerDatosBasicosXIFC(pIFC);
	
		return vNitDatosBasicosDto;
	}
	
	@Override
	public ContribuyenteDto obtenerDatosBasicosXNIT(Long pNIT)
	{
		
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		ContribuyenteDto vNitDatosBasicosDto = new ContribuyenteDto();
		
		vNitDatosBasicosDto = vServiciosPadronRestClient.ObtenerDatosBasicosXNIT(pNIT);
	
		return vNitDatosBasicosDto;
	}
}
