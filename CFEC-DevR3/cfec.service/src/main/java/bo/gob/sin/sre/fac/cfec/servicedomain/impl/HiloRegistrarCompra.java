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

public class HiloRegistrarCompra extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(HiloRegistrarCompra.class);

	@Autowired
	IValidarRecepcion iValRec;

	private XmlRecepcionGenericoDto xmlRecGenDto;

	@Override
	public void run() {
		LOG.info("HILO REGISTRO DE FACTURAS||Iniciando");
		LOG.info("FECHA HORA INICIO SERVICIO :" + new Date());
		try {
			iValRec.enviarJsonComprasRecepcionBDEtapa2(xmlRecGenDto);
			LOG.info("CodigoRecepion: " + xmlRecGenDto.getV_recepcionId());
			LOG.info("FECHA HORA FIN SERVICIO:" + new Date());
			Runtime garbage = Runtime.getRuntime();
			garbage.gc();
		} catch (Exception e1) {
			LOG.error(e1.getMessage());
			LogExcepcion.registrar(e1, LOG, MethodSign.build(xmlRecGenDto));
			LOG.error("HILO REGISTRO DE HiloRegistrarCompra ||EXCEPCION");

			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "HiloRegistrarCompra - run", new Date(), "CORE",
					e1.getLocalizedMessage(), e1.getMessage(), Parametros.ESTADO_ACTIVO, xmlRecGenDto.getP_nit(),
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

	/**
	 * @return the iValRec
	 */
	public IValidarRecepcion getiValRec() {
		return iValRec;
	}

	/**
	 * @param iValRec the iValRec to set
	 */
	public void setiValRec(IValidarRecepcion iValRec) {
		this.iValRec = iValRec;
	}

}