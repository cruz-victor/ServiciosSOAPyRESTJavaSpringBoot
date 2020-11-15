package bo.gob.sin.sre.fac.controller;


import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.CertificacionSolicitudCertificacionList;
import bo.gob.sin.sre.fac.model.CertificacionSolicitudCertificacionModel;
import bo.gob.sin.sre.fac.model.ComportamientoPaginaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.reports.ReportesController;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.sre.fac.thread.HiloEncolador;
//import bo.gob.sin.sre.fac.thread.HiloEncolador;
import net.sf.jasperreports.engine.JRException;

/**
* @Descripcion: Inicia Solicitud el Proveedor.
* @author: Wilson Limachi.
* @version: 1.0.0.1/25/06/2018.
*/

@ManagedBean(name = "solicitudCertificacionController")
@RequestScoped
public class SolicitudCertificacionController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{certificacionSolicitudCertificacionModel}")
	CertificacionSolicitudCertificacionModel certificacionSolicitudCertificacionModel;
	
	@ManagedProperty(value = "#{certificacionSolicitudCertificacionList}")
	CertificacionSolicitudCertificacionList certificacionSolicitudCertificacionList;
	
	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;
	
	@ManagedProperty(value="#{comportamientoPaginaModel}")
	ComportamientoPaginaModel comportamientoPaginaModel;
	
	@ManagedProperty(value = "#{reportesController}")
	ReportesController reportesController;	

	@ManagedProperty(value = "#{metodosPadron}")
    private MetodosPadron metodosPadron;	
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;

	/**
	* @Descipcion: Descripción Controla el estado del cambio de Pagina de estado.
	* @author: Wilson Limachi. 
	*/
	public void Load() 
	{		
		Long vNit =  this.contextoUsuarioModel.getContribuyente().getNit();
		
		if(vNit != null)
		{	
			this.metodosPadron.validacionSolicitudCertificacion(vNit); 	
	
			this.getCertificacionParametrics().obtenerTipoSistema();
			this.getCertificacionParametrics().obtenerCertificacionModalidadFacturacionGrupo();
			this.getCertificacionParametrics().obtenerTipoModulos();	
			this.getCertificacionParametrics().obtenerTipoDocumentoFactura();
			this.getCertificacionParametrics().obtenerTipodocumentoIdentidad();		
			this.getComportamientoPaginaModel().setEsDisableControl(false);
		}
	}
	
	/**
	* @Descipcion: Cancela el proceso y restablece valores. 
	* @author: Wilson Limachi. 
	* @param e: Evento semántico que indica que se produjo una acción definida por el componente
	*/
	public void CancelarFormulario(ActionEvent e)
	{
		this.getCertificacionSolicitudCertificacionModel().borrarDatos();
		this.getCertificacionSolicitudCertificacionList().setContactos(new SolicitudContactosCertificacionesDto());
		this.getCertificacionSolicitudCertificacionList().setListaContactos(new ArrayList<>());
		this.getReportesController().setRespuestaBase64("");
		this.getComportamientoPaginaModel().setEsDisableControl(false);
	}
	
	/**
	* @Descipcion: Recupera los datos del sistema para actualizar.
	* @author: Wilson Limachi.
	* @param e: Representa el comportamiento del componente específico de Ajax
	*/
	public void SeleccionCombo(AjaxBehaviorEvent pValueChangeEvent)
	{
		Integer vIdTipoSistema= (Integer) ((UIOutput) pValueChangeEvent.getSource()).getValue();	
		getCertificacionSolicitudCertificacionModel().getSolicitud().setTipoSistemaId(vIdTipoSistema);
	}

	/**
	* @Descipcion: Cancela el proceso y restablece valores. 
	* @author: Wilson Limachi. 
	* @param e: Evento semántico que indica que se produjo una acción definida por el componente
	*/
	public void GuardarSolicitud(ActionEvent e) throws IOException 
	{
		RespuestaRegistrarSolicitudCertificacionDto vResultado = new RespuestaRegistrarSolicitudCertificacionDto();
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		getCertificacionSolicitudCertificacionModel().getSolicitud().setListaContactos(this.getCertificacionSolicitudCertificacionList().getListaContactos());
				
		Map<String, String> errores = new HashMap<String, String>();
		errores =  this.certificacionSolicitudCertificacionList.erroresGrillaContactos();
		
		if(errores.isEmpty())
		{
				RespuestaEstaRegistradoGenericoDto resultadoVerificar = new RespuestaEstaRegistradoGenericoDto();
				SolicitudVerificacionSistemaRegistradoDto solicitudVerficacion = new SolicitudVerificacionSistemaRegistradoDto();
				
				solicitudVerficacion.setContribuyenteId(getContextoUsuarioModel().getContribuyente().getIfc());
				solicitudVerficacion.setListaModalidad(getCertificacionSolicitudCertificacionModel().getSolicitud().getListaModalidad());
				solicitudVerficacion.setNombreSistema(getCertificacionSolicitudCertificacionModel().getSolicitud().getNombreSistema());
				solicitudVerficacion.setNit(this.getContextoUsuarioModel().getContribuyente().getNit());
				solicitudVerficacion.setTipoSistema(this.getCertificacionSolicitudCertificacionModel().getSolicitud().getTipoSistemaId());			
				
				resultadoVerificar = serviciosFacturacionRest.verificacionSistemaRegistrado(solicitudVerficacion);
				
				if(!resultadoVerificar.isEstaRegistrado())
				{				
					getCertificacionSolicitudCertificacionModel().getSolicitud().setUsuarioId(getContextoUsuarioModel().getUsuario().getUsuarioId());
					getCertificacionSolicitudCertificacionModel().getSolicitud().setContribuyenteId(this.getContextoUsuarioModel().getContribuyente().getIfc());												
					
					getCertificacionSolicitudCertificacionModel().getSolicitud().setNit(this.getContextoUsuarioModel().getContribuyente().getNit());
					getCertificacionSolicitudCertificacionModel().getSolicitud().setTipoDepartamentoId(Integer.parseInt((this.getContextoUsuarioModel().getContribuyente().getDependenciaJurisdiccionId()+"").substring(0, 1)));
					//para el inicio de tramite es la oficina 0
					getCertificacionSolicitudCertificacionModel().getSolicitud().setOficinaId(0);
					vResultado = serviciosFacturacionRest.registrarSolicitudSistema(getCertificacionSolicitudCertificacionModel().getSolicitud());													
										
					mensajesBean.addMensajes(vResultado);
					
					if (vResultado.isOk()) 
					{
						this.getCertificacionSolicitudCertificacionModel().getSolicitudCertificacionDto().getRegistroSolicitud().setSolicitudCertificacionId(vResultado.getSolicitudCertificacionId());
						this.getCertificacionSolicitudCertificacionModel().getSolicitudCertificacionDto().getRegistroSolicitud().setSistemaId(vResultado.getSistemaId());
						this.getComportamientoPaginaModel().setEsDisableControl(true);
						
						try
						{
						    this.EnviarReporteCorreo(vResultado);
						}
						catch (Exception e2)
						{
							RequestContext.getCurrentInstance().execute("toastr.error('Error en la generación del reporte', '')");
						}
						
						this.getCertificacionSolicitudCertificacionModel().borrarDatos();		
						
						RequestContext.getCurrentInstance().execute("rc2();");
					}
					else {
						RequestContext.getCurrentInstance().execute("toastr.error('Error en la generación dela solicitud', '')");
					}					
				}
				else
				{
					mensajesBean.addMensajes(resultadoVerificar);
				}
		}
		else
		{
			for (String key : errores.keySet())
			{
				RequestContext.getCurrentInstance().execute("toastr.warning('"+ errores.get(key) + "', '')");
			}
		}
	}
	
	/**
	* @Descipcion: Genera un reporte de la solicitud.
	* @author: Wilson Limachi. 
	 * @throws JRException 
	 * @throws IOException 
	*/
	public String GenerarReporteDeCertificacion() throws IOException, JRException
	{			
		if(this.getReportesController().getRespuestaBase64().equals(" "))
		{
			RespuestaDatosSistemasSolCertificacionDto vReporteSolicitudCertificacionDto = new RespuestaDatosSistemasSolCertificacionDto();
			ReporteDatosSolicitudCertificacionDto vRespuestaDatosSolicitud = new ReporteDatosSolicitudCertificacionDto();
			ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();		
	
			vReporteSolicitudCertificacionDto.setSolicitudCertificacionId(this.getCertificacionSolicitudCertificacionModel().getSolicitudCertificacionDto().getRegistroSolicitud().getSolicitudCertificacionId());
			vReporteSolicitudCertificacionDto.setSistemaId( this.getCertificacionSolicitudCertificacionModel().getSolicitudCertificacionDto().getRegistroSolicitud().getSistemaId());
			
			vRespuestaDatosSolicitud = vServiciosFacturacionRest.recuperarDatosReporteSolicitudCertificacion(vReporteSolicitudCertificacionDto);
			
			byte[] vReporteJasper = null;
			
			if(vRespuestaDatosSolicitud.isOk())
			{	
				String rutaImagen = "images/logo.png";
				String rutaReporte = "reports/Recaudaciones/Facturacion/CertificacionSistemas/SolicitudCertificacionSistemasReport.jasper";
				
				rutaImagen = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaImagen);	
				rutaReporte = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaReporte);
				
				vReporteJasper = reportesController.ReportePruebasSistemasContenidoAdjuntoRuta(vRespuestaDatosSolicitud, rutaImagen, rutaReporte);				
				
				if(vReporteJasper==null)
				{
					RequestContext.getCurrentInstance().execute("toastr.error('Error en la generación del Reporte', '')");
				}
			}
			else
			{
				System.out.println("Error en la generación del reporte");
				RequestContext.getCurrentInstance().execute("toastr.error('Error en la generación del reporte', '')");
			}
		}
			
		return this.getReportesController().getRespuestaBase64();
		
	}
	
	/**
	* @Descipcion: Genera un reporte de la solicitud Adjunto.
	* @author: Wilson Limachi. 
	*/
	public ReporteDatosSolicitudCertificacionDto ObtieneDatosReporteCertificacionAdjunto()
	{		
		RespuestaDatosSistemasSolCertificacionDto vReporteSolicitudCertificacionDto = new RespuestaDatosSistemasSolCertificacionDto();
		ReporteDatosSolicitudCertificacionDto vRespuestaDatosSolicitud = new ReporteDatosSolicitudCertificacionDto();
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();		

		vReporteSolicitudCertificacionDto.setSolicitudCertificacionId(this.getCertificacionSolicitudCertificacionModel().getSolicitudCertificacionDto().getRegistroSolicitud().getSolicitudCertificacionId());
		vReporteSolicitudCertificacionDto.setSistemaId( this.getCertificacionSolicitudCertificacionModel().getSolicitudCertificacionDto().getRegistroSolicitud().getSistemaId());		
		
		vRespuestaDatosSolicitud = vServiciosFacturacionRest.recuperarDatosReporteSolicitudCertificacion(vReporteSolicitudCertificacionDto);		
		
		return vRespuestaDatosSolicitud;
	}
	
	/**
	* @Descipcion: Genera reporte de Solicitud. 
	* @author: Wilson Limachi. 
	* @param pRespuestaRegistrarSolicitudCertificacionDto: parámetro de entrada que contiene la solicitud de certificacion registrada.
	*/
	public void EnviarReporteCorreo(RespuestaRegistrarSolicitudCertificacionDto pRespuestaRegistrarSolicitudCertificacionDto) throws UnsupportedEncodingException
	{	
		ReporteDatosSolicitudCertificacionDto vRespuestaDatosSolicitud = new ReporteDatosSolicitudCertificacionDto();
		
		vRespuestaDatosSolicitud = this.ObtieneDatosReporteCertificacionAdjunto();	
		
		if(vRespuestaDatosSolicitud.isOk())
		{
			this.getReportesController().setRespuestaBase64(" ");
			this.EnviarCorreosContactos(vRespuestaDatosSolicitud, this.getCertificacionSolicitudCertificacionModel().getSolicitud().getListaContactos(), pRespuestaRegistrarSolicitudCertificacionDto.getSolicitudCertificacionId());
		}
		else
		{
			mensajesBean.addMensajes(vRespuestaDatosSolicitud);
		}		
	}		
	
	/**
	* @Descipcion: Envia masivamente de la lista de contactos. 
	* @author: Wilson Limachi. 
	* @param pRespuestaRegistrarSolicitudCertificacionDto: parámetro de entrada que contiene la solicitud de certificacion registrada.
	*/
	public void EnviarCorreosContactos(ReporteDatosSolicitudCertificacionDto pReporteDatosSolicitudCertificacionDto , List<SolicitudContactosCertificacionesDto> pContactos, Long pSolicitudCertificacionId)
	{
		
		String rutaImagen = "images/logo.png";
		String rutaReporte = "reports/Recaudaciones/Facturacion/CertificacionSistemas/SolicitudCertificacionSistemasReport.jasper";
		
		rutaImagen = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaImagen);
		rutaReporte = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaReporte);
		
		HiloEncolador hilo = new HiloEncolador();
		hilo.setContactos(pContactos);	
		hilo.setSolicitudCertificacionId(pSolicitudCertificacionId);
		hilo.setReporteDatosSolicitudCertificacionDto(pReporteDatosSolicitudCertificacionDto);
		hilo.setImagenRuta(rutaImagen);
		hilo.setReporteRuta(rutaReporte);
		hilo.start();		
	}		
	
	public void tieneErroresValidacion(String pNumeroErrores)
	{
		if(pNumeroErrores.length()>0)
		{
			int vValor= Integer.parseInt(pNumeroErrores);
			
			if(vValor<=0)
			{
				this.getContextoUsuarioModel().RedireccionarIndex();
			}						
		}
		else
		{
			this.getContextoUsuarioModel().RedireccionarIndex();
		}
	}

	public CertificacionSolicitudCertificacionModel getCertificacionSolicitudCertificacionModel() {
		return certificacionSolicitudCertificacionModel;
	}

	public void setCertificacionSolicitudCertificacionModel(CertificacionSolicitudCertificacionModel certificacionSolicitudCertificacionModel) 
	{		
		this.certificacionSolicitudCertificacionModel = certificacionSolicitudCertificacionModel;
	}
	
	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}
	public ReportesController getReportesController() {
		return reportesController;
	}

	public void setReportesController(ReportesController reportesController) {
		this.reportesController = reportesController;
	}
	
	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public CertificacionSolicitudCertificacionList getCertificacionSolicitudCertificacionList() {
		return certificacionSolicitudCertificacionList;
	}

	public void setCertificacionSolicitudCertificacionList(
			CertificacionSolicitudCertificacionList certificacionSolicitudCertificacionList) {
		this.certificacionSolicitudCertificacionList = certificacionSolicitudCertificacionList;
	}

	public MetodosPadron getMetodosPadron() {
		return metodosPadron;
	}

	public void setMetodosPadron(MetodosPadron metodosPadron) {
		this.metodosPadron = metodosPadron;
	}

	public ComportamientoPaginaModel getComportamientoPaginaModel() {
		return comportamientoPaginaModel;
	}

	public void setComportamientoPaginaModel(ComportamientoPaginaModel comportamientoPaginaModel) {
		this.comportamientoPaginaModel = comportamientoPaginaModel;
	}
}
