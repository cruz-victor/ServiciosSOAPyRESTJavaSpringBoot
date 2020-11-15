package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RespuestaFacturaParcialDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private long ventaId;
	private String fechaEmision;
	private float montoTotal;
	private String usuario;
	private String direccion;
	private int sucursalId;
	private String complemento;
	private Integer puntoVentaId;
	private String numeroDocumento;
	private String nombreRazonSocial;
	private int tipoDocumentoIdentidad;
	private List<RespuestaFacturaDetalleParcialDto> detalle;

	public RespuestaFacturaParcialDto() {
		detalle = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "RespuestaFacturaParcialDto [ventaId=" + ventaId + ", fechaEmision=" + fechaEmision + ", montoTotal="
				+ montoTotal + ", usuario=" + usuario + ", direccion=" + direccion + ", sucursalId=" + sucursalId
				+ ", complemento=" + complemento + ", puntoVentaId=" + puntoVentaId + ", numeroDocumento="
				+ numeroDocumento + ", nombreRazonSocial=" + nombreRazonSocial + ", tipoDocumentoIdentidad="
				+ tipoDocumentoIdentidad + ", detalle=" + detalle + "]";
	}

	/**
	 * @return the ventaId
	 */
	public long getVentaId() {
		return ventaId;
	}

	/**
	 * @param ventaId the ventaId to set
	 */
	public void setVentaId(long ventaId) {
		this.ventaId = ventaId;
	}

	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the montoTotal
	 */
	public float getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @param montoTotal the montoTotal to set
	 */
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	/**
	 * @return the detalle
	 */
	public List<RespuestaFacturaDetalleParcialDto> getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(List<RespuestaFacturaDetalleParcialDto> detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public String getComplemento() {
		return complemento;
	}

	public Integer getPuntoVentaId() {
		return puntoVentaId;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public String getNombreRazonSocial() {
		return nombreRazonSocial;
	}

	public int getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setPuntoVentaId(Integer puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}

	public void setTipoDocumentoIdentidad(int tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

}