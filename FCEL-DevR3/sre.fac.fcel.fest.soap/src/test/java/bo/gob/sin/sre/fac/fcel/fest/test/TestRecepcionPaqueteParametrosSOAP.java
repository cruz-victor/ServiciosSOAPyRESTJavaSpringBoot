package bo.gob.sin.sre.fac.fcel.fest.test;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.fcel.fest.service.IServicioFacturaComputarizadaEstandar;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppConfig.class })
//@Transactional
@Rollback
public class TestRecepcionPaqueteParametrosSOAP {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39112/FacturaComputarizadaEstandar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaComputarizadaEstandar";

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro01() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro02() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro03() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(1);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro04() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema(null);
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro05() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("1808D8C24EF-808D8C24EF");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(1);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro06() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(0);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro07() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(3);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro09() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(null);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro10() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(0);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro11() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(3);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro12() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(0);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro13() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(7);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro15() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(null);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro16() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(99999999999999L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro17() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(null);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro18() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(0L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro20() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("12D270CA12D270CA12D270CA12D270CA12D270CA");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro21() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis(null);
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro22() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("12D270CA12D270CA12D270CA12D270CA12D270CA");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro23() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd(null);
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro24() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(0);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro25() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(7);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro27() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(null);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro28() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(null);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro30() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(0);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro31() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(29);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro33() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(10000);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro34() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(-1);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro36() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(10000);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro37() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(-1);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro38() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(null);
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro39() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
//		solicitud.setFechaEnvio(22/2/2019);
		solicitud.setArchivo(
				"H4sICAyN8VwEAGFyY2hpdm9fYWN0dWFsaXphZG9fcGFxdWV0ZV9uaXRlcnJvci50YXIA7VZbj5pAFN6f0neThgHcykMfdFGUyigjgsxLw4DhNihdlduv7xntdrW1aZMmTdN0EoLO5dy+73xD8BwmabX/GITHU8DTLoj2H8vg02l73H7cpcft8/P++W1T8IffGBKSpHfv3j1Il/Htuy8p6gNS1HeqIj2qYh49yrL68EZ6+APjdDgGzw/SH/H1F46lrqnMq+OocNtQ5hXLpNRaqfUsHXFW4Ioa/EQ7KXVdYsydOF126oelQRNfISgsJjqTUR1tSBIW/NH3SEI2WPK9fu5vZvHWQAe2sx63U1wuV+Y+mpJ6kQ6qSImU+S7s5oXW0nbQWvqwmaMEOxOcBYZ79FeoZLuznYyuwA6cXxT9yikmR7rBte9hvpbxnnoocQwt8zekZLKqzYpfiusE9vJZpn6YPQ3jpYETv3AzujGTZbyPYU48A1ZwiXioDGWtXWbj2sqGiqVb8LYGc7lfRmP3GGxwFX6xc7HVR8xzWya9xDHuWc6wtvS6Yjv3CD4qUkyyaOq2vnN9DiOaqdLCwU94PVZtyW/sLhpjyVZtJ2+wHrZWFo3tLDKIY7V2Rg2rG6rLJy2LPL/3lF7szPQG/tN8mSWw7k4saSwvnLAmWdzYGUTk2A12LGRLYKOz+1aOJ2DjfOY6d1/W8sCLqrXiZtHG7Hyvhjzq6s78te882Jjcl7HAokcUN2GGycMdqWZTWjKjScKdWVHgmuXMOvB7vf8mByaTksqaHXl9iSHK2Y4ks2nSBU55Crxam+1IG3nrdN5d15CCrSQRmF14rAKvxv25M0Rzx6pdfdwssmG9yGb1PLtgQgH3wJhMmMe7wNNOt/hrR39n8nUxeWRy32EQp8h3tYO80pFNIfZtVlc/2Hcvn3WwGVXEEPVGIqdq5RF4w5oxAczWwKtzv+0YImVoaGOwJfh0igzNpIZ489z37GvbJ2ErLK73DgTnIPewBo60UOd7e76JD9XMAH6KercjNZTzR+D/AXpWCncun6XD6h4/bLkpxRnqqAg7ZGTn1msOEj4EZ1/r3gtGd2qC6YbAfs32wR702dV5xCHvnKFRQuVBT7f3pvD90keuMWmDwpX81c/jZYAt2FozhSRMV5H1BA/gfzt/zSVUCXyI4Xa+4p6x+jn/vsYNnHBz6G95oeev+bzOv56ZnnHWfQ+lgTc49xhgXEPtEuaZJeu+iwny1kDHGgxc49QY97AzPMEDfu6uX+N8oBsOmk6SZd7w7Wq0qJ+HMdbX3SIdWUyxYuAG4Kp1M4PwGeTDNi7kC/oo7/SDrMbAwZR6ahxOTVE7mYLGi34OC7cLDVdwJ6er0QFsib0t9fD+8nsdMwPsTt0u8gA3T/zOY+DRKVRARz1SCRtz0U+G2wdO3dbp67lBj3BiAacRnBPafo+XNz22lt0MMG5vuUUqX/nCd4Q5KKPQcfwU59fcnGwNzEND6FP/unde7qOS6VIaTU0Ed9RLHODju/tkALUQmBzojeZPIC4uQ+4JHbsZ+ICa8gzyri1n3FhnTH+453veoVELcSNfETnz0zITNl5y/n79rt4DrnQq6jXofdHor1oM+pFHHpZuOUl4KOMWdC0762eB4Y4YC1wP/mrYzIymjMCm0Hq4tzPAq/bP2nwTf3KlgSdLF3m/zl3HCffBec4585QkZ0wV93SpD+KQ5ze8GQkOlgzBnsu3QCXu8jk8EFMNnBNxu+Ku9zei335TA6YY+ZxAD08OkAv4WQs/wF3zi8Z8/60g+AdxcYirtTob4SwakbHQ0W/W41v9WwG/A6dfwh64b8zsjNer1pti/pWThIuYGGjxE/QYffkGWYseHyFYAwzLBGo7CSFOoRHQD+8f/o//4//4P/6Pf3Z8BmaIcXwAEgAA");
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	@Rollback(false)
	@Test
	public void recepcionPaqueteNro40() throws Exception {
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("E79A306");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(2);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("10DC8801");
		solicitud.setCufd("8F35E3A6970F81B67E41535CC2C492CE");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(null);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(null);
		solicitud.setHashArchivo("db03c063c1d6ac961302776ad94c2c793553fcab647ab3d37a689d3db0dbf4a1");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

}