package bo.gob.sin.sre.fac.cfec.controller;

import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/soporte")
public class SoporteController {

	@Autowired
	IUtilitarios iutil;

	@GetMapping(value = "/verificarConexion")
	public int verificarComunicacion() {
		return CodigosMensajesServiciosSOAPServiceImpl.COMUNICACION_EXITOSA;
	}

	@PostMapping(value = "/jsonEtapa1", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaJsonDto> obtenerJsonEtapa1(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		RespuestaJsonDto vResultado = new RespuestaJsonDto();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(pSolicitud));
			JSONObject jsonFinal = new JSONObject();
			jsonFinal.put("XmlFactura", "");
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);
			SolicitudJsonDto solicitudJson = new SolicitudJsonDto();
			solicitudJson.setSolicitud(jsonFinal.toString());
			vResultado.setRespuesta(solicitudJson.getSolicitud());
		} catch (Exception e) {
			vResultado.setRespuesta("");
		}
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}

	@PostMapping(value = "/jsonEtapa2", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaJsonDto> obtenerJsonEtapa2(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		RespuestaJsonDto vResultado = new RespuestaJsonDto();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String vXmlInd = iutil.decodificarArchivo(pSolicitud.getV_archivo());
			JSONObject jsonFactura = XML.toJSONObject(vXmlInd);
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(pSolicitud));
			JSONObject jsonFinal = new JSONObject();
			jsonFinal.put("XmlFactura", jsonFactura);
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);
			SolicitudJsonDto solicitudJson = new SolicitudJsonDto();
			solicitudJson.setSolicitud(jsonFinal.toString());
			vResultado.setRespuesta(solicitudJson.getSolicitud());
		} catch (Exception e) {
			vResultado.setRespuesta("");
		}
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}

	@PostMapping(value = "/archivoToXmlIndividual", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaJsonDto> archivoToXmlIndividual(
			@RequestBody SolicitudJsonDto pSolicitud) {
		RespuestaJsonDto vResultado = new RespuestaJsonDto();
		try {
			vResultado.setRespuesta(iutil.decodificarArchivo(pSolicitud.getSolicitud()));
		} catch (Exception e) {
			vResultado.setRespuesta("");
		}
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}

	@PostMapping(value = "/archivoToXmlPaquete", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaXmlXsdDto> archivoToXmlPaquete(
			@RequestBody SolicitudJsonDto pSolicitud) {
		RespuestaXmlXsdDto vResultado = new RespuestaXmlXsdDto();
		try {
			byte[] compressed = Base64.getDecoder().decode(pSolicitud.getSolicitud());
			List<String> listaFacturasB64 = iutil.decompressLoteFacturas(compressed);

			// valida cada una de las facturas del paquete
			for (String xmlB64 : listaFacturasB64) {
				vResultado.getListaDescripcionesRespuestas().add(iutil.decodificarToBase64(xmlB64));
			}

		} catch (Exception e) {
			vResultado = new RespuestaXmlXsdDto();
		}
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}
}