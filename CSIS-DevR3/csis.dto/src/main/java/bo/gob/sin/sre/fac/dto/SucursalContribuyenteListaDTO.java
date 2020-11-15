package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.scn.empa.casu.dto.SucursalContribuyenteDto;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;
//IASC
public class SucursalContribuyenteListaDTO extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SucursalContribuyenteDto> lista;

	public SucursalContribuyenteListaDTO(List<SucursalContribuyenteDto> lista) {
		super();
		this.lista = lista;
	}

	public SucursalContribuyenteListaDTO() 
	{
		this.lista = new ArrayList<>();
	}

	public List<SucursalContribuyenteDto> getLista() {
		return lista;
	}

	public void setLista(List<SucursalContribuyenteDto> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "SucursalContribuyenteListaDto [lista=" + lista + "]";
	}
	
}
