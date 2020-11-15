package bo.gob.sin.sre.fac.controller;


import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.CertificacionSolicitudCertificacionList;
import bo.gob.sin.sre.fac.model.CertificacionSolicitudCertificacionModel;
import bo.gob.sin.sre.fac.model.ComportamientoPaginaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.SolicitudCertificacionList;
import bo.gob.sin.sre.fac.reports.ReportesController;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.sre.fac.thread.HiloEncolador;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings("deprecation")
@ManagedBean(name = "cancelarSolicitudCertificacionController")
@RequestScoped
public class CancelarSolicitudCertificacionController  implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{certificacionSolicitudCertificacionList}")
	CertificacionSolicitudCertificacionList certificacionSolicitudCertificacionList; 
	
	@ManagedProperty(value = "#{certificacionSolicitudCertificacionModel}")
	CertificacionSolicitudCertificacionModel certificacionSolicitudCertificacionModel;
	
	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;
	
	@ManagedProperty(value = "#{comportamientoPaginaModel}")
	ComportamientoPaginaModel comportamientoPaginaModel;

	@ManagedProperty(value = "#{solicitudCertificacionList}")
	SolicitudCertificacionList solicitudCertificacionList;
	
	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;

	@ManagedProperty(value = "#{reportesController}")
	ReportesController reportesController;
	
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	@ManagedProperty(value = "#{correoBean}")
	CorreoController correoController;



	/**
	 * @Descipcion: Carga los valores de la lista de sistemas del contribuyente.
	 * @author: Wilson Limachi.
	 */
	public void Load() 
	{
		this.getCertificacionParametrics().obtenerCertificacionModalidadFacturacionGrupo();
		this.getCertificacionParametrics().obtenerTipodocumentoIdentidad();		
		
		this.getSolicitudCertificacionList().recuperarListaEnProcesoSolicitudes(this.getContextoUsuarioModel().getContribuyente().getIfc());
		this.comportamientoPaginaModel.setEsEditable(false);
	}

	/**
	 * @Descipcion: Cancela el estado de la solicitud.
	 * @author: Wilson Limachi.
	 */
	public void cancelarSolicitudCertificacion(SolicitudCertificacionDto pSolicitudCertificacion) 
	{
		List<SolicitudCertificacionDto> listaEstadoCertificacionAux = new ArrayList<>();
		SolicitudSolicitudCertificacionDto vSolicitud = new SolicitudSolicitudCertificacionDto();
		RespuestaActualizacionGenericoDto vRespuesta = new RespuestaActualizacionGenericoDto();
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		
		listaEstadoCertificacionAux = this.getSolicitudCertificacionList().getListaEstadoCertificacion();		
		int vPosicion = this.getSolicitudCertificacionList().getListaEstadoCertificacion().indexOf(pSolicitudCertificacion);			
		Long vUsuario = this.getContextoUsuarioModel().getUsuario().getUsuarioId();
		vSolicitud.setSolicitudCertificacionId(pSolicitudCertificacion.getSolicitudCertificacionId());
		vSolicitud.setUsuarioId(vUsuario);			

		vRespuesta = vServiciosFacturacionRest.cancelarSolicitudCertificacion(vSolicitud);
		mensajesBean.addMensajes(vRespuesta);
		
		if (vRespuesta.isOk()) 
		{
			this.getSolicitudCertificacionList().recuperarListaEnProcesoSolicitudes(this.getContextoUsuarioModel().getContribuyente().getIfc());
			
			SolicitudCertificacionDto vSolicitudCertificacionDto = this.getSolicitudCertificacionList().getListaEstadoCertificacion().stream().filter(sistema -> (pSolicitudCertificacion.getSolicitudCertificacionId()+"").equals(sistema.getSolicitudCertificacionId()+"")).findFirst().orElse(null);
			
			if(!vSolicitudCertificacionDto.equals(null))
			{
				listaEstadoCertificacionAux.set(vPosicion, vSolicitudCertificacionDto);
				this.getSolicitudCertificacionList().setListaEstadoCertificacion(listaEstadoCertificacionAux);
			}
		}				
	}
	
	/**
	* @Descipcion: Genera reporte de Solicitud. 
	* @author: Wilson Limachi. 
	* @param pRespuestaRegistrarSolicitudCertificacionDto: parámetro de entrada que contiene la solicitud de certificacion registrada.
	* @fecha: 11/07/2019 
	*/
	public void GenerarReporteSolicitudCertificacion(SolicitudCertificacionDto pSolicitudCertificacionDto)
	{	
		String rutaImagen = "images/logo.png";
		String rutaReporte = "reports/Recaudaciones/Facturacion/CertificacionSistemas/SolicitudCertificacionSistemasReport.jasper";
		
		if(pSolicitudCertificacionDto.getTipoSolicitudCertificacionId() == ConstFacturacion.TIPO_SOLICITUD_ID_ACTUALIZACION)
		{
			rutaReporte = "reports/Recaudaciones/Facturacion/CertificacionSistemas/SolicitudReCertificacionSistemasReport.jasper";
		}
		
		rutaImagen = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaImagen);	
		rutaReporte = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaReporte);
		
		
		ReporteDatosSolicitudCertificacionDto vRespuestaDatosSolicitud = new ReporteDatosSolicitudCertificacionDto();
		
		RespuestaDatosSistemasSolCertificacionDto vReporteSolicitudCertificacionDto = new RespuestaDatosSistemasSolCertificacionDto();
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();		

		vReporteSolicitudCertificacionDto.setSolicitudCertificacionId(pSolicitudCertificacionDto.getSolicitudCertificacionId());
		vReporteSolicitudCertificacionDto.setSistemaId(pSolicitudCertificacionDto.getSistemaId());		
		
		vRespuestaDatosSolicitud = vServiciosFacturacionRest.recuperarDatosReporteSolicitudCertificacion(vReporteSolicitudCertificacionDto);		
		
		mensajesBean.addMensajes(vRespuestaDatosSolicitud);
		
		if(vRespuestaDatosSolicitud.isOk())
		{
			byte[] vReporteJasper = null;
			
			try 
			{
				vReporteJasper = reportesController.ReportePruebasSistemasContenidoAdjuntoRuta(vRespuestaDatosSolicitud, rutaImagen, rutaReporte);
			} 
			catch (IOException e) 
			{
				vReporteJasper = null;
				e.printStackTrace();
			} 
			catch (JRException e) 
			{
				vReporteJasper = null;
				e.printStackTrace();
			}				
			
			if(vReporteJasper==null)
			{
				RequestContext.getCurrentInstance().execute("toastr.error('Error en la generación del Reporte', '')");
			}
		}
		else
		{
			this.getReportesController().setRespuestaBase64("");			
		}		
	}	
	
	/**
	 * @Descipcion: Cancela el estado de la solicitud.
	 * @author: Wilson Limachi.
	 */
	public void initRecertificar(SolicitudCertificacionDto pSolicitudCertificacion)
	{
		this.getCertificacionParametrics().obtenerTipoDocumentoFactura();
		this.certificacionSolicitudCertificacionList.cargarListaContactos(pSolicitudCertificacion.getSolicitudCertificacionId());
		
		this.getCertificacionSolicitudCertificacionModel().getSolicitud().setSolicitudCertificacionId(pSolicitudCertificacion.getSolicitudCertificacionId());
		this.getCertificacionSolicitudCertificacionModel().getSolicitud().setSistemaId(pSolicitudCertificacion.getSistemaId());		
		this.getCertificacionSolicitudCertificacionModel().getSolicitud().setTipoSolicitudCertificacionId(pSolicitudCertificacion.getTipoSolicitudCertificacionId());
		
		RequestContext.getCurrentInstance().reset(":formCancelacionSolicitudSistemas:panelCuadroDialogo");
		this.comportamientoPaginaModel.setEsEditable(false);
		this.certificacionSolicitudCertificacionList.setContactos(new SolicitudContactosCertificacionesDto());
				
		DatosRecertificacion vDatosRecertificacion = new DatosRecertificacion();
		
		vDatosRecertificacion.setEntradaSistemaId(pSolicitudCertificacion.getSistemaId());
		vDatosRecertificacion.setEntradaSolicitudCertificacionId(pSolicitudCertificacion.getSolicitudCertificacionId());
		this.getCertificacionSolicitudCertificacionList().obtenerDatosReSolCertificacion(vDatosRecertificacion);
		
		this.getCertificacionSolicitudCertificacionModel().getSolicitud().setListaDocumentos(new ArrayList<>());	
		
		this.getCertificacionSolicitudCertificacionList().getDatosReCertificacion().getSalidaClasificadorTipoDocSistemas().forEach(item->
		{
			System.out.println("DatoRecuperados: "+item.getClasificadorId());
			ClasificadorDto vClasificadorDto = this.getCertificacionParametrics().getListaTipoDocumentoFactura().stream().filter(p ->((p.getClasificadorId()+"").equals(item.getClasificadorId()+""))).findFirst().orElse(null);
			
			if(vClasificadorDto != null)
			{
				System.out.println("DatoRecuperadosDentro: "+item.getClasificadorId()+" RecuperadoClasificador: "+ vClasificadorDto.getClasificadorId());
				this.getCertificacionParametrics().getListaTipoDocumentoFactura().remove(vClasificadorDto);
				System.out.println("tamaño parametrica: " + this.getCertificacionParametrics().getListaTipoDocumentoFactura().size());
			}
		});
		
		
		this.getCertificacionSolicitudCertificacionList().getDatosReCertificacion().getSalidaClasificadorTipoDocSistemas().forEach(f -> f.setTipoClasificador("/"));
		this.getCertificacionParametrics().getListaTipoDocumentoFactura().addAll(this.getCertificacionSolicitudCertificacionList().getDatosReCertificacion().getSalidaClasificadorTipoDocSistemas());
		
		certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setSistemaId(pSolicitudCertificacion.getSistemaId());
		certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setSolicitudCertificacionId(pSolicitudCertificacion.getSolicitudCertificacionId());
	}
	
	/**
	 * @Descipcion: Cancela el estado de la solicitud.
	 * @author: Wilson Limachi.
	 */
	public void recertificar()
	{
		certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setListaDocumentos(new ArrayList<>());
		
		if(this.certificacionSolicitudCertificacionList.getListaContactos().size()>0)
		{
			if(!this.comportamientoPaginaModel.isEsEditable())
			{	
				RespuestaRegistrarSolicitudCertificacionDto vRespuestaRegistrarSolicitudCertificacionDto = new RespuestaRegistrarSolicitudCertificacionDto();
				
				ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
				
				certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setListaContactos(this.certificacionSolicitudCertificacionList.getListaContactos());
				certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setListaDocumentos(this.certificacionSolicitudCertificacionModel.getSolicitud().getListaDocumentos());				
				

				
				this.getCertificacionSolicitudCertificacionList().getDatosReCertificacion().getSalidaClasificadorTipoDocSistemas().forEach(item->
				{
					certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().getListaDocumentos().add(item.getClasificadorId()+"");
				});
				
				this.getCertificacionSolicitudCertificacionList().getDatosReCertificacion().getSalidaClasificadorModalidades().forEach(item->
				{
					certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().getListaModalidad().add(item.getClasificadorId()+"");
				});
				
				certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setVersion(this.certificacionSolicitudCertificacionModel.getSolicitud().getVersion());
				
				certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setUsuarioId(getContextoUsuarioModel().getUsuario().getUsuarioId());
				certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setContribuyenteId(this.getContextoUsuarioModel().getContribuyente().getIfc());	
				certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion().setNit(this.getContextoUsuarioModel().getContribuyente().getNit());
				
				
				vRespuestaRegistrarSolicitudCertificacionDto = vServiciosFacturacionRest.registrarRecertificacionSolicitudSistema(certificacionSolicitudCertificacionModel.getpSolicitudRecertificacion());
				
				mensajesBean.addMensajes(vRespuestaRegistrarSolicitudCertificacionDto);
				
				if(vRespuestaRegistrarSolicitudCertificacionDto.isOk() && vRespuestaRegistrarSolicitudCertificacionDto.isEstaRegistrado())
				{
					this.getSolicitudCertificacionList().recuperarListaEnProcesoSolicitudes(this.getContextoUsuarioModel().getContribuyente().getIfc());
					RequestContext.getCurrentInstance().execute("PF('listaDatoContribuyentes').getPaginator().setPage(0)");
					
					try 
					{
						this.EnviarReporteCorreo(vRespuestaRegistrarSolicitudCertificacionDto);
					} 
					catch (UnsupportedEncodingException e) 
					{
						e.printStackTrace();
						RequestContext.getCurrentInstance().execute("toastr.error('Error en la generación del reporte', '')");
					}
				}
				
				RequestContext.getCurrentInstance().execute("PF('cuadroDialogo').hide();");
			}
			else
			{
				RequestContext.getCurrentInstance().execute("toastr.info('No ha terminado de adicionar el contacto', '')");
			}
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.info('Por favor adicione un contacto', '')");
		}
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
			this.EnviarCorreosContactos(vRespuestaDatosSolicitud, this.getCertificacionSolicitudCertificacionList().getListaContactos(), pRespuestaRegistrarSolicitudCertificacionDto.getSolicitudCertificacionId());
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
		
		if(this.getCertificacionSolicitudCertificacionModel().getSolicitud().getTipoSolicitudCertificacionId() == ConstFacturacion.TIPO_SOLICITUD_ID_ACTUALIZACION)
		{
			rutaReporte = "reports/Recaudaciones/Facturacion/CertificacionSistemas/SolicitudReCertificacionSistemasReport.jasper";
		}				
		
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
	
	/**
	* @Descipcion: Genera un reporte de la solicitud Adjunto.
	* @author: Wilson Limachi. 
	*/
	public ReporteDatosSolicitudCertificacionDto ObtieneDatosReporteCertificacionAdjunto()
	{		
		RespuestaDatosSistemasSolCertificacionDto vReporteSolicitudCertificacionDto = new RespuestaDatosSistemasSolCertificacionDto();
		ReporteDatosSolicitudCertificacionDto vRespuestaDatosSolicitud = new ReporteDatosSolicitudCertificacionDto();
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();		

		vReporteSolicitudCertificacionDto.setSolicitudCertificacionId(this.getCertificacionSolicitudCertificacionModel().getSolicitud().getSolicitudCertificacionId());

		vReporteSolicitudCertificacionDto.setSistemaId(this.getCertificacionSolicitudCertificacionModel().getSolicitud().getSistemaId());		
		
		vRespuestaDatosSolicitud = vServiciosFacturacionRest.recuperarDatosReporteSolicitudCertificacion(vReporteSolicitudCertificacionDto);		
		
		return vRespuestaDatosSolicitud;
	}
	
	/**
	 * @Descipcion: Edita la Lista de Contactos.
	 * @author: Wilson Limachi.
	 */
	public void editarContactos()
	{
		this.comportamientoPaginaModel.setEsEditable(true);
	}
	
	/**
	 * @Descipcion: Quita un contacto de la Lista de Contactos.
	 * @author: Wilson Limachi.
	 */
	public void quitarContactos(SolicitudContactosCertificacionesDto pSolicitudContactosCertificacionesDto)
	{
		this.getCertificacionSolicitudCertificacionList().getListaContactos().remove(pSolicitudContactosCertificacionesDto);
	}	
	/**
	 * @Descipcion: Cancela la Edicion la Lista de Contactos.
	 * @author: Wilson Limachi.
	 */
	public void cancelarEditarContactos()
	{
		this.comportamientoPaginaModel.setEsEditable(false);
	}
	
	/**
	 * @Descipcion: Adiciona en Lista de Contactos.
	 * @author: Wilson Limachi.
	 */
	public void adicionarEditarContactos()
	{
		this.comportamientoPaginaModel.setEsEditable(false);
		
		this.getCertificacionSolicitudCertificacionList().getListaContactos().add(this.certificacionSolicitudCertificacionList.getContactos());
		this.certificacionSolicitudCertificacionList.setContactos(new SolicitudContactosCertificacionesDto());
	}
	
	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public SolicitudCertificacionList getSolicitudCertificacionList() {
		return solicitudCertificacionList;
	}

	public void setSolicitudCertificacionList(SolicitudCertificacionList solicitudCertificacionList) {
		this.solicitudCertificacionList = solicitudCertificacionList;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public CorreoController getCorreoController() {
		return correoController;
	}

	public void setCorreoController(CorreoController correoController) {
		this.correoController = correoController;
	}

	public ReportesController getReportesController() {
		return reportesController;
	}

	public void setReportesController(ReportesController reportesController) {
		this.reportesController = reportesController;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}

	public CertificacionSolicitudCertificacionModel getCertificacionSolicitudCertificacionModel() {
		return certificacionSolicitudCertificacionModel;
	}

	public void setCertificacionSolicitudCertificacionModel(
			CertificacionSolicitudCertificacionModel certificacionSolicitudCertificacionModel) {
		this.certificacionSolicitudCertificacionModel = certificacionSolicitudCertificacionModel;
	}

	public CertificacionSolicitudCertificacionList getCertificacionSolicitudCertificacionList() {
		return certificacionSolicitudCertificacionList;
	}

	public void setCertificacionSolicitudCertificacionList(
			CertificacionSolicitudCertificacionList certificacionSolicitudCertificacionList) {
		this.certificacionSolicitudCertificacionList = certificacionSolicitudCertificacionList;
	}

	public ComportamientoPaginaModel getComportamientoPaginaModel() {
		return comportamientoPaginaModel;
	}

	public void setComportamientoPaginaModel(ComportamientoPaginaModel comportamientoPaginaModel) {
		this.comportamientoPaginaModel = comportamientoPaginaModel;
	}
}
