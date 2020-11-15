package bo.gob.sin.sre.fac.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspose.words.License;

import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.BitacoraObservacionComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.ContribuyentesModalidadesDto;
import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ObtenerListSistemaDto;
import bo.gob.sin.sre.fac.dto.ObtenerListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemaCertificacion;
import bo.gob.sin.sre.fac.dto.RecuperarListaContactosCertificacion;
import bo.gob.sin.sre.fac.dto.RecuperarListaSolicitudCertificacion;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituListaDto;
import bo.gob.sin.sre.fac.dto.RegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistrosPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.DatosTramiteSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaTamanioGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaVerificacionPruebasConcluidasDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarReCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;
import bo.gob.sin.sre.fac.service.IAsignacionesEquipoCertificacionesService;
import bo.gob.sin.sre.fac.service.ICambiarEstadoSolicitudCertificacionService;
import bo.gob.sin.sre.fac.service.IConsultaEstadoCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.IPruebasAutomaticasService;
import bo.gob.sin.sre.fac.service.IPruebasManualesService;
import bo.gob.sin.sre.fac.service.IRecuperarDatosSistemasService;
import bo.gob.sin.sre.fac.service.IRecuperarDatosTramiteSolCertificacionService;
import bo.gob.sin.sre.fac.service.IRecuperarListaContactosCertificacionService;
import bo.gob.sin.sre.fac.service.IRecuperarListaSolicitudesCertificacionEnProcesoService;
import bo.gob.sin.sre.fac.service.IRegistroCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.IRegistroHuellasSistemaContribuyenteService;
import bo.gob.sin.sre.fac.service.IRegistroHuellasSistemaService;
import bo.gob.sin.sre.fac.service.IRegistroPruebasAutomaticasConsultaService;
import bo.gob.sin.sre.fac.service.IRegistroReCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.IRegistroVerificacionInsituService;
import bo.gob.sin.sre.fac.service.IRegistroVerificacionPruebasInsituService;
import bo.gob.sin.sre.fac.service.IRegistrosObservacionesInsituService;
//import bo.gob.sin.sre.fac.servicedomain.IAprobacionRechazoCertificacionService;
import bo.gob.sin.sre.fac.service.ISeguimientoCertificacionSistemasService;

@CrossOrigin(maxAge = 3600)
@RestController
// @CacheConfig(cacheNames = { "books" })
@RequestMapping(value = "/certificaciones")
public class CertificacionSistemasController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(CertificacionSistemasController.class);
	@Autowired
	IRegistroCertificacionSistemasService iRegistroCertificacionSistemasService;

	@Autowired
	IRecuperarListaSolicitudesCertificacionEnProcesoService iRecuperarListaSolicitudesCertificacionEnProcesoService;

	@Autowired
	ICambiarEstadoSolicitudCertificacionService iCambiarEstadoSolicitudCertificacionService;

	@Autowired
	IRecuperarListaContactosCertificacionService iRecuperarListaContactosCertificacionService;

	@Autowired
	IRecuperarDatosTramiteSolCertificacionService iRecuperarDatosTramiteSolCertificacionService;

	@Autowired
	IRecuperarDatosSistemasService iRecuperarDatosSistemasService;

	@Autowired
	private IPruebasAutomaticasService iPruebasAutomaticasService;

	@Autowired
	private IPruebasManualesService iPruebasManualesService;

	@Autowired
	private IRegistroHuellasSistemaContribuyenteService iRegistroHuellasSistemaContribuyenteService;

	@Autowired
	private IRegistroVerificacionInsituService iRegistroVerificacionInsituService;

	@Autowired
	private IRegistrosObservacionesInsituService iRegistrosObservacionesInsituService;

	@Autowired
	private IRegistroPruebasAutomaticasConsultaService iRegistroConsultaPruebasAutomaticasService;

	@Autowired
	private IAsignacionesEquipoCertificacionesService iAsignacionesEquipoCertificacionesService;

	@Autowired
	IRegistroHuellasSistemaService iRegistroHuellaSistemaService;

	@Autowired
	IConsultaEstadoCertificacionSistemasService iConsultaEstadoCertificacionSistemasService;
	
	@Autowired
	ISeguimientoCertificacionSistemasService iSeguimientoCertificacionSistemasService;
	
	@Autowired
	IRegistroReCertificacionSistemasService iRegistroReCertificacionSistemasService;
	
	@Autowired
	IRegistroVerificacionPruebasInsituService iRegistroVerificacionPruebasInsituService;

//	@Autowired
//	IAprobacionRechazoCertificacionService iAprobacionRechazoCertificacionService;
	
	public CertificacionSistemasController() {
		Locale.setDefault(new Locale("es", "ES"));
		License license = new License();
		try {
			InputStream licPath = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("Aspose.Words.Product.Family.lic");
			license.setLicense(licPath);
		} catch (Exception e) {
		}
	}

	/**
	 * Objetivo: verificacion de sistemas registrados. Creado por: Reynaldo Chambi.
	 * Fecha: 13/04/2018 Modificado por: Gualberto Condori Fecha de Modificacion:
	 * 15/06/2018
	 */
	// IASC - Cambio en ruta de acceso - 11/10/2018
	// IASC - Cambio en ruta por refactorizacion - 21/11/2018
	@PostMapping(value = "/verificacionSistemaRegistrado")
	public ResponseEntity<RespuestaEstaRegistradoGenericoDto> verificacionSistemaRegistrado(
			@RequestBody SolicitudVerificacionSistemaRegistradoDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaEstaRegistradoGenericoDto vRespuesta = iRegistroCertificacionSistemasService
				.verificacionSistemaRegistrado(pSolicitud);
		ResponseEntity<RespuestaEstaRegistradoGenericoDto> vResponseEntity = new ResponseEntity<RespuestaEstaRegistradoGenericoDto>(
				vRespuesta, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity);
		return vResponseEntity;
	}
	
	@PostMapping(value = "/prueba")
	public ResponseEntity<RespuestaEstaRegistradoGenericoDto> prueba() 
	{
		LOG.info("Inicio solicitud:");
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		ResponseEntity<RespuestaEstaRegistradoGenericoDto> vResponseEntity = new ResponseEntity<RespuestaEstaRegistradoGenericoDto>(
				vRespuesta, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity);
		return vResponseEntity;
	}

	/**
	 * Objetivo: registrar solicitud de sistemas. Creado por: Reynaldo Chambi.
	 * Fecha: 03/04/2018 Modificado por: Gualberto Condori Fecha de Modificacion:
	 * 18/06/2018
	 */

	// IASC - Cambio en ruta de acceso - 11/10/2018
	// IASC - CAmbio en ruta por refactorizacion - 21/11/2018
	@PostMapping(value = "/registrarSolicitudSistema")
	public ResponseEntity<RespuestaRegistrarSolicitudCertificacionDto> registrarSolicitudSistema(
			@RequestBody SolicitudRegistrarCertificacionSistemasDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaRegistrarSolicitudCertificacionDto vRespuestaEntidad = new RespuestaRegistrarSolicitudCertificacionDto();
		vRespuestaEntidad = iRegistroCertificacionSistemasService.registrarSolicitudSistema(pSolicitud);
		ResponseEntity<RespuestaRegistrarSolicitudCertificacionDto> vResponseEntity = new ResponseEntity<RespuestaRegistrarSolicitudCertificacionDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}

	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO, para
	 * Cancelacion de Solicitud de Certificacion
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo
	 *         RespuestaSistemasDeCertificacionFuncionarioDto.
	 */
	@PostMapping("/recuperaSolicitudesCertificacionParaCancelar")
	public ResponseEntity<RecuperarListaSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(
			@RequestBody SolicitudSolicitudCertificacionDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		RecuperarListaSolicitudCertificacion vRespuestaDto = iRecuperarListaSolicitudesCertificacionEnProcesoService
				.recuperaSolicitudesCertificacionParaCancelar(pSolicitud);
		ResponseEntity<RecuperarListaSolicitudCertificacion> vResponseEntity = new ResponseEntity<RecuperarListaSolicitudCertificacion>(
				vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	/**
	 * Cancelar el proceso de SolicitudCertificacion
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 11/10/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo
	 *         RespuestaActualizacionSolicitudCertificacionDto.
	 */
	@PostMapping(value = "/cancelarSolicitudCertificacion")
	public ResponseEntity<RespuestaActualizacionGenericoDto> cancelarSolicitudCertificacion(@RequestBody SolicitudSolicitudCertificacionDto pSolicitud) 
	{
		
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iCambiarEstadoSolicitudCertificacionService.cancelarCertificacion(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	/**
	 * Recuperar Lista de sistemas asociados al contribuyente, que se encuentran en
	 * estado = en proceso
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/09/2018
	 * @modificadoPor: rosario.garcia
	 * @FechaModificacion: 23/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return lista de sistemas
	 */
	@PostMapping("/obtenerSistemasEstadoEnProceso")
	public ResponseEntity<ObtenerListSistemaDto> obtenerSistemasEstadoEnProceso(
			@RequestBody ObtenerListSistemaDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		ObtenerListSistemaDto vRespuestaDto = iRecuperarListaSolicitudesCertificacionEnProcesoService
				.obtenerSistemasEstadoEnProceso(pSolicitud);
		ResponseEntity<ObtenerListSistemaDto> vResponseEntity = new ResponseEntity<ObtenerListSistemaDto>(vRespuestaDto,
				HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	// IASC - Recupera los datos de los contacos de la solicitud - 24/11/2018
	@RequestMapping(value = "/recuperarContactosCertificacion/{pSolicitudCertificacionId}", method = RequestMethod.GET)
	public ResponseEntity<RecuperarListaContactosCertificacion> obtenerContactosCertificacion(
			@PathVariable String pSolicitudCertificacionId) {
		LOG.info("Inicio solicitud:" + pSolicitudCertificacionId);
		RecuperarListaContactosCertificacion vRespuestaDto = iRecuperarListaContactosCertificacionService
				.recuperarListaContactosCertificacion(Long.parseLong(pSolicitudCertificacionId));
		ResponseEntity<RecuperarListaContactosCertificacion> vResponseEntity = new ResponseEntity<RecuperarListaContactosCertificacion>(
				vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	// IASC - Recupera los datos del tramite de la solicitud de certificacion -
	// 25/11/2018
	@RequestMapping(value = "/recuperarDatosTramite/{pTramiteId}", method = RequestMethod.GET)
	public ResponseEntity<DatosTramiteSolCertificacionDto> obtenerDatosTramiteCertificacion(
			@PathVariable String pTramiteId) {
		DatosTramiteSolCertificacionDto vRespuestaDto = iRecuperarDatosTramiteSolCertificacionService
				.obtenerDatosTramiteCertificacion(Long.parseLong(pTramiteId));
		ResponseEntity<DatosTramiteSolCertificacionDto> vResponseEntity = new ResponseEntity<DatosTramiteSolCertificacionDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	// IASC - Recupera los datos del sistema para el componente de datos de sistema
	// - 26/11/2018
	@PostMapping("/recuperarDatosComponenteSistema")
	public ResponseEntity<RespuestaDatosSistemasSolCertificacionDto> recuperarDatosComponenteSistema(
			@RequestBody RespuestaDatosSistemasSolCertificacionDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaDatosSistemasSolCertificacionDto vRespuestaDto = iRecuperarDatosSistemasService
				.obtenerDatosSistemaCertificacion(pSolicitud.getSistemaId(), pSolicitud.getSolicitudCertificacionId());
		ResponseEntity<RespuestaDatosSistemasSolCertificacionDto> vResponseEntity = new ResponseEntity<RespuestaDatosSistemasSolCertificacionDto>(
				vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	/**
	 * @author rosario.garcia
	 * @fecha 27/11/2018
	 * @return los datos del sistema por el sistemaId
	 */
	@PostMapping("/obtenerDatosSistemaPorSistemaId")
	public ResponseEntity<SistemasDto> obtenerDatosSistemaPorSistemaId(@RequestBody SistemasDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		SistemasDto vRespuestaDto = iRecuperarDatosSistemasService
				.obtenerDatosSistemaPorSistemaId(pSolicitud.getSistemaId());
		ResponseEntity<SistemasDto> vResponseEntity = new ResponseEntity<SistemasDto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	/**
	 * Obtiene las pruebas manuales de un sistema.
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 24/11/2018
	 * @param pSolicitudPruebasSistemas, parametro busqueda pruebas automatizadas
	 * @return Devuelve objeto Dto de tipo Lista
	 *         RegistrosPruebasAutomatizadasDetalleDto.*
	 */
	@PostMapping(value = "/obtienePruebasManualesSistema")
	public ResponseEntity<RespuestaListaRegistroPruebasManualesDto> obtienePruebasManualesSistema(
			@RequestBody SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		RespuestaListaRegistroPruebasManualesDto vRespuestaDto = new RespuestaListaRegistroPruebasManualesDto();
		vRespuestaDto = iPruebasManualesService.obtieneListaPruebasManuales(pSolicitudPruebasSistemas.getSolicitudId(),
				pSolicitudPruebasSistemas.getTramiteId(), pSolicitudPruebasSistemas.getSistemaId());

		// iRegistroHuellaSistemaService.obtieneComponentesArchivos(pSolicitud);
		ResponseEntity<RespuestaListaRegistroPruebasManualesDto> vResponseEntity = new ResponseEntity<RespuestaListaRegistroPruebasManualesDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Modifica los campos de observacion, estado_prueba_id
	 * 
	 * @author: Levi Laura
	 * @Fecha: 28/11/2018
	 * @param RegistrosPruebasManualesDto
	 * @return Devuleve booleano true si modifico o false lo contrario*
	 */
	@PostMapping(value = "/guardarCambiosPruebaManual")
	public ResponseEntity<RespuestaActualizacionGenericoDto> modificaObservacionEstado(
			@RequestBody RegistrosPruebasManualesDto pRegistroPruebaManual) {
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iPruebasManualesService.modificaObservacionEstado(pRegistroPruebaManual);

		// iRegistroHuellaSistemaService.obtieneComponentesArchivos(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene las pruebas manuales de un sistema.
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 24/11/2018
	 * @param pSolicitudPruebasSistemas, parametro busqueda pruebas automatizadas
	 * @return Devuelve objeto Dto de tipo Lista
	 *         RegistrosPruebasAutomatizadasDetalleDto.*
	 */
	@PostMapping(value = "/verificarPruebasFinalizadas")
	public ResponseEntity<RespuestaVerificacionPruebasConcluidasDto> verificarPruebasFinalizadas(
			@RequestBody SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		RespuestaVerificacionPruebasConcluidasDto vRespuestaDto = new RespuestaVerificacionPruebasConcluidasDto();

		vRespuestaDto = iPruebasAutomaticasService.verificaPruebasConcluidas(pSolicitudPruebasSistemas);
		ResponseEntity<RespuestaVerificacionPruebasConcluidasDto> vResponseEntity = new ResponseEntity<RespuestaVerificacionPruebasConcluidasDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/***
	 * @author rosario.garcia
	 * @fecha 27/11/2018 Servicio que permite registrar los archivos de sistema -
	 *        Contribuyente
	 */
	@PostMapping(value = "/registrarHuellaDigitalSistemaContribuyente")
	public ResponseEntity<RegistroHuellasDigitalesDto> RegistrarHuellasDigitalesSistemas(
			@RequestBody ListaRegistroHuellasDigitalesDto pSolicitud) {
		RegistroHuellasDigitalesDto vRespuestaDto = new RegistroHuellasDigitalesDto();
		vRespuestaDto = iRegistroHuellasSistemaContribuyenteService.registrarHuellasDigitalesSistemas(pSolicitud);
		ResponseEntity<RegistroHuellasDigitalesDto> vResponseEntity = new ResponseEntity<RegistroHuellasDigitalesDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Modifica observacion in situ.
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 28/11/2018
	 * @param pSolicitudRegistroObservaciones, parametro para registro de
	 *        observaciones
	 * @return Devuelve objeto Dto de tipo Lista
	 *         RegistroObservacionesComponentesInsituDto.*
	 */
	@PostMapping(value = "/modificarObservacionInSitu")
	public ResponseEntity<RegistroObservacionesComponentesInsituDto> modificarObservacionInSitu(
			@RequestBody RegistroObservacionesComponentesInsituDto pSolicitudRegistroObservaciones) {
		RegistroObservacionesComponentesInsituDto vRespuestaDto = new RegistroObservacionesComponentesInsituDto();

		vRespuestaDto = iRegistroVerificacionInsituService.modificarObservacionInSitu(pSolicitudRegistroObservaciones);
		ResponseEntity<RegistroObservacionesComponentesInsituDto> vResponseEntity = new ResponseEntity<RegistroObservacionesComponentesInsituDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Registra la primera vez los componentes iniciales para la verificacion in
	 * situ
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 28/11/2018
	 * @param pSolicitudRegistroObservaciones, parametro que contiene el dto
	 *        RegistroObservacionesComponentesInsituDto
	 * @return Devuelve objeto Dto de tipo Lista
	 *         RegistroObservacionesComponentesInsituListaDto.*
	 */
	@PostMapping(value = "/registrarDatosInicioObservaciones")
	public ResponseEntity<RegistroObservacionesComponentesInsituListaDto> registrarDatosInicioObservaciones(
			@RequestBody RegistroObservacionesComponentesInsituDto pSolicitudRegistroObservaciones) {
		RegistroObservacionesComponentesInsituListaDto vRespuestaDto = new RegistroObservacionesComponentesInsituListaDto();
		vRespuestaDto = iRegistroVerificacionInsituService
				.registrarDatosInicioObservaciones(pSolicitudRegistroObservaciones);

//		vRespuestaDto = iRegistroVerificacionInsituService.obtenerObservacionesComponentesInsituPorCodigoCertificacion(
//				pSolicitudRegistroObservaciones.getSolicitudCertificacionId());

		ResponseEntity<RegistroObservacionesComponentesInsituListaDto> vResponseEntity = new ResponseEntity<RegistroObservacionesComponentesInsituListaDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene las observaciones de los componentes insitu por codigo de
	 * cerificaciones
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 28/11/2018
	 * @param pCodigoCertificacion, parametro codigo de solciitud de certificacion
	 * @return Devuelve objeto Dto de tipo Lista
	 *         RegistroObservacionesComponentesInsituListaDto.*
	 */
	@RequestMapping(value = "/obtenerObservacionesComponentesInsituPorCodigoCertificacion/{pCodigoCertificacion}", method = RequestMethod.GET)
	public ResponseEntity<RegistroObservacionesComponentesInsituListaDto> obtenerContactosCertificacion(
			@PathVariable long pCodigoCertificacion) {
		RegistroObservacionesComponentesInsituListaDto vRespuestaDto = new RegistroObservacionesComponentesInsituListaDto();
		vRespuestaDto = iRegistroVerificacionInsituService
				.obtenerObservacionesComponentesInsituPorCodigoCertificacion(pCodigoCertificacion);

		ResponseEntity<RegistroObservacionesComponentesInsituListaDto> vResponseEntity = new ResponseEntity<RegistroObservacionesComponentesInsituListaDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Registra una observacion al componente insitu
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 28/11/2018
	 * @param pSolicitudRegistroObservaciones, parametro que contiene el dto
	 *        RegistroObservacionesComponentesInsituDto
	 * @return Devuelve objeto Dto RegistroObservacionesComponentesInsituDto.*
	 */
	@PostMapping(value = "/registrarObservacionInSitu")
	public ResponseEntity<RegistroObservacionesComponentesInsituDto> registrarObservacionInSitu(
			@RequestBody RegistroObservacionesComponentesInsituDto pSolicitudRegistroObservaciones) {
		RegistroObservacionesComponentesInsituDto vRespuestaDto = new RegistroObservacionesComponentesInsituDto();
		vRespuestaDto = iRegistroVerificacionInsituService.registrarObservacionInSitu(pSolicitudRegistroObservaciones);
		ResponseEntity<RegistroObservacionesComponentesInsituDto> vResponseEntity = new ResponseEntity<RegistroObservacionesComponentesInsituDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Registra una observacion al compoennte insitu para su bitacora
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 28/11/2018
	 * @param pSolicitudBitacoraObservaciones, parametro que contiene el dto
	 *        BitacoraObservacionComponentesInsituDto
	 * @return Devuelve objeto Dto tipo lista
	 *         BitacoraObservacionComponentesInsituDto.*
	 */
	@PostMapping(value = "/registrarBitacoraObservacionInSitu")
	public ResponseEntity<BitacoraObservacionComponentesInsituDto> registrarBitacoraObservacionInSitu(
			@RequestBody BitacoraObservacionComponentesInsituDto pSolicitudBitacoraObservaciones) {
		BitacoraObservacionComponentesInsituDto vRespuestaDto = new BitacoraObservacionComponentesInsituDto();
		vRespuestaDto = iRegistroVerificacionInsituService
				.registrarBitacoraObservacionInSitu(pSolicitudBitacoraObservaciones);
		ResponseEntity<BitacoraObservacionComponentesInsituDto> vResponseEntity = new ResponseEntity<BitacoraObservacionComponentesInsituDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene listado de verificacion insitu
	 * 
	 * @author: Levi Laura
	 * @Fecha: 28/11/2018
	 * @param RegistrosObservacionesInsituDto
	 * @return Devuelve listado de verificación insitu*
	 */

	@PostMapping(value = "/obtieneListadoVerificacionInsitu")
	public ResponseEntity<RespuestaRegistrosObservacionesInsituDto> modificaObservacionEstado(
			@RequestBody RegistrosObservacionesInsituDto pSolicitudListado) {
		RespuestaRegistrosObservacionesInsituDto vRespuestaDto = new RespuestaRegistrosObservacionesInsituDto();
		vRespuestaDto = iRegistrosObservacionesInsituService
				.obtieneListaVerificacionInsitu(pSolicitudListado.getSolicitudCertificacionId());

		// iRegistroHuellaSistemaService.obtieneComponentesArchivos(pSolicitud);
		ResponseEntity<RespuestaRegistrosObservacionesInsituDto> vResponseEntity = new ResponseEntity<RespuestaRegistrosObservacionesInsituDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	// IASC - Recupera los datos para el reporte de solicitud de certificacion -
	// 27/11/2018
	@PostMapping("/recuperarDatosReporteSolicitudCertificacion")
	public ResponseEntity<ReporteDatosSolicitudCertificacionDto> recuperarDatosReporteSolicitudCertificacion(
			@RequestBody RespuestaDatosSistemasSolCertificacionDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		ReporteDatosSolicitudCertificacionDto vRespuestaDto = iRecuperarDatosSistemasService
				.obtenerDatosReporteSolCertificacion(pSolicitud.getSistemaId(),
						pSolicitud.getSolicitudCertificacionId());
		ResponseEntity<ReporteDatosSolicitudCertificacionDto> vResponseEntity = new ResponseEntity<ReporteDatosSolicitudCertificacionDto>(
				vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	/**
	 * Guarda un regsitro de observacion a verificacion insitu
	 * 
	 * @author: Levi Laura
	 * @Fecha: 29/11/2018
	 * @param RegistrosObservacionesInsituDto
	 * @return Devuelve listado de verificación insitu*
	 */
	@PostMapping(value = "/registraObservacionVerificacionInsitu")
	public ResponseEntity<RespuestaRegistrosObservacionesInsituDto> registrarVerificacionInsitu(
			@RequestBody RegistrosObservacionesInsituDto pRegistroObs) {
		RespuestaRegistrosObservacionesInsituDto vRespuestaDto = new RespuestaRegistrosObservacionesInsituDto();
		vRespuestaDto = iRegistrosObservacionesInsituService.registroObservacionComponente(pRegistroObs);

		// iRegistroHuellaSistemaService.obtieneComponentesArchivos(pSolicitud);
		ResponseEntity<RespuestaRegistrosObservacionesInsituDto> vResponseEntity = new ResponseEntity<RespuestaRegistrosObservacionesInsituDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Lista los sistema de certificacion de un contribuyente
	 * 
	 * @author: Noemi Mamani
	 * @Fecha: 29/11/2018
	 * @param pPersonaContribuyenteId
	 * @modificado fecha: 21/12/2018
	 * @return Devuelve listado de sistemas de certificacion de un contribuyente*
	 */
	@RequestMapping(value = "/recuperaListaEstadoCertificacionSistemas/{pPersonaContribuyenteId}", method = RequestMethod.GET)
	public ResponseEntity<RecuperaListaSistemaCertificacion> recuperaListaEstadoCertificacionSistemas(
			@PathVariable long pPersonaContribuyenteId) {
		RecuperaListaSistemaCertificacion vRespuestaDto = new RecuperaListaSistemaCertificacion();
		vRespuestaDto = iConsultaEstadoCertificacionSistemasService
				.recuperaListaEstadoCertificacionSistemasService(pPersonaContribuyenteId);
		ResponseEntity<RecuperaListaSistemaCertificacion> vResponseEntity = new ResponseEntity<RecuperaListaSistemaCertificacion>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene los componentes y archivos registrados por el contribuyente para
	 * generar el Reporte
	 * 
	 * @author rosario.garcia
	 * @param pSolicitud de tipo SistemasDto
	 * @return lista de componentes y archivos registrados por el contribuyente
	 * @fecha 03/12/2018
	 */
	@PostMapping(value = "/obtenerListaComponentesRegistradosPorContribuyente")
	public ResponseEntity<ReporteComponentesRegistradosCtbteDto> obtenerListaComponentesRegistradosPorContribuyente(
			@RequestBody SistemasDto pSolicitud) {
		ReporteComponentesRegistradosCtbteDto vRespuestaDto = new ReporteComponentesRegistradosCtbteDto();
		vRespuestaDto = iRegistroHuellasSistemaContribuyenteService
				.obtenerListaComponentesRegistradosPorContribuyente(pSolicitud.getSistemaId());
		ResponseEntity<ReporteComponentesRegistradosCtbteDto> vResponseEntity = new ResponseEntity<ReporteComponentesRegistradosCtbteDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * Obtiene los componentes y archivos registrados por el contribuyente para
	 * generar el Reporte
	 * 
	 * @author wilson.limachi
	 * @param pSolicitud de tipo SistemasDto y contribuyente
	 * @return lista de componentes y archivos registrados por el contribuyente
	 * @fecha 04/09/2019
	 */
	@PostMapping(value = "/obtenerListaComponentesRegistradosPorContribuyenteSistema")
	public ResponseEntity<ReporteComponentesRegistradosCtbteDto> obtenerListaComponentesRegistradosPorContribuyenteSistema(@RequestBody SistemasDto pSolicitud) 
	{
		ReporteComponentesRegistradosCtbteDto vRespuestaDto = new ReporteComponentesRegistradosCtbteDto();
		vRespuestaDto = iRegistroHuellasSistemaContribuyenteService.obtenerListaComponentesRegistradosPorContribuyente(pSolicitud.getSistemaId(), pSolicitud.getIdContribuyenteProvedor());
		ResponseEntity<ReporteComponentesRegistradosCtbteDto> vResponseEntity = new ResponseEntity<ReporteComponentesRegistradosCtbteDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Muestra las pruebas automaticas
	 * 
	 * @author: Levi Laura
	 * @Fecha: 04/12/2018
	 * @param SolicitudPruebasSistemasDto
	 * @return Devuelve listado de pruebas automaticas*
	 */

	@PostMapping(value = "/obtienePruebasAutomaticasSistema")
	public ResponseEntity<RespuestaListaRegistroPruebasAutomaticasDto> obtieneListadoPruebasAutomaticas(
			@RequestBody SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		RespuestaListaRegistroPruebasAutomaticasDto vRespuestaDto = new RespuestaListaRegistroPruebasAutomaticasDto();
		vRespuestaDto = iRegistroConsultaPruebasAutomaticasService.obtieneListadoPruebasAutomaticas(
				pSolicitudPruebasSistemas.getSolicitudId(), pSolicitudPruebasSistemas.getTramiteId(),
				pSolicitudPruebasSistemas.getSistemaId());
		ResponseEntity<RespuestaListaRegistroPruebasAutomaticasDto> vResponseEntity = new ResponseEntity<RespuestaListaRegistroPruebasAutomaticasDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene equipo certificacion por numero de tramite.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 10/12/2018
	 * @param pTramiteId, numero de solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
	@RequestMapping(value = "/obtenerEquipoCertificacionPorTramite/{pTramiteId}", method = RequestMethod.GET)
	public ResponseEntity<AsignacionesCertificacionesListaDto> obtenerEquipoCertificacionPorTramite(
			@PathVariable long pTramiteId) {
		LOG.info("Inicio tramite:" + pTramiteId);
		AsignacionesCertificacionesListaDto vRespuestaEntidad = new AsignacionesCertificacionesListaDto();
		vRespuestaEntidad = iAsignacionesEquipoCertificacionesService
				.obtenerAsignacionEquipoCertificacionPorTramite(pTramiteId);
		ResponseEntity<AsignacionesCertificacionesListaDto> vResponseEntity = new ResponseEntity<AsignacionesCertificacionesListaDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}

	/**
	 * Registra equipo certificacion.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 10/12/2018
	 * @param pTramiteId, numero de solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
	@PostMapping(value = "/registrarEquipoCertificacion")
	public ResponseEntity<RespuestaEstaRegistradoGenericoDto> registrarEquipoCertificacion(
			@RequestBody AsignacionesCertificacionesListaDto pDatosEquipoAsignacion) {
		LOG.info("Inicio tramite:" + pDatosEquipoAsignacion.toString());
		RespuestaEstaRegistradoGenericoDto vRespuestaEntidad = new RespuestaEstaRegistradoGenericoDto();
		vRespuestaEntidad = iAsignacionesEquipoCertificacionesService
				.registrarAsignacionEquipoCertificacion(pDatosEquipoAsignacion);
		ResponseEntity<RespuestaEstaRegistradoGenericoDto> vResponseEntity = new ResponseEntity<RespuestaEstaRegistradoGenericoDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}

	/**
	 * VErifica asi contribuyente ingresa a visita in situ.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 10/12/2018
	 * @param pNit, nit contribuyente
	 * @return Devuelve objeto Dto de respuesta
	 */
	@RequestMapping(value = "/verificarContribuyenteRequiereInSitu/{pNit}", method = RequestMethod.GET)
	public ResponseEntity<RespuestaEstaRegistradoGenericoDto> verificarContribuyenteRequiereInSitu(
			@PathVariable long pNit) {
		LOG.info("Inicio tramite:" + pNit);
		RespuestaEstaRegistradoGenericoDto vRespuestaEntidad = new RespuestaEstaRegistradoGenericoDto();
		vRespuestaEntidad = iRegistroVerificacionInsituService.verificarContribuyenteRequiereVisitaInsitu(pNit);
		ResponseEntity<RespuestaEstaRegistradoGenericoDto> vResponseEntity = new ResponseEntity<RespuestaEstaRegistradoGenericoDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}

	/**
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author:
	 * @Fecha:
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RegistrarHuellaSistemaDto.*
	 */
	@PostMapping(value = "/registrarHuellaDigitalSistema")
	public ResponseEntity<RegistroHuellasDigitalesDto> RegistrarHuellasDigitalesSistemasFinal(
			@RequestBody ListaRegistroHuellasDigitalesDto pSolicitud) {
		RegistroHuellasDigitalesDto vRespuestaDto = new RegistroHuellasDigitalesDto();
		vRespuestaDto = iRegistroHuellaSistemaService.registrarHuellasDigitalesSistemas(pSolicitud);
		ResponseEntity<RegistroHuellasDigitalesDto> vResponseEntity = new ResponseEntity<RegistroHuellasDigitalesDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * VErifica pruebas manuales finalizadas.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 10/12/2018
	 * @param pSolicitudPruebasSistemas, objecto SolicitudPruebasSistemasDto
	 * @return Devuelve objeto Dto de respuesta
	 */
	@PostMapping(value = "/verificarPruebasManualesTerminadas")
	public ResponseEntity<RespuestaEstaRegistradoGenericoDto> verificarPruebasManualesTerminadas(
			@RequestBody SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		RespuestaEstaRegistradoGenericoDto vRespuestaDto = new RespuestaEstaRegistradoGenericoDto();
		vRespuestaDto = iPruebasManualesService.verificarPruebasManualesFinalizadas(
				pSolicitudPruebasSistemas.getSolicitudId(), pSolicitudPruebasSistemas.getTramiteId(),
				pSolicitudPruebasSistemas.getSistemaId());
		ResponseEntity<RespuestaEstaRegistradoGenericoDto> vResponseEntity = new ResponseEntity<RespuestaEstaRegistradoGenericoDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Realiza la encriptación de los archivos en formato CRC, MD5 y SHA
	 * 
	 * @author:
	 * @Fecha:
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RegistrarHuellaSistemaDto.*
	 */
	@PostMapping(value = "/obtenerCifradoHuellaDigital")
	public ResponseEntity<RegistrarHuellaSistemaDto> obtenerCifradoHuellaDigital(
			@RequestBody GenerarHuellaSistemaDto pSolicitud) {
		RegistrarHuellaSistemaDto vRespuestaDto = new RegistrarHuellaSistemaDto();
		vRespuestaDto = iRegistroHuellaSistemaService.obtenerCifradoHuellaDigital(pSolicitud);
		ResponseEntity<RegistrarHuellaSistemaDto> vResponseEntity = new ResponseEntity<RegistrarHuellaSistemaDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtienes componentes en visita in situ no aprobadas.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 12/12/2018
	 * @param pSolicitudCertificacion, solicitud certificacion
	 * @return Devuelve objeto Dto de respuesta
	 */
	@RequestMapping(value = "/verificarObservacionComponenteInSituAprobadas/{pSolicitudCertificacion}", method = RequestMethod.GET)
	public ResponseEntity<RespuestaEstaRegistradoGenericoDto> obtieneComponentesNoAprobadosVisitaInsituPorSolicitud(
			@PathVariable long pSolicitudCertificacion) {
		LOG.info("Inicio solicitud certificacion:" + pSolicitudCertificacion);
		RespuestaEstaRegistradoGenericoDto vRespuestaEntidad = new RespuestaEstaRegistradoGenericoDto();
		vRespuestaEntidad = iRegistroVerificacionInsituService
				.verificarObservacionComponenteInSituAprobadas(pSolicitudCertificacion);
		ResponseEntity<RespuestaEstaRegistradoGenericoDto> vResponseEntity = new ResponseEntity<RespuestaEstaRegistradoGenericoDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}

	/**
	 * Muestra las pruebas automaticas de forma paginada
	 * 
	 * @author: Levi Laura
	 * @Fecha: 04/12/2018
	 * @param SolicitudPruebasSistemasDto
	 * @return Devuelve listado de pruebas automaticas*
	 */
	@PostMapping(value = "/obtienePruebasAutomaticasPaginada")
	public ResponseEntity<RespuestaListaRegistroPruebasAutomaticasDto> obtieneListaPaginadaPruebasAutomaticas(
			@RequestBody SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		RespuestaListaRegistroPruebasAutomaticasDto vRespuestaDto = new RespuestaListaRegistroPruebasAutomaticasDto();
		vRespuestaDto = iRegistroConsultaPruebasAutomaticasService
				.obtieneListaPaginadaPruebasAutomaticas(pSolicitudPruebasSistemas);
		ResponseEntity<RespuestaListaRegistroPruebasAutomaticasDto> vResponseEntity = new ResponseEntity<RespuestaListaRegistroPruebasAutomaticasDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene listado Sistema sCertificados general.
	 * 
	 * @author: Sergio Mallea
	 * @Fecha: 13/12/2018
	 * @param
	 * @return Devuelve objeto Dto de respuesta
	 */
	@PostMapping(value = "/listadoSistemasCertificados")
	public ResponseEntity<ObtenerListaSistemasCertificacionDto> listadoSistemasCertificados() {

		ObtenerListaSistemasCertificacionDto vRespuestaDto = new ObtenerListaSistemasCertificacionDto();

		vRespuestaDto = iRecuperarListaSolicitudesCertificacionEnProcesoService
				.recuperaListaSolicitudSistemasCertificacion();

		ResponseEntity<ObtenerListaSistemasCertificacionDto> vResponseEntity = new ResponseEntity<ObtenerListaSistemasCertificacionDto>(
				vRespuestaDto, HttpStatus.OK);

		return vResponseEntity;
	}

	/**
	 * Muestra el tamaño de la consulta para sacar listado pruebas automaticas
	 * 
	 * @author: Levi Laura
	 * @Fecha: 04/12/2018
	 * @param SolicitudPruebasSistemasDto
	 * @return Devuelve conteo listado de pruebas automaticas*
	 */
	@PostMapping(value = "/obtieneTotalRegistrosPruebasAutomaticas")
	public ResponseEntity<RespuestaTamanioGenericoDto> obtieneTotalRegistrosPruebasAutomaticas(
			@RequestBody SolicitudPruebasSistemasDto pSolicitudPruebasSistemas) {
		RespuestaTamanioGenericoDto vRespuestaDto = new RespuestaTamanioGenericoDto();
		vRespuestaDto = iRegistroConsultaPruebasAutomaticasService
				.obtieneTotalRegistrosPruebasAutomaticas(pSolicitudPruebasSistemas);
		ResponseEntity<RespuestaTamanioGenericoDto> vResponseEntity = new ResponseEntity<RespuestaTamanioGenericoDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene el registro del listado de huellas digitales de sistema.
	 * 
	 * @author: Sergio Ichaso
	 * @Fecha: 18/12/20183
	 * @param pSistemaId, objeto de tipo long
	 * @return Devuelve objeto Dto de tipo RegistrarHuellaSistemaDto.*
	 */
//	@PostMapping(value = "/obtieneRegistroHuellasDigitalesPorSistema/{pSistemaId}")
	@RequestMapping(value = "/obtieneRegistroHuellasDigitales/{pSistemaId}", method = RequestMethod.GET)
	public ResponseEntity<ListaRegistroHuellasDigitalesDto> obtieneComponentesArchivos(@PathVariable Long pSistemaId) {
		ListaRegistroHuellasDigitalesDto vRespuestaDto = new ListaRegistroHuellasDigitalesDto();
		vRespuestaDto = iRegistroHuellaSistemaService.obtieneComponentesArchivos(pSistemaId);
		ResponseEntity<ListaRegistroHuellasDigitalesDto> vResponseEntity = new ResponseEntity<ListaRegistroHuellasDigitalesDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * Devuelve el listado de los porcentajes del consumo de los servicios SOAP (Certificación de Sistemas).
	 * 
	 * @author: junior.flores
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, objeto de tipo long
	 * @return Devuelve objeto Dto de tipo ListaSeguimientoCertificacionSistemasDto.
	 */
	@RequestMapping(value = "/listarSeguimientoCertificacionSistemas/{pSistemaId}/{pNit}", method = RequestMethod.GET)	
	public ResponseEntity<ListaSeguimientoCertificacionSistemasDto> registrarSolicitudSistemaaa(
			@PathVariable Long pSistemaId, @PathVariable Long pNit) {
		LOG.info("Inicio solicitud:" + pSistemaId);
		ListaSeguimientoCertificacionSistemasDto vRespuestaEntidad = new ListaSeguimientoCertificacionSistemasDto();
		vRespuestaEntidad = iSeguimientoCertificacionSistemasService.obtenerListaCertificacionSistemas(pSistemaId, pNit);
		ResponseEntity<ListaSeguimientoCertificacionSistemasDto> vResponseEntity = new ResponseEntity<ListaSeguimientoCertificacionSistemasDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}

	/**
	 * Obtiene el detalle de las pruebas de la etapa 0
	 * @param pSistemaId
	 * @param pNit
	 * 
	 * @author: rosario.garcia
	 * @fecha: 05/06/2019
	 * 
	 */
	/*@RequestMapping(value = "/obtenerDetalleCasosDePruebaEtapa0/{pSistemaId}/{pNit}", method = RequestMethod.GET)
	public ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto> obtenerDetalleCasosPruebaEtap0(@PathVariable Long pSistemaId, @PathVariable Long pNit ){
		LOG.info("Inicio solicitud:" + pSistemaId);
		ListaDetalleCasosDePruebaEtapa0Dto vRespuestaDto = new ListaDetalleCasosDePruebaEtapa0Dto();
		vRespuestaDto = iSeguimientoCertificacionSistemasService.obtenerListaDdetalleCasosDePruebaEtap0(pSistemaId, pNit);
		ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto> vResponseEntity = new ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity.toString());
		return vResponseEntity;
	}*/
	
	/**
	 * Obtiene el certificado de aprobacion de SFE.
	 * 
	 * @author: Freddy yuca
	 * @Fecha: 28/12/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
//	@PostMapping(value = "/obtenerCertificadoDeAprobacion")
//	public ResponseEntity<RespuestaCertificadoAprobacionDto> obtenerCertificadoDeAprobacion(
//			@RequestBody SolicitudCertificadoAprobacionDto pSolicitud) {
//		LOG.info("Inicio solicitud:" + pSolicitud);
//		RespuestaCertificadoAprobacionDto vRespuestaEntidad = new RespuestaCertificadoAprobacionDto();
//		vRespuestaEntidad = iAprobacionRechazoCertificacionService.obtenerCertificadoDeAprobacion(pSolicitud);
//		ResponseEntity<RespuestaCertificadoAprobacionDto> vResponseEntity = new ResponseEntity<RespuestaCertificadoAprobacionDto>(
//				vRespuestaEntidad, HttpStatus.OK);
//		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
//		return vResponseEntity;
//	}

	/**
	 * Guardar documento firmado facsimil.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 08/02/2019
	 * @param pSolicitudCertificacionFirma, objeto de tipo
	 *        SolicitudCertificadoAprobacionDto
	 * @return Devuelve objeto RespuestaCertificadoAprobacionDto de respuesta
	 */
//	@PostMapping(value = "/guardarDocumentoFirmado")
//	public ResponseEntity<RespuestaCertificadoAprobacionDto> guardarDocumentoFirmado(
//			@RequestBody SolicitudCertificadoAprobacionDto pSolicitudCertificacionFirma) {
//
//		RespuestaCertificadoAprobacionDto vRespuestaDto = new RespuestaCertificadoAprobacionDto();
//		vRespuestaDto = iAprobacionRechazoCertificacionService.guardarDocumentoAdjunto(pSolicitudCertificacionFirma);
//
//		ResponseEntity<RespuestaCertificadoAprobacionDto> vResponseEntity = new ResponseEntity<RespuestaCertificadoAprobacionDto>(
//				vRespuestaDto, HttpStatus.OK);
//
//		return vResponseEntity;
//	}

	/**
	 * Obtiene las notivicaciones registradas en base al documento generado y el contribuyente.
	 * 
	 * @author: Sergio.Ichaso
	 * @Fecha: 11/02/2019
	 * @param pSolicitudCertificacionFirma, objeto de tipo
	 *        SolicitudCertificadoAprobacionDto
	 * @return Devuelve objeto RespuestaCertificadoAprobacionDto de respuesta
	 */
//	@PostMapping(value = "/obtenerNotificacionElectronicaPorContribuyenteYActuado")
//	public ResponseEntity<NotificacionElectronicaListaDto> obtenerNotificacionElectronicaPorContribuyenteYActuado(
//			@RequestBody SolicitudCertificadoAprobacionDto pSolicitudCertificacionFirma) {
//
//		NotificacionElectronicaListaDto vRespuestaDto = new NotificacionElectronicaListaDto();
//		vRespuestaDto = iAprobacionRechazoCertificacionService
//				.obtenerNotificacionElectronicaPorContribuyenteYActuado(pSolicitudCertificacionFirma);
//
//		ResponseEntity<NotificacionElectronicaListaDto> vResponseEntity = new ResponseEntity<NotificacionElectronicaListaDto>(
//				vRespuestaDto, HttpStatus.OK);
//
//		return vResponseEntity;
//	}
	
	/**
	 * Obtiene el certificado de aprobacion de SFE.
	 * 
	 * @author: Freddy yuca
	 * @Fecha: 14/12/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
//	@PostMapping(value = "/obtenerCertificadoDeAprobacionHash")
//	public ResponseEntity<RespuestaCertificadoAprobacionDto> obtenerCertificadoDeAprobacionHash(
//			@RequestBody SolicitudCertificadoAprobacionDto pSolicitud) {
//		LOG.info("Inicio solicitud:" + pSolicitud);
//		RespuestaCertificadoAprobacionDto vRespuestaEntidad = new RespuestaCertificadoAprobacionDto();
//		vRespuestaEntidad = iAprobacionRechazoCertificacionService.generarHash(pSolicitud);
//		ResponseEntity<RespuestaCertificadoAprobacionDto> vResponseEntity = new ResponseEntity<RespuestaCertificadoAprobacionDto>(
//				vRespuestaEntidad, HttpStatus.OK);
//		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
//		return vResponseEntity;
//	}

	/**
	 * Obtiene el certificado de aprobacion de SFE.
	 * 
	 * @author: Freddy yuca
	 * @Fecha: 14/12/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
//	@PostMapping(value = "/guardarCertificadoDeAprobacionFirma")
//	public ResponseEntity<RespuestaCertificadoAprobacionDto> guardarCertificadoDeAprobacionFirma(
//			@RequestBody SolicitudCertificadoAprobacionDto pSolicitud) {
//		LOG.info("Inicio solicitud:" + pSolicitud);
//		RespuestaCertificadoAprobacionDto vRespuestaEntidad = new RespuestaCertificadoAprobacionDto();
//		vRespuestaEntidad = iAprobacionRechazoCertificacionService.incrustarFirma(pSolicitud);
//		ResponseEntity<RespuestaCertificadoAprobacionDto> vResponseEntity = new ResponseEntity<RespuestaCertificadoAprobacionDto>(
//				vRespuestaEntidad, HttpStatus.OK);
//		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
//		return vResponseEntity;
//	}
	
	/**
	 * Obtiene el certificado de aprobacion de SFE.
	 * 
	 * @author: Freddy yuca
	 * @Fecha: 20/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
//	@PostMapping(value = "/generarCertificadoDeAprobacion")
//	public ResponseEntity<RespuestaCertificadoAprobacionDto> generarCertificadoDeAprobacion(
//			@RequestBody SolicitudCertificadoAprobacionDto pSolicitud) {
//		LOG.info("Inicio solicitud:" + pSolicitud);
//		RespuestaCertificadoAprobacionDto vRespuestaEntidad = new RespuestaCertificadoAprobacionDto();
//		vRespuestaEntidad = iAprobacionRechazoCertificacionService.generarCertificadoDeAprobacion(pSolicitud);
//		ResponseEntity<RespuestaCertificadoAprobacionDto> vResponseEntity = new ResponseEntity<RespuestaCertificadoAprobacionDto>(
//				vRespuestaEntidad, HttpStatus.OK);
//		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
//		return vResponseEntity;
//	}
	
	/**
	 * Obtiene el datos de recertificacion.
	 * 
	 * @author: wilson.limachi
	 * @Fecha: 20/08/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
	@PostMapping(value = "/obtenerDatosReSolCertificacion")
	public ResponseEntity<DatosRecertificacion> obtenerDatosReSolCertificacion(@RequestBody DatosRecertificacion pSolicitud) 
	{
		LOG.info("Inicio solicitud:" + pSolicitud);
		DatosRecertificacion vRespuestaEntidad = new DatosRecertificacion();
		vRespuestaEntidad = iRecuperarDatosSistemasService.obtenerDatosReSolCertificacion(pSolicitud);
		ResponseEntity<DatosRecertificacion> vResponseEntity = new ResponseEntity<DatosRecertificacion>(vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}
	
	/**
	 * Procesa la recertificacion.
	 * 
	 * @author: wilson.limachi
	 * @Fecha: 20/08/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de respuesta
	 */
	@PostMapping(value = "/registrarRecertificacionSolicitudSistema")
	public ResponseEntity<RespuestaRegistrarSolicitudCertificacionDto> registrarRecertificacionSolicitudSistema(@RequestBody SolicitudRegistrarReCertificacionSistemasDto pSolicitud) 
	{
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaRegistrarSolicitudCertificacionDto vRespuestaEntidad = new RespuestaRegistrarSolicitudCertificacionDto();
		vRespuestaEntidad = iRegistroReCertificacionSistemasService.registrarRecertificacionSolicitudSistema(pSolicitud);
		ResponseEntity<RespuestaRegistrarSolicitudCertificacionDto> vResponseEntity = new ResponseEntity<RespuestaRegistrarSolicitudCertificacionDto>(vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}
	
	@PostMapping(value = "/aprobarSolicitudCertificacion")
	public ResponseEntity<RespuestaActualizacionGenericoDto> aprobarSolicitudCertificacion(@RequestBody SolicitudSolicitudCertificacionDto pSolicitud) 
	{
		
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iCambiarEstadoSolicitudCertificacionService.aprobarCertificacion(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}
	
	@PostMapping(value = "/rechazarSolicitudCertificacion")
	public ResponseEntity<RespuestaActualizacionGenericoDto> rechazarSolicitudCertificacion(@RequestBody SolicitudSolicitudCertificacionDto pSolicitud) 
	{
		
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iCambiarEstadoSolicitudCertificacionService.rechazarCertificacion(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaDto.toString());
		return vResponseEntity;
	}

	/**
	 * @Objetivo: obtiene Modalidad Contribuyente. 
	 * @Autor: wilson.limachi.
	 * @Date: 12/09/2019
	 */
	@PostMapping(value = "/obtieneModalidadContribuyente")
	public ResponseEntity<ContribuyentesModalidadesDto> obtieneModalidadContribuyente(@RequestBody ContribuyentesModalidadesDto pSolicitud) 
	{
		LOG.info("Inicio solicitud:" + pSolicitud);
		ContribuyentesModalidadesDto vRespuestaEntidad = new ContribuyentesModalidadesDto();
		vRespuestaEntidad = iRegistroCertificacionSistemasService.obtieneModalidadContribuyente(pSolicitud.getDatosEntradaNit());
		ResponseEntity<ContribuyentesModalidadesDto> vResponseEntity = new ResponseEntity<ContribuyentesModalidadesDto>(vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}
	
	@PostMapping(value = "/guardarRegistroVerificacionInsitu")
	public ResponseEntity<RespuestaRegistroVerificacionInsituDto> guardarRegistroVerificacionInsitu(
			@RequestBody SolicitudRegistroVerificacionInsituDto pSolicitud) {
		RespuestaRegistroVerificacionInsituDto vRespuestaDto = new RespuestaRegistroVerificacionInsituDto();
		vRespuestaDto = iRegistroVerificacionPruebasInsituService.guardar(pSolicitud);
		ResponseEntity<RespuestaRegistroVerificacionInsituDto> vResponseEntity = new ResponseEntity<RespuestaRegistroVerificacionInsituDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	@PostMapping(value = "/obtieneRegistroListaRegistroObservacionesInsitu")
	public ResponseEntity<RespuestaRegistroVerificacionInsituDto> obtieneRegistroListaRegistroObservacionesInsitu(@RequestBody RespuestaRegistroVerificacionInsituDto pSolicitud) 
	{
		RespuestaRegistroVerificacionInsituDto vRespuestaDto = new RespuestaRegistroVerificacionInsituDto();
		vRespuestaDto = iRegistrosObservacionesInsituService.obtieneRegistroListaRegistroObservacionesInsitu(pSolicitud.getDatosEntradaSistemaId(), pSolicitud.getDatosEntradaSolicitudCertificacionId(), pSolicitud.getDatosEntradaUsuarioId());
		ResponseEntity<RespuestaRegistroVerificacionInsituDto> vResponseEntity = new ResponseEntity<RespuestaRegistroVerificacionInsituDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
}