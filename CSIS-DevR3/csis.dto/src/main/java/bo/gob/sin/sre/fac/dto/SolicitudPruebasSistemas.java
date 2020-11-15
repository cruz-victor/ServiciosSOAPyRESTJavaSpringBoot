package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class SolicitudPruebasSistemas  implements Serializable
{
	private static final long serialVersionUID = 1L;

	public SolicitudPruebasSistemas(Long pruebaSistemaId, Long solicitudCertificacionId, Long tramiteId, Long pruebaId,
			Long sistemaId, Integer estadoPruebaId, String observaciones, String estadoId, Date fechaInicio,
			Date fechaFin, Long usuarioRegistroId, Long usuarioUltimaModificacionId, Date fechaRegistro,
			Date fechaUltimaModificacion) {
		super();
		this.pruebaSistemaId = pruebaSistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaId = pruebaId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.observaciones = observaciones;
		this.estadoId = estadoId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	public SolicitudPruebasSistemas() {
		
	}
	
	private Long pruebaSistemaId;
	private Long solicitudCertificacionId;
	private Long tramiteId;
	private Long pruebaId;
	private Long sistemaId;
	private Integer estadoPruebaId;
	private String observaciones;
	private String estadoId;
	private Date fechaInicio;
	private Date fechaFin;
	private Long usuarioRegistroId;
	private Long usuarioUltimaModificacionId;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	
	public Long getPruebaSistemaId() {
		return pruebaSistemaId;
	}
	public void setPruebaSistemaId(Long pruebaSistemaId) {
		this.pruebaSistemaId = pruebaSistemaId;
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
	public Long getPruebaId() {
		return pruebaId;
	}
	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Integer getEstadoPruebaId() {
		return estadoPruebaId;
	}
	public void setEstadoPruebaId(Integer estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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
		return "SreFacSolPruebasSistemas [pruebaSistemaId=" + pruebaSistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tramiteId=" + tramiteId + ", pruebaId=" + pruebaId + ", sistemaId="
				+ sistemaId + ", estadoPruebaId=" + estadoPruebaId + ", observaciones=" + observaciones + ", estadoId="
				+ estadoId + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + "]";
	}
}
