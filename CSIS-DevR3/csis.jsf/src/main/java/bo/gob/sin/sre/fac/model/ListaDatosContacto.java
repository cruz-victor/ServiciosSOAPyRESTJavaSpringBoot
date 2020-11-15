package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.controller.TramiteCertificacionBean;
import bo.gob.sin.sre.fac.dto.RecuperarListaContactosCertificacion;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@SuppressWarnings("deprecation")
@ManagedBean(name = "listaDatosContacto")
@ViewScoped
public class ListaDatosContacto implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SolicitudContactosCertificacionesDto> listaContactos;

	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	@PostConstruct
	public void init() {
		this.tramiteCertificacionBean.setearContexto();
		this.cargarListaContactos(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
	}

	public void cargarListaContactos(long idSolicitudCertificacion) {
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		RecuperarListaContactosCertificacion vRespuesta = vServiciosFacturacionRest
				.obtenerContactosCertificacion(idSolicitudCertificacion + "");
		if (vRespuesta.isOk()) {
			this.setListaContactos(new ArrayList<>());

			vRespuesta.getLista().forEach(item -> {
				SolicitudContactosCertificacionesDto vSolicitudContactosCertificacionesAux = new SolicitudContactosCertificacionesDto();
				vSolicitudContactosCertificacionesAux.setCelular(item.getCelular());
				vSolicitudContactosCertificacionesAux.setComplemento(item.getComplemento());
				vSolicitudContactosCertificacionesAux.setCorreo(item.getCorreoElectronico());
				vSolicitudContactosCertificacionesAux.setNombre(item.getNombreCompleto());
				vSolicitudContactosCertificacionesAux.setNumeroDocumento(item.getNumeroDocumento());
				vSolicitudContactosCertificacionesAux.setTipoDocumentoIdentidad(item.getTipoDocumentoIdentidadId());
				this.listaContactos.add(vSolicitudContactosCertificacionesAux);
			});
		} else {
			this.listaContactos = new ArrayList<>();
		}
	}

	public List<SolicitudContactosCertificacionesDto> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(List<SolicitudContactosCertificacionesDto> listaContactos) {
		this.listaContactos = listaContactos;
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}

}
