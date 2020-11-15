package bo.gob.sin.sre.fac.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sap.cder.cest.controller.EstadoBean;
import bo.gob.sin.sen.enco.model.CookieHelper;
import bo.gob.sin.sre.fac.dto.DatosTramiteSolCertificacionDto;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@SuppressWarnings("deprecation")
@ManagedBean(name = "tramiteCertificacionBean")
@SessionScoped
public class TramiteCertificacionBean implements Serializable{
	private static final long serialVersionUID = 5707525509279209076L;
	
	@ManagedProperty(value = "#{estadoBean}")
	private EstadoBean recepcionBean; 
	EstadoDerivacionDto estado; 
	private DatosTramiteSolCertificacionDto tramiteSolicitudCertificacion;
	
	public void setearContexto()
	{
		this.obtenerContexto(estado);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("EstadoProceso", estado);

		DatosTramiteSolCertificacionDto vDatosTramiteSolicitudCertificacion = (DatosTramiteSolCertificacionDto) sessionMap.get(ParametrosCsis.CTX_DATOS_TRAMITE_CERTIFICACION);
		if (vDatosTramiteSolicitudCertificacion == null) {
			vDatosTramiteSolicitudCertificacion = new DatosTramiteSolCertificacionDto();
		}
		estado = recepcionBean.getEstado();
		if(estado !=null)
        {
        	long vTramiteId = estado.getTramiteId();
        	ServiciosFacturacionRest serviciosFacturacionRest =new ServiciosFacturacionRest();
        	vDatosTramiteSolicitudCertificacion=serviciosFacturacionRest.obtenerDatosTramiteCertificacion(vTramiteId);
			sessionMap.put(ParametrosCsis.CTX_DATOS_TRAMITE_CERTIFICACION, vDatosTramiteSolicitudCertificacion);
			this.tramiteSolicitudCertificacion = vDatosTramiteSolicitudCertificacion;
        }
	}

	public void obtenerContexto(EstadoDerivacionDto estado) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();

			CookieHelper cookie = new CookieHelper();
			Cookie vGalleta = cookie.getCookie("TramiteSIN");
			try {
				estado = (EstadoDerivacionDto) fromString(vGalleta.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}

			this.estado = estado;
		} catch (Exception e) {
			this.estado = null;
		}
	}
	
	private static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}
	
	public EstadoBean getRecepcionBean() {
		return recepcionBean;
	}

	public void setRecepcionBean(EstadoBean recepcionBean) {
		this.recepcionBean = recepcionBean;
	}	
	
	public DatosTramiteSolCertificacionDto getTramiteSolicitudCertificacion() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		tramiteSolicitudCertificacion = (DatosTramiteSolCertificacionDto)sessionMap.get(ParametrosCsis.CTX_DATOS_TRAMITE_CERTIFICACION);
		return tramiteSolicitudCertificacion;
	}

	public void setTramiteSolicitudCertificacion(DatosTramiteSolCertificacionDto tramiteSolicitudCertificacion) {
		this.tramiteSolicitudCertificacion = tramiteSolicitudCertificacion;
	}
}
