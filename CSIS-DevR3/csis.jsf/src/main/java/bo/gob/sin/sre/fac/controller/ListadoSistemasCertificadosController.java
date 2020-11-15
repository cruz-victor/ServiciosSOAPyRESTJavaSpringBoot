package bo.gob.sin.sre.fac.controller;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.model.ConsultaListaSistemasCertificacion;


@ManagedBean(name = "listaPruebaController")
@ViewScoped
public class ListadoSistemasCertificadosController implements Serializable {


	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{pruebaListaSistemas}")
	ConsultaListaSistemasCertificacion pruebaListaSistemas;
	
	public void Load()
	{
		this.getPruebaListaSistemas().LlenaGrilla();
	}

	public ConsultaListaSistemasCertificacion getPruebaListaSistemas() {
		return pruebaListaSistemas;
	}

	public void setPruebaListaSistemas(ConsultaListaSistemasCertificacion pruebaListaSistemas) {
		this.pruebaListaSistemas = pruebaListaSistemas;
	}
	
		 
} 