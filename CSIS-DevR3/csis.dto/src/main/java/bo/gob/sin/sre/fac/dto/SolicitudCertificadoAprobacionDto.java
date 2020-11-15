package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudCertificadoAprobacionDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long sistemaId;
	private long tramiteId;
	private long solicitudId;
	private long usuarioId;
	private int dependenciaId;
	private int oficinaId;
	private String archivoFirma;
	private String nombreArchivoFirma;	
	private long documentoAdjuntoId;
	private long personaContribuyenteId;

	public long getSistemaId() {
		return sistemaId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public long getSolicitudId() {
		return solicitudId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public void setSolicitudId(long solicitudId) {
		this.solicitudId = solicitudId;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getDependenciaId() {
		return dependenciaId;
	}

	public int getOficinaId() {
		return oficinaId;
	}

	public void setDependenciaId(int dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public void setOficinaId(int oficinaId) {
		this.oficinaId = oficinaId;
	}

	public String getArchivoFirma() {
		return archivoFirma;
	}

	public String getNombreArchivoFirma() {
		return nombreArchivoFirma;
	}

	public void setArchivoFirma(String archivoFirma) {
		this.archivoFirma = archivoFirma;
	}

	public void setNombreArchivoFirma(String nombreArchivoFirma) {
		this.nombreArchivoFirma = nombreArchivoFirma;
	}

	public long getDocumentoAdjuntoId() {
		return documentoAdjuntoId;
	}

	public void setDocumentoAdjuntoId(long documentoAdjuntoId) {
		this.documentoAdjuntoId = documentoAdjuntoId;
	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}	
	
	
}
