package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudActualizaPruebaCertificacionSistemaDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long pruebaCertificacionSistemaId;	
	private Long etapaCertificacionSistemaId;
	private boolean inicioPrueba;
	private Long sistemaId;
	private Long contribuyenteId; 
	private String modalidadFacturacion;
	
	public Long getPruebaCertificacionSistemaId() {
		return pruebaCertificacionSistemaId;
	}
	public void setPruebaCertificacionSistemaId(Long pruebaCertificacionSistemaId) {
		this.pruebaCertificacionSistemaId = pruebaCertificacionSistemaId;
	}
	public Long getEtapaCertificacionSistemaId() {
		return etapaCertificacionSistemaId;
	}
	public void setEtapaCertificacionSistemaId(Long etapaCertificacionSistemaId) {
		this.etapaCertificacionSistemaId = etapaCertificacionSistemaId;
	}
	public boolean isInicioPrueba() {
		return inicioPrueba;
	}
	public void setInicioPrueba(boolean inicioPrueba) {
		this.inicioPrueba = inicioPrueba;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public String getModalidadFacturacion() {
		return modalidadFacturacion;
	}
	public void setModalidadFacturacion(String modalidadFacturacion) {
		this.modalidadFacturacion = modalidadFacturacion;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Long getContribuyenteId() {
		return contribuyenteId;
	}
	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}	
}
