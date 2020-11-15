package bo.gob.sin.sre.fac.model;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;


@ManagedBean (name ="busquedaDatosBasicosComponent")
@ViewScoped
public class BusquedaDatosBasicosComponent implements Serializable
{
	@ManagedProperty(value = "#{metodosPadron}")
	MetodosPadron metodosPadron;
	
	ContribuyenteDto nitDatosBasicosDto;
	private boolean fueBuscado;
	
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
		nitDatosBasicosDto= new ContribuyenteDto();
		this.LimpiarDatosContribuyente();
	}
	
	public void BuscarDatosContribuyentes(Long pNit)
	{		
		System.out.println("Metodobuscarcontrinbuyente"+pNit);
		
		ContribuyenteDto vNitDatosBasicosDto = new ContribuyenteDto (); 
		vNitDatosBasicosDto = metodosPadron.ObtenerDatosBasicosXNIT(pNit);
		
		
		if(pNit != null)
		{
			if(pNit != 0)
			{
				if(vNitDatosBasicosDto.isOk() && vNitDatosBasicosDto.getNombreRazonSocial()!= null)
				{
					this.setNitDatosBasicosDto(vNitDatosBasicosDto);
					this.fueBuscado = true;
					RequestContext.getCurrentInstance().execute("toastr.success('El nit: "+this.getNitDatosBasicosDto().getNit()+" produjo resultados.', '')");
				}
				else
				{
					RequestContext.getCurrentInstance().execute("toastr.error('El nit: "+this.getNitDatosBasicosDto().getNit()+" no produjo resultados.', '')");
					this.LimpiarDatosContribuyente();
				}
			}
			else
			{
				RequestContext.getCurrentInstance().execute("toastr.error('El nit: "+this.getNitDatosBasicosDto().getNit()+" introducido es invalido.', '')");
				this.LimpiarDatosContribuyente();
			}			
		}
		else
		{
			RequestContext.getCurrentInstance().execute("toastr.error('El nit no puede ser vacia.', '')");
			this.LimpiarDatosContribuyente();
		}
	}	
	
	public void LimpiarDatosContribuyente()
	{		
		System.out.println("Metodobuscarcontrinbuyente"+this.getNitDatosBasicosDto().getNit());
		
		this.getNitDatosBasicosDto().setNit(0L);
		this.getNitDatosBasicosDto().getPersonaJuridica().setDependenciaRegistroDescripcion("");
		this.getNitDatosBasicosDto().getPersonaNatural().setDependenciaRegistroDescripcion("");
		this.getNitDatosBasicosDto().setNombreRazonSocial("");
		
		
		this.fueBuscado = false;
	}
	
	public boolean isFueBuscado() {
		return fueBuscado;
	}

	public void setFueBuscado(boolean fueBuscado) {
		this.fueBuscado = fueBuscado;
	}
	
	public MetodosPadron getMetodosPadron() {
		return metodosPadron;
	}

	public void setMetodosPadron(MetodosPadron metodosPadron) {
		this.metodosPadron = metodosPadron;
	}
	
	public ContribuyenteDto getNitDatosBasicosDto() {
		return nitDatosBasicosDto;
	}

	public void setNitDatosBasicosDto(ContribuyenteDto nitDatosBasicosDto) {
		this.nitDatosBasicosDto = nitDatosBasicosDto;
	}
}

