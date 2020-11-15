package bo.gob.sin.sre.fac.fcel.fest.test;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionRecepcion;
import bo.gob.sin.sre.fac.fcel.fest.service.IServicioFacturaComputarizadaEstandar;

public class TestComputarizadaEstandar {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39112/FacturaComputarizadaEstandar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaComputarizadaEstandar";
	String rutaArchivoEntrada = "D:\\PruebaServicio\\archivo.xml";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("FC809F97");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("2E3B2E8DE90CB3F848ADC6826DB3AF61");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICOIf3lwEAEZDRXN0YW5kYXIxLnR4dACVVtu2mjAQ/aC+cJG1ykMfVC5iTZQIBPJGoAsNwdJWQfj6Tqineurp7QkNycyePXt22Dn2jNO+KptkKAzZcaEd0X7WB8eF5A3umC8vbNSOZUqaTVQdd+MML8W1ySjWynR9CA37XKwSLUvX7afGq7M4GUvfuzDfG4LV4cx9a9yeDmMeacd8RbTC+dxtzNIsB8tEg9UVTdEhMe/R3s6i+BplxkFy6p5zak1xMiM+PsZhzn/FgbMqd3vhmnXgNBkL3xMswSL3k3MWX7vM8LSc2pfdfv1PNW0UhmXwzgk/V4FzFRldy8xIhiwCXo7zKljO3/NGaoTqbWHYw064PeAykYPgid5vDKst3eScp7grxMOZU3Jm6bojjSfK1RRPR06h9uuAe+AaO2Qm0YvGVbnVmWrnY52JmYmj9TIcwyt2AwO5aLZ1Fi4RxYzUsYEiuSRuoOMx1LHDliQKIKY6d8+dmUnDnNmMCKSTCC+wqE3kZjMUBwbWYg1F8Yhj7KAaadto7ZCo725n7jEMu85p2cVmIoDDMaP9O+TAvuf1B/ykLZpEZJPuLCeMr4jsFyjSURW7yTbS8C4ee6XBgVEspj5V2kc4O3HPDdIyww5LamlcZ5KfyOGl3znt7eBEhpKCfpbvH3Iy6NfhMPVnyjsbkONam2iubyLXSJxg2IpQ34rA3IhqhgXkB4y573mcyvEXDBdu6EeoYZ+lbccl7qCWA3eglgTHBOqJtFmV1AnaJ5+ruJbO3vX2ZDlXfe04XQ8sWR8+NfYlNmyoz/v2Rn1xni464tuipLqqsdtTop4t6LEGfUGsaU5PXAc+fdvlBlaauZS+vWa+eso6o+EDBy+aIqqPZ6b4G2cjjkITjxXoJ9Z2S/ui8hXNY7xHHnHH08U3dsP0d95vGDX8LZ9ixe+20V0foXFtFQ4WPWsqookGXHSx750A5xWwvXCDWUogrh1msO+BuxfsMczwF5b+iy70TtWR+LY2aTfKLMhz5obSFulK/1VvfqxrRBbAzY0/Xe1/Xn+rHvvCKDnsRAZc993T+j2PpnofGt45a2Q39dqceuxkVD/m9Hf4rxhwSOa7b9bxRp5vLJXg97BWX+Wn/WLbf51X2InH7XGBuImqAjyubKSawTHwicya5BKslC7kBbxMKxoP7o+4YnRWQT3glWovPFNUMT+uwM+V1ibNbpYLARin38GqVrFP4A0/3sG8cT+xgLv60VPLFOvKj/lo7SNXAx7Ak6k35PRZY690rWMJvgle+6ibVzMSMfB9bt48feqHd4Q1+dNrnWutNMT9q3zgTPlxWzZqtogHNSsvaDPDfYdF0KNo3kO+3+15xqwvBsCmK6yxIS87UY0oCscHzCHouS4pVjW1XLzhvat1x1aJUHMae8EZOXAfOhn4w3OOWx232rAoGtnffHjPoKeFqer1ph6qHk13I/hTnraHad9Redik8wE0+uTNGXCfg8aY84P3+/+HnlJr6jnMd63e7WSyJcD/0/o9bg9eq3Il6o79oQfl34DFXKg7ouV6csmhV1BP95d5HbDSGrWVBlzFQfmrx61Ac/I2+8I1MWArzOR484gnP1Vag7zyD16zvnuvBH3W1acVbreN1XIHvrFWa53tg2pz8wzg7ZXu4P8/faPsqg8fvgNebwrG2AkAAA==");
		solicitud.setHashArchivo("beaa28db28a83bbc943be610638c20e2ab41a90928fea8f9357a4aef24af7e59");
		IRespuesta respuesta = servicio.recepcion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	/*
	 * VALIDACION RECEPCION INDIVIDUAL
	 */
	@Test
	public void validacionRecepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(22);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(1);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(12633L);
		IRespuesta respuesta = servicio.validacionRecepcion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	/*
	 * RECEPCION PAQUETE
	 */
	@Test
	public void recepcionPaquete() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(22);
		solicitud.setCodigoModalidad(22);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(21);
		solicitud.setCodigoSucursal(1);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("e6dbdb4e683edf029be42e23a5fe582e509890b4e1d0fea97fba8b5ed24cff55");
		solicitud.setArchivo(
				"H4sICENS0FwEAGFyY2hpdm8gLSBwYXF1ZXRlIHZhbGlkby50YXIA7VZRc5pAEM5P6bvTDiCm8qhBT6wQi8DJvXGQEfAgtCoov77fkabRxk47006nD70ZBnPs7u3u9+13iT7HaVY/vnn7poo+HR72D2/qSGTJ47tjIW7+0FJURXn//v2N8rS+f+u375Ubta/f6spAeT+4hX1fU/s3b5Sbv7AOu330+Ub5K2f9g2tpGjqnzSYpglOsiZrnSmav9MbKxoIXTs2IOLBWyYLAJQtvky1b/cOSsDTsu2pcTE2uqU2ydtO4ELchdVN37SghHWzDtbV5IOqOl/btw8yplqv5YzJzm/tsWCf9pL8o43ZRGCd2Gp5sc3RcqKnjTZ08IsE+XKkVL7s4OVshDvzvi0HtFdM9WztNSB3ha84jo2rqESMP127FNd2wim95TTkJ8mQ2r3kh8tAP2oRMD4xMT4sybdmd1bvLRhvLPOYhnYtQC06hp3+w7rCHZ0kGVTIJ9tHaqeNcP9qm1TjtqMW7Xd4Zh2jtTjkVLe9/jXPXxTokVBVxYRDkpyTrebrM7cbx7OFCG6icBieuPOc3OffLExr2kEdue1brmL48Q+59Z8O2y8LSw3yjsWKescLWHc3G3zb68bHveOgInbQOCTW7DQRiPPlsHjdf6xqGmrGNaFL7/SBHfm1Im57tNfWV/fOzt9Fa9siRPe65/SDlZC7i0q2tGas4OaZxOa8ZOCTzx7nn9hc1cM2tmGZ8TOhA4SoTvHRTa5a2kVcdItoYVumeEupni/YcC4ZYaepStXripw6+TAYLb6Ti0QJzcrxHD+5zq1nko8Y2m5oVAXg07TCKqHE47wEvjH1YzoVfTG+5NvA48pT1rkrUlY0/MuT+kDf1D+yu1eNH63HtEtlvVdZUr6gr3xX4tl3mPvDv5qjkqlvFxJggluTDISHGnBH5FltgeIVL57ZDYDVC7XFjt3i3yLEM9sj34uzv8N7H5Ci++j/PUcVNJcNsqHK2Lnv9NU/F2UWdj98Dr5SP/twErs/1mpwIeZa4xi2PBgrsap9MS97qxzM/h61dxDc+hrA7932uI8B8RkWghKuxHmvbW8zuDrqhxGUgrGx06UMN2T+f992Um7pq3+EB9pf757WpNXJGr4I27Addr37Oved+wLcIttAI7d7cvuD5sv/iM+swNkOqZhEddvMFfBv0NOV0XvH2VU6o24DWHR3wTDAyQc9HBzw45+r3c57s2BpYFG663B7Fw2p833webaSG3Gdjm/ftDbAHLkZrEVdYqIevA9TrpKFWmjtN34B/GaP6Jp7NZe80Bt2WsxwXQRsTYHkab9lqvEMsaXti1Hl8+u1vOEHcGbSVAjcqf2834MEh7kM7qVvLGAs5SyQYMDq47NM3v2HPFa4Nnqjwk/dIfYVXFxz3tSAHxqdlPjnDwq1DnMs6bXEEdBba/XQeYr/S+SX6gTthx4l/3k+pz1VSyHl0p7IW3G9VqE16NnTG9iYNYv3I5qrWYYa3CXVkThW0S8Z4mYnX319zTx2fYKvKelCvnPsXvQZmbCZ7MbzQa9yROfrYhJ1eDnCWk3bYk8nGBpa4v04ccVg3C3Pw9bVWh+gjMEwZZkvqqsz5Ze8Cx0O3N1EF8gXGgwyzJ+S9IDHlT31yGHWr77jbxFKnqRHARgnX3WwcMcdHOcfgo5D5BxR3Mel4cu67xywowFzE0McO89M1zbjwAd/mr/QCnMue5qu5pr8eQ17MQ065rThe2P/ou1ILv//+4W4lMXu+5wV4uO1FsInoNAvbyYVer+S+942bwAs5kaPUU/D5l/636t38X//X//V//eb6AgZDXREAEAAA");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	/*
	 * VALIDACION RECEPCION PAQUETE
	 */
	@Test
	public void validacionRecepcionPaquete() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(23);
		solicitud.setCodigoModalidad(23);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(33);
		solicitud.setCodigoSucursal(1);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(136L);
		IRespuesta respuesta = servicio.validacionRecepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	/*
	 * ANULACION
	 */
	@Test
	public void anulacion() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(23);
		solicitud.setCodigoEmision(13);
		solicitud.setCodigoModalidad(23);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(13);
		solicitud.setCodigoDocumentoSector(13);
		solicitud.setCodigoSucursal(1);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(3049L);
		solicitud.setCuf("abc123456");
		solicitud.setCodigoMotivo(1);
		IRespuesta respuesta = servicio.anulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	/*
	 * VALIDACION ANULACION
	 */
	@Test
	public void validacionAnulacion() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(25);
		solicitud.setCodigoEmision(51);
		solicitud.setCodigoModalidad(25);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(15);
		solicitud.setCodigoDocumentoSector(15);
		solicitud.setCodigoSucursal(1);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(3049L);
		solicitud.setCuf("abc123456");
		solicitud.setCodigoRecepcion(274L);
		solicitud.setCodigoMotivo(1);
		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}
}
