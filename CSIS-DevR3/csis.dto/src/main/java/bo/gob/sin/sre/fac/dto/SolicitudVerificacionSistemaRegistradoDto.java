package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SolicitudVerificacionSistemaRegistradoDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//IASC
	private Long contribuyenteId;
	private String nombreSistema;
	private List<String> listaModalidad;
	private Long nit;
	private Integer tipoSistema;
	
	public SolicitudVerificacionSistemaRegistradoDto(Long contribuyenteId, String nombreSistema,
			List<String> listaModalidad, Long nit, Integer tipoSistema) {
		this.contribuyenteId = contribuyenteId;
		this.nombreSistema = nombreSistema;
		this.listaModalidad = listaModalidad;
		this.nit = nit;
		this.tipoSistema = tipoSistema;
	}

	public SolicitudVerificacionSistemaRegistradoDto() {
		this.listaModalidad = new ArrayList<>();
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}
	
	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}
	
	public String getNombreSistema() {
		return nombreSistema;
	}
	
	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public List<String> getListaModalidad() {
		return listaModalidad;
	}

	public void setListaModalidad(List<String> listaModalidad) {
		this.listaModalidad = listaModalidad;
	}

	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public Integer getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(Integer tipoSistema) {
		this.tipoSistema = tipoSistema;
	}
	
}
