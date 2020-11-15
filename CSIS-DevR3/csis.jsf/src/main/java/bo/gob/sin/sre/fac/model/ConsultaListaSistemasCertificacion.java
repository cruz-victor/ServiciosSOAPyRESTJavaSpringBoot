package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.dto.ObtenerListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;



@ManagedBean(name ="pruebaListaSistemas")
@ViewScoped
public class ConsultaListaSistemasCertificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<RecuperaListaSistemasCertificacionDto> listaGeneralSistemas;
	private List<RecuperaListaSistemasCertificacionDto> listaGeneralSistemasFiltrada;
	
	@PostConstruct
	void init()
	{
		listaGeneralSistemas = new ArrayList<>();
	}
	
	public void LlenaGrilla ()
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		ObtenerListaSistemasCertificacionDto vResultado = serviciosFacturacionRest.recuperaListaSistemasCertificacionGeneral();
		
		listaGeneralSistemas.clear();
		
		if (vResultado.isOk()) 
		{
			listaGeneralSistemas = new ArrayList<>();
			listaGeneralSistemas = vResultado.getListaSistemaDto();			
		} 
		else 
		{
			listaGeneralSistemas = new ArrayList<>();
		}		
		
	}

	public List<RecuperaListaSistemasCertificacionDto> getListaGeneralSistemas() {
		return listaGeneralSistemas;
	}

	public void setListaGeneralSistemas(List<RecuperaListaSistemasCertificacionDto> listaGeneralSistemas) {
		this.listaGeneralSistemas = listaGeneralSistemas;
	}

	public List<RecuperaListaSistemasCertificacionDto> getListaGeneralSistemasFiltrada() {
		return listaGeneralSistemasFiltrada;
	}

	public void setListaGeneralSistemasFiltrada(List<RecuperaListaSistemasCertificacionDto> listaGeneralSistemasFiltrada) {
		this.listaGeneralSistemasFiltrada = listaGeneralSistemasFiltrada;
	}
	
	
	
}
