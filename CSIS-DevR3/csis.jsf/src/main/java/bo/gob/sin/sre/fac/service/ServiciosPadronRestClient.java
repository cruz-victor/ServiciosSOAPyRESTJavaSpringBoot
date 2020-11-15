package bo.gob.sin.sre.fac.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.caco.dto.ContribuyenteMasDireccionFiscalDto;
import bo.gob.sin.scn.empa.casu.dto.SucursalContribuyenteDto;
import bo.gob.sin.scn.empa.casu.dto.SucursalContribuyenteListaDto;
import bo.gob.sin.scn.empa.ccoc.dto.ActividadesXNitDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitActivoDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosMasDireccionFiscalDto;
import bo.gob.sin.scn.empa.ccoc.dto.RespuestaServicioDto;
import bo.gob.sin.scn.empa.ccoc.dto.TallerContribuyenteListaDto;
import bo.gob.sin.scn.empa.para.dto.ResultadoGenericoDto;
import bo.gob.sin.scn.empa.para.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SucursalContribuyenteListaDTO;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import io.undertow.util.StatusCodes;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ServiciosPadronRestClient extends PeticionesRest  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}
	

	
	/**
	 * Objetivo: probar la conección con los servicios de padrón Creado por: Fabio
	 * Ramos Fecha: 04/05/2018 Modificado por: Wilson Limachi Fecha Modificacion:
	 * 08/05/2018 Modificado por: Americo Chavez Modificacion de ruta de servicio
	 * Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public RespuestaOperacionDto  nitActivo(long pNit) {
		configFacturacion_Puerto39288_Rest();
		RespuestaOperacionDto  resultado = new RespuestaOperacionDto();;

		try {
			String ruta = host + "/rest/validacionesContribuyente/nitActivo/";

			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<Boolean> typeRef = new TypeReference<Boolean>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaOperacionDto();
				resultado.setMensajes(ErrorComunicacion());
			} 
			else 
			{
				resultado.setOk(Json.serializer().fromInputStream(response.body().byteStream(), typeRef));
				
				if(!resultado.isOk())
				{
					resultado.setMensajes(ErrorNitInactivo());
				}
			}
		} 
		catch (Exception e) 
		{
			resultado = new RespuestaOperacionDto();
			resultado.setMensajes(ErrorComunicacion());
		}

		return resultado;
	}
	
	/**
	 * Objetivo: verificar Marca Domicilio Inexistente por NIT. respuesta existencia
	 * marca domicilio inexsistente Creado por: Wilson Limachi. Fecha: 11/05/2018
	 * Modificado por: Americo Chavez Modificacion de ruta de servicio Fecha:
	 * 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 26/11/2019
	 */
	public RespuestaOperacionDto verificarMarcaDomicilioInexistenteXNIT(long pNit) {
		configFacturacion_Puerto39288_Rest();
		RespuestaOperacionDto resultado = new RespuestaOperacionDto();

		try {
			String ruta = host + "/rest/validacionesContribuyente/verificaMarcaDomicilioInexistenteO/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<Boolean> typeRef = new TypeReference<Boolean>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaOperacionDto();
				resultado.setMensajes(ErrorComunicacion());
			} 
			else 
			{
				resultado.setOk(Json.serializer().fromInputStream(response.body().byteStream(), typeRef));
				
				if(resultado.isOk())
				{
					resultado.setMensajes(ErrorDomicilioInexistente());
				}				
			}
		} catch (Exception e) {
			resultado = new RespuestaOperacionDto();
			resultado.setMensajes(ErrorComunicacion());
		}

		return resultado;
	}
		
	/**
	 * Objetivo: verificar Marca Fiscalizacion por NIT. respuesta 1:existencia marca
	 * domicilio inexsistente 0: no tiene la marca Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018 Modificado por: Americo Chavez Modificacion de ruta de
	 * servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public RespuestaServicioDto verificarMarcaFiscalizacionXNIT(long pNit) {
		configFacturacionPadronRest();
		RespuestaServicioDto resultado = new RespuestaServicioDto();

		try {
			String ruta = host + "/rest/validacionesContribuyente/verificarMarcaFiscalizacionO/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<Boolean> typeRef = new TypeReference<Boolean>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaServicioDto();
			} else {
				resultado.setOk(Json.serializer().fromInputStream(response.body().byteStream(), typeRef));
			}
		} catch (Exception e) {
			resultado = new RespuestaServicioDto();
		}

		return resultado;
	}
	
	/**
	 * Verifica si la persona esta registrada en el padron     
	 * Creado por: Wilson Limachi            
	 * 
	 * @param pNumeroDocumento      numero de documento
	 * @param pCodigoComplementario codigo complementario
	 * @return RespuestaServicioDto true/0 si nit es activo
	 */
	public RespuestaServicioDto verificarPersonaTieneRegistroPadron(String pNumeroDocumento, String pCodigoComplementario)
	{
		configFacturacionPadronRest();
		RespuestaServicioDto resultado = new RespuestaServicioDto();

		try 
		{
			String ruta = host + "/rest/validacionesContribuyente/verificaRegistroPadronPorNumeroDocumentoO/";
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("pNumeroDocumento", pNumeroDocumento);
			map.put("pCodigoComplementario", pCodigoComplementario);

			TypeReference<Boolean> typeRef = new TypeReference<Boolean>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaServicioDto();
			} 
			else 
			{
				resultado.setOk(Json.serializer().fromInputStream(response.body().byteStream(), typeRef));
			}
		} 
		catch (Exception e) 
		{
			resultado = new RespuestaServicioDto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: existe Form Iva200210 Vigente. Dado el numero de documento y codigo
	 * complementario, verificar si la persona tiene registro en el padron Creado
	 * por: Wilson Limachi. Fecha: 11/05/2018 Modificado por: Americo Chavez
	 * Modificacion de ruta de servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 09/09/2019
	 */
	public RespuestaServicioDto existeFormIva200210Vigente(long pNit) {
		configFacturacionPadronExisteFormIvaRest();
		RespuestaServicioDto resultado = new RespuestaServicioDto();

		try {
			String ruta = host + "/rest/validacionConsultaObligacion/existeFormIva200210VigenteO/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<Boolean> typeRef = new TypeReference<Boolean>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaServicioDto();
				resultado.setMensajes(ErrorComunicacion());
			} 
			else 
			{
				resultado.setOk(Json.serializer().fromInputStream(response.body().byteStream(), typeRef));
				
				if(!resultado.isOk())
				{
					resultado.setMensajes(ErrorVigenciaForm200_210());
				}
			}
		} 
		catch (Exception e) 
		{
			resultado = new RespuestaServicioDto();
			resultado.setMensajes(ErrorComunicacion());
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

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ActividadesXNitDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
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
	 * Fecha: 26/06/2019
	 */
	public SucursalContribuyenteListaDto obtenerSucursalesActivasXNIT(long pNit) {
		configFacturacionPadronObtenerSucursalesActivasXNITRest();
		SucursalContribuyenteListaDto resultado = new SucursalContribuyenteListaDto();

		try {
			String ruta = host + "/rest/consultaSucursalContribuyente/obtenerSucursalesActivasXNitO/";
			Map<String, String> map = new HashMap<>();
			map.put("pNit", pNit + "");

			TypeReference<ResultadoGenericoListaDto<SucursalContribuyenteDto>> typeRef = new TypeReference<ResultadoGenericoListaDto<SucursalContribuyenteDto>>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new SucursalContribuyenteListaDto();
			} 
			else 
			{
				ResultadoGenericoListaDto<SucursalContribuyenteDto> vResultado = new ResultadoGenericoListaDto<SucursalContribuyenteDto>();
				vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				
				resultado.setListaSucursalContribuyenteDto(vResultado.getResultadoLista());
				resultado.setMensajes(vResultado.getMensajes());
				resultado.setOk(vResultado.isOk());
			}
		} 
		catch (Exception e) 
		{
			resultado = new SucursalContribuyenteListaDto();
		}

		return resultado;
	}
	
	/**
	 * Objetivo: Obtener Actividades por NIT respuesta 1:existencia marca bloqueo
	 * dosificacion - 0: inexsistente, no tiene la marca. Creado por: Wilson
	 * Limachi. Fecha: 11/05/2018 Modificado por: Americo Chavez Modificacion de
	 * ruta de servicio Fecha: 11/10/2018
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public RespuestaOperacionDto verificarMarcaJuridicaXNIT(long pNit) 
	{
		configFacturacion_Puerto39288_Rest();
		RespuestaOperacionDto resultado = new RespuestaOperacionDto();

		try 
		{
			String ruta = host + "/rest/validacionesContribuyente/verificaMarcaJuridicaO/";
			Map<String, String> map = new HashMap<String, String>();
			map.put("pNit", pNit + "");

			TypeReference<Boolean> typeRef = new TypeReference<Boolean>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaOperacionDto();
				resultado.setMensajes(ErrorComunicacion());
			} 
			else 
			{
				resultado.setOk(Json.serializer().fromInputStream(response.body().byteStream(), typeRef));
				
				if(!resultado.isOk())
				{
					resultado.setMensajes(ErrorVerificarMarcaJuridica());
				}
			}
		} 
		catch (Exception e) 
		{
			resultado = new RespuestaOperacionDto();
			resultado.setMensajes(ErrorComunicacion());
		}

		return resultado;
	}
	
	//Agregar codigo de Error
	public List<StrMensajeAplicacionDto> ErrorComunicacion()
	{
		List<StrMensajeAplicacionDto> vListaError = new ArrayList<>();
		StrMensajeAplicacionDto vStrMensajeAplicacionDto=new StrMensajeAplicacionDto();
		vStrMensajeAplicacionDto.setDescripcion("Error de Comunicación");
		vListaError.add(vStrMensajeAplicacionDto);
		return vListaError;
	}
	
	//Agregar codigo de Error
	public List<StrMensajeAplicacionDto> ErrorNitInactivo()
	{
		List<StrMensajeAplicacionDto> vListaError = new ArrayList<>();
		StrMensajeAplicacionDto vStrMensajeAplicacionDto=new StrMensajeAplicacionDto();
		vStrMensajeAplicacionDto.setDescripcion("Nit Inactivo");
		vListaError.add(vStrMensajeAplicacionDto);
		return vListaError;
	}
	
	public List<StrMensajeAplicacionDto> ErrorDomicilioInexistente()
	{
		List<StrMensajeAplicacionDto> vListaError = new ArrayList<>();
		StrMensajeAplicacionDto vStrMensajeAplicacionDto=new StrMensajeAplicacionDto();
		vStrMensajeAplicacionDto.setDescripcion("Domicilio Inexistente");
		vListaError.add(vStrMensajeAplicacionDto);
		return vListaError;
	}
	
	public List<StrMensajeAplicacionDto> ErrorVigenciaForm200_210()
	{
		List<StrMensajeAplicacionDto> vListaError = new ArrayList<>();
		StrMensajeAplicacionDto vStrMensajeAplicacionDto=new StrMensajeAplicacionDto();
		vStrMensajeAplicacionDto.setDescripcion("Formularios 200 o 210 error en Vigencia.");
		vListaError.add(vStrMensajeAplicacionDto);
		return vListaError;
	}
	
	public List<StrMensajeAplicacionDto> ErrorVerificarMarcaJuridica()
	{
		List<StrMensajeAplicacionDto> vListaError = new ArrayList<>();
		StrMensajeAplicacionDto vStrMensajeAplicacionDto=new StrMensajeAplicacionDto();
		vStrMensajeAplicacionDto.setDescripcion("Error en Marca Jurídica.");
		vListaError.add(vStrMensajeAplicacionDto);
		return vListaError;
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

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new TallerContribuyenteListaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
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
	public SucursalContribuyenteListaDTO obtenerSucursalXIFC(long pIfc, int pNumeroSucursal)
	{
		configFacturacionPadronRest();
		SucursalContribuyenteListaDTO resultado = new SucursalContribuyenteListaDTO();

		try 
		{
			String ruta = host + "/rest/empadronamientoConsulta/obtenerSucursalXIFC/";
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("pIfc", pIfc+"");
			map.put("pNumeroSucursal", pNumeroSucursal+"");

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				//resultado = new SucursalContribuyenteDto();
			} 
			else 
			{
				//resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			resultado = new SucursalContribuyenteListaDTO();
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

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new NitActivoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new NitActivoDto();
		}

		return resultado;
	}
	
	public ContribuyenteDto ObtenerDatosBasicosXNIT(long pNit) 
	{
		configFacturacionPadronRest();
		ContribuyenteDto resultado = new ContribuyenteDto();

		try 
		{
			String ruta = host + "/rest/consultasContribuyente/obtenerContribuyentePorNitO/";
			Map<String, String> map = new HashMap<>();
			map.put("nit", pNit + "");
			TypeReference<ResultadoGenericoDto<ContribuyenteDto>> typeRef = new TypeReference<ResultadoGenericoDto<ContribuyenteDto>>() {};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new ContribuyenteDto();
			} 
			else 
			{
				ResultadoGenericoDto<ContribuyenteDto> vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				resultado = vResultado.getResultadoObjeto(); 
				resultado.setMensajes(vResultado.getMensajes());
				
				if(vResultado.isOk())
				{
					resultado.setOk(true);				
				}
			}
		} 
		catch (Exception e) 
		{
			resultado = new ContribuyenteDto();
		}

		return resultado;
	}
	
	public ContribuyenteDto ObtenerDatosBasicosXIFC(long pIfc) 
	{
		configFacturacionPadronRest();
		ContribuyenteDto resultado = new ContribuyenteDto();

		try 
		{
			String ruta = host + "/rest/consultasContribuyente/obtenerContribuyentePorPersonaIdO/";
			Map<String, String> map = new HashMap<>();
			map.put("ifc", pIfc + "");

			TypeReference<ResultadoGenericoDto<ContribuyenteDto>> typeRef = new TypeReference<ResultadoGenericoDto<ContribuyenteDto>>() {};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new ContribuyenteDto();
			} 
			else 
			{
    			ResultadoGenericoDto<ContribuyenteDto> vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				resultado = vResultado.getResultadoObjeto(); 
				resultado.setMensajes(vResultado.getMensajes());
				
				if(vResultado.isOk())
				{
					resultado.setOk(true);				
				}
			}
		} 
		catch (Exception e) 
		{
			resultado = new ContribuyenteDto();
		}

		return resultado;
	}
	
	public ContribuyenteMasDireccionFiscalDto ObtenerDatosBasicosMasDomicilioXNIT(long pNit) 
	{
		configFacturacionPadronRest();
		ContribuyenteMasDireccionFiscalDto resultado = new ContribuyenteMasDireccionFiscalDto();
		try 
		{
			String ruta = host + "/rest/consultasContribuyente/obtenerContribuyenteMasDomicilioFiscalXNitO/";
			Map<String, String> map = new HashMap<>();
			map.put("nit", pNit + "");

			TypeReference<ContribuyenteMasDireccionFiscalDto> typeRef = new TypeReference<ContribuyenteMasDireccionFiscalDto>() {};
			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new ContribuyenteMasDireccionFiscalDto();
			} 
			else 
			{
				ContribuyenteMasDireccionFiscalDto vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				resultado = vResultado;
			}
		} 
		catch (Exception e) 
		{
			resultado = new ContribuyenteMasDireccionFiscalDto();
		}

		return resultado;
	}
	
	public ContribuyenteDto ObtenerDatosBasicosXIFCParaCertificado(long pIfc) 
	{
		configFacturacionPadronRest();
		ContribuyenteDto resultado = new ContribuyenteDto();

		try 
		{
			String ruta = host + "/rest/consultasContribuyente/obtenerContribuyentePorPersonaIdP/";
			Map<String, String> map = new HashMap<>();
			map.put("ifc", pIfc + "");

			TypeReference<ResultadoGenericoDto<ContribuyenteDto>> typeRef = new TypeReference<ResultadoGenericoDto<ContribuyenteDto>>() {};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new ContribuyenteDto();
			} 
			else 
			{
    			ResultadoGenericoDto<ContribuyenteDto> vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				resultado = vResultado.getResultadoObjeto(); 
				resultado.setMensajes(vResultado.getMensajes());
				
				if(vResultado.isOk())
				{
					resultado.setOk(true);				
				}
			}
		} 
		catch (Exception e) 
		{
			resultado = new ContribuyenteDto();
		}

		return resultado;
	}
	
}








	