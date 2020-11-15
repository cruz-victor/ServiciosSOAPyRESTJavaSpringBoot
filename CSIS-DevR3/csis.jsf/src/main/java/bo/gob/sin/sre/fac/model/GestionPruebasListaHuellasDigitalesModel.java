package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;

@ManagedBean (name ="gestionPruebasListaHuellasDigitalesModel")
@ViewScoped
public class GestionPruebasListaHuellasDigitalesModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<RegistroHuellasDigitalesDto> listaHuellasDigitales;
	private Long sistemaId;

	
	@PostConstruct
	public void init() {
		listaHuellasDigitales = new ArrayList<>();	
	}	
	
	public List<RegistroHuellasDigitalesDto> getListaHuellasDigitales() {
		return listaHuellasDigitales;
	}
	public void setListaHuellasDigitales(List<RegistroHuellasDigitalesDto> listaHuellasDigitales) {
		this.listaHuellasDigitales = listaHuellasDigitales;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}	
	
	
}
