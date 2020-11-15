package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;


public class SistemasCertificadosDto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long sistemaId;
	private Long contribuyenteId;
	private String nombreSistema;
	private Date fechaRegistro;
	private String tipoSistemaId;
	private String personaContacto;
	private String telefonoContacto;
	private String correoContacto;
	private Integer modalidadId;
	private String estadoId;
	private Long estadoSistemaId;
	private String version;
	private Long modalidadFacturacionId;
	private String modalidad;
	private String cuis;
	private Date fechaInicio;
	private Integer solicitudCertificacionId;
	private String solicitudCertificacion;
	private Integer sucursalId;
	private String estadoSolicitudCertificacion;
	private Long sistemaContribuyenteId;
	
	public SistemasCertificadosDto() {
	}
	
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Long getContribuyenteId() {
		return contribuyenteId;
	}
	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}
	public String getNombreSistema() {
		return nombreSistema;
	}
	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getTipoSistemaId() {
		return tipoSistemaId;
	}
	public void setTipoSistemaId(String tipoSistemaId) {
		this.tipoSistemaId = tipoSistemaId;
	}
	public String getPersonaContacto() {
		return personaContacto;
	}
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}
		
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getCorreoContacto() {
		return correoContacto;
	}
	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}
	
	public Integer getModalidadId() {
		return modalidadId;
	}
	public void setModalidadId(Integer modalidadId) {
		this.modalidadId = modalidadId;
	}
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public Long getEstadoSistemaId() {
		return estadoSistemaId;
	}
	public void setEstadoSistemaId(Long estadoSistemaId) {
		this.estadoSistemaId = estadoSistemaId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Long getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}
	public void setModalidadFacturacionId(Long modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getCuis() {
		return cuis;
	}
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Integer getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}
	public void setSolicitudCertificacionId(Integer solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}
	public String getSolicitudCertificacion() {
		return solicitudCertificacion;
	}
	public void setSolicitudCertificacion(String solicitudCertificacion) {
		this.solicitudCertificacion = solicitudCertificacion;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	
	public String getEstadoSolicitudCertificacion() {
		return estadoSolicitudCertificacion;
	}

	public void setEstadoSolicitudCertificacion(String estadoSolicitudCertificacion) {
		this.estadoSolicitudCertificacion = estadoSolicitudCertificacion;
	}
	

	public Long getSistemaContribuyenteId() {
		return sistemaContribuyenteId;
	}

	public void setSistemaContribuyenteId(Long sistemaContribuyenteId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
	}

	@Override
	public String toString() {
		return "SistemasCertificadosDto [sistemaId=" + sistemaId + ", contribuyenteId=" + contribuyenteId
				+ ", nombreSistema=" + nombreSistema + ", fechaRegistro=" + fechaRegistro + ", tipoSistemaId="
				+ tipoSistemaId + ", personaContacto=" + personaContacto + ", telefonoContacto=" + telefonoContacto
				+ ", correoContacto=" + correoContacto + ", modalidadId=" + modalidadId + ", estadoId=" + estadoId
				+ ", estadoSistemaId=" + estadoSistemaId + ", version=" + version + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", modalidad=" + modalidad + ", cuis=" + cuis + ", fechaInicio="
				+ fechaInicio + ", solicitudCertificacionId=" + solicitudCertificacionId + ", solicitudCertificacion="
				+ solicitudCertificacion + ", sucursalId=" + sucursalId + ", estadoSolicitudCertificacion="
				+ estadoSolicitudCertificacion + ", sistemaContribuyenteId=" + sistemaContribuyenteId + "]";
	}
		
}
