package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ComponentsCertificadosDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer componenteCertificadoId;
	private Long componenteArchivoId;
	private String estadoId;
	private Long sistemaId;
	private Integer tipoComponenteId;
	private String tipoCompoonenteDescripcion;
	private long solicitudCertificacionId;
	
	public ComponentsCertificadosDto() 
	{
	}
	
	public ComponentsCertificadosDto(Integer componenteCertificadoId, Long componenteArchivoId, String estadoId,
			Long sistemaId, Integer tipoComponenteId, String tipoCompoonenteDescripcion,
			long solicitudCertificacionId) {
		super();
		this.componenteCertificadoId = componenteCertificadoId;
		this.componenteArchivoId = componenteArchivoId;
		this.estadoId = estadoId;
		this.sistemaId = sistemaId;
		this.tipoComponenteId = tipoComponenteId;
		this.tipoCompoonenteDescripcion = tipoCompoonenteDescripcion;
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public String getTipoCompoonenteDescripcion() {
		return tipoCompoonenteDescripcion;
	}

	public void setTipoCompoonenteDescripcion(String tipoCompoonenteDescripcion) {
		this.tipoCompoonenteDescripcion = tipoCompoonenteDescripcion;
	}

	public Integer getComponenteCertificadoId() {
		return componenteCertificadoId;
	}
	public void setComponenteCertificadoId(Integer componenteCertificadoId) {
		this.componenteCertificadoId = componenteCertificadoId;
	}
	public Long getComponenteArchivoId() {
		return componenteArchivoId;
	}
	public void setComponenteArchivoId(Long componenteArchivoId) {
		this.componenteArchivoId = componenteArchivoId;
	}
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Integer getTipoComponenteId() {
		return tipoComponenteId;
	}
	public void setTipoComponenteId(Integer tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}
	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}
	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}


}
