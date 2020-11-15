/**
 * 
 */
package bo.gob.sin.sre.fac.cfec.service;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;

/**
 * @author reynaldo.chambi
 * @fecha 09/08/2019
 */
public interface IServiciosReversionClientRest {
	public RespuestaDatosCufDto reversionCuf(SolicitudRevertirCadenaDto pSolicitud);
}
