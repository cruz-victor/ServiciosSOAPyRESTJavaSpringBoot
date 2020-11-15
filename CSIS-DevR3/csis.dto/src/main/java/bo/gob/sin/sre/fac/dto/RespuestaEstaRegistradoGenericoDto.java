package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaEstaRegistradoGenericoDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean estaRegistrado;

	public boolean isEstaRegistrado() {
		return estaRegistrado;
	}

	public void setEstaRegistrado(boolean estaRegistrado) {
		this.estaRegistrado = estaRegistrado;
	}


}
