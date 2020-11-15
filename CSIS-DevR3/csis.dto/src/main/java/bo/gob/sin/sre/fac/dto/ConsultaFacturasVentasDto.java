package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class ConsultaFacturasVentasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long nitEmisor;
	private Long contribuyenteId;
	private String fechaInicio;
	private String  fechaFin;
	private Integer tipoDocumentoFiscal;

	public ConsultaFacturasVentasDto() {		
	}

	public Long getNitEmisor() {
		return nitEmisor;
	}

	public void setNitEmisor(Long nitEmisor) {
		this.nitEmisor = nitEmisor;
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getTipoDocumentoFiscal() {
		return tipoDocumentoFiscal;
	}

	public void setTipoDocumentoFiscal(Integer tipoDocumentoFiscal) {
		this.tipoDocumentoFiscal = tipoDocumentoFiscal;
	}
}
