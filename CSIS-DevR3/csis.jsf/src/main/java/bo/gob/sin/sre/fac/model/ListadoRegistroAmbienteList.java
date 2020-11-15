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
import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetalleCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionGiecRest;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "listadoRegistroAmbienteList")
@ViewScoped
 
public class ListadoRegistroAmbienteList implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private List<DetallesCertificacionesSistemasDto> listaDetallesCertificacionesSistemas;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		listaDetallesCertificacionesSistemas = new ArrayList<>();		
	}
	
	public void obtieneListaDiagramasCertificaciones(Long pEtapaId, Long pSistema, Long pSolicitudCertificacionId)
	{
		ServiciosConsultaFacturacionRestClient vServiciosFacturacionRest = new ServiciosConsultaFacturacionRestClient();
		RespuestaListaDetalleCertificacionesSistemasDto vRespuestaRespuestaSalida = new RespuestaListaDetalleCertificacionesSistemasDto();
		
//		vRespuestaRespuestaSalida.setDatosEntradaEtapaId(pEtapaId);
//		vRespuestaRespuestaSalida.setDatosEntradaSistemaId(pSistema);
//		vRespuestaRespuestaSalida.setDatosEntradaSolicitudCertificacionId(pSolicitudCertificacionId);
		
		listaDetallesCertificacionesSistemas = new ArrayList<>();
		DetallesCertificacionesSistemasDto vObjDetallesCertificacionesSistemasDto = new DetallesCertificacionesSistemasDto();
		vObjDetallesCertificacionesSistemasDto.setSolicitudCertificacionId(pSolicitudCertificacionId); 
		vObjDetallesCertificacionesSistemasDto.setSistemaId(pSistema);
		vRespuestaRespuestaSalida = vServiciosFacturacionRest.obtieneListaDetallesCertificacionesSistemas(vObjDetallesCertificacionesSistemasDto);			
		
		if(vRespuestaRespuestaSalida.isOk())		
			listaDetallesCertificacionesSistemas = vRespuestaRespuestaSalida.getListaCertificacionesSistemasDto();
		else
			mensajesBean.addMensajes(vRespuestaRespuestaSalida);
	}
	
	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public List<DetallesCertificacionesSistemasDto> getListaDetallesCertificacionesSistemas() {
		return listaDetallesCertificacionesSistemas;
	}

	public void setListaDetallesCertificacionesSistemas(
			List<DetallesCertificacionesSistemasDto> listaDetallesCertificacionesSistemas) {
		this.listaDetallesCertificacionesSistemas = listaDetallesCertificacionesSistemas;
	}
}
