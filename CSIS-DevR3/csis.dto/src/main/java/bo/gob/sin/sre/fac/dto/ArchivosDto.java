package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ArchivosDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long archivoId;
	private byte[] archivo;
	private String estadoId;

	public ArchivosDto(Long archivoId, byte[] archivo, String estadoId) {
		super();
		this.archivoId = archivoId;
		this.archivo = archivo;
		this.estadoId = estadoId;
	}

	public ArchivosDto() {

	}

	public Long getArchivoId() {
		return archivoId;
	}

	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "ArchivosDto [archivoId=" + archivoId + ", archivo=" + archivo + ", estadoId=" + estadoId + "]";
	}

}
