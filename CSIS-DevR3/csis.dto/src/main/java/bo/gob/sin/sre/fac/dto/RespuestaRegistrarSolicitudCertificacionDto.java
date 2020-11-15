package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaRegistrarSolicitudCertificacionDto extends ListaMensajesAplicacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	private boolean estaRegistrado;

		
	private SistemasDto sistemaACertificar;
	private SolicitudCertificacionDto registroSolicitud;
	private Long solicitudCertificacionId;	
	private Long sistemaId;
	
	public RespuestaRegistrarSolicitudCertificacionDto()
	{
		sistemaACertificar = new SistemasDto();
		registroSolicitud = new SolicitudCertificacionDto();
	}
	
	public boolean isEstaRegistrado() {
		return estaRegistrado;
	}
	public void setEstaRegistrado(boolean estaRegistrado) {
		this.estaRegistrado = estaRegistrado;
	}
	public SistemasDto getSistemaACertificar() {
		return sistemaACertificar;
	}
	public void setSistemaACertificar(SistemasDto sistemaACertificar) {
		this.sistemaACertificar = sistemaACertificar;
	}
	public SolicitudCertificacionDto getRegistroSolicitud() {
		return registroSolicitud;
	}
	public void setRegistroSolicitud(SolicitudCertificacionDto registroSolicitud) {
		this.registroSolicitud = registroSolicitud;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}
	
	

	
	
	
	


	
	

}