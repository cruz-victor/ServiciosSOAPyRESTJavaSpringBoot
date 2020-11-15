package bo.gob.sin.sre.fac.dto;

import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaRegistroVerificacionInsituDto extends ListaMensajesAplicacion 
{

	private long registroObservacionInsituId;
	private List<RegistroVerificacionInsituDto> lista;
	
	private Long datosEntradaSolicitudCertificacionId;
	private Long datosEntradaSistemaId;
	private Long datosEntradaUsuarioId;

	public RespuestaRegistroVerificacionInsituDto() {
		lista = new ArrayList<>();
	}

	public List<RegistroVerificacionInsituDto> getLista() {
		return lista;
	}

	public void setLista(List<RegistroVerificacionInsituDto> lista) {
		this.lista = lista;
	}

	public long getRegistroObservacionInsituId() {
		return registroObservacionInsituId;
	}

	public void setRegistroObservacionInsituId(long registroObservacionInsituId) {
		this.registroObservacionInsituId = registroObservacionInsituId;
	}

	/**
	 * @return the datosEntradaSolicitudCertificacionId
	 */
	public Long getDatosEntradaSolicitudCertificacionId() {
		return datosEntradaSolicitudCertificacionId;
	}

	/**
	 * @param datosEntradaSolicitudCertificacionId the datosEntradaSolicitudCertificacionId to set
	 */
	public void setDatosEntradaSolicitudCertificacionId(Long datosEntradaSolicitudCertificacionId) {
		this.datosEntradaSolicitudCertificacionId = datosEntradaSolicitudCertificacionId;
	}

	/**
	 * @return the datosEntradaSistemaId
	 */
	public Long getDatosEntradaSistemaId() {
		return datosEntradaSistemaId;
	}

	/**
	 * @param datosEntradaSistemaId the datosEntradaSistemaId to set
	 */
	public void setDatosEntradaSistemaId(Long datosEntradaSistemaId) {
		this.datosEntradaSistemaId = datosEntradaSistemaId;
	}

	/**
	 * @return the datosEntradaUsuarioId
	 */
	public Long getDatosEntradaUsuarioId() {
		return datosEntradaUsuarioId;
	}

	/**
	 * @param datosEntradaUsuarioId the datosEntradaUsuarioId to set
	 */
	public void setDatosEntradaUsuarioId(Long datosEntradaUsuarioId) {
		this.datosEntradaUsuarioId = datosEntradaUsuarioId;
	}

}
