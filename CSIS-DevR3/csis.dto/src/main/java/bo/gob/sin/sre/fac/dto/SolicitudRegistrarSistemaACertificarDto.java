package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class SolicitudRegistrarSistemaACertificarDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Long getTramiteId() {
		return tramiteId;
	}
	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}
	public Integer getTipoDepartamentoId() {
		return tipoDepartamentoId;
	}
	public void setTipoDepartamentoId(Integer tipoDepartamentoId) {
		this.tipoDepartamentoId = tipoDepartamentoId;
	}
	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}
	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}
	public Integer getTipoSistemaId() {
		return tipoSistemaId;
	}
	public void setTipoSistemaId(Integer tipoSistemaId) {
		this.tipoSistemaId = tipoSistemaId;
	}
	public Integer getEstadoSistemaId() {
		return estadoSistemaId;
	}
	public void setEstadoSistemaId(Integer estadoSistemaId) {
		this.estadoSistemaId = estadoSistemaId;
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
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}
	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}
	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}
	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	private Long sistemaId;
	private Long tramiteId;
	private Integer tipoDepartamentoId;
	private Integer modalidadFacturacionId;
	private Integer tipoSistemaId;
	private Integer estadoSistemaId;
	
	private String nombreSistema;
	private String version;
	private String estadoId;
	
	private Long usuarioUltimaModificacionId;
	private Long usuarioRegistroId;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	
	public SolicitudRegistrarSistemaACertificarDto() {
		
	}
	
	public SolicitudRegistrarSistemaACertificarDto(Long sistemaId, Long tramiteId, Integer tipoDepartamentoId,
			Integer modalidadFacturacionId, Integer tipoSistemaId, Integer estadoSistemaId, String nombreSistema,
			String version, String estadoId, Long usuarioUltimaModificacionId,
			Long usuarioRegistroId, Date fechaRegistro, Date fechaUltimaModificacion) {
		super();
		this.sistemaId = sistemaId;
		this.tramiteId = tramiteId;
		this.tipoDepartamentoId = tipoDepartamentoId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.tipoSistemaId = tipoSistemaId;
		this.estadoSistemaId = estadoSistemaId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.estadoId = estadoId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	@Override
	public String toString() {
		return "SreFacSolRegistrarSolicitudCertificacion [sistemaId=" + sistemaId + ", tramiteId=" + tramiteId
				+ ", tipoDepartamentoId=" + tipoDepartamentoId + ", modalidadFacturacionId=" + modalidadFacturacionId
				+ ", tipoSistemaId=" + tipoSistemaId + ", estadoSistemaId=" + estadoSistemaId + ", nombreSistema="
				+ nombreSistema + ", version=" + version + ", estadoId="
				+ estadoId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + "]";
	}

}
