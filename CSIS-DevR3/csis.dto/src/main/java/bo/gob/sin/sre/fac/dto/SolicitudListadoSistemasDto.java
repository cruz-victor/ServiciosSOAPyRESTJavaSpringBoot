package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Map;

public class SolicitudListadoSistemasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean totalRegistros;
	private int primerRegistro;
	private int tamanioPagina;
	private String campoOrden;
	private boolean ascendente;
	private Map<String, Object> filtros;

	public boolean isTotalRegistros() {
		return totalRegistros;
	}

	public int getPrimerRegistro() {
		return primerRegistro;
	}

	public int getTamanioPagina() {
		return tamanioPagina;
	}

	public String getCampoOrden() {
		return campoOrden;
	}

	public boolean isAscendente() {
		return ascendente;
	}

	public Map<String, Object> getFiltros() {
		return filtros;
	}

	public void setTotalRegistros(boolean totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public void setPrimerRegistro(int primerRegistro) {
		this.primerRegistro = primerRegistro;
	}

	public void setTamanioPagina(int tamanioPagina) {
		this.tamanioPagina = tamanioPagina;
	}

	public void setCampoOrden(String campoOrden) {
		this.campoOrden = campoOrden;
	}

	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}

	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}
}
