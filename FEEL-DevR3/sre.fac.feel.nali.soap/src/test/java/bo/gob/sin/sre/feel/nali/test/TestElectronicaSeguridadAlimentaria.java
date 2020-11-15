package bo.gob.sin.sre.feel.nali.test;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcionNali;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionRecepcion;
import bo.gob.sin.sre.feel.nali.service.IServicioNotaFiscalElectronicaSeguridadAlimentaria;

public class TestElectronicaSeguridadAlimentaria {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39196/NotaFiscalElectronicaSeguridadAlimentaria?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/NotaFiscalElectronicaSeguridadAlimentaria";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioNotaFiscalElectronicaSeguridadAlimentaria servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaSeguridadAlimentaria.class);
		SolicitudRecepcionNali solicitud = new SolicitudRecepcionNali();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(16);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("573c076f1afc8be04b1b277749b06441fee534a8060f1108233806a728aeb388");
		solicitud.setArchivo(
				"H4sICOvjr1wEAG5vdGFGaXNjYWxFbGVjdHJvbmljYVNlZ3VyaWRhZEFsaW1lbnRhcmlhRmlybWFkYS54bWwAnVlZl6I4FP5B9cIiPfpYKEFQYhFIAnlj8YgQlCkXll8/FyyraqZnP6dtj+nkrt9d+221mKW8PeQ16zNN3tNSOXrBrHWOpkxrfBe2vIlBOTJG7G14OL4Ns82bbdxzG9lJhMuYdyi1WZmv3XtayzJmWAqd9QknhbDQJeGqTE+kyGpZOOvimtrGsDsVQxIqx2RNlGx1vm/1XM97Q/d6457V2d0rX1svWMQh7cJYK2TKrWvCjQF43mKNHoEOvG9uqWIUKWdDZqNSMFwmNrvGtLvHGlISvri9Be4t1UlBajnA2YXwTsY66VPNaOB3KHiuglxVzImZ2vIquKHEkdvEgTHLNP/4djgfnFUHOroy1lgfh7ONs3wdz25JRFDK5ZDqzosXvvbeKmu9Ab6HdrSDQrjaZNqiH2mMb8BmKsjap4ooQAbga43vOm/1Ot9qP//b8lDNY53Vb7XV45XXeSV8akfxSmvwQqv1Vu19/Heg777ZWBW1/4IHtvSor+wsYhOLWR4lS29FNb90ZnjwBq8kSxy+Dv7gAc/pzYOPtqgSnt+pDn6M3CHm7ctE/+fz6T5gohccl5ONK+/mWAz80zUxN+4OagrwhxIH5i6r5wdvsHovbO/f33zIPJ6dUtVUAR93VrNbbo82+cZ3PZ4t+OiX0fbLsKuBxjmmwC/CTarNXgAr3S5QWrxUOo/53W51Hjz44OOsw2UFegrwXVGM/njg+8uHqaYes5oFcdTcU4mBryzSlWGn9qIfeQJ2746F+pirxZ7PRz/dU+72grnFvl7cqLYAfdDl6eMvnUiT2Qsr1TD4ddLBFfb4PWLN/52OzJZtqpDRlxP+UkVW4/cUP6vZSPdT3hxiKau/052/4FUFnn1tR5rpCWhE7p3Yi3K8O9r1u2xpZF7Ex/kzhhLeLpwT6XNOj2/lJNc1szv5pD++/dPP6x90VvAlmd7QFz90OvxNR1/rmlEn8RU/ZaqRRmgLLCIC7xd+DPfeSgds/KSnSrBbBfgohDZ/WR4nvvOnjsxGfVKzERcqDulPn7fl4mkvCjH9K/Apvmyh3ifM2QtlwnpI2y3kHHhzhdyggP/G/Hb5431iswH89Lf2SznwBd8LyI35ZI8v2Z/2COGO4CBPGc92n3H2df7Jdz3haAX4OyZ8/gJ5eYr7fMIMLgCLTTrMfueXJ68POeiY/1IL5K9ZBbYCv7zeHjnnuw06DHpLAfH3Ex2bGeA7eGt4IqoO4WXTOitf9YbzgURYaX9Yhwxyf2JLkBHsAnkUZO5z8M92aVYJ3IEce8y57B17fgBdR/9NcZNEkCuOZgs+qnKOlVT3fkcr602wsytB3z5+/JbpET5Ls42z3RV+VyIw70D/JPjsMPJ1IPc88rhzgBx0gU8DekJNchtho8qx09V7hCH/W4d9YBaO3UFdAluuWbUF/33q+4VVNdPZWL/ubxKbkH/nW50NOQf88Z98+7vYoxrkTZDprbQ0wNYH5n+XF0Ix6e38WZz95Feg8cdaNIe8Mvrv8i22xhrS5I+6hkCuMc+N+gLOnWH8AJ2/uvOTPhT8IdaTHmHCZ0DDglr0yDd/cedvaMzhvdd7ivUV56rZg13UWP+eq4jMNNwnkfmoF9Ica8rgWALOoJcYoJ5MsehCfDzqwBdP9C3PvraTzT7z6ZdP04fuGOKtGXHPuDHhQ0Te6N/bo39RJcj4HQuAVTbKxOA9YGyKv25bUnhjQs0AWVQ29gYPvBy/4uhP8sJhv8bNrjaadKUcoX9SReC8fOYgZbKBOtWE4ZM/4M59xPTqWyzrWI3lR846fuZmsDGR41kKOXnCD8KAP6PI16yHuIG+r7tlwz/0Yct5uwsW490K6ufJ6Z2XZdmF4NcxV7lpLUa/zX2w+6OvesQ1+OCeVmMOL+5iaUJ/lY9xqiS2snDqQsnXrz+2/QI4Zrd88KBPc09blQTbwYEcZ91pxVbbCPrFpVp6Ibltw+m8hX5CdcrZfDvmv3oBfoJvW/6II9IA/7GenFPN/5QRzlXw2fP84PPuBLwakOsK/eE5X5N2d5zfc5Bke8qGbQ21v5/3YNduO7wqW33sWcmQ8OwKPgS7YeiTrWsGPYVXUm2SRf1mV/rQecTaG3KlqNnYS5Rgb0arauEcJ/vRrEa3TBNgE3UY7cfWbpGecA2yXR3omyFP9Mkoc/gf/aMBtmp2SXVTiqU6fNngUZ++8/m/fBlynj16QKh33dvqZcu9Dtuz63QOPSbU1JJp4G8L+o5HPhwetiI9xOMgwM4pxBXg/SdbEMgLECdKyBngZVH9W7keMs2hF5zsAfOLV/7OT8qYfyF3W998ZH2cITHWAPlWdddxZgG5z0npD3lNjVTJcbiWNkENE+w8SyvXDRV1mdXGJRncwlME9FXzBehigb1lphMOOVkVD/0CwYUEDI6zy8sSfEDBT2M9DrhRj7H9u7hkH3IcGs5o98OnrbqXAu2sax3Yrpla7D3VUAnxdvZU8Sup54ZfoXt2IhWtVJfRhoIMIqwJYSG6eDq5xrK5+8GVZGG+jGW+C4a8p8dr6Ncdco94RlaXDe4v6i6MFawJ4ldFNeaYXIpbFo3YyM39iZ3EKqc5FzeBBN5oDAcqUzKWv2W2dc9UsyWlhEyOfk3W4hgz1xVrM0gGsfSqxTaMina/jMu95f+yrMU1YXIbhMVpFzUV1pxxPgvyupolq1xhSCosan7kFL1na7YklUCUEx/TgsTKFQsEeJDkzVfNc0IXW6GhKhnY2QtZTRgdsJTrZAXTkl5Yu7apCHJ32yHX9uxsBIqBMKUdhVzh12zweFMlsnFj6fQshPpddVZc+QqplXa3YhJmg4TaRplGPsTQvOPc2NKq+7GLmIuPVy+D2NqfmrenHWnUGHu70PzT65D2ix1G7j1mnkEl6XeW0eWl1wYrMlBL3VGdaN46W7z5Z4iLP8PAeR5ozPjCyUH1VtKKH73snK9ou6NYgu8bAbM3nI+4wQGVVqKQla+gG2DBzBW59HnuBswamCRRxjsTUwTnhHDabFO1ccFnZVghQqhYUor0cN0gSlUTW7npMYb8KNcDJCMKszwbmPWpLyMIYgFofdzTcp1IxEDfEP6a+XpjBpV7CiEb+UxSn183oPsSowImVKqGlLi5YvxKQhMzO9dDJu7cIjSIckwsqYd1g7wnbiqVCj3HrF7oQm3Y3sIQH81SKAb3K09hFnoPqwbTCOu+igSBP5TmKFeaFQN9RKUuvW/6MJW97xjESdVs2FouA4R4UPlP3ID87MQqxkPV74KqofuKsZC6LlFcIwN9KWWgEzLh7EeiF5iN8vIG7y32K5wvIWdtEpgpg/pyDyKc+HCfWGgTMmaRiGFfz9dPO/oq7oiK3bjEFtDd+MwVosZXiA/BraaNT5WaShkHdVMwRZgxy7pcAyonwPcgf4S2WuVSNpjL477CK59h9yf/Hj/sOPnZHf1sCS1H1EKWr2CT8sVq/A35ZBusGz04uXaCzlooUZFZ3SaJWMBZ4TBlUXHbmZH+YuTRuc8V/z20BPPAFBkyeQrxkFqfdpQphXgsoVKEuAqYNNNVbhFL1KnitHsmzlwywEX7Hkjy646xpbBpu0eYe7V/xxVEZdXZybrSWZSzUPffYUZ2s8qbESaTQEEIerX2accRm0zL1NRCcjOIayDBqTXbpMG13Sh4xlS39KprE+vxewC5nIdY2a8OEIkLldsLltjGBiu5QzVU83VubrTcwMqhpajpssFdbvtLmz7xeHKdgIp+XxXXpIZXZaXFJzfZ17iNFRX5vNmIlWOMOzTYPxhJcA1Ci0H/gXcEYmvHzipeiyGQxa8ekxtSHbp8IO8cSU2oZEclkgmqnnYE3CsDVQCXOsQKoBPkbTlVFL68bjBi2p7n11AlLtSG+74iLg6LNdQqI44O9xyRY8Cl3MviXahy61WyZRxwEoG/KSrGuCEc6087EmuKn8BDBDDO9OnecQHx1k3xFiBrBhgC7OMl4EcPtPzjvrv0FVUHP/8zfvtnPfgrHKOdX+fvGS/eE5oZ+WCO8cZIBUBT8Bh/pg+zJMzNNuScwFObK+x82oxhmy8vQyZZKCKrC0u8YuWzHpz7oMzumZYfk5NZkfoC80ge7ZipJ4gEIcyZsdJcdyH8ZlbPmbnx6fVGTnFHyuJE7HkfAs9Ulb+m63y3Ua4x9NgdxHqY2bnqs+KY1yp72nGruO/e6aylFdWgBzYT6dqcusQfqOFZBHqMPMqg9gYaOiXIe2cB1LOqqIO6OGfAN6G5nfPFj9Tq7sxqZhyqF9QRSSNU0RAsqTdJ/BnXs5nHTIv2Cwk7rIJxdUgRMTzV0/Ynk4my2Hh2IT1NUKitt3go7rz0el8jBj1JP6kNnMkCehVXCVAuhIW9bC1n0G1pMSMbyP88LF+feHSz+oq8CPK4dAcRYo1b/h1wMsTM3ELfJUO1CECWHwwRmmhkmehNh+siSCkCPBV+WDGEbaYGtqd5KmJpGN9D2dAsQjNcwV5IjT/xyCOMoacx30Jl7KlimH0MH+ZgmDPqcQaAvdPYV0/nsK88JjXMm9a4H6bj3qTflgfFW846mD9V2Jnd4MzYltbCGSzN47HulYDWAeQIsx5DHIvSlbjMPn57GvRGHV69wreo8PCq4zBW4b6OV9O7XoSxjutY86Z3jiaOHQuovwjR6wV4DdsyHnl2wHP8bt8Cr42BjjdUA8jUeQPM5iuYjQfv4iuzBa0WIdQAN+zNNXyvAmodILPtQnU861Zv1H3brpwbDmbKFrpBoAc0XsddsbKD2XzqDRjMhXUz7rx3MfS0Xz2DHDLYY2USg6/Hnednb+FmMEvCfuZxv/IWfjW/hINqEoRd/9itwlIthK02aYXNpx+gtg0w0/Zhja4i/KQVAp0G+uBdDvsrETmwqzxo3uowm/bksNnfrV5n3rQDe8r7IY817sNdmcGu9o88KPj8sXMde6bf90Nb5Sr3FP6/op5/67MRRLcrp91cDbsuKhqYq4EHu4y7l+xjhgS6J7g37QBgRmwe+5lxx2O9/AbYSmGTJBkAAA==");
		solicitud.setCodigoAutorizacion(12345L);
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
		IServicioNotaFiscalElectronicaSeguridadAlimentaria servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaSeguridadAlimentaria.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(16);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(162L);
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
		IServicioNotaFiscalElectronicaSeguridadAlimentaria servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaSeguridadAlimentaria.class);
		SolicitudRecepcionNali solicitud = new SolicitudRecepcionNali();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(16);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("");
		solicitud.setArchivo("");
		solicitud.setCodigoAutorizacion(12345L);
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
		IServicioNotaFiscalElectronicaSeguridadAlimentaria servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaSeguridadAlimentaria.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(16);
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
		IServicioNotaFiscalElectronicaSeguridadAlimentaria servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaSeguridadAlimentaria.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(16);
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
		IServicioNotaFiscalElectronicaSeguridadAlimentaria servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaSeguridadAlimentaria.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(3);
		solicitud.setCodigoDocumentoSector(16);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setNumeroDocumentoFiscal(10100L);
		solicitud.setCuf("a24312312b42131100");
		solicitud.setCodigoRecepcion(269L);
		solicitud.setCodigoMotivo(1);

		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}