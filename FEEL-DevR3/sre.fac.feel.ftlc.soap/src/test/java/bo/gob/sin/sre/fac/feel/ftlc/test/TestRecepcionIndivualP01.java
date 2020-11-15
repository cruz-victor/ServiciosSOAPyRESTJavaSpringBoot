package bo.gob.sin.sre.fac.feel.ftlc.test;

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
import bo.gob.sin.sre.fac.feel.ftlc.service.IServicioFacturaElectronicaTelecomunicacion;

public class TestRecepcionIndivualP01 {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39113/FacturaElectronicaTelecomunicacion?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaTelecomunicacion";
	String rutaArchivoEntrada = "D:\\PruebaServicio\\archivo.xml";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual02() throws Exception {
		// Nota archivos recepcionIndividual y documentoFiscal es para electronica
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaTelecomunicacion servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaTelecomunicacion.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("FC809F97");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("2E3B2E8DE90CB3F848ADC6826DB3AF61");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"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");
		solicitud.setHashArchivo("3fc071876890c923ce4c4b757a09ce83848129cf784f748c816a93714731ac8a");
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
		IServicioFacturaElectronicaTelecomunicacion servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaTelecomunicacion.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E0DC5BF");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("56ECDA1C");
		solicitud.setCufd("A08466C8A96AE9CBA8949470D1DE430A");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(12665L);
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
		IServicioFacturaElectronicaTelecomunicacion servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaTelecomunicacion.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E0DC5BF");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("56ECDA1C");
		solicitud.setCufd("A08466C8A96AE9CBA8949470D1DE430A");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("d6f3535109287bdc5d0652d68ba6fb81ba7ae357c40c1b2665a1be403c06922c");
		solicitud.setArchivo(
				"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");
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
		IServicioFacturaElectronicaTelecomunicacion servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaTelecomunicacion.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E0DC5BF");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("56ECDA1C");
		solicitud.setCufd("A08466C8A96AE9CBA8949470D1DE430A");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(137L);
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
		IServicioFacturaElectronicaTelecomunicacion servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaTelecomunicacion.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
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
		IServicioFacturaElectronicaTelecomunicacion servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaTelecomunicacion.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("98A710AE");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("9583EFE");
		solicitud.setCufd("b8b86fbbfc87c8b30ad75565a34f635e");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
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