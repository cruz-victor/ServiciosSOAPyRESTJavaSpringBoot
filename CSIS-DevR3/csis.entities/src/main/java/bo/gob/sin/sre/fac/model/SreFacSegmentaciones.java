package bo.gob.sin.sre.fac.model;




import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sre_fac_segmentaciones_2019", schema="SRE_RECAUDACIONES")
public class SreFacSegmentaciones  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
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
	private Double montoVentas;
	
	@Column(name = "monto_compras")	
	private Double montoCompras;
	
	@Column(name = "cantidad_ventas")	
	private Double cantidadVentas;
	
	@Column(name = "cantidad_compras")	
	private Double cantidadCompras;
	
	@Column(name = "iva_declarado")	
	private Double ivaDeclarado;
	
	@Column(name = "caracteristicas")	
	private String caracteristicas;
	
	@Column(name = "marca_exportador")	
	private String marcaExportador;
	
	@Column(name = "modalidad_destino")	
	private String modalidadDestino;

	@Column(name = "criterio")	
	private String criterio;
	
	@Column(name = "grupo")	
	private String grupo;
	
	@Column(name = "fecha_adecuacion")	
	private Date fechaAdecuacion;

	public SreFacSegmentaciones() {
		
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

	public Double getMontoVentas() {
		return montoVentas;
	}

	public void setMontoVentas(Double montoVentas) {
		this.montoVentas = montoVentas;
	}

	public Double getMontoCompras() {
		return montoCompras;
	}

	public void setMontoCompras(Double montoCompras) {
		this.montoCompras = montoCompras;
	}

	public Double getCantidadVentas() {
		return cantidadVentas;
	}

	public void setCantidadVentas(Double cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}

	public Double getCantidadCompras() {
		return cantidadCompras;
	}

	public void setCantidadCompras(Double cantidadCompras) {
		this.cantidadCompras = cantidadCompras;
	}

	public Double getIvaDeclarado() {
		return ivaDeclarado;
	}

	public void setIvaDeclarado(Double ivaDeclarado) {
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

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}	
}
