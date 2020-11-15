package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class RegistroVerificacionInsituDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long registroObservacionInsituId;

	private Long solicitudCertificacionId;
	
	private Integer estadoPruebaId;
	
	private String observacion;	
	
	private Long observacionInsituId;

	private String estadoPruebaDescripcion;
	
	private String descripcionPrueba;
	
	private Long usuarioRegistroId;
	
	private Integer tipoObligatorioId;
	
	private Integer tipoEsquemaId;

	public Long getObservacionInsituId() {
		return observacionInsituId;
	}

	public void setObservacionInsituId(Long observacionInsituId) {
		this.observacionInsituId = observacionInsituId;
	}

	public String getDescripcionPrueba() {
		return descripcionPrueba;
	}

	public void setDescripcionPrueba(String descripcionPrueba) {
		this.descripcionPrueba = descripcionPrueba;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Integer getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(Integer estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstadoPruebaDescripcion() {
		return estadoPruebaDescripcion;
	}

	public void setEstadoPruebaDescripcion(String estadoPruebaDescripcion) {
		this.estadoPruebaDescripcion = estadoPruebaDescripcion;
	}

	public Long getRegistroObservacionInsituId() {
		return registroObservacionInsituId;
	}

	public void setRegistroObservacionInsituId(Long registroObservacionInsituId) {
		this.registroObservacionInsituId = registroObservacionInsituId;
	}

	/**
	 * @return the usuarioRegistroId
	 */
	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	/**
	 * @param usuarioRegistroId the usuarioRegistroId to set
	 */
	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	/**
	 * @return the tipoObligatorioId
	 */
	public Integer getTipoObligatorioId() {
		return tipoObligatorioId;
	}

	/**
	 * @param tipoObligatorioId the tipoObligatorioId to set
	 */
	public void setTipoObligatorioId(Integer tipoObligatorioId) {
		this.tipoObligatorioId = tipoObligatorioId;
	}

	/**
	 * @return the tipoEsquemaId
	 */
	public Integer getTipoEsquemaId() {
		return tipoEsquemaId;
	}

	/**
	 * @param tipoEsquemaId the tipoEsquemaId to set
	 */
	public void setTipoEsquemaId(Integer tipoEsquemaId) {
		this.tipoEsquemaId = tipoEsquemaId;
	}

}
