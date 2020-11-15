package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.ContribuyentesModalidadesDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaVerificacionPruebasConcluidasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.model.AsignacionEquiposComputacionComponent;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.model.SeguimientoCertificacionSistemasList;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "gestionPruebasController")
public class GestionPruebasController implements Serializable {
	private static final long serialVersionUID = 1L;

	final static String ID_TAB_PANTALLAS = "tabPantallas";
	private String estado;
	private boolean mostrarVerificarInsitu;
	private boolean mostrarRegistroComponentes;

	private transient TabView tabview;
	private int activeTab;

	private EstadoDerivacionDto estadoDerivacionDto;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	private ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;
	
	@ManagedProperty(value = "#{seguimientoCertificacionSistemasList}")
	private SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList;		

	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	@ManagedProperty(value = "#{asignacionEquiposComputacionComponent}")
	private AsignacionEquiposComputacionComponent asignacionEquiposComputacionComponent;
	
	Map<String, Boolean> controlTabsGestionPruebas;
	
	@PostConstruct
	public void init() 
	{
		controlTabsGestionPruebas = new HashMap<String, Boolean>();
		
		controlTabsGestionPruebas.put("Pruebas_Generales", true);
		controlTabsGestionPruebas.put("Consulta_Huellas", false);
		controlTabsGestionPruebas.put("Registro_Visita_InSitu", false);
		controlTabsGestionPruebas.put("Documentos_Emitir", false);
	}
	
	public void ActualizarEstadoConsultaHuella()
	{
		controlTabsGestionPruebas.put("Pruebas_Generales", true);
		
		double vConsulta_Huellas = 0;
		
		for(int i=0; i < this.getSeguimientoCertificacionSistemasList().getlistaSeguimientoPruebasSistemas().size();i++)
		{
			System.out.println("PORCENTAJE OBTENIDO "+i+" : "+this.getSeguimientoCertificacionSistemasList().getlistaSeguimientoPruebasSistemas().get(i).getPorcentaje());
			vConsulta_Huellas = this.getSeguimientoCertificacionSistemasList().getlistaSeguimientoPruebasSistemas().get(i).getPorcentaje() + vConsulta_Huellas;
		}
		
		controlTabsGestionPruebas.put("Consulta_Huellas", (vConsulta_Huellas==100? true : false));
	}
	
	public void ActualizarEstadoConsultaVisitaInSitu()
	{	
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		ContribuyentesModalidadesDto vSolicitud= new ContribuyentesModalidadesDto();
		ContribuyentesModalidadesDto vEntrada= new ContribuyentesModalidadesDto();
		vSolicitud.setDatosEntradaNit(this.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getNit());
		
		vEntrada = vServiciosFacturacionRest.obtieneModalidadContribuyente(vSolicitud);
		
		if(vEntrada.isOk() && vEntrada.getInSitu()!=null && vEntrada.getInSitu()==1 && this.getControlTabsGestionPruebas().containsKey("Consulta_Huellas"))
		{
			controlTabsGestionPruebas.put("Registro_Visita_InSitu", true);
		}
		else
		{
			controlTabsGestionPruebas.put("Registro_Visita_InSitu", false);
		}
	}
	
	public void ActualizarEstadoConsultaDocumentoEmitir()
	{	
		int vContarEstadosTabsGestionPruebas = 0;
		
		if(this.getControlTabsGestionPruebas().containsKey("Documentos_Emitir"))
		{
			vContarEstadosTabsGestionPruebas++;
		}
		
		if(this.getControlTabsGestionPruebas().containsKey("Registro_Visita_InSitu"))
		{
			vContarEstadosTabsGestionPruebas++;
		}
		
		if(this.getControlTabsGestionPruebas().containsKey("Documentos_Emitir"))
		{
			vContarEstadosTabsGestionPruebas++;
		}
		
		if(vContarEstadosTabsGestionPruebas==3)
		{
			controlTabsGestionPruebas.put("Documentos_Emitir", true);
		}
	}

	public void Load() {

		this.setMostrarVerificarInsitu(true);
		this.setMostrarRegistroComponentes(true);

		this.obtenerContexto(estadoDerivacionDto);
		this.tramiteCertificacionBean.setearContexto();
		contextoSolicitudCertificacionSistemaModel.getSolicitud().setSolicitudId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		contextoSolicitudCertificacionSistemaModel.getSolicitud().setContribuyenteId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getPersonaContribuyenteId());
		contextoSolicitudCertificacionSistemaModel.getSolicitud()
				.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
		contextoSolicitudCertificacionSistemaModel.getSolicitud()
				.setTramiteId(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());
		asignacionEquiposComputacionComponent.getDatosSistemasSolCertificacion()
				.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
		asignacionEquiposComputacionComponent.getDatosSistemasSolCertificacion().setSolicitudCertificacionId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		asignacionEquiposComputacionComponent.obtenerAsignacionEquiposComputacion();

		long vPersonaContribuyente = tramiteCertificacionBean.getTramiteSolicitudCertificacion()
				.getPersonaContribuyenteId();
		MetodosPadron vMetodosPadron = new MetodosPadron();
		this.getContextoSolicitudCertificacionSistemaModel().getSolicitud()
				.setNit(vMetodosPadron.ObtenerDatosBasicosXIFC(vPersonaContribuyente).getNit());

		this.contextoSolicitudCertificacionSistemaModel.setPruebasConcluidas(false);
		this.validarEstadosPruebas();
		
		this.getSeguimientoCertificacionSistemasList().obtenerListadoSeguimientoCertificacionSistemas(this.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getSistemaId(), this.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getNit());
		this.getSeguimientoCertificacionSistemasList().setMostrarPanel(false);
		
		ActualizarEstadoConsultaHuella();
		ActualizarEstadoConsultaVisitaInSitu();
		ActualizarEstadoConsultaDocumentoEmitir();
	}

	public void onVerificarInSitu() {
		this.activeTab = 2;

		ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();
		RespuestaEstaRegistradoGenericoDto vVerificacionInsituCompletada = vRest
				.verificarObservacionComponenteInSituAprobadas(
						this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());

		this.contextoSolicitudCertificacionSistemaModel.setVisitaInsituValidada(false);
		if (vVerificacionInsituCompletada.isOk()) {
			this.contextoSolicitudCertificacionSistemaModel
					.setVisitaInsituValidada(vVerificacionInsituCompletada.isEstaRegistrado());
		}

		List<String> vComponentes = new ArrayList<>();
		vComponentes.add("pnlBotonera");
		vComponentes.add("pnlHuellas");
		RequestContext.getCurrentInstance().update(vComponentes);
	}

	public void onMostrarPruebas() {
		System.out.println("Mostrar Pruebas!!!");
	}

	public void validarEstadosPruebas() {
		ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();
		RespuestaVerificacionPruebasConcluidasDto vRespuesta = vRest
				.verificarPruebasFinalizadas(contextoSolicitudCertificacionSistemaModel.getSolicitud());		
		if (vRespuesta.isOk()) {
			if (vRespuesta.isPruebasConcluidas()) {
				long vNit = this.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getNit();
				RespuestaEstaRegistradoGenericoDto vRequiereInsituDto = vRest
						.verificarContribuyenteRequiereInSitu(vNit);
				SolicitudPruebasSistemasDto vDatosSolicitud = new SolicitudPruebasSistemasDto();
				vDatosSolicitud
						.setTramiteId(this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getTramiteId());
				vDatosSolicitud.setSolicitudId(
						this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());
				vDatosSolicitud
						.setSistemaId(this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId());
				RespuestaEstaRegistradoGenericoDto vPruebasManuales = vRest
						.verificarPruebasManualesTerminadas(vDatosSolicitud);

				this.contextoSolicitudCertificacionSistemaModel
						.setContribuyenteEnVisitaSitu(vRequiereInsituDto.isEstaRegistrado());

				this.contextoSolicitudCertificacionSistemaModel
						.setPruebasConcluidas(vRespuesta.isPruebasConcluidas() && vPruebasManuales.isEstaRegistrado());
			}

			RequestContext.getCurrentInstance().execute("toastr.info('Estado de pruebas verificado', '')");
		} else {
			RequestContext.getCurrentInstance()
					.execute("toastr.error('No se pudo verificar el estado de pruebas', '')");
		}

	}

	public void onRevisar() {
		this.validarEstadosPruebas();
		List<String> vComponentes = new ArrayList<>();
		vComponentes.add("pnlBotonera");
		vComponentes.add("pnlHuellas");
		RequestContext.getCurrentInstance().update(vComponentes);
	}

	public TabView getTabview() {
		return tabview;
	}

	public void setTabview(org.primefaces.component.tabview.TabView tabview) {
		this.tabview = tabview;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public void tabIsChanged(TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		TabView tabView = (TabView) event.getComponent();
		String activeIndexValue = params.get(tabView.getClientId(context) + "_tabindex");

		this.setActiveTab(Integer.parseInt(activeIndexValue));
	}

	public void obtenerContexto(EstadoDerivacionDto estado) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			estado = (EstadoDerivacionDto) sessionMap.get("EstadoProceso");
			this.estadoDerivacionDto = estado;
		} catch (Exception e) {
			this.estadoDerivacionDto = null;
		}
	}

	public EstadoDerivacionDto getEstadoDerivacionDto() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		estadoDerivacionDto = (EstadoDerivacionDto) sessionMap.get("EstadoProceso");
		return estadoDerivacionDto;
	}
	
	public void mostrarTabsTramiteGestionPruebas()
	{
		
		
		
		
		
	}

	public void setEstadoDerivacionDto(EstadoDerivacionDto estadoDerivacionDto) {
		this.estadoDerivacionDto = estadoDerivacionDto;
	}

	public AsignacionEquiposComputacionComponent getAsignacionEquiposComputacionComponent() {
		return asignacionEquiposComputacionComponent;
	}

	public void setAsignacionEquiposComputacionComponent(
			AsignacionEquiposComputacionComponent asignacionEquiposComputacionComponent) {
		this.asignacionEquiposComputacionComponent = asignacionEquiposComputacionComponent;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isMostrarVerificarInsitu() {
		return mostrarVerificarInsitu;
	}

	public void setMostrarVerificarInsitu(boolean mostrarVerificarInsitu) {
		this.mostrarVerificarInsitu = mostrarVerificarInsitu;
	}

	public boolean isMostrarRegistroComponentes() {
		return mostrarRegistroComponentes;
	}

	public void setMostrarRegistroComponentes(boolean mostrarRegistroComponentes) {
		this.mostrarRegistroComponentes = mostrarRegistroComponentes;
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}

	public SeguimientoCertificacionSistemasList getSeguimientoCertificacionSistemasList() {
		return seguimientoCertificacionSistemasList;
	}

	public void setSeguimientoCertificacionSistemasList(
			SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList) {
		this.seguimientoCertificacionSistemasList = seguimientoCertificacionSistemasList;
	}

	public Map<String, Boolean> getControlTabsGestionPruebas() {
		return controlTabsGestionPruebas;
	}

	public void setControlTabsGestionPruebas(Map<String, Boolean> controlTabsGestionPruebas) {
		this.controlTabsGestionPruebas = controlTabsGestionPruebas;
	}
}
