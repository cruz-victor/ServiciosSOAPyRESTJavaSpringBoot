package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class SolicitudSolicitudCertificacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long personaContribuyenteId;

	private long solicitudCertificacionId;
	private long usuarioId;

	//BORRAR
	private long tramiteId;
	//private long citeId;
	private String codigoDocumentoId;
	
	//NUEVOS
	private String cite;
	private long citeId;
	private Date fechaDocumento;

	public SolicitudSolicitudCertificacionDto(long personaContribuyenteId, long pTramiteId, long pCiteId,
			String pCodigoDocumentoId) {
		super();
		this.personaContribuyenteId = personaContribuyenteId;
		this.tramiteId = pTramiteId;
		this.citeId = pCiteId;
		this.codigoDocumentoId = pCodigoDocumentoId;
	}

	public SolicitudSolicitudCertificacionDto() {

	}

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	@Override
	public String toString() {
		return "SolicitudSolicitudCertificacionDto [personaContribuyenteId=" + personaContribuyenteId + "]";
	}
	
	

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public long getCiteId() {
		return citeId;
	}

	public void setCiteId(long citeId) {
		this.citeId = citeId;
	}

	public String getCodigoDocumentoId() {
		return codigoDocumentoId;
	}

	public void setCodigoDocumentoId(String codigoDocumentoId) {
		this.codigoDocumentoId = codigoDocumentoId;
	}

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public Date getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	
	

}
