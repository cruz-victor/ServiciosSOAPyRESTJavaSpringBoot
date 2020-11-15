package bo.gob.sin.sre.fac.cfec.dto;

public class XmlRecepcionGenericoDto {
	private Long p_sistema_id;
	private Integer p_tipo_ambiente_id;
	private Integer p_tipo_emision_id;
	private Integer p_tipo_modalidad_id;
	private Long p_nit;
	private String p_cuis;
	private String p_cufd;
	private Integer p_tipo_documento_fiscal_id;
	private Integer p_tipo_documento_sector_id;
	private Integer p_sucursal_id;
	private Integer p_punto_venta_id;
	private String p_fecha_envio;
	private String v_archivo;
	private String v_hash_archivo;
	private String p_codigo_sistema;

	private Long p_numero_documento_fiscal;
	private Integer p_motivo_anulacion_id;
	private String p_cuf;
	private Long p_recepcion_anulacion_id;

	private String p_cufp;
	private String p_cuape;
	// ***** atributos complementarios *****
	private Long p_persona_contribuyente_id;
	private Integer v_oficinaId;
	private Long v_recepcionId;
	private Long v_recepcionPaqueteId;
	private String v_archivoXml;
	private String v_archivoXmlB64;
	private Long v_usuarioRegistroId;
	private Long v_usuarioUltimaModificacionId;

	private Integer v_tipoDepartamentoId;

	/**
	 * valor que determina la procedencia del servicio 1 - Electronica, 2 -
	 * computarizada,...
	 */
	private int vOrigenServicio;
	private String p_caed;
	private Long p_codigoAutorizacionAlimentaria;

	public XmlRecepcionGenericoDto() {

	}

	public XmlRecepcionGenericoDto(Long p_sistema_id, Integer p_tipo_ambiente_id, Integer p_tipo_emision_id,
			Integer p_tipo_modalidad_id, Long p_nit, String p_cuis, String p_cufd, Integer p_tipo_documento_fiscal_id,
			Integer p_tipo_documento_sector_id, Integer p_sucursal_id, Integer p_punto_venta_id, String p_fecha_envio,
			String v_archivo, String v_hash_archivo, String p_codigo_sistema, Long p_numero_documento_fiscal,
			Integer p_motivo_anulacion_id, String p_cuf, Long p_recepcion_anulacion_id, String p_cufp, String p_cuape,
			Long p_persona_contribuyente_id, Integer v_oficinaId, Long v_recepcionId, Long v_recepcionPaqueteId,
			String v_archivoXml, String v_archivoXmlB64, Long v_usuarioRegistroId, Long v_usuarioUltimaModificacionId,
			Integer v_tipoDepartamentoId, int vOrigenServicio, String p_caed, Long p_codigoAutorizacionAlimentaria) {
		super();
		this.p_sistema_id = p_sistema_id;
		this.p_tipo_ambiente_id = p_tipo_ambiente_id;
		this.p_tipo_emision_id = p_tipo_emision_id;
		this.p_tipo_modalidad_id = p_tipo_modalidad_id;
		this.p_nit = p_nit;
		this.p_cuis = p_cuis;
		this.p_cufd = p_cufd;
		this.p_tipo_documento_fiscal_id = p_tipo_documento_fiscal_id;
		this.p_tipo_documento_sector_id = p_tipo_documento_sector_id;
		this.p_sucursal_id = p_sucursal_id;
		this.p_punto_venta_id = p_punto_venta_id;
		this.p_fecha_envio = p_fecha_envio;
		this.v_archivo = v_archivo;
		this.v_hash_archivo = v_hash_archivo;
		this.p_codigo_sistema = p_codigo_sistema;
		this.p_numero_documento_fiscal = p_numero_documento_fiscal;
		this.p_motivo_anulacion_id = p_motivo_anulacion_id;
		this.p_cuf = p_cuf;
		this.p_recepcion_anulacion_id = p_recepcion_anulacion_id;
		this.p_cufp = p_cufp;
		this.p_cuape = p_cuape;
		this.p_persona_contribuyente_id = p_persona_contribuyente_id;
		this.v_oficinaId = v_oficinaId;
		this.v_recepcionId = v_recepcionId;
		this.v_recepcionPaqueteId = v_recepcionPaqueteId;
		this.v_archivoXml = v_archivoXml;
		this.v_archivoXmlB64 = v_archivoXmlB64;
		this.v_usuarioRegistroId = v_usuarioRegistroId;
		this.v_usuarioUltimaModificacionId = v_usuarioUltimaModificacionId;
		this.v_tipoDepartamentoId = v_tipoDepartamentoId;
		this.vOrigenServicio = vOrigenServicio;
		this.p_caed = p_caed;
		this.p_codigoAutorizacionAlimentaria = p_codigoAutorizacionAlimentaria;
	}

	@Override
	public String toString() {
		return "XmlRecepcionGenericoDto [p_sistema_id=" + p_sistema_id + ", p_tipo_ambiente_id=" + p_tipo_ambiente_id
				+ ", p_tipo_emision_id=" + p_tipo_emision_id + ", p_tipo_modalidad_id=" + p_tipo_modalidad_id
				+ ", p_nit=" + p_nit + ", p_cuis=" + p_cuis + ", p_cufd=" + p_cufd + ", p_tipo_documento_fiscal_id="
				+ p_tipo_documento_fiscal_id + ", p_tipo_documento_sector_id=" + p_tipo_documento_sector_id
				+ ", p_sucursal_id=" + p_sucursal_id + ", p_punto_venta_id=" + p_punto_venta_id + ", p_fecha_envio="
				+ p_fecha_envio + ", v_archivo=" + v_archivo + ", v_hash_archivo=" + v_hash_archivo
				+ ", p_codigo_sistema=" + p_codigo_sistema + ", p_numero_documento_fiscal=" + p_numero_documento_fiscal
				+ ", p_motivo_anulacion_id=" + p_motivo_anulacion_id + ", p_cuf=" + p_cuf
				+ ", p_recepcion_anulacion_id=" + p_recepcion_anulacion_id + ", p_cufp=" + p_cufp + ", p_cuape="
				+ p_cuape + ", p_persona_contribuyente_id=" + p_persona_contribuyente_id + ", v_oficinaId="
				+ v_oficinaId + ", v_recepcionId=" + v_recepcionId + ", v_recepcionPaqueteId=" + v_recepcionPaqueteId
				+ ", v_archivoXml=" + v_archivoXml + ", v_archivoXmlB64=" + v_archivoXmlB64 + ", v_usuarioRegistroId="
				+ v_usuarioRegistroId + ", v_usuarioUltimaModificacionId=" + v_usuarioUltimaModificacionId
				+ ", v_tipoDepartamentoId=" + v_tipoDepartamentoId + ", vOrigenServicio=" + vOrigenServicio
				+ ", p_caed=" + p_caed + ", p_codigoAutorizacionAlimentaria=" + p_codigoAutorizacionAlimentaria + "]";
	}

	public Long getP_sistema_id() {
		return p_sistema_id;
	}

	public void setP_sistema_id(Long p_sistema_id) {
		this.p_sistema_id = p_sistema_id;
	}

	public Integer getP_tipo_ambiente_id() {
		return p_tipo_ambiente_id;
	}

	public void setP_tipo_ambiente_id(Integer p_tipo_ambiente_id) {
		this.p_tipo_ambiente_id = p_tipo_ambiente_id;
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

	public Long getP_nit() {
		return p_nit;
	}

	public void setP_nit(Long p_nit) {
		this.p_nit = p_nit;
	}

	public String getP_cuis() {
		return p_cuis;
	}

	public void setP_cuis(String p_cuis) {
		this.p_cuis = p_cuis;
	}

	public String getP_cufd() {
		return p_cufd;
	}

	public void setP_cufd(String p_cufd) {
		this.p_cufd = p_cufd;
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

	public Integer getP_sucursal_id() {
		return p_sucursal_id;
	}

	public void setP_sucursal_id(Integer p_sucursal_id) {
		this.p_sucursal_id = p_sucursal_id;
	}

	public Integer getP_punto_venta_id() {
		return p_punto_venta_id;
	}

	public void setP_punto_venta_id(Integer p_punto_venta_id) {
		this.p_punto_venta_id = p_punto_venta_id;
	}

	public String getP_fecha_envio() {
		return p_fecha_envio;
	}

	public void setP_fecha_envio(String p_fecha_envio) {
		this.p_fecha_envio = p_fecha_envio;
	}

	public String getV_archivo() {
		return v_archivo;
	}

	public void setV_archivo(String v_archivo) {
		this.v_archivo = v_archivo;
	}

	public String getP_codigo_sistema() {
		return p_codigo_sistema;
	}

	public void setP_codigo_sistema(String p_codigo_sistema) {
		this.p_codigo_sistema = p_codigo_sistema;
	}

	public Long getP_numero_documento_fiscal() {
		return p_numero_documento_fiscal;
	}

	public void setP_numero_documento_fiscal(Long p_numero_documento_fiscal) {
		this.p_numero_documento_fiscal = p_numero_documento_fiscal;
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

	public Long getP_recepcion_anulacion_id() {
		return p_recepcion_anulacion_id;
	}

	public void setP_recepcion_anulacion_id(Long p_recepcion_anulacion_id) {
		this.p_recepcion_anulacion_id = p_recepcion_anulacion_id;
	}

	public Long getP_persona_contribuyente_id() {
		return p_persona_contribuyente_id;
	}

	public void setP_persona_contribuyente_id(Long p_persona_contribuyente_id) {
		this.p_persona_contribuyente_id = p_persona_contribuyente_id;
	}

	public Integer getV_oficinaId() {
		return v_oficinaId;
	}

	public void setV_oficinaId(Integer v_oficinaId) {
		this.v_oficinaId = v_oficinaId;
	}

	public Long getV_recepcionId() {
		return v_recepcionId;
	}

	public void setV_recepcionId(Long v_recepcionId) {
		this.v_recepcionId = v_recepcionId;
	}

	public Long getV_recepcionPaqueteId() {
		return v_recepcionPaqueteId;
	}

	public void setV_recepcionPaqueteId(Long v_recepcionPaqueteId) {
		this.v_recepcionPaqueteId = v_recepcionPaqueteId;
	}

	public String getV_archivoXml() {
		return v_archivoXml;
	}

	public void setV_archivoXml(String v_archivoXml) {
		this.v_archivoXml = v_archivoXml;
	}

	public String getV_archivoXmlB64() {
		return v_archivoXmlB64;
	}

	public void setV_archivoXmlB64(String v_archivoXmlB64) {
		this.v_archivoXmlB64 = v_archivoXmlB64;
	}

	public Long getV_usuarioRegistroId() {
		return v_usuarioRegistroId;
	}

	public void setV_usuarioRegistroId(Long v_usuarioRegistroId) {
		this.v_usuarioRegistroId = v_usuarioRegistroId;
	}

	public Long getV_usuarioUltimaModificacionId() {
		return v_usuarioUltimaModificacionId;
	}

	public void setV_usuarioUltimaModificacionId(Long v_usuarioUltimaModificacionId) {
		this.v_usuarioUltimaModificacionId = v_usuarioUltimaModificacionId;
	}

	public Integer getV_tipoDepartamentoId() {
		return v_tipoDepartamentoId;
	}

	public void setV_tipoDepartamentoId(Integer v_tipoDepartamentoId) {
		this.v_tipoDepartamentoId = v_tipoDepartamentoId;
	}

	public String getV_hash_archivo() {
		return v_hash_archivo;
	}

	public void setV_hash_archivo(String v_hash_archivo) {
		this.v_hash_archivo = v_hash_archivo;
	}

	public String getP_cufp() {
		return p_cufp;
	}

	public String getP_cuape() {
		return p_cuape;
	}

	public void setP_cufp(String p_cufp) {
		this.p_cufp = p_cufp;
	}

	public void setP_cuape(String p_cuape) {
		this.p_cuape = p_cuape;
	}

	/**
	 * @return the vOrigenServicio
	 */
	public int getvOrigenServicio() {
		return vOrigenServicio;
	}

	/**
	 * @param vOrigenServicio the vOrigenServicio to set
	 */
	public void setvOrigenServicio(int vOrigenServicio) {
		this.vOrigenServicio = vOrigenServicio;
	}

	public String getP_caed() {
		return p_caed;
	}

	public void setP_caed(String p_caed) {
		this.p_caed = p_caed;
	}

	public Long getP_codigoAutorizacionAlimentaria() {
		return p_codigoAutorizacionAlimentaria;
	}

	public void setP_codigoAutorizacionAlimentaria(Long p_codigoAutorizacionAlimentaria) {
		this.p_codigoAutorizacionAlimentaria = p_codigoAutorizacionAlimentaria;
	}

}