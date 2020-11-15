package bo.gob.sin.sre.fac.cfec.dto.comp.interfaces;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class ObtencionPorDiaCompras implements IObtencionPorDiaCompras {

	Long nit;
	Date fecha;
	String codigoSistema;
	String cufd;
	String cuis;
	Integer codigoSucursal;
	Integer codigoPuntoVenta;
	Integer codigoAmbiente;


	public ObtencionPorDiaCompras() {

	}


	public ObtencionPorDiaCompras(Long nit, Date fecha, String codigoSistema, String cufd, String cuis,
			Integer codigoSucursal, Integer codigoPuntoVenta, Integer codigoAmbiente) {
		super();
		this.nit = nit;
		this.fecha = fecha;
		this.codigoSistema = codigoSistema;
		this.cufd = cufd;
		this.cuis = cuis;
		this.codigoSucursal = codigoSucursal;
		this.codigoPuntoVenta = codigoPuntoVenta;
		this.codigoAmbiente = codigoAmbiente;
	}


	@Override
	public Long getNit() {
		return nit;
	}
	@Override
	public Date getFecha() {
		return fecha;
	}
	@Override
	public String getCodigoSistema() {
		return codigoSistema;
	}
	@Override
	public String getCufd() {
		return cufd;
	}
	@Override
	public String getCuis() {
		return cuis;
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
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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