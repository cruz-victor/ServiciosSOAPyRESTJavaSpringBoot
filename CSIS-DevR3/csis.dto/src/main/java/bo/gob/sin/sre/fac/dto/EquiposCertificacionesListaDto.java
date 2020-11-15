package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class EquiposCertificacionesListaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<EquiposCertificacionesDto> equiposCertificacionesDto;

	public List<EquiposCertificacionesDto> getEquiposCertificacionesDto() {
		return equiposCertificacionesDto;
	}

	public void setEquiposCertificacionesDto(List<EquiposCertificacionesDto> equiposCertificacionesDto) {
		this.equiposCertificacionesDto = equiposCertificacionesDto;
	}

}
