package bo.gob.sin.sre.fac.cfec.dto.comp.interfaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(SolicitudValidacionRecepcionAdapter.class)
public interface ISolicitudValidacionRecepcion {

	String getCodigoSistema();

	Integer getCodigoAmbiente();

	Long getNit();

	String getCuis();

	String getCufd();

	Integer getCodigoSucursal();

	Integer getCodigoPuntoVenta();

	Long getCodigoRecepcion();
	
	@XmlElement(required=true)
	void setCodigoSistema(String codigoSistema);

	@XmlElement(required=true)
	void setCodigoAmbiente(Integer codigoAmbiente);

	@XmlElement(required=true)
	void setNit(Long nit);

	@XmlElement(required=true)
	void setCuis(String cuis);

	@XmlElement(required=true)
	void setCufd(String cufd);

	@XmlElement(required=true)
	void setCodigoSucursal(Integer codigoSucursal);

	@XmlElement(nillable = true)
	void setCodigoPuntoVenta(Integer codigoPuntoVenta);

	@XmlElement(required=true)
	void setCodigoRecepcion(Long codigoRecepcion);
}