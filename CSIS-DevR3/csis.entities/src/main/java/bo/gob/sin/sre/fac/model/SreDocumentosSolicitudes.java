package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SRE_FAC_DOCUMENTOS_SOLICITUDES", schema = "SRE_RECAUDACIONES")
public class SreDocumentosSolicitudes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "documento_solicitud_id")
	private int documentoSolicitudId;

	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;

	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;

	@Column(name = "solicitud_certificacion_id")
	private long solicitudCertificacionId;

	@Column(name = "tramite_id")
	private long tramiteId;

	@Column(name = "cite_id")
	private long citeId;

	@Column(name = "codigo_documento_id")
	private String codigoDocumentoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
	private String estadoId;

	public SreDocumentosSolicitudes(int documentoSolicitudId, long usuarioRegistroId, long usuarioUltimaModificacionId, long solicitudCertificacionId,
			long tramiteId, long citeId, String codigoDocumentoId, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId,
			String codigoSistema) {
		this.documentoSolicitudId = documentoSolicitudId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.citeId = citeId;
		this.codigoDocumentoId = codigoDocumentoId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}
	
	@Override
	public String toString() {
		return "SreDocumentosSolicitudes [documentoSolicitudId=" + documentoSolicitudId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", solicitudCertificacionId=" + solicitudCertificacionId + ", tramiteId="
				+ tramiteId + ", citeId=" + citeId + ", codigoDocumentoId="
				+ codigoDocumentoId + ", estadoSistemaId=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	public SreDocumentosSolicitudes() {
	}

	public int getDocumentoSolicitudId() {
		return documentoSolicitudId;
	}

	public void setDocumentoSolicitudId(int documentoSolicitudId) {
		this.documentoSolicitudId = documentoSolicitudId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public long getCiteId() {
		return citeId;
	}

	public void setCiteId(long citeId) {
		this.citeId = citeId;
	}

	public String getCodigoDocumentoId() {
		return codigoDocumentoId;
	}

	public void setCodigoDocumentoId(String codigoDocumentoId) {
		this.codigoDocumentoId = codigoDocumentoId;
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
	
}