package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIData;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.RecuperarListaContactosCertificacion;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ManagedBean(name = "certificacionSolicitudCertificacionList")
@ViewScoped
public class CertificacionSolicitudCertificacionList  implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SistemasContribuyentesDto> listaDatosSistemaContribuyenteDto;
        
    private List<SolicitudContactosCertificacionesDto> listaContactos;
    private SolicitudContactosCertificacionesDto contactos; 
    
    private DatosRecertificacion datosReCertificacion;
        
    @ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
        
    private int numeroRegistroEditados; 
    
    /**
    * @Descipcion: inicializa y carga los datos por defecto. 
    * @author: Wilson Limachi.
    */
    @PostConstruct
    public void init() 
    {
            numeroRegistroEditados = 0;
            listaDatosSistemaContribuyenteDto = new ArrayList<>();
            contactos = new SolicitudContactosCertificacionesDto();
            listaContactos = new ArrayList<>();
            datosReCertificacion = new DatosRecertificacion();
    }
    
    /**
     * @Descipcion: AÃ±ade contactos a la grilla de contactos.
     * @author: Wilson Limachi. 
     */
     public void adicionarContacto()
     {
     		System.out.println("adicionarContacto ENTRO");
     		numeroRegistroEditados = 0;
     		//this.getListaContactos().add(new SolicitudContactosCertificacionesDto());
     		
             if(this.existeDuplicados())
             {
                     RequestContext.getCurrentInstance().execute("toastr.info('Ya existe ese registro.', '')");              
             }
             else
             {       
                     this.getListaContactos().add(this.getContactos());                    
             }               
             
             this.setContactos(new SolicitudContactosCertificacionesDto());
    }
    
     public void cargarListaContactos(long idSolicitudCertificacion)
     {
    	   ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
    	   RecuperarListaContactosCertificacion vRespuesta = new RecuperarListaContactosCertificacion();
	       vRespuesta = vServiciosFacturacionRest.obtenerContactosCertificacion(idSolicitudCertificacion+"");
	       
	       mensajesBean.addMensajes(vRespuesta);
	       
	       if(vRespuesta.isOk())
	       {
	    	   this.setListaContactos(new ArrayList<>());
	    	   
	    	   vRespuesta.getLista().forEach(item->
	    	   {
	    		   SolicitudContactosCertificacionesDto vSolicitudContactosCertificacionesAux = new SolicitudContactosCertificacionesDto();
	    		   
	    		   vSolicitudContactosCertificacionesAux.setCelular(item.getCelular());
	    		   vSolicitudContactosCertificacionesAux.setComplemento(item.getComplemento());
	    		   vSolicitudContactosCertificacionesAux.setCorreo(item.getCorreoElectronico());
	    		   vSolicitudContactosCertificacionesAux.setNombre(item.getNombreCompleto());
	    		   vSolicitudContactosCertificacionesAux.setNumeroDocumento(item.getNumeroDocumento());
	    		   vSolicitudContactosCertificacionesAux.setTipoDocumentoIdentidad(item.getTipoDocumentoIdentidadId());
	    		   this.listaContactos.add(vSolicitudContactosCertificacionesAux);
	    	   });
	       }
	       else
	       {
	           this.listaDatosSistemaContribuyenteDto = new ArrayList<>();
	       }
     }
     
 	/**
 	 * Obtiene el datos de recertificacion.
 	 * 
 	 * @author: wilson.limachi
 	 * @Fecha: 20/08/2019
 	 * @param pSolicitud, objeto de tipo solicitud
 	 * @return Devuelve objeto Dto de respuesta
 	 */
     public void obtenerDatosReSolCertificacion(DatosRecertificacion pDatosRecertificacion)
     {	
    	 ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
    	 
    	 this.datosReCertificacion = vServiciosFacturacionRest.obtenerDatosReSolCertificacion(pDatosRecertificacion);
    	 
    	 if(!datosReCertificacion.isOk())
    	 {
    		 mensajesBean.addMensajes(datosReCertificacion);
    	 }
     }
    
     
    public void limpiar() 
    {
            numeroRegistroEditados = 0;
            this.setContactos(new SolicitudContactosCertificacionesDto());                        
    }
    
    /**
    * @Descipcion: Verifica si existe duplicados en la grilla de contactos.
    * @author: Wilson Limachi. 
    */
    public boolean existeDuplicados()
    {
    	    List<SolicitudContactosCertificacionesDto> listaComparar = new ArrayList<>();
    	    listaComparar = this.getListaContactos();
            Long count = listaComparar.stream().filter(p -> (p.getNumeroDocumento().equals(this.getContactos().getNumeroDocumento()) && p.getCorreo().equals(this.getContactos().getCorreo()))).count();
            return count > 0? true:false;            
    }
    
    /**
    * @Descipcion: Elimina de la grilla de contactos.
    * @author: Wilson Limachi. 
    */
    public void eliminarContacto()
    {
            this.getListaContactos().remove(this.getContactos());
            numeroRegistroEditados = 0;
            this.setContactos(new SolicitudContactosCertificacionesDto());
    }
    
    /**
    * @Descipcion: Cancela los datos del dialogo y cierra el cuadro de dialogo.
    * @author: Wilson Limachi. 
    */
    public void cancelarContacto()
    {
            this.setContactos(new SolicitudContactosCertificacionesDto());
            numeroRegistroEditados = 0;
    }
    
    public void onRowEdit(RowEditEvent event) 
    {
            numeroRegistroEditados--;
            System.out.println("numeroRegistroEditados" + numeroRegistroEditados);
    }
     
    public void onRowCancel(RowEditEvent event) {
        numeroRegistroEditados--;
        System.out.println("numeroRegistroEditados" + numeroRegistroEditados);
    }
        
    /**
    * @Descipcion: Edita la grilla de Contactos.
    * @author: Wilson Limachi. 
    */
    public void onCellEdit(CellEditEvent event) 
    {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) 
        {
//                DataTable dataTableObject = (DataTable) event.getSource();
//                
//                SolicitudContactosCertificacionesDto vRegistro = (SolicitudContactosCertificacionesDto) dataTableObject.getRowData();
//                int a ;
//                a = this.getListaContactos().size();
                
            //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
//    /**
//    * @Descipcion: obtiene los sistemas del contribuyente. 
//    * @author: Wilson Limachi. 
//    * @param idContribuyente: identificador contribuyente.
//    */
//    public void recuperarSistemasAsociadosProveedor(Long idContribuyente)
//    {
//            RespuestaListaDatosSistemaContribuyenteDto vResultado = new RespuestaListaDatosSistemaContribuyenteDto();
//            ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
//            SolicitudRegistroProveedorDto vSolicitud = new SolicitudRegistroProveedorDto();
//            vSolicitud.setContribuyenteProveedorId(idContribuyente);
//            vResultado = vServiciosFacturacionRest.recuperarSistemasAsociadosProveedor(vSolicitud);
//            
//            if(vResultado.isOk())
//            {
//                    this.listaDatosSistemaContribuyenteDto = vResultado.getListaDatosSistemaContribuyenteDto();
//            }
//            else
//            {
//                    this.listaDatosSistemaContribuyenteDto = new ArrayList<>();
//            }       
//            
//            mensajesBean.addMensajes(vResultado);
//    }
    
    /**
    * @Descipcion: establece valores de la grill contactos. 
    * @author: Wilson Limachi. 
    */
    public void ajaxEventListener(AjaxBehaviorEvent event)
    {
            
    }
    
    public void sumadorEditados(RowEditEvent event)
    {
            numeroRegistroEditados++;
            System.out.println("numeroRegistroEditados" + numeroRegistroEditados);
            
            UIData table = (UIData) event.getComponent();
            String tableId = table.getClientId();
            System.out.println("ID" + tableId);
    }
    
    public Map<String, String> erroresGrillaContactos()
    {
    	Map<String, String> errores = new HashMap<String, String>();
    	
    	if(this.getListaContactos().size() <= 0)
    	{
    		errores.put("Nro. Contactos", "Debe agregar al menos un contacto.");	
    	}
    	
    	if(this.getNumeroRegistroEditados() != 0)
    	{
    		errores.put("NIT ACTIVO", "Debe guardar las ediciones pendientes.");	
    	}
    	
    	return errores; 
    }

    public List<SistemasContribuyentesDto> getListaDatosSistemaContribuyenteDto() {
            return listaDatosSistemaContribuyenteDto;
    }

    public void setListaDatosSistemaContribuyenteDto(List<SistemasContribuyentesDto> listaDatosSistemaContribuyenteDto) {
            this.listaDatosSistemaContribuyenteDto = listaDatosSistemaContribuyenteDto;
    }

    public MensajesBean getMensajesBean() {
            return mensajesBean;
    }

    public void setMensajesBean(MensajesBean mensajesBean) {
            this.mensajesBean = mensajesBean;
    }

    public List<SolicitudContactosCertificacionesDto> getListaContactos() {
            return listaContactos;
    }

    public void setListaContactos(List<SolicitudContactosCertificacionesDto> listaContactos) {
            this.listaContactos = listaContactos;
    }

    public SolicitudContactosCertificacionesDto getContactos() {
            return contactos;
    }

    public void setContactos(SolicitudContactosCertificacionesDto contactos) {
            this.contactos = contactos;
    }

    public int getNumeroRegistroEditados() {
            return numeroRegistroEditados;
    }

    public void setNumeroRegistroEditados(int numeroRegistroEditados) {
            this.numeroRegistroEditados = numeroRegistroEditados;
    }

	public DatosRecertificacion getDatosReCertificacion() {
		return datosReCertificacion;
	}

	public void setDatosReCertificacion(DatosRecertificacion datosReCertificacion) {
		this.datosReCertificacion = datosReCertificacion;
	}
}
