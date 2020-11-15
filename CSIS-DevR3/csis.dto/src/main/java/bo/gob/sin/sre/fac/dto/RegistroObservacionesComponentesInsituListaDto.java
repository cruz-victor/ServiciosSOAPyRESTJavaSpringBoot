package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RegistroObservacionesComponentesInsituListaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<RegistroObservacionesComponentesInsituDto> registrosObservacionesComponentesInsituDto;

	public List<RegistroObservacionesComponentesInsituDto> getRegistrosObservacionesComponentesInsituDto() {
		return registrosObservacionesComponentesInsituDto;
	}

	public void setRegistrosObservacionesComponentesInsituDto(List<RegistroObservacionesComponentesInsituDto> registrosObservacionesComponentesInsituDto) {
		this.registrosObservacionesComponentesInsituDto = registrosObservacionesComponentesInsituDto;
	}

	
}
