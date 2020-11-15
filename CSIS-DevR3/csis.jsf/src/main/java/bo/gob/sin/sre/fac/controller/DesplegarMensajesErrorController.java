package bo.gob.sin.sre.fac.controller;


import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.CertificacionSolicitudCertificacionList;
import bo.gob.sin.sre.fac.model.CertificacionSolicitudCertificacionModel;
import bo.gob.sin.sre.fac.model.ComportamientoPaginaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.reports.ReportesController;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.sre.fac.thread.HiloEncolador;
//import bo.gob.sin.sre.fac.thread.HiloEncolador;
import net.sf.jasperreports.engine.JRException;

/**
* @Descripcion: Inicia Solicitud el Proveedor.
* @author: Wilson Limachi.
* @version: 1.0.0.1
* @fecha: 31/07/2019.
*/

@ManagedBean(name = "desplegarMensajesErrorController")
@RequestScoped
public class DesplegarMensajesErrorController implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	/**
	* @Descipcion: Descripción Controla el estado del cambio de Pagina de estado.
	* @author: Wilson Limachi. 
	*/
	public void Load() 
	{		
		
	}
	
	/**
	* @Descipcion: Desplega mensjaes de error general. 
	* @author: Wilson Limachi. 
	* @param pMensaje: Mensaje enviado
	*/
	public void desplegarMensajesError(String pMensaje)
	{
		if(pMensaje !=null && pMensaje != "")
		{	
			RequestContext.getCurrentInstance().execute("toastr.error('"+pMensaje +"', '')");
		}
	}
}
