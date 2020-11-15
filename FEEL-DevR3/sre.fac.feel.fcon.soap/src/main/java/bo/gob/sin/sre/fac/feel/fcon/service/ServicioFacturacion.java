package bo.gob.sin.sre.fac.feel.fcon.service;

import java.text.Format;
import java.text.SimpleDateFormat;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.fcon.interfaces.ISolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.fcon.interfaces.ISolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudValidacionRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.Respuesta;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICoreFacturacionSoap;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@WebService(targetNamespace = "https://siat.impuestos.gob.bo/FacturaElectronicaContingencia")
public class ServicioFacturacion implements IServicioFacturaElectronicaContingencia {

	private static final Logger LOG = LoggerFactory.getLogger(ServicioFacturacion.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;

	@Override
	public int verificarConexion() {
		return CodigosMensajesServiciosSOAPServiceImpl.COMUNICACION_EXITOSA;
	}

	@Override
	public IRespuesta recepcion(ISolicitudRecepcion solicitud) {
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema(solicitud.getCodigoSistema());
			vArchivoXml.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vArchivoXml.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vArchivoXml.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vArchivoXml.setP_nit(solicitud.getNit());
			vArchivoXml.setP_cuis(solicitud.getCuis());
			vArchivoXml.setP_cufd(solicitud.getCufd());
			vArchivoXml.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vArchivoXml.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vArchivoXml.setP_sucursal_id(solicitud.getCodigoSucursal());
			if (solicitud.getCodigoPuntoVenta() != null && solicitud.getCodigoPuntoVenta() != 0) {
				vArchivoXml.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			}
			Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			vArchivoXml.setP_fecha_envio(formatter.format(solicitud.getFechaEnvio()));
			vArchivoXml.setV_archivo(solicitud.getArchivo());
			vArchivoXml.setV_hash_archivo(solicitud.getHashArchivo());
			vArchivoXml.setvOrigenServicio(Parametros.F_MODALIDAD_FACTURA_ELECTRONICA);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(vArchivoXml, Parametros.Recepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());
			vRespuesta.setListaDescripcionesRespuestas(vRespuestaDto.getListaDescripcionesRespuestas());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
		LOG.info("recepcionFactura ||Finalizado");
		return vRespuesta;
	}

	@Override
	public IRespuesta recepcionPaquete(ISolicitudRecepcion solicitud) {
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionPaquete ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema(solicitud.getCodigoSistema());
			vArchivoXml.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vArchivoXml.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vArchivoXml.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vArchivoXml.setP_nit(solicitud.getNit());
			vArchivoXml.setP_cuis(solicitud.getCuis());
			vArchivoXml.setP_cufd(solicitud.getCufd());
			vArchivoXml.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vArchivoXml.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vArchivoXml.setP_sucursal_id(solicitud.getCodigoSucursal());
			if (solicitud.getCodigoPuntoVenta() != null && solicitud.getCodigoPuntoVenta() > 0) {
				vArchivoXml.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			}
			Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			vArchivoXml.setP_fecha_envio(formatter.format(solicitud.getFechaEnvio()));
			vArchivoXml.setV_archivo(solicitud.getArchivo());
			vArchivoXml.setV_hash_archivo(solicitud.getHashArchivo());
			vArchivoXml.setvOrigenServicio(Parametros.F_MODALIDAD_FACTURA_ELECTRONICA);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(vArchivoXml, Parametros.RecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());
			vRespuesta.setListaDescripcionesRespuestas(vRespuestaDto.getListaDescripcionesRespuestas());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("recepcionPaquete||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
		LOG.info("recepcionPaquete ||Finalizado");
		return vRespuesta;
	}

	@Override
	public IRespuesta validacionRecepcion(ISolicitudValidacionRecepcion solicitud) {
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("validacionRecepcion ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema(solicitud.getCodigoSistema());
			vArchivoXml.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vArchivoXml.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vArchivoXml.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vArchivoXml.setP_nit(solicitud.getNit());
			vArchivoXml.setP_cuis(solicitud.getCuis());
			vArchivoXml.setP_cufd(solicitud.getCufd());
			vArchivoXml.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vArchivoXml.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vArchivoXml.setP_sucursal_id(solicitud.getCodigoSucursal());
			if (solicitud.getCodigoPuntoVenta() != null && solicitud.getCodigoPuntoVenta() != 0) {
				vArchivoXml.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			}
			vArchivoXml.setV_recepcionId(solicitud.getCodigoRecepcion());
			vArchivoXml.setvOrigenServicio(Parametros.F_MODALIDAD_FACTURA_ELECTRONICA);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(vArchivoXml, Parametros.ValidacionRecepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());
			vRespuesta.setListaDescripcionesRespuestas(vRespuestaDto.getListaDescripcionesRespuestas());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("validacionRecepcion||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
		LOG.info("validacionRecepcion ||Finalizado");
		return vRespuesta;
	}

	@Override
	public IRespuesta validacionRecepcionPaquete(ISolicitudValidacionRecepcion solicitud) {
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("validacionRecepcionPaquete ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema(solicitud.getCodigoSistema());
			vArchivoXml.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vArchivoXml.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vArchivoXml.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vArchivoXml.setP_nit(solicitud.getNit());
			vArchivoXml.setP_cuis(solicitud.getCuis());
			vArchivoXml.setP_cufd(solicitud.getCufd());
			vArchivoXml.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vArchivoXml.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vArchivoXml.setP_sucursal_id(solicitud.getCodigoSucursal());
			if (solicitud.getCodigoPuntoVenta() != null && solicitud.getCodigoPuntoVenta() > 0) {
				vArchivoXml.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			}
			vArchivoXml.setV_recepcionPaqueteId(solicitud.getCodigoRecepcion());
			vArchivoXml.setvOrigenServicio(Parametros.F_MODALIDAD_FACTURA_ELECTRONICA);
			RespuestaValidacionRecepcionPaqueteDto vRespuestaDto = iCoreFacturacionSoap
					.CoreFacturacionPrincipalPaquete(vArchivoXml, Parametros.ValidacionRecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaErroresDetalles(vRespuestaDto.getListaErroresDetalles());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());
			vRespuesta.setListaDescripcionesRespuestas(vRespuestaDto.getListaDescripcionesRespuestas());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("validacionRecepcionPaquete||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
		LOG.info("validacionRecepcionPaquete ||Finalizado");
		return vRespuesta;
	}

	@Override
	public IRespuesta anulacion(ISolicitudAnulacion solicitud) {
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("anulacion||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema(solicitud.getCodigoSistema());
			vArchivoXml.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vArchivoXml.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vArchivoXml.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vArchivoXml.setP_nit(solicitud.getNit());
			vArchivoXml.setP_cuis(solicitud.getCuis());
			vArchivoXml.setP_cufd(solicitud.getCufd());
			vArchivoXml.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vArchivoXml.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vArchivoXml.setP_sucursal_id(solicitud.getCodigoSucursal());
			vArchivoXml.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			vArchivoXml.setP_numero_documento_fiscal(solicitud.getNumeroDocumentoFiscal());
			vArchivoXml.setP_cuf(solicitud.getCuf());
			vArchivoXml.setP_motivo_anulacion_id(solicitud.getCodigoMotivo());
			vArchivoXml.setP_caed(solicitud.getCaed());
			vArchivoXml.setvOrigenServicio(Parametros.F_MODALIDAD_FACTURA_ELECTRONICA);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(vArchivoXml, Parametros.Anulacion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());
			vRespuesta.setListaDescripcionesRespuestas(vRespuestaDto.getListaDescripcionesRespuestas());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("anulacion||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
		LOG.info("anulacion||Finalizado");
		return vRespuesta;
	}

	@Override
	public IRespuesta validacionAnulacion(ISolicitudValidacionAnulacion solicitud) {
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("validacionAnulacion ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema(solicitud.getCodigoSistema());
			vArchivoXml.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vArchivoXml.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vArchivoXml.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vArchivoXml.setP_nit(solicitud.getNit());
			vArchivoXml.setP_cuis(solicitud.getCuis());
			vArchivoXml.setP_cufd(solicitud.getCufd());
			vArchivoXml.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vArchivoXml.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vArchivoXml.setP_sucursal_id(solicitud.getCodigoSucursal());
			vArchivoXml.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			vArchivoXml.setP_numero_documento_fiscal(solicitud.getNumeroDocumentoFiscal());
			vArchivoXml.setP_caed(solicitud.getCaed());
			vArchivoXml.setP_recepcion_anulacion_id(solicitud.getCodigoRecepcion());
			vArchivoXml.setP_motivo_anulacion_id(solicitud.getCodigoMotivo());
			vArchivoXml.setP_cuf(solicitud.getCuf());
			vArchivoXml.setvOrigenServicio(Parametros.F_MODALIDAD_FACTURA_ELECTRONICA);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap
					.CoreFacturacionPrincipal(vArchivoXml, Parametros.ValidacionAnulacion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());
			vRespuesta.setListaDescripcionesRespuestas(vRespuestaDto.getListaDescripcionesRespuestas());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("validacionAnulacion||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
		LOG.info("validacionAnulacion ||Finalizado");
		return vRespuesta;
	}

}