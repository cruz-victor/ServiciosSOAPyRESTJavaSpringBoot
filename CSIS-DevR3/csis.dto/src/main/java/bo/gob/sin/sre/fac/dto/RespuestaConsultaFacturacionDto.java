package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;;

public class RespuestaConsultaFacturacionDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long nit;
	private Date fechaAdecuacion;
	private String grupo;
	private String modalidadDestino;
	private String reporte;
	
    private List<MensajeError> mensajesError;
	
	public RespuestaConsultaFacturacionDto() {
		
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public Date getFechaAdecuacion() {
		return fechaAdecuacion;
	}

	public void setFechaAdecuacion(Date fechaAdecuacion) {
		this.fechaAdecuacion = fechaAdecuacion;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getModalidadDestino() {
		return modalidadDestino;
	}

	public void setModalidadDestino(String modalidadDestino) {
		this.modalidadDestino = modalidadDestino;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public List<MensajeError> getMensajesError() {
		return mensajesError;
	}

	public void setMensajesError(List<MensajeError> mensajesError) {
		this.mensajesError = mensajesError;
	}

}
