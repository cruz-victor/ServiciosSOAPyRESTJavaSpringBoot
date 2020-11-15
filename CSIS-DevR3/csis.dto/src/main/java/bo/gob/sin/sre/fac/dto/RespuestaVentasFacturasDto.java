package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaVentasFacturasDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<VentasFacturasDto> listaVentasFacturas;

	public List<VentasFacturasDto> getListaVentasFacturas() {
		return listaVentasFacturas;
	}

	public void setListaVentasFacturas(List<VentasFacturasDto> listaVentasFacturas) {
		this.listaVentasFacturas = listaVentasFacturas;
	}

}
