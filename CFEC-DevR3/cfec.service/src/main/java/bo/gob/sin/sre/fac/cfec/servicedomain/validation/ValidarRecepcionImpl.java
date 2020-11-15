package bo.gob.sin.sre.fac.cfec.servicedomain.validation;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sre.fac.cfec.domain.IJsonComprasDomain;
import bo.gob.sin.sre.fac.cfec.domain.IJsonDomain;
import bo.gob.sin.sre.fac.cfec.domain.IOperacionesCufDomain;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacArchivoXmlValidoDomain;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacErrorCompraDetalleDomain;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacErrorCompraDomain;
import bo.gob.sin.sre.fac.cfec.domain.IValidarArchivoHash;
import bo.gob.sin.sre.fac.cfec.dto.LogBDErroresDto;
import bo.gob.sin.sre.fac.cfec.dto.LogConsumoServicioDto;
import bo.gob.sin.sre.fac.cfec.dto.LogEtapa6Dto;
import bo.gob.sin.sre.fac.cfec.dto.RecepcionErrorDetalleDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionFirmadoXmlFacturaDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudValidacionFirmadoXmlFacturaDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlAnulacionContingenciaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlAnulacionDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlAnulacionPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionIndividualDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionIndividualNaliDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionIndividualPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionMasivaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionMasivaPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionPaqueteNaliDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlRecepcionPaquetePrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionAnulacionContingenciaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionAnulacionDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionAnulacionPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionRecepcionDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionRecepcionMasivaFacturaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionRecepcionMasivaFacturaPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionRecepcionPaqueteFacturaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionRecepcionPaqueteFacturaPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.mapping.XmlValidacionRecepcionPrevaloradaDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresCompras;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresComprasDetalles;
import bo.gob.sin.sre.fac.cfec.service.IServicioPushClientRest;
import bo.gob.sin.sre.fac.cfec.service.IServiciosFirmaClientRest;
import bo.gob.sin.sre.fac.cfec.service.IServiciosReversionClientRest;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.impl.HiloRegistrarCompra;
import bo.gob.sin.sre.fac.cfec.servicedomain.impl.HiloRegistrarComprasPaqueteFactura;
import bo.gob.sin.sre.fac.cfec.servicedomain.impl.HiloRegistrarFactura;
import bo.gob.sin.sre.fac.cfec.servicedomain.impl.HiloRegistrarMasivoFactura;
import bo.gob.sin.sre.fac.cfec.servicedomain.impl.HiloRegistrarPaqueteFactura;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mfcm.dto.FirebaseResponseDto;
import bo.gob.sin.str.cmsj.mfcm.dto.SolicitudPersonaIdDocumentoDto;

@Service
@Transactional
public class ValidarRecepcionImpl implements IValidarRecepcion {

	private static final Logger LOG = LoggerFactory.getLogger(ValidarRecepcionImpl.class);

	@Autowired
	IValidarRecepcion iValRec;

	@Autowired
	IUtilitarios iutil;

	@Autowired
	IValidarArchivoHash validarArchivoHash;

	@Autowired
	IJsonDomain iJsonDomain;

	@Autowired
	IJsonComprasDomain iJsonComprasDomain;

	@Autowired
	IOperacionesCufDomain iOperacionesCufDomain;

	@Autowired
	IServiciosFirmaClientRest iServiciosFirmaClientRest;

	@Autowired
	IServiciosReversionClientRest iServiciosReversionClientRest;

	@Autowired
	ISreFacErrorCompraDomain iSreFacErrorCompraDomain;

	@Autowired
	ISreFacErrorCompraDetalleDomain iSreFacErrorCompraDetalleDomain;

	@Autowired
	IServicioPushClientRest iServicioPushClientRest;

	@Autowired
	ISreFacArchivoXmlValidoDomain iSreFacArchivoXmlValidoDomain;

	@Override
	public void enviarJsonFacturaHilo(XmlRecepcionGenericoDto xmlRecGenDto) {
		if (xmlRecGenDto.getV_recepcionId() > 0) {
			HiloRegistrarFactura hilo = new HiloRegistrarFactura();
			hilo.setiValRec(iValRec);
			hilo.setXmlRecGenDto(xmlRecGenDto);
			hilo.start();
		}
	}

	@Override
	public void enviarJsonFacturaPaqueteHilo(XmlRecepcionGenericoDto xmlRecGenDto) {
		if (xmlRecGenDto.getV_recepcionPaqueteId() > 0) {
			HiloRegistrarPaqueteFactura hilo = new HiloRegistrarPaqueteFactura(iValRec, iutil, iJsonDomain,
					iSreFacArchivoXmlValidoDomain);
			hilo.setXmlRecGenDto(xmlRecGenDto);
			hilo.start();
		}
	}

	@Override
	public List<Integer> validarHashFactura(String pArchivo, String pHashArchivo) {
		List<Integer> vListaErrores = new ArrayList<>();
		try {
			if (pArchivo != null && pHashArchivo != null && !pArchivo.isEmpty() && !pHashArchivo.isEmpty()) {
				if (!validarArchivoHash.verificarArchivoSha256(pArchivo, pHashArchivo))
					vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.HASH_INVALIDO);
			} else {
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.HASH_INVALIDO);
			}
		} catch (Exception e) {
			vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.HASH_INVALIDO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarHashFactura", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaErrores;
	}

	@Override
	public List<Integer> validarFirmaElectronicaIndividual(String pArchivoXml) {
		List<Integer> vListaErrores = new ArrayList<>();
		try {
			if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
				SolicitudValidacionFirmadoXmlFacturaDto vSolValFirmadoXmlFacturaDto = new SolicitudValidacionFirmadoXmlFacturaDto();
				vSolValFirmadoXmlFacturaDto.setArchivo(iutil.codificarToBase64(pArchivoXml));
				RespuestaValidacionFirmadoXmlFacturaDto vRespValFirmXmlFacturaDto = iServiciosFirmaClientRest
						.verificarFirmadoXml(vSolValFirmadoXmlFacturaDto);

				if (!vRespValFirmXmlFacturaDto.isOk()) {
					vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FIRMA_INCORRRECTA);
				}
			} else {
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FIRMA_INCORRRECTA);
			}
		} catch (Exception e) {
			vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FIRMA_INCORRRECTA);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarFirmaElectronicaIndividual", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaErrores;
	}

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pArchivo
	 * @return
	 * @fecha 13/12/2018
	 */
	public List<Integer> validarArchivoIndividual(String pArchivo) {
		List<Integer> vListaCodigoError = new ArrayList<>();
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				String vXmlArchivo = iutil.decodificarArchivo(pArchivo);
				if (vXmlArchivo == null || vXmlArchivo.isEmpty()) {
					vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
				}
			} else {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarArchivoIndividual", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaCodigoError;
	}

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pArchivo
	 * @return
	 * @fecha 13/12/2018
	 */
	public List<Integer> validarArchivoPaquete(String pArchivo) {
		List<Integer> vListaCodigoError = new ArrayList<>();
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				// Decodifica el archivo recibido y obtiene lista de facturas codificadas
				byte[] compressed = Base64.getDecoder().decode(pArchivo);
				List<String> listaFacturasB64 = iutil.decompressLoteFacturas(compressed);
				if (listaFacturasB64.isEmpty()) {
					vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
				} else {
					for (String xmlB64 : listaFacturasB64) {
						if (iutil.decodificarToBase64(xmlB64) == null)
							vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
					}

					if (listaFacturasB64.size() > iJsonDomain.obtenerCantidadMaximaPaquete())
						vListaCodigoError.add(
								CodigosMensajesServiciosSOAPServiceImpl.A_SOBREPASADO_EL_LIMITE_PERMITIDO_ASIGNADO);
				}
			} else {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
			}

		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarArchivoPaquete", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaCodigoError;
	}

	@Override
	public RespuestaXmlXsdDto validarDocumentoFiscalXsd(String pArchivoXml, int pTipoDocFiscal, int pTipoDocSector) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
				String docFiscalXsd = this.obtenerNombreDocFiscal(pTipoDocFiscal, pTipoDocSector);
				List<Integer> vValFact = iutil.validarFacturaXml(pArchivoXml, docFiscalXsd);
				if (!vValFact.isEmpty()) {
					vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(pArchivoXml, docFiscalXsd));
				}
			} else {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarDocumentoFiscalXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, pTipoDocSector);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarDocumentoFiscalElectronicaXsd(String pArchivoXml, int pTipoDocFiscal,
			int pTipoDocSector) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
			String vTemp = iutil.formatearSignature(pArchivoXml);
			if (vTemp == null) {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			}

			String docFiscalXsd = this.obtenerNombreDocFiscal(pTipoDocFiscal, pTipoDocSector);
			List<Integer> vValFact = iutil.validarFacturaXml(vTemp, docFiscalXsd);

			if (vTemp != null && !vValFact.isEmpty()) {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
				vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(vTemp, docFiscalXsd));
			}
		} else {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarRecepcionIndXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				XmlRecepcionIndividualPrevaloradaDto dtoAux = new XmlRecepcionIndividualPrevaloradaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCuape(xmlRecGenDto.getP_cuape());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setFechaEnvio(
						new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
				dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
				dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL));
				}
			} else {
				if (xmlRecGenDto.getP_codigoAutorizacionAlimentaria() != null) {
					XmlRecepcionIndividualNaliDto dtoAux = new XmlRecepcionIndividualNaliDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setFechaEnvio(
							new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
					dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
					dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());
					dtoAux.setCodigoAutorizacion(xmlRecGenDto.getP_codigoAutorizacionAlimentaria());
					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL));
					}
				} else {

					XmlRecepcionIndividualDto dtoAux = new XmlRecepcionIndividualDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setFechaEnvio(
							new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
					dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
					dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL));
					}
				}
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarRecepcionIndXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);

		// Realizar el registro de los log para la recepción de la factura.
		registrarLogConsumoServicio(xmlRecGenDto, vListaCodigoError.size() == 0 ? 1000 : vListaCodigoError.get(0),
				Parametros.CONSUMO_SERVICIO_RECEPCION_FACTURA, 0L);
		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarRecepcionPaqXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				XmlRecepcionPaquetePrevaloradaDto dtoAux = new XmlRecepcionPaquetePrevaloradaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCuape(xmlRecGenDto.getP_cuape());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setFechaEnvio(
						new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
				dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
				dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_RECEPCION_PAQUETE);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_PAQUETE));
				}
			} else {
				if (xmlRecGenDto.getP_codigoAutorizacionAlimentaria() != null) {
					XmlRecepcionPaqueteNaliDto dtoAux = new XmlRecepcionPaqueteNaliDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setFechaEnvio(
							new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
					dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
					dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());
					dtoAux.setCodigoAutorizacion(xmlRecGenDto.getP_codigoAutorizacionAlimentaria());
					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL));
					}
				} else {

					XmlRecepcionPaqueteDto dtoAux = new XmlRecepcionPaqueteDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setFechaEnvio(
							new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
					dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
					dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_PAQUETE);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_PAQUETE));
					}
				}
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarRecepcionPaqXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	/**
	 * @autor edwin.coro
	 * @descripción @param pDto
	 * @return
	 * @fecha 22/03/2019
	 */
	@Override
	public RespuestaXmlXsdDto validarValidacionRecepcionIndXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {

			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				XmlValidacionRecepcionPrevaloradaDto dtoAux = new XmlValidacionRecepcionPrevaloradaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCuape(xmlRecGenDto.getP_cuape());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setCodigoRecepcion(xmlRecGenDto.getV_recepcionId());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_INDIVIDUAL);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_INDIVIDUAL));
				}
			} else {
				XmlValidacionRecepcionDto dtoAux = new XmlValidacionRecepcionDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setCodigoRecepcion(xmlRecGenDto.getV_recepcionId());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_INDIVIDUAL);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_INDIVIDUAL));
				}
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarValidacionRecepcionIndXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);

		return vRespuesta;
	}

	/**
	 * @autor junior.flores
	 * @descripción Método que realiza la inserción de los log para el consumo de
	 *              los servicios
	 * @param pXmlRecGenDto: Archivo en formato xml con la información de la
	 *        recepción pListaErrores Listado de la descripción de los errores luego
	 *        de la validación XML/XSD pResultado: Resultado de la transacción
	 *        pCasoPrueba: Descripción del Caso de Prueba pRecepcionId: Código de la
	 *        recepción
	 * @fecha 30/05/2019
	 */
	@Override
	public void registrarLogConsumoServicio(XmlRecepcionGenericoDto pXmlRecGenDto, Integer pResultado,
			String pCasoPrueba, Long pRecepcionId) {
		// Realizar el registro de los logs
		LogConsumoServicioDto vObjLogConsumoServicioDto = new LogConsumoServicioDto();
		ObjectMapper mapper = null;
		ModelMapper vMapper = null;
		if (pXmlRecGenDto.getP_tipo_ambiente_id() != null && pXmlRecGenDto.getP_tipo_ambiente_id() == 2) {
			mapper = new ObjectMapper();
			vMapper = new ModelMapper();
			try {
				vObjLogConsumoServicioDto = vMapper.map(pXmlRecGenDto, LogConsumoServicioDto.class);
				vObjLogConsumoServicioDto.setP_descripcion_respuesta(pCasoPrueba + '|' + pResultado + "");
				vObjLogConsumoServicioDto.setP_etapa_certificacion_sistema_id(Parametros.ETAPA_I_CONSUMO_SERVICIOS);
				vObjLogConsumoServicioDto.setV_recepcionId(pRecepcionId);
				JSONObject jsonRecepcion = new JSONObject();
				JSONObject jsonFinal = new JSONObject();
				jsonRecepcion = new JSONObject(mapper.writeValueAsString(vObjLogConsumoServicioDto));
				jsonFinal.put("xmlRecepcionLog", jsonRecepcion);
				iJsonDomain.registroLogCertificacionSistemas(jsonFinal.toString());
				jsonFinal = null;
				jsonRecepcion = null;
			} catch (Exception e) {
				LOG.error("Exception || registrarLogConsumoServicio : " + e.getMessage());
				LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
						Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogConsumoServicio", new Date(), "CORE",
						e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pXmlRecGenDto.getP_nit(),
						pXmlRecGenDto.getV_recepcionId(), pXmlRecGenDto.getV_recepcionPaqueteId(),
						pXmlRecGenDto.getP_tipo_documento_sector_id());
				iValRec.registrarLogExcepcionBDErrores(vErrorDto);
			}
		}
		mapper = null;
		vMapper = null;
		vObjLogConsumoServicioDto = null;
	}

	@Override
	public RespuestaXmlXsdDto validarValidacionRecepcionPaqXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				XmlValidacionRecepcionPaqueteFacturaPrevaloradaDto dtoAux = new XmlValidacionRecepcionPaqueteFacturaPrevaloradaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCuape(xmlRecGenDto.getP_cuape());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setCodigoRecepcion(xmlRecGenDto.getV_recepcionPaqueteId());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_PAQUETE);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_PAQUETE));
				}
			} else {
				XmlValidacionRecepcionPaqueteFacturaDto dtoAux = new XmlValidacionRecepcionPaqueteFacturaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());

				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setCodigoRecepcion(xmlRecGenDto.getV_recepcionPaqueteId());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_PAQUETE);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_PAQUETE));
				}
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarValidacionRecepcionPaqXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarAnulacionXsd(XmlRecepcionGenericoDto pDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {

			if (pDto.getP_tipo_emision_id() == null || pDto.getP_tipo_emision_id() <= 0)
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.TIPO_EMISION_INVALIDO);

			if (vListaCodigoError.isEmpty())
				if (pDto.getP_tipo_documento_fiscal_id() == Parametros.F_TIPO_DOC_FISCAL_FACTURA_CONTIGENCIA || pDto
						.getP_tipo_documento_fiscal_id() == Parametros.F_TIPO_DOC_FISCAL_NOTA_FISCAL_CONTIGENCIA) {

					XmlAnulacionContingenciaDto dtoAux = new XmlAnulacionContingenciaDto();
					dtoAux.setCodigoSistema(pDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(pDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoEmision(pDto.getP_tipo_emision_id());
					dtoAux.setCodigoModalidad(pDto.getP_tipo_modalidad_id());
					dtoAux.setNit(pDto.getP_nit());
					dtoAux.setCuis(pDto.getP_cuis());
					dtoAux.setCufd(pDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(pDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(pDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(pDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(pDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(pDto.getP_numero_documento_fiscal());
					dtoAux.setCuf(pDto.getP_cuf());
					dtoAux.setCodigoMotivo(pDto.getP_motivo_anulacion_id());
					dtoAux.setCaed(pDto.getP_caed());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_ANULACION));
					}
				} else if (pDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
						|| pDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
					XmlAnulacionPrevaloradaDto dtoAux = new XmlAnulacionPrevaloradaDto();
					dtoAux.setCodigoSistema(pDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(pDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(pDto.getP_tipo_modalidad_id());
					dtoAux.setNit(pDto.getP_nit());
					dtoAux.setCuis(pDto.getP_cuis());
					dtoAux.setCufd(pDto.getP_cufd());
					dtoAux.setCuape(pDto.getP_cuape());
					dtoAux.setCodigoDocumentoFiscal(pDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(pDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(pDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(pDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(pDto.getP_numero_documento_fiscal());
					dtoAux.setCufp(pDto.getP_cufp());
					dtoAux.setCodigoMotivo(pDto.getP_motivo_anulacion_id());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_ANULACION));
					}
				} else {
					XmlAnulacionDto dtoAux = new XmlAnulacionDto();
					dtoAux.setCodigoSistema(pDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(pDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(pDto.getP_tipo_modalidad_id());
					dtoAux.setNit(pDto.getP_nit());
					dtoAux.setCuis(pDto.getP_cuis());
					dtoAux.setCufd(pDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(pDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(pDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(pDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(pDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(pDto.getP_numero_documento_fiscal());
					dtoAux.setCuf(pDto.getP_cuf());
					dtoAux.setCodigoMotivo(pDto.getP_motivo_anulacion_id());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_ANULACION));
					}
				}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarAnulacionXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pDto.getP_nit(),
					pDto.getV_recepcionId(), pDto.getV_recepcionPaqueteId(), pDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);

		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarValidacionAnulacionXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {

			if (xmlRecGenDto.getP_tipo_emision_id() == null || xmlRecGenDto.getP_tipo_emision_id() <= 0)
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.TIPO_EMISION_INVALIDO);

			if (vListaCodigoError.isEmpty())
				if (xmlRecGenDto.getP_tipo_documento_fiscal_id() == Parametros.F_TIPO_DOC_FISCAL_FACTURA_CONTIGENCIA
						|| xmlRecGenDto
								.getP_tipo_documento_fiscal_id() == Parametros.F_TIPO_DOC_FISCAL_NOTA_FISCAL_CONTIGENCIA) {

					XmlValidacionAnulacionContingenciaDto dtoAux = new XmlValidacionAnulacionContingenciaDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(xmlRecGenDto.getP_numero_documento_fiscal());
					dtoAux.setCuf(xmlRecGenDto.getP_cuf());
					dtoAux.setCodigoMotivo(xmlRecGenDto.getP_motivo_anulacion_id());
					dtoAux.setCodigoRecepcion(xmlRecGenDto.getP_recepcion_anulacion_id());
					dtoAux.setCaed(xmlRecGenDto.getP_caed());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_ANULACION);

					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_ANULACION));
					}
				} else if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
						|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
					XmlValidacionAnulacionPrevaloradaDto dtoAux = new XmlValidacionAnulacionPrevaloradaDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCuape(xmlRecGenDto.getP_cuape());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(xmlRecGenDto.getP_numero_documento_fiscal());
					dtoAux.setCufp(xmlRecGenDto.getP_cufp());
					dtoAux.setCodigoMotivo(xmlRecGenDto.getP_motivo_anulacion_id());
					dtoAux.setCodigoRecepcion(xmlRecGenDto.getP_recepcion_anulacion_id());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_VALIDACION_ANULACION));
					}
				} else {
					XmlValidacionAnulacionDto dtoAux = new XmlValidacionAnulacionDto();
					dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
					dtoAux.setNit(xmlRecGenDto.getP_nit());
					dtoAux.setCuis(xmlRecGenDto.getP_cuis());
					dtoAux.setCufd(xmlRecGenDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(xmlRecGenDto.getP_numero_documento_fiscal());
					dtoAux.setCuf(xmlRecGenDto.getP_cuf());
					dtoAux.setCodigoMotivo(xmlRecGenDto.getP_motivo_anulacion_id());
					dtoAux.setCodigoRecepcion(xmlRecGenDto.getP_recepcion_anulacion_id());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_VALIDACION_ANULACION));
					}
				}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarValidacionAnulacionXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);

		return vRespuesta;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto enviarJsonRecepcionBDEtapa1(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		try {
			// Limpiar archivo XML
			xmlRecGenDto.setV_archivoXml(null);
			xmlRecGenDto.setV_archivoXmlB64(null);

			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			JSONObject jsonFinal = new JSONObject();
			jsonFinal.put("XmlFactura", "");
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			solicitud.setSolicitud(jsonFinal.toString());

			LOG.info("EnviarJsonRecepcionBaseDatos || " + solicitud.getSolicitud());

			String vRequest = iJsonDomain.recepcionEtapa1(solicitud.getSolicitud());

			if (vRequest != null) {
				// DESPLIEGUE DE RESULTADO DE LISTA ERRORES
				String[] respuesta = vRequest.split(":");
				if (respuesta[0].equals(Parametros.resultadoIdRecepcionBd)) {
					vRespuesta.setCodigoRecepcion(Long.parseLong(respuesta[1]));
					vRespuesta.setTransaccion(true);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_PENDIENTE);
				} else {
					List<Integer> vListaErrores = new ArrayList<>();
					for (String error : respuesta[1].split(",")) {
						vListaErrores.add(Integer.parseInt(error));
					}
					vRespuesta.setListaCodigosRespuestas(vListaErrores);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
				}
			} else {
				LOG.error("Error en request recepcionEtapa1: " + vRequest);
				vRespuesta.getListaCodigosRespuestas()
						.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
				vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			}
		} catch (Exception e) {
			LOG.error("enviarJsonRecepcionBDEtapa1 || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "enviarJsonRecepcionBDEtapa1", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public void enviarJsonRecepcionBDEtapa2(XmlRecepcionGenericoDto xmlRecGenDto, int pTipoEmision) {
		// codigo_respuesta generacion de CUF - etapa0
		int vCodigoRespuestaLogCUF = 1000;

		try {
			if (pTipoEmision == Parametros.F_TIPO_EMISION_ONLINE) {
				SolicitudJsonDto solicitud = new SolicitudJsonDto();
				JSONObject jsonFinal = new JSONObject();
				ObjectMapper mapper = new ObjectMapper();

				String vXmlInd = iutil.decodificarArchivo(xmlRecGenDto.getV_archivo());
				RespuestaJsonDto vRequestInd = new RespuestaJsonDto();
				RecepcionErrorDetalleDto vErrorDetalle = this.obtenerDatosErrorFactura(vXmlInd);

				// Validacion de CUF
				boolean vResValCuf = this.validarCuf(xmlRecGenDto, xmlRecGenDto.getV_archivo(), pTipoEmision);
				if (!vResValCuf) {
					vErrorDetalle.getListaCodigosError()
							.add(CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO);

					// codigo_respuesta generacion de CUF - etapa0
					vCodigoRespuestaLogCUF = CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO;
				}

				// Validacion de NITCI
				if (!excluyeValidacionDocumentoIdentidad(xmlRecGenDto.getP_tipo_documento_sector_id())) {
					List<Integer> vResValNitCi = this.validarDocumentoIdentidadNitCi(vXmlInd);
					if (!vResValNitCi.isEmpty() && !this.validarCodExcDoc(vXmlInd)) {
						vErrorDetalle.getListaCodigosError().addAll(vResValNitCi);
					}
				}

				// TODO: Reynaldo : Individual conversion de archivo XML a B64
				xmlRecGenDto.setV_archivo(iutil.codificarToBase64(vXmlInd));

				JSONObject jsonFactura = XML.toJSONObject(vXmlInd);
				JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
				jsonFinal.put("XmlFactura", jsonFactura);
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				if (vErrorDetalle.getListaCodigosError().isEmpty()) {
					solicitud.setSolicitud(jsonFinal.toString());
					LOG.info("enviarJsonRecepcionBDEtapa2 || " + solicitud.getSolicitud());

					vRequestInd.setRespuesta(iJsonDomain.recepcionEtapa2(solicitud.getSolicitud()));

					// codigo_respuesta generacion de CUF - etapa0 -
					vCodigoRespuestaLogCUF = 1000;

					if (vRequestInd == null || vRequestInd.getRespuesta().length() == 0
							|| !vRequestInd.getRespuesta().equals("1")) {
						vErrorDetalle.getListaCodigosError().add(
								CodigosMensajesServiciosSOAPServiceImpl.ERROR_EJECUCION_SERVICIO_VALIDACION_DETALLE_DOCUMENTO_FISCAL);

						if (vRequestInd.getRespuesta().equals("0"))
							LOG.error("RESPUESTA BASE DE DATOS||EXISTE ERRORES EN LA FACTURA/NOTA FISCAL");
						if (vRequestInd.getRespuesta().equals("-1"))
							LOG.error("RESPUESTA BASE DE DATOS||EXCEPCION REGISTRADA EN LOG DE TRANSVERSALES");
						if (vRequestInd.getRespuesta().equals("-2"))
							LOG.error("RESPUESTA BASE DE DATOS||EXCEPCION SIN REGISTRO");
					}
				} else {
					this.registrarFacturaInvalida(jsonFinal.toString(), vErrorDetalle);
				}
			} else {
				// TODO: Reynaldo Chambi - COMENTADO DE ENVIO PAQUETES 13/09/2019
//				byte[] compressed = Base64.getDecoder().decode(xmlRecGenDto.getV_archivo());
//				List<String> listaFacturasB64 = iutil.decompressLoteFacturas(compressed);
//
//				// Parallel processing
//				long vTiempoInicioParall = System.currentTimeMillis();
//				listaFacturasB64.parallelStream()
//						.forEach(xmlB64 -> procesarPaqueteIndividual(xmlRecGenDto, pTipoEmision, xmlB64));
//				long vTiempoFinParall = System.currentTimeMillis();
//				long vTiempoParall = (vTiempoFinParall - vTiempoInicioParall) / 1000;
//				System.out.println("Tiempo requerido con parallelStream() : " + vTiempoParall);
			}
		} catch (Exception e) {
			LOG.error("Error al enviar archivo JSON Etapa 2");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "enviarJsonRecepcionBDEtapa2", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		// Registrar Log etapa 0 (Generacion de CUF) -- 20190604
		registrarLogGeneracionCUF(xmlRecGenDto, vCodigoRespuestaLogCUF);
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto validacionRecepcionBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		JSONObject jsonRecepcion = new JSONObject();

		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				LOG.info("validacionRecepcionBd || " + jsonFinal.toString());

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonDomain.validacionRecepcionXml(jsonFinal.toString());
				// String vRequest = "{'codigorecepcionindividual': 10, 'codigoestado': 20,
				// 'numerofactura': 30, 'cuf': 'cudddffff', 'listacodigoserror':'0'}";
				JSONObject objeto = new JSONObject(vRequest);
				List<String> vListaErrores = Arrays.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));

				vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));
				if (!vListaErrores.get(0).equals("0"))
					vRespuesta.setListaCodigosRespuestas(
							vListaErrores.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
				else {
					vRespuesta.setTransaccion(true);
					this.noticarPushFactura(xmlRecGenDto);
				}
			}
		} catch (Exception e) {
			LOG.error("validacionRecepcionBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionRecepcionBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vRespuesta;

	}

	@Override
	public RespuestaValidacionRecepcionPaqueteDto validacionRecepcionPaqueteBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();

		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				LOG.info("validacionRecepcionPaqueteBd || " + jsonFinal.toString());

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonDomain.validacionRecepcionPaqueteXml(jsonFinal.toString());

				// String vRequest = "{'codigorecepcionindividual': 0, 'codigoestado': 0,
				// 'numerofactura': 0, 'cuf': '0', 'listacodigoserror':'1,12'}";
				// String vRequest =
				// "{'codigoestado':708,'codigorecepcion':123,'recepcionerrordetalle':[{'cuf':'4f2b1df8782f80825097864b4b8','codigoestado':709,'numerofactura':2,'listacodigoserror':'1,18','codigorecepcionindividual':11109},
				// {'cuf':'4f2b1df8782f80825097864b4b8','codigoestado':709,'numerofactura':1,'listacodigoserror':'1,18,102','codigorecepcionindividual':11111}]}";
				// String vRequest = {"codigoestado": 709, "codigorecepcion":
				// 2611,"recepcionerrordetalle": []}
				JSONObject objeto = new JSONObject(vRequest);
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));

				if (vRespuesta.getCodigoEstado() == 0) {
					vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
					List<String> vListaErroresGral = Arrays
							.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));
					vRespuesta.setListaCodigosRespuestas(
							vListaErroresGral.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
				} else {
					vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcion"));
					JSONArray vListaFacturasError = objeto.getJSONArray("recepcionerrordetalle");
					List<RecepcionErrorDetalleDto> listaErroresDetalles = new ArrayList<>();

					for (int i = 0; i < vListaFacturasError.length(); i++) {
						JSONObject errorDetalle = vListaFacturasError.getJSONObject(i);
						RecepcionErrorDetalleDto vdto = new RecepcionErrorDetalleDto();
						vdto.setCodigoRecepcionIndividual(errorDetalle.getLong("codigorecepcionindividual"));
						vdto.setNumeroFactura(errorDetalle.getLong("numerofactura"));
						vdto.setCuf(errorDetalle.getString("cuf"));
						List<String> vListaErroresInd = Arrays
								.asList(errorDetalle.getString("listacodigoserror").split("\\s*,\\s*"));
						if (!vListaErroresInd.get(0).equals("0") && !vListaErroresInd.get(0).isEmpty()) {
							vRespuesta.setListaCodigosRespuestas(vListaErroresInd.stream().map(s -> Integer.parseInt(s))
									.collect(Collectors.toList()));
							vdto.setListaCodigosError(vListaErroresInd.stream().map(s -> Integer.parseInt(s))
									.collect(Collectors.toList()));
							listaErroresDetalles.add(vdto);
						}
					}

					if (vListaFacturasError.length() < 1)
						vRespuesta.setTransaccion(true);

					vRespuesta.setListaErroresDetalles(listaErroresDetalles);
				}
			}
		} catch (Exception e) {
			LOG.error("validacionRecepcionPaqueteBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionRecepcionPaqueteBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto anulacionBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		try {
			ObjectMapper mapper = new ObjectMapper();

			JSONObject jsonFinal = new JSONObject();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			jsonFinal.put("XmlFactura", "");
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			solicitud.setSolicitud(jsonFinal.toString());

			LOG.info("anulacionBd || " + jsonFinal.toString());

			String vRequest = iJsonDomain.anulacionXml(solicitud.getSolicitud());

			// DESPLIEGUE DE RESULTADO DE LISTA ERRORES
			String[] respuesta = vRequest.split(":");
			if (respuesta[0].equals(Parametros.resultadoIdRecepcionBd)) {
				vRespuesta.setCodigoRecepcion(Long.parseLong(respuesta[1]));
				vRespuesta.setTransaccion(true);
				vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.ANULACION_PENDIENTE_CONFIRMACION);
			} else {
				List<Integer> vListaErrores = new ArrayList<>();

				for (String error : respuesta[1].split(",")) {
					vListaErrores.add(Integer.parseInt(error));
				}
				vRespuesta.setListaCodigosRespuestas(vListaErrores);
				vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.ANULACION_RECHAZADA);
			}

		} catch (Exception e) {
			LOG.error("anulacionEnvioBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "anulacionBd", new Date(), "CORE", e.getLocalizedMessage(),
					e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(), xmlRecGenDto.getV_recepcionId(),
					xmlRecGenDto.getV_recepcionPaqueteId(), xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vRespuesta;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto validacionAnulacionBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		JSONObject jsonRecepcion = new JSONObject();

		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				LOG.info("validacionAnulacionBd || " + jsonFinal.toString());

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonDomain.validacionAnulacionXml(jsonFinal.toString());
				// String vRequest = "{'codigorecepcionanulacion': 2611,'codigoestado':
				// 705,'listacodigoserror': []}";
				// String vRequest = "{'codigorecepcionanulacion': 0,'codigoestado':
				// 0,'listacodigoserror': [10,15,2]}";

				JSONObject objeto = new JSONObject(vRequest);
				vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionanulacion"));
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));
				JSONArray vListaErrores = objeto.getJSONArray("listacodigoserror");
				if (vListaErrores.length() > 0) {
					for (int i = 0; i < vListaErrores.length(); i++) {
						vRespuesta.getListaCodigosRespuestas().add(vListaErrores.getInt(i));
					}
				} else
					vRespuesta.setTransaccion(true);
			}
		} catch (Exception e) {
			LOG.error("anulacionEnvioBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionAnulacionBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public List<String> obtenerDescripcionesCodigosRespuesta(List<Integer> pSolicitud) {
		List<String> vResultado = new ArrayList<>();
		try {
			String listString = pSolicitud.stream().map(Object::toString).collect(Collectors.joining(", "));
			String vRequest = iJsonDomain.obtenerDescripcionCodigosRespuesta(listString);
			if (vRequest != null) {
				vResultado = Arrays.asList(vRequest.split(","));
			} else {
				LOG.error("obtenerDescripcionesCodigosRespuesta || Excepcion : " + vRequest);
				vResultado.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO + "");
			}
		} catch (Exception e) {
			LOG.error("anulacionEnvioBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			vResultado.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO + "");
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerDescripcionesCodigosRespuesta", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vResultado;
	}

	/**
	 * @autor edwin.coro
	 * @descripción recupera datos especificos de
	 * @param archivoXml
	 * @return
	 * @fecha 06/05/2019
	 */
	private RecepcionErrorDetalleDto obtenerDatosErrorFactura(String archivoXml) {
		RecepcionErrorDetalleDto vErrorDetalle = new RecepcionErrorDetalleDto();
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xml = dBuilder.parse(new InputSource(new StringReader(archivoXml)));
			String vNumFactura = "";
			int vDocSector = Integer
					.parseInt(xml.getElementsByTagName("codigoDocumentoSector").item(0).getTextContent());
			if (vDocSector == Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
				vNumFactura = xml.getElementsByTagName("numeroBoleto").item(0).getTextContent();
			}
			if (vDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO) {
				vNumFactura = xml.getElementsByTagName("numeroNotaCreditoDebito").item(0).getTextContent();
			}
			if (vDocSector != Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO
					&& vDocSector != Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
				vNumFactura = xml.getElementsByTagName("numeroFactura").item(0).getTextContent();
			}

			vErrorDetalle
					.setNumeroFactura((vNumFactura == null || vNumFactura.isEmpty()) ? 0 : Long.parseLong(vNumFactura));

			// Corrigiendo bug, cufp prevaloradas
			vErrorDetalle.setCuf(xml.getElementsByTagName("cuf").item(0) == null
					? xml.getElementsByTagName("cufp").item(0).getTextContent()
					: xml.getElementsByTagName("cuf").item(0).getTextContent());

		} catch (Exception e) {
			vErrorDetalle.getListaCodigosError()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LOG.error("obtenerDatosErrorFactura || Error al obtener Datos Error Factura");
			LogExcepcion.registrar(e, LOG, MethodSign.build(archivoXml));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerDatosErrorFactura", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vErrorDetalle;
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion para el registro de facturas invalidas
	 * @param pJsonEtapa               dto en formato json, obtenido a traves de los
	 *                                 parametros del servicio
	 * @param RecepcionErrorDetalleDto dto con los datos de la factura invalida
	 * @fecha 13/06/2019
	 */
	private void registrarFacturaInvalida(String pJsonEtapa, RecepcionErrorDetalleDto pErrorDetalle) {
		try {
			List<Dictionary<String, Integer>> vLista = new ArrayList<>();
			for (Integer recErrorDetDto : pErrorDetalle.getListaCodigosError()) {
				Dictionary<String, Integer> vErrores = new Hashtable<String, Integer>();
				vErrores.put("resultado_ejecucion", recErrorDetDto);
				vLista.add(vErrores);
			}
			JSONArray jsonErrores = new JSONArray(vLista);
			JSONObject jsonListaErrores = new JSONObject();
			jsonListaErrores.put("resultados", jsonErrores);
			String vListaErrores = jsonListaErrores.toString();

			iJsonDomain.registrarErroresBd(pJsonEtapa, vListaErrores);
		} catch (Exception e) {
			LOG.error("registrarFacturaInvalida : ");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pJsonEtapa));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerDatosErrorFactura", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que valida el valor del codigo unico factura - CUF .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio,
	 *                     xmlB64 archivo de la factura
	 * @return Respuesta true or false dependiendo la validacion
	 * @fecha 03/05/2019
	 */
	@Override
	public Boolean validarCuf(XmlRecepcionGenericoDto pXmlRecGenDto, String xmlB64, int pTipoEmision) {
		boolean vRespuesta = false;
		try {
			if (pTipoEmision == Parametros.F_TIPO_EMISION_ONLINE) {
				DocumentBuilder dBuilder;
				dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document xml = dBuilder.parse(new InputSource(new StringReader(iutil.decodificarArchivo(xmlB64))));

				int vDocSector = Integer
						.parseInt(xml.getElementsByTagName("codigoDocumentoSector").item(0).getTextContent());
				String vNumFactura = "";
				if (vDocSector == Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
					vNumFactura = xml.getElementsByTagName("numeroBoleto").item(0).getTextContent();
				}
				if (vDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO
						|| vDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO_SFV) {
					vNumFactura = xml.getElementsByTagName("numeroNotaCreditoDebito").item(0).getTextContent();
				}
				if (vDocSector != Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO
						&& vDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO_SFV
						&& vDocSector != Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
					vNumFactura = xml.getElementsByTagName("numeroFactura").item(0).getTextContent();
				}
				long vNumeroFactura = Long.parseLong(vNumFactura);

				String vFechaEmision = xml.getElementsByTagName("fechaEmision").item(0).getTextContent();
				Long vNit = Long.parseLong(xml.getElementsByTagName("nitEmisor").item(0).getTextContent());
				Integer vSucursal = Integer
						.parseInt(xml.getElementsByTagName("codigoSucursal").item(0).getTextContent());
				Integer vPos = xml.getElementsByTagName("codigoPuntoVenta").item(0).getTextContent().isEmpty() ? null
						: Integer.parseInt(xml.getElementsByTagName("codigoPuntoVenta").item(0).getTextContent());

				// correccion de bug, se contempla campo cufp en caso de prevaloradas
				String vCufXml = xml.getElementsByTagName("cuf").item(0) == null
						? xml.getElementsByTagName("cufp").item(0).getTextContent()
						: xml.getElementsByTagName("cuf").item(0).getTextContent();

				String vCufXmlParametros = iOperacionesCufDomain.generadorCUF(vNit, vSucursal, vFechaEmision,
						pXmlRecGenDto.getP_tipo_modalidad_id(), pXmlRecGenDto.getP_tipo_emision_id(),
						pXmlRecGenDto.getP_tipo_documento_fiscal_id(), vDocSector, vNumeroFactura, vPos);

				String vCufParametros = iOperacionesCufDomain.generadorCUF(pXmlRecGenDto.getP_nit(),
						pXmlRecGenDto.getP_sucursal_id(), vFechaEmision, pXmlRecGenDto.getP_tipo_modalidad_id(),
						pXmlRecGenDto.getP_tipo_emision_id(), pXmlRecGenDto.getP_tipo_documento_fiscal_id(),
						pXmlRecGenDto.getP_tipo_documento_sector_id(), vNumeroFactura,
						pXmlRecGenDto.getP_punto_venta_id());

				if (vCufXml.equals(vCufXmlParametros) && vCufXml.equals(vCufParametros)) {
					vRespuesta = true;
				}
			}

			if (pTipoEmision == Parametros.F_TIPO_EMISION_OFFLINE || pTipoEmision == Parametros.F_TIPO_EMISION_MASIVA) {
				DocumentBuilder dBuilder;
				dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document xml = dBuilder.parse(new InputSource(new StringReader(iutil.decodificarToBase64(xmlB64))));
				int vDocSector = Integer
						.parseInt(xml.getElementsByTagName("codigoDocumentoSector").item(0).getTextContent());
				String vNumFactura = "";
				if (vDocSector == Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
					vNumFactura = xml.getElementsByTagName("numeroBoleto").item(0).getTextContent();
				}
				if (vDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO
						|| vDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO_SFV) {
					vNumFactura = xml.getElementsByTagName("numeroNotaCreditoDebito").item(0).getTextContent();
				}
				if (vDocSector != Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO
						&& vDocSector != Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO_SFV
						&& vDocSector != Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
					vNumFactura = xml.getElementsByTagName("numeroFactura").item(0).getTextContent();
				}

				long vNumeroFactura = Long.parseLong(vNumFactura);

				String vFechaEmision = xml.getElementsByTagName("fechaEmision").item(0).getTextContent();
				Long vNit = Long.parseLong(xml.getElementsByTagName("nitEmisor").item(0).getTextContent());
				Integer vSucursal = Integer
						.parseInt(xml.getElementsByTagName("codigoSucursal").item(0).getTextContent());
				Integer vPos = xml.getElementsByTagName("codigoPuntoVenta").item(0).getTextContent().isEmpty() ? null
						: Integer.parseInt(xml.getElementsByTagName("codigoPuntoVenta").item(0).getTextContent());

				// String vCufXml = xml.getElementsByTagName("cuf").item(0).getTextContent();
				// correccion de bug, se contempla campo cufp en caso de prevaloradas
				String vCufXml = xml.getElementsByTagName("cuf").item(0) == null
						? xml.getElementsByTagName("cufp").item(0).getTextContent()
						: xml.getElementsByTagName("cuf").item(0).getTextContent();

				String vCufXmlParametros = iOperacionesCufDomain.generadorCUF(vNit, vSucursal, vFechaEmision,
						pXmlRecGenDto.getP_tipo_modalidad_id(), pXmlRecGenDto.getP_tipo_emision_id(),
						pXmlRecGenDto.getP_tipo_documento_fiscal_id(), vDocSector, vNumeroFactura, vPos);

				String vCufParametros = iOperacionesCufDomain.generadorCUF(pXmlRecGenDto.getP_nit(),
						pXmlRecGenDto.getP_sucursal_id(), vFechaEmision, pXmlRecGenDto.getP_tipo_modalidad_id(),
						pXmlRecGenDto.getP_tipo_emision_id(), pXmlRecGenDto.getP_tipo_documento_fiscal_id(),
						pXmlRecGenDto.getP_tipo_documento_sector_id(), vNumeroFactura,
						pXmlRecGenDto.getP_punto_venta_id());

				if (vCufXml.equals(vCufXmlParametros) && vCufXml.equals(vCufParametros)) {
					vRespuesta = true;
				}
			}

			/*
			 * if (pXmlRecGenDto.getP_tipo_documento_fiscal_id() ==
			 * Parametros.F_TIPO_DOC_FISCAL_NOTA_FISCAL_CONTIGENCIA || pXmlRecGenDto
			 * .getP_tipo_documento_fiscal_id() ==
			 * Parametros.F_TIPO_DOC_FISCAL_FACTURA_CONTIGENCIA) { DocumentBuilder dBuilder;
			 * dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			 * Document xml = dBuilder.parse(new InputSource(new
			 * StringReader(iutil.decodificarToBase64(xmlB64)))); int vDocSector = Integer
			 * .parseInt(xml.getElementsByTagName("codigoDocumentoSector").item(0).
			 * getTextContent()); String vNumFactura = ""; if (vDocSector ==
			 * Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) { vNumFactura =
			 * xml.getElementsByTagName("numeroBoleto").item(0).getTextContent(); } else {
			 * vNumFactura =
			 * xml.getElementsByTagName("numeroFactura").item(0).getTextContent(); }
			 * 
			 * long vNumeroFactura = Long.parseLong(vNumFactura);
			 * 
			 * String vFechaEmision =
			 * xml.getElementsByTagName("fechaEmision").item(0).getTextContent(); Long vNit
			 * =
			 * Long.parseLong(xml.getElementsByTagName("nitEmisor").item(0).getTextContent()
			 * ); Integer vSucursal = Integer
			 * .parseInt(xml.getElementsByTagName("codigoSucursal").item(0).getTextContent()
			 * ); Integer vPos =
			 * xml.getElementsByTagName("codigoPuntoVenta").item(0).getTextContent().isEmpty
			 * () ? null :
			 * Integer.parseInt(xml.getElementsByTagName("codigoPuntoVenta").item(0).
			 * getTextContent());
			 * 
			 * // correccion de bug, se contempla campo cufp en caso de prevaloradas String
			 * vCufXml = xml.getElementsByTagName("cuf").item(0) == null ?
			 * xml.getElementsByTagName("cufp").item(0).getTextContent() :
			 * xml.getElementsByTagName("cuf").item(0).getTextContent();
			 * 
			 * String vCufXmlParametros = iOperacionesCufDomain.generadorCUF(vNit,
			 * vSucursal, vFechaEmision, pXmlRecGenDto.getP_tipo_modalidad_id(),
			 * pXmlRecGenDto.getP_tipo_emision_id(),
			 * pXmlRecGenDto.getP_tipo_documento_fiscal_id(), vDocSector, vNumeroFactura,
			 * vPos);
			 * 
			 * String vCufParametros =
			 * iOperacionesCufDomain.generadorCUF(pXmlRecGenDto.getP_nit(),
			 * pXmlRecGenDto.getP_sucursal_id(), vFechaEmision,
			 * pXmlRecGenDto.getP_tipo_modalidad_id(), pXmlRecGenDto.getP_tipo_emision_id(),
			 * pXmlRecGenDto.getP_tipo_documento_fiscal_id(),
			 * pXmlRecGenDto.getP_tipo_documento_sector_id(), vNumeroFactura,
			 * pXmlRecGenDto.getP_punto_venta_id());
			 * 
			 * if (vCufXml.equals(vCufXmlParametros) && vCufXml.equals(vCufParametros)) {
			 * vRespuesta = true; } }
			 */
		} catch (Exception e) {
			LOG.error("Exception || validarCuf : " + e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(pXmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarCuf", new Date(), "CORE", e.getLocalizedMessage(),
					e.getMessage(), Parametros.ESTADO_ACTIVO, pXmlRecGenDto.getP_nit(),
					pXmlRecGenDto.getV_recepcionId(), pXmlRecGenDto.getV_recepcionPaqueteId(),
					pXmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public List<Integer> validarDocumentoIdentidadNitCi(String pArchivoXml) {
		List<Integer> vListaErrores = new ArrayList<>();
		try {
			if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document xml = dBuilder.parse(new InputSource(new StringReader(pArchivoXml)));
				Integer vCodTipoDocId = Integer
						.parseInt(xml.getElementsByTagName("codigoTipoDocumentoIdentidad").item(0).getTextContent());
				Long vNumDoc = 0L;

				switch (vCodTipoDocId) {
				case Parametros.F_TIPO_DOCUMENTO_IDENTIDAD_NIT:
					vNumDoc = Long.parseLong(xml.getElementsByTagName("numeroDocumento").item(0).getTextContent());
					vListaErrores.addAll(this.validarDocumentoIdentidadNit(vNumDoc));
					break;
				case Parametros.F_TIPO_DOCUMENTO_IDENTIDAD_CI:
					vNumDoc = Long.parseLong(xml.getElementsByTagName("numeroDocumento").item(0).getTextContent());
					vListaErrores.addAll(this.validarDocumentoIdentidadCi(vNumDoc));
					break;
				default:
					break;
				}
			} else {
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.XML_INVALIDO);
			}
		} catch (Exception e) {
			vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.XML_INVALIDO);
			LOG.error("validarDocumentoIdentidad || Error al obtener Datos Error Factura");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pArchivoXml));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarDocumentoIdentidadNitCi", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaErrores;
	}

	private List<Integer> validarDocumentoIdentidadNit(Long pNumDoc) {
		List<Integer> vListaErrores = new ArrayList<>();
		try {
			if (pNumDoc != null && pNumDoc > 0) {
				Integer vResNitBd = iJsonDomain.validarNitBd(pNumDoc);
				if (vResNitBd == null || vResNitBd != Parametros.CODIGO_EXITO_VALIDACION_NIT_BD)
					vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.NIT_INVALIDO);
			} else {
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.NIT_INVALIDO);
			}
		} catch (Exception e) {
			vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.NIT_INVALIDO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarDocumentoIdentidadNit", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaErrores;
	}

	private List<Integer> validarDocumentoIdentidadCi(Long pNumDoc) {
		List<Integer> vListaErrores = new ArrayList<>();
		try {
			if (pNumDoc != null && pNumDoc > 0) {
				// TODO (Edwin Coro) Aun no existe servicio para validacion de CI
			} else {
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.CEDULA_IDENTIDAD_INVALIDO);
			}
		} catch (Exception e) {
			vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.CEDULA_IDENTIDAD_INVALIDO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarDocumentoIdentidadCi", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vListaErrores;
	}

	@Override
	public boolean validarCodExcDoc(String pArchivoXml) {
		boolean vResultado = false;
		try {
			if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document xml = dBuilder.parse(new InputSource(new StringReader(pArchivoXml)));
				String vCodExp = xml.getElementsByTagName("codigoExcepcionDocumento").item(0).getTextContent();
				String vCodDocSector = xml.getElementsByTagName("codigoDocumentoSector").item(0).getTextContent();
				long vNumCodExp = vCodExp.isEmpty() ? 0 : Long.parseLong(vCodExp);
				int vNumDocSector = vCodDocSector.isEmpty() ? 0 : Integer.parseInt(vCodDocSector);
				if (vNumCodExp > 0 && vNumDocSector > 0) {
					// Invoca funcion en base de datos para verificacion de existencia de Codigo
					// Excepcion Documento (Codigo Recepcion)
					Integer vExiste = iJsonDomain.verificarExistenciaCodigoRecepcion(vNumDocSector, vNumCodExp);
					if (vExiste != null && vExiste > 0) {
						vResultado = true;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("validarDocumentoIdentidad || Error al obtener Datos Error Factura");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pArchivoXml));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarCodExcDoc", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vResultado;
	}

	/**
	 * @author rosario.garcia
	 * @descripcion metodo que permite registrar los Logs en las estructuras de
	 *              etapa 0
	 * 
	 * @fecha 31/05/20199
	 * 
	 */
	private void registrarLogGeneracionCUF(XmlRecepcionGenericoDto pXmlRecGenDto, int codigoRespuestaLogCUF) {

		LogConsumoServicioDto vObjLogConsumoServicioDto = new LogConsumoServicioDto();
		ObjectMapper mapper = new ObjectMapper();
		ModelMapper vMapper = new ModelMapper();
		if (pXmlRecGenDto.getP_tipo_ambiente_id() != null && pXmlRecGenDto.getP_tipo_ambiente_id() == 2) {
			try {
				vObjLogConsumoServicioDto = vMapper.map(pXmlRecGenDto, LogConsumoServicioDto.class);
				if (pXmlRecGenDto.getP_punto_venta_id() == null)
					pXmlRecGenDto.setP_punto_venta_id(0);
				vObjLogConsumoServicioDto.setP_codigo_error(codigoRespuestaLogCUF + "");
				vObjLogConsumoServicioDto.setP_etapa_certificacion_sistema_id(Parametros.ETAPA_GENERACION_CUF);
				vObjLogConsumoServicioDto.setP_descripcion_respuesta(pXmlRecGenDto.getP_nit() + "|"
						+ pXmlRecGenDto.getP_sucursal_id() + "|" + pXmlRecGenDto.getP_tipo_modalidad_id() + "|"
						+ pXmlRecGenDto.getP_tipo_emision_id() + "|" + pXmlRecGenDto.getP_tipo_documento_fiscal_id()
						+ "|" + pXmlRecGenDto.getP_tipo_documento_sector_id() + "|"
						+ pXmlRecGenDto.getP_punto_venta_id());
				JSONObject jsonRecepcion = new JSONObject();
				JSONObject jsonFinal = new JSONObject();
				jsonRecepcion = new JSONObject(mapper.writeValueAsString(vObjLogConsumoServicioDto));
				jsonFinal.put("xmlRecepcionLog", jsonRecepcion);
				// Registro de log etapa 0
				// System.out.println("Json Final--->>>"+jsonFinal.toString());
				iJsonDomain.registroLogCertificacionSistemas(jsonFinal.toString());

				jsonFinal = null;
				jsonRecepcion = null;

			} catch (Exception e) {
				LOG.error("Exception || registrarLogGeneracionCUF : " + e.getMessage());
				LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
						Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogGeneracionCUF", new Date(), "CORE",
						e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pXmlRecGenDto.getP_nit(),
						pXmlRecGenDto.getV_recepcionId(), pXmlRecGenDto.getV_recepcionPaqueteId(),
						pXmlRecGenDto.getP_tipo_documento_sector_id());
				iValRec.registrarLogExcepcionBDErrores(vErrorDto);
			}
		}
		mapper = null;
		vMapper = null;
		vObjLogConsumoServicioDto = null;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarValidarPortalWeb(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		try {
			xmlRecGenDto.setV_archivoXml(null);
			ObjectMapper mapper = new ObjectMapper();
			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			JSONObject jsonRecepcionEtapa1 = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			JSONObject jsonFinalEtapa1 = new JSONObject();
			jsonFinalEtapa1.put("XmlFactura", "");
			jsonFinalEtapa1.put("xmlRecepcionGenerica", jsonRecepcionEtapa1);
			solicitud.setSolicitud(jsonFinalEtapa1.toString());

			LOG.info("JsonRecepcionBaseDatos || " + solicitud.getSolicitud());
			String vRequestEtapa1 = iJsonDomain.recepcionEtapa1(solicitud.getSolicitud());

			if (vRequestEtapa1 != null) {
				// DESPLIEGUE DE RESULTADO DE LISTA ERRORES
				String[] respuesta = vRequestEtapa1.split(":");
				if (respuesta[0].equals(Parametros.resultadoIdRecepcionBd)) {
					vRespuesta.setCodigoRecepcion(Long.parseLong(respuesta[1]));
					xmlRecGenDto.setV_recepcionId(vRespuesta.getCodigoRecepcion());
					xmlRecGenDto.setV_archivoXml(iutil.decodificarArchivo(xmlRecGenDto.getV_archivo()));

					RecepcionErrorDetalleDto vErrorDetalle = this
							.obtenerDatosErrorFactura(xmlRecGenDto.getV_archivoXml());
					// Validacion de NITCI
					if (!excluyeValidacionDocumentoIdentidad(xmlRecGenDto.getP_tipo_documento_sector_id())) {
						List<Integer> vResValNitCi = this
								.validarDocumentoIdentidadNitCi(xmlRecGenDto.getV_archivoXml());
						if (!vResValNitCi.isEmpty() && !this.validarCodExcDoc(xmlRecGenDto.getV_archivoXml())) {
							vErrorDetalle.getListaCodigosError().addAll(vResValNitCi);
						}
					}

					if (vErrorDetalle.getListaCodigosError().isEmpty()) {

						// TODO: Reynaldo : Individual conversion de archivo XML a B64
						xmlRecGenDto.setV_archivo(iutil.codificarToBase64(xmlRecGenDto.getV_archivoXml()));

						JSONObject jsonFactura = XML.toJSONObject(xmlRecGenDto.getV_archivoXml());
						JSONObject jsonRecepcionEtapa2 = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
						JSONObject jsonFinalEtapa2 = new JSONObject();
						jsonFinalEtapa2.put("XmlFactura", jsonFactura);
						jsonFinalEtapa2.put("xmlRecepcionGenerica", jsonRecepcionEtapa2);
						solicitud.setSolicitud(jsonFinalEtapa2.toString());

						LOG.info("JsonRecepcionBDEtapa2 || " + solicitud.getSolicitud());
						String vRequestEtapa2 = iJsonDomain.recepcionEtapa2(solicitud.getSolicitud());

						if (vRequestEtapa2 != null && vRequestEtapa2.trim().length() != 0
								&& vRequestEtapa2.equals("1")) {
							// validacion recepcion
							JSONObject jsonValRecep = new JSONObject();
							xmlRecGenDto.setV_archivoXml(null);
							xmlRecGenDto.setV_archivo(null);
							JSONObject jsonDtoValRec = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
							jsonValRecep.put("xmlRecepcionGenerica", jsonDtoValRec);

							// Envio de dto a funcion en Base de datos
							LOG.info("Json validacionRecepcionBd || " + jsonValRecep.toString());
							String vRequest = iJsonDomain.validacionRecepcionXml(jsonValRecep.toString());
							JSONObject objeto = new JSONObject(vRequest);
							List<String> vListaErrores = Arrays
									.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));
							vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
							vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));
							if (!vListaErrores.get(0).equals("0")) {
								vRespuesta.setListaCodigosRespuestas(vListaErrores.stream()
										.map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
							} else {
								vRespuesta.setTransaccion(true);
								this.noticarPushFactura(xmlRecGenDto);
							}
						} else {
							// error en etapa 2
							vErrorDetalle.getListaCodigosError().add(Integer.parseInt(vRequestEtapa2));
							vRespuesta.setListaCodigosRespuestas(vErrorDetalle.getListaCodigosError());
							this.registrarFacturaInvalida(jsonFinalEtapa2.toString(), vErrorDetalle);
						}
					} else {
						// error validacion nit/ci
						vRespuesta.setListaCodigosRespuestas(vErrorDetalle.getListaCodigosError());
						this.registrarFacturaInvalida(jsonFinalEtapa1.toString(), vErrorDetalle);
					}
				} else {
					List<Integer> vListaErrores = new ArrayList<>();
					for (String error : respuesta[1].split(",")) {
						vListaErrores.add(Integer.parseInt(error));
					}
					vRespuesta.setListaCodigosRespuestas(vListaErrores);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
				}
			} else {
				LOG.error("Error en request recepcionEtapa1: " + vRequestEtapa1);
				vRespuesta.getListaCodigosRespuestas()
						.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			}
		} catch (Exception e) {
			LOG.error("enviarJsonRecepcionBDPortalWeb || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "recepcionarValidarPortalWeb", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarRecepcionMasXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				XmlRecepcionMasivaPrevaloradaDto dtoAux = new XmlRecepcionMasivaPrevaloradaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setFechaEnvio(
						new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
				dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
				dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_RECEPCION_MASIVA);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_MASIVA));
				}
			} else {
				XmlRecepcionMasivaDto dtoAux = new XmlRecepcionMasivaDto();
				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setFechaEnvio(
						new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
				dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
				dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_RECEPCION_MASIVA);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_MASIVA));
				}
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarRecepcionMasXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto enviarJsonRecepcionBDEtapa1Masivo(
			XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		try {
			// Limpiar archivo XML
			xmlRecGenDto.setV_archivoXml(null);
			xmlRecGenDto.setV_archivoXmlB64(null);

			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			JSONObject jsonFinal = new JSONObject();
			jsonFinal.put("XmlFactura", "");
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			solicitud.setSolicitud(jsonFinal.toString());

			// LOG.info("EnviarJsonRecepcionBaseDatos || " + solicitud.getSolicitud());

			String vRequest = iJsonDomain.recepcionEtapa1Masivo(solicitud.getSolicitud());

			if (vRequest != null) {
				// DESPLIEGUE DE RESULTADO DE LISTA ERRORES
				String[] respuesta = vRequest.split(":");
				if (respuesta[0].equals(Parametros.resultadoIdRecepcionBd)) {
					xmlRecGenDto.setV_recepcionPaqueteId(Long.parseLong(respuesta[1]));
					vRespuesta.setCodigoRecepcion(Long.parseLong(respuesta[1]));
					vRespuesta.setTransaccion(true);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_PENDIENTE);
				} else {
					List<Integer> vListaErrores = new ArrayList<>();
					for (String error : respuesta[1].split(",")) {
						vListaErrores.add(Integer.parseInt(error));
					}
					vRespuesta.setListaCodigosRespuestas(vListaErrores);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
				}
			} else {
				LOG.error("Error en request recepcionEtapa1: " + vRequest);
				vRespuesta.getListaCodigosRespuestas()
						.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
				vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			}
		} catch (Exception e) {
			LOG.error("enviarJsonRecepcionBDEtapa1 || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "enviarJsonRecepcionBDEtapa1Masivo", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		byte[] compressed = Base64.getDecoder().decode(xmlRecGenDto.getV_archivo());
		List<String> listaFacturasB64 = iutil.decompressLoteFacturas(compressed);
		this.registrarLogEnvioMasivo(xmlRecGenDto, vRespuesta.getCodigoEstado(), listaFacturasB64.size());

		return vRespuesta;
	}

	@Override
	public void enviarJsonFacturaMasivoHilo(XmlRecepcionGenericoDto xmlRecGenDto) {

		if (xmlRecGenDto.getV_recepcionPaqueteId() > 0) {

			HiloRegistrarMasivoFactura hilo = new HiloRegistrarMasivoFactura(iValRec, iutil, iJsonDomain,
					iSreFacArchivoXmlValidoDomain);
			hilo.setXmlRecGenDto(xmlRecGenDto);
			hilo.start();
		}
	}

	@Override
	public RespuestaXmlXsdDto validarValidacionRecepcionMasXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				XmlValidacionRecepcionMasivaFacturaPrevaloradaDto dtoAux = new XmlValidacionRecepcionMasivaFacturaPrevaloradaDto();

				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCuape(xmlRecGenDto.getP_cuape());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setCodigoRecepcion(xmlRecGenDto.getV_recepcionPaqueteId());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_MASIVA);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_MASIVA));
				}
			} else {
				XmlValidacionRecepcionMasivaFacturaDto dtoAux = new XmlValidacionRecepcionMasivaFacturaDto();

				dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
				dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
				dtoAux.setCodigoEmision(xmlRecGenDto.getP_tipo_emision_id());
				dtoAux.setCodigoModalidad(xmlRecGenDto.getP_tipo_modalidad_id());
				dtoAux.setNit(xmlRecGenDto.getP_nit());
				dtoAux.setCuis(xmlRecGenDto.getP_cuis());
				dtoAux.setCufd(xmlRecGenDto.getP_cufd());
				dtoAux.setCodigoDocumentoFiscal(xmlRecGenDto.getP_tipo_documento_fiscal_id());
				dtoAux.setCodigoDocumentoSector(xmlRecGenDto.getP_tipo_documento_sector_id());
				dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
				dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
				dtoAux.setCodigoRecepcion(xmlRecGenDto.getV_recepcionPaqueteId());

				boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_MASIVA);
				if (!vValXmlXsd) {
					vListaCodigoError
							.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_MASIVA));
				}
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarValidacionRecepcionMasXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	@Override
	public RespuestaValidacionRecepcionPaqueteDto validacionRecepcionMasivaBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();

		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonDomain.validacionRecepcionMasivaXml(jsonFinal.toString());

				JSONObject objeto = new JSONObject(vRequest);
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));

				if (vRespuesta.getCodigoEstado() == 0) {
					vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
					List<String> vListaErroresGral = Arrays
							.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));
					vRespuesta.setListaCodigosRespuestas(
							vListaErroresGral.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
				} else {
					vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcion"));
					JSONArray vListaFacturasError = objeto.getJSONArray("recepcionerrordetalle");
					List<RecepcionErrorDetalleDto> listaErroresDetalles = new ArrayList<>();

					for (int i = 0; i < vListaFacturasError.length(); i++) {
						JSONObject errorDetalle = vListaFacturasError.getJSONObject(i);
						RecepcionErrorDetalleDto vdto = new RecepcionErrorDetalleDto();
						vdto.setCodigoRecepcionIndividual(errorDetalle.getLong("codigorecepcionindividual"));
						int vNumDocSector = xmlRecGenDto.getP_tipo_documento_sector_id();

						if (vNumDocSector == Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
							vdto.setNumeroFactura(errorDetalle.getLong("numeroBoleto"));
						}
						if (vNumDocSector == Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO) {
							vdto.setNumeroFactura(errorDetalle.getLong("numeroNotaCreditoDebito"));
						}
						if (vNumDocSector != Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO
								&& vNumDocSector != Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
							vdto.setNumeroFactura(errorDetalle.getLong("numerofactura"));
						}

						vdto.setCuf(errorDetalle.getString("cuf"));
						List<String> vListaErroresInd = Arrays
								.asList(errorDetalle.getString("listacodigoserror").split("\\s*,\\s*"));
						if (!vListaErroresInd.get(0).equals("0") && !vListaErroresInd.get(0).isEmpty()) {
							vRespuesta.setListaCodigosRespuestas(vListaErroresInd.stream().map(s -> Integer.parseInt(s))
									.collect(Collectors.toList()));
							vdto.setListaCodigosError(vListaErroresInd.stream().map(s -> Integer.parseInt(s))
									.collect(Collectors.toList()));
							listaErroresDetalles.add(vdto);
						}
					}

					if (vListaFacturasError.length() < 1)
						vRespuesta.setTransaccion(true);

					vRespuesta.setListaErroresDetalles(listaErroresDetalles);
				}
			}
		} catch (Exception e) {
			LOG.error("validacionRecepcionPaqueteBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionRecepcionMasivaBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		// TODO: AJUSTE DE LOG MASIVO
		this.registrarLogEnvioMasivo(xmlRecGenDto, vRespuesta.getCodigoEstado(), 0);
		return vRespuesta;
	}

	/**
	 * @author edwin.coro
	 * @descripcion
	 * @param pDocSector valor tipo documento sector
	 * @return true que implica no realizar validacion documento de identidad NIT/CI
	 */
	private boolean excluyeValidacionDocumentoIdentidad(int pDocSector) {
		ArrayList<Integer> intList = new ArrayList<>();
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_EXPORTACION_MINERA);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_DE_EXPORTACION);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_DE_EXPORTACION_YPFB);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_HIDROCARBURO);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_PREVALORADA_DIGITAL);
		intList.add(Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_LIBRE_CONSIGNACION);
		intList.add(Parametros.DOCUMENTO_SECTOR_FACTURA_LIBRE_CONSIGNACION_YPFB);

		return intList.contains(pDocSector);
	}

	/**
	 * @autor edwin.coro
	 * @descripción Obtiene el documento xsd con el cual se debe comparar la factura
	 *              Se sobreentiende que los valores de pTipoEmision y pDocSector
	 *              existen en las parametricas
	 * @param pTipoEmision tipo de emision 1-online, 2-oflfine, 4-contingencia,..
	 * @param pDocSector   tipo documento sector (en caso de contingencia)
	 * @return nombre del archivo xsd para validar factura xml
	 * @fecha 03/07/2019
	 *
	 * @autor edwin.coro
	 * @descripción Se ajusta debido a tratamiendo de documentos fiscales
	 *              contingencia,
	 * @param pTipoDocFiscal tipo doc fiscal 4-factura contingencia, 6-nota fiscal
	 *                       contingencia,..
	 * @param pDocSector     tipo documento sector (en caso de contingencia)
	 * @return nombre del archivo xsd para validar factura xml
	 * @fecha modificación 09/10/2019
	 */
	private String obtenerNombreDocFiscal(int pTipoDocFiscal, int pDocSector) {
		String vResultado = "";
		if (pTipoDocFiscal == Parametros.F_TIPO_DOC_FISCAL_FACTURA_CONTIGENCIA) {
			switch (pDocSector) {
			case Parametros.DOCUMENTO_SECTOR_FACTURA_ESTANDAR:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_ESTANDAR_1;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_SECTORES_EDUCATIVOS:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_SECTOR_EDUCATIVO_2;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_ALQUILER_DE_BIENES_INMUEBLES:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_ALQUILER_DE_BIENES_INMUEBLES_3;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIALIZACION_DE_HIDROCARBUROS:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_COMERCIALIZACION_DE_HIDROCARBUROS_4;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_SERVICIOS_BASICOS:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_SERVICIOS_BASICOS_5;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_DE_EMBOTELLADORAS:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_DE_EMBOTELLADORAS_6;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_ENTIDADES_FINANCIERAS:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_ENTIDADES_FINANCIERAS_7;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_HOTELES:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_HOTELES_8;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_HOSPITALES_CLINICAS:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_HOSPITALES_CLINICAS_9;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_JUEGOS_DE_AZAR:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_JUEGOS_DE_AZAR_10;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_ARTISTAS_INTERNACIONALES:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_ARTISTAS_INTERNACIONALES_11;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_DE_EXPORTACION:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_COMERCIAL_DE_EXPORTACION_12;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_HIDROCARBURO:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_HIDROCARBURO_23;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_VENTA_INTERNA_MINERA:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_VENTA_INTERNA_MINERA_27;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_PREVALORADA_DIGITAL:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_PREVALORADA_DIGITAL_28;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_TELECOMUNICACION:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_TELECOMUNICACION_29;
				break;
			}
		} else if (pTipoDocFiscal == Parametros.F_TIPO_DOC_FISCAL_NOTA_FISCAL_CONTIGENCIA) {
			switch (pDocSector) {
			case Parametros.DOCUMENTO_SECTOR_FACTURA_LIBRE_CONSIGNACION:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_LIBRE_CONSIGNACION_13;
				break;
			case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_ZONA_FRANCA:
				vResultado = Parametros.ARCHIVO_XSD_NOTA_FISCAL_ZONA_FRANCA_14;
				break;
			case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_ARTISTAS_NACIONALES:
				vResultado = Parametros.ARCHIVO_XSD_NOTA_FISCAL_ARTISTAS_NACIONALES_15;
				break;
			case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_SEGURIDAD_ALIMENTARIA:
				vResultado = Parametros.ARCHIVO_XSD_NOTA_FISCAL_SEGURIDAD_ALIMENTARIA_16;
				break;
			case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_COMPRA_VENTA_DE_MONEDA_EXTRANJERA:
				vResultado = Parametros.ARCHIVO_XSD_NOTA_FISCAL_COMPRA_VENTA_MONEDA_EXTRANJERA_17;
				break;
			case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_TURISMO_RECEPTIVO:
				vResultado = Parametros.ARCHIVO_XSD_NOTA_FISCAL_TURISMO_RECEPTIVO_21;
				break;
			case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_TASA_CERO:
				vResultado = Parametros.ARCHIVO_XSD_NOTA_FISCAL_TASA_CERO_22;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_DE_EXPORTACION_YPFB:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_COMERCIAL_DE_EXPORTACION_YPFB_24;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_EXPORTACION_MINERA:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_COMERCIAL_EXPORTACION_MINERA_25;
				break;
			case Parametros.DOCUMENTO_SECTOR_FACTURA_LIBRE_CONSIGNACION_YPFB:
				vResultado = Parametros.ARCHIVO_XSD_FACTURA_LIBRE_CONSIGNACION_YPFB_26;
				break;
			}
		} else {
			vResultado = Parametros.ARCHIVO_XSD_DOCUMENTO_FISCAL;
		}
		return vResultado;
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo implementado en etapa 2 de recepcion de paquetes,
	 *              invocado para el procesamiento en cada factura
	 * @param xmlRecGenDto dto obtenido con los datos de los parametros obtenidos en
	 *                     el servicio
	 * @param pTipoEmision tipo de emision validado con anterioridad (existe en
	 *                     parametricas)
	 * @param xmlB64       factura individual codificada base 64 (extraida del paq)
	 * @fecha 03/07/2019
	 */
	@Override
	public void procesarPaqueteIndividual(XmlRecepcionGenericoDto xmlRecGenDto, int pTipoEmision, String xmlB64) {

		int vCodigoRespuestaLogCUF = 1000;
		try {

			String vXml = null;
			vXml = iutil.decodificarToBase64(xmlB64);
			RecepcionErrorDetalleDto vErrorDetalle = this.obtenerDatosErrorFactura(vXml);

			// Limpieza de archivo XML B64
			xmlRecGenDto.setV_archivo(null);

			// Validacion de CUF
			boolean vResValCuf = this.validarCuf(xmlRecGenDto, xmlB64, pTipoEmision);
			if (!vResValCuf) {
				vErrorDetalle.getListaCodigosError()
						.add(CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO);
				// codigo_respuesta generacion de CUF - etapa0
				vCodigoRespuestaLogCUF = CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO;
			}

			// Validacion de NITCI
			if (!excluyeValidacionDocumentoIdentidad(xmlRecGenDto.getP_tipo_documento_sector_id())) {
				List<Integer> vResValNitCi = this.validarDocumentoIdentidadNitCi(vXml);
				if (!vResValNitCi.isEmpty() && !this.validarCodExcDoc(vXml)) {
					vErrorDetalle.getListaCodigosError().addAll(vResValNitCi);
				}
			}

			String vTemp = "";
			// ModalidadComputarizada
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA)
				vTemp = vXml;
			// ModalidadElectronica
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_ELECTRONICA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				vTemp = iutil.formatearSignature(vXml);
			}

			// Valida estructura de la factura contra el archivo xsd
			List<Integer> vValFact = iutil.validarFacturaXml(vTemp, obtenerNombreDocFiscal(
					xmlRecGenDto.getP_tipo_documento_fiscal_id(), xmlRecGenDto.getP_tipo_documento_sector_id()));
			if (!vValFact.isEmpty()) {
				vErrorDetalle.getListaCodigosError()
						.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			}

			// Verifica la firma si es modalidad electronica
			if (xmlRecGenDto.getP_tipo_modalidad_id() == Parametros.F_MODALIDAD_FACTURA_ELECTRONICA) {
				SolicitudValidacionFirmadoXmlFacturaDto vSolValFirmXmlFactDto = new SolicitudValidacionFirmadoXmlFacturaDto();
				vSolValFirmXmlFactDto.setArchivo(xmlB64);
				if (!iServiciosFirmaClientRest.verificarFirmadoXml(vSolValFirmXmlFactDto).isOk()) {
					vErrorDetalle.getListaCodigosError().add(CodigosMensajesServiciosSOAPServiceImpl.FIRMA_INCORRRECTA);
				}
			}

			// Asignacion de factura xmlB64
			xmlRecGenDto.setV_archivo(xmlB64);

			// Si existen errores registra factura invalida, caso contrario invoca funcion
			if (vErrorDetalle.getListaCodigosError().isEmpty()) {
				// LOG.info("enviarJsonRecepcionBDEtapa2 || " + solicitud.getSolicitud());
				RespuestaJsonDto vRequestPaq = new RespuestaJsonDto();
				vRequestPaq.setRespuesta(
						iJsonDomain.recepcionEtapa2(generarJsonPaqueteMasivo(xmlB64, xmlRecGenDto).getSolicitud()));
				if (vRequestPaq == null || vRequestPaq.getRespuesta().length() == 0
						|| !vRequestPaq.getRespuesta().equals("1")) {
					vErrorDetalle.getListaCodigosError().add(
							CodigosMensajesServiciosSOAPServiceImpl.ERROR_EJECUCION_SERVICIO_VALIDACION_DETALLE_DOCUMENTO_FISCAL);
				}
			}

			if (!vErrorDetalle.getListaCodigosError().isEmpty()) {
				this.registrarFacturaInvalida(generarJsonPaqueteMasivo(xmlB64, xmlRecGenDto).getSolicitud(),
						vErrorDetalle);
			}

			// Registrar Log etapa 0 (Generacion de CUF) -- 20190604
			registrarLogGeneracionCUF(xmlRecGenDto, vCodigoRespuestaLogCUF);
		} catch (Exception e) {
			LOG.error("procesarPaquete || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "procesarPaqueteIndividual", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	@Override
	public Long obtenerCodigoRecIndBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		Long vRespuesta = 0L;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);
				vRespuesta = iJsonDomain.obtenerCodigoRecIndBd(jsonFinal.toString());
			}
		} catch (Exception e) {
			LOG.error("obtenerCodigoRecIndBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerCodigoRecIndBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vRespuesta;
	}

	/**
	 * @autor Reynaldo.Chambi
	 * @descripción metodo implementado en etapa 2 de recepcion masiva, invocado
	 *              para el procesamiento en cada factura
	 * @param xmlRecGenDto dto obtenido con los datos de los parametros obtenidos en
	 *                     el servicio
	 * @param pTipoEmision tipo de emision validado con anterioridad (existe en
	 *                     parametricas)
	 * @param xmlB64       factura individual codificada base 64 (extraida del
	 *                     paquete masivo)
	 * @fecha 15/07/2019
	 */
	@Override
	public void procesarMasivoIndividual(XmlRecepcionGenericoDto xmlRecGenDto, int pTipoEmision, String xmlB64) {
		try {
			String vXml = null;
			vXml = iutil.decodificarToBase64(xmlB64);
			RecepcionErrorDetalleDto vErrorDetalle = new RecepcionErrorDetalleDto();
			vErrorDetalle = this.obtenerDatosErrorFactura(vXml);

			// Limpieza de archivo XML B64
			xmlRecGenDto.setV_archivo(null);

			// Validacion de CUF
			boolean vResValCuf = this.validarCuf(xmlRecGenDto, xmlB64, pTipoEmision);
			if (!vResValCuf)
				vErrorDetalle.getListaCodigosError()
						.add(CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO);

			// Validacion de NITCI
			if (!excluyeValidacionDocumentoIdentidad(xmlRecGenDto.getP_tipo_documento_sector_id())) {
				List<Integer> vResValNitCi = this.validarDocumentoIdentidadNitCi(vXml);
				if (!vResValNitCi.isEmpty() && !this.validarCodExcDoc(vXml))
					vErrorDetalle.getListaCodigosError().addAll(vResValNitCi);
			}

			String vTemp = "";
			// ModalidadComputarizada
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_COMPUTARIZADA)
				vTemp = vXml;
			// ModalidadElectronica
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_ELECTRONICA)
				vTemp = iutil.formatearSignature(vXml);

			// Valida estructura de la factura contra el archivo xsd
			List<Integer> vValFact = iutil.validarFacturaXml(vTemp, obtenerNombreDocFiscal(
					xmlRecGenDto.getP_tipo_documento_fiscal_id(), xmlRecGenDto.getP_tipo_documento_sector_id()));
			if (!vValFact.isEmpty())
				vErrorDetalle.getListaCodigosError()
						.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);

			// Verifica la firma si es modalidad electronica
			if (xmlRecGenDto.getP_tipo_modalidad_id() == Parametros.F_MODALIDAD_FACTURA_ELECTRONICA) {
				SolicitudValidacionFirmadoXmlFacturaDto vSolValFirmXmlFactDto = new SolicitudValidacionFirmadoXmlFacturaDto();
				vSolValFirmXmlFactDto.setArchivo(xmlB64);
				if (!iServiciosFirmaClientRest.verificarFirmadoXml(vSolValFirmXmlFactDto).isOk())
					vErrorDetalle.getListaCodigosError().add(CodigosMensajesServiciosSOAPServiceImpl.FIRMA_INCORRRECTA);
			}
			// Asignacion de factura xmlB64
			xmlRecGenDto.setV_archivo(xmlB64);

			// Si existen errores registra factura invalida, caso contrario invoca funcion
			if (vErrorDetalle.getListaCodigosError().isEmpty()) {
				RespuestaJsonDto vRequestPaq = new RespuestaJsonDto();
				vRequestPaq.setRespuesta(iJsonDomain
						.recepcionEtapa2Masivo(generarJsonPaqueteMasivo(xmlB64, xmlRecGenDto).getSolicitud()));
				if (vRequestPaq == null || vRequestPaq.getRespuesta().length() == 0
						|| !vRequestPaq.getRespuesta().equals("1")) {
					vErrorDetalle.getListaCodigosError().add(
							CodigosMensajesServiciosSOAPServiceImpl.ERROR_EJECUCION_SERVICIO_VALIDACION_DETALLE_DOCUMENTO_FISCAL);
				}
			}

			if (!vErrorDetalle.getListaCodigosError().isEmpty()) {
				this.registrarFacturaInvalida(generarJsonPaqueteMasivo(xmlB64, xmlRecGenDto).getSolicitud(),
						vErrorDetalle);
				LOG.info("===" + generarJsonPaqueteMasivo(xmlB64, xmlRecGenDto).getSolicitud());
			}

		} catch (Exception e) {
			LOG.error("procesarMasivo || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "procesarMasivoIndividual", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	/**
	 * @author Reynaldo Chambi
	 * @descripcion metodo que permite registrar los Logs en las estructuras de
	 *              etapa 10 - Envios Masivos
	 * @fecha 31/05/20199
	 * 
	 */
	private void registrarLogEnvioMasivo(XmlRecepcionGenericoDto pXmlRecGenDto, int codigoRespuestaLogEnvioMasivo,
			int cantidadFacturas) {

		LogConsumoServicioDto vObjLogConsumoServicioDto = new LogConsumoServicioDto();
		ObjectMapper mapper = new ObjectMapper();
		ModelMapper vMapper = new ModelMapper();
		if (pXmlRecGenDto.getP_tipo_ambiente_id() != null && pXmlRecGenDto.getP_tipo_ambiente_id() == 2) {
			try {
				vObjLogConsumoServicioDto = vMapper.map(pXmlRecGenDto, LogConsumoServicioDto.class);
				if (pXmlRecGenDto.getP_punto_venta_id() == null)
					pXmlRecGenDto.setP_punto_venta_id(0);
				vObjLogConsumoServicioDto.setP_codigo_error(codigoRespuestaLogEnvioMasivo + "");
				vObjLogConsumoServicioDto.setP_etapa_certificacion_sistema_id(Parametros.ETAPA_X_ENVIO_MASIVO);
				vObjLogConsumoServicioDto.setV_cantidadMaximaFacturas(iJsonDomain
						.obtenerCantidadMaximaMasivo(pXmlRecGenDto.getP_nit(), pXmlRecGenDto.getP_sucursal_id()));
				vObjLogConsumoServicioDto.setV_cantidadFacturas(cantidadFacturas);
				vObjLogConsumoServicioDto.setV_recepcionPaqueteId(pXmlRecGenDto.getV_recepcionPaqueteId());
				vObjLogConsumoServicioDto.setP_descripcion_respuesta(
						pXmlRecGenDto.getP_codigo_sistema() + "|" + pXmlRecGenDto.getP_tipo_ambiente_id() + "|"
								+ pXmlRecGenDto.getP_tipo_emision_id() + "|" + pXmlRecGenDto.getP_tipo_modalidad_id()
								+ "|" + pXmlRecGenDto.getP_nit() + "|" + pXmlRecGenDto.getP_cuis() + "|"
								+ pXmlRecGenDto.getP_cufd() + "|" + pXmlRecGenDto.getP_tipo_documento_fiscal_id() + "|"
								+ pXmlRecGenDto.getP_tipo_documento_sector_id() + "|" + pXmlRecGenDto.getP_sucursal_id()
								+ "|" + pXmlRecGenDto.getP_punto_venta_id() + "|" + pXmlRecGenDto.getP_fecha_envio()
								+ "|" + pXmlRecGenDto.getV_archivo());
				JSONObject jsonRecepcion = new JSONObject();
				JSONObject jsonFinal = new JSONObject();
				jsonRecepcion = new JSONObject(mapper.writeValueAsString(vObjLogConsumoServicioDto));
				jsonFinal.put("xmlRecepcionLog", jsonRecepcion);
				// Registro de log etapa 10
				// System.out.println("Json Final--->>>"+jsonFinal.toString());
				iJsonDomain.registroLogCertificacionSistemas(jsonFinal.toString());

				jsonFinal = null;
				jsonRecepcion = null;

			} catch (Exception e) {
				LOG.error("Exception || registrarLogEnvioMasivo : " + e.getMessage());
				LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
						Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogEnvioMasivo", new Date(), "CORE",
						e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pXmlRecGenDto.getP_nit(),
						pXmlRecGenDto.getV_recepcionId(), pXmlRecGenDto.getV_recepcionPaqueteId(),
						pXmlRecGenDto.getP_tipo_documento_sector_id());
				iValRec.registrarLogExcepcionBDErrores(vErrorDto);
			}
		}
		mapper = null;
		vMapper = null;
		vObjLogConsumoServicioDto = null;
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivo
	 * @return
	 * @fecha 23/07/2019
	 */
	public List<Integer> validarArchivoMasivo(String pArchivo, long pNit, int pSucursal) {
		List<Integer> vListaCodigoError = new ArrayList<>();
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				// Decodifica el archivo recibido y obtiene lista de facturas codificadas
				byte[] compressed = Base64.getDecoder().decode(pArchivo);
				List<String> listaFacturasB64 = iutil.decompressLoteFacturas(compressed);
				if (listaFacturasB64.isEmpty()) {
					vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
				} else {

					for (String xmlB64 : listaFacturasB64) {
						if (iutil.decodificarToBase64(xmlB64) == null)
							vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
					}

					int vCantidadMaxima = iJsonDomain.obtenerCantidadMaximaMasivo(pNit, pSucursal);
					if (vCantidadMaxima > 0) {
						if (listaFacturasB64.size() > vCantidadMaxima)
							vListaCodigoError.add(
									CodigosMensajesServiciosSOAPServiceImpl.A_SOBREPASADO_EL_LIMITE_PERMITIDO_ASIGNADO);
					} else {
						vListaCodigoError.add(
								CodigosMensajesServiciosSOAPServiceImpl.REGISTRO_SOLICITUD_PROCESO_MASIVO_INEXISTENTE);
					}
				}
			} else {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
			}

		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.ARCHIVO_INVALIDO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarArchivoMasivo", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vListaCodigoError;
	}

	@Override
	public void registrarLogPruebaObligatoriaEtapa6(LogEtapa6Dto pLogEtapa6) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(pLogEtapa6));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("jsonEtapa6", jsonRecepcion);

				// Envio de dto a funcion en Base de datos
				iJsonDomain.registrarLogPruebaObligatoriaEtapa6(jsonFinal.toString());
			}
		} catch (Exception e) {
			LOG.error("registrarLogPruebaEtapa6Hoja1 || Excepcion : " + e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(pLogEtapa6));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogPruebaObligatoriaEtapa6", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L,
					pLogEtapa6.getCodigoRecepcionPaqueteId(), pLogEtapa6.getTipoDocumentoSectorId());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	@Override
	public void enviarJsonComprasRecepcionBDEtapa2(XmlRecepcionGenericoDto xmlRecGenDto) {

		try {
			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			JSONObject jsonFinal = new JSONObject();
			ObjectMapper mapper = new ObjectMapper();

			String vXmlInd = iutil.decodificarArchivo(xmlRecGenDto.getV_archivo());
			RespuestaJsonDto vRequestInd = new RespuestaJsonDto();
			RecepcionErrorDetalleDto vErrorDetalle = this.obtenerDatosErrorCompras(vXmlInd);

			// TODO: Reynaldo : Individual conversion de archivo XML a B64
			xmlRecGenDto.setV_archivo(iutil.codificarToBase64(vXmlInd));

			JSONObject jsonFactura = XML.toJSONObject(vXmlInd);
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			jsonFinal.put("XmlFactura", jsonFactura);
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

			if (vErrorDetalle.getListaCodigosError().isEmpty()) {
				solicitud.setSolicitud(jsonFinal.toString());
				LOG.info("enviarJsonRecepcionBDEtapa2 || " + solicitud.getSolicitud());

				vRequestInd.setRespuesta(iJsonComprasDomain.recepcionEtapa2(solicitud.getSolicitud()));

				if (vRequestInd == null || vRequestInd.getRespuesta().length() == 0
						|| !vRequestInd.getRespuesta().equals("1")) {
					vErrorDetalle.getListaCodigosError().add(
							CodigosMensajesServiciosSOAPServiceImpl.ERROR_EJECUCION_SERVICIO_VALIDACION_DETALLE_DOCUMENTO_FISCAL);

					if (vRequestInd.getRespuesta().equals("0"))
						LOG.error("RESPUESTA BASE DE DATOS||EXISTE ERRORES EN LA FACTURA/NOTA FISCAL");
					if (vRequestInd.getRespuesta().equals("-1"))
						LOG.error("RESPUESTA BASE DE DATOS||EXCEPCION REGISTRADA EN LOG DE TRANSVERSALES");
					if (vRequestInd.getRespuesta().equals("-2"))
						LOG.error("RESPUESTA BASE DE DATOS||EXCEPCION SIN REGISTRO");
				}
			} else {
				this.registrarCompraInvalida(jsonFinal, vErrorDetalle.getListaCodigosError());
			}
		} catch (Exception e) {
			LOG.error("Error al enviar archivo JSON Etapa 2");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "enviarJsonComprasRecepcionBDEtapa2", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	@Override
	public void enviarJsonComprasRecepcionPaqueteBDEtapa2(XmlRecepcionGenericoDto xmlRecGenDto) {
		try {
			byte[] compressed = Base64.getDecoder().decode(xmlRecGenDto.getV_archivo());
			List<String> listaFacturasB64 = iutil.decompressLoteFacturas(compressed);

			// Parallel processing
			long vTiempoInicioParall = System.currentTimeMillis();
			listaFacturasB64.parallelStream().forEach(xmlB64 -> procesarComprasPaqueteIndividual(xmlRecGenDto, xmlB64));
			long vTiempoFinParall = System.currentTimeMillis();
			long vTiempoParall = (vTiempoFinParall - vTiempoInicioParall) / 1000;
			System.out.println("Tiempo requerido con parallelStream() : " + vTiempoParall);

		} catch (Exception e) {
			LOG.error("Error al enviar archivo JSON Etapa 2");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "enviarJsonComprasRecepcionPaqueteBDEtapa2", new Date(),
					"CORE", e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo implementado en etapa 2 de recepcion de paquetes,
	 *              invocado para el procesamiento en cada factura
	 * @param xmlRecGenDto dto obtenido con los datos de los parametros obtenidos en
	 *                     el servicio
	 * @param pTipoEmision tipo de emision validado con anterioridad (existe en
	 *                     parametricas)
	 * @param xmlB64       factura individual codificada base 64 (extraida del paq)
	 * @fecha 03/08/2019
	 */
	private void procesarComprasPaqueteIndividual(XmlRecepcionGenericoDto xmlRecGenDto, String xmlB64) {
		try {
			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			JSONObject jsonFinal = new JSONObject();
			ObjectMapper mapper = new ObjectMapper();

			String vXml = iutil.decodificarToBase64(xmlB64);
			RecepcionErrorDetalleDto vErrorDetalle = this.obtenerDatosErrorCompras(vXml);

			// TODO: Reynaldo Chambi: registro de Archivo XML B64
			xmlRecGenDto.setV_archivo(xmlB64);
			// Validar CUF
			SolicitudRevertirCadenaDto vSolicitudCuf = new SolicitudRevertirCadenaDto();
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xml = dBuilder.parse(new InputSource(new StringReader(vXml)));
			vSolicitudCuf.setCodigo(xml.getElementsByTagName("cuf").item(0).getTextContent());

			RespuestaDatosCufDto vRespuestaCuf = iServiciosReversionClientRest.reversionCuf(vSolicitudCuf);
			if (vRespuestaCuf.getNitEmisor() != null && vRespuestaCuf.getNitEmisor() > 0) {
				if (xmlRecGenDto.getP_nit().equals(vRespuestaCuf.getNitEmisor())) {
					xmlRecGenDto.setP_tipo_modalidad_id(vRespuestaCuf.getModalidad());
					xmlRecGenDto.setP_tipo_emision_id(Parametros.F_TIPO_EMISION_OFFLINE);
					xmlRecGenDto.setP_tipo_documento_fiscal_id(vRespuestaCuf.getDocumentoFiscal());
					xmlRecGenDto.setP_tipo_documento_sector_id(vRespuestaCuf.getDocumentoSector());
				} else {
					vErrorDetalle.getListaCodigosError().add(CodigosMensajesServiciosSOAPServiceImpl.NIT_INVALIDO);
				}
			} else {
				vErrorDetalle.getListaCodigosError()
						.add(CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO);
			}

			// Valida estructura de la factura contra el archivo xsd
			List<Integer> vValFact = iutil.validarFacturaXml(vXml, Parametros.ARCHIVO_XSD_COMPRAS);
			if (!vValFact.isEmpty()) {
				vErrorDetalle.getListaCodigosError()
						.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			}

			// TODO: REYNALDO CHAMBI REVISAR CON MAS CASOS
			byte[] bytesB = Base64.getDecoder().decode(xmlB64.trim());
			JSONObject jsonFactura = XML.toJSONObject(new String(bytesB, StandardCharsets.UTF_8));
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			jsonFinal.put("XmlFactura", jsonFactura);
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);
			solicitud.setSolicitud(jsonFinal.toString());

			// Si existen errores registra factura invalida, caso contrario invoca funcion
			if (vErrorDetalle.getListaCodigosError().isEmpty()) {
				LOG.info("enviarJsonRecepcionBDEtapa2 || " + solicitud.getSolicitud());
				RespuestaJsonDto vRequestPaq = new RespuestaJsonDto();
				vRequestPaq.setRespuesta(iJsonComprasDomain.recepcionEtapa2(solicitud.getSolicitud()));
				if (vRequestPaq == null || vRequestPaq.getRespuesta().length() == 0
						|| !vRequestPaq.getRespuesta().equals("1")) {
					vErrorDetalle.getListaCodigosError().add(
							CodigosMensajesServiciosSOAPServiceImpl.ERROR_EJECUCION_SERVICIO_VALIDACION_DETALLE_DOCUMENTO_FISCAL);

					if (vRequestPaq.getRespuesta().equals("0"))
						LOG.error("RESPUESTA BASE DE DATOS||EXISTE ERRORES EN LA FACTURA/NOTA FISCAL");
					if (vRequestPaq.getRespuesta().equals("-1"))
						LOG.error("RESPUESTA BASE DE DATOS||EXCEPCION REGISTRADA EN LOG DE TRANSVERSALES");
					if (vRequestPaq.getRespuesta().equals("-2"))
						LOG.error("RESPUESTA BASE DE DATOS||EXCEPCION SIN REGISTRO");
				}
			}

			if (!vErrorDetalle.getListaCodigosError().isEmpty()) {
				this.registrarFacturaInvalida(jsonFinal.toString(), vErrorDetalle);
			}
		} catch (Exception e) {
			LOG.error("procesarPaquete || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "procesarComprasPaqueteIndividual", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que valida el valor del codigo unico factura - CUF .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio,
	 *                     xmlB64 archivo de la factura
	 * @return Respuesta true or false dependiendo la validacion
	 * @fecha 03/05/2019
	 */
	@Override
	public String obtenerCuf(XmlRecepcionGenericoDto pXmlRecGenDto) {

		try {
			DocumentBuilder dBuilder;
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xml = dBuilder
					.parse(new InputSource(new StringReader(iutil.decodificarArchivo(pXmlRecGenDto.getV_archivo()))));

			return xml.getElementsByTagName("cuf").item(0).getTextContent();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("Exception || validarCuf : " + e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("Exception || validarCuf : " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("Exception || validarCuf : " + e.getMessage());
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("Exception || validarCuf : " + e.getMessage());
		}

		return null;
	}

	@Override
	public void enviarJsonComprasFacturaHilo(XmlRecepcionGenericoDto xmlRecGenDto) {

		if (xmlRecGenDto.getV_recepcionId() > 0) {
			HiloRegistrarCompra hilo = new HiloRegistrarCompra();
			hilo.setiValRec(iValRec);
			hilo.setXmlRecGenDto(xmlRecGenDto);
			hilo.start();
		}
	}

	@Override
	public void enviarJsonComprasFacturaPaqueteHilo(XmlRecepcionGenericoDto xmlRecGenDto) {
		if (xmlRecGenDto.getV_recepcionPaqueteId() > 0) {
			HiloRegistrarComprasPaqueteFactura hilo = new HiloRegistrarComprasPaqueteFactura(iValRec);
			hilo.setXmlRecGenDto(xmlRecGenDto);
			hilo.start();
		}
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción recupera datos especificos de
	 * @param archivoXml
	 * @return
	 * @fecha 06/08/2019
	 */
	private RecepcionErrorDetalleDto obtenerDatosErrorCompras(String archivoXml) {
		RecepcionErrorDetalleDto vErrorDetalle = new RecepcionErrorDetalleDto();
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xml = dBuilder.parse(new InputSource(new StringReader(archivoXml)));
			String vNumFactura = xml.getElementsByTagName("numeroFactura").item(0).getTextContent();
			vErrorDetalle
					.setNumeroFactura((vNumFactura == null || vNumFactura.isEmpty()) ? 0 : Long.parseLong(vNumFactura));
			vErrorDetalle.setCuf(xml.getElementsByTagName("cuf").item(0).getTextContent());
		} catch (Exception e) {
			vErrorDetalle.getListaCodigosError()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LOG.error("obtenerDatosErrorFactura || Error al obtener Datos Error Factura");
			LogExcepcion.registrar(e, LOG, MethodSign.build(archivoXml));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerDatosErrorCompras", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vErrorDetalle;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto validacionRecepcionComprasBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		JSONObject jsonRecepcion = new JSONObject();

		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				LOG.info("validacionRecepcionBd || " + jsonFinal.toString());

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonComprasDomain.validacionRecepcionXml(jsonFinal.toString());
				JSONObject objeto = new JSONObject(vRequest);
				List<String> vListaErrores = Arrays.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));

				vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));
				if (!vListaErrores.get(0).equals("0"))
					vRespuesta.setListaCodigosRespuestas(
							vListaErrores.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
				else
					vRespuesta.setTransaccion(true);
			}
		} catch (Exception e) {
			LOG.error("validacionRecepcionBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionRecepcionComprasBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vRespuesta;

	}

	@Override
	public RespuestaValidacionRecepcionPaqueteDto validacionRecepcionComprasPaqueteBd(
			XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();

		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

				LOG.info("validacionRecepcionPaqueteBd || " + jsonFinal.toString());

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonComprasDomain.validacionRecepcionPaqueteXml(jsonFinal.toString());

				JSONObject objeto = new JSONObject(vRequest);
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));

				if (vRespuesta.getCodigoEstado() == 0) {
					vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
					List<String> vListaErroresGral = Arrays
							.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));
					vRespuesta.setListaCodigosRespuestas(
							vListaErroresGral.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
				} else {
					vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcion"));
					JSONArray vListaFacturasError = objeto.getJSONArray("recepcionerrordetalle");
					List<RecepcionErrorDetalleDto> listaErroresDetalles = new ArrayList<>();

					for (int i = 0; i < vListaFacturasError.length(); i++) {
						JSONObject errorDetalle = vListaFacturasError.getJSONObject(i);
						RecepcionErrorDetalleDto vdto = new RecepcionErrorDetalleDto();
						vdto.setCodigoRecepcionIndividual(errorDetalle.getLong("codigorecepcionindividual"));
						vdto.setNumeroFactura(errorDetalle.getLong("numerofactura"));
						vdto.setCuf(errorDetalle.getString("cuf"));
						List<String> vListaErroresInd = Arrays
								.asList(errorDetalle.getString("listacodigoserror").split("\\s*,\\s*"));
						if (!vListaErroresInd.get(0).equals("0") && !vListaErroresInd.get(0).isEmpty()) {
							vRespuesta.setListaCodigosRespuestas(vListaErroresInd.stream().map(s -> Integer.parseInt(s))
									.collect(Collectors.toList()));
							vdto.setListaCodigosError(vListaErroresInd.stream().map(s -> Integer.parseInt(s))
									.collect(Collectors.toList()));
							listaErroresDetalles.add(vdto);
						}
					}

					if (vListaFacturasError.length() < 1)
						vRespuesta.setTransaccion(true);

					vRespuesta.setListaErroresDetalles(listaErroresDetalles);
				}
			}
		} catch (Exception e) {
			LOG.error("validacionRecepcionPaqueteBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionRecepcionComprasPaqueteBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public RespuestaXmlXsdDto validarDocumentoFiscalComprasXsd(String pArchivoXml) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
				List<Integer> vValFact = iutil.validarFacturaXml(pArchivoXml, Parametros.ARCHIVO_XSD_COMPRAS);
				if (!vValFact.isEmpty()) {
					vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
					vListaDescripcionesError
							.addAll(iutil.obtenerErroresXmlXsd(pArchivoXml, Parametros.ARCHIVO_XSD_COMPRAS));
				}
			} else {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarDocumentoFiscalComprasXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, 0, 0L, 0L, 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);
		return vRespuesta;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto enviarJsonComprasRecepcionBDEtapa1(
			XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		try {
			// Limpiar archivo XML
			xmlRecGenDto.setV_archivoXml(null);
			xmlRecGenDto.setV_archivoXmlB64(null);

			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			JSONObject jsonFinal = new JSONObject();
			jsonFinal.put("XmlFactura", "");
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			solicitud.setSolicitud(jsonFinal.toString());

			LOG.info("EnviarJsonRecepcionBaseDatos || " + solicitud.getSolicitud());

			String vRequest = iJsonComprasDomain.recepcionEtapa1(solicitud.getSolicitud());

			if (vRequest != null) {
				// DESPLIEGUE DE RESULTADO DE LISTA ERRORES
				String[] respuesta = vRequest.split(":");
				if (respuesta[0].equals(Parametros.resultadoIdRecepcionBd)) {
					vRespuesta.setCodigoRecepcion(Long.parseLong(respuesta[1]));
					vRespuesta.setTransaccion(true);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_PENDIENTE);
				} else {
					List<Integer> vListaErrores = new ArrayList<>();
					for (String error : respuesta[1].split(",")) {
						vListaErrores.add(Integer.parseInt(error));
					}
					vRespuesta.setListaCodigosRespuestas(vListaErrores);
					vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
				}
			} else {
				LOG.error("Error en request recepcionEtapa1: " + vRequest);
				vRespuesta.getListaCodigosRespuestas()
						.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
				vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			}
		} catch (Exception e) {
			LOG.error("enviarJsonRecepcionBDEtapa1 || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "enviarJsonComprasRecepcionBDEtapa1", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	@Override
	public RespuestaFacturaParcialDto obtenerFacturaDatosParcial(SolicitudFacturaParcialDto pSolicitud) {
		RespuestaFacturaParcialDto vRespuesta = new RespuestaFacturaParcialDto();
		try {
			if (pSolicitud.getpNitEmisor() > 0 && !pSolicitud.getpCuf().isEmpty()) {
				RespuestaDatosCufDto vRespuestaCuf = iServiciosReversionClientRest
						.reversionCuf(new SolicitudRevertirCadenaDto(pSolicitud.getpCuf()));
				if (vRespuestaCuf.getNitEmisor() != null && vRespuestaCuf.getNitEmisor() > 0
						&& (pSolicitud.getpNitEmisor() == vRespuestaCuf.getNitEmisor())) {
					pSolicitud.setpTipoDocumentoFiscal(vRespuestaCuf.getDocumentoFiscal());
					pSolicitud.setpTipoDocumentoSector(vRespuestaCuf.getDocumentoSector());
				} else {
					return vRespuesta;
				}
			} else {
				return vRespuesta;
			}

			if (!pSolicitud.getpCuf().isEmpty() && pSolicitud.getpNitEmisor() > 0
					&& pSolicitud.getpNumeroFactura() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(pSolicitud));
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("jsonObtenerFacturaDatosParcial", jsonRecepcion);

				// Envio de dto a funcion en Base de datos
				String vRequest = iJsonDomain.obtenerFacturaDatosParcial(jsonFinal.toString());

				if (!vRequest.equals("0")) {
					// {"ventaId":15164,"montoTotal":25.00000,"detalle":[{"detalleventaid":26945,"actividadeconomicaid":620100,
					// "productosinid":83141, "productoid":"JN-131231", "descripcion":"JUGO EN
					// VASO", "cantidad":10.00000, "unidadmedida":"58",
					// "preciounitario":2.50000, "subtotal":25.00000}]}

					ObjectMapper modelMapper = new ObjectMapper();
					modelMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
					vRespuesta = modelMapper.readValue(vRequest, RespuestaFacturaParcialDto.class);
				}
			}

			return vRespuesta;
		} catch (Exception e) {
			LOG.error("obtenerFacturaDatosParcial || Excepcion : " + e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			vRespuesta = new RespuestaFacturaParcialDto();
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerFacturaDatosParcial", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pSolicitud.getpNitEmisor(), 0L,
					0L, pSolicitud.getpTipoDocumentoSector());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vRespuesta;
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción Método que realiza la inserción de los log para etapa de 11
	 *              anulacion de facturas
	 * @param pXmlRecGenDto: Archivo en formato xml con la información de la
	 *        recepción pListaErrores Listado de la descripción de los errores luego
	 *        de la validación XML/XSD pResultado: Resultado de la transacción
	 *        pCasoPrueba: Descripción del Caso de Prueba pRecepcionId: Código de la
	 *        recepción
	 * @fecha 15/08/2019
	 */
	@Override
	public void registrarLogAnulacionFactura(XmlRecepcionGenericoDto pXmlRecGenDto, Integer pResultado,
			String pCasoPrueba, Long pRecepcionId) {
		// Realizar el registro de los logs
		LogConsumoServicioDto vObjLogConsumoServicioDto = new LogConsumoServicioDto();
		ObjectMapper mapper = null;
		ModelMapper vMapper = null;
		if (pXmlRecGenDto.getP_tipo_ambiente_id() != null && pXmlRecGenDto.getP_tipo_ambiente_id() == 2) {
			mapper = new ObjectMapper();
			vMapper = new ModelMapper();
			try {
				vObjLogConsumoServicioDto = vMapper.map(pXmlRecGenDto, LogConsumoServicioDto.class);
				vObjLogConsumoServicioDto.setP_descripcion_respuesta(
						pCasoPrueba + '|' + pResultado + "|" + pXmlRecGenDto.getP_punto_venta_id());
				vObjLogConsumoServicioDto.setP_etapa_certificacion_sistema_id(Parametros.ETAPA_XI_ANULACIONES);
				vObjLogConsumoServicioDto.setV_recepcionId(pRecepcionId);
				vObjLogConsumoServicioDto.setP_nit(pXmlRecGenDto.getP_nit());
				JSONObject jsonRecepcion = new JSONObject();
				JSONObject jsonFinal = new JSONObject();
				jsonRecepcion = new JSONObject(mapper.writeValueAsString(vObjLogConsumoServicioDto));
				jsonFinal.put("xmlRecepcionLog", jsonRecepcion);
				iJsonDomain.registroLogCertificacionSistemas(jsonFinal.toString());
				jsonFinal = null;
				jsonRecepcion = null;
			} catch (Exception e) {
				LOG.error("Exception || registrarLogAnulaciones : " + e.getMessage());
				LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
						Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogAnulacionFactura", new Date(), "CORE",
						e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pXmlRecGenDto.getP_nit(),
						pXmlRecGenDto.getV_recepcionId(), pXmlRecGenDto.getV_recepcionPaqueteId(),
						pXmlRecGenDto.getP_tipo_documento_sector_id());
				iValRec.registrarLogExcepcionBDErrores(vErrorDto);
			}
		}
		mapper = null;
		vMapper = null;
		vObjLogConsumoServicioDto = null;
	}

	/**
	 * @autor reynaldo.chambi
	 * @descripción Método que realiza la validacion de compras
	 * @fecha 15/08/2019
	 */
	@Override
	public RespuestaXmlXsdDto validarRecepcionComprasIndXsd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {
			XmlRecepcionIndividualDto dtoAux = new XmlRecepcionIndividualDto();
			dtoAux.setCodigoSistema(xmlRecGenDto.getP_codigo_sistema());
			dtoAux.setCodigoAmbiente(xmlRecGenDto.getP_tipo_ambiente_id());
			dtoAux.setNit(xmlRecGenDto.getP_nit());
			dtoAux.setCuis(xmlRecGenDto.getP_cuis());
			dtoAux.setCufd(xmlRecGenDto.getP_cufd());
			dtoAux.setCodigoSucursal(xmlRecGenDto.getP_sucursal_id());
			dtoAux.setCodigoPuntoVenta(xmlRecGenDto.getP_punto_venta_id());
			dtoAux.setFechaEnvio(
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(xmlRecGenDto.getP_fecha_envio()));
			dtoAux.setArchivo(xmlRecGenDto.getV_archivo());
			dtoAux.setHashArchivo(xmlRecGenDto.getV_hash_archivo());

			boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
					Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL);
			if (!vValXmlXsd) {
				vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
				vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
						Parametros.ARCHIVO_XSD_RECEPCION_INDIVIDUAL));
			}

		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarRecepcionComprasIndXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);

		return vRespuesta;
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion para el registro de facturas invalidas
	 * @param pJsonEtapa               dto en formato json, obtenido a traves de los
	 *                                 parametros del servicio
	 * @param RecepcionErrorDetalleDto dto con los datos de la factura invalida
	 * @fecha 13/06/2019
	 */
	private void registrarCompraInvalida(JSONObject pJsonEtapa, List<Integer> pListaErrores) {
		try {

			SreFacRecepcionesErroresCompras pSolicitud = new SreFacRecepcionesErroresCompras();

			pSolicitud.setCaed(pJsonEtapa.getString("caed"));
			pSolicitud.setCuf(pJsonEtapa.getString("cuf"));
			pSolicitud.setCufd(pJsonEtapa.getString("cufd"));
			pSolicitud.setCuis(pJsonEtapa.getString("cuis"));
			pSolicitud.setFechaConsolidacion(new Date());
			// pJsonEtapa.getString("fechaEnvio")
			pSolicitud.setFechaEnvio(new Date());
			pSolicitud.setFechaRecepcion(new Date());
			pSolicitud.setNombreArchivo(pJsonEtapa.getString("caed"));
			pSolicitud.setNumeroFactura(Long.parseLong(pJsonEtapa.getString("numeroFactura")));
			pSolicitud.setRecepcionCompraId(Long.parseLong(pJsonEtapa.getString("V_recepcionId")));
			pSolicitud.setRecepcionErrorCompraId(Long.parseLong(pJsonEtapa.getString("codigoRecepcion")));

			long vRespuesta = iSreFacErrorCompraDomain.registrarErrorCompra(pSolicitud, pListaErrores);
			if (vRespuesta > 0) {
				for (Integer integer : pListaErrores) {
					SreFacRecepcionesErroresComprasDetalles pSolicitudDetalle = new SreFacRecepcionesErroresComprasDetalles();
					pSolicitudDetalle.setCodigoErrorId(integer);
					pSolicitudDetalle.setRecepcionErrorCompraId(vRespuesta);
					iSreFacErrorCompraDetalleDomain.registrarErrorCompraDetalle(pSolicitudDetalle);
				}
			}

		} catch (Exception e) {
			LOG.error("registrarCompraInvalida : ");
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarRecepcionComprasIndXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO,
					Long.parseLong(pJsonEtapa.getString("nit")), Long.parseLong(pJsonEtapa.getString("V_recepcionId")),
					Long.parseLong(pJsonEtapa.getString("V_recepcionPaqueteId")), 0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	@Override
	public void registrarLogExcepcionBDErrores(LogBDErroresDto pLogBDErroresDto) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(pLogBDErroresDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("jsonLogException", jsonRecepcion);

				// Envio de dto a funcion en Base de datos
				iJsonDomain.registrarLogExcepcionBDErrores(jsonFinal.toString());
			}
		} catch (Exception e) {
			LOG.error("registrarLogExcepcionBDErrores || Excepcion : " + e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(pLogBDErroresDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogExcepcionBDErrores", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pLogBDErroresDto.getNit(),
					pLogBDErroresDto.getRecepcionId(), pLogBDErroresDto.getRecepcionPaqueteId(),
					pLogBDErroresDto.getTipoDocumentoSectorId());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	public void noticarPushFactura(XmlRecepcionGenericoDto xmlRecGenDto) {

		try {
			// TODO: Reynaldo Chambi: servicio de push || Servicio de Transversales:
			String vResultado = iJsonDomain.obtenerFacturaPush(xmlRecGenDto.getP_tipo_documento_sector_id(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getP_cuf());

			if (vResultado != null && !vResultado.isEmpty()) {
				JSONObject objetoJson = new JSONObject(vResultado);
				// {"documento_identidad_id":339,"numero_documento":"8049369013","complemento":"","cuf":"6C59531E4CC80341ECC2698AF02352537AB66807",
				// "nit_emisor":618563010,"monto_total":9.00000,"numero_factura":320}
				int tipoDocumentoIdentidadId = objetoJson.getInt("documento_identidad_id");
				String numeroDocumento = objetoJson.getString("numero_documento");
				String codigoComplemento = "";

				if (!objetoJson.isNull("complemento"))
					codigoComplemento = objetoJson.getString("complemento");

				String titulo = Parametros.TITULO_NOTIFICACION_PUSH;
				int tipoNotificacionId = Parametros.TIPO_ID_NOTIFICACION_PUSH;

				// int tipoDocumentoIdentidadId = 337;
				// String numeroDocumento = "3337562";
				// String codigoComplemento = "";
				// String titulo = Parametros.TITULO_NOTIFICACION_PUSH;
				// int tipoNotificacionId = Parametros.TIPO_ID_NOTIFICACION_PUSH;

				// ------------- Mensaje ------------------
				// String nitCi = objetoJson.getString("numero_documento");
				String cuf = objetoJson.getString("cuf");
				// String docSector = xmlRecGenDto.getP_tipo_documento_sector_id().toString();
				// String nitEmisor = xmlRecGenDto.getP_nit().toString();
				String montoTotal = objetoJson.getDouble("monto_total") + "";
				String numeroFactura = objetoJson.getLong("numero_factura") + "";
				// ------------- Mensaje ------------------
				// String mensaje =
				// objetoJson.getString("numero_documento")+"|"+objetoJson.getString("cuf")+"|"+xmlRecGenDto.getP_tipo_documento_sector_id().toString()+"|"+xmlRecGenDto.getP_nit().toString()+"|"+objetoJson.getString("monto_total")+"|"+objetoJson.getString("numero_factura");

				String mensaje = numeroDocumento + "|" + cuf + "|"
						+ xmlRecGenDto.getP_tipo_documento_sector_id().toString() + "|"
						+ xmlRecGenDto.getP_nit().toString() + "|" + montoTotal + "|" + numeroFactura;

				// Inyectar servicio de transversales ();
				SolicitudPersonaIdDocumentoDto vSolicitudPush = new SolicitudPersonaIdDocumentoDto();
				vSolicitudPush.setCodigoComplementario(codigoComplemento);
				vSolicitudPush.setMensaje(mensaje);
				vSolicitudPush.setNumeroDocumento(numeroDocumento);
				vSolicitudPush.setTipoDocumentoIdentidadId(tipoDocumentoIdentidadId);
				vSolicitudPush.setTipoNotificacionId(tipoNotificacionId);
				vSolicitudPush.setTitulo(titulo);

				FirebaseResponseDto vRespuestaPush = iServicioPushClientRest.notificacionPush(vSolicitudPush);
				LOG.info("vRespuestaPush || " + vRespuestaPush);
			}

		} catch (Exception e) {
			LOG.error("registrarLogExcepcionBDErrores || ExcepcionNoticarPushFactura : " + e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "registrarLogExcepcionBDErroresNoticarPushFactura", new Date(),
					"CORE", e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto obtenerRecepcionAnulacionPorCuf(
			XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		JSONObject jsonRecepcion = new JSONObject();

		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));

			if (jsonRecepcion.length() > 0) {
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);
				LOG.info("validacionRecepcionBd || " + jsonFinal.toString());

				// Envio de dto a funcion en Base de datos
				long vRequest = iJsonDomain.obtenerRecepcionAnulacion(jsonFinal.toString());
				// String vRequest = "{'codigorecepcionindividual': 10, 'codigoestado': 20,
				// 'numerofactura': 30, 'cuf': 'cudddffff', 'listacodigoserror':'0'}";
				JSONObject objeto = new JSONObject(vRequest);
				List<String> vListaErrores = Arrays.asList(objeto.getString("listacodigoserror").split("\\s*,\\s*"));

				vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionindividual"));
				vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));
				if (!vListaErrores.get(0).equals("0"))
					vRespuesta.setListaCodigosRespuestas(
							vListaErrores.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
				else {
					vRespuesta.setTransaccion(true);
				}
			}
		} catch (Exception e) {
			LOG.error("validacionRecepcionBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validacionRecepcionBd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vRespuesta;
	}

	public SolicitudJsonDto generarJsonPaqueteMasivo(String xmlB64, XmlRecepcionGenericoDto xmlRecGenDto) {

		SolicitudJsonDto vRespuesta = new SolicitudJsonDto();
		JSONObject jsonFinal = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();

		try {
			byte[] bytesB = Base64.getDecoder().decode(xmlB64.trim());
			JSONObject jsonFactura = XML.toJSONObject(new String(bytesB, StandardCharsets.UTF_8));
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			jsonFinal.put("XmlFactura", jsonFactura);
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);
		} catch (JSONException | JsonProcessingException e) {
			LOG.error("procesarMasivo || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "procesarMasivoIndividual", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setSolicitud(jsonFinal.toString());
		return vRespuesta;
	}

	/**
	 * @autor junior.flores
	 * @descripción metodo que invoca funcion para la obtenci�n de los datos de la
	 *              recepci�n de la anulaci�n
	 * @param xmlRecGenDto dto en formato json, obtenido a traves de los parametros
	 *                     del servicio
	 * @fecha 25/10/2019
	 */
	@Override
	public RespuestaListaRegistroRecepcionesSoapDto recepcionAnulacionBd(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		try {
			ObjectMapper mapper = new ObjectMapper();

			JSONObject jsonFinal = new JSONObject();
			JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(xmlRecGenDto));
			jsonFinal.put("XmlFactura", "");
			jsonFinal.put("xmlRecepcionGenerica", jsonRecepcion);

			SolicitudJsonDto solicitud = new SolicitudJsonDto();
			solicitud.setSolicitud(jsonFinal.toString());

			LOG.info("anulacionBd || " + jsonFinal.toString());

			// DESPLIEGUE DE RESULTADO DE LISTA ERRORES
			String vRequest = iJsonDomain.recepcionAnulacionXml(jsonFinal.toString());
			JSONObject objeto = new JSONObject(vRequest);
			vRespuesta.setCodigoRecepcion(objeto.getLong("codigorecepcionanulacion"));
			vRespuesta.setCodigoEstado(objeto.getInt("codigoestado"));
			JSONArray vListaErrores = objeto.getJSONArray("listacodigoserror");
			if (vListaErrores.length() > 0) {
				for (int i = 0; i < vListaErrores.length(); i++) {
					vRespuesta.getListaCodigosRespuestas().add(vListaErrores.getInt(i));
				}
			} else
				vRespuesta.setTransaccion(true);

		} catch (Exception e) {
			LOG.error("anulacionEnvioBd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			vRespuesta.getListaCodigosRespuestas()
					.add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "anulacionBd", new Date(), "CORE", e.getLocalizedMessage(),
					e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(), xmlRecGenDto.getV_recepcionId(),
					xmlRecGenDto.getV_recepcionPaqueteId(), xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
		return vRespuesta;
	}

	/**
	 * @autor junior.flores
	 * @descripci�n metodo que realiza la validaci�n del xsd con el xml
	 * @param XmlRecepcionGenericoDto dto en formato json, obtenido a traves de los
	 *                                parametros del servicio
	 * @fecha 29/10/2019
	 */
	@Override
	public RespuestaXmlXsdDto validarRecepcionAnulacionXsd(XmlRecepcionGenericoDto pDto) {
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<String> vListaDescripcionesError = new ArrayList<>();
		try {

			if (vListaCodigoError.isEmpty())
				if (pDto.getP_tipo_documento_fiscal_id() == Parametros.F_TIPO_DOC_FISCAL_FACTURA_CONTIGENCIA || pDto
						.getP_tipo_documento_fiscal_id() == Parametros.F_TIPO_DOC_FISCAL_NOTA_FISCAL_CONTIGENCIA) {

					XmlAnulacionContingenciaDto dtoAux = new XmlAnulacionContingenciaDto();
					dtoAux.setCodigoSistema(pDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(pDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(pDto.getP_tipo_modalidad_id());
					dtoAux.setNit(pDto.getP_nit());
					dtoAux.setCuis(pDto.getP_cuis());
					dtoAux.setCufd(pDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(pDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(pDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(pDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(pDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(pDto.getP_numero_documento_fiscal());
					dtoAux.setCuf(pDto.getP_cuf());
					dtoAux.setCaed(pDto.getP_caed());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_ANULACION));
					}
				} else if (pDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA
						|| pDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
					XmlAnulacionPrevaloradaDto dtoAux = new XmlAnulacionPrevaloradaDto();
					dtoAux.setCodigoSistema(pDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(pDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(pDto.getP_tipo_modalidad_id());
					dtoAux.setNit(pDto.getP_nit());
					dtoAux.setCuis(pDto.getP_cuis());
					dtoAux.setCufd(pDto.getP_cufd());
					dtoAux.setCuape(pDto.getP_cuape());
					dtoAux.setCodigoDocumentoFiscal(pDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(pDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(pDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(pDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(pDto.getP_numero_documento_fiscal());
					dtoAux.setCufp(pDto.getP_cufp());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_ANULACION));
					}
				} else {
					XmlAnulacionDto dtoAux = new XmlAnulacionDto();
					dtoAux.setCodigoSistema(pDto.getP_codigo_sistema());
					dtoAux.setCodigoAmbiente(pDto.getP_tipo_ambiente_id());
					dtoAux.setCodigoModalidad(pDto.getP_tipo_modalidad_id());
					dtoAux.setNit(pDto.getP_nit());
					dtoAux.setCuis(pDto.getP_cuis());
					dtoAux.setCufd(pDto.getP_cufd());
					dtoAux.setCodigoDocumentoFiscal(pDto.getP_tipo_documento_fiscal_id());
					dtoAux.setCodigoDocumentoSector(pDto.getP_tipo_documento_sector_id());
					dtoAux.setCodigoSucursal(pDto.getP_sucursal_id());
					dtoAux.setCodigoPuntoVenta(pDto.getP_punto_venta_id());
					dtoAux.setNumeroDocumentoFiscal(pDto.getP_numero_documento_fiscal());
					dtoAux.setCuf(pDto.getP_cuf());

					boolean vValXmlXsd = iutil.validarXmlXsd(dtoAux.obtenerFormatoXml(),
							Parametros.ARCHIVO_XSD_RECEPCION_ANULACION);
					if (!vValXmlXsd) {
						vListaCodigoError
								.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
						vListaDescripcionesError.addAll(iutil.obtenerErroresXmlXsd(dtoAux.obtenerFormatoXml(),
								Parametros.ARCHIVO_XSD_RECEPCION_ANULACION));
					}
				}
		} catch (Exception e) {
			vListaCodigoError.add(CodigosMensajesServiciosSOAPServiceImpl.SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO);
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "validarAnulacionXsd", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pDto.getP_nit(),
					pDto.getV_recepcionId(), pDto.getV_recepcionPaqueteId(), pDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		vRespuesta.setListaDescripcionesRespuestas(vListaDescripcionesError);

		return vRespuesta;
	}
}