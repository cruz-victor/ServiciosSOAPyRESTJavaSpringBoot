package bo.gob.sin.sre.fac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sap.cder.dto.RespDerivacionMultipleDto;
import bo.gob.sin.sap.cder.dto.SolicitudDerivacionMultipleDto;
import bo.gob.sin.sap.cder.dto.UsuarioAsignacionDto;
import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.sre.fac.model.RespUsuariosAsignancionProcesoDto;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import io.undertow.util.StatusCodes;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Response;

public class ServiciosWorkFlowRest extends PeticionesRest {
	private static final Logger LOG = LoggerFactory.getLogger(ServiciosWorkFlowRest.class);
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}

	public RespUsuariosAsignancionProcesoDto obtenerUsuariosAsignacionEquipoCertificacion(String pToken) {
		configFacturacionWorkflowcderRest();
		RespUsuariosAsignancionProcesoDto vResultado = new RespUsuariosAsignancionProcesoDto();

		try {
			String ruta = host + "/rest/workflow/asignacionSiguientes/";
			TypeReference<List<UsuarioAsignacionDto>> typeRef = new TypeReference<List<UsuarioAsignacionDto>>() {	};
			Map<String, String> map = new HashMap<>();
			map.put("proceso", ParametrosCsis.PROCESO_DE_CERTIFICACION + "");
			map.put("etapa", ParametrosCsis.ETAPA_ASIGNACION_CERTIFICACION.toString() + "");
			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespUsuariosAsignancionProcesoDto();
			} else {
				List<UsuarioAsignacionDto> lista = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				vResultado.setUsuariosAsignacion(lista);
				vResultado.setOk(true);
			}
		} catch (Exception e) {
			vResultado = new RespUsuariosAsignancionProcesoDto();
			vResultado.setOk(false);
		}
		return vResultado;
	}

	public RespDerivacionMultipleDto derivarUsuariosAsignacionEquipoCertificacion(SolicitudDerivacionMultipleDto pUsuariosAsignacion, String pToken) {
		configFacturacionWorkflowcderRest();
		RespDerivacionMultipleDto vResultado = new RespDerivacionMultipleDto();
		try {

			String ruta = host + "/rest/workflow/derivacionMultiple/";
			TypeReference<RespDerivacionMultipleDto> typeRef = new TypeReference<RespDerivacionMultipleDto>() {	};
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pUsuariosAsignacion);
			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespDerivacionMultipleDto();
			} else {
				vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new RespDerivacionMultipleDto();
		}

		return vResultado;
	}
	
}
