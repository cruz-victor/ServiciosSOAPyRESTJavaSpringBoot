package bo.gob.sin.sre.fac.csis.query.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.fac.csis.query.IEtapa2Query;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.ListaSistemasSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaCalcularPorcentajePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetallePruebasEtapas2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaMatchLogCasosPruebaEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebaSugeridaDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaResumenPruebaCertificacionSistemaDto;

@CrossOrigin(maxAge = 3600)
@RestController

@RequestMapping("/SeguimientoCertificacionPruebas")
public class Etapa2Controller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(Etapa2Controller.class);
		

	@Autowired	
	IEtapa2Query iEtapa2Query;
	
	@PostMapping(value="/etapa2/{pSistemaId}/{pDocumentoSectorId}")
	public ResponseEntity<RespuestaMatchLogCasosPruebaEtapa2Dto> matchLogCasosPruebaEtapa2(@PathVariable long pSistemaId, @PathVariable int pDocumentoSectorId)
	{
		RespuestaMatchLogCasosPruebaEtapa2Dto vRespuestaDto=new RespuestaMatchLogCasosPruebaEtapa2Dto();		
		vRespuestaDto=iEtapa2Query.matchLogCasosPruebaEtapa2(pSistemaId, pDocumentoSectorId);								
		return (vRespuestaDto.getEstado()==0? new  ResponseEntity<RespuestaMatchLogCasosPruebaEtapa2Dto>(vRespuestaDto, HttpStatus.NOT_FOUND): new  ResponseEntity<RespuestaMatchLogCasosPruebaEtapa2Dto>(vRespuestaDto, HttpStatus.OK));			
	}
	
	@GetMapping(value="/etapa2/{pSistemaId}/{pDocumentoSectorId}")
	public ResponseEntity<RespuestaCalcularPorcentajePruebasEtapa2Dto> calcularPorcentajePruebasEtapa2(@PathVariable long pSistemaId, @PathVariable int pDocumentoSectorId)
	{
		RespuestaCalcularPorcentajePruebasEtapa2Dto vRespuestaDto=new RespuestaCalcularPorcentajePruebasEtapa2Dto();		
		vRespuestaDto=iEtapa2Query.calcularPorcentajePruebasEtapa2(pSistemaId, pDocumentoSectorId);	
		return (vRespuestaDto.getPorcentaje()==0.0f? new  ResponseEntity<RespuestaCalcularPorcentajePruebasEtapa2Dto>(vRespuestaDto, HttpStatus.NOT_FOUND):new  ResponseEntity<RespuestaCalcularPorcentajePruebasEtapa2Dto>(vRespuestaDto, HttpStatus.OK));
	}

	@GetMapping(value="/etapa2/{pSistemaId}")
	public ResponseEntity<RespuestaListaDetallePruebasEtapas2Dto>  obtenerDetallePruebasEtapa2(@PathVariable long pSistemaId)
	{
		RespuestaListaDetallePruebasEtapas2Dto vRespuestaDto=new RespuestaListaDetallePruebasEtapas2Dto();			
		vRespuestaDto=iEtapa2Query.obtenerDetallePruebasEtapa2(pSistemaId);				
		
		return (vRespuestaDto.getListaDetallePruebas().size()>0?new ResponseEntity<RespuestaListaDetallePruebasEtapas2Dto>(vRespuestaDto, HttpStatus.OK):new ResponseEntity<RespuestaListaDetallePruebasEtapas2Dto>(HttpStatus.NO_CONTENT));
	}		
	
	@PostMapping(value="/etapa2/casopruebasugerida/")
	public ResponseEntity<RespuestaPruebaSugeridaDto>  registrarPruebaSugeridaEtapa2(@RequestBody DetallePruebasEtapa2Dto pDetallePruebasEtapa2Dto)
	{
		RespuestaPruebaSugeridaDto vRespuestaDto=new RespuestaPruebaSugeridaDto();		
		vRespuestaDto=iEtapa2Query.registrarPruebaSugeridaEtapa2(pDetallePruebasEtapa2Dto.getSistemaid(), pDetallePruebasEtapa2Dto.getExlnro(), pDetallePruebasEtapa2Dto.getExltipoprueba(), pDetallePruebasEtapa2Dto.getExltipoemision(), pDetallePruebasEtapa2Dto.getExlparametrodeentrada(), pDetallePruebasEtapa2Dto.getDocumentosectoridprueba(), pDetallePruebasEtapa2Dto.isChecado()?"CHECADO":"NO_CHECADO"); 	
		return (vRespuestaDto.getResultado()==0?new ResponseEntity<RespuestaPruebaSugeridaDto>(vRespuestaDto, HttpStatus.NOT_FOUND):new ResponseEntity<RespuestaPruebaSugeridaDto>(vRespuestaDto, HttpStatus.OK));
	}	
		
	@RequestMapping(value = "/verificacion/obtenerListaVerificacionInsituPorSolicitudCertificacionId/{pSolicitudCertificacionId}/{pTipoPrueba}", method = RequestMethod.GET)
	public ResponseEntity<RespuestaRegistroVerificacionInsituDto> obtenerListaVerificacionInsituPorSolicitudCertificacionId(
			@PathVariable long pSolicitudCertificacionId,
			@PathVariable long pTipoPrueba) {
		RespuestaRegistroVerificacionInsituDto vRespuesta = iEtapa2Query.obtenerListaVerificacionInsituPorSolicitudCertificacionId(pSolicitudCertificacionId, pTipoPrueba);
		return new ResponseEntity<>(vRespuesta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/verificacion/obtenerResumenPruebaCertificacionSistema/{pSistemaId}", method = RequestMethod.GET)
	public ResponseEntity<RespuestaResumenPruebaCertificacionSistemaDto> obtenerResumenPruebaCertificacionSistema(@PathVariable long pSistemaId) {
		RespuestaResumenPruebaCertificacionSistemaDto vRespuesta = iEtapa2Query.obtenerResumenPruebaCertificacionSistema(pSistemaId);
		return new ResponseEntity<>(vRespuesta, HttpStatus.OK);
	}
	
	@PostMapping(value="/obtenerListadoSistemasFase2")
	public ResponseEntity<ListaSistemasSolicitudCertificacionDto> obtenerListadoSistemasFase2(@RequestBody ListaSistemasSolicitudCertificacionDto pListaSistemasSolicitudCertificacionDto)
	{
		ListaSistemasSolicitudCertificacionDto vRespuesta=new ListaSistemasSolicitudCertificacionDto();		
		vRespuesta=iEtapa2Query.obtenerListadoSistemasFase2(pListaSistemasSolicitudCertificacionDto.getDatosEntradaContribuyenteId());								
		return new  ResponseEntity<ListaSistemasSolicitudCertificacionDto>(vRespuesta, HttpStatus.OK);			
	}
}
