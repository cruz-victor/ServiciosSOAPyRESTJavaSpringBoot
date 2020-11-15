package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sap.cead.componentes.controller.ScrDtoOficina;
import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.sre.fac.service.ServiciosParametricaRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "rvi")
public class RegistroVerificacionInsituController implements Serializable {
	private static final long serialVersionUID = 1L;

	private EstadoDerivacionDto estadoDerivacionDto;

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;
	
	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	private ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	List<RegistroVerificacionInsituDto> lista;
	private List<ClasificadorDto> listaEstadoPrueba;

	@PostConstruct
	public void inicio() {
		this.obtenerContexto(estadoDerivacionDto);
		this.tramiteCertificacionBean.setearContexto();
		this.contextoSolicitudCertificacionSistemaModel.getSolicitud().setSolicitudId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		this.cargarParametricas();
		
		RespuestaRegistroVerificacionInsituDto vRespuestaRegistroVerificacionInsituDto = new RespuestaRegistroVerificacionInsituDto();
		vRespuestaRegistroVerificacionInsituDto.setDatosEntradaSolicitudCertificacionId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		vRespuestaRegistroVerificacionInsituDto.setDatosEntradaSistemaId((Long)tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
		vRespuestaRegistroVerificacionInsituDto.setDatosEntradaUsuarioId(this.getContextoUsuarioModel().getUsuario().getUsuarioId());
		
		cargarDatos(vRespuestaRegistroVerificacionInsituDto);
	}

	public void cargarParametricas() {
		ServiciosParametricaRest vServiciosParametricaRest = new ServiciosParametricaRest();
		listaEstadoPrueba= vServiciosParametricaRest.obtenerParametrica("estado_prueba_solicitud_id");
	}
	
	private void cargarDatos(RespuestaRegistroVerificacionInsituDto pRespuestaRegistroVerificacionInsituDto) 
	{
		ServiciosFacturacionRest vServiciosFacturacionRest = new  ServiciosFacturacionRest();	
		RespuestaRegistroVerificacionInsituDto respuesta = vServiciosFacturacionRest.obtieneRegistroListaRegistroObservacionesInsitu(pRespuestaRegistroVerificacionInsituDto);
		
		this.lista = respuesta.getLista();
	}

	public void actualizarPrueba(RegistroVerificacionInsituDto pPrueba) {
		RequestContext.getCurrentInstance()
		.execute("toastr.success('" + pPrueba.getDescripcionPrueba() + "')");
	}
	
	public void onCellEdit(CellEditEvent event) {
		/*Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();*/
		FacesContext context = FacesContext.getCurrentInstance();
		RegistroVerificacionInsituDto entity = context.getApplication().evaluateExpressionGet(context, "#{registro}", RegistroVerificacionInsituDto.class);
		 
		
		this.guardarRegistro(entity);
	}
	
	private void guardarRegistro(RegistroVerificacionInsituDto pRegistro) {
		ServiciosFacturacionRest vServicio = new ServiciosFacturacionRest();
		SolicitudRegistroVerificacionInsituDto vSolicitud = new SolicitudRegistroVerificacionInsituDto();
		pRegistro.setUsuarioRegistroId(this.getContextoUsuarioModel().getUsuario().getUsuarioId());
		vSolicitud.setRegistro(pRegistro);
		RespuestaRegistroVerificacionInsituDto vRespuestaDto = vServicio.guardarRegistroVerificacionInsitu(vSolicitud);
		if (vRespuestaDto.isOk()) {
			pRegistro.setRegistroObservacionInsituId(vRespuestaDto.getRegistroObservacionInsituId());
			Optional<ClasificadorDto> match = listaEstadoPrueba.stream().filter(item -> (int)item.getClasificadorId() == (int)pRegistro.getEstadoPruebaId()).findFirst();
			pRegistro.setEstadoPruebaDescripcion(match.isPresent() ? match.get().getDescripcion() : "");
			RequestContext.getCurrentInstance().execute("toastr.success('Datos actualizados','Correcto')");
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Ocurrio un error al guardar el registro','Error')");
		}		
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
		return estadoDerivacionDto;
	}

	public void setEstadoDerivacionDto(EstadoDerivacionDto estadoDerivacionDto) {
		this.estadoDerivacionDto = estadoDerivacionDto;
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public List<RegistroVerificacionInsituDto> getLista() {
		return lista;
	}

	public void setLista(List<RegistroVerificacionInsituDto> lista) {
		this.lista = lista;
	}

	public List<ClasificadorDto> getListaEstadoPrueba() {
		return listaEstadoPrueba;
	}

	public void setListaEstadoPrueba(List<ClasificadorDto> listaEstadoPrueba) {
		this.listaEstadoPrueba = listaEstadoPrueba;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}
	
	
}
