package bo.gob.sin.sre.fac.thread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.reports.ReportesController;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mail.Email;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import net.sf.jasperreports.engine.JasperPrint;

public class HiloEncolador extends Thread implements Serializable{
	
	private static final Logger LOG = LoggerFactory.getLogger(HiloEncolador.class);
	
	private JasperPrint reporteJasper; 
	private List<SolicitudContactosCertificacionesDto> contactos;
	private Long solicitudCertificacionId;
	private byte[] reporteByte;
	private ReporteDatosSolicitudCertificacionDto reporteDatosSolicitudCertificacionDto;
	private String imagenRuta;
	private String reporteRuta;	
	
	@Override
	public void run() 
	{				
		LOG.info("HILO REGISTRO DE FACTURA COMPUTARIZADA || Iniciando");
		LOG.info("************************************************************************");
		LOG.info("FECHA INICIO SERVICIO :"+ new Date());

		try 
		{
			String vMensaje = "<p>&nbsp;</p> <center> <table id='bodyTable' border='0' width='100%' cellspacing='0' cellpadding='0' align='center'> <tbody> <tr> <td id='bodyCell' align='center' valign='top'><!-- BEGIN TEMPLATE // --> <table id='templateContainer' border='0' cellspacing='0' cellpadding='0'> <tbody> <tr> <td align='center' valign='top'><!-- BEGIN PREHEADER // --> <!-- // END PREHEADER --></td> </tr> <tr> <td align='center' valign='top'><!-- BEGIN HEADER // --> <table id='templateHeader' style='height: 81px;' border='0' width='744' cellspacing='0' cellpadding='0'> <tbody> <tr> <td class='headerContent' style='width: 742px;' align='center' valign='top' bgcolor='#032650' height='80px'> <p><br /><br /><span style='color: #ffffff;'>SOLICITUD CERTIFICACI&Oacute;N DE SISTEMAS<br /></span></p> <p>&nbsp;</p> </td> </tr> </tbody> </table> <!-- // END HEADER --></td> </tr> <tr> <td align='center' valign='top'><!-- BEGIN BODY // --> <table id='templateBody' style='height: 441px;' border='0' width='734' cellspacing='0' cellpadding='0'> <tbody> <tr> <td class='bodyContent' style='width: 730px;' valign='top'> <h3>Se&ntilde;or Contribuyente:<br /><br /></h3> <h3 style='text-align: justify;'><em>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Por intermedio de la presente, se pone en conocimiento que su solicitud de Certificaci&oacute;n de Sistemas, fue registrado satisfactoriamente en Impuestos Nacionales. A partir de la fecha puede realizar las pruebas de conectividad y consumo de servicios web, seg&uacute;n detalle adjunto.</em></h3> <h5>&nbsp;</h5> <h5>&nbsp;</h5> <h5>&nbsp;</h5> <h5><br />Atentamente,<br /> Servicio de Impuestos Nacionales</h5> </td> </tr> <tr> <td class='bodyContent' style='width: 730px;' valign='top'>&nbsp;</td> </tr> </tbody> </table> <table id='bodyTable' border='0' width='100%' cellspacing='0' cellpadding='0' align='center'> <tbody> <tr> <td id='bodyCell' align='center' valign='top'><!-- BEGIN TEMPLATE // --> <table id='templateContainer' border='0' cellspacing='0' cellpadding='0'> <tbody> <tr> <td align='center' valign='top'><!-- BEGIN PREHEADER // --> <!-- // END PREHEADER --></td> </tr> <tr> <td align='center' valign='top'><!-- BEGIN HEADER // --> <table id='templateHeader' style='height: 81px;' border='0' width='744' cellspacing='0' cellpadding='0'> <tbody> <tr> <td class='headerContent' style='width: 742px;' align='center' valign='top' bgcolor='#032650' height='80px'><br /> <h5><span style='color: #ffffff;'><em><strong>Direcci&oacute;n: Calle Ballivi&aacute;n Nro 1333</strong></em><strong>, <em>La Paz - Bolivia</em></strong>&nbsp;</span><br /><span style='color: #ffffff;'><strong>siat.produccion@impuestos.gob.bo&nbsp;- L&iacute;nea Gratuita 800-10-34-44</strong></span></h5> </td> </tr> </tbody> </table> <!-- // END BODY --></td> </tr> <tr> <td align='center' valign='top'>&nbsp;</td> </tr> </tbody> </table> <!-- // END TEMPLATE --></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </center>";
			
			String asunto = ConstFacturacion.TEXTO_ASUNTO_CORREO;
			boolean vFueEnviado=false;
			
			ReportesController vReportesController = new ReportesController();	
			byte[] vReporteJasper = null;		
			vReporteJasper = vReportesController.ReportePruebasSistemasContenidoAdjuntoRuta(this.getReporteDatosSolicitudCertificacionDto(), this.getImagenRuta(), this.getReporteRuta());						
			
			if(vReporteJasper != null)
			{
				for (SolicitudContactosCertificacionesDto contacto : contactos) 
				{
					vFueEnviado = this.submitAdjunto(contacto.getCorreo(), asunto, vMensaje, vReporteJasper);				
				}
			}
			
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(this.contactos));
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
			
			if(!vFueEnviado)
			{
				LOG.error("No fue Evnviado Destino: "+ pDestino +" Asunto: "+ pAsunto);
			}
		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pDestino));
			LOG.error(e.getMessage());
			vFueEnviado=false;
		}
		
		return vFueEnviado;
	}
	
	public boolean submitAdjunto(String pDestino, String pAsunto, String pMensaje, byte[] bytesArray) 
	{
		boolean vFueEnviado=false;
		Email e = new Email();
		RestUrl vConf = new RestUrl();
		String vRutaEmisor = vConf.getPropetyValue("correo_emisor_facturacion");
		
		try
		{
			String[] destinos = new String[1];
			destinos[0]=pDestino;
			String[] copias = new String[0];
			String[] copiasOcultas = new String[0];
			String desde = vRutaEmisor;			
			String asunto = pAsunto;
			boolean autenticado=true;			
			List<byte[]>adjuntos = new ArrayList<>();
			adjuntos.add(bytesArray);
			List<String> nombresAdjuntos = new ArrayList<>();
			nombresAdjuntos.add("SiatPruebas.pdf");
			vFueEnviado = e.envioCorreoHTMLAdjuntos(destinos, copias, copiasOcultas, desde, pMensaje, asunto, autenticado, adjuntos, nombresAdjuntos);
			
			if(!vFueEnviado)
			{
				LOG.error("No fue Evnviado Destino: "+ pDestino +" Asunto: "+ pAsunto);
			}
				
		}
		catch (Exception ex) 
		{
			LogExcepcion.registrar(ex, LOG, MethodSign.build(pDestino));
			LOG.error(ex.getMessage());
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

	public List<SolicitudContactosCertificacionesDto> getContactos() {
		return contactos;
	}

	public void setContactos(List<SolicitudContactosCertificacionesDto> contactos) {
		this.contactos = contactos;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public byte[] getReporteByte() {
		return reporteByte;
	}

	public void setReporteByte(byte[] reporteByte) {
		this.reporteByte = reporteByte;
	}

	public ReporteDatosSolicitudCertificacionDto getReporteDatosSolicitudCertificacionDto() {
		return reporteDatosSolicitudCertificacionDto;
	}

	public void setReporteDatosSolicitudCertificacionDto(
			ReporteDatosSolicitudCertificacionDto reporteDatosSolicitudCertificacionDto) {
		this.reporteDatosSolicitudCertificacionDto = reporteDatosSolicitudCertificacionDto;
	}

	public String getImagenRuta() {
		return imagenRuta;
	}

	public void setImagenRuta(String imagenRuta) {
		this.imagenRuta = imagenRuta;
	}

	public String getReporteRuta() {
		return reporteRuta;
	}

	public void setReporteRuta(String reporteRuta) {
		this.reporteRuta = reporteRuta;
	}	
}