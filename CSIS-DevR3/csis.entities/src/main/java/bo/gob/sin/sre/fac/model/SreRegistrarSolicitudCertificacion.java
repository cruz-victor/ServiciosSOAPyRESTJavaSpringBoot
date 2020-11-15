package bo.gob.sin.sre.fac.model;



import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class SreRegistrarSolicitudCertificacion  extends ListaMensajesAplicacion{
	
	
	
	
	
	public SreSistemas getSistemaACertificar() {
		return sistemaACertificar;
	}

	
	public void setSistemaACertificar(SreSistemas sistemaACertificar) {
		this.sistemaACertificar = sistemaACertificar;
	}

	
	public SreSolicitudCertificacion getRegistroSolicitud() {
		return registroSolicitud;
	}

	
	public void setRegistroSolicitud(SreSolicitudCertificacion registroSolicitud) {
		this.registroSolicitud = registroSolicitud;
	}

	
	public boolean isEstaRegistrado() {
		return estaRegistrado;
	}

	
	public void setEstaRegistrado(boolean estaRegistrado) {
		this.estaRegistrado = estaRegistrado;
	}

	
	
	
	public Long getSistemaId() {
		return sistemaId;
	}


	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}




	private SreSistemas sistemaACertificar;
	
	private SreSolicitudCertificacion registroSolicitud;
	
	private boolean estaRegistrado;
	
	private Long sistemaId;

	
	
}
