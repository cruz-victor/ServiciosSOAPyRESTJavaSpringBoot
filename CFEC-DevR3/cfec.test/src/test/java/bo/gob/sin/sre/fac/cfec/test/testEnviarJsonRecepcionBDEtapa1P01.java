package bo.gob.sin.sre.fac.cfec.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.Respuesta;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICoreFacturacionSoap;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback 
public class testEnviarJsonRecepcionBDEtapa1P01 {
	private static final Logger LOG = LoggerFactory.getLogger(testEnviarJsonRecepcionBDEtapa1P01.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	

	@Rollback(false)
	@Test
	public void enviarJsonRecepcionBDEtapa1P01() throws Exception{
		// va al catch 
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml = null;
			vRespuesta  = iValRec.enviarJsonRecepcionBDEtapa1(vArchivoXml);			
			LOG.info("TestEnviarJsonRecepcionBDEtapa1P01 ==>"+vRespuesta.toString()); 			
			// Caso Computarizado Ideal 
			// codigoRecepcion=12594
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("TestValidarArchivoPaqueteP01||Excepcion");
			vListaCodigoError.add(10);	// 10 = Archivo Invalido			
		}
	}
	
}
