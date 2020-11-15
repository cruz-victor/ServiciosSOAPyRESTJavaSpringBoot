package bo.gob.sin.sre.fac.service.external;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import bo.gob.sin.sap.cipr.dto.RespuestaServicioDto;
import bo.gob.sin.sap.cipr.dto.SolicitudDerivacionDto;
import bo.gob.sin.sen.enmo.jsf.service.RestClient;
import bo.gob.sin.str.caut.dto.UsuarioAutenticadoDto;
import bo.gob.sin.str.caut.security.jwt.model.JwtUserDetails;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServiciosWorkFlowRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ServiciosWorkFlowRest.class);
	
	private static String host;
	private static String token;
	private static String padron;
	private static OkHttpClient client;
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static Gson gson = new Gson();

	public static void configWorkFlow() {
		RestUrl vConf = new RestUrl();
		String vUrlServidor = vConf.getPropetyValue("sap_cipr_rest");
		host = vUrlServidor;
	}

	public ServiciosWorkFlowRest() {
		super();
		RestClient cl = new RestClient();
		this.client = cl.obtenerCliente();
		this.host = host;
	}
	
    public void ConfiguracionServiciosTokenRest() {
        RestUrl vConf = new RestUrl();
        String vUrlServidor = vConf.getPropetyValue("str_cau_caut_rest");
        RestClient cl = new RestClient();
        this.client = cl.obtenerCliente();
        this.host = vUrlServidor;
}

	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}

	public static String getPadron() {
		return padron;
	}

	public long iniciarProcesoAutomaTico(SolicitudDerivacionDto pSolicitudDerivacion) {
		RespuestaServicioDto resultado = new RespuestaServicioDto();
		try {
			String json = "";
			configWorkFlow();

			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitudDerivacion);
			String ruta = host + "/rest/workflow/iniciar";
			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == 404) {
				resultado = new RespuestaServicioDto();
			} else {
				InputStream istream = response.body().byteStream();
				InputStreamReader isr = null;
				BufferedReader br = null;
				StringBuilder sb = new StringBuilder();
				String content;

				isr = new InputStreamReader(istream);
				br = new BufferedReader(isr);
				while ((content = br.readLine()) != null) {
					sb.append(content);
				}
				resultado = mapper.readValue(sb.toString(), RespuestaServicioDto.class);
			}

		} catch (Exception e) {
			resultado = new RespuestaServicioDto();
			resultado.setTramiteId(0L);
		}
		if (!resultado.isOk()) {
			resultado.setTramiteId(0L);
		}
		LOG.info("TRAMITE FINAL: "+ resultado.getTramiteId());

		return resultado.getTramiteId();
	}

	/**
	 * para poder obtener el PIN basándose en el PIN y el NIT retorna el PIN y como
	 * parte de la información el estado del mismo como un valor booleano
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 22/11/2018
	 * @return Devuelve objeto respuesta PinDto
	 */
//	public RespUsuariosAsignancionProcesoDto obtenerUsuariosAsignacionEquipoCertificacion() {
//		configWorkFlow();
//
//		RespUsuariosAsignancionProcesoDto vResultado = new RespUsuariosAsignancionProcesoDto();
//
//		try {
//			String ruta = host + "/rest/workflow/usuariosProceso/";
//
//			ObjectMapper mapper = new ObjectMapper();
//
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("proceso", 1002 + "");
//			map.put("etapa", 2005 + "");
//			Response response = ejecutarGetConParametrosToken(ruta, map);
//
//			if (response.code() == 404) {
//				vResultado = new RespUsuariosAsignancionProcesoDto();
//			} else {
//
//				InputStream istream = response.body().byteStream();
//				InputStreamReader isr = null;
//				BufferedReader br = null;
//				StringBuilder sb = new StringBuilder();
//				String content;
//
//				isr = new InputStreamReader(istream);
//				br = new BufferedReader(isr);
//				while ((content = br.readLine()) != null) {
//					sb.append(content);
//				}
//				vResultado = mapper.readValue(sb.toString(), RespUsuariosAsignancionProcesoDto.class);
//			}
//		} catch (Exception e) {
//			vResultado = new RespUsuariosAsignancionProcesoDto();
//			vResultado.setOk(false);
//		}
//
//		return vResultado;
//	}

	public Response ejecutarPostConParametroMapperToken(String hostDireccion, String json) {
        LOG.info("INGRESO A LA LLAMADA WORKFLOW.");
        System.out.println("HOSTDIRECCION : "+hostDireccion);
        System.out.println("JSON : "+json);
        
        String vToken="";
        vToken = obtenerToken();
        
        System.out.println("vToken : "+vToken);		
        		
        Response response;
        try {
               RequestBody body = RequestBody.create(JSON, json);
               HttpUrl route = HttpUrl.parse(hostDireccion).newBuilder().build();
               Request request = new Request.Builder().url(route).post(body).addHeader("Authorization", vToken)
                             .build();
               response = client.newCall(request).execute();
        } catch (Exception e) 
        {
        	LOG.info("ANTES DE LA SALIDA A LA LLAMADA WORKFLOW." + e.getMessage());
               LogExcepcion.registrar(e, LOG, MethodSign.build(hostDireccion));
               return null;
        }

        LOG.info("SALIDA A LA LLAMADA WORKFLOW.");
        return response;
  }


	public Response ejecutarGetConParametrosToken(String hostDireccion, Map<String, String> params) {
		Response response;
		try {
			HttpUrl.Builder route = HttpUrl.parse(hostDireccion).newBuilder();

			for (Map.Entry<String, String> param : params.entrySet()) {
				route.addPathSegment(param.getValue());
			}

			Request request = new Request.Builder().url(route.build()).addHeader("Authorization", obtenerToken()).get()
					.build();

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

	public String obtenerToken() 
	{
		String vResultado="";
        UsuarioAutenticadoDto resultado = new UsuarioAutenticadoDto();
        
        try {
                String json = "";
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

}
