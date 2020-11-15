package bo.gob.sin.sre.fac.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.BitacoraObservacionComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.BitacorasObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.VerificacionInsituModel;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@SuppressWarnings("deprecation")
@ManagedBean(name = "gestionInsituController")
@RequestScoped
public class GestionVerificacionInsituController {

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	@ManagedProperty(value = "#{verificacionInsituModel}")
	VerificacionInsituModel verificacionInsituModelo;

	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	public void initVista() 
	{
		this.getVerificacionInsituModelo().obtenerlistaComponentes();
		this.getVerificacionInsituModelo().obtenerlistaVerificaciones();
		
		if (!FacesContext.getCurrentInstance().isPostback()) 
		{
			this.verificacionInsituModelo.setFiltroComponentes(this.verificacionInsituModelo.getListaComponentes());
		}
	}

	public void mostrar() 
	{
		int row = this.verificacionInsituModelo.getFilaEdit();
//		this.verificacionInsituModelo.setCont(this.verificacionInsituModelo.getCont()+1);
//		BitacoraObservacionComponentesInsituDto nueva = new BitacoraObservacionComponentesInsituDto();
//		nueva.setObservaciones("OBSERVACION EXTRA - "+this.verificacionInsituModelo.getCont());
//		verificacionInsituModelo.getListaComponentes().get(row).getBitacorasObservacionesComponentesInsitu().add(nueva);
		verificacionInsituModelo.getListaComponentes().get(row).getBitacorasObservacionesComponentesInsitu().add(verificacionInsituModelo.getNuevaObservacion());
		// System.out.println("ENTRO A GUARDAR.." + nueva.getObservaciones()+" De la
		// fila "+ row);
		// RequestContext.getCurrentInstance().update("idPanelTabla");
		// guardarNuevaObservacion();
	}

	public void validarFecha() 
	{
		
	}

	public void nuevaObservacion(RegistroObservacionesComponentesInsituDto pComponente) 
	{
		// Actualizar Dialog
		this.verificacionInsituModelo.setNuevaObservacion(new BitacoraObservacionComponentesInsituDto());
		this.verificacionInsituModelo.getNuevaObservacion().setEstadoId(pComponente.getEstadoId());
		//
		this.verificacionInsituModelo.setComponenteConObs(new RegistroObservacionesComponentesInsituDto());

		RegistroObservacionesComponentesInsituDto vComponenteIObservacionAux = new RegistroObservacionesComponentesInsituDto();
		vComponenteIObservacionAux.setObservacionComponenteInsituId(pComponente.getObservacionComponenteInsituId());
		vComponenteIObservacionAux.setEstadoId(pComponente.getEstadoId());
		vComponenteIObservacionAux.setEstadoPruebaDescripcion(pComponente.getEstadoPruebaDescripcion());
		vComponenteIObservacionAux.setEstadoPruebaId(pComponente.getEstadoPruebaId());
		
		vComponenteIObservacionAux.setObservacionComponenteInsituId(pComponente.getObservacionComponenteInsituId());
		vComponenteIObservacionAux.setSolicitudCertificacionId(pComponente.getSolicitudCertificacionId());
		vComponenteIObservacionAux.setTipoComponenteDescripcion(pComponente.getTipoComponenteDescripcion());
		vComponenteIObservacionAux.setTipoComponenteId(pComponente.getTipoComponenteId());
		vComponenteIObservacionAux.setUsuarioUltimaModificacionId(contextoUsuarioModel.getUsuario().getUsuarioId());

		this.verificacionInsituModelo.setComponenteConObs(vComponenteIObservacionAux);
		this.verificacionInsituModelo.setObservacionAcomp(new BitacorasObservacionesComponentesInsituDto());
		this.verificacionInsituModelo.setNuevaObservacion(new BitacoraObservacionComponentesInsituDto());
	}

	public void guardarNuevaObservacion() {
		int row = this.verificacionInsituModelo.getFilaEdit();
		// System.out.println("ENTRO A GUARDAR.." +
		// this.verificacionInsituModelo.getNuevaVerificacion().getObservacion()+" De la
		// fila "+ row);
		long vUsuarioId = contextoUsuarioModel.getUsuario().getUsuarioId();
		this.verificacionInsituModelo.getNuevaObservacion().setUsuarioUltimaModificacionId(vUsuarioId);
		this.verificacionInsituModelo.getNuevaObservacion().setUsuarioRegistroId(vUsuarioId);
		this.verificacionInsituModelo.getNuevaObservacion().setEstadoPruebaId(this.verificacionInsituModelo.getComponenteConObs().getEstadoPruebaId());
		this.verificacionInsituModelo.getNuevaObservacion().setObservacionComponenteInsituId(this.verificacionInsituModelo.getComponenteConObs().getObservacionComponenteInsituId());

		this.verificacionInsituModelo.setListaObservacionAcomp(new ArrayList<>());
		this.verificacionInsituModelo.getListaObservacionAcomp().add(this.verificacionInsituModelo.getNuevaObservacion());
		this.verificacionInsituModelo.getComponenteConObs().setBitacorasObservacionesComponentesInsitu(this.verificacionInsituModelo.getListaObservacionAcomp());

		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		RegistroObservacionesComponentesInsituDto vResultado = new RegistroObservacionesComponentesInsituDto();
		vResultado = vServiciosFacturacionRest.registroNuevaObservacionComponentesInsitu(this.verificacionInsituModelo.getComponenteConObs());

		if (vResultado.isOk()) 
		{
			this.getVerificacionInsituModelo().obtenerlistaComponentes();
			RequestContext.getCurrentInstance().execute("toastr.success('Se guardo la Observacion.', '')");
//			this.verificacionInsituModelo.getComponenteConObs().setFechaRegistro(vResultado.getFechaRegistro());
//			verificacionInsituModelo.getListaComponentes().get(row).setEstadoPruebaDescripcion(this.verificacionInsituModelo.getComponenteConObs().getEstadoPruebaDescripcion());
//			verificacionInsituModelo.getListaComponentes().get(row).getBitacorasObservacionesComponentesInsitu().add(
//					this.verificacionInsituModelo.getComponenteConObs().getBitacorasObservacionesComponentesInsitu().get(0));
		} 
		else 
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Error al guardar la Observacion.', '')");
		}
	}

	public void guardarNuevaVerificacion() 
	{

		RespuestaRegistrosObservacionesInsituDto vResultado = new RespuestaRegistrosObservacionesInsituDto();

		long vUsuarioId = contextoUsuarioModel.getUsuario().getUsuarioId();
		this.verificacionInsituModelo.getNuevaVerificacion().setUsuarioRegistroId(vUsuarioId);
		this.verificacionInsituModelo.getNuevaVerificacion().setSolicitudCertificacionId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());

		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		vResultado = vServiciosFacturacionRest.registroNuevaObservacionVerificacionInsitu(this.verificacionInsituModelo.getNuevaVerificacion());
		
		if (vResultado.isOk()) 
		{
			this.getVerificacionInsituModelo().obtenerlistaVerificaciones();
			RequestContext.getCurrentInstance().execute("toastr.success('Datos Guardados de la nueva Verificacion.', '')");
		} 
		else 
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Error al guardar la Verificación.', '')");
		}
	}

	public void mostrarNuevaObservacion() 
	{
		this.verificacionInsituModelo.setCont(this.verificacionInsituModelo.getCont() + 1);
		System.out.println(this.verificacionInsituModelo.getCont() + "> Mi Observacion = " + this.verificacionInsituModelo.getNuevaObservacion().getObservaciones());
		System.out.println(this.verificacionInsituModelo.getCont() + "> Mi Estado = " + this.verificacionInsituModelo.getComponenteConObs().getEstadoPruebaId());
	}

	public void nuevaVerificacion() 
	{
		this.verificacionInsituModelo.getNuevaVerificacion().setFechaVerificacion(new Date());
		this.verificacionInsituModelo.getNuevaVerificacion().setObservacion(null);
	}

	public void verResultradoFiltro() 
	{
		RequestContext.getCurrentInstance().execute("toastr.success('Datos Filtrados.', '')");
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public VerificacionInsituModel getVerificacionInsituModelo() {
		return verificacionInsituModelo;
	}

	public void setVerificacionInsituModelo(VerificacionInsituModel verificacionInsituModelo) {
		this.verificacionInsituModelo = verificacionInsituModelo;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

}
