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
@Table(name = "SRE_FAC_RUTAS_SERVICIOS", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreRutasServicios.findAll", query = "SELECT s FROM SreRutasServicios s")
public class SreRutasServicios implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "ruta_servicio_id")
	private long rutaServicioId;
	
	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "modalidad_facturacion_id")
	private int modalidadFacturacionId;
	
	@Column(name = "tipo_documento_sector_id")
	private int  tipoDocumentoSectorId;
	
	@Column(name = "estado_ruta_servicio_id")
	private Integer estadoRutaServicioId;
	
	@Column(name = "ruta_servicio")
	private String rutaServicio;
	
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;

	public SreRutasServicios() {
	}

	public SreRutasServicios(long rutaServicioId, long usuarioRegistroId, long usuarioUltimaModificacionId,
			int modalidadFacturacionId, int tipoDocumentoSectorId, Integer estadoRutaServicioId, String rutaServicio,
			Date fechaInicio, Date fechaFin, Date fechaRegistro, Date fechaUltimaModificacion, String estadoId) {
		this.rutaServicioId = rutaServicioId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
		this.estadoRutaServicioId = estadoRutaServicioId;
		this.rutaServicio = rutaServicio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	public long getRutaServicioId() {
		return rutaServicioId;
	}

	public void setRutaServicioId(long rutaServicioId) {
		this.rutaServicioId = rutaServicioId;
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

	public int getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(int modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	public int getTipoDocumentoSectorId() {
		return tipoDocumentoSectorId;
	}

	public void setTipoDocumentoSectorId(int tipoDocumentoSectorId) {
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}

	public Integer getEstadoRutaServicioId() {
		return estadoRutaServicioId;
	}

	public void setEstadoRutaServicioId(Integer estadoRutaServicioId) {
		this.estadoRutaServicioId = estadoRutaServicioId;
	}

	public String getRutaServicio() {
		return rutaServicio;
	}

	public void setRutaServicio(String rutaServicio) {
		this.rutaServicio = rutaServicio;
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
		return "SreRutasServicios [rutaServicioId=" + rutaServicioId + ", usuarioRegistroId=" + usuarioRegistroId
				+ ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", tipoDocumentoSectorId=" + tipoDocumentoSectorId
				+ ", estadoRutaServicioId=" + estadoRutaServicioId + ", rutaServicio=" + rutaServicio + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

}
