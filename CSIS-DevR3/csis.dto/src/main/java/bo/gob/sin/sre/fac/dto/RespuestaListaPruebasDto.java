package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;
import bo.gob.sin.sre.fac.dto.PruebasDto;

//IASC
public class RespuestaListaPruebasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<PruebasDto> lista;

	public List<PruebasDto> getLista() {
		return lista;
	}

	public void setLista(List<PruebasDto> lista) {
		this.lista = lista;
	}
}
