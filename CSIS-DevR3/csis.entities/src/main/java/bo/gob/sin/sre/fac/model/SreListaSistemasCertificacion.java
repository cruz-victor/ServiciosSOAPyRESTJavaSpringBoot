package bo.gob.sin.sre.fac.model;

import javax.persistence.CascadeType;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SRE_FAC_SOLICITUDES_CERTIFICACIONES", schema="SRE_RECAUDACIONES")
public class SreListaSistemasCertificacion implements Serializable{
	// RCR	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "persona_contribuyente_id")	
	private Long personaContribuyenteId;
	
	
	@Column(name = "estado_solicitud_certificacion_id")	
	private long estadoSolicitudCertificacionId;
	
	
	@Column(name = "fecha_aprobacion")
	private Date fechaAprobacion;
	
	@Column(name = "solicitud_certificacion_id")
	private int solicitudCertificacionId;


	@Column(name = "sistema_id")
	private long  sistemaId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "nombre_Sistema")
	private SreSistemas nombreSistema;
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "version")
	private SreSistemas version;


	public  SreListaSistemasCertificacion() {}
	
	public Long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}


	public void setPersonaContribuyenteId(Long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}


	public long getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}


	public void setEstadoSolicitudCertificacionId(long estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}


	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}


	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}


	public SreSistemas getNombreSistema() {
		return nombreSistema;
	}


	public void setNombreSistema(SreSistemas nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	
	public SreSistemas getVersion() {
		return version;
	}


	public void setVersion(SreSistemas version) {
		this.version = version;
	}

	public int getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(int solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	@Override
	public String toString() {
		return "SreListaSistemasCertificacion [personaContribuyenteId=" + personaContribuyenteId
				+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId + ", fechaAprobacion="
				+ fechaAprobacion + ", solicitudCertificacionId=" + solicitudCertificacionId + ", sistemaId="
				+ sistemaId + ", nombreSistema=" + nombreSistema + ", version=" + version + "]";
	}


}




