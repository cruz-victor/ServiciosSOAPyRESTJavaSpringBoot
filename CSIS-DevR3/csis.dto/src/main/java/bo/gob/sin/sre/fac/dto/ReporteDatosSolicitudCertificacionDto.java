package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

//IASC
public class ReporteDatosSolicitudCertificacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private RespuestaDatosSistemasSolCertificacionDto sistema;
	private List<DatosReporteSolCertificacionSistemasDto> documentos;
	
	public ReporteDatosSolicitudCertificacionDto(RespuestaDatosSistemasSolCertificacionDto sistema,
			List<DatosReporteSolCertificacionSistemasDto> documentos) {
		this.sistema = sistema;
		this.documentos = documentos;
	}
	
	public ReporteDatosSolicitudCertificacionDto() {
		documentos = new ArrayList<>();
	}

	public RespuestaDatosSistemasSolCertificacionDto getSistema() {
		return sistema;
	}

	public void setSistema(RespuestaDatosSistemasSolCertificacionDto sistema) {
		this.sistema = sistema;
	}

	public List<DatosReporteSolCertificacionSistemasDto> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DatosReporteSolCertificacionSistemasDto> documentos) {
		this.documentos = documentos;
	}

	@Override
	public String toString() {
		return "ReporteDatosSolicitudCertificacionDto [sistema=" + sistema + ", documentos=" + documentos + "]";
	}
}
