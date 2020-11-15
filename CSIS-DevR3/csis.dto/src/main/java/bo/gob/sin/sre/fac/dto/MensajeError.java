package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class MensajeError implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String remitente;
    private String descripcion;
	
	public MensajeError() {
		
	}

	public MensajeError(String remitente, String descripcion) {
		super();
		this.remitente = remitente;
		this.descripcion = descripcion;
	}
	
	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
