package bo.gob.sin.sre.fac.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.RecuperarListaSolicitudCertificacion;
import bo.gob.sin.sre.fac.dto.SolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name ="solicitudCertificacionList")
@ViewScoped
public class SolicitudCertificacionList implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	private List<SolicitudCertificacionDto> listaEstadoCertificacion;
	private boolean estadoImprimir;
	
	@PostConstruct
	void init()
	{
		listaEstadoCertificacion = new ArrayList<>();
	}

	/**
	 * Despliega la lista de Solicitud Certificacion con estado EN PROCESO.
	 * 
	 * @author: Wilson Limach
	 * @Fecha: 
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto listaEstadoCertificacion
	 */
	public void recuperarListaEnProcesoSolicitudes(Long pPersonaContribuyente) {
		RecuperarListaSolicitudCertificacion vResultado = new RecuperarListaSolicitudCertificacion();
		SolicitudSolicitudCertificacionDto solicitud = new SolicitudSolicitudCertificacionDto();
		ServiciosFacturacionRest serviciosFacturacionRest = new ServiciosFacturacionRest();
		solicitud.setPersonaContribuyenteId(pPersonaContribuyente);
		vResultado = serviciosFacturacionRest.recuperaSolicitudesCertificacionParaCancelar(solicitud);

		if (vResultado.isOk()) {
			listaEstadoCertificacion = vResultado.getLista();
		}

		mensajesBean.addMensajes(vResultado);
	}
	
	
	public List<SolicitudCertificacionDto> getListaEstadoCertificacion() {
		return listaEstadoCertificacion;
	}

	public void setListaEstadoCertificacion(List<SolicitudCertificacionDto> listaEstadoCertificacion) {
		this.listaEstadoCertificacion = listaEstadoCertificacion;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public boolean isEstadoImprimir() {
		return estadoImprimir;
	}

	public void setEstadoImprimir(boolean estadoImprimir) {
		this.estadoImprimir = estadoImprimir;
	}

}
