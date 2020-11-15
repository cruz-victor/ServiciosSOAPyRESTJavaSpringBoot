package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//IASC

@Entity
@Table(name = "SRE_FAC_REG_RES_SOLICITUDES", schema="SRE_RECAUDACIONES")
public class SreRegistrosResultadosSolicitudes implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "reg_res_solicitud_id")	
	private Long registroResultadoSolicitudId;
	
	@Column(name = "usuario_registro_id")	
	private Long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")	
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "sistema_id")	
	private Long sistemaId;
	
	@Column(name = "solicitud_certificacion_id")	
	private Long solicitudCertificacionId;
	
	@Column(name = "tramite_id")	
	private Long tramiteId;
	
	@Column(name = "tipo_resultado_certificacion_id")	
	private Integer tipoResultadoCertificacionId;
	
	@Column(name = "motivo_rechazo")	
	private String motivoRechazo;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")	
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")	
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")	
	private String estadoId;

	public SreRegistrosResultadosSolicitudes(Long registroResultadoSolicitudId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Long sistemaId, Long solicitudCertificacionId, Long tramiteId,
			Integer tipoResultadoCertificacionId, String motivoRechazo, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId) {
		this.registroResultadoSolicitudId = registroResultadoSolicitudId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.sistemaId = sistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.tipoResultadoCertificacionId = tipoResultadoCertificacionId;
		this.motivoRechazo = motivoRechazo;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public SreRegistrosResultadosSolicitudes() {
	}

	public Long getRegistroResultadoSolicitudId() {
		return registroResultadoSolicitudId;
	}

	public void setRegistroResultadoSolicitudId(Long registroResultadoSolicitudId) {
		this.registroResultadoSolicitudId = registroResultadoSolicitudId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public Integer getTipoResultadoCertificacionId() {
		return tipoResultadoCertificacionId;
	}

	public void setTipoResultadoCertificacionId(Integer tipoResultadoCertificacionId) {
		this.tipoResultadoCertificacionId = tipoResultadoCertificacionId;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreRegistrosResultadosSolicitudes [registroResultadoSolicitudId=" + registroResultadoSolicitudId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", sistemaId=" + sistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tramiteId=" + tramiteId + ", tipoResultadoCertificacionId="
				+ tipoResultadoCertificacionId + ", motivoRechazo=" + motivoRechazo + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}
}
