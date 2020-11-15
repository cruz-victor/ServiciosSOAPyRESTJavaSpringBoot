package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SRE_FAC_CONTRIBUYENTES_MODALIDADES", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreContribuyentesModalidades.findAll", query = "SELECT s FROM SreContribuyentesModalidades s")
public class SreContribuyentesModalidades implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contribuyente_modalidad_id")
	private Long contribuyenteModalidadId;

	@Column(name = "usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name = "usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;

	@Column(name = "nit")
	private Long nit;

	@Column(name = "persona_contribuyente_id")
	private Long personaContribuyenteId;

	@Column(name = "modalidad_facturacion_id")
	private Integer modalidadFacturacionId;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
	private String estadoId;

	@Column(name = "in_situ")
	private Integer inSitu;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@Column(name = "estado_modalidad_contribuyente_id")
	private Integer estadoModalidadContribuyenteId;

	public SreContribuyentesModalidades() {
	}

	public SreContribuyentesModalidades(Long contribuyenteModalidadId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Long nit, Long personaContribuyenteId, Integer modalidadFacturacionId,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId, Integer inSitu, Date fechaInicio,
			Date fechaFin, Integer estadoModalidadContribuyenteId) {
		this.contribuyenteModalidadId = contribuyenteModalidadId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.nit = nit;
		this.personaContribuyenteId = personaContribuyenteId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.inSitu = inSitu;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estadoModalidadContribuyenteId = estadoModalidadContribuyenteId;
	}


	public Long getContribuyenteModalidadId() {
		return contribuyenteModalidadId;
	}

	public void setContribuyenteModalidadId(Long contribuyenteModalidadId) {
		this.contribuyenteModalidadId = contribuyenteModalidadId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public Long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(Long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Integer getInSitu() {
		return inSitu;
	}

	public void setInSitu(Integer inSitu) {
		this.inSitu = inSitu;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getEstadoModalidadContribuyenteId() {
		return estadoModalidadContribuyenteId;
	}

	public void setEstadoModalidadContribuyenteId(Integer estadoModalidadContribuyenteId) {
		this.estadoModalidadContribuyenteId = estadoModalidadContribuyenteId;
	}

	@Override
	public String toString() {
		return "SreContribuyentesModalidades [contribuyenteModalidadId=" + contribuyenteModalidadId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", nit=" + nit + ", personaContribuyenteId=" + personaContribuyenteId
				+ ", modalidadFacturacionId=" + modalidadFacturacionId + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + ", inSitu="
				+ inSitu + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", estadoModalidadContribuyenteId=" + estadoModalidadContribuyenteId + "]";
	}
}
