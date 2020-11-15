package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

public class RespuestaMensajesCorrespondenciaDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean ok;
	private List<MensajesCorrespondenciaDto> mensajes;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public List<MensajesCorrespondenciaDto> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<MensajesCorrespondenciaDto> mensajes) {
		this.mensajes = mensajes;
	}

}
