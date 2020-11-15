package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificacionElectronicaDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long notificacionElectronicaId;

	private long usuarioRegistroId;

	private long usuarioUltimaModificacionId;

	private long usuarioNotificadorId;

	private String usuarioNotificador;

	private int oficinaId;

	private int dependenciaId;

	private long codUnidadOrigen;

	private long documentoActuadoId;

	private long adjuntoActuadoId;

	private String cadenaCiteActuado;

	private Long seqActoAdministrativo;

	private String nroActoAdministrativo;

	private Short tipoActoAdministrativoId;

	private Long documentoConstanciaId;

	private Long adjuntoConstanciaId;

	private int tipoNotificacionElectronicaId;

	private String tipoNotificacionElectronicaDescripcion;

	private int estadoNotificacionElectronicaId;

	private String estadoNotificacionElectronicaDescripcion;

	private long personaContribuyenteId;

	private long nit;

	private Date fechaNotificacion;

	private Date fechaNotificacionActiva;

	private Date fechaNotificacionVencida;

	private int plazoNotificacion;

	private Date fechaRegistro;

	private Date fechaUltimaModificacion;

	private String estadoId;

	public long getNotificacionElectronicaId() {
		return notificacionElectronicaId;
	}

	public void setNotificacionElectronicaId(long notificacionElectronicaId) {
		this.notificacionElectronicaId = notificacionElectronicaId;
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

	public long getUsuarioNotificadorId() {
		return usuarioNotificadorId;
	}

	public void setUsuarioNotificadorId(long usuarioNotificadorId) {
		this.usuarioNotificadorId = usuarioNotificadorId;
	}

	public int getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(int oficinaId) {
		this.oficinaId = oficinaId;
	}

	public int getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(int dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public long getCodUnidadOrigen() {
		return codUnidadOrigen;
	}

	public void setCodUnidadOrigen(long codUnidadOrigen) {
		this.codUnidadOrigen = codUnidadOrigen;
	}

	public long getDocumentoActuadoId() {
		return documentoActuadoId;
	}

	public void setDocumentoActuadoId(long documentoActuadoId) {
		this.documentoActuadoId = documentoActuadoId;
	}

	public long getAdjuntoActuadoId() {
		return adjuntoActuadoId;
	}

	public void setAdjuntoActuadoId(long adjuntoActuadoId) {
		this.adjuntoActuadoId = adjuntoActuadoId;
	}

	public String getCadenaCiteActuado() {
		return cadenaCiteActuado;
	}

	public void setCadenaCiteActuado(String cadenaCiteActuado) {
		this.cadenaCiteActuado = cadenaCiteActuado;
	}

	public Long getSeqActoAdministrativo() {
		return seqActoAdministrativo;
	}

	public void setSeqActoAdministrativo(Long seqActoAdministrativo) {
		this.seqActoAdministrativo = seqActoAdministrativo;
	}

	public String getNroActoAdministrativo() {
		return nroActoAdministrativo;
	}

	public void setNroActoAdministrativo(String nroActoAdministrativo) {
		this.nroActoAdministrativo = nroActoAdministrativo;
	}

	public Short getTipoActoAdministrativoId() {
		return tipoActoAdministrativoId;
	}

	public void setTipoActoAdministrativoId(Short tipoActoAdministrativoId) {
		this.tipoActoAdministrativoId = tipoActoAdministrativoId;
	}

	public Long getDocumentoConstanciaId() {
		return documentoConstanciaId;
	}

	public void setDocumentoConstanciaId(Long documentoConstanciaId) {
		this.documentoConstanciaId = documentoConstanciaId;
	}

	public Long getAdjuntoConstanciaId() {
		return adjuntoConstanciaId;
	}

	public void setAdjuntoConstanciaId(Long adjuntoConstanciaId) {
		this.adjuntoConstanciaId = adjuntoConstanciaId;
	}

	public int getTipoNotificacionElectronicaId() {
		return tipoNotificacionElectronicaId;
	}

	public void setTipoNotificacionElectronicaId(int tipoNotificacionElectronicaId) {
		this.tipoNotificacionElectronicaId = tipoNotificacionElectronicaId;
	}

	public String getTipoNotificacionElectronicaDescripcion() {
		return tipoNotificacionElectronicaDescripcion;
	}

	public void setTipoNotificacionElectronicaDescripcion(String tipoNotificacionElectronicaDescripcion) {
		this.tipoNotificacionElectronicaDescripcion = tipoNotificacionElectronicaDescripcion;
	}

	public int getEstadoNotificacionElectronicaId() {
		return estadoNotificacionElectronicaId;
	}

	public void setEstadoNotificacionElectronicaId(int estadoNotificacionElectronicaId) {
		this.estadoNotificacionElectronicaId = estadoNotificacionElectronicaId;
	}

	public String getEstadoNotificacionElectronicaDescripcion() {
		return estadoNotificacionElectronicaDescripcion;
	}

	public void setEstadoNotificacionElectronicaDescripcion(String estadoNotificacionElectronicaDescripcion) {
		this.estadoNotificacionElectronicaDescripcion = estadoNotificacionElectronicaDescripcion;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public Date getFechaNotificacionActiva() {
		return fechaNotificacionActiva;
	}

	public void setFechaNotificacionActiva(Date fechaNotificacionActiva) {
		this.fechaNotificacionActiva = fechaNotificacionActiva;
	}

	public Date getFechaNotificacionVencida() {
		return fechaNotificacionVencida;
	}

	public void setFechaNotificacionVencida(Date fechaNotificacionVencida) {
		this.fechaNotificacionVencida = fechaNotificacionVencida;
	}

	public int getPlazoNotificacion() {
		return plazoNotificacion;
	}

	public void setPlazoNotificacion(int plazoNotificacion) {
		this.plazoNotificacion = plazoNotificacion;
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

	public String getUsuarioNotificador() {
		return usuarioNotificador;
	}

	public void setUsuarioNotificador(String usuarioNotificador) {
		this.usuarioNotificador = usuarioNotificador;
	}

	public NotificacionElectronicaDto() {
		super();
	}

	public NotificacionElectronicaDto(long notificacionElectronicaId, long usuarioRegistroId,
			long usuarioUltimaModificacionId, long usuarioNotificadorId, String usuarioNotificador, int oficinaId,
			int dependenciaId, long codUnidadOrigen, long documentoActuadoId, long adjuntoActuadoId,
			String cadenaCiteActuado, Long seqActoAdministrativo, String nroActoAdministrativo,
			Short tipoActoAdministrativoId, Long documentoConstanciaId, Long adjuntoConstanciaId,
			int tipoNotificacionElectronicaId, String tipoNotificacionElectronicaDescripcion,
			int estadoNotificacionElectronicaId, String estadoNotificacionElectronicaDescripcion,
			long personaContribuyenteId, long nit, Date fechaNotificacion, Date fechaNotificacionActiva,
			Date fechaNotificacionVencida, int plazoNotificacion, Date fechaRegistro, Date fechaUltimaModificacion,
			String estadoId) {
		super();
		this.notificacionElectronicaId = notificacionElectronicaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.usuarioNotificadorId = usuarioNotificadorId;
		this.usuarioNotificador = usuarioNotificador;
		this.oficinaId = oficinaId;
		this.dependenciaId = dependenciaId;
		this.codUnidadOrigen = codUnidadOrigen;
		this.documentoActuadoId = documentoActuadoId;
		this.adjuntoActuadoId = adjuntoActuadoId;
		this.cadenaCiteActuado = cadenaCiteActuado;
		this.seqActoAdministrativo = seqActoAdministrativo;
		this.nroActoAdministrativo = nroActoAdministrativo;
		this.tipoActoAdministrativoId = tipoActoAdministrativoId;
		this.documentoConstanciaId = documentoConstanciaId;
		this.adjuntoConstanciaId = adjuntoConstanciaId;
		this.tipoNotificacionElectronicaId = tipoNotificacionElectronicaId;
		this.tipoNotificacionElectronicaDescripcion = tipoNotificacionElectronicaDescripcion;
		this.estadoNotificacionElectronicaId = estadoNotificacionElectronicaId;
		this.estadoNotificacionElectronicaDescripcion = estadoNotificacionElectronicaDescripcion;
		this.personaContribuyenteId = personaContribuyenteId;
		this.nit = nit;
		this.fechaNotificacion = fechaNotificacion;
		this.fechaNotificacionActiva = fechaNotificacionActiva;
		this.fechaNotificacionVencida = fechaNotificacionVencida;
		this.plazoNotificacion = plazoNotificacion;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "NotificacionElectronicaDto [notificacionElectronicaId=" + notificacionElectronicaId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", usuarioNotificadorId=" + usuarioNotificadorId
				+ ", usuarioNotificador=" + usuarioNotificador + ", oficinaId=" + oficinaId + ", dependenciaId="
				+ dependenciaId + ", codUnidadOrigen=" + codUnidadOrigen + ", documentoActuadoId=" + documentoActuadoId
				+ ", adjuntoActuadoId=" + adjuntoActuadoId + ", cadenaCiteActuado=" + cadenaCiteActuado
				+ ", seqActoAdministrativo=" + seqActoAdministrativo + ", nroActoAdministrativo="
				+ nroActoAdministrativo + ", tipoActoAdministrativoId=" + tipoActoAdministrativoId
				+ ", documentoConstanciaId=" + documentoConstanciaId + ", adjuntoConstanciaId=" + adjuntoConstanciaId
				+ ", tipoNotificacionElectronicaId=" + tipoNotificacionElectronicaId
				+ ", tipoNotificacionElectronicaDescripcion=" + tipoNotificacionElectronicaDescripcion
				+ ", estadoNotificacionElectronicaId=" + estadoNotificacionElectronicaId
				+ ", estadoNotificacionElectronicaDescripcion=" + estadoNotificacionElectronicaDescripcion
				+ ", personaContribuyenteId=" + personaContribuyenteId + ", nit=" + nit + ", fechaNotificacion="
				+ fechaNotificacion + ", fechaNotificacionActiva=" + fechaNotificacionActiva
				+ ", fechaNotificacionVencida=" + fechaNotificacionVencida + ", plazoNotificacion=" + plazoNotificacion
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion
				+ ", estadoId=" + estadoId + "]";
	}

}
