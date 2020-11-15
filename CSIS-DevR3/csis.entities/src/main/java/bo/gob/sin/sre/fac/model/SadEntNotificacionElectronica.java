package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sad_administrativo.sad_not_notificaciones_electronicas")
public class SadEntNotificacionElectronica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "notificacion_electronica_id")	
	private long notificacionElectronicaId;

	@Column(name = "usuario_registro_id")	
	private long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")	
	private long usuarioUltimaModificacionId;
	
	@Column(name = "usuario_notificador_id")	
	private long usuarioNotificadorId;
	
	@Column(name = "oficina_id")	
	private int oficinaId;
	
	@Column(name = "dependencia_id")	
	private int dependenciaId;

	@Column(name = "cod_unidad_origen")	
	private long codUnidadOrigen;
	
	@Column(name = "documento_actuado_id")	
	private long documentoActuadoId;
	
	@Column(name = "adjunto_actuado_id")	
	private long adjuntoActuadoId;

	@Column(name = "cadena_cite_actuado")
	private String cadenaCiteActuado;
	
	@Column(name = "seq_acto_administrativo")	
	private Long seqActoAdministrativo;
	
	@Column(name = "nro_acto_administrativo")
	private String nroActoAdministrativo;
	
	@Column(name = "tipo_acto_administrativo")	
	private Short tipoActoAdministrativoId;
	
	@Column(name = "documento_constancia_id")	
	private Long documentoConstanciaId;

	@Column(name = "adjunto_constancia_id")	
	private Long adjuntoConstanciaId;
	
	@Column(name = "tipo_notificacion_electronica_id")	
	private int tipoNotificacionElectronicaId;
	
	@Column(name = "estado_notificacion_electronica_id")	
	private int estadoNotificacionElectronicaId;

	@Column(name = "persona_contribuyente_id")	
	private long personaContribuyenteId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_notificacion")	
	private Date fechaNotificacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_notificacion_activa")	
	private Date fechaNotificacionActiva;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_notificacion_vencida")	
	private Date fechaNotificacionVencida;

	@Column(name = "plazo_notificacion")	
	private int plazoNotificacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")	
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")	
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
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

	public int getEstadoNotificacionElectronicaId() {
		return estadoNotificacionElectronicaId;
	}

	public void setEstadoNotificacionElectronicaId(int estadoNotificacionElectronicaId) {
		this.estadoNotificacionElectronicaId = estadoNotificacionElectronicaId;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
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

//	public SadDtoNotificacionElectronica transformarEntidadADto() {
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		return modelMapper.map(this, SadDtoNotificacionElectronica.class);
//	}
}