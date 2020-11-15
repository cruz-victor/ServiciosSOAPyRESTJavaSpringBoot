package bo.gob.sin.sre.fac.service.impl;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import bo.gob.sin.sre.fac.domain.ISeguimientoCertificacionSistemasDomain;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

public class HiloActualizarMatch extends Thread{

	private static final Logger LOG = LoggerFactory.getLogger(HiloActualizarMatch.class);
	
	@Autowired
	private ISeguimientoCertificacionSistemasDomain iValRec;
	
	private Long sistemaId;
	private Integer etapaCertificacionSistemaId; 
	
	@Override
	public void run() {
		Short vResultado=0;
		LOG.info("FECHA HORA INICIO SERVICIO H:" + new Date());
		try {
			vResultado=iValRec.actualizarMatchBd(getSistemaId(), getEtapaCertificacionSistemaId());
			LOG.info("Resultado: " + vResultado);
			LOG.info("FECHA HORA FIN SERVICIO H:" + new Date());
			Runtime garbage = Runtime.getRuntime();
			garbage.gc();
		} catch (Exception e1) {
			LOG.error(e1.getMessage());
			LogExcepcion.registrar(e1, LOG, MethodSign.build(vResultado));
			LOG.error("HILO REGISTRO DE HiloRegistrarCompra ||EXCEPCION");
		}
	}

	public ISeguimientoCertificacionSistemasDomain getiValRec() {
		return iValRec;
	}

	public void setiValRec(ISeguimientoCertificacionSistemasDomain iValRec) {
		this.iValRec = iValRec;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Integer getEtapaCertificacionSistemaId() {
		return etapaCertificacionSistemaId;
	}

	public void setEtapaCertificacionSistemaId(Integer etapaCertificacionSistemaId) {
		this.etapaCertificacionSistemaId = etapaCertificacionSistemaId;
	}	
}
