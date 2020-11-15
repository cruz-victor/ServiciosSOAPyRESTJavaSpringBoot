package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;


//IASC
public class RecuperaSistemasContribuyentesDto  extends ListaMensajesAplicacion implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long sistemaId;
	private String nombreSistema;
	private Long sistemaContribuyenteId;
	private Integer modalidadFacturacionId;
	
	private List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes;
	private RecuperaSistemasContribuyentesDto listaContribuyentes;
	
	private Long solicitudCertificacionId;
	private Long contribuyenteId;
	
	public RecuperaSistemasContribuyentesDto(Long sistemaId, String nombreSistema, Long sistemaContribuyenteId,
			Integer modalidadFacturacionId, List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes,
			RecuperaSistemasContribuyentesDto listaContribuyentes, Long solicitudCertificacionId,
			Long contribuyenteId) {
		super();
		this.sistemaId = sistemaId;
		this.nombreSistema = nombreSistema;
		this.sistemaContribuyenteId = sistemaContribuyenteId;
		this.modalidadFacturacionId = modalidadFacturacionId;
		this.listaSistemasContribuyentes = listaSistemasContribuyentes;
		this.listaContribuyentes = listaContribuyentes;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.contribuyenteId = contribuyenteId;
	}

	public RecuperaSistemasContribuyentesDto() {
		
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
	
	public Long getSistemaContribuyenteId() {
		return sistemaContribuyenteId;
	}

	public void setSistemaContribuyenteId(Long sistemaContribuyenteId) {
		this.sistemaContribuyenteId = sistemaContribuyenteId;
	}

	
	
	
	public List<RecuperaSistemasContribuyentesDto> getListaSistemasContribuyentes() {
		return listaSistemasContribuyentes;
	}

	public void setListaSistemasContribuyentes(List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes) {
		this.listaSistemasContribuyentes = listaSistemasContribuyentes;
	}

	
	
	
	public RecuperaSistemasContribuyentesDto getListaContribuyentes() {
		return listaContribuyentes;
	}

	public void setListaContribuyentes(RecuperaSistemasContribuyentesDto listaContribuyentes) {
		this.listaContribuyentes = listaContribuyentes;
	}
	

	public Integer getModalidadFacturacionId() {
		return modalidadFacturacionId;
	}

	public void setModalidadFacturacionId(Integer modalidadFacturacionId) {
		this.modalidadFacturacionId = modalidadFacturacionId;
	}

	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	@Override
	public String toString() {
		return "RecuperaSistemasContribuyentesDto [sistemaId=" + sistemaId + ", nombreSistema=" + nombreSistema
				+ ", sistemaContribuyenteId=" + sistemaContribuyenteId + ", modalidadFacturacionId="
				+ modalidadFacturacionId + ", listaSistemasContribuyentes=" + listaSistemasContribuyentes
				+ ", listaContribuyentes=" + listaContribuyentes + ", solicitudCertificacionId="
				+ solicitudCertificacionId + ", contribuyenteId=" + contribuyenteId + "]";
	}
}
