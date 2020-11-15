package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class RegistroVerificacionesInsituDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long registroPruebaAutomaticaId;
	private long usuarioRegistroId;
	private long usuarioUltimaModficacionId;
	private long solicitudCertificacionId;
	private long tramiteId;
	private long pruebaAutomaticaId;
	private long componenteId;
	private int estadoId;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;

	private String descripcionComponente;
	private String descripcionEstado;
	
	public RegistroVerificacionesInsituDto() {
		super();
	}

	public RegistroVerificacionesInsituDto(long registroPruebaAutomaticaId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long solicitudCertificacionId, long tramiteId, long pruebaAutomaticaId,
			long sistemaId, int estadoPruebaId, long cantidadIntento, long cantidadCorrectos, long cantidadErrores,
			long porcentajeCorrectos, long porcentajeErrores, Date fechaInicio, Date fechaFin, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId) {
		super();
		this.registroPruebaAutomaticaId = registroPruebaAutomaticaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaAutomaticaId = pruebaAutomaticaId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public long getRegistroPruebaAutomaticaId() {
		return registroPruebaAutomaticaId;
	}

	public void setRegistroPruebaAutomaticaId(long registroPruebaAutomaticaId) {
		this.registroPruebaAutomaticaId = registroPruebaAutomaticaId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getUsuarioUltimaModficacionId() {
		return usuarioUltimaModficacionId;
	}

	public void setUsuarioUltimaModficacionId(long usuarioUltimaModficacionId) {
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
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

	public long getPruebaAutomaticaId() {
		return pruebaAutomaticaId;
	}

	public void setPruebaAutomaticaId(long pruebaAutomaticaId) {
		this.pruebaAutomaticaId = pruebaAutomaticaId;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	public long getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(long componenteId) {
		this.componenteId = componenteId;
	}

	public int getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}

	public String getDescripcionComponente() {
		return descripcionComponente;
	}

	public void setDescripcionComponente(String descripcionComponente) {
		this.descripcionComponente = descripcionComponente;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	@Override
	public String toString() {
		return "RegistrosPruebasAutomaticasDto [registroPruebaAutomaticaId=" + registroPruebaAutomaticaId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModficacionId="
				+ usuarioUltimaModficacionId + ", solicitudCertificacionId=" + solicitudCertificacionId + ", tramiteId="
				+ tramiteId + ", pruebaAutomaticaId=" + pruebaAutomaticaId
				+ ", fechaFin=" + fechaFin + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

}
