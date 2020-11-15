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
import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaSistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionGiecRest;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "archivosDiagramaModel")
@ViewScoped
 
public class ArchivosDiagramaModel implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private DiagramasCertificacionesDto diagramasCertificaciones;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init()
	{
		diagramasCertificaciones = new DiagramasCertificacionesDto();
	}
	
	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	/**
	 * @return the diagramasCertificaciones
	 */
	public DiagramasCertificacionesDto getDiagramasCertificaciones() {
		return diagramasCertificaciones;
	}

	/**
	 * @param diagramasCertificaciones the diagramasCertificaciones to set
	 */
	public void setDiagramasCertificaciones(DiagramasCertificacionesDto diagramasCertificaciones) {
		this.diagramasCertificaciones = diagramasCertificaciones;
	}
}
