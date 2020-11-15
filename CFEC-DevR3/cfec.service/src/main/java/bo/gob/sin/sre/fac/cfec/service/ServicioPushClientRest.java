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
import org.json.JSONObject;
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

import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cmsj.mfcm.dto.FirebaseResponseDto;
import bo.gob.sin.str.cmsj.mfcm.dto.SolicitudPersonaIdDocumentoDto;

@Service
@Transactional
public class ServicioPushClientRest implements IServicioPushClientRest {

	private static final Logger LOG = LoggerFactory.getLogger(ServicioPushClientRest.class);

	@Value("${url_servicio_push}")
	private String urlServicioPush;
	@Value("${server.ssl.key-store}")
	private String serverSslKeyStore;
	@Value("${server.ssl.key-store-password}")
	private String serverSslKeyStorePassword;
	@Value("${url_servicio_generico_token}")
	private String servicioGenericoToken;

	@Override
	public FirebaseResponseDto notificacionPush(SolicitudPersonaIdDocumentoDto pSolicitud) {
		FirebaseResponseDto resultado = new FirebaseResponseDto();
		FileInputStream vStoreJks = null;
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			vStoreJks = new FileInputStream(new File(serverSslKeyStore));
			keyStore.load(vStoreJks, serverSslKeyStorePassword.toCharArray());
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).loadKeyMaterial(keyStore, serverSslKeyStorePassword.toCharArray()).build());
			HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
			ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			String vUrlServicio = urlServicioPush + "/rest/fb/notificar/push/";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Token " + obtenerToken());
			HttpEntity<SolicitudPersonaIdDocumentoDto> vEntidadSolicitud = new HttpEntity<>(pSolicitud,headers);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			ResponseEntity<FirebaseResponseDto> vResponse = restTemplate.exchange(vUrlServicio,
					HttpMethod.POST, vEntidadSolicitud,
					new ParameterizedTypeReference<FirebaseResponseDto>() {
					});
			resultado = vResponse.getBody();
		} catch (Exception e) {
			resultado = new FirebaseResponseDto();
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

	private String obtenerToken() {
        //String uri = "https://desasiatrest.impuestos.gob.bo:39102/token/getGenerico/1000";
        String uri = servicioGenericoToken + "/token/getGenerico/1000";
        
        RestTemplate restTemplate2 = new RestTemplate();
        String aux = restTemplate2.getForObject(uri, String.class);
        JSONObject objeto = new JSONObject(aux);
        return objeto.getString("token");
	} 
}