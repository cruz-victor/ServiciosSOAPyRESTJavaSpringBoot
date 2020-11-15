package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.util.StringUtils;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.DatosSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasDto;
import bo.gob.sin.sre.fac.dto.ObtenerListSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroAutorizacionRechazoCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarReCertificacionSistemasDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@ManagedBean(name = "certificacionSolicitudCertificacionModel")
@ViewScoped
public class CertificacionSolicitudCertificacionModel  implements Serializable
{	
	private SolicitudRegistrarCertificacionSistemasDto solicitud;
	private DatosSistemasDto datosSistemasDto;
	private RegistroAutorizacionRechazoCertificacionDto solicitudRegistroAutorizacionRechazo; 
	private ListaSistemasDto listaSolicitudesActCertificacion;
	private RespuestaRegistrarSolicitudCertificacionDto solicitudCertificacionDto; 
	private SolicitudRegistrarReCertificacionSistemasDto pSolicitudRecertificacion;
	private static final long serialVersionUID = 1L;

	private boolean estadoFinalizadoSolicitud;
	private boolean estadoFinalizadoActualizacionSolicitud;
	private boolean esVisibleLlavePublica;	
	private String motivoRechazo;
	private String motivoAprovacion;  

	private RespuestaActualizacionDto resultado;

	private List<SistemasDto> listaSistemasEnVerificacion;
	private List<SistemasDto> listaSistemasEnVerificacionFiltrar;
	private SistemasDto solicitudSistema;
	
	private String observacion;
	private Date fechaInspeccion;
	private boolean mostrarGrilla;
	private boolean mostrarPanel;
	private int tipoResultadoCertificacion;
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	/**
	* @Descipcion: inicializa y carga los datos por defecto. 
	* @author: Wilson Limachi.
	*/
	@PostConstruct
	public void init() {
		solicitud = new SolicitudRegistrarCertificacionSistemasDto();
		pSolicitudRecertificacion = new SolicitudRegistrarReCertificacionSistemasDto();
		datosSistemasDto = new DatosSistemasDto();
		solicitudCertificacionDto = new RespuestaRegistrarSolicitudCertificacionDto();
		solicitudRegistroAutorizacionRechazo = new RegistroAutorizacionRechazoCertificacionDto();
		this.listaSolicitudesActCertificacion = new ListaSistemasDto();
		this.borrarDatos();
		//this.setEstadoFinalizadoSolicitud(false);
		this.setEstadoFinalizadoActualizacionSolicitud(false);
		this.setEsVisibleLlavePublica(false);
		
		resultado = new RespuestaActualizacionDto();
		
		listaSistemasEnVerificacion = new ArrayList<>();
		solicitudSistema = new SistemasDto();
	}
	
	/**
	* @Descipcion: obtiene los sistemas del contribuyente. 
	* @author: Wilson Limachi. 
	* @param idContribuyente: identificador contribuyente.
	*/
	public void obtenerListaSistemasActualizarIdContribuyente(Long idContribuyente)
	{
		ListaSistemasDto vResultado = new ListaSistemasDto();
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		SolicitudActualizaSolicitudCertificacionDto vSolicitud = new SolicitudActualizaSolicitudCertificacionDto();
		vSolicitud.setContribuyenteId(idContribuyente);
		vResultado = vServiciosFacturacionRest.listarSistemasParaActualizarIdContribuyente(vSolicitud);
		
		if(vResultado.isOk())
		{
			this.listaSolicitudesActCertificacion = vResultado;
		}
		else
		{
			this.listaSolicitudesActCertificacion = new ListaSistemasDto();
		}	
		
		mensajesBean.addMensajes(vResultado);
	}
	
//	/**
//	* @Descipcion: Cancela el proceso y restablece valores. 
//	* @author: Wilson Limachi. 
//	* @param sistemaId: Llave primaria del dto DatosSistemasDto.
//	*/
//	public void ObtenerDatos(Long sistemaId)
//	{
//		DatosSistemasDto vSolicitud = new DatosSistemasDto();
//		vSolicitud.setSistemaId(sistemaId);
//		ServiciosFacturacionRest vServiciosFacturacionRest= new ServiciosFacturacionRest();
//		
//		datosSistemasDto = vServiciosFacturacionRest.recuperarDatosSistemaCertificacion(vSolicitud);		
//	}

	/**
	* @Descipcion: restablece valores. 
	* @author: Wilson Limachi. 
	*/
	public void borrarDatos() 
	{		
		this.setSolicitud(new SolicitudRegistrarCertificacionSistemasDto());				
		
		if(!this.getSolicitud().getTiposModulos().isEmpty())
		{
			this.getSolicitud().getTiposModulos().removeAll(this.getSolicitud().getTiposModulos());
		}	
		
		this.setEsVisibleLlavePublica(false);
	}

	/**
	* @Descipcion: Valida Campos. 
	* @author: Wilson Limachi. 
	*/
	public boolean esValidoCampos() {
		boolean verificar = false;
		verificar = this.solicitud.getTipoSistemaId() != null 
				&& StringUtils.hasText(this.solicitud.getNombreSistema()) && StringUtils.hasText(this.solicitud.getVersion() + "")
				&& !this.solicitud.getTiposModulos().isEmpty();
		
		if(!verificar)
		{
			//if(this.getSolicitud().getListaContactos().size())
		}
		
		return verificar;
	}
	
	/**
	* @Descipcion: Valida Campos para Actualización. 
	* @author: Wilson Limachi. 
	*/
	public boolean esValidoCamposActualizar() {
		boolean verificar = false;
		verificar = this.solicitud.getTipoSistemaId()!=null 
				&& StringUtils.hasText(this.solicitud.getVersion() + "")
				&& !this.solicitud.getTiposModulos().isEmpty();
		return verificar;
	}
	
	/**
	* @Descipcion: Valida si un correo es válido.
	* @author: Wilson Limachi.
	* @param pEmail: Valor de un Correo Electrónico.
	*/
	public boolean esValidoCorreoElectronico(String pEmail)
	{
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(pEmail);
		return m.matches();
	}
	
	/**
	* @Descipcion: Afirma el valor de los Beans.
	* @author: Wilson Limachi.
	* @param e: Representa el comportamiento del componente específico de Ajax
	*/
	public void AfirmarValor(AjaxBehaviorEvent e)
	{
		 System.out.println("listener");
	}

	
	/**
	 * Recupera Lista Sistemas en VERIFICACION, para Registro insitu
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 31/08/2018
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve lista
	 */
	public void recuperarListaSistemasEnVerificacion(Long pContribuyenteId) {
		ObtenerListSistemaDto vResultado = new ObtenerListSistemaDto();
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		ObtenerListSistemaDto vSolicitud = new ObtenerListSistemaDto();
		vSolicitud.setPersonaContribuyenteId(pContribuyenteId);
		vSolicitud.setEstadoSolicitudCertificacionId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_VERIFICACION);
		vResultado = vServiciosFacturacionRest.recuperaSistemas(vSolicitud);
		if (vResultado.isOk()) {
			if (vResultado.getListaSistemas().isEmpty()) {
				mensajesBean.addMensajes(vResultado);
			} else {
				listaSistemasEnVerificacion = vResultado.getListaSistemas();
				mensajesBean.addMensajes(vResultado);
			}
		} else {
			mensajesBean.addMensajes(vResultado);
		}
	}
	public boolean isEsVisibleLlavePublica() {
		return esVisibleLlavePublica;
	}

	public void setEsVisibleLlavePublica(boolean esVisibleLlavePublica) {
		this.esVisibleLlavePublica = esVisibleLlavePublica;
	}
	
	public boolean isEstadoFinalizadoSolicitud() {
		return estadoFinalizadoSolicitud;
	}

	public void setEstadoFinalizadoSolicitud(boolean estadoFinalizadoSolicitud) {
		this.estadoFinalizadoSolicitud = estadoFinalizadoSolicitud;
	}
	
	public boolean isEstadoFinalizadoActualizacionSolicitud() {
		return estadoFinalizadoActualizacionSolicitud;
	}

	public void setEstadoFinalizadoActualizacionSolicitud(boolean estadoFinalizadoActualizacionSolicitud) {
		this.estadoFinalizadoActualizacionSolicitud = estadoFinalizadoActualizacionSolicitud;
	}

	public DatosSistemasDto getDatosSistemasDto() 
	{		
		return datosSistemasDto;
	}

	public void setDatosSistemasDto(DatosSistemasDto datosSistemasDto) {
		this.datosSistemasDto = datosSistemasDto;
	}

	public RegistroAutorizacionRechazoCertificacionDto getSolicitudRegistroAutorizacionRechazo() {
		return solicitudRegistroAutorizacionRechazo;
	}

	public void setSolicitudRegistroAutorizacionRechazo(
			RegistroAutorizacionRechazoCertificacionDto solicitudRegistroAutorizacionRechazo) {
		this.solicitudRegistroAutorizacionRechazo = solicitudRegistroAutorizacionRechazo;
	}
	
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getMotivoAprovacion() {
		return motivoAprovacion;
	}

	public void setMotivoAprovacion(String motivoAprovacion) {
		this.motivoAprovacion = motivoAprovacion;
	}

	public ListaSistemasDto getListaSolicitudesActCertificacion() {
		return listaSolicitudesActCertificacion;
	}

	public void setListaSolicitudesActCertificacion(ListaSistemasDto listaSolicitudesActCertificacion) {
		this.listaSolicitudesActCertificacion = listaSolicitudesActCertificacion;
	}

	public RespuestaActualizacionDto getResultado() {
		return resultado;
	}

	public void setResultado(RespuestaActualizacionDto resultado) {
		this.resultado = resultado;
	}

	public RespuestaRegistrarSolicitudCertificacionDto getSolicitudCertificacionDto() {
		return solicitudCertificacionDto;
	}

	public void setSolicitudCertificacionDto(RespuestaRegistrarSolicitudCertificacionDto solicitudCertificacionDto) {
		this.solicitudCertificacionDto = solicitudCertificacionDto;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public List<SistemasDto> getListaSistemasEnVerificacion() {
		return listaSistemasEnVerificacion;
	}

	public void setListaSistemasEnVerificacion(List<SistemasDto> listaSistemasEnVerificacion) {
		this.listaSistemasEnVerificacion = listaSistemasEnVerificacion;
	}

	public SistemasDto getSolicitudSistema() {
		return solicitudSistema;
	}

	public void setSolicitudSistema(SistemasDto solicitudSistema) {
		this.solicitudSistema = solicitudSistema;
	}

	public List<SistemasDto> getListaSistemasEnVerificacionFiltrar() {
		return listaSistemasEnVerificacionFiltrar;
	}

	public void setListaSistemasEnVerificacionFiltrar(List<SistemasDto> listaSistemasEnVerificacionFiltrar) {
		this.listaSistemasEnVerificacionFiltrar = listaSistemasEnVerificacionFiltrar;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaInspeccion() {
		return fechaInspeccion;
	}

	public void setFechaInspeccion(Date fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}

	public boolean isMostrarGrilla() {
		return mostrarGrilla;
	}

	public void setMostrarGrilla(boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	public boolean isMostrarPanel() {
		return mostrarPanel;
	}

	public void setMostrarPanel(boolean mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}

	public SolicitudRegistrarCertificacionSistemasDto getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudRegistrarCertificacionSistemasDto solicitud) {
		this.solicitud = solicitud;
	}

	public int getTipoResultadoCertificacion() {
		return tipoResultadoCertificacion;
	}

	public void setTipoResultadoCertificacion(int tipoResultadoCertificacion) {
		this.tipoResultadoCertificacion = tipoResultadoCertificacion;
	}

	public SolicitudRegistrarReCertificacionSistemasDto getpSolicitudRecertificacion() {
		return pSolicitudRecertificacion;
	}

	public void setpSolicitudRecertificacion(SolicitudRegistrarReCertificacionSistemasDto pSolicitudRecertificacion) {
		this.pSolicitudRecertificacion = pSolicitudRecertificacion;
	}
	
}
