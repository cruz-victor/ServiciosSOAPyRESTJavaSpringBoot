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
@Table(name = "SRE_FAC_TIPOS_DOCUMENTOS_SISTEMAS", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreTiposDocumentosSistemas.findAll", query = "SELECT s FROM SreTiposDocumentosSistemas s")
public class SreTiposDocumentosSistemas implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "tipo_documento_sistema_id")
	private Long tipoDocumentoSistemaId;
	
	@Column(name = "usuario_registro_id")
	private Long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "sistema_id")
	private Long sistemaId;
	
	@Column(name = "solicitud_certificacion_id")
	private Long  solicitudCertificacionId;
	
	@Column(name = "tipo_documento_factura_id")
	private Integer tipoDocumentoFacturaId;
	
	@Column(name = "estado_tipo_documento_sistema_id")
	private Integer estadoTipoDocumentoSistemaId;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;

	public SreTiposDocumentosSistemas() {
	}

	public SreTiposDocumentosSistemas(Long tipoDocumentoSistemaId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Long sistemaId, Long solicitudCertificacionId,
			Integer tipoDocumentoFacturaId, Integer estadoTipoDocumentoSistemaId, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId) {
		super();
		this.tipoDocumentoSistemaId = tipoDocumentoSistemaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.sistemaId = sistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tipoDocumentoFacturaId = tipoDocumentoFacturaId;
		this.estadoTipoDocumentoSistemaId = estadoTipoDocumentoSistemaId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public Long getTipoDocumentoSistemaId() {
		return tipoDocumentoSistemaId;
	}

	public void setTipoDocumentoSistemaId(Long tipoDocumentoSistemaId) {
		this.tipoDocumentoSistemaId = tipoDocumentoSistemaId;
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

	public Integer getTipoDocumentoFacturaId() {
		return tipoDocumentoFacturaId;
	}

	public void setTipoDocumentoFacturaId(Integer tipoDocumentoFacturaId) {
		this.tipoDocumentoFacturaId = tipoDocumentoFacturaId;
	}

	public Integer getEstadoTipoDocumentoSistemaId() {
		return estadoTipoDocumentoSistemaId;
	}

	public void setEstadoTipoDocumentoSistemaId(Integer estadoTipoDocumentoSistemaId) {
		this.estadoTipoDocumentoSistemaId = estadoTipoDocumentoSistemaId;
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
		return "SreTiposDocumentosSistemas [tipoDocumentoSistemaId=" + tipoDocumentoSistemaId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", sistemaId="
				+ sistemaId + ", solicitudCertificacionId=" + solicitudCertificacionId + ", tipoDocumentoFacturaId="
				+ tipoDocumentoFacturaId + ", estadoTipoDocumentoSistemaId=" + estadoTipoDocumentoSistemaId
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion
				+ ", estadoId=" + estadoId + "]";
	}
}
