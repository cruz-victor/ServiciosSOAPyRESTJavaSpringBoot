package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Objetivo: Entidad para el detalle de la factura de ventas para clínicas/hospitales.
 * Creado por: Peter Flores.
 * Fecha: 24/12/2018
 */
@Entity
@Table(name="sre_fac_detalles_ventas_hospitales", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreFacDetallesVentasHospitales.findAll", query="SELECT s FROM SreFacDetallesVentasHospitales s")
public class SreFacDetallesVentasHospitales implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="detalle_venta_hospital_id")
	private Long detalleVentaHospitalId;

	@Column(name="actividad_economica_id")
	private Long actividadEconomicaId;

	private BigDecimal cantidad;

	private String descripcion;

	private String espcecialidad;

	@Column(name="especialidad_detalle")
	private String especialidadDetalle;

	@Column(name="especialidad_medico")
	private String especialidadMedico;

	@Column(name="estado_id")
	private String estadoId;

	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name="monto_descuento")
	private BigDecimal montoDescuento;

	@Column(name="nit_documento_medico")
	private Long nitDocumentoMedico;

	@Column(name="nombre_apellido_medico")
	private String nombreApellidoMedico;

	@Column(name="nro_factura_medico")
	private Long nroFacturaMedico;

	@Column(name="nro_matricula_medico")
	private String nroMatriculaMedico;

	@Column(name="nro_quirofano_sala_operaciones")
	private Integer nroQuirofanoSalaOperaciones;

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

	//bi-directional many-to-one association to SreFacVentasHospitales
	@ManyToOne
	@JoinColumn(name="venta_hospital_id")
	private SreFacVentasHospitales sreFacVentasHospitales;

	public SreFacDetallesVentasHospitales() {
	}

	public Long getDetalleVentaHospitalId() {
		return this.detalleVentaHospitalId;
	}

	public void setDetalleVentaHospitalId(Long detalleVentaHospitalId) {
		this.detalleVentaHospitalId = detalleVentaHospitalId;
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

	public String getEspcecialidad() {
		return this.espcecialidad;
	}

	public void setEspcecialidad(String espcecialidad) {
		this.espcecialidad = espcecialidad;
	}

	public String getEspecialidadDetalle() {
		return this.especialidadDetalle;
	}

	public void setEspecialidadDetalle(String especialidadDetalle) {
		this.especialidadDetalle = especialidadDetalle;
	}

	public String getEspecialidadMedico() {
		return this.especialidadMedico;
	}

	public void setEspecialidadMedico(String especialidadMedico) {
		this.especialidadMedico = especialidadMedico;
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

	public Long getNitDocumentoMedico() {
		return this.nitDocumentoMedico;
	}

	public void setNitDocumentoMedico(Long nitDocumentoMedico) {
		this.nitDocumentoMedico = nitDocumentoMedico;
	}

	public String getNombreApellidoMedico() {
		return this.nombreApellidoMedico;
	}

	public void setNombreApellidoMedico(String nombreApellidoMedico) {
		this.nombreApellidoMedico = nombreApellidoMedico;
	}

	public Long getNroFacturaMedico() {
		return this.nroFacturaMedico;
	}

	public void setNroFacturaMedico(Long nroFacturaMedico) {
		this.nroFacturaMedico = nroFacturaMedico;
	}

	public String getNroMatriculaMedico() {
		return this.nroMatriculaMedico;
	}

	public void setNroMatriculaMedico(String nroMatriculaMedico) {
		this.nroMatriculaMedico = nroMatriculaMedico;
	}

	public Integer getNroQuirofanoSalaOperaciones() {
		return this.nroQuirofanoSalaOperaciones;
	}

	public void setNroQuirofanoSalaOperaciones(Integer nroQuirofanoSalaOperaciones) {
		this.nroQuirofanoSalaOperaciones = nroQuirofanoSalaOperaciones;
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

	public SreFacVentasHospitales getSreFacVentasHospitales() {
		return this.sreFacVentasHospitales;
	}

	public void setSreFacVentasHospitales(SreFacVentasHospitales sreFacVentasHospitales) {
		this.sreFacVentasHospitales = sreFacVentasHospitales;
	}

}