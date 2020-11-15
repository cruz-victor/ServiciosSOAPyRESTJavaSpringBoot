package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

//IASC
public class RespuestaListaSistemasContribuyentesDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes;
	private RecuperaSistemasContribuyentesDto listaContribuyentes;
	
	

	public List<RecuperaSistemasContribuyentesDto> getListaSistemasContribuyentes() {
		return listaSistemasContribuyentes;
	}

	public void setListaSistemasContribuyentes(List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes) {
		this.listaSistemasContribuyentes = listaSistemasContribuyentes;
	}

	public RecuperaSistemasContribuyentesDto getListaContribuyentes() {
		return listaContribuyentes;
	}

	public void setListaContribuyentes(RecuperaSistemasContribuyentesDto listaContribuyentes) {
		this.listaContribuyentes = listaContribuyentes;
	}

	@Override
	public String toString() {
		return "RespuestaListaSistemasContribuyentesDto [listaSistemasContribuyentes=" + listaSistemasContribuyentes
				+ "]";
	}

}
