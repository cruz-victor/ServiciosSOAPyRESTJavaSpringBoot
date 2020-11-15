package bo.gob.sin.sre.fac.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sap.cder.dto.RespDerivacionMultipleDto;
import bo.gob.sin.sap.cder.dto.SolicitudDerivacionMultipleDto;
import bo.gob.sin.sap.cder.dto.UsuarioAsignacionDto;
import bo.gob.sin.sap.cder.dto.UsuarioAsignacionProcesoDto;
import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesDto;
import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.model.AsignacionEquipoCertificacionModel;
import bo.gob.sin.sre.fac.model.AsignacionEquiposComputacionComponent;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.model.RespUsuariosAsignancionProcesoDto;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.sre.fac.service.ServiciosWorkFlowRest;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;

@SuppressWarnings("deprecation")
@ManagedBean(name = "asignacionEquipoCertificacionController")
@RequestScoped
public class AsignacionEquipoCertificacionController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	@ManagedProperty(value = "#{asignacionEquipoCertificacionModel}")
	AsignacionEquipoCertificacionModel asignacionEquipoCertificacionModel;

	@ManagedProperty(value = "#{asignacionEquiposComputacionComponent}")
	AsignacionEquiposComputacionComponent asignacionEquiposComputacionComponent;

	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	private EstadoDerivacionDto estadoDerivacionDto;

	public void Load() {
		this.obtenerContexto(estadoDerivacionDto);
		this.tramiteCertificacionBean.setearContexto();

		this.asignacionEquipoCertificacionModel.setFuncionariosAsignados(new RespUsuariosAsignancionProcesoDto());
		ServiciosWorkFlowRest vServiciosrestWorkFlow = new ServiciosWorkFlowRest();

		asignacionEquiposComputacionComponent.getDatosSistemasSolCertificacion()
				.setSistemaId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSistemaId());
		asignacionEquiposComputacionComponent.getDatosSistemasSolCertificacion().setSolicitudCertificacionId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		asignacionEquiposComputacionComponent.obtenerAsignacionEquiposComputacion();

		this.asignacionEquipoCertificacionModel.setFuncionarios(vServiciosrestWorkFlow.obtenerUsuariosAsignacionEquipoCertificacion(contextoUsuarioModel.getUsuario().getToken()));

		long vPersonaContribuyente = tramiteCertificacionBean.getTramiteSolicitudCertificacion()
				.getPersonaContribuyenteId();
		MetodosPadron vMetodosPadron = new MetodosPadron();
		if (!this.asignacionEquipoCertificacionModel.getFuncionarios().isOk()) {
			RequestContext.getCurrentInstance()
					.execute("toastr.error('No se recuperaron los funcionarios para asignación', '')");
		}

		this.asignacionEquipoCertificacionModel.setAsignarseUnoMismo(false);
	}

	public void onDerivar() throws IOException {
		System.out.println("Derivar!!!");
		if (this.asignacionEquipoCertificacionModel.isAsignarseUnoMismo()) {
			UsuarioAsignacionDto vUsuario = new UsuarioAsignacionDto();
			vUsuario.setUsuarioId(contextoUsuarioModel.getUsuario().getUsuarioId());
			vUsuario.setDependenciaId(contextoUsuarioModel.getUsuario().getDependenciaId());
			vUsuario.setOficinaId(contextoUsuarioModel.getUsuario().getOficinaId());
			vUsuario.setEtapaId(ParametrosCsis.ETAPA_GESTION_PRUEBAS_INSITU);
			this.asignacionEquipoCertificacionModel.agregarUsuario(vUsuario);
		} else {
			this.asignacionEquipoCertificacionModel
					.quitarUsuarioPorId(this.contextoUsuarioModel.getUsuario().getUsuarioId());
		}

		if (asignacionEquipoCertificacionModel.getFuncionariosAsignados().getUsuariosAsignacion().isEmpty()) {
			RequestContext.getCurrentInstance().execute("toastr.error('Debe asignar al menos un funcionario', '')");
			return;
		} else {
			/*asignacionEquipoCertificacionModel.getFuncionariosAsignados().getUsuariosAsignacion().forEach(x -> {

				RequestContext.getCurrentInstance()
						.execute("toastr.info('Id funcionario: " + x.getUsuarioId() + " ' , '')");
			});*/

			this.obtenerContexto(estadoDerivacionDto);
			if (estadoDerivacionDto == null) {
				System.out.println("Contexto Bandeja nulo!!!");
				return;
			} else {
				AsignacionesCertificacionesListaDto vRespuesta = registrarEquipoCertificacion();
				if (vRespuesta.isOk()) {
					ServiciosWorkFlowRest vServiciosrestWorkFlow = new ServiciosWorkFlowRest();
					SolicitudDerivacionMultipleDto vSol = new SolicitudDerivacionMultipleDto();
					vSol.setInstruccion(this.asignacionEquipoCertificacionModel.getInstruccion());
					vSol.setUsuarioId(contextoUsuarioModel.getUsuario().getUsuarioId());
					vSol.setDerivacionId(this.getEstadoDerivacionDto().getDerivacionId());
					vSol.setUsuarios(new ArrayList<>());
					for (UsuarioAsignacionDto vUser : asignacionEquipoCertificacionModel.getFuncionariosAsignados().getUsuariosAsignacion()) {
						vSol.getUsuarios().add(this.transformar(vUser));
					}

					RespDerivacionMultipleDto vRespuestaDerivacion = vServiciosrestWorkFlow.derivarUsuariosAsignacionEquipoCertificacion(vSol,	contextoUsuarioModel.getUsuario().getToken());
					if (vRespuestaDerivacion.isOk()) {
						RequestContext.getCurrentInstance().execute("toastr.success('Se realizo la derivación.' , '')");
						FacesContext context = FacesContext.getCurrentInstance();
						RestUrl vConf = new RestUrl();
						String vUrlBandejaRecepcion = vConf.getPropetyValue("workflow_bandeja_recepcion");
						context.getExternalContext().redirect(vUrlBandejaRecepcion);
					} else {
						RequestContext.getCurrentInstance()
								.execute("toastr.error('No se realizo la derivación.' , '')");
					}
				}
			}
		}
	}
	
	private UsuarioAsignacionProcesoDto transformar(UsuarioAsignacionDto usuario) {
		UsuarioAsignacionProcesoDto respuesta=new UsuarioAsignacionProcesoDto();
		respuesta.setEtapaId(usuario.getEtapaId());
		respuesta.setId(usuario.getId());
		respuesta.setOficinaId(usuario.getOficinaId());
		respuesta.setUsuarioId(usuario.getUsuarioId());
		respuesta.setDependenciaId(usuario.getDependenciaId());
		respuesta.setInstruccion(usuario.getInstruccion());
		return respuesta;
	}

	private AsignacionesCertificacionesListaDto registrarEquipoCertificacion() {
		AsignacionesCertificacionesListaDto vAsignacionesDto = new AsignacionesCertificacionesListaDto();
		List<AsignacionesCertificacionesDto> vListaFuncionariosAsignados = new ArrayList<>();
		ServiciosFacturacionRest vClienteRest = new ServiciosFacturacionRest();
		vAsignacionesDto = vClienteRest.obtenerEquipoCertificacionPorTramite(estadoDerivacionDto.getTramiteId());

		if (vAsignacionesDto.isOk()) {
			if (vAsignacionesDto.getAsignacionesCertificacionesListaDto() == null) {
				asignacionEquipoCertificacionModel.getFuncionariosAsignados().getUsuariosAsignacion()
						.forEach(vFuncionariosAsignacion -> {
							AsignacionesCertificacionesDto vAsignacionesCertificacionesDto = new AsignacionesCertificacionesDto();
							vAsignacionesCertificacionesDto.setTramiteId(estadoDerivacionDto.getTramiteId());
							vAsignacionesCertificacionesDto.setFechaAsignacion(Date.from(Instant.now()));
							vAsignacionesCertificacionesDto.setUsuarioId(vFuncionariosAsignacion.getUsuarioId());
							vAsignacionesCertificacionesDto
									.setUsuarioRegistroId(contextoUsuarioModel.getUsuario().getUsuarioId());
							vAsignacionesCertificacionesDto
									.setUsuarioUltimaModificacionId(contextoUsuarioModel.getUsuario().getUsuarioId());

							// Registra responsable 
							vAsignacionesCertificacionesDto.setResponsable(
									vFuncionariosAsignacion.getUsuarioId() == asignacionEquipoCertificacionModel
											.getFuncionarioResponsable().getUsuarioId());
							
							vAsignacionesCertificacionesDto.setEstadoAsignacionCertificacionId(
									ParametrosCsis.ESTADO_ASINGACION_EQUIPO_CERTIFICACION_ASIGNADO);
							vListaFuncionariosAsignados.add(vAsignacionesCertificacionesDto);

						});
				vAsignacionesDto.setAsignacionesCertificacionesListaDto(vListaFuncionariosAsignados);
				RespuestaEstaRegistradoGenericoDto vRespuestaregistro = vClienteRest
						.registrarEquipoCertificacion(vAsignacionesDto);
				vAsignacionesDto.setOk(vRespuestaregistro.isOk());
				vAsignacionesDto.setMensajes(vRespuestaregistro.getMensajes());
			}
		}
		return vAsignacionesDto;
	}

	public AsignacionEquipoCertificacionModel getAsignacionEquipoCertificacionModel() {
		return asignacionEquipoCertificacionModel;
	}

	public void setAsignacionEquipoCertificacionModel(
			AsignacionEquipoCertificacionModel asignacionEquipoCertificacionModel) {
		this.asignacionEquipoCertificacionModel = asignacionEquipoCertificacionModel;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public EstadoDerivacionDto getEstadoDerivacionDto() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		estadoDerivacionDto = (EstadoDerivacionDto) sessionMap.get("EstadoProceso");
		return estadoDerivacionDto;
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

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}
}
