package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RegistroObservacionesComponentesInsituDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long observacionComponenteInsituId;

	private long solicitudCertificacionId;

	private long estadoPruebaId;

	private String estadoPruebaDescripcion;

	private long tipoComponenteId;

	private String tipoComponenteDescripcion;

	private long usuarioRegistroId;

	private Date fechaRegistro;

	private long usuarioUltimaModificacionId;

	private String estadoId;

	private List<BitacoraObservacionComponentesInsituDto> bitacorasObservacionesComponentesInsitu;

	public long getObservacionComponenteInsituId() {
		return observacionComponenteInsituId;
	}

	public void setObservacionComponenteInsituId(long observacionComponenteInsituId) {
		this.observacionComponenteInsituId = observacionComponenteInsituId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(long estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getEstadoPruebaDescripcion() {
		return estadoPruebaDescripcion;
	}

	public void setEstadoPruebaDescripcion(String estadoPruebaDescripcion) {
		this.estadoPruebaDescripcion = estadoPruebaDescripcion;
	}

	public long getTipoComponenteId() {
		return tipoComponenteId;
	}

	public void setTipoComponenteId(long tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}

	public String getTipoComponenteDescripcion() {
		return tipoComponenteDescripcion;
	}

	public void setTipoComponenteDescripcion(String tipoComponenteDescripcion) {
		this.tipoComponenteDescripcion = tipoComponenteDescripcion;
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

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<BitacoraObservacionComponentesInsituDto> getBitacorasObservacionesComponentesInsitu() {
		return bitacorasObservacionesComponentesInsitu;
	}

	public void setBitacorasObservacionesComponentesInsitu(List<BitacoraObservacionComponentesInsituDto> bitacorasObservacionesComponentesInsitu) {
		this.bitacorasObservacionesComponentesInsitu = bitacorasObservacionesComponentesInsitu;
	}
}
