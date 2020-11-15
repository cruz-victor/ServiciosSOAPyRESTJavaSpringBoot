/**
 * 
 */
package bo.gob.sin.sre.fac.cfec.service;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionFirmadoXmlFacturaDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudValidacionFirmadoXmlFacturaDto;

/**
 * @author edwin.coro
 * @fecha 16/05/2019
 */
public interface IServiciosFirmaClientRest {
	public RespuestaValidacionFirmadoXmlFacturaDto verificarFirmadoXml(
			SolicitudValidacionFirmadoXmlFacturaDto pSolicitud);
}
