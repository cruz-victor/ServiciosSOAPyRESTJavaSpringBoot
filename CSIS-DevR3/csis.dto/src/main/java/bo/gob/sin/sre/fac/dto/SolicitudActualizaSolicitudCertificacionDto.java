package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class SolicitudActualizaSolicitudCertificacionDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long sistemaId;
	private Long solicitudCertificacionId;
	private Long contribuyenteId;
	private String nombreSistema;
	private Date fechaRegistro;
	private String tipoSistemaId;
	private String codigoCertificado;
	private String citeCertificado;
	private Date fechaEmisionCertificado;
	private String personaContacto;
	private Integer telefonoContacto;
	private String correoContacto;
	private String modalidadId;
	private String estadoId;
	private Long idUsuario;
	private Integer tipoPrueba;

	public SolicitudActualizaSolicitudCertificacionDto() {
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	
	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
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

	public Integer getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(Integer telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getModalidadId() {
		return modalidadId;
	}

	public void setModalidadId(String modalidadId) {
		this.modalidadId = modalidadId;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(Integer tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

}
