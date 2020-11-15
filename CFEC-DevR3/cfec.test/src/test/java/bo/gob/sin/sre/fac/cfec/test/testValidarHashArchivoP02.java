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
public class testValidarHashArchivoP02 {
	private static final Logger LOG = LoggerFactory.getLogger(testValidarHashArchivoP02.class);
	
	@Autowired
	ICoreFacturacionSoap iCoreFacturacionSoap;
		
	@Autowired
	IValidarRecepcion iValRec;
	
	@Autowired
	IUtilitarios iutil;
	
	
	@Rollback(false)
	@Test
	public void validarHashArchivoP02() throws Exception{
		
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
	
}
