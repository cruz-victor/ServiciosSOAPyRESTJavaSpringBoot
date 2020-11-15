package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;

@ManagedBean(name = "contextoSolicitudCertificacionSistemaModel")
@ViewScoped
public class ContextoSolicitudCertificacionSistemaModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private SolicitudPruebasSistemasDto solicitud;
	
	private boolean contribuyenteEnVisitaSitu;
	private boolean visitaInsituValidada;
	private boolean guardoHuellas;
	private boolean pruebasConcluidas;

	public ContextoSolicitudCertificacionSistemaModel() {
		// TODO Auto-generated constructor stub
	}

	public SolicitudPruebasSistemasDto getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudPruebasSistemasDto solicitud) {
		this.solicitud = solicitud;
	}

	@PostConstruct
	public void init() {
		this.setSolicitud(new SolicitudPruebasSistemasDto());
	}

	public boolean isContribuyenteEnVisitaSitu() {
		return contribuyenteEnVisitaSitu;
	}

	public void setContribuyenteEnVisitaSitu(boolean contribuyenteEnVisitaSitu) {
		this.contribuyenteEnVisitaSitu = contribuyenteEnVisitaSitu;
	}

	public boolean isVisitaInsituValidada() {
		return visitaInsituValidada;
	}

	public void setVisitaInsituValidada(boolean visitaInsituValidada) {
		this.visitaInsituValidada = visitaInsituValidada;
	}

	public boolean isGuardoHuellas() {
		return guardoHuellas;
	}

	public void setGuardoHuellas(boolean guardoHuellas) {
		this.guardoHuellas = guardoHuellas;
	}

	public boolean isPruebasConcluidas() {
		return pruebasConcluidas;
	}

	public void setPruebasConcluidas(boolean pruebasConcluidas) {
		this.pruebasConcluidas = pruebasConcluidas;
	}
}
