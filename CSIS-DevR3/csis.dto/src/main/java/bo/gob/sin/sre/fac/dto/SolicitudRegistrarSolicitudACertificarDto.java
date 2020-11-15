package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class SolicitudRegistrarSolicitudACertificarDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long solicitudCertificacionId;
	private Long tramiteId;
	private Long usuarioRegistroId;
	private Long usuarioUltimaModficacionId;
	private Long contribuyenteId;
	private Integer oficinaId;
	private Integer dependenciaId;
	private Integer tipoSolicitudId;
	private Integer modalidadId;
	private Integer estadoSolicitudCertificacionId;
	private Long sistemaId;
	private Long certificadoAutorizacionId;
	private String codigoCertificado;
	private String citeCertificado;
	private Date fechaEmisionCertificado;
	private String personaContacto;
	private String telefonoContacto;
	private String correoContacto;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;
	private String cuis;
	private Long nitContribuyente;
	
	public SolicitudRegistrarSolicitudACertificarDto() {
		
	}	

	public SolicitudRegistrarSolicitudACertificarDto(Long solicitudCertificacionId, Long tramiteId,
			Long usuarioRegistroId, Long usuarioUltimaModficacionId, Long contribuyenteId, Integer oficinaId,
			Integer dependenciaId, Integer tipoSolicitudId, Integer modalidadId, Integer estadoSolicitudCertificacionId,
			Long sistemaId, Long certificadoAutorizacionId, String codigoCertificado, String citeCertificado,
			Date fechaEmisionCertificado, String personaContacto, String telefonoContacto, String correoContacto,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId, String cuis, Long nitContribuyente) {
		super();
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.contribuyenteId = contribuyenteId;
		this.oficinaId = oficinaId;
		this.dependenciaId = dependenciaId;
		this.tipoSolicitudId = tipoSolicitudId;
		this.modalidadId = modalidadId;
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
		this.sistemaId = sistemaId;
		this.certificadoAutorizacionId = certificadoAutorizacionId;
		this.codigoCertificado = codigoCertificado;
		this.citeCertificado = citeCertificado;
		this.fechaEmisionCertificado = fechaEmisionCertificado;
		this.personaContacto = personaContacto;
		this.telefonoContacto = telefonoContacto;
		this.correoContacto = correoContacto;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.cuis = cuis;
		this.nitContribuyente = nitContribuyente;
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

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModficacionId() {
		return usuarioUltimaModficacionId;
	}

	public void setUsuarioUltimaModficacionId(Long usuarioUltimaModficacionId) {
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public Integer getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(Integer oficinaId) {
		this.oficinaId = oficinaId;
	}

	public Integer getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(Integer dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public Integer getTipoSolicitudId() {
		return tipoSolicitudId;
	}

	public void setTipoSolicitudId(Integer tipoSolicitudId) {
		this.tipoSolicitudId = tipoSolicitudId;
	}

	public Integer getModalidadId() {
		return modalidadId;
	}

	public void setModalidadId(Integer modalidadId) {
		this.modalidadId = modalidadId;
	}

	public Integer getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}

	public void setEstadoSolicitudCertificacionId(Integer estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getCertificadoAutorizacionId() {
		return certificadoAutorizacionId;
	}

	public void setCertificadoAutorizacionId(Long certificadoAutorizacionId) {
		this.certificadoAutorizacionId = certificadoAutorizacionId;
	}

	public String getCodigoCertificado() {
		return codigoCertificado;
	}

	public void setCodigoCertificado(String codigoCertificado) {
		this.codigoCertificado = codigoCertificado;
	}

	public String getCiteCertificado() {
		return citeCertificado;
	}

	public void setCiteCertificado(String citeCertificado) {
		this.citeCertificado = citeCertificado;
	}

	public Date getFechaEmisionCertificado() {
		return fechaEmisionCertificado;
	}

	public void setFechaEmisionCertificado(Date fechaEmisionCertificado) {
		this.fechaEmisionCertificado = fechaEmisionCertificado;
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

	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public Long getNitContribuyente() {
		return nitContribuyente;
	}

	public void setNitContribuyente(Long nitContribuyente) {
		this.nitContribuyente = nitContribuyente;
	}

	@Override
	public String toString() {
		return "SolicitudRegistrarSolicitudACertificarDto [solicitudCertificacionId=" + solicitudCertificacionId
				+ ", tramiteId=" + tramiteId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModficacionId=" + usuarioUltimaModficacionId + ", contribuyenteId=" + contribuyenteId
				+ ", oficinaId=" + oficinaId + ", dependenciaId=" + dependenciaId + ", tipoSolicitudId="
				+ tipoSolicitudId + ", modalidadId=" + modalidadId + ", estadoSolicitudCertificacionId="
				+ estadoSolicitudCertificacionId + ", sistemaId=" + sistemaId + ", certificadoAutorizacionId="
				+ certificadoAutorizacionId + ", codigoCertificado=" + codigoCertificado + ", citeCertificado="
				+ citeCertificado + ", fechaEmisionCertificado=" + fechaEmisionCertificado + ", personaContacto="
				+ personaContacto + ", telefonoContacto=" + telefonoContacto + ", correoContacto=" + correoContacto
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion
				+ ", estadoId=" + estadoId + ", cuis=" + cuis + ", nitContribuyente=" + nitContribuyente + "]";
	}
}
