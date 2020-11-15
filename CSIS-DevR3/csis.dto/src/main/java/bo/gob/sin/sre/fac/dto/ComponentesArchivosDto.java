package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ComponentesArchivosDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long componenteArchivoId;
	private Long archivoId;
	private byte[] archivo;
	private String crc;
	private String extension;
	private String md5;
	private String mime;
	private String nombre;
	private String ruta;
	private String sha2;
	private Date fechaRegistro;
	
	public ComponentesArchivosDto()
	{  
	}

	

	public ComponentesArchivosDto(Long componenteArchivoId, Long archivoId, byte[] archivo, String crc,
			String extension, String md5, String mime, String nombre, String ruta, String sha2, Date fecha_registro) {
		super();
		this.componenteArchivoId = componenteArchivoId;
		this.archivoId = archivoId;
		this.archivo = archivo;
		this.crc = crc;
		this.extension = extension;
		this.md5 = md5;
		this.mime = mime;
		this.nombre = nombre;
		this.ruta = ruta;
		this.sha2 = sha2;
		this.fechaRegistro = fecha_registro;
	}



	public Date getFechaRegistro() {
		return fechaRegistro;
	}



	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



	public Long getComponenteArchivoId() {
		return componenteArchivoId;
	}

	public void setComponenteArchivoId(Long componenteArchivoId) {
		this.componenteArchivoId = componenteArchivoId;
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

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getSha2() {
		return sha2;
	}

	public void setSha2(String sha2) {
		this.sha2 = sha2;
	}
	
}
