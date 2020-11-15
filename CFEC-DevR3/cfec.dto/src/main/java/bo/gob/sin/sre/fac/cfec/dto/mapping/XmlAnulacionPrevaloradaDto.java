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
@XmlType(propOrder = { "codigoSistema", "codigoAmbiente", "codigoModalidad", "nit", "cuis", "cufd", "cuape",
		"codigoDocumentoFiscal", "codigoDocumentoSector", "codigoSucursal", "codigoPuntoVenta", "numeroDocumentoFiscal",
		"cufp", "codigoMotivo" })
public class XmlAnulacionPrevaloradaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoSistema;
	private Integer codigoAmbiente;
	private Integer codigoModalidad;
	private Long nit;
	private String cuis;
	private String cufd;
	private String cuape;
	private Integer codigoDocumentoFiscal;
	private Integer codigoDocumentoSector;
	private Integer codigoSucursal;
	private Integer codigoPuntoVenta;
	private Long numeroDocumentoFiscal;
	private String cufp;
	private Integer codigoMotivo;

	public XmlAnulacionPrevaloradaDto() {

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
			contextObj = JAXBContext.newInstance(XmlAnulacionPrevaloradaDto.class);
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
		return "XmlAnulacionPrevaloradaDto [codigoSistema=" + codigoSistema + ", codigoAmbiente=" + codigoAmbiente
				+ ", codigoModalidad=" + codigoModalidad + ", nit=" + nit + ", cuis=" + cuis + ", cufd=" + cufd
				+ ", cuape=" + cuape + ", codigoDocumentoFiscal=" + codigoDocumentoFiscal + ", codigoDocumentoSector="
				+ codigoDocumentoSector + ", codigoSucursal=" + codigoSucursal + ", codigoPuntoVenta="
				+ codigoPuntoVenta + ", numeroDocumentoFiscal=" + numeroDocumentoFiscal + ", cufp=" + cufp
				+ ", codigoMotivo=" + codigoMotivo + "]";
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
	public String getCufp() {
		return cufp;
	}

	@XmlElement
	public Integer getCodigoMotivo() {
		return codigoMotivo;
	}

	@XmlElement
	public String getCuape() {
		return cuape;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public void setCodigoAmbiente(Integer codigoAmbiente) {
		this.codigoAmbiente = codigoAmbiente;
	}

	public void setCodigoModalidad(Integer codigoModalidad) {
		this.codigoModalidad = codigoModalidad;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public void setCuis(String cuis) {
		this.cuis = cuis;
	}

	public void setCufd(String cufd) {
		this.cufd = cufd;
	}

	public void setCodigoDocumentoFiscal(Integer codigoDocumentoFiscal) {
		this.codigoDocumentoFiscal = codigoDocumentoFiscal;
	}

	public void setCodigoDocumentoSector(Integer codigoDocumentoSector) {
		this.codigoDocumentoSector = codigoDocumentoSector;
	}

	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	public void setNumeroDocumentoFiscal(Long numeroDocumentoFiscal) {
		this.numeroDocumentoFiscal = numeroDocumentoFiscal;
	}

	public void setCufp(String cufp) {
		this.cufp = cufp;
	}

	public void setCodigoMotivo(Integer codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	
	public void setCuape(String cuape) {
		this.cuape = cuape;
	}
}