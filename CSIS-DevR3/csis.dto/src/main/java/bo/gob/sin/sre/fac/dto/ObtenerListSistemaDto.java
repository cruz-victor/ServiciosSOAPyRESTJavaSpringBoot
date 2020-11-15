package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ObtenerListSistemaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long personaContribuyenteId;
	private Integer estadoSolicitudCertificacionId;

	private List<SistemasDto> listaSistemas;

	public List<SistemasDto> getListaSistemas() {
		return listaSistemas;
	}

	public void setListaSistemas(List<SistemasDto> listaSistemas) {
		this.listaSistemas = listaSistemas;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public Integer getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}

	public void setEstadoSolicitudCertificacionId(Integer estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}

}