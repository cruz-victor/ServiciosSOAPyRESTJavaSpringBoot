package bo.gob.sin.sre.fac.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sre_fac_componentes_archivos", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SreComponentesArchivos.findAll", query="SELECT s FROM SreComponentesArchivos s")
public class SreComponentesArchivos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="componente_archivo_id")	
	private Long componenteArchivoId;

	@Column(name="archivo_id")
	private Long archivoId;

	@Column(name="crc")
	private String crc;

	@Column(name="estado_id")
	private String estadoId;

	@Column(name="extension")
	private String extension;

	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name="md5")
	private String md5;

	@Column(name="mime")
	private String mime;

	@Column(name="nombre")
	private String nombre;

	@Column(name="ruta")
	private String ruta;

	@Column(name="sha2")
	private String sha2;

	@Column(name="usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name="usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;

	public SreComponentesArchivos() {
	}

	public Long getComponenteArchivoId() {
		return this.componenteArchivoId;
	}

	public void setComponenteArchivoId(Long componenteArchivoId) {
		this.componenteArchivoId = componenteArchivoId;
	}

	public Long getArchivoId() {
		return this.archivoId;
	}

	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}

	public String getCrc() {
		return this.crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUltimaModificacion() {
		return this.fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMime() {
		return this.mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getSha2() {
		return this.sha2;
	}

	public void setSha2(String sha2) {
		this.sha2 = sha2;
	}

	public Long getUsuarioRegistroId() {
		return this.usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return this.usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}
}
