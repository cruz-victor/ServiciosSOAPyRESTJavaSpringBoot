package bo.gob.sin.sre.fac.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.MeterGaugeChartModel;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.ComponentesArchivosDto;
import bo.gob.sin.sre.fac.dto.ComponentsCertificadosDto;
import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.SistemasSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.model.ArchivosDiagramaModel;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.ComportamientoPaginaModel;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.ListaDetalleHuellaList;
import bo.gob.sin.sre.fac.model.ListadoArchivosDiagramaList;
import bo.gob.sin.sre.fac.model.ListadoEtapasCertificadosFase2List;
import bo.gob.sin.sre.fac.model.ListadoRegistroAmbienteList;
import bo.gob.sin.sre.fac.model.RegistroAmbienteModel;
import bo.gob.sin.sre.fac.model.RegistroArchivoComponent;
import bo.gob.sin.sre.fac.model.RegistroDetalleHuellaModel;
import bo.gob.sin.sre.fac.model.RegistroHuellasDigitalesListaComponent;
import bo.gob.sin.sre.fac.model.SeguimientoCertificacionParametrics;
import bo.gob.sin.sre.fac.model.SeguimientoCertificacionSistemasList;
import bo.gob.sin.sre.fac.model.SistemasCertificadosModel;
import bo.gob.sin.sre.fac.reports.ReportesController;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import net.sf.jasperreports.engine.JRException;
import javax.faces.event.ActionEvent;

/**
 * @Descripcion: Muestra el listado de los sistemas asociados al proveedor.
 *               Adicionalmente el listado de las etapas de la fase II de
 *               implementación
 * @author: Wilson Limachi
 * @fecha: 19/009/2019.
 */

@ManagedBean(name = "seguimientoCertificacionSistemasComponentesController")
@RequestScoped
public class SeguimientoCertificacionSistemasComponentesController implements Serializable 
{
	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;
	
	@ManagedProperty(value = "#{listadoEtapasCertificadosFase2List}")
	ListadoEtapasCertificadosFase2List listadoEtapasCertificadosFase2List;
	
	@ManagedProperty(value = "#{seguimientoCertificacionSistemasList}")
	private SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList;
	
	@ManagedProperty(value = "#{registroHuellasDigitalesListaComponent}")
	private RegistroHuellasDigitalesListaComponent registroHuellasDigitalesListaComponent;
	
	@ManagedProperty(value = "#{listadoArchivosDiagramaList}")
	private ListadoArchivosDiagramaList listadoArchivosDiagramaList;
	
	@ManagedProperty(value = "#{archivosDiagramaModel}")
	private ArchivosDiagramaModel archivosDiagramaModel;	
	
	@ManagedProperty(value = "#{registroDetalleHuellaModel}")
	private RegistroDetalleHuellaModel registroDetalleHuellaModel;
	
	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;
	
	@ManagedProperty(value = "#{seguimientoCertificacionParametrics}")
	SeguimientoCertificacionParametrics seguimientoCertificacionParametrics;
	
	@ManagedProperty(value = "#{registroArchivoComponent}")
	private RegistroArchivoComponent registroArchivoComponent;
	
	@ManagedProperty(value = "#{sistemasCertificadosModel}")
	private SistemasCertificadosModel sistemasCertificadosModel;
	
	@ManagedProperty(value = "#{listaDetalleHuellaList}")
	private ListaDetalleHuellaList listaDetalleHuellaList;
	
	@ManagedProperty(value = "#{comportamientoPaginaModel}")
	private ComportamientoPaginaModel comportamientoPaginaModel;
	
	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@ManagedProperty(value = "#{listadoRegistroAmbienteList}")
	private ListadoRegistroAmbienteList listadoRegistroAmbienteList;
	
	@ManagedProperty(value = "#{registroAmbienteModel}")
	private RegistroAmbienteModel registroAmbienteModel;

	private static final long serialVersionUID = 1L;
	
	private MeterGaugeChartModel meterGaugeModel;

	@ManagedProperty(value = "#{reportesController}")
	ReportesController reportesController;
	
	private int FASE_DOS_HUELLA_ARCHIVO = ConstFacturacion.FASE_DOS_HUELLA_ARCHIVO;
	private int FASE_DOS_ARCHIVO_DESPLIEGUE = ConstFacturacion.FASE_DOS_ARCHIVO_DESPLIEGUE;
	private int FASE_DOS_ARCHIVO_COMPONENTES = ConstFacturacion.FASE_DOS_ARCHIVO_COMPONENTES;
	private int FASE_DOS_ARCHIVO_COUMNICACION = ConstFacturacion.FASE_DOS_ARCHIVO_COUMNICACION;
	private int FASE_DOS_ARCHIVO_MANUAL_USUARIO = ConstFacturacion.FASE_DOS_ARCHIVO_MANUAL_USUARIO;
	private int FASE_DOS_ARCHIVO_MANUAL_INSTALACION = ConstFacturacion.FASE_DOS_ARCHIVO_MANUAL_INSTALACION;
	private int FASE_DOS_AMBIENTE = ConstFacturacion.FASE_DOS_AMBIENTE;

	public void Load() 
	{
		//this.getSeguimientoCertificacionSistemasList().consultaSistemasAsociadosContribuyente(this.getContextoUsuarioModel().getContribuyente().getIfc());
		this.listaDetalleHuellaList.obtenerListadoSistemasFase2(this.getContextoUsuarioModel().getContribuyente().getIfc());
		IniciarEstados();
		this.getSeguimientoCertificacionParametrics().obtenerTipoClasificadorTipoEsquema();
	}
	
	public void LoadComponente(Long pIdSistema, long pIfc, Long pSolicitudCertificacionId) 
	{
		if (!FacesContext.getCurrentInstance().isPostback())
		{
			this.IniciarEstados();
			this.VerDetallesEtapas(pIfc,pIdSistema, pSolicitudCertificacionId);
		}
	}
	
	public void IniciarEstados()
	{
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas", true);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_Huellas", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaDespliegue", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaComunicacion", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaComponente", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaManualUsuario", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaManualInstalacion", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_SistemaOperativo", false);
	}
	
	public void VerDetallesEtapas(Long pContribuyenteId, Long pSistemaId, Long pSolicitudCertificacionId)
	{
		this.getSistemasCertificadosModel().getSistemasContribuyentes().setContribuyenteId(pContribuyenteId);
		this.getSistemasCertificadosModel().getSistemasContribuyentes().setSistemaId(pSistemaId);
		this.getSistemasCertificadosModel().getSistemasContribuyentes().setSolicitudCertificacionId(pSolicitudCertificacionId);		
		
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas", false);
		this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", true);
		
		this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(pContribuyenteId, pSistemaId, pSolicitudCertificacionId);
	}
	
	public void volverSistemasEtapas(String pInstancia)
	{
		if(pInstancia.equals("GrillaSistemas_Etapa"))
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas", true);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
		}
		
		if(pInstancia.contains("GrillaSistemas_Etapa_"))
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", true);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_Huellas", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaDespliegue", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaComunicacion", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaComponente", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaManualUsuario", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaManualInstalacion", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_SistemaOperativo", false);
		}
	}
	
	//ver casos de pruebas
	
	public void verDetalleEtapa(SeguimientoCertificacionSistemasFaseDosDto pSolicitud)
	{	
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_HUELLA_ARCHIVO)
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_Huellas", true);
			this.certificacionParametrics.obtenerComponentesMinimos();
			this.getListaDetalleHuellaList().obtieneComponentesArchivosCertificados(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId());
		}
		
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_ARCHIVO_DESPLIEGUE)
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaDespliegue", true);
			this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(Long.parseLong(pSolicitud.getEtapa_certificacion_sistemas_id()+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
		
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_ARCHIVO_COMPONENTES)
		{	
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaComponente", true);	
			this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(Long.parseLong(pSolicitud.getEtapa_certificacion_sistemas_id()+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
		
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_ARCHIVO_COUMNICACION)
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaComunicacion", true);
			this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(Long.parseLong(pSolicitud.getEtapa_certificacion_sistemas_id()+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
		
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_ARCHIVO_MANUAL_USUARIO)
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaManualUsuario", true);
			this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(Long.parseLong(pSolicitud.getEtapa_certificacion_sistemas_id()+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
		
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_ARCHIVO_MANUAL_INSTALACION)
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_EsquemaManualInstalacion", true);
			this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(Long.parseLong(pSolicitud.getEtapa_certificacion_sistemas_id()+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
		
		if(pSolicitud.getEtapa_certificacion_sistemas_id() == FASE_DOS_AMBIENTE)
		{
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa", false);
			this.getComportamientoPaginaModel().getControlGrillas().put("GrillaSistemas_Etapa_SistemaOperativo", true);
			this.certificacionParametrics.obtenerComponentesMinimos();
			this.getListadoRegistroAmbienteList().obtieneListaDiagramasCertificaciones(Long.parseLong(pSolicitud.getEtapa_certificacion_sistemas_id()+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
	}
	
	//ACCIONES
	
	public void adicionarComponenteHuellaDialogo()
	{
		if(!this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId().equals(null))
		{
			if(this.getListadoRegistroAmbienteList().getListaDetallesCertificacionesSistemas().size() < 1)
			{
				this.certificacionParametrics.obtenerComponentesMinimos();
				this.getRegistroArchivoComponent().initConfiguracionHuellas();
				this.getRegistroArchivoComponent().iniciarValores();
				RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoHuellaArchivo').show()");
				this.getRegistroDetalleHuellaModel().setRegistroDetalleHuella(new RespuestaDetalleHuellaDto());
				this.getRegistroDetalleHuellaModel().setComponentesMinimosId(new ArrayList<>());
			}
			else
			{
				RequestContext.getCurrentInstance().execute("toastr.info('No puede registrar más de un registro.', '')");
			}			
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Seleccione un sistema.', '')");
		}
	}
	
	public void adicionarComponenteDiagramaDialogo(int pDiagramaSolicitud)
	{
		this.getArchivosDiagramaModel().setDiagramasCertificaciones(new DiagramasCertificacionesDto());
		this.getRegistroArchivoComponent().iniciarValores();
		
		if(FASE_DOS_ARCHIVO_DESPLIEGUE == pDiagramaSolicitud)
		{
			this.getRegistroArchivoComponent().initConfiguracionDiagramas();
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoDiagramaArchivo').show()");
		}
		
		if(FASE_DOS_ARCHIVO_COMPONENTES == pDiagramaSolicitud)
		{
			this.getRegistroArchivoComponent().initConfiguracionDiagramas();
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoDiagramaArchivo').show()");
		}
		
		if(FASE_DOS_ARCHIVO_COUMNICACION == pDiagramaSolicitud)
		{
			this.getRegistroArchivoComponent().initConfiguracionDiagramas();
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoDiagramaArchivo').show()");
		}
		
		if(FASE_DOS_ARCHIVO_MANUAL_USUARIO == pDiagramaSolicitud)
		{
			this.getRegistroArchivoComponent().initConfiguracionManuales();
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoDiagramaArchivo').show()");
		}
		
		if(FASE_DOS_ARCHIVO_MANUAL_INSTALACION == pDiagramaSolicitud)
		{
			this.getRegistroArchivoComponent().initConfiguracionManuales();
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoDiagramaArchivo').show()");
		}
	}
	
	public void cambioComboSistema()
	{
		Long vSistemaId;
		Long vSolicitudCertificacionId;
		Long vIdContribuyente;
		
		SistemasSolicitudCertificacionDto vSistemasContribuyentes= new SistemasSolicitudCertificacionDto();
		vSistemasContribuyentes = this.listaDetalleHuellaList.getListaSistemasSolicitudCertificacionDto().getListaSistemasSolicitudCertificacionDto().stream().filter(sistema -> (sistema.getSistemaId()+"").equals(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId()+"")).findFirst().orElse(null);
				
		if(vSistemasContribuyentes!=null)
		{
			vSistemaId = vSistemasContribuyentes.getSistemaId();
			vSolicitudCertificacionId = vSistemasContribuyentes.getSolicitudCertificacionId();
			vIdContribuyente = vSistemasContribuyentes.getContribuyenteId();
			
			this.getListaDetalleHuellaList().obtieneComponentesArchivosCertificados(vSistemaId, vSolicitudCertificacionId, vIdContribuyente);
		}
	}
	
	public void registrarComponenteArchivo()
	{
		if(!(this.getRegistroArchivoComponent().getArchivo().getArchivo() ==null))
		{
			long count = this.getListaDetalleHuellaList().getListaDetalleHuellaDto().getListaDetalleHuella().stream().filter(p -> p.getComponentesArchivos().getNombre().equals(this.getRegistroArchivoComponent().getArchivo().getNombreArchivo())).count();
			
			if(count == 0L)
			{
				RespuestaDetalleHuellaDto vRespuestaDetalleHuellaDto = new RespuestaDetalleHuellaDto();
				
				List<ComponentsCertificadosDto> listaComponentesCertificados = new ArrayList<>();
				ComponentesArchivosDto vComponentesArchivos= new ComponentesArchivosDto();
				
				for (String componenteMnimo : this.getRegistroDetalleHuellaModel().getComponentesMinimosId()) 
				{
					ComponentsCertificadosDto vComponentsCertificadosDto = new ComponentsCertificadosDto();
					vComponentsCertificadosDto.setTipoComponenteId(Integer.parseInt(componenteMnimo));
					vComponentsCertificadosDto.setSistemaId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId());
					vComponentsCertificadosDto.setSolicitudCertificacionId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
					listaComponentesCertificados.add(vComponentsCertificadosDto);
				}
				
				vComponentesArchivos.setArchivo(this.getRegistroArchivoComponent().getArchivo().getArchivo());
				vComponentesArchivos.setExtension(this.getRegistroArchivoComponent().getArchivo().getExtension());
				vComponentesArchivos.setNombre(this.getRegistroArchivoComponent().getArchivo().getNombreArchivo());
				vComponentesArchivos.setRuta(this.getRegistroArchivoComponent().getArchivo().getRutaArchivo());
				
				
				vRespuestaDetalleHuellaDto.setListaComponentsCertificados(listaComponentesCertificados);
				vRespuestaDetalleHuellaDto.setComponentesArchivos(vComponentesArchivos);
				vRespuestaDetalleHuellaDto.setDatosEntradaSistemaId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId());
				vRespuestaDetalleHuellaDto.setDatosEntradaSolicitudCertificacion(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
				vRespuestaDetalleHuellaDto.setDatosEntradaUsuarioRegistro(this.getContextoUsuarioModel().getUsuario().getUsuarioId());
				
				RespuestaOperacionDto vRespuestaOperacionDto = new RespuestaOperacionDto();
				ServiciosConsultaFacturacionRestClient vServiciosConsultaFacturacionRestClient = new ServiciosConsultaFacturacionRestClient();
				vRespuestaOperacionDto = vServiciosConsultaFacturacionRestClient.registrarComponentesArchivos(vRespuestaDetalleHuellaDto);
				
				mensajesBean.addMensajes(vRespuestaOperacionDto);
				
				if(vRespuestaOperacionDto.isOk())
				{
					this.getListaDetalleHuellaList().obtieneComponentesArchivosCertificados(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId());
					this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
				}
				
				RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoHuellaArchivo').hide()");
			}
			else
			{
				RequestContext.getCurrentInstance().execute("toastr.error('El archivo seleccionado ya existe.', '')");
			}
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Seleccione un archivo.', '')");
		}
	}
	
	public void registrarComponenteArchivoDiagrama(int pDiagramaId)
	{
		if(!(this.getRegistroArchivoComponent().getArchivo().getArchivo() ==null))
		{
			long count = this.listadoArchivosDiagramaList.getListaDiagramasCertificaciones().getListaDiagramasCertificacionesDto().stream().filter(p -> (p.getNombreArchivo()).equals(this.getRegistroArchivoComponent().getArchivo().getNombreArchivo())).count();
						
			if(count == 0L)
			{
				ServiciosConsultaFacturacionRestClient vServiciosConsultaFacturacionRestClient = new ServiciosConsultaFacturacionRestClient();				
				
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setUsuarioRegistroId(this.contextoUsuarioModel.getUsuario().getUsuarioId());
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setSistemaId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId());
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setSolicitudCertificacionId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setNombreArchivo(this.registroArchivoComponent.getArchivo().getNombreArchivo());
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setExtensionArchivo(this.registroArchivoComponent.getArchivo().getExtension());
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setEtapaId(Long.parseLong(pDiagramaId+""));
				this.getArchivosDiagramaModel().getDiagramasCertificaciones().setArchivo(this.registroArchivoComponent.getArchivo().getArchivo());
				
				
				this.getArchivosDiagramaModel().setDiagramasCertificaciones(vServiciosConsultaFacturacionRestClient.registraDiagramasCertificaciones(this.getArchivosDiagramaModel().getDiagramasCertificaciones()));
				
				mensajesBean.addMensajes(this.getArchivosDiagramaModel().getDiagramasCertificaciones());
				
				if(this.getArchivosDiagramaModel().getDiagramasCertificaciones().isOk())
				{					
					this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(this.getArchivosDiagramaModel().getDiagramasCertificaciones().getEtapaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
					this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
				}
			}
			else
			{
				RequestContext.getCurrentInstance().execute("toastr.error('El archivo seleccionado ya existe.', '')");
			}
			
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoDiagramaArchivo').hide()");
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('Seleccione un archivo.', '')");
		}
	}
	
	public void anularHuella(Long pArchivoId)
	{
		ServiciosConsultaFacturacionRestClient vServiciosConsultaFacturacionRestClient = new ServiciosConsultaFacturacionRestClient();
		RespuestaOperacionDto vRespuestaOperacionDto = new RespuestaOperacionDto();
		
		vRespuestaOperacionDto.setDatosEntradaArchivoId(pArchivoId);
		vRespuestaOperacionDto.setDatosEntradaUsuarioRegistro(this.contextoUsuarioModel.getUsuario().getUsuarioId());
		
		vRespuestaOperacionDto = vServiciosConsultaFacturacionRestClient.actualizaCanceladoComponentesArchivosCertificados(vRespuestaOperacionDto);
		
		mensajesBean.addMensajes(vRespuestaOperacionDto);
		
		if(vRespuestaOperacionDto.isOk())
		{
			this.getListaDetalleHuellaList().obtieneComponentesArchivosCertificados(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId());
			this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
	}
	
	public void actualizaDiagramasCertificaciones(Long pArchivoId, Long pDiagramaId, Long pEtapaId)
	{
		ServiciosConsultaFacturacionRestClient vServiciosConsultaFacturacionRestClient = new ServiciosConsultaFacturacionRestClient();
		RespuestaOperacionDto vRespuestaOperacionDto = new RespuestaOperacionDto();
		
		vRespuestaOperacionDto.setDatosEntradaId(pDiagramaId);
		vRespuestaOperacionDto.setDatosEntradaArchivoId(pArchivoId);
		vRespuestaOperacionDto.setDatosEntradaUsuarioRegistro(this.contextoUsuarioModel.getUsuario().getUsuarioId());
		
		vRespuestaOperacionDto = vServiciosConsultaFacturacionRestClient.actualizaDiagramasCertificaciones(vRespuestaOperacionDto);
		
		mensajesBean.addMensajes(vRespuestaOperacionDto);
		
		if(vRespuestaOperacionDto.isOk())
		{
			this.getListadoArchivosDiagramaList().obtieneListaDiagramasCertificaciones(pEtapaId, this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
			this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		}
	}
	
	public void registrarAmbiente()
	{

		DetallesCertificacionesSistemasDto vRespuestaDetalleHuellaDto = new DetallesCertificacionesSistemasDto();
		
		vRespuestaDetalleHuellaDto.setArchivoDiagramaId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getArchivoDiagramaId());
		vRespuestaDetalleHuellaDto.setOtroBaseDatos(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getOtroBaseDatos());
		vRespuestaDetalleHuellaDto.setOtroLenguajeProgramacion(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getOtroLenguajeProgramacion());
		vRespuestaDetalleHuellaDto.setOtroServidorAplicacion(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getOtroServidorAplicacion());
		vRespuestaDetalleHuellaDto.setOtroSistemaOperativo(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getOtroSistemaOperativo());
		vRespuestaDetalleHuellaDto.setSistemaId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId());
		vRespuestaDetalleHuellaDto.setSolicitudCertificacionId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
		vRespuestaDetalleHuellaDto.setTipoBaseDatosId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getTipoBaseDatosId());
		vRespuestaDetalleHuellaDto.setTipoConexionId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getTipoConexionId());
		vRespuestaDetalleHuellaDto.setTipoLenguajeProgramacionId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getTipoLenguajeProgramacionId());
		vRespuestaDetalleHuellaDto.setTipoServidorAplicacionId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getTipoServidorAplicacionId());
		vRespuestaDetalleHuellaDto.setTipoSistemaOperativoId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getTipoSistemaOperativoId());		
		vRespuestaDetalleHuellaDto.setVersionLenguajeProgramacion(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getVersionLenguajeProgramacion());		
		vRespuestaDetalleHuellaDto.setVersionSistemaOperativo(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getVersionSistemaOperativo());
		vRespuestaDetalleHuellaDto.setUsuarioRegistroId(this.getContextoUsuarioModel().getUsuario().getUsuarioId());
		vRespuestaDetalleHuellaDto.setUsuarioUltimaModificacionId(this.getContextoUsuarioModel().getUsuario().getUsuarioId());
		vRespuestaDetalleHuellaDto.setTipoEsquemaId(this.getRegistroAmbienteModel().getRegistroCertificacionesSistemas().getTipoEsquemaId());
		vRespuestaDetalleHuellaDto.setEtapaId(FASE_DOS_AMBIENTE);
		
		DetallesCertificacionesSistemasDto vRespuestaOperacionDto = new DetallesCertificacionesSistemasDto();
		vRespuestaOperacionDto = this.getRegistroAmbienteModel().registraDetalleCertificacionesSistemas(vRespuestaDetalleHuellaDto);
		
		mensajesBean.addMensajes(vRespuestaOperacionDto);
		
		if(vRespuestaOperacionDto.isOk())
		{
			//this.getListadoRegistroAmbienteList().getListaDetallesCertificacionesSistemas().add(vRespuestaOperacionDto);
			this.getListadoRegistroAmbienteList().obtieneListaDiagramasCertificaciones(Long.parseLong(FASE_DOS_AMBIENTE+""), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());			
			this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
			limpiarAmbiente();
		}
		
		RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCargadoHuellaArchivo').hide()");
	}
	
	
	public void limpiarAmbiente()
	{
		getRegistroAmbienteModel().setRegistroCertificacionesSistemas(new DetallesCertificacionesSistemasDto());
	}
	
	public void anularAmbiente(Long pArchivoId)
	{
		ServiciosConsultaFacturacionRestClient vServiciosConsultaFacturacionRestClient = new ServiciosConsultaFacturacionRestClient();
		DetallesCertificacionesSistemasDto vRespuestaOperacionDto = new DetallesCertificacionesSistemasDto();
		
		vRespuestaOperacionDto.setDetalleCertificacionSistemaId(pArchivoId);		
		vRespuestaOperacionDto.setUsuarioUltimaModificacionId(this.contextoUsuarioModel.getUsuario().getUsuarioId());
		vRespuestaOperacionDto = vServiciosConsultaFacturacionRestClient.anularDetalleCertificacionesSistemas(vRespuestaOperacionDto);
		
		mensajesBean.addMensajes(vRespuestaOperacionDto);
		
		if(vRespuestaOperacionDto.isOk())
		{		
			this.getListadoRegistroAmbienteList().obtieneListaDiagramasCertificaciones(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
			this.getListadoEtapasCertificadosFase2List().cargarlistaEtapaFaseDos(this.getSistemasCertificadosModel().getSistemasContribuyentes().getContribuyenteId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getSistemasCertificadosModel().getSistemasContribuyentes().getSolicitudCertificacionId());
			limpiarAmbiente();
		}
	}
	
	
	public void anularDescripcionArchivo(Long pArchivoDiagramaId)
	{
		
	}
	
	public ReportesController getReportesController() {
		return reportesController;
	}

	public void setReportesController(ReportesController reportesController) {
		this.reportesController = reportesController;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}
	
	public SeguimientoCertificacionSistemasList getSeguimientoCertificacionSistemasList() {
		return seguimientoCertificacionSistemasList;
	}

	public void setSeguimientoCertificacionSistemasList(SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList) {
		this.seguimientoCertificacionSistemasList = seguimientoCertificacionSistemasList;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	/**
	 * @return the comportamientoPaginaModel
	 */
	public ComportamientoPaginaModel getComportamientoPaginaModel() {
		return comportamientoPaginaModel;
	}

	/**
	 * @param comportamientoPaginaModel the comportamientoPaginaModel to set
	 */
	public void setComportamientoPaginaModel(ComportamientoPaginaModel comportamientoPaginaModel) {
		this.comportamientoPaginaModel = comportamientoPaginaModel;
	}

	/**
	 * @return the listadoEtapasCertificadosFase2List
	 */
	public ListadoEtapasCertificadosFase2List getListadoEtapasCertificadosFase2List() {
		return listadoEtapasCertificadosFase2List;
	}

	/**
	 * @param listadoEtapasCertificadosFase2List the listadoEtapasCertificadosFase2List to set
	 */
	public void setListadoEtapasCertificadosFase2List(
			ListadoEtapasCertificadosFase2List listadoEtapasCertificadosFase2List) {
		this.listadoEtapasCertificadosFase2List = listadoEtapasCertificadosFase2List;
	}

	public RegistroHuellasDigitalesListaComponent getRegistroHuellasDigitalesListaComponent() {
		return registroHuellasDigitalesListaComponent;
	}

	public void setRegistroHuellasDigitalesListaComponent(
			RegistroHuellasDigitalesListaComponent registroHuellasDigitalesListaComponent) {
		this.registroHuellasDigitalesListaComponent = registroHuellasDigitalesListaComponent;
	}

	public ListaDetalleHuellaList getListaDetalleHuellaList() {
		return listaDetalleHuellaList;
	}

	public void setListaDetalleHuellaList(ListaDetalleHuellaList listaDetalleHuellaList) {
		this.listaDetalleHuellaList = listaDetalleHuellaList;
	}

	public RegistroArchivoComponent getRegistroArchivoComponent() {
		return registroArchivoComponent;
	}

	public void setRegistroArchivoComponent(RegistroArchivoComponent registroArchivoComponent) {
		this.registroArchivoComponent = registroArchivoComponent;
	}

	public SistemasCertificadosModel getSistemasCertificadosModel() {
		return sistemasCertificadosModel;
	}

	public void setSistemasCertificadosModel(SistemasCertificadosModel sistemasCertificadosModel) {
		this.sistemasCertificadosModel = sistemasCertificadosModel;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}

	public RegistroDetalleHuellaModel getRegistroDetalleHuellaModel() {
		return registroDetalleHuellaModel;
	}

	public void setRegistroDetalleHuellaModel(RegistroDetalleHuellaModel registroDetalleHuellaModel) {
		this.registroDetalleHuellaModel = registroDetalleHuellaModel;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public int getFASE_DOS_HUELLA_ARCHIVO() {
		return FASE_DOS_HUELLA_ARCHIVO;
	}

	public void setFASE_DOS_HUELLA_ARCHIVO(int fASE_DOS_HUELLA_ARCHIVO) {
		FASE_DOS_HUELLA_ARCHIVO = fASE_DOS_HUELLA_ARCHIVO;
	}

	public int getFASE_DOS_ARCHIVO_DESPLIEGUE() {
		return FASE_DOS_ARCHIVO_DESPLIEGUE;
	}

	public void setFASE_DOS_ARCHIVO_DESPLIEGUE(int fASE_DOS_ARCHIVO_DESPLIEGUE) {
		FASE_DOS_ARCHIVO_DESPLIEGUE = fASE_DOS_ARCHIVO_DESPLIEGUE;
	}

	public int getFASE_DOS_ARCHIVO_COMPONENTES() {
		return FASE_DOS_ARCHIVO_COMPONENTES;
	}

	public void setFASE_DOS_ARCHIVO_COMPONENTES(int fASE_DOS_ARCHIVO_COMPONENTES) {
		FASE_DOS_ARCHIVO_COMPONENTES = fASE_DOS_ARCHIVO_COMPONENTES;
	}

	public int getFASE_DOS_ARCHIVO_COUMNICACION() {
		return FASE_DOS_ARCHIVO_COUMNICACION;
	}

	public void setFASE_DOS_ARCHIVO_COUMNICACION(int fASE_DOS_ARCHIVO_COUMNICACION) {
		FASE_DOS_ARCHIVO_COUMNICACION = fASE_DOS_ARCHIVO_COUMNICACION;
	}

	public int getFASE_DOS_ARCHIVO_MANUAL_USUARIO() {
		return FASE_DOS_ARCHIVO_MANUAL_USUARIO;
	}

	public void setFASE_DOS_ARCHIVO_MANUAL_USUARIO(int fASE_DOS_ARCHIVO_MANUAL_USUARIO) {
		FASE_DOS_ARCHIVO_MANUAL_USUARIO = fASE_DOS_ARCHIVO_MANUAL_USUARIO;
	}

	public int getFASE_DOS_ARCHIVO_MANUAL_INSTALACION() {
		return FASE_DOS_ARCHIVO_MANUAL_INSTALACION;
	}

	public void setFASE_DOS_ARCHIVO_MANUAL_INSTALACION(int fASE_DOS_ARCHIVO_MANUAL_INSTALACION) {
		FASE_DOS_ARCHIVO_MANUAL_INSTALACION = fASE_DOS_ARCHIVO_MANUAL_INSTALACION;
	}

	public int getFASE_DOS_AMBIENTE() {
		return FASE_DOS_AMBIENTE;
	}

	public void setFASE_DOS_AMBIENTE(int fASE_DOS_AMBIENTE) {
		FASE_DOS_AMBIENTE = fASE_DOS_AMBIENTE;
	}

	/**
	 * @return the listadoArchivosDiagramaList
	 */
	public ListadoArchivosDiagramaList getListadoArchivosDiagramaList() {
		return listadoArchivosDiagramaList;
	}

	/**
	 * @param listadoArchivosDiagramaList the listadoArchivosDiagramaList to set
	 */
	public void setListadoArchivosDiagramaList(ListadoArchivosDiagramaList listadoArchivosDiagramaList) {
		this.listadoArchivosDiagramaList = listadoArchivosDiagramaList;
	}

	/**
	 * @return the seguimientoCertificacionParametrics
	 */
	public SeguimientoCertificacionParametrics getSeguimientoCertificacionParametrics() {
		return seguimientoCertificacionParametrics;
	}

	/**
	 * @param seguimientoCertificacionParametrics the seguimientoCertificacionParametrics to set
	 */
	public void setSeguimientoCertificacionParametrics(
			SeguimientoCertificacionParametrics seguimientoCertificacionParametrics) {
		this.seguimientoCertificacionParametrics = seguimientoCertificacionParametrics;
	}

	/**
	 * @return the archivosDiagramaModel
	 */
	public ArchivosDiagramaModel getArchivosDiagramaModel() {
		return archivosDiagramaModel;
	}

	/**
	 * @param archivosDiagramaModel the archivosDiagramaModel to set
	 */
	public void setArchivosDiagramaModel(ArchivosDiagramaModel archivosDiagramaModel) {
		this.archivosDiagramaModel = archivosDiagramaModel;
	}

	public ListadoRegistroAmbienteList getListadoRegistroAmbienteList() {
		return listadoRegistroAmbienteList;
	}

	public void setListadoRegistroAmbienteList(ListadoRegistroAmbienteList listadoRegistroAmbienteList) {
		this.listadoRegistroAmbienteList = listadoRegistroAmbienteList;
	}

	public RegistroAmbienteModel getRegistroAmbienteModel() {
		return registroAmbienteModel;
	}

	public void setRegistroAmbienteModel(RegistroAmbienteModel registroAmbienteModel) {
		this.registroAmbienteModel = registroAmbienteModel;
	}		
}
