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

public class ServiciosTransversalesRest extends PeticionesRest implements Serializable{
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
	 * Objetivo: Verifica si el Login y el Ifc pertenecen al usuario 
	 * Creado por: junior.flores 
	 * Fecha: 22/11/2019
	 */
	public boolean verificaExistenciaUsuarioPorIfc(String pLoginUsuario,Long pIfc) 
	{
		configTransversales();
		String vRuta = host + "/usuario/existeUsuarioPorIfc/";
		boolean vResultado = false;
		
		try 
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("Login/usuario", pLoginUsuario + "");
			map.put("IFC", pIfc + "");
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return vResultado;
	}
}








	