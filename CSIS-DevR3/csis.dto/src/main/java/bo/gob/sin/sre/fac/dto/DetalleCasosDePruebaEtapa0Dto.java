package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class DetalleCasosDePruebaEtapa0Dto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long nit;
	private String fecha_emision;
	private int sucursal;
	private int modalidad;
	private int emision;
	private int documento_fiscal;
	private int sector;
	private String numero_factura;
	private Integer punto_venta;
	private Long cantidad_casos_correctos;
	private Long cantidad_casos_incorrectos;
	private Long cantidad_casos_esperados; 
	private boolean es_caso_prueba_registrada;//sugerida
	private String fecha_prueba;
	private long caso_prueba_id;
	private String hash;
	private String hash_descripcion;
	private int opcional;
	
	
	public Long getNit() {
		return nit;
	}
	public void setNit(Long nit) {
		this.nit = nit;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public int getModalidad() {
		return modalidad;
	}
	public void setModalidad(int modalidad) {
		this.modalidad = modalidad;
	}
	public int getEmision() {
		return emision;
	}
	public void setEmision(int emision) {
		this.emision = emision;
	}
	public int getDocumento_fiscal() {
		return documento_fiscal;
	}
	public void setDocumento_fiscal(int documento_fiscal) {
		this.documento_fiscal = documento_fiscal;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public Integer getPunto_venta() {
		return punto_venta;
	}
	public void setPunto_venta(Integer punto_venta) {
		this.punto_venta = punto_venta;
	}
	public Long getCantidad_casos_correctos() {
		return cantidad_casos_correctos;
	}
	public void setCantidad_casos_correctos(Long cantidad_casos_correctos) {
		this.cantidad_casos_correctos = cantidad_casos_correctos;
	}
	public Long getCantidad_casos_incorrectos() {
		return cantidad_casos_incorrectos;
	}
	public void setCantidad_casos_incorrectos(Long cantidad_casos_incorrectos) {
		this.cantidad_casos_incorrectos = cantidad_casos_incorrectos;
	}
	public Long getCantidad_casos_esperados() {
		return cantidad_casos_esperados;
	}
	public void setCantidad_casos_esperados(Long cantidad_casos_esperados) {
		this.cantidad_casos_esperados = cantidad_casos_esperados;
	}
	public String getFecha_prueba() {
		return fecha_prueba;
	}
	public void setFecha_prueba(String fecha_prueba) {
		this.fecha_prueba = fecha_prueba;
	}
	public long getCaso_prueba_id() {
		return caso_prueba_id;
	}
	public void setCaso_prueba_id(long caso_prueba_id) {
		this.caso_prueba_id = caso_prueba_id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getHash_descripcion() {
		return hash_descripcion;
	}
	public void setHash_descripcion(String hash_descripcion) {
		this.hash_descripcion = hash_descripcion;
	}
	public int getOpcional() {
		return opcional;
	}
	public void setOpcional(int opcional) {
		this.opcional = opcional;
	}
	public boolean isEs_caso_prueba_registrada() {
		return es_caso_prueba_registrada;
	}
	public void setEs_caso_prueba_registrada(boolean es_caso_prueba_registrada) {
		this.es_caso_prueba_registrada = es_caso_prueba_registrada;
	}

}
