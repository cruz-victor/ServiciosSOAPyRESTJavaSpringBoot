package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sre_fac_registros_observaciones_insitu", schema = "sre_recaudaciones")
public class SreRegistroVerificacionPruebaInsitu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registro_observacion_insitu_id")
	private Long registroObservacionInsituId;

	@Column(name = "solicitud_certificacion_id")
	private Long solicitudCertificacionId;

	@Column(name = "estado_prueba_id")
	private Integer estadoPruebaId;

	@Column(name = "observacion")
	private String observacion;

	@Column(name = "observacion_insitu_id")
	private Long observacionInsituId;

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
	
	@Column(name = "tipo_obligatorio_id")
	private Integer tipoObligatorioId;
	
	@Column(name="tipo_esquema_id")
	private Integer tipoEsquemaId;
	
	private transient String estadoPruebaDescripcion;
	
	private transient String descripcionPrueba;

	public SreRegistroVerificacionPruebaInsitu() {

	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Integer getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(Integer estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long getObservacionInsituId() {
		return observacionInsituId;
	}

	public void setObservacionInsituId(Long observacionInsituId) {
		this.observacionInsituId = observacionInsituId;
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

	public Long getRegistroObservacionInsituId() {
		return registroObservacionInsituId;
	}

	public void setRegistroObservacionInsituId(Long registroObservacionInsituId) {
		this.registroObservacionInsituId = registroObservacionInsituId;
	}

	/**
	 * @return the tipoObligatorioId
	 */
	public Integer getTipoObligatorioId() {
		return tipoObligatorioId;
	}

	/**
	 * @param tipoObligatorioId the tipoObligatorioId to set
	 */
	public void setTipoObligatorioId(Integer tipoObligatorioId) {
		this.tipoObligatorioId = tipoObligatorioId;
	}

	/**
	 * @return the tipoEsquemaId
	 */
	public Integer getTipoEsquemaId() {
		return tipoEsquemaId;
	}

	/**
	 * @param tipoEsquemaId the tipoEsquemaId to set
	 */
	public void setTipoEsquemaId(Integer tipoEsquemaId) {
		this.tipoEsquemaId = tipoEsquemaId;
	}

	/**
	 * @return the estadoPruebaDescripcion
	 */
	public String getEstadoPruebaDescripcion() {
		return estadoPruebaDescripcion;
	}

	/**
	 * @param estadoPruebaDescripcion the estadoPruebaDescripcion to set
	 */
	public void setEstadoPruebaDescripcion(String estadoPruebaDescripcion) {
		this.estadoPruebaDescripcion = estadoPruebaDescripcion;
	}

	/**
	 * @return the descripcionPrueba
	 */
	public String getDescripcionPrueba() {
		return descripcionPrueba;
	}

	/**
	 * @param descripcionPrueba the descripcionPrueba to set
	 */
	public void setDescripcionPrueba(String descripcionPrueba) {
		this.descripcionPrueba = descripcionPrueba;
	}
}