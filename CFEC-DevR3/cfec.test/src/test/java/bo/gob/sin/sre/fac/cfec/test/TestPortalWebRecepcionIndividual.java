package bo.gob.sin.sre.fac.cfec.test;

import static org.junit.Assert.assertTrue;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fact.cfec.restclient.IServiciosClientRest;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback
public class TestPortalWebRecepcionIndividual {
	private static final Logger LOG = LoggerFactory.getLogger(TestPortalWebRecepcionIndividual.class);

	@Autowired
	IServiciosClientRest iServiciosClientRest;

	@Rollback(false)
	@Test
	public void recepcionFacturaPortalWeb01() throws Exception {
		RespuestaJsonDto vRespuesta = new RespuestaJsonDto();
		try {
			LOG.info("recepcionFacturaPortalWeb01 ==>" + vRespuesta.toString());
			SolicitudRecepcion solicitud = new SolicitudRecepcion();
			solicitud.setCodigoSistema("E79A306");
			solicitud.setCodigoAmbiente(2);
			solicitud.setCodigoEmision(1);
			solicitud.setCodigoModalidad(6);
			solicitud.setNit(1020703023L);
			solicitud.setCuis("10DC8801");
			solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
			solicitud.setCodigoDocumentoFiscal(1);
			solicitud.setCodigoDocumentoSector(1);
			solicitud.setCodigoSucursal(0);
			solicitud.setCodigoPuntoVenta(0);
			solicitud.setFechaEnvio(new Date());
			solicitud.setArchivo(
					"H4sICOIf3lwEAEZDRXN0YW5kYXIxLnR4dACVVtu2mjAQ/aC+cJG1ykMfVC5iTZQIBPJGoAsNwdJWQfj6Tqineurp7QkNycyePXt22Dn2jNO+KptkKAzZcaEd0X7WB8eF5A3umC8vbNSOZUqaTVQdd+MML8W1ySjWynR9CA37XKwSLUvX7afGq7M4GUvfuzDfG4LV4cx9a9yeDmMeacd8RbTC+dxtzNIsB8tEg9UVTdEhMe/R3s6i+BplxkFy6p5zak1xMiM+PsZhzn/FgbMqd3vhmnXgNBkL3xMswSL3k3MWX7vM8LSc2pfdfv1PNW0UhmXwzgk/V4FzFRldy8xIhiwCXo7zKljO3/NGaoTqbWHYw064PeAykYPgid5vDKst3eScp7grxMOZU3Jm6bojjSfK1RRPR06h9uuAe+AaO2Qm0YvGVbnVmWrnY52JmYmj9TIcwyt2AwO5aLZ1Fi4RxYzUsYEiuSRuoOMx1LHDliQKIKY6d8+dmUnDnNmMCKSTCC+wqE3kZjMUBwbWYg1F8Yhj7KAaadto7ZCo725n7jEMu85p2cVmIoDDMaP9O+TAvuf1B/ykLZpEZJPuLCeMr4jsFyjSURW7yTbS8C4ee6XBgVEspj5V2kc4O3HPDdIyww5LamlcZ5KfyOGl3znt7eBEhpKCfpbvH3Iy6NfhMPVnyjsbkONam2iubyLXSJxg2IpQ34rA3IhqhgXkB4y573mcyvEXDBdu6EeoYZ+lbccl7qCWA3eglgTHBOqJtFmV1AnaJ5+ruJbO3vX2ZDlXfe04XQ8sWR8+NfYlNmyoz/v2Rn1xni464tuipLqqsdtTop4t6LEGfUGsaU5PXAc+fdvlBlaauZS+vWa+eso6o+EDBy+aIqqPZ6b4G2cjjkITjxXoJ9Z2S/ui8hXNY7xHHnHH08U3dsP0d95vGDX8LZ9ixe+20V0foXFtFQ4WPWsqookGXHSx750A5xWwvXCDWUogrh1msO+BuxfsMczwF5b+iy70TtWR+LY2aTfKLMhz5obSFulK/1VvfqxrRBbAzY0/Xe1/Xn+rHvvCKDnsRAZc993T+j2PpnofGt45a2Q39dqceuxkVD/m9Hf4rxhwSOa7b9bxRp5vLJXg97BWX+Wn/WLbf51X2InH7XGBuImqAjyubKSawTHwicya5BKslC7kBbxMKxoP7o+4YnRWQT3glWovPFNUMT+uwM+V1ibNbpYLARin38GqVrFP4A0/3sG8cT+xgLv60VPLFOvKj/lo7SNXAx7Ak6k35PRZY690rWMJvgle+6ibVzMSMfB9bt48feqHd4Q1+dNrnWutNMT9q3zgTPlxWzZqtogHNSsvaDPDfYdF0KNo3kO+3+15xqwvBsCmK6yxIS87UY0oCscHzCHouS4pVjW1XLzhvat1x1aJUHMae8EZOXAfOhn4w3OOWx232rAoGtnffHjPoKeFqer1ph6qHk13I/hTnraHad9Redik8wE0+uTNGXCfg8aY84P3+/+HnlJr6jnMd63e7WSyJcD/0/o9bg9eq3Il6o79oQfl34DFXKg7ouV6csmhV1BP95d5HbDSGrWVBlzFQfmrx61Ac/I2+8I1MWArzOR484gnP1Vag7zyD16zvnuvBH3W1acVbreN1XIHvrFWa53tg2pz8wzg7ZXu4P8/faPsqg8fvgNebwrG2AkAAA==");
			solicitud.setHashArchivo("beaa28db28a83bbc943be610638c20e2ab41a90928fea8f9357a4aef24af7e59");

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
			Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			vXmlRecGenDto.setP_fecha_envio(formatter.format(solicitud.getFechaEnvio()));
			vXmlRecGenDto.setV_archivo(solicitud.getArchivo());
			vXmlRecGenDto.setV_hash_archivo(solicitud.getHashArchivo());
			vRespuesta = iServiciosClientRest.recepcionFacturaPortalWeb(vXmlRecGenDto);
			System.out.print("Resultado: " + vRespuesta.toString());
			assertTrue(vRespuesta.getRespuesta().length() > 0);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.error(e.getMessage());
			assertTrue(vRespuesta.getRespuesta().length() > 0);
		}
	}

}
