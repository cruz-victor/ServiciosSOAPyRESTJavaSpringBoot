package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sap.cead.componentes.controller.DocumentoAdjuntoAbstract;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "gestionDocumentoAdjuntoController")
public class GestionDocumentoAdjuntoController extends DocumentoAdjuntoAbstract implements Serializable {
	private static final long serialVersionUID = 1L;

	private EstadoDerivacionDto estadoDerivacionDto;

	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	private ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	@PostConstruct
	public void inicio() {
		this.obtenerContexto(estadoDerivacionDto);
		this.tramiteCertificacionBean.setearContexto();
		this.contextoSolicitudCertificacionSistemaModel.getSolicitud().setSolicitudId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		this.cargarDatos(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());
	}

	public void obtenerContexto(EstadoDerivacionDto estado) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			estado = (EstadoDerivacionDto) sessionMap.get("EstadoProceso");
			this.estadoDerivacionDto = estado;
		} catch (Exception e) {
			this.estadoDerivacionDto = null;
		}
	}

	public EstadoDerivacionDto getEstadoDerivacionDto() {
		return estadoDerivacionDto;
	}

	public void setEstadoDerivacionDto(EstadoDerivacionDto estadoDerivacionDto) {
		this.estadoDerivacionDto = estadoDerivacionDto;
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	@Override
	public void cargarDatos(long idTramite) {
		this.getListaDocumentosAdjuntos().cargarDocumentosAdjuntosTramite(idTramite);
	}

	@Override
	public void adicionarDocumentoAdjunto() {
		this.getListaDocumentosAdjuntos().adicionarDocumentoAdjunto();
	}
}
