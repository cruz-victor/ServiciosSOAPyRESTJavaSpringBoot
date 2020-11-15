package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.persistence.Column;

public class SreFacRegistrosPruebasManuDetalle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public SreFacRegistrosPruebasManuDetalle(long pPruebaManualId, String pNombre,  String pDescripcion, String pObservaciones, int pEstadoPruebaId, long registroPruebaManualId) {
		super();
		this.setPruebaManualId(pPruebaManualId);
		this.setNombre(pNombre);
		this.setDescripcion(pDescripcion);
		this.setpEstadoPruebaId(pEstadoPruebaId);
		this.setRegistroPruebaManualId(registroPruebaManualId);
		
		this.setObservaciones(pObservaciones);
	}

	public SreFacRegistrosPruebasManuDetalle() {
	};
	
	
	private Long pruebaManualId;
	private int pEstadoPruebaId;
	
	private String nombre;
	private String descripcion;
	private long registroPruebaManualId;
	
	private String observaciones;

	public Long getPruebaManualId() {
		return pruebaManualId;
	}

	public void setPruebaManualId(Long pruebaManualId) {
		this.pruebaManualId = pruebaManualId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getpEstadoPruebaId() {
		return pEstadoPruebaId;
	}

	public void setpEstadoPruebaId(int pEstadoPruebaId) {
		this.pEstadoPruebaId = pEstadoPruebaId;
	}

	public long getRegistroPruebaManualId() {
		return registroPruebaManualId;
	}

	public void setRegistroPruebaManualId(long registroPruebaManualId) {
		this.registroPruebaManualId = registroPruebaManualId;
	}

	
	
	
	
}
