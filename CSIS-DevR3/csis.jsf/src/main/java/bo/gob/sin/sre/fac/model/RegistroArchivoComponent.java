package bo.gob.sin.sre.fac.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import bo.gob.sin.sre.fac.dto.RegistroArchivoDto;
import bo.gob.sin.sre.fac.service.ServiciosParametricaRest;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
//import bo.gob.sin.transversales.parametricas.dto.ClasificadorDto;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean(name ="registroArchivoComponent")
@ViewScoped
public class RegistroArchivoComponent implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private RegistroArchivoDto archivo;
	private UploadedFile file;
	private UploadedFile archivoEnMemoria;
	
	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;
	
	@ManagedProperty(value = "#{seguimientoCertificacionParametrics}")
	SeguimientoCertificacionParametrics seguimientoCertificacionParametrics;

	@PostConstruct
	void init()
	{
		this.archivo = new RegistroArchivoDto();
		
		this.getArchivo().setTamanioArchivoBytes("5000000");
		this.getArchivo().setMensajeTamanioInvalido("El archivo excede los "+ this.getArchivo().getTamanioArchivoBytes().charAt(0) +" megas.");
		
		this.certificacionParametrics.obtenerArchivoExtensionValido();
		List<ClasificadorDto> listObtenerArchivoExtensionValido = certificacionParametrics.getListaArchivoExtensionValido();
		String vResultado="La extensión requerida es: ";
		vResultado=vResultado +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getAbreviatura).collect(Collectors.joining(", "));
		this.getArchivo().setMensajeArchivoInvalido(vResultado);
		
		String vResultadoExtension="/(\\.|\\/)(";
		vResultadoExtension = vResultadoExtension +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getAbreviatura).collect(Collectors.joining("|"))+")$/";		
	    this.getArchivo().setTipoArchivoPermitido(vResultadoExtension);
	}
	
	public void initConfiguracionHuellas()
	{
		//this.archivo = new RegistroArchivoDto();
		
		this.getArchivo().setTamanioArchivoBytes("5000000");
		this.getArchivo().setMensajeTamanioInvalido("El archivo excede los "+ this.getArchivo().getTamanioArchivoBytes().charAt(0) +" megas.");
		
		this.certificacionParametrics.obtenerArchivoExtensionValido();
		List<ClasificadorDto> listObtenerArchivoExtensionValido = certificacionParametrics.getListaArchivoExtensionValido();
		String vResultado="La extensión requerida es: ";
		vResultado=vResultado +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getAbreviatura).collect(Collectors.joining(", "));
		this.getArchivo().setMensajeArchivoInvalido(vResultado);
		
		String vResultadoExtension="/(\\.|\\/)(";
		vResultadoExtension = vResultadoExtension +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getAbreviatura).collect(Collectors.joining("|"))+")$/";		
	    this.getArchivo().setTipoArchivoPermitido(vResultadoExtension);
	}
	
	public void initConfiguracionDiagramas()
	{
		this.archivo = new RegistroArchivoDto();
		
		this.getArchivo().setTamanioArchivoBytes("1000000");
		this.getArchivo().setMensajeTamanioInvalido("El archivo excede los "+ this.getArchivo().getTamanioArchivoBytes().charAt(0) +" megas.");
		
		seguimientoCertificacionParametrics.obtenerTipoClasificadorListaExtensionDiagramaArchivo();
		List<ClasificadorDto> listObtenerArchivoExtensionValido = seguimientoCertificacionParametrics.getListaExtensionDiagramaArchivo();
		String vResultado="La extensión requerida es: ";
		vResultado=vResultado +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getDescripcion).collect(Collectors.joining(", "));
		this.getArchivo().setMensajeArchivoInvalido(vResultado);
		
		String vResultadoExtension="/(\\.|\\/)(";
		vResultadoExtension = vResultadoExtension +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getDescripcion).collect(Collectors.joining("|"))+")$/";		
	    this.getArchivo().setTipoArchivoPermitido(vResultadoExtension);
	}
	
	public void initConfiguracionManuales()
	{
		//this.archivo = new RegistroArchivoDto();
		
		this.getArchivo().setTamanioArchivoBytes("4000000");
		this.getArchivo().setMensajeTamanioInvalido("El archivo excede los "+ this.getArchivo().getTamanioArchivoBytes().charAt(0) +" megas.");
		
		seguimientoCertificacionParametrics.obtenerTipoClasificadorListaExtensionManualArchivo();
		List<ClasificadorDto> listObtenerArchivoExtensionValido = seguimientoCertificacionParametrics.getListaExtensionManualArchivo();
		String vResultado="La extensión requerida es: ";
		vResultado=vResultado +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getDescripcion).collect(Collectors.joining(", "));
		this.getArchivo().setMensajeArchivoInvalido(vResultado);
		
		String vResultadoExtension="/(\\.|\\/)(";
		vResultadoExtension = vResultadoExtension +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getDescripcion).collect(Collectors.joining("|"))+")$/";		
	    this.getArchivo().setTipoArchivoPermitido(vResultadoExtension);
	}
	
	public void iniciarValores()
	{
		this.getArchivo().setNombreArchivo(null);
		this.getArchivo().setArchivoRutaCompleta(null);
		this.getArchivo().setArchivo(null);
		this.getArchivo().setRutaArchivo(null);
	}
	
	public void handleFileUpload(FileUploadEvent event) 
	{ 
		this.archivo.setArchivoRutaCompleta(event.getFile().getFileName());		
		this.archivo.setExtension(this.archivo.obtenerExtensionArchivo(this.archivo.getArchivoRutaCompleta()));
		this.archivo.setNombreArchivo(event.getFile().getFileName().replace("." + this.archivo.getExtension(), ""));
		this.setFile(event.getFile());		
		this.setArchivoEnMemoria(event.getFile());
		byte[] content = event.getFile().getContents();
		this.archivo.setArchivo(content);		
		  
		
	    //MODO AUTOMATICO PERO NO ES UTIL CUANDO ESTA PUBLICADO AFUERA, ES MEJOR QUE SE PONGA COMO MANUAL LA RUTA
		//this.archivo.setRutaArchivo(file.getAbsolutePath().replace(this.archivo.getNombreArchivo()+ "." + this.archivo.getExtension(), ""));
	}

	public RegistroArchivoDto getArchivo() {
		return archivo;
	}

	public void setArchivo(RegistroArchivoDto archivo) {
		this.archivo = archivo;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UploadedFile getArchivoEnMemoria() {
		return archivoEnMemoria;
	}

	public void setArchivoEnMemoria(UploadedFile archivoEnMemoria) {
		this.archivoEnMemoria = archivoEnMemoria;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
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

//	public void obtenerCertificacionModalidadFacturacionGrupo()
//	{
//		List<ClasificadorDto> resultado = new ArrayList<>();
//		resultado = vServiciosParametricaRest.recuperarClasificadorPorGrupo(ConstFacturacion.MODALIDAD_CERTIFICACION_ID);		
//		
//		this.listaCertificacionModalidadFacturacionGrupo = resultado;
//	}
	
}
