package bo.gob.sin.sre.fac.cfec.dto;

/**
 * @author junior.flores
 * @fecha 29/05/2019
 */
public class LogConsumoServicioDto {

	private String p_codigo_sistema;

	private Integer p_tipo_documento_fiscal_id;

	private Integer p_tipo_documento_sector_id;
	
	private String p_estado_id;

	private Integer p_estado_match;

	private String p_codigo_error;

	private String p_descripcion_respuesta;
	
	private Integer p_etapa_certificacion_sistema_id;
	
	private Long v_recepcionId;
	
	private Long v_recepcionPaqueteId;
	
	private Integer v_cantidadFacturas;
	
	private Integer v_cantidadMaximaFacturas;
	
	private Long p_nit;

	public String getP_codigo_sistema() {
		return p_codigo_sistema;
	}

	public void setP_codigo_sistema(String p_codigo_sistema) {
		this.p_codigo_sistema = p_codigo_sistema;
	}

	public Integer getP_tipo_documento_fiscal_id() {
		return p_tipo_documento_fiscal_id;
	}

	public void setP_tipo_documento_fiscal_id(Integer p_tipo_documento_fiscal_id) {
		this.p_tipo_documento_fiscal_id = p_tipo_documento_fiscal_id;
	}

	public Integer getP_tipo_documento_sector_id() {
		return p_tipo_documento_sector_id;
	}

	public void setP_tipo_documento_sector_id(Integer p_tipo_documento_sector_id) {
		this.p_tipo_documento_sector_id = p_tipo_documento_sector_id;
	}

	public String getP_estado_id() {
		return p_estado_id;
	}

	public void setP_estado_id(String p_estado_id) {
		this.p_estado_id = p_estado_id;
	}

	public Integer getP_estado_match() {
		return p_estado_match;
	}

	public void setP_estado_match(Integer p_estado_match) {
		this.p_estado_match = p_estado_match;
	}

	public String getP_codigo_error() {
		return p_codigo_error;
	}

	public void setP_codigo_error(String p_codigo_error) {
		this.p_codigo_error = p_codigo_error;
	}

	public String getP_descripcion_respuesta() {
		return p_descripcion_respuesta;
	}

	public void setP_descripcion_respuesta(String p_descripcion_respuesta) {
		this.p_descripcion_respuesta = p_descripcion_respuesta;
	}

	public Long getV_recepcionId() {
		return v_recepcionId;
	}

	public void setV_recepcionId(Long v_recepcionId) {
		this.v_recepcionId = v_recepcionId;
	}

	public Integer getP_etapa_certificacion_sistema_id() {
		return p_etapa_certificacion_sistema_id;
	}

	public void setP_etapa_certificacion_sistema_id(Integer p_etapa_certificacion_sistema_id) {
		this.p_etapa_certificacion_sistema_id = p_etapa_certificacion_sistema_id;
	}

	public Long getV_recepcionPaqueteId() {
		return v_recepcionPaqueteId;
	}

	public void setV_recepcionPaqueteId(Long v_recepcionPaqueteId) {
		this.v_recepcionPaqueteId = v_recepcionPaqueteId;
	}

	public Integer getV_cantidadFacturas() {
		return v_cantidadFacturas;
	}

	public void setV_cantidadFacturas(Integer v_cantidadFacturas) {
		this.v_cantidadFacturas = v_cantidadFacturas;
	}

	public Integer getV_cantidadMaximaFacturas() {
		return v_cantidadMaximaFacturas;
	}

	public void setV_cantidadMaximaFacturas(Integer v_cantidadMaximaFacturas) {
		this.v_cantidadMaximaFacturas = v_cantidadMaximaFacturas;
	}

	public Long getP_nit() {
		return p_nit;
	}

	public void setP_nit(Long p_nit) {
		this.p_nit = p_nit;
	}
}
