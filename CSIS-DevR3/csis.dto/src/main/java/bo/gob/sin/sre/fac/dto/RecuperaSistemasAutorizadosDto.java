package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;
import java.util.List;


//IASC
public class RecuperaSistemasAutorizadosDto implements Serializable{
	private Long sistemaId;
	private String nombreSistema;
	private String tipoSistema;
	private Integer sucursal;
	private String cuis;
	private Date fechaSolicitud;
	private String estado;
	private Integer estadoId;
		
	private List<RecuperaSistemasAutorizadosDto> listaSucursales;
	
	public RecuperaSistemasAutorizadosDto(Long sistemaId, String nombreSistema, String tipoSistema, Integer sucursal,
			String cuis, Date fechaSolicitud, String estado, Integer estadoId) {
		super();
		this.sistemaId = sistemaId;
		this.nombreSistema = nombreSistema;
		this.tipoSistema = tipoSistema;
		this.sucursal = sucursal;
		this.cuis = cuis;
		this.fechaSolicitud = fechaSolicitud;
		this.estado = estado;
		this.estadoId = estadoId;
	}

	public RecuperaSistemasAutorizadosDto() {
		
	}
	
	public RecuperaSistemasAutorizadosDto(Long sistemaId, Integer sucursal, String cuis) {
		this.sistemaId = sistemaId;
		this.sucursal = sucursal;
		this.cuis = cuis;
	}
	
	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}
	
	public String getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
	}
	
	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	
	public String getCuis() {
		return cuis;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}
	
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	


	public List<RecuperaSistemasAutorizadosDto> getListaSucursales() {
		return listaSucursales;
	}

	public void setListaSucursales(List<RecuperaSistemasAutorizadosDto> listaSucursales) {
		this.listaSucursales = listaSucursales;
	}

	@Override
	public String toString() {
		return "RecuperaSistemasAutorizadosDto [sistemaId=" + sistemaId + ", nombreSistema=" + nombreSistema
				+ ", tipoSistema=" + tipoSistema + ", sucursal=" + sucursal + ", cuis=" + cuis + ", fechaSolicitud="
				+ fechaSolicitud + ", estado=" + estado + ", estadoId=" + estadoId + "]";
	}
	
}
