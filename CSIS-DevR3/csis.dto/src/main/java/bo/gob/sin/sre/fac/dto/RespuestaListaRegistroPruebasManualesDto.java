package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaListaRegistroPruebasManualesDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<RegistrosPruebasManualesDto> pruebasManuales;

	public List<RegistrosPruebasManualesDto> getPruebasManuales() {
		return pruebasManuales;
	}

	public void setPruebasManuales(List<RegistrosPruebasManualesDto> pruebasManuales) {
		this.pruebasManuales = pruebasManuales;
	}

}
