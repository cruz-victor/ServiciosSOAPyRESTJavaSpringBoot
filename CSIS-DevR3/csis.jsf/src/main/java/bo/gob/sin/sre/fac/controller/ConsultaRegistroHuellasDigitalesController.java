package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.model.CertificacionParametrics;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.ListadoSistemasCertificadosModelList;
import bo.gob.sin.sre.fac.model.RegistroHuellasDigitalesComponent;
import bo.gob.sin.sre.fac.model.RegistroHuellasDigitalesListaComponent;
import bo.gob.sin.sre.fac.model.SistemasCertificadosModel;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean(name = "consultaRegistroHuellasDigitalesController")
@RequestScoped
public class ConsultaRegistroHuellasDigitalesController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{registroHuellasDigitalesListaComponent}")
	RegistroHuellasDigitalesListaComponent registroHuellasDigitalesListaComponent;
	
	@ManagedProperty(value = "#{listadoSistemasCertificadosModelList}")
	ListadoSistemasCertificadosModelList listadoSistemasCertificadosModelList;
	
	@ManagedProperty(value = "#{registroHuellasDigitalesComponent}")
	RegistroHuellasDigitalesComponent registroHuellasDigitalesComponent;

	@ManagedProperty(value = "#{sistemasCertificadosModel}")
	SistemasCertificadosModel sistemasCertificadosModel;

	@ManagedProperty(value = "#{certificacionParametrics}")
	CertificacionParametrics certificacionParametrics;
	
	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	public void Load() 
	{	
		//listadoSistemasCertificadosModelList.cargarListaSistemasContribuyentes(1001771198L);
		listadoSistemasCertificadosModelList.cargarListaSistemasContribuyentes(this.contextoUsuarioModel.getContribuyente().getIfc());
	}
	
	public void LoadComponente(Long idContribuyente, Long idSistema) 
	{	
		if (!FacesContext.getCurrentInstance().isPostback())
		{
			this.mostrarConsultaComponentes(idSistema, idContribuyente);
		}
	}
	
	public void mostrarConsultaComponentes()
	{
		this.getRegistroHuellasDigitalesListaComponent().obtenerComponentesRegistradosContribuyente(this.getSistemasCertificadosModel().getSistemasContribuyentes().getSistemaId(), this.getContextoUsuarioModel().getContribuyente().getIfc());
	}
	
	public void mostrarConsultaComponentes(Long pSistemaId, Long pIdContribuyente)
	{	
		this.getRegistroHuellasDigitalesListaComponent().obtenerComponentesRegistradosContribuyente(pSistemaId, pIdContribuyente);
	}
	
	public RegistroHuellasDigitalesComponent getRegistroHuellasDigitalesComponent() {
		return registroHuellasDigitalesComponent;
	}

	public void setRegistroHuellasDigitalesComponent(RegistroHuellasDigitalesComponent registroHuellasDigitalesComponent) {
		this.registroHuellasDigitalesComponent = registroHuellasDigitalesComponent;
	}

	public CertificacionParametrics getCertificacionParametrics() {
		return certificacionParametrics;
	}

	public void setCertificacionParametrics(CertificacionParametrics certificacionParametrics) {
		this.certificacionParametrics = certificacionParametrics;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public RegistroHuellasDigitalesListaComponent getRegistroHuellasDigitalesListaComponent() {
		return registroHuellasDigitalesListaComponent;
	}

	public void setRegistroHuellasDigitalesListaComponent(
			RegistroHuellasDigitalesListaComponent registroHuellasDigitalesListaComponent) {
		this.registroHuellasDigitalesListaComponent = registroHuellasDigitalesListaComponent;
	}

	public ListadoSistemasCertificadosModelList getListadoSistemasCertificadosModelList() {
		return listadoSistemasCertificadosModelList;
	}

	public void setListadoSistemasCertificadosModelList(
			ListadoSistemasCertificadosModelList listadoSistemasCertificadosModelList) {
		this.listadoSistemasCertificadosModelList = listadoSistemasCertificadosModelList;
	}

	public SistemasCertificadosModel getSistemasCertificadosModel() {
		return sistemasCertificadosModel;
	}

	public void setSistemasCertificadosModel(SistemasCertificadosModel sistemasCertificadosModel) {
		this.sistemasCertificadosModel = sistemasCertificadosModel;
	}
}
