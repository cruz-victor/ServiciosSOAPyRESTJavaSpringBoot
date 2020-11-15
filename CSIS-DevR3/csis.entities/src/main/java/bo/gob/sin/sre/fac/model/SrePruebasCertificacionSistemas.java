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

/**
 * Objetivo: Entidad para las pruebas de certificación de sistemas.
 * Creado por: Peter Flores.
 * Fecha: 05/06/2019
 */
@Entity
@Table(name = "SRE_FAC_PRUEBAS_CERTIFICACION_SISTEMAS", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SrePruebasCertificacionSistemas.findAll", query="SELECT s FROM SrePruebasCertificacionSistemas s")
public class SrePruebasCertificacionSistemas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRUEBA_CERTIFICACION_SISTEMA_ID")
	private long pruebaCertificacionSistemaId;
	
	@Column(name = "PRUEBA_ETAPA_CERTIFICACION_ID")
	private long pruebaEtapaSistemasId;
	
	@Column(name = "SISTEMA_ID")
	private long sistemaId;
	
	@Column(name = "FECHA_INICIO_PRUEBA")
	private Date fechaInicioPrueba;
	
	@Column(name = "FECHA_FIN_PRUEBA")
	private Date fechaFinPrueba;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private long usuarioRegistroId;
	
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;
	
	@Column(name = "ETAPA_COMPLETADA")
	private Boolean etapaCompletada;

	public long getPruebaCertificacionSistemaId() {
		return pruebaCertificacionSistemaId;
	}

	public void setPruebaCertificacionSistemaId(long pruebaCertificacionSistemaId) {
		this.pruebaCertificacionSistemaId = pruebaCertificacionSistemaId;
	}

	public long getPruebaEtapaSistemasId() {
		return pruebaEtapaSistemasId;
	}

	public void setPruebaEtapaSistemasId(long pruebaEtapaSistemasId) {
		this.pruebaEtapaSistemasId = pruebaEtapaSistemasId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Date getFechaInicioPrueba() {
		return fechaInicioPrueba;
	}

	public void setFechaInicioPrueba(Date fechaInicioPrueba) {
		this.fechaInicioPrueba = fechaInicioPrueba;
	}

	public Date getFechaFinPrueba() {
		return fechaFinPrueba;
	}

	public void setFechaFinPrueba(Date fechaFinPrueba) {
		this.fechaFinPrueba = fechaFinPrueba;
	}

	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
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

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Boolean getEtapaCompletada() {
		return etapaCompletada;
	}

	public void setEtapaCompletada(Boolean etapaCompletada) {
		this.etapaCompletada = etapaCompletada;
	}
}
