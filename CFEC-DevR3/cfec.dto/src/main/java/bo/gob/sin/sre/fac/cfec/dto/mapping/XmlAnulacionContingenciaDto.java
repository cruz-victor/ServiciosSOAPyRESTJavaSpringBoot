package bo.gob.sin.sre.fac.cfec.dto.mapping;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;

@XmlRootElement(name = "anulacionFacturaNotaFiscal")
@XmlType(propOrder = { "codigoSistema", "codigoAmbiente", "codigoEmision", "codigoModalidad", "nit", "cuis", "cufd",
		"codigoDocumentoFiscal", "codigoDocumentoSector", "codigoSucursal", "codigoPuntoVenta", "numeroDocumentoFiscal",
		"cuf", "codigoMotivo", "caed" })
public class XmlAnulacionContingenciaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoSistema;
	private Integer codigoAmbiente;
	private Integer codigoEmision;
	private Integer codigoModalidad;
	private Long nit;
	private String cuis;
	private String cufd;
	private Integer codigoDocumentoFiscal;
	private Integer codigoDocumentoSector;
	private Integer codigoSucursal;
	private Integer codigoPuntoVenta;
	private Long numeroDocumentoFiscal;
	private String cuf;
	private Integer codigoMotivo;
	private String caed;

	public XmlAnulacionContingenciaDto() {

	}

	public String obtenerFormatoXml() {

		OutputStream xmlOutput = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			@Override
			public String toString() {
				return this.string.toString();
			}
		};

		JAXBContext contextObj = null;
		Marshaller marshallerObj = null;
		try {
			contextObj = JAXBContext.newInstance(XmlAnulacionContingenciaDto.class);
			marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshallerObj.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, Parametros.ARCHIVO_XSD_ANULACION);
			marshallerObj.marshal(this, xmlOutput);
			xmlOutput.flush();
			xmlOutput.close();
		} catch (JAXBException e) {
			return e.getMessage();
		} catch (IOException e) {
			return e.getMessage();
		} finally {
			if (contextObj != null) {
				contextObj = null;
			}
			if (marshallerObj != null) {
				marshallerObj = null;
			}
		}

		return xmlOutput.toString();
	}

	@Override
	public String toString() {
		return "XmlAnulacionContingenciaDto [codigoSistema=" + codigoSistema + ", codigoAmbiente=" + codigoAmbiente
				+ ", codigoEmision=" + codigoEmision + ", codigoModalidad=" + codigoModalidad + ", nit=" + nit
				+ ", cuis=" + cuis + ", cufd=" + cufd + ", codigoDocumentoFiscal=" + codigoDocumentoFiscal
				+ ", codigoDocumentoSector=" + codigoDocumentoSector + ", codigoSucursal=" + codigoSucursal
				+ ", codigoPuntoVenta=" + codigoPuntoVenta + ", numeroDocumentoFiscal=" + numeroDocumentoFiscal
				+ ", cuf=" + cuf + ", codigoMotivo=" + codigoMotivo + ", caed=" + caed + "]";
	}

	@XmlElement
	public String getCodigoSistema() {
		return codigoSistema;
	}

	@XmlElement
	public Integer getCodigoAmbiente() {
		return codigoAmbiente;
	}

	@XmlElement
	public Integer getCodigoEmision() {
		return codigoEmision;
	}

	@XmlElement
	public Integer getCodigoModalidad() {
		return codigoModalidad;
	}

	@XmlElement
	public Long getNit() {
		return nit;
	}

	@XmlElement
	public String getCuis() {
		return cuis;
	}

	@XmlElement
	public String getCufd() {
		return cufd;
	}

	@XmlElement
	public Integer getCodigoDocumentoFiscal() {
		return codigoDocumentoFiscal;
	}

	@XmlElement
	public Integer getCodigoDocumentoSector() {
		return codigoDocumentoSector;
	}

	@XmlElement
	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}

	@XmlElement(nillable = true)
	public Integer getCodigoPuntoVenta() {
		return codigoPuntoVenta;
	}

	@XmlElement
	public Long getNumeroDocumentoFiscal() {
		return numeroDocumentoFiscal;
	}

	@XmlElement
	public String getCuf() {
		return cuf;
	}

	@XmlElement
	public Integer getCodigoMotivo() {
		return codigoMotivo;
	}

	@XmlElement
	public String getCaed() {
		return caed;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @param codigoAmbiente the codigoAmbiente to set
	 */
	public void setCodigoAmbiente(Integer codigoAmbiente) {
		this.codigoAmbiente = codigoAmbiente;
	}

	public void setCodigoEmision(Integer codigoEmision) {
		this.codigoEmision = codigoEmision;
	}

	/**
	 * @param codigoModalidad the codigoModalidad to set
	 */
	public void setCodigoModalidad(Integer codigoModalidad) {
		this.codigoModalidad = codigoModalidad;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(Long nit) {
		this.nit = nit;
	}

	/**
	 * @param cuis the cuis to set
	 */
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	/**
	 * @param cufd the cufd to set
	 */
	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	/**
	 * @param codigoDocumentoFiscal the codigoDocumentoFiscal to set
	 */
	public void setCodigoDocumentoFiscal(Integer codigoDocumentoFiscal) {
		this.codigoDocumentoFiscal = codigoDocumentoFiscal;
	}

	/**
	 * @param codigoDocumentoSector the codigoDocumentoSector to set
	 */
	public void setCodigoDocumentoSector(Integer codigoDocumentoSector) {
		this.codigoDocumentoSector = codigoDocumentoSector;
	}

	/**
	 * @param codigoSucursal the codigoSucursal to set
	 */
	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	/**
	 * @param codigoPuntoVenta the codigoPuntoVenta to set
	 */
	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	/**
	 * @param numeroDocumentoFiscal the numeroDocumentoFiscal to set
	 */
	public void setNumeroDocumentoFiscal(Long numeroDocumentoFiscal) {
		this.numeroDocumentoFiscal = numeroDocumentoFiscal;
	}

	/**
	 * @param cuf the cuf to set
	 */
	public void setCuf(String cuf) {
		this.cuf = cuf;
	}

	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(Integer codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	/**
	 * @param caed the caed to set
	 */
	public void setCaed(String caed) {
		this.caed = caed;
	}

}