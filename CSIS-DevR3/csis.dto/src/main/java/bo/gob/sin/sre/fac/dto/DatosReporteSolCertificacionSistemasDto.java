package bo.gob.sin.sre.fac.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatosReporteSolCertificacionSistemasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String modalidad;
	private int modalidadId;
	private String cuis;
	private List<DatosReporteSolCertificacionSistemasRutasDto> listaRutas;
	
	public DatosReporteSolCertificacionSistemasDto(String modalidad, int modalidadId, String cuis,
			List<DatosReporteSolCertificacionSistemasRutasDto> listaRutas) {
		this.modalidad = modalidad;
		this.modalidadId = modalidadId;
		this.cuis = cuis;
		this.listaRutas = listaRutas;
	}

	public DatosReporteSolCertificacionSistemasDto() {
		listaRutas = new ArrayList<>();
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public List<DatosReporteSolCertificacionSistemasRutasDto> getListaRutas() {
		return listaRutas;
	}

	public void setListaRutas(List<DatosReporteSolCertificacionSistemasRutasDto> listaRutas) {
		this.listaRutas = listaRutas;
	}

	public int getModalidadId() {
		return modalidadId;
	}

	public void setModalidadId(int modalidadId) {
		this.modalidadId = modalidadId;
	}

}
