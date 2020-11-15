package bo.gob.sin.sre.fac.cfec.dto.comp.interfaces;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ObtencionPorDiaComprasAdapter.class)
public interface IObtencionPorDiaCompras {

	Long getNit();
	Date getFecha();
	String getCodigoSistema();

	String getCufd();

	String getCuis();

	Integer getCodigoSucursal();

	Integer getCodigoPuntoVenta();

	Integer getCodigoAmbiente();
	
	@XmlElement(required=true)
	void setNit(Long nit);

	@XmlElement(required=true)
	void setFecha(Date fecha);
	
	@XmlElement(required=true)
	void setCodigoSistema(String codigoSistema);

	@XmlElement(required=true)
	void setCufd(String cufd);

	@XmlElement(required=true)
	void setCuis(String cuis);

	@XmlElement(required=true)
	void setCodigoSucursal(Integer codigoSucursal);
	
	@XmlElement(required=true)
	void setCodigoPuntoVenta(Integer codigoPuntoVenta);

	@XmlElement(required=true)
	void setCodigoAmbiente(Integer codigoAmbiente);
}