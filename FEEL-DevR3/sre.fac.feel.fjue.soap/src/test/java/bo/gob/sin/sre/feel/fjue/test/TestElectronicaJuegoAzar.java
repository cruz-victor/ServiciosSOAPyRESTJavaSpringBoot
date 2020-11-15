package bo.gob.sin.sre.feel.fjue.test;

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
import bo.gob.sin.sre.feel.fjue.service.IServicioFacturaElectronicaJuegoAzar;

public class TestElectronicaJuegoAzar {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39182/FacturaElectronicaJuegoAzar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaJuegoAzar";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaJuegoAzar servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaJuegoAzar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(10);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("58b79b3419f74d3bf0bf6a589f75edddc3e9f248196c897b4a11780bc5bab6c0");
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
		IServicioFacturaElectronicaJuegoAzar servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaJuegoAzar.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(10);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(12482L);
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
		IServicioFacturaElectronicaJuegoAzar servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaJuegoAzar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(10);
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
		IServicioFacturaElectronicaJuegoAzar servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaJuegoAzar.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(10);
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
		IServicioFacturaElectronicaJuegoAzar servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaJuegoAzar.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(10);
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
		IServicioFacturaElectronicaJuegoAzar servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaJuegoAzar.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(10);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(1234L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}