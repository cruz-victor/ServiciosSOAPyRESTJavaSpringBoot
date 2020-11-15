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
import org.springframework.transaction.annotation.Transactional;

//import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.fcel.fest.service.IServicioFacturaComputarizadaEstandar;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppConfig.class })
//@Transactional
@Rollback 
public class TestRecepcionPaqueteP02 {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39112/FacturaComputarizadaEstandar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaComputarizadaEstandar";
	String rutaArchivoEntrada = "D:\\PruebaServicio\\archivo.xml";

	
	@Rollback(false)
	@Test
	public void recepcionPaquete() throws Exception {
			URL urlWsdl = new URL(URL);
			QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
			Service servicioSoap = Service.create(urlWsdl, nombreServicio);
			IServicioFacturaComputarizadaEstandar servicio = servicioSoap
					.getPort(IServicioFacturaComputarizadaEstandar.class);
			SolicitudRecepcion solicitud = new SolicitudRecepcion();
			solicitud.setCodigoSistema("9BB347E");
			solicitud.setCodigoAmbiente(2);
			solicitud.setCodigoEmision(2);
			solicitud.setCodigoModalidad(2); 
			solicitud.setNit(1020703023L);
			solicitud.setCuis("85EA4EE9");
			solicitud.setCufd("16597E97098BF8FCF84A9E06CEF0BCD2");
			solicitud.setCodigoDocumentoFiscal(1);
			solicitud.setCodigoDocumentoSector(1);
			solicitud.setCodigoSucursal(1); // error
			solicitud.setCodigoPuntoVenta(0);
			solicitud.setFechaEnvio(new Date());
			solicitud.setArchivo("H4sICGnX4lwEAHBhcS50YXIA7VbZjtowFJ1P6Xulytk6zUMfgCyEEoeELMRvWaqA49C0A9m+vtcZWmiZblJbVSqWkMHYdzn3+PgaM/3hkOzz5IPw4tAd7v7EQAJC9/f3d+hxfD3L9xK6EyT5HimKpKCXfP9LUb57hu7+wjhC+h/u0F/x9Q+OlabKadQWeRX2mcialKKdvZZbazdlaYUbYrIjGdAu33jV0i92q0HGM9pVcYRRvllsXVE9ZPMQxZtF/bYyyjgIh9w0jsQ0emu+PaSmMjj77ZD4aJfMPZRp75qllEt5r0h2rzRZlTU2nbT2Wo39oPNjccvSSD8kkTLaicVgd2mHaL9kB85y3/UxRco2jcIhMw1KQkwTMzzEQdfEooGSSD2u1oufymnJY5hZzzX3XWFpHY2jBYvFsI99wGU3KazZ5FVaMeRFQp2Jar+iegtxSbZmw2y/WopKnevhIdngJqMXZ/bhgWwWjVcZNJ+P9kRbm/D9AsTdp4hsY8kTskrnvvmZYmVigVBZwv5i5g5uh3VLtHVbdvygdXTDcMtYcDRbsSkzvcBWHB9rtqaDTX7u7DuWwopocodpoGAEn2GiOPrCdHSiebRArs8MW4PvQdy6JdZt2janM2cbolomUd4EUkgBwyGO2ue2Bvuu1y/i9+qsCmk88k7R3KCzvfXU9gW7CPTQ8RFeBUPLOdiTCNOxTgV6A2dH7FPRq4mounmkoFQgLN1720/1TqJWtfZen0fAn9mrC58E6rXdjvUZ/co9YKIs/Ymw9K021PTeoS58LGlJCxlT8A8xJqZhpBEbvorhmIrCDnJYx5u6SRluIJdtqkEuIQ48yMdHchGWob0O3xVBybS1bqy92VjXJo0WPQkX27eVegxEFfIzHp7IL0g208YzVZpHAs+xWUcen2vgY7miYz05RvtUADxNVU9FzDlzzE11QUw+szKO3AsMPnHK43U8EI7fIA/YdyU8FLKjBWg1U4/cX1Zd2rvEETfpZvpATjH9GPdTjAg/JKOt4Lnjn/nhil3N4yD+Naf8KESARROYxh7i7CC2T9hgsvHArurGsO8Cu0+xB3CH35PNz/BCaHgeoamikbt+rICfQypybnlNbn5Rm8d15LEMsDnhJ/D91+tP5aMeSeRtVzQGrNvmav3sB/Hau6JxiCvWjLWWxhprcSTskuhb8XcY4mDE1J/M4wk/D2TDQO9hrezY2/XUaT9MCqwFg7Ob2qlkFxloXF4xfgcHy/RYXIVHa855wY6gZSirDHg/goJEcgH5gFbyvTBv7IKYQQF6zrk2cnY5m1KIcfxuzUtuew/a8Pgf3LfUDBXArrzU1HyDBa7H6aCsfR0BDqDJkdEn0TXHvuC1gBnoJmjtJW++uCM+Ad1PpZOmj/UwdrDGPmut1pWcQ6nZsQvMuB7XecXvlmdAzlwL6ljUn2NqtbY/acHft/ZcxyxMe4hN4LEGIjuuaDHYvjtcxOwCn8s8wjynOqVPaO980ZB5SPk9DQzrYGvwHmox6MO1j1Mep9wwzSrWnnR4TaCmmcTzNcYa8hqNbyPoU7Kpt+O+Hdewkec9cPRKm2PAPgGOEe0R9/Pvi5pGylhzuN8l/2/FQscD/K/Wz3Zb0FruK+Rv7CMf5H58J6UpfyPqVAiPCdQK8ml+cF97zLkWqZwDOscg/1rj5sA5drr7VJcwxJZJ4e6kEVd6yrkGftl3tGZx1l4G/CyLt3NcO5VSpxr0WPOFQNZWsTxpBuD2Be/g90/1KKvi9eu7nxzn/l/8N/p/SXns/+9v/f/fGLf+/1/u//Xf2v+7gd27fnbr/2/9/63/v/X/t/7/1v/f+v//vP+/jdu4jf93fATLRF6XABwAAA==");
			solicitud.setHashArchivo(
					"689cc4717f9355ca993d9f64f52e546d4a7a817a0a9ae589fa16e1887ceaf93c");
			IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
			System.out.print("Resultado: " + respuesta.toString());
			assertTrue(respuesta.isTransaccion());
		}

	
}
