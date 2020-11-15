package bo.gob.sin.sre.fac.cfec.dto;

public class RespuestaDatosCufDto {
	
	private Long nitEmisor;
	private String fechaEmision;
	private Integer sucursal;
	private Integer modalidad;
	private Integer tipoEmision;
	private Integer documentoFiscal;
	private Integer documentoSector;
	private Long numeroFactura;
	private Integer puntoVenta;
	
	public RespuestaDatosCufDto() {
	}

	public RespuestaDatosCufDto(Long nitEmisor, String fechaEmision, Integer sucursal, Integer modalidad,
			Integer tipoEmision, Integer documentoFiscal, Integer documentoSector, Long numeroFactura,
			Integer puntoVenta) {
		this.nitEmisor = nitEmisor;
		this.fechaEmision = fechaEmision;
		this.sucursal = sucursal;
		this.modalidad = modalidad;
		this.tipoEmision = tipoEmision;
		this.documentoFiscal = documentoFiscal;
		this.documentoSector = documentoSector;
		this.numeroFactura = numeroFactura;
		this.puntoVenta = puntoVenta;
	}

	public Long getNitEmisor() {
		return nitEmisor;
	}

	public void setNitEmisor(Long nitEmisor) {
		this.nitEmisor = nitEmisor;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getModalidad() {
		return modalidad;
	}

	public void setModalidad(Integer modalidad) {
		this.modalidad = modalidad;
	}

	public Integer getTipoEmision() {
		return tipoEmision;
	}

	public void setTipoEmision(Integer tipoEmision) {
		this.tipoEmision = tipoEmision;
	}

	public Integer getDocumentoFiscal() {
		return documentoFiscal;
	}

	public void setDocumentoFiscal(Integer documentoFiscal) {
		this.documentoFiscal = documentoFiscal;
	}

	public Integer getDocumentoSector() {
		return documentoSector;
	}

	public void setDocumentoSector(Integer documentoSector) {
		this.documentoSector = documentoSector;
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Integer getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(Integer puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	@Override
	public String toString() {
		return "RespuestaDatosCufDto [nitEmisor=" + nitEmisor + ", fechaEmision=" + fechaEmision + ", sucursal="
				+ sucursal + ", modalidad=" + modalidad + ", tipoEmision=" + tipoEmision + ", documentoFiscal="
				+ documentoFiscal + ", documentoSector=" + documentoSector + ", numeroFactura=" + numeroFactura
				+ ", puntoVenta=" + puntoVenta + "]";
	}

}
