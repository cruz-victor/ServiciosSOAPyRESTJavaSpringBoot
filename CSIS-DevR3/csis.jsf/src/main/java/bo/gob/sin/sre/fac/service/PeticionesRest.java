package bo.gob.sin.sre.fac.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sen.enco.model.ContextoJSF;
import bo.gob.sin.sen.enmo.jsf.model.HttpClient;
import bo.gob.sin.str.caut.dto.UsuarioAutenticadoDto;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PeticionesRest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public transient OkHttpClient client;
	public String host;

	public List<StrMensajeAplicacionDto> mensajes;

	public Response ejecutarPostConParametro(String hostDireccion, JSONObject json) {
		Response response;

		try {
			RequestBody body = RequestBody.create(JSON, json.toString());
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).post(body).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
	
	public Response ejecutarPostConParametroToken(String hostDireccion, JSONObject json) {
		Response response;
		ContextoJSF contexto = new ContextoJSF();
		String vToken = "Token " + contexto.getUsuario().getToken();

		try {
			RequestBody body = RequestBody.create(JSON, json.toString());
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).post(body).addHeader("Authorization", vToken).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}

	public Response ejecutarPostConParametroMapper(String hostDireccion, String json) {
		Response response;

		try {
			RequestBody body = RequestBody.create(JSON, json);
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).post(body).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}

	public Response ejecutarPostConParametroMapperToken(String hostDireccion, String json) {
		Response response;
		ContextoJSF contexto = new ContextoJSF();
		String vToken = "Token " + contexto.getUsuario().getToken();
		//String vToken = this.obtenerToken();
		try {
			RequestBody body = RequestBody.create(JSON, json);
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).post(body).addHeader("Authorization", vToken).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
	
	public Response ejecutarPostSinParametro(String hostDireccion) {
		Response response;
		try {
			RequestBody body = RequestBody.create(JSON, "");
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).post(body).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
	

	public Response ejecutarPostSinParametroToken(String hostDireccion) {
		Response response;
		ContextoJSF contexto = new ContextoJSF();
		String vToken = "Token " + contexto.getUsuario().getToken();
		//String vToken = this.obtenerToken();
		try {
			RequestBody body = RequestBody.create(JSON, "");
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().post(body).url(route).addHeader("Authorization", vToken).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}

	public Response ejecutarGetConParametros(String hostDireccion, Map<String, String> params) {
		Response response;

		try {
			HttpUrl.Builder route = HttpUrl.parse(hostDireccion).newBuilder();

			for (Map.Entry<String, String> param : params.entrySet()) {
				route.addPathSegment(param.getValue());
			}

			Request request = new Request.Builder().url(route.build()).get().build();

			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}

	public Response ejecutarGetConParametrosToken(String hostDireccion, Map<String, String> params) {
		Response response;
		ContextoJSF contexto = new ContextoJSF();
		String vToken = "Token " + contexto.getUsuario().getToken();
		try {
			HttpUrl.Builder route = HttpUrl.parse(hostDireccion).newBuilder();

			for (Map.Entry<String, String> param : params.entrySet()) {
				route.addPathSegment(param.getValue());
			}

			Request request = new Request.Builder().url(route.build()).addHeader("Authorization", vToken).get().build();

			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}

	public Response ejecutarGetSinParametrosToken(String hostDireccion) {
		Response response;
		ContextoJSF contexto = new ContextoJSF();
		String vToken = "Token " + contexto.getUsuario().getToken();
		try {
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).get().addHeader("Authorization", vToken).get().build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
	
	public Response ejecutarGetSinParametros(String hostDireccion) {
		Response response;

		try {
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).get().build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}

	public void configParametricas() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("str_cps_clas_query_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}

	public void configFacturacionCertificacionRest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("sre_fac_csis_csis_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
		
	public void configFacturacionGiecRest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("sre_fac_giec_giec_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}

	public void configFacturacionPadronRest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("scn_emp_caco_query_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
	
	public void configFacturacion_Puerto39288_Rest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("scn_emp_caco_val_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
	
	public void configFacturacionPadronObtenerSucursalesActivasXNITRest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("scn_emp_casu_query_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
	
	public void configFacturacionPadronExisteFormIvaRest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("scn_emp_caot_val_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
	
    public void ConfiguracionServiciosTokenRest() {
        RestUrl vConf = new RestUrl();
        String vUrlServidor = vConf.getPropetyValue("str_cau_caut_rest");
        client = HttpClient.globalClient();
        this.host = vUrlServidor;
    }
        
    public void configFacturacionCsisRestQuery() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("sre_fac_csis_csis_query_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
    }
    
    public void configFacturacionCsisRestQueryPreProduccion() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("sre_fac_csis_csis_query_rest_pre");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
    }
	
    
	/** 
	 * Objetivo: Acceso a ruta Csis para el ambiente de Pre Producción.
	 * Creado por: junior.flores
	 * Fecha: 02/07/2019 
	 */
    public void configFacturacionCsisRestPruebasPreProduccion() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("sre_fac_csis_csis_rest_pre");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
    }
    
    public void configFacturacionWorkflowcderRest() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("sap_cder_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
	
	public Response ejecutarPostConParametroMapperSinToken(String hostDireccion, String json) {
		Response response;
		try {
			RequestBody body = RequestBody.create(JSON, json);
			HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
			Request request = new Request.Builder().url(route).post(body).build();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
	
	/**
     * Objetivo: Obtener Datos Contribuyente por Nit. Creado por: Wilson Limachi.
     * Fecha: 04/05/2018
     * Modificado por: Wilson Limachi
     * Fecha: 30/08/2019
     */
    public String obtenerToken() {
            
            String vResultado="";
            UsuarioAutenticadoDto resultado = new UsuarioAutenticadoDto();
            
            try {
                    ConfiguracionServiciosTokenRest();

                    ObjectMapper mapper = new ObjectMapper();
                    String ruta = host + "/token/getGenerico/";
                    
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("mil", 1000 + "");
                    
                    Response response = ejecutarGetConParametros(ruta, map);

                    if (response.code() == 404) 
                    {
                            resultado = new UsuarioAutenticadoDto();
                            vResultado = "";
                    } 
                    else 
                    {
                            InputStream istream = response.body().byteStream();
                            InputStreamReader isr = null;
                            BufferedReader br = null;
                            StringBuilder sb = new StringBuilder();
                            String content;

                            isr = new InputStreamReader(istream);
                            br = new BufferedReader(isr);
                            while ((content = br.readLine()) != null) 
                            {
                                    sb.append(content);
                            }
                            
                            resultado = mapper.readValue(sb.toString(), UsuarioAutenticadoDto.class);
                            vResultado = "Token "+ resultado.getToken();
                    }

            } 
            catch (Exception e) 
            {
                    resultado = new UsuarioAutenticadoDto();
                    vResultado = "";
            }               

            return vResultado;
    }
    
	/** 
	 * Objetivo: Acceso a los servicios de Transversales.
	 * Creado por: junior.flores
	 * Fecha: 22/11/2019 
	 */
	public void configTransversales() {
		RestUrl vConf = new RestUrl();
		String vUrlClasificador = vConf.getPropetyValue("str_usu_rest");
		client = HttpClient.globalClient();
		host = vUrlClasificador;
	}
}
