package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Arrays;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RegistrarHuellaSistemaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long sistemaId;
	private Integer tipoComponenteId;
	private String md5;
	private String sha2;
	private String crc;
	private Long usuarioId;
	private String nombreArchivo;
	private String extension;
	private boolean estaRegistrado;

	private byte[] archivo;

	public RegistrarHuellaSistemaDto(Long sistemaId, Integer tipoComponenteId, String md5,
			String sha2, String crc, Long usuarioId, String nombreArchivo, String extension, boolean estaRegistrado,
			byte[] archivo, ComponentesCertificadosDto componentesCertificadosDto, ArchivosDto archivosDto) {
		this.sistemaId = sistemaId;
		this.tipoComponenteId = tipoComponenteId;
		this.md5 = md5;
		this.sha2 = sha2;
		this.crc = crc;
		this.usuarioId = usuarioId;
		this.nombreArchivo = nombreArchivo;
		this.extension = extension;
		this.estaRegistrado = estaRegistrado;
		this.archivo = archivo;
	}

	public RegistrarHuellaSistemaDto() {
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	

	public Integer getTipoComponenteId() {
		return tipoComponenteId;
	}

	public void setTipoComponenteId(Integer tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getSha2() {
		return sha2;
	}

	public void setSha2(String sha2) {
		this.sha2 = sha2;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isEstaRegistrado() {
		return estaRegistrado;
	}

	public void setEstaRegistrado(boolean estaRegistrado) {
		this.estaRegistrado = estaRegistrado;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return "RegistrarHuellaSistemaDto [sistemaId=" + sistemaId 
				+ ", tipoComponenteId=" + tipoComponenteId + ", md5=" + md5 + ", sha2=" + sha2 + ", crc=" + crc
				+ ", usuarioId=" + usuarioId + ", nombreArchivo=" + nombreArchivo + ", extension=" + extension
				+ ", estaRegistrado=" + estaRegistrado + ", archivo=" + Arrays.toString(archivo) + "]";
	}

}