package bo.gob.sin.sre.fac.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;

@Entity
@Table(name = "SRE_FAC_SISTEMAS_CONTRIBUYENTES", schema="SRE_RECAUDACIONES")

public class SreSistemasContribuyentes implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "SISTEMA_CONTRIBUYENTE_ID")
	private Long sistemaContribuyenteId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private Long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "CONTRIBUYENTE_ID")
	private Long contribuyenteId;
	
	@Column(name = "CONTRIBUYENTE_PROVEEDOR_ID")
	private Long contribuyenteProveedorId;
	
	@Column(name = "USUARIO_APRUEBA_ID")
	private Long usuarioApruebaId;
	
	@Column(name = "USUARIO_BAJA_ID")
	private Long usuarioBajaId;
	
	@Column(name = "SISTEMA_ID")
	private Long sistemaId;
	
	@Column(name = "MODALIDAD_FACTURACION_ID")
	private Integer modalidadFacturacionId;
	
	@Column(name = "ESTADO_SISTEMA_CONTRIBUYENTE_ID")
	private Integer estadoSistemaContribuyenteId;
	
	@Column(name = "OBSERVACION")
	private String observacion;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_SOLICITUD")
	private Date fechaSolicitud;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_APROBACION_RECHAZO")
	private Date fechaAprobacionRechazo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;
	
	@Column(name = "MODALIDAD_SERVICIO_ID")
	private Integer modalidadServicioId;
		
	
	public SreSistemasContribuyentes(Long sistemaContribuyenteId, Long usuarioRegistroId, Long usuarioUltimaModificacionId, Long contribuyenteId,
			Long contribuyenteProveedorId, Long usuarioApruebaId, Long usuarioBajaId, Long sistemaId, Integer modalidadFacturacionId, Integer estadoSistemaContribuyenteId, 
			String observacion, Date fechaSolicitud, Date fechaAprobacionRechazo, Date fechaBaja, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId,
			Integer modalidadServicioId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.contribuyenteId = contribuyenteId;
		this.contribuyenteProveedorId = contribuyenteProveedorId;
		this.usuarioApruebaId = usuarioApruebaId;
		this.usuarioBajaId = usuarioBajaId;
		this.sistemaId = sistemaId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
		this.observacion = observacion;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaAprobacionRechazo = fechaAprobacionRechazo;
		this.fechaBaja = fechaBaja;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.modalidadServicioId = modalidadServicioId;
	}

	public SreSistemasContribuyentes() {
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
	

	public Integer getModalidadServicioId() {
		return modalidadServicioId;
	}

	public void setModalidadServicioId(Integer modalidadServicioId) {
		this.modalidadServicioId = modalidadServicioId;
	}

	@Override
	public String toString() {
		return "SreFacEntSistemasContribuyentes [sistemaContribuyenteId=" + sistemaContribuyenteId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", contribuyenteId=" + contribuyenteId + ", contribuyenteProveedorId="
				+ contribuyenteProveedorId + ", usuarioApruebaId=" + usuarioApruebaId + ", usuarioBajaId="
				+ usuarioBajaId + ", sistemaId=" + sistemaId + ", modalidadFacturacionId=" + modalidadFacturacionId
				+ ", estadoSistemaContribuyenteId=" + estadoSistemaContribuyenteId + ", observacion=" + observacion
				+ ", fechaSolicitud=" + fechaSolicitud + ", fechaAprobacionRechazo=" + fechaAprobacionRechazo
				+ ", fechaBaja=" + fechaBaja + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}
}