package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VentasFacturasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long ventaAlquilerId;
	private String codigoCliente;
	private String complemento;
	private String cuf;
	private String cufd;
	private String cuis;
	private String direccion;
	private Integer estadoFacturaId;
	private String estadoId;
	private Date fechaEmision;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String leyenda;
	private Integer metodoPagoId;
	private BigDecimal montoDescuento;
	private BigDecimal montoTotal;
	private BigDecimal montoTotalMoneda;
	private Long nitEmisor;
	private String nombreRazonSocial;
	private String numeroDocumento;
	private Long numeroFactura;
	private Long numeroTarjeta;
	private String periodoFacturado;
	private Long personaContribuyenteId;
	private Integer puntoVentaId;
	private Long recepcionCorrectaAlquilerId;
	private Integer sucursalId;
	private Integer tipoAmbienteId;
	private BigDecimal tipoCambio;
	private Integer tipoDepartamentoId;
	private Integer tipoDocumentoFiscalId;
	private Integer tipoDocumentoIdentidadId;
	private Integer tipoDocumentoSectorId;
	private Integer tipoEmisionId;
	private Integer tipoModalidadId;
	private Integer tipoMonedaId;
	private BigDecimal montoTotalArrendamiento;
	private Long recepcionCorrectaBancoId;
	private Long ventaBancoId;
	private Long ventaColegioId;
	private String nombreEstudiante;
	private Long ventaEmbotelladoraId;
	private BigDecimal montoIce;
	private Long ventaEspectaculoId;
	private String artistaEvento;
	private String direccionSalon;
	private Date fechaEvento;
	private String lugarEvento;
	private Long nitSalon;
	private Long recepcionCorrectaEspectaculoId;
	private String tipoEvento;
	private Long ventaExportacionId;
	private BigDecimal gastosSeguroFrontera;
	private BigDecimal gastosTransporteFrontera;
	private String incoterm;
	private String lugarDestino;
	private BigDecimal montoSeguroInternacional;
	private BigDecimal montoTotalPuerto;
	private BigDecimal montoTransporteFrontera;
	private BigDecimal otrosMontos;
	private BigDecimal previoValorBruto;
	private String puertoDestino;
	private Long recepcionCorrectaExportacionId;
	private Integer tipoPaisId;
	private BigDecimal totalFobFrontera;
	private Long ventaHidrocarburoId;
	private String ciudad;
	private String condicionPago;
	private BigDecimal montoIehd;
	private String nombrePropietario;
	private String nombreRepresentanteLegal;
	private String periodoEntrega;
	private Long ventaHospitalId;
	private String modalidadServicio;
	private Long recepcionCorrectaHospitalId;
	private Long recepcionCorrectaHidrocarburoId;
	private Long recepcionCorrectaEmbotelladoraId;
	private Long recepcionCorrectaColegioId;
	private Long ventaHotelId;
	private Integer cantidadHabitaciones;
	private Integer cantidadHuespedes;
	private Integer cantidadMayores;
	private Integer cantidadMenores;
	private Date fechaIngresoHospedaje;
	private Long recepcionCorrectaHotelId;
	private Long ventaJuegoId;
	private BigDecimal montoTotalIj;
	private BigDecimal montoTotalSujetoIpj;
	private Long recepcionCorrectaJuegoId;
	private Long ventaServicioId;
	private BigDecimal consumoKwh;
	private BigDecimal consumoMetrosCubicos;
	private String domicilioComprador;
	private Integer gestion;
	private String mes;
	private BigDecimal montoDescuentoLey1886;	
	private BigDecimal montoTotalSujetoIva;
	private String numeroMedidor;
	private Long recepcionCorrectaServicioId;
	private BigDecimal tasaAlumbrado;
	private BigDecimal tasaAseo;
	private String zona;
	private String usuario;
	private Long usuarioRegistroId;
	private Long usuarioUltimaModificacionId;
	public Long getVentaAlquilerId() {
		return ventaAlquilerId;
	}
	public void setVentaAlquilerId(Long ventaAlquilerId) {
		this.ventaAlquilerId = ventaAlquilerId;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCuf() {
		return cuf;
	}
	public void setCuf(String cuf) {
		this.cuf = cuf;
	}
	public String getCufd() {
		return cufd;
	}
	public void setCufd(String cufd) {
		this.cufd = cufd;
	}
	public String getCuis() {
		return cuis;
	}
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getEstadoFacturaId() {
		return estadoFacturaId;
	}
	public void setEstadoFacturaId(Integer estadoFacturaId) {
		this.estadoFacturaId = estadoFacturaId;
	}
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	public Integer getMetodoPagoId() {
		return metodoPagoId;
	}
	public void setMetodoPagoId(Integer metodoPagoId) {
		this.metodoPagoId = metodoPagoId;
	}
	public BigDecimal getMontoDescuento() {
		return montoDescuento;
	}
	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public BigDecimal getMontoTotalMoneda() {
		return montoTotalMoneda;
	}
	public void setMontoTotalMoneda(BigDecimal montoTotalMoneda) {
		this.montoTotalMoneda = montoTotalMoneda;
	}
	public Long getNitEmisor() {
		return nitEmisor;
	}
	public void setNitEmisor(Long nitEmisor) {
		this.nitEmisor = nitEmisor;
	}
	public String getNombreRazonSocial() {
		return nombreRazonSocial;
	}
	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Long getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getPeriodoFacturado() {
		return periodoFacturado;
	}
	public void setPeriodoFacturado(String periodoFacturado) {
		this.periodoFacturado = periodoFacturado;
	}
	public Long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}
	public void setPersonaContribuyenteId(Long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}
	public Integer getPuntoVentaId() {
		return puntoVentaId;
	}
	public void setPuntoVentaId(Integer puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}
	public Long getRecepcionCorrectaAlquilerId() {
		return recepcionCorrectaAlquilerId;
	}
	public void setRecepcionCorrectaAlquilerId(Long recepcionCorrectaAlquilerId) {
		this.recepcionCorrectaAlquilerId = recepcionCorrectaAlquilerId;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	public Integer getTipoAmbienteId() {
		return tipoAmbienteId;
	}
	public void setTipoAmbienteId(Integer tipoAmbienteId) {
		this.tipoAmbienteId = tipoAmbienteId;
	}
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public Integer getTipoDepartamentoId() {
		return tipoDepartamentoId;
	}
	public void setTipoDepartamentoId(Integer tipoDepartamentoId) {
		this.tipoDepartamentoId = tipoDepartamentoId;
	}
	public Integer getTipoDocumentoFiscalId() {
		return tipoDocumentoFiscalId;
	}
	public void setTipoDocumentoFiscalId(Integer tipoDocumentoFiscalId) {
		this.tipoDocumentoFiscalId = tipoDocumentoFiscalId;
	}
	public Integer getTipoDocumentoIdentidadId() {
		return tipoDocumentoIdentidadId;
	}
	public void setTipoDocumentoIdentidadId(Integer tipoDocumentoIdentidadId) {
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}
	public Integer getTipoDocumentoSectorId() {
		return tipoDocumentoSectorId;
	}
	public void setTipoDocumentoSectorId(Integer tipoDocumentoSectorId) {
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}
	public Integer getTipoEmisionId() {
		return tipoEmisionId;
	}
	public void setTipoEmisionId(Integer tipoEmisionId) {
		this.tipoEmisionId = tipoEmisionId;
	}
	public Integer getTipoModalidadId() {
		return tipoModalidadId;
	}
	public void setTipoModalidadId(Integer tipoModalidadId) {
		this.tipoModalidadId = tipoModalidadId;
	}
	public Integer getTipoMonedaId() {
		return tipoMonedaId;
	}
	public void setTipoMonedaId(Integer tipoMonedaId) {
		this.tipoMonedaId = tipoMonedaId;
	}
	public BigDecimal getMontoTotalArrendamiento() {
		return montoTotalArrendamiento;
	}
	public void setMontoTotalArrendamiento(BigDecimal montoTotalArrendamiento) {
		this.montoTotalArrendamiento = montoTotalArrendamiento;
	}
	public Long getRecepcionCorrectaBancoId() {
		return recepcionCorrectaBancoId;
	}
	public void setRecepcionCorrectaBancoId(Long recepcionCorrectaBancoId) {
		this.recepcionCorrectaBancoId = recepcionCorrectaBancoId;
	}
	public Long getVentaBancoId() {
		return ventaBancoId;
	}
	public void setVentaBancoId(Long ventaBancoId) {
		this.ventaBancoId = ventaBancoId;
	}
	public Long getVentaColegioId() {
		return ventaColegioId;
	}
	public void setVentaColegioId(Long ventaColegioId) {
		this.ventaColegioId = ventaColegioId;
	}
	public String getNombreEstudiante() {
		return nombreEstudiante;
	}
	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}
	public Long getVentaEmbotelladoraId() {
		return ventaEmbotelladoraId;
	}
	public void setVentaEmbotelladoraId(Long ventaEmbotelladoraId) {
		this.ventaEmbotelladoraId = ventaEmbotelladoraId;
	}
	public BigDecimal getMontoIce() {
		return montoIce;
	}
	public void setMontoIce(BigDecimal montoIce) {
		this.montoIce = montoIce;
	}
	public Long getVentaEspectaculoId() {
		return ventaEspectaculoId;
	}
	public void setVentaEspectaculoId(Long ventaEspectaculoId) {
		this.ventaEspectaculoId = ventaEspectaculoId;
	}
	public String getArtistaEvento() {
		return artistaEvento;
	}
	public void setArtistaEvento(String artistaEvento) {
		this.artistaEvento = artistaEvento;
	}
	public String getDireccionSalon() {
		return direccionSalon;
	}
	public void setDireccionSalon(String direccionSalon) {
		this.direccionSalon = direccionSalon;
	}
	public Date getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	public String getLugarEvento() {
		return lugarEvento;
	}
	public void setLugarEvento(String lugarEvento) {
		this.lugarEvento = lugarEvento;
	}
	public Long getNitSalon() {
		return nitSalon;
	}
	public void setNitSalon(Long nitSalon) {
		this.nitSalon = nitSalon;
	}
	public Long getRecepcionCorrectaEspectaculoId() {
		return recepcionCorrectaEspectaculoId;
	}
	public void setRecepcionCorrectaEspectaculoId(Long recepcionCorrectaEspectaculoId) {
		this.recepcionCorrectaEspectaculoId = recepcionCorrectaEspectaculoId;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public Long getVentaExportacionId() {
		return ventaExportacionId;
	}
	public void setVentaExportacionId(Long ventaExportacionId) {
		this.ventaExportacionId = ventaExportacionId;
	}
	public BigDecimal getGastosSeguroFrontera() {
		return gastosSeguroFrontera;
	}
	public void setGastosSeguroFrontera(BigDecimal gastosSeguroFrontera) {
		this.gastosSeguroFrontera = gastosSeguroFrontera;
	}
	public BigDecimal getGastosTransporteFrontera() {
		return gastosTransporteFrontera;
	}
	public void setGastosTransporteFrontera(BigDecimal gastosTransporteFrontera) {
		this.gastosTransporteFrontera = gastosTransporteFrontera;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public String getLugarDestino() {
		return lugarDestino;
	}
	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino;
	}
	public BigDecimal getMontoSeguroInternacional() {
		return montoSeguroInternacional;
	}
	public void setMontoSeguroInternacional(BigDecimal montoSeguroInternacional) {
		this.montoSeguroInternacional = montoSeguroInternacional;
	}
	public BigDecimal getMontoTotalPuerto() {
		return montoTotalPuerto;
	}
	public void setMontoTotalPuerto(BigDecimal montoTotalPuerto) {
		this.montoTotalPuerto = montoTotalPuerto;
	}
	public BigDecimal getMontoTransporteFrontera() {
		return montoTransporteFrontera;
	}
	public void setMontoTransporteFrontera(BigDecimal montoTransporteFrontera) {
		this.montoTransporteFrontera = montoTransporteFrontera;
	}
	public BigDecimal getOtrosMontos() {
		return otrosMontos;
	}
	public void setOtrosMontos(BigDecimal otrosMontos) {
		this.otrosMontos = otrosMontos;
	}
	public BigDecimal getPrevioValorBruto() {
		return previoValorBruto;
	}
	public void setPrevioValorBruto(BigDecimal previoValorBruto) {
		this.previoValorBruto = previoValorBruto;
	}
	public String getPuertoDestino() {
		return puertoDestino;
	}
	public void setPuertoDestino(String puertoDestino) {
		this.puertoDestino = puertoDestino;
	}
	public Long getRecepcionCorrectaExportacionId() {
		return recepcionCorrectaExportacionId;
	}
	public void setRecepcionCorrectaExportacionId(Long recepcionCorrectaExportacionId) {
		this.recepcionCorrectaExportacionId = recepcionCorrectaExportacionId;
	}
	public Integer getTipoPaisId() {
		return tipoPaisId;
	}
	public void setTipoPaisId(Integer tipoPaisId) {
		this.tipoPaisId = tipoPaisId;
	}
	public BigDecimal getTotalFobFrontera() {
		return totalFobFrontera;
	}
	public void setTotalFobFrontera(BigDecimal totalFobFrontera) {
		this.totalFobFrontera = totalFobFrontera;
	}
	public Long getVentaHidrocarburoId() {
		return ventaHidrocarburoId;
	}
	public void setVentaHidrocarburoId(Long ventaHidrocarburoId) {
		this.ventaHidrocarburoId = ventaHidrocarburoId;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCondicionPago() {
		return condicionPago;
	}
	public void setCondicionPago(String condicionPago) {
		this.condicionPago = condicionPago;
	}
	public BigDecimal getMontoIehd() {
		return montoIehd;
	}
	public void setMontoIehd(BigDecimal montoIehd) {
		this.montoIehd = montoIehd;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getNombreRepresentanteLegal() {
		return nombreRepresentanteLegal;
	}
	public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
		this.nombreRepresentanteLegal = nombreRepresentanteLegal;
	}
	public String getPeriodoEntrega() {
		return periodoEntrega;
	}
	public void setPeriodoEntrega(String periodoEntrega) {
		this.periodoEntrega = periodoEntrega;
	}
	public Long getVentaHospitalId() {
		return ventaHospitalId;
	}
	public void setVentaHospitalId(Long ventaHospitalId) {
		this.ventaHospitalId = ventaHospitalId;
	}
	public String getModalidadServicio() {
		return modalidadServicio;
	}
	public void setModalidadServicio(String modalidadServicio) {
		this.modalidadServicio = modalidadServicio;
	}
	public Long getRecepcionCorrectaHospitalId() {
		return recepcionCorrectaHospitalId;
	}
	public void setRecepcionCorrectaHospitalId(Long recepcionCorrectaHospitalId) {
		this.recepcionCorrectaHospitalId = recepcionCorrectaHospitalId;
	}
	public Long getRecepcionCorrectaHidrocarburoId() {
		return recepcionCorrectaHidrocarburoId;
	}
	public void setRecepcionCorrectaHidrocarburoId(Long recepcionCorrectaHidrocarburoId) {
		this.recepcionCorrectaHidrocarburoId = recepcionCorrectaHidrocarburoId;
	}
	public Long getRecepcionCorrectaEmbotelladoraId() {
		return recepcionCorrectaEmbotelladoraId;
	}
	public void setRecepcionCorrectaEmbotelladoraId(Long recepcionCorrectaEmbotelladoraId) {
		this.recepcionCorrectaEmbotelladoraId = recepcionCorrectaEmbotelladoraId;
	}
	public Long getRecepcionCorrectaColegioId() {
		return recepcionCorrectaColegioId;
	}
	public void setRecepcionCorrectaColegioId(Long recepcionCorrectaColegioId) {
		this.recepcionCorrectaColegioId = recepcionCorrectaColegioId;
	}
	public Long getVentaHotelId() {
		return ventaHotelId;
	}
	public void setVentaHotelId(Long ventaHotelId) {
		this.ventaHotelId = ventaHotelId;
	}
	public Integer getCantidadHabitaciones() {
		return cantidadHabitaciones;
	}
	public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
		this.cantidadHabitaciones = cantidadHabitaciones;
	}
	public Integer getCantidadHuespedes() {
		return cantidadHuespedes;
	}
	public void setCantidadHuespedes(Integer cantidadHuespedes) {
		this.cantidadHuespedes = cantidadHuespedes;
	}
	public Integer getCantidadMayores() {
		return cantidadMayores;
	}
	public void setCantidadMayores(Integer cantidadMayores) {
		this.cantidadMayores = cantidadMayores;
	}
	public Integer getCantidadMenores() {
		return cantidadMenores;
	}
	public void setCantidadMenores(Integer cantidadMenores) {
		this.cantidadMenores = cantidadMenores;
	}
	public Date getFechaIngresoHospedaje() {
		return fechaIngresoHospedaje;
	}
	public void setFechaIngresoHospedaje(Date fechaIngresoHospedaje) {
		this.fechaIngresoHospedaje = fechaIngresoHospedaje;
	}
	public Long getRecepcionCorrectaHotelId() {
		return recepcionCorrectaHotelId;
	}
	public void setRecepcionCorrectaHotelId(Long recepcionCorrectaHotelId) {
		this.recepcionCorrectaHotelId = recepcionCorrectaHotelId;
	}
	public Long getVentaJuegoId() {
		return ventaJuegoId;
	}
	public void setVentaJuegoId(Long ventaJuegoId) {
		this.ventaJuegoId = ventaJuegoId;
	}
	public BigDecimal getMontoTotalIj() {
		return montoTotalIj;
	}
	public void setMontoTotalIj(BigDecimal montoTotalIj) {
		this.montoTotalIj = montoTotalIj;
	}
	public BigDecimal getMontoTotalSujetoIpj() {
		return montoTotalSujetoIpj;
	}
	public void setMontoTotalSujetoIpj(BigDecimal montoTotalSujetoIpj) {
		this.montoTotalSujetoIpj = montoTotalSujetoIpj;
	}
	public Long getRecepcionCorrectaJuegoId() {
		return recepcionCorrectaJuegoId;
	}
	public void setRecepcionCorrectaJuegoId(Long recepcionCorrectaJuegoId) {
		this.recepcionCorrectaJuegoId = recepcionCorrectaJuegoId;
	}
	public Long getVentaServicioId() {
		return ventaServicioId;
	}
	public void setVentaServicioId(Long ventaServicioId) {
		this.ventaServicioId = ventaServicioId;
	}
	public BigDecimal getConsumoKwh() {
		return consumoKwh;
	}
	public void setConsumoKwh(BigDecimal consumoKwh) {
		this.consumoKwh = consumoKwh;
	}
	public BigDecimal getConsumoMetrosCubicos() {
		return consumoMetrosCubicos;
	}
	public void setConsumoMetrosCubicos(BigDecimal consumoMetrosCubicos) {
		this.consumoMetrosCubicos = consumoMetrosCubicos;
	}
	public String getDomicilioComprador() {
		return domicilioComprador;
	}
	public void setDomicilioComprador(String domicilioComprador) {
		this.domicilioComprador = domicilioComprador;
	}
	public Integer getGestion() {
		return gestion;
	}
	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public BigDecimal getMontoDescuentoLey1886() {
		return montoDescuentoLey1886;
	}
	public void setMontoDescuentoLey1886(BigDecimal montoDescuentoLey1886) {
		this.montoDescuentoLey1886 = montoDescuentoLey1886;
	}
	public BigDecimal getMontoTotalSujetoIva() {
		return montoTotalSujetoIva;
	}
	public void setMontoTotalSujetoIva(BigDecimal montoTotalSujetoIva) {
		this.montoTotalSujetoIva = montoTotalSujetoIva;
	}
	public String getNumeroMedidor() {
		return numeroMedidor;
	}
	public void setNumeroMedidor(String numeroMedidor) {
		this.numeroMedidor = numeroMedidor;
	}
	public Long getRecepcionCorrectaServicioId() {
		return recepcionCorrectaServicioId;
	}
	public void setRecepcionCorrectaServicioId(Long recepcionCorrectaServicioId) {
		this.recepcionCorrectaServicioId = recepcionCorrectaServicioId;
	}
	public BigDecimal getTasaAlumbrado() {
		return tasaAlumbrado;
	}
	public void setTasaAlumbrado(BigDecimal tasaAlumbrado) {
		this.tasaAlumbrado = tasaAlumbrado;
	}
	public BigDecimal getTasaAseo() {
		return tasaAseo;
	}
	public void setTasaAseo(BigDecimal tasaAseo) {
		this.tasaAseo = tasaAseo;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
}
