package bo.gob.sin.sre.fac.cfec.dto;

import java.util.Date;

public class SolicitudObtenerCompraPorDia {

	private String pFecha;
	private String pNumeroDocumento; 
	private String pCodigoSistema; 
	private String pCufd; 
	private String pCuis;
	private Integer pCodigoSucursal;
	private Integer pCodigoPuntoVenta; 
	private Integer pCodigoAmbiente;
	public String getpFecha() {
		return pFecha;
	}
	public String getpNumeroDocumento() {
		return pNumeroDocumento;
	}
	public String getpCodigoSistema() {
		return pCodigoSistema;
	}
	public String getpCufd() {
		return pCufd;
	}
	public String getpCuis() {
		return pCuis;
	}
	public Integer getpCodigoSucursal() {
		return pCodigoSucursal;
	}
	public Integer getpCodigoPuntoVenta() {
		return pCodigoPuntoVenta;
	}
	public Integer getpCodigoAmbiente() {
		return pCodigoAmbiente;
	}
	public void setpFecha(String pFecha) {
		this.pFecha = pFecha;
	}
	public void setpNumeroDocumento(String pNumeroDocumento) {
		this.pNumeroDocumento = pNumeroDocumento;
	}
	public void setpCodigoSistema(String pCodigoSistema) {
		this.pCodigoSistema = pCodigoSistema;
	}
	public void setpCufd(String pCufd) {
		this.pCufd = pCufd;
	}
	public void setpCuis(String pCuis) {
		this.pCuis = pCuis;
	}
	public void setpCodigoSucursal(Integer pCodigoSucursal) {
		this.pCodigoSucursal = pCodigoSucursal;
	}
	public void setpCodigoPuntoVenta(Integer pCodigoPuntoVenta) {
		this.pCodigoPuntoVenta = pCodigoPuntoVenta;
	}
	public void setpCodigoAmbiente(Integer pCodigoAmbiente) {
		this.pCodigoAmbiente = pCodigoAmbiente;
	}
	
}
