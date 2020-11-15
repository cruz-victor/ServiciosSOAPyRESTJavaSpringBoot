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

@ManagedBean(name = "listadoSistemasCertificadosModelList")
@ViewScoped
 
public class ListadoSistemasCertificadosModelList implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		listaSistemasContribuyentes = new ArrayList<>();
	}
	
	public void cargarListaSistemasContribuyentes(Long pIdContribuyente)
	{
		ServiciosFacturacionGiecRest vServiciosFacturacionRest = new ServiciosFacturacionGiecRest();
		RespuestaListaSistemasContribuyentesDto vRespuestaListaSistemasContribuyentesDto = new RespuestaListaSistemasContribuyentesDto();
		
		listaSistemasContribuyentes = new ArrayList<>();
		
		vRespuestaListaSistemasContribuyentesDto = vServiciosFacturacionRest.obtenerListaSistemasContribuyente(pIdContribuyente);
		
		mensajesBean.addMensajes(vRespuestaListaSistemasContribuyentesDto);
		
		if(vRespuestaListaSistemasContribuyentesDto.isOk())
		{
			listaSistemasContribuyentes = vRespuestaListaSistemasContribuyentesDto.getListaSistemasContribuyentes();
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Error al obtener la listade sistemas contribuyente', '')");
		}		
	}

	public List<RecuperaSistemasContribuyentesDto> getListaSistemasContribuyentes() {
		return listaSistemasContribuyentes;
	}

	public void setListaSistemasContribuyentes(List<RecuperaSistemasContribuyentesDto> listaSistemasContribuyentes) {
		this.listaSistemasContribuyentes = listaSistemasContribuyentes;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}
}
