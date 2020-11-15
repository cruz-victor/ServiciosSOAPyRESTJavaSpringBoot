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
 * @author Reynaldo.chambi
 * @fecha 10/08/2018
 */
@Entity
@Table(name = "sre_fac_recepciones_errores_compras", schema = "SRE_RECAUDACIONES")

public class SreFacRecepcionesErroresCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recepcion_error_compra_id")
	private long recepcionErrorCompraId;

	@Column(name="usuario_registro_id")
	private Long usuarioRegistroId;

	@Column(name="usuario_ultima_modificacion_id")
	private Long usuarioUltimaModificacionId;
	
	@Column(name="archivo_xml_invalido_id")
	private long archivoXmlInvalidoId;
	
	@Column(name="recepcion_compra_id")
	private long recepcionCompraId;
	
	@Column(name="recepcion_paquete_compra_id")
	private long recepcionPaqueteCompraId;
	
	@Column(name="numero_factura")
	private long numeroFactura;
	
	@Column(name="cuf")
	private String cuf;
	
	@Column(name="caed")
	private String caed;
	
	@Column(name="cufd")
	private String cufd;
	
	@Column(name="cuis")
	private String cuis;
	
	@Column(name="nombre_archivo")
	private String nombreArchivo;
		
	@Column(name="fecha_envio")
	private Date fechaEnvio;
	
	@Column(name="fecha_recepcion")
	private Date fechaRecepcion;
	
	@Column(name="fecha_consolidacion")
	private Date fechaConsolidacion;
	
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;

	@Column(name="estado_id")
	private String estadoId;

	public SreFacRecepcionesErroresCompras() {
		super();
	}

	public SreFacRecepcionesErroresCompras(long recepcionErrorCompraId, Long usuarioRegistroId,
			Long usuarioUltimaModificacionId, long archivoXmlInvalidoId, long recepcionCompraId,
			long recepcionPaqueteCompraId, long numeroFactura, String cuf, String caed, String cufd, String cuis,
			String nombreArchivo, Date fechaEnvio, Date fechaRecepcion, Date fechaConsolidacion, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId) {
		super();
		this.recepcionErrorCompraId = recepcionErrorCompraId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.archivoXmlInvalidoId = archivoXmlInvalidoId;
		this.recepcionCompraId = recepcionCompraId;
		this.recepcionPaqueteCompraId = recepcionPaqueteCompraId;
		this.numeroFactura = numeroFactura;
		this.cuf = cuf;
		this.caed = caed;
		this.cufd = cufd;
		this.cuis = cuis;
		this.nombreArchivo = nombreArchivo;
		this.fechaEnvio = fechaEnvio;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaConsolidacion = fechaConsolidacion;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreFacRecepcionesErroresCompras [recepcionErrorCompraId=" + recepcionErrorCompraId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", archivoXmlInvalidoId=" + archivoXmlInvalidoId
				+ ", recepcionCompraId=" + recepcionCompraId + ", recepcionPaqueteCompraId=" + recepcionPaqueteCompraId
				+ ", numeroFactura=" + numeroFactura + ", cuf=" + cuf + ", caed=" + caed + ", cufd=" + cufd + ", cuis="
				+ cuis + ", nombreArchivo=" + nombreArchivo + ", fechaEnvio=" + fechaEnvio + ", fechaRecepcion="
				+ fechaRecepcion + ", fechaConsolidacion=" + fechaConsolidacion + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + "]";
	}

	public long getRecepcionErrorCompraId() {
		return recepcionErrorCompraId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public long getArchivoXmlInvalidoId() {
		return archivoXmlInvalidoId;
	}

	public long getRecepcionCompraId() {
		return recepcionCompraId;
	}

	public long getRecepcionPaqueteCompraId() {
		return recepcionPaqueteCompraId;
	}

	public long getNumeroFactura() {
		return numeroFactura;
	}

	public String getCuf() {
		return cuf;
	}

	public String getCaed() {
		return caed;
	}

	public String getCufd() {
		return cufd;
	}

	public String getCuis() {
		return cuis;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public Date getFechaConsolidacion() {
		return fechaConsolidacion;
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

	public void setRecepcionErrorCompraId(long recepcionErrorCompraId) {
		this.recepcionErrorCompraId = recepcionErrorCompraId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public void setArchivoXmlInvalidoId(long archivoXmlInvalidoId) {
		this.archivoXmlInvalidoId = archivoXmlInvalidoId;
	}

	public void setRecepcionCompraId(long recepcionCompraId) {
		this.recepcionCompraId = recepcionCompraId;
	}

	public void setRecepcionPaqueteCompraId(long recepcionPaqueteCompraId) {
		this.recepcionPaqueteCompraId = recepcionPaqueteCompraId;
	}

	public void setNumeroFactura(long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public void setCuf(String cuf) {
		this.cuf = cuf;
	}

	public void setCaed(String caed) {
		this.caed = caed;
	}

	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public void setFechaConsolidacion(Date fechaConsolidacion) {
		this.fechaConsolidacion = fechaConsolidacion;
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