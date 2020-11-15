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
@Table(name = "SRE_FAC_SISTEMAS", schema = "SRE_RECAUDACIONES")
public class SreSistemas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sistema_id")
	private long sistemaId;

	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;

	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;

	@Column(name = "tramite_id")
	private long tramiteId;

	@Column(name = "tipo_departamento_id")
	private int tipoDepartamentoId;

	@Column(name = "tipo_sistema_id")
	private int tipoSistemaId;

	@Column(name = "estado_sistema_id")
	private int estadoSistemaId;

	@Column(name = "nombre_sistema")
	private String nombreSistema;

	@Column(name = "version")
	private String version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name = "estado_id")
	private String estadoId;
	
	@Column(name = "codigo_sistema")
	private String codigoSistema;

	public SreSistemas(long sistemaId, long usuarioUltimaModificacionId, long usuarioRegistroId, long tramiteId,
			int tipoDepartamentoId, int tipoSistemaId, int estadoSistemaId, String nombreSistema, String version,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId, String codigoSistema) {
		super();
		this.sistemaId = sistemaId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.tramiteId = tramiteId;
		this.tipoDepartamentoId = tipoDepartamentoId;
		this.tipoSistemaId = tipoSistemaId;
		this.estadoSistemaId = estadoSistemaId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.codigoSistema = codigoSistema;
	}

	public SreSistemas() {
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public int getTipoDepartamentoId() {
		return tipoDepartamentoId;
	}

	public void setTipoDepartamentoId(int tipoDepartamentoId) {
		this.tipoDepartamentoId = tipoDepartamentoId;
	}

	public int getTipoSistemaId() {
		return tipoSistemaId;
	}

	public void setTipoSistemaId(int tipoSistemaId) {
		this.tipoSistemaId = tipoSistemaId;
	}

	public int getEstadoSistemaId() {
		return estadoSistemaId;
	}

	public void setEstadoSistemaId(int estadoSistemaId) {
		this.estadoSistemaId = estadoSistemaId;
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

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	@Override
	public String toString() {
		return "SreSistemas [sistemaId=" + sistemaId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", tramiteId=" + tramiteId + ", tipoDepartamentoId="
				+ tipoDepartamentoId + ", tipoSistemaId=" + tipoSistemaId + ", estadoSistemaId=" + estadoSistemaId
				+ ", nombreSistema=" + nombreSistema + ", version=" + version + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + ", codigoSistema="
				+ codigoSistema + "]";
	}
	
}