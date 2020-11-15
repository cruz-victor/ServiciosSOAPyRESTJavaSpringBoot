package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaListadoSistemasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer listaTamanio;
	private List<RecuperaListaSistemasCertificacionDto> lista;

	public Integer getListaTamanio() {
		return listaTamanio;
	}

	public List<RecuperaListaSistemasCertificacionDto> getLista() {
		return lista;
	}

	public void setListaTamanio(Integer listaTamanio) {
		this.listaTamanio = listaTamanio;
	}

	public void setLista(List<RecuperaListaSistemasCertificacionDto> lista) {
		this.lista = lista;
	}
}
