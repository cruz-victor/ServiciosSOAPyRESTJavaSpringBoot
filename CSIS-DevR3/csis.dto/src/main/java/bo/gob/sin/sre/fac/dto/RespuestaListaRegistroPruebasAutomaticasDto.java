package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaListaRegistroPruebasAutomaticasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<RegistrosPruebasAutomaticasDto> pruebasAutomaticas;

	public List<RegistrosPruebasAutomaticasDto> getPruebasAutomaticas() {
		return pruebasAutomaticas;
	}

	public void setPruebasAutomaticas(List<RegistrosPruebasAutomaticasDto> pruebasAutomaticas) {
		this.pruebasAutomaticas = pruebasAutomaticas;
	}

}
