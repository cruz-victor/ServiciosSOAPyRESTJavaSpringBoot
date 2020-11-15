package bo.gob.sin.sre.fac.cfec.dao;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;

/**
 * @author edwin.coro
 *
 */
public interface IOperacionCufDao {

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 22/07/2019
	 */
	RespuestaFacturaVentasDto obtenerFacturaVentas(SolicitudObtencionFacturaVentasDto pSolicitud, RespuestaDatosCufDto vRespuestaCuf);
}