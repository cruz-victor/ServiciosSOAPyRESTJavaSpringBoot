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
@Table(name = "sre_fac_sistemas_modulos", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreSistemasModulos.findAll", query = "SELECT s FROM SreSistemasModulos s")
public class SreSistemasModulos  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "sistema_modulo_id")	
	private Long sistemaModuloId;
	
	@Column(name = "solicitud_certificacion_id")	
	private Long solicitudCertificacionId;
	
	@Column(name = "tramite_id")	
	private Long tramiteId;
	
	@Column(name = "sistema_id")	
	private Long sistemaId;
	
	@Column(name = "tipo_modulo_id")	
	private Integer tipoModuloId;
	
	@Column(name = "estado_id")	
	private String estadoId;
	
	@Column(name = "usuario_registro_id")	
	private Long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")	
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "fecha_registro")	
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")	
	private Date fechaUltimaModificacion;

	public SreSistemasModulos() {
		
	}

	public SreSistemasModulos(Long sistemaModuloId, Long solicitudCertificacionId, Long tramiteId, Long sistemaId,
			Integer tipoModuloId, String estadoId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Date fechaRegistro, Date fechaUltimaModificacion) {
		super();
		this.sistemaModuloId = sistemaModuloId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.sistemaId = sistemaId;
		this.tipoModuloId = tipoModuloId;
		this.estadoId = estadoId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Long getSistemaModuloId() {
		return sistemaModuloId;
	}

	public void setSistemaModuloId(Long sistemaModuloId) {
		this.sistemaModuloId = sistemaModuloId;
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

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Integer getTipoModuloId() {
		return tipoModuloId;
	}

	public void setTipoModuloId(Integer tipoModuloId) {
		this.tipoModuloId = tipoModuloId;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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

	@Override
	public String toString() {
		return "SreFacEntSistemasModulos [sistemaModuloId=" + sistemaModuloId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tramiteId=" + tramiteId + ", sistemaId=" + sistemaId + ", tipoModuloId="
				+ tipoModuloId + ", estadoId=" + estadoId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + "]";
	}
}
