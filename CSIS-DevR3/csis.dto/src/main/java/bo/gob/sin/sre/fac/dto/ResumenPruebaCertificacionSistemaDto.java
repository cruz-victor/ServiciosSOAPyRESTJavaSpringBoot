package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ResumenPruebaCertificacionSistemaDto extends ListaMensajesAplicacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long pruebaCertificacionSistemaId;
	private Integer pruebaEtapaCertificacionId;
	private Long sistemaId;
	private Date fechaInicioPrueba;
	private Date fechaFinPrueba;
	private boolean etapaCompletada;
	private Integer etapaCertificacionSistemasId;

	public Long getPruebaCertificacionSistemaId() {
		return pruebaCertificacionSistemaId;
	}

	public void setPruebaCertificacionSistemaId(Long pruebaCertificacionSistemaId) {
		this.pruebaCertificacionSistemaId = pruebaCertificacionSistemaId;
	}

	public Integer getPruebaEtapaCertificacionId() {
		return pruebaEtapaCertificacionId;
	}

	public void setPruebaEtapaCertificacionId(Integer pruebaEtapaCertificacionId) {
		this.pruebaEtapaCertificacionId = pruebaEtapaCertificacionId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Date getFechaInicioPrueba() {
		return fechaInicioPrueba;
	}

	public void setFechaInicioPrueba(Date fechaInicioPrueba) {
		this.fechaInicioPrueba = fechaInicioPrueba;
	}

	public Date getFechaFinPrueba() {
		return fechaFinPrueba;
	}

	public void setFechaFinPrueba(Date fechaFinPrueba) {
		this.fechaFinPrueba = fechaFinPrueba;
	}

	public Integer getEtapaCertificacionSistemasId() {
		return etapaCertificacionSistemasId;
	}

	public void setEtapaCertificacionSistemasId(Integer etapaCertificacionSistemasId) {
		this.etapaCertificacionSistemasId = etapaCertificacionSistemasId;
	}

	public boolean isEtapaCompletada() {
		return etapaCompletada;
	}

	public void setEtapaCompletada(boolean etapaCompletada) {
		this.etapaCompletada = etapaCompletada;
	}

}
