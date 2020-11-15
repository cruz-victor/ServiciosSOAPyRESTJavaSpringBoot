package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class RegistrosObservacionesInsituDto implements Serializable  {
	
	

	
	private static final long serialVersionUID = 1L;
    private int registroObservacionInsituId;
	private long usuarioRegistroId;
	private long usuarioUltimaModificacionId;
	private long solicitudCertificacionId;
	private int estadoOservacionInsituId;
	private String observacion;
	private Date fechaVerificacion;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;
	private String descripcionUsuarioRegistro;
	private String descripcionEstadoObservacionInsitu;
	
	
	
	
	
    
    
    
    public RegistrosObservacionesInsituDto() {
    	
    	super();
    }
    public RegistrosObservacionesInsituDto(int registroObservacionInsituId,long usuarioRegistroId,long usuarioUltimaModificacionId,
			long solicitudCertificacionId,int estadoOservacionInsituId,String observacion,Date fechaVerificacion,Date fechaRegistro,
			 Date fechaUltimaModificacion, String estadoId,String descripcionUsuarioRegistro,String descripcionEstadoObservacionInsitu) {
	
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
		this.descripcionUsuarioRegistro=descripcionUsuarioRegistro;
		this.descripcionEstadoObservacionInsitu=descripcionEstadoObservacionInsitu;
		
    	
    }
	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}
	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}
	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}
	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}
	public Date getFechaVerificacion() {
		return fechaVerificacion;
	}
	public void setFechaVerificacion(Date fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public int getRegistroObservacionInsituId() {
		return registroObservacionInsituId;
	}
	public void setRegistroObservacionInsituId(int registroObservacionInsituId) {
		this.registroObservacionInsituId = registroObservacionInsituId;
	}
	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}
	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}
	public int getEstadoOservacionInsituId() {
		return estadoOservacionInsituId;
	}
	public void setEstadoOservacionInsituId(int estadoOservacionInsituId) {
		this.estadoOservacionInsituId = estadoOservacionInsituId;
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
	public String getDescripcionUsuarioRegistro() {
		return descripcionUsuarioRegistro;
	}
	public void setDescripcionUsuarioRegistro(String descripcionUsuarioRegistro) {
		this.descripcionUsuarioRegistro = descripcionUsuarioRegistro;
	}
	public String getDescripcionEstadoObservacionInsitu() {
		return descripcionEstadoObservacionInsitu;
	}
	public void setDescripcionEstadoObservacionInsitu(String descripcionEstadoObservacionInsitu) {
		this.descripcionEstadoObservacionInsitu = descripcionEstadoObservacionInsitu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	@Override
	public String toString() {
		return "RegistrosObservacionesInsituDto [usuarioRegistroId=" + usuarioRegistroId
				+ ", fechaVerificacion=" + fechaVerificacion + ", observacion="
				+ observacion + ", estadoObservacionInsituId=" + estadoOservacionInsituId + ", descripcionUsuarioRegistro="
				+ descripcionUsuarioRegistro + ", descripcionEstadoObservacionInsitu=" + descripcionEstadoObservacionInsitu
				+ ", solicitudCertificacionId="+solicitudCertificacionId
				+ "]";
      }
}