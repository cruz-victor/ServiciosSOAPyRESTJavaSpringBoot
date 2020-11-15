package bo.gob.sin.sre.fac.model;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.str.cexc.CodigoExcepcionSIN;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cexc.SINExcepcion;


@ManagedBean (name ="visorImpresionComponent")
@ViewScoped
public class VisorImpresionComponent implements Serializable
{
	private static final Logger LOG = LoggerFactory.getLogger(VisorImpresionComponent.class);
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init()
	{
	}
	
	public String getPath(String vReporte) 
	{
		if(vReporte!=null || !vReporte.isEmpty())
		{
	    	FacesContext context = FacesContext.getCurrentInstance();
	        String path = ((HttpServletRequest)context.getExternalContext().getRequest()).getRequestURI();
	        
	        context.getExternalContext().getSessionMap().put("archivoB64", vReporte);
			
		    String[] partes = path.split("/");
		    return "/" + partes[1];
		}
		else
		{
			//RequestContext.getCurrentInstance().execute("toastr.error('Archivo inexistente, consulte al administrador del sistema','Error')");
	         LogExcepcion.registrar((new SINExcepcion(CodigoExcepcionSIN.STRING_B64_NULA)).set("archivoB64", vReporte), LOG, MethodSign.build(new Object[]{vReporte}));
			return("");
		}
	}	
}

