package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SRE_FAC_PRUEBAS_AUTOMATICAS", schema="SRE_RECAUDACIONES")
public class SrePruebasAutomaticas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRUEBA_AUTOMATICA_ID")
	private long pruebaAutomaticaId;
	
	@Column(name = "USUARIO_REGISTRO_ID")
	private long usuarioRegistroId;
	
	@Column(name = "USUARIO_ULTIMA_MODIFICACION_ID")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "TIPO_PRUEBA_ID")
	private int tipoPruebaId;
	
	@Column(name = "SUBTIPO_PRUEBA_ID")
	private int subTipoPruebaId;
	
	@Column(name = "TIPO_DOCUMENTO_SECTOR_ID")
	private int tipoDocumentoSectorId;
	
	@Column(name = "ESTADO_PRUEBA_AUTOMATICA")
	private int estadoPruebaAutomaticaId;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "UMBRAL_ACEPTACION")
	private int umbralAceptacion;
	
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
	
	@Column(name = "CANTIDAD_PRUEBA")
	private long cantidadPrueba;
	
	@Column(name = "MODALIDAD_FACTURACION_ID")
	private int modalidadFacturacionId;
	
	public SrePruebasAutomaticas() {
		
	}

	public SrePruebasAutomaticas(long pruebaAutomaticaId, long usuarioRegistroId, long usuarioUltimaModificacionId,
			int tipoPruebaId, int subTipoPruebaId, int tipoDocumentoSectorId, int estadoPruebaAutomaticaId,
			String nombre, String descripcion, int umbralAceptacion, Date fechaInicio, Date fechaFin,
			Date fechaRegistro, Date fechaUltimaModificacion, String estadoId, long cantidadPrueba,
			int modalidadFacturacionId) {
		this.pruebaAutomaticaId = pruebaAutomaticaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.tipoPruebaId = tipoPruebaId;
		this.subTipoPruebaId = subTipoPruebaId;
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
		this.estadoPruebaAutomaticaId = estadoPruebaAutomaticaId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.umbralAceptacion = umbralAceptacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.cantidadPrueba = cantidadPrueba;
		this.modalidadFacturacionId = modalidadFacturacionId;
	}



	public long getPruebaAutomaticaId() {
		return pruebaAutomaticaId;
	}

	public void setPruebaAutomaticaId(long pruebaAutomaticaId) {
		this.pruebaAutomaticaId = pruebaAutomaticaId;
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

	public int getTipoPruebaId() {
		return tipoPruebaId;
	}

	public void setTipoPruebaId(int tipoPruebaId) {
		this.tipoPruebaId = tipoPruebaId;
	}

	public int getSubTipoPruebaId() {
		return subTipoPruebaId;
	}

	public void setSubTipoPruebaId(int subTipoPruebaId) {
		this.subTipoPruebaId = subTipoPruebaId;
	}

	public int getTipoDocumentoSectorId() {
		return tipoDocumentoSectorId;
	}

	public void setTipoDocumentoSectorId(int tipoDocumentoSectorId) {
		this.tipoDocumentoSectorId = tipoDocumentoSectorId;
	}

	public int getEstadoPruebaAutomaticaId() {
		return estadoPruebaAutomaticaId;
	}

	public void setEstadoPruebaAutomaticaId(int estadoPruebaAutomaticaId) {
		this.estadoPruebaAutomaticaId = estadoPruebaAutomaticaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getUmbralAceptacion() {
		return umbralAceptacion;
	}

	public void setUmbralAceptacion(int umbralAceptacion) {
		this.umbralAceptacion = umbralAceptacion;
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
	
	public long getCantidadPrueba() {
		return cantidadPrueba;
	}

	public void setCantidadPrueba(long cantidadPrueba) {
		this.cantidadPrueba = cantidadPrueba;
	}

	public int getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(int modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	@Override
	public String toString() {
		return "SrePruebasAutomaticas [pruebaAutomaticaId=" + pruebaAutomaticaId + ", usuarioRegistroId="
				+ usuarioRegistroId + ", usuarioUltimaModificacionId=" + usuarioUltimaModificacionId + ", tipoPruebaId="
				+ tipoPruebaId + ", subTipoPruebaId=" + subTipoPruebaId + ", tipoDocumentoSectorId="
				+ tipoDocumentoSectorId + ", estadoPruebaAutomaticaId=" + estadoPruebaAutomaticaId + ", nombre="
				+ nombre + ", descripcion=" + descripcion + ", umbralAceptacion=" + umbralAceptacion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId
				+ ", cantidadPrueba=" + cantidadPrueba + ", modalidadFacturacionId=" + modalidadFacturacionId + "]";
	}
}

