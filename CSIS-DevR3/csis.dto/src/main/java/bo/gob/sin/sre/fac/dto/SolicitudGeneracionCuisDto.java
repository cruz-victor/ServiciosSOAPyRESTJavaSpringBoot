package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class SolicitudGeneracionCuisDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//IASC
	private long nit;
	private long idSistema;
	private Integer sucursal;
	private long modalidad;
	private String cuis;
	
	public SolicitudGeneracionCuisDto(long nit, long idSistema, Integer sucursal, long modalidad, String cuis) {
		this.nit = nit;
		this.idSistema = idSistema;
		this.sucursal = sucursal;
		this.modalidad = modalidad;
		this.cuis = cuis;
	}

	public SolicitudGeneracionCuisDto() {
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public long getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(long idSistema) {
		this.idSistema = idSistema;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public long getModalidad() {
		return modalidad;
	}

	public void setModalidad(long modalidad) {
		this.modalidad = modalidad;
	}
	
	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	@Override
	public String toString() {
		return "SolicitudGeneracionCuisDto [nit=" + nit + ", idSistema=" + idSistema + ", sucursal=" + sucursal
				+ ", modalidad=" + modalidad + ", cuis=" + cuis + "]";
	}

}
