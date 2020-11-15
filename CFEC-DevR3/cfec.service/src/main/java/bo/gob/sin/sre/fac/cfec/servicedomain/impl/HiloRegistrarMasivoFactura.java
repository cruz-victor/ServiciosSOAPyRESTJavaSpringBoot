package bo.gob.sin.sre.fac.cfec.servicedomain.impl;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.sin.sre.fac.cfec.domain.IJsonDomain;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacArchivoXmlValidoDomain;
import bo.gob.sin.sre.fac.cfec.dto.LogBDErroresDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlValido;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

public class HiloRegistrarMasivoFactura extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloRegistrarMasivoFactura.class);

	@Autowired
	private IValidarRecepcion iValRec;
	@Autowired
	IUtilitarios iutil;
	@Autowired
	IJsonDomain iJsonDomain;
	@Autowired
	ISreFacArchivoXmlValidoDomain iSreFacArchivoXmlValidoDomain;

	private XmlRecepcionGenericoDto xmlRecGenDto;

	public HiloRegistrarMasivoFactura(IValidarRecepcion iValRec, IUtilitarios iutil, IJsonDomain iJsonDomain,
			ISreFacArchivoXmlValidoDomain iSreFacArchivoXmlValidoDomain) {
		this.iValRec = iValRec;
		this.iutil = iutil;
		this.iJsonDomain = iJsonDomain;
		this.iSreFacArchivoXmlValidoDomain = iSreFacArchivoXmlValidoDomain;
	}

	@Override
	public void run() {

		// LOG.info("HILO REGISTRO MASIVO DE FACTURAS || Iniciando");
		// LOG.info("FECHA HORA INICIO SERVICIO :" + new Date());
		try {
			if (xmlRecGenDto.getV_recepcionPaqueteId() > 0) {

				byte[] compressed = Base64.getDecoder().decode(xmlRecGenDto.getV_archivo());
				List<String> listaFacturasB64 = this.iutil.decompressLoteFacturas(compressed);
				iJsonDomain.modificarRecepcionPaqueteCantidadArchivo(this.registrarArchivo(xmlRecGenDto.getV_archivo()),
						xmlRecGenDto.getV_recepcionPaqueteId(), listaFacturasB64.size(),
						xmlRecGenDto.getP_tipo_documento_sector_id());
				// limpiamos archivo
				xmlRecGenDto.setV_archivo(null);
				// Parallel processing
				listaFacturasB64.parallelStream().forEach(xmlB64 -> iValRec.procesarMasivoIndividual(xmlRecGenDto,
						Parametros.F_TIPO_EMISION_MASIVA, xmlB64));
				String vResultado = iJsonDomain.modificarEtapaProcesada(xmlRecGenDto.getP_tipo_documento_fiscal_id(),
						xmlRecGenDto.getP_tipo_documento_sector_id(), xmlRecGenDto.getV_recepcionPaqueteId(),
						Parametros.ESTADO_RECEPCION_PROCESADA);
			}

			// LOG.info("CodigoRecepion: " + xmlRecGenDto.getV_recepcionPaqueteId());
			// LOG.info("FECHA HORA FIN SERVICIO:" + new Date());
			Runtime garbage = Runtime.getRuntime();
			garbage.gc();

		} catch (Exception e) {
			LOG.error(e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LOG.info("Error al enviar archivo JSON Etapa 2 Masivo || HILO EXCEPCION");
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "HiloRegistrarMasivoFactura - run", new Date(), "CORE",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
					xmlRecGenDto.getV_recepcionId(), xmlRecGenDto.getV_recepcionPaqueteId(),
					xmlRecGenDto.getP_tipo_documento_sector_id());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}
	}

	/**
	 * @return the xmlRecGenDto
	 */
	public XmlRecepcionGenericoDto getXmlRecGenDto() {
		return xmlRecGenDto;
	}

	/**
	 * @param xmlRecGenDto the xmlRecGenDto to set
	 */
	public void setXmlRecGenDto(XmlRecepcionGenericoDto xmlRecGenDto) {
		this.xmlRecGenDto = xmlRecGenDto;
	}

	public long registrarArchivo(String pArchivoXml) {
		SreFacArchivoXmlValido vSolicitud = new SreFacArchivoXmlValido();
		vSolicitud.setArchivo(pArchivoXml.getBytes());
		return iSreFacArchivoXmlValidoDomain.registrarArchivoXmlValido(vSolicitud);
	}
}