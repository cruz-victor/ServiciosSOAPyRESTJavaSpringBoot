package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class SolicitudActualizaCertificacionSistemaDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long certificacionId;
	private long sistemaId;
	private long contribuyenteId;
	private int estadoCertificacion;
	private Date fechaInicio;
	private Date fechaFin;
	private int usuarioId;
	private String estadoId;
	
	public long getCertificacionId() {
		return certificacionId;
	}
	public void setCertificacionId(long certificacionId) {
		this.certificacionId = certificacionId;
	}
	public long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public int getEstadoCertificacion() {
		return estadoCertificacion;
	}
	public void setEstadoCertificacion(int estadoCertificacion) {
		this.estadoCertificacion = estadoCertificacion;
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
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public long getContribuyenteId() {
		return contribuyenteId;
	}
	public void setContribuyenteId(long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SolicitudActualizaCertificacionSistema [certificacionId=").append(certificacionId)
				.append(", sistemaId=").append(sistemaId).append(", contribuyenteId=").append(contribuyenteId)
				.append(", estadoCertificacion=").append(estadoCertificacion).append(", fechaInicio=")
				.append(fechaInicio).append(", fechaFin=").append(fechaFin).append(", usuarioId=").append(usuarioId)
				.append(", estadoId=").append(estadoId).append("]");
		return builder.toString();
	}
	
	
}
