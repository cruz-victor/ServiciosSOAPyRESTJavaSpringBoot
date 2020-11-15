package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the sre_fac_detalles_ventas_colegios database table.
 * 
 */
@Entity
@Table(name="sre_fac_detalles_ventas_colegios", schema = "SRE_RECAUDACIONES")
@NamedQuery(name="SreFacDetallesVentasColegio.findAll", query="SELECT s FROM SreFacDetallesVentasColegio s")
public class SreFacDetallesVentasColegio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="detalle_venta_colegio_id")
	private Long detalleVentaColegioId;

	@Column(name="actividad_economica_id")
	private Long actividadEconomicaId;

	private BigDecimal cantidad;

	private String descripcion;

	@Column(name="estado_id")
	private String estadoId;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Timestamp fechaUltimaModificacion;

	@Column(name="monto_descuento")
	private BigDecimal montoDescuento;

	@Column(name="producto_id")
	private String productoId;

	@Column(name="producto_sin_id")
	private Long productoSinId;

	private BigDecimal subtotal;

	@Column(name="unidad_medida")
	private String unidadMedida;

	@Column(name="usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name="usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;

	@Column(name="venta_colegio_id")
	private Long ventaColegioId;

	public SreFacDetallesVentasColegio() {
	}

	public Long getDetalleVentaColegioId() {
		return this.detalleVentaColegioId;
	}

	public void setDetalleVentaColegioId(Long detalleVentaColegioId) {
		this.detalleVentaColegioId = detalleVentaColegioId;
	}

	public Long getActividadEconomicaId() {
		return this.actividadEconomicaId;
	}

	public void setActividadEconomicaId(Long actividadEconomicaId) {
		this.actividadEconomicaId = actividadEconomicaId;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Timestamp getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Timestamp fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public BigDecimal getMontoDescuento() {
		return this.montoDescuento;
	}

	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getProductoId() {
		return this.productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public Long getProductoSinId() {
		return this.productoSinId;
	}

	public void setProductoSinId(Long productoSinId) {
		this.productoSinId = productoSinId;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Long getUsuarioRegistroId() {
		return this.usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return this.usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public Long getVentaColegioId() {
		return this.ventaColegioId;
	}

	public void setVentaColegioId(Long ventaColegioId) {
		this.ventaColegioId = ventaColegioId;
	}

}