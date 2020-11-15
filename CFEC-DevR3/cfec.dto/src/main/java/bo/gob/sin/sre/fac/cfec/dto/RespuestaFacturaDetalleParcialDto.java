package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;

public class RespuestaFacturaDetalleParcialDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private long detalleVentaId;
	private long actividadEconomicaId;
	private long productoSinId;
	private String productoId;
	private String descripcion;
	private float cantidad;
	private String unidadMedida;
	private float precioUnitario;
	private float subTotal;

	public RespuestaFacturaDetalleParcialDto() {
	}

	@Override
	public String toString() {
		return "RespuestaFacturaDetalleParcialDto [detalleVentaId=" + detalleVentaId + ", actividadEconomicaId="
				+ actividadEconomicaId + ", productoSinId=" + productoSinId + ", productoId=" + productoId
				+ ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", unidadMedida=" + unidadMedida
				+ ", precioUnitario=" + precioUnitario + ", subTotal=" + subTotal + "]";
	}

	/**
	 * @return the detalleVentaId
	 */
	public long getDetalleVentaId() {
		return detalleVentaId;
	}

	/**
	 * @param detalleVentaId the detalleVentaId to set
	 */
	public void setDetalleVentaId(long detalleVentaId) {
		this.detalleVentaId = detalleVentaId;
	}

	/**
	 * @return the actividadEconomicaId
	 */
	public long getActividadEconomicaId() {
		return actividadEconomicaId;
	}

	/**
	 * @param actividadEconomicaId the actividadEconomicaId to set
	 */
	public void setActividadEconomicaId(long actividadEconomicaId) {
		this.actividadEconomicaId = actividadEconomicaId;
	}

	/**
	 * @return the productoSinId
	 */
	public long getProductoSinId() {
		return productoSinId;
	}

	/**
	 * @param productoSinId the productoSinId to set
	 */
	public void setProductoSinId(long productoSinId) {
		this.productoSinId = productoSinId;
	}

	/**
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}

	/**
	 * @param productoId the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the cantidad
	 */
	public float getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * @return the precioUnitario
	 */
	public float getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the subTotal
	 */
	public float getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

}