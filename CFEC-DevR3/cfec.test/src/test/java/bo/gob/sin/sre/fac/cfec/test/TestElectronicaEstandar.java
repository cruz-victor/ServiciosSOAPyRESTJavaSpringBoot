package bo.gob.sin.sre.fac.cfec.test;

//import static org.junit.Assert.assertTrue;
//
//import java.net.URL;
//import java.util.Date;
//
//import javax.xml.namespace.QName;
//import javax.xml.ws.Service;
//
import org.junit.Test;

/*import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionRecepcion;
import bo.gob.sin.sre.fest.controller.IServicioFacturacion;*/

public class TestElectronicaEstandar {

	/*
	 * RECEPCION INDIVIDUAL
	 * */
	@Test
	public void recepcionIndivual() throws Exception{
		/*URL urlWsdl = new URL(ConfiguracionRutas.E_ESTANDAR_URL);
		QName nombreServicio = new QName(ConfiguracionRutas.E_ESTANDAR_TARGET_NAME_SPACE,ConfiguracionRutas.NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturacion servicio = servicioSoap.getPort(IServicioFacturacion.class);
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
		solicitud.setArchivo("4sICOuNnlwEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXIgLSBjb3BpYS54bWwAlVbJ2ppAEHwgL4CS/BwlLEJkVGS/MZCP3ZCoIDx9ajBGTcx28ENxuqe7qrpmtoq0oH6fpY03JELd0ZIrrP2iNwq5pg3pIr0+RyNXpIHdrJ2s2I4L8qG8NKFPuDQw850gnZKVx4WB2X5qtCp0vTHVtXOka4Oxyk9UF8fNIR9jhyvilc0lyuduPU/n6SDOrUHskibprHLZW3spdNyLEwp5TX31FPvilCcU3AJ5EN+eKSfm1PfGRNfKyCNlrHun0L10oaBxsS+dt3vzn+pao57ogzFTdp8zQ7mUoW/WoeANoYPeimVmfFi+0abmbJ9vE0EatqXao8a5pVh4Wm9rQWxT1TvFAemS8iHm4J2iwOzsRivT1ZTvYilLtp5H3QPlojyc23zSqGxvFpNtdcJHpZhvFHWwRnWISjxLa7FxzGL7QSpTP2RrzWlds5uh/nrjoB+H1JaSjZGyG/Adv3c8KUOB+MYCeFRW42Lfa8x9r4nPA+UJH84Z39pxW7L6XrzPuI+IYfhUccDwIS0VFrMd/qO6m+0PXk4LeQfsjnSQzf1+yROlZ3mGyCflxMc9R0kFu40EaZf6Ikf5qKYHO7/xGvu9ZBzsIfXB9Ye3h3oj7JvnjIerNheDpaji2lmOa8cQPUWdb8pdvymXwrrMho2D/RsPutA06tfjTzWcqcAXSePtw6DtaE2gmzqninjrgYQ+n9OmYnx11DeHyDPzT410dgUJ/WjHF/24cSB3ts544llP3d632bMFTxV084Ct3Sa6pFKBMC2cU10yI5096yr0Hzm6acXuwMUpYniNiwtxdtBEcoEmzmyvpHnM9fbEMQ3kY/S9nr9j/L0+jhzjKZc7c1Xg4Gvn2OmBkVTFftrthEvLaokeZuT2n+N7HPDoXF07oNYBNd7wIVFgI7+0C7HuAb9bDy7m8wvW5NtyJxBHFaDp+UapestR73PD23nStHWqP84N37H+PF3iQr+fEYfp+OndL2tt3RuB6X/igtjGqzDLAurCHj+/v+MBLnvgCOzMFjgwrjimD8zMKWzqx/5PVGBzYHfwpKMDnUY+w8DtEfP6v3vsMQpqeDPeVZf6017e9F+XGVHccVPILuMwCqwM2mJz+Bm6xpyx+a2LODAy+GpD5+YJPtn278nZWGEODmZt6Jc8GeQSfDCPqpPj5pTM7RZeMhqrKgPX0IYHvOwO66poLx/p3MoS+Gba1GzesU7j8Z4HDtASqZNhAbxQI6vpkbsVvNjXhth/myUrucZMvkPffDL38gQ4/TpnT3PjoC+Ozo2Zddfn0wy6gldi3fA9D+MsD+ELEXq7vQM+0Mjl+KRn5I2DaJpd2yfgVzqhD/CijpZj9Bb87XdrXnkd9F2lrFaetMy7SOlC3/ea3ZXZRaupVif2Fy98Wh6Qiwcf3bZqN2tnqgOfX3M8ewD4E8gA7U0+jFjP5t4yW3Uzp9L2O1f8uNvLmlPI/s4jW2BfRQEpgX0fTj77OOPaD4+ynCuWP/zt3jNPG7bGJtBqy7j2Ks1xxr6D309r4RFV/KzjHryz+jzETuc08451iXNrLjPttpSH3gLM/qvZ4aYe+ckfB3mRCNU75DniDsAlB682iuVjDPRmunRuw+/BA/CD1opffeLmN6TGnvUfPQI8PmvoinuqT+fjQ06tBH4C6z1SvZJOZ0tdsrsBO++v94Pfrbnz8DvNWGWIc5/l+FUzrlCf/6jLUfzoFNxUBz4vczzE/6QR8aPnpltDtTVDFWW30jb7Ss1sd5HduI8mvzVx7l3vDndM7hpidyt2XoTgcepfuff8i3bqSHa5N+jDO1+x4mvU++TBydXrOg+xqX71GKtY8Njjld7udzdfYt6ish7T65mafVqRdtOILVVw/12Z8DYjWz/EPGrImnSL+1N99e0XZ50TYc9o/yetMh6fNITftzujpuD+0qfXeXgXon87INhbrMLAmH0DsPluwMgLAAA=");
		IRespuesta respuesta = servicio.recepcion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());*/
	}
	
	/*
	 * VALIDACION RECEPCION INDIVIDUAL
	 * */
	@Test
	public void validacionRecepcionIndivual() throws Exception{
		/*URL urlWsdl = new URL(ConfiguracionRutas.E_ESTANDAR_URL);
		QName nombreServicio = new QName(ConfiguracionRutas.E_ESTANDAR_TARGET_NAME_SPACE,ConfiguracionRutas.NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturacion servicio = servicioSoap.getPort(IServicioFacturacion.class);
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
		IRespuesta respuesta = servicio.validacionRecepcion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());*/
	}
	
	/*
	 * RECEPCION PAQUETE
	 * */
	@Test
	public void recepcionPaquete() throws Exception{
		/*URL urlWsdl = new URL(ConfiguracionRutas.E_ESTANDAR_URL);
		QName nombreServicio = new QName(ConfiguracionRutas.E_ESTANDAR_TARGET_NAME_SPACE,ConfiguracionRutas.NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturacion servicio = servicioSoap.getPort(IServicioFacturacion.class);
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
		assertTrue(respuesta.isTransaccion());*/
	}
	
	/*
	 * VALIDACION RECEPCION PAQUETE
	 * */
	@Test
	public void validacionRecepcionPaquete() throws Exception{
		/*URL urlWsdl = new URL(ConfiguracionRutas.E_ESTANDAR_URL);
		QName nombreServicio = new QName(ConfiguracionRutas.E_ESTANDAR_TARGET_NAME_SPACE,ConfiguracionRutas.NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturacion servicio = servicioSoap.getPort(IServicioFacturacion.class);
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
		assertTrue(respuesta.isTransaccion());*/
	}
	
	/*
	 * ANULACION
	 * */
	@Test
	public void anulacion() throws Exception{
		/*URL urlWsdl = new URL(ConfiguracionRutas.E_ESTANDAR_URL);
		QName nombreServicio = new QName(ConfiguracionRutas.E_ESTANDAR_TARGET_NAME_SPACE,ConfiguracionRutas.NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturacion servicio = servicioSoap.getPort(IServicioFacturacion.class);
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
		assertTrue(respuesta.isTransaccion());*/
	}
	
	/*
	 * VALIDACION ANULACION
	 * */
	@Test
	public void validacionAnulacion() throws Exception{
		/*URL urlWsdl = new URL(ConfiguracionRutas.E_ESTANDAR_URL);
		QName nombreServicio = new QName(ConfiguracionRutas.E_ESTANDAR_TARGET_NAME_SPACE,ConfiguracionRutas.NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturacion servicio = servicioSoap.getPort(IServicioFacturacion.class);
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
		assertTrue(respuesta.isTransaccion());*/
	}
}
