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
public class testValidarFirmaElectronicaIndividualP03 {
	private static final Logger LOG = LoggerFactory.getLogger(testValidarFirmaElectronicaIndividualP03.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	@Rollback(false)
	@Test
	public void validarFirmaElectronicaIndividualP03() throws Exception{
		
		// caso ideal 
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			//archivo no se puede decodificar
			vArchivoXml.setV_archivoXml(null);
			
			vRespuesta=iValRec.validarFirmaElectronicaIndividual(vArchivoXml.getV_archivoXml());
				
			LOG.info("testValidarFirmaElectronicaIndicidualP03 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(11);	// 11 = FIRMA INCORRECTA
			
		}
	}
	
}
