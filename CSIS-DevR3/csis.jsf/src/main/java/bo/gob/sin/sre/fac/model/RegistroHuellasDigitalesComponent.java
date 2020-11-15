package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;

import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean (name ="registroHuellasDigitalesComponent")
@ViewScoped
public class RegistroHuellasDigitalesComponent  implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;

	private List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitalesDto;
	
	private List<String> componentesMinimosId;
	
	private String rutaArchivo;
	
	private UploadedFile file;
	private UploadedFile archivoEnMemoria;
	private String nombreArchivoSubido;
	
	private String tipoArchivoPermitido;
	private String mensajeArchivoInvalido;
	private String rutaArchivoRegex;	
	private List<ClasificadorDto> listaClasificadorComponenteMinimo;
	
	private byte[] archivo;
	
	private Long SistemaId;
	
	private RegistroHuellasDigitalesDto registroHuellasDigitalesDto; 
	
	@PostConstruct
	public void init() {
		listaRegistroHuellasDigitalesDto = new ArrayList<>();	
		listaClasificadorComponenteMinimo = new ArrayList<>();
		registroHuellasDigitalesDto = new RegistroHuellasDigitalesDto();
	}	

	public List<RegistroHuellasDigitalesDto> getListaRegistroHuellasDigitalesDto() {
		return listaRegistroHuellasDigitalesDto;
	}

	public void setListaRegistroHuellasDigitalesDto(List<RegistroHuellasDigitalesDto> listaRegistroHuellasDigitalesDto) {
		this.listaRegistroHuellasDigitalesDto = listaRegistroHuellasDigitalesDto;
	}

	public List<String> getComponentesMinimosId() {
		return componentesMinimosId;
	}

	public void setComponentesMinimosId(List<String> componentesMinimosId) {
		this.componentesMinimosId = componentesMinimosId;
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

	public String getNombreArchivoSubido() {
		return nombreArchivoSubido;
	}

	public void setNombreArchivoSubido(String nombreArchivoSubido) {
		this.nombreArchivoSubido = nombreArchivoSubido;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getTipoArchivoPermitido() {
		List<ClasificadorDto> listObtenerArchivoExtensionValido = certificacionParametrics.getListaArchivoExtensionValido();
		String vResultado="/(\\.|\\/)(";
		vResultado=vResultado +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getAbreviatura).collect(Collectors.joining("|"))+")$/";		
	    return vResultado;
	}


	public String getMensajeArchivoInvalido() {
		List<ClasificadorDto> listObtenerArchivoExtensionValido = certificacionParametrics.getListaArchivoExtensionValido();
		String vResultado="La exensión requerida es: ";
		vResultado=vResultado +	listObtenerArchivoExtensionValido.stream().map(ClasificadorDto::getAbreviatura).collect(Collectors.joining(", "));
	    return vResultado;
	}
	
	
	public String getRutaArchivoRegex() {
		return rutaArchivoRegex;
	}

	public void setRutaArchivoRegex(String rutaArchivoRegex) {
		this.rutaArchivoRegex = rutaArchivoRegex;
	}

	/**
	 * @autor peter.flores
	 * @descripción Realiza el registro del listado de huellas digitales para el sistema.
	 * @param pSolicitud Lista de huellas digitales de sistema
	 * @fecha 16/11/2018
	 */
	public RegistroHuellasDigitalesDto RegistrarHuellasDigitalesSistemas(List<RegistroHuellasDigitalesDto> pSolicitud) {
		ServiciosFacturacionRest serviciosFacturacionRest=new ServiciosFacturacionRest();
		ListaRegistroHuellasDigitalesDto listaRegistroHuellasDigitalesDto = new ListaRegistroHuellasDigitalesDto();
		listaRegistroHuellasDigitalesDto.setListaRegistroHuellasDigitales(pSolicitud);
		RegistroHuellasDigitalesDto vResultado=serviciosFacturacionRest.RegistrarHuellasDigitalesSistemasFinal(listaRegistroHuellasDigitalesDto);
	    return vResultado;
	}
	
	/**
	 * @autor peter.flores
	 * @descripción Obtine el cifrado de la huella en encriptaciones Md5, Sha y Crc.
	 * @param pSolicitud Contenido del archivo
	 * @fecha 09/11/2018
	 */
	public RegistrarHuellaSistemaDto ObtenerCifradoHuellaDigital(GenerarHuellaSistemaDto pSolicitud) {
		ServiciosFacturacionRest serviciosFacturacionRest=new ServiciosFacturacionRest();
		RegistrarHuellaSistemaDto vResultado=serviciosFacturacionRest.ObtenerCifradoHuellaDigital(pSolicitud);
	    return vResultado;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public Long getSistemaId() {
		return SistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		SistemaId = sistemaId;
	}

	public List<ClasificadorDto> getListaClasificadorComponenteMinimo() {
		return listaClasificadorComponenteMinimo;
	}

	public void setListaClasificadorComponenteMinimo(List<ClasificadorDto> listaClasificadorComponenteMinimo) {
		this.listaClasificadorComponenteMinimo = listaClasificadorComponenteMinimo;
	}

	public RegistroHuellasDigitalesDto getRegistroHuellasDigitalesDto() {
		return registroHuellasDigitalesDto;
	}

	public void setRegistroHuellasDigitalesDto(RegistroHuellasDigitalesDto registroHuellasDigitalesDto) {
		this.registroHuellasDigitalesDto = registroHuellasDigitalesDto;
	} 
}
