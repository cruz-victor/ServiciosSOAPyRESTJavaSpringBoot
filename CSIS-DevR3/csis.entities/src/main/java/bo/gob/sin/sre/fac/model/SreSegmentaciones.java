package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "sre_fac_segmentaciones", schema="SRE_RECAUDACIONES")
public class SreSegmentaciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nit")
	private long nit;

	@Column(name = "razon_social")
	private String razonSocial;

	@Column(name = "tipo_persona")
	private String tipoPersona;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "segmentacion")
	private String segmentacion;

	@Column(name = "actividad")
	private String actividad;

	@Column(name = "gran_actividad")
	private String granActividad;

	@Column(name = "departamento")
	private String departamento;

	@Column(name = "alcaldia")
	private String alcaldia;

	@Column(name = "modalidad_predominante")
	private String modalidadPredominante;

	@Column(name = "monto_ventas")
	private BigDecimal montoVentas;

	@Column(name = "montoCompras")
	private BigDecimal montoCompras;

	@Column(name = "cantidad_ventas")
	private BigDecimal cantidadVentas;

	@Column(name = "cantidad_compras")
	private BigDecimal cantidadCompras;

	@Column(name = "iva_declarado")
	private BigDecimal ivaDeclarado;

	@Column(name = "caracteristicas")
	private String caracteristicas;

	@Column(name = "marca_exportador")
	private String marcaExportador;

	@Column(name = "fecha_adecuacion")
	private Date fechaAdecuacion;

	@Column(name = "grupo")
	private String grupo;

	@Column(name = "modalidad_destino")
	private String modalidadDestino;

	@Column(name = "estado_id")
	private String estadoId;

	@Column(name = "procesado")
	private Integer procesado;

	@Column(name = "in_situ")
	private Short inSitu;

	public SreSegmentaciones(long nit, String razonSocial, String tipoPersona, String categoria, String segmentacion,
			String actividad, String granActividad, String departamento, String alcaldia, String modalidadPredominante,
			BigDecimal montoVentas, BigDecimal montoCompras, BigDecimal cantidadVentas, BigDecimal cantidadCompras,
			BigDecimal ivaDeclarado, String caracteristicas, String marcaExportador, Date fechaAdecuacion, String grupo,
			String modalidadDestino, String estadoId, Integer procesado, Short inSitu) {
		super();
		this.nit = nit;
		this.razonSocial = razonSocial;
		this.tipoPersona = tipoPersona;
		this.categoria = categoria;
		this.segmentacion = segmentacion;
		this.actividad = actividad;
		this.granActividad = granActividad;
		this.departamento = departamento;
		this.alcaldia = alcaldia;
		this.modalidadPredominante = modalidadPredominante;
		this.montoVentas = montoVentas;
		this.montoCompras = montoCompras;
		this.cantidadVentas = cantidadVentas;
		this.cantidadCompras = cantidadCompras;
		this.ivaDeclarado = ivaDeclarado;
		this.caracteristicas = caracteristicas;
		this.marcaExportador = marcaExportador;
		this.fechaAdecuacion = fechaAdecuacion;
		this.grupo = grupo;
		this.modalidadDestino = modalidadDestino;
		this.estadoId = estadoId;
		this.procesado = procesado;
		this.inSitu = inSitu;
	}

	@Override
	public String toString() {
		return "SreSegmentaciones [nit=" + nit + ", razonSocial=" + razonSocial + ", tipoPersona=" + tipoPersona
				+ ", categoria=" + categoria + ", segmentacion=" + segmentacion + ", actividad=" + actividad
				+ ", granActividad=" + granActividad + ", departamento=" + departamento + ", alcaldia=" + alcaldia
				+ ", modalidadPredominante=" + modalidadPredominante + ", montoVentas=" + montoVentas
				+ ", montoCompras=" + montoCompras + ", cantidadVentas=" + cantidadVentas + ", cantidadCompras="
				+ cantidadCompras + ", ivaDeclarado=" + ivaDeclarado + ", caracteristicas=" + caracteristicas
				+ ", marcaExportador=" + marcaExportador + ", fechaAdecuacion=" + fechaAdecuacion + ", grupo=" + grupo
				+ ", modalidadDestino=" + modalidadDestino + ", estadoId=" + estadoId + ", procesado=" + procesado
				+ ", inSitu=" + inSitu + "]";
	}

	public SreSegmentaciones() {
		super();
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSegmentacion() {
		return segmentacion;
	}

	public void setSegmentacion(String segmentacion) {
		this.segmentacion = segmentacion;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getGranActividad() {
		return granActividad;
	}

	public void setGranActividad(String granActividad) {
		this.granActividad = granActividad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getAlcaldia() {
		return alcaldia;
	}

	public void setAlcaldia(String alcaldia) {
		this.alcaldia = alcaldia;
	}

	public String getModalidadPredominante() {
		return modalidadPredominante;
	}

	public void setModalidadPredominante(String modalidadPredominante) {
		this.modalidadPredominante = modalidadPredominante;
	}

	public BigDecimal getMontoVentas() {
		return montoVentas;
	}

	public void setMontoVentas(BigDecimal montoVentas) {
		this.montoVentas = montoVentas;
	}

	public BigDecimal getMontoCompras() {
		return montoCompras;
	}

	public void setMontoCompras(BigDecimal montoCompras) {
		this.montoCompras = montoCompras;
	}

	public BigDecimal getCantidadVentas() {
		return cantidadVentas;
	}

	public void setCantidadVentas(BigDecimal cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}

	public BigDecimal getCantidadCompras() {
		return cantidadCompras;
	}

	public void setCantidadCompras(BigDecimal cantidadCompras) {
		this.cantidadCompras = cantidadCompras;
	}

	public BigDecimal getIvaDeclarado() {
		return ivaDeclarado;
	}

	public void setIvaDeclarado(BigDecimal ivaDeclarado) {
		this.ivaDeclarado = ivaDeclarado;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getMarcaExportador() {
		return marcaExportador;
	}

	public void setMarcaExportador(String marcaExportador) {
		this.marcaExportador = marcaExportador;
	}

	public Date getFechaAdecuacion() {
		return fechaAdecuacion;
	}

	public void setFechaAdecuacion(Date fechaAdecuacion) {
		this.fechaAdecuacion = fechaAdecuacion;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getModalidadDestino() {
		return modalidadDestino;
	}

	public void setModalidadDestino(String modalidadDestino) {
		this.modalidadDestino = modalidadDestino;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Integer getProcesado() {
		return procesado;
	}

	public void setProcesado(Integer procesado) {
		this.procesado = procesado;
	}

	public Short getInSitu() {
		return inSitu;
	}

	public void setInSitu(Short inSitu) {
		this.inSitu = inSitu;
	}

}
