package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaRegistroHuellasDigitalesDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitales;

	public List<RegistroHuellasDigitalesDto> getListaRegistroHuellasDigitales() {
		return listaRegistroHuellasDigitales;
	}

	public void setListaRegistroHuellasDigitales(List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitales) {
		this.listaRegistroHuellasDigitales = listaRegistroHuellasDigitales;
	}
}
