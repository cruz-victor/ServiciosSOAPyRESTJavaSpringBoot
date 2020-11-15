package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaSeguimientoCertificacionSistemasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SeguimientoCertificacionSistemasDto> listaSeguimientoCertificacionSistemasDto;

	public List<SeguimientoCertificacionSistemasDto> getListaSeguimientoCertificacionSistemasDto() {
		return listaSeguimientoCertificacionSistemasDto;
	}

	public void setListaSeguimientoCertificacionSistemasDto(
			List<SeguimientoCertificacionSistemasDto> listaSeguimientoCertificacionSistemasDto) {
		this.listaSeguimientoCertificacionSistemasDto = listaSeguimientoCertificacionSistemasDto;
	}	
}
