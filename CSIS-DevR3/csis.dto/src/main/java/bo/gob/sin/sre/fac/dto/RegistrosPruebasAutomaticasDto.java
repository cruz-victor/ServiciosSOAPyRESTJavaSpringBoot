package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class RegistrosPruebasAutomaticasDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreSistema;
	private long registroPruebaAutomaticaId;
	private long usuarioRegistroId;
	private long usuarioUltimaModficacionId;
	private long solicitudCertificacionId;
	private long tramiteId;
	private long pruebaAutomaticaId;
	private long sistemaId;
	private int estadoPruebaId;
	private long cantidadIntento;
	private long cantidadCorrectos;
	private long cantidadErrores;
	private java.math.BigDecimal porcentajeCorrectos;
	private java.math.BigDecimal porcentajeErrores;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;

	private String descripcionPruebaAuto;
	private String descripcionEstadoPruebaAuto;
	
	private String nombrePrueba;
	
	
	public RegistrosPruebasAutomaticasDto() {
		super();
	}

	public RegistrosPruebasAutomaticasDto(long registroPruebaAutomaticaId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long solicitudCertificacionId, long tramiteId, long pruebaAutomaticaId,
			long sistemaId, int estadoPruebaId, long cantidadIntento, long cantidadCorrectos, long cantidadErrores,
			java.math.BigDecimal porcentajeCorrectos, java.math.BigDecimal porcentajeErrores, Date fechaInicio, Date fechaFin, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId, String nombrePrueba) {
		super();
		this.registroPruebaAutomaticaId = registroPruebaAutomaticaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaAutomaticaId = pruebaAutomaticaId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.cantidadIntento = cantidadIntento;
		this.cantidadCorrectos = cantidadCorrectos;
		this.cantidadErrores = cantidadErrores;
		this.porcentajeCorrectos = porcentajeCorrectos;
		this.porcentajeErrores = porcentajeErrores;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.nombrePrueba=nombrePrueba;
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

	public String getNombrePrueba() {
		return nombrePrueba;
	}

	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
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

	public long getCantidadIntento() {
		return cantidadIntento;
	}

	public void setCantidadIntento(long cantidadIntento) {
		this.cantidadIntento = cantidadIntento;
	}

	public long getCantidadCorrectos() {
		return cantidadCorrectos;
	}

	public void setCantidadCorrectos(long cantidadCorrectos) {
		this.cantidadCorrectos = cantidadCorrectos;
	}

	public long getCantidadErrores() {
		return cantidadErrores;
	}

	public void setCantidadErrores(long cantidadErrores) {
		this.cantidadErrores = cantidadErrores;
	}



	public java.math.BigDecimal getPorcentajeCorrectos() {
		return porcentajeCorrectos;
	}

	public void setPorcentajeCorrectos(java.math.BigDecimal porcentajeCorrectos) {
		this.porcentajeCorrectos = porcentajeCorrectos;
	}


	public java.math.BigDecimal getPorcentajeErrores() {
		return porcentajeErrores;
	}

	public void setPorcentajeErrores(java.math.BigDecimal porcentajeErrores) {
		this.porcentajeErrores = porcentajeErrores;
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

	
	public String getDescripcionPruebaAuto() {
		return descripcionPruebaAuto;
	}

	public void setDescripcionPruebaAuto(String descripcionPruebaAuto) {
		this.descripcionPruebaAuto = descripcionPruebaAuto;
	}

	public String getDescripcionEstadoPruebaAuto() {
		return descripcionEstadoPruebaAuto;
	}

	public void setDescripcionEstadoPruebaAuto(String descripcionEstadoPruebaAuto) {
		this.descripcionEstadoPruebaAuto = descripcionEstadoPruebaAuto;
	}

	@Override
	public String toString() {
		return "RegistrosPruebasAutomaticasDto [registroPruebaAutomaticaId=" + registroPruebaAutomaticaId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModficacionId="
				+ usuarioUltimaModficacionId + ", solicitudCertificacionId=" + solicitudCertificacionId + ", tramiteId="
				+ tramiteId + ", pruebaAutomaticaId=" + pruebaAutomaticaId + ", sistemaId=" + sistemaId
				+ ", estadoPruebaId=" + estadoPruebaId + ", cantidadIntento=" + cantidadIntento + ", cantidadCorrectos="
				+ cantidadCorrectos + ", cantidadErrores=" + cantidadErrores + ", porcentajeCorrectos="
				+ porcentajeCorrectos + ", porcentajeErrores=" + porcentajeErrores + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

}
