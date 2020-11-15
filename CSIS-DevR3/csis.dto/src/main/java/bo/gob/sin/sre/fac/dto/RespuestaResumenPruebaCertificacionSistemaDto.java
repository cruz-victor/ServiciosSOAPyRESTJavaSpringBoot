package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaResumenPruebaCertificacionSistemaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<ResumenPruebaCertificacionSistemaDto> lista;

	public List<ResumenPruebaCertificacionSistemaDto> getLista() {
		return lista;
	}

	public void setLista(List<ResumenPruebaCertificacionSistemaDto> lista) {
		this.lista = lista;
	}

	public RespuestaResumenPruebaCertificacionSistemaDto() {
		this.lista = new ArrayList<>();
	}
	
}
