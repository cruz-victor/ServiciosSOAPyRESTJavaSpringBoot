package bo.gob.sin.sre.fac.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetalleCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudConsultaFacturacionDto;
import io.undertow.util.StatusCodes;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ServiciosConsultaFacturacionRestClient extends PeticionesRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiciosConsultaFacturacionRestClient.class);
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public ServiciosConsultaFacturacionRestClient(String host, OkHttpClient client) {
		super();
		LOG.info("Ingresando constructor ServiciosFacturacionRest host={}, client={}", host, client);
		this.host = host;
		this.client = client;
		LOG.info("Saliendo constructor ServiciosFacturacionRest");
	}

	public ServiciosConsultaFacturacionRestClient() {
	}

	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}

	/**
	 * Objetivo: VerificarConeccion. Creado por: Wilson Limachi. Fecha: 13/08/2018
	 * 
	 * @Modificado por: Peter Flores
	 * @Fecha:12/10/2018
	 * @Descripción: Se relizó la verificación del servicio Comun para los datos
	 *               básicos
	 */
	public RespuestaOperacionDto verificarConexionInicio() {
		configFacturacionCertificacionRest();
		RespuestaOperacionDto resultado = new RespuestaOperacionDto();

		try {
			String ruta = host + "/comun/verificarConexionInicio";

			TypeReference<RespuestaOperacionDto> typeRef = new TypeReference<RespuestaOperacionDto>() {
			};

			Response response = ejecutarGetSinParametrosToken(ruta);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaOperacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaOperacionDto();
		}
		return resultado;
	}

	/**
	 * Consulta de Modalidad para empresas
	 * 
	 * @author: wilson.limachi
	 * @Fecha: 26/02/2019
	 * @return Devuelve objeto respuesta RespuestaConsultaFacturacionDto
	 */
	public RespuestaConsultaFacturacionDto consultaFacturacion(SolicitudConsultaFacturacionDto pSolicitud) 
	{
		configFacturacionCertificacionRest();
		RespuestaConsultaFacturacionDto resultado = new RespuestaConsultaFacturacionDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/facturacion/consultaFacturacion";
			TypeReference<RespuestaConsultaFacturacionDto> typeRef = new TypeReference<RespuestaConsultaFacturacionDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new RespuestaConsultaFacturacionDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new RespuestaConsultaFacturacionDto();
	    }
	
		return resultado;
	}
	
	/**
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 19/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo ListaDetalleHuellaDto.*
	 */
	public ListaDetalleHuellaDto obtieneComponentesArchivosCertificados(ListaDetalleHuellaDto pSolicitud) 
	{
		configFacturacionCertificacionRest();
		ListaDetalleHuellaDto resultado = new ListaDetalleHuellaDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/obtieneComponentesArchivosCertificados";
			TypeReference<ListaDetalleHuellaDto> typeRef = new TypeReference<ListaDetalleHuellaDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new ListaDetalleHuellaDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new ListaDetalleHuellaDto();
	    }
	
		return resultado;
	}
	
	/**
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 20/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo ListaDetalleHuellaDto.*
	 */
	public RespuestaOperacionDto registrarComponentesArchivos(RespuestaDetalleHuellaDto  pSolicitud) 
	{
		configFacturacionCertificacionRest();
		RespuestaOperacionDto resultado = new RespuestaOperacionDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/registrarComponentesArchivos";
			TypeReference<RespuestaOperacionDto> typeRef = new TypeReference<RespuestaOperacionDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new RespuestaOperacionDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new RespuestaOperacionDto();
	    }
	
		return resultado;
	}
	
	
	/***
	 * @Descripcion: Cambia el estado de cancelado a los archivos componente certificados.
	 * @author wilson.limachi
	 * @return dto respuesta
	 * @fecha 24/09/2019
	 */
	public RespuestaOperacionDto actualizaCanceladoComponentesArchivosCertificados(RespuestaOperacionDto  pSolicitud) 
	{
		configFacturacionCertificacionRest();
		RespuestaOperacionDto resultado = new RespuestaOperacionDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/actualizaCanceladoComponentesArchivosCertificados";
			TypeReference<RespuestaOperacionDto> typeRef = new TypeReference<RespuestaOperacionDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new RespuestaOperacionDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new RespuestaOperacionDto();
	    }
	
		return resultado;
	}
	
	/** 
	 * Realiza el listado del diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi
	 * @Fecha: 25/09/2019
	 * @param  pSolicitud, Todos los datos para obtener el listado de Diagramas de Certificaciones   		   
	 * @return   Devuelve el objeto ListaDiagramasCertificacionesDto.
	 */
	public ListaDiagramasCertificacionesDto obtieneListaDiagramasCertificaciones(ListaDiagramasCertificacionesDto  pSolicitud) 
	{
		configFacturacionCertificacionRest();
		ListaDiagramasCertificacionesDto resultado = new ListaDiagramasCertificacionesDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/obtieneListaDiagramasCertificaciones";
			TypeReference<ListaDiagramasCertificacionesDto> typeRef = new TypeReference<ListaDiagramasCertificacionesDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new ListaDiagramasCertificacionesDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new ListaDiagramasCertificacionesDto();
	    }
	
		return resultado;
	}
	
	/** 
	 * Realiza el registro del diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha: 25/09/2019
	 * @param  pSolicitud, Todos los datos correspondientes a Diagrama de Certificaciones 		   
	 * @return   Devuelve el objeto DiagramasCertificacionesDto con la respuesta de la transacción.
	 */
	public DiagramasCertificacionesDto registraDiagramasCertificaciones(DiagramasCertificacionesDto  pSolicitud) 
	{
		configFacturacionCertificacionRest();
		DiagramasCertificacionesDto resultado = new DiagramasCertificacionesDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/registraDiagramasCertificaciones";
			TypeReference<DiagramasCertificacionesDto> typeRef = new TypeReference<DiagramasCertificacionesDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new DiagramasCertificacionesDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new DiagramasCertificacionesDto();
	    }
	
		return resultado;
	}
	
	/** 
	 * Realiza el listado del detalle de certificaciones de Sistemas 
	 * 
	 * @author: peter.flores
	 * @Fecha: 26/09/2019
	 * @param  pSolicitud, Todos los datos para obtener el listado de Detalle de Certificaciones de Sistemas   		   
	 * @return   Devuelve el objeto RespuestaListaDetalleCertificacionesSistemasDto.
	 */
	public RespuestaListaDetalleCertificacionesSistemasDto obtieneListaDetallesCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitud) 
	{
		configFacturacionCertificacionRest();
		RespuestaListaDetalleCertificacionesSistemasDto resultado = new RespuestaListaDetalleCertificacionesSistemasDto();		
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/obtenerListaDetalleCertificacionSistemas";
			TypeReference<RespuestaListaDetalleCertificacionesSistemasDto> typeRef = new TypeReference<RespuestaListaDetalleCertificacionesSistemasDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new RespuestaListaDetalleCertificacionesSistemasDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }            
	    } 
		catch (Exception e) 
		{
	         resultado = new RespuestaListaDetalleCertificacionesSistemasDto();
	    }
	
		return resultado;
	}
	
	/** 
	 * Realiza el registro del detalle de certificaciones de Sistemas 
	 * 
	 * @author: peter.flores
	 * @Fecha: 26/09/2019
	 * @param  pSolicitud, Todos los datos para realizar el registro del Detalle de Certificaciones de Sistemas   		   
	 * @return   Devuelve el objeto DetallesCertificacionesSistemasDto.
	 */
	public DetallesCertificacionesSistemasDto registraDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitud) 
	{
		configFacturacionCertificacionRest();
		DetallesCertificacionesSistemasDto resultado = new DetallesCertificacionesSistemasDto();		
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/registraDetalleCertificacionesSistemas";
			TypeReference<DetallesCertificacionesSistemasDto> typeRef = new TypeReference<DetallesCertificacionesSistemasDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new DetallesCertificacionesSistemasDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }            
	    } 
		catch (Exception e) 
		{
	         resultado = new DetallesCertificacionesSistemasDto();
	    }
	
		return resultado;
	}
	
	/** 
	 * Realiza la actualizacion del estado de diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha: 27/09/2019
	 * @param  pDiagramaCertificacionId	   
	 * @return   Devuelve el objeto RespuestaOperacionDto
	 */
	public RespuestaOperacionDto actualizaDiagramasCertificaciones(RespuestaOperacionDto  pSolicitud) 
	{
		configFacturacionCertificacionRest();
		RespuestaOperacionDto resultado = new RespuestaOperacionDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/actualizaDiagramasCertificaciones";
			TypeReference<RespuestaOperacionDto> typeRef = new TypeReference<RespuestaOperacionDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new RespuestaOperacionDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new RespuestaOperacionDto();
	    }
	
		return resultado;
	}
	
	/** 
	 * Realiza la anulaci�n del detalle de certificaciones de Sistemas 
	 * 
	 * @author: peter.flores
	 * @Fecha: 27/09/2019
	 * @param  pSolicitud, Todos los datos para eliminar el registro del Detalle de Certificaciones de Sistemas   		   
	 * @return   Devuelve el objeto DetallesCertificacionesSistemasDto.
	 */
	public DetallesCertificacionesSistemasDto anularDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitud) 
	{
		configFacturacionCertificacionRest();
		DetallesCertificacionesSistemasDto resultado = new DetallesCertificacionesSistemasDto();		
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/anularDetalleCertificacionSistemas";
			TypeReference<DetallesCertificacionesSistemasDto> typeRef = new TypeReference<DetallesCertificacionesSistemasDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new DetallesCertificacionesSistemasDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }            
	    } 
		catch (Exception e) 
		{
	         resultado = new DetallesCertificacionesSistemasDto();
	    }
	
		return resultado;
	}
}
