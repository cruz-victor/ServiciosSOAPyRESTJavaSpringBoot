package bo.gob.sin.sre.fac.dto;


import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaCertificadoAprobacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long documentoEmitidoId;
	private String cadenaCite;
	private String archivoCertificadoAprobacion;
	private String nombreArchivoCertificadoAprobacion;
	private String archivoCertificadoAprobacionHash;
	private String nombreArchivoCertificadoAprobacionHash;
	private Integer estadoFirmaId;
	private long documentoAdjuntodId;
	private long estadoDocumentoAdjunto;

	public Long getDocumentoEmitidoId() {
		return documentoEmitidoId;
	}

	public String getArchivoCertificadoAprobacion() {
		return archivoCertificadoAprobacion;
	}

	public void setDocumentoEmitidoId(Long documentoEmitidoId) {
		this.documentoEmitidoId = documentoEmitidoId;
	}

	public void setArchivoCertificadoAprobacion(String archivoCertificadoAprobacion) {
		this.archivoCertificadoAprobacion = archivoCertificadoAprobacion;
	}

	public String getCadenaCite() {
		return cadenaCite;
	}

	public void setCadenaCite(String cadenaCite) {
		this.cadenaCite = cadenaCite;
	}

	public String getNombreArchivoCertificadoAprobacion() {
		return nombreArchivoCertificadoAprobacion;
	}

	public void setNombreArchivoCertificadoAprobacion(String nombreArchivoCertificadoAprobacion) {
		this.nombreArchivoCertificadoAprobacion = nombreArchivoCertificadoAprobacion;
	}

	public String getArchivoCertificadoAprobacionHash() {
		return archivoCertificadoAprobacionHash;
	}

	public String getNombreArchivoCertificadoAprobacionHash() {
		return nombreArchivoCertificadoAprobacionHash;
	}

	public void setArchivoCertificadoAprobacionHash(String archivoCertificadoAprobacionHash) {
		this.archivoCertificadoAprobacionHash = archivoCertificadoAprobacionHash;
	}

	public void setNombreArchivoCertificadoAprobacionHash(String nombreArchivoCertificadoAprobacionHash) {
		this.nombreArchivoCertificadoAprobacionHash = nombreArchivoCertificadoAprobacionHash;
	}

	public Integer getEstadoFirmaId() {
		return estadoFirmaId;
	}

	public void setEstadoFirmaId(Integer estadoFirmaId) {
		this.estadoFirmaId = estadoFirmaId;
	}

	public long getDocumentoAdjuntodId() {
		return documentoAdjuntodId;
	}

	public void setDocumentoAdjuntodId(long documentoAdjuntodId) {
		this.documentoAdjuntodId = documentoAdjuntodId;
	}

	public long getEstadoDocumentoAdjunto() {
		return estadoDocumentoAdjunto;
	}

	public void setEstadoDocumentoAdjunto(long estadoDocumentoAdjunto) {
		this.estadoDocumentoAdjunto = estadoDocumentoAdjunto;
	}

	
}
