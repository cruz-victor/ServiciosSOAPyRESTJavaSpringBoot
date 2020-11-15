package bo.gob.sin.sre.fac.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import io.undertow.util.StatusCodes;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ServiciosParametricaRest extends PeticionesRest implements Serializable{
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
	 * Creado por: Wilson Limachi Choque
	 * Fecha: 10/05/2019
	 */
	public ClasificadorDto recuperarClasificadorPorId(int pClasificadorId) 
	{
		configParametricas();
		ClasificadorDto vResultado = new ClasificadorDto();
		try 
		{	
			
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
	 * Objetivo: Recuperar Clasificador por Grupo. 
	 * Creado por: Wilson Limachi Choque. 
	 * Fecha: 31/05/2019
	 */
	public List<ClasificadorDto> recuperarClasificadorPorGrupo(int pClasificadorId) 
	{
		configParametricas();
		String vRuta = host + "/clasificador/porGrupo/";
		List<ClasificadorDto> vResultado = new ArrayList<ClasificadorDto>();
		
		try 
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("pClasificadorId", pClasificadorId + "");
			
			TypeReference<List<ClasificadorDto>> listTypeRef = new TypeReference<List<ClasificadorDto>>() {};
			Response response = ejecutarGetConParametros(vRuta, map);

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
}








	