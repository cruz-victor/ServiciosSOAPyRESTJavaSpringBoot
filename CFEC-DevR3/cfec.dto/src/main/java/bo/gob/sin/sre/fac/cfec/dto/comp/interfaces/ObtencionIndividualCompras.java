package bo.gob.sin.sre.fac.cfec.dto.comp.interfaces;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class ObtencionIndividualCompras implements IObtencionIndividualCompras {

	Long nit;
	String cuf;
	Long numeroFactura;
	String codigoSistema;
	String cufd;
	String cuis;
	Integer codigoSucursal;
	Integer codigoPuntoVenta;
	Integer codigoAmbiente;
	
	public ObtencionIndividualCompras() {

	}

	public ObtencionIndividualCompras(Long nit, String cuf, Long numeroFactura,
			String codigoSistema, String cufd, String cuis, Integer codigoSucursal, Integer codigoPuntoVenta,
			Integer codigoAmbiente) {
		super();
		this.nit = nit;
		this.cuf = cuf;
		this.numeroFactura = numeroFactura;
		this.codigoSistema = codigoSistema;
		this.cufd = cufd;
		this.cuis = cuis;
		this.codigoSucursal = codigoSucursal;
		this.codigoPuntoVenta = codigoPuntoVenta;
		this.codigoAmbiente = codigoAmbiente;
	}

	public Long getNit() {
		return nit;
	}

	public String getCuf() {
		return cuf;
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public String getCufd() {
		return cufd;
	}

	public String getCuis() {
		return cuis;
	}

	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}

	public Integer getCodigoPuntoVenta() {
		return codigoPuntoVenta;
	}

	public Integer getCodigoAmbiente() {
		return codigoAmbiente;
	}

	@Override
	@XmlElement(required=true)
	public void setNit(Long nit) {
		this.nit = nit;
	}
	
	@Override
	@XmlElement(required=true)
	public void setCuf(String cuf) {
		this.cuf = cuf;
	}

	@Override
	@XmlElement(required=true)
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	@Override
	@XmlElement(required=true)
	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	@Override
	@XmlElement(required=true)
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoAmbiente(Integer codigoAmbiente) {
		this.codigoAmbiente = codigoAmbiente;
	}

}