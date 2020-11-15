package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class RecuperaListaSistemasCertificacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long personaContribuyenteId;
	private int estadoSolicitudCertificacionId;
	private Date  fechaAprobacion;
	private long solicitudCertificacionId;
	private long sistemaId;
	private String nombreSistema;
	private String version;
	private String descripcionEstadoSolicitud;
	private String descripcionModalidad;
	private String razonSocial;
	private Long nit;

	public RecuperaListaSistemasCertificacionDto(long solicitudCertificacionId, long nit, String razonSocial,
			long personaContribuyenteId, String nombreSistema, String version, String estadoCertificacion,
			String modalidad) {
		super();
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.nit = nit;
		this.razonSocial = razonSocial;
		this.personaContribuyenteId = personaContribuyenteId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.descripcionEstadoSolicitud = estadoCertificacion;
		this.descripcionModalidad = modalidad;
	}
	
	public RecuperaListaSistemasCertificacionDto(Long personaContribuyenteId, int estadoSolicitudCertificacionId,
			Date fechaAprobacion, long solicitudCertificacionId, long sistemaId, String nombreSistema, String version,
			String descripcionEstadoSolicitud, String descripcionModalidad, String razonSocial, long nit) {
		super();
		this.personaContribuyenteId = personaContribuyenteId;
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
		this.fechaAprobacion = fechaAprobacion;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.sistemaId = sistemaId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.descripcionEstadoSolicitud = descripcionEstadoSolicitud;
		this.descripcionModalidad = descripcionModalidad;
		this.razonSocial  = razonSocial;
		this.nit = nit;
	}

	public RecuperaListaSistemasCertificacionDto() {

	}

	public Long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(Long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public int getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}

	public void setEstadoSolicitudCertificacionId(int estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
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

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	
	public String getDescripcionEstadoSolicitud() {
		return descripcionEstadoSolicitud;
	}

	public void setDescripcionEstadoSolicitud(String descripcionEstadoSolicitud) {
		this.descripcionEstadoSolicitud = descripcionEstadoSolicitud;
	}

	public String getDescripcionModalidad() {
		return descripcionModalidad;
	}

	public void setDescripcionModalidad(String descripcionModalidad) {
		this.descripcionModalidad = descripcionModalidad;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	@Override
	public String toString() {
		return "RecuperaListaSistemasCertificacionDto [personaContribuyenteId=" + personaContribuyenteId
				+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId + ", fechaAprobacion="
				+ fechaAprobacion + ", solicitudCertificacionId=" + solicitudCertificacionId + ", sistemaId="
				+ sistemaId + ", nombreSistema=" + nombreSistema + ", version=" + version
				+ ", DescripcionEstadoSolicitud=" + descripcionEstadoSolicitud + ", descripcionModalidad="
				+ descripcionModalidad + "]";
	}
	
	
	


}
