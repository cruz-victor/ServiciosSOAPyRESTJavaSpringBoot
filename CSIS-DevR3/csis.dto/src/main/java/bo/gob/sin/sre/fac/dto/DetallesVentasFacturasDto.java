package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Objetivo: Detalle para la consulta de las siguientes facturas:
 * 				Factura Estándar
 *				Factura Sectores Educativos
 *				Factura De Alquiler De Bienes Inmuebles
 *				Factura De Comercialización De Hidrocarburos
 *				Factura De Servicios Básicos
 *				Factura De Embotelladoras
 *				Factura De Entidades Financieras
 *				Factura De Hoteles
 *				Facturas De Hospitales/Clínicas
 *				Factura De Juegos De Azar
 *				Factura De Artistas Internacionales
 *				Factura Comercial De Exportación
 * Creado por: Peter Flores. 
 * Fecha Creación: 20/12/2018
 */
public class DetallesVentasFacturasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long actividadEconomicaId;
	private Long cantidad;
	private String descripcion;
	private String estadoId;
	private String fechaRegistro;
	private String fechaUltimaModificacion;
	private BigDecimal montoDescuento;
	private String numeroSerie;
	private BigDecimal precioUnitario;
	private String productoId;
	private Long productoSinId;
	private BigDecimal subtotal;
	private Long usuarioRegistroId;
	private Long usuarioUltimaModificacionId;	
	private String espcecialidad;
	private String especialidadDetalle;	
	private String especialidadMedico;
	private Long nitDocumentoMedico;
	private String nombreApellidoMedico;
	private Long nroFacturaMedico;
	private String nroMatriculaMedico;
	private Integer nroQuirofanoSalaOperaciones;
	private String unidadMedida;
	private String marcaIce;
	private Integer cantidadDias;
	private Integer codigoTipoHabitacion;

	public Long getActividadEconomicaId() {
		return actividadEconomicaId;
	}

	public void setActividadEconomicaId(Long actividadEconomicaId) {
		this.actividadEconomicaId = actividadEconomicaId;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public BigDecimal getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getProductoId() {
		return productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public Long getProductoSinId() {
		return productoSinId;
	}

	public void setProductoSinId(Long productoSinId) {
		this.productoSinId = productoSinId;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public String getEspcecialidad() {
		return espcecialidad;
	}

	public void setEspcecialidad(String espcecialidad) {
		this.espcecialidad = espcecialidad;
	}

	public String getEspecialidadDetalle() {
		return especialidadDetalle;
	}

	public void setEspecialidadDetalle(String especialidadDetalle) {
		this.especialidadDetalle = especialidadDetalle;
	}

	public String getEspecialidadMedico() {
		return especialidadMedico;
	}

	public void setEspecialidadMedico(String especialidadMedico) {
		this.especialidadMedico = especialidadMedico;
	}

	public Long getNitDocumentoMedico() {
		return nitDocumentoMedico;
	}

	public void setNitDocumentoMedico(Long nitDocumentoMedico) {
		this.nitDocumentoMedico = nitDocumentoMedico;
	}

	public String getNombreApellidoMedico() {
		return nombreApellidoMedico;
	}

	public void setNombreApellidoMedico(String nombreApellidoMedico) {
		this.nombreApellidoMedico = nombreApellidoMedico;
	}

	public Long getNroFacturaMedico() {
		return nroFacturaMedico;
	}

	public void setNroFacturaMedico(Long nroFacturaMedico) {
		this.nroFacturaMedico = nroFacturaMedico;
	}

	public String getNroMatriculaMedico() {
		return nroMatriculaMedico;
	}

	public void setNroMatriculaMedico(String nroMatriculaMedico) {
		this.nroMatriculaMedico = nroMatriculaMedico;
	}

	public Integer getNroQuirofanoSalaOperaciones() {
		return nroQuirofanoSalaOperaciones;
	}

	public void setNroQuirofanoSalaOperaciones(Integer nroQuirofanoSalaOperaciones) {
		this.nroQuirofanoSalaOperaciones = nroQuirofanoSalaOperaciones;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getMarcaIce() {
		return marcaIce;
	}

	public void setMarcaIce(String marcaIce) {
		this.marcaIce = marcaIce;
	}

	public Integer getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	public Integer getCodigoTipoHabitacion() {
		return codigoTipoHabitacion;
	}

	public void setCodigoTipoHabitacion(Integer codigoTipoHabitacion) {
		this.codigoTipoHabitacion = codigoTipoHabitacion;
	}	
}
