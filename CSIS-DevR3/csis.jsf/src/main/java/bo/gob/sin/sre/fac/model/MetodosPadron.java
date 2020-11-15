package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bo.gob.sin.scn.empa.ccoc.dto.RespuestaServicioDto;
import bo.gob.sin.scn.empa.ccoc.dto.TallerContribuyenteListaDto;
import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.casu.dto.SucursalContribuyenteListaDto;
import bo.gob.sin.scn.empa.ccoc.dto.ActividadesXNitDto;
import bo.gob.sin.scn.empa.ccoc.dto.ExistenciaFormIva200210Dto;
import bo.gob.sin.scn.empa.ccoc.dto.NitActivoDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SucursalContribuyenteListaDTO;
import bo.gob.sin.sre.fac.service.ServiciosPadronRestClient;

@ManagedBean(name ="metodosPadron")
@NoneScoped
public class MetodosPadron implements Serializable
{		
	private static final long serialVersionUID = 1L;

	@PostConstruct
	void init()
	{

	}
	
	/** 
	 * Objetivo: Obtener Datos Contribuyente por Nit.
	 * Creado por: Wilson Limachi.
	 * Fecha: 04/05/2018
	 */
	public ContribuyenteDto ObtenerDatosBasicosXNIT(long pNit)
	 {
		ContribuyenteDto vResultado = new ContribuyenteDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.ObtenerDatosBasicosXNIT(pNit);
		return vResultado; 
	 }
	
	public ContribuyenteDto ObtenerDatosBasicosXIFC(long pIfc)
	 {
		ContribuyenteDto vResultado = new ContribuyenteDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.ObtenerDatosBasicosXIFC(pIfc);
		return vResultado; 
	 }	
	
	public ContribuyenteDto ObtenerDatosBasicosXIFCParaCertificado(long pIfc)
	 {
		ContribuyenteDto vResultado = new ContribuyenteDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.ObtenerDatosBasicosXIFCParaCertificado(pIfc);
		return vResultado; 
	 }
	/**
	 * Objetivo: probar la conección con los servicios de padrón
	 * Creado por: Wilson Limachi
	 * Fecha: 11/05/2018
	 */
	public RespuestaOperacionDto nitActivo(long pNit)
	 {
		RespuestaOperacionDto vResultado = new RespuestaOperacionDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.nitActivo(pNit);
		return vResultado; 
	 }
	
	/**
	 * Objetivo: obtener Sucursales Activas por NIT. Lista de Sucursales Activas correspondiente a un Contribuyente
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public SucursalContribuyenteListaDto obtenerSucursalesActivasXNIT(long pNit)
	 {
		SucursalContribuyenteListaDto vResultado = new SucursalContribuyenteListaDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.obtenerSucursalesActivasXNIT(pNit);
		return vResultado; 
	 }
	
	/**
	 * Objetivo: Obtener Actividades por NIT. Lista de actividades
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public ActividadesXNitDto ObtenerActividadessXNIT(long pNit)
	 {
		ActividadesXNitDto vResultado = new ActividadesXNitDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.ObtenerActividadessXNIT(pNit);
		return vResultado; 
	 }
	

	/**
	 * Objetivo: Obtener Actividades por NIT respuesta 1:existencia marca bloqueo dosificacion - 0: inexsistente, no tiene la marca.
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public RespuestaOperacionDto verificarMarcaJuridicaXNIT(long pNit)
	 {
		RespuestaOperacionDto vResultado = new RespuestaOperacionDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.verificarMarcaJuridicaXNIT(pNit);
		return vResultado; 
	 }
	
	/**
	 * Objetivo: verificar Marca Fiscalizacion por NIT. respuesta 1:existencia marca domicilio inexsistente 0: no tiene la  marca
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public RespuestaServicioDto verificarMarcaFiscalizacionXNIT(long pNit)
	 {
		RespuestaServicioDto vResultado = new RespuestaServicioDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.verificarMarcaFiscalizacionXNIT(pNit);
		return vResultado; 
	 }
	
	/**
	 * Objetivo: verificar Marca Domicilio Inexistente por NIT. respuesta existencia marca domicilio inexsistente
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public RespuestaOperacionDto verificarMarcaDomicilioInexistenteXNIT(long pNit)
	 {
		RespuestaOperacionDto vResultado = new RespuestaOperacionDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.verificarMarcaDomicilioInexistenteXNIT(pNit);
		return vResultado; 
	 }
	
	/**
	 * Objetivo: existe Form Iva200210 Vigente. Dado el numero de documento y codigo complementario, verificar si la persona tiene registro en el padron
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public RespuestaServicioDto existeFormIva200210Vigente(long pNit)
	 {
		RespuestaServicioDto vResultado = new RespuestaServicioDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.existeFormIva200210Vigente(pNit);
		return vResultado; 
	 }
	
	/**
	 * Objetivo: existe Form Iva200210 Vigente. Dado el numero de documento y codigo complementario, verificar si la persona tiene registro en el padron
	 * Creado por: Wilson Limachi.
	 * Fecha: 11/05/2018
	 */
	public RespuestaServicioDto verificarPersonaTieneRegistroPadron(String pNumeroDocumento, String pCodigoComplemento)
	{
		RespuestaServicioDto  vResultado = new RespuestaServicioDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.verificarPersonaTieneRegistroPadron(pNumeroDocumento, pCodigoComplemento);
		return vResultado; 
	}
	
	/**
	 * Obtener Talleres activos por NIt
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 25/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo TallerContribuyenteListaDto.
	 */
	public TallerContribuyenteListaDto obtenerTalleresActivosXNIT(long pNit)
	 {
		TallerContribuyenteListaDto vResultado = new TallerContribuyenteListaDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.obtenerTalleresActivosXNIT(pNit);
		return vResultado; 
	 }
	
	/**
	 * Dado el Ifc de un contribuyente y el numero de sucursal obtener los datos de                      
	 * la sucursal
	 * 
	 * @author wilson limachi
	 * @param pNit nit
	 * @return Datos de la sucursal
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public SucursalContribuyenteListaDTO obtenerSucursalXIFC(long pNit, int pNumeroSucursal)
	 {
		SucursalContribuyenteListaDTO vResultado = new SucursalContribuyenteListaDTO();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.obtenerSucursalXIFC(pNit, pNumeroSucursal);
		return vResultado; 
	 }
	
	/**
	 * Valida el NIT.
	 * 
	 * @author wilson limachi
	 * @param pNit
	 * @return true si es correcto, caso contrario false y descripción de error
	 * Modificado por: Wilson Limachi
	 * Fecha: 29/11/2018
	 */
	public NitActivoDto validarNIT(long pNit)
	 {
		NitActivoDto vResultado = new NitActivoDto();
		ServiciosPadronRestClient vServiciosPadronRestClient = new ServiciosPadronRestClient();
		vResultado = vServiciosPadronRestClient.validarNIT(pNit);
		return vResultado; 
	 }
	
	public void validacionSolicitudCertificacion(long pNit)
	{
		Map<String, String> errores = new HashMap<String, String>();
		
		try
		 {
			RespuestaOperacionDto vNitActivoDto = new RespuestaOperacionDto ();
			RespuestaOperacionDto vVerificarMarcaJuridicaXNIT = new RespuestaOperacionDto ();
				RespuestaOperacionDto  vVerificarMarcaDomicilioInexistenteXNIT  = new RespuestaOperacionDto ();
				RespuestaServicioDto vExistenciaFormIva200210Dto  = new RespuestaServicioDto();
		
				
				vNitActivoDto = this.nitActivo(pNit);
				vVerificarMarcaJuridicaXNIT = this.verificarMarcaJuridicaXNIT(pNit);
				vVerificarMarcaDomicilioInexistenteXNIT = this.verificarMarcaDomicilioInexistenteXNIT(pNit);
				vExistenciaFormIva200210Dto = this.existeFormIva200210Vigente(pNit);
				
				 if(!vNitActivoDto.isOk())
				 {
					errores.put("NIT ACTIVO", vNitActivoDto.getMensajes().get(0).getDescripcion());					 
				 }
				 
				 if(vVerificarMarcaJuridicaXNIT.isOk())
				 {
					errores.put("MARCA JURIDICA", vVerificarMarcaJuridicaXNIT.getMensajes().get(0).getDescripcion());					 
				 }
				 
				 if(vVerificarMarcaDomicilioInexistenteXNIT.isOk())
				 {
					errores.put("MARCA DOMICILIO INEXISTENTE", vVerificarMarcaDomicilioInexistenteXNIT.getMensajes().get(0).getDescripcion());					
				 }
				 
				 if(!vExistenciaFormIva200210Dto.isOk())
				 {
					 ContribuyenteDto vContribuyenteDto = new ContribuyenteDto() ;
					 
					 vContribuyenteDto = ObtenerDatosBasicosXNIT(pNit); 
					 
					 try
					 {
					 
						 if(vContribuyenteDto.isOk())
						 {
							 Integer vTipoNaturalezaJuridicaOracle = 10;
							 Integer vTipoNaturalezaJuridicaPostgres = 774;
							 
							 if(!(vContribuyenteDto.getPersonaJuridica().getTipoNaturalezaJuridicaId().equals(vTipoNaturalezaJuridicaOracle) || vContribuyenteDto.getPersonaJuridica().getTipoNaturalezaJuridicaId().equals(vTipoNaturalezaJuridicaPostgres)))
							 {
								 errores.put("FORMULARIO IVA 200 Y 210", vExistenciaFormIva200210Dto.getMensajes().get(0).getDescripcion());
							 }
						 }
						 else
						 {
							 errores.put("FORMULARIO IVA 200 Y 210", vExistenciaFormIva200210Dto.getMensajes().get(0).getDescripcion()); 
						 }
					 }
					 catch (Exception e) 
					 {
						 errores.put("FORMULARIO IVA 200 Y 210", vExistenciaFormIva200210Dto.getMensajes().get(0).getDescripcion()); 
					 }
				 }
				 
				 if(!errores.isEmpty()) 
				 {					 
						FacesContext.getCurrentInstance().getExternalContext().getFlash().put("autorizacionSolicitudCertificacion", errores);				
						FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/errorValidacion.xhtml?faces-redirect=true");					 
				 }
		 
		 }
		 catch (Exception e) 
		 {
			try 
			{
				errores.put("ERROR", "Se Presentó un error interno.");	
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("autorizacionSolicitudCertificacion", errores);
				e.printStackTrace();				
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/errorValidacion.xhtml?faces-redirect=true");				 							
			} 
			catch (Exception e2) 
			{
				try 
				{
					FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/index.xhtml");		
				} catch (Exception e3) 
				{
					
				}
							 
			}			 
		 }
	}
}
