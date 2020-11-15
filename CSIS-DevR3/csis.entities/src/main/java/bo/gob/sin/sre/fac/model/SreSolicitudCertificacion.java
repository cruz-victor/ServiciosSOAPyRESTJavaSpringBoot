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
@Table(name = "SRE_FAC_SOLICITUDES_CERTIFICACIONES", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreSolicitudCertificacion.findAll", query = "SELECT s FROM SreSolicitudCertificacion s")
public class SreSolicitudCertificacion implements Serializable {
	// RCR
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SOLICITUD_CERTIFICACION_ID")
	private long solicitudCertificacionId;

	@Column(name = "USUARIO_REGISTRO_ID")
	private long usuarioRegistroId;

	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private long usuarioUltimaModficacionId;

	@Column(name = "TRAMITE_ID")
	private long tramiteId;

	@Column(name = "PERSONA_CONTRIBUYENTE_ID")
	private long personaContribuyenteId;

	@Column(name = "TIPO_SOLICITUD_CERTIFICACION_ID")
	private int tipoSolicitudCertificacionId;

	@Column(name = "ESTADO_SOLICITUD_CERTIFICACION_ID")
	private int estadoSolicitudCertificacionId;

	@Column(name = "CITE_INFORME_CERTIFICACION_ID")
	private Long citeInformeCertificacionId;

	@Column(name = "CITE_INFORME_INSPECCION_ID")
	private Long citeInformeInspeccionId;

	@Column(name = "CITE_CERTIFICADO_ID")
	private Long citeCertificadoId;

	@Column(name = "FECHA_INFORME_CERTIFICACION")
	private Date fechaInformeCertificacion;

	@Column(name = "FECHA_INFORME_INSPECCION")
	private Date fechaInformeInspeccion;

	@Column(name = "FECHA_CERTIFICADO")
	private Date fechaCertificado;

	@Column(name = "CITE_INFORME_CERTIFICACION")
	private String citeInformeCertificacion;

	@Column(name = "CITE_INFORME_INSPECCION")
	private String citeInformeInspeccion;

	@Column(name = "CERTIFICADO_AUTORIZACION_ID")
	private Long certificadoAutorizacionId;

	@Column(name = "CODIGO_CERTIFICADO")
	private String codigoCertificado;

	@Column(name = "CITE_CERTIFICADO")
	private String citeCertificado;

	@Column(name = "FECHA_SOLICITUD")
	private Date fechaSolicitud;

	@Column(name = "FECHA_APROBACION")
	private Date fechaAprobacion;

	@Column(name = "FECHA_CANCELACION")
	private Date fechaCancelacion;

	@Column(name = "FECHA_EMISION_CERTIFICADO")
	private Date fechaEmisionCertificado;

	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;

	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name = "ESTADO_ID")
	private String estadoId;

	@Column(name = "SISTEMA_ID")
	private Long sistemaId;

	@Column(name = "ESTADO_PRUEBA_SOLICITUD_ID")
	private int estadoPruebaSolicitudId;

	public SreSolicitudCertificacion(long solicitudCertificacionId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long tramiteId, long personaContribuyenteId,
			int tipoSolicitudCertificacionId, int estadoSolicitudCertificacionId, Long citeInformeCertificacionId,
			Long citeInformeInspeccionId, Long citeCertificadoId, Date fechaInformeCertificacion,
			Date fechaInformeInspeccion, Date fechaCertificado, String citeInformeCertificacion,
			String citeInformeInspeccion, Long certificadoAutorizacionId, String codigoCertificado,
			String citeCertificado, Date fechaSolicitud, Date fechaAprobacion, Date fechaCancelacion,
			Date fechaEmisionCertificado, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId,
			Long sistemaId, int estadoPruebaSolicitudId) {
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
		this.sistemaId = sistemaId;
		this.estadoPruebaSolicitudId = estadoPruebaSolicitudId;
	}
	
	private transient String descripcion;
	
	private transient String nombreSistema;
	
	private transient String version;

	public SreSolicitudCertificacion() {

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

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public int getEstadoPruebaSolicitudId() {
		return estadoPruebaSolicitudId;
	}

	public void setEstadoPruebaSolicitudId(int estadoPruebaSolicitudId) {
		this.estadoPruebaSolicitudId = estadoPruebaSolicitudId;
	}	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@Override
	public String toString() {
		return "SreSolicitudCertificacion [solicitudCertificacionId=" + solicitudCertificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModficacionId="
				+ usuarioUltimaModficacionId + ", tramiteId=" + tramiteId + ", personaContribuyenteId="
				+ personaContribuyenteId + ", tipoSolicitudCertificacionId=" + tipoSolicitudCertificacionId
				+ ", estadoSolicitudCertificacionId=" + estadoSolicitudCertificacionId + ", citeInformeCertificacionId="
				+ citeInformeCertificacionId + ", citeInformeInspeccionId=" + citeInformeInspeccionId
				+ ", citeCertificadoId=" + citeCertificadoId + ", fechaInformeCertificacion="
				+ fechaInformeCertificacion + ", fechaInformeInspeccion=" + fechaInformeInspeccion
				+ ", fechaCertificado=" + fechaCertificado + ", citeInformeCertificacion=" + citeInformeCertificacion
				+ ", citeInformeInspeccion=" + citeInformeInspeccion + ", certificadoAutorizacionId="
				+ certificadoAutorizacionId + ", codigoCertificado=" + codigoCertificado + ", citeCertificado="
				+ citeCertificado + ", fechaSolicitud=" + fechaSolicitud + ", fechaAprobacion=" + fechaAprobacion
				+ ", fechaCancelacion=" + fechaCancelacion + ", fechaEmisionCertificado=" + fechaEmisionCertificado
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion
				+ ", estadoId=" + estadoId + ", sistemaId=" + sistemaId + ", estadoPruebaSolicitudId="
				+ estadoPruebaSolicitudId + "]";
	}

}