package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class SistemasDto extends ListaMensajesAplicacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sistemaId;
	private Long usuarioUltimaModificacionId;
	private Long usuarioRegistroId;
	private Long tramiteId;
	private Integer tipoDepartamentoId;
	private Integer modalidadFacturacionId;
	private Integer tipoSistemaId;
	private Integer estadoSistemaId;
	private String nombreSistema;
	private String descripcionSistema;
	private String version;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;

	private List<SistemasDto> listaSistemas;

	private Long solicitudCertificacionId;
	private String descripcionModalidadFacturacionId;
	private String descripcionTipoSistema;
	private Long idContribuyenteProvedor;

	public String getDescripcionTipoSistema() {
		return descripcionTipoSistema;
	}

	public void setDescripcionTipoSistema(String descripcionTipoSistema) {
		this.descripcionTipoSistema = descripcionTipoSistema;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
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

	public String getDescripcionSistema() {
		return descripcionSistema;
	}

	public void setDescripcionSistema(String descripcionSistema) {
		this.descripcionSistema = descripcionSistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public List<SistemasDto> getListaSistemas() {
		return listaSistemas;
	}

	public void setListaSistemas(List<SistemasDto> listaSistemas) {
		this.listaSistemas = listaSistemas;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public String getDescripcionModalidadFacturacionId() {
		return descripcionModalidadFacturacionId;
	}

	public void setDescripcionModalidadFacturacionId(String descripcionModalidadFacturacionId) {
		this.descripcionModalidadFacturacionId = descripcionModalidadFacturacionId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdContribuyenteProvedor() {
		return idContribuyenteProvedor;
	}

	public void setIdContribuyenteProvedor(Long idContribuyenteProvedor) {
		this.idContribuyenteProvedor = idContribuyenteProvedor;
	}

	@Override
	public String toString() {
		return "SistemasDto [sistemaId=" + sistemaId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", tramiteId=" + tramiteId + ", tipoDepartamentoId="
				+ tipoDepartamentoId + ", modalidadFacturacionId=" + modalidadFacturacionId + ", tipoSistemaId="
				+ tipoSistemaId + ", estadoSistemaId=" + estadoSistemaId + ", nombreSistema=" + nombreSistema
				+ ", descripcionSistema=" + descripcionSistema + ", version=" + version + ", fechaRegistro="
				+ fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId
				+ ", listaSistemas=" + listaSistemas + ", solicitudCertificacionId=" + solicitudCertificacionId
				+ ", descripcionModalidadFacturacionId=" + descripcionModalidadFacturacionId
				+ ", descripcionTipoSistema=" + descripcionTipoSistema + ", idContribuyenteProvedor="
				+ idContribuyenteProvedor + "]";
	}
}
