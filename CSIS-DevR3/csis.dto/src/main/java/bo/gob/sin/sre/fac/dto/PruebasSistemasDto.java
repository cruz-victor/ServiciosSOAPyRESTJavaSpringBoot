package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

//public class PruebasSistemasDto implements Comparable<PruebasSistemasDto> {
public class PruebasSistemasDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long pruebaSistemaId;
	private Long solicitudCertificacionId;
	private Long tramiteId;
	private Long pruebaId;
	private String nombrePrueba;
	private Long sistemaId;
	private Integer estadoPruebaId;
	private String descripcionEstadoPrueba;
	private String observaciones;
	private String estadoId;
	private Date fechaInicio;
	private Date fechaFin;
	
	public PruebasSistemasDto(Long pruebaSistemaId, Long solicitudCertificacionId, Long tramiteId, Long pruebaId,
			String nombrePrueba, Long sistemaId, Integer estadoPruebaId, String descripcionEstadoPrueba,
			String observaciones, String estadoId, Date fechaInicio, Date fechaFin) {
		super();
		this.pruebaSistemaId = pruebaSistemaId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.tramiteId = tramiteId;
		this.pruebaId = pruebaId;
		this.nombrePrueba = nombrePrueba;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.descripcionEstadoPrueba = descripcionEstadoPrueba;
		this.observaciones = observaciones;
		this.estadoId = estadoId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public PruebasSistemasDto() {
		
	}
	
	public Long getPruebaSistemaId() {
		return pruebaSistemaId;
	}

	public void setPruebaSistemaId(Long pruebaSistemaId) {
		this.pruebaSistemaId = pruebaSistemaId;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public Long getPruebaId() {
		return pruebaId;
	}

	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Integer getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(Integer estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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

	public String getNombrePrueba() {
		return nombrePrueba;
	}

	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
	}

	public String getDescripcionEstadoPrueba() {
		return descripcionEstadoPrueba;
	}

	public void setDescripcionEstadoPrueba(String descripcionEstadoPrueba) {
		this.descripcionEstadoPrueba = descripcionEstadoPrueba;
	}

	@Override
	public String toString() {
		return "SreFacDtoPruebasSistemas [pruebaSistemaId=" + pruebaSistemaId + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", tramiteId=" + tramiteId + ", pruebaId=" + pruebaId + ", nombrePrueba="
				+ nombrePrueba + ", sistemaId=" + sistemaId + ", estadoPruebaId=" + estadoPruebaId
				+ ", descripcionEstadoPrueba=" + descripcionEstadoPrueba + ", observaciones=" + observaciones
				+ ", estadoId=" + estadoId + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

//	@Override
//	public int compareTo(PruebasSistemasDto o) {
//		return getDateTime().compareTo(o.getDateTime());
//	}

}
