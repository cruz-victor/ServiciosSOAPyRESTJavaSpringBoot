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
@Table(name = "sre_fac_equipos_certificaciones", schema = "SRE_RECAUDACIONES")
public class SreEquiposCertificaciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipo_certificacion_id")
	private long equipoCertificacionId;

	@Column(name = "certificacion_proceso")
	private long certificacionProceso;

	@Column(name = "oficina_id")
	private long oficinaId;

	@Column(name = "dependencia_id")
	private long dependenciaId;

	@Column(name = "estado_equipo_certificacion_id")
	private int estadoEquipoCertificacionId;

	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;

	@Column(name = "usuario_id")
	private long usuarioId;

	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
	private String estadoId;

	public SreEquiposCertificaciones() {
		super();
	}

	public SreEquiposCertificaciones(long equipoCertificacionId, long certificacionProceso, long oficinaId,
			long dependenciaId, int estadoEquipoCertificacionId, long usuarioRegistroId, long usuarioId,
			long usuarioUltimaModificacionId, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		super();
		this.equipoCertificacionId = equipoCertificacionId;
		this.certificacionProceso = certificacionProceso;
		this.oficinaId = oficinaId;
		this.dependenciaId = dependenciaId;
		this.estadoEquipoCertificacionId = estadoEquipoCertificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioId = usuarioId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreEquiposCertificaciones [equipoCertificacionId=" + equipoCertificacionId + ", certificacionProceso="
				+ certificacionProceso + ", oficinaId=" + oficinaId + ", dependenciaId=" + dependenciaId
				+ ", estadoEquipoCertificacionId=" + estadoEquipoCertificacionId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioId=" + usuarioId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	public long getEquipoCertificacionId() {
		return equipoCertificacionId;
	}

	public void setEquipoCertificacionId(long equipoCertificacionId) {
		this.equipoCertificacionId = equipoCertificacionId;
	}

	public long getCertificacionProceso() {
		return certificacionProceso;
	}

	public void setCertificacionProceso(long certificacionProceso) {
		this.certificacionProceso = certificacionProceso;
	}

	public long getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(long oficinaId) {
		this.oficinaId = oficinaId;
	}

	public long getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(long dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public int getEstadoEquipoCertificacionId() {
		return estadoEquipoCertificacionId;
	}

	public void setEstadoEquipoCertificacionId(int estadoEquipoCertificacionId) {
		this.estadoEquipoCertificacionId = estadoEquipoCertificacionId;
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

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
