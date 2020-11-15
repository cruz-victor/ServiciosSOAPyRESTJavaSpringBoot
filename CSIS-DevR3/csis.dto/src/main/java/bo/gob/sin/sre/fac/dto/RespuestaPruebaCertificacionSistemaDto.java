package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaPruebaCertificacionSistemaDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

private long pruebaCertificacionSistemaId;
	
	private long etapaCertificacionSistemasId;
		
	private Date fechaInicioPrueba;
		
	private Date fechaFinPrueba;
		
	private Integer cantidadPruebas;	
	
	private long usuarioUltimaModificacionId;	
	
	private long usuarioRegistroId;
		
	private Date fechaRegistro;
	
	private Date fechaUltimaModificacion;
		
	private String estadoId;

	public long getPruebaCertificacionSistemaId() {
		return pruebaCertificacionSistemaId;
	}

	public void setPruebaCertificacionSistemaId(long pruebaCertificacionSistemaId) {
		this.pruebaCertificacionSistemaId = pruebaCertificacionSistemaId;
	}

	public long getEtapaCertificacionSistemasId() {
		return etapaCertificacionSistemasId;
	}

	public void setEtapaCertificacionSistemasId(long etapaCertificacionSistemasId) {
		this.etapaCertificacionSistemasId = etapaCertificacionSistemasId;
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
