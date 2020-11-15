package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class EquiposCertificacionesDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long equipoCertificacionId;

	private long certificacionProceso;

	private long oficinaId;

	private String usuarioId;

	private String usuarioNombre;

	private long oficinaDescripcion;

	private long dependenciaId;

	private long dependenciaDescripcion;

	private int estadoEquipoCertificacionId;

	private int estadoEquipoCertificacionDescripcion;

	private long usuarioRegistroId;

	private long usuarioUltimaModificacionId;

	private Date fechaRegistro;

	private Date fechaUltimaModificacion;

	private String estadoId;

	public EquiposCertificacionesDto() {
		super();
	}

	public EquiposCertificacionesDto(long equipoCertificacionId, long certificacionProceso, long oficinaId,
			String usuarioId, String usuarioNombre, long oficinaDescripcion, long dependenciaId,
			long dependenciaDescripcion, int estadoEquipoCertificacionId, int estadoEquipoCertificacionDescripcion,
			long usuarioRegistroId, long usuarioUltimaModificacionId, Date fechaRegistro, Date fechaUltimaModificacion,
			String estadoId) {
		super();
		this.equipoCertificacionId = equipoCertificacionId;
		this.certificacionProceso = certificacionProceso;
		this.oficinaId = oficinaId;
		this.usuarioId = usuarioId;
		this.usuarioNombre = usuarioNombre;
		this.oficinaDescripcion = oficinaDescripcion;
		this.dependenciaId = dependenciaId;
		this.dependenciaDescripcion = dependenciaDescripcion;
		this.estadoEquipoCertificacionId = estadoEquipoCertificacionId;
		this.estadoEquipoCertificacionDescripcion = estadoEquipoCertificacionDescripcion;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
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

	public long getOficinaDescripcion() {
		return oficinaDescripcion;
	}

	public void setOficinaDescripcion(long oficinaDescripcion) {
		this.oficinaDescripcion = oficinaDescripcion;
	}

	public long getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(long dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public long getDependenciaDescripcion() {
		return dependenciaDescripcion;
	}

	public void setDependenciaDescripcion(long dependenciaDescripcion) {
		this.dependenciaDescripcion = dependenciaDescripcion;
	}

	public int getEstadoEquipoCertificacionId() {
		return estadoEquipoCertificacionId;
	}

	public void setEstadoEquipoCertificacionId(int estadoEquipoCertificacionId) {
		this.estadoEquipoCertificacionId = estadoEquipoCertificacionId;
	}

	public int getEstadoEquipoCertificacionDescripcion() {
		return estadoEquipoCertificacionDescripcion;
	}

	public void setEstadoEquipoCertificacionDescripcion(int estadoEquipoCertificacionDescripcion) {
		this.estadoEquipoCertificacionDescripcion = estadoEquipoCertificacionDescripcion;
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

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

}
