package bo.gob.sin.sre.fac.service.external;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.ActividadesXNitDto;
import bo.gob.sin.scn.empa.ccoc.dto.ExistenciaFormIva200210Dto;
import bo.gob.sin.scn.empa.ccoc.dto.NitActivoDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;
import bo.gob.sin.scn.empa.ccoc.dto.RespuestaServicioDto;
import bo.gob.sin.scn.empa.ccoc.dto.SucursalContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.SucursalContribuyenteListaDto;
import bo.gob.sin.scn.empa.ccoc.dto.TallerContribuyenteListaDto;
import bo.gob.sin.scn.empa.para.dto.ResultadoGenericoDto;
import bo.gob.sin.sen.enmo.jsf.model.HttpClient;
import bo.gob.sin.sen.enmo.jsf.service.RestClient;
import bo.gob.sin.str.caut.dto.UsuarioAutenticadoDto;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
//import bo.gob.sin.str.ccs.jsfconf.RestUrl;
//import bo.gob.sin.transversales.configuracionsistema.clienterest.ClienteRest;
//import bo.gob.sin.transversales.configuracion.jsfconfig.RestUrl;
//import bo.gob.sin.transversales.configuracionsistema.clienterest.ClienteRest;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiciosPadronRestClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ServiciosPadronRestClient.class);
	
	private static String host;
	private static String token;
	private static String padron;
	private static OkHttpClient client;
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static Gson gson = new Gson();

	public void configFacturacionPadronRest() {
		RestUrl vConf = new RestUrl();
		String vUrlServidor = vConf.getPropetyValue("scn_emp_caco_query_rest");
		this.client = HttpClient.globalClient();
		this.host = vUrlServidor;
	}
	
	public void ConfiguracionServiciosTokenRest() {
		RestUrl vConf = new RestUrl();
		String vUrlServidor = vConf.getPropetyValue("str_cau_caut_rest");
		RestClient  cl = new RestClient ();
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
	
	

	/**
	 * Objetivo: probar la conección con los servicios de padrón Creado por: Fabio
	 * Ramos Fecha: 04/05/2018 Modificado por: Wilson Limachi Fecha Modificacion:
	 * 08/05/2018 Modificado por: Americo Chavez Modificacion de ruta de servicio
	 * Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public NitActivoDto  nitActivo(long pNit) {
		configFacturacionPadronRest();
		NitActivoDto  resultado = new NitActivoDto();

		try 
		{
			
			String ruta = host + "/rest/empadronamientoConsulta/nitActivo/";

			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<NitActivoDto > typeRef = new TypeReference<NitActivoDto >() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) 
			{
				resultado = null;
			} 
			else 
			{
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} catch (Exception e) {
			resultado = new NitActivoDto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: existe Form Iva200210 Vigente. Dado el numero de documento y codigo
	 * complementario, verificar si la persona tiene registro en el padron Creado
	 * por: Wilson Limachi. Fecha: 11/05/2018 Modificado por: Americo Chavez
	 * Modificacion de ruta de servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public ExistenciaFormIva200210Dto existeFormIva200210Vigente(long pNit) {
		configFacturacionPadronRest();
		ExistenciaFormIva200210Dto resultado = new ExistenciaFormIva200210Dto();

		try {
			String ruta = host + "/rest/empadronamientoConsulta/existeFormIva200210Vigente/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<ExistenciaFormIva200210Dto> typeRef = new TypeReference<ExistenciaFormIva200210Dto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) {
				resultado = null;
			} else {
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ExistenciaFormIva200210Dto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: Obtener Actividades por NIT. Lista de actividades Creado por:
	 * Wilson Limachi. Fecha: 11/05/2018 Modificado por: Americo Chavez Modificacion
	 * de ruta de servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public ActividadesXNitDto ObtenerActividadessXNIT(long pNit) {
		configFacturacionPadronRest();
		ActividadesXNitDto resultado = new ActividadesXNitDto();

		try {
			String ruta = host + "/rest/empadronamientoConsulta/obtenerActividadessXNIT/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<ActividadesXNitDto> typeRef = new TypeReference<ActividadesXNitDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) {
				resultado = null;
			} else {
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ActividadesXNitDto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: obtener Sucursales Activas por NIT. Lista de Sucursales Activas
	 * correspondiente a un Contribuyente Creado por: Wilson Limachi. Fecha:
	 * 11/05/2018 Modificado por: Americo Chavez Modificacion de ruta de servicio
	 * Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public SucursalContribuyenteListaDto obtenerSucursalesActivasXNIT(long pNit) {
		configFacturacionPadronRest();
		SucursalContribuyenteListaDto resultado = new SucursalContribuyenteListaDto();

		try {
			String ruta = host + "/rest/empadronamientoConsulta/obtenerSucursalesActivasXNIT/";
			Map<String, String> map = new HashMap<>();
			map.put("pNit", pNit + "");

			TypeReference<SucursalContribuyenteListaDto> typeRef = new TypeReference<SucursalContribuyenteListaDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) {
				resultado = null;
			} else {
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} catch (Exception e) {
			resultado = new SucursalContribuyenteListaDto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: Obtener Datos Contribuyente por Nit. Creado por: Wilson Limachi.
	 * Fecha: 04/05/2018 Modificado por: Americo Chavez Modificacion de ruta de
	 * servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public ContribuyenteDto ObtenerDatosBasicosXNIT(long pNit) {
		configFacturacionPadronRest();
		ContribuyenteDto resultado = new ContribuyenteDto();

		try {
			String json = "";
			String ruta = host + "/rest/consultasContribuyente/obtenerContribuyentePorNitO/";

			Map<String, String> map = new HashMap<String, String>();
			map.put("nit", pNit + "");

			TypeReference<ResultadoGenericoDto<ContribuyenteDto>> typeRef = new TypeReference<ResultadoGenericoDto<ContribuyenteDto>>() {};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) {
				resultado = null;
			} else {
				ObjectMapper mapper = new ObjectMapper();
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
				
				ResultadoGenericoDto<ContribuyenteDto> vResultado = new ResultadoGenericoDto<ContribuyenteDto>();
				vResultado = mapper.readValue(sb.toString(), typeRef);
				
				resultado = vResultado.getResultadoObjeto(); 
				resultado.setMensajes(vResultado.getMensajes());
				resultado.setOk(vResultado.isOk());				
			}
		} 
		catch (Exception e) 
		{
			resultado = new ContribuyenteDto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: Obtener Datos Contribuyente por Nit. Creado por: Wilson Limachi.
	 * Fecha: 04/05/2018 Modificado por: Americo Chavez Modificacion de ruta de
	 * servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public ContribuyenteDto ObtenerDatosBasicosXIFC(long pIfc) 
	{
		configFacturacionPadronRest();
		ContribuyenteDto resultado = new ContribuyenteDto();

		try 
		{
			String ruta = host + "/rest/consultasContribuyente/obtenerContribuyentePorPersonaIdO/";

			Map<String, String> map = new HashMap<String, String>();
			map.put("pIfc", pIfc + "");

			TypeReference<ResultadoGenericoDto<ContribuyenteDto>> typeRef = new TypeReference<ResultadoGenericoDto<ContribuyenteDto>>() {};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) {
				resultado = null;
			} else 
			{
				ObjectMapper mapper = new ObjectMapper();
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
				
				ResultadoGenericoDto<ContribuyenteDto> vResultado = new ResultadoGenericoDto<ContribuyenteDto>();
				vResultado = mapper.readValue(sb.toString(), typeRef);
				
				resultado = vResultado.getResultadoObjeto(); 
				resultado.setMensajes(vResultado.getMensajes());
				resultado.setOk(vResultado.isOk());				
			}
		} catch (Exception e) {
			resultado = new ContribuyenteDto();
		}

		return resultado;
	}
	
	
	
		
	/**
	 * Dado el pIfc de un contribuyente, obtiene los talleres asociados                                
	 * 
	 * @author wilson limachi
	 * @param pNit nit
	 * @return Lista de talleres asociados al NIT
	 */
	public TallerContribuyenteListaDto obtenerTalleresActivosXNIT(long pNit) {
		configFacturacionPadronRest();
		TallerContribuyenteListaDto resultado = new TallerContribuyenteListaDto();

		try {
			String ruta = host + "/rest/empadronamientoConsulta/obtenerTalleresActivosXNIT/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<TallerContribuyenteListaDto> typeRef = new TypeReference<TallerContribuyenteListaDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) {
				resultado = null;
			} 
			else 
			{
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} catch (Exception e) {
			resultado = new TallerContribuyenteListaDto();
		}

		return resultado;
	}
	
	/**
	 * Dado el Ifc de un contribuyente y el numero de sucursal obtener los datos de                      
	 * la sucursal
	 * 
	 * @author wilson limachi
	 * @param pNit nit
	 * @return Datos de la sucursal
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public SucursalContribuyenteDto obtenerSucursalXIFC(long pIfc, int pNumeroSucursal)
	{
		configFacturacionPadronRest();
		SucursalContribuyenteDto resultado = new SucursalContribuyenteDto();

		try 
		{
			String ruta = host + "/rest/empadronamientoConsulta/obtenerSucursalXIFC/";
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("pIfc", pIfc+"");
			map.put("pNumeroSucursal", pNumeroSucursal+"");

			TypeReference<SucursalContribuyenteDto> typeRef = new TypeReference<SucursalContribuyenteDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) 
			{
				resultado = null;
			} 
			else 
			{
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			resultado = new SucursalContribuyenteDto();
		}

		return resultado;
	}
	
	/**
	 * Valida el NIT.
	 * 
	 * @author wilson limachi
	 * @param pNit
	 * @return true si es correcto, caso contrario false y descripción de error
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public NitActivoDto validarNIT(long pNit) {
		configFacturacionPadronRest();
		NitActivoDto resultado = new NitActivoDto();

		try {
			String ruta = host + "/rest/empadronamientoConsulta/validarNIT/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<NitActivoDto> typeRef = new TypeReference<NitActivoDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() != 200) 
			{
				resultado = null;
			} 
			else 
			{
				ObjectMapper mapper = new ObjectMapper();
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
				
				resultado = mapper.readValue(sb.toString(), typeRef);
			}
		} catch (Exception e) {
			resultado = new NitActivoDto();
		}

		return resultado;
	}	
	
	/**
	 * Objetivo: Obtener Datos Contribuyente por Nit. Creado por: Wilson Limachi.
	 * Fecha: 04/05/2018 Modificado por: Americo Chavez Modificacion de ruta de
	 * servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public String obtenerToken() {
		
		String vResultado="";
		UsuarioAutenticadoDto resultado = new UsuarioAutenticadoDto();
		
		try {
			String json = "";
			ConfiguracionServiciosTokenRest();

			ObjectMapper mapper = new ObjectMapper();
			String ruta = host + "/token/getGenerico/";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("mil", 1000 + "");
			
			Response response = ejecutarGetConParametrosToken2(ruta, map);

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
	
	public Response ejecutarGetConParametrosToken2(String hostDireccion, Map<String, String> params) {
		Response response;
		try {
			HttpUrl.Builder route = HttpUrl.parse(hostDireccion).newBuilder();

			for (Map.Entry<String, String> param : params.entrySet()) {
				route.addPathSegment(param.getValue());
			}

			Request request = new Request.Builder().url(route.build()).get()
					.build();

			response = client.newCall(request).execute();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
}
