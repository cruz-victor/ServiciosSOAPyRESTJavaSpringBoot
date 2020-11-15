package bo.gob.sin.sre.fac.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.LazyDataModel;

import bo.gob.sin.sre.fac.dto.RegistrosPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.PruebasAutomaticasLazyDataModel;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

/**
 * @Descripcion: Obtiene lista de Pruebas Automaticas registradas para una
 *               solicitud de Sistema Fact.
 * @author hugo.quinonez
 * @version: 1.0.0.0/23/11/2018.
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "gestionPAutoController")
@RequestScoped
public class GestionPruebasAutomaticasController {

//	@ManagedProperty(value = "#{contextoUsuarioModel}")
//	ContextoUsuarioModel contextoUsuarioModel;
//		
//	@ManagedProperty(value = "#{mensajesBean}")
//    private MensajesBean mensajesBean;

	private List<RegistrosPruebasAutomaticasDto> listaPruebasAuto;
	private List<RegistrosPruebasAutomaticasDto> filtroPruebasAuto;

	private LazyDataModel<RegistrosPruebasAutomaticasDto> model;

	ServiciosFacturacionRest vServiciosFacturacionRest;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	public void Load() {
		System.out.println("Ingresando a Obtener P. Automaticas.");
		obtenerListaPruebasAuto();
		// System.out.println("Se recuperó "+listaPruebasAuto.size()+" filas");
	}

	public void obtenerListaPruebasAuto() {
		model = new PruebasAutomaticasLazyDataModel(contextoSolicitudCertificacionSistemaModel);
//		listaPruebasAuto = new ArrayList<>();
//
//		RespuestaListaRegistroPruebasAutomaticasDto vResultado = new RespuestaListaRegistroPruebasAutomaticasDto();
//		vServiciosFacturacionRest = new ServiciosFacturacionRest();
//		vResultado = vServiciosFacturacionRest
//				.obtenerDatosPruebasAutomaticasSistema(contextoSolicitudCertificacionSistemaModel.getSolicitud());
//		if (vResultado != null) {
//			listaPruebasAuto = vResultado.getPruebasAutomaticas();
//		} else {
//			System.out.println("Error al obtener Datos P. Automáticas.");
//		}
	}

	public List<RegistrosPruebasAutomaticasDto> getListaPruebasAuto() {
		return listaPruebasAuto;
	}

	public void setListaPruebasAuto(List<RegistrosPruebasAutomaticasDto> listaPruebasAuto) {
		this.listaPruebasAuto = listaPruebasAuto;
	}

	public List<RegistrosPruebasAutomaticasDto> getFiltroPruebasAuto() {
		return filtroPruebasAuto;
	}

	public void setFiltroPruebasAuto(List<RegistrosPruebasAutomaticasDto> filtroPruebasAuto) {
		this.filtroPruebasAuto = filtroPruebasAuto;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public LazyDataModel<RegistrosPruebasAutomaticasDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<RegistrosPruebasAutomaticasDto> model) {
		this.model = model;
	}

}
