package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaDetalleHuellaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<RespuestaDetalleHuellaDto> ListaDetalleHuella;
	
	private Long datoEntradaIdSistema;
	private Long datoEntradaIdSolicitudCertificacion;
	private Long datoEntradaIdContribuyente;
	
	public ListaDetalleHuellaDto()
	{
		ListaDetalleHuella = new ArrayList<>();
	}

	public List<RespuestaDetalleHuellaDto> getListaDetalleHuella() {
		return ListaDetalleHuella;
	}

	public void setListaDetalleHuella(List<RespuestaDetalleHuellaDto> listaDetalleHuella) {
		ListaDetalleHuella = listaDetalleHuella;
	}

	public Long getDatoEntradaIdSistema() {
		return datoEntradaIdSistema;
	}

	public void setDatoEntradaIdSistema(Long datoEntradaIdSistema) {
		this.datoEntradaIdSistema = datoEntradaIdSistema;
	}

	public Long getDatoEntradaIdSolicitudCertificacion() {
		return datoEntradaIdSolicitudCertificacion;
	}

	public void setDatoEntradaIdSolicitudCertificacion(Long datoEntradaIdSolicitudCertificacion) {
		this.datoEntradaIdSolicitudCertificacion = datoEntradaIdSolicitudCertificacion;
	}

	public Long getDatoEntradaIdContribuyente() {
		return datoEntradaIdContribuyente;
	}

	public void setDatoEntradaIdContribuyente(Long datoEntradaIdContribuyente) {
		this.datoEntradaIdContribuyente = datoEntradaIdContribuyente;
	}
}