package bo.gob.sin.sre.fac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SRE_FAC_CLASIFICADORES", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreClasificadores.java.findAll", query = "SELECT c FROM SreClasificadores c")
public class SreClasificadores implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASIFICADOR_ID")
	private Integer clasificadorId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private Long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "CODIGO_CLASIFICADOR")
	private Integer codigoClasificador;
	
	@Column(name = "CODIGO_CLASIFICADOR_EQUIVALENTE")
	private Integer codigoClasificadorEquivalente;
			
	@Column(name = "AGRUPADOR")
	private String agrupador;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ABREVIATURA")
	private String abreviatura;
	
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;
		
	public SreClasificadores(Integer clasificadorId, Long usuarioRegistroId, Long usuarioUltimaModificacionId,
			Integer codigoClasificador, Integer codigoClasificadorEquivalente, String agrupador, String descripcion,
			String abreviatura, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		this.clasificadorId = clasificadorId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.codigoClasificador = codigoClasificador;
		this.codigoClasificadorEquivalente = codigoClasificadorEquivalente;
		this.agrupador = agrupador;
		this.descripcion = descripcion;
		this.abreviatura = abreviatura;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public SreClasificadores() {
	}

	public Integer getClasificadorId() {
		return clasificadorId;
	}

	public void setClasificadorId(Integer clasificadorId) {
		this.clasificadorId = clasificadorId;
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

	public Integer getCodigoClasificador() {
		return codigoClasificador;
	}

	public void setCodigoClasificador(Integer codigoClasificador) {
		this.codigoClasificador = codigoClasificador;
	}

	public Integer getCodigoClasificadorEquivalente() {
		return codigoClasificadorEquivalente;
	}

	public void setCodigoClasificadorEquivalente(Integer codigoClasificadorEquivalente) {
		this.codigoClasificadorEquivalente = codigoClasificadorEquivalente;
	}

	public String getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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

	@Override
	public String toString() {
		return "SreClasificadores [clasificadorId=" + clasificadorId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", codigoClasificador="
				+ codigoClasificador + ", codigoClasificadorEquivalente=" + codigoClasificadorEquivalente
				+ ", agrupador=" + agrupador + ", descripcion=" + descripcion + ", abreviatura=" + abreviatura
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion
				+ ", estadoId=" + estadoId + "]";
	}

}
