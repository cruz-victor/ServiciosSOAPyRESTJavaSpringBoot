package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaServicioDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long idRegistro;
	private String Cuis;
	private Long sistemaContribuyenteId;
	private Long sistemaId;
	private String nombreSistema;
	
	private String observacion;
	private Long contribuyenteId;
	private Integer estadoSistemaContribuyenteId;
	
	private List<RespuestaServicioDto> lista;
	public RespuestaServicioDto() {
	}

	public RespuestaServicioDto(Long idRegistro, String cuis) {
		this.idRegistro = idRegistro;
		Cuis = cuis;
	}

	public String getCuis() {
		return Cuis;
	}

	public void setCuis(String cuis) {
		Cuis = cuis;
	}

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Long getSistemaContribuyenteId() {
		return sistemaContribuyenteId;
	}

	public void setSistemaContribuyenteId(Long sistemaContribuyenteId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	

	public List<RespuestaServicioDto> getLista() {
		return lista;
	}

	public void setLista(List<RespuestaServicioDto> lista) {
		this.lista = lista;
	}



	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public Integer getEstadoSistemaContribuyenteId() {
		return estadoSistemaContribuyenteId;
	}

	public void setEstadoSistemaContribuyenteId(Integer estadoSistemaContribuyenteId) {
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
	}

	@Override
	public String toString() {
		return "RespuestaServicioDto [idRegistro=" + idRegistro + ", Cuis=" + Cuis + ", sistemaContribuyenteId="
				+ sistemaContribuyenteId + ", sistemaId=" + sistemaId + ", nombreSistema=" + nombreSistema + "]";
	}
}
