package bo.gob.sin.sre.fac.controller;





import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.model.ConsultaFacturacionModel;


@ManagedBean (name="consultaFacturacion")
@RequestScoped
public class ConsultaFacturacionController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{consultaFacturacionModel}")
	ConsultaFacturacionModel consultaFacturacionM;
	
	
	
	public void finalizar() {
		try {
			
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("https://www.impuestos.gob.bo");
			
		}catch (Exception e2) {
		}
	}
	
	public void buscar() {
		
		 SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
         
		 RespuestaConsultaFacturacionDto resultadoConsulta=new RespuestaConsultaFacturacionDto();
		
		try {
					
				if(consultaFacturacionM.getNit()!=0) {
					
					resultadoConsulta=consultaFacturacionM.consultaFacturacion(consultaFacturacionM.getNit());
					if(resultadoConsulta.isOk()) 
					{
						
						this.getConsultaFacturacionM().setFechaAdecuacion(formatDate.format(resultadoConsulta.getFechaAdecuacion()));
						this.getConsultaFacturacionM().setGrupo(resultadoConsulta.getGrupo());
						this.getConsultaFacturacionM().setModalidadDestino(resultadoConsulta.getModalidadDestino());
						this.getConsultaFacturacionM().setNit(resultadoConsulta.getNit());
						this.getConsultaFacturacionM().setReporte(resultadoConsulta.getReporte());
						this.getConsultaFacturacionM().setBuscadoExito(true);						
						
					}else {
						RequestContext.getCurrentInstance().execute("toastr.error('No hay resultado para el nit ingresado', '')");
						this.getConsultaFacturacionM().setBuscadoExito(false);
						this.getConsultaFacturacionM().limpiar();
					}
					
				}else {
					RequestContext.getCurrentInstance().execute("toastr.error('El campo Nit es requerido', '')");
					this.getConsultaFacturacionM().limpiar();
					this.getConsultaFacturacionM().setBuscadoExito(false);
				}
			
		} catch (Exception e2) {
		}
		
	}

	public ConsultaFacturacionModel getConsultaFacturacionM() {
		return consultaFacturacionM;
	}

	public void setConsultaFacturacionM(ConsultaFacturacionModel consultaFacturacionM) {
		this.consultaFacturacionM = consultaFacturacionM;
	}
	
}
