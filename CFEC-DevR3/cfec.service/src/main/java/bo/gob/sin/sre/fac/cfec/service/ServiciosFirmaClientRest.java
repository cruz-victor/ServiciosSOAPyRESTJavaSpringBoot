package bo.gob.sin.sre.fac.cfec.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionFirmadoXmlFacturaDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudValidacionFirmadoXmlFacturaDto;
import bo.gob.sin.str.cexc.LogExcepcion;

@Service
@Transactional
public class ServiciosFirmaClientRest implements IServiciosFirmaClientRest {

	private static final Logger LOG = LoggerFactory.getLogger(ServiciosFirmaClientRest.class);

	@Value("${url_servicio_verificador_firmado}")
	private String urlServicioVerificadorFirmado;
	@Value("${server.ssl.key-store}")
	private String serverSslKeyStore;
	@Value("${server.ssl.key-store-password}")
	private String serverSslKeyStorePassword;

	@Override
	public RespuestaValidacionFirmadoXmlFacturaDto verificarFirmadoXml(SolicitudValidacionFirmadoXmlFacturaDto pSolicitud) {
		RespuestaValidacionFirmadoXmlFacturaDto resultado = new RespuestaValidacionFirmadoXmlFacturaDto();
		FileInputStream vStoreJks = null;
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			vStoreJks = new FileInputStream(new File(serverSslKeyStore));
			keyStore.load(vStoreJks, serverSslKeyStorePassword.toCharArray());
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
					new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy())
							.loadKeyMaterial(keyStore, serverSslKeyStorePassword.toCharArray()).build());
			HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
			ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

			String vUrlServicio = urlServicioVerificadorFirmado + "/arServiciosInternos/verificarFirmadoXml/";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<SolicitudValidacionFirmadoXmlFacturaDto> vEntidadSolicitud = new HttpEntity<>(pSolicitud,
					headers);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			ResponseEntity<RespuestaValidacionFirmadoXmlFacturaDto> vResponse = restTemplate.exchange(vUrlServicio,
					HttpMethod.POST, vEntidadSolicitud,
					new ParameterizedTypeReference<RespuestaValidacionFirmadoXmlFacturaDto>() {
					});
			resultado = vResponse.getBody();
		} catch (Exception e) {
			resultado = new RespuestaValidacionFirmadoXmlFacturaDto();
			LOG.error(e.getMessage());
			LogExcepcion.registrar(e, LOG);
		}
		finally {
			try {
				if (vStoreJks != null) {
					vStoreJks.close();
				}
			} catch (IOException e) {
			}
		}

		return resultado;
	}

}