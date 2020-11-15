package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaVerificacionPruebasConcluidasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean pruebasConcluidas;

	public boolean isPruebasConcluidas() {
		return pruebasConcluidas;
	}

	public void setPruebasConcluidas(boolean pruebasConcluidas) {
		this.pruebasConcluidas = pruebasConcluidas;
	}
}
