package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;

/**
 * @author Reynaldo Chambi
 * @fecha 09/08/2019
 */
public class DetalleFacturaVentasDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long actividadEconomica;
	private Integer codigoProductoSin;
	private Float cantidad;
	private Integer unidadMedida;
	private Float precioUnitario;
	private Float subTotal;
	
	public DetalleFacturaVentasDto() {
		
	}
	
	public DetalleFacturaVentasDto(Long actividadEconomica, Integer codigoProductoSin, Float cantidad,
			Integer unidadMedida, Float precioUnitario, Float subTotal) {
		super();
		this.actividadEconomica = actividadEconomica;
		this.codigoProductoSin = codigoProductoSin;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.precioUnitario = precioUnitario;
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "DetalleFacturaVentasDto [actividadEconomica=" + actividadEconomica + ", codigoProductoSin="
				+ codigoProductoSin + ", cantidad=" + cantidad + ", unidadMedida=" + unidadMedida + ", precioUnitario="
				+ precioUnitario + ", subTotal=" + subTotal + "]";
	}

	public Long getActividadEconomica() {
		return actividadEconomica;
	}

	public Integer getCodigoProductoSin() {
		return codigoProductoSin;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public Integer getUnidadMedida() {
		return unidadMedida;
	}

	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setActividadEconomica(Long actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public void setCodigoProductoSin(Integer codigoProductoSin) {
		this.codigoProductoSin = codigoProductoSin;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public void setUnidadMedida(Integer unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}
}
