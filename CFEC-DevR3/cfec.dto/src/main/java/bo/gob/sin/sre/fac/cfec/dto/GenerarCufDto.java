package bo.gob.sin.sre.fac.cfec.dto;

import java.util.Date;

public class GenerarCufDto {

	private Long pNit; 
	private Integer pSucursal; 
	private String pFecha; 
	private Integer	pModalidad; 
	private Integer pTipoEmision;  
	private Integer pTipoDocumentoFiscal; 
	private Integer pTipoDocumentoSector; 
	private Long pNumFactura; 
	private Integer pPuntoVenta;
	
	public GenerarCufDto() {
	}
	
	public GenerarCufDto(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,
			Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura,
			Integer pPuntoVenta) {
		super();
		this.pNit = pNit;
		this.pSucursal = pSucursal;
		this.pFecha = pFecha;
		this.pModalidad = pModalidad;
		this.pTipoEmision = pTipoEmision;
		this.pTipoDocumentoFiscal = pTipoDocumentoFiscal;
		this.pTipoDocumentoSector = pTipoDocumentoSector;
		this.pNumFactura = pNumFactura;
		this.pPuntoVenta = pPuntoVenta;
	}

	@Override
	public String toString() {
		return "OperacionesCuf [pNit=" + pNit + ", pSucursal=" + pSucursal + ", pFecha=" + pFecha + ", pModalidad="
				+ pModalidad + ", pTipoEmision=" + pTipoEmision + ", pTipoDocumentoFiscal=" + pTipoDocumentoFiscal
				+ ", pTipoDocumentoSector=" + pTipoDocumentoSector + ", pNumFactura=" + pNumFactura
				+ ", pPuntoVenta=" + pPuntoVenta + "]";
	}

	public Long getpNit() {
		return pNit;
	}

	public void setpNit(Long pNit) {
		this.pNit = pNit;
	}

	public Integer getpSucursal() {
		return pSucursal;
	}

	public void setpSucursal(Integer pSucursal) {
		this.pSucursal = pSucursal;
	}

	public String getpFecha() {
		return pFecha;
	}

	public void setpFecha(String pFecha) {
		this.pFecha = pFecha;
	}

	public Integer getpModalidad() {
		return pModalidad;
	}

	public void setpModalidad(Integer pModalidad) {
		this.pModalidad = pModalidad;
	}

	public Integer getpTipoEmision() {
		return pTipoEmision;
	}

	public void setpTipoEmision(Integer pTipoEmision) {
		this.pTipoEmision = pTipoEmision;
	}

	public Integer getpTipoDocumentoFiscal() {
		return pTipoDocumentoFiscal;
	}

	public void setpTipoDocumentoFiscal(Integer pTipoDocumentoFiscal) {
		this.pTipoDocumentoFiscal = pTipoDocumentoFiscal;
	}

	public Integer getpTipoDocumentoSector() {
		return pTipoDocumentoSector;
	}

	public void setpTipoDocumentoSector(Integer pTipoDocumentoSector) {
		this.pTipoDocumentoSector = pTipoDocumentoSector;
	}

	public Long getpNumFactura() {
		return pNumFactura;
	}

	public void setpNumFactura(Long pNumFactura) {
		this.pNumFactura = pNumFactura;
	}

	public Integer getpPuntoVenta() {
		return pPuntoVenta;
	}

	public void setpPuntoVenta(Integer pPuntoVenta) {
		this.pPuntoVenta = pPuntoVenta;
	}
}
