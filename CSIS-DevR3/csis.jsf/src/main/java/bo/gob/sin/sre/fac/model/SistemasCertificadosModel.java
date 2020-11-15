package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionGiecRest;

@ManagedBean(name = "sistemasCertificadosModel")
@ViewScoped
 
public class SistemasCertificadosModel implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private RecuperaSistemasContribuyentesDto sistemasContribuyentes;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		sistemasContribuyentes = new RecuperaSistemasContribuyentesDto();
	}

	public RecuperaSistemasContribuyentesDto getSistemasContribuyentes() {
		return sistemasContribuyentes;
	}

	public void setSistemasContribuyentes(RecuperaSistemasContribuyentesDto sistemasContribuyentes) {
		this.sistemasContribuyentes = sistemasContribuyentes;
	}
	
	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}
}
