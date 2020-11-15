package bo.gob.sin.sre.fac.cfec.dto.interfaces;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class SolicitudRecepcion implements ISolicitudRecepcion {


	String codigoSistema;

	Integer codigoAmbiente;

	Integer codigoEmision;

	Integer codigoModalidad;

	Long nit;

	String cuis;

	String cufd;

	Integer codigoDocumentoFiscal;

	Integer codigoDocumentoSector;

	Integer codigoSucursal;

	Integer codigoPuntoVenta;

	Date fechaEnvio;

	String archivo;

	String hashArchivo;

	public SolicitudRecepcion() {

	}

	public SolicitudRecepcion(String codigoSistema, Integer codigoAmbiente, Integer codigoEmision,
			Integer codigoModalidad, Long nit, String cuis, String cufd, Integer codigoDocumentoFiscal,
			Integer codigoDocumentoSector, Integer codigoSucursal, Integer codigoPuntoVenta, Date fechaEnvio,
			String archivo, String hashArchivo) {
		super();
		this.codigoSistema = codigoSistema;
		this.codigoAmbiente = codigoAmbiente;
		this.codigoEmision = codigoEmision;
		this.codigoModalidad = codigoModalidad;
		this.nit = nit;
		this.cuis = cuis;
		this.cufd = cufd;
		this.codigoDocumentoFiscal = codigoDocumentoFiscal;
		this.codigoDocumentoSector = codigoDocumentoSector;
		this.codigoSucursal = codigoSucursal;
		this.codigoPuntoVenta = codigoPuntoVenta;
		this.fechaEnvio = fechaEnvio;
		this.archivo = archivo;
		this.hashArchivo = hashArchivo;
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
	public Integer getCodigoEmision() {
		return codigoEmision;
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
	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	@Override
	public String getArchivo() {
		return archivo;
	}

	@Override
	public String getHashArchivo() {
		return hashArchivo;
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
	public void setCodigoEmision(Integer codigoEmision) {
		this.codigoEmision = codigoEmision;
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
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	@Override
	@XmlElement(required=true)
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	@Override
	@XmlElement(required=true)
	public void setHashArchivo(String hashArchivo) {
		this.hashArchivo = hashArchivo;
	}
}