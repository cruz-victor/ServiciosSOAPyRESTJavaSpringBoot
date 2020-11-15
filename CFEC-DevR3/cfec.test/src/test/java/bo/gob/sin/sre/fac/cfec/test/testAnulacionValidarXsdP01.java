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
public class testAnulacionValidarXsdP01 {
	private static final Logger LOG = LoggerFactory.getLogger(testAnulacionValidarXsdP01.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	

	@Rollback(false)
	@Test
	public void anulacionValidarXsdP01() throws Exception{
			
			//  va al catch 
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			LOG.info("validarDatos de la Recepcion ||Iniciando");
			try {						
				vRespuesta=iValRec.validarAnulacionXsd(vArchivoXml);
				LOG.info("testAnulacionValidarXsdP01 ==>"+vRespuesta.toString());  
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("testAnulacionValidarXsdP01||Excepcion");
				vListaCodigoError.add(88);	// 88 = SOAP_PARAMETROS_FORMATO_XSD_INCORRECTO
				vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			}
		}
		
			
}
