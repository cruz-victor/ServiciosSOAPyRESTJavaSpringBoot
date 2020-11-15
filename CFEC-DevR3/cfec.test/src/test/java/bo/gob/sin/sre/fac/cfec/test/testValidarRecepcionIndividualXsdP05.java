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
public class testValidarRecepcionIndividualXsdP05 {
	private static final Logger LOG = LoggerFactory.getLogger(testValidarRecepcionIndividualXsdP05.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	@Rollback(false)
	@Test
	public void validarRecepcionIndividualXsdP05() throws Exception{
		
		// caso ideal cuando es prevalorada   ??? DATOSSSSS
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(5); // 4 y 5 prevalorada 
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-02T18:10:00.829"); 
			vArchivoXml.setV_archivo("H4sICJbT0FwEAGFyY2hpdm9QcmV2YWxvcmFkYS50eHQApVXLsppAEP0gN4CSkkUWcoERIqMizDCzcyAF8vCSeOX19enBJJqYV1UWlOUw3X26z+nDzjIWgnZZWpMh0apWFMrJPyw692RWosYtR9WVj8opjYN6E2an3bj4sEM8Z/NATWrHEprawbs8qat3jAZ5tPaqtHYuYu7lHNnZR6RexNl/93GNm93Be03XQbc9Ldt0ns4352Tc1MbAh+XgW6t+o+Y4dHBxROSNHdRGnLHCqF7wg5vJ+G2tt2HtvPEYd4ziKtLwK6dqHiKjYHHQCG1huPU/Y7tCztItFh/cl9WSac6JQ86ktmcvp1Umz0RdKQFVm0Qzhl1hd36xmvuWD7/+cqPpTWqTt2OM22TKATFWf02pCjkMBPiUNPbyXcG03YvxfJ69ZjJmh7DKz6sZC7Me8vZ+zeDXHbdWObCia9mc1In1PX+RUl7u6iDnYdlDDyMPVwtf2yt+bY/wX8Eh03BBTluEc255FdSeYu71Jk7PQsUq5AbOncuucKGf5/N7TNAkNSnYpA/dYrS/8IP5IaXO1XXMnNV969qV5774KrY6mWfgFHikxvW5rqmKc9CSmlxTZH/jtREWaGztqZLrzfi93xryvLJomrPkdwYc9NuD0mH5VHvQzasCD8wM6gLGI3IcQavxl7UVfDlSWTeaRbaaC8B/DGHGmlEeadrutb7hVFd4eJ+30IKGawbmcQA5jD2Dez/MS1GrFBkl9JVzbXnXzpmATr2WIGc41kRh4ULBRdTjYrXAY6b7lt3jMHrURsRi7xPUedCG2k6zQgbsQQe9R1D3h7N7PegXcNiwG0U69bj8y2zvvQlNr0A7M1xkOuD5+fx7jRRVHcwQ5uY1Ylz0cFc5xibMzXljddX+Gnf/Lc8d//O7ex+I6MBByULd53GZhZcPnWvtVX98zQiqwJPI6KKgSiTX0CM7mFJrDdQfXOlV4CfAa3FMtqM4maPQvAG0emGxn8GOD3L/eLyxPsdYOVKcJ4OpuwhLTJC7KsXczziKMoGM0V2DH5x5I3UP9/qURlkqfQZVBY/9K/R/4bHE9MDZmowpBc7pcpasTYnzHdxTkzmRPtQ+azKQO/4mdQe7V4F3ST/pH3iwhYZVMXFqhBzwi7k7zUveYdSrmEYGdtPskiMi53p50LD0wyaF3sADHdD6FXwRegI+wtXoW24HeX5z587Ltx0BD235mhQSy5HKfbQVf7Qf/MMcALcq+4i06mEHn9/vyma7Ce3RD114uva5xm2fvvY1srnXJOjmA4eKrMPBtIODud0TzwzLxnRtsnUdbkbKEvCARjQ8gD5/9qFcnGGuyCmlp99mqIMWwFPvPquK2ywwp/KuPSOlE4Zj16ZUn2JDSgDrI+83HQqVXI+3b470iSuGvhL47kgNEYhN0aSN/9lb0JcXiXmQC2sxTPnn5PTVD578J9II1Iv+lHOaFeC6CPDFl8lHnSJdg6Yi2IvYVCfMcZND33vQswb6auH7WoLm3n8BkzS2j0AIAAA=");
			vArchivoXml.setV_hash_archivo("9f02e6c320420a9ddc0612506c344c48d9be07d353f46873178b7e03a1bb740d");
			
			vRespuesta=iValRec.validarRecepcionIndXsd(vArchivoXml);
				
			LOG.info("validarDatos de la Recepcion ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);		// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
}
