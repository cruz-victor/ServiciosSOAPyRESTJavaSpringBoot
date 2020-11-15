package bo.gob.sin.sre.fac.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.DatosTramiteSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasProveedorDto;
import bo.gob.sin.sre.fac.dto.NotificacionElectronicaListaDto;
import bo.gob.sin.sre.fac.dto.ObtenerListSistemaDto;
import bo.gob.sin.sre.fac.dto.ObtenerListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemaCertificacion;
import bo.gob.sin.sre.fac.dto.RecuperarListaContactosCertificacion;
import bo.gob.sin.sre.fac.dto.RecuperarListaSolicitudCertificacion;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroAutorizacionRechazoCertificacionDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituListaDto;
import bo.gob.sin.sre.fac.dto.RegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistrosPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaCalcularPorcentajePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaCertificadoAprobacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetallePruebasEtapas2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.RespuestaMatchLogCasosPruebaEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebaSugeridaDto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaTamanioGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaVerificacionPruebasConcluidasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasCertificadosContribuyenteDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudCertificadoAprobacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemas;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarReCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroProveedorDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;
import bo.gob.sin.sre.fac.dto.StubCancelacionSolicitudDto;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import io.undertow.util.StatusCodes;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ServiciosFacturacionGiecRest extends PeticionesRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiciosFacturacionGiecRest.class);
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public ServiciosFacturacionGiecRest(String host, OkHttpClient client) {
		super();
		LOG.info("Ingresando constructor ServiciosFacturacionGiecRest host={}, client={}", host, client);
		this.host = host;
		this.client = client;
		LOG.info("Saliendo constructor ServiciosFacturacionGiecRest");
	}

	public ServiciosFacturacionGiecRest() {
	}

	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}
	
	/**
	 * @descripcion: Lista Sistemas Contribuyente
	 * @author: wilson.limachi.
	 * @fecha: 05/09/2019
	 */
	public RespuestaListaSistemasContribuyentesDto obtenerListaSistemasContribuyente(Long pContribuyenteId) 
	{
		configFacturacionGiecRest();
		RespuestaListaSistemasContribuyentesDto resultado = new RespuestaListaSistemasContribuyentesDto();
		SistemasCertificadosContribuyenteDto vSistemasCertificadosContribuyenteDto = new SistemasCertificadosContribuyenteDto();
		vSistemasCertificadosContribuyenteDto.setContribuyenteId(pContribuyenteId);
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(vSistemasCertificadosContribuyenteDto);
			// iniciarSistemas/recuperarSistemasContribuyentes
			String ruta = host + "/solicitud/iniciarSistemas/recuperarSistemasContribuyentes";
			TypeReference<RespuestaListaSistemasContribuyentesDto> typeRef = new TypeReference<RespuestaListaSistemasContribuyentesDto>() {};
			// Response response = ejecutarPostConParametroMapperToken(ruta, json);
			Response response = ejecutarPostConParametroMapper(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaListaSistemasContribuyentesDto();
			} 
			else 
			{
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
				// resultado = vResponseEntity.getBody();
			}
		} 
		catch (Exception e) 
		{
			resultado = new RespuestaListaSistemasContribuyentesDto();
			e.printStackTrace();
		}
		
		return resultado;
	}
}