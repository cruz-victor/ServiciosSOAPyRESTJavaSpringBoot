package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudConsultaFacturacionDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private long nit;

	public SolicitudConsultaFacturacionDto() {
		
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}
	
	
}
