package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;

public interface IPruebasCertificacionSistemasService {

	/** 
	 * Realiza la actualización de la tabla Pruebas de Certificación de Sistemas, para el inicio o el fin de las pruebas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 05/06/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RespuestaActualizacionGenericoDto.
	 */
	public RespuestaActualizacionGenericoDto actualizarPruebasCertificacionSistemas(
			SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud);
	
	/** 
	 * Realiza el reinicio de la tabla Pruebas de Certificación de Sistemas, para comenzar nuevamente con las pruebas 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 05/06/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RespuestaActualizacionGenericoDto.
	 */
	public RespuestaActualizacionGenericoDto reiniciarPruebasCertificacionSistemas(
			SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud);
	
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificación de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Información necesaria para registrar la etapa de prueba (detalle)
	* @return Devuelve un valor booleano que indica la transacción
	*/
	public RespuestaActualizacionGenericoDto registroPruebaDetalleOpcional(
			SeguimientoDetalleCertificacionSistemasDto pSolicitud);
}
