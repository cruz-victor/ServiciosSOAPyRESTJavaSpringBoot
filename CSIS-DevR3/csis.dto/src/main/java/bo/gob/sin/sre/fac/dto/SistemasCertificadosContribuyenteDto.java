package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class SistemasCertificadosContribuyenteDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long contribuyenteId;
	private Long sistemaId;
	private Long proveedorId;
	private Long usuarioId;
	private Integer modalidadFacturacionId;
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
	public Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}
	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
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
		return "SistemasCertificadosContribuyenteDto [contribuyenteId=" + contribuyenteId + ", sistemaId=" + sistemaId
				+ ", proveedorId=" + proveedorId + ", usuarioId=" + usuarioId + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", estadoSistemaContribuyenteId=" + estadoSistemaContribuyenteId
				+ ", observacion=" + observacion + "]";
	}
	
}
