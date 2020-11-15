package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaRegistrosObservacionesInsituDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<RegistrosObservacionesInsituDto> lista;

	public List<RegistrosObservacionesInsituDto> getLista() {
		return lista;
	}

	public void setLista(List<RegistrosObservacionesInsituDto> lista) {
		this.lista = lista;
	}
	
	
	
	
	

}
