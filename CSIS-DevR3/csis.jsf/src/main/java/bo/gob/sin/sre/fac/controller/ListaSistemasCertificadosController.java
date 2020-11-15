package bo.gob.sin.sre.fac.controller;



import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.model.ListadoSistemasCertificadosLazyDataModel;


@ManagedBean(name = "listaSistemasController")
@ViewScoped
public class ListaSistemasCertificadosController implements Serializable {
	private static final long serialVersionUID = 1L;

	private ListadoSistemasCertificadosLazyDataModel lista;

	public void Load() {
		lista = new ListadoSistemasCertificadosLazyDataModel();
	}

	public ListadoSistemasCertificadosLazyDataModel getLista() {
		return lista;
	}

	public void setLista(ListadoSistemasCertificadosLazyDataModel lista) {
		this.lista = lista;
	}

}