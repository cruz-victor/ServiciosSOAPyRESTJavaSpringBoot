package bo.gob.sin.sre.fac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.GestionPruebasListaHuellasDigitalesModel;
import bo.gob.sin.sre.fac.model.RegistroHuellasDigitalesComponent;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean(name = "gestionPruebasListaHuellasDigitalesController")
@RequestScoped
public class GestionPruebasListaHuellasDigitalesController {

	@ManagedProperty(value = "#{gestionPruebasListaHuellasDigitalesModel}")
	GestionPruebasListaHuellasDigitalesModel gestionPruebasListaHuellasDigitalesModel;

	ServiciosFacturacionRest vServiciosFacturacionRest;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	@ManagedProperty(value = "#{registroHuellasDigitalesComponent}")
	private RegistroHuellasDigitalesComponent registroHuellasDigitalesComponent;

	@ManagedProperty(value = "#{certificacionParametrics}")
	private CertificacionParametrics certificacionParametrics;

	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	private List<RegistroHuellasDigitalesDto> listaHuellasDigitales;

	private List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitalesDtoAux;

	public List<RegistroHuellasDigitalesDto> getListaHuellasDigitales() {
		return listaHuellasDigitales;
	}

	public void setListaHuellasDigitales(List<RegistroHuellasDigitalesDto> listaHuellasDigitales) {
		this.listaHuellasDigitales = listaHuellasDigitales;
	}

	public GestionPruebasListaHuellasDigitalesModel getGestionPruebasListaHuellasDigitalesModel() {
		return gestionPruebasListaHuellasDigitalesModel;
	}

	public void setGestionPruebasListaHuellasDigitalesModel(
			GestionPruebasListaHuellasDigitalesModel gestionPruebasListaHuellasDigitalesModel) {
		this.gestionPruebasListaHuellasDigitalesModel = gestionPruebasListaHuellasDigitalesModel;
	}

	public void Load() 
	{
		if (!FacesContext.getCurrentInstance().isPostback())
		{
			this.GenerarListadoHuellas();
		}		
	}

	public void GenerarListadoHuellas() {
		System.out.println("Bean entra a generar GenerarListadoHuellas(): ");

		gestionPruebasListaHuellasDigitalesModel.setListaHuellasDigitales(new ArrayList<>());

		ListaRegistroHuellasDigitalesDto vResultado = new ListaRegistroHuellasDigitalesDto();
		vServiciosFacturacionRest = new ServiciosFacturacionRest();

		Long pSistema;
		pSistema = contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId();
		vResultado = vServiciosFacturacionRest.obtieneRegistroHuellasDigitales(pSistema);
		listaHuellasDigitales = vResultado.getListaRegistroHuellasDigitales();
		System.out.println("Sale de generar GenerarListadoHuellas(): ");
	}

	public void eliminarRegistro() {

		List<RegistroHuellasDigitalesDto> vListaRegistroHuellasDigitalesDto = new ArrayList<RegistroHuellasDigitalesDto>();
		RegistroHuellasDigitalesDto vRegistroHuellasDigitalesDto = new RegistroHuellasDigitalesDto();
		vListaRegistroHuellasDigitalesDto = registroHuellasDigitalesComponent.getListaRegistroHuellasDigitalesDto();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		int fila = (int) request.getAttribute("rowIndex");
		vRegistroHuellasDigitalesDto = vListaRegistroHuellasDigitalesDto.get(fila);

		for (Integer clasificadorComponenteMinimoId : vRegistroHuellasDigitalesDto.getTipoComponenteId()) {
			ClasificadorDto clasificadorComponenteMinimoDto = registroHuellasDigitalesComponent
					.getListaClasificadorComponenteMinimo().stream()
					.filter(x -> x.getClasificadorId().equals(clasificadorComponenteMinimoId)).findFirst().orElse(null);
			certificacionParametrics.getListaComponentesMinimos().add(clasificadorComponenteMinimoDto);
		}
		getRegistroHuellasDigitalesComponent().getListaRegistroHuellasDigitalesDto()
				.remove(vRegistroHuellasDigitalesDto);
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
				.add("RegistroResultadoVerificacionSituForm:idPanelComponente");
		RequestContext.getCurrentInstance().update("RegistroResultadoVerificacionSituForm:idPanelComponente");

	}

	public void onRegistroComponentes() {
		if (this.contextoSolicitudCertificacionSistemaModel.isPruebasConcluidas()) {
			if (registroHuellasDigitalesComponent.getListaRegistroHuellasDigitalesDto() != null) {

				if (registroHuellasDigitalesComponent.getListaRegistroHuellasDigitalesDto().isEmpty()) {
					RequestContext.getCurrentInstance()
							.execute("toastr.error('Debe cargar la grilla de componentes mínimos.', '')");
					return;
				}

				if (!registroHuellasDigitalesComponent.getCertificacionParametrics().getListaComponentesMinimos()
						.isEmpty()) {
					RequestContext.getCurrentInstance()
							.execute("toastr.error('Faltan componentes mínimos que debe cargar.', '')");
					return;
				}

				RegistroHuellasDigitalesDto vResultado = new RegistroHuellasDigitalesDto();

				registroHuellasDigitalesComponent.getListaRegistroHuellasDigitalesDto().forEach(
						x -> x.setSistemaId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId()));

				vResultado = registroHuellasDigitalesComponent.RegistrarHuellasDigitalesSistemas(
						registroHuellasDigitalesComponent.getListaRegistroHuellasDigitalesDto());
				if (vResultado != null) {
					registroHuellasDigitalesComponent
							.setListaRegistroHuellasDigitalesDto(listaRegistroHuellasDigitalesDtoAux);
					FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
							.add("RegistroResultadoVerificacionSituForm:idPanelComponente");
					RequestContext.getCurrentInstance()
							.update("RegistroResultadoVerificacionSituForm:idPanelComponente");
					mensajesBean.addMensajes(vResultado);

					this.contextoSolicitudCertificacionSistemaModel.setGuardoHuellas(true);

				} else {
					mensajesBean.addMensajes(vResultado);
				}

			} else {
				RequestContext.getCurrentInstance().execute("toastr.error('Los componentes ya se registraron.', '')");
				return;

			}
		} else {
			RequestContext.getCurrentInstance().execute("toastr.error('Deben concluirse las pruebas.', '')");
		}
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public RegistroHuellasDigitalesComponent getRegistroHuellasDigitalesComponent() {
		return registroHuellasDigitalesComponent;
	}

	public void setRegistroHuellasDigitalesComponent(
			RegistroHuellasDigitalesComponent registroHuellasDigitalesComponent) {
		this.registroHuellasDigitalesComponent = registroHuellasDigitalesComponent;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public List<RegistroHuellasDigitalesDto> getListaRegistroHuellasDigitalesDtoAux() {
		return listaRegistroHuellasDigitalesDtoAux;
	}

	public void setListaRegistroHuellasDigitalesDtoAux(
			List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitalesDtoAux) {
		this.listaRegistroHuellasDigitalesDtoAux = listaRegistroHuellasDigitalesDtoAux;
	}

}
