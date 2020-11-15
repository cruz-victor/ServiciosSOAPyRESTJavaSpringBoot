/**
 * 
 */
package bo.gob.sin.sre.fac.cfec.servicedomain;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

/**
 * @author reynaldo.chambi
 * @fecha 12/06/2019
 */
public interface IMasivos {
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarMasivos(XmlRecepcionGenericoDto xmlRecGenDto);
	
}