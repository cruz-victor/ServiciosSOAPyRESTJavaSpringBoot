package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ContribuyentesModalidadesDto extends ListaMensajesAplicacion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contribuyenteModalidadId;
	private Long nit;
	private Long personaContribuyenteId;
	private Integer modalidadFacturacionId;
	private Integer inSitu;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer estadoModalidadContribuyenteId;	
	private Long datosEntradaNit;
	
	public ContribuyentesModalidadesDto()
	{
		
	}
	
	public ContribuyentesModalidadesDto(Long contribuyenteModalidadId, Long usuarioRegistroId,
			Long nit, Long personaContribuyenteId, Integer modalidadFacturacionId,
			Integer inSitu, Date fechaInicio,
			Date fechaFin, Integer estadoModalidadContribuyenteId, Long datosEntradaNit) 
	{
		super();
		this.contribuyenteModalidadId = contribuyenteModalidadId;
		this.nit = nit;
		this.personaContribuyenteId = personaContribuyenteId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.inSitu = inSitu;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estadoModalidadContribuyenteId = estadoModalidadContribuyenteId;
		this.datosEntradaNit = datosEntradaNit; 
	}
	/**
	 * @return the contribuyenteModalidadId
	 */
	public Long getContribuyenteModalidadId() {
		return contribuyenteModalidadId;
	}
	/**
	 * @param contribuyenteModalidadId the contribuyenteModalidadId to set
	 */
	public void setContribuyenteModalidadId(Long contribuyenteModalidadId) {
		this.contribuyenteModalidadId = contribuyenteModalidadId;
	}

	/**
	 * @return the nit
	 */
	public Long getNit() {
		return nit;
	}
	/**
	 * @param nit the nit to set
	 */
	public void setNit(Long nit) {
		this.nit = nit;
	}
	/**
	 * @return the personaContribuyenteId
	 */
	public Long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}
	/**
	 * @param personaContribuyenteId the personaContribuyenteId to set
	 */
	public void setPersonaContribuyenteId(Long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}
	/**
	 * @return the modalidadFacturacionId
	 */
	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}
	/**
	 * @param modalidadFacturacionId the modalidadFacturacionId to set
	 */
	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	/**
	 * @return the inSitu
	 */
	public Integer getInSitu() {
		return inSitu;
	}
	/**
	 * @param inSitu the inSitu to set
	 */
	public void setInSitu(Integer inSitu) {
		this.inSitu = inSitu;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the estadoModalidadContribuyenteId
	 */
	public Integer getEstadoModalidadContribuyenteId() {
		return estadoModalidadContribuyenteId;
	}
	/**
	 * @param estadoModalidadContribuyenteId the estadoModalidadContribuyenteId to set
	 */
	public void setEstadoModalidadContribuyenteId(Integer estadoModalidadContribuyenteId) {
		this.estadoModalidadContribuyenteId = estadoModalidadContribuyenteId;
	}

	/**
	 * @return the datosEntradaNit
	 */
	public Long getDatosEntradaNit() {
		return datosEntradaNit;
	}

	/**
	 * @param datosEntradaNit the datosEntradaNit to set
	 */
	public void setDatosEntradaNit(Long datosEntradaNit) {
		this.datosEntradaNit = datosEntradaNit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContribuyentesModalidadesDto [contribuyenteModalidadId=" + contribuyenteModalidadId + ", nit=" + nit
				+ ", personaContribuyenteId=" + personaContribuyenteId + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", inSitu=" + inSitu + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", estadoModalidadContribuyenteId=" + estadoModalidadContribuyenteId + ", datosEntradaNit="
				+ datosEntradaNit + "]";
	}
}
