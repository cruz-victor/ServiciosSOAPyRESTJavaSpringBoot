package bo.gob.sin.sre.fac.fcel.fest.test;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionRecepcion;
import bo.gob.sin.sre.fac.fcel.fest.service.IServicioFacturaComputarizadaEstandar;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback 
public class TestRecepcionIndivualP03 {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39112/FacturaComputarizadaEstandar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaComputarizadaEstandar";
	String rutaArchivoEntrada = "D:\\PruebaServicio\\archivo.xml";

	
	@Rollback(false)
	@Test
	public void recepcionIndivual03() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
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
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICOIf3lwEAEZDRXN0YW5kYXIxLnR4dACVVtu2mjAQ/aC+cJG1ykMfVC5iTZQIBPJGoAsNwdJWQfj6Tqineurp7QkNycyePXt22Dn2jNO+KptkKAzZcaEd0X7WB8eF5A3umC8vbNSOZUqaTVQdd+MML8W1ySjWynR9CA37XKwSLUvX7afGq7M4GUvfuzDfG4LV4cx9a9yeDmMeacd8RbTC+dxtzNIsB8tEg9UVTdEhMe/R3s6i+BplxkFy6p5zak1xMiM+PsZhzn/FgbMqd3vhmnXgNBkL3xMswSL3k3MWX7vM8LSc2pfdfv1PNW0UhmXwzgk/V4FzFRldy8xIhiwCXo7zKljO3/NGaoTqbWHYw064PeAykYPgid5vDKst3eScp7grxMOZU3Jm6bojjSfK1RRPR06h9uuAe+AaO2Qm0YvGVbnVmWrnY52JmYmj9TIcwyt2AwO5aLZ1Fi4RxYzUsYEiuSRuoOMx1LHDliQKIKY6d8+dmUnDnNmMCKSTCC+wqE3kZjMUBwbWYg1F8Yhj7KAaadto7ZCo725n7jEMu85p2cVmIoDDMaP9O+TAvuf1B/ykLZpEZJPuLCeMr4jsFyjSURW7yTbS8C4ee6XBgVEspj5V2kc4O3HPDdIyww5LamlcZ5KfyOGl3znt7eBEhpKCfpbvH3Iy6NfhMPVnyjsbkONam2iubyLXSJxg2IpQ34rA3IhqhgXkB4y573mcyvEXDBdu6EeoYZ+lbccl7qCWA3eglgTHBOqJtFmV1AnaJ5+ruJbO3vX2ZDlXfe04XQ8sWR8+NfYlNmyoz/v2Rn1xni464tuipLqqsdtTop4t6LEGfUGsaU5PXAc+fdvlBlaauZS+vWa+eso6o+EDBy+aIqqPZ6b4G2cjjkITjxXoJ9Z2S/ui8hXNY7xHHnHH08U3dsP0d95vGDX8LZ9ixe+20V0foXFtFQ4WPWsqookGXHSx750A5xWwvXCDWUogrh1msO+BuxfsMczwF5b+iy70TtWR+LY2aTfKLMhz5obSFulK/1VvfqxrRBbAzY0/Xe1/Xn+rHvvCKDnsRAZc993T+j2PpnofGt45a2Q39dqceuxkVD/m9Hf4rxhwSOa7b9bxRp5vLJXg97BWX+Wn/WLbf51X2InH7XGBuImqAjyubKSawTHwicya5BKslC7kBbxMKxoP7o+4YnRWQT3glWovPFNUMT+uwM+V1ibNbpYLARin38GqVrFP4A0/3sG8cT+xgLv60VPLFOvKj/lo7SNXAx7Ak6k35PRZY690rWMJvgle+6ibVzMSMfB9bt48feqHd4Q1+dNrnWutNMT9q3zgTPlxWzZqtogHNSsvaDPDfYdF0KNo3kO+3+15xqwvBsCmK6yxIS87UY0oCscHzCHouS4pVjW1XLzhvat1x1aJUHMae8EZOXAfOhn4w3OOWx232rAoGtnffHjPoKeFqer1ph6qHk13I/hTnraHad9Redik8wE0+uTNGXCfg8aY84P3+/+HnlJr6jnMd63e7WSyJcD/0/o9bg9eq3Il6o79oQfl34DFXKg7ouV6csmhV1BP95d5HbDSGrWVBlzFQfmrx61Ac/I2+8I1MWArzOR484gnP1Vag7zyD16zvnuvBH3W1acVbreN1XIHvrFWa53tg2pz8wzg7ZXu4P8/faPsqg8fvgNebwrG2AkAAA==");
		solicitud.setHashArchivo("9820094b249d19c9901e6da056418fdbf745e56020b8LILILILILILIXXXXXCCC"); // error
		IRespuesta respuesta = servicio.recepcion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	
}
