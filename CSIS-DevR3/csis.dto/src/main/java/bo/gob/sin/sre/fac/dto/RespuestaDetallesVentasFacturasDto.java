package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaDetallesVentasFacturasDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<DetallesVentasFacturasDto> listaDetallesVentasFacturas;

	public List<DetallesVentasFacturasDto> getListaDetallesVentasFacturas() {
		return listaDetallesVentasFacturas;
	}

	public void setListaDetallesVentasFacturas(List<DetallesVentasFacturasDto> listaDetallesVentasFacturas) {
		this.listaDetallesVentasFacturas = listaDetallesVentasFacturas;
	}
	
	
}
