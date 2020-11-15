package bo.gob.sin.sre.fac.feel.fcmb.test;

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
import bo.gob.sin.sre.fac.feel.fcmb.service.IServicioFacturaElectronicaComercializacionHidrocarburo;

public class TestElectronicaComercializacionHidrocarburo {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39112/FacturaComputarizadaEstandar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaComputarizadaEstandar";
	/*
	 * RECEPCION INDIVIDUAL
	 * */
	@Test
	public void recepcionIndivual() throws Exception{
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE,NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaComercializacionHidrocarburo servicio = servicioSoap.getPort(IServicioFacturaElectronicaComercializacionHidrocarburo.class);
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
		solicitud.setHashArchivo("4477c928a5a4f5f2dd9800b6bfed0bd4ceef19d51b4324750339ce47cc5ef128");
		solicitud.setArchivo("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");
		IRespuesta respuesta = servicio.recepcion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
	
	/*
	 * VALIDACION RECEPCION INDIVIDUAL
	 * */
	@Test
	public void validacionRecepcionIndivual() throws Exception{
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE,NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaComercializacionHidrocarburo servicio = servicioSoap.getPort(IServicioFacturaElectronicaComercializacionHidrocarburo.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
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
		solicitud.setCodigoRecepcion(12482L);
		IRespuesta respuesta = servicio.validacionRecepcion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
	
	/*
	 * RECEPCION PAQUETE
	 * */
	@Test
	public void recepcionPaquete() throws Exception{
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE,NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaComercializacionHidrocarburo servicio = servicioSoap.getPort(IServicioFacturaElectronicaComercializacionHidrocarburo.class);
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
		solicitud.setHashArchivo("c032922f85e0ae37c230039953cef8ce3ccd091a48345d9563c8b6e85b4f00c8");
		solicitud.setArchivo("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");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
	
	/*
	 * VALIDACION RECEPCION PAQUETE
	 * */
	@Test
	public void validacionRecepcionPaquete() throws Exception{
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE,NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaComercializacionHidrocarburo servicio = servicioSoap.getPort(IServicioFacturaElectronicaComercializacionHidrocarburo.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
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
		IRespuesta respuesta = servicio.validacionRecepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
	
	/*
	 * ANULACION
	 * */
	@Test
	public void anulacion() throws Exception{
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE,NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaComercializacionHidrocarburo servicio = servicioSoap.getPort(IServicioFacturaElectronicaComercializacionHidrocarburo.class);
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
		IRespuesta respuesta = servicio.anulacion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
	
	/*
	 * VALIDACION ANULACION
	 * */
	@Test
	public void validacionAnulacion() throws Exception{
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE,NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaComercializacionHidrocarburo servicio = servicioSoap.getPort(IServicioFacturaElectronicaComercializacionHidrocarburo.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
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
		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
}
