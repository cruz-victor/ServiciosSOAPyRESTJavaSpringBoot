package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class DatosTramiteSolCertificacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long sistemaId;
	private long tramiteId;
	private long solicitudCertificacionId;
	private long personaContribuyenteId;
	private int estadoSolicitudId;
	private int oficinaId;
	private Date fechaSolicitud;
	private Date fechaAprobacion;
	private Date fechaCancelacion;
	private Date fechaRechazo;
	private long documentoAdjuntodId;
	
	public DatosTramiteSolCertificacionDto() {
		
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}
	
	public int getEstadoSolicitudId() {
		return estadoSolicitudId;
	}

	public void setEstadoSolicitudId(int estadoSolicitudId) {
		this.estadoSolicitudId = estadoSolicitudId;
	}
	
	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public int getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(int oficinaId) {
		this.oficinaId = oficinaId;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	public long getDocumentoAdjuntodId() {
		return documentoAdjuntodId;
	}

	public void setDocumentoAdjuntodId(long documentoAdjuntodId) {
		this.documentoAdjuntodId = documentoAdjuntodId;
	}
}
