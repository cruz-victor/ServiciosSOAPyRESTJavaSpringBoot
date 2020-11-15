package bo.gob.sin.sre.fac.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class DatosSistemasDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long sistemaId;
	private String nombreSistema;
	private String tipoSistema;
	private String modalidad;
	private Date fechaSolicitud;
	
	private List<DatosSistemasDto> listaDatosSistema;
	public DatosSistemasDto(Long sistemaId, String nombreSistema, String tipoSistema, String modalidad,
			Date fechaSolicitud) {
		this.sistemaId = sistemaId;
		this.nombreSistema = nombreSistema;
		this.tipoSistema = tipoSistema;
		this.modalidad = modalidad;
		this.fechaSolicitud = fechaSolicitud;
	}


	public DatosSistemasDto() {
		listaDatosSistema = new ArrayList<>();
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
	
	public String getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
	}
	
	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	
	public List<DatosSistemasDto> getListaDatosSistema() {
		return listaDatosSistema;
	}


	public void setListaDatosSistema(List<DatosSistemasDto> listaDatosSistema) {
		this.listaDatosSistema = listaDatosSistema;
	}


	@Override
	public String toString() {
		return "DatosSistemasDto [sistemaId=" + sistemaId + ", nombreSistema=" + nombreSistema + ", tipoSistema="
				+ tipoSistema + ", modalidad=" + modalidad + ", fechaSolicitud=" + fechaSolicitud + "]";
	}
	
}
