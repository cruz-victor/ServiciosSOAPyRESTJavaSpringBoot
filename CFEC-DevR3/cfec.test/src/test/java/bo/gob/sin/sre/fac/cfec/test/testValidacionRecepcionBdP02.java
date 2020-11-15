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
public class testValidacionRecepcionBdP02 {
	private static final Logger LOG = LoggerFactory.getLogger(testValidacionRecepcionBdP02.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	@Rollback(false)
	@Test
	public void TestRecepcionIndivualP01() throws Exception{
		// computarizada OK
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("E79A306");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("10DC8801");
			vArchivoXml.setP_cufd("8F35E3A6970F81B67E41535CC2C492CE");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-16T22:03:27.829");
			vArchivoXml.setV_archivo("H4sICMMV3lwEAEZDRXN0YW5kYXIxLnR4dACVVtu2mjAQ/aC+cF2rPPRB5SLWREEgkDcCXWAIlrYKwtd3Qj3VU09vDy40JjN79uzZYW9bBiNDVbbJWGiiZ1w5ooMx+MelYC3uqScudFKOZRq226g67icDr/i1zQhWynRTB5p1LtaJkqWb7lPrNlmcTKXnXqjnjv66PjPPnHanesoj5ZivQ6WwP/dbvdTL0dTRaPZFW/SILwZ0sLIovkaZVgtGnHNOzDlOpsXHxzjU/q84cFbm7i5MMWtGkqnwXE4TzHMvOWfxtc80V8mJddkfNv9U01ZiWPnv7OBz5dtXnpGNyLRkzCLg5bio/NXiPWuFEhK1KzRr3HNnAFw6shE80futZnalk5zzFPcFfzhzSs403fRh6/JyPcdT4YzcrwLukSm0zvRQLVpH5pZnqr2HVcoNHUebVTAFV+z4GnKQsbOXzi5GBopqB0elE/DY3NnICIEYzH2IKc/dc2d60lLbMEKO1DDCS8wbHTmZgWJfw0qsoCiecIxt1CBlF23sMBr625l7DM1qclL2sZ5w4HDKyPAO2bDvef0Bf9gVbcKzWXemHcRXFB6WKFJRFTvJLlLwPp4GqcGREsznPlXKRzg7c8+0sKOaFZTEVJhKBTuF9Uu/czJY/ikcSwL6Wb1/yEmhX3U992fOa4zIdsxttFC3kaMltj/u+AI+vr7llYE55AeMuee6jIjpFwwXpqlHqOGQpV3PBO6hlprZUEuC4xDqiRSjSpoEHZLPVdwI++C4h3C1kH3tGdmMNNnUn1rrEmsW1Od+e6O+OE+XfehZvCSqrLE/kFA+O9BjA/qCWPOcnpgKfHqWwzQsNXMpPWtDPfkUTUaCBw5eNBXKPp6p5G8yJhwFOp4q0E+s7FfWReYr2sd4jzzinqXLb/SG6e+83zAq+Fs+x4rf7aK7PgLt2kkcNHrWVEQSBbjoY889Ac4rYHvhBtM0hLhWkMG+B+5esMcww19o+i+6UHtZR+JZyqzdKDMhz5lpUlthX3qvevNjXQlFAdzc+FPl/uf1t+qxLpSE9Z5nwPXQP63f8yiy94HmnrNW9HOv9bnHdkbUY05+h/+KAYegnvNmHW/k+UZTAX4Pa81VfDosd8PXRYXteNodl4jpqCrA48pWyBmcfC8UWZtc/LXUhbiAlylF68L9EVeUGBXUA14p98IzRRX14gr8XGpt1ux2teSAcf7urxsZ+wTe8OM/mDfmJSZw1zx6apliVfoxm8xD5CjAA3gyccecPGvsla5VLMA3wWsfdfNqRiIKvs/0m6fP/XCPsCZ+eq19baSGmHcVD5xJP+7KVs5W6ELN0gu6THPegccOKFoMkO93e54xq8sRsKkSa6yJy55XE4qC6QFzAHpuSoJlTR3jb3jvetPTdcLlnMauf0Y23Id2Bv7wnONWx602zItWDDcfPlDoaaHLet25h7JH890I/pSnXT3vO0oPm3U+gkafvDkD7nPQGLV/8H7//dBTYs49h/lu5H97kexC4P9p/R53AK+VuRJ5x/7Qg/RvwKIv5R3RMTW55NArqKf/y7yOWGqNWFIDjuSg/NXj1qA5cZt97ugYsBV6crx5xJOfSq1BXvEHr9ncvVeAPpvq0xp3u9bsmA3vWOuNSg9+tb15BvD2Snfw+5/eUfbVhw/fAYgOL93YCQAA");
			vArchivoXml.setV_hash_archivo("02f0836fccc44af6b75f7b8e3a1a56548427d4dc92517628d6bfbb4ea962b89d");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.Recepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("registrarRecepcion CompEstandar ==>"+vRespuesta.toString());
			// Caso Computarizado Ideal 
			// codigoRecepcion=12594
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	@Rollback(false)
	@Test
	public void TestRecepcionIndivualP02() throws Exception{
        // Nota archivos recepcionIndividual y documentoFiscal es para electronica
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("177E1E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(1); //electronica
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("9887F10F");
			vArchivoXml.setP_cufd("bf7af8e371357f7faf2cd3858bb62a14");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-04T11:20:00.829");
			vArchivoXml.setV_archivo("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");
			vArchivoXml.setV_hash_archivo("cc48d6c0215b170153721efcd5ba0675d36753581a056e4fbe27af966bd06845");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.Recepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionIndivualP02 ==>"+vRespuesta.toString());
			// Caso Electronico Ideal 
			// codigoRecepcion=
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	@Rollback(false)
	@Test
	public void TestRecepcionIndivualP03() throws Exception{
        // computarizada error en hashArchivo y Archivo no se puede decodificar
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-02T18:10:00.829");
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700Py"); //con error para decodificar
			vArchivoXml.setV_hash_archivo("9820094b249d19c9901e6da056418fdbf745e56020b8LILILILILILIXXXXXCCC"); // error
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.Recepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionIndivualP03 ==>"+vRespuesta.toString());
			//error = 1000
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	@Rollback(false)
	@Test
	public void TestRecepcionIndivualP04() throws Exception{
        // electronica - error en el firma
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("177E1E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(1); //electronica
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("9887F10F");
			vArchivoXml.setP_cufd("bf7af8e371357f7faf2cd3858bb62a14");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-04T11:20:00.829");
			vArchivoXml.setV_archivo("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");
			vArchivoXml.setV_hash_archivo("9f9a62ab5b700b7c5829b1459ccfbe5ea342d101e6944aeb227d4c1ab5334a09");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.Recepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionIndivualP04 ==>"+vRespuesta.toString());
			//error = 88
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}

	@Rollback(false)
	@Test
	public void TestRecepcionIndivualP05() throws Exception{
        //computarizada con error en XSD
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-02T18:10:00.829");
			vArchivoXml.setV_archivo("H4sICKh2y1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXJFcnJvclhTRC50eHQAlVZdl5owEP1BfQGUPcujLh9ClygYEpM3Az0IBMvpKl+/vhPWrWx3e9o+eNCQmdy5c+fGnW0tBe3yrCZDashWlFoR7pedX6ylqFHLPXnlo1Zkh7h+xnmxG5foqexrRpGWHYJTZFiXdEM0dgiab7VbsYSMmedeuecO/uZ0EZ45bs+n8Yi14riJtdT+3j4vskU2mItwMNu0TtuwXHXh3mI46TEzTlJQ53Kk5pSHGUkxz8Pt/8oDsers5io08yQoGVPPLTlB5dEjF5b0LTNc7Uit624f/FNNzwrDk//Fjr7nvt2XjAaSGWRgGHgpVrn/tHoUtdRiqjepYQ270ukA1yK0Q3iGj8+G2WQOuRwPqE3LWcyZXPghaOPaLbPNlK8L7VWP8ErF6IB9EBo/sUWsp7Wjzldx+c5DOi+XPaek5HZWILyWnDKdY1JwL1kgTEqGV7AnHRHuWrYg9S7XvkLshD+jvAKMBsLVIoZPaFfLqGLLuELe1o7X2yTpUIXcuFw/RVo07J6sKWaeQxhxww0LZxTp6RmdhL3s1L6P6/d6QVcDp6icuK/QGju96zv9LhnWUZyYu0izMOSojgfFL2qEsZzVPOnyLPS1Ls5xS2pyzTznS2hDfQbE0KxNNmrNopya2rw3vCbQe9cVVI7T2aXfhbi6hHYCnyAJcf4Q4tUDcH8FTnTAUAPO7yxRPfsdh9kKGgycBKdvtXVNDAvqcV9UPYkeuxjqwcWaxkkf0GK93yfIj0jg+IBT1NaFnQOZ1O6DMEwsoEZGu09qjJvUsxx4DxqYagq4p56yYjT6EuJ7zcSTndBi1eOLqltoslLPI41P3P5Ea57qpS4Vh7sy1LZ2utja0S+9vcs13uOZmg+vl694HvNvG9Rsa7MRMJvZJtD53s+fZ/vf8EVG36hcHC+NmT5s4UmFQX6iKcQPMfBgRQziQaeA7caLpkvgogINnLjxOOPthl2PT2ndwB7nr/gEtRSviVjESqM9GhUHejtpy7O09315XY89MgI3N+58Vc9FGIrzWKbQq1fOPnKA4SwO/diVbLm96/W+fudAOx7WwJl7YbV8rX0xacFmVC+OdF7zHGuPAIfkah5wqv3CpccteNjLJ+e88APwX8Na1ctv+/W2+7HKkZ2M22IdikWYp+BvWS3VrI6+F0sG8+ZvlB7lFWZCS2sX7o4k53SZQz3gk2ovPA9hDh6Ug5crHTXgn9Xz07oEjNN3f1Op3Gfw3Nd3JcyFR0zgrprPbHZAuvJiMZp77GjAA/gxdYc7B/d5eadZHUnwS+Wz/V1v72YJw2xrYnHz86kfbgFr8pfH2n3FD6ALr5/rU/lwk9VqBmNX1SwMvWGG8iBHQ+NK+d+f9nzmYwNg0xXWxJDKk/otZm9a//j+45xEaW1VmaplNKOk0NTd0aF3fhi0fEPKzJtzNml1AJ3dfNgNY+0xp+6JAX6oG5VpLTs2/S+Ya9l97z/33/fc4L+T7zi65OodNgmuIujd7+v3vOkmkOosQuGO9F77G5YTl11aE4WRqLt10sLH+XUU3uwfPSldkOI2229ngKaCmwd89MrEIIAn+Wvet/178NXjfr1MjeoBML/A/wstPRPpF6v21j/obyzVTAovUbzB77f73bVBK10GugP+HxjwFcOcMTUXB//LT7+yniu0CQAA");
			vArchivoXml.setV_hash_archivo("77d156c258df4d268730e91bf015219e976e3eb25a4fa71da70c0432e3c4cc32");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.Recepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionIndivualP05 ==>"+vRespuesta.toString());
			//error 89 = error en XSD
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarHashArchivoP01() throws Exception{
		
		// caso ideal sin error de hash
		List<Integer> vRespuesta = new ArrayList<>();
		String pArchivo;
		String pHashArchivo;
		LOG.info("validarHash ||Iniciando");
		try {
			
			pArchivo ="H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==";
			pHashArchivo="9820094b249d19c9901e6da056418fdbf745e56020b8064e4f12d4d417ce8cb6";
			vRespuesta=iValRec.validarHashFactura(pArchivo, pHashArchivo);
				
			LOG.info("validarHashArchivo ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(911); // 911 = HASH_INVALIDO
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarHashArchivoP02() throws Exception{
		
		// caso ideal sin error de hash
		List<Integer> vRespuesta = new ArrayList<>();
		String pArchivo;
		String pHashArchivo;
		LOG.info("validarHash ||Iniciando");
		try {
			
			pArchivo ="H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==";
			pHashArchivo="9820094b249d19c9901e6da056418fdbf745e56020b8XXXXXXXXXXX";  // error
			vRespuesta=iValRec.validarHashFactura(pArchivo, pHashArchivo);
				
			LOG.info("validarHashArchivo ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(911);
			// 911 = HASH_INVALIDO
			
		}
	}
	@Rollback(false)
	@Test
	public void testValidarRecepcionIndividualXsdP01() throws Exception{
		
		// caso q va al catch
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vRespuesta=iValRec.validarRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarRecepcionIndividualXsdP02() throws Exception{
		
		// caso ideal cuando no es prevalorada		
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-02T18:10:00.829");
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==");
			vArchivoXml.setV_hash_archivo("9820094b249d19c9901e6da056418fdbf745e56020b8064e4f12d4d417ce8cb6");
			
			vRespuesta=iValRec.validarRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);		// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarRecepcionIndividualXsdP03() throws Exception{
		
		// caso cuando no es prevalorada y tiene error de XSD
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(3); // error 
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-02T18:10:00.829");
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==");
			vArchivoXml.setV_hash_archivo("9820094b249d19c9901e6da056418fdbf745e56020b8064e4f12d4d417ce8cb6");
			
			vRespuesta=iValRec.validarRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP03 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);		// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarRecepcionIndividualXsdP04() throws Exception{
		
		// caso cuando es prevalorada y tiene error de XSD
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(5); // error 
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
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==");
			vArchivoXml.setV_hash_archivo("9820094b249d19c9901e6da056418fdbf745e56020b8064e4f12d4d417ce8cb6");
			
			vRespuesta=iValRec.validarRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP04 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);		// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarRecepcionIndividualXsdP05() throws Exception{
		
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
	
	@Rollback(false)
	@Test
	public void testValidarArchivoIndividualP01() throws Exception{
		
		// caso que va al catch  ???????
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setV_archivo("asaasasasa"); //???? no va al cath ni vacío , ni null
			
			vRespuesta=iValRec.validarArchivoIndividual(vArchivoXml.getV_archivo());
				
			LOG.info("testValidarArchivoIndividualP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarArchivoIndividualP02() throws Exception{
		
		// caso que va ABCG
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setV_archivo("");
			
			vRespuesta=iValRec.validarArchivoIndividual(vArchivoXml.getV_archivo());
				
			LOG.info("testValidarArchivoIndividualP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarArchivoIndividualP03() throws Exception{
		
		// caso no pueda decofificar 
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9");
			
			vRespuesta=iValRec.validarArchivoIndividual(vArchivoXml.getV_archivo());
				
			LOG.info("testValidarArchivoIndividualP03 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarArchivoIndividualP04() throws Exception{
		
		// caso ideal 
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==");
			
			vRespuesta=iValRec.validarArchivoIndividual(vArchivoXml.getV_archivo());
				
			LOG.info("testValidarArchivoIndividualP04 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarDocumentoFiscalXsdP01() throws Exception{
		
		// caso ideal 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			//archivo decodificado
			vArchivoXml.setV_archivo("H4sICBhqy1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIudHh0AJVWXZeaMBD9QX3hQ3qWR1k+hC5REBKTNwM9CATLaZWvX98J61a2bk/bBw8akpk7d+7cuLPNFSd9kTd4zDTR8Uopw/2q90tL8AZ1zBNXNillfoibl6Qod9MKPVdDQwlS8kNwijTzkm2wQg9B+7Vxa5riKffcK/Pc0d+cLtwzpu35NB0TpTxuYiWzv3Uveq7no6GHo9FlTdaF1boP9yZN0iGh2klw4lyOxJjjUC0tl3GY/V9x4KzM3V65Ypw4wVPmuRXDqDp6+ELToaOaqxyJed3tg3+q6UViePY/2dG3wreHipJAUA2PNAFeynXhP6+feCOUmKhtppnjrnJ6wKWHdgjP8OlFM9rcwZfjAXVZtThzxhd2CLq4cat8M8frQ3s9oGQtz6iAfeQKO1E9VrPGkfnluWLnIZVVq4ERXDE7L1FiCUaoyhJcMi/VUYIrmqxhTzahpO+ojptdoXyBszP+nLAaMGooqfUYPqFdr6KaruIaeVs7trZp2qMauXFlPUdKNO6ezfnMMgbX4pZpZpITpGZndOL2qpf7Htfv9YKuRkZQNXNfIytxBtd3hl06WlGcGrtIMROIUR8Pkl/Ucm21qHnW5ZmrlsrPcYcbfM09p/i6Qe22MVpug143gcr2fvEyLXI2GPruupyIac5b+X2Y1JfQTuETpGFSfA6T9Wfg/Qp8qJC/AYzfaCr79TsGo+MkGBkOTl8b85pqJtTi/pC1pGrsJlBLUlokToeAlNZ+nyI/woHj233HG/NCz4FIG/cz14yEQ32U9B/UF7eZZzrwHvovazQD5smnqCmJPoWynxpwRPIOe6LnSiz7e2HEULgiavk8kvjE7A905sk+qkLyt6tCZWtn+taOfmntXawFh1TOhjeIVzxPf+X8DV+kDa2MxZKVttCGzT0hMYgP9ITYIQYezIjCedAoYLvxoqgCuKih/yemPS14u2FX41PWtOJfNMGJKXlNuR5LfQ5okhyo3awrz1Te9+V1PfbwBNzcuPNlPReuSc5jkUGvXjl75CCBXAz6savoags6eFi/c6AcDxZw5l5oI15r12ct2JSo5ZEsa15iHRDgEMxzQBuZ8guXGnfgXz8+yPODHYD/BtbqQXzdW9v++7pAdjptSyvkelhk4G15I+ScTr4XCwqz5m+kHsUVZkLJGhfujbRgZFVAPeCRci88D2EB/lOAj0sdteCd9cuzVQHG+bu/qWXsM/jt67sK5sLDBnBXL700PyBV+jCfjH3iKMADeDFxxzsH93l5p1kVCfBK6bHDXW/vZimB2Va4fvPyuR9uCWvil7/aQ80OoAtvWOpTenCbN3IGY1fWzDW1pRpwbjsKmtbS+/605yMPGwGbKrGmmpCeNGwT+qb1x/ePcxJljVnnspbJiNJSkfdGjxb6SjdBxza4yr0lZ7NWR9DZzYPdMFaeCuKeKOCHulGVNaKn83+CpZbd9/5z/32PvcHX2XccVTD5LjFwUkfQu9/X73GzTSBkLkzgfvRe+xtWM5d91mCJEct7ddbC4/w6Em/+j56U6bi8zfZbDtBUcPOAR69MNQx40r/Gfdu/B1897q1VptWfAfMP+G+hZGcs/HLd3foH/Y2FnEnupZI3+P12t7s2aKXPQXfA/2cKfMUwZ1TOxcH/9BMEXM0csAkAAA==");
			vArchivoXml.setV_archivoXml(iutil.decodificarArchivo(vArchivoXml.getV_archivo()));
			vRespuesta=iValRec.validarDocumentoFiscalXsd(vArchivoXml.getV_archivoXml(), vArchivoXml.getP_tipo_emision_id(), vArchivoXml.getP_tipo_documento_sector_id());
				
			LOG.info("testValidarDocumentoFiscalXsdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(11);	// 11 = FIRMA INCORRECTA
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarDocumentoFiscalXsdP02() throws Exception{
		
		// caso ideal 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			//archivo no se puede decodificar
			vArchivoXml.setV_archivo(null);
			
			vRespuesta=iValRec.validarDocumentoFiscalXsd(vArchivoXml.getV_archivoXml(), vArchivoXml.getP_tipo_emision_id(), vArchivoXml.getP_tipo_documento_sector_id());
				
			LOG.info("testValidarDocumentoFiscalXsdP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(11);	// 11 = FIRMA INCORRECTA
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarFirmaElectronicaIndicidualP03() throws Exception{
		
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
	
	@Rollback(false)
	@Test
	public void testValidarFirmaElectronicaIndicidualP02() throws Exception{
		
		// caso ideal 
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
				
			// factura electronica computarizada
			vArchivoXml.setV_archivo("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"); 
			vArchivoXml.setV_archivoXml(iutil.decodificarArchivo(vArchivoXml.getV_archivo()));
			vRespuesta=iValRec.validarFirmaElectronicaIndividual(vArchivoXml.getV_archivoXml());
				
			LOG.info("testValidarFirmaElectronicaIndicidualP02==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarFirmaElectronicaIndicidualP01() throws Exception{
		
		// caso Q VAYA AL CATCH ???? 
		List<Integer> vRespuesta = new ArrayList<>();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			//archivo no se puede decodificar			
			// factura electronica computarizada con error utf8-bom
			vArchivoXml.setV_archivo("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"); 
			vArchivoXml.setV_archivoXml(iutil.decodificarArchivo(vArchivoXml.getV_archivo()));
			//vArchivoXml.setV_archivoXml("H4sICCYEz1wEAGZhY3R1cmFFbGVjdHJvbmlXXXXXXX");
			vRespuesta=iValRec.validarFirmaElectronicaIndividual(vArchivoXml.getV_archivoXml());
				
			LOG.info("testValidarFirmaElectronicaIndicidualP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			
		}
	}
	
	//------------------------------------------------------------------
	// Test Validacion de recepcion
	//------------------------------------------------------------------
	@Rollback(false)
	@Test
	public void TestValidacionRecepcionIndivualP01() throws Exception{
		// validacion recepcion estandar computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12594L);
			
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.ValidacionRecepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestValidacionRecepcionIndivualP01 ==>"+vRespuesta.toString());

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	
	@Rollback(false)
	@Test
	public void TestValidacionRecepcionIndivualP02() throws Exception{
		// validacion recepcion estandar computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(3); // error 
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12582L);  // se recepciono con error
			
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.ValidacionRecepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestValidacionRecepcionIndivualP01 ==>"+vRespuesta.toString());

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	//------------------------------------------------------------------
	// Test Validacion de recepcion
	//------------------------------------------------------------------
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionIndXsdP01() throws Exception{
		
		//  va al catch 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {						
			
			vRespuesta=iValRec.validarValidacionRecepcionIndXsd(vArchivoXml);
			LOG.info("testValidarValidacionRecepcionIndXsdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		}
	}
	
		
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionIndXsdP02() throws Exception{
		
		// 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12582L);  // se recepciono con error
			
			vRespuesta=iValRec.validarValidacionRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionIndXsdP03() throws Exception{
		
		// 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(3); //error 
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12582L);  // se recepciono con error
			
			vRespuesta=iValRec.validarValidacionRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionIndXsdP04() throws Exception{
		
		// 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("9B0B4B26");
			vArchivoXml.setP_tipo_ambiente_id(3); // error en xsd
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(5);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("F03B001D");
			vArchivoXml.setP_cufd("767B6B5704E94C8F779B9C33A38CBF2D");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12657L);  // se recepciono sin error
			
			vRespuesta=iValRec.validarValidacionRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarRecepcionIndividualXsdP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionIndXsdP05() throws Exception{
		
		//  debe estar en el proyecto de prevalorada  OK  necesita archivo validacionRecepcionIndividual de prevalorada
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("9B0B4B26");
			vArchivoXml.setP_tipo_ambiente_id(2); 
			vArchivoXml.setP_tipo_emision_id(1);
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
			vArchivoXml.setV_recepcionId(12482L);  // se recepciono sin error
			
			vRespuesta=iValRec.validarValidacionRecepcionIndXsd(vArchivoXml);
				
			LOG.info("testValidarValidacionRecepcionIndXsdP05 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidacionRecepcionBdP01() throws Exception{
		
		// va al catch
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2); 
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12677L); 
						
			vRespuesta=iValRec.validacionRecepcionBd(vArchivoXml);
			// vRespuesta=iValRec.validacionRecepcionBd(null);	
			LOG.info("testValidacionRecepcionBdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("testValidacionRecepcionBdP01||Excepcion");						
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidacionRecepcionBdP02() throws Exception{
		
		// ???? CONSULTAR
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
//			vArchivoXml.setP_codigo_sistema("9BB347E");
//			vArchivoXml.setP_tipo_ambiente_id(2); 
//			vArchivoXml.setP_tipo_emision_id(1);
//			vArchivoXml.setP_tipo_modalidad_id(5);
//			vArchivoXml.setP_nit(1020703023L);
//			vArchivoXml.setP_cuis("85EA4EE9");
//			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
//			vArchivoXml.setP_tipo_documento_fiscal_id(1);
//			vArchivoXml.setP_tipo_documento_sector_id(1);
//			vArchivoXml.setP_sucursal_id(0);
//			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12677L); 
						
			 vRespuesta=iValRec.validacionRecepcionBd(vArchivoXml);
			LOG.info("testValidacionRecepcionBdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("testValidacionRecepcionBdP01||Excepcion");						
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidacionRecepcionBdP03() throws Exception{
		
		
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("9B0B4B26");
			vArchivoXml.setP_tipo_ambiente_id(2); // error en xsd
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("F03B001D");
			vArchivoXml.setP_cufd("767B6B5704E94C8F779B9C33A38CBF2D");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionId(12582L);  // se recepciono con error
						
			vRespuesta=iValRec.validacionRecepcionBd(vArchivoXml);
				
			LOG.info("testValidacionRecepcionBdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("testValidacionRecepcionBdP01||Excepcion");						
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidacionRecepcionBdP04() throws Exception{
		
		
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			

			vRespuesta=iValRec.validacionRecepcionBd(vArchivoXml);
				
			LOG.info("testValidacionRecepcionBdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("testValidacionRecepcionBdP01||Excepcion");						
		}
	}
	
	
	
  //------------------------------------------------------------------
  // Test recepcion Paquete
  //------------------------------------------------------------------

	@Rollback(false)
	@Test
	public void TestValidarArchivoPaqueteP01() throws Exception{
		// va al catch 
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<Integer> vRespuesta = new ArrayList<>();
		
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
		    vArchivoXml.setV_archivo("A");
			vRespuesta  = iValRec.validarArchivoPaquete(vArchivoXml.getV_archivo());			
			LOG.info("TestValidarArchivoPaqueteP01 ==>"+vRespuesta.toString()); 			
			// Caso Computarizado Ideal 
			// codigoRecepcion=12594
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("TestValidarArchivoPaqueteP01||Excepcion");
			vListaCodigoError.add(10);	// 10 = Archivo Invalido			
		}
	}
	
	@Rollback(false)
	@Test
	public void TestValidarArchivoPaqueteP02() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		List<Integer> vRespuesta = new ArrayList<>();
		
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
		    vArchivoXml.setV_archivo(null);
			vRespuesta  = iValRec.validarArchivoPaquete(vArchivoXml.getV_archivo());			
			LOG.info("TestValidarArchivoPaqueteP01 ==>"+vRespuesta.toString()); 			
			// Caso Computarizado Ideal 
			// codigoRecepcion=12594
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("TestValidarArchivoPaqueteP01||Excepcion");
			vListaCodigoError.add(10);	// 10 = Archivo Invalido			
		}
	}
	
	@Rollback(false)
	@Test
	public void TestValidarArchivoPaqueteP03() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
        
		List<Integer> vRespuesta = new ArrayList<>();
		//Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
		    vArchivoXml.setV_archivo("H4sICI1H1FwEAGNvbXB1MS50YXIA7VXXXXXx");
			vRespuesta  = iValRec.validarArchivoPaquete(vArchivoXml.getV_archivo());			
			LOG.info("TestValidarArchivoPaqueteP01 ==>"+vRespuesta.toString()); 			
			// Caso Computarizado Ideal 
			// codigoRecepcion=12594
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("TestValidarArchivoPaqueteP01||Excepcion");			
		}
	}
	
	
	@Rollback(false)
	@Test
	public void TestValidarArchivoPaqueteP04() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
        
		List<Integer> vRespuesta = new ArrayList<>();
		//Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
		    vArchivoXml.setV_archivo("H4sICI1H1FwEAGNvbXB1MS50YXIA7VbbbptAEM2n9L1qtdyc8GiHi6EB2xgW7755IcLA4lqNDZiv7yxxGtKkaiu1VR8YKSIGdnfmzJnDuX84bvfp9su7D++kj23FPx7b49UfDiQhdH19fYUe4/urOpmgK0lRJxpSFU2F+5KkytrVO3T1D+IEAHy5Qv/krP8wloausrjJ0gqfE5nXrEC5t1YbJ59xVvk1tfmJdihPN0F1F2b5slP926KtSOyjdOPuVrJ+TOYYkY17uK+skkS4S23rRG3r7Mx3R2Zr3WK/67YhyrfzACXG5/pOSZX0rCneWauTKqm9Ytp4a52EURsSecdZbB63sdbvQ+QoH+5Djd/aB9aKsw8nhrQdi3GX2FZBsV9sbXwkUVsT2ULbWD8t1+4v1XQncrh13hurz5ljtAWJXU5kfCYh4JJPM+d2esMqjoJYOiSyfl4WZgN5KZ7hwdW7uZO1Q2ri43bj10kxWLPHR7px66CyinTe79d4xrT1C1OskSD3M0N0R5RASipTnC/WZUvbl2ihtjTGBTXS3A9nnMZEoiHOqR0pfogLEk7hnaTzw6YmCq6WGfoEa/v805iWy8pRSZHJtHJzWnmqL3vw22tIvIL1kezHZufbRPY6zJe3er9muAeTgwOV9TCNfSnZ+ztmqK147/X953qBV2ca+0WPfenPQrO1HLNdRufZKoi05QrpIexRbjcCX//AZHVQc8/LPZNmEtsHNa7wKbXN7H7uHxaVdmAG8HXuSnTtZHfd4MwKQ98ti8W8688tnMYLy6NnRPDHIy80J143nQDuJz8sJTi/ghw/k0j06/sctJrF7plid3df6adI1qEW60HUEkmBFUItYT6Lg6h143y2Xke+s8Ku6RhNzSr9SPYujyprwmQtZFAfiZs36gsOia2b8Bz6L2rUXWqLKy+hN+890U8ZMIrTGtu8YSgQ/T3SWEMM8VJct3Gwo8YbPLNFHyUu8FsWHloYibIwVt+49mKvAYZEzIbd8sd8bn6K+VN+K7k9iL1oqMoDbhjM5iIH/gaffLoJAAd9RWD9sp+DCy5I4oBFCf3fUflmgNsldynYJdWB/wonWKwLXCOmBD1v/U5gINU9r2wdvezL4/3Axh1gc8HOEfUcmSwwD3gCvXrE7DUGIZxFoR/LgqgL4MGr+88YoO1mBphZR1Lxx9qVngsGiaV8Gw9rHuba+pAHp7YJ3EjQt7ykoAb9enjjnAe6AfwruFe2/H49WzRfpplvRN0in3lM8bIEtC2tuJjTzrEDTmDWnLngIz/BTKCksuC7EWU0VjOoBzRSvAvXjZeB/mSg44JHB9DO8u52VkCO/f/OvBR770FvH58VMBc21gC7cqil6caXhA6zTluHJgIcQItj6/yMwfO8vOCs5HPQStDYoRa9mKUQZhsx5aLlfT+sHO7xb/pqtCXdAC/sdshPocGHtBIzGFiiZiZLByID5oaJ/G7awHk/euctDTtDbpLINZK50KR2EZInrr9+/npOVkmll6mopdNWUY7Ed6PxB/yK5m5N57hI7SFmPVfPwLOLBltegG6y2NoRyB/q9ouk4g3pPcGQy9ZL/Xn+/bz3HJ963TElTsWzUMNhuYLefX//ed9k7nJxFo7h+2g/9tcreiybpMIiRyy+qz0XXs+vKfJNf1GTEgXnl9l+OgM45V404LVWRjKGfKKf7vv0/hp0dbueqYlcTiDnB/AWKNlj7uTT+tI/6G/AxUwyOxK4we+nb7tlAFeaFHgH+E8I4BXAnBExFxvn/dUfjoH/l/8L/38t9/5fUUb//y9i9P//u/93Rv8/+v/R/4/+f/T/o/8f/f/o//9oPPn/v+X9f9v/I034f+kajf7/X8To//93/z8d/f/o/0f/P/r/0f+P/n/0/6P/H2OMMcb4I/EVBuduhwAoAAA=");
			vRespuesta  = iValRec.validarArchivoPaquete(vArchivoXml.getV_archivo());			
			LOG.info("TestValidarArchivoPaqueteP01 ==>"+vRespuesta.toString()); 			
			// Caso Computarizado Ideal 
			// codigoRecepcion=12594
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("TestValidarArchivoPaqueteP01||Excepcion");			
		}
	}
	
	@Rollback(false)
	@Test
	public void TestEnviarJsonRecepcionBDEtapa1P01() throws Exception{
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
	
	@Rollback(false)
	@Test
	public void TestEnviarJsonRecepcionBDEtapa1P02() throws Exception{
		
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
	
	@Rollback(false)
	@Test
	public void TestEnviarJsonRecepcionBDEtapa1P03() throws Exception{
		///???
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		
		LOG.info("recepcionFactura ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
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
	
	@Rollback(false)
	@Test
	public void TestEnviarJsonRecepcionBDEtapa1P04() throws Exception{
		
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		
		LOG.info("recepcionFactura ||Iniciando");
		try {
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-14T17:09:00.829");
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

	//*******
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionPaqXsdP01() throws Exception{
		
		//  va al catch 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {						
			
			vRespuesta=iValRec.validarValidacionRecepcionPaqXsd(vArchivoXml);
			LOG.info("testValidarValidacionRecepcionPaqXsdP01 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
		}
	}
	
		
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionPaqXsdP02() throws Exception{
		
		// 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionPaqueteId(139L); // se recepciono con error 
			//.setV_recepcionId(139L);  
			
			vRespuesta=iValRec.validarValidacionRecepcionPaqXsd(vArchivoXml);
				
			LOG.info("testValidarValidacionRecepcionPaqXsdP02 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionPaqXsdP03() throws Exception{
		
		// 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("39562F");
			vArchivoXml.setP_tipo_ambiente_id(3); //error 
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("0BDDA3BC");
			vArchivoXml.setP_cufd("dae91da3e083d42a3da456665b8cad2e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionPaqueteId(139L); // se recepciono con error 
			
			vRespuesta=iValRec.validarValidacionRecepcionPaqXsd(vArchivoXml);
				
			LOG.info("testValidarValidacionRecepcionPaqXsdP03 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionPaqXsdP04() throws Exception{
		
		// 
		List<Integer> vListaCodigoError = new ArrayList<>();
		RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
		LOG.info("validarDatos de la Recepcion ||Iniciando");
		try {
			
			vArchivoXml.setP_codigo_sistema("9B0B4B26");
			vArchivoXml.setP_tipo_ambiente_id(3); // error en xsd
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(5);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("F03B001D");
			vArchivoXml.setP_cufd("767B6B5704E94C8F779B9C33A38CBF2D");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setV_recepcionPaqueteId(139L); // se recepciono con error
			
			vRespuesta=iValRec.validarValidacionRecepcionPaqXsd(vArchivoXml);
				
			LOG.info("testValidarValidacionRecepcionPaqXsdP04 ==>"+vRespuesta.toString());  
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
			vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
			
		}
	}
	
	
	@Rollback(false)
	@Test
	public void testValidarValidacionRecepcionPaqXsdP05() throws Exception{
		
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
	
	@Rollback(false)
	@Test
	public void TestValidarRecepcionP01() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("98A710AE");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(3); //error
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("9583EFE");
			vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-16T19:28:00.829");
			vArchivoXml.setV_recepcionId(12667L);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.ValidacionRecepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestValidarRecepcionP01 Estandar ==>"+vRespuesta.toString());

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}

	
	@Rollback(false)
	@Test
	public void TestValidarRecepcionP02() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("98A710AE");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(1);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("9583EFE");
			vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-16T19:54:00.829");
			vArchivoXml.setV_recepcionId(12667L);
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.ValidacionRecepcion);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestValidarRecepcionP02 Estandar ==>"+vRespuesta.toString());

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	//-------------------------------------------------------------//
	//---------- Estandar C. Recepcion paquete -----------------------
	//-------------------------------------------------------------//
	@Rollback(false)
	@Test
	public void TestRecepcionPaqueteP01() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(3); //error
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-15T12:35:00.829");
			vArchivoXml.setV_archivo("H4sICAcX3FwEAGNvbXB1MS50YXIA7VbbktJAEN1P8d3SmtzUPPgA5EKQDCQkEzJvuWwl5IJRILevtwfYJStarlVqWcpUUYFhpvv06dOdvt/tg20cfH7x6gX3et/u737DQhxCb9++vUOn9fVTfPNWvOMEeCBOEHienX8joLsX6O4PrAMQ8PkO/RFff+FaKrIYek0Sl6SL+KIOM7QxV2JjbMZFWOKa6sWB9mgTr+1y7iSbZS/iSdaWvodRvJ6lFi/voylB/npW3Zda7rukj3XtQHWtM6bpPtSlfrFN+8BBm2Bqo0j5WM+FWIg7STA7qY7KqDazUWOuZN9xW8fn0yL01H3gSUc7Pu9uhnao8lN24C7zXR1CJKWhR/pI1zJKcBboZO+7be3zGgo8+bBczZ4V05xhmBgvFetjYiht5nuzwudJ5zvAy2aUGJPRu7AskO1xVcTL3TJTG8AlmIoJT/PdnJeqWCX7YI3rKBvc2ZI9Xc9qu9SyeHq015jKiMeKwe5wgL0LEU19weaiUmX+2b1kqWOOZqKAndnE6q0WqwZvqqaIkdHgPhFx73ZmTjQrV+E/izczOsaKCjbZvYt/XyAlVcQWZ66EEXz6kbRQZ/pCpYqdJchyCs1U4LvrN1aOVTNr6vOdiw1ezgMvrl2BZMBj73vNS9OBc9f7A/x2FZUk84/akxTLbU17NTYdzkxclSwchJdu3zAddtTD2TFXCfoAd4/8h7xdUV62Yk9CIUeLcGunDzkPvEY2tnYXe6ChybuBTwo5S9Njjo5+xc5UVGnujLi5o3JEgdgzq1uAnuaMDwf8A8ZA17TQK/qvMBxCnttADCt/XdVhgWuIJQ0ViIVg14Z4HCQmJCfminxM3LxQVqq2sicjltc69GYdJbP0vpQPLi9DfNruG/G5wXpc27qcxR7HYqxXns2eFWgyX2bHfDKOtiEHfOqyGvKYaeYQ6/KM6uxZ5L5nDTh40JTN8rinjL9e7LGSCKAbtJzIB+YrKoe2hhziOlyPd/SM58ecn/EhvAuOttyXOLtow+LbimGgzrWeHI8g4KF2dW0LGFvA9sALpmsb7MqWD+cGvD1gd6GGP9H1czTB1SwOosvopNuIcbAPeaYru471J3k57SO7iICbM3cdiyf0ZMa5Stc4i49xfosD8FWSHGqcXyjJY+4u+xcOIG8NcJaCTqpz7IhpweK1vV8Ww5ifYHUAB/XsFLQhYOURlxsKdhqq135CnUgQB+xJJl3nibP70BiKxZn9x8TR5d6Y4iLa0orVadSNcwp9L9yMe+C5gn7Xx9NZGm1xYYDWDKaN0mZnmZ/e0G3YLw5MRwFgopMmgb67O30fS8w2FUh3+i0CH21xD9xRfdDnpgR8aXDm3Us3bzHwwEUCSSPg4LpenujfodDXQ8EY9qInteTyJINz3dkOy0fqQ71TeBc87EENgS7a3RN9gt1gTY81aHssZhneWxg4H7VYiRoTeP/emW/1MNBrHjOsHK6OPcnJeYjze/9f993prKZTFgtwpBl7eH8ABmvQG8Yd2OLgHTLkLGdaBR6bcw8eO2q8NLTU9wA/xN37wgx6CvjkxaGW06/6z+X3xTYXlqzv2Bi0WLF8EldSIaar/Uuex9DnwRdHDsHaPuU3M45cRtNZwTASD96hOtPCdf3aR7zkeT1pijm/ONXLow+BbM494LpXcrgAPMUP7T721gL6ap7cT3G1KKUqhNkF6oSjKyOZ96f8ARfAP9Sk3haMN/j9rDlkmbx/f/cL1mD+5/+K+R++s/lfvM3/f2Td5v+/ff43f+38j/zGdtzb/P/9+R8RRYXZ3xBv8/9t/r/N/7f5/zb/3+b/f3X+v63buq3/d30Br1+RgwAcAAA=");
			vArchivoXml.setV_hash_archivo("550005ae2f8cee84a0e1ea2237825d13f258247063cc28f10fa33fb98d46e548");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.RecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionPaqueteP01 Estandar ==>"+vRespuesta.toString());
			// codigoRecepcion=139
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}

	@Rollback(false)
	@Test
	public void TestRecepcionPaqueteP02() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2	");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-15T11:14:00.829");
			vArchivoXml.setV_archivo("H4sICAcX3FwEAGNvbXB1MS50YXIA7VbbktJAEN1P8d3SmtzUPPgA5EKQDCQkEzJvuWwl5IJRILevtwfYJStarlVqWcpUUYFhpvv06dOdvt/tg20cfH7x6gX3et/u737DQhxCb9++vUOn9fVTfPNWvOMEeCBOEHienX8joLsX6O4PrAMQ8PkO/RFff+FaKrIYek0Sl6SL+KIOM7QxV2JjbMZFWOKa6sWB9mgTr+1y7iSbZS/iSdaWvodRvJ6lFi/voylB/npW3Zda7rukj3XtQHWtM6bpPtSlfrFN+8BBm2Bqo0j5WM+FWIg7STA7qY7KqDazUWOuZN9xW8fn0yL01H3gSUc7Pu9uhnao8lN24C7zXR1CJKWhR/pI1zJKcBboZO+7be3zGgo8+bBczZ4V05xhmBgvFetjYiht5nuzwudJ5zvAy2aUGJPRu7AskO1xVcTL3TJTG8AlmIoJT/PdnJeqWCX7YI3rKBvc2ZI9Xc9qu9SyeHq015jKiMeKwe5wgL0LEU19weaiUmX+2b1kqWOOZqKAndnE6q0WqwZvqqaIkdHgPhFx73ZmTjQrV+E/izczOsaKCjbZvYt/XyAlVcQWZ66EEXz6kbRQZ/pCpYqdJchyCs1U4LvrN1aOVTNr6vOdiw1ezgMvrl2BZMBj73vNS9OBc9f7A/x2FZUk84/akxTLbU17NTYdzkxclSwchJdu3zAddtTD2TFXCfoAd4/8h7xdUV62Yk9CIUeLcGunDzkPvEY2tnYXe6ChybuBTwo5S9Njjo5+xc5UVGnujLi5o3JEgdgzq1uAnuaMDwf8A8ZA17TQK/qvMBxCnttADCt/XdVhgWuIJQ0ViIVg14Z4HCQmJCfminxM3LxQVqq2sicjltc69GYdJbP0vpQPLi9DfNruG/G5wXpc27qcxR7HYqxXns2eFWgyX2bHfDKOtiEHfOqyGvKYaeYQ6/KM6uxZ5L5nDTh40JTN8rinjL9e7LGSCKAbtJzIB+YrKoe2hhziOlyPd/SM58ecn/EhvAuOttyXOLtow+LbimGgzrWeHI8g4KF2dW0LGFvA9sALpmsb7MqWD+cGvD1gd6GGP9H1czTB1SwOosvopNuIcbAPeaYru471J3k57SO7iICbM3cdiyf0ZMa5Stc4i49xfosD8FWSHGqcXyjJY+4u+xcOIG8NcJaCTqpz7IhpweK1vV8Ww5ifYHUAB/XsFLQhYOURlxsKdhqq135CnUgQB+xJJl3nibP70BiKxZn9x8TR5d6Y4iLa0orVadSNcwp9L9yMe+C5gn7Xx9NZGm1xYYDWDKaN0mZnmZ/e0G3YLw5MRwFgopMmgb67O30fS8w2FUh3+i0CH21xD9xRfdDnpgR8aXDm3Us3bzHwwEUCSSPg4LpenujfodDXQ8EY9qInteTyJINz3dkOy0fqQ71TeBc87EENgS7a3RN9gt1gTY81aHssZhneWxg4H7VYiRoTeP/emW/1MNBrHjOsHK6OPcnJeYjze/9f993prKZTFgtwpBl7eH8ABmvQG8Yd2OLgHTLkLGdaBR6bcw8eO2q8NLTU9wA/xN37wgx6CvjkxaGW06/6z+X3xTYXlqzv2Bi0WLF8EldSIaar/Uuex9DnwRdHDsHaPuU3M45cRtNZwTASD96hOtPCdf3aR7zkeT1pijm/ONXLow+BbM494LpXcrgAPMUP7T721gL6ap7cT3G1KKUqhNkF6oSjKyOZ96f8ARfAP9Sk3haMN/j9rDlkmbx/f/cL1mD+5/+K+R++s/lfvM3/f2Td5v+/ff43f+38j/zGdtzb/P/9+R8RRYXZ3xBv8/9t/r/N/7f5/zb/3+b/f3X+v63buq3/d30Br1+RgwAcAAA=");
			vArchivoXml.setV_hash_archivo("550005ae2f8cee84a0e1ea2237825d13f258247063cc28f10fa33fb98d46e548");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.RecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionPaqueteP01 Estandar ==>"+vRespuesta.toString());
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	@Rollback(false)
	@Test
	public void TestRecepcionPaqueteP03() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-15T20:19:00.829");
			vArchivoXml.setV_archivo("H4sICAcX3FwEAGNvbXB1MS50YXIA7VbbktJAEN1P8d3SmtzUPPgA5EKQDCQkEzJvuWwl5IJRILevtwfYJStarlVqWcpUUYFhpvv06dOdvt/tg20cfH7x6gX3et/u737DQhxCb9++vUOn9fVTfPNWvOMEeCBOEHienX8joLsX6O4PrAMQ8PkO/RFff+FaKrIYek0Sl6SL+KIOM7QxV2JjbMZFWOKa6sWB9mgTr+1y7iSbZS/iSdaWvodRvJ6lFi/voylB/npW3Zda7rukj3XtQHWtM6bpPtSlfrFN+8BBm2Bqo0j5WM+FWIg7STA7qY7KqDazUWOuZN9xW8fn0yL01H3gSUc7Pu9uhnao8lN24C7zXR1CJKWhR/pI1zJKcBboZO+7be3zGgo8+bBczZ4V05xhmBgvFetjYiht5nuzwudJ5zvAy2aUGJPRu7AskO1xVcTL3TJTG8AlmIoJT/PdnJeqWCX7YI3rKBvc2ZI9Xc9qu9SyeHq015jKiMeKwe5wgL0LEU19weaiUmX+2b1kqWOOZqKAndnE6q0WqwZvqqaIkdHgPhFx73ZmTjQrV+E/izczOsaKCjbZvYt/XyAlVcQWZ66EEXz6kbRQZ/pCpYqdJchyCs1U4LvrN1aOVTNr6vOdiw1ezgMvrl2BZMBj73vNS9OBc9f7A/x2FZUk84/akxTLbU17NTYdzkxclSwchJdu3zAddtTD2TFXCfoAd4/8h7xdUV62Yk9CIUeLcGunDzkPvEY2tnYXe6ChybuBTwo5S9Njjo5+xc5UVGnujLi5o3JEgdgzq1uAnuaMDwf8A8ZA17TQK/qvMBxCnttADCt/XdVhgWuIJQ0ViIVg14Z4HCQmJCfminxM3LxQVqq2sicjltc69GYdJbP0vpQPLi9DfNruG/G5wXpc27qcxR7HYqxXns2eFWgyX2bHfDKOtiEHfOqyGvKYaeYQ6/KM6uxZ5L5nDTh40JTN8rinjL9e7LGSCKAbtJzIB+YrKoe2hhziOlyPd/SM58ecn/EhvAuOttyXOLtow+LbimGgzrWeHI8g4KF2dW0LGFvA9sALpmsb7MqWD+cGvD1gd6GGP9H1czTB1SwOosvopNuIcbAPeaYru471J3k57SO7iICbM3cdiyf0ZMa5Stc4i49xfosD8FWSHGqcXyjJY+4u+xcOIG8NcJaCTqpz7IhpweK1vV8Ww5ifYHUAB/XsFLQhYOURlxsKdhqq135CnUgQB+xJJl3nibP70BiKxZn9x8TR5d6Y4iLa0orVadSNcwp9L9yMe+C5gn7Xx9NZGm1xYYDWDKaN0mZnmZ/e0G3YLw5MRwFgopMmgb67O30fS8w2FUh3+i0CH21xD9xRfdDnpgR8aXDm3Us3bzHwwEUCSSPg4LpenujfodDXQ8EY9qInteTyJINz3dkOy0fqQ71TeBc87EENgS7a3RN9gt1gTY81aHssZhneWxg4H7VYiRoTeP/emW/1MNBrHjOsHK6OPcnJeYjze/9f993prKZTFgtwpBl7eH8ABmvQG8Yd2OLgHTLkLGdaBR6bcw8eO2q8NLTU9wA/xN37wgx6CvjkxaGW06/6z+X3xTYXlqzv2Bi0WLF8EldSIaar/Uuex9DnwRdHDsHaPuU3M45cRtNZwTASD96hOtPCdf3aR7zkeT1pijm/ONXLow+BbM494LpXcrgAPMUP7T721gL6ap7cT3G1KKUqhNkF6oSjKyOZ96f8ARfAP9Sk3haMN/j9rDlkmbx/f/cL1mD+5/+K+R++s/lfvM3/f2Td5v+/ff43f+38j/zGdtzb/P/9+R8RRYXZ3xBv8/9t/r/N/7f5/zb/3+b/f3X+v63buq3/d30Br1+RgwAcAAA=");
			vArchivoXml.setV_hash_archivo("550005ae2f8cee84a0e1ea2237825d13f258247063cc28f10fa33fb98d46e548");
			RespuestaListaRegistroRecepcionesSoapDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipal(vArchivoXml, Parametros.RecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionPaqueteP01 Estandar ==>"+vRespuesta.toString());
			// codigoRecepcion=139
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	
	@Rollback(false)
	@Test
	public void TestValidarRecepcionPaqueteP01() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(3); //error
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-15T20:22:00.829");
			vArchivoXml.setV_recepcionPaqueteId(140L);
			RespuestaValidacionRecepcionPaqueteDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipalPaquete(vArchivoXml, Parametros.ValidacionRecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionPaqueteP01 Estandar ==>"+vRespuesta.toString());

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}

	
	@Rollback(false)
	@Test
	public void TestValidarRecepcionPaqueteP02() throws Exception{
		// computarizada
		XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();

		Respuesta vRespuesta = new Respuesta();
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//***            
			vArchivoXml.setP_codigo_sistema("9BB347E");
			vArchivoXml.setP_tipo_ambiente_id(2);
			vArchivoXml.setP_tipo_emision_id(2);
			vArchivoXml.setP_tipo_modalidad_id(2);
			vArchivoXml.setP_nit(1020703023L);
			vArchivoXml.setP_cuis("85EA4EE9");
			vArchivoXml.setP_cufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			vArchivoXml.setP_tipo_documento_fiscal_id(1);
			vArchivoXml.setP_tipo_documento_sector_id(1);
			vArchivoXml.setP_sucursal_id(0);
			vArchivoXml.setP_punto_venta_id(null);			
			vArchivoXml.setP_fecha_envio("2019-05-15T16:30:00.829");
			vArchivoXml.setV_recepcionPaqueteId(139L);
			RespuestaValidacionRecepcionPaqueteDto vRespuestaDto = iCoreFacturacionSoap.CoreFacturacionPrincipalPaquete(vArchivoXml, Parametros.ValidacionRecepcionPaquete);
			// conversion a interfaces
			vRespuesta.setCodigoRecepcion(vRespuestaDto.getCodigoRecepcion());
			vRespuesta.setTransaccion(vRespuestaDto.isTransaccion());
			vRespuesta.setCodigoEstado(vRespuestaDto.getCodigoEstado());
			vRespuesta.setListaCodigosRespuestas(vRespuestaDto.getListaCodigosRespuestas());

			LOG.info("TestRecepcionPaqueteP01 Estandar ==>"+vRespuesta.toString());

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("registrarRecepcion Estandar||Excepcion");
			vRespuesta.setCodigoRecepcion(0L);
			vRespuesta.setTransaccion(false);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.ERROR_DE_EJECUCION_DEL_SERVICIO);
		}
	}
	// -----------------------------------------------------//
	//----------- anulación -----------------------
	//------------------------------------------------------//
		@Rollback(false)
		@Test
		public void testAnulacionValidarXsdP01() throws Exception{
			
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
		
			
		@Rollback(false)
		@Test
		public void testAnulacionValidarXsdP02() throws Exception{
			
			// 
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
		
		
		@Rollback(false)
		@Test
		public void testAnulacionValidarXsdP03() throws Exception{
			
			// 
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			LOG.info("validarDatos de la Recepcion ||Iniciando");
			try {
				
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2);
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(3); // error
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
					
				LOG.info("testAnulacionValidarXsdP03 ==>"+vRespuesta.toString());  
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("testAnulacionValidarXsdP01||Excepcion");
				vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
				vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
				
			}
		}
		
		@Rollback(false)
		@Test
		public void testAnulacionValidarXsdP04() throws Exception{
			
			// 
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			LOG.info("validarDatos de la Recepcion ||Iniciando");
			try {
				
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(3); // error en xsd
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(5); // prevalorada
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(28); // prevalorada
				vArchivoXml.setP_sucursal_id(0);
				vArchivoXml.setP_punto_venta_id(null);			
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_cufd("abc123456");
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				vRespuesta=iValRec.validarAnulacionXsd(vArchivoXml);
					
				LOG.info("testAnulacionValidarXsdP04 ==>"+vRespuesta.toString());  
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("testAnulacionValidarXsdP04 ||Excepcion");
				vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
				vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
				
			}
		}
		
		
		@Rollback(false)
		@Test
		public void testAnulacionValidarXsdP05() throws Exception{
			
			//  debe estar en el proyecto de prevalorada  OK  necesita archivo validacionRecepcionPaquete de prevalorada
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaXmlXsdDto vRespuesta = new RespuestaXmlXsdDto();
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			LOG.info("validarDatos de la Recepcion ||Iniciando");
			try {
				
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2); 
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(5);
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("767B6B5704E94C8F779B9C33A38CBF2D");
				vArchivoXml.setP_cuape("dae91da3e083d42a3da456665b8cad2e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(28);
				vArchivoXml.setP_sucursal_id(0);
				vArchivoXml.setP_punto_venta_id(null);			
				vArchivoXml.setP_numero_documento_fiscal(66L);
				vArchivoXml.setP_cufp("a812312f1223892b");
				vArchivoXml.setP_motivo_anulacion_id(912);
				
				vRespuesta=iValRec.validarAnulacionXsd(vArchivoXml);
					
				LOG.info("testAnulacionValidarXsdP05 ==>"+vRespuesta.toString());  
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("testAnulacionValidarXsdP05||Excepcion");
				vListaCodigoError.add(88);	// 88 = FORMATO_XSD_INCORRECTO
				vRespuesta.setListaCodigosRespuestas(vListaCodigoError);
				
			}
		}
		
		@Rollback(false)
		@Test
		public void TestAnulacionBdP01() throws Exception{
			// va al catch 
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
			
			LOG.info("recepcionFactura ||Iniciando");
			try {   
				vArchivoXml = null;
				vRespuesta  = iValRec.anulacionBd(vArchivoXml);			
				LOG.info("TestAnulacionBdP01 ==>"+vRespuesta.toString()); 			

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("TestAnulacionBdP01||Excepcion");
				vListaCodigoError.add(10);	// 10 = Archivo Invalido			
			}
		}
		
		@Rollback(false)
		@Test
		public void TestAnulacionBdP02() throws Exception{
			
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
			
			LOG.info("recepcionFactura ||Iniciando");
			try {
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(3); // error
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(2); 
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(1);
				vArchivoXml.setP_sucursal_id(1);
				vArchivoXml.setP_punto_venta_id(null);				
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				vRespuesta  = iValRec.anulacionBd(vArchivoXml);				
				LOG.info("TestAnulacionBdP02 ==>"+vRespuesta.toString()); 			
				// Caso Computarizado Ideal 
				// codigoRecepcion=12594
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("TestAnulacionBdP02||Excepcion");
				vListaCodigoError.add(10);	// 10 = Archivo Invalido			
			}
		}
		
		@Rollback(false)
		@Test
		public void TestAnulacionBdP03() throws Exception{
			///???
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
			
			LOG.info("recepcionFactura ||Iniciando");
			try {
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2); 
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(2); 
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(1);
				vArchivoXml.setP_sucursal_id(1);
				vArchivoXml.setP_punto_venta_id(null);				
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				vRespuesta  = iValRec.enviarJsonRecepcionBDEtapa1(vArchivoXml);			
				LOG.info("TestAnulacionBdP03 ==>"+vRespuesta.toString()); 			
				// Caso Computarizado Ideal 
				// codigoRecepcion=12594
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("TestAnulacionBdP03||Excepcion");
				vListaCodigoError.add(10);	// 10 = Archivo Invalido			
			}
		}
		
		@Rollback(false)
		@Test
		public void TestAnulacionBdP04() throws Exception{
			
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
			
			LOG.info("recepcionFactura ||Iniciando");
			try {
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2); 
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(2); 
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(1);
				vArchivoXml.setP_sucursal_id(1);
				vArchivoXml.setP_punto_venta_id(null);				
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				vRespuesta  = iValRec.enviarJsonRecepcionBDEtapa1(vArchivoXml);			
				LOG.info("TestAnulacionBdP04 ==>"+vRespuesta.toString()); 			
				// Caso Computarizado Ideal 
				// codigoRecepcion=12594
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("TestAnulacionBdP04||Excepcion");
				vListaCodigoError.add(10);	// 10 = Archivo Invalido			
			}
		}
		
		@Rollback(false)
		@Test
		public void TestAnulacionFacturaP01() throws Exception{
			
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
			
			LOG.info("recepcionFactura ||Iniciando");
			try {
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2); 
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(2); 
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(1);
				vArchivoXml.setP_sucursal_id(1);
				vArchivoXml.setP_punto_venta_id(null);				
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				vRespuesta  = iValRec.enviarJsonRecepcionBDEtapa1(vArchivoXml);			
				LOG.info("TestAnulacionBdP04 ==>"+vRespuesta.toString()); 			
				// Caso Computarizado Ideal 
				// codigoRecepcion=12594
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("TestAnulacionBdP04||Excepcion");
				vListaCodigoError.add(10);	// 10 = Archivo Invalido			
			}
		}
		
		@Rollback(false)
		@Test
		public void TestAnulacionFacturaP02() throws Exception{
			
			XmlRecepcionGenericoDto vArchivoXml = new XmlRecepcionGenericoDto();
			List<Integer> vListaCodigoError = new ArrayList<>();
			RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
			
			LOG.info("recepcionFactura ||Iniciando");
			try {
				vArchivoXml.setP_codigo_sistema("98A710AE");
				vArchivoXml.setP_tipo_ambiente_id(2); 
				vArchivoXml.setP_tipo_emision_id(2);
				vArchivoXml.setP_tipo_modalidad_id(2); 
				vArchivoXml.setP_nit(1020703023L);
				vArchivoXml.setP_cuis("9583EFE");
				vArchivoXml.setP_cufd("b8b86fbbfc87c8b30ad75565a34f635e");
				vArchivoXml.setP_tipo_documento_fiscal_id(1);
				vArchivoXml.setP_tipo_documento_sector_id(1);
				vArchivoXml.setP_sucursal_id(1);
				vArchivoXml.setP_punto_venta_id(null);				
				vArchivoXml.setP_numero_documento_fiscal(150L);
				vArchivoXml.setP_motivo_anulacion_id(912);
				vArchivoXml.setP_cuf("1eecd7b50eef5e5bde755ca05f735");
				vRespuesta  = iValRec.enviarJsonRecepcionBDEtapa1(vArchivoXml);			
				LOG.info("TestAnulacionBdP04 ==>"+vRespuesta.toString()); 			
				// Caso Computarizado Ideal 
				// codigoRecepcion=12594
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("TestAnulacionBdP04||Excepcion");
				vListaCodigoError.add(10);	// 10 = Archivo Invalido			
			}
		}
}
