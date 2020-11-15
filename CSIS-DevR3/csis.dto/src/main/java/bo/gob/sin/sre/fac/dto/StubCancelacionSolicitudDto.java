package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class StubCancelacionSolicitudDto extends ListaMensajesAplicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	// CORRESPONDECIA
	private String cite;
	private long tramiteId;
	private String codigoDocumentoId;
	private long citeId;
	
	// BUZON TRIBUTARIO
	private String mensaje;
	private String uri;
	private long nit;

	public StubCancelacionSolicitudDto() {
	}

	public StubCancelacionSolicitudDto(String cite) {

		// CORRESPONDECIA
		super();
		this.cite = cite;

	}

	public StubCancelacionSolicitudDto(String mensaje, long nit) {
		// BUZON TRIBUTARIO
		super();
		this.mensaje = mensaje;
		this.nit = nit;

	}

	@Override
	public String toString() {
		return "mensaje [mensaje=" + mensaje + ", usuarioUltimaModificacionId=" + nit + ", nit ]";
	}

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public String getCodigoDocumentoId() {
		return codigoDocumentoId;
	}

	public void setCodigoDocumentoId(String codigoDocumentoId) {
		this.codigoDocumentoId = codigoDocumentoId;
	}

	public long getCiteId() {
		return citeId;
	}

	public void setCiteId(long citeId) {
		this.citeId = citeId;
	}
	
	
}
