package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sre.fac.dto.RegistrosPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

/**
 * @Descripcion: Obtiene lista de Pruebas Manuales registradas para una
 *               solicitud de Sistema Fact.
 * @author hugo.quinonez
 * @version: 1.0.0.0/23/11/2018.
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "gestionPManualController")
@ViewScoped
public class GestionPruebasManualesController  implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	private List<RegistrosPruebasManualesDto> listaPruebasManuales;
	private List<RegistrosPruebasManualesDto> filtroPruebasManuales;
	private String obs;
	private RegistrosPruebasManualesDto pruebaManualTmp = new RegistrosPruebasManualesDto();

	ServiciosFacturacionRest vServiciosFacturacionRest;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	public void Load() {
		obtenerListaPruebasManuales();
	}

	/*
	 * @Descipcion: Crea stub para llenar la grilla de Pruebas automaticas.
	 ** 
	 * @author: hugo.quinonez
	 * 
	 */
	public void llenarListaPManual() {
		SolicitudPruebasSistemasDto pruebaManual = new SolicitudPruebasSistemasDto();
		listaPruebasManuales = new ArrayList<>();
	}

	public void obtenerListaPruebasManuales() {
		System.out.println("Ingresando a recuperar Pruebas manuales..");
		listaPruebasManuales = new ArrayList<>();
		RespuestaListaRegistroPruebasManualesDto vResultado = new RespuestaListaRegistroPruebasManualesDto();
		vServiciosFacturacionRest = new ServiciosFacturacionRest();

		vResultado = vServiciosFacturacionRest
				.obtenerDatosPruebasManualesSistema(contextoSolicitudCertificacionSistemaModel.getSolicitud());
		if (vResultado != null) {
			listaPruebasManuales = vResultado.getPruebasManuales();
//			System.out.println("Pruebas manuales encontradas=" + listaPruebasManuales.size());
		}
	}

	public void guardarCambiosPruebasManuales() {
//		pPruebasManualesDto.set
//		SolicitudPruebasSistemasDto pruebaManualModificada = new SolicitudPruebasSistemasDto();

		this.getPruebaManualTmp().setUsuarioUltimaModficacionId(contextoUsuarioModel.getUsuario().getUsuarioId());
		RespuestaActualizacionGenericoDto vRespuesta = vServiciosFacturacionRest
				.modificarObservacionEstadoPruebaManual(this.getPruebaManualTmp());
		if (vRespuesta.isOk()) {
			RequestContext.getCurrentInstance().execute("toastr.success('Cambio realizado.' , '')");
		} else {
			RequestContext.getCurrentInstance().execute("toastr.error('Cambio no realizado.' , '')");
		}

		System.out.println("Ingresando a guardar cambios...");
	}

	public void editarFilaPruebaManual(RegistrosPruebasManualesDto pRegistroPruebaManual) {
		RegistrosPruebasManualesDto pruebaManualTmp = new RegistrosPruebasManualesDto();
		setPruebaManualTmp(pRegistroPruebaManual);

		System.out.println("editando cambios: " + pruebaManualTmp.getObservaciones());
	}

	public List<RegistrosPruebasManualesDto> getListaPruebasManuales() {
		return listaPruebasManuales;
	}

	public void setListaPruebasManuales(List<RegistrosPruebasManualesDto> listaPruebasManuales) {
		this.listaPruebasManuales = listaPruebasManuales;
	}

	public List<RegistrosPruebasManualesDto> getFiltroPruebasManuales() {
		return filtroPruebasManuales;
	}

	public void setFiltroPruebasManuales(List<RegistrosPruebasManualesDto> filtroPruebasManuales) {
		this.filtroPruebasManuales = filtroPruebasManuales;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
		System.out.println("Nuevo valor para obs=" + this.obs);
	}

	public RegistrosPruebasManualesDto getPruebaManualTmp() {
		return pruebaManualTmp;
	}

	public void setPruebaManualTmp(RegistrosPruebasManualesDto pruebaManualTmp) {
		this.pruebaManualTmp = pruebaManualTmp;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	
}
