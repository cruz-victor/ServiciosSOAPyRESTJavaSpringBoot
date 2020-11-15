/**
 * 
 */
package bo.gob.sin.sre.fac.cfec;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

/**
 * @author edwin.coro
 * @fecha 12/06/2019
 */
public interface IPortalWeb {
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarValidarPortalWeb(XmlRecepcionGenericoDto xmlRecGenDto);
}