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

@Entity
@Table(name = "SRE_FAC_OBSERVACIONES_INSITU", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreFacObservacionesInsitu.findAll", query = "SELECT s FROM SreFacObservacionesInsitu s")
public class SreFacObservacionesInsitu  implements Serializable  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "observacion_insitu_id")
	private int observacionInsituId;
	
	
	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "observacion")
	private String observacion;
	
	@Column(name = "estado_observacion_insitu_id")
	private int estadoOservacionInsituId;
	
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;
	
	@Column(name="tipo_esquema_id")
	private Integer tipoEsquemaId;
	
	@Column(name="tipo_obligatorio_id")
	private Integer tipoObligatorioId;
	
	
	public SreFacObservacionesInsitu() {
		
	}


	public SreFacObservacionesInsitu(int observacionInsituId, long usuarioRegistroId, long usuarioUltimaModificacionId,
			String observacion, int estadoOservacionInsituId, Date fechaInicio, Date fechaFin, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId, Integer tipoEsquemaId, Integer tipoObligatorioId) {
		super();
		this.observacionInsituId = observacionInsituId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.observacion = observacion;
		this.estadoOservacionInsituId = estadoOservacionInsituId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.tipoEsquemaId = tipoEsquemaId;
		this.tipoObligatorioId = tipoObligatorioId;
	}


	/**
	 * @return the observacionInsituId
	 */
	public int getObservacionInsituId() {
		return observacionInsituId;
	}


	/**
	 * @param observacionInsituId the observacionInsituId to set
	 */
	public void setObservacionInsituId(int observacionInsituId) {
		this.observacionInsituId = observacionInsituId;
	}


	/**
	 * @return the usuarioRegistroId
	 */
	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}


	/**
	 * @param usuarioRegistroId the usuarioRegistroId to set
	 */
	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}


	/**
	 * @return the usuarioUltimaModificacionId
	 */
	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}


	/**
	 * @param usuarioUltimaModificacionId the usuarioUltimaModificacionId to set
	 */
	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}


	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}


	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	/**
	 * @return the estadoOservacionInsituId
	 */
	public int getEstadoOservacionInsituId() {
		return estadoOservacionInsituId;
	}


	/**
	 * @param estadoOservacionInsituId the estadoOservacionInsituId to set
	 */
	public void setEstadoOservacionInsituId(int estadoOservacionInsituId) {
		this.estadoOservacionInsituId = estadoOservacionInsituId;
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
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	/**
	 * @return the fechaUltimaModificacion
	 */
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}


	/**
	 * @param fechaUltimaModificacion the fechaUltimaModificacion to set
	 */
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}


	/**
	 * @return the estadoId
	 */
	public String getEstadoId() {
		return estadoId;
	}


	/**
	 * @param estadoId the estadoId to set
	 */
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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
}
