package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ReporteDatosSistemasSolCertificacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Date fechaSolicitud;
	private long nit;
	private String razonSocial;
	private String nombreSistema;
	private String version;
	private String codigoSistema;
	private String modalidad;
	
	//Datos de salida
	private long sistemaId;
	private long solicitudCertificacionId;
	
	public ReporteDatosSistemasSolCertificacionDto(Date fechaSolicitud, long nit, String razonSocial,
			String nombreSistema, String version, String codigoSistema, String modalidad, long sistemaId,
			long solicitudCertificacionId) {
		this.fechaSolicitud = fechaSolicitud;
		this.nit = nit;
		this.razonSocial = razonSocial;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.codigoSistema = codigoSistema;
		this.modalidad = modalidad;
		this.sistemaId = sistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public ReporteDatosSistemasSolCertificacionDto() {
		
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	
	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	@Override
	public String toString() {
		return "RespuestaDatosSistemasSolCertificacionDto [fechaSolicitud=" + fechaSolicitud + ", nit=" + nit
				+ ", razonSocial=" + razonSocial + ", nombreSistema=" + nombreSistema + ", version=" + version
				+ ", codigoSistema=" + codigoSistema + ", modalidad=" + modalidad + ", sistemaId=" + sistemaId
				+ ", solicitudCertificacionId=" + solicitudCertificacionId + "]";
	}
}
