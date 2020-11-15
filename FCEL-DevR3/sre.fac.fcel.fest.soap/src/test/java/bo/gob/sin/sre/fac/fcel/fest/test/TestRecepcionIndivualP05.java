package bo.gob.sin.sre.fac.fcel.fest.test;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.SolicitudValidacionRecepcion;
import bo.gob.sin.sre.fac.fcel.fest.service.IServicioFacturaComputarizadaEstandar;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback 
public class TestRecepcionIndivualP05 {

	public static final String NOMBRE_SERVICIO = "ServicioFacturacionService";
	public static final String URL = "http://localhost:39112/FacturaComputarizadaEstandar?wsdl";
	public static final String TARGET_NAME_SPACE = "https://siat.impuestos.gob.bo/FacturaComputarizadaEstandar";
	String rutaArchivoEntrada = "D:\\PruebaServicio\\archivo.xml";

	
	@Rollback(false)
	@Test
	public void recepcionIndivual05() throws Exception {
		 //computarizada con error en XSD
		URL urlWsdl = new URL(URL);
		QName nombreServicio = new QName(TARGET_NAME_SPACE, NOMBRE_SERVICIO);
		Service servicioSoap = Service.create(urlWsdl, nombreServicio);
		IServicioFacturaComputarizadaEstandar servicio = servicioSoap
				.getPort(IServicioFacturaComputarizadaEstandar.class);
		SolicitudRecepcion solicitud = new SolicitudRecepcion();
		solicitud.setCodigoSistema("39562F");
		solicitud.setCodigoAmbiente(2);
		solicitud.setCodigoEmision(1);
		solicitud.setCodigoModalidad(2);
		solicitud.setNit(1020703023L);
		solicitud.setCuis("0BDDA3BC");
		solicitud.setCufd("dae91da3e083d42a3da456665b8cad2e");
		solicitud.setCodigoDocumentoFiscal(1);
		solicitud.setCodigoDocumentoSector(1);
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo(
				"H4sICKh2y1wEAGZhY3R1cmFDb21wdXRhcml6YWRhRXN0YW5kYXJFcnJvclhTRC50eHQAlVZdl5owEP1BfQGUPcujLh9ClygYEpM3Az0IBMvpKl+/vhPWrWx3e9o+eNCQmdy5c+fGnW0tBe3yrCZDashWlFoR7pedX6ylqFHLPXnlo1Zkh7h+xnmxG5foqexrRpGWHYJTZFiXdEM0dgiab7VbsYSMmedeuecO/uZ0EZ45bs+n8Yi14riJtdT+3j4vskU2mItwMNu0TtuwXHXh3mI46TEzTlJQ53Kk5pSHGUkxz8Pt/8oDsers5io08yQoGVPPLTlB5dEjF5b0LTNc7Uit624f/FNNzwrDk//Fjr7nvt2XjAaSGWRgGHgpVrn/tHoUtdRiqjepYQ270ukA1yK0Q3iGj8+G2WQOuRwPqE3LWcyZXPghaOPaLbPNlK8L7VWP8ErF6IB9EBo/sUWsp7Wjzldx+c5DOi+XPaek5HZWILyWnDKdY1JwL1kgTEqGV7AnHRHuWrYg9S7XvkLshD+jvAKMBsLVIoZPaFfLqGLLuELe1o7X2yTpUIXcuFw/RVo07J6sKWaeQxhxww0LZxTp6RmdhL3s1L6P6/d6QVcDp6icuK/QGju96zv9LhnWUZyYu0izMOSojgfFL2qEsZzVPOnyLPS1Ls5xS2pyzTznS2hDfQbE0KxNNmrNopya2rw3vCbQe9cVVI7T2aXfhbi6hHYCnyAJcf4Q4tUDcH8FTnTAUAPO7yxRPfsdh9kKGgycBKdvtXVNDAvqcV9UPYkeuxjqwcWaxkkf0GK93yfIj0jg+IBT1NaFnQOZ1O6DMEwsoEZGu09qjJvUsxx4DxqYagq4p56yYjT6EuJ7zcSTndBi1eOLqltoslLPI41P3P5Ea57qpS4Vh7sy1LZ2utja0S+9vcs13uOZmg+vl694HvNvG9Rsa7MRMJvZJtD53s+fZ/vf8EVG36hcHC+NmT5s4UmFQX6iKcQPMfBgRQziQaeA7caLpkvgogINnLjxOOPthl2PT2ndwB7nr/gEtRSviVjESqM9GhUHejtpy7O09315XY89MgI3N+58Vc9FGIrzWKbQq1fOPnKA4SwO/diVbLm96/W+fudAOx7WwJl7YbV8rX0xacFmVC+OdF7zHGuPAIfkah5wqv3CpccteNjLJ+e88APwX8Na1ctv+/W2+7HKkZ2M22IdikWYp+BvWS3VrI6+F0sG8+ZvlB7lFWZCS2sX7o4k53SZQz3gk2ovPA9hDh6Ug5crHTXgn9Xz07oEjNN3f1Op3Gfw3Nd3JcyFR0zgrprPbHZAuvJiMZp77GjAA/gxdYc7B/d5eadZHUnwS+Wz/V1v72YJw2xrYnHz86kfbgFr8pfH2n3FD6ALr5/rU/lwk9VqBmNX1SwMvWGG8iBHQ+NK+d+f9nzmYwNg0xXWxJDKk/otZm9a//j+45xEaW1VmaplNKOk0NTd0aF3fhi0fEPKzJtzNml1AJ3dfNgNY+0xp+6JAX6oG5VpLTs2/S+Ya9l97z/33/fc4L+T7zi65OodNgmuIujd7+v3vOkmkOosQuGO9F77G5YTl11aE4WRqLt10sLH+XUU3uwfPSldkOI2229ngKaCmwd89MrEIIAn+Wvet/178NXjfr1MjeoBML/A/wstPRPpF6v21j/obyzVTAovUbzB77f73bVBK10GugP+HxjwFcOcMTUXB//LT7+yniu0CQAA");
		solicitud.setHashArchivo("77d156c258df4d268730e91bf015219e976e3eb25a4fa71da70c0432e3c4cc32"); 
		IRespuesta respuesta = servicio.recepcion(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	
}
