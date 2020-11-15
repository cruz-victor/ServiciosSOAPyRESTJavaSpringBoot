package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import bo.gob.sin.sre.fac.model.SreSistemas;

@Entity
@Table(name = "SRE_FAC_SOLICITUDES_CERTIFICACIONES", schema="SRE_RECAUDACIONES")
public class SreSistemasDeCertificacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// RCR	
	@Id
	@Column(name = "persona_contribuyente_id")	
	private Long contribuyenteId;
	
	@Column(name = "sistema_id")	
	private Long sistemaId;
	
	@Column(name = "ESTADO_SOLICITUD_CERTIFICACION_ID")	
	private Integer estadoSolicitudCertificacionId;
	
	@Column(name = "solicitud_Certificacion_Id")	
	private Integer solicitudCertificacionId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "estado_sistema_id")
	private SreSistemas estadoSistemaId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "nombre_sistema")
	private SreSistemas nombreSistema;
	
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
   // @JoinColumn(name = "modalidad_facturacion_id")
	//private SreSistemas modalidadFacturacionId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "fecha_registro")
	private SreSistemas fechaRegistro;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "version")
	private SreSistemas version;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "cuis")
	private SreIniciosSistemas cuis;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "fecha_inicio")
	private SreIniciosSistemas fechaInicio;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "sucursal_id")
	private SreIniciosSistemas sucursalId;
	
	
	public SreSistemasDeCertificacion() {
		
	}
	
	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Integer getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}

	public void setEstadoSolicitudCertificacionId(Integer estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}

	public SreSistemas getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(SreSistemas nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public SreSistemas getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(SreSistemas fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public SreSistemas getVersion() {
		return version;
	}

	public void setVersion(SreSistemas version) {
		this.version = version;
	}

	public Integer getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Integer solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	//public SreSistemas getModalidadFacturacionId() {
	//	return modalidadFacturacionId;
	//}

	//public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
	//	this.modalidadFacturacionId = modalidadFacturacionId;
	//}

	
	public SreSistemas getEstadoSistemaId() {
		return estadoSistemaId;
	}

	public void setEstadoSistemaId(SreSistemas estadoSistemaId) {
		this.estadoSistemaId = estadoSistemaId;
	}

	public SreIniciosSistemas getCuis() {
		return cuis;
	}

	public void setCuis(SreIniciosSistemas cuis) {
		this.cuis = cuis;
	}

	public SreIniciosSistemas getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(SreIniciosSistemas fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public SreIniciosSistemas getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(SreIniciosSistemas sucursalId) {
		this.sucursalId = sucursalId;
	}

	@Override
	public String toString() {
		return "SreFacEntSistemasDeCertificacion [persona_Contribuyente_id=" + contribuyenteId + ", sistemaId=" + sistemaId
				+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", estadoSistemaId=" + estadoSistemaId + ", nombreSistema=" + nombreSistema
				+ ", fechaRegistro=" + fechaRegistro
				+ ", version=" + version + ", cuis=" + cuis + ", fechaInicio=" + fechaInicio + ", sucursalId="
				+ sucursalId + "]";
	}

}
