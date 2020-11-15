package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;

/**
 * @author freddy.yuca
 * @fecha 24/01/2019
 */
public class SolicitudObtencionFacturaVentasDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String autorizacionCuf;
	private Long nit;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAutorizacionCuf() {
		return autorizacionCuf;
	}
	public Long getNit() {
		return nit;
	}
	public void setAutorizacionCuf(String autorizacionCuf) {
		this.autorizacionCuf = autorizacionCuf;
	}
	public void setNit(Long nit) {
		this.nit = nit;
	}


}