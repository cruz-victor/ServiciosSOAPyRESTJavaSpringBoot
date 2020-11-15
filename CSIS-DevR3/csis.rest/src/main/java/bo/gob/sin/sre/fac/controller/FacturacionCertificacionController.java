package bo.gob.sin.sre.fac.controller;



import java.io.Serializable;

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

import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
//import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionDomain;
//import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionDomain;
//import bo.gob.sin.recaudaciones.servicedomain.ICertificacionSistemasService;
import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasProveedorDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetalleCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroProveedorDto;
import bo.gob.sin.sre.fac.service.ICambiarEstadoSolicitudCertificacionService;
import bo.gob.sin.sre.fac.service.IPruebasCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.IDetalleCertificacionesSistemasService;
import bo.gob.sin.sre.fac.service.IRegistrarDiagramasCertificacionesService;
import bo.gob.sin.sre.fac.service.IRegistroHuellasSistemaContribuyenteService;
import bo.gob.sin.sre.fac.service.IRegistroHuellasSistemaService;
import bo.gob.sin.sre.fac.service.ISeguimientoCertificacionSistemasService;

@CrossOrigin(maxAge = 3600)
@RestController
// @CacheConfig(cacheNames = { "books" })
@RequestMapping(value = "/certificacion")
public class FacturacionCertificacionController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(FacturacionCertificacionController.class);

	//@Autowired
	//ISolicitudCertificacionDomain iSolicitudCertificacionDomain;

	@Autowired
	IRegistroHuellasSistemaService iRegistroHuellaSistemaService;

	@Autowired
	ICambiarEstadoSolicitudCertificacionService iCambiarEstadoSolicitudCertificacionService;
	
	@Autowired
	IPruebasCertificacionSistemasService iPruebasCertificacionSistemasService;
	
	@Autowired
	ISeguimientoCertificacionSistemasService iSeguimientoCertificacionSistemasService;
	
	@Autowired
	IRegistroHuellasSistemaContribuyenteService iRegistroHuellasSistemaContribuyenteService;
	
	@Autowired
	IRegistrarDiagramasCertificacionesService iRegistrarDiagramasCertificacionesService;
	
	@Autowired
	IDetalleCertificacionesSistemasService iDetalleCertificacionesSistemasService;
	
	// RCR
	// IASC - Cambio en ruta de acceso - 11/10/2018
	/*@PostMapping(value = "/listarSistemasPorIdContribuyente")
	public ListaSistemasDto listarSistemasPorIdContribuyente(
			@RequestBody SolicitudActualizaSolicitudCertificacionDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaActualizacionDto vRespuestaEntidad = new RespuestaActualizacionDto();
		ListaSistemasDto listaDto = iSolicitudCertificacionDomain.listarSistemasPorIdContribuyente(pSolicitud);
		vRespuestaEntidad.setOk(true);

		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return listaDto;
	}*/

	/**
	 * Realiza la encriptación de los archivos en formato CRC, MD5 y SHA
	 * 
	 * @author: Peter Flores
	 * @Fecha: 14/11/2018
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
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author: Peter Flores
	 * @Fecha: 14/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RegistrarHuellaSistemaDto.*
	 */
	@PostMapping(value = "/registrarHuellaDigitalSistema")
	public ResponseEntity<RegistroHuellasDigitalesDto> RegistrarHuellasDigitalesSistemas(
			@RequestBody ListaRegistroHuellasDigitalesDto pSolicitud) {
		RegistroHuellasDigitalesDto vRespuestaDto = new RegistroHuellasDigitalesDto();
		vRespuestaDto = iRegistroHuellaSistemaService.registrarHuellasDigitalesSistemas(pSolicitud);
		ResponseEntity<RegistroHuellasDigitalesDto> vResponseEntity = new ResponseEntity<RegistroHuellasDigitalesDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/**
	 * Obtiene el registro del listado de huellas digitales de sistema.
	 * 
	 * @author: Peter Flores
	 * @Fecha: 20/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RegistrarHuellaSistemaDto.*
	 */
	@RequestMapping(value = "/obtieneRegistroHuellasDigitales/{pSolicitud}", method = RequestMethod.GET)
	public ResponseEntity<ListaRegistroHuellasDigitalesDto> obtieneComponentesArchivos(@PathVariable Long pSolicitud) {
		ListaRegistroHuellasDigitalesDto vRespuestaDto = new ListaRegistroHuellasDigitalesDto();
		vRespuestaDto = iRegistroHuellaSistemaService.obtieneComponentesArchivos(pSolicitud);
		ResponseEntity<ListaRegistroHuellasDigitalesDto> vResponseEntity = new ResponseEntity<ListaRegistroHuellasDigitalesDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * Realiza la actualización de la tabla Pruebas de Certificación de Sistemas, para el inicio o el fin de las pruebas
	 * 
	 * @author: Peter Flores
	 * @Fecha: 05/06/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RespuestaActualizacionGenericoDto.*
	 */
	@PostMapping(value = "/actualizarPruebasCertificacionSistemas")
	public ResponseEntity<RespuestaActualizacionGenericoDto> actualizarPruebasCertificacionSistemas(
			@RequestBody SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud) {
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iPruebasCertificacionSistemasService.actualizarPruebasCertificacionSistemas(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(
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
	 * Realiza el reinicio de la tabla Pruebas de Certificación de Sistemas, para comenzar nuevamente con las pruebas
	 * 
	 * @author: Peter Flores
	 * @Fecha: 06/06/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RespuestaActualizacionGenericoDto.*
	 */
	@PostMapping(value = "/reiniciarPruebasCertificacionSistemas")
	public ResponseEntity<RespuestaActualizacionGenericoDto> reiniciarPruebasCertificacionSistemas(
			@RequestBody SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud) {
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iPruebasCertificacionSistemasService.reiniciarPruebasCertificacionSistemas(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * @author: rosario.garcia
	 * @fecha: 05/06/2019
	 * 
	 */
	@RequestMapping(value = "/obtenerDetalleCasosDePruebaEtapa0/{pSistemaId}/{pNit}", method = RequestMethod.GET)
	public ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto> obtenerDetalleCasosPruebaEtap0(@PathVariable Long pSistemaId, @PathVariable Long pNit ){
		LOG.info("Inicio solicitud:" + pSistemaId);
		ListaDetalleCasosDePruebaEtapa0Dto vRespuestaDto = new ListaDetalleCasosDePruebaEtapa0Dto();
		vRespuestaDto = iSeguimientoCertificacionSistemasService.obtenerListaDdetalleCasosDePruebaEtap0(pSistemaId, pNit);
		ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto> vResponseEntity = new ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity.toString());
		return vResponseEntity;
	}
	  
	/**
	 * Devuelve el listado de los porcentajes del consumo de los servicios SOAP (Certificación de Sistemas).
	 * 
	 * @author: junior.flores
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, objeto de tipo long
	 * @return Devuelve objeto Dto de tipo ListaDetalleSeguimientoCertificacionSistemasDto.
	 */
	@RequestMapping(value = "/listarDetalleSeguimientoCertificacionSistemas/{pSistemaId}/{pEtapaPruebaCertificacionId}/{pNit}", method = RequestMethod.GET)	
	public ResponseEntity<ListaDetalleSeguimientoCertificacionSistemasDto> registrarSolicitudSistemaaa(
			@PathVariable Long pSistemaId, @PathVariable Integer pEtapaPruebaCertificacionId, @PathVariable Long pNit) {
		LOG.info("Inicio solicitud:" + pSistemaId);
		ListaDetalleSeguimientoCertificacionSistemasDto vRespuestaEntidad = new ListaDetalleSeguimientoCertificacionSistemasDto();
		vRespuestaEntidad = iSeguimientoCertificacionSistemasService.obtenerListaDetalleCertificacionSistemas(pSistemaId, pEtapaPruebaCertificacionId, pNit);
		ResponseEntity<ListaDetalleSeguimientoCertificacionSistemasDto> vResponseEntity = new ResponseEntity<ListaDetalleSeguimientoCertificacionSistemasDto>(
				vRespuestaEntidad, HttpStatus.OK);
		LOG.info("Finalizacion:" + vRespuestaEntidad.toString());
		return vResponseEntity;
	}
	
	/** 
	 * Obtener la lista de contribuyentes
	 * 
	 * @author: junior.flores 
	 * @Fecha: 18/06/2019
	 * @param  pSistemaContribuyenteId, Código unico del sistema contribuyente
	 * 		   pEstadoSistemaContribuyenteId, Código unico del estado del sistema contribuyente 
	 * @return   Devuelve el objeto ListaSistemasProveedorDto.
	 */
	@PostMapping(value = "/consultaSistemasAsociadosContribuyente")
	public ResponseEntity<ListaSistemasProveedorDto> consultaSistemasAsociadosContribuyente(@RequestBody SolicitudRegistroProveedorDto pSolicitud) {
		ListaSistemasProveedorDto vRespuesta = iSeguimientoCertificacionSistemasService.consultaSistemasAsociadosContribuyente(pSolicitud.getContribuyenteId(), pSolicitud.getEstadoSistemaContribuyenteId());
		ResponseEntity<ListaSistemasProveedorDto> vResponseEntity = new ResponseEntity<ListaSistemasProveedorDto>(vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificación de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Información necesaria para registrar la etapa de prueba (detalle)
	* @return Devuelve un valor booleano que indica la transacción
	*/
	@PostMapping(value = "/registroPruebaDetalleOpcional")
	public ResponseEntity<RespuestaActualizacionGenericoDto> registroPruebaDetalleOpcional(
			@RequestBody SeguimientoDetalleCertificacionSistemasDto pSolicitud) {
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto = iPruebasCertificacionSistemasService.registroPruebaDetalleOpcional(pSolicitud);
		ResponseEntity<RespuestaActualizacionGenericoDto> vResponseEntity = new ResponseEntity<RespuestaActualizacionGenericoDto>(
				vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * @descripcion Servicio que permite obtener los casos de prueba sugeridas de la etapa 0
	 * 
	 * @author: rosario.garcia
	 * @fecha: 10/07/2019
	 * 
	 */
	@RequestMapping(value = "/obtenerCasosDePruebaSugeridasEtapa0/{pSistemaId}", method = RequestMethod.GET)
	public ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto> obtenerCasosPruebaSugeridasEtapa0(@PathVariable Long pSistemaId ){
		LOG.info("Inicio solicitud:" + pSistemaId);
		ListaDetalleCasosDePruebaEtapa0Dto vRespuestaDto = new ListaDetalleCasosDePruebaEtapa0Dto();
		vRespuestaDto = iSeguimientoCertificacionSistemasService.obtenerListaCasosDePruebaSugeridasEtap0(pSistemaId);
		ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto> vResponseEntity = new ResponseEntity<ListaDetalleCasosDePruebaEtapa0Dto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity.toString());
		return vResponseEntity;
	}
	
	/**
	 * @descripcion Servicio que permite obtener los casos de prueba sugeridas de la etapa 0
	 * 
	 * @author: rosario.garcia
	 * @fecha: 10/07/2019
	 * 
	 */
	@PostMapping(value = "/listaEtapaFaseDos")
	public ResponseEntity<ListaSeguimientoCertificacionSistemasFaseDosDto> listaEtapaFaseDos(@RequestBody ListaSeguimientoCertificacionSistemasFaseDosDto pSolicitud){
		
		ListaSeguimientoCertificacionSistemasFaseDosDto vRespuestaDto = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		vRespuestaDto = iSeguimientoCertificacionSistemasService.ListaEtapaFaseDos(pSolicitud);
		ResponseEntity<ListaSeguimientoCertificacionSistemasFaseDosDto> vResponseEntity = new ResponseEntity<ListaSeguimientoCertificacionSistemasFaseDosDto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity.toString());
		return vResponseEntity;
	}
	
	/**
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 19/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo ListaDetalleHuellaDto.*
	 */
	@PostMapping(value = "/obtieneComponentesArchivosCertificados")
	public ResponseEntity<ListaDetalleHuellaDto> RegistrarHuellasDigitalesSistemas(@RequestBody ListaDetalleHuellaDto pSolicitud) {
		ListaDetalleHuellaDto vRespuestaDto = new ListaDetalleHuellaDto();
		vRespuestaDto = iRegistroHuellasSistemaContribuyenteService.obtieneComponentesArchivosCertificados (pSolicitud.getDatoEntradaIdSistema(), pSolicitud.getDatoEntradaIdSolicitudCertificacion(), pSolicitud.getDatoEntradaIdContribuyente());
		ResponseEntity<ListaDetalleHuellaDto> vResponseEntity = new ResponseEntity<ListaDetalleHuellaDto>(vRespuestaDto, HttpStatus.OK);
		LOG.info("Finalizacion:" + vResponseEntity.toString());
		return vResponseEntity;
	}
	
		/** 
	 * Realiza el registro del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 23/09/2019
	 * @param  pSolicitud, Todos los datos correspondientes a Diagrama de Certificaciones 		   
	 * @return   Devuelve el objeto DiagramasCertificacionesDto con la respuesta de la transacción.
	 */
	@PostMapping(value = "/registraDiagramasCertificaciones")
	public ResponseEntity<DiagramasCertificacionesDto> registraDiagramasCertificaciones(@RequestBody DiagramasCertificacionesDto pSolicitud) {
		DiagramasCertificacionesDto vRespuesta = iRegistrarDiagramasCertificacionesService.registraDiagramasCertificaciones(pSolicitud);
		ResponseEntity<DiagramasCertificacionesDto> vResponseEntity = new ResponseEntity<DiagramasCertificacionesDto>(vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author: Wilson Limachi Choque
	 * @Fecha: 20/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RespuestaOperacionDto.*
	 */
	@PostMapping(value = "/registrarComponentesArchivos")
	public ResponseEntity<RespuestaOperacionDto> registrarComponentesArchivos(@RequestBody RespuestaDetalleHuellaDto pSolicitud) 
	{
		RespuestaOperacionDto  vRespuestaDto = new RespuestaOperacionDto ();
		vRespuestaDto = iRegistroHuellaSistemaService.registrarComponentesArchivos(pSolicitud);
		ResponseEntity<RespuestaOperacionDto> vResponseEntity = new ResponseEntity<RespuestaOperacionDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/**
	 * Realiza el registro del listado de las huellas digitales del sistema
	 * 
	 * @author: Wilson Limachi Choque
	 * @Fecha: 24/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo RespuestaOperacionDto.*
	 */
	@PostMapping(value = "/actualizaCanceladoComponentesArchivosCertificados")
	public ResponseEntity<RespuestaOperacionDto> registrarComponentesArchivos(@RequestBody RespuestaOperacionDto pSolicitud) 
	{
		RespuestaOperacionDto  vRespuestaDto = new RespuestaOperacionDto ();
		vRespuestaDto = iRegistroHuellasSistemaContribuyenteService.actualizaComponentesArchivosCertificados(pSolicitud.getDatosEntradaArchivoId(), pSolicitud.getDatosEntradaUsuarioRegistro());
		ResponseEntity<RespuestaOperacionDto> vResponseEntity = new ResponseEntity<RespuestaOperacionDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}

	/** 
	 * Realiza el registro del diagrama de certificaciones 
	 * 
	 * @author: reynaldo.chambi
	 * @Fecha: 23/09/2019
	 * @param  pSolicitud, Todos los datos correspondientes a Detalle de Certificaciones 		   
	 * @return   Devuelve el objeto DiagramasCertificacionesDto con la respuesta de la transacción.
	 */
	@PostMapping(value = "/registraDetalleCertificacionesSistemas")
	public ResponseEntity<DetallesCertificacionesSistemasDto> registraDetalleCertificacionesSistemas(@RequestBody DetallesCertificacionesSistemasDto pSolicitud) {
		DetallesCertificacionesSistemasDto vRespuesta = iDetalleCertificacionesSistemasService.registraDetalleCertificacionesSistemas(pSolicitud);
		ResponseEntity<DetallesCertificacionesSistemasDto> vResponseEntity = new ResponseEntity<DetallesCertificacionesSistemasDto>(vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 24/09/2019
	 * @param  pSolicitud, Todos los datos para obtener el listado de Diagramas de Certificaciones   		   
	 * @return   Devuelve el objeto ListaDiagramasCertificacionesDto.
	 */
	@PostMapping(value = "/obtieneListaDiagramasCertificaciones")
	public ResponseEntity<ListaDiagramasCertificacionesDto> recuperaListaDiagramasCertificaciones(@RequestBody ListaDiagramasCertificacionesDto pSolicitud) {
		ListaDiagramasCertificacionesDto vRespuesta = iRegistrarDiagramasCertificacionesService.recuperaListaDiagramasCertificaciones(pSolicitud);
		ResponseEntity<ListaDiagramasCertificacionesDto> vResponseEntity = new ResponseEntity<ListaDiagramasCertificacionesDto>(vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: reynaldo.chambi 
	 * @Fecha: 26/09/2019
	 * @param  pSolicitud, Todos los datos para obtener el listado de Diagramas de Certificaciones   		   
	 * @return   Devuelve el objeto ListaDiagramasCertificacionesDto.
	 */
	@PostMapping(value = "/obtenerListaDetalleCertificacionSistemas")
	public ResponseEntity<RespuestaListaDetalleCertificacionesSistemasDto> obtenerListaDetalleCertificacionSistemas(@RequestBody DetallesCertificacionesSistemasDto pSolicitud) {
		RespuestaListaDetalleCertificacionesSistemasDto vRespuesta = iDetalleCertificacionesSistemasService.obtenerListaDetalleCertificacionSistemas(pSolicitud.getSolicitudCertificacionId(), pSolicitud.getSistemaId());
		ResponseEntity<RespuestaListaDetalleCertificacionesSistemasDto> vResponseEntity = new ResponseEntity<RespuestaListaDetalleCertificacionesSistemasDto>(vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/** 
	 * Realiza la actualizacion del estado de diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha: 27/09/2019
	 * @param  pDiagramaCertificacionId	   
	 * @return   Devuelve el objeto RespuestaOperacionDto
	 */
	@PostMapping(value = "/actualizaDiagramasCertificaciones")
	public ResponseEntity<RespuestaOperacionDto> actualizaDiagramasCertificaciones(@RequestBody RespuestaOperacionDto pSolicitud) 
	{
		RespuestaOperacionDto  vRespuestaDto = new RespuestaOperacionDto ();
		vRespuestaDto = iRegistrarDiagramasCertificacionesService.actualizaDiagramasCertificaciones(pSolicitud.getDatosEntradaArchivoId(), pSolicitud.getDatosEntradaId(), pSolicitud.getDatosEntradaUsuarioRegistro());
		ResponseEntity<RespuestaOperacionDto> vResponseEntity = new ResponseEntity<RespuestaOperacionDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
	}
	
	/** 
	 * Realiza Anulación de detalle certificacion sistemas  
	 * 
	 * @author: reynaldo.chambi 
	 * @Fecha: 27/09/2019
	 * @param  pSolicitud, Objeto a anular.   		   
	 * @return   Devuelve el objeto DetallesCertificacionesSistemasDto.
	 */
	@PostMapping(value = "/anularDetalleCertificacionSistemas")
	public ResponseEntity<DetallesCertificacionesSistemasDto> anularDetalleCertificacionSistemas(@RequestBody DetallesCertificacionesSistemasDto pSolicitud) {
		DetallesCertificacionesSistemasDto vRespuesta = iDetalleCertificacionesSistemasService.anularDetalleCertificacionesSistemas(pSolicitud);
		ResponseEntity<DetallesCertificacionesSistemasDto> vResponseEntity = new ResponseEntity<DetallesCertificacionesSistemasDto>(vRespuesta, HttpStatus.OK);
		return vResponseEntity;
	}
}