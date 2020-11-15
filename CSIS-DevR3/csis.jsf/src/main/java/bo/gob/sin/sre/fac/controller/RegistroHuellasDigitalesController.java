package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.ListadoSistemasCertificadosModelList;
import bo.gob.sin.sre.fac.model.RegistroHuellasDigitalesComponent;
import bo.gob.sin.sre.fac.model.RegistroHuellasDigitalesListaComponent;
import bo.gob.sin.sre.fac.model.SistemasCertificadosModel;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean(name = "registroHuellasDigitalesController")
@RequestScoped
public class RegistroHuellasDigitalesController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{registroHuellasDigitalesListaComponent}")
	RegistroHuellasDigitalesListaComponent registroHuellasDigitalesListaComponent;
	
	@ManagedProperty(value = "#{listadoSistemasCertificadosModelList}")
	ListadoSistemasCertificadosModelList listadoSistemasCertificadosModelList;
	
	@ManagedProperty(value = "#{registroHuellasDigitalesComponent}")
	RegistroHuellasDigitalesComponent registroHuellasDigitalesComponent;
	
	@ManagedProperty(value = "#{sistemasCertificadosModel}")
	SistemasCertificadosModel sistemasCertificadosModel;

	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;
	
	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	public void Load() 
	{	
		if (!FacesContext.getCurrentInstance().isPostback())
		{
		
			if(certificacionParametrics.getListaComponentesMinimos().size()==0 && registroHuellasDigitalesComponent.getListaClasificadorComponenteMinimo().size()==0)
			{
					this.certificacionParametrics.obtenerComponentesMinimos();
					this.certificacionParametrics.obtenerArchivoExtensionValido();
					
					if(certificacionParametrics.getListaComponentesMinimos().size()==0)
					{
						RequestContext.getCurrentInstance().execute("toastr.error('No se pudo recuperar la información de los componentes mínimos.', '')");
					}
					if(certificacionParametrics.getListaArchivoExtensionValido().size()==0)
					{
						RequestContext.getCurrentInstance().execute("toastr.error('No se pudo recuperar la información de los archivos válidos.', '')");
					}					
			}
		}
	}
	
	public void cargarGrillaRegistro(Long pSistemaId)
	{	
		Long ifc= this.getContextoUsuarioModel().getContribuyente().getIfc();
		ifc = 1001771198L;
		this.getRegistroHuellasDigitalesListaComponent().obtenerComponentesRegistradosContribuyente(pSistemaId, ifc);
	}
	
	
	public void handleFileUpload(FileUploadEvent event) {
	    
		this.getRegistroHuellasDigitalesComponent().setNombreArchivoSubido(event.getFile().getFileName());
		this.getRegistroHuellasDigitalesComponent().setFile(event.getFile());
		this.getRegistroHuellasDigitalesComponent().setArchivoEnMemoria(event.getFile());
		byte[] content = event.getFile().getContents();
		getRegistroHuellasDigitalesComponent().setArchivo(content);
		
	}
	
	public void adicionarComponenteDialogo()
	{
		//if(this.sistemasCertificadosModel.getSistemasContribuyentes().getSistemaId() != null)
		//{
			RequestContext.getCurrentInstance().execute("PF('cuadroDialogoCorregirFacturaCompraManual').show()");
		//}
	}			
	
	public void limpiar() {
		
		getRegistroHuellasDigitalesComponent().setNombreArchivoSubido(null);
		getRegistroHuellasDigitalesComponent().setRutaArchivo(null);
		getRegistroHuellasDigitalesComponent().setNombreArchivoSubido(null);
		getRegistroHuellasDigitalesComponent().setFile(null);
		getRegistroHuellasDigitalesComponent().setArchivoEnMemoria(null);
		getRegistroHuellasDigitalesComponent().setComponentesMinimosId(null);
	}
	
	public void adicionarComponente() {

		String vNombreComponentesMinimos=null;
		RegistroHuellasDigitalesDto  registroHuellasDigitalesDto = new RegistroHuellasDigitalesDto();
		registroHuellasDigitalesDto.setNombre(getRegistroHuellasDigitalesComponent().getNombreArchivoSubido());
		registroHuellasDigitalesDto.setRutaArchivo(getRegistroHuellasDigitalesComponent().getRutaArchivo());
		GenerarHuellaSistemaDto generarHuellaSistemaDto = new GenerarHuellaSistemaDto();
		generarHuellaSistemaDto.setArchivo(getRegistroHuellasDigitalesComponent().getArchivo());
		RegistrarHuellaSistemaDto vResultado=getRegistroHuellasDigitalesComponent().ObtenerCifradoHuellaDigital(generarHuellaSistemaDto);
		registroHuellasDigitalesDto.setArchivo(getRegistroHuellasDigitalesComponent().getArchivo());
		registroHuellasDigitalesDto.setCrc(vResultado.getCrc());
		registroHuellasDigitalesDto.setMd5(vResultado.getMd5());
		registroHuellasDigitalesDto.setSha2(vResultado.getSha2());
		registroHuellasDigitalesDto.setUsuarioId(this.getContextoUsuarioModel().getUsuario().getUsuarioId());
		registroHuellasDigitalesDto.setSistemaId(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId());
		
		List<Integer> vComponentesMinimos = new ArrayList<>();		
		this.getRegistroHuellasDigitalesComponent().getComponentesMinimosId().stream().forEach((c) -> vComponentesMinimos.add(Integer.parseInt(c)));
		
		registroHuellasDigitalesDto.setTipoComponenteId(vComponentesMinimos);
		
				
		for (Integer valorComponenteMinimo :vComponentesMinimos) 
		{
			vNombreComponentesMinimos=vNombreComponentesMinimos==null ? certificacionParametrics.getListaComponentesMinimos().stream().filter(x->x.getClasificadorId().equals(valorComponenteMinimo)).findFirst().orElseGet(null).getDescripcion() : vNombreComponentesMinimos+", "+certificacionParametrics.getListaComponentesMinimos().stream().filter(x->x.getClasificadorId().equals(valorComponenteMinimo)).findFirst().orElseGet(null).getDescripcion();
			ClasificadorDto clasificadorComponenteMinimoDto = certificacionParametrics.getListaComponentesMinimos().stream().filter(x->x.getClasificadorId().equals(valorComponenteMinimo)).findFirst().orElse(null);
			registroHuellasDigitalesComponent.getListaClasificadorComponenteMinimo().add(clasificadorComponenteMinimoDto);
			certificacionParametrics.getListaComponentesMinimos().remove(clasificadorComponenteMinimoDto);
		}		
		registroHuellasDigitalesDto.setComponenteMinimo(vNombreComponentesMinimos);
		getRegistroHuellasDigitalesComponent().getListaRegistroHuellasDigitalesDto().add(registroHuellasDigitalesDto);
		
		
		
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		ListaRegistroHuellasDigitalesDto vSolicitud = new ListaRegistroHuellasDigitalesDto();
		RegistroHuellasDigitalesDto vRespuesta = new RegistroHuellasDigitalesDto();
		List<RegistroHuellasDigitalesDto> vRegistro = new ArrayList<>();
		vRegistro.add(registroHuellasDigitalesDto);
		
		vSolicitud.setListaRegistroHuellasDigitales(vRegistro);		
		
		vRespuesta = vServiciosFacturacionRest.RegistrarHuellasDigitalesSistemasContribuyente(vSolicitud);
		
		System.out.println("RESPUESTA: "+vRespuesta.isOk());
		
		
//		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RegistroResultadoVerificacionSituForm:idPanelComponente");
//		RequestContext.getCurrentInstance().update("RegistroResultadoVerificacionSituForm:idPanelComponente");		
		limpiar();
	}
	
	public void cancelar() {
		//FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RegistroResultadoVerificacionSituForm:idPanelComponente");
		//RequestContext.getCurrentInstance().update("RegistroResultadoVerificacionSituForm:idPanelComponente");
		limpiar();
	}
	
	/**
	 * @autor peter.flores
	 * @descripción elimina registro seleccionado de la grilla
	 * @param pFilaDetalle fila seleccionado a eliminar
	 * @fecha 27/11/2018
	 */
	public void eliminarFilaComponente(RegistroHuellasDigitalesDto pFilaDetalle) {
		
		for (Integer clasificadorComponenteMinimoId : pFilaDetalle.getTipoComponenteId()) {
			ClasificadorDto clasificadorComponenteMinimoDto = registroHuellasDigitalesComponent.getListaClasificadorComponenteMinimo().stream().filter(x->x.getClasificadorId().equals(clasificadorComponenteMinimoId)).findFirst().orElse(null);
			certificacionParametrics.getListaComponentesMinimos().add(clasificadorComponenteMinimoDto);
		}
		getRegistroHuellasDigitalesComponent().getListaRegistroHuellasDigitalesDto().remove(pFilaDetalle);
	}
	
	public void seleccionaRegistro(RegistroHuellasDigitalesDto pFilaDetalle) {
		
		registroHuellasDigitalesComponent.setRegistroHuellasDigitalesDto(pFilaDetalle);	
	}
	
	public RegistroHuellasDigitalesComponent getRegistroHuellasDigitalesComponent() {
		return registroHuellasDigitalesComponent;
	}

	public void setRegistroHuellasDigitalesComponent(RegistroHuellasDigitalesComponent registroHuellasDigitalesComponent) {
		this.registroHuellasDigitalesComponent = registroHuellasDigitalesComponent;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public RegistroHuellasDigitalesListaComponent getRegistroHuellasDigitalesListaComponent() {
		return registroHuellasDigitalesListaComponent;
	}

	public void setRegistroHuellasDigitalesListaComponent(
			RegistroHuellasDigitalesListaComponent registroHuellasDigitalesListaComponent) {
		this.registroHuellasDigitalesListaComponent = registroHuellasDigitalesListaComponent;
	}

	public ListadoSistemasCertificadosModelList getListadoSistemasCertificadosModelList() {
		return listadoSistemasCertificadosModelList;
	}

	public void setListadoSistemasCertificadosModelList(
			ListadoSistemasCertificadosModelList listadoSistemasCertificadosModelList) {
		this.listadoSistemasCertificadosModelList = listadoSistemasCertificadosModelList;
	}

	public SistemasCertificadosModel getSistemasCertificadosModel() {
		return sistemasCertificadosModel;
	}

	public void setSistemasCertificadosModel(SistemasCertificadosModel sistemasCertificadosModel) {
		this.sistemasCertificadosModel = sistemasCertificadosModel;
	}
}
