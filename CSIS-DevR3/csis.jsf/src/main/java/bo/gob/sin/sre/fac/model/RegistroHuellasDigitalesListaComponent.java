package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.DatosReporteComponentesCtbteDto;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean (name ="registroHuellasDigitalesListaComponent")
@ViewScoped
public class RegistroHuellasDigitalesListaComponent  implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	
	List<ReporteComponentesRegistradosCtbteDto> listaComponentesContribuyente;
	
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	public void init() 
	{	
		listaComponentesContribuyente = new ArrayList<>();
	}	

	/**
	 * @autor wilson.limachi
	 * @descripción obtenerComponentesRegistradosContribuyente
	 * @param pSolicitud Lista de huellas digitales de sistema
	 * @fecha 03/09/2019
	 */
	public void obtenerComponentesRegistradosContribuyente(Long pSistemaId, Long pIdContribuyenteProveedor) 
	{
		listaComponentesContribuyente = new ArrayList<>();
		ServiciosFacturacionRest serviciosFacturacionRest=new ServiciosFacturacionRest();		
	    ReporteComponentesRegistradosCtbteDto vReporteComponentesRegistradosCtbteDto = new ReporteComponentesRegistradosCtbteDto();
	    SistemasDto vSolicitud = new SistemasDto(); 
	    vSolicitud.setSistemaId(pSistemaId);
	    vSolicitud.setIdContribuyenteProvedor(pIdContribuyenteProveedor);
	    
	    vReporteComponentesRegistradosCtbteDto  = serviciosFacturacionRest.obtenerListaComponentesRegistradosPorContribuyenteSistema(vSolicitud);
	    
	    mensajesBean.addMensajes(vReporteComponentesRegistradosCtbteDto);
	    
	    if(vReporteComponentesRegistradosCtbteDto.isOk() && vReporteComponentesRegistradosCtbteDto.getNombreSistema()!="")
	    {
	    	listaComponentesContribuyente.add(vReporteComponentesRegistradosCtbteDto);
	    }
	    else
	    {
	    	listaComponentesContribuyente = new ArrayList<>();
	    }
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public List<ReporteComponentesRegistradosCtbteDto> getListaComponentesContribuyente() {
		return listaComponentesContribuyente;
	}

	public void setListaComponentesContribuyente(
			List<ReporteComponentesRegistradosCtbteDto> listaComponentesContribuyente) {
		this.listaComponentesContribuyente = listaComponentesContribuyente;
	}
}
