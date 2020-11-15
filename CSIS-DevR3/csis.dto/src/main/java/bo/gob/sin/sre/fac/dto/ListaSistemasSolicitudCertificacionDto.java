package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaSistemasSolicitudCertificacionDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long datosEntradaContribuyenteId;
	
	private List<SistemasSolicitudCertificacionDto> listaSistemasSolicitudCertificacionDto;

	public void ListaSistemasSolicitudCertificacionDto()
	{
		listaSistemasSolicitudCertificacionDto = new ArrayList<>();
	}
	
	public Long getDatosEntradaContribuyenteId() {
		return datosEntradaContribuyenteId;
	}

	public void setDatosEntradaContribuyenteId(Long datosEntradaContribuyenteId) {
		this.datosEntradaContribuyenteId = datosEntradaContribuyenteId;
	}

	public List<SistemasSolicitudCertificacionDto> getListaSistemasSolicitudCertificacionDto() {
		return listaSistemasSolicitudCertificacionDto;
	}

	public void setListaSistemasSolicitudCertificacionDto(
			List<SistemasSolicitudCertificacionDto> listaSistemasSolicitudCertificacionDto) {
		this.listaSistemasSolicitudCertificacionDto = listaSistemasSolicitudCertificacionDto;
	}
}
