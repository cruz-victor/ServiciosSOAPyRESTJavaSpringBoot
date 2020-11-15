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
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionGiecRest;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "listadoEtapasCertificadosFase2List")
@ViewScoped
 
public class ListadoEtapasCertificadosFase2List implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private ListaSeguimientoCertificacionSistemasFaseDosDto listaEtapaFaseDos;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		listaEtapaFaseDos = new ListaSeguimientoCertificacionSistemasFaseDosDto();
	}
	
	public void cargarlistaEtapaFaseDos(Long pIdContribuyente, Long pSistema, Long pSolicitudCertificacionId)
	{
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		ListaSeguimientoCertificacionSistemasFaseDosDto vRespuestaListaSeguimientoCertificacionSistemasFaseDosDto = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		ListaSeguimientoCertificacionSistemasFaseDosDto vSolicitudListaSeguimientoCertificacionSistemasFaseDosDto = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		
		//Datos quemados para visualizacion
		vSolicitudListaSeguimientoCertificacionSistemasFaseDosDto.setDatosEntradaSistemaId(pSistema);
		//vSolicitudListaSeguimientoCertificacionSistemasFaseDosDto.setDatosEntradaUsuarioRegistro(this.con);
		vSolicitudListaSeguimientoCertificacionSistemasFaseDosDto.setDatosEntradaSolicitudCertificacion(pSolicitudCertificacionId);
				
		listaEtapaFaseDos = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		
		vRespuestaListaSeguimientoCertificacionSistemasFaseDosDto = vServiciosFacturacionRest.listaEtapaFaseDos(vSolicitudListaSeguimientoCertificacionSistemasFaseDosDto);
		
		mensajesBean.addMensajes(vRespuestaListaSeguimientoCertificacionSistemasFaseDosDto);
		
		if(vRespuestaListaSeguimientoCertificacionSistemasFaseDosDto.isOk())
		{
			listaEtapaFaseDos = vRespuestaListaSeguimientoCertificacionSistemasFaseDosDto;
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Error al obtener la listade sistemas contribuyente', '')");
		}		
	}
	
	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	/**
	 * @return the listaEtapaFaseDos
	 */
	public ListaSeguimientoCertificacionSistemasFaseDosDto getListaEtapaFaseDos() {
		return listaEtapaFaseDos;
	}

	/**
	 * @param listaEtapaFaseDos the listaEtapaFaseDos to set
	 */
	public void setListaEtapaFaseDos(ListaSeguimientoCertificacionSistemasFaseDosDto listaEtapaFaseDos) {
		this.listaEtapaFaseDos = listaEtapaFaseDos;
	}
}
