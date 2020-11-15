package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;


//IASC
public class RespuestaListaSistemasAutorizadosDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<RecuperaSistemasAutorizadosDto> listaSistemasAutorizados;

	public List<RecuperaSistemasAutorizadosDto> getListaSistemasAutorizados() {
		return listaSistemasAutorizados;
	}

	public void setListaSistemasAutorizados(List<RecuperaSistemasAutorizadosDto> listaSistemasAutorizados) {
		this.listaSistemasAutorizados = listaSistemasAutorizados;
	}

}
