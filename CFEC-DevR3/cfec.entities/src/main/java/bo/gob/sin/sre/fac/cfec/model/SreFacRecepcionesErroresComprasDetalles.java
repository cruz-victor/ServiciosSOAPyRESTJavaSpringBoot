package bo.gob.sin.sre.fac.cfec.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author edwin.coro
 * @fecha 01/12/2018
 */
@Entity
@Table(name = "sre_fac_recepciones_errores_compras_detalles", schema = "SRE_RECAUDACIONES")

public class SreFacRecepcionesErroresComprasDetalles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recepcion_error_compra_detalle_id")
	private long recepcionErrorCompraDetalleId;

	@Column(name="usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name="usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;
	
	@Column(name="recepcion_error_compra_id")
	private long recepcionErrorCompraId;

	@Column(name="codigo_error_id")
	private int codigoErrorId;
	
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name="estado_id")
	private String estadoId;

	public SreFacRecepcionesErroresComprasDetalles() {
		super();
	}

	public SreFacRecepcionesErroresComprasDetalles(long recepcionErrorCompraDetalleId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, long recepcionErrorCompraId, int codigoErrorId, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId) {
		super();
		this.recepcionErrorCompraDetalleId = recepcionErrorCompraDetalleId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.recepcionErrorCompraId = recepcionErrorCompraId;
		this.codigoErrorId = codigoErrorId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreFacRecepcionesErroresComprasDetalles [recepcionErrorCompraDetalleId=" + recepcionErrorCompraDetalleId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", recepcionErrorCompraId=" + recepcionErrorCompraId
				+ ", codigoErrorId=" + codigoErrorId + ", fechaRegistro=" + fechaRegistro + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	public long getRecepcionErrorCompraDetalleId() {
		return recepcionErrorCompraDetalleId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public long getRecepcionErrorCompraId() {
		return recepcionErrorCompraId;
	}

	public int getCodigoErrorId() {
		return codigoErrorId;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setRecepcionErrorCompraDetalleId(long recepcionErrorCompraDetalleId) {
		this.recepcionErrorCompraDetalleId = recepcionErrorCompraDetalleId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public void setRecepcionErrorCompraId(long recepcionErrorCompraId) {
		this.recepcionErrorCompraId = recepcionErrorCompraId;
	}

	public void setCodigoErrorId(int codigoErrorId) {
		this.codigoErrorId = codigoErrorId;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

}