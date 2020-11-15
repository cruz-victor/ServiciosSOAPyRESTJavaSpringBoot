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
public class TestRecepcionPaqueteP01 {

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
		solicitud.setCodigoSucursal(0);
		solicitud.setCodigoPuntoVenta(0);
		solicitud.setFechaEnvio(new Date());
		solicitud.setArchivo("H4sICMgH41wEAHBhcS50YXIA7VZbk9IwFOan+O6Mk95c++AD0AtFGmjpheataZ1CSLG60NL+ek+6KKysus6o44xkhgmE5Fy+8+XLscbm/T7d5ekn6dX+uB/8iYEkhO7u7gboYXw7q3cKGkiKeodeI+W1rIn9r2V58AIN/sI4QPqfBuiv+PoHx8LQVRo3RV5GbSbzmjK0cZdq42xGnJa4JjY/kA5t8pVfzoJis+hUPGbHMokxylfTtSfr+2wSoWQ1rd6X1jYJoy63rQOxrdaZrPfU1rr5bt2lAdqkEx9lxod6puRK3mqK22p1Vma1y4aNu9STIDwGibzmNDb3aaz1dhI53FzaIcYv2YGzwnd1oEhb0zjqMttiJMIstaN9Eh7rRLZQGuuHxXL6rJxmIoax89LwPhSOcWRJPOWJHLVJALhshoUzHr6hJUd+LFWZrLcLZjYQl+IaLszum5msVbkZ7dMVrjN2cWYX7clqWvulxfJJb092jaHYL0HcLUVknSi+lJWm8C3OFAsbS4SpCg6mY6/zjth0ZNd01XkQdnPT0bzQVLBhan4QjbzQk+YBNlzDA5vi3Nl3okQlMdQjZqGGEXy6oTY3p/bcJIbPCuQF3HIN+B4mjbfFpsua+nTmbEPWt2mc16ESMcCwS+LmpWvAvuv1i/j9KisjlvS80wwvPLr+cuQGkluEZjQPEF6EXSM42JIYs75OBXoHZ3vsqexXRNa9PNYQlQinO3/9pd5p3OjOzm/zGPgzfnPhk0C91uu+Pr1ftXUBo1kwlGaB00SGKc/ZUJozR5mxQsUM/EOMqW1ZNObdNzEcqCxtIIdlsqpqynENuaypAblEOPQhnwCpRbSN3GX0oQi33Fia1tIf93WtaTxtSTRdvy/1QyjrkJ91/0R+Yboa1b6tszyWRI71MvbFXAEft8AvsNXf0x2VAE9bN6mMBWcOua1PiS1mvk1i7wKDL5zyRR33RODXqR0OPAV3hTo3QrQY6wfhLysv7V3iiGu6Gt2TU0w/x/0UI8L3aW8rfDkPzvzw5GMl4iDBNaeCOEKARR3a1g7iPEJsX7DBZOWDXd1LYN8Fdl9iD+EOfySr5/BCqkUeka2jnrtBooGfPZUFt/w6tx/V5mEd+TwDbE74SWL/9fpT+egHEvvrBUsA66a+Wj/7QaL2nmztk5LXfa2VvsZGEkubNP5e/EcMcXBim0/m8YSfe7LioPewtj3y98vRvPk0LLABOrIZuVRxiww0Li+5uIOdY/s8KaODMxG84AfQMpSVFrwfYUFitYB8QCvFXphXbkHssAA9F1zrOTsbjxjE2H93Jlthewfa8PAf3DdqRxpgt73U1HyFJaHHtNOWgYkAB9Dk2GrT+Jpjj3gtYQ66CVp7yZtHdyQgoPtUOWl6Xw9rA2v8q9Yax63gELWP/AIzocdVXoq75VuQs9CCKpHNl5g5jRsMG/D3vT3XMUujFmKTRKyhzA8LVnRu4HUXMXvA520eY5FTRdkT2juZ1mQSMXFPQ8vZuwa8h0YC+nDt45THKTfMspI3Jx1eEqhppoh8rb6Gokb92wj6lK6qdb9vIzSs53kLHL3S5gSwT4FjxHjA/fz7oqax1tcc7vdW/Lfg0dwH/K/Wz3Yb0FrhKxJv7AMf1LZ/J5WReCMqKkWHFGoF+dQ/ua8tFlyLdcEBU2CQf6txE+AcP919Bm8pxJYp0eakEVd6KrgGfvkPtGZ61l4O/NwW7ye4mpdaRQ3osSZTiSydYnbSDMDtEe/g97N6lEXx9u3gmePc/8v/Rv//Gt36/784bv3/v9z/m7+1//dCt/UC89b/3/r/W/9/6/9v/f+t/7/1//95/38bt3Eb/+/4DMgQy2AAHAAA");
		solicitud.setHashArchivo(
				"4dd8d2c5f9df936504cbfa0acfe6deaa5ef692cecefeea2c19e5e0809f7dd6a3");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	
}
