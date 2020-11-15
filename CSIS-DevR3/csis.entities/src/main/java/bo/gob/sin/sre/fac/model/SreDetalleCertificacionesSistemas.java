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
@Table(name = "sre_fac_detalles_certificaciones_sistemas", schema="SRE_RECAUDACIONES")
@NamedQuery(name = "SreDetalleCertificacionesSistemas.findAll", query = "SELECT s FROM SreDetalleCertificacionesSistemas s")
public class SreDetalleCertificacionesSistemas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "detalle_certificacion_sistema_id")
	private long detalleCertificacionSistemaId;
	
	@Column(name = "usuario_registro_id")
	private long usuarioRegistroId;
	
	@Column(name = "usuario_ultima_modificacion_id")
	private long usuarioUltimaModificacionId;
	
	@Column(name = "sistema_id")
	private long  sistemaId;
		
	@Column(name = "solicitud_certificacion_id")
	private long solicitudCertificacionId;
	
	@Column(name = "tipo_sistema_operativo_id")
	private long tipoSistemaOperativoId;
	
	@Column(name = "otro_sistema_operativo")
	private String otroSistemaOperativo;
	
	@Column(name = "version_sistema_operativo")
	private String versionSistemaOperativo;
	
	@Column(name = "tipo_servidor_aplicacion_id")
	private long tipoServidorAplicacionId;
	
	@Column(name = "otro_servidor_aplicacion")
	private String otroServidorAplicacion;
	
	@Column(name = "tipo_base_datos_id")
	private long tipoBaseDatosId;
		
	@Column(name = "otro_base_datos")
	private String otroBaseDatos;
	
	@Column(name = "tipo_lenguaje_programacion_id")
	private long tipoLenguajeProgramacionId;
	
	@Column(name = "otro_lenguaje_programacion")
	private String otroLenguajeProgramacion;
	
	@Column(name = "version_lenguaje_programacion")
	private String versionLenguajeProgramacion;
	
	@Column(name = "tipo_conexion_id")
	private int tipoConexionId;
	
	@Column(name = "archivo_diagrama_id")
	private int archivoDiagramaId;
	
	@Column(name = "estado_fase_certificacion_id")
	private int estadoFaseCertificacionId;
	
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Column(name = "fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name = "estado_id")
	private String estadoId;
	
	@Column(name = "etapa_id")
	private int etapaId;
	
	@Column(name = "tipo_esquema_id")
	private long tipoEsquemaId;
	 
	private transient String tipoEsquemaDescripcion;

	public SreDetalleCertificacionesSistemas() {
	}

	


	public SreDetalleCertificacionesSistemas(long detalleCertificacionSistemaId, long usuarioRegistroId,
			long usuarioUltimaModificacionId, long sistemaId, long solicitudCertificacionId,
			long tipoSistemaOperativoId, String otroSistemaOperativo, String versionSistemaOperativo,
			long tipoServidorAplicacionId, String otroServidorAplicacion, long tipoBaseDatosId, String otroBaseDatos,
			long tipoLenguajeProgramacionId, String otroLenguajeProgramacion, String versionLenguajeProgramacion,
			int tipoConexionId, int archivoDiagramaId, int estadoFaseCertificacionId, Date fechaRegistro,
			Date fechaUltimaModificacion, String estadoId, int etapaId) {
		super();
		this.detalleCertificacionSistemaId = detalleCertificacionSistemaId;
		this.usuarioRegistroId = usuarioRegistroId;
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
		this.sistemaId = sistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tipoSistemaOperativoId = tipoSistemaOperativoId;
		this.otroSistemaOperativo = otroSistemaOperativo;
		this.versionSistemaOperativo = versionSistemaOperativo;
		this.tipoServidorAplicacionId = tipoServidorAplicacionId;
		this.otroServidorAplicacion = otroServidorAplicacion;
		this.tipoBaseDatosId = tipoBaseDatosId;
		this.otroBaseDatos = otroBaseDatos;
		this.tipoLenguajeProgramacionId = tipoLenguajeProgramacionId;
		this.otroLenguajeProgramacion = otroLenguajeProgramacion;
		this.versionLenguajeProgramacion = versionLenguajeProgramacion;
		this.tipoConexionId = tipoConexionId;
		this.archivoDiagramaId = archivoDiagramaId;
		this.estadoFaseCertificacionId = estadoFaseCertificacionId;
		this.fechaRegistro = fechaRegistro;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.estadoId = estadoId;
		this.etapaId = etapaId;
	}




	public long getDetalleCertificacionSistemaId() {
		return detalleCertificacionSistemaId;
	}

	public void setDetalleCertificacionSistemaId(long detalleCertificacionSistemaId) {
		this.detalleCertificacionSistemaId = detalleCertificacionSistemaId;
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

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public long getTipoSistemaOperativoId() {
		return tipoSistemaOperativoId;
	}

	public void setTipoSistemaOperativoId(long tipoSistemaOperativoId) {
		this.tipoSistemaOperativoId = tipoSistemaOperativoId;
	}

	public String getOtroSistemaOperativo() {
		return otroSistemaOperativo;
	}

	public void setOtroSistemaOperativo(String otroSistemaOperativo) {
		this.otroSistemaOperativo = otroSistemaOperativo;
	}

	public String getVersionSistemaOperativo() {
		return versionSistemaOperativo;
	}

	public void setVersionSistemaOperativo(String versionSistemaOperativo) {
		this.versionSistemaOperativo = versionSistemaOperativo;
	}

	public long getTipoServidorAplicacionId() {
		return tipoServidorAplicacionId;
	}

	public void setTipoServidorAplicacionId(long tipoServidorAplicacionId) {
		this.tipoServidorAplicacionId = tipoServidorAplicacionId;
	}

	public String getOtroServidorAplicacion() {
		return otroServidorAplicacion;
	}

	public void setOtroServidorAplicacion(String otroServidorAplicacion) {
		this.otroServidorAplicacion = otroServidorAplicacion;
	}

	public long getTipoBaseDatosId() {
		return tipoBaseDatosId;
	}

	public void setTipoBaseDatosId(long tipoBaseDatosId) {
		this.tipoBaseDatosId = tipoBaseDatosId;
	}

	public String getOtroBaseDatos() {
		return otroBaseDatos;
	}

	public void setOtroBaseDatos(String otroBaseDatos) {
		this.otroBaseDatos = otroBaseDatos;
	}

	public long getTipoLenguajeProgramacionId() {
		return tipoLenguajeProgramacionId;
	}

	public void setTipoLenguajeProgramacionId(long tipoLenguajeProgramacionId) {
		this.tipoLenguajeProgramacionId = tipoLenguajeProgramacionId;
	}

	public String getOtroLenguajeProgramacion() {
		return otroLenguajeProgramacion;
	}

	public void setOtroLenguajeProgramacion(String otroLenguajeProgramacion) {
		this.otroLenguajeProgramacion = otroLenguajeProgramacion;
	}

	public String getVersionLenguajeProgramacion() {
		return versionLenguajeProgramacion;
	}

	public void setVersionLenguajeProgramacion(String versionLenguajeProgramacion) {
		this.versionLenguajeProgramacion = versionLenguajeProgramacion;
	}

	public int getTipoConexionId() {
		return tipoConexionId;
	}

	public void setTipoConexionId(int tipoConexionId) {
		this.tipoConexionId = tipoConexionId;
	}

	public int getArchivoDiagramaId() {
		return archivoDiagramaId;
	}

	public void setArchivoDiagramaId(int archivoDiagramaId) {
		this.archivoDiagramaId = archivoDiagramaId;
	}

	public int getEstadoFaseCertificacionId() {
		return estadoFaseCertificacionId;
	}

	public void setEstadoFaseCertificacionId(int estadoFaseCertificacionId) {
		this.estadoFaseCertificacionId = estadoFaseCertificacionId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * @return the etapaId
	 */
	public int getEtapaId() {
		return etapaId;
	}




	/**
	 * @param etapaId the etapaId to set
	 */
	public void setEtapaId(int etapaId) {
		this.etapaId = etapaId;
	}




	/**
	 * @return the tipoEsquemaId
	 */
	public long getTipoEsquemaId() {
		return tipoEsquemaId;
	}




	/**
	 * @param tipoEsquemaId the tipoEsquemaId to set
	 */
	public void setTipoEsquemaId(long tipoEsquemaId) {
		this.tipoEsquemaId = tipoEsquemaId;
	}




	/**
	 * @return the tipoEsquemaDescripcion
	 */
	public String getTipoEsquemaDescripcion() {
		return tipoEsquemaDescripcion;
	}




	/**
	 * @param tipoEsquemaDescripcion the tipoEsquemaDescripcion to set
	 */
	public void setTipoEsquemaDescripcion(String tipoEsquemaDescripcion) {
		this.tipoEsquemaDescripcion = tipoEsquemaDescripcion;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SreDetalleCertificacionesSistemas [detalleCertificacionSistemaId=" + detalleCertificacionSistemaId
				+ ", usuarioRegistroId=" + usuarioRegistroId + ", usuarioUltimaModificacionId="
				+ usuarioUltimaModificacionId + ", sistemaId=" + sistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tipoSistemaOperativoId=" + tipoSistemaOperativoId
				+ ", otroSistemaOperativo=" + otroSistemaOperativo + ", versionSistemaOperativo="
				+ versionSistemaOperativo + ", tipoServidorAplicacionId=" + tipoServidorAplicacionId
				+ ", otroServidorAplicacion=" + otroServidorAplicacion + ", tipoBaseDatosId=" + tipoBaseDatosId
				+ ", otroBaseDatos=" + otroBaseDatos + ", tipoLenguajeProgramacionId=" + tipoLenguajeProgramacionId
				+ ", otroLenguajeProgramacion=" + otroLenguajeProgramacion + ", versionLenguajeProgramacion="
				+ versionLenguajeProgramacion + ", tipoConexionId=" + tipoConexionId + ", archivoDiagramaId="
				+ archivoDiagramaId + ", estadoFaseCertificacionId=" + estadoFaseCertificacionId + ", fechaRegistro="
				+ fechaRegistro + ", fechaUltimaModificacion=" + fechaUltimaModificacion + ", estadoId=" + estadoId
				+ ", etapaId=" + etapaId + ", tipoEsquemaId=" + tipoEsquemaId + "]";
	}





	
}
