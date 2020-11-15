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
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionGiecRest;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "listadoArchivosDiagramaList")
@ViewScoped
 
public class ListadoArchivosDiagramaList implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private ListaDiagramasCertificacionesDto listaDiagramasCertificaciones;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		listaDiagramasCertificaciones = new ListaDiagramasCertificacionesDto();
	}
	
	public void obtieneListaDiagramasCertificaciones(Long pEtapaId, Long pSistema, Long pSolicitudCertificacionId)
	{
		ServiciosConsultaFacturacionRestClient vServiciosFacturacionRest = new ServiciosConsultaFacturacionRestClient();
		ListaDiagramasCertificacionesDto vRespuestaRespuestaSalida = new ListaDiagramasCertificacionesDto();
		
		vRespuestaRespuestaSalida.setDatosEntradaEtapaId(pEtapaId);
		vRespuestaRespuestaSalida.setDatosEntradaSistemaId(pSistema);
		vRespuestaRespuestaSalida.setDatosEntradaSolicitudCertificacionId(pSolicitudCertificacionId);
		
		listaDiagramasCertificaciones = new ListaDiagramasCertificacionesDto();
		
		vRespuestaRespuestaSalida = vServiciosFacturacionRest.obtieneListaDiagramasCertificaciones(vRespuestaRespuestaSalida);
		
		mensajesBean.addMensajes(vRespuestaRespuestaSalida);
		
		if(vRespuestaRespuestaSalida.isOk())
		{
			listaDiagramasCertificaciones = vRespuestaRespuestaSalida;
		}	
	}
	
	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	/**
	 * @return the listaDiagramasCertificaciones
	 */
	public ListaDiagramasCertificacionesDto getListaDiagramasCertificaciones() {
		return listaDiagramasCertificaciones;
	}

	/**
	 * @param listaDiagramasCertificaciones the listaDiagramasCertificaciones to set
	 */
	public void setListaDiagramasCertificaciones(ListaDiagramasCertificacionesDto listaDiagramasCertificaciones) {
		this.listaDiagramasCertificaciones = listaDiagramasCertificaciones;
	}
}
