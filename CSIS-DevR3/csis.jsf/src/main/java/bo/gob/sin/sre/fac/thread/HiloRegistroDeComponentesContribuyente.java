package bo.gob.sin.sre.fac.thread;


import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.sre.fac.reports.MetodosConversorReport;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mail.Email;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import net.sf.jasperreports.engine.JasperPrint;

public class HiloRegistroDeComponentesContribuyente extends Thread implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(HiloRegistroDeComponentesContribuyente.class);
	
	private JasperPrint reporteJasper; 
	private Long solicitudCertificacionId;
	
	@Override
	public void run() 
	{				
		LOG.info("HILO ENVIO DE CORREO PARA INFORMAR EL REGISTRO DE COMPONENTES QUE REALIZO EL CONTRIBUYENTE");
		LOG.info("************************************************************************");
		LOG.info("FECHA INICIO SERVICIO :"+ new Date());

		try 
		{
			MetodosConversorReport vMetodosConversorReport = new MetodosConversorReport();
			String reporteFormateado = vMetodosConversorReport.JasperToPdfXmlCsvHtml(this.getReporteJasper(), "HTML");
			
			String asunto = ConstFacturacion.TEXTO_ASUNTO_CORREO;
			boolean vFueEnviado=false;
				vFueEnviado = this.submit("wilson.limachi@impuestos.gob.bo", asunto, reporteFormateado);
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG);
			LOG.error(e.getMessage());
		} 
		finally {
			Runtime garbage = Runtime.getRuntime();
	        garbage.gc();
		}

		LOG.info("HILO REPORTE ENVIADO || Finalizando");
		LOG.info("FECHA FIN SERVICIO:"+ new Date());
	}	
	
	public boolean submit(String pDestino, String pAsunto, String pMensaje) 
	{
		boolean vFueEnviado=false;
		
		RestUrl vConf = new RestUrl();
		String vRutaEmisor = vConf.getPropetyValue("correo_emisor_facturacion");
		
		try
		{
			vFueEnviado=false;
			Email envio = new Email();
				
			String[] destinos = new String[1];
			destinos[0] = pDestino;
			
			
			String[] copias = new String[0];
			String[] copiasOcultas = new String[0];

			vFueEnviado = envio.envioCorreoHTML(destinos, copias, copiasOcultas, vRutaEmisor, pMensaje, pAsunto, false);
		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pDestino));
			LOG.error(e.getMessage());
			vFueEnviado=false;
		}
		
		return vFueEnviado;
	}

	public JasperPrint getReporteJasper() {
		return reporteJasper;
	}

	public void setReporteJasper(JasperPrint reporteJasper) {
		this.reporteJasper = reporteJasper;
	}
	
	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}
}