package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaDetalleSeguimientoCertificacionSistemasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SeguimientoDetalleCertificacionSistemasDto> listaSeguimientoDetalleCertificacionSistemasDto;

	public List<SeguimientoDetalleCertificacionSistemasDto> getListaSeguimientoDetalleCertificacionSistemasDto() {
		return listaSeguimientoDetalleCertificacionSistemasDto;
	}

	public void setListaSeguimientoDetalleCertificacionSistemasDto(
			List<SeguimientoDetalleCertificacionSistemasDto> listaSeguimientoDetalleCertificacionSistemasDto) {
		this.listaSeguimientoDetalleCertificacionSistemasDto = listaSeguimientoDetalleCertificacionSistemasDto;
	}
	
	
}
