package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class DatosReporteSolCertificacionSistemasRutasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String tipoDocumento;
	private String ruta;
	private int modalidadId;
	
	public DatosReporteSolCertificacionSistemasRutasDto(String tipoDocumento, String ruta, int modalidadId) {
		this.tipoDocumento = tipoDocumento;
		this.ruta = ruta;
		this.modalidadId = modalidadId;
	}

	public DatosReporteSolCertificacionSistemasRutasDto() {
		
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public int getModalidadId() {
		return modalidadId;
	}

	public void setModalidadId(int modalidadId) {
		this.modalidadId = modalidadId;
	}
	
}
