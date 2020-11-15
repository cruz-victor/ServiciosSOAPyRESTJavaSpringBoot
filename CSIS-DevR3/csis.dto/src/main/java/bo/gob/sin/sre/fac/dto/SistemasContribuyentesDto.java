package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class SistemasContribuyentesDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
private Long sistemaContribuyenteId;
	
	private Long usuarioRegistroId;
	private Long usuarioUltimaModificacionId;
	private Long contribuyenteId;
	private Long contribuyenteProveedorId;
	private String nombrecontribuyenteProveedor;
	private Long usuarioApruebaId;
	private Long usuarioBajaId;
	private Long sistemaId;
	private String nombreSistema;
	private Integer modalidadFacturacionId;
	private String modalidadFacturacion;
	private Integer estadoSistemaContribuyenteId;
	private String descripcionEstadoSistemaContribuyente;
	private String observacion;
	private Date fechaSolicitud;
	private Date fechaAprobacionRechazo;
	private Date fechaBaja;
	private Date fechaRegistro;
	private Date fechaUltimaModificacion;
	private String estadoId;
	private String nombreContribuyente;
	private String modalidad;
	private Integer tipoSistemaId;
	private String tipoSistema;
	private Integer porcentaje;
	
	public SistemasContribuyentesDto(Long sistemaContribuyenteId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, Long contribuyenteId, Long contribuyenteProveedorId,
			String nombrecontribuyenteProveedor, Long usuarioApruebaId, Long usuarioBajaId, Long sistemaId,
			String nombreSistema, Integer modalidadFacturacionId, Integer estadoSistemaContribuyenteId,
			String descripcionEstadoSistemaContribuyente, String observacion, Date fechaSolicitud,
			Date fechaAprobacionRechazo, Date fechaBaja, Date fechaRegistro, Date fechaUltimaModificacion,
			String estadoId, String nombreContribuyente, String modalidad, String tipoSistema, 
			Integer tipoSistemaId,String modalidadFacturacion,Integer porcentaje) {
		super();
		this.sistemaContribuyenteId = sistemaContribuyenteId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.contribuyenteId = contribuyenteId;
		this.contribuyenteProveedorId = contribuyenteProveedorId;
		this.nombrecontribuyenteProveedor = nombrecontribuyenteProveedor;
		this.usuarioApruebaId = usuarioApruebaId;
		this.usuarioBajaId = usuarioBajaId;
		this.sistemaId = sistemaId;
		this.nombreSistema = nombreSistema;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.modalidadFacturacion = modalidadFacturacion;
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
		this.descripcionEstadoSistemaContribuyente = descripcionEstadoSistemaContribuyente;
		this.observacion = observacion;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaAprobacionRechazo = fechaAprobacionRechazo;
		this.fechaBaja = fechaBaja;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.nombreContribuyente = nombreContribuyente;
		this.modalidad = modalidad;
		this.tipoSistema = tipoSistema;
		this.tipoSistemaId = tipoSistemaId;
		this.porcentaje = porcentaje;
	}

	public SistemasContribuyentesDto() {
		
	}
	
	public Long getSistemaContribuyenteId() {
		return sistemaContribuyenteId;
	}
	public void setSistemaContribuyenteId(Long sistemaContribuyenteId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
	}
	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}
	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}
	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}
	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}
	public Long getContribuyenteId() {
		return contribuyenteId;
	}
	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}
	public Long getContribuyenteProveedorId() {
		return contribuyenteProveedorId;
	}
	public void setContribuyenteProveedorId(Long contribuyenteProveedorId) {
		this.contribuyenteProveedorId = contribuyenteProveedorId;
	}
	public Long getUsuarioApruebaId() {
		return usuarioApruebaId;
	}
	public void setUsuarioApruebaId(Long usuarioApruebaId) {
		this.usuarioApruebaId = usuarioApruebaId;
	}
	public Long getUsuarioBajaId() {
		return usuarioBajaId;
	}
	public void setUsuarioBajaId(Long usuarioBajaId) {
		this.usuarioBajaId = usuarioBajaId;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}
	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}
	public Integer getEstadoSistemaContribuyenteId() {
		return estadoSistemaContribuyenteId;
	}
	public void setEstadoSistemaContribuyenteId(Integer estadoSistemaContribuyenteId) {
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Date getFechaAprobacionRechazo() {
		return fechaAprobacionRechazo;
	}
	public void setFechaAprobacionRechazo(Date fechaAprobacionRechazo) {
		this.fechaAprobacionRechazo = fechaAprobacionRechazo;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
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
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public String getNombrecontribuyenteProveedor() {
		return nombrecontribuyenteProveedor;
	}
	public void setNombrecontribuyenteProveedor(String nombrecontribuyenteProveedor) {
		this.nombrecontribuyenteProveedor = nombrecontribuyenteProveedor;
	}
	public String getNombreSistema() {
		return nombreSistema;
	}
	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}
	public String getDescripcionEstadoSistemaContribuyente() {
		return descripcionEstadoSistemaContribuyente;
	}
	public void setDescripcionEstadoSistemaContribuyente(String descripcionEstadoSistemaContribuyente) {
		this.descripcionEstadoSistemaContribuyente = descripcionEstadoSistemaContribuyente;
	}
	
	public String getNombreContribuyente() {
		return nombreContribuyente;
	}

	public void setNombreContribuyente(String nombreContribuyente) {
		this.nombreContribuyente = nombreContribuyente;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Integer getTipoSistemaId() {
		return tipoSistemaId;
	}

	public void setTipoSistemaId(Integer tipoSistemaId) {
		this.tipoSistemaId = tipoSistemaId;
	}

	public String getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
	}

	public String getModalidadFacturacion() {
		return modalidadFacturacion;
	}

	public void setModalidadFacturacion(String modalidadFacturacion) {
		this.modalidadFacturacion = modalidadFacturacion;
	}
	
	
	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public String toString() {
		return "SistemasContribuyentesDto [sistemaContribuyenteId=" + sistemaContribuyenteId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", contribuyenteId=" + contribuyenteId + ", contribuyenteProveedorId=" + contribuyenteProveedorId
				+ ", nombrecontribuyenteProveedor=" + nombrecontribuyenteProveedor + ", usuarioApruebaId="
				+ usuarioApruebaId + ", usuarioBajaId=" + usuarioBajaId + ", sistemaId=" + sistemaId
				+ ", nombreSistema=" + nombreSistema + ", modalidadFacturacionId=" + modalidadFacturacionId
				+ ", estadoSistemaContribuyenteId=" + estadoSistemaContribuyenteId
				+ ", descripcionEstadoSistemaContribuyente=" + descripcionEstadoSistemaContribuyente + ", observacion="
				+ observacion + ", fechaSolicitud=" + fechaSolicitud + ", fechaAprobacionRechazo="
				+ fechaAprobacionRechazo + ", fechaBaja=" + fechaBaja + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId
				+ ", nombreContribuyente=" + nombreContribuyente + ", modalidad=" + modalidad 
				+ ", tipoSistema=" + tipoSistema + ", tipoSistema=" + tipoSistema + ", modalidadFacturacion=" + modalidadFacturacion
				+ ", porcentaje=" + porcentaje
				+ "]";
	}
}