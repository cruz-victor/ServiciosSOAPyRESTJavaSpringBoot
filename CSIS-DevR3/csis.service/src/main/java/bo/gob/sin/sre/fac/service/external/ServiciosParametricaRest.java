package bo.gob.sin.sre.fac.service.external;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
//import bo.gob.sin.transversales.parametricas.dto.ClasificadorDto;
import io.undertow.util.StatusCodes;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ServiciosParametricaRest extends PeticionesRest implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ServiciosParametricaRest.class);

	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}
	
	/**
	 * @autor wilson.limachi
	 * @descripción obtencion de paramétrica
	 * @return lista dto de tipo ClasificadorDto
	 * @fecha 06/11/2018
	 */
	public List<ClasificadorDto> obtenerParametrica(String pNombreRutaSinHost) 
	{
		configParametricas();
		String vRuta = host + "/clasificador/tipoClasificador/" + pNombreRutaSinHost;
		List<ClasificadorDto> vResultado = new ArrayList<ClasificadorDto>();
		
		try 
		{
			TypeReference<List<ClasificadorDto>> listTypeRef = new TypeReference<List<ClasificadorDto>>() {};
			Response response = ejecutarGetSinParametros(vRuta);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				vResultado = new ArrayList<ClasificadorDto>();
			} 
			else 
			{
				vResultado = Json.serializer().fromInputStream(response.body().byteStream(), listTypeRef);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vResultado = new ArrayList<ClasificadorDto>();
		}

		return vResultado;
	}
	
	/**
	 * Objetivo: Recuperar Clasificador por Id . 
	 * Creado por: Carmen Rosa Silva. Fecha:
	 * 10/09/2018
	 */
	public ClasificadorDto recuperarClasificadorPorId(int pClasificadorId) 
	{
		configParametricas();
		ClasificadorDto vResultado = new ClasificadorDto();
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pClasificadorId);
			String ruta = host + "/clasificador/id/";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("pClasificadorId", pClasificadorId + "");
			
			TypeReference<ClasificadorDto> listTypeRef = new TypeReference<ClasificadorDto>() {
			};
			Response response = ejecutarGetConParametros(ruta, map);
			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				vResultado = new ClasificadorDto();
			} 
			else 
			{
				vResultado = Json.serializer().fromInputStream(response.body().byteStream(), listTypeRef);
			}
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pClasificadorId));
			e.printStackTrace();
			vResultado = new ClasificadorDto();
		}

		return vResultado;
	}
	
	/**
	 * Objetivo: Recuperar Clasificador por tIPO . 
	 * Creado por: Wilson Limachi. Fecha:
	 * 29/09/2019
	 */
	public List<ClasificadorDto> recuperarClasificadorPorTipo(String pTipo) 
	{
		configParametricas();
		List<ClasificadorDto> vResultado = new ArrayList<>();
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pTipo);
			String ruta = host + "/clasificador/tipoClasificador/";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("pTipo", pTipo + "");
			
			TypeReference<List<ClasificadorDto>> listTypeRef = new TypeReference<List<ClasificadorDto>>() {
			};
			Response response = ejecutarGetConParametros(ruta, map);
			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				vResultado = new ArrayList<>();
			} 
			else 
			{
				vResultado = Json.serializer().fromInputStream(response.body().byteStream(), listTypeRef);
			}
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pTipo));
			e.printStackTrace();
			vResultado = new ArrayList<>();
		}

		return vResultado;
	}
}








	