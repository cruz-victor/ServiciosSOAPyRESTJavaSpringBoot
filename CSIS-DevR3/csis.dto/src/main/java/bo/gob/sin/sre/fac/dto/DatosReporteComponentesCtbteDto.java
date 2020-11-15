package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class DatosReporteComponentesCtbteDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nombreArchivo;
	private String nombreComponente;
	private String ruta;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getNombreComponente() {
		return nombreComponente;
	}
	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}	
}
