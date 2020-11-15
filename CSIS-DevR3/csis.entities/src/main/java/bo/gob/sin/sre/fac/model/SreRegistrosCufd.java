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
@Table(name = "SRE_FAC_REGISTROS_CUFD", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreRegistrosCufd.findAll", query = "SELECT s FROM SreRegistrosCufd s")
public class SreRegistrosCufd implements Serializable {
	
	// IASC	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "CUFD_ID")
	private Long cufdId;

	@Column(name = "USUARIO_REGISTRO_ID")
	private Long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION")
	private Long usuarioUltimaModficacion;
	
	@Column(name = "SISTEMA_ID")
	private Long sistemaId;
	
	@Column(name = "NIT_EMISOR")
	private Long nitEmisor;
	
	@Column(name = "CUFD")
	private String cufd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Column(name = "SUCURSAL_ID")
	private Integer sucursalId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name = "ESTADO_ID")
	private String estadoId;
	
	@Column(name = "ESTADO_REGISTRO_CUFD_ID")
	private Integer estadoRegistroCufdId;
	
	@Column(name = "CUIS")
	private String cuis;

	public SreRegistrosCufd(Long cufdId, Long usuarioRegistroId, Long usuarioUltimaModficacion, Long sistemaId,
			Long nitEmisor, String cufd, Date fechaInicio, Date fechaFin, Integer sucursalId, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId, Integer estadoRegistroCufdId, String cuis) {
		this.cufdId = cufdId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacion = usuarioUltimaModficacion;
		this.sistemaId = sistemaId;
		this.nitEmisor = nitEmisor;
		this.cufd = cufd;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sucursalId = sucursalId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.estadoRegistroCufdId = estadoRegistroCufdId;
		this.cuis = cuis;
	}

	public SreRegistrosCufd() {

	}

	public Long getCufdId() {
		return cufdId;
	}

	public void setCufdId(Long cufdId) {
		this.cufdId = cufdId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModficacion() {
		return usuarioUltimaModficacion;
	}

	public void setUsuarioUltimaModficacion(Long usuarioUltimaModficacion) {
		this.usuarioUltimaModficacion = usuarioUltimaModficacion;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getNitEmisor() {
		return nitEmisor;
	}

	public void setNitEmisor(Long nitEmisor) {
		this.nitEmisor = nitEmisor;
	}

	public String getCufd() {
		return cufd;
	}

	public void setCufd(String cufd) {
		this.cufd = cufd;
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

	public Integer getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
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

	public Integer getEstadoRegistroCufdId() {
		return estadoRegistroCufdId;
	}

	public void setEstadoRegistroCufdId(Integer estadoRegistroCufdId) {
		this.estadoRegistroCufdId = estadoRegistroCufdId;
	}

	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}
	
	

}