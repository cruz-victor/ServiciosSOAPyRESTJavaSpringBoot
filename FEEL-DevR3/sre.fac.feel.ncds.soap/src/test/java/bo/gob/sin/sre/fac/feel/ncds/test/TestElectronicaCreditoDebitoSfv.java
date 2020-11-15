package bo.gob.sin.sre.fac.feel.ncds.test;

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
import bo.gob.sin.sre.fac.feel.ncds.service.IServicioNotaFiscalElectronicaCreditoDebitoSfv;

public class TestElectronicaCreditoDebitoSfv {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39125/NotaFiscalElectronicaCreditoDebitoSfv?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/NotaFiscalElectronicaCreditoDebitoSfv";

	/*
	 * RECEPCION INDIVIDUAL
	 */
	@Test
	public void recepcionIndivual() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioNotaFiscalElectronicaCreditoDebitoSfv servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaCreditoDebitoSfv.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(2);
		solicitud.setCodigoDocumentoSector(30);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("f6f517a14233bcd0b23e97e13842608dbec862729823db51f107dfade515eec4");
		solicitud.setArchivo(
				"H4sICPLJp1wEAG5vdGFGaXNjYWxDb21wdXRhcml6YWRhQ3JlZGl0b0RlYml0by54bWwAlVZNc5swEP1BvfBh0nDowQ5Gxg2KwSAh3RB0wCBcZmyD4dd3hZOmNG6nPTAakHa1+/btW3aOvRC0L/KGDJkhO1FpB3+/6L3DSooGdxzJCx+1AyEheo6Kw25c4KfqehFmWIaNHJnhngLDPmcborFk235r3JrFeOA0bHNkrzndwvpYfEP6SRz9h28b3O722+/5JuxfDo9dbubm8zEbnxt74MPj4DvL67Ne4sjFVYrIme31VhyxxqhV8b1XKPuXxuqixj3zBPeMYhkb+Dunehkhu2JJ2ApjYXtwJkcuShNcMXp1hKH3eRKWWSMfGA3LwNxKyE0TWihZA+tgLTIjOOwK7av3tCx2CJesIRVPtuXbt9t3q83X5Ax+u6xaXH3H6/G4HGEdd0/2JU1CV1A5CtP75ATfi1e7R3EkZ/AFcdvaHXw++dHNPqe6zBr7RZh3YlTYH5Y3n861yin75FMimRGYrIp7TkmDaayzJpR4hNVY95gy0x/jx2cD67wC+6J+teX1rglLHtVXjtYjj5YL3wg0v1mP8K7hiBm4IocXwIE7WwmxTTZzLCZ+HIWOdWYq/rinXbWEuz5+n9uFbQbYsolvViCQ+8D2KzczV2X/2S29J1/HTgB+fj03z13AHjfsIKeWJnQuxTEsvU05plF7SWlve8dwyGl88J4eZ3Xg4C9F7lSjlNqXXeX1flSffSeAh8R+lD344xIedvEjZkHeDaf4O4unmituzeva2Gd23Mq4cR+EYUXCwC2j/afo9bsH3L/tLYrYsKuUuidVZ+DjAXLbs6TthMQd5FgK526OcZqsuhAp/HWVZ7enIaywh9x6V61/wRvwAk5BDLqg5AL82nKkVlkzGtzj48zvrgosf/R731F8sZSPAbjXMRPOUusD/1RPCfS6N6ygf+oH0cgT9LeWHYn0DsvuLl80fEqn+OKJ94xuK8DkLV9HIKnikXPbt3h4ycxQz5r1p5eotl6f93jf9+/mGyREE+akU5Wqf2CSBvpgZBGoCJpWjTu8zMZfMXB1wHJIk7acuFi/9tI7Fr9xxEKgS1oO2jHnVwj8ih/8aP0Xfr3bzvPXO1UjgmwN9GwHOnZMqaU4c8X7BdSs7wTkA3HGSjvEGuKleSsa9/QXP2uecJ3Tq6qt/lwte4jnDDFowCWln6cQESOn5JSjeR+93uWqfgJ+GUK7p2nLi4rrX87OfCNiAadqFlk+T+oiOn3tPSfQ/fF7EaF1ofiWUtCup1UzYdDgVum0h5ROxgWfansFv+7gAf89hHXI9ZpTKbPDCvK78d3b1AU3t2W+IersxGO+7wuIqwY9Aux4lzU61PzonKB/OYoLgezRAwzzRp4nDk/vWxWDmi1dNvRwv7yoeZUq7ph+Ab1R8/2qyk4v7aTnwwK4dZXfwJ6jOU/zBOtqjorR2uxrG8U18NAkY05dqOccJ2bYNdR41sOxoTC2B9CFxXtPzTQh4sCvX+YTxPJx1u0AS1V/geLp3O2bO9Vvwn5NwLcFtZUVi2AOjmuY3crXn8689ct73PFm2/HNFG+U0sUnv2I6ns2P1QDxwwxRecnLnMcfz0DOo1/5d+1/mz8yMzD086qaZkCt9HddwH/MiSV+ESM1s2yo4VVmYw++yMjMLWjrR/2H/5+f+upHNyzftHl254ZcbpjoaqYrbjtB3K5i8A8zbDofUVKrM3O7FfQK3KuD/e3/ReV5wVHfZZutVHpEwD5HH/mRmeRw6/MeYosvU31M4Je89fZ9bX6rO4mzxr3A7H6bv9df+cQhFoGukmy2kC+GHrlh886pGX/g/T//x8bFlx+V8ofVnAoAAA==");
		IRespuesta respuesta = servicio.recepcion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
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
		IServicioNotaFiscalElectronicaCreditoDebitoSfv servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaCreditoDebitoSfv.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(2);
		solicitud.setCodigoDocumentoSector(30);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setCodigoRecepcion(12482L);
		IRespuesta respuesta = servicio.validacionRecepcion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
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
		IServicioNotaFiscalElectronicaCreditoDebitoSfv servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaCreditoDebitoSfv.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(2);
		solicitud.setCodigoDocumentoSector(30);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setHashArchivo("c032922f85e0ae37c230039953cef8ce3ccd091a48345d9563c8b6e85b4f00c8");
		solicitud.setArchivo(
				"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");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
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
		IServicioNotaFiscalElectronicaCreditoDebitoSfv servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaCreditoDebitoSfv.class);
		SolicitudValidacionRecepcion solicitud = new SolicitudValidacionRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(2);
		solicitud.setCodigoDocumentoSector(30);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		IRespuesta respuesta = servicio.validacionRecepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
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
		IServicioNotaFiscalElectronicaCreditoDebitoSfv servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaCreditoDebitoSfv.class);
		SolicitudAnulacion solicitud = new SolicitudAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(2);
		solicitud.setCodigoDocumentoSector(30);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		IRespuesta respuesta = servicio.anulacion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
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
		IServicioNotaFiscalElectronicaCreditoDebitoSfv servicio = servicioSoap
				.getPort(IServicioNotaFiscalElectronicaCreditoDebitoSfv.class);
		SolicitudValidacionAnulacion solicitud = new SolicitudValidacionAnulacion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(1);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(2);
		solicitud.setCodigoDocumentoSector(30);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		IRespuesta respuesta = servicio.validacionAnulacion(solicitud);
		System.out.print("Resultado: " + respuesta.isTransaccion());
		assertTrue(respuesta.isTransaccion());
	}
}
