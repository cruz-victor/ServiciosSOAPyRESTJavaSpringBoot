package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class DiagramasCertificacionesDto extends ListaMensajesAplicacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long diagramaCertificacionId;	
	private Long usuarioRegistroId;	
	private Long usuarioUltimaModificacionId;	
	private Long  sistemaId;			
	private Long solicitudCertificacionId;
	private Long archivoDiagramaId;	
	private Date fechaRegistro;	
	private Date fechaUltimaModificacion;	
	private String nombreArchivo;	
	private String estadoId;
	private Long etapaId;
	private String extensionArchivo;
	private byte[] archivo;
	
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

	public Long getDiagramaCertificacionId() {
		return diagramaCertificacionId;
	}

	public void setDiagramaCertificacionId(Long diagramaCertificacionId) {
		this.diagramaCertificacionId = diagramaCertificacionId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Long getArchivoDiagramaId() {
		return archivoDiagramaId;
	}

	public void setArchivoDiagramaId(Long archivoDiagramaId) {
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
	 * @return the etapaId
	 */
	public Long getEtapaId() {
		return etapaId;
	}

	/**
	 * @return the archivo
	 */
	public byte[] getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	/**
	 * @param etapaId the etapaId to set
	 */
	public void setEtapaId(Long etapaId) {
		this.etapaId = etapaId;
	}
}
