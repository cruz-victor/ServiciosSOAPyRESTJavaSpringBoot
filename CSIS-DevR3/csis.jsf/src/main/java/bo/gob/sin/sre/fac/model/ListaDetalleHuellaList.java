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
import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionGiecRest;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionQueryRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "listaDetalleHuellaList")
@ViewScoped
 
public class ListaDetalleHuellaList implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private ListaDetalleHuellaDto listaDetalleHuellaDto;
	
	private ListaSistemasSolicitudCertificacionDto listaSistemasSolicitudCertificacionDto;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		listaDetalleHuellaDto = new ListaDetalleHuellaDto();
		listaSistemasSolicitudCertificacionDto = new ListaSistemasSolicitudCertificacionDto();
	}
	
	public void obtieneComponentesArchivosCertificados(Long pSistema, Long pSolicitudCertificacionId, Long pIdContribuyente)
	{
		ServiciosConsultaFacturacionRestClient vServiciosFacturacionRest = new ServiciosConsultaFacturacionRestClient();
		ListaDetalleHuellaDto vRespuesta= new ListaDetalleHuellaDto();
		ListaDetalleHuellaDto vSolicitud= new ListaDetalleHuellaDto();
				
		vSolicitud.setDatoEntradaIdSistema(pSistema);
		vSolicitud.setDatoEntradaIdSolicitudCertificacion(pSolicitudCertificacionId);
		vSolicitud.setDatoEntradaIdContribuyente(pIdContribuyente);
		
		listaDetalleHuellaDto = new ListaDetalleHuellaDto();				
		
		vRespuesta = vServiciosFacturacionRest.obtieneComponentesArchivosCertificados(vSolicitud);
		
		mensajesBean.addMensajes(vRespuesta);
		
		if(vRespuesta.isOk())
		{
			listaDetalleHuellaDto = vRespuesta;
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Error al obtener la listade sistemas contribuyente', '')");
		}		
	}
	
	public void obtenerListadoSistemasFase2(Long pIdContribuyente)
	{
		ServiciosFacturacionQueryRestClient vServiciosFacturacionRest = new ServiciosFacturacionQueryRestClient();
		ListaSistemasSolicitudCertificacionDto vRespuestaSolicitud= new ListaSistemasSolicitudCertificacionDto();

		vRespuestaSolicitud.setDatosEntradaContribuyenteId(pIdContribuyente);
		
		listaSistemasSolicitudCertificacionDto = new ListaSistemasSolicitudCertificacionDto();				
		
		vRespuestaSolicitud = vServiciosFacturacionRest.obtenerListadoSistemasFase2(vRespuestaSolicitud);
		
		mensajesBean.addMensajes(vRespuestaSolicitud);
		
		if(vRespuestaSolicitud.isOk())
		{
			listaSistemasSolicitudCertificacionDto = vRespuestaSolicitud;
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

	public ListaDetalleHuellaDto getListaDetalleHuellaDto() {
		return listaDetalleHuellaDto;
	}

	public void setListaDetalleHuellaDto(ListaDetalleHuellaDto listaDetalleHuellaDto) {
		this.listaDetalleHuellaDto = listaDetalleHuellaDto;
	}

	public ListaSistemasSolicitudCertificacionDto getListaSistemasSolicitudCertificacionDto() {
		return listaSistemasSolicitudCertificacionDto;
	}

	public void setListaSistemasSolicitudCertificacionDto(
			ListaSistemasSolicitudCertificacionDto listaSistemasSolicitudCertificacionDto) {
		this.listaSistemasSolicitudCertificacionDto = listaSistemasSolicitudCertificacionDto;
	}
}
