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
@Table(name="sre_fac_componentes_certificados_tmp", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreComponentesCertificadosTmp.findAll", query="SELECT s FROM SreComponentesCertificadosTmp s")
public class SreComponentesCertificadosTmp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="componente_certificado_tmp_id")
	private Integer componenteCertificadoTmpId;

	@Column(name="componente_archivo_tmp_id")
	private Long componenteArchivoTmpId;

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

	public SreComponentesCertificadosTmp() {
	}

	public Integer getComponenteCertificadoTmpId() {
		return componenteCertificadoTmpId;
	}

	public void setComponenteCertificadoTmpId(Integer componenteCertificadoTmpId) {
		this.componenteCertificadoTmpId = componenteCertificadoTmpId;
	}

	public Long getComponenteArchivoTmpId() {
		return componenteArchivoTmpId;
	}

	public void setComponenteArchivoTmpId(Long componenteArchivoTmpId) {
		this.componenteArchivoTmpId = componenteArchivoTmpId;
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
}
