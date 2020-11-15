package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SistemaSolicitudCertificacionDto implements Serializable {

	/*dimavi*/
	 
	private static final long serialVersionUID = 1L;
	private long solicitudCertificacionId;	
	private long personaContribuyenteId;	
	private int estadoSolicitudCertificacionId;	
	private Date fechaAprobacion;
	private Long sistemaId;	
	private Long tramiteId;
	private int modalidadFacturacionId;
	
	private String nombreSistema;
	private String version;
	private String descripcionEstadoSolicitudCertificacion;
	private String descripcionModalidad;
	

	private List<SistemaSolicitudCertificacionDto> listaSistema;
	
	public SistemaSolicitudCertificacionDto(long solicitudCertificacionId, long personaContribuyenteId,
			 								int estadoSolicitudCertificacionId, Date fechaAprobacion, Long sistemaId, int modalidadFacturacionId,
			 								String nombreSistema, String version, String descripcionEstadoSolicitudCertificacion, String descripcionModalidad) {
		super();
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.personaContribuyenteId = personaContribuyenteId;		
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;	
		this.fechaAprobacion = fechaAprobacion;		
		this.sistemaId = sistemaId;		
		this.modalidadFacturacionId = modalidadFacturacionId;			
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.descripcionEstadoSolicitudCertificacion = descripcionEstadoSolicitudCertificacion;
		this.descripcionModalidad = descripcionModalidad;
	}

	public SistemaSolicitudCertificacionDto() {

	}

	
	public List<SistemaSolicitudCertificacionDto> getListaSistema() {
		return listaSistema;
	}

	public void setListaSistema(List<SistemaSolicitudCertificacionDto> listaSistema) {
		this.listaSistema = listaSistema;
	}

	public long getSistemaSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSistemaSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}


	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}

	
	public int getEstadoSolicitudCertificacionId() {
		return estadoSolicitudCertificacionId;
	}

	public void setEstadoSolicitudCertificacionId(int estadoSolicitudCertificacionId) {
		this.estadoSolicitudCertificacionId = estadoSolicitudCertificacionId;
	}

	
	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}


	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}



	public String getDescripcionModalidad() {
		return descripcionModalidad;
	}

	public void setDescripcionModalidad(String descripcionModalidad) {
		this.descripcionModalidad = descripcionModalidad;
	}
	
	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public int getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(int modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	public String getDescripcionEstadoSolicitudCertificacion() {
		return descripcionEstadoSolicitudCertificacion;
	}

	public void setDescripcionEstadoSolicitudCertificacion(String descripcionEstadoSolicitudCertificacion) {
		this.descripcionEstadoSolicitudCertificacion = descripcionEstadoSolicitudCertificacion;
	}

		
	@Override
	public String toString() {
		return "SistemaSolicitudCertificacionDto [solicitudCertificacionId=" + solicitudCertificacionId
				+ ", personaContribuyenteId=" + personaContribuyenteId + ", estadoSolicitudCertificacionId="
				+ estadoSolicitudCertificacionId + ", fechaAprobacion=" + fechaAprobacion + ", sistemaId=" + sistemaId
				+ ", modalidadFacturacionId=" + modalidadFacturacionId + ", nombreSistema=" + nombreSistema
				+ ", version=" + version + ", descripcionEstadoSolicitudCertificacion="
				+ descripcionEstadoSolicitudCertificacion + ", descripcionModalidad=" + descripcionModalidad + "]";
	}

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}
}
