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
@Table(name="sre_fac_componentes_certificados", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreComponentesCertificados.findAll", query="SELECT s FROM SreComponentesArchivos s")
public class SreComponentesCertificados implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="componente_certificado_id")
	private Integer componenteCertificadoId;

	@Column(name="componente_archivo_id")
	private Long componenteArchivoId;

	@Column(name="estado_id")
	private String estadoId;

	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name="sistema_id")
	private Long sistemaId;

	@Column(name="tipo_componente_id")
	private Integer tipoComponenteId;

	@Column(name="usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name="usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "SOLICITUD_CERTIFICACION_ID")
	private long solicitudCertificacionId;

	private transient String tipoCompoonenteDescripcion;
	
	public SreComponentesCertificados() {
	}

	public Integer getComponenteCertificadoId() {
		return this.componenteCertificadoId;
	}

	public void setComponenteCertificadoId(Integer componenteCertificadoId) {
		this.componenteCertificadoId = componenteCertificadoId;
	}

	public Long getComponenteArchivoId() {
		return this.componenteArchivoId;
	}

	public void setComponenteArchivoId(Long componenteArchivoId) {
		this.componenteArchivoId = componenteArchivoId;
	}

	public String getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Long getSistemaId() {
		return this.sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Integer getTipoComponenteId() {
		return this.tipoComponenteId;
	}

	public void setTipoComponenteId(Integer tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}

	public Long getUsuarioRegistroId() {
		return this.usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return this.usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public String getTipoCompoonenteDescripcion() {
		return tipoCompoonenteDescripcion;
	}

	public void setTipoCompoonenteDescripcion(String tipoCompoonenteDescripcion) {
		this.tipoCompoonenteDescripcion = tipoCompoonenteDescripcion;
	}
}
