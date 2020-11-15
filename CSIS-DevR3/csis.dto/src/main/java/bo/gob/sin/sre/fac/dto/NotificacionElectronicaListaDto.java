package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class NotificacionElectronicaListaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<NotificacionElectronicaDto> notificacionesElectronicasDto;

	public List<NotificacionElectronicaDto> getNotificacionesElectronicasDto() {
		return notificacionesElectronicasDto;
	}

	public void setNotificacionesElectronicasDto(List<NotificacionElectronicaDto> notificacionesElectronicasDto) {
		this.notificacionesElectronicasDto = notificacionesElectronicasDto;
	}

	
}
