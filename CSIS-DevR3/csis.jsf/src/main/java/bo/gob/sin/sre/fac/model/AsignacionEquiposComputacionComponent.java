package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "asignacionEquiposComputacionComponent")
@ViewScoped
public class AsignacionEquiposComputacionComponent   implements Serializable {

	private static final long serialVersionUID = 1L;

	private  RespuestaDatosSistemasSolCertificacionDto datosSistemasSolCertificacion; 
	
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init() {
		datosSistemasSolCertificacion =new RespuestaDatosSistemasSolCertificacionDto();
	}
	
	public void obtenerAsignacionEquiposComputacion() {
		ServiciosFacturacionRest serviciosFacturacionRest = new ServiciosFacturacionRest();
		RespuestaDatosSistemasSolCertificacionDto respuestaDatosSistemasSolCertificacionDto =new RespuestaDatosSistemasSolCertificacionDto();
		respuestaDatosSistemasSolCertificacionDto.setSistemaId(datosSistemasSolCertificacion.getSistemaId());
		respuestaDatosSistemasSolCertificacionDto.setSolicitudCertificacionId(datosSistemasSolCertificacion.getSolicitudCertificacionId());
		respuestaDatosSistemasSolCertificacionDto=serviciosFacturacionRest.recuperarDatosComponenteSistema(respuestaDatosSistemasSolCertificacionDto);
		if(respuestaDatosSistemasSolCertificacionDto.isOk())
			datosSistemasSolCertificacion = respuestaDatosSistemasSolCertificacionDto;
		else
			mensajesBean.addMensajes(respuestaDatosSistemasSolCertificacionDto);
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}
	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public RespuestaDatosSistemasSolCertificacionDto getDatosSistemasSolCertificacion() {
		return datosSistemasSolCertificacion;
	}

	public void setDatosSistemasSolCertificacion(RespuestaDatosSistemasSolCertificacionDto datosSistemasSolCertificacion) {
		this.datosSistemasSolCertificacion = datosSistemasSolCertificacion;
	}
}
