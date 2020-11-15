package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

/**
 * @author Reynaldo Chambi
 * @fecha 29/07/2019
 */
public class RespuestaObtenerComprasDto {

	private static final long serialVersionUID = 1L;

	private String fechaEmision;
	private String modalidadFacturacion;
	private String numeroFactura;
	private String cufAutorizacion;
	private String nitEmisor;
	private String montoTotal;
	private String estadoFactura;
	private List<Integer> listaCodigosRespuestas;
	private boolean transaccion;
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getModalidadFacturacion() {
		return modalidadFacturacion;
	}
	public void setModalidadFacturacion(String modalidadFacturacion) {
		this.modalidadFacturacion = modalidadFacturacion;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public String getCufAutorizacion() {
		return cufAutorizacion;
	}
	public void setCufAutorizacion(String cufAutorizacion) {
		this.cufAutorizacion = cufAutorizacion;
	}
	public String getNitEmisor() {
		return nitEmisor;
	}
	public void setNitEmisor(String nitEmisor) {
		this.nitEmisor = nitEmisor;
	}
	public String getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getEstadoFactura() {
		return estadoFactura;
	}
	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Integer> getListaCodigosRespuestas() {
		return listaCodigosRespuestas;
	}
	public boolean isTransaccion() {
		return transaccion;
	}
	public void setListaCodigosRespuestas(List<Integer> listaCodigosRespuestas) {
		this.listaCodigosRespuestas = listaCodigosRespuestas;
	}
	public void setTransaccion(boolean transaccion) {
		this.transaccion = transaccion;
	}
}