package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;

/**
 * @author freddy.yuca
 * @fecha 24/01/2019
 */
public class SolicitudJsonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String solicitud;

	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}

}