package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.persistence.Column;

public class SreFacRegistrosPruebasAutoDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	public SreFacRegistrosPruebasAutoDetalle(long pPruebaAutomaticaId, String pNombre, long pCantidadIntento,
			long pCantidadCorrectos, java.math.BigDecimal pPorcentajeCorrectos, int pEstadoPruebaId, String pDescripcion) {
		this.setPorcentajeCorrectos(pPorcentajeCorrectos);
		this.setPruebaAutomaticaId(pPruebaAutomaticaId);
		this.setCantidadCorrectos(pCantidadCorrectos);
		this.setCantidadIntento(pCantidadIntento);
		this.setDescripcion(pDescripcion);
		this.setNombre(pNombre);
		this.setEstadoPruebaId(pEstadoPruebaId);
	}

	public SreFacRegistrosPruebasAutoDetalle() {
	};

	@Column(name = "prueba_automatica_id")
	private Long pruebaAutomaticaId;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "cantidad_intento")
	private Long cantidadIntento;

	@Column(name = "cantidad_correctos")
	private Long cantidadCorrectos;

	@Column(name = "porcentajeCorrectos")
	private java.math.BigDecimal porcentajeCorrectos;

	@Column(name = "estado_prueba_id")
	private int estadoPruebaId;

	@Column(name = "descripcion")
	private String descripcion;

	public Long getPruebaAutomaticaId() {
		return pruebaAutomaticaId;
	}

	public void setPruebaAutomaticaId(Long pruebaAutomaticaId) {
		this.pruebaAutomaticaId = pruebaAutomaticaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCantidadIntento() {
		return cantidadIntento;
	}

	public void setCantidadIntento(Long cantidadIntento) {
		this.cantidadIntento = cantidadIntento;
	}

	public Long getCantidadCorrectos() {
		return cantidadCorrectos;
	}

	public void setCantidadCorrectos(Long cantidadCorrectos) {
		this.cantidadCorrectos = cantidadCorrectos;
	}


	public java.math.BigDecimal getPorcentajeCorrectos() {
		return porcentajeCorrectos;
	}

	public void setPorcentajeCorrectos(java.math.BigDecimal porcentajeCorrectos) {
		this.porcentajeCorrectos = porcentajeCorrectos;
	}

	public int getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(int estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
