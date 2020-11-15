package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaMatchLogCasosPruebaEtapa2Dto extends ListaMensajesAplicacion implements Serializable  {	
	private static final long serialVersionUID = 1L;
	private int estado;

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
