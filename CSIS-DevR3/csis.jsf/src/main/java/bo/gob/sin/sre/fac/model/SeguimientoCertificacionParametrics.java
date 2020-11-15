package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.service.ServiciosParametricaRest;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
//import bo.gob.sin.transversales.parametricas.dto.ClasificadorDto;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean(name ="seguimientoCertificacionParametrics")
@ViewScoped
public class SeguimientoCertificacionParametrics implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<ClasificadorDto> listaTipoEsquema;
	private List<ClasificadorDto> listaExtensionManualArchivo;
	private List<ClasificadorDto> listaExtensionDiagramaArchivo;
	private ServiciosParametricaRest vServiciosParametricaRest;	
	
	@PostConstruct
	void init()
	{
		this.listaTipoEsquema = new ArrayList<>();
		this.listaExtensionManualArchivo = new ArrayList<>();
		this.listaExtensionDiagramaArchivo = new ArrayList<>(); 
		
		vServiciosParametricaRest = new ServiciosParametricaRest();		
	}

	public void obtenerTipoClasificadorTipoEsquema()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_esquema_id");		
		this.listaTipoEsquema = resultado;
	}
	
	public void obtenerTipoClasificadorListaExtensionManualArchivo()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_extension_manual_id");		
		this.listaExtensionManualArchivo = resultado;
	}
	
	public void obtenerTipoClasificadorListaExtensionDiagramaArchivo()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_extension_diagrama_id");		
		this.listaExtensionDiagramaArchivo = resultado;
	}

	/**
	 * @return the listaTipoEsquema
	 */
	public List<ClasificadorDto> getListaTipoEsquema() {
		return listaTipoEsquema;
	}

	/**
	 * @param listaTipoEsquema the listaTipoEsquema to set
	 */
	public void setListaTipoEsquema(List<ClasificadorDto> listaTipoEsquema) {
		this.listaTipoEsquema = listaTipoEsquema;
	}

	/**
	 * @return the listaExtensionManualArchivo
	 */
	public List<ClasificadorDto> getListaExtensionManualArchivo() {
		return listaExtensionManualArchivo;
	}

	/**
	 * @param listaExtensionManualArchivo the listaExtensionManualArchivo to set
	 */
	public void setListaExtensionManualArchivo(List<ClasificadorDto> listaExtensionManualArchivo) {
		this.listaExtensionManualArchivo = listaExtensionManualArchivo;
	}

	/**
	 * @return the listaExtensionDiagramaArchivo
	 */
	public List<ClasificadorDto> getListaExtensionDiagramaArchivo() {
		return listaExtensionDiagramaArchivo;
	}

	/**
	 * @param listaExtensionDiagramaArchivo the listaExtensionDiagramaArchivo to set
	 */
	public void setListaExtensionDiagramaArchivo(List<ClasificadorDto> listaExtensionDiagramaArchivo) {
		this.listaExtensionDiagramaArchivo = listaExtensionDiagramaArchivo;
	}
}
