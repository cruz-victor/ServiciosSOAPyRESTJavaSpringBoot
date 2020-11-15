package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RecuperaListaSistemaCertificacion  extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SistemaSolicitudCertificacionDto> lista;
	private boolean esEditable;

	public List<SistemaSolicitudCertificacionDto> getLista() {
		return lista;
	}

	public void setLista(List<SistemaSolicitudCertificacionDto> lista) {
		this.lista = lista;
	}

	public boolean isEsEditable() {
		return esEditable;
	}

	public void setEsEditable(boolean esEditable) {
		this.esEditable = esEditable;
	}
}
