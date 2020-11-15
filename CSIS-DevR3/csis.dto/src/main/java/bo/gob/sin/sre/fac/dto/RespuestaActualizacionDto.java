package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaActualizacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private boolean estaActualizado;
	private boolean estaRegistrado;
	
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
	private String codigoCertificado;
	private String citeCertificado;
	private Date fechaEmisionCertificado;
	private String personaContacto;
	private String telefonoContacto;
	private String correoContacto;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String cuis;
	private String estadoId;
	private Integer estadoInicioId;

	private List<RespuestaActualizacionDto> listaSolicitudesCertificaciones;
	
	private List<SreSolicitudCertificacion> listaCertificacion;
	
	private boolean solicitudWebSucursal;

	public boolean isEstaActualizado() {
		return estaActualizado;
	}

	public RespuestaActualizacionDto() {
		listaSolicitudesCertificaciones = new ArrayList<>();
	}

	public void setEstaActualizado(boolean estaActualizado) {
		this.estaActualizado = estaActualizado;
	}

	public List<RespuestaActualizacionDto> getListaSolicitudesCertificaciones() {
		return listaSolicitudesCertificaciones;
	}

	public void setListaSolicitudesCertificaciones(List<RespuestaActualizacionDto> listaSolicitudesCertificaciones) {
		this.listaSolicitudesCertificaciones = listaSolicitudesCertificaciones;
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

	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	
	
	

	public Integer getEstadoInicioId() {
		return estadoInicioId;
	}

	public void setEstadoInicioId(Integer estadoInicioId) {
		this.estadoInicioId = estadoInicioId;
	}

	public boolean isEstaRegistrado() {
		return estaRegistrado;
	}

	public void setEstaRegistrado(boolean estaRegistrado) {
		this.estaRegistrado = estaRegistrado;
	}

	
	

	public List<SreSolicitudCertificacion> getListaCertificacion() {
		return listaCertificacion;
	}

	public void setListaCertificacion(List<SreSolicitudCertificacion> listaCertificacion) {
		this.listaCertificacion = listaCertificacion;
	}
	
	

	

	public boolean isSolicitudWebSucursal() {
		return solicitudWebSucursal;
	}

	public void setSolicitudWebSucursal(boolean solicitudWebSucursal) {
		this.solicitudWebSucursal = solicitudWebSucursal;
	}

	@Override
	public String toString() {
		return "EmpResActualizacion [estaActualizado=" + estaActualizado + "]";
	}
}
