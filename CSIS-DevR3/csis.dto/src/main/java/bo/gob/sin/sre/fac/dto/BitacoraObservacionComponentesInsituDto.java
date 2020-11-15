package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class BitacoraObservacionComponentesInsituDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long bitacoraObservacionComponenteInsituId;

	private long solicitudCertificacionId;

	private long observacionComponenteInsituId;

	private String observaciones;

	private long estadoPruebaId;;

	private long usuarioRegistroId;
	
	private Date fechaRegistro;

	private long usuarioUltimaModificacionId;

	private String estadoId;

	public long getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(long estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public long getBitacoraObservacionComponenteInsituId() {
		return bitacoraObservacionComponenteInsituId;
	}

	public void setBitacoraObservacionComponenteInsituId(long bitacoraObservacionComponenteInsituId) {
		this.bitacoraObservacionComponenteInsituId = bitacoraObservacionComponenteInsituId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getObservacionComponenteInsituId() {
		return observacionComponenteInsituId;
	}

	public void setObservacionComponenteInsituId(long observacionComponenteInsituId) {
		this.observacionComponenteInsituId = observacionComponenteInsituId;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
