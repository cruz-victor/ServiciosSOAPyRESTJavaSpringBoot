package bo.gob.sin.sre.fac.reports;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.SolicitudCertificacionDto;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "reportesController")
@ViewScoped
public class ReportesController implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String respuestaBase64 = "";	

	public JasperPrint ReportePruebasSistemasContenido(ReporteDatosSolicitudCertificacionDto pReporteSolicitudCertificacionDto) throws IOException 
	{
		byte[] reportePdf = null;
		String rutaReporte = "reports/Recaudaciones/Facturacion/CertificacionSistemas/SolicitudCertificacionSistemasReport.jasper";
		String rutaImagen = "images/SIAT.jpg";
		Image vlogo=null;
		File file = null;
		
		file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaImagen));	
		
		vlogo = ImageIO.read(new FileInputStream(file));

		System.out.println("----------- inicio reporte Certificacion----------");
		File vReporte = null;
		vReporte = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaReporte));
		
		JasperPrint vReporteJasper = null;
		HashMap vParametrosReporte = new HashMap();

		vParametrosReporte.put("DataSource", pReporteSolicitudCertificacionDto);
		vParametrosReporte.put("ListaRutasAbajo", pReporteSolicitudCertificacionDto.getDocumentos().get(0));
		vParametrosReporte.put("Image", vlogo);

		try 
		{
			vReporteJasper = JasperFillManager.fillReport(vReporte.getPath(), vParametrosReporte,new JREmptyDataSource());
			reportePdf = JasperExportManager.exportReportToPdf(vReporteJasper);
			respuestaBase64 = Base64.getEncoder().encodeToString(reportePdf);
		} 
		catch (JRException e1) 
		{
			respuestaBase64 = "";
			vReporteJasper = null;
			e1.printStackTrace();
		}

		return vReporteJasper;
	}
	
	public byte[] ReportePruebasSistemasContenidoAdjuntoRuta(ReporteDatosSolicitudCertificacionDto pReporteSolicitudCertificacionDto, String vRuta, String vRutaReporte) throws IOException, JRException 
	{
		byte[] reportePdf = null;
		JasperPrint vReporteJasper = null;
		
		try 
		{
			RestUrl vConf = new RestUrl();
			String vdireccionEspecificacion="";			
			vdireccionEspecificacion = vConf.getPropetyValue("ruta_especificacion_lista");			

			Image vlogo=null;
			File file = null;
			
			file = new File(vRuta);				
			vlogo = ImageIO.read(new FileInputStream(file));
	
			System.out.println("----------- inicio reporte Certificacion----------");
			File vReporte = null;
			vReporte = new File(vRutaReporte);
						
			HashMap vParametrosReporte = new HashMap();	
			
			vParametrosReporte.put("DataSource", pReporteSolicitudCertificacionDto);
			vParametrosReporte.put("Image", vlogo);			
			vParametrosReporte.put("direccionEspecificacion", vdireccionEspecificacion);		
			
			vReporteJasper = JasperFillManager.fillReport(vReporte.getPath(), vParametrosReporte,new JREmptyDataSource());			
			
			reportePdf = JasperExportManager.exportReportToPdf(vReporteJasper);
			respuestaBase64 = Base64.getEncoder().encodeToString(reportePdf);
		} 
		catch (JRException e1) 
		{
			respuestaBase64 = "";
			vReporteJasper = null;
			System.out.println("Error en metodo ReportePruebasSistemasContenidoAdjuntoRuta");
			e1.printStackTrace();
		}		

		return reportePdf;
	}


	public String getRespuestaBase64() {
		return respuestaBase64;
	}

	public void setRespuestaBase64(String respuestaBase64) {
		this.respuestaBase64 = respuestaBase64;
	}

	/**
	 * Reporte de componentes registrados por el contribuyente
	 * 
	 * @author rosario.garcia        
	 * @fecha: 01/12/2018                
	 */
	public JasperPrint ReporteComponentesRegistradosContribuyente(ReporteComponentesRegistradosCtbteDto pListaComponentes)
		throws UnsupportedEncodingException, JRException, ParseException {
		String pNombreSistema = pListaComponentes.getNombreSistema();
		String pModalidadSistema = pListaComponentes.getDescripcionModalidadFacturacionId();
		byte[] vReportePdf = null;
		JasperPrint vReporteJasper = null;
		File vReporte = null;
		System.out.println("----------- inicio reporte registro de componentes y archivos registrados ----------"+pNombreSistema);
		String vRutaReporte = "reports/Recaudaciones/Facturacion/ReporteComponentesRegistradosContribuyente.jasper";
		vReporte = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(vRutaReporte));

		Map<String, Object> vParametrosReporte = new HashMap<String, Object>();
		vParametrosReporte.put("nombreSistema", pNombreSistema);
		vParametrosReporte.put("descripcionModalidadFacturacionId", pModalidadSistema);
		vParametrosReporte.put("fechaRegistro", new SimpleDateFormat("dd/MM/yyyy").format(pListaComponentes.getFechaRegistro()));
		
		JRBeanCollectionDataSource itemsJRBeans = new JRBeanCollectionDataSource(pListaComponentes.getListaComponentesContribuyente());
		vParametrosReporte.put("ItemDataSource", itemsJRBeans);
	
		try {
			vReporteJasper = JasperFillManager.fillReport(vReporte.getPath(), vParametrosReporte, new JREmptyDataSource());
			vReportePdf = JasperExportManager.exportReportToPdf(vReporteJasper);
			respuestaBase64 = Base64.getEncoder().encodeToString(vReportePdf);
		} catch (JRException e1) {
			respuestaBase64 = "";
			vReporteJasper = null;
			e1.printStackTrace();
		}
		return vReporteJasper;
	}
	
	/**
	 * @descripcion: Recupera el archivo JasperPrint para mostrar el reporte de Seguimiento de Certificación de Sistemas 
	 * @author: junior.flores.
	 * @fecha: 17/06/2019
	 * @param pSistemasContribuyentesDto: Información del contribuyente
	 * 		  pReporteSolicitudCertificacionDto: Información del Seguimiento de Certificación de Sistemas.
	 * 		  pSeguimientoCertificacionSistemasDto: Dto del Seguimiento de Certificación de Sistemas.
	 */
	private JasperPrint ReporteSeguimientoCertiticacionSistema(SistemasContribuyentesDto pSistemasContribuyentesDto,List<SeguimientoDetalleCertificacionSistemasDto>  pReporteSolicitudCertificacionDto,SeguimientoCertificacionSistemasDto pSeguimientoCertificacionSistemasDto) throws IOException, JRException 
	{		
		String rutaReporte ="";
		switch (pSeguimientoCertificacionSistemasDto.getEtapa_certificacion_sistemas_id()) {
		case 2851:
			rutaReporte = "reports/Recaudaciones/Facturacion/Certificacion/SeguimientoCertificacionSistemaReport1.jasper";
			break;
		case 2857:
			rutaReporte = "reports/Recaudaciones/Facturacion/Certificacion/SeguimientoCertificacionSistemaReport7.jasper";
			break;
		}
	
		String rutaImagen = "images/SIAT.jpg";
		Image vlogo=null;
		File file = null;
		
		file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaImagen));		
		vlogo = ImageIO.read(new FileInputStream(file));
		System.out.println("----------- inicio reporte Certificacion----------");
		File vReporte = null;
		vReporte = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaReporte));
		JasperPrint vReporteJasper = null;
		Map<String, Object> vParametrosReporte = new HashMap<String, Object>();
		vParametrosReporte.put("Image", vlogo);		
		vParametrosReporte.put("porcentaje", pSeguimientoCertificacionSistemasDto.getPorcentaje());
		vParametrosReporte.put("cantidadPruebas", pSeguimientoCertificacionSistemasDto.getCantidad_pruebas());
		Integer vCantidad=pReporteSolicitudCertificacionDto.get(0).getPorcentaje_avance()!=null ? pReporteSolicitudCertificacionDto.stream().mapToInt(SeguimientoDetalleCertificacionSistemasDto::getPorcentaje_avance).sum():0;
		Long vCantidadT = pReporteSolicitudCertificacionDto.stream().filter(t ->  t.isSatisfactorio()).count();
		vParametrosReporte.put("cantidadTotal",vCantidad);	
		vParametrosReporte.put("cantidadTotalT", vCantidadT);
		vParametrosReporte.put("cantidadTotalP", (int) (pSeguimientoCertificacionSistemasDto.getCantidad_pruebas()-vCantidad));
		vParametrosReporte.put("cantidadTotalPendiente", (Long) (pSeguimientoCertificacionSistemasDto.getCantidad_pruebas()-vCantidadT));
		if(pSistemasContribuyentesDto!=null) {
			vParametrosReporte.put("modalidadServicio", pSistemasContribuyentesDto.getModalidadFacturacion());
			vParametrosReporte.put("nombreContribuyente", pSistemasContribuyentesDto.getNombreContribuyente());
			vParametrosReporte.put("tipoSistema", pSistemasContribuyentesDto.getTipoSistema());
			vParametrosReporte.put("nombreSistema", pSistemasContribuyentesDto.getNombreSistema());
		}
		JRBeanCollectionDataSource itemsJRBeans = new JRBeanCollectionDataSource(pReporteSolicitudCertificacionDto);
		vParametrosReporte.put("DataSource", itemsJRBeans);

		try 
		{
			vReporteJasper = JasperFillManager.fillReport(vReporte.getPath(), vParametrosReporte,new JREmptyDataSource());
		} 
		catch (JRException e1) 
		{
			respuestaBase64 = "";
			vReporteJasper = null;
			e1.printStackTrace();
		}

		return vReporteJasper;
	}
	
	/**
	 * @descripcion: Recupera el archivo StreamedContent para mostrar el reporte de Seguimiento de Certificación de Sistemas 
	 * @author: junior.flores.
	 * @fecha: 17/06/2019
	 * @param pSistemasContribuyentesDto: Información del contribuyente
	 * 		  pReporteSolicitudCertificacionDto: Información del Seguimiento de Certificación de Sistemas. 	
	 * 		  pSeguimientoCertificacionSistemasDto: Dto del Seguimiento de Certificación de Sistemas.	  
	 */
	public StreamedContent ReporteSeguimientoCertiticacionSistemas(SistemasContribuyentesDto pSistemasContribuyentesDto, List<SeguimientoDetalleCertificacionSistemasDto>  pReporteSolicitudCertificacionDto, SeguimientoCertificacionSistemasDto pSeguimientoCertificacionSistemasDto)
			throws UnsupportedEncodingException, JRException, ParseException {
		JasperPrint vReporteJasper = null;
		byte[] reportePdf = null;

		try {
			vReporteJasper = ReporteSeguimientoCertiticacionSistema(pSistemasContribuyentesDto,pReporteSolicitudCertificacionDto, pSeguimientoCertificacionSistemasDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportePdf = JasperExportManager.exportReportToPdf(vReporteJasper);
		InputStream fis = new ByteArrayInputStream(reportePdf);
		StreamedContent salida = new DefaultStreamedContent(fis, "application/pdf; charset=UTF-8", "Reporte1.pdf");

		System.out.println("----------- fin reporte pruebas ----------");
		return salida;
	}
	
	/**
	 * Reporte Cancelacion Solicitud de certificación
	 * 
	 * @author Wilson Limachi Choque  
	 * @fecha:11/07/2019                
	 */
	public JasperPrint ReporteCancelacionSolicitudCertificacion(Long pUsuarioId,long pNit,String pRazonSocial,String pCite,SolicitudCertificacionDto pSolicitud) {
		byte[] reportePdf = null;
		String rutaReporte = "reports/Recaudaciones/Facturacion/CancelacionSolicitudCertificacion.jasper";
		System.out.println("----------- inicio reporte Cancelacion Solicitud Certificación----------");
		File vReporte = null;
		vReporte = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaReporte));
		JasperPrint vReporteJasper = null;
		Map<String, Object> vParametrosReporte = new HashMap<String, Object>();

		vParametrosReporte.put("usuarioId", new String(pUsuarioId + ""));
		vParametrosReporte.put("cite", pCite);
		vParametrosReporte.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		vParametrosReporte.put("nit", new String(pNit + ""));
		vParametrosReporte.put("razonSocial",pRazonSocial);
		vParametrosReporte.put("sistema", pSolicitud.getNombreSistema());
		vParametrosReporte.put("modalidad", pSolicitud.getDescripcionModalidad());
		vParametrosReporte.put("version", pSolicitud.getVersion());
		vParametrosReporte.put("fechaSolicitud", new SimpleDateFormat("dd/MM/yyyy").format(pSolicitud.getFechaSolicitud()));

		try {
			vReporteJasper = JasperFillManager.fillReport(vReporte.getPath(), vParametrosReporte, new JREmptyDataSource());
			reportePdf = JasperExportManager.exportReportToPdf(vReporteJasper);
			respuestaBase64 = Base64.getEncoder().encodeToString(reportePdf);
		} catch (JRException e1) {
			respuestaBase64 = "";
			vReporteJasper = null;
			e1.printStackTrace();
		}
		return vReporteJasper;
	}
}
