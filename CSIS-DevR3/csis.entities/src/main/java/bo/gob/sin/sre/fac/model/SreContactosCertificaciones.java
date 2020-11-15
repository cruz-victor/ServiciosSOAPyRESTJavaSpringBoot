package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SRE_FAC_CONTACTOS_SOLICITUDES_CERTIFICACIONES", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreContactosCertificaciones.findAll", query = "SELECT s FROM SreContactosCertificaciones s")
public class SreContactosCertificaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "contacto_solicitud_certificacion_id")
	private Long contactoSolicitudCertificacionId;
	
	@Column(name = "usuario_registro_id")
	private Long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "solicitud_certificacion_id")
	private Long solicitudCertificacionId;
	
	@Column(name = "nombre_completo")
	private String  nombreCompleto;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "correo_electronico")
	private String correoElectronico;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;
	
	@Column(name = "numero_documento")
	private String numeroDocumento;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "tipo_documento_identidad_id")
	private Integer tipoDocumentoIdentidadId;

	public SreContactosCertificaciones() {
	}

	public SreContactosCertificaciones(Long contactoSolicitudCertificacionId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Long solicitudCertificacionId, String nombreCompleto, String celular,
			String correoElectronico, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId,
			String numeroDocumento, String complemento, Integer tipoDocumentoIdentidadId) {
		this.contactoSolicitudCertificacionId = contactoSolicitudCertificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.nombreCompleto = nombreCompleto;
		this.celular = celular;
		this.correoElectronico = correoElectronico;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.numeroDocumento = numeroDocumento;
		this.complemento = complemento;
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}

	public Long getContactoSolicitudCertificacionId() {
		return contactoSolicitudCertificacionId;
	}

	public void setContactoSolicitudCertificacionId(Long contactoSolicitudCertificacionId) {
		this.contactoSolicitudCertificacionId = contactoSolicitudCertificacionId;
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

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public Integer getTipoDocumentoIdentidadId() {
		return tipoDocumentoIdentidadId;
	}

	public void setTipoDocumentoIdentidadId(Integer tipoDocumentoIdentidadId) {
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}

	@Override
	public String toString() {
		return "SreContactosCertificaciones [contactoSolicitudCertificacionId=" + contactoSolicitudCertificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", solicitudCertificacionId=" + solicitudCertificacionId
				+ ", nombreCompleto=" + nombreCompleto + ", celular=" + celular + ", correoElectronico="
				+ correoElectronico + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + ", numeroDocumento=" + numeroDocumento
				+ ", complemento=" + complemento + ", tipoDocumentoIdentidadId=" + tipoDocumentoIdentidadId + "]";
	}
}
