package bo.gob.sin.sre.fac.reports;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class MetodosConversorReport implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String JasperToPdfXmlCsvHtml(JasperPrint pJasperPrint, String pTipoExportacion)
	{
		String reporteFormateado ="";
		
		if(pJasperPrint != null)
		{							
			try 
		    {
				byte[] reporteHtml = null;
				reporteHtml = exportar(pJasperPrint, pTipoExportacion);							
		    	reporteFormateado = new String(reporteHtml, "UTF-8");
		    } 
		    catch (UnsupportedEncodingException e) 
		    {
		    	reporteFormateado ="";
		        throw new IllegalStateException(e);
		    }	
		}
		else
		{
			reporteFormateado ="";
		}			
		
		return reporteFormateado;
	}
	
	public byte[] exportar(final JasperPrint pJprint, String pFormatoReporte)
	{
	    Exporter exporter=null;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    boolean html = false;
	    
	    try
	    {
		    switch (pFormatoReporte) {
		        case "HTML":
		            exporter = new HtmlExporter();
		            exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
		            html = true;
		            break;
	
		        case "CSV":
		            exporter = new JRCsvExporter();
		            break;
	
		        case "XML":
		            exporter = new JRXmlExporter();
		            break;
	
		        case "PDF":
		            exporter = new JRPdfExporter();
		            break;
	
		        default:
		        	exporter = null;
		    }
	
		    if (!html) 
		    {
		        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		    }
	
		    exporter.setExporterInput(new SimpleExporterInput(pJprint));
		    exporter.exportReport();
	    }
	    catch (Exception e) 
	    {
	    	out = null;
		}
	    
	    return out.toByteArray();
	}
}
