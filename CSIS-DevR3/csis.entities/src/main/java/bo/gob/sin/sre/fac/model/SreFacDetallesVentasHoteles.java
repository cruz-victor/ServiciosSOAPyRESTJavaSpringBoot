package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Objetivo: Entidad para el detalle de la factura de ventas para hoteles.
 * Creado por: Peter Flores.
 * Fecha: 24/12/2018
 */
@Entity
@Table(name="sre_fac_detalles_ventas_hoteles", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreFacDetallesVentasHoteles.findAll", query="SELECT s FROM SreFacDetallesVentasHoteles s")
public class SreFacDetallesVentasHoteles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="detalle_venta_hotel_id")
	private Long detalleVentaHotelId;

	@Column(name="actividad_economica_id")
	private Long actividadEconomicaId;

	@Column(name="cantidad_dias")
	private Integer cantidadDias;

	@Column(name="codigo_tipo_habitacion")
	private Integer codigoTipoHabitacion;

	private String descripcion;

	@Column(name="estado_id")
	private String estadoId;

	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name="monto_descuento")
	private BigDecimal montoDescuento;

	@Column(name="precio_unitario")
	private BigDecimal precioUnitario;

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

	//bi-directional many-to-one association to SreFacVentasHoteles
	@ManyToOne
	@JoinColumn(name="venta_hotel_id")
	private SreFacVentasHoteles sreFacVentasHoteles;

	public SreFacDetallesVentasHoteles() {
	}

	public Long getDetalleVentaHotelId() {
		return this.detalleVentaHotelId;
	}

	public void setDetalleVentaHotelId(Long detalleVentaHotelId) {
		this.detalleVentaHotelId = detalleVentaHotelId;
	}

	public Long getActividadEconomicaId() {
		return this.actividadEconomicaId;
	}

	public void setActividadEconomicaId(Long actividadEconomicaId) {
		this.actividadEconomicaId = actividadEconomicaId;
	}

	public Integer getCantidadDias() {
		return this.cantidadDias;
	}

	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	public Integer getCodigoTipoHabitacion() {
		return this.codigoTipoHabitacion;
	}

	public void setCodigoTipoHabitacion(Integer codigoTipoHabitacion) {
		this.codigoTipoHabitacion = codigoTipoHabitacion;
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

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public BigDecimal getMontoDescuento() {
		return this.montoDescuento;
	}

	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
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

	public SreFacVentasHoteles getSreFacVentasHoteles() {
		return this.sreFacVentasHoteles;
	}

	public void setSreFacVentasHoteles(SreFacVentasHoteles sreFacVentasHoteles) {
		this.sreFacVentasHoteles = sreFacVentasHoteles;
	}

}