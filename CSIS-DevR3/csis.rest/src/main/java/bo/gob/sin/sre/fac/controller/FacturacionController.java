package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudConsultaFacturacionDto;
import bo.gob.sin.sre.fac.service.IConsultaFacturacionServiceDomain;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/facturacion")
public class FacturacionController  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	IConsultaFacturacionServiceDomain iConsultaFacturacionServiceDomain;
	
	@PostMapping("/consultaFacturacion")
	public ResponseEntity<RespuestaConsultaFacturacionDto> consultaFacturacion(@RequestBody SolicitudConsultaFacturacionDto solicitud) 
	{
		RespuestaConsultaFacturacionDto vRespuesta = new RespuestaConsultaFacturacionDto();
		vRespuesta = iConsultaFacturacionServiceDomain.consultaFacturacion(solicitud);
		ResponseEntity<RespuestaConsultaFacturacionDto> vResponseEntity = new ResponseEntity<RespuestaConsultaFacturacionDto>(
				vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
}
