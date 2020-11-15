package bo.gob.sin.sre.feel.fhot.test;

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
import bo.gob.sin.sre.feel.fhot.service.IServicioFacturaElectronicadaHotel;

public class TestElectronicaElectronicadaHotel {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39178/FacturaElectronicaHotel?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaHotel";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicadaHotel servicio = servicioSoap.getPort(IServicioFacturaElectronicadaHotel.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(8);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("c2bf0086e74e4db8966155d801aa7fd63f231844466601337f74ea4b434ccc40");
		solicitud.setArchivo(
				"H4sICIjGr1wEAGZhY3R1cmFFbGVjdHJvbmljYUhvdGVsRmlybWFkYS54bWwArVnZlrI4EH6guWHRnt+LuWiFIChRAkkgdyyeRghItxvw9FPRtpfZZ85ceDiELLV89VWl3FqzScavL0XDhtyQl6zS9n44ubr7ucwafBGOPItR2zNGnHX0st+Ok9XWEWViEj1vEMocVhVL75I1skpoeSkcdnSX5SlzpuOmLcc00vbpkmi5dbiszcIshqnpD9NL3uQXv3q++uEsiWgfJUYpM26fUj4dCwedE4PuYR9Y350zbVpmnI25gyrBcJU67JTQ/pIYSEv57LwNvSbhWCtiryS8lyDbkBnTDr67mUlktphOciPYb18O3tbBZdKwSsBc9b6I+nMaE5RxOWam+5MfPQ++lV/9EZ7jVemlEa53uTEbHvMLrsu8mTmPM7eVfYV11+1i9rtv72uqgic/JZGr+aM9qF9SBYMf+f37utv3xUut5NNFE/yER7bwaaBtbOIQm9k+JQvfokZQuRM8+qNfkQWOnsdg9H+sjdua9/U3n7WZjvXEVD5Fx231DHN+P/4uW53GnkwM3GXG5KdgmLwofTNHllnz44U36JrEpHTt6ZANz6MfuT3I+3XNb86d61lLLqxh58KxwZ5X8BPM58WFLtXYjAs+1ZJosloE9Q/RMPAnutn/5svKBVvWJ98KTn6EqB+9PIEv4Jec/SiZwtmN4PiQUHZK46/nTy8Z9wbBvHLXzM7UmFUpBx3lXAImBxfNy7w5ybxSPp2dktaTtEFPgJMoAz0Sfv2NHqTLnZkN3/SM3+T2hKOesk44+OeLXsyR10wjF7DrSemWabJWz5STUlh3PbMWvsXehTjK17pUNrrhpno2fcuHp/LjVJ01fNtrvK+Hs06508u7LD9edkvcbZppl1naHuJPF6H7k8JRZujXzPm+9iFnYPSdGhfRNKAOGlKDAdbt/r6OdMKYWeB3JZt84DZ56GGXuojxVXAi83EywJo//vZxJnq3FXET7nUFxK7yr4j9Gyb+4vvDD2XWgkwOqiOOppnp/cG5ernjs+GP17Dzbc0wh9ivnyCOj8ATWt4y6VYTsPUfzB2/YdLLmgL2xpfQmQH3MJC1k5/4dAGfmEI8PPnWs/rBngJioixDPm3zho2ZVl5ycy7hjFcRffcFnKmBzS/ghzZTen36AIuYAAZnQQLzfsM5NIm9V/gOnBMYOLJHbD3DM9Ax4AiPL58Y0gngvZMQg++20S+3uHRmmsI6Hm+8cwL8azAXePuDD+5jGvgSsH/D0Z/a8L4ncdgIeL1h+n2PD11gLylABly9qNj9Pv6QbXmLNSvh+j7lP274KG4xBT4CbDxwnPGZikMKnF5mNpzdgE+iiXm3/Vf9+u/7Q9zswMfqPXLY1LV/XhwXzxqO/CfXVvlqPmSmkIDfS76fQ+x60nXYJHXkPo3dlwTiBORpUg65Q+VI8K3rEJhTlgnYBPIHjLfW0Zi8gK3qL5wxuksEsTmHfOmBPFITHOY4CNbjUuWzG44dBvPoSwb4yVvwG+wBa0bgCwPO7DLTf7nHl+Kpvts1qIaxM9jzKGKI14Y88oyemwz8Li/bpjuBPYHD6x9rk40FRwPY9hvHfeMZHUPeBP2ryeTTT9/4L4LzNciRag+w9/c8ugV7KAxlDr2fAbqBzYx7nDLYT/GzrMBfGq6C3r/lpD+Z85tYoVBfiCWrlAwpn/zkV8nkvv4j5wwgr67kp4Y8/waDAcRNXSjZx2kQhZrKYeOaepHS8/dzPs/+5PabnwFX93yj1n9ZS9N4DhyB9jcc3Gqodx65xYUH2L6vu8X6MD988F2+GTPIR7+Z9zsuI5CLgZv679xHuuTBe5BX3/lQYaxOwj+JV/P7vIed86UnldyMT0GuG04e/HAFHlO8zDKlf6xi8UM3FWdHQb9gyHzIA7nWQFfgVE19/31eFKo2q+753oZ6hhrYSoBXbZDxX6z9jZ8TqCG2lW9uvuRmsHn34PW18cAos7/G6P/IexBn3p2frHdeMqHekp/8qmonxd8ZcBK8/6AG4LdBqlaUj7oZzjoUS3Ld7H9cCqiZ120+rhvIc8OPAfa8rsfn6dpUcwnUTHkFMq22CHdCcZ4N9XoDcV71FmDlVpffOeNeK6u8kzqz2rXREWw2AGYP2V/U6Ay5jzo9JNQ/7Rz9uOZ+j53J6TYeqZqQ7reA4wBy+nvdDZzYlYUDsVDrYPPyIqwPGctiyQZB38cX83nmFHCWBLm0mduUWrF8floPM5AkPxejr3Jzux4h71r2BerCi5JBLGGvQT+pvO2a3piE+pg69oCj5CbLV7s+dL7Zu2ENcJa620gXsTAE3e/2IwPYaxRg5yz2lf0o3G/OuSFANu0l4H0LZ3Ug8+nf+gewZAjeX1QNsY4/bfBes3095z+eO6N0fzu7X+seCgZd3elOyWhr2V67jfsW+CzEsfJ3ALXkDftL/2YrtvSAa3AD55yUjdb672xhQyzJ3CRYKLwYwT+U612m8Vm724OdkwF/8xMB/obcpn310WOMNehYcPpTrnUiCk/LtMWbHO5BTJPnrBKNrx2vdEQ68FVfGEXKTIRSC11SVPIQBUD22o+1prgB8h8SZbZk8q4f5GK474JfKlWXgQ8i0O8M+R9qPnH5bVw+5FgAE++cesBtAfHrDozqF18KhKk+CTTp+ZKEgV4caNMtw1FMNxyb0bLjgWlP/JFIrKMt+GoitNLOONGCsTzkVMxDXp4LCRpU+EqWh8Y1S3tz7Vxas56NpU1ot82M2ROOgZsqzMMYgFvj16D1QgJzC8j6GZUD3I0d3PqgLWqwoVuMulBDJH2isTmlBfHjQnIk0pTrFLgyxKyovD2eEOu4ynhxIg1pfau+BuPcCpdlwHhfM9MHDi5CrE1fM6PsqJQ9Z8TGcB+i4N/dkiSFXXacQv1WzzTedjaJ53bIymsy0pEidCmMmcRtt13DrXdnBz8vmq5kiLRFO1+sDLwAzCxB6ilpTmzjnF7hnRALWazpMW/caWQEY74UZxKLfdhgM11656ApOY7dCauoTlRXgIopbz3Tj4pVOJJrtswfdnwKNffNj9kGcsKByWJNTXIqGuDg8GQRp1+ly+ItpPo6rdyRmqKGfsdsAdj8QwxU/Rpqrg+ccIteN5QA19nqWwJ38mnwWS/CuMKN7oWUvIHVrAByrNCQGVJvLgAzNMKcoaLPbFsPKDIJQiKsTxdey0PR4E3AECK1R4K48Hc1IxG1NaLZPaNsDnZyOStCyC0xpsFDX8Jsto1gr/d5ljBBCsZChjAL49La1WgVNMWG1d4cfDlPtS6IqKtxQBqJGCa2NKOmQ76tU1iLWTNLCCJeoel2GBebtKbXB24iRloY55lZtFyySWB287D22kiKRTASGvDTJqx1kpuFRZm0mUSEaMwE/FLQp4Fzr1/0iRk/TkFlHtYd3VHPpUysyOIdNxIzwgtOmNgyC61CSZ4IYzio5TJo5Ug02BvsSinCQdu97UB+kBentT4hvINxD2re7g36Aqt0mHm53s1hvh3QDjNKUEF1q9A+cGNREy2pJve+RmBfWC9lkxjadCdlErbz/S5iZ87KVdqgmNSohLgzBSr4riHLTdz5EOMG5zXwk/cUaHgOe/3Ovw87Kj8Hys+UtEJjATytAPSBO5B6XwT05O7aYpXX4sArsaEcXSO76wrmCW6XcWSQJKnK5WqU+q7yTLI4+UTSK5gCfuK8q2fHDzs27BTS4hUjqMMN4nGKjlgjNgFMB9V8ypoyZYCLbHFaMecwZdSrRTSfUD0ZxOKHQRg2IlscdlHBCp1ti8XpEgL/AJ8hzrp1QFknls8POwI2IXJHdgw4fcONvqKUpYJ2p5X5/BaYZUwbfwgNWRX7k8erUvgmmWysYpmZLMl0dsjqA2C5jBIukkKz34RZm6E1D3iLRr92L6txfnzYMa9Lj7TeU+jor3AfMDaR2Oeye0rMeRUBxtK6q/1KQm04RVksu5Xu+YCpS65NEcTWlFdUK1q84s6h57RzQguZ2IGrVCxaZk9DytmBh+92rAH3EY5CiePc1hmgM8nMuYgikqy0g8ZiMRWGvmW2hFps9kTsWt/YxRmwWu4WM405npdy9sSdU8vpcQjjOUsAJ4XGQDmk4gYl5gce7eAWP+6V2d6cxYWat1hDvEX2Ld5cGpWAIcC+5gXAF2uh3+fT2rOiuPA4+3v8Puz4pzim04UwTn3qnDoyStMHWYBbHOCubXCLPzTPG4wzTQDnuCNvNPDBvKeaSFYjHiBcZR6hja9h7j/yQeWt8DAbheG95g5x0tEdobs8pcviwJCHBfTMwkab+suio5EnqA0cY0ydokLOxinsbPQwnHnhvDsW2uQt1Eu4qyDV67wKk8254xkZo+92PFyC5jjsKsgnkTgCF7/SWohAIgtH9ZU45SvWi3E1snXCIZcPJ7bWTk3oiFXqlD2c2xFN6JnZHSPoRYRtmVA9mEQNIzknBHJ0u5Pd/oMfq5ee2iRaG+yYGYhlMT6yZQ2ZUTzliDUb+3BNHXiTRIfcUG2cWeqPniWWMtyx+SvUzwOH/mXSEpfpsg60/rqLyyUZRcnsDvhfbPzFA49ySDXa72zCaYulb4qELGbAq7ik9gnqLrblyIsAMxTy75uwvbddaxsp8k4B4GmH5htCwVMxc5MxGcGvJ7yfbbgkfR6/GNC7bFn1gUeRa/pG2PZsG6ia6kX3LWlBbf24o2m32grdxiO4F72q/kHUoBOM9+vKPW+s4Lqu7N4fqbYJ1VgNPV6tgj5vL6p8wE1iQt+3x6Or4rjxG7gnvr/D+BRXtuZb8GwCuGvl0AOjMD/X7us8iSuooqtkUOt8qBfXiHnE0n26uMJZ/hnv1Zn22b+d/Txzx3kF+4wb1ZcfbeixQ18WsOgPvRVVehjpwOG13Lp2AU/sBeGcEjrdqrG17c/ginn09xN9XQVnuM/CfrCHpXr+gep93WoDarJ9Cnfqwlb/adCPmiGMMdwd2UChdlB968/aQo453DHz+j4/GPVFNPTbLUU25JPF2sYbcGoNfZNzoNkPP3g53DvBF5sE+oYfe6nYayT0zlSP0INacqJt4N69sV6UnD1WfRXrZYBe3eQh70OeCPrX0O9R/4H89owIfrf+uaqZvtRDt/cQ6jrohzbZ+FlnJ7H674iqu89f/29jTX75FYDnvyegGgAA");
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
		IServicioFacturaElectronicadaHotel servicio = servicioSoap.getPort(IServicioFacturaElectronicadaHotel.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(8);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(137L);
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
		IServicioFacturaElectronicadaHotel servicio = servicioSoap.getPort(IServicioFacturaElectronicadaHotel.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(8);
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
		IServicioFacturaElectronicadaHotel servicio = servicioSoap.getPort(IServicioFacturaElectronicadaHotel.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(8);
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
		IServicioFacturaElectronicadaHotel servicio = servicioSoap.getPort(IServicioFacturaElectronicadaHotel.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(8);
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
		IServicioFacturaElectronicadaHotel servicio = servicioSoap.getPort(IServicioFacturaElectronicadaHotel.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(8);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(264L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}