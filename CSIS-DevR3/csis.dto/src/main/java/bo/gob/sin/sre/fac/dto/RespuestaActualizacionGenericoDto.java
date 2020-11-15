package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaActualizacionGenericoDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean estaActualizado;

	public boolean isEstaActualizado() {
		return estaActualizado;
	}

	public void setEstaActualizado(boolean estaActualizado) {
		this.estaActualizado = estaActualizado;
	}


}
