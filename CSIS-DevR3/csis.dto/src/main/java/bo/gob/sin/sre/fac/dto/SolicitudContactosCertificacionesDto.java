package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudContactosCertificacionesDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//IASC
	private String nombre;
	private String complemento;
	private String celular;
	private String correo;
	private Integer tipoDocumentoIdentidad;
	private String numeroDocumento;
	private boolean estaEditado;

	public SolicitudContactosCertificacionesDto(String nombre, String complemento, String celular, String correo,
			Integer tipoDocumentoIdentidad, String numeroDocumento, boolean estaEditado) {
		this.nombre = nombre;
		this.complemento = complemento;
		this.celular = celular;
		this.correo = correo;
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
		this.numeroDocumento = numeroDocumento;
		this.estaEditado = estaEditado;
	}

	public boolean isEstaEditado() {
		return estaEditado;
	}

	public void setEstaEditado(boolean estaEditado) {
		this.estaEditado = estaEditado;
	}

	public SolicitudContactosCertificacionesDto() {

	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}	

	public Integer getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(Integer tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Override
	public String toString() {
		return "SolicitudContactosCertificacionesDto [nombre=" + nombre + ", complemento=" + complemento + ", celular="
				+ celular + ", correo=" + correo + ", tipoDocumentoIdentidad=" + tipoDocumentoIdentidad
				+ ", numeroDocumento=" + numeroDocumento + ", estaEditado=" + estaEditado + "]";
	}

}
