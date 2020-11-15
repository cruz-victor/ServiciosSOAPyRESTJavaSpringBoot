package bo.gob.sin.sre.fac.cfec.dto;

public class XmlRecepcionGenericoRestDto {
	private String v_archivo;
	private Integer p_tipo_ambiente_id;
	private Integer p_tipo_documento_fiscal_id;
	private Integer p_tipo_documento_sector_id;
	private Integer p_tipo_emision_id;
	private Integer p_tipo_modalidad_id;
	private Integer p_punto_venta_id;
	private String p_codigo_sistema;
	private Integer p_sucursal_id;
	private String p_cufd;
	private String p_cuis;
	private String p_fecha_envio;
	private String v_hash_archivo;
	private Long p_nit;
	// Validacion
	private Long v_recepcionId;
	// Anulacion
	private Integer p_motivo_anulacion_id;
	private String p_cuf;
	private Long p_numero_documento_fiscal;
	private Long p_recepcion_anulacion_id;
	
	/**
	 * valor que determina la procedencia del servicio 1 - Electronica, 2 -
	 * computarizada,...
	 */
	private int vOrigenServicio;
	private int vEtapa;

	public XmlRecepcionGenericoRestDto() {

	}

	public XmlRecepcionGenericoRestDto(String v_archivo, Integer p_tipo_ambiente_id, Integer p_tipo_documento_fiscal_id,
			Integer p_tipo_documento_sector_id, Integer p_tipo_emision_id, Integer p_tipo_modalidad_id,
			Integer p_punto_venta_id, String p_codigo_sistema, Integer p_sucursal_id, String p_cufd, String p_cuis,
			String p_fecha_envio, String v_hash_archivo, Long p_nit, Long v_recepcionId, Integer p_motivo_anulacion_id,
			String p_cuf, Long p_numero_documento_fiscal, Long p_recepcion_anulacion_id, int vOrigenServicio,
			int vEtapa) {
		super();
		this.v_archivo = v_archivo;
		this.p_tipo_ambiente_id = p_tipo_ambiente_id;
		this.p_tipo_documento_fiscal_id = p_tipo_documento_fiscal_id;
		this.p_tipo_documento_sector_id = p_tipo_documento_sector_id;
		this.p_tipo_emision_id = p_tipo_emision_id;
		this.p_tipo_modalidad_id = p_tipo_modalidad_id;
		this.p_punto_venta_id = p_punto_venta_id;
		this.p_codigo_sistema = p_codigo_sistema;
		this.p_sucursal_id = p_sucursal_id;
		this.p_cufd = p_cufd;
		this.p_cuis = p_cuis;
		this.p_fecha_envio = p_fecha_envio;
		this.v_hash_archivo = v_hash_archivo;
		this.p_nit = p_nit;
		this.v_recepcionId = v_recepcionId;
		this.p_motivo_anulacion_id = p_motivo_anulacion_id;
		this.p_cuf = p_cuf;
		this.p_numero_documento_fiscal = p_numero_documento_fiscal;
		this.p_recepcion_anulacion_id = p_recepcion_anulacion_id;
		this.vOrigenServicio = vOrigenServicio;
		this.vEtapa = vEtapa;
	}

	@Override
	public String toString() {
		return "XmlRecepcionGenericoRestDto [v_archivo=" + v_archivo + ", p_tipo_ambiente_id=" + p_tipo_ambiente_id
				+ ", p_tipo_documento_fiscal_id=" + p_tipo_documento_fiscal_id + ", p_tipo_documento_sector_id="
				+ p_tipo_documento_sector_id + ", p_tipo_emision_id=" + p_tipo_emision_id + ", p_tipo_modalidad_id="
				+ p_tipo_modalidad_id + ", p_punto_venta_id=" + p_punto_venta_id + ", p_codigo_sistema="
				+ p_codigo_sistema + ", p_sucursal_id=" + p_sucursal_id + ", p_cufd=" + p_cufd + ", p_cuis=" + p_cuis
				+ ", p_fecha_envio=" + p_fecha_envio + ", v_hash_archivo=" + v_hash_archivo + ", p_nit=" + p_nit
				+ ", v_recepcionId=" + v_recepcionId + ", p_motivo_anulacion_id=" + p_motivo_anulacion_id + ", p_cuf="
				+ p_cuf + ", p_numero_documento_fiscal=" + p_numero_documento_fiscal + ", p_recepcion_anulacion_id="
				+ p_recepcion_anulacion_id + ", vOrigenServicio=" + vOrigenServicio + ", vEtapa=" + vEtapa + "]";
	}

	public String getV_archivo() {
		return v_archivo;
	}

	public void setV_archivo(String v_archivo) {
		this.v_archivo = v_archivo;
	}

	public Integer getP_tipo_ambiente_id() {
		return p_tipo_ambiente_id;
	}

	public void setP_tipo_ambiente_id(Integer p_tipo_ambiente_id) {
		this.p_tipo_ambiente_id = p_tipo_ambiente_id;
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

	public Integer getP_tipo_emision_id() {
		return p_tipo_emision_id;
	}

	public void setP_tipo_emision_id(Integer p_tipo_emision_id) {
		this.p_tipo_emision_id = p_tipo_emision_id;
	}

	public Integer getP_tipo_modalidad_id() {
		return p_tipo_modalidad_id;
	}

	public void setP_tipo_modalidad_id(Integer p_tipo_modalidad_id) {
		this.p_tipo_modalidad_id = p_tipo_modalidad_id;
	}

	public Integer getP_punto_venta_id() {
		return p_punto_venta_id;
	}

	public void setP_punto_venta_id(Integer p_punto_venta_id) {
		this.p_punto_venta_id = p_punto_venta_id;
	}

	public String getP_codigo_sistema() {
		return p_codigo_sistema;
	}

	public void setP_codigo_sistema(String p_codigo_sistema) {
		this.p_codigo_sistema = p_codigo_sistema;
	}

	public Integer getP_sucursal_id() {
		return p_sucursal_id;
	}

	public void setP_sucursal_id(Integer p_sucursal_id) {
		this.p_sucursal_id = p_sucursal_id;
	}

	public String getP_cufd() {
		return p_cufd;
	}

	public void setP_cufd(String p_cufd) {
		this.p_cufd = p_cufd;
	}

	public String getP_cuis() {
		return p_cuis;
	}

	public void setP_cuis(String p_cuis) {
		this.p_cuis = p_cuis;
	}

	public String getP_fecha_envio() {
		return p_fecha_envio;
	}

	public void setP_fecha_envio(String p_fecha_envio) {
		this.p_fecha_envio = p_fecha_envio;
	}

	public String getV_hash_archivo() {
		return v_hash_archivo;
	}

	public void setV_hash_archivo(String v_hash_archivo) {
		this.v_hash_archivo = v_hash_archivo;
	}

	public Long getP_nit() {
		return p_nit;
	}

	public void setP_nit(Long p_nit) {
		this.p_nit = p_nit;
	}

	public Long getV_recepcionId() {
		return v_recepcionId;
	}

	public void setV_recepcionId(Long v_recepcionId) {
		this.v_recepcionId = v_recepcionId;
	}

	public Integer getP_motivo_anulacion_id() {
		return p_motivo_anulacion_id;
	}

	public void setP_motivo_anulacion_id(Integer p_motivo_anulacion_id) {
		this.p_motivo_anulacion_id = p_motivo_anulacion_id;
	}

	public String getP_cuf() {
		return p_cuf;
	}

	public void setP_cuf(String p_cuf) {
		this.p_cuf = p_cuf;
	}

	public Long getP_numero_documento_fiscal() {
		return p_numero_documento_fiscal;
	}

	public void setP_numero_documento_fiscal(Long p_numero_documento_fiscal) {
		this.p_numero_documento_fiscal = p_numero_documento_fiscal;
	}

	public Long getP_recepcion_anulacion_id() {
		return p_recepcion_anulacion_id;
	}

	public void setP_recepcion_anulacion_id(Long p_recepcion_anulacion_id) {
		this.p_recepcion_anulacion_id = p_recepcion_anulacion_id;
	}

	public int getvOrigenServicio() {
		return vOrigenServicio;
	}

	public void setvOrigenServicio(int vOrigenServicio) {
		this.vOrigenServicio = vOrigenServicio;
	}

	public int getvEtapa() {
		return vEtapa;
	}

	public void setvEtapa(int vEtapa) {
		this.vEtapa = vEtapa;
	}
}