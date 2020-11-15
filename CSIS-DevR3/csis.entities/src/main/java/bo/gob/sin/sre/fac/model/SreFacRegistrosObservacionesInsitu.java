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
@Table(name = "SRE_FAC_REGISTROS_OBSERVACIONES_INSITU", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreFacRegistrosObservacionesInsitu.findAll", query = "SELECT s FROM SreFacRegistrosObservacionesInsitu s")
public class SreFacRegistrosObservacionesInsitu  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "registro_observacion_insitu_id")
	private int registroObservacionInsituId;
	
	
	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;
	
	
	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "solicitud_certificacion_id")
	private long solicitudCertificacionId;
	
	@Column(name = "estado_observacion_insitu_id")
	private int estadoOservacionInsituId;
	
	@Column(name = "observacion")
	private String observacion;
	
	@Column(name = "fecha_verificacion")
	private Date fechaVerificacion;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;

	
	public SreFacRegistrosObservacionesInsitu() {
		
	}

	public SreFacRegistrosObservacionesInsitu(int registroObservacionInsituId,long usuarioRegistroId,long usuarioUltimaModificacionId,
			long solicitudCertificacionId,int estadoOservacionInsituId,String observacion,Date fechaVerificacion,Date fechaRegistro,
			 Date fechaUltimaModificacion, String estadoId) {
	
		this.registroObservacionInsituId=registroObservacionInsituId;
		this.usuarioRegistroId=usuarioRegistroId;
		this.usuarioUltimaModificacionId=usuarioUltimaModificacionId;
		this.solicitudCertificacionId=solicitudCertificacionId;
		this.estadoOservacionInsituId=estadoOservacionInsituId;
		this.observacion=observacion;
		this.fechaVerificacion=fechaVerificacion;
		this.fechaRegistro=fechaRegistro;
		this.fechaUltimaModificacion=fechaUltimaModificacion;
		this.estadoId=estadoId;
		
		
		
		
	}
	
	public int getRegistroObservacionInsituId() {
		return registroObservacionInsituId;
	}

	public void setRegistroObservacionInsituId(int registroObservacionInsituId) {
		this.registroObservacionInsituId = registroObservacionInsituId;
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

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public int getEstadoOservacionInsituId() {
		return estadoOservacionInsituId;
	}

	public void setEstadoOservacionInsituId(int estadoOservacionInsituId) {
		this.estadoOservacionInsituId = estadoOservacionInsituId;
	}



	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaVerificacion() {
		return fechaVerificacion;
	}

	public void setFechaVerificacion(Date fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
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
		return "SreFacRegistrosObservacionesInsitu [registroObservacionInsituId="
				+ registroObservacionInsituId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", estadoOservacionInsituId=" + estadoOservacionInsituId + ", observacion="
				+ observacion + ", fechaVerificacion=" + fechaVerificacion + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	
	
	
}
