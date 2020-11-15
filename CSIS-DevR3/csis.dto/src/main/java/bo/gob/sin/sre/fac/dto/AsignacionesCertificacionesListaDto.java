package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class AsignacionesCertificacionesListaDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<AsignacionesCertificacionesDto> asignacionesCertificacionesDto;

	public List<AsignacionesCertificacionesDto> getAsignacionesCertificacionesListaDto() {
		return asignacionesCertificacionesDto;
	}

	public void setAsignacionesCertificacionesListaDto(
			List<AsignacionesCertificacionesDto> asignacionesCertificacionesDto) {
		this.asignacionesCertificacionesDto = asignacionesCertificacionesDto;
	}
}
