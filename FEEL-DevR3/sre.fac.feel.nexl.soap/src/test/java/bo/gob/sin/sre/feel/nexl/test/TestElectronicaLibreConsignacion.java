package bo.gob.sin.sre.feel.nexl.test;

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
import bo.gob.sin.sre.feel.nexl.service.IServicioNotaFiscalElectronicaLibreConsignacion;

public class TestElectronicaLibreConsignacion {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39184/NotaFiscalElectronicaLibreConsignacion?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/NotaFiscalElectronicaLibreConsignacion";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioNotaFiscalElectronicaLibreConsignacion servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaLibreConsignacion.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(13);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("1e0b1b423ac2a59053111a8988e01c7d69f2a7dc535708bea7d646e2709acf40");
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
		IServicioNotaFiscalElectronicaLibreConsignacion servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaLibreConsignacion.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(13);
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
		IServicioNotaFiscalElectronicaLibreConsignacion servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaLibreConsignacion.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(13);
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
		IServicioNotaFiscalElectronicaLibreConsignacion servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaLibreConsignacion.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(13);
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
		IServicioNotaFiscalElectronicaLibreConsignacion servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaLibreConsignacion.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(13);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(1234L);
		solicitud.setCuf("asdf");
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
		IServicioNotaFiscalElectronicaLibreConsignacion servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaLibreConsignacion.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(13);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(1234L);
		solicitud.setCuf("asdf");
		solicitud.setCodigoRecepcion(1234L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}