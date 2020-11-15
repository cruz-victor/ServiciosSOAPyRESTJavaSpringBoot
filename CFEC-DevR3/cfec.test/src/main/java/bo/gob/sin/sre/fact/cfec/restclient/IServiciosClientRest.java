/**
 * 
 */
package bo.gob.sin.sre.fact.cfec.restclient;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

/**
 * @author edwin.coro
 * @fecha 04/06/2019
 * @descripcion Clase que despliega los servicios rest a los que se puede
 *              invocar desde pruebas unitarias
 */
public interface IServiciosClientRest {
	public RespuestaJsonDto recepcionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud);

	public RespuestaJsonDto validacionRecepcionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud);

	public RespuestaJsonDto anulacionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud);

	public RespuestaJsonDto validacionAnulacionFacturaPortalWeb(XmlRecepcionGenericoDto pSolicitud);
}
