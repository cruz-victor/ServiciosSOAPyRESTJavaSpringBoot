package bo.gob.sin.sre.fac.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sen.enco.model.ContextoJSF;
import bo.gob.sin.sre.fac.dto.NotificacionElectronicaListaDto;
import bo.gob.sin.sre.fac.dto.RespuestaCertificadoAprobacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudCertificadoAprobacionDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.model.SeguimientoCertificacionSistemasList;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@SuppressWarnings("deprecation")
@ManagedBean(name = "etapaEmisionGestionPruebasController")
@ViewScoped
public class EtapaEmisionGestionPruebasController implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient TabView tabview;
	private int activeTab;
	private boolean documentoNotificado;

	private EstadoDerivacionDto estadoDerivacionDto;
	private NotificacionElectronicaListaDto notificaciones;

	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	private ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	@ManagedProperty(value = "#{seguimientoCertificacionSistemasList}")
	private SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList;		

	
	private String cite;
	private String estado;
	private Integer estadoId;
	private long documentoEmitidoId;
	private String nombreArchivoFirma;
	private String archivoFirma;

	static final int ESTADO_FIRMA_FIRMADO = 651;
	static final int ESTADO_FIRMA_PENDIENTE = 650;

	public void Load() 
	{
		
		this.obtenerContexto(estadoDerivacionDto);
		this.tramiteCertificacionBean.setearContexto();
		contextoSolicitudCertificacionSistemaModel.getSolicitud().setSolicitudId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		contextoSolicitudCertificacionSistemaModel.getSolicitud().setContribuyenteId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getPersonaContribuyenteId());
		contextoSolicitudCertificacionSistemaModel.getSolicitud()
				.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
		contextoSolicitudCertificacionSistemaModel.getSolicitud()
				.setTramiteId(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());

		long vPersonaContribuyente = tramiteCertificacionBean.getTramiteSolicitudCertificacion()
				.getPersonaContribuyenteId();
		MetodosPadron vMetodosPadron = new MetodosPadron();
		this.getContextoSolicitudCertificacionSistemaModel().getSolicitud()
				.setNit(vMetodosPadron.ObtenerDatosBasicosXIFC(vPersonaContribuyente).getNit());
		
		this.getSeguimientoCertificacionSistemasList().obtenerListadoSeguimientoCertificacionSistemas(this.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getSistemaId(), this.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getNit());
		this.getSeguimientoCertificacionSistemasList().setMostrarPanel(false);
	}


	public StreamedContent descargarCertificado() {
		try {
			ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
			SolicitudCertificadoAprobacionDto solicitud = new SolicitudCertificadoAprobacionDto();
			ContextoJSF contexto = new ContextoJSF();
			solicitud.setSolicitudId(
					tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
			solicitud.setTramiteId(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());
			solicitud.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
			solicitud.setUsuarioId(contexto.getUsuario().getUsuarioId());
			solicitud.setOficinaId(contexto.getUsuario().getOficinaId());
			solicitud.setDependenciaId(contexto.getUsuario().getDependenciaId());

			RespuestaCertificadoAprobacionDto vRespuesta = vServiciosFacturacionRest
					.obtenerCertificadoDeAprobacion(solicitud);
			if (vRespuesta.isOk()) {
				byte[] docbyte = Base64.decodeBase64(vRespuesta.getArchivoCertificadoAprobacion());
				InputStream stream = new ByteArrayInputStream(docbyte);
				return new DefaultStreamedContent(stream, "application/pdf; charset=UTF-8",
						vRespuesta.getNombreArchivoCertificadoAprobacion());
			} else {
				RequestContext.getCurrentInstance()
						.execute("toastr.error('" + vRespuesta.getMensajes().get(0).toString() + "','Error')");
				return null;
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance()
					.execute("toastr.error('Ocurrio un error al descargar el certificado','Error')");
			return null;
		}
	}

	public StreamedContent obtenerCertificadoDeAprobacionFirma() {
		try {
			ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
			SolicitudCertificadoAprobacionDto solicitud = new SolicitudCertificadoAprobacionDto();
			ContextoJSF contexto = new ContextoJSF();
			solicitud.setSolicitudId(
					tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
			solicitud.setTramiteId(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());
			solicitud.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
			solicitud.setUsuarioId(contexto.getUsuario().getUsuarioId());
			solicitud.setOficinaId(contexto.getUsuario().getOficinaId());
			solicitud.setDependenciaId(contexto.getUsuario().getDependenciaId());

			RespuestaCertificadoAprobacionDto vRespuesta = vServiciosFacturacionRest
					.obtenerCertificadoDeAprobacionHash(solicitud);
			if (vRespuesta.isOk()) {
				byte[] docbyte = Base64.decodeBase64(vRespuesta.getArchivoCertificadoAprobacionHash());
				InputStream stream = new ByteArrayInputStream(docbyte);
				return new DefaultStreamedContent(stream, "application/zip;",
						vRespuesta.getNombreArchivoCertificadoAprobacionHash());
			} else {
				RequestContext.getCurrentInstance()
						.execute("toastr.error('" + vRespuesta.getMensajes().get(0).toString() + "','Error')");
				return null;
			}
		} catch (Exception e) {
			RequestContext.getCurrentInstance()
					.execute("toastr.error('Ocurrio un error al obtener el hash de CCSC','Error')");
			return null;
		}
	}

	public void guardarArchivoFirmado() {
		if (this.nombreArchivoFirma != null && !this.nombreArchivoFirma.isEmpty()) {
			ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
			SolicitudCertificadoAprobacionDto solicitud = new SolicitudCertificadoAprobacionDto();
			ContextoJSF contexto = new ContextoJSF();
			solicitud.setSolicitudId(
					tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
			solicitud.setTramiteId(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());
			solicitud.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
			solicitud.setUsuarioId(contexto.getUsuario().getUsuarioId());
			solicitud.setOficinaId(contexto.getUsuario().getOficinaId());
			solicitud.setDependenciaId(contexto.getUsuario().getDependenciaId());

			solicitud.setNombreArchivoFirma(nombreArchivoFirma);
			solicitud.setArchivoFirma(archivoFirma);
			RespuestaCertificadoAprobacionDto vRespuesta = vServiciosFacturacionRest
					.guardarCertificadoDeAprobacionFirma(solicitud);
			if (vRespuesta.isOk()) {
				this.setEstado("FIRMADO DIGITALMENTE");
				this.setEstadoId(ESTADO_FIRMA_FIRMADO);
				RequestContext.getCurrentInstance().execute("toastr.success('Documento Firmado Correctamente')");
			} else {
				RequestContext.getCurrentInstance()
						.execute("toastr.error('" + vRespuesta.getMensajes().get(0).toString() + "','Error')");
			}
		} else {
			RequestContext.getCurrentInstance().execute("toastr.error('Debe seleccionar el archivo firmado','Error')");
		}
	}

	public void cargarArchivo(FileUploadEvent event) throws IOException {
		String nombre = event.getFile().getFileName();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try (InputStream inputstream = event.getFile().getInputstream()) {
			int length;
			byte[] buffer = new byte[1024];
			while ((length = inputstream.read(buffer)) != -1)
				out.write(buffer, 0, length);
		}

		byte[] binario = out.toByteArray();
		String strEncodedData = Base64.encodeBase64String(binario);
		archivoFirma = strEncodedData;
		nombreArchivoFirma = nombre;
	}

	public void obtenerContexto(EstadoDerivacionDto pEstado) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			this.estadoDerivacionDto = (EstadoDerivacionDto) sessionMap.get("EstadoProceso");
		} catch (Exception e) {
			this.estadoDerivacionDto = null;
		}
	}

	public TabView getTabview() {
		return tabview;
	}

	public void setTabview(org.primefaces.component.tabview.TabView tabview) {
		this.tabview = tabview;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public void tabIsChanged(TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		TabView tabView = (TabView) event.getComponent();
		String activeIndexValue = params.get(tabView.getClientId(context) + "_tabindex");
		this.setActiveTab(Integer.parseInt(activeIndexValue));
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}

	public EstadoDerivacionDto getEstadoDerivacionDto() {
		return estadoDerivacionDto;
	}

	public void setEstadoDerivacionDto(EstadoDerivacionDto estadoDerivacionDto) {
		this.estadoDerivacionDto = estadoDerivacionDto;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public String getNombreArchivoFirma() {
		return nombreArchivoFirma;
	}

	public String getArchivoFirma() {
		return archivoFirma;
	}

	public void setNombreArchivoFirma(String nombreArchivoFirma) {
		this.nombreArchivoFirma = nombreArchivoFirma;
	}

	public void setArchivoFirma(String archivoFirma) {
		this.archivoFirma = archivoFirma;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public long getDocumentoEmitidoId() {
		return documentoEmitidoId;
	}

	public void setDocumentoEmitidoId(long documentoEmitidoId) {
		this.documentoEmitidoId = documentoEmitidoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getEstadoFirmaFirmado() {
		return ESTADO_FIRMA_FIRMADO;
	}

	public static long getEstadoFirmaPendiente() {
		return ESTADO_FIRMA_PENDIENTE;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public boolean isDocumentoNotificado() {
		return documentoNotificado;
	}

	public void setDocumentoNotificado(boolean documentoNotificado) {
		this.documentoNotificado = documentoNotificado;
	}

	public NotificacionElectronicaListaDto getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(NotificacionElectronicaListaDto notificaciones) {
		this.notificaciones = notificaciones;
	}


	public SeguimientoCertificacionSistemasList getSeguimientoCertificacionSistemasList() {
		return seguimientoCertificacionSistemasList;
	}


	public void setSeguimientoCertificacionSistemasList(
			SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList) {
		this.seguimientoCertificacionSistemasList = seguimientoCertificacionSistemasList;
	}

}
