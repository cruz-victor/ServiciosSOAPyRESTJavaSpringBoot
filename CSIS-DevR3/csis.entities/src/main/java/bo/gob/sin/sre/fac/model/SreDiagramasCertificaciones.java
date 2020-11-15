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
@Table(name = "sre_fac_diagramas_certificaciones", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreDiagramasCertificaciones.findAll", query = "SELECT s FROM SreDiagramasCertificaciones s")
public class SreDiagramasCertificaciones implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "diagrama_certificacion_id")
	private long diagramaCertificacionId;
	
	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "sistema_id")
	private long  sistemaId;
		
	@Column(name = "solicitud_certificacion_id")
	private long solicitudCertificacionId;
	
	@Column(name = "archivo_diagrama_id")
	private long archivoDiagramaId;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;
	
	@Column(name = "etapa_id")
	private long etapaId;
	
	@Column(name = "nombre_archivo")
	private String nombreArchivo;
	
	@Column(name = "extension_archivo")
	private String extensionArchivo;	

	public SreDiagramasCertificaciones() {
	}


	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}



	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}



	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}



	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}



	public long getDiagramaCertificacionId() {
		return diagramaCertificacionId;
	}

	public void setDiagramaCertificacionId(long diagramaCertificacionId) {
		this.diagramaCertificacionId = diagramaCertificacionId;
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

	public long getArchivoDiagramaId() {
		return archivoDiagramaId;
	}

	public void setArchivoDiagramaId(long archivoDiagramaId) {
		this.archivoDiagramaId = archivoDiagramaId;
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

	public long getEtapaId() {
		return etapaId;
	}

	public void setEtapaId(long etapaId) {
		this.etapaId = etapaId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SreDiagramasCertificaciones [diagramaCertificacionId=" + diagramaCertificacionId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", sistemaId=" + sistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", archivoDiagramaId=" + archivoDiagramaId + ", fechaRegistro="
				+ fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId
				+ ", etapaId=" + etapaId + ", nombreArchivo=" + nombreArchivo + ", extensionArchivo=" + extensionArchivo
				+ "]";
	}

}
