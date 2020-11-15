package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Objetivo: Entidad para la factura de ventas para Servicio Básicos.
 * Creado por: Peter Flores.
 * Fecha: 26/12/2018
 */
@Entity
@Table(name="sre_fac_ventas_servicios", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreFacVentasServicios.findAll", query="SELECT s FROM SreFacVentasServicios s")
public class SreFacVentasServicios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="venta_servicio_id")
	private Long ventaServicioId;

	private String ciudad;

	@Column(name="codigo_cliente")
	private String codigoCliente;

	private String complemento;

	@Column(name="consumo_kwh")
	private BigDecimal consumoKwh;

	@Column(name="consumo_metros_cubicos")
	private BigDecimal consumoMetrosCubicos;

	private String cuf;

	private String cufd;

	private String cuis;

	private String direccion;

	@Column(name="domicilio_comprador")
	private String domicilioComprador;

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

	private Integer gestion;

	private String leyenda;

	private String mes;

	@Column(name="metodo_pago_id")
	private Integer metodoPagoId;

	@Column(name="monto_descuento")
	private BigDecimal montoDescuento;

	@Column(name="monto_descuento_ley1886")
	private BigDecimal montoDescuentoLey1886;

	@Column(name="monto_total")
	private BigDecimal montoTotal;

	@Column(name="monto_total_moneda")
	private BigDecimal montoTotalMoneda;

	@Column(name="monto_total_sujeto_iva")
	private BigDecimal montoTotalSujetoIva;

	@Column(name="nit_emisor")
	private Long nitEmisor;

	@Column(name="nombre_razon_social")
	private String nombreRazonSocial;

	@Column(name="numero_documento")
	private String numeroDocumento;

	@Column(name="numero_factura")
	private Long numeroFactura;

	@Column(name="numero_medidor")
	private String numeroMedidor;

	@Column(name="numero_tarjeta")
	private Long numeroTarjeta;

	@Column(name="persona_contribuyente_id")
	private Long personaContribuyenteId;

	@Column(name="punto_venta_id")
	private Long puntoVentaId;

	@Column(name="recepcion_correcta_servicio_id")
	private Long recepcionCorrectaServicioId;

	@Column(name="sucursal_id")
	private Integer sucursalId;

	@Column(name="tasa_alumbrado")
	private BigDecimal tasaAlumbrado;

	@Column(name="tasa_aseo")
	private BigDecimal tasaAseo;

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

	private String zona;

	public SreFacVentasServicios() {
	}

	public Long getVentaServicioId() {
		return this.ventaServicioId;
	}

	public void setVentaServicioId(Long ventaServicioId) {
		this.ventaServicioId = ventaServicioId;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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

	public BigDecimal getConsumoKwh() {
		return this.consumoKwh;
	}

	public void setConsumoKwh(BigDecimal consumoKwh) {
		this.consumoKwh = consumoKwh;
	}

	public BigDecimal getConsumoMetrosCubicos() {
		return this.consumoMetrosCubicos;
	}

	public void setConsumoMetrosCubicos(BigDecimal consumoMetrosCubicos) {
		this.consumoMetrosCubicos = consumoMetrosCubicos;
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

	public String getDomicilioComprador() {
		return this.domicilioComprador;
	}

	public void setDomicilioComprador(String domicilioComprador) {
		this.domicilioComprador = domicilioComprador;
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

	public Integer getGestion() {
		return this.gestion;
	}

	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}

	public String getLeyenda() {
		return this.leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
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

	public BigDecimal getMontoDescuentoLey1886() {
		return this.montoDescuentoLey1886;
	}

	public void setMontoDescuentoLey1886(BigDecimal montoDescuentoLey1886) {
		this.montoDescuentoLey1886 = montoDescuentoLey1886;
	}

	public BigDecimal getMontoTotal() {
		return this.montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getMontoTotalMoneda() {
		return this.montoTotalMoneda;
	}

	public void setMontoTotalMoneda(BigDecimal montoTotalMoneda) {
		this.montoTotalMoneda = montoTotalMoneda;
	}

	public BigDecimal getMontoTotalSujetoIva() {
		return this.montoTotalSujetoIva;
	}

	public void setMontoTotalSujetoIva(BigDecimal montoTotalSujetoIva) {
		this.montoTotalSujetoIva = montoTotalSujetoIva;
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

	public String getNumeroMedidor() {
		return this.numeroMedidor;
	}

	public void setNumeroMedidor(String numeroMedidor) {
		this.numeroMedidor = numeroMedidor;
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

	public Long getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(Long puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public Long getRecepcionCorrectaServicioId() {
		return this.recepcionCorrectaServicioId;
	}

	public void setRecepcionCorrectaServicioId(Long recepcionCorrectaServicioId) {
		this.recepcionCorrectaServicioId = recepcionCorrectaServicioId;
	}

	public Integer getSucursalId() {
		return this.sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}

	public BigDecimal getTasaAlumbrado() {
		return this.tasaAlumbrado;
	}

	public void setTasaAlumbrado(BigDecimal tasaAlumbrado) {
		this.tasaAlumbrado = tasaAlumbrado;
	}

	public BigDecimal getTasaAseo() {
		return this.tasaAseo;
	}

	public void setTasaAseo(BigDecimal tasaAseo) {
		this.tasaAseo = tasaAseo;
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

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}