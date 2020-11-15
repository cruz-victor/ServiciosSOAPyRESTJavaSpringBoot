package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SRE_FAC_PRUEBAS_MANUALES", schema="SRE_RECAUDACIONES")
public class SrePruebasManuales implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRUEBA_MANUAL_ID")
	private long pruebaManualId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "TIPO_PRUEBA_ID")
	private int tipoPruebaId;
	
	@Column(name = "SUBTIPO_PRUEBA_ID")
	private int subTipoPruebaId;
	
	@Column(name = "ESTADO_PRUEBA_MANUAL_ID")
	private int estadoPruebaManualId;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "UMBRAL_ACEPTACION")
	private int umbralAceptacion;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;
	
	public SrePruebasManuales() {
		
	}

	public SrePruebasManuales(long pruebaManualId, long usuarioRegistroId, long usuarioUltimaModificacionId,
			int tipoPruebaId, int subTipoPruebaId, int estadoPruebaManualId, String nombre, String descripcion,
			int umbralAceptacion, Date fechaInicio, Date fechaFin, Date fechaRegistro, Date fechaUltimaModificacion,
			String estadoId) {
		this.pruebaManualId = pruebaManualId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.tipoPruebaId = tipoPruebaId;
		this.subTipoPruebaId = subTipoPruebaId;
		this.estadoPruebaManualId = estadoPruebaManualId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.umbralAceptacion = umbralAceptacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public long getPruebaManualId() {
		return pruebaManualId;
	}

	public void setPruebaManualId(long pruebaManualId) {
		this.pruebaManualId = pruebaManualId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public int getTipoPruebaId() {
		return tipoPruebaId;
	}

	public void setTipoPruebaId(int tipoPruebaId) {
		this.tipoPruebaId = tipoPruebaId;
	}

	public int getSubTipoPruebaId() {
		return subTipoPruebaId;
	}

	public void setSubTipoPruebaId(int subTipoPruebaId) {
		this.subTipoPruebaId = subTipoPruebaId;
	}

	public int getEstadoPruebaManualId() {
		return estadoPruebaManualId;
	}

	public void setEstadoPruebaManualId(int estadoPruebaManualId) {
		this.estadoPruebaManualId = estadoPruebaManualId;
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

	public int getUmbralAceptacion() {
		return umbralAceptacion;
	}

	public void setUmbralAceptacion(int umbralAceptacion) {
		this.umbralAceptacion = umbralAceptacion;
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
		return "SrePruebasManuales [pruebaManualId=" + pruebaManualId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", tipoPruebaId=" + tipoPruebaId
				+ ", subTipoPruebaId=" + subTipoPruebaId + ", estadoPruebaManualId=" + estadoPruebaManualId
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + ", umbralAceptacion=" + umbralAceptacion
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

}

