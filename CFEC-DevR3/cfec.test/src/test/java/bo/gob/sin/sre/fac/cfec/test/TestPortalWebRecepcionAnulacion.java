package bo.gob.sin.sre.fac.cfec.test;

import static org.junit.Assert.assertTrue;

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
import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudAnulacion;
import bo.gob.sin.sre.fact.cfec.restclient.IServiciosClientRest;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback
public class TestPortalWebRecepcionAnulacion {
	private static final Logger LOG = LoggerFactory.getLogger(TestPortalWebRecepcionAnulacion.class);

	@Autowired
	IServiciosClientRest iServiciosClientRest;

	@Rollback(false)
	@Test
	public void anulacionFacturaPortalWebP01() throws Exception {
		RespuestaJsonDto vRespuesta = new RespuestaJsonDto();
		try {

			LOG.info("anulacionFacturaPortalWebP01 ==>" + vRespuesta.toString());
			SolicitudAnulacion solicitud = new SolicitudAnulacion();
			solicitud.setCodigoSistema("E79A306");
			solicitud.setCodigoAmbiente(2);
			solicitud.setCodigoEmision(1);
			solicitud.setCodigoModalidad(2);
			solicitud.setNit(1020703023L);
			solicitud.setCuis("10DC8801");
			solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
			solicitud.setCodigoDocumentoFiscal(1);
			solicitud.setCodigoDocumentoSector(1);
			solicitud.setCodigoSucursal(0);
			solicitud.setCodigoPuntoVenta(0);
			solicitud.setNumeroDocumentoFiscal(12786L);
			solicitud.setCuf("752BC414B60C880DF78FE619BDB574546BE2");
			solicitud.setCodigoMotivo(912);

			XmlRecepcionGenericoDto vXmlRecGenDto = new XmlRecepcionGenericoDto();
			vXmlRecGenDto.setP_codigo_sistema(solicitud.getCodigoSistema());
			vXmlRecGenDto.setP_tipo_ambiente_id(solicitud.getCodigoAmbiente());
			vXmlRecGenDto.setP_tipo_emision_id(solicitud.getCodigoEmision());
			vXmlRecGenDto.setP_tipo_modalidad_id(solicitud.getCodigoModalidad());
			vXmlRecGenDto.setP_nit(solicitud.getNit());
			vXmlRecGenDto.setP_cuis(solicitud.getCuis());
			vXmlRecGenDto.setP_cufd(solicitud.getCufd());
			vXmlRecGenDto.setP_tipo_documento_fiscal_id(solicitud.getCodigoDocumentoFiscal());
			vXmlRecGenDto.setP_tipo_documento_sector_id(solicitud.getCodigoDocumentoSector());
			vXmlRecGenDto.setP_sucursal_id(solicitud.getCodigoSucursal());
			if (solicitud.getCodigoPuntoVenta() != null && solicitud.getCodigoPuntoVenta() > 0) {
				vXmlRecGenDto.setP_punto_venta_id(solicitud.getCodigoPuntoVenta());
			}

			vXmlRecGenDto.setP_numero_documento_fiscal(solicitud.getNumeroDocumentoFiscal());
			vXmlRecGenDto.setP_cuf(solicitud.getCuf());
			vXmlRecGenDto.setP_motivo_anulacion_id(solicitud.getCodigoMotivo());
			vRespuesta = iServiciosClientRest.anulacionFacturaPortalWeb(vXmlRecGenDto);
			System.out.print("Resultado: " + vRespuesta.toString());
			assertTrue(vRespuesta.getRespuesta().length() > 0);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.error(e.getMessage());
			assertTrue(vRespuesta.getRespuesta().length() > 0);
		}
	}

}
