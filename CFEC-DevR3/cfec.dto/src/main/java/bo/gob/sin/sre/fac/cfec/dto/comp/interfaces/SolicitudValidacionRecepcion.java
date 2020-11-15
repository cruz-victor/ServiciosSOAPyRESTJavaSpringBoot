package bo.gob.sin.sre.fac.cfec.dto.comp.interfaces;

import javax.xml.bind.annotation.XmlElement;

public class SolicitudValidacionRecepcion implements ISolicitudValidacionRecepcion {

	String codigoSistema;

	Integer codigoAmbiente;

	Long nit;

	String cuis;

	String cufd;

	Integer codigoSucursal;

	Integer codigoPuntoVenta;

	Long codigoRecepcion;

	public SolicitudValidacionRecepcion() {

	}

	public SolicitudValidacionRecepcion(String codigoSistema, Integer codigoAmbiente, Long nit, String cuis, String cufd, Integer codigoSucursal, Integer codigoPuntoVenta, Long codigRecepcion) {
		super();
		this.codigoSistema = codigoSistema;
		this.codigoAmbiente = codigoAmbiente;
		this.nit = nit;
		this.cuis = cuis;
		this.cufd = cufd;
		this.codigoSucursal = codigoSucursal;
		this.codigoPuntoVenta = codigoPuntoVenta;
		this.codigoRecepcion = codigRecepcion;
	}

	@Override
	public String getCodigoSistema() {
		return codigoSistema;
	}

	@Override
	public Integer getCodigoAmbiente() {
		return codigoAmbiente;
	}

	@Override
	public Long getNit() {
		return nit;
	}

	@Override
	public String getCuis() {
		return cuis;
	}

	@Override
	public String getCufd() {
		return cufd;
	}

	@Override
	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}

	@Override
	public Integer getCodigoPuntoVenta() {
		return codigoPuntoVenta;
	}

	@Override
	public Long getCodigoRecepcion() {
		return codigoRecepcion;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoAmbiente(Integer codigoAmbiente) {
		this.codigoAmbiente = codigoAmbiente;
	}

	@Override
	@XmlElement(required=true)
	public void setNit(Long nit) {
		this.nit = nit;
	}

	@Override
	@XmlElement(required=true)
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	@Override
	@XmlElement(required=true)
	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	@Override
	@XmlElement(nillable = true)
	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoRecepcion(Long codigoRecepcion) {
		this.codigoRecepcion = codigoRecepcion;
	}
}