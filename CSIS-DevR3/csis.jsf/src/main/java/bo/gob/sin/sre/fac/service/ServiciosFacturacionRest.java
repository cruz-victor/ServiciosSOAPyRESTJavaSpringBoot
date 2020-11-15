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
import bo.gob.sin.sre.fac.dto.ContribuyentesModalidadesDto;
import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.DatosTramiteSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
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
import bo.gob.sin.sre.fac.dto.RespuestaListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.RespuestaMatchLogCasosPruebaEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebaSugeridaDto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaResumenPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.RespuestaTamanioGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaVerificacionPruebasConcluidasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
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
import bo.gob.sin.sre.fac.dto.SolicitudRegistroVerificacionInsituDto;
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

public class ServiciosFacturacionRest extends PeticionesRest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiciosFacturacionRest.class);
	static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public ServiciosFacturacionRest(String host, OkHttpClient client) {
		super();
		LOG.info("Ingresando constructor ServiciosFacturacionRest host={}, client={}", host, client);
		this.host = host;
		this.client = client;
		LOG.info("Saliendo constructor ServiciosFacturacionRest");
	}

	public ServiciosFacturacionRest() {
	}

	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}

	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaRegistrarSolicitudCertificacionDto registrarSolicitudSistema(
			SolicitudRegistrarCertificacionSistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaRegistrarSolicitudCertificacionDto resultado = new RespuestaRegistrarSolicitudCertificacionDto();
		
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/registrarSolicitudSistema";
			TypeReference<RespuestaRegistrarSolicitudCertificacionDto> typeRef = new TypeReference<RespuestaRegistrarSolicitudCertificacionDto>() {
			};

			Response response = ejecutarPostConParametroMapper(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaRegistrarSolicitudCertificacionDto();
				resultado.setMensajes(ErrorComunicacion("Error de Comunicación, en el registro de certificación."));
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaRegistrarSolicitudCertificacionDto();
			resultado.setMensajes(ErrorComunicacion("Error de Comunicación, en el registro de certificación."));
		}

		return resultado;
	}

	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaPruebasSistemasDeCertificacionDto recuperarListaPruebasSistemasDeCertificacion(
			SolicitudPruebasSistemasDeCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaPruebasSistemasDeCertificacionDto resultado = new RespuestaPruebasSistemasDeCertificacionDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/recuperarListaPruebasSistemasDeCertificacion";
			TypeReference<RespuestaPruebasSistemasDeCertificacionDto> typeRef = new TypeReference<RespuestaPruebasSistemasDeCertificacionDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaPruebasSistemasDeCertificacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaPruebasSistemasDeCertificacionDto();
		}

		return resultado;
	}


	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaActualizacionDto rechazarSolicitudCertificacionSistema(
			SolicitudActualizaCertificacionSistemaDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionDto resultado = new RespuestaActualizacionDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/rechazarSolicitudCertificacionSistema";
			TypeReference<RespuestaActualizacionDto> typeRef = new TypeReference<RespuestaActualizacionDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionDto();
		}

		return resultado;
	}


	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaActualizacionDto actualizarPruebasSistemasDeCertificacion(SolicitudPruebasSistemas pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionDto resultado = new RespuestaActualizacionDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/actualizarPruebasSistemasDeCertificacion";
			TypeReference<RespuestaActualizacionDto> typeRef = new TypeReference<RespuestaActualizacionDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionDto();
		}

		return resultado;
	}


	

	

	// WLC
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaActualizacionDto actualizarSolicitudCertificacion(SolicitudRegistrarSistemasDto pPrimaria) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionDto resultado = new RespuestaActualizacionDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pPrimaria);
			String ruta = host + "/certificaciones/actualizarSolicitudCertificacion";
			TypeReference<RespuestaActualizacionDto> typeRef = new TypeReference<RespuestaActualizacionDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionDto();
		}

		return resultado;
	}

	// WLC
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public ListaSistemasDto listarSistemasPorIdContribuyente(SolicitudActualizaSolicitudCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		ListaSistemasDto resultado = new ListaSistemasDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/listarSistemasPorIdContribuyente";
			TypeReference<ListaSistemasDto> typeRef = new TypeReference<ListaSistemasDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaSistemasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaSistemasDto();
		}

		return resultado;
	}

	// WLC
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public ListaSistemasDto listarSistemasParaActualizarIdContribuyente(
			SolicitudActualizaSolicitudCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		ListaSistemasDto resultado = new ListaSistemasDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/listarSistemasParaActualizarIdContribuyente";
			TypeReference<ListaSistemasDto> typeRef = new TypeReference<ListaSistemasDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaSistemasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaSistemasDto();
		}

		return resultado;
	}

	
	// WLC
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public SolicitudRegistrarSistemasDto recuperarDatosParaActualizarSolicitudCertificacion(
			SolicitudRegistrarSistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		SolicitudRegistrarSistemasDto resultado = new SolicitudRegistrarSistemasDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/recuperarDatosParaActualizarSolicitudCertificacion";
			TypeReference<SolicitudRegistrarSistemasDto> typeRef = new TypeReference<SolicitudRegistrarSistemasDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new SolicitudRegistrarSistemasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new SolicitudRegistrarSistemasDto();
		}

		return resultado;
	}

	

	// WLC
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaEstaRegistradoGenericoDto verificacionSistemaRegistrado(
			SolicitudVerificacionSistemaRegistradoDto pSolicitud) {

		LOG.info("Entrando a verificacionSistemaRegistrado pSolicitud={}", pSolicitud);

		configFacturacionCertificacionRest();
		RespuestaEstaRegistradoGenericoDto resultado = new RespuestaEstaRegistradoGenericoDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/verificacionSistemaRegistrado";
			TypeReference<RespuestaEstaRegistradoGenericoDto> typeRef = new TypeReference<RespuestaEstaRegistradoGenericoDto>() {
			};

			Response response = ejecutarPostConParametroMapper(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaEstaRegistradoGenericoDto();
				resultado.setMensajes(ErrorComunicacion("Error de Comunicación, en la verificación de Sistemas."));
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
			System.out.println("expcetion: " + e);
			resultado = new RespuestaEstaRegistradoGenericoDto();
			resultado.setMensajes(ErrorComunicacion("Error de Comunicación, en la verificación de Sistemas."));
		}

		LOG.info("Saliendo verificacionSistemaRegistrado pSolicitud={}", pSolicitud);

		return resultado;
	}



	/**
	 * Objetivo: Registrar Aprobación o Rechazo Certificación. Creado por: Wilson
	 * Limachi. Fecha: 30/04/2018
	 */
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RegistroAutorizacionRechazoCertificacionDto registrarAprobacionRechazoCertificacion(
			RegistroAutorizacionRechazoCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RegistroAutorizacionRechazoCertificacionDto resultado = new RegistroAutorizacionRechazoCertificacionDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/registrarAprobacionRechazoCertificacion";
			TypeReference<RegistroAutorizacionRechazoCertificacionDto> typeRef = new TypeReference<RegistroAutorizacionRechazoCertificacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RegistroAutorizacionRechazoCertificacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RegistroAutorizacionRechazoCertificacionDto();
		}
		return resultado;
	}

	




	

//	/**
//	 * Recuperar Reporte Cabecera y Detalle de Solicitud de certificacion
//	 * 
//	 * @author: Wilson Limachi
//	 * @Fecha: 28/06/20118
//	 * @param pSolicitud,objeto de tipo solicitud
//	 * @return Devuelve objeto respuesta Dto solicitud de Certificacion.
//	 */
//	// IASC - Cambio en ruta de acceso - 11/10/2018
//	public ReporteSolicitudCertificacionDto recuperarReporteSolicitudCertificacion(
//			ReporteSolicitudCertificacionDto pSolicitud) {
//		configFacturacionCertificacionRest();
//		ReporteSolicitudCertificacionDto resultado = new ReporteSolicitudCertificacionDto();
//		try {
//			String json = "";
//			ObjectMapper mapper = new ObjectMapper();
//			json = mapper.writeValueAsString(pSolicitud);
//			String ruta = host + "/certificaciones/recuperarReporteSolicitudCertificacion";
//			TypeReference<ReporteSolicitudCertificacionDto> typeRef = new TypeReference<ReporteSolicitudCertificacionDto>() {
//			};
//			Response response = ejecutarPostConParametroMapperToken(ruta, json);
//			if (response.code() == StatusCodes.NOT_FOUND) {
//				resultado = new ReporteSolicitudCertificacionDto();
//			} else {
//				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
//			}
//		} catch (Exception e) {
//			resultado = new ReporteSolicitudCertificacionDto();
//		}
//		return resultado;
//	}

//	/**
//	 * Recuperar Grilla de Pruebas por Funcionario
//	 * 
//	 * @author: Wilson Limachi
//	 * @Fecha: 29/06/2018
//	 * @param pSolicitud, objeto de tipo solicitud
//	 * @return Devuelve objeto respuesta Dto de tipo Respuesta Pruebas Sistemas de
//	 *         Certificacion.
//	 */
//	// IASC - Cambio en ruta de acceso - 11/10/2018
//	public RespuestaSistemasDeCertificacionFuncionarioDto recuperarListaPruebasSistemasDeCertificacionFuncionario(
//			SolicitudPruebasSistemasDeCertificacionDto pSolicitud) {
//		configFacturacionCertificacionRest();
//		RespuestaSistemasDeCertificacionFuncionarioDto resultado = new RespuestaSistemasDeCertificacionFuncionarioDto();
//		try {
//			String json = "";
//			ObjectMapper mapper = new ObjectMapper();
//			json = mapper.writeValueAsString(pSolicitud);
//			String ruta = host + "/certificaciones/recuperarListaPruebasSistemasDeCertificacionFuncionario";
//			TypeReference<RespuestaSistemasDeCertificacionFuncionarioDto> typeRef = new TypeReference<RespuestaSistemasDeCertificacionFuncionarioDto>() {
//			};
//			Response response = ejecutarPostConParametroMapperToken(ruta, json);
//			if (response.code() == StatusCodes.NOT_FOUND) {
//				resultado = new RespuestaSistemasDeCertificacionFuncionarioDto();
//			} else {
//				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
//			}
//		} catch (Exception e) {
//			resultado = new RespuestaSistemasDeCertificacionFuncionarioDto();
//		}
//		return resultado;
//	}

	
	

	

	

	/**
	 * Recuperar Lista de Sistemas
	 * 
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 08/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo SolicitudImprentaDto.
	 */
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public ObtenerListSistemaDto recuperaSistemas(ObtenerListSistemaDto pSolicitud) {
		configFacturacionCertificacionRest();
		ObtenerListSistemaDto resultado = new ObtenerListSistemaDto();
		// Mock Stup TODO: Quitar luego de tener el servicio REST funcionando
		SistemasDto sistemasDto = new SistemasDto();
		List<SistemasDto> lsistemasDto = new ArrayList<>();
		sistemasDto.setDescripcionModalidadFacturacionId("facturacion");
		sistemasDto.setNombreSistema("contrabando en frontera");
		sistemasDto.setVersion("0.0.1");
		sistemasDto.setTipoSistemaId(1);
		sistemasDto.setSistemaId(2787L);
		lsistemasDto.add(sistemasDto);
		sistemasDto = new SistemasDto();
		sistemasDto.setDescripcionModalidadFacturacionId("electronica");
		sistemasDto.setNombreSistema("administracion de bienes");
		sistemasDto.setVersion("0.0.1");
		sistemasDto.setSistemaId(3721L);
		sistemasDto.setTipoSistemaId(2);
		lsistemasDto.add(sistemasDto);
		resultado.setListaSistemas(lsistemasDto);
		resultado.setOk(true);
		return resultado;
		// Mock Stup
//		try {
//
//			String json = "";
//			ObjectMapper mapper = new ObjectMapper();
//			json = mapper.writeValueAsString(pSolicitud);
//			String ruta = host + "/certificaciones/recuperaSistemas";
//			TypeReference<ObtenerListSistemaDto> typeRef = new TypeReference<ObtenerListSistemaDto>() {
//			};
//			Response response = ejecutarPostConParametroMapperToken(ruta, json);
//			if (response.code() == StatusCodes.NOT_FOUND) {
//				resultado = new ObtenerListSistemaDto();
//			} else {
//				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
//			}
//		} catch (Exception e) {
//			resultado = new ObtenerListSistemaDto();
//		}
//		
//		return resultado;
	}

	/**
	 * @author rosario.garcia
	 * @param pSolicitud
	 * @return las solicitudes de certificacion con estado en proceso
	 * @fecha 23/11/2018
	 */
	public ObtenerListSistemaDto recuperaSistemasContribuyente(ObtenerListSistemaDto pSolicitud) {
		configFacturacionCertificacionRest();
		System.out.println("re " + pSolicitud.getPersonaContribuyenteId());
		ObtenerListSistemaDto resultado = new ObtenerListSistemaDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtenerSistemasEstadoEnProceso";
			TypeReference<ObtenerListSistemaDto> typeRef = new TypeReference<ObtenerListSistemaDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ObtenerListSistemaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ObtenerListSistemaDto();
		}
		return resultado;
	}

	/**
	 * @author rosario.garcia
	 * @param pSolicitud
	 * @return los datos para el reporte de componentes y archivos registrados
	 * @fecha 03/11/2018
	 */
	public ReporteComponentesRegistradosCtbteDto obtenerComponentesRegistradosContribuyente(SistemasDto pSolicitud) {
		configFacturacionCertificacionRest();

		ReporteComponentesRegistradosCtbteDto resultado = new ReporteComponentesRegistradosCtbteDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtenerListaComponentesRegistradosPorContribuyente";
			TypeReference<ReporteComponentesRegistradosCtbteDto> typeRef = new TypeReference<ReporteComponentesRegistradosCtbteDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ReporteComponentesRegistradosCtbteDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ReporteComponentesRegistradosCtbteDto();
		}
		return resultado;
	}
	
	/**
	 * @author wilson.limachi
	 * @param pSolicitud
	 * @return los datos para el reporte de componentes y archivos registrados
	 * @fecha 03/11/2018
	 */
	public ReporteComponentesRegistradosCtbteDto obtenerListaComponentesRegistradosPorContribuyenteSistema(SistemasDto pSolicitud) 
	{
		configFacturacionCertificacionRest();

		ReporteComponentesRegistradosCtbteDto resultado = new ReporteComponentesRegistradosCtbteDto();
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtenerListaComponentesRegistradosPorContribuyenteSistema";
			TypeReference<ReporteComponentesRegistradosCtbteDto> typeRef = new TypeReference<ReporteComponentesRegistradosCtbteDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new ReporteComponentesRegistradosCtbteDto();
			} 
			else 
			{
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			resultado = new ReporteComponentesRegistradosCtbteDto();
		}
		
		return resultado;
	}
	
	/**
	 * Para verificar si tiene registro de huella de sistema
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 21/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto respuesta RespuestaListaDatosSistemaContribuyenteDto
	 */
	// IASC - Cambio en ruta de acceso - 11/10/2018
	public RespuestaActualizacionDto verificaRegistroHuella(SolicitudRegistroProveedorDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionDto vResultado = new RespuestaActualizacionDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/verificarRegistroHuella";
			TypeReference<RespuestaActualizacionDto> typeRef = new TypeReference<RespuestaActualizacionDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespuestaActualizacionDto();
			} else {
				vResultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new RespuestaActualizacionDto();
		}

		return vResultado;
	}


//	// IASC - Cambio en ruta de acceso - 11/10/2018
//	public SolicitudResultadoInspeccionDto cambioEstadoRegistroSolicitudCertificacion(
//			SolicitudResultadoInspeccionDto pPrimaria) {
//		configFacturacionCertificacionRest();
//		SolicitudResultadoInspeccionDto resultado = new SolicitudResultadoInspeccionDto();
//
//		// Mock Stup TODO: Quitar luego de tener el servicio REST funcionando
//		StrMensajeAplicacionDto MensajeAplicacionDto = new StrMensajeAplicacionDto();
//		MensajeAplicacionDto.setDescripcion("Registro realizado exitosamente");
//		MensajeAplicacionDto.setDescripcionUi("Registro realizado exitosamente");
//		MensajeAplicacionDto.setCodigo(2010);
//		MensajeAplicacionDto.setEstadoId("AC");
//		resultado.addMensaje(MensajeAplicacionDto);
//		resultado.setOk(true);
//		return resultado;
//		// Mock Stup TODO: Quitar luego de tener el servicio REST funcionando
//
////		try {
////			String json = "";
////			ObjectMapper mapper = new ObjectMapper();
////			json = mapper.writeValueAsString(pPrimaria);
////			String ruta = host + "/certificaciones/cambioEstadoRegistroSolicitudCertificacion";
////			TypeReference<SolicitudResultadoInspeccionDto> typeRef = new TypeReference<SolicitudResultadoInspeccionDto>() {
////			};
////
////			Response response = ejecutarPostConParametroMapperToken(ruta, json);
////
////			if (response.code() == StatusCodes.NOT_FOUND) {
////				resultado = new SolicitudResultadoInspeccionDto();
////			} else {
////				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
////			}
////		} catch (Exception e) {
////			resultado = new SolicitudResultadoInspeccionDto();
////	
////		}
////
////		return resultado;
//	}

	
	

//	/**
//	 * Objetivo: habilita la verificación in situ de la certificacion y la solicitud
//	 * de aprobacion de la certificacion Creado por: Wilson Limahci. Fecha:
//	 * 20/09/2018
//	 */
//	// IASC - Cambio en ruta de acceso - 11/10/2018
//	public RespuestaActualizacionDto habilitarEstadoCertificacion(SolicitudResultadoInspeccionDto pSolicitud) {
//		configFacturacionCertificacionRest();
//		RespuestaActualizacionDto resultado = new RespuestaActualizacionDto();
//
//		try {
//			String json = "";
//			ObjectMapper mapper = new ObjectMapper();
//			json = mapper.writeValueAsString(pSolicitud);
//			String ruta = host + "/certificaciones/habilitarEstadoCertificacion";
//			TypeReference<RespuestaActualizacionDto> typeRef = new TypeReference<RespuestaActualizacionDto>() {
//			};
//
//			Response response = ejecutarPostConParametroMapperToken(ruta, json);
//
//			if (response.code() == StatusCodes.NOT_FOUND) {
//				resultado = new RespuestaActualizacionDto();
//			} else {
//				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
//			}
//		} catch (Exception e) {
//			resultado = new RespuestaActualizacionDto();
//		}
//
//		return resultado;
//	}

	

	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO, para
	 * Cancelacion de Solicitud de Certificacion
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 21/08/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto respuesta Dto de tipo Respuesta Pruebas Sistemas de
	 *         Certificacion
	 */
	public RecuperarListaSolicitudCertificacion recuperaSolicitudesCertificacionParaCancelar(
			SolicitudSolicitudCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RecuperarListaSolicitudCertificacion resultado = new RecuperarListaSolicitudCertificacion();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/recuperaSolicitudesCertificacionParaCancelar";
			TypeReference<RecuperarListaSolicitudCertificacion> typeRef = new TypeReference<RecuperarListaSolicitudCertificacion>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RecuperarListaSolicitudCertificacion();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RecuperarListaSolicitudCertificacion();
		}
		return resultado;
	}

	

	/**
	 * Para cancelar la solicitud de certificacion de solicitud
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 24/08/2018 Carmen Rosa Silva Paco 21/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto respuesta Dto de tipo Respuesta Pruebas Sistemas de
	 *         Certificacion.
	 */
	public RespuestaActualizacionGenericoDto cancelarSolicitudCertificacion(
			SolicitudSolicitudCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionGenericoDto resultado = new RespuestaActualizacionGenericoDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/cancelarSolicitudCertificacion";
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionGenericoDto();
		}
		return resultado;
	}

	
	
	

	/**
	 * @modificacion: Consumo del servicio Encriptación de huellas digitales de
	 *                sistema
	 * @author: Peter Flores.
	 * @fecha: 14/11/2018
	 */
	public RegistrarHuellaSistemaDto ObtenerCifradoHuellaDigital(GenerarHuellaSistemaDto pSolicitud) {
		configFacturacionCertificacionRest();
		RegistrarHuellaSistemaDto resultado = new RegistrarHuellaSistemaDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/obtenerCifradoHuellaDigital";
			TypeReference<RegistrarHuellaSistemaDto> typeRef = new TypeReference<RegistrarHuellaSistemaDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RegistrarHuellaSistemaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RegistrarHuellaSistemaDto();
		}
		return resultado;
	}

	/**
	 * @modificacion: Consumo del servicio Encriptación de huellas digitales de
	 *                sistema
	 * @author: Peter Flores.
	 * @fecha: 16/11/2018
	 */
	public RegistroHuellasDigitalesDto RegistrarHuellasDigitalesSistemasFinal(
			ListaRegistroHuellasDigitalesDto pSolicitud) {
		configFacturacionCertificacionRest();
		RegistroHuellasDigitalesDto resultado = new RegistroHuellasDigitalesDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/registrarHuellaDigitalSistema";
			TypeReference<RegistroHuellasDigitalesDto> typeRef = new TypeReference<RegistroHuellasDigitalesDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RegistroHuellasDigitalesDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RegistroHuellasDigitalesDto();
		}
		return resultado;
	}

	/**
	 * @modificacion: Consumo del servicio generar cite (STUB)
	 * @author: Carmen Rosa Silva
	 * @fecha: 21/11/2018
	 */
	public StubCancelacionSolicitudDto generarCite(StubCancelacionSolicitudDto pSolicitud) {
		configFacturacionCertificacionRest();
		StubCancelacionSolicitudDto resultado = new StubCancelacionSolicitudDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/generarCite";
			TypeReference<StubCancelacionSolicitudDto> typeRef = new TypeReference<StubCancelacionSolicitudDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new StubCancelacionSolicitudDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new StubCancelacionSolicitudDto();
		}
		return resultado;
	}

	public ListaRegistroHuellasDigitalesDto obtieneRegistroHuellasDigitales(Long pSolicitud) {
		configFacturacionCertificacionRest();
		ListaRegistroHuellasDigitalesDto resultado;

		try {
			String ruta = host + "/certificacion/obtieneRegistroHuellasDigitales";
			Map<String, String> map = new HashMap<>();
			map.put("pSolicitud", pSolicitud + "");

			TypeReference<ListaRegistroHuellasDigitalesDto> typeRef = new TypeReference<ListaRegistroHuellasDigitalesDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaRegistroHuellasDigitalesDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaRegistroHuellasDigitalesDto();
		}

		return resultado;
	}

	/**
	 * @descripcion Consumo del servicio rest de obtencion de lista de Pruebas
	 *              Automaticas de un Sistema
	 * @param pSolicitud (objeto de SolicitudPruebasSistemasDto)
	 * @return Lista RespuestaListaRegistroPruebasAutomaticasDto
	 * @author hugo.quinonez
	 * @fecha 26/11/2018
	 * @modificacion
	 */
	public RespuestaListaRegistroPruebasAutomaticasDto obtenerDatosPruebasAutomaticasSistema(
			SolicitudPruebasSistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaListaRegistroPruebasAutomaticasDto resultado = new RespuestaListaRegistroPruebasAutomaticasDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtienePruebasAutomaticasSistema";
			TypeReference<RespuestaListaRegistroPruebasAutomaticasDto> typeRef = new TypeReference<RespuestaListaRegistroPruebasAutomaticasDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaListaRegistroPruebasAutomaticasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaListaRegistroPruebasAutomaticasDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @descripcion Consumo del servicio rest de obtencion de lista de
	 *              verificaciones in situ sus Observaciones y estados
	 * @param pSolicitud (objeto de RegistrosObservacionesInsituDto)
	 * @return Lista RespuestaRegistrosObservacionesInsituDto
	 * @author hugo.quinonez
	 * @fecha 29/11/2018
	 * @modificacion
	 */
	public RespuestaRegistrosObservacionesInsituDto obtenerListaVerificacionesInsitu(
			RegistrosObservacionesInsituDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaRegistrosObservacionesInsituDto resultado = new RespuestaRegistrosObservacionesInsituDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtieneListadoVerificacionInsitu ";
			TypeReference<RespuestaRegistrosObservacionesInsituDto> typeRef = new TypeReference<RespuestaRegistrosObservacionesInsituDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaRegistrosObservacionesInsituDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaRegistrosObservacionesInsituDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @modificacion: Consumo del servicio rest de Pruebas Manuales de un Sistema
	 * @author: john.paz
	 * @fecha: 27/11/2018 RespuestaListaRegistroPruebasManualesDto
	 */
	public RespuestaListaRegistroPruebasManualesDto obtenerDatosPruebasManualesSistema(
			SolicitudPruebasSistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaListaRegistroPruebasManualesDto resultado = new RespuestaListaRegistroPruebasManualesDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtienePruebasManualesSistema";
			TypeReference<RespuestaListaRegistroPruebasManualesDto> typeRef = new TypeReference<RespuestaListaRegistroPruebasManualesDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaListaRegistroPruebasManualesDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaListaRegistroPruebasManualesDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @modificacion: Consumo del servicio rest de verificacion fin de pruebas
	 * @author: sergio.ichaso
	 * @fecha: 27/11/2018
	 */
	public RespuestaVerificacionPruebasConcluidasDto verificarPruebasFinalizadas(
			SolicitudPruebasSistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaVerificacionPruebasConcluidasDto resultado = new RespuestaVerificacionPruebasConcluidasDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/verificarPruebasFinalizadas";
			TypeReference<RespuestaVerificacionPruebasConcluidasDto> typeRef = new TypeReference<RespuestaVerificacionPruebasConcluidasDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaVerificacionPruebasConcluidasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaVerificacionPruebasConcluidasDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @modificacion: Recupera los datos para el reporte de solicitud de
	 *                certificacion
	 * @author: wilson.limachi
	 * @fecha: 28/11/2018
	 */
	public ReporteDatosSolicitudCertificacionDto recuperarDatosReporteSolicitudCertificacion(
			RespuestaDatosSistemasSolCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		ReporteDatosSolicitudCertificacionDto resultado = new ReporteDatosSolicitudCertificacionDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/recuperarDatosReporteSolicitudCertificacion";
			TypeReference<ReporteDatosSolicitudCertificacionDto> typeRef = new TypeReference<ReporteDatosSolicitudCertificacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ReporteDatosSolicitudCertificacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new ReporteDatosSolicitudCertificacionDto();
		}

		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @modificacion: Recupera los datos del sistema para el componente de datos de
	 *                sistema
	 * @author: wilson.limachi
	 * @fecha: 28/11/2018
	 */
	public RespuestaDatosSistemasSolCertificacionDto recuperarDatosComponenteSistema(
			RespuestaDatosSistemasSolCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaDatosSistemasSolCertificacionDto resultado = new RespuestaDatosSistemasSolCertificacionDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/recuperarDatosComponenteSistema";
			TypeReference<RespuestaDatosSistemasSolCertificacionDto> typeRef = new TypeReference<RespuestaDatosSistemasSolCertificacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaDatosSistemasSolCertificacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaDatosSistemasSolCertificacionDto();
		}

		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @descripcion Consumo del servicio rest de obtencion de Componentes y sus
	 *              Observaciones
	 * @param pSolicitud (objeto de RegistroObservacionesComponentesInsituDto)
	 * @return Lista RegistroObservacionesComponentesInsituListaDto
	 * @author hugo.quinonez
	 * @fecha 28/11/2018
	 * @modificacion
	 */
	public RegistroObservacionesComponentesInsituListaDto obtenerDatosInicialesObservacionesCompSistema(
			RegistroObservacionesComponentesInsituDto pSolicitud) {
		configFacturacionCertificacionRest();
		RegistroObservacionesComponentesInsituListaDto resultado = new RegistroObservacionesComponentesInsituListaDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitud.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/registrarDatosInicioObservaciones";
			TypeReference<RegistroObservacionesComponentesInsituListaDto> typeRef = new TypeReference<RegistroObservacionesComponentesInsituListaDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RegistroObservacionesComponentesInsituListaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RegistroObservacionesComponentesInsituListaDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * Consumo de servicio REST para obtener los datos del sistema
	 * 
	 * @author rosario.garcia
	 * @param pSolicitud
	 * @return SistemasDto
	 */
	public SistemasDto cargarDatosSistemasHuellaContribuyente(SistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		SistemasDto resultado = new SistemasDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtenerDatosSistemaPorSistemaId";
			TypeReference<SistemasDto> typeRef = new TypeReference<SistemasDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new SistemasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new SistemasDto();
		}
		System.out.println("datos " + resultado);
		return resultado;
	}

	/**
	 * @ Consumo del servicio rest de obtencion de lista de verificaciones in situ
	 * sus Observaciones y estados
	 * 
	 * @author:
	 * @fecha: 29/11/2018
	 * @modificacion:
	 */
	public RespuestaActualizacionGenericoDto modificarObservacionEstadoPruebaManual(
			RegistrosPruebasManualesDto pRegistroPruebaManual) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionGenericoDto resultado = new RespuestaActualizacionGenericoDto();

		System.out.println("FRONTEND|| pSolicitud : " + pRegistroPruebaManual.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pRegistroPruebaManual);
			String ruta = host + "/certificaciones/guardarCambiosPruebaManual";
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaActualizacionGenericoDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @descripcion Consumo del servicio rest de @Registro de nueva observacion a un
	 *              Componente de Sistema solicitado
	 * @param pSolicitudRegistroObservaciones (objeto de
	 *                                        RegistroObservacionesComponentesInsituDto)
	 * @return objeto RegistroObservacionesComponentesInsituDto
	 * @author hugo.quinonez
	 * @fecha 29/11/2018
	 * @modificacion hugo.quinonez 4/12/2018
	 */
	public RegistroObservacionesComponentesInsituDto registroNuevaObservacionComponentesInsitu(
			RegistroObservacionesComponentesInsituDto pSolicitudRegistroObservaciones) {
		configFacturacionCertificacionRest();
		RegistroObservacionesComponentesInsituDto resultado = new RegistroObservacionesComponentesInsituDto();

		System.out.println("FRONTEND|| pSolicitud : " + pSolicitudRegistroObservaciones.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitudRegistroObservaciones);
			String ruta = host + "/certificaciones/modificarObservacionInSitu";
			TypeReference<RegistroObservacionesComponentesInsituDto> typeRef = new TypeReference<RegistroObservacionesComponentesInsituDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RegistroObservacionesComponentesInsituDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RegistroObservacionesComponentesInsituDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @ Consumo del servicio rest de obtencion de certificado de aprobación
	 * 
	 * @author: freddy.yuca
	 * @fecha: 30/11/2018
	 * @modificacion:
	 */
	public RespuestaCertificadoAprobacionDto obtenerCertificadoDeAprobacion(
			SolicitudCertificadoAprobacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaCertificadoAprobacionDto resultado = new RespuestaCertificadoAprobacionDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtenerCertificadoDeAprobacion";
			TypeReference<RespuestaCertificadoAprobacionDto> typeRef = new TypeReference<RespuestaCertificadoAprobacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaCertificadoAprobacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaCertificadoAprobacionDto();
		}
		return resultado;
	}

	/**
	 * Consumo del servicio REST para registrar los archivos y huellas del sistema
	 * 
	 * @author rosario.garcia
	 * @param pSolicitud de tipo ListaRegistroHuellasDigitalesDto
	 * @return RegistroHuellasDigitalesDto
	 * @fecha 01/12/2018
	 */
	public RegistroHuellasDigitalesDto RegistrarHuellasDigitalesSistemasContribuyente(
			ListaRegistroHuellasDigitalesDto pSolicitud) {
		configFacturacionCertificacionRest();
		RegistroHuellasDigitalesDto resultado = new RegistroHuellasDigitalesDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/registrarHuellaDigitalSistemaContribuyente";
			TypeReference<RegistroHuellasDigitalesDto> typeRef = new TypeReference<RegistroHuellasDigitalesDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RegistroHuellasDigitalesDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RegistroHuellasDigitalesDto();
		}
		return resultado;
	}

	/**
	 * Consumo del servicio REST para obtener los datos del tramite de inicio de
	 * certificación
	 * 
	 * @author Freddy Yuca
	 * @param pTramiteId código único del trámite
	 * @return DatosTramiteSolCertificacionDto
	 * @fecha 15/08/2019
	 */
	public DatosTramiteSolCertificacionDto obtenerDatosTramiteCertificacion(Long pTramiteId) {
		configFacturacionCertificacionRest();
		DatosTramiteSolCertificacionDto resultado = new DatosTramiteSolCertificacionDto();

		try {
			String ruta = host + "/certificaciones/recuperarDatosTramite/";
			TypeReference<DatosTramiteSolCertificacionDto> typeRef = new TypeReference<DatosTramiteSolCertificacionDto>() {
			};
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("pTramiteId", pTramiteId.toString());

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new DatosTramiteSolCertificacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new DatosTramiteSolCertificacionDto();
		}

		return resultado;
	}

	/**
	 * Recupera los datos de los contacos de la solicitud.
	 * 
	 * @author Wilson Limachi
	 * @param pSolicitudCertificacionId código único de certificación
	 * @return RespuestaDatosTramiteSolCertificacionDto
	 * @fecha 05/12/2018
	 */
	public RecuperarListaContactosCertificacion obtenerContactosCertificacion(String pSolicitudCertificacionId) {
		configFacturacionCertificacionRest();
		RecuperarListaContactosCertificacion resultado = new RecuperarListaContactosCertificacion();

		try {
			String ruta = host + "/certificaciones/recuperarContactosCertificacion/";
			TypeReference<RecuperarListaContactosCertificacion> typeRef = new TypeReference<RecuperarListaContactosCertificacion>() {
			};
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("pSolicitudCertificacionId", pSolicitudCertificacionId);

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RecuperarListaContactosCertificacion();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RecuperarListaContactosCertificacion();
		}

		return resultado;
	}

	/**
	 * @descripcion Consumo del servicio rest de @Registro de nueva observacion a
	 *              Verificacion In Situ de Sistema solicitado
	 * @param pRegistroObs (objeto de RegistrosObservacionesInsituDto)
	 * @return objeto RespuestaRegistrosObservacionesInsituDto
	 * @author hugo.quinonez
	 * @fecha 07/12/2018
	 * @modificacion
	 */
	public RespuestaRegistrosObservacionesInsituDto registroNuevaObservacionVerificacionInsitu(
			RegistrosObservacionesInsituDto pRegistroObs) {
		configFacturacionCertificacionRest();
		RespuestaRegistrosObservacionesInsituDto resultado = new RespuestaRegistrosObservacionesInsituDto();

		System.out.println("FRONTEND|| pSolicitud : " + pRegistroObs.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pRegistroObs);
			String ruta = host + "/certificaciones/registraObservacionVerificacionInsitu";
			TypeReference<RespuestaRegistrosObservacionesInsituDto> typeRef = new TypeReference<RespuestaRegistrosObservacionesInsituDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaRegistrosObservacionesInsituDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaRegistrosObservacionesInsituDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @descripcion registro equipo de certificacion
	 * @param pAsignaciones (objeto de AsignacionesCertificacionesListaDto)
	 * @return objeto RespuestaEstaRegistradoGenericoDto
	 * @author sergio.ichaso
	 * @fecha 07/12/2018
	 * @modificacion
	 */
	public RespuestaEstaRegistradoGenericoDto registrarEquipoCertificacion(
			AsignacionesCertificacionesListaDto pAsignaciones) {
		configFacturacionCertificacionRest();
		RespuestaEstaRegistradoGenericoDto resultado = new RespuestaEstaRegistradoGenericoDto();

		System.out.println("FRONTEND|| pEquipoAsignacion : " + pAsignaciones.toString());
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pAsignaciones);
			String ruta = host + "/certificaciones/registrarEquipoCertificacion";
			TypeReference<RespuestaEstaRegistradoGenericoDto> typeRef = new TypeReference<RespuestaEstaRegistradoGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaEstaRegistradoGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaEstaRegistradoGenericoDto();
		}
		System.out.println("Respuesta FrontEnd : " + resultado.toString());
		return resultado;
	}

	/**
	 * @descripcion Recupera el equipo de certificacion por tramite
	 * @param pAsignaciones (objeto de AsignacionesCertificacionesListaDto)
	 * @return objeto RespuestaEstaRegistradoGenericoDto
	 * @author sergio.ichaso
	 * @fecha 07/12/2018
	 * @modificacion
	 */
	public AsignacionesCertificacionesListaDto obtenerEquipoCertificacionPorTramite(Long pTramite) {
		configFacturacionCertificacionRest();
		AsignacionesCertificacionesListaDto resultado = new AsignacionesCertificacionesListaDto();

		try {			
			String ruta = host + "/certificaciones/obtenerEquipoCertificacionPorTramite/";
			TypeReference<AsignacionesCertificacionesListaDto> typeRef = new TypeReference<AsignacionesCertificacionesListaDto>() {
			};
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("pTramite", pTramite.toString());

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new AsignacionesCertificacionesListaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new AsignacionesCertificacionesListaDto();
		}

		return resultado;
	}

	/**
	 * @descripcion Recupera el equipo de certificacion por tramite
	 * @param pAsignaciones (objeto de AsignacionesCertificacionesListaDto)
	 * @return objeto RespuestaEstaRegistradoGenericoDto
	 * @author sergio.ichaso
	 * @fecha 07/12/2018
	 * @modificacion
	 */
	public RespuestaEstaRegistradoGenericoDto verificarContribuyenteRequiereInSitu(Long pNit) {
		configFacturacionCertificacionRest();
		RespuestaEstaRegistradoGenericoDto resultado = new RespuestaEstaRegistradoGenericoDto();

		try {
			String ruta = host + "/certificaciones/verificarContribuyenteRequiereInSitu/";
			TypeReference<RespuestaEstaRegistradoGenericoDto> typeRef = new TypeReference<RespuestaEstaRegistradoGenericoDto>() {
			};
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("pNit", pNit.toString());

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaEstaRegistradoGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaEstaRegistradoGenericoDto();
		}

		return resultado;
	}

	/**
	 * @descripcion Recupera el equipo de certificacion por tramite
	 * @param pAsignaciones (objeto de AsignacionesCertificacionesListaDto)
	 * @return objeto RespuestaEstaRegistradoGenericoDto
	 * @author sergio.ichaso
	 * @fecha 07/12/2018
	 * @modificacion
	 */
	public RespuestaEstaRegistradoGenericoDto verificarPruebasManualesTerminadas(
			SolicitudPruebasSistemasDto pDatosSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaEstaRegistradoGenericoDto resultado = new RespuestaEstaRegistradoGenericoDto();

		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pDatosSolicitud);
			String ruta = host + "/certificaciones/verificarPruebasManualesTerminadas";
			TypeReference<RespuestaEstaRegistradoGenericoDto> typeRef = new TypeReference<RespuestaEstaRegistradoGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaEstaRegistradoGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			System.out.println("F: " + e.toString());
			resultado = new RespuestaEstaRegistradoGenericoDto();
		}

		return resultado;
	}

	/**
	 * @descripcion Verifica si verificacion en situ esta aprobada
	 * @param pSolicitudCertificacion codigo de solicitud de cerficicacion
	 * @return objeto RespuestaEstaRegistradoGenericoDto
	 * @author sergio.ichaso
	 * @fecha 07/12/2018
	 * @modificacion
	 */
	public RespuestaEstaRegistradoGenericoDto verificarObservacionComponenteInSituAprobadas(
			Long pSolicitudCertificacion) {
		configFacturacionCertificacionRest();
		RespuestaEstaRegistradoGenericoDto resultado = new RespuestaEstaRegistradoGenericoDto();

		try {
			
			String ruta = host + "/certificaciones/verificarObservacionComponenteInSituAprobadas/";
			TypeReference<RespuestaEstaRegistradoGenericoDto> typeRef = new TypeReference<RespuestaEstaRegistradoGenericoDto>() {
			};
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("pTramite", pSolicitudCertificacion.toString());

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaEstaRegistradoGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaEstaRegistradoGenericoDto();
		}

		return resultado;
	}

	/**
	 * @descripcion devuelve un dto
	 * @param
	 * @return objeto ObtenerListaSistemasCertificacionDto
	 * @author Noemi Mamani
	 * @fecha 14/12/2018
	 * @modificacion
	 * @Abel Ludeño parametro
	 */
	public ObtenerListaSistemasCertificacionDto recuperaListaSistemasCertificacionGeneral() {
		configFacturacionCertificacionRest();
		ObtenerListaSistemasCertificacionDto resultado = new ObtenerListaSistemasCertificacionDto();

		try {

			String ruta = host + "/certificacion1/listadoSistemasCertificados";
			TypeReference<ObtenerListaSistemasCertificacionDto> typeRef = new TypeReference<ObtenerListaSistemasCertificacionDto>() {
			};

			// Response response = ejecutarPostSinParametro(ruta);
			// Response response = ejecutarPostConParametroMapper(ruta, json);

			Response response = ejecutarGetSinParametros(ruta);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ObtenerListaSistemasCertificacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {

			resultado = new ObtenerListaSistemasCertificacionDto();
		}

		return resultado;
	}

	/**
	 * @ Consumo del servicio rest de obtencion de certificado de aprobación
	 * 
	 * @author: freddy.yuca
	 * @fecha: 14/12/2018
	 * @modificacion:
	 */
	public RespuestaCertificadoAprobacionDto obtenerCertificadoDeAprobacionHash(
			SolicitudCertificadoAprobacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaCertificadoAprobacionDto resultado = new RespuestaCertificadoAprobacionDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtenerCertificadoDeAprobacionHash";
			TypeReference<RespuestaCertificadoAprobacionDto> typeRef = new TypeReference<RespuestaCertificadoAprobacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaCertificadoAprobacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaCertificadoAprobacionDto();
		}
		return resultado;
	}

	/**
	 * @ Consumo del servicio rest de obtencion de certificado de aprobación
	 * 
	 * @author: freddy.yuca
	 * @fecha: 14/12/2018
	 * @modificacion:
	 */
	public RespuestaCertificadoAprobacionDto guardarCertificadoDeAprobacionFirma(
			SolicitudCertificadoAprobacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaCertificadoAprobacionDto resultado = new RespuestaCertificadoAprobacionDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/guardarCertificadoDeAprobacionFirma";
			TypeReference<RespuestaCertificadoAprobacionDto> typeRef = new TypeReference<RespuestaCertificadoAprobacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaCertificadoAprobacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaCertificadoAprobacionDto();
		}
		return resultado;
	}

	/**
	 * @ Obtener pruebas automaticas por solicitud de certificacion id
	 * 
	 * @author: segio.ichaso
	 * @fecha: 14/12/2018
	 * @modificacion:
	 */
	public RespuestaListaRegistroPruebasAutomaticasDto obtieneListaPaginadaPruebasAutomaticas(
			SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		configFacturacionCertificacionRest();
		RespuestaListaRegistroPruebasAutomaticasDto resultado = new RespuestaListaRegistroPruebasAutomaticasDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitudPruebasSistemas);
			String ruta = host + "/certificaciones/obtienePruebasAutomaticasPaginada";
			TypeReference<RespuestaListaRegistroPruebasAutomaticasDto> typeRef = new TypeReference<RespuestaListaRegistroPruebasAutomaticasDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaListaRegistroPruebasAutomaticasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaListaRegistroPruebasAutomaticasDto();
		}
		return resultado;
	}

	/**
	 * Obtiene listado total pruebas automaticas
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 17/12/2018
	 * @param SolicitudPruebasSistemasDto
	 * @return Devuelve conteo listado de pruebas automaticas*
	 */
	@PostMapping(value = "/obtieneTotalRegistrosPruebasAutomaticas")
	public RespuestaTamanioGenericoDto obtieneTotalRegistrosPruebasAutomaticas(
			SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		configFacturacionCertificacionRest();
		RespuestaTamanioGenericoDto resultado = new RespuestaTamanioGenericoDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitudPruebasSistemas);
			String ruta = host + "/certificaciones/obtieneTotalRegistrosPruebasAutomaticas";
			TypeReference<RespuestaTamanioGenericoDto> typeRef = new TypeReference<RespuestaTamanioGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaTamanioGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaTamanioGenericoDto();
		}
		return resultado;
	}

	/**
	 * Obtiene listado total pruebas automaticas
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 18/12/2018
	 * @param SolicitudPruebasSistemasDto
	 * @return Devuelve conteo listado de pruebas automaticas*
	 */
	public ListaRegistroHuellasDigitalesDto obtieneRegistroHuellasDigitalesPorSistema(Long pSistema) {
		configFacturacionCertificacionRest();
		ListaRegistroHuellasDigitalesDto resultado;

		try {
			String ruta = host + "/certificaciones/obtieneRegistroHuellasDigitalesPorSistema";
			Map<String, String> map = new HashMap<>();
			map.put("pSolicitud", pSistema + "");

			TypeReference<ListaRegistroHuellasDigitalesDto> typeRef = new TypeReference<ListaRegistroHuellasDigitalesDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaRegistroHuellasDigitalesDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaRegistroHuellasDigitalesDto();
		}

		return resultado;
	}

	/**
	 * @ Consumo del servicio rest de obtencion de certificado de aprobación
	 * 
	 * @author: freddy.yuca
	 * @fecha: 28/12/2018
	 * @modificacion:
	 */
	public RespuestaCertificadoAprobacionDto generarCertificadoDeAprobacion(
			SolicitudCertificadoAprobacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaCertificadoAprobacionDto resultado = new RespuestaCertificadoAprobacionDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/generarCertificadoDeAprobacion";
			TypeReference<RespuestaCertificadoAprobacionDto> typeRef = new TypeReference<RespuestaCertificadoAprobacionDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaCertificadoAprobacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaCertificadoAprobacionDto();
		}
		return resultado;
	}

	/**
	 * Obtiene listado total de Estados de Certificacion de Sistemas por
	 * contribuyente
	 * 
	 * @author: Noemi Mamani
	 * @Fecha: 28/12/2018
	 * @param pPersonaContribuyente
	 * @return Devuelve conteo listado de Estados de Certificacion de Sistemas*
	 */
	public RecuperaListaSistemaCertificacion recuperaListaEstadoCertificacionSistemas(long pPersonaContribuyente) {
		configFacturacionCertificacionRest();
		RecuperaListaSistemaCertificacion resultado;

		try {
			String ruta = host + "/certificaciones/recuperaListaEstadoCertificacionSistemas";
			Map<String, String> map = new HashMap<>();
			map.put("pPersonaContribuyente", pPersonaContribuyente + "");

			TypeReference<RecuperaListaSistemaCertificacion> typeRef = new TypeReference<RecuperaListaSistemaCertificacion>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RecuperaListaSistemaCertificacion();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RecuperaListaSistemaCertificacion();
		}

		return resultado;
	}

	/**
	 * Obtiene listado total de Estados de Certificacion de Sistemas por
	 * contribuyente
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 7/01/2018
	 * @return Devuelve mensaje standar de error
	 */
	public List<StrMensajeAplicacionDto> ErrorComunicacion(String pMensaje) {
		List<StrMensajeAplicacionDto> vListaError = new ArrayList<>();
		StrMensajeAplicacionDto vStrMensajeAplicacionDto = new StrMensajeAplicacionDto();
		vStrMensajeAplicacionDto.setDescripcion(pMensaje);
		vListaError.add(vStrMensajeAplicacionDto);
		return vListaError;
	}

	/**
	 * @descripcion devuelve un dto
	 * @param
	 * @return objeto ObtenerListaSistemasCertificacionDto paginado
	 * @author Freddy Yuca
	 * @fecha 21/01/2019
	 * @modificacion
	 */
	public RespuestaListadoSistemasDto obtenerListaSistemasCertificacion(SolicitudListadoSistemasDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaListadoSistemasDto resultado = new RespuestaListadoSistemasDto();
		try {
			String ruta = host + "/certificacion1/obtenerSistemasCertificacionPaginado";
			TypeReference<RespuestaListadoSistemasDto> typeRef = new TypeReference<RespuestaListadoSistemasDto>() {
			};
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(pSolicitud);
			Response response = ejecutarPostConParametroMapper(ruta, json);

			if (response.code() != StatusCodes.NOT_FOUND) {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaListadoSistemasDto();
		}

		return resultado;
	}

	/**
	 * Consumo servicio para Guardar documento firmado facsimil.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 08/02/2019
	 * @param pSolicitudCertificacionFirma, objeto de tipo
	 *        SolicitudCertificadoAprobacionDto
	 * @return Devuelve objeto RespuestaCertificadoAprobacionDto de respuesta
	 */
	public RespuestaCertificadoAprobacionDto guardarDocumentoFirmado(
			SolicitudCertificadoAprobacionDto pSolicitudCertificacionFirma) {
		configFacturacionCertificacionRest();
		RespuestaCertificadoAprobacionDto resultado = new RespuestaCertificadoAprobacionDto();

		try {
			String ruta = host + "/certificaciones/guardarDocumentoFirmado/";

			TypeReference<RespuestaCertificadoAprobacionDto> typeRef = new TypeReference<RespuestaCertificadoAprobacionDto>() {
			};
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitudCertificacionFirma);

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaCertificadoAprobacionDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaCertificadoAprobacionDto();
		}

		return resultado;
	}

	/**
	 * Consumo servicio para obtener detalle datos notificacion.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 11/02/2019
	 * @param pSolicitudCertificacion, objeto de tipo
	 *        SolicitudCertificadoAprobacionDto
	 * @return Devuelve objeto NotificacionElectronicaListaDto de respuesta
	 */
	public NotificacionElectronicaListaDto obtenerNotificacionElectronicaPorContribuyenteYActuado(
			SolicitudCertificadoAprobacionDto pSolicitudCertificacion) {
		configFacturacionCertificacionRest();
		NotificacionElectronicaListaDto resultado = new NotificacionElectronicaListaDto();

		try {
			String ruta = host + "/certificaciones/obtenerNotificacionElectronicaPorContribuyenteYActuado/";

			TypeReference<NotificacionElectronicaListaDto> typeRef = new TypeReference<NotificacionElectronicaListaDto>() {
			};
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitudCertificacion);

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new NotificacionElectronicaListaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new NotificacionElectronicaListaDto();
		}

		return resultado;
	}

	/**
	 * Recupera la lista del Estado de Certificación de Sistemas por asociados al contribuyente, 
	 * utilizando el criterio de filtro del código del sistema.
	 * 
	 * @author: junior.flores
	 * @Fecha: 03/06/2019
	 * @param pSistema: Código único de Sistema
	 * 		  pNit: Número de Identificación Tributaria
	 * @return Devuelve el listado de las etapas de la Fase I de implementación
	 */
	public ListaSeguimientoCertificacionSistemasDto recuperarListaSeguimientoCertificacionSistemas(Long pSistema, Long pNit) {
		configFacturacionCsisRestPruebasPreProduccion();
		ListaSeguimientoCertificacionSistemasDto resultado;
		try {
			String ruta = host + "/certificacion/listarSeguimientoCertificacionSistemas";
			Map<String, String> map = new HashMap<>();
			map.put("pSolicitud", pSistema + "");
			map.put("pNit", pNit + "");
			TypeReference<ListaSeguimientoCertificacionSistemasDto> typeRef = new TypeReference<ListaSeguimientoCertificacionSistemasDto>() {
			};

			Response response = ejecutarGetConParametros(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaSeguimientoCertificacionSistemasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaSeguimientoCertificacionSistemasDto();
		}

		return resultado;
	}
		
	/**
	 * @descripcion: Recupera los sistemas asociados del contribuyente
	 * @author: junior.flores.
	 * @fecha: 03/06/2019
	 * @param pSistema: Información del contribuyente
	 */
	public ListaSistemasProveedorDto consultaSistemasAsociadosContribuyente(SolicitudRegistroProveedorDto pSolicitud) {
		configFacturacionCsisRestPruebasPreProduccion();
		ListaSistemasProveedorDto resultado = new ListaSistemasProveedorDto();
		
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificacion/consultaSistemasAsociadosContribuyente";
			TypeReference<ListaSistemasProveedorDto> typeRef = new TypeReference<ListaSistemasProveedorDto>() {
			};

			Response response = ejecutarPostConParametroMapper(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaSistemasProveedorDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaSistemasProveedorDto();
		}
		
		return resultado;
	}
	
	/**
	 * Inicia o finaliza la Etapa de Certificación de Sistemas y recupera la lista del Estado de Certificación de Sistemas por asociados al contribuyente, 
	 * utilizando el criterio de filtro del código del sistema.
	 * 
	 * @author: junior.flores
	 * @Fecha: 03/06/2019
	 * @param pSistema: Código único de Sistema
	 * @return Devuelve el listado de las etapas de la Fase I de implementación
	 */
	public RespuestaActualizacionGenericoDto actualizarPruebaCertificacionSistema(SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud) {
		configFacturacionCsisRestPruebasPreProduccion();
		RespuestaActualizacionGenericoDto resultado;
		try {
			String json = "";
			String ruta = host + "/certificacion/actualizarPruebasCertificacionSistemas";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};
			
			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionGenericoDto();
		}

		return resultado;
	}
	
	/**
	 * Recupera la lista del Estado de Certificación de Sistemas por asociados al contribuyente, 
	 * utilizando el criterio de filtro del código del sistema.
	 * 
	 * @author: junior.flores
	 * @Fecha: 11/06/2019
	 * @param pSistema: Código único de Sistema
	 * @return Devuelve el listado de las etapas de la Fase I de implementación
	 */
	public RespuestaActualizacionGenericoDto reiniciaPruebaCertificacionSistema(SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud) {
		configFacturacionCsisRestPruebasPreProduccion();
		RespuestaActualizacionGenericoDto resultado;
		try {
			String json = "";
			String ruta = host + "/certificacion/reiniciarPruebasCertificacionSistemas";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};

			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionGenericoDto();
		}

		return resultado;
	}
	
	/**
	 * Recupera la lista del Detalle del Estado de Certificación de Sistemas por asociados al contribuyente, 
	 * utilizando el criterio de filtro del código del sistema.
	 * 
	 * @author: junior.flores
	 * @Fecha: 14/06/2019
	 * @param pSistema: Código único de Sistema 		  
	 * 		  pEtapaPruebaCertificacionId: Código de la Etapa de Certificación de Sistemas
	 * 		  pNit: Número de Identificación Tributaria.
	 * @return Devuelve el listado de las etapas de la Fase I de implementación (Detalle)
	 */
	public ListaDetalleSeguimientoCertificacionSistemasDto recuperarListaDetalleSeguimientoCertificacionSistemas(Long pSistema, Integer pEtapaPruebaCertificacionId, Long pNit) {
		configFacturacionCsisRestPruebasPreProduccion();
		ListaDetalleSeguimientoCertificacionSistemasDto resultado;
		try {
			String ruta = host + "/certificacion/listarDetalleSeguimientoCertificacionSistemas";
			Map<String, String> map = new HashMap<>();
			map.put("pSolicitud", pSistema + "");
			map.put("pNit", pNit + "");
			map.put("pEtapaPruebaCertificacionId", pEtapaPruebaCertificacionId + "");
			TypeReference<ListaDetalleSeguimientoCertificacionSistemasDto> typeRef = new TypeReference<ListaDetalleSeguimientoCertificacionSistemasDto>() {
			};

			Response response = ejecutarGetConParametrosToken(ruta, map);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new ListaDetalleSeguimientoCertificacionSistemasDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new ListaDetalleSeguimientoCertificacionSistemasDto();
		}

		return resultado;
	}
	
	
	/**
	 * @Descipcion:Identifica los registros LOG y CASOS DE PRUEBAS que sean iguales. Es decir, segun el codigo hash almacenado en tabla LOG y 
	 * CASOS DE PRUEBA, identifica los registro que sean iguales. En caso de ser iguales actualiza los valores de los campos: 
					estado_match=1; 
					caso_prueba_id=Identficador del caso de prueba;
	 * @author: Victor Cruz Gomez. 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return 1 - Ejecucion exitosa  0 - Ocurrio una excepcion.
	 */	
	public RespuestaMatchLogCasosPruebaEtapa2Dto matchLogCasosPruebaEtapa2(long pSistemaId, int pDocumentoSectorId)
	{
		configFacturacionCsisRestQueryPreProduccion();
		RespuestaMatchLogCasosPruebaEtapa2Dto vResultado;
		try {
			String vRuta = host + "/SeguimientoCertificacionPruebas/etapa2";
			Map<String, String> vMap = new HashMap<>();
			vMap.put("pSistemaId", pSistemaId + "");
			vMap.put("pDocumentoSectorId", pDocumentoSectorId + "");
			TypeReference<RespuestaMatchLogCasosPruebaEtapa2Dto> typeRef = new TypeReference<RespuestaMatchLogCasosPruebaEtapa2Dto>() {
			};

			Response vResponse = ejecutarGetConParametros(vRuta, vMap);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespuestaMatchLogCasosPruebaEtapa2Dto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new RespuestaMatchLogCasosPruebaEtapa2Dto();
		}

		return vResultado;
	}

	/**
	 * @Descipcion: Calcula el porcentaje de pruebas realizadas en la etapa 2 por sector.
	 * @author: Victor Cruz Gomez. 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return Porcentaje - Porcentaje de pruebas efectuadas.
	 */
	public RespuestaCalcularPorcentajePruebasEtapa2Dto calcularPorcentajePruebasEtapa2(long pSistemaId, int pDocumentoSectorId)
	{
		configFacturacionCsisRestQueryPreProduccion();
		RespuestaCalcularPorcentajePruebasEtapa2Dto vResultado;
		try {
			String vRuta = host + "/SeguimientoCertificacionPruebas/etapa2";
			Map<String, String> vMap = new HashMap<>();
			vMap.put("pSistemaId", pSistemaId + "");
			vMap.put("pDocumentoSectorId", pDocumentoSectorId + "");
			TypeReference<RespuestaCalcularPorcentajePruebasEtapa2Dto> typeRef = new TypeReference<RespuestaCalcularPorcentajePruebasEtapa2Dto>() {
			};

			Response vResponse = ejecutarGetConParametros(vRuta, vMap);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespuestaCalcularPorcentajePruebasEtapa2Dto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new RespuestaCalcularPorcentajePruebasEtapa2Dto();
		}

		return vResultado;
	}
	
	/**
	 * @Descipcion: Obtiene una lista con todos los casos de prueba de la etapa 2 efectuados por el contribuente.
	 * @author: Victor Cruz Gomez.  
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return Detalle de pruebas realizadas en la etapa 2.
	 */
	public RespuestaListaDetallePruebasEtapas2Dto obtenerDetallePruebasEtapa2(long pSistemaId)
	{
		configFacturacionCsisRestQueryPreProduccion();
		RespuestaListaDetallePruebasEtapas2Dto vResultado;
		try {
			String vRuta = host + "/SeguimientoCertificacionPruebas/etapa2";
			Map<String, String> vMap = new HashMap<>();
			vMap.put("pSistemaId", pSistemaId + "");			
			TypeReference<RespuestaListaDetallePruebasEtapas2Dto> typeRef = new TypeReference<RespuestaListaDetallePruebasEtapas2Dto>() {
			};

			Response vResponse = ejecutarGetConParametros(vRuta, vMap);

			if (vResponse.code() == StatusCodes.NO_CONTENT) {
				vResultado = new RespuestaListaDetallePruebasEtapas2Dto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new RespuestaListaDetallePruebasEtapas2Dto();
		}

		return vResultado;
	}
			
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificación de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Información necesaria para actualizar la etapa de prueba
	* @return Devuelve un valor booleano que indica la transacción
	*/
	public RespuestaActualizacionGenericoDto registroPruebaDetalleOpcional(SeguimientoDetalleCertificacionSistemasDto pSolicitud) {
		System.out.println("Ingreso al registro");
		configFacturacionCsisRestPruebasPreProduccion();
		RespuestaActualizacionGenericoDto resultado;
		try {
			String json = "";
			String ruta = host + "/certificacion/registroPruebaDetalleOpcional";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};
			
			Response response = ejecutarPostConParametroMapperToken(ruta, json);

			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionGenericoDto();
		}

		return resultado;
	}
	
	/**
	 * @descripcion Obtiene los casos de prueba sugeridos para la etapa 0 
	 * @author rosario.garcia
	 * @fecha 10/07/2019
	 */	
	public ListaDetalleCasosDePruebaEtapa0Dto obtenerCasosPruebaSugeridasEtapa0(long pSistemaId)
	{
		configFacturacionCsisRestPruebasPreProduccion();
		ListaDetalleCasosDePruebaEtapa0Dto vResultado;
		try {
			String vRuta = host + "/certificacion/obtenerCasosDePruebaSugeridasEtapa0";
			Map<String, String> vMap = new HashMap<>();
			vMap.put("pSistemaId", pSistemaId + "");			
			TypeReference<ListaDetalleCasosDePruebaEtapa0Dto> typeRef = new TypeReference<ListaDetalleCasosDePruebaEtapa0Dto>() {
			};

			Response vResponse = ejecutarGetConParametros(vRuta, vMap);

			if (vResponse.code() == StatusCodes.NO_CONTENT) {
				vResultado = new ListaDetalleCasosDePruebaEtapa0Dto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new ListaDetalleCasosDePruebaEtapa0Dto();
		}

		return vResultado;
	}
	
	
	/**
	 * @Descipcion: Registra un caso de prueba sugerido .
	 * @author: Victor Cruz Gomez.  
	 * @param pCasoPruebaSugerido - Dto con los datos necesario para registrar un caso de prueba sugerido. 
	 * @return Estado del registro (1=exito; 0=error).
	 */
	public RespuestaPruebaSugeridaDto registrarCasoPruebaSugeridaEtapa2(DetallePruebasEtapa2Dto pDetallePruebasEtapa2)
	{
		configFacturacionCsisRestQueryPreProduccion();
		RespuestaPruebaSugeridaDto vResultado;
		String vJson="";
		try {			
			String vRuta = host + "/SeguimientoCertificacionPruebas/etapa2/casopruebasugerida/";
			ObjectMapper vMapper=new ObjectMapper();
			vJson=vMapper.writeValueAsString(pDetallePruebasEtapa2);								
			TypeReference<RespuestaPruebaSugeridaDto> typeRef = new TypeReference<RespuestaPruebaSugeridaDto>() {
			};

			Response vResponse = ejecutarPostConParametroMapper(vRuta, vJson);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespuestaPruebaSugeridaDto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			vResultado = new RespuestaPruebaSugeridaDto();
		}

		return vResultado;
	}
	
	/**
	 * Obtiene el datos de recertificacion.
	 * 
	 * @author: wilson.limachi
	 * @Fecha: 20/08/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
	public DatosRecertificacion obtenerDatosReSolCertificacion(DatosRecertificacion pDatosRecertificacion)
	{
		this.configFacturacionCertificacionRest();
		DatosRecertificacion vResultado;
		String vJson="";
		try 
		{			
			String vRuta = host + "/certificaciones/obtenerDatosReSolCertificacion";
			ObjectMapper vMapper=new ObjectMapper();
			vJson=vMapper.writeValueAsString(pDatosRecertificacion);								
			TypeReference<DatosRecertificacion> typeRef = new TypeReference<DatosRecertificacion>() {
			};

			Response vResponse = ejecutarPostConParametroMapper(vRuta, vJson);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new DatosRecertificacion();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			vResultado = new DatosRecertificacion();
		}

		return vResultado;
	}
	
	/**
	 * Procesa la recertificacion.
	 * 
	 * @author: wilson.limachi
	 * @Fecha: 20/08/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
	public RespuestaRegistrarSolicitudCertificacionDto registrarRecertificacionSolicitudSistema(SolicitudRegistrarReCertificacionSistemasDto pSolicitud)
	{
		this.configFacturacionCertificacionRest();
		RespuestaRegistrarSolicitudCertificacionDto vResultado;
		String vJson="";
		try 
		{			
			String vRuta = host + "/certificaciones/registrarRecertificacionSolicitudSistema";
			ObjectMapper vMapper=new ObjectMapper();
			vJson=vMapper.writeValueAsString(pSolicitud);								
			TypeReference<RespuestaRegistrarSolicitudCertificacionDto> typeRef = new TypeReference<RespuestaRegistrarSolicitudCertificacionDto>() {
			};

			Response vResponse = ejecutarPostConParametroMapper(vRuta, vJson);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new RespuestaRegistrarSolicitudCertificacionDto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			vResultado = new RespuestaRegistrarSolicitudCertificacionDto();
		}

		return vResultado;
	}
		
	public ContribuyentesModalidadesDto obtieneModalidadContribuyente(ContribuyentesModalidadesDto pSolicitud)
	{
		this.configFacturacionCertificacionRest();
		ContribuyentesModalidadesDto vResultado;
		String vJson="";
		try 
		{			
			String vRuta = host + "/certificaciones/obtieneModalidadContribuyente";
			ObjectMapper vMapper=new ObjectMapper();
			vJson=vMapper.writeValueAsString(pSolicitud);								
			TypeReference<ContribuyentesModalidadesDto> typeRef = new TypeReference<ContribuyentesModalidadesDto>() {
			};

			Response vResponse = ejecutarPostConParametroMapper(vRuta, vJson);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new ContribuyentesModalidadesDto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			vResultado = new ContribuyentesModalidadesDto();
		}

		return vResultado;
	}
	
	public RespuestaActualizacionGenericoDto aprobarSolicitudCertificacion(
			SolicitudSolicitudCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionGenericoDto resultado = new RespuestaActualizacionGenericoDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/aprobarSolicitudCertificacion";
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionGenericoDto();
		}
		return resultado;
	}
	
	public RespuestaActualizacionGenericoDto rechazarSolicitudCertificacion(
			SolicitudSolicitudCertificacionDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaActualizacionGenericoDto resultado = new RespuestaActualizacionGenericoDto();
		try {
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/rechazarSolicitudCertificacion";
			TypeReference<RespuestaActualizacionGenericoDto> typeRef = new TypeReference<RespuestaActualizacionGenericoDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaActualizacionGenericoDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaActualizacionGenericoDto();
		}
		return resultado;
	}
	
	public RespuestaRegistroVerificacionInsituDto obtieneRegistroListaRegistroObservacionesInsitu(RespuestaRegistroVerificacionInsituDto pSolicitud) 
	{
		configFacturacionCertificacionRest();
		RespuestaRegistroVerificacionInsituDto resultado = new RespuestaRegistroVerificacionInsituDto();
		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/obtieneRegistroListaRegistroObservacionesInsitu";
			TypeReference<RespuestaRegistroVerificacionInsituDto> typeRef = new TypeReference<RespuestaRegistroVerificacionInsituDto>() 
			{
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			
			if (response.code() == StatusCodes.NOT_FOUND) 
			{
				resultado = new RespuestaRegistroVerificacionInsituDto();
			} 
			else 
			{
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			resultado = new RespuestaRegistroVerificacionInsituDto();
		}
		
		return resultado;
	}
	
	public RespuestaRegistroVerificacionInsituDto obtenerListaVerificacionInsituPorSolicitudCertificacionId(long pSolicitudCertificacionId) {
		configFacturacionCsisRestQuery();
		RespuestaRegistroVerificacionInsituDto resultado;
		try {
			String ruta = host + "/SeguimientoCertificacionPruebas/verificacion/obtenerListaVerificacionInsituPorSolicitudCertificacionId/";
			TypeReference<RespuestaRegistroVerificacionInsituDto> typeRef = new TypeReference<RespuestaRegistroVerificacionInsituDto>() {};
			Map<String, String> map = new LinkedHashMap<>();
			map.put("pSolicitudCertificacionId", String.valueOf(pSolicitudCertificacionId));
			map.put("pTipoPrueba", "0");
			Response response = ejecutarGetConParametrosToken(ruta, map);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaRegistroVerificacionInsituDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaRegistroVerificacionInsituDto();
		}

		return resultado;
	}

	public RespuestaRegistroVerificacionInsituDto guardarRegistroVerificacionInsitu(SolicitudRegistroVerificacionInsituDto pSolicitud) {
		configFacturacionCertificacionRest();
		RespuestaRegistroVerificacionInsituDto resultado = new RespuestaRegistroVerificacionInsituDto();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/certificaciones/guardarRegistroVerificacionInsitu";
			TypeReference<RespuestaRegistroVerificacionInsituDto> typeRef = new TypeReference<RespuestaRegistroVerificacionInsituDto>() {
			};
			Response response = ejecutarPostConParametroMapperToken(ruta, json);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaRegistroVerificacionInsituDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaRegistroVerificacionInsituDto();
		}
		return resultado;
	}

	public ListaSeguimientoCertificacionSistemasFaseDosDto listaEtapaFaseDos(ListaSeguimientoCertificacionSistemasFaseDosDto  pSolicitud)
	{
		this.configFacturacionCertificacionRest();
		ListaSeguimientoCertificacionSistemasFaseDosDto vResultado = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		String vJson="";
		try 
		{			
			String vRuta = host + "/certificacion/listaEtapaFaseDos";
			ObjectMapper vMapper=new ObjectMapper();
			vJson=vMapper.writeValueAsString(pSolicitud);								
			TypeReference<ListaSeguimientoCertificacionSistemasFaseDosDto> typeRef = new TypeReference<ListaSeguimientoCertificacionSistemasFaseDosDto>() {
			};

			Response vResponse = ejecutarPostConParametroMapper(vRuta, vJson);

			if (vResponse.code() == StatusCodes.NOT_FOUND) {
				vResultado = new ListaSeguimientoCertificacionSistemasFaseDosDto();
			} else {
				vResultado = Json.serializer().fromInputStream(vResponse.body().byteStream(), typeRef);
			}
		} 
		catch (Exception e) 
		{
			vResultado = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		}

		return vResultado;
	}
	
	public RespuestaResumenPruebaCertificacionSistemaDto obtenerResumenPruebaCertificacionSistema(long pSistemaId) {
		configFacturacionCsisRestQuery();
		RespuestaResumenPruebaCertificacionSistemaDto resultado;
		try {
			String ruta = host + "/SeguimientoCertificacionPruebas/verificacion/obtenerResumenPruebaCertificacionSistema/";
			TypeReference<RespuestaResumenPruebaCertificacionSistemaDto> typeRef = new TypeReference<RespuestaResumenPruebaCertificacionSistemaDto>() {};
			Map<String, String> map = new LinkedHashMap<>();
			map.put("pSistemaId", String.valueOf(pSistemaId));
			Response response = ejecutarGetConParametrosToken(ruta, map);
			if (response.code() == StatusCodes.NOT_FOUND) {
				resultado = new RespuestaResumenPruebaCertificacionSistemaDto();
			} else {
				resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
			}
		} catch (Exception e) {
			resultado = new RespuestaResumenPruebaCertificacionSistemaDto();
		}

		return resultado;
	}
}