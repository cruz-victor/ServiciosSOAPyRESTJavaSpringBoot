package bo.gob.sin.sre.fac.cfec.dto;

import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

/**
 * @author Reynaldo Chambi
 * @fecha 09/08/2019
 */
public class RespuestaFacturaVentasDto extends ListaMensajesAplicacion {

	private static final long serialVersionUID = 1L;

	private Long numeroFactura;
	private String autorizacionCuf;
	private String fechaEmision;
	private Float montoTotal;
	private List<DetalleFacturaVentasDto> detalleFacturaVentas;
	
	public RespuestaFacturaVentasDto() {
		
	}
	
	public RespuestaFacturaVentasDto(Long numeroFactura, String autorizacionCuf, String fechaEmision, Float montoTotal,
			List<DetalleFacturaVentasDto> detalleFacturaVentas) {
		super();
		this.numeroFactura = numeroFactura;
		this.autorizacionCuf = autorizacionCuf;
		this.fechaEmision = fechaEmision;
		this.montoTotal = montoTotal;
		this.detalleFacturaVentas = detalleFacturaVentas;
	}

	@Override
	public String toString() {
		return "RespuestaFacturaVentasDto [numeroFactura=" + numeroFactura + ", autorizacionCuf=" + autorizacionCuf
				+ ", fechaEmision=" + fechaEmision + ", montoTotal=" + montoTotal + ", detalleFacturaVentas="
				+ detalleFacturaVentas + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public String getAutorizacionCuf() {
		return autorizacionCuf;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public Float getMontoTotal() {
		return montoTotal;
	}

	public List<DetalleFacturaVentasDto> getDetalleFacturaVentas() {
		return detalleFacturaVentas;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public void setAutorizacionCuf(String autorizacionCuf) {
		this.autorizacionCuf = autorizacionCuf;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setMontoTotal(Float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public void setDetalleFacturaVentas(List<DetalleFacturaVentasDto> detalleFacturaVentas) {
		this.detalleFacturaVentas = detalleFacturaVentas;
	}
}