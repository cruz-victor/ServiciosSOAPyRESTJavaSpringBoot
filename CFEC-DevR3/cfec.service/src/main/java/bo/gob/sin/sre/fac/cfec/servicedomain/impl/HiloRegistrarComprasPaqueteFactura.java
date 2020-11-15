package bo.gob.sin.sre.fac.cfec.servicedomain.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.sin.sre.fac.cfec.dto.LogBDErroresDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

public class HiloRegistrarComprasPaqueteFactura extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloRegistrarComprasPaqueteFactura.class);

	@Autowired
	private IValidarRecepcion iValRec;

	private XmlRecepcionGenericoDto xmlRecGenDto;

	public HiloRegistrarComprasPaqueteFactura(IValidarRecepcion iValRec) {
		this.iValRec = iValRec;
	}

	@Override
	public void run() {

		LOG.info("HILO REGISTRO PAQUETE DE FACTURAS || Iniciando");
		LOG.info("FECHA HORA INICIO SERVICIO :" + new Date());
		try {
			iValRec.enviarJsonComprasRecepcionPaqueteBDEtapa2(xmlRecGenDto);
			LOG.info("CodigoRecepion: " + xmlRecGenDto.getV_recepcionPaqueteId());
			LOG.info("FECHA HORA FIN SERVICIO:" + new Date());
			Runtime garbage = Runtime.getRuntime();
			garbage.gc();

		} catch (Exception e) {
			LOG.error(e.getMessage());
			LogExcepcion.registrar(e, LOG, MethodSign.build(xmlRecGenDto));
			LOG.info("HILO REGISTRO DE HiloRegistrarComprasPaqueteFactura || EXCEPCION");

			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "HiloRegistrarComprasPaqueteFactura - run", new Date(), "CORE",
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

}