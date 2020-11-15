package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.sre.fac.controller.TramiteCertificacionBean;
import bo.gob.sin.sre.fac.service.ServiciosPadronRestClient;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@ManagedBean(name = "componenteDatosBasicos")
@ViewScoped
public class DatosBasicosComponents implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	private ContribuyenteDto datosBasicosContribuyente;

	@PostConstruct
	void init() {
		this.datosBasicosContribuyente = new ContribuyenteDto();
		if (contextoUsuarioModel.getUsuario().getTipoUsuario().equals(ConstFacturacion.TIPO_USUARIO_CONTRIBUYENTE)) {
			this.datosBasicosContribuyente = contextoUsuarioModel.getContribuyente();	
		}else {
			this.CargarContribuyenteXIFC(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getPersonaContribuyenteId());
		}		
	}

	public void CargarContribuyenteXIFC(Long pIfc) {
		ServiciosPadronRestClient vServicio = new ServiciosPadronRestClient();
		ContribuyenteDto vResultadoNitDatosBasicosDto = vServicio.ObtenerDatosBasicosXIFC(pIfc);
		if(vResultadoNitDatosBasicosDto!=null)
		{
			datosBasicosContribuyente = vResultadoNitDatosBasicosDto;
		}
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public void setDatosBasicosContribuyente(ContribuyenteDto datosBasicosContribuyente) {
		this.datosBasicosContribuyente = datosBasicosContribuyente;
	}

	public ContribuyenteDto getDatosBasicosContribuyente() {
		return datosBasicosContribuyente;
	}
	
	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}
}

