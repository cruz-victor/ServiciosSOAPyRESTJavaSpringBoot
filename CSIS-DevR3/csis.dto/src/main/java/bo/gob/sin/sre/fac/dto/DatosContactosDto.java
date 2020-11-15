package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class DatosContactosDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String nombreCompleto;
	private String celular;
	private String correoElectronico;
	private String numeroDocumento;
	private String complemento;
	private String tipoDocumentoIdentidad;
	private Integer tipoDocumentoIdentidadId;
	
	public DatosContactosDto(String nombreCompleto, String celular, String correoElectronico, String numeroDocumento,
			String complemento, String tipoDocumentoIdentidad, Integer tipoDocumentoIdentidadId) {
		this.nombreCompleto = nombreCompleto;
		this.celular = celular;
		this.correoElectronico = correoElectronico;
		this.numeroDocumento = numeroDocumento;
		this.complemento = complemento;
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}

	public DatosContactosDto() {
		
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}
	
	public Integer getTipoDocumentoIdentidadId() {
		return tipoDocumentoIdentidadId;
	}

	public void setTipoDocumentoIdentidadId(Integer tipoDocumentoIdentidadId) {
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}

	@Override
	public String toString() {
		return "DatosContactosDto [nombreCompleto=" + nombreCompleto + ", celular=" + celular + ", correoElectronico="
				+ correoElectronico + ", numeroDocumento=" + numeroDocumento + ", complemento=" + complemento
				+ ", tipoDocumentoIdentidad=" + tipoDocumentoIdentidad + ", tipoDocumentoIdentidadId="
				+ tipoDocumentoIdentidadId + "]";
	}

}
