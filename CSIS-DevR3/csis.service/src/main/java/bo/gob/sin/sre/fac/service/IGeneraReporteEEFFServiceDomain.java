package bo.gob.sin.sre.fac.service;



import java.awt.image.BufferedImage;

import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import net.sf.jasperreports.engine.JRException;

public interface IGeneraReporteEEFFServiceDomain 
{
	byte[] consultaFacturacion(RespuestaConsultaFacturacionDto datosConsulta,BufferedImage logo,BufferedImage logoSiat) throws JRException;
	
}
