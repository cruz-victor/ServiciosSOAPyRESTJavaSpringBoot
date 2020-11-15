package bo.gob.sin.sre.fact.cfec.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

@Service
@Transactional
public class ServiciosClientRest implements IServiciosClientRest {

	private static final Logger LOG = LoggerFactory.getLogger(ServiciosClientRest.class);

	@Value("${url_servicio_rest_test}")
	private String urlServicioRestTest;

	@Override
	public RespuestaJsonDto recepcionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud) {
		RespuestaJsonDto resultado = new RespuestaJsonDto();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String vUrlServicio = urlServicioRestTest + "/facturacion/recepcionFacturaPortalWeb";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<XmlRecepcionGenericoDto> vEntidadSolicitud = new HttpEntity<>(pSolicitud, headers);
			ResponseEntity<RespuestaJsonDto> vResponse = restTemplate.exchange(vUrlServicio, HttpMethod.POST,
					vEntidadSolicitud, new ParameterizedTypeReference<RespuestaJsonDto>() {
					});
			resultado = vResponse.getBody();
		} catch (Exception e) {
			resultado = new RespuestaJsonDto();
			LOG.error(e.getMessage());
		}

		return resultado;
	}

	@Override
	public RespuestaJsonDto validacionRecepcionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud) {
		RespuestaJsonDto resultado = new RespuestaJsonDto();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String vUrlServicio = urlServicioRestTest + "/facturacion/validacionRecepcionFacturaPortalWeb";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<XmlRecepcionGenericoDto> vEntidadSolicitud = new HttpEntity<>(pSolicitud, headers);
			ResponseEntity<RespuestaJsonDto> vResponse = restTemplate.exchange(vUrlServicio, HttpMethod.POST,
					vEntidadSolicitud, new ParameterizedTypeReference<RespuestaJsonDto>() {
					});
			resultado = vResponse.getBody();
		} catch (Exception e) {
			resultado = new RespuestaJsonDto();
			LOG.error(e.getMessage());
		}

		return resultado;
	}

	@Override
	public RespuestaJsonDto anulacionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud) {
		RespuestaJsonDto resultado = new RespuestaJsonDto();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String vUrlServicio = urlServicioRestTest + "/facturacion/anulacionFacturaPortalWeb";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<XmlRecepcionGenericoDto> vEntidadSolicitud = new HttpEntity<>(pSolicitud, headers);
			ResponseEntity<RespuestaJsonDto> vResponse = restTemplate.exchange(vUrlServicio, HttpMethod.POST,
					vEntidadSolicitud, new ParameterizedTypeReference<RespuestaJsonDto>() {
					});
			resultado = vResponse.getBody();
		} catch (Exception e) {
			resultado = new RespuestaJsonDto();
			LOG.error(e.getMessage());
		}

		return resultado;
	}

	@Override
	public RespuestaJsonDto validacionAnulacionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud) {
		RespuestaJsonDto resultado = new RespuestaJsonDto();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String vUrlServicio = urlServicioRestTest + "/facturacion/validacionAnulacionFacturaPortalWeb";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<XmlRecepcionGenericoDto> vEntidadSolicitud = new HttpEntity<>(pSolicitud, headers);
			ResponseEntity<RespuestaJsonDto> vResponse = restTemplate.exchange(vUrlServicio, HttpMethod.POST,
					vEntidadSolicitud, new ParameterizedTypeReference<RespuestaJsonDto>() {
					});
			resultado = vResponse.getBody();
		} catch (Exception e) {
			resultado = new RespuestaJsonDto();
			LOG.error(e.getMessage());
		}

		return resultado;
	}

}