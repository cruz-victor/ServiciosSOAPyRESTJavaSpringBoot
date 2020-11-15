package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "verificacionInsituComponentesModel")
@ViewScoped
public class VerificacionInsituComponentesModel implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private long nit;
	
	@PostConstruct
    public void init() {
		
		this.limpiar();
    }
	
	
	public void limpiar() {
		
		this.nit=0;

	}

	public long getNit() {
		return nit;
	}


	public void setNit(long nit) {
		this.nit = nit;
	}

}
