package bo.gob.sin.sre.fac.dto;


import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;


public class RespuestaPruebasSistemasDeCertificacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<PruebasSistemasDto> lista;
	private boolean esEditable;
	public List<PruebasSistemasDto> getLista() {
		return lista;
	}

	public void setLista(List<PruebasSistemasDto> lista) {
		this.lista = lista;
	}

	public boolean isEsEditable() {
		return esEditable;
	}

	public void setEsEditable(boolean esEditable) {
		this.esEditable = esEditable;
	}

}
