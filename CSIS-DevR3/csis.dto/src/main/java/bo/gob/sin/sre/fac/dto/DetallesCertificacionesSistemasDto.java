package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class DetallesCertificacionesSistemasDto extends ListaMensajesAplicacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long detalleCertificacionSistemaId;

	private long usuarioRegistroId;
	
	private long usuarioUltimaModificacionId;
	
	private long  sistemaId;
		
	private long solicitudCertificacionId;
	
	private long tipoSistemaOperativoId;
	
	private String otroSistemaOperativo;
	
	private String versionSistemaOperativo;
	
	private long tipoServidorAplicacionId;
	
	private String otroServidorAplicacion;
	
	private long tipoBaseDatosId;
		
	private String otroBaseDatos;
	
	private long tipoLenguajeProgramacionId;
	
	private String otroLenguajeProgramacion;
	
	private String versionLenguajeProgramacion;
	
	private int tipoConexionId;
	
	private int archivoDiagramaId;
	
	private int estadoFaseCertificacionId;
	
	private Date fechaRegistro;
	
	private Date fechaUltimaModificacion;
	
	private String estadoId;
	
	private int etapaId;
	
	private Long tipoEsquemaId;	
	private String tipoEsquemaDescripcion;

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
	public Long getTipoEsquemaId() {
		return tipoEsquemaId;
	}

	/**
	 * @param tipoEsquemaId the tipoEsquemaId to set
	 */
	public void setTipoEsquemaId(Long tipoEsquemaId) {
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

}
