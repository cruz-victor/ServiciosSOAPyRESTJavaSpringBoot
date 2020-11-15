package bo.gob.sin.sre.fac.servicedomain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.service.IDetalleCertificacionesSistemasService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback 
public class testValidacionRecepcionBdP01 {
	private static final Logger LOG = LoggerFactory.getLogger(testValidacionRecepcionBdP01.class);
	
	@Autowired
	IDetalleCertificacionesSistemasService iDetalleCertificacionesSistemasService;
		
	@Rollback(false)
	@Test
	public void TestAnulacionDetalleCertificacion() throws Exception{

		LOG.info("recepcionFactura ||Iniciando");
		DetallesCertificacionesSistemasDto vRespuesta=new DetallesCertificacionesSistemasDto();
		try {
			vRespuesta.setDetalleCertificacionSistemaId(15);
			vRespuesta = iDetalleCertificacionesSistemasService.anularDetalleCertificacionesSistemas(vRespuesta);
			LOG.info("registrarRecepcion CompEstandar ==>"+vRespuesta.toString());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
		}
	}
}
