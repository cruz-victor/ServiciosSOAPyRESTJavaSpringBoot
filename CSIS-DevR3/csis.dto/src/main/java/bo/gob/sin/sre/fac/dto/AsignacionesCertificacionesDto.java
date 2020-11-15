package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class AsignacionesCertificacionesDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long asignacionCertificacionId;

	private long tramiteId;

	private int estadoAsignacionCertificacionId;

	private String estadoAsignacionCertificacionDescripcion;

	private Date fechaAsignacion;

	private Date fechaFinalizacion;

	private long usuarioRegistroId;

	private long usuarioId;

	private long usuarioUltimaModificacionId;

	private Date fechaRegistro;

	private Date fechaUltimaModificacion;

	private Boolean responsable;

	private String estadoId;

	public AsignacionesCertificacionesDto() {
		super();
	}

	public AsignacionesCertificacionesDto(long asignacionCertificacionId, long tramiteId,
			int estadoAsignacionCertificacionId, String estadoAsignacionCertificacionDescripcion, Date fechaAsignacion,
			Date fechaFinalizacion, long usuarioRegistroId, long usuarioId, long usuarioUltimaModificacionId,
			Date fechaRegistro, Date fechaUltimaModificacion, Boolean responsable, String estadoId) {
		super();
		this.asignacionCertificacionId = asignacionCertificacionId;
		this.tramiteId = tramiteId;
		this.estadoAsignacionCertificacionId = estadoAsignacionCertificacionId;
		this.estadoAsignacionCertificacionDescripcion = estadoAsignacionCertificacionDescripcion;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaFinalizacion = fechaFinalizacion;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioId = usuarioId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.responsable = responsable;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "AsignacionesCertificacionesDto [asignacionCertificacionId=" + asignacionCertificacionId + ", tramiteId="
				+ tramiteId + ", estadoAsignacionCertificacionId=" + estadoAsignacionCertificacionId
				+ ", estadoAsignacionCertificacionDescripcion=" + estadoAsignacionCertificacionDescripcion
				+ ", fechaAsignacion=" + fechaAsignacion + ", fechaFinalizacion=" + fechaFinalizacion
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioId=" + usuarioId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", responsable=" + responsable
				+ ", estadoId=" + estadoId + "]";
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

	public String getEstadoAsignacionCertificacionDescripcion() {
		return estadoAsignacionCertificacionDescripcion;
	}

	public void setEstadoAsignacionCertificacionDescripcion(String estadoAsignacionCertificacionDescripcion) {
		this.estadoAsignacionCertificacionDescripcion = estadoAsignacionCertificacionDescripcion;
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

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
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

	public Boolean getResponsable() {
		return responsable;
	}

	public void setResponsable(Boolean responsable) {
		this.responsable = responsable;
	}

}
