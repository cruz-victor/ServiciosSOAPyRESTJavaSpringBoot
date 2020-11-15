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
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICoreFacturacionSoap;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback 
public class testValidarValidacionRecepcionPaqXsdP05 {
	private static final Logger LOG = LoggerFactory.getLogger(testValidarValidacionRecepcionPaqXsdP05.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	@Rollback(false)
	@Test
	public void validarValidacionRecepcionPaqXsdP05() throws Exception{
		
		//  debe estar en el proyecto de prevalorada  OK  necesita archivo validacionRecepcionPaquete de prevalorada
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("9B0B4B26");
			vArchivoXml.setP_tipo_ambiente_id(2); 
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(5);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("F03B001D");
			vArchivoXml.setP_cufd("767B6B5704E94C8F779B9C33A38CBF2D");
			vArchivoXml.setP_cuape("767B6B5704E94C8F779B9C33A38CBF2D");
			//solicitud.setCuape("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionPaqueteId(140L); // se recepciono sin error
			
			vRespuesta=iValRec.validarValidacionRecepcionPaqXsd(vArchivoXml);
				
			LOG.info("testValidarValidacionRecepcionIndXsdP05 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	}
