package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudRegistroProveedorDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long contribuyenteId;
	private Long sistemaId;
	private Long contribuyenteProveedorId;
	private Long usuarioApruebaId;
	private Long usuarioBajaId;
	private Long usuarioRegistroId;
	private Long usuarioUltimaModificacionId;
	private Long modalidadFacturacionId;
	private Integer estadoSistemaContribuyenteId;
	private String observacion;
	
	public Long getContribuyenteId() {
		return contribuyenteId;
	}
	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Long getContribuyenteProveedorId() {
		return contribuyenteProveedorId;
	}
	public void setContribuyenteProveedorId(Long contribuyenteProveedorId) {
		this.contribuyenteProveedorId = contribuyenteProveedorId;
	}
	public Long getUsuarioApruebaId() {
		return usuarioApruebaId;
	}
	public void setUsuarioApruebaId(Long usuarioApruebaId) {
		this.usuarioApruebaId = usuarioApruebaId;
	}
	public Long getUsuarioBajaId() {
		return usuarioBajaId;
	}
	public void setUsuarioBajaId(Long usuarioBajaId) {
		this.usuarioBajaId = usuarioBajaId;
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
	public Long getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}
	public void setModalidadFacturacionId(Long modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}
	public Integer getEstadoSistemaContribuyenteId() {
		return estadoSistemaContribuyenteId;
	}
	public void setEstadoSistemaContribuyenteId(Integer estadoSistemaContribuyenteId) {
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	@Override
	public String toString() {
		return "SolicitudRegistroProveedorDto [contribuyenteId=" + contribuyenteId + ", sistemaId=" + sistemaId
				+ ", contribuyenteProveedorId=" + contribuyenteProveedorId + ", usuarioApruebaId=" + usuarioApruebaId
				+ ", usuarioBajaId=" + usuarioBajaId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", estadoSistemaContribuyenteId=" + estadoSistemaContribuyenteId
				+ ", observacion=" + observacion + "]";
	}
	
}
