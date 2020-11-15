package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SRE_FAC_DETALLE_SOLICITUDES_CERTIFICACIONES", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreDetalleSolicitudesCertificaciones.findAll", query = "SELECT s FROM SreDetalleSolicitudesCertificaciones s")
public class SreDetalleSolicitudesCertificaciones implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "detalle_solicitud_certificacion_id")
	private long detalleSolicitudCertificacionId;
	
	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "solicitud_certificacion_id")
	private long solicitudCertificacionId;
	
	@Column(name = "sistema_id")
	private long  sistemaId;
	
	@Column(name = "modalidad_facturacion_id")
	private int modalidadFacturacionId;
	
	@Column(name = "cuis")
	private String cuis;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;

	public SreDetalleSolicitudesCertificaciones() {
	}

	public SreDetalleSolicitudesCertificaciones(long detalleSolicitudCertificacionId, long usuarioRegistroId,
			long usuarioUltimaModificacionId, long solicitudCertificacionId, long sistemaId, int modalidadFacturacionId,
			String cuis, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		this.detalleSolicitudCertificacionId = detalleSolicitudCertificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.sistemaId = sistemaId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.cuis = cuis;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public long getDetalleSolicitudCertificacionId() {
		return detalleSolicitudCertificacionId;
	}

	public void setDetalleSolicitudCertificacionId(long detalleSolicitudCertificacionId) {
		this.detalleSolicitudCertificacionId = detalleSolicitudCertificacionId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public int getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(int modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
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

	@Override
	public String toString() {
		return "SreDetalleSolicitudesCertificaciones [detalleSolicitudCertificacionId="
				+ detalleSolicitudCertificacionId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", sistemaId=" + sistemaId + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", cuis=" + cuis + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

}
