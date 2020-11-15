package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SolicitudCertificacionDto implements Serializable {

	/**
	 * CRSP
	 */
	private static final long serialVersionUID = 1L;
	private long solicitudCertificacionId;
	private long usuarioRegistroId;
	private long usuarioUltimaModficacionId;
	private long tramiteId;
	private long personaContribuyenteId;
	private int tipoSolicitudCertificacionId;
	private int estadoSolicitudCertificacionId;
	private Long citeInformeCertificacionId;
	private Long citeInformeInspeccionId;
	private Long citeCertificadoId;
	private Date fechaInformeCertificacion;
	private Date fechaInformeInspeccion;
	private Date fechaCertificado;
	private String citeInformeCertificacion;
	private String citeInformeInspeccion;
	private Long sistemaId;
	private Long certificadoAutorizacionId;
	private String codigoCertificado;
	private String citeCertificado;
	private Date fechaSolicitud;
	private Date fechaAprobacion;
	private Date fechaCancelacion;
	private Date fechaEmisionCertificado;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;

	private String nombreSistema;
	private String version;
	private String descripcion;
	private String descripcionModalidad;
	private long documentoAdjuntoId;
	
	private Boolean cancelable;
	private Boolean recertificable;
	


	public SolicitudCertificacionDto(long solicitudCertificacionId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long tramiteId, long personaContribuyenteId,
			int tipoSolicitudCertificacionId, int estadoSolicitudCertificacionId, Long citeInformeCertificacionId,
			Long citeInformeInspeccionId, Long citeCertificadoId, Date fechaInformeCertificacion,
			Date fechaInformeInspeccion, Date fechaCertificado, String citeInformeCertificacion,
			String citeInformeInspeccion, Long sistemaId, Long certificadoAutorizacionId, String codigoCertificado,
			String citeCertificado, Date fechaSolicitud, Date fechaAprobacion, Date fechaCancelacion,
			Date fechaEmisionCertificado, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId,
			String nombreSistema, String version, String descripcion, String descripcionModalidad,
			long documentoAdjuntoId, Boolean cancelable, Boolean recertificable) {
		super();
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.tramiteId = tramiteId;
		this.personaContribuyenteId = personaContribuyenteId;
		this.tipoSolicitudCertificacionId = tipoSolicitudCertificacionId;
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
		this.citeInformeCertificacionId = citeInformeCertificacionId;
		this.citeInformeInspeccionId = citeInformeInspeccionId;
		this.citeCertificadoId = citeCertificadoId;
		this.fechaInformeCertificacion = fechaInformeCertificacion;
		this.fechaInformeInspeccion = fechaInformeInspeccion;
		this.fechaCertificado = fechaCertificado;
		this.citeInformeCertificacion = citeInformeCertificacion;
		this.citeInformeInspeccion = citeInformeInspeccion;
		this.sistemaId = sistemaId;
		this.certificadoAutorizacionId = certificadoAutorizacionId;
		this.codigoCertificado = codigoCertificado;
		this.citeCertificado = citeCertificado;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaCancelacion = fechaCancelacion;
		this.fechaEmisionCertificado = fechaEmisionCertificado;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.descripcion = descripcion;
		this.descripcionModalidad = descripcionModalidad;
		this.documentoAdjuntoId = documentoAdjuntoId;
		this.cancelable = cancelable;
		this.recertificable = recertificable;
	}

	public SolicitudCertificacionDto() {

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

	public long getUsuarioUltimaModficacionId() {
		return usuarioUltimaModficacionId;
	}

	public void setUsuarioUltimaModficacionId(long usuarioUltimaModficacionId) {
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public int getTipoSolicitudCertificacionId() {
		return tipoSolicitudCertificacionId;
	}

	public void setTipoSolicitudCertificacionId(int tipoSolicitudCertificacionId) {
		this.tipoSolicitudCertificacionId = tipoSolicitudCertificacionId;
	}

	public int getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}

	public void setEstadoSolicitudCertificacionId(int estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}

	public Long getCiteInformeCertificacionId() {
		return citeInformeCertificacionId;
	}

	public void setCiteInformeCertificacionId(Long citeInformeCertificacionId) {
		this.citeInformeCertificacionId = citeInformeCertificacionId;
	}

	public Long getCiteInformeInspeccionId() {
		return citeInformeInspeccionId;
	}

	public void setCiteInformeInspeccionId(Long citeInformeInspeccionId) {
		this.citeInformeInspeccionId = citeInformeInspeccionId;
	}

	public Long getCiteCertificadoId() {
		return citeCertificadoId;
	}

	public void setCiteCertificadoId(Long citeCertificadoId) {
		this.citeCertificadoId = citeCertificadoId;
	}

	public Date getFechaInformeCertificacion() {
		return fechaInformeCertificacion;
	}

	public void setFechaInformeCertificacion(Date fechaInformeCertificacion) {
		this.fechaInformeCertificacion = fechaInformeCertificacion;
	}

	public Date getFechaInformeInspeccion() {
		return fechaInformeInspeccion;
	}

	public void setFechaInformeInspeccion(Date fechaInformeInspeccion) {
		this.fechaInformeInspeccion = fechaInformeInspeccion;
	}

	public Date getFechaCertificado() {
		return fechaCertificado;
	}

	public void setFechaCertificado(Date fechaCertificado) {
		this.fechaCertificado = fechaCertificado;
	}

	public String getCiteInformeCertificacion() {
		return citeInformeCertificacion;
	}

	public void setCiteInformeCertificacion(String citeInformeCertificacion) {
		this.citeInformeCertificacion = citeInformeCertificacion;
	}

	public String getCiteInformeInspeccion() {
		return citeInformeInspeccion;
	}

	public void setCiteInformeInspeccion(String citeInformeInspeccion) {
		this.citeInformeInspeccion = citeInformeInspeccion;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getCertificadoAutorizacionId() {
		return certificadoAutorizacionId;
	}

	public void setCertificadoAutorizacionId(Long certificadoAutorizacionId) {
		this.certificadoAutorizacionId = certificadoAutorizacionId;
	}

	public String getCodigoCertificado() {
		return codigoCertificado;
	}

	public void setCodigoCertificado(String codigoCertificado) {
		this.codigoCertificado = codigoCertificado;
	}

	public String getCiteCertificado() {
		return citeCertificado;
	}

	public void setCiteCertificado(String citeCertificado) {
		this.citeCertificado = citeCertificado;
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

	public Date getFechaEmisionCertificado() {
		return fechaEmisionCertificado;
	}

	public void setFechaEmisionCertificado(Date fechaEmisionCertificado) {
		this.fechaEmisionCertificado = fechaEmisionCertificado;
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

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionModalidad() {
		return descripcionModalidad;
	}

	public void setDescripcionModalidad(String descripcionModalidad) {
		this.descripcionModalidad = descripcionModalidad;
	}

	@Override
	public String toString() {
		return "SolicitudCertificacionDto [solicitudCertificacionId=" + solicitudCertificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModficacionId="
				+ usuarioUltimaModficacionId + ", tramiteId=" + tramiteId + ", personaContribuyenteId="
				+ personaContribuyenteId + ", tipoSolicitudCertificacionId=" + tipoSolicitudCertificacionId
				+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId + ", citeInformeCertificacionId="
				+ citeInformeCertificacionId + ", citeInformeInspeccionId=" + citeInformeInspeccionId
				+ ", citeCertificadoId=" + citeCertificadoId + ", fechaInformeCertificacion="
				+ fechaInformeCertificacion + ", fechaInformeInspeccion=" + fechaInformeInspeccion
				+ ", fechaCertificado=" + fechaCertificado + ", citeInformeCertificacion=" + citeInformeCertificacion
				+ ", citeInformeInspeccion=" + citeInformeInspeccion + ", sistemaId=" + sistemaId
				+ ", certificadoAutorizacionId=" + certificadoAutorizacionId + ", codigoCertificado="
				+ codigoCertificado + ", citeCertificado=" + citeCertificado + ", fechaSolicitud=" + fechaSolicitud
				+ ", fechaAprobacion=" + fechaAprobacion + ", fechaCancelacion=" + fechaCancelacion
				+ ", fechaEmisionCertificado=" + fechaEmisionCertificado + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + ", nombreSistema="
				+ nombreSistema + ", version=" + version + ", descripcion=" + descripcion + ", descripcionModalidad="
				+ descripcionModalidad + ", documentoAdjuntoId=" + documentoAdjuntoId + ", cancelable=" + cancelable
				+ ", recertificable=" + recertificable + "]";
	}

	public long getDocumentoAdjuntoId() {
		return documentoAdjuntoId;
	}

	public void setDocumentoAdjuntoId(long documentoAdjuntoId) {
		this.documentoAdjuntoId = documentoAdjuntoId;
	}

	public Boolean getCancelable() {
		return cancelable;
	}

	public void setCancelable(Boolean cancelable) {
		this.cancelable = cancelable;
	}

	public Boolean getRecertificable() {
		return recertificable;
	}

	public void setRecertificable(Boolean recertificable) {
		this.recertificable = recertificable;
	}
}