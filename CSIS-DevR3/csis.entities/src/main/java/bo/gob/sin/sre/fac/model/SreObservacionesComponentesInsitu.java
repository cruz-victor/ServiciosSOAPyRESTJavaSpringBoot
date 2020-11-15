package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sre_fac_observaciones_componentes_insitu", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreObservacionesComponentesInsitu.findAll", query = "SELECT s FROM SreObservacionesComponentesInsitu s")
public class SreObservacionesComponentesInsitu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "observacion_componente_insitu_id")
	private long observacionComponenteInsituId;

	@Column(name = "solicitud_certificacion_id")
	private long solicitudCertificacionId;

	@Column(name = "estado_prueba_id")
	private long estadoPruebaId;

	@Column(name = "tipo_componente_id")
	private long tipoComponenteId;

	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;

	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
	private String estadoId;

	@OneToMany(mappedBy = "observacionComponentesInsitu", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JsonManagedReference("observaciones-bitacora")
	@XmlTransient
	private List<SreBitacorasObservacionesComponentesInsitu> bitacorasObservacionesComponentesInsitu = new ArrayList<>();

	
	public SreObservacionesComponentesInsitu() {
		super();
	}

	public SreObservacionesComponentesInsitu(long observacionComponenteInsituId, long solicitudCertificacionId,
			long estadoPruebaId, long tipoComponenteId, long usuarioRegistroId, long usuarioUltimaModificacionId,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		super();
		this.observacionComponenteInsituId = observacionComponenteInsituId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.estadoPruebaId = estadoPruebaId;
		this.tipoComponenteId = tipoComponenteId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public List<SreBitacorasObservacionesComponentesInsitu> getBitacorasObservacionesComponentesInsitu() {
		return bitacorasObservacionesComponentesInsitu;
	}

	public void setBitacorasObservacionesComponentesInsitu(
			List<SreBitacorasObservacionesComponentesInsitu> bitacorasObservacionesComponentesInsitu) {
		this.bitacorasObservacionesComponentesInsitu = bitacorasObservacionesComponentesInsitu;
	}

	public long getObservacionComponenteInsituId() {
		return observacionComponenteInsituId;
	}

	public void setObservacionComponenteInsituId(long observacionComponenteInsituId) {
		this.observacionComponenteInsituId = observacionComponenteInsituId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(long estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public long getTipoComponenteId() {
		return tipoComponenteId;
	}

	public void setTipoComponenteId(long tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
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

	@Override
	public String toString() {
		return "SreObservacionesComponentesInsitu [observacionComponenteInsituId=" + observacionComponenteInsituId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", solicitudCertificacionId=" + solicitudCertificacionId
				+ ", estadoPruebaId=" + estadoPruebaId + ", tipoComponenteId=" + tipoComponenteId + ", fechaRegistro="
				+ fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId
				+ "]";
	}
}
