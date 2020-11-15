package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class GeneraCuisDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long nit;
	private long idSistema;
	private Integer sucursal;
	private long modalidad;
	private long usuario;
	private long contribuyenteId;
	private long sistemaContribuyenteId;
	private long puntoVentaId;
	
	public GeneraCuisDto(long nit, long idSistema, Integer sucursal, long modalidad, long usuario, long contribuyenteId,
			long sistemaContribuyenteId, long puntoVentaId) {
		this.nit = nit;
		this.idSistema = idSistema;
		this.sucursal = sucursal;
		this.modalidad = modalidad;
		this.usuario = usuario;
		this.contribuyenteId = contribuyenteId;
		this.sistemaContribuyenteId = sistemaContribuyenteId;
		this.puntoVentaId = puntoVentaId;
	}

	public GeneraCuisDto() {
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

	public long getUsuario() {
		return usuario;
	}

	public void setUsuario(long usuario) {
		this.usuario = usuario;
	}

	public long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public long getSistemaContribuyenteId() {
		return sistemaContribuyenteId;
	}
	
	public void setSistemaContribuyenteId(long sistemaContribuyenteId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
	}
	
	public long getPuntoVentaId() {
		return puntoVentaId;
	}

	public void setPuntoVentaId(long puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}
	

	@Override
	public String toString() {
		return "GeneraCuisDto [nit=" + nit + ", idSistema=" + idSistema + ", sucursal=" + sucursal + ", modalidad="
				+ modalidad + ", usuario=" + usuario + ", contribuyenteId=" + contribuyenteId
				+ ", sistemaContribuyenteId=" + sistemaContribuyenteId+ ", puntoVentaId=" + puntoVentaId + "]";
	}

	
}
