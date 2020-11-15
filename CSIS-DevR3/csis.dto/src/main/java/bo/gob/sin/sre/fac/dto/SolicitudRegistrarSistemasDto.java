package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class SolicitudRegistrarSistemasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long sistemaId;
	private Long tramiteId;
	private Long nitContribuyente;
	private Integer tipoDepartamentoId;
	private Integer modalidadFacturacionId;
	private Integer tipoSistemaId;
	private Integer estadoSistemaId;
	private String nombreSistema;
	private String version;
	private String estadoId;
	private Long usuarioUltimaModificacionId;
	private Long usuarioRegistroId;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;

	private Long solicitudCertificacionId;
	// private Long tramiteId;
	// private Long usuarioRegistroId;
	private Long usuarioUltimaModficacionId;
	private Long contribuyenteId;
	private Integer oficinaId;
	private Integer dependenciaId;
	private Integer tipoSolicitudId;
	private Integer modalidadId;
	private Integer estadoSolicitudCertificacionId;
	// private Long sistemaId;
	// private Long certificadoAutorizacionId;
	private String codigoCertificado;
	private String citeCertificado;
	private Date fechaEmisionCertificado;
	private String personaContacto;
	private String telefonoContacto;
	private String correoContacto;
	// private Date fechaRegistro;
	// private Date fechaUltimaModificacion;
	// private String estadoId;

	private List<Integer> tiposPrueba;
	private List<Integer> tiposModulos;

	private List<SolicitudRegistrarSistemasDto> lista;


	public SolicitudRegistrarSistemasDto(Long sistemaId, Long tramiteId, Long nitContribuyente,
			Integer tipoDepartamentoId, Integer modalidadFacturacionId, Integer tipoSistemaId, Integer estadoSistemaId,
			String nombreSistema, String version, String estadoId, Long usuarioUltimaModificacionId,
			Long usuarioRegistroId, Date fechaRegistro, Date fechaUltimaModificacion, Long solicitudCertificacionId,
			Long usuarioUltimaModficacionId, Long contribuyenteId, Integer oficinaId, Integer dependenciaId,
			Integer tipoSolicitudId, Integer modalidadId, Integer estadoSolicitudCertificacionId,
			String codigoCertificado, String citeCertificado, Date fechaEmisionCertificado, String personaContacto,
			String telefonoContacto, String correoContacto, List<Integer> tiposPrueba, List<Integer> tiposModulos,
			List<SolicitudRegistrarSistemasDto> lista) {
		super();
		this.sistemaId = sistemaId;
		this.tramiteId = tramiteId;
		this.nitContribuyente = nitContribuyente;
		this.tipoDepartamentoId = tipoDepartamentoId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.tipoSistemaId = tipoSistemaId;
		this.estadoSistemaId = estadoSistemaId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.estadoId = estadoId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.contribuyenteId = contribuyenteId;
		this.oficinaId = oficinaId;
		this.dependenciaId = dependenciaId;
		this.tipoSolicitudId = tipoSolicitudId;
		this.modalidadId = modalidadId;
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
		this.codigoCertificado = codigoCertificado;
		this.citeCertificado = citeCertificado;
		this.fechaEmisionCertificado = fechaEmisionCertificado;
		this.personaContacto = personaContacto;
		this.telefonoContacto = telefonoContacto;
		this.correoContacto = correoContacto;
		this.tiposPrueba = tiposPrueba;
		this.tiposModulos = tiposModulos;
		this.lista = lista;
	}

	public SolicitudRegistrarSistemasDto() {
		this.tiposPrueba = new ArrayList<>();
		this.tiposModulos = new ArrayList<>();
		this.lista = lista = new ArrayList<>();
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public Integer getTipoDepartamentoId() {
		return tipoDepartamentoId;
	}

	public void setTipoDepartamentoId(Integer tipoDepartamentoId) {
		this.tipoDepartamentoId = tipoDepartamentoId;
	}

	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	public Integer getTipoSistemaId() {
		return tipoSistemaId;
	}

	public void setTipoSistemaId(Integer tipoSistemaId) {
		this.tipoSistemaId = tipoSistemaId;
	}

	public Integer getEstadoSistemaId() {
		return estadoSistemaId;
	}

	public void setEstadoSistemaId(Integer estadoSistemaId) {
		this.estadoSistemaId = estadoSistemaId;
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

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
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

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
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

	public List<Integer> getTiposPrueba() {
		return tiposPrueba;
	}

	public void setTiposPrueba(List<Integer> tiposPrueba) {
		this.tiposPrueba = tiposPrueba;
	}

	public List<Integer> getTiposModulos() {
		return tiposModulos;
	}

	public void setTiposModulos(List<Integer> tiposModulos) {
		this.tiposModulos = tiposModulos;
	}

	public List<SolicitudRegistrarSistemasDto> getLista() {
		return lista;
	}

	public void setLista(List<SolicitudRegistrarSistemasDto> lista) {
		this.lista = lista;
	}

	public Long getNitContribuyente() {
		return nitContribuyente;
	}

	public void setNitContribuyente(Long nitContribuyente) {
		this.nitContribuyente = nitContribuyente;
	}

	@Override
	public String toString() {
		return "SolicitudRegistrarSistemasDto [sistemaId=" + sistemaId + ", tramiteId=" + tramiteId
				+ ", nitContribuyente=" + nitContribuyente + ", tipoDepartamentoId=" + tipoDepartamentoId
				+ ", modalidadFacturacionId=" + modalidadFacturacionId + ", tipoSistemaId=" + tipoSistemaId
				+ ", estadoSistemaId=" + estadoSistemaId + ", nombreSistema=" + nombreSistema + ", version=" + version
				+ ", estadoId=" + estadoId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", usuarioUltimaModficacionId=" + usuarioUltimaModficacionId
				+ ", contribuyenteId=" + contribuyenteId + ", oficinaId=" + oficinaId + ", dependenciaId="
				+ dependenciaId + ", tipoSolicitudId=" + tipoSolicitudId + ", modalidadId=" + modalidadId
				+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId + ", codigoCertificado="
				+ codigoCertificado + ", citeCertificado=" + citeCertificado + ", fechaEmisionCertificado="
				+ fechaEmisionCertificado + ", personaContacto=" + personaContacto + ", telefonoContacto="
				+ telefonoContacto + ", correoContacto=" + correoContacto + ", tiposPrueba=" + tiposPrueba
				+ ", tiposModulos=" + tiposModulos + ", lista=" + lista + "]";
	}

}
