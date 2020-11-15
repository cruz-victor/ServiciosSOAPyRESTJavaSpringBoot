package bo.gob.sin.sre.fac.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "comportamientoPaginaModel")
@ViewScoped
public class ComportamientoPaginaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean esVisibleGrilla;
	private boolean esVisibleTexto;
	private boolean esVisiblePanel;
	private boolean esVisibleBoton;
	private boolean esVisiblePagina;
	private boolean esEditable;
	private boolean esVisiblePanelGroup; 
	private boolean esVisibleLink;
	private String descripcionDialogo;
	private Integer valorCuadroDialogo;
	private boolean esDisableControl;
	private Map<String, Boolean> controlGrillas;
	
	@PostConstruct
	public void init() 
	{
		controlGrillas = new HashMap<String, Boolean>();
	}

	public boolean isEsVisibleGrilla() {
		return esVisibleGrilla;
	}

	public void setEsVisibleGrilla(boolean esVisibleGrilla) {
		this.esVisibleGrilla = esVisibleGrilla;
	}

	public boolean isEsVisiblePanel() {
		return esVisiblePanel;
	}

	public void setEsVisiblePanel(boolean esVisiblePanel) {
		this.esVisiblePanel = esVisiblePanel;
	}

	public boolean isEsVisibleBoton() {
		return esVisibleBoton;
	}

	public void setEsVisibleBoton(boolean esVisibleBoton) {
		this.esVisibleBoton = esVisibleBoton;
	}

	public boolean isEsVisiblePagina() {
		return esVisiblePagina;
	}

	public void setEsVisiblePagina(boolean esVisiblePagina) {
		this.esVisiblePagina = esVisiblePagina;
	}

	public boolean isEsVisiblePanelGroup() {
		return esVisiblePanelGroup;
	}

	public void setEsVisiblePanelGroup(boolean esVisiblePanelGroup) {
		this.esVisiblePanelGroup = esVisiblePanelGroup;
	}

	public boolean isEsEditable() {
		return esEditable;
	}

	public void setEsEditable(boolean esEditable) {
		this.esEditable = esEditable;
	}

	public boolean isEsVisibleLink() {
		return esVisibleLink;
	}

	public void setEsVisibleLink(boolean esVisibleLink) {
		this.esVisibleLink = esVisibleLink;
	}

	public String getDescripcionDialogo() {
		return descripcionDialogo;
	}

	public void setDescripcionDialogo(String descripcionDialogo) {
		this.descripcionDialogo = descripcionDialogo;
	}

	public Integer getValorCuadroDialogo() {
		return valorCuadroDialogo;
	}

	public void setValorCuadroDialogo(Integer valorCuadroDialogo) {
		this.valorCuadroDialogo = valorCuadroDialogo;
	}

	public boolean isEsVisibleTexto() {
		return esVisibleTexto;
	}

	public void setEsVisibleTexto(boolean esVisibleTexto) {
		this.esVisibleTexto = esVisibleTexto;
	}

	public boolean isEsDisableControl() {
		return esDisableControl;
	}

	public void setEsDisableControl(boolean esDisableControl) {
		this.esDisableControl = esDisableControl;
	}

	/**
	 * @return the controlGrillas
	 */
	public Map<String, Boolean> getControlGrillas() {
		return controlGrillas;
	}

	/**
	 * @param controlGrillas the controlGrillas to set
	 */
	public void setControlGrillas(Map<String, Boolean> controlGrillas) {
		this.controlGrillas = controlGrillas;
	}
}
