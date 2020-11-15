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
@Table(name = "sre_fac_asignaciones_certificaciones", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreAsignacionesCertificaciones.findAll", query = "SELECT s FROM SreAsignacionesCertificaciones s")
public class SreAsignacionesCertificaciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asignacion_certificacion_id")
	private long asignacionCertificacionId;

	@Column(name = "tramite_id")
	private long tramiteId;

	@Column(name = "estado_asignacion_certificacion_id")
	private int estadoAsignacionCertificacionId;

	@Column(name = "fecha_asignacion")
	private Date fechaAsignacion;

	@Column(name = "fecha_finalizacion")
	private Date fechaFinalizacion;

	@Column(name = "usuario_id")
	private long usuarioId;

	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;

	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
	private String estadoId;

	public SreAsignacionesCertificaciones() {
		super();
	}

	public SreAsignacionesCertificaciones(long asignacionCertificacionId, long tramiteId,
			int estadoAsignacionCertificacionId, Date fechaAsignacion, Date fechaFinalizacion, long usuarioId,
			long usuarioRegistroId, long usuarioUltimaModificacionId, Date fechaRegistro, Date fechaUltimaModificacion,
			String estadoId) {
		super();
		this.asignacionCertificacionId = asignacionCertificacionId;
		this.tramiteId = tramiteId;
		this.estadoAsignacionCertificacionId = estadoAsignacionCertificacionId;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaFinalizacion = fechaFinalizacion;
		this.usuarioId = usuarioId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreAsignacionesCertificaciones [asignacionCertificacionId=" + asignacionCertificacionId + ", tramiteId="
				+ tramiteId + ", estadoAsignacionCertificacionId=" + estadoAsignacionCertificacionId
				+ ", fechaAsignacion=" + fechaAsignacion + ", fechaFinalizacion=" + fechaFinalizacion + ", usuarioId="
				+ usuarioId + ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	public long getAsignacionCertificacionId() {
		return asignacionCertificacionId;
	}

	public void setAsignacionCertificacionId(long asignacionCertificacionId) {
		this.asignacionCertificacionId = asignacionCertificacionId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public int getEstadoAsignacionCertificacionId() {
		return estadoAsignacionCertificacionId;
	}

	public void setEstadoAsignacionCertificacionId(int estadoAsignacionCertificacionId) {
		this.estadoAsignacionCertificacionId = estadoAsignacionCertificacionId;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
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

}
