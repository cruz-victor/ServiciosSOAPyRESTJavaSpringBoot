package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

//IASC
public class RegistroAutorizacionRechazoCertificacionDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long sistemaId;
	private Long solicitudCertificacionId;
	private Long tramiteId;
	private Integer tipoResultadoCertificacionId;
	private String motivoRechazo;
	private Long usuarioId;
	private boolean estaRegistrado;
	
	
	
	public RegistroAutorizacionRechazoCertificacionDto() {
		
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
	public Long getTramiteId() {
		return tramiteId;
	}
	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}
	public Integer getTipoResultadoCertificacionId() {
		return tipoResultadoCertificacionId;
	}
	public void setTipoResultadoCertificacionId(Integer tipoResultadoCertificacionId) {
		this.tipoResultadoCertificacionId = tipoResultadoCertificacionId;
	}
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public boolean isEstaRegistrado() {
		return estaRegistrado;
	}
	
	public void setEstaRegistrado(boolean estaRegistrado) {
		this.estaRegistrado = estaRegistrado;
	}
	@Override
	public String toString() {
		return "RegistroAutorizacionRechazoCertificacionDto [sistemaId=" + sistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tramiteId=" + tramiteId + ", tipoResultadoCertificacionId="
				+ tipoResultadoCertificacionId + ", motivoRechazo=" + motivoRechazo + ", usuarioId=" + usuarioId
				+ ", estaRegistrado=" + estaRegistrado + "]";
	}
	
}
