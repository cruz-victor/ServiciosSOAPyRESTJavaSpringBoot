package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaDiagramasCertificacionesDto  extends ListaMensajesAplicacion  implements Serializable{

	private static final long serialVersionUID = 1L;

	List<DiagramasCertificacionesDto> listaDiagramasCertificacionesDto;
	
    private Long datosEntradaSolicitudCertificacionId; 
    
    private Long datosEntradaSistemaId; 
    
    private Long datosEntradaEtapaId;

	public List<DiagramasCertificacionesDto> getListaDiagramasCertificacionesDto() {
		return listaDiagramasCertificacionesDto;
	}

	public void setListaDiagramasCertificacionesDto(List<DiagramasCertificacionesDto> listaDiagramasCertificacionesDto) {
		this.listaDiagramasCertificacionesDto = listaDiagramasCertificacionesDto;
	}

	public Long getDatosEntradaSolicitudCertificacionId() {
		return datosEntradaSolicitudCertificacionId;
	}

	public void setDatosEntradaSolicitudCertificacionId(Long datosEntradaSolicitudCertificacionId) {
		this.datosEntradaSolicitudCertificacionId = datosEntradaSolicitudCertificacionId;
	}

	public Long getDatosEntradaSistemaId() {
		return datosEntradaSistemaId;
	}

	public void setDatosEntradaSistemaId(Long datosEntradaSistemaId) {
		this.datosEntradaSistemaId = datosEntradaSistemaId;
	}

	public Long getDatosEntradaEtapaId() {
		return datosEntradaEtapaId;
	}

	public void setDatosEntradaEtapaId(Long datosEntradaEtapaId) {
		this.datosEntradaEtapaId = datosEntradaEtapaId;
	}

}
