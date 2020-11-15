package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaTamanioGenericoDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long tamanioLista;

	public Long getTamanioLista() {
		return tamanioLista;
	}

	public void setTamanioLista(Long tamanioLista) {
		this.tamanioLista = tamanioLista;
	}

}
