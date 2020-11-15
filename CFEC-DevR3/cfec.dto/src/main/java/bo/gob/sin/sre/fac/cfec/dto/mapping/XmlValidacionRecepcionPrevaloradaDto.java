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

/**
 * Clase que contiene los campos de entrada del servicio SOAP, Recepcion
 * Individual y paquete La clase tiene el metodo obtenerFormatoXml
 * 
 * @author carmen.silva
 * 
 */
@XmlRootElement(name = "validacionRecepcionIndividual")
@XmlType(propOrder = { "codigoSistema", "codigoAmbiente", "codigoEmision", "codigoModalidad", "nit", "cuis", "cufd",
		"cuape", "codigoDocumentoFiscal", "codigoDocumentoSector", "codigoSucursal", "codigoPuntoVenta",
		"codigoRecepcion" })
public class XmlValidacionRecepcionPrevaloradaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoSistema;
	private Integer codigoAmbiente;
	private Integer codigoEmision;
	private Integer codigoModalidad;
	private Long nit;
	private String cuis;
	private String cufd;
	private String cuape;
	private Integer codigoDocumentoFiscal;
	private Integer codigoDocumentoSector;
	private Integer codigoSucursal;
	private Integer codigoPuntoVenta;
	private Long codigoRecepcion;

	public XmlValidacionRecepcionPrevaloradaDto() {
	}

	@Override
	public String toString() {
		return "XmlValidacionRecepcionPrevaloradaDto [codigoSistema=" + codigoSistema + ", codigoAmbiente="
				+ codigoAmbiente + ", codigoEmision=" + codigoEmision + ", codigoModalidad=" + codigoModalidad
				+ ", nit=" + nit + ", cuis=" + cuis + ", cufd=" + cufd + ", cuape=" + cuape + ", codigoDocumentoFiscal="
				+ codigoDocumentoFiscal + ", codigoDocumentoSector=" + codigoDocumentoSector + ", codigoSucursal="
				+ codigoSucursal + ", codigoPuntoVenta=" + codigoPuntoVenta + ", codigoRecepcion=" + codigoRecepcion
				+ "]";
	}

	/**
	 * @autor edwin.coro
	 * @descripciÃ³n Obtiene los campos del dto en una estructura xml (string)
	 * @return cadena con los campos del dto en estructura xml (string)
	 * @fecha 14/12/2018
	 */
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
			contextObj = JAXBContext.newInstance(XmlValidacionRecepcionPrevaloradaDto.class);
			marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshallerObj.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
					Parametros.ARCHIVO_XSD_VALIDACION_RECEPCION_INDIVIDUAL);
			marshallerObj.marshal(this, xmlOutput);
			xmlOutput.flush();
			xmlOutput.close();
		} catch (JAXBException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
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

	/**
	 * @return the codigoSistema
	 */
	@XmlElement
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return the codigoAmbiente
	 */
	@XmlElement
	public Integer getCodigoAmbiente() {
		return codigoAmbiente;
	}

	/**
	 * @param codigoAmbiente the codigoAmbiente to set
	 */
	public void setCodigoAmbiente(Integer codigoAmbiente) {
		this.codigoAmbiente = codigoAmbiente;
	}

	/**
	 * @return the codigoEmision
	 */
	@XmlElement
	public Integer getCodigoEmision() {
		return codigoEmision;
	}

	/**
	 * @param codigoEmision the codigoEmision to set
	 */
	public void setCodigoEmision(Integer codigoEmision) {
		this.codigoEmision = codigoEmision;
	}

	/**
	 * @return the codigoModalidad
	 */
	@XmlElement
	public Integer getCodigoModalidad() {
		return codigoModalidad;
	}

	/**
	 * @param codigoModalidad the codigoModalidad to set
	 */
	public void setCodigoModalidad(Integer codigoModalidad) {
		this.codigoModalidad = codigoModalidad;
	}

	/**
	 * @return the nit
	 */
	@XmlElement
	public Long getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(Long nit) {
		this.nit = nit;
	}

	/**
	 * @return the cuis
	 */
	@XmlElement
	public String getCuis() {
		return cuis;
	}

	/**
	 * @param cuis the cuis to set
	 */
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	/**
	 * @return the cufd
	 */
	@XmlElement
	public String getCufd() {
		return cufd;
	}

	/**
	 * @param cufd the cufd to set
	 */
	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	/**
	 * @return the codigoDocumentoFiscal
	 */
	@XmlElement
	public Integer getCodigoDocumentoFiscal() {
		return codigoDocumentoFiscal;
	}

	/**
	 * @param codigoDocumentoFiscal the codigoDocumentoFiscal to set
	 */
	public void setCodigoDocumentoFiscal(Integer codigoDocumentoFiscal) {
		this.codigoDocumentoFiscal = codigoDocumentoFiscal;
	}

	/**
	 * @return the codigoDocumentoSector
	 */
	@XmlElement
	public Integer getCodigoDocumentoSector() {
		return codigoDocumentoSector;
	}

	/**
	 * @param codigoDocumentoSector the codigoDocumentoSector to set
	 */
	public void setCodigoDocumentoSector(Integer codigoDocumentoSector) {
		this.codigoDocumentoSector = codigoDocumentoSector;
	}

	/**
	 * @return the codigoSucursal
	 */
	@XmlElement
	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @param codigoSucursal the codigoSucursal to set
	 */
	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	@XmlElement(nillable = true)
	public Integer getCodigoPuntoVenta() {
		return codigoPuntoVenta;
	}

	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	/**
	 * @return the codigoRecepcion
	 */
	@XmlElement
	public Long getCodigoRecepcion() {
		return codigoRecepcion;
	}

	/**
	 * @param codigoRecepcion the codigoRecepcion to set
	 */
	public void setCodigoRecepcion(Long codigoRecepcion) {
		this.codigoRecepcion = codigoRecepcion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCuape() {
		return cuape;
	}

	public void setCuape(String cuape) {
		this.cuape = cuape;
	}

}