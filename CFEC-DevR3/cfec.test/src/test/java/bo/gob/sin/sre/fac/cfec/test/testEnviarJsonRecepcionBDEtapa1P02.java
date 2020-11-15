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
public class testEnviarJsonRecepcionBDEtapa1P02 {
	private static final Logger LOG = LoggerFactory.getLogger(testEnviarJsonRecepcionBDEtapa1P02.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	@Rollback(false)
	@Test
	public void enviarJsonRecepcionBDEtapa1P02() throws Exception{
		
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		
		LOG.info("recepcionFactura ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(3); //error
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-14T12:28:00.829");  // no actualizar fecha
			vArchivoXml.setV_archivo("H4sICK7r2lwEAHBhcXVldGVFc3RhZGFyLnRhcgDtWMFum0AQzaf0XrVawLjlaAeDoQEbDIt3b16oMLC4VoMB8/WdxU5DmlRtpbbKgZEiEmB3Z968eTzl8321OyS7r2/evZHeV2118w8CSQh9+PDhBl3ix+tkOkU3kgIXdaqqqiTen07kmzfo5j/ECQD4eoP+y1mvMNa6NmFRkyYlPscyr1mOMmczaaxszlnp1tTkJ9qhLNn65V2QZutu4t7mbUkiFyVbe+/JWhUvMSJb+/i5NAoS4i4xjRM1jbO13FfMVLvVYd/tApTtlj6K9S/1nZIoyVlVnLNax2VcO/mscTYaCcI2IPKes2hR7SK134fIYTbch+p/tA+sFWcfTwypexbhLjaNnGI335m4ImFbE9lAu0g7rTf2b9V0J3K4td7q3pfU0tucRDYnMj6TAHDJZql1O/vISo78SDrGsnZe54sG8lIc3YGr8/FOVo/JAle7rVvH+WDNAVd0a9d+aeTJst+vcfRZ6+YzsUaC3M8M0T1RfCkuF+J8sS5dm65E80lLI5xTPcncYM5pRCQa4IyaoeIGOCfBDN6JOzdoaqLgcp2iT7C2zz+JaAE5ym5QKD78OHox8Qoy8QvXXOn+fBWGjVu4hp/Pbz3knde3Wr9muAeT/SOVtSCJXCk+uHumT1rx3vP7j/UCr840cvMe+8KdB4vWsBbtOjzPPT9U1x7SAtij2G0Fvu6RyZNBzT0vD0yaS+zg17jEp8RcpJ+X7nFVqkemA1+XtkQ3VnrXDc4sMfTdMFjEu/7c3GqcoKgcPaycwA+dwJo6eToF3E+AhwTnl5DjFxKKfv2Yg1qzyD5TbO8/l9oplDWoxbgXtYSSbwRQS5DNIz9s7Sibbzaha3nYXlh6U7NSq8jB5mFpTJmsBgzqI1HzQn3+MTa1BTyH/osaNZua4soLEnlvHdFPGTCKkhqbvGHIF/2taKQihnghrrvI31P9BZ6Zoo8SF/itcwet9FhZ6d53rj3Za4AhEbNhtvySz8dfYv6Qnye3R7EXDSbygBs6M7nIgb/AJ5dufcBB8wisB45CbldckMQBiwL6v6fyxwFu19wlfx+XR/47nGCRJnANmeL3vHU7gYFU97wyNfS0L5f7vok7wOaKnSXqqZgsMPd5DL26YPYcgwDOotCPdU4mK+DBs/uPGKDddg6YGRUp+aV2peeCTiIp20XDmoe5ti7kwam5AG7E6Htekl+Dft2/cM493QL+JdwrWv55M181X2epq4fdKps7THHSGLQtKbmY084yfU5g1qyl4CM/wUyguDTguxGmNJqkUA9opHgXrlsnBf1JQccFj46gncXd7TyHHPvfrWUh9j6A3l6e5TAXJlYBu2KopcnWlYQOs07dBAsEOIAWR8b5EYPHeXnCWcnloJWgsUMtejJLAcw2YspVy/t+GBnc49/1VW8LugVemO2Qn0KDj0kpZtA3RM1Mlo5EBsz1BXK7WQPn/eydlzTsDLlJItdQ5kKT2lVAHrj+/PnzOfHiUisSUUunemGGxHejcQf8Cpd2TZc4T8whZj1Xz8CzqwYbjo8+ppGxJ5A/1O3mcckb0nuCIZeNp/rz+Pfj3kt86nVnIXEqngUqDgoPevfj/cd946XNxVk4gu+jeemvk/dYNnGJRY5YfFd7Ljyf34XIN/lNTYoVnF1n++EM4JR91YDnWhnKGPIJf7nvw/sb0NXdZj6J5WIKOd+Dt0DxAXMrm9XX/kF/fS5mkpmhwA3+fvi2GzpwpUmAd4D/lABePswZEXOxtd7e/OUY+H/5Nfj/Kbr4f+XD6P//R4z+/7X7/8Xo/0f/P/r/0f+P/n/0/6P/H/3/X42B/1dehf9X1Mv//0f//19i9P+v3f9bo/8f/f/o/0f/P/r/0f+P/n/0/2OMMcYYfyW+Afnzi2IAKAAA");
			vArchivoXml.setV_hash_archivo("57DF9BB3521E3E87061E77E95127EF656011FF071E85A843D5546A04427A9C77");
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
