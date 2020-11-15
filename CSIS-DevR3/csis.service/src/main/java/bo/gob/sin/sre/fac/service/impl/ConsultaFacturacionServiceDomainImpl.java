package bo.gob.sin.sre.fac.service.impl;



import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IConsultaFacturacionDomain;
import bo.gob.sin.sre.fac.domain.IUtilesEEFFDomain;
import bo.gob.sin.sre.fac.dto.MensajeError;
import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudConsultaFacturacionDto;
import bo.gob.sin.sre.fac.model.SreFacSegmentaciones;
import bo.gob.sin.sre.fac.service.IConsultaFacturacionServiceDomain;
import bo.gob.sin.sre.fac.service.IGeneraReporteEEFFServiceDomain;


@Service
@Transactional
public class ConsultaFacturacionServiceDomainImpl implements IConsultaFacturacionServiceDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaFacturacionServiceDomainImpl.class);
	
	@Autowired
	IConsultaFacturacionDomain iConsultaFacturacionDomain;
	
	@Autowired
	IGeneraReporteEEFFServiceDomain iGeneraReporteEEFFServiceDomain;
	
	@Autowired
	IUtilesEEFFDomain iUtilesEEFFDomain;

	@Override
	public RespuestaConsultaFacturacionDto consultaFacturacion(SolicitudConsultaFacturacionDto solicitud) {
		
		RespuestaConsultaFacturacionDto resultado=new RespuestaConsultaFacturacionDto();
		resultado.setOk(false);
		List<MensajeError> mensajes=new ArrayList<>();
		try {
			SreFacSegmentaciones segementacion=new SreFacSegmentaciones();
			segementacion=iConsultaFacturacionDomain.obtenerPorNit(solicitud.getNit());
			
			if(segementacion!=null) {
				
				resultado.setOk(true);
				BufferedImage logo = ImageIO.read(getClass().getResource("/images/logo1.png"));
				BufferedImage logoSiat = ImageIO.read(getClass().getResource("/images/logoSiat.png"));
				
				resultado.setFechaAdecuacion(segementacion.getFechaAdecuacion());
				resultado.setGrupo(segementacion.getGrupo());
				resultado.setModalidadDestino(segementacion.getModalidadDestino());
				resultado.setNit(segementacion.getNit());
				
				byte[] reporte=iGeneraReporteEEFFServiceDomain.consultaFacturacion(resultado, logo, logoSiat);
				resultado.setReporte(iUtilesEEFFDomain.encoderB64DomainApp(iUtilesEEFFDomain.compressFile(reporte)));
				
			}else {
				
				resultado.setOk(false);
				mensajes.add(new MensajeError("Servicio REST","No hay resultado para el nit ingresado."));
	            resultado.setMensajesError(mensajes);
			}
			
		}catch (Exception e) {
            e.printStackTrace();
            resultado.setOk(false);
          }
		
		return resultado;
	}
	
	
	
}
