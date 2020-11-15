package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RegistroHuellasDigitalesDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long sistemaId;
	private String nombre;
	private String componenteMinimo;
	private List<Integer> tipoComponenteId;
	private String rutaArchivo;
	private String sha2;
	private String crc;
	private String md5;
	private Long usuarioId;
	private String extension;
	private byte[] archivo;

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getComponenteMinimo() {
		return componenteMinimo;
	}
	public void setComponenteMinimo(String componenteMinimo) {
		this.componenteMinimo = componenteMinimo;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
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
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public List<Integer> getTipoComponenteId() {
		return tipoComponenteId;
	}
	public void setTipoComponenteId(List<Integer> tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}	
}
