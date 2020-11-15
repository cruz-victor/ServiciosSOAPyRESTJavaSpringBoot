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
@Table(name = "SRE_FAC_REGISTROS_PRUEBAS_MANUALES", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreFacRegistrosPruebasManuales.findAll", query = "SELECT s FROM SreFacRegistrosPruebasManuales s")
public class SreFacRegistrosPruebasManuales implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REGISTRO_PRUEBA_MANUAL_ID")
	private long registroPruebaManualId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private long usuarioRegistroId;

	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private long usuarioUltimaModficacionId;

	@Column(name = "SOLICITUD_CERTIFICACION_ID")
	private long solicitudCertificacionId;
	
	@Column(name = "TRAMITE_ID")
	private long tramiteId;

	@Column(name = "PRUEBA_MANUAL_ID")
	private long pruebaManualId;
	
	@Column(name = "SISTEMA_ID")
	private long sistemaId;

	@Column(name = "ESTADO_PRUEBA_ID")
	private int estadoPruebaId;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

    @Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;

	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name = "ESTADO_ID")
	private String estadoId;

	public SreFacRegistrosPruebasManuales(long registroPruebaManualId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long solicitudCertificacionId, long tramiteId, long pruebaManualId,
			long sistemaId, int estadoPruebaId, String observaciones, Date fechaInicio, Date fechaFin,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		super();
		this.registroPruebaManualId = registroPruebaManualId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaManualId = pruebaManualId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.observaciones = observaciones;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public SreFacRegistrosPruebasManuales() {
		
	}

	public long getRegistroPruebaManualId() {
		return registroPruebaManualId;
	}

	public void setRegistroPruebaManualId(long registroPruebaManualId) {
		this.registroPruebaManualId = registroPruebaManualId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public long getUsuarioUltimaModficacionId() {
		return usuarioUltimaModficacionId;
	}

	public void setUsuarioUltimaModficacionId(long usuarioUltimaModficacionId) {
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public long getPruebaManualId() {
		return pruebaManualId;
	}

	public void setPruebaManualId(long pruebaManualId) {
		this.pruebaManualId = pruebaManualId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public int getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(int estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
		return "SreFacRegistrosPruebasManuales [registroPruebaManualId=" + registroPruebaManualId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModficacionId="
				+ usuarioUltimaModficacionId + ", solicitudCertificacionId=" + solicitudCertificacionId + ", tramiteId="
				+ tramiteId + ", pruebaManualId=" + pruebaManualId + ", sistemaId=" + sistemaId + ", estadoPruebaId="
				+ estadoPruebaId + ", observaciones=" + observaciones + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion
				+ ", estadoId=" + estadoId + "]";
	}
	
	
	
}
