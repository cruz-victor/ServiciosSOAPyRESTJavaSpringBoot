package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sre_lista_sistemas_certificacion_vw", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreSolicitudCertificacionListado.findAll", query = "SELECT s FROM SreSolicitudCertificacionListado s")
public class SreSolicitudCertificacionListado implements Serializable {
	// RCR
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SOLICITUD_CERTIFICACION_ID")
	private long solicitudCertificacionId;

	@Column(name = "nit")
	private long nit;
	
	@Column(name = "razon_social")
	private String razonSocial;
	
	@Column(name = "PERSONA_CONTRIBUYENTE_ID")
	private long personaContribuyenteId;

	@Column(name = "nombre_sistema")
	private String nombreSistema;
	
	@Column(name = "version")
	private String version;
	
	@Column(name = "estado_certificacion")
	private String estadoCertificacion;
	
	@Column(name = "modalidad")
	private String modalidad;

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public long getNit() {
		return nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public String getVersion() {
		return version;
	}

	public String getEstadoCertificacion() {
		return estadoCertificacion;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setEstadoCertificacion(String estadoCertificacion) {
		this.estadoCertificacion = estadoCertificacion;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}	
}