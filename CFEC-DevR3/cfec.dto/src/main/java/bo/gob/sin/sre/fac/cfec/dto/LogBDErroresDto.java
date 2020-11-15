package bo.gob.sin.sre.fac.cfec.dto;

import java.util.Date;

public class LogBDErroresDto {
	private long errorId;
	private long usuarioRegistroId;
	private int subsistemaId;
	private String funcion;
	private Date fechaRegistro;
	private String codigoError;
	private String descripcion;
	private String valoresParametro;
	private String estadoId;
	private long nit;
	private Long recepcionId;
	private Long recepcionPaqueteId;
	private int tipoDocumentoSectorId;

	/**
	 * @param errorId
	 * @param usuarioRegistroId
	 * @param subsistemaId
	 * @param funcion
	 * @param fechaRegistro
	 * @param codigoError
	 * @param descripcion
	 * @param valoresParametro
	 * @param estadoId
	 * @param nit
	 * @param recepcionId
	 * @param recepcionPaqueteId
	 * @param tipoDocumentoSectorId
	 */
	public LogBDErroresDto(long errorId, long usuarioRegistroId, int subsistemaId, String funcion, Date fechaRegistro,
			String codigoError, String descripcion, String valoresParametro, String estadoId, long nit,
			Long recepcionId, Long recepcionPaqueteId, int tipoDocumentoSectorId) {
		super();
		this.errorId = errorId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.subsistemaId = subsistemaId;
		this.funcion = funcion;
		this.fechaRegistro = fechaRegistro;
		this.codigoError = codigoError;
		this.descripcion = descripcion;
		this.valoresParametro = valoresParametro;
		this.estadoId = estadoId;
		this.nit = nit;
		this.recepcionId = recepcionId;
		this.recepcionPaqueteId = recepcionPaqueteId;
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}

	public LogBDErroresDto() {
	}

	/**
	 * @return the errorId
	 */
	public long getErrorId() {
		return errorId;
	}

	/**
	 * @param errorId the errorId to set
	 */
	public void setErrorId(long errorId) {
		this.errorId = errorId;
	}

	/**
	 * @return the usuarioRegistroId
	 */
	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	/**
	 * @param usuarioRegistroId the usuarioRegistroId to set
	 */
	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	/**
	 * @return the subsistemaId
	 */
	public int getSubsistemaId() {
		return subsistemaId;
	}

	/**
	 * @param subsistemaId the subsistemaId to set
	 */
	public void setSubsistemaId(int subsistemaId) {
		this.subsistemaId = subsistemaId;
	}

	/**
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * @param funcion the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the valoresParametro
	 */
	public String getValoresParametro() {
		return valoresParametro;
	}

	/**
	 * @param valoresParametro the valoresParametro to set
	 */
	public void setValoresParametro(String valoresParametro) {
		this.valoresParametro = valoresParametro;
	}

	/**
	 * @return the estadoId
	 */
	public String getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId the estadoId to set
	 */
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * @return the nit
	 */
	public long getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(long nit) {
		this.nit = nit;
	}

	/**
	 * @return the recepcionId
	 */
	public Long getRecepcionId() {
		return recepcionId;
	}

	/**
	 * @param recepcionId the recepcionId to set
	 */
	public void setRecepcionId(Long recepcionId) {
		this.recepcionId = recepcionId;
	}

	/**
	 * @return the recepcionPaqueteId
	 */
	public Long getRecepcionPaqueteId() {
		return recepcionPaqueteId;
	}

	/**
	 * @param recepcionPaqueteId the recepcionPaqueteId to set
	 */
	public void setRecepcionPaqueteId(Long recepcionPaqueteId) {
		this.recepcionPaqueteId = recepcionPaqueteId;
	}

	/**
	 * @return the tipoDocumentoSectorId
	 */
	public int getTipoDocumentoSectorId() {
		return tipoDocumentoSectorId;
	}

	/**
	 * @param tipoDocumentoSectorId the tipoDocumentoSectorId to set
	 */
	public void setTipoDocumentoSectorId(int tipoDocumentoSectorId) {
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}

}