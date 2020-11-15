package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Map;

public class SolicitudPruebasSistemasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long tramiteId;
	private long contribuyenteId;
	private long nit;
	private long sistemaId;
	private long solicitudId;
	private long tipoPruebaId;
	private long registroPruebaManualId;
	private String observaciones;
	private int estadoPruebaId;
	private int primerRegistro;
	private int tamanioPagina;
	private String campoOrden;
	private boolean ascendente;
	private Map<String, Object> filtros;

	public SolicitudPruebasSistemasDto(long tramiteId, long contribuyenteId, long sistemaId, int estadoPruebaId,
			int primerRegistro, int tamanioPagina, String campoOrden, boolean ascendente) {
		super();
		this.tramiteId = tramiteId;
		this.contribuyenteId = contribuyenteId;
		this.sistemaId = sistemaId;
		this.estadoPruebaId = estadoPruebaId;
		this.primerRegistro = primerRegistro;
		this.tamanioPagina = tamanioPagina;
		this.campoOrden = campoOrden;
		this.ascendente = ascendente;
	}

	public boolean getAscendente() {
		return ascendente;
	}

	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}

	public int getPrimerRegistro() {
		return primerRegistro;
	}

	public void setPrimerRegistro(int primerRegistro) {
		this.primerRegistro = primerRegistro;
	}

	public int getTamanioPagina() {
		return tamanioPagina;
	}

	public void setTamanioPagina(int tamanioPagina) {
		this.tamanioPagina = tamanioPagina;
	}

	public String getCampoOrden() {
		return campoOrden;
	}

	public void setCampoOrden(String campoOrden) {
		this.campoOrden = campoOrden;
	}

	public long getRegistroPruebaManualId() {
		return registroPruebaManualId;
	}

	public void setRegistroPruebaManualId(long registroPruebaManualId) {
		this.registroPruebaManualId = registroPruebaManualId;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getEstadoPruebaId() {
		return estadoPruebaId;
	}

	public void setEstadoPruebaId(int estadoPruebaId) {
		this.estadoPruebaId = estadoPruebaId;
	}

	public SolicitudPruebasSistemasDto() {
		super();
	}

	public long getSolicitudId() {
		return solicitudId;
	}

	public void setSolicitudId(long solicitudId) {
		this.solicitudId = solicitudId;
	}

	public long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public long getTipoPruebaId() {
		return tipoPruebaId;
	}

	public void setTipoPruebaId(long tipoPruebaId) {
		this.tipoPruebaId = tipoPruebaId;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public Map<String, Object> getFiltros() {
		return filtros;
	}

	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}

}
