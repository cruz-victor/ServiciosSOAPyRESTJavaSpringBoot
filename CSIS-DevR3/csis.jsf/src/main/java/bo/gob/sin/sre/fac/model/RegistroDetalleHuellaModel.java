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
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean (name ="registroDetalleHuellaModel")
@ViewScoped
public class RegistroDetalleHuellaModel  implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private RespuestaDetalleHuellaDto registroDetalleHuella;
	
	private List<String> componentesMinimosId;
	
	@PostConstruct
	public void init() 
	{	
		registroDetalleHuella = new RespuestaDetalleHuellaDto();
		componentesMinimosId = new ArrayList<>();
	}

	public RespuestaDetalleHuellaDto getRegistroDetalleHuella() {
		return registroDetalleHuella;
	}

	public void setRegistroDetalleHuella(RespuestaDetalleHuellaDto registroDetalleHuella) {
		this.registroDetalleHuella = registroDetalleHuella;
	}

	public List<String> getComponentesMinimosId() {
		return componentesMinimosId;
	}

	public void setComponentesMinimosId(List<String> componentesMinimosId) {
		this.componentesMinimosId = componentesMinimosId;
	}	
}
