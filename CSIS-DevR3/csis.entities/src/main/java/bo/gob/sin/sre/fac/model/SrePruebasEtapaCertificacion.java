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
@Table(name = "SRE_FAC_PRUEBAS_ETAPA_CERTIFICACION", schema="SRE_RECAUDACIONES")
@NamedQuery(name="SrePruebasEtapaCertificacion.findAll", query="SELECT s FROM SrePruebasEtapaCertificacion s")
public class SrePruebasEtapaCertificacion implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRUEBA_ETAPA_CERTIFICACION_ID")
	private long pruebaEtapaCertificacionid;
	
	@Column(name = "ETAPA_CERTIFICACION_SISTEMAS_ID")
	private long etapaCertificacionSistemasId;
	
	@Column(name = "CANTIDAD_PRUEBAS")
	private Integer cantidadPruebas;
	
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

	public long getPruebaEtapaCertificacionid() {
		return pruebaEtapaCertificacionid;
	}

	public void setPruebaEtapaCertificacionid(long pruebaEtapaCertificacionid) {
		this.pruebaEtapaCertificacionid = pruebaEtapaCertificacionid;
	}

	public long getEtapaCertificacionSistemasId() {
		return etapaCertificacionSistemasId;
	}

	public void setEtapaCertificacionSistemasId(long etapaCertificacionSistemasId) {
		this.etapaCertificacionSistemasId = etapaCertificacionSistemasId;
	}


	public Integer getCantidadPruebas() {
		return cantidadPruebas;
	}

	public void setCantidadPruebas(Integer cantidadPruebas) {
		this.cantidadPruebas = cantidadPruebas;
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
}

