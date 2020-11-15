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

import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import net.bytebuddy.asm.Advice.Return;

@ManagedBean (name ="registroAmbienteModel")
@ViewScoped
public class RegistroAmbienteModel  implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private DetallesCertificacionesSistemasDto registroCertificacionesSistemas;
	
	private List<String> componentesMinimosId;
	
	@PostConstruct
	public void init() 
	{	
		registroCertificacionesSistemas = new DetallesCertificacionesSistemasDto();
		componentesMinimosId = new ArrayList<>();
	}
	
	public DetallesCertificacionesSistemasDto registraDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitud)
	{
		ServiciosConsultaFacturacionRestClient vServiciosConsultaFacturacionRestClient = new ServiciosConsultaFacturacionRestClient();
		DetallesCertificacionesSistemasDto vRespuestaOperacionDto = vServiciosConsultaFacturacionRestClient.registraDetalleCertificacionesSistemas(pSolicitud);
		return vRespuestaOperacionDto;
		
	}

	public DetallesCertificacionesSistemasDto getRegistroCertificacionesSistemas() {
		return registroCertificacionesSistemas;
	}

	public void setRegistroCertificacionesSistemas(DetallesCertificacionesSistemasDto registroCertificacionesSistemas) {
		this.registroCertificacionesSistemas = registroCertificacionesSistemas;
	}

	public List<String> getComponentesMinimosId() {
		return componentesMinimosId;
	}

	public void setComponentesMinimosId(List<String> componentesMinimosId) {
		this.componentesMinimosId = componentesMinimosId;
	}	
}
