package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

//IASC
public class RespuestaListaDetalleCertificacionesSistemasDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<DetallesCertificacionesSistemasDto> listaCertificacionesSistemasDto;

	public List<DetallesCertificacionesSistemasDto> getListaCertificacionesSistemasDto() {
		return listaCertificacionesSistemasDto;
	}

	public void setListaCertificacionesSistemasDto(
			List<DetallesCertificacionesSistemasDto> listaCertificacionesSistemasDto) {
		this.listaCertificacionesSistemasDto = listaCertificacionesSistemasDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
