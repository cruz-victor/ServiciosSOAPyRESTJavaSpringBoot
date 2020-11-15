package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class SolicitudGenerarCiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long codAdministracion;
	private long caso;
	private long tipDocumento;
	private Date fechaDocumento;
	private String referencia;
	private int fojas;
	private long documentoIdentidad;
	private long codigoActoAdministrativo;

	public SolicitudGenerarCiteDto() {

	}

	public SolicitudGenerarCiteDto(long codAdministracion, long caso, long tipDocumento, Date fechaDocumento,
			String referencia, int fojas, long documentoIdentidad, long codigoActoAdministrativo) {
		super();
		this.codAdministracion = codAdministracion;
		this.caso = caso;
		this.tipDocumento = tipDocumento;
		this.fechaDocumento = fechaDocumento;
		this.referencia = referencia;
		this.fojas = fojas;
		this.documentoIdentidad = documentoIdentidad;
		this.codigoActoAdministrativo = codigoActoAdministrativo;
	}

	public long getCodAdministracion() {
		return codAdministracion;
	}

	public void setCodAdministracion(long codAdministracion) {
		this.codAdministracion = codAdministracion;
	}

	public long getCaso() {
		return caso;
	}

	public void setCaso(long caso) {
		this.caso = caso;
	}

	public long getTipDocumento() {
		return tipDocumento;
	}

	public void setTipDocumento(long tipDocumento) {
		this.tipDocumento = tipDocumento;
	}

	public Date getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getFojas() {
		return fojas;
	}

	public void setFojas(int fojas) {
		this.fojas = fojas;
	}

	public long getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(long documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public long getCodigoActoAdministrativo() {
		return codigoActoAdministrativo;
	}

	public void setCodigoActoAdministrativo(long codigoActoAdministrativo) {
		this.codigoActoAdministrativo = codigoActoAdministrativo;
	}

	@Override
	public String toString() {
		return "SolicitudGenerarCiteDto [codAdministracion=" + codAdministracion + ", caso=" + caso + "]";
	}
}

