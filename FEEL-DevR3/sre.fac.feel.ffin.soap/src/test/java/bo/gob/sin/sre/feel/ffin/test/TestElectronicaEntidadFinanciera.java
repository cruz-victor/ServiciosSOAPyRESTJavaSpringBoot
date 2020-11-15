package bo.gob.sin.sre.feel.ffin.test;

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
import bo.gob.sin.sre.feel.ffin.service.IServicioFacturaElectronicaEntidadFinanciera;

public class TestElectronicaEntidadFinanciera {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39176/FacturaElectronicaEntidadFinanciera?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaElectronicaEntidadFinanciera";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaElectronicaEntidadFinanciera servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaEntidadFinanciera.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(7);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("f028073eff98a9d666b8441b55471419a49c50ab298861c272c774c8044e156e");
		solicitud.setArchivo(
				"H4sICLedr1wEAGZhY3R1cmFFbGVjdHJvbmljYUVudGlkYWRGaW5hbmNpZXJhRmlybWFkby54bWwArVlXc+o4FP5B++IC2fgxxpbBIIFlFew3FwZjy8Qbisuv3yOS3GTbbJmdublMhHTqd2p2njPLZX8sWzEWlrrntXHC8axfnVyVt+SeBuqWTsZJCBps2PG0m2brXZBWiU3NokUoD0RdLsN73qo64eJWBqpJJA0yOa/gfZfuw2q1rK55MJ+252rKmHHKltQovNf7xi7tcpzbeJzfi7a44/qlx7GTMD6wxKpULv0r0JnKAN0Si5+ADrzvbrkBtKWYigDVqSB1Fohrwod7YiEjk85tF4dtIolRAm8qBwWyjrk17+B7lJ9plwaooa26JXJeZ1KMSTyfFVZ02h1f16vFyzPcO6WSqKL1f1qcXo76DPQzqDS7wnLGXe33IKuNPQyf+HkDtEtfXLM9uRf1DGjAG2+4ldIEGk7wKcvjHXvpdwvnD98B76N+twuImdbzCtfRhNkKfsIT8VYDnvwBey/AC77/4lGXMm12dWFGNfKJV/m0oYh6yI/qF5M0q3HrFROeVkZU8x4beALejzdf/B4+PucmMRNbYwBddrXm88fzrze0K1pRJw+8zL3NyUW5VJdMols+ulmyd6sy8I/sHN5XHh5w7QO972++5M/hPLWcqJRzIzdTBf7RdvrG3zXh7C5aja0vf6RAKwuQ5js9fF6vwLbNFXvRFTPEMTs+4ekFfpIbZskc9G7Bp68Jf/ipy63ZN9861+QcKt6iJ8AJyy3SJbL/iQfCSPerIw/QmFli1H7LLfMEesTJvrvnitxBnyr3/qAPz/bunQZOrf2s5Y8lVZ/Y29X8m35gl8DxgaeZS62jE6bBZxxFXzKexRVi6Tc0/wSHmsaYG/Se2HBf23T6LpvZ58HH+egC5psnwPUF4sUozkKt6tlDrnzvXtIPHn/AiUEu2UNO/hPY2CYsMUj9MmDWgy2cJpPlPbKGTvNI2R/sQtI9BTpOlMA98NmXHQxTge4N+LtKrec/6C20D1phJGwG/DjEQ9TDpwH8ezIVs28xxZN9+Avw+RZT5v2BocAxtF/J9IjBK/jaAPvfIb9c/vzu4BaAi7ylFeDsYY/veeOvbfgvaHz5510eg6oCsKDt93f0aSAm8PNf+AnutKIBe1lbr4E3vz//wdfQWI0sdE1adX/Env3ApJdI85TJb76QjsYnz23Qxf9BB/D38nudCeii0m/xCribg05wf47TfXNkl3W/8iITT69HCvWngBoCNQjeOOMqoCpp+THdV6+ZDLvi5NaAAcjtqc7htY73dI+PacCPkANAxqErAzgvtlN+cu/w/gv/Nj4We7AbfF+AvNmeKrhzSeA90Ooh/z5iUtMrY/eeQh0EWpOWYbWEGnDWPNW9GF1V2K56xHugnqDGwZn2xaAOEA/fdS33xAQMdvk0/yWX6Aq+7gBvZmGLqmjVn/jqm7wm0fVK1xEb3nzEzW/yA4M8BnqtHvz0nUSGIJcYP3z6DPlD++DyLf50ve7K9lGbUWI98hjo5f9EvMSAOqPj4S/ufOn1Gd8cfJUuRa1lyeTsJ1wnM/ybmuGOILep9eCWun3p+8fvd8olG6ZrBL5GCv8pja/3j9gYAa8PDMBbnSs0djroFR54ABu9+w5yqM4boBf4ltRg9/73tQf8ApiiVerNBrj37fcfd8z83R4klTp3+8fDknTbdt7lnnGCvsdM49VP8PZ3975sBrIpzVfIucboCPH0mX96wKPWQ+jeQuPlf88FS8Ch+shvddFjr78DBk8fefAh47t9KGB6ULr/2SECeJtX5VKMaexCbzjciulverbFc7+NHX23gR7yvBoBm/XAACs3sEeYt6mW6RlyzO2jD7tk+w56BPBHo/N+dU8XrpsHJdBURhYYzqqtjHL58rQZHeBY3MoJ33I7PG9MGm+mFeji33kjvM0eesuFWWNGbxv2OO+hVzIftjFIBbX9lknyHrN72gF/XYNecyv6ISOcm+CLz/NjJIcz8OpArivY+rVc0n57er6XIMnmXEyb1hnT8XmEvDdsphdjY+v+lkIfUlwhR4LdCPRL/rWwKujjuPWQxfxmV/6hszcDe4dK5xzwcQ32FrxpnNXpYT8O/fWtsFKwiTlp+4llWOVn0oJs15WPLpAXxkzLzP6lfyxxg1x7ySGfpQtz+rIBP+3q/v6dz3/lK9Dqs5+PKcfXQ2BeNhIPJJhdH+fsBXpbXgsL/O1Dz/Heb0zvtqIjxOKUgp1zyGGA0T/YgkKOgLg2mBSAF6f5p3K9y/Tck8XDHlCXcf0bPxk634qp9L/5yP84Q2mVL4XaqSoUyn9L/fJJgoxbv0yxHwYH/3lIztUvkVTrjFeulKjN/OfZocEQo90QNdwBXXywN9QSKiFHm+m7fnEqUwUY1HMO+L7XefOs62ss5y3khd/GpfiQ49it42WZCU5eiRBnhspeQI+Xcn/a2HzOW8fnAb9jq3hjXojpWZyFrIbMqsZSHIfUqk7psnrK9/SJMhUL9jI/nJwNrxH0csXI7NVImWhhAplR77JmrLpiuwwTazbHy0qtbYTX5spK6zCIPXQ7nLmBFbpQr6zSRizKM+KpvxqJ8mdR7Oy23AwJ94eiDVnCr0EJcxlpHYtNZHfg3S8lpz5dJPXBj35enCHqpIhzsxgPfB7lTNVrgy63iLZRO+DtPoLYI1PGr/XaSoZIOgY7k13sq/EQoA78OQlRUYh3Ls6uUezDOmIV4Krb5bwMJFKZsJJ2ZVf+tu8g9+N5aQ0zmCOXh4WzkGfXL4JrShpV4dNloIrOAD/DNjB60RDETGXjtn/LzuGMKvdensOq4I4H/phi0dw3kyDUen2jS+Qzn08HwMynHQ8+NiBPzKkxD3KjvDA7TBkTV750wYKNlS/dSjR0TBmCWc+15blwdtErxMWfYeD1ObbE/AsnRxN7yk/e+99n6fF+y3UfAxiGGR3ONW5IzJWfGdSLDHQDPLqloRaRLMNY+JNQdF9A30o4gnNKJe82udmF2TKtWYMo5emCc2SzZYc4N13ily4WAkX70o6R2nOY4cQk/E99uaCIGSbQ+rhngecVElxRBv/NIrtz4yY8M8hGkVA8ktc1980FQZUvfG4yTsPSmP9CmUsEoIaJ9C59yuN9SaivbNZ2CH/ipjF5apdEtI6dmp04+OQp4t0iNeYyarAhfPTGmo7wPbEjE6UU/nFeotLoPAH6pI25wN/0EaZ42wpKRdOtxVItYoRk3ESfuAH5IaYaIZkZDXHT8UMjBONhSI1wXoC+nAvQCblw9pTZFRFaXtmRgy9+gfMF5Kx1Bj1l3F7u8Z5kEdynPlozIXy6FySyy+WnHSOTDNQkYVITH+iuIxGmaUuu2z2MW37XJ+fGzJVK4rarYHJ1E1EMpQVUzl1DJ/XEArMpleqIVKdDQ7xIkPAP/j192PHh51D72U+tEnEfdgoGcbl0PP171KBNvOzs+BwGGXq1mEJV4Q/rbC9iKaqVMJxGBqsZHS/zcv86lkb0xvxUYDBFgVyZnyEO/R92VDlXy6yGSsFIEwvl5l7pUz9tc2PVH0T6KpUAXPRvsaK/bIVYQK/XHxCRuI3upBGsbIYgWza22JeC2dFbbqiwaPCMCpXFBkLQ1/WfdtTYFFZh5j5S6ym9xgqc2op1Hl/7tUFmwgxr3Fy7xE7eYvE6k4wYB+9oU8sxZeCILJivYWuz4hZq5bJ011Y5J8ax56gbiilcbMZLn//IY+Eq5ul4aKpr1sKrurFg15AdWtInhoki2a1TbzXXezbYL82z+BozX0D/QbYUYmsrXk2yTKdYVb9goda0OQ7lRN8gf1mpSbdcQSJDzacdAffGxA3ApQ2xAugEeXvJDUMurmuChHWQ5ZWZNIRe7H5oaEhYtcybcJ7sj/cS0VMMMyrkqrfUVBvcqF5IwMke/M1RpeOGSmJ/2pH6j/iJMaKAcWE/7p0ciLfhEW8x8meAIcA+WQB+7NgqP+6Hi8gwbfDz3+N3/LBj+1c4RtuoLd8KWb1lvJiXk6vjTdAGgGYQHX9uBLmfSSeAnBNjs7uSuukLQQK5uEyFEizd+wOriSdq/GHH1zGui3thlafs7MKu8DJi2NJthWtniMZMwrxmdNctg9+FP0rhriN+vdFzMtC6OtPgeWTAMzfVL/my3K6NawI9+ACxzoqgNCNRncrWFJ923BjhGz6/WnnDLeiB3UyFgeQhjSY+xz59zepyX4wXM7bQOUP4TcTOJm2qNm6r1wL4ZlDPSuk85f5wF343k4jwrT9XfI8azsCSdpclP+J6NsPC9fnoqDxwKiHNKUd0jk1sHc6uSOtqjYNKYSvlJYe961TdZY3HyKJzflZR1s5JoSoFNdyIUZmmPsHFUs2g27ISQdeQ/yWrXz7xGBbtFeE95HEVTikjlvSjO+BkSoS7gb5LMbOKQZYngSjPLLrI7G4gbRXnHAGeqog1ApFAmHGALWwikbPkzlTHiz2akQZ2SWbyA49yTwhrqbtjhu6pEgL9TGTpeV61egaAnZXuqx/nHOagrIUZ1te7ZP4T7LzHTX008GIGuyxsEq+5wdl8U/vOavItLBMb14DWCeRgxQgY0n2Pgsny43dsbVkyEO8FPtMGZjy9GzPhvk28xzuo54lN2sTCj3fQN50GEfPIYejlArymTZ1ongPw1J/9LsZ9AnTw1ExYz8jTaoJd34gnfImMmcMbh0ENCNnoLuHTi7l/hMy2ZaY+G7wdD3cbb3Uj8czY1JEF9IDGy0g8bGxhX/foDQTMiG2ndw7bBHrwr55BTQXMk4XSe3C94/zRW4SFTUyYrd/vN9iJmucLm0yXIhJGp8FjtVmlgdnlDXE//QC1bSph38VadE3ZD1oM6HTQB29L2DXBrhX2EUcLe8eZlhP2trC7epnp/cLW+5T3Qx5f7zpDpfftv+fBweeZ1Ds93TP9th/aGFd14PA3jfb5W5+N9A5e6dknbVGt+yvYE1/0nqX4mB/p527ATzu4f4OZ/vH3gV8BXurBNUAZAAA=");
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
		IServicioFacturaElectronicaEntidadFinanciera servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaEntidadFinanciera.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(7);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(324L);
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
		IServicioFacturaElectronicaEntidadFinanciera servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaEntidadFinanciera.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(7);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("5e824096010a619db6f80ebe8565c60d0b59addf7a9225a43acc6b26ad839e35");
		solicitud.setArchivo("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");
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
		IServicioFacturaElectronicaEntidadFinanciera servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaEntidadFinanciera.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(7);
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
		IServicioFacturaElectronicaEntidadFinanciera servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaEntidadFinanciera.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(7);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoMotivo(4);

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
		IServicioFacturaElectronicaEntidadFinanciera servicio = servicioSoap
				.getPort(IServicioFacturaElectronicaEntidadFinanciera.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(7);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(254L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}