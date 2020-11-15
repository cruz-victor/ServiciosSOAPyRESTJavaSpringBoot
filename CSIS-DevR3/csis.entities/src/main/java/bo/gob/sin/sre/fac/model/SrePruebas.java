package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SRE_FAC_PRUEBAS", schema="SRE_RECAUDACIONES")
public class SrePruebas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRUEBA_ID")
	private Long pruebaId;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "TIPO_PRUEBA_ID")
	private Integer tipoPruebaId;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private Long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;


	public SrePruebas() {
		
	}
	
	public SrePruebas(Long pruebaId, String nombre, String descripcion, Integer tipoPruebaId, Date fechaInicio,
			Date fechaFin, String estadoId, Long usuarioRegistroId, Long usuarioUltimaModificacionId,
			Date fechaRegistro, Date fechaUltimaModificacion) {
		super();
		this.pruebaId = pruebaId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoPruebaId = tipoPruebaId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estadoId = estadoId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Long getPruebaId() {
		return pruebaId;
	}

	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTipoPruebaId() {
		return tipoPruebaId;
	}

	public void setTipoPruebaId(Integer tipoPruebaId) {
		this.tipoPruebaId = tipoPruebaId;
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

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "SreFacEntPruebas [pruebaId=" + pruebaId + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", tipoPruebaId=" + tipoPruebaId + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", estadoId=" + estadoId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + "]";
	}
}

