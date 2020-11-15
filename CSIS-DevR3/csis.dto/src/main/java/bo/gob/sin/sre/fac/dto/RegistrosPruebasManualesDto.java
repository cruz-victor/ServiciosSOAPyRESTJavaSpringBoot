package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class RegistrosPruebasManualesDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long registroPruebaManualId;
	private long usuarioRegistroId;
	private long usuarioUltimaModficacionId;
	private long solicitudCertificacionId;
	private long tramiteId;
	private long pruebaManualId;
	private long sistemaId;
	private int estadoPruebaId;
	private String observaciones;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;
	private String nombre;
	private String descripcion;
	
	public RegistrosPruebasManualesDto() {
		super();
		
	}
	public RegistrosPruebasManualesDto(long registroPruebaManualId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long solicitudCertificacionId, long tramiteId, long pruebaManualId,
			long sistemaId, int estadoPruebaId, String observaciones, Date fechaInicio, Date fechaFin,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId,String nombre) {
		super();
		this.registroPruebaManualId = registroPruebaManualId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaManualId = pruebaManualId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.observaciones = observaciones;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.nombre=nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getRegistroPruebaManualId() {
		return registroPruebaManualId;
	}


	public void setRegistroPruebaManualId(long registroPruebaManualId) {
		this.registroPruebaManualId = registroPruebaManualId;
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


	public long getPruebaManualId() {
		return pruebaManualId;
	}


	public void setPruebaManualId(long pruebaManualId) {
		this.pruebaManualId = pruebaManualId;
	}


	public long getSistemaId() {
		return sistemaId;
	}


	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}


	public int getEstadoPruebaId() {
		return estadoPruebaId;
	}


	public void setEstadoPruebaId(int estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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


	public String getEstadoId() {
		return estadoId;
	}


	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "RegistrosPruebasManualesDto [registroPruebaManualId=" + registroPruebaManualId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModficacionId=" + usuarioUltimaModficacionId
				+ ", solicitudCertificacionId=" + solicitudCertificacionId + ", tramiteId=" + tramiteId
				+ ", pruebaManualId=" + pruebaManualId + ", sistemaId=" + sistemaId + ", estadoPruebaId="
				+ estadoPruebaId + ", observaciones=" + observaciones + ", estadoId=" + estadoId + ", nombre=" + nombre +"]";
	}
	
	

}
