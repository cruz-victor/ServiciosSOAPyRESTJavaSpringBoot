package bo.gob.sin.sre.fac.cfec.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.fac.cfec.IPortalWeb;
import bo.gob.sin.sre.fac.cfec.dto.GenerarCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaObtenerComprasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtenerCompraIndividualDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtenerCompraPorDia;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoRestDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICompras;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICoreFacturacionSoap;
import bo.gob.sin.sre.fac.cfec.servicedomain.IOperacionesCufService;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@CrossOrigin(maxAge = 3600)
@RestController
// @CacheConfig(cacheNames = { "books" })
@RequestMapping(value = "/facturacion")
public class FacturacionController {
	private static final Logger LOG = LoggerFactory.getLogger(FacturacionController.class);

	@Autowired
	IOperacionesCufService iOperacionesCufService;
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
	@Autowired
	IValidarRecepcion iValRec;
	@Autowired
	IPortalWeb iPortalWeb;
	@Autowired
	ICompras iCompras;

	/**
	 * Recuperar Reporte Cabecera y Detalle de Solicitud de certificacion
	 * 
	 * @author: Reynaldo Chambi Rojas
	 * @Fecha: 03/05/2019
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve string del CUF.
	 */
	@PostMapping("/generarCuf")
	public String generarCuf(@RequestBody GenerarCufDto pSolicitud) {
		String vRespuesta = iOperacionesCufService.generadorCUF(pSolicitud);
		return vRespuesta;
	}

	/**
	 * Recuperar Cabecera y Detalle de Solicitud de facturacion
	 * 
	 * @author: Reynaldo Chambi Rojas
	 * @Fecha: 13/05/2019
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve respuesta validado.
	 */
	@PostMapping(value = "/recepcionFacturaManual")
	public RespuestaListaRegistroRecepcionesSoapDto recepcionFacturaManual(
			@RequestBody XmlRecepcionGenericoRestDto pSolicitud) {
		ModelMapper mapeo = new ModelMapper();
		XmlRecepcionGenericoDto vSolicitud = mapeo.map(pSolicitud, XmlRecepcionGenericoDto.class);

		int vEtapa = pSolicitud.getvEtapa();
		RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap
				.CoreFacturacionPrincipal(vSolicitud, vEtapa);
		return vRespuestaDto;
	}

	/**
	 * @autor edwin.coro
	 * @descripción servicio que contempla la etapa de recepcion de documento fiscal
	 *              - portal web
	 * @param pSolicitud dto XmlRecepcionGenericoDto
	 * @return dto RespuestaListaRegistroRecepcionesSoapDto con resultado de etapa
	 *         recepcion
	 * @fecha 05/06/2019
	 */
	@PostMapping(value = "/recepcionFacturaPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaListaRegistroRecepcionesSoapDto> recepcionFacturaPortalWeb(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		try {
			pSolicitud.setvOrigenServicio(Parametros.F_MODALIDAD_PORTAL_WEB);
			RespuestaListaRegistroRecepcionesSoapDto vResultado = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(pSolicitud, Parametros.Recepcion);
			if (!vResultado.getListaCodigosRespuestas().isEmpty()) {
				vResultado.getListaDescripcionesRespuestas()
						.addAll(iValRec.obtenerDescripcionesCodigosRespuesta(vResultado.getListaCodigosRespuestas()));
			}
			return new ResponseEntity<>(vResultado, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("recepcionFacturaPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaListaRegistroRecepcionesSoapDto(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @autor edwin.coro
	 * @descripción servicio que combina las etapas de validacion de recepcion de
	 *              documento fiscal - portal web
	 * @param pSolicitud dto XmlRecepcionGenericoDto
	 * @return dto RespuestaListaRegistroRecepcionesSoapDto con resultado de etapa
	 *         validacion de recepción
	 * @fecha 05/06/2019
	 */
	@PostMapping(value = "/validacionRecepcionFacturaPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaListaRegistroRecepcionesSoapDto> validacionRecepcionFacturaPortalWeb(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		try {
			pSolicitud.setvOrigenServicio(Parametros.F_MODALIDAD_PORTAL_WEB);
			RespuestaListaRegistroRecepcionesSoapDto vResultado = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(pSolicitud, Parametros.ValidacionRecepcion);
			if (!vResultado.getListaCodigosRespuestas().isEmpty()) {
				vResultado.getListaDescripcionesRespuestas()
						.addAll(iValRec.obtenerDescripcionesCodigosRespuesta(vResultado.getListaCodigosRespuestas()));
			}
			return new ResponseEntity<>(vResultado, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("validacionRecepcionFacturaPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaListaRegistroRecepcionesSoapDto(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/anulacionFacturaPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaListaRegistroRecepcionesSoapDto> anulacionFacturaPortalWeb(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		try {
			pSolicitud.setvOrigenServicio(Parametros.F_MODALIDAD_PORTAL_WEB);
			RespuestaListaRegistroRecepcionesSoapDto vResultado = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(pSolicitud, Parametros.Anulacion);
			if (!vResultado.getListaCodigosRespuestas().isEmpty()) {
				vResultado.getListaDescripcionesRespuestas()
						.addAll(iValRec.obtenerDescripcionesCodigosRespuesta(vResultado.getListaCodigosRespuestas()));
			}
			return new ResponseEntity<>(vResultado, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("anulacionFacturaPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaListaRegistroRecepcionesSoapDto(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/validacionAnulacionFacturaPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaListaRegistroRecepcionesSoapDto> validacionAnulacionFacturaPortalWeb(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		try {
			pSolicitud.setvOrigenServicio(Parametros.F_MODALIDAD_PORTAL_WEB);
			RespuestaListaRegistroRecepcionesSoapDto vResultado = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(pSolicitud, Parametros.ValidacionAnulacion);
			if (!vResultado.getListaCodigosRespuestas().isEmpty()) {
				vResultado.getListaDescripcionesRespuestas()
						.addAll(iValRec.obtenerDescripcionesCodigosRespuesta(vResultado.getListaCodigosRespuestas()));
			}
			return new ResponseEntity<>(vResultado, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("validacionAnulacionFacturaPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaListaRegistroRecepcionesSoapDto(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @autor edwin.coro
	 * @descripción servicio que combina las etapas de recepcion y validacion de
	 *              recepcion de documento fiscal - portal web
	 * @param pSolicitud dto XmlRecepcionGenericoDto
	 * @return dto RespuestaListaRegistroRecepcionesSoapDto con resultado de etapa
	 *         recepcion o validacion de recepción
	 * @fecha 10/06/2019
	 */
	@PostMapping(value = "/recepcionValidacionDocFiscalPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaListaRegistroRecepcionesSoapDto> recepcionValidacionDocFiscalPortalWeb(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		try {
			pSolicitud.setvOrigenServicio(Parametros.F_MODALIDAD_PORTAL_WEB);
			RespuestaListaRegistroRecepcionesSoapDto vResultadoReg = iPortalWeb.recepcionarValidarPortalWeb(pSolicitud);
			return new ResponseEntity<>(vResultadoReg, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("recepcionValidacionFacturaPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaListaRegistroRecepcionesSoapDto(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @autor edwin.coro
	 * @descripción servicio que combina las etapas de recepcion de anulacion y
	 *              validacion de recepcion anulacion de documento fiscal - portal
	 *              web
	 * @param pSolicitud dto XmlRecepcionGenericoDto
	 * @return dto RespuestaListaRegistroRecepcionesSoapDto con resultado de etapa
	 *         recepcion anulacion o validacion de recepción anulacion
	 * @fecha 10/06/2019
	 */
	@PostMapping(value = "/anulacionValidacionDocFiscalPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaListaRegistroRecepcionesSoapDto> anulacionValidacionDocFiscalPortalWeb(
			@RequestBody XmlRecepcionGenericoDto pSolicitud) {
		try {
			pSolicitud.setvOrigenServicio(Parametros.F_MODALIDAD_PORTAL_WEB);
			RespuestaListaRegistroRecepcionesSoapDto vResultadoRegAn = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(pSolicitud, Parametros.Anulacion);
			if (!vResultadoRegAn.getListaCodigosRespuestas().isEmpty()) {
				vResultadoRegAn.getListaDescripcionesRespuestas().addAll(
						iValRec.obtenerDescripcionesCodigosRespuesta(vResultadoRegAn.getListaCodigosRespuestas()));
			} else {
				pSolicitud.setP_recepcion_anulacion_id(vResultadoRegAn.getCodigoRecepcion());
				RespuestaListaRegistroRecepcionesSoapDto vResultadoValAn = iCoreFacturacionSoap
						.CoreFacturacionPrincipal(pSolicitud, Parametros.ValidacionAnulacion);
				if (!vResultadoValAn.getListaCodigosRespuestas().isEmpty()) {
					vResultadoValAn.getListaDescripcionesRespuestas().addAll(
							iValRec.obtenerDescripcionesCodigosRespuesta(vResultadoValAn.getListaCodigosRespuestas()));
				}
				vResultadoRegAn = vResultadoValAn;
			}
			return new ResponseEntity<>(vResultadoRegAn, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("anulacionValidacionDocFiscalPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaListaRegistroRecepcionesSoapDto(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Recuperar Reporte Cabecera y Detalle de Solicitud de certificacion
	 * 
	 * @author: Reynaldo Chambi Rojas
	 * @Fecha: 03/08/2019
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve string del CUF.
	 */
	@PostMapping("/obtenerFacturaVentas")
	public RespuestaFacturaVentasDto obtenerFacturaVentas(@RequestBody SolicitudObtencionFacturaVentasDto pSolicitud) {

		try {
			return iOperacionesCufService.obtenerFacturaVentas(pSolicitud);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
		}

		return null;
	}

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 19/08/2019
	 */
	@PostMapping(value = "/obtenerFacturaPortalWeb", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaFacturaParcialDto> obtenerFacturaPortalWeb(
			@RequestBody SolicitudFacturaParcialDto pSolicitud) {
		try {
			RespuestaFacturaParcialDto vResultado = iValRec.obtenerFacturaDatosParcial(pSolicitud);
			return new ResponseEntity<>(vResultado, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("obtenerFacturaDatosParcial || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			return new ResponseEntity<>(new RespuestaFacturaParcialDto(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Recuperar Reporte Cabecera y Detalle de Solicitud de certificacion
	 * 
	 * @author: Reynaldo Chambi Rojas
	 * @Fecha: 03/08/2019
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve string del CUF.
	 */
	@PostMapping("/obtenerCompraIndividual")
	public RespuestaObtenerComprasDto obtenerFacturaVentasIndividual(
			@RequestBody SolicitudObtenerCompraIndividualDto pSolicitud) {
		RespuestaObtenerComprasDto vRespuesta = new RespuestaObtenerComprasDto();
		try {
			vRespuesta = iCompras.recepcionBdComprasIndividual(pSolicitud.getpCuf(), pSolicitud.getpNumeroFactura(),
					pSolicitud.getpNit().toString(), pSolicitud.getpCodigoSistema(), pSolicitud.getpCufd(),
					pSolicitud.getpCuis(), pSolicitud.getpCodigoSucursal(), pSolicitud.getpCodigoPuntoVenta(),
					pSolicitud.getpCodigoAmbiente());
			if (vRespuesta == null) {
				vRespuesta = new RespuestaObtenerComprasDto();
				List<Integer> vListaErrores = new ArrayList<Integer>();
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_INEXISTENTE);
				vRespuesta.setListaCodigosRespuestas(vListaErrores);
			}

		} catch (Exception e) {
			LOG.error("obtenerFacturaVentas || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
		}

		return vRespuesta;
	}

	/**
	 * Recuperar Reporte Cabecera y Detalle de Solicitud de certificacion
	 * 
	 * @author: Reynaldo Chambi Rojas
	 * @Fecha: 03/08/2019
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve string del CUF.
	 */
	@PostMapping("/obtenerComprasPorDia")
	public List<RespuestaObtenerComprasDto> obtenerFacturaVentasPorDia(
			@RequestBody SolicitudObtenerCompraPorDia pSolicitud) {

		List<RespuestaObtenerComprasDto> vRespuesta = new ArrayList<>();

		try {
			Date vFecha = new SimpleDateFormat("yyyy-MM-dd").parse(pSolicitud.getpFecha());
			vRespuesta = iCompras.recepcionBdComprasPorDia(vFecha, vFecha, pSolicitud.getpNumeroDocumento(),
					pSolicitud.getpCodigoSistema(), pSolicitud.getpCufd(), pSolicitud.getpCuis(),
					pSolicitud.getpCodigoSucursal(), pSolicitud.getpCodigoPuntoVenta(),
					pSolicitud.getpCodigoAmbiente());
			if (vRespuesta == null) {
				vRespuesta = new ArrayList<>();
				List<Integer> vListaErrores = new ArrayList<Integer>();
				RespuestaObtenerComprasDto vCompras = new RespuestaObtenerComprasDto();
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_INEXISTENTE);
				vCompras.setListaCodigosRespuestas(vListaErrores);
				vRespuesta.add(vCompras);
			}
		} catch (Exception e) {
			LOG.error("obtenerFacturaVentas || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
		}

		return vRespuesta;
	}

}