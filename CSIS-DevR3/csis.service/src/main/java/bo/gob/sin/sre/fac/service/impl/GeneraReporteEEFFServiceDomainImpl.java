package bo.gob.sin.sre.fac.service.impl;


import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.service.IGeneraReporteEEFFServiceDomain;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
@Transactional
public class GeneraReporteEEFFServiceDomainImpl implements IGeneraReporteEEFFServiceDomain, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(GeneraReporteEEFFServiceDomainImpl.class);
	
	private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private SimpleDateFormat formatFechas = new SimpleDateFormat("dd/MM/yyyy");


	@Override
	public byte[] consultaFacturacion(RespuestaConsultaFacturacionDto datosConsulta, BufferedImage logo,
			BufferedImage logoSiat) throws JRException {
		byte[] vReportPdf;
		Map<String, Object> vParametros = new HashMap<>();
		Date fechaSistema=new Date();
		String fechaActual=formatDate.format(fechaSistema);
		String fechaAdecuacion=formatFechas.format(datosConsulta.getFechaAdecuacion());
		
		vParametros.put("facturacionDto", datosConsulta);
		vParametros.put("logoImpuestos", logo);
		vParametros.put("logoSiat", logoSiat);
		vParametros.put("fechaActual", fechaActual);
		vParametros.put("fechaAdecuacion", fechaAdecuacion);
		URL in = getClass().getResource("/reports/consultaFacturacion.jasper");
		JasperReport report = (JasperReport) JRLoader.loadObject(in); 
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, vParametros, new JREmptyDataSource());
		vReportPdf = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return vReportPdf;
	}
	
}
