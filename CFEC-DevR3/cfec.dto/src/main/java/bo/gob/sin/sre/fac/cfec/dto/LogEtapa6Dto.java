package bo.gob.sin.sre.fac.cfec.dto;

public class LogEtapa6Dto {
	private String codigoSistema;
	private Integer tipoDocumentoFiscalId;
	private Integer tipoDocumentoSectorId;
	private Long codigoRecepcionPaqueteId;
	private Long nit;

	/**
	 * @return the codigoSistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return the tipoDocumentoFiscalId
	 */
	public Integer getTipoDocumentoFiscalId() {
		return tipoDocumentoFiscalId;
	}

	/**
	 * @param tipoDocumentoFiscalId the tipoDocumentoFiscalId to set
	 */
	public void setTipoDocumentoFiscalId(Integer tipoDocumentoFiscalId) {
		this.tipoDocumentoFiscalId = tipoDocumentoFiscalId;
	}

	/**
	 * @return the tipoDocumentoSectorId
	 */
	public Integer getTipoDocumentoSectorId() {
		return tipoDocumentoSectorId;
	}

	/**
	 * @param tipoDocumentoSectorId the tipoDocumentoSectorId to set
	 */
	public void setTipoDocumentoSectorId(Integer tipoDocumentoSectorId) {
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}

	/**
	 * @return the codigoRecepcionPaqueteId
	 */
	public Long getCodigoRecepcionPaqueteId() {
		return codigoRecepcionPaqueteId;
	}

	/**
	 * @param codigoRecepcionPaqueteId the codigoRecepcionPaqueteId to set
	 */
	public void setCodigoRecepcionPaqueteId(Long codigoRecepcionPaqueteId) {
		this.codigoRecepcionPaqueteId = codigoRecepcionPaqueteId;
	}

	/**
	 * @return the nit
	 */
	public Long getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(Long nit) {
		this.nit = nit;
	}

}