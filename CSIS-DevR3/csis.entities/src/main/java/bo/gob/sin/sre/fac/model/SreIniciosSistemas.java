package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SRE_FAC_INICIOS_SISTEMAS", schema="SRE_RECAUDACIONES")
public class SreIniciosSistemas implements Serializable {

	private static final long serialVersionUID = 1L;
	//FRRA
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inicio_sistema_id")	
	private Long inicioSistemaId;
	
	@Column(name = "usuario_ultima_modificacion_id")	
	private Long usuarioUltimaModificacionId;
	
	@Column(name = "usuario_registro_id")	
	private Long usuarioRegistroId;
	
	@Column(name = "contribuyente_id")	
	private Long contribuyenteId;
	
	@Column(name = "sucursal_id")	
	private Integer sucursalId;
		
	@Column(name = "sistema_id")	
	private Long sistemaId;
	
	@Column(name = "sistema_contribuyente_id")	
	private Long sistemaContribuyenteId;
	
	@Column(name = "estado_inicio_id")	
	private Integer estadoInicioId;
	
	@Column(name = "cuis")	
	private String cuis;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_baja")	
	private Date fechaBaja;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")	
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion")	
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")	
	private String estadoId;
	
	@Column(name = "usuario_baja_id")	
	private Long usuarioBajaId;
	
	@Column(name = "punto_venta_id")	
	private Long puntoVentaId;

	public SreIniciosSistemas(Long inicioSistemaId, Long usuarioUltimaModificacionId, Long usuarioRegistroId,
			Long contribuyenteId, Integer sucursalId, Long sistemaId, Long sistemaContribuyenteId, Integer estadoInicioId,
			String cuis, Date fechaSolicitud, Date fechaInicio, Date fechaBaja, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId, Long usuarioSolicitaId, Long usuarioBajaId, Long puntoVentaId) {
		super();
		this.inicioSistemaId = inicioSistemaId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.contribuyenteId = contribuyenteId;
		this.sucursalId = sucursalId;
		this.sistemaId = sistemaId;
		this.sistemaContribuyenteId = sistemaContribuyenteId;
		this.estadoInicioId = estadoInicioId;
		this.cuis = cuis;
		this.fechaBaja = fechaBaja;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.usuarioBajaId = usuarioBajaId;
		this.puntoVentaId = puntoVentaId;
	}

	public SreIniciosSistemas() {
	}

	public Long getInicioSistemaId() {
		return inicioSistemaId;
	}

	public void setInicioSistemaId(Long inicioSistemaId) {
		this.inicioSistemaId = inicioSistemaId;
	}

	public Long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public Long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(Long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public Integer getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getSistemaContribuyenteId() {
		return sistemaContribuyenteId;
	}

	public void setSistemaContribuyenteId(Long sistemaContribuyenteId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
	}

	public Integer getEstadoInicioId() {
		return estadoInicioId;
	}

	public void setEstadoInicioId(Integer estadoInicioId) {
		this.estadoInicioId = estadoInicioId;
	}

	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
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

	public Long getUsuarioBajaId() {
		return usuarioBajaId;
	}

	public void setUsuarioBajaId(Long usuarioBajaId) {
		this.usuarioBajaId = usuarioBajaId;
	}

	public Long getPuntoVentaId() {
		return puntoVentaId;
	}

	public void setPuntoVentaId(Long puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	@Override
	public String toString() {
		return "SreIniciosSistemas [inicioSistemaId=" + inicioSistemaId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", usuarioRegistroId=" + usuarioRegistroId + ", contribuyenteId="
				+ contribuyenteId + ", sucursalId=" + sucursalId + ", sistemaId=" + sistemaId
				+ ", sistemaContribuyenteId=" + sistemaContribuyenteId + ", estadoInicioId=" + estadoInicioId
				+ ", cuis=" + cuis + ", fechaBaja=" + fechaBaja + ", fechaRegistro=" + fechaRegistro
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId + ", usuarioBajaId="
				+ usuarioBajaId + ", puntoVentaId="
						+ puntoVentaId + "]";
	}	
}