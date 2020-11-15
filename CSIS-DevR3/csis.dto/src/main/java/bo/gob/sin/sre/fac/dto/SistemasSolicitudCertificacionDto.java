package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class SistemasSolicitudCertificacionDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long sistemaId;
	private String nombreSistema;
	private Long solicitudCertificacionId;
	private Long contribuyenteId;
	private Long tramiteId;
	private String tipoSistema;
	private String modalidad;
	private Long modalidadId;
	
	public SistemasSolicitudCertificacionDto() {
		
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
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

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public String getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Long getModalidadId() {
		return modalidadId;
	}

	public void setModalidadId(Long modalidadId) {
		this.modalidadId = modalidadId;
	}

	@Override
	public String toString() {
		return "SistemasSolicitudCertificacionDto [sistemaId=" + sistemaId + ", nombreSistema=" + nombreSistema
				+ ", solicitudCertificacionId=" + solicitudCertificacionId + ", contribuyenteId=" + contribuyenteId
				+ ", tramiteId=" + tramiteId + ", tipoSistema=" + tipoSistema + ", modalidad=" + modalidad
				+ ", modalidadId=" + modalidadId + "]";
	}
}
