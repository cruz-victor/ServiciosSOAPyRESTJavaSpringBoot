package bo.gob.sin.sre.feel.fart.test;

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
import bo.gob.sin.sre.feel.fart.service.IServicioFacturaElectronicadaArtistaInternacional;

public class TestElectronicaArtistaInternacional {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39194/FacturaElectronicaArtistaInternacional?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaArtistaInternacional";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicadaArtistaInternacional servicio = servicioSoap
				.getPort(IServicioFacturaElectronicadaArtistaInternacional.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("b5860b1044fcfe7d647253d7a47ec7a91e427d5d0b7908118a525300012ca5dc");
		solicitud.setArchivo(
				"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");
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
		IServicioFacturaElectronicadaArtistaInternacional servicio = servicioSoap
				.getPort(IServicioFacturaElectronicadaArtistaInternacional.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(111L);
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
		IServicioFacturaElectronicadaArtistaInternacional servicio = servicioSoap
				.getPort(IServicioFacturaElectronicadaArtistaInternacional.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("");
		solicitud.setArchivo("");
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
		IServicioFacturaElectronicadaArtistaInternacional servicio = servicioSoap
				.getPort(IServicioFacturaElectronicadaArtistaInternacional.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(12482L);
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
		IServicioFacturaElectronicadaArtistaInternacional servicio = servicioSoap
				.getPort(IServicioFacturaElectronicadaArtistaInternacional.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
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
		IServicioFacturaElectronicadaArtistaInternacional servicio = servicioSoap
				.getPort(IServicioFacturaElectronicadaArtistaInternacional.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(11);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(257L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}