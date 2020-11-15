package bo.gob.sin.sre.feel.fedu.test;

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
import bo.gob.sin.sre.feel.fedu.service.IServicioFacturaElectronicaSectorEducativo;

public class TestElectronicaSectorEducativo {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39164/FacturaElectronicaSectorEducativo?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaSectorEducativo";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaSectorEducativo servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaSectorEducativo.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(2);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("4cd75cf30ca065590415ec214eee4221ba5fbb0c826e88810991cd36773131cd");
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
		IServicioFacturaElectronicaSectorEducativo servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaSectorEducativo.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(2);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(394L);
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
		IServicioFacturaElectronicaSectorEducativo servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaSectorEducativo.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(2);
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
		IServicioFacturaElectronicaSectorEducativo servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaSectorEducativo.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(2);
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
		IServicioFacturaElectronicaSectorEducativo servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaSectorEducativo.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(2);
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
		IServicioFacturaElectronicaSectorEducativo servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaSectorEducativo.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(2);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(266L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}