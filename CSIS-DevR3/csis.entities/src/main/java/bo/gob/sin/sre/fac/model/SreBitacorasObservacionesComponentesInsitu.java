package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "sre_fac_bitacoras_observaciones_componentes_insitu", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreBitacorasObservacionesComponentesInsitu.findAll", query = "SELECT s FROM SreBitacorasObservacionesComponentesInsitu s")
public class SreBitacorasObservacionesComponentesInsitu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bitacora_observacion_componente_insitu_id")
	private long bitacoraObservacionComponenteInsituId;

	@Column(name = "observacion_componente_insitu_id")
	private long observacionComponenteInsituId;

	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;

	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_prueba_id")
	private long estadoPruebaId;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "estado_id")
	private String estadoId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "observacion_componente_insitu_id", nullable = false, insertable=false, updatable=false)
	@JsonBackReference("observaciones-bitacora")
	private SreObservacionesComponentesInsitu observacionComponentesInsitu;

	public SreBitacorasObservacionesComponentesInsitu() {
		super();
	}

	public SreBitacorasObservacionesComponentesInsitu(long bitacoraObservacionComponenteInsituId,
			long observacionComponenteInsituId, long usuarioRegistroId,
			long usuarioUltimaModificacionId, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		super();
		this.bitacoraObservacionComponenteInsituId = bitacoraObservacionComponenteInsituId;
		this.observacionComponenteInsituId = observacionComponenteInsituId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public long getBitacoraObservacionComponenteInsituId() {
		return bitacoraObservacionComponenteInsituId;
	}

	public void setBitacoraObservacionComponenteInsituId(long bitacoraObservacionComponenteInsituId) {
		this.bitacoraObservacionComponenteInsituId = bitacoraObservacionComponenteInsituId;
	}

	public long getObservacionComponenteInsituId() {
		return observacionComponenteInsituId;
	}

	public void setObservacionComponenteInsituId(long observacionComponenteInsituId) {
		this.observacionComponenteInsituId = observacionComponenteInsituId;
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

	public SreObservacionesComponentesInsitu getObservacionComponentesInsitu() {
		return observacionComponentesInsitu;
	}

	public void setObservacionComponentesInsitu(SreObservacionesComponentesInsitu observacionComponentesInsitu) {
		this.observacionComponentesInsitu = observacionComponentesInsitu;
	}

	public long getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(long estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "SreBitacorasObservacionesComponentesInsitu [observacionComponenteInsituId="
				+ observacionComponenteInsituId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", bitacoraObservacionComponenteInsituId="
				+ bitacoraObservacionComponenteInsituId + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

}
