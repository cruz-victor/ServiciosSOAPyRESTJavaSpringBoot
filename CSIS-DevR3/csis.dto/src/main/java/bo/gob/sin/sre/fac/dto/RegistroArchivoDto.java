package bo.gob.sin.sre.fac.dto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RegistroArchivoDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nombreArchivo;
	private String rutaArchivo;;
	private String extension;
	private byte[] archivo;
	private String tipoArchivoPermitido;
	private String mensajeArchivoInvalido;
	private String mensajeTamanioInvalido;
	private String tamanioArchivoBytes;
	private String archivoRutaCompleta;
	
	public RegistroArchivoDto()
	{  
	}
	
	public String obtenerExtensionArchivo(String ruta) 
	{
		String extension = ruta.substring(ruta.lastIndexOf(".")+1, ruta.length());
		
		return extension;
    }
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
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
	public String getTipoArchivoPermitido() {
		return tipoArchivoPermitido;
	}
	public void setTipoArchivoPermitido(String tipoArchivoPermitido) {
		this.tipoArchivoPermitido = tipoArchivoPermitido;
	}
	public String getMensajeArchivoInvalido() {
		return mensajeArchivoInvalido;
	}
	public void setMensajeArchivoInvalido(String mensajeArchivoInvalido) {
		this.mensajeArchivoInvalido = mensajeArchivoInvalido;
	}

	public String getMensajeTamanioInvalido() {
		return mensajeTamanioInvalido;
	}

	public void setMensajeTamanioInvalido(String mensajeTamanioInvalido) {
		this.mensajeTamanioInvalido = mensajeTamanioInvalido;
	}

	public String getTamanioArchivoBytes() {
		return tamanioArchivoBytes;
	}

	public void setTamanioArchivoBytes(String tamanioArchivoBytes) {
		this.tamanioArchivoBytes = tamanioArchivoBytes;
	}

	public String getArchivoRutaCompleta() {
		return archivoRutaCompleta;
	}

	public void setArchivoRutaCompleta(String archivoRutaCompleta) {
		this.archivoRutaCompleta = archivoRutaCompleta;
	}
}
