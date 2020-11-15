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
public class TestRecepcionPaqueteP03 {

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
		solicitud.setCodigoAmbiente(3); //error
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
		solicitud.setHashArchivo("e6dbdb4e683edf029be42e23a5fe582e509890b4e1d0fea97fba8b5ed24cff55");
		solicitud.setArchivo(
				"H4sICENS0FwEAGFyY2hpdm8gLSBwYXF1ZXRlIHZhbGlkby50YXIA7VZRc5pAEM5P6bvTDiCm8qhBT6wQi8DJvXGQEfAgtCoov77fkabRxk47006nD70ZBnPs7u3u9+13iT7HaVY/vnn7poo+HR72D2/qSGTJ47tjIW7+0FJURXn//v2N8rS+f+u375Ubta/f6spAeT+4hX1fU/s3b5Sbv7AOu330+Ub5K2f9g2tpGjqnzSYpglOsiZrnSmav9MbKxoIXTs2IOLBWyYLAJQtvky1b/cOSsDTsu2pcTE2uqU2ydtO4ELchdVN37SghHWzDtbV5IOqOl/btw8yplqv5YzJzm/tsWCf9pL8o43ZRGCd2Gp5sc3RcqKnjTZ08IsE+XKkVL7s4OVshDvzvi0HtFdM9WztNSB3ha84jo2rqESMP127FNd2wim95TTkJ8mQ2r3kh8tAP2oRMD4xMT4sybdmd1bvLRhvLPOYhnYtQC06hp3+w7rCHZ0kGVTIJ9tHaqeNcP9qm1TjtqMW7Xd4Zh2jtTjkVLe9/jXPXxTokVBVxYRDkpyTrebrM7cbx7OFCG6icBieuPOc3OffLExr2kEdue1brmL48Q+59Z8O2y8LSw3yjsWKescLWHc3G3zb68bHveOgInbQOCTW7DQRiPPlsHjdf6xqGmrGNaFL7/SBHfm1Im57tNfWV/fOzt9Fa9siRPe65/SDlZC7i0q2tGas4OaZxOa8ZOCTzx7nn9hc1cM2tmGZ8TOhA4SoTvHRTa5a2kVcdItoYVumeEupni/YcC4ZYaepStXripw6+TAYLb6Ti0QJzcrxHD+5zq1nko8Y2m5oVAXg07TCKqHE47wEvjH1YzoVfTG+5NvA48pT1rkrUlY0/MuT+kDf1D+yu1eNH63HtEtlvVdZUr6gr3xX4tl3mPvDv5qjkqlvFxJggluTDISHGnBH5FltgeIVL57ZDYDVC7XFjt3i3yLEM9sj34uzv8N7H5Ci++j/PUcVNJcNsqHK2Lnv9NU/F2UWdj98Dr5SP/twErs/1mpwIeZa4xi2PBgrsap9MS97qxzM/h61dxDc+hrA7932uI8B8RkWghKuxHmvbW8zuDrqhxGUgrGx06UMN2T+f992Um7pq3+EB9pf757WpNXJGr4I27Addr37Oved+wLcIttAI7d7cvuD5sv/iM+swNkOqZhEddvMFfBv0NOV0XvH2VU6o24DWHR3wTDAyQc9HBzw45+r3c57s2BpYFG663B7Fw2p833webaSG3Gdjm/ftDbAHLkZrEVdYqIevA9TrpKFWmjtN34B/GaP6Jp7NZe80Bt2WsxwXQRsTYHkab9lqvEMsaXti1Hl8+u1vOEHcGbSVAjcqf2834MEh7kM7qVvLGAs5SyQYMDq47NM3v2HPFa4Nnqjwk/dIfYVXFxz3tSAHxqdlPjnDwq1DnMs6bXEEdBba/XQeYr/S+SX6gTthx4l/3k+pz1VSyHl0p7IW3G9VqE16NnTG9iYNYv3I5qrWYYa3CXVkThW0S8Z4mYnX319zTx2fYKvKelCvnPsXvQZmbCZ7MbzQa9yROfrYhJ1eDnCWk3bYk8nGBpa4v04ccVg3C3Pw9bVWh+gjMEwZZkvqqsz5Ze8Cx0O3N1EF8gXGgwyzJ+S9IDHlT31yGHWr77jbxFKnqRHARgnX3WwcMcdHOcfgo5D5BxR3Mel4cu67xywowFzE0McO89M1zbjwAd/mr/QCnMue5qu5pr8eQ17MQ065rThe2P/ou1ILv//+4W4lMXu+5wV4uO1FsInoNAvbyYVer+S+942bwAs5kaPUU/D5l/636t38X//X//V//eb6AgZDXREAEAAA");
		IRespuesta respuesta = servicio.recepcionPaquete(solicitud);
		System.out.print("Resultado: " + respuesta.toString());
		assertTrue(respuesta.isTransaccion());
	}

	
}
