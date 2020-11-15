package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SRE_FAC_PRUEBAS_SISTEMAS", schema="SRE_RECAUDACIONES")
public class SrePruebasSistemas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "prueba_sistema_id")
	private Long pruebaSistemaId;

    @Column(name = "solicitud_certificacion_id")
	private Long solicitudCertificacionId;

    @Column(name = "tramite_id")
	private Long tramiteId;
    
	@Column(name = "PRUEBA_ID")
	private Long pruebaId;
	
	@Column(name = "sistema_id")
	private Long sistemaId;
	
	@Column(name = "ESTADO_PRUEBA_ID")
	private Integer estadoPruebaId;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "ESTADO_ID")
	private String estadoId;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private Long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	public SrePruebasSistemas(Long pruebaSistemaId, Long solicitudCertificacionId, Long tramiteId, Long pruebaId,
			Long sistemaId, Integer estadoPruebaId, String observaciones, String estadoId, Date fechaInicio,
			Date fechaFin, Long usuarioRegistroId, Long usuarioUltimaModificacionId, Date fechaRegistro,
			Date fechaUltimaModificacion) {
		super();
		this.pruebaSistemaId = pruebaSistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaId = pruebaId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.observaciones = observaciones;
		this.estadoId = estadoId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	public SrePruebasSistemas() {
		
	}
	
	public Long getPruebaSistemaId() {
		return pruebaSistemaId;
	}

	public void setPruebaSistemaId(Long pruebaSistemaId) {
		this.pruebaSistemaId = pruebaSistemaId;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public Long getPruebaId() {
		return pruebaId;
	}

	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Integer getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(Integer estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SreFacEntPruebasSistemas [pruebaSistemaId=" + pruebaSistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tramiteId=" + tramiteId + ", pruebaId=" + pruebaId + ", sistemaId="
				+ sistemaId + ", estadoPruebaId=" + estadoPruebaId + ", observaciones=" + observaciones + ", estadoId="
				+ estadoId + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId
				+ ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + "]";
	}

}