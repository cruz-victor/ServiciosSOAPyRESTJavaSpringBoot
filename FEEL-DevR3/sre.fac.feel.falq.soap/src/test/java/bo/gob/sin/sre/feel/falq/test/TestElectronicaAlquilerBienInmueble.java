package bo.gob.sin.sre.feel.falq.test;

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
import bo.gob.sin.sre.feel.falq.service.IServicioFacturaElectronicaAlquilerBienInmueble;

public class TestElectronicaAlquilerBienInmueble {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39166/FacturaElectronicaAlquilerBienInmueble?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaAlquilerBienInmueble";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaAlquilerBienInmueble servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaAlquilerBienInmueble.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(3);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("9c3b33a525d2640223ce3567260fefd82a0e1c90737b52cd41d54820a55b7aee");
		solicitud.setArchivo("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");
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
		IServicioFacturaElectronicaAlquilerBienInmueble servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaAlquilerBienInmueble.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(3);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(144L);
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
		IServicioFacturaElectronicaAlquilerBienInmueble servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaAlquilerBienInmueble.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(3);
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
		IServicioFacturaElectronicaAlquilerBienInmueble servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaAlquilerBienInmueble.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(3);
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
		IServicioFacturaElectronicaAlquilerBienInmueble servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaAlquilerBienInmueble.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(3);
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
		IServicioFacturaElectronicaAlquilerBienInmueble servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaAlquilerBienInmueble.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(3);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(267L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}