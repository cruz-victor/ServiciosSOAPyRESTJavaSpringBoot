package bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces;

import javax.xml.bind.annotation.XmlElement;

public class SolicitudRecepcionAnulacion implements ISolicitudRecepcionAnulacion {


	String codigoSistema;

	Integer codigoAmbiente;

	Integer codigoModalidad;

	Long nit;

	String cuis;

	String cufd;

	Integer codigoDocumentoFiscal;

	Integer codigoDocumentoSector;

	Integer codigoSucursal;

	Integer codigoPuntoVenta;

	Long numeroDocumentoFiscal;
	
	String cufp;

	public SolicitudRecepcionAnulacion() {

	}

	public SolicitudRecepcionAnulacion(String codigoSistema, Integer codigoAmbiente,
			Integer codigoModalidad, Long nit, String cuis, String cufd, Integer codigoDocumentoFiscal,
			Integer codigoDocumentoSector, Integer codigoSucursal, Integer codigoPuntoVenta, Long numeroDocumentoFiscal,
			String cufp) {
		super();
		this.codigoSistema = codigoSistema;
		this.codigoAmbiente = codigoAmbiente;
		this.codigoModalidad = codigoModalidad;
		this.nit = nit;
		this.cuis = cuis;
		this.cufd = cufd;
		this.codigoDocumentoFiscal = codigoDocumentoFiscal;
		this.codigoDocumentoSector = codigoDocumentoSector;
		this.codigoSucursal = codigoSucursal;
		this.codigoPuntoVenta = codigoPuntoVenta;
		this.numeroDocumentoFiscal = numeroDocumentoFiscal;
		this.cufp = cufp;
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
	public Integer getCodigoModalidad() {
		return codigoModalidad;
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
	public Integer getCodigoDocumentoFiscal() {
		return codigoDocumentoFiscal;
	}

	@Override
	public Integer getCodigoDocumentoSector() {
		return codigoDocumentoSector;
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
	public Long getNumeroDocumentoFiscal() {
		return numeroDocumentoFiscal;
	}
	
	@Override
	public String getCufp() {
		return cufp;
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
	public void setCodigoModalidad(Integer codigoModalidad) {
		this.codigoModalidad = codigoModalidad;
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
	public void setCodigoDocumentoFiscal(Integer codigoDocumentoFiscal) {
		this.codigoDocumentoFiscal = codigoDocumentoFiscal;
	}

	@Override
	@XmlElement(required=true)
	public void setCodigoDocumentoSector(Integer codigoDocumentoSector) {
		this.codigoDocumentoSector = codigoDocumentoSector;
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
	public void setNumeroDocumentoFiscal(Long numeroDocumentoFiscal) {
		this.numeroDocumentoFiscal = numeroDocumentoFiscal;
	}
	
	@Override
	@XmlElement(required=true)
	public void setCufp(String cufp) {
		this.cufp = cufp;
	}
}