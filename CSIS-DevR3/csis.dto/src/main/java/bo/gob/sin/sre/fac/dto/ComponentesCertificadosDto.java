package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ComponentesCertificadosDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long componenteCertificadoId;
	private Long usuarioRegistroId;
	private Long usuarioUltimaModificacionId;
	private Long sistemaId;
	private Long sistemaModuloId;
	private Integer tipoComponenteId;
	private String md5;
	private String sha2;
	private String crc;
	private Date fechaUltimaModificacion;
	private Date fechaRegistro;
	private String estadoId;

	public ComponentesCertificadosDto(Long componenteCertificadoId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Long sistemaId, Long sistemaModuloId, Integer tipoComponenteId,
			String md5, String sha2, String crc, Date fechaUltimaModificacion, Date fechaRegistro, String estadoId) {
		super();
		this.componenteCertificadoId = componenteCertificadoId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.sistemaId = sistemaId;
		this.sistemaModuloId = sistemaModuloId;
		this.tipoComponenteId = tipoComponenteId;
		this.md5 = md5;
		this.sha2 = sha2;
		this.crc = crc;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.fechaRegistro = fechaRegistro;
		this.estadoId = estadoId;
	}

	public ComponentesCertificadosDto() {

	}

	public Long getComponenteCertificadoId() {
		return componenteCertificadoId;
	}

	public void setComponenteCertificadoId(Long componenteCertificadoId) {
		this.componenteCertificadoId = componenteCertificadoId;
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

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getSistemaModuloId() {
		return sistemaModuloId;
	}

	public void setSistemaModuloId(Long sistemaModuloId) {
		this.sistemaModuloId = sistemaModuloId;
	}

	public Integer getTipoComponenteId() {
		return tipoComponenteId;
	}

	public void setTipoComponenteId(Integer tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getSha2() {
		return sha2;
	}

	public void setSha2(String sha2) {
		this.sha2 = sha2;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "ComponentesCertificadosDto [componenteCertificadoId=" + componenteCertificadoId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", sistemaId="
				+ sistemaId + ", sistemaModuloId=" + sistemaModuloId + ", tipoComponenteId=" + tipoComponenteId
				+ ", md5=" + md5 + ", sha2=" + sha2 + ", crc=" + crc + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", fechaRegistro=" + fechaRegistro + ", estadoId=" + estadoId + "]";
	}

}
