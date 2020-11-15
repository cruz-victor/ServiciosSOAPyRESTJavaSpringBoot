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
public class testAnulacionValidarXsdP02 {
	private static final Logger LOG = LoggerFactory.getLogger(testAnulacionValidarXsdP02.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	@Rollback(false)
	@Test
	public void validarAnulacionXsdP02() throws Exception{
			

			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			LOG.info("validarDatos de la Recepcion ||Iniciando");
			try {
				
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2);
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(2);
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e	");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(1);
				vArchivoXml.setP_sucursal_id(0);
				vArchivoXml.setP_punto_venta_id(null);				
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				 				
				vRespuesta=iValRec.validarAnulacionXsd(vArchivoXml);
					
				LOG.info("testAnulacionValidarXsdP02 ==>"+vRespuesta.toString());  
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("testAnulacionValidarXsdP02 ||Excepcion");
				vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
				vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
				
			}
		}
		
		
}
