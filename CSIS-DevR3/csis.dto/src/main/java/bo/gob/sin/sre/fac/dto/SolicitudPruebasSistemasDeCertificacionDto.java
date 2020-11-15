package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudPruebasSistemasDeCertificacionDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long sistemaId;
	private Long solicitudCertificacionId;
	
	
	//Funcionario
	private Long contribuyenteId;
	private Integer oficinaId; 

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

	public Integer getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(Integer oficinaId) {
		this.oficinaId = oficinaId;
	}
	
	
}
