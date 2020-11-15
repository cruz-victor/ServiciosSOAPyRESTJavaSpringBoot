package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaListaDetallePruebasEtapas2Dto extends ListaMensajesAplicacion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<DetallePruebasEtapa2Dto> listaDetallePruebas;

	public List<DetallePruebasEtapa2Dto> getListaDetallePruebas() {
		return listaDetallePruebas;
	}

	public void setListaDetallePruebas(List<DetallePruebasEtapa2Dto> listaDetallePruebas) {
		this.listaDetallePruebas = listaDetallePruebas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}			
}
