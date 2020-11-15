package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ObtenerListaSistemasCertificacionDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public List <RecuperaListaSistemasCertificacionDto> listaSistemaDto;

	public List<RecuperaListaSistemasCertificacionDto> getListaSistemaDto() {
		return listaSistemaDto;
	}

	public void setListaSistemaDto(List<RecuperaListaSistemasCertificacionDto> listaSistemaDto) {
		this.listaSistemaDto = listaSistemaDto;
	}


}
