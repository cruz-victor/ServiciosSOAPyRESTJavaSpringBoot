package bo.gob.sin.sre.fac.model;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "sre_fac_log_0_cuf", schema = "sre_recaudaciones")
public class SreFacLogCuf implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "log_id")
	private long logId;
	
	@Column(name = "sistema_id")
	private long sistemaId;
	
	@Column(name = "hash")
	private String hash;
	
	@Column(name = "hash_descripcion")
	private String hashDescripcion;
	
	@Column(name = "estado_match")
	private int estadoMatch;
	
	@Column(name = "caso_prueba_id")
	private Long casoPruebaId;
	
	@Column(name = "documento_fiscal_id")
	private int documentoFiscalId;
	
	@Column(name = "documento_sector_id")
	private int documentoSectorId;
	
	@Column(name = "fecha_prueba")
	private Timestamp fechaPrueba;
	
	@Column(name = "id_recepcion")
	private long idRecepcion;
	
	@Column(name = "estado_id")
	private String estadoId;
	
	@Column(name = "fecha_registro")
	private Timestamp fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Timestamp fechaUltimaModificacion;
	
	@Column(name = "usuario_registro_id")
	private long ususarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion")
	private long usuarioUltimaModificacion;

	
	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHashDescripcion() {
		return hashDescripcion;
	}

	public void setHashDescripcion(String hashDescripcion) {
		this.hashDescripcion = hashDescripcion;
	}

	public int getEstadoMatch() {
		return estadoMatch;
	}

	public void setEstadoMatch(int estadoMatch) {
		this.estadoMatch = estadoMatch;
	}

	public Long getCasoPruebaId() {
		return casoPruebaId;
	}

	public void setCasoPruebaId(Long casoPruebaId) {
		this.casoPruebaId = casoPruebaId;
	}

	public int getDocumentoFiscalId() {
		return documentoFiscalId;
	}

	public void setDocumentoFiscalId(int documentoFiscalId) {
		this.documentoFiscalId = documentoFiscalId;
	}

	public int getDocumentoSectorId() {
		return documentoSectorId;
	}

	public void setDocumentoSectorId(int documentoSectorId) {
		this.documentoSectorId = documentoSectorId;
	}

	public Timestamp getFechaPrueba() {
		return fechaPrueba;
	}

	public void setFechaPrueba(Timestamp fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
	}

	public long getIdRecepcion() {
		return idRecepcion;
	}

	public void setIdRecepcion(long idRecepcion) {
		this.idRecepcion = idRecepcion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Timestamp getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Timestamp fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public long getUsusarioRegistroId() {
		return ususarioRegistroId;
	}

	public void setUsusarioRegistroId(long ususarioRegistroId) {
		this.ususarioRegistroId = ususarioRegistroId;
	}

	public long getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(long usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}
	
}
