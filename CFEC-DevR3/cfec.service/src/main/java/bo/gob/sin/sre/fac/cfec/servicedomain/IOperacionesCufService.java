package bo.gob.sin.sre.fac.cfec.servicedomain;

import bo.gob.sin.sre.fac.cfec.dto.GenerarCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.sre.fac.cfec.dto.ValidarCufDto;

public interface IOperacionesCufService {
	
	/**
	 * @author Reynaldo Chambi
	 * @fecha 09/08/2019
	 */
	public  String generadorCUF(GenerarCufDto dtoCuf);
	/**
	 * @author Reynaldo Chambi
	 * @fecha 09/08/2019
	 */
	public boolean verificacionCuf(ValidarCufDto dtoCuf);
	/**
	 * @author Reynaldo Chambi
	 * @fecha 09/08/2019
	 */
	public RespuestaFacturaVentasDto obtenerFacturaVentas(SolicitudObtencionFacturaVentasDto pSolicitud);
	/**
	 * @author Reynaldo Chambi
	 * @fecha 20/08/2019
	 */
	public RespuestaDatosCufDto revertirCuf(SolicitudRevertirCadenaDto pSolicitud);
}
