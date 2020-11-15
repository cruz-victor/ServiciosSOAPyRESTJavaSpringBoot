package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class MensajesCorrespondenciaDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descripcion;
	private String tipo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
