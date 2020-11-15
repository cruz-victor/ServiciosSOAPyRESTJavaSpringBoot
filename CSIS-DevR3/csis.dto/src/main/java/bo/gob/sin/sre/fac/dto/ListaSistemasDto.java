package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaSistemasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<SreSistemas> listaSistemas;
	private List<SreSolicitudCertificacion> listaSolicitudesCertificacion;
	
	public List<SreSolicitudCertificacion> getListaSolicitudesCertificacion() {
		return listaSolicitudesCertificacion;
	}

	public void setListaSolicitudesCertificacion(List<SreSolicitudCertificacion> listaSolicitudesCertificacion) {
		this.listaSolicitudesCertificacion = listaSolicitudesCertificacion;
	}

	public List<SreSistemas> getListaSistemas() {
		return listaSistemas;
	}

	public void setListaSistemas(List<SreSistemas> listaSistemas) {
		this.listaSistemas = listaSistemas;
	}
}
