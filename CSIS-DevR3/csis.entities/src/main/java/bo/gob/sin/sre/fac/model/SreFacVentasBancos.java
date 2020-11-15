package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Objetivo: Entidad para la factura de ventas para Entidades Financieras.
 * Creado por: Peter Flores.
 * Fecha: 26/12/2018
 */
@Entity
@Table(name="sre_fac_ventas_bancos", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreFacVentasBancos.findAll", query="SELECT s FROM SreFacVentasBancos s")
public class SreFacVentasBancos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="venta_banco_id")
	private Long ventaBancoId;

	@Column(name="codigo_cliente")
	private String codigoCliente;

	private String complemento;

	private String cuf;

	private String cufd;

	private String cuis;

	private String direccion;

	@Column(name="estado_factura_id")
	private Integer estadoFacturaId;

	@Column(name="estado_id")
	private String estadoId;

	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	private String leyenda;

	@Column(name="metodo_pago_id")
	private Integer metodoPagoId;

	@Column(name="monto_descuento")
	private BigDecimal montoDescuento;

	@Column(name="monto_total")
	private BigDecimal montoTotal;

	@Column(name="monto_total_arrendamiento")
	private BigDecimal montoTotalArrendamiento;

	@Column(name="monto_total_moneda")
	private BigDecimal montoTotalMoneda;

	@Column(name="nit_emisor")
	private Long nitEmisor;

	@Column(name="nombre_razon_social")
	private String nombreRazonSocial;

	@Column(name="numero_documento")
	private String numeroDocumento;

	@Column(name="numero_factura")
	private Long numeroFactura;

	@Column(name="numero_tarjeta")
	private Long numeroTarjeta;

	@Column(name="persona_contribuyente_id")
	private Long personaContribuyenteId;

	@Column(name="punto_venta_id")
	private Integer puntoVentaId;

	@Column(name="recepcion_correcta_banco_id")
	private Long recepcionCorrectaBancoId;

	@Column(name="sucursal_id")
	private Integer sucursalId;

	@Column(name="tipo_ambiente_id")
	private Integer tipoAmbienteId;

	@Column(name="tipo_cambio")
	private BigDecimal tipoCambio;

	@Column(name="tipo_departamento_id")
	private Integer tipoDepartamentoId;

	@Column(name="tipo_documento_fiscal_id")
	private Integer tipoDocumentoFiscalId;

	@Column(name="tipo_documento_identidad_id")
	private Integer tipoDocumentoIdentidadId;

	@Column(name="tipo_documento_sector_id")
	private Integer tipoDocumentoSectorId;

	@Column(name="tipo_emision_id")
	private Integer tipoEmisionId;

	@Column(name="tipo_modalidad_id")
	private Integer tipoModalidadId;

	@Column(name="tipo_moneda_id")
	private Integer tipoMonedaId;

	private String usuario;

	@Column(name="usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name="usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;

	public SreFacVentasBancos() {
	}

	public Long getVentaBancoId() {
		return this.ventaBancoId;
	}

	public void setVentaBancoId(Long ventaBancoId) {
		this.ventaBancoId = ventaBancoId;
	}

	public String getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCuf() {
		return this.cuf;
	}

	public void setCuf(String cuf) {
		this.cuf = cuf;
	}

	public String getCufd() {
		return this.cufd;
	}

	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	public String getCuis() {
		return this.cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEstadoFacturaId() {
		return this.estadoFacturaId;
	}

	public void setEstadoFacturaId(Integer estadoFacturaId) {
		this.estadoFacturaId = estadoFacturaId;
	}

	public String getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
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

	public String getLeyenda() {
		return this.leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public Integer getMetodoPagoId() {
		return this.metodoPagoId;
	}

	public void setMetodoPagoId(Integer metodoPagoId) {
		this.metodoPagoId = metodoPagoId;
	}

	public BigDecimal getMontoDescuento() {
		return this.montoDescuento;
	}

	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public BigDecimal getMontoTotal() {
		return this.montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getMontoTotalArrendamiento() {
		return this.montoTotalArrendamiento;
	}

	public void setMontoTotalArrendamiento(BigDecimal montoTotalArrendamiento) {
		this.montoTotalArrendamiento = montoTotalArrendamiento;
	}

	public BigDecimal getMontoTotalMoneda() {
		return this.montoTotalMoneda;
	}

	public void setMontoTotalMoneda(BigDecimal montoTotalMoneda) {
		this.montoTotalMoneda = montoTotalMoneda;
	}

	public Long getNitEmisor() {
		return this.nitEmisor;
	}

	public void setNitEmisor(Long nitEmisor) {
		this.nitEmisor = nitEmisor;
	}

	public String getNombreRazonSocial() {
		return this.nombreRazonSocial;
	}

	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Long getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Long getNumeroTarjeta() {
		return this.numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Long getPersonaContribuyenteId() {
		return this.personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(Long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public Integer getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(Integer puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public Long getRecepcionCorrectaBancoId() {
		return this.recepcionCorrectaBancoId;
	}

	public void setRecepcionCorrectaBancoId(Long recepcionCorrectaBancoId) {
		this.recepcionCorrectaBancoId = recepcionCorrectaBancoId;
	}

	public Integer getSucursalId() {
		return this.sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}

	public Integer getTipoAmbienteId() {
		return this.tipoAmbienteId;
	}

	public void setTipoAmbienteId(Integer tipoAmbienteId) {
		this.tipoAmbienteId = tipoAmbienteId;
	}

	public BigDecimal getTipoCambio() {
		return this.tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Integer getTipoDepartamentoId() {
		return this.tipoDepartamentoId;
	}

	public void setTipoDepartamentoId(Integer tipoDepartamentoId) {
		this.tipoDepartamentoId = tipoDepartamentoId;
	}

	public Integer getTipoDocumentoFiscalId() {
		return this.tipoDocumentoFiscalId;
	}

	public void setTipoDocumentoFiscalId(Integer tipoDocumentoFiscalId) {
		this.tipoDocumentoFiscalId = tipoDocumentoFiscalId;
	}

	public Integer getTipoDocumentoIdentidadId() {
		return this.tipoDocumentoIdentidadId;
	}

	public void setTipoDocumentoIdentidadId(Integer tipoDocumentoIdentidadId) {
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}

	public Integer getTipoDocumentoSectorId() {
		return this.tipoDocumentoSectorId;
	}

	public void setTipoDocumentoSectorId(Integer tipoDocumentoSectorId) {
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}

	public Integer getTipoEmisionId() {
		return this.tipoEmisionId;
	}

	public void setTipoEmisionId(Integer tipoEmisionId) {
		this.tipoEmisionId = tipoEmisionId;
	}

	public Integer getTipoModalidadId() {
		return this.tipoModalidadId;
	}

	public void setTipoModalidadId(Integer tipoModalidadId) {
		this.tipoModalidadId = tipoModalidadId;
	}

	public Integer getTipoMonedaId() {
		return this.tipoMonedaId;
	}

	public void setTipoMonedaId(Integer tipoMonedaId) {
		this.tipoMonedaId = tipoMonedaId;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
}