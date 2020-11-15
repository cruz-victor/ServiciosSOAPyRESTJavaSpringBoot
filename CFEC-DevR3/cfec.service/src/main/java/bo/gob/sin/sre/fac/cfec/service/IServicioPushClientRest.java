/**
 * 
 */
package bo.gob.sin.sre.fac.cfec.service;

import bo.gob.sin.str.cmsj.mfcm.dto.FirebaseResponseDto;
import bo.gob.sin.str.cmsj.mfcm.dto.SolicitudPersonaIdDocumentoDto;

/**
 * @author reynaldo.chambi
 * @fecha 09/08/2019
 */
public interface IServicioPushClientRest {
	public FirebaseResponseDto notificacionPush(SolicitudPersonaIdDocumentoDto pSolicitud);
}
