package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaDetalleCasosDePruebaEtapa0Dto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<DetalleCasosDePruebaEtapa0Dto> listaDetalleCasosDePruebaEtapa0;

	public List<DetalleCasosDePruebaEtapa0Dto> getListaDetalleCasosDePruebaEtapa0() {
		return listaDetalleCasosDePruebaEtapa0;
	}

	public void setListaDetalleCasosDePruebaEtapa0(List<DetalleCasosDePruebaEtapa0Dto> listaDetalleCasosDePruebaEtapa0) {
		this.listaDetalleCasosDePruebaEtapa0 = listaDetalleCasosDePruebaEtapa0;
	}
	 
}
