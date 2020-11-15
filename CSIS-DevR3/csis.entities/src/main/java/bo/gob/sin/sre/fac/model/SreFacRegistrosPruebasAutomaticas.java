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
@Table(name = "SRE_FAC_REGISTROS_PRUEBAS_AUTOMATICAS", schema = "SRE_RECAUDACIONES")
@NamedQuery(name = "SreFacRegistrosPruebasAutomaticas.findAll", query = "SELECT s FROM SreFacRegistrosPruebasAutomaticas s")
public class SreFacRegistrosPruebasAutomaticas implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REGISTRO_PRUEBA_AUTOMATICA_ID")
	private long registroPruebaAutomaticaId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private long usuarioRegistroId;

	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private long usuarioUltimaModficacionId;

	@Column(name = "SOLICITUD_CERTIFICACION_ID")
	private long solicitudCertificacionId;

	@Column(name = "TRAMITE_ID")
	private long tramiteId;

	@Column(name = "PRUEBA_AUTOMATICA_ID")
	private long pruebaAutomaticaId;

	@Column(name = "SISTEMA_ID")
	private long sistemaId;

	@Column(name = "ESTADO_PRUEBA_ID")
	private int estadoPruebaId;

	@Column(name = "CANTIDAD_INTENTO")
	private long cantidadIntento;

	@Column(name = "CANTIDAD_CORRECTOS")
	private long cantidadCorrectos;
	
	@Column(name = "CANTIDAD_ERRORES")
	private long cantidadErrores;
	
	@Column(name = "PORCENTAJE_CORRECTOS")
	private java.math.BigDecimal porcentajeCorrectos;
	
	@Column(name = "PORCENTAJE_ERRORES")
	private java.math.BigDecimal porcentajeErrores;	

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



	public SreFacRegistrosPruebasAutomaticas(long registroPruebaAutomaticaId, long usuarioRegistroId,
			long usuarioUltimaModficacionId, long solicitudCertificacionId, long tramiteId, long pruebaAutomaticaId,
			long sistemaId, int estadoPruebaId, long cantidadIntento, long cantidadCorrectos, long cantidadErrores,
			java.math.BigDecimal porcentajeCorrectos, java.math.BigDecimal porcentajeErrores, Date fechaInicio, Date fechaFin, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId) {
		super();
		this.registroPruebaAutomaticaId = registroPruebaAutomaticaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModficacionId = usuarioUltimaModficacionId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaAutomaticaId = pruebaAutomaticaId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.cantidadIntento = cantidadIntento;
		this.cantidadCorrectos = cantidadCorrectos;
		this.cantidadErrores = cantidadErrores;
		this.porcentajeCorrectos = porcentajeCorrectos;
		this.porcentajeErrores = porcentajeErrores;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}
	
	public SreFacRegistrosPruebasAutomaticas() {
		
	}


	public long getRegistroPruebaAutomaticaId() {
		return registroPruebaAutomaticaId;
	}

	public void setRegistroPruebaAutomaticaId(long registroPruebaAutomaticaId) {
		this.registroPruebaAutomaticaId = registroPruebaAutomaticaId;
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

	public long getPruebaAutomaticaId() {
		return pruebaAutomaticaId;
	}

	public void setPruebaAutomaticaId(long pruebaAutomaticaId) {
		this.pruebaAutomaticaId = pruebaAutomaticaId;
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

	public long getCantidadIntento() {
		return cantidadIntento;
	}

	public void setCantidadIntento(long cantidadIntento) {
		this.cantidadIntento = cantidadIntento;
	}

	public long getCantidadCorrectos() {
		return cantidadCorrectos;
	}

	public void setCantidadCorrectos(long cantidadCorrectos) {
		this.cantidadCorrectos = cantidadCorrectos;
	}

	public long getCantidadErrores() {
		return cantidadErrores;
	}

	public void setCantidadErrores(long cantidadErrores) {
		this.cantidadErrores = cantidadErrores;
	}



	public java.math.BigDecimal getPorcentajeCorrectos() {
		return porcentajeCorrectos;
	}

	public void setPorcentajeCorrectos(java.math.BigDecimal porcentajeCorrectos) {
		this.porcentajeCorrectos = porcentajeCorrectos;
	}



	public java.math.BigDecimal getPorcentajeErrores() {
		return porcentajeErrores;
	}

	public void setPorcentajeErrores(java.math.BigDecimal porcentajeErrores) {
		this.porcentajeErrores = porcentajeErrores;
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
		return "SreFacRegistrosPruebasAutomaticas [registroPruebaAutomaticaId=" + registroPruebaAutomaticaId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModficacionId="
				+ usuarioUltimaModficacionId + ", solicitudCertificacionId=" + solicitudCertificacionId + ", tramiteId="
				+ tramiteId + ", pruebaAutomaticaId=" + pruebaAutomaticaId + ", sistemaId=" + sistemaId
				+ ", estadoPruebaId=" + estadoPruebaId + ", cantidadIntento=" + cantidadIntento + ", cantidadCorrectos="
				+ cantidadCorrectos + ", cantidadErrores=" + cantidadErrores + ", porcentajeCorrectos="
				+ porcentajeCorrectos + ", porcentajeErrores=" + porcentajeErrores + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	

}
