package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SeguimientoCertificacionSistemasFaseDosDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer prueba_certificacion_sistema_id;
	private Integer etapa_certificacion_sistemas_id;
	private String descripcion_prueba;
	private Integer cantidad_pruebas;
	private Integer cantidad_pruebas_exitoso;
	private Integer cantidad_pruebas_pendiente;
	private Double porcentaje;
	private boolean es_prueba_concluida;
	private boolean existe_fecha_fin_prueba;
	private int etapaId; 
	
	public Integer getPrueba_certificacion_sistema_id() {
		return prueba_certificacion_sistema_id;
	}
	public void setPrueba_certificacion_sistema_id(Integer prueba_certificacion_sistema_id) {
		this.prueba_certificacion_sistema_id = prueba_certificacion_sistema_id;
	}
	public Integer getEtapa_certificacion_sistemas_id() {
		return etapa_certificacion_sistemas_id;
	}
	public void setEtapa_certificacion_sistemas_id(Integer etapa_certificacion_sistemas_id) {
		this.etapa_certificacion_sistemas_id = etapa_certificacion_sistemas_id;
	}
	public String getDescripcion_prueba() {
		return descripcion_prueba;
	}
	public void setDescripcion_prueba(String descripcion_prueba) {
		this.descripcion_prueba = descripcion_prueba;
	}
	public Integer getCantidad_pruebas() {
		return cantidad_pruebas;
	}
	public void setCantidad_pruebas(Integer cantidad_pruebas) {
		this.cantidad_pruebas = cantidad_pruebas;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public boolean isEs_prueba_concluida() {
		return es_prueba_concluida;
	}
	public void setEs_prueba_concluida(boolean es_prueba_concluida) {
		this.es_prueba_concluida = es_prueba_concluida;
	}
	public Integer getCantidad_pruebas_exitoso() {
		return cantidad_pruebas_exitoso;
	}
	public void setCantidad_pruebas_exitoso(Integer cantidad_pruebas_exitoso) {
		this.cantidad_pruebas_exitoso = cantidad_pruebas_exitoso;
	}
	public Integer getCantidad_pruebas_pendiente() {
		return cantidad_pruebas_pendiente;
	}
	public void setCantidad_pruebas_pendiente(Integer cantidad_pruebas_pendiente) {
		this.cantidad_pruebas_pendiente = cantidad_pruebas_pendiente;
	}
	public boolean isExiste_fecha_fin_prueba() {
		return existe_fecha_fin_prueba;
	}
	public void setExiste_fecha_fin_prueba(boolean existe_fecha_fin_prueba) {
		this.existe_fecha_fin_prueba = existe_fecha_fin_prueba;
	}
	/**
	 * @return the etapaId
	 */
	public int getEtapaId() {
		return etapaId;
	}
	/**
	 * @param etapaId the etapaId to set
	 */
	public void setEtapaId(int etapaId) {
		this.etapaId = etapaId;
	}	
}
