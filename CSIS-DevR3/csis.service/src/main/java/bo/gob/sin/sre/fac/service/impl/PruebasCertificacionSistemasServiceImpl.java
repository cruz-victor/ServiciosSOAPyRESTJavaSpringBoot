package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.domain.IPruebasCertificacionSistemasDomain;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.model.SrePruebasCertificacionSistemas;
import bo.gob.sin.sre.fac.service.IPruebasCertificacionSistemasService;
import bo.gob.sin.str.ccs.cale.domain.ICalendarioDomain;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class PruebasCertificacionSistemasServiceImpl implements  IPruebasCertificacionSistemasService, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private IPruebasCertificacionSistemasDomain iPruebasCertificacionSistemasDomain;

	@Autowired
	private ICalendarioDomain iCalendarioDomain;
	
	/** 
	 * Realiza la actualización de la tabla Pruebas de Certificación de Sistemas, para el inicio o el fin de las pruebas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 05/06/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RespuestaActualizacionGenericoDto.
	 */
	@Override
	public RespuestaActualizacionGenericoDto actualizarPruebasCertificacionSistemas(
			SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud) {	

		Date vFecha = iCalendarioDomain.getTimeStamp();
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto.setOk(false);

		try {

			SrePruebasCertificacionSistemas vRespuesta = iPruebasCertificacionSistemasDomain.actualizarPruebasCertificacionSistemas(pSolicitud,vFecha);
						
			if (vRespuesta != null && vRespuesta.getPruebaCertificacionSistemaId()>0) {
				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.ACEPTACION_SOLICITUD_CERTIFICACION_ACTIVACION));
				vRespuestaDto.setOk(true);
			} else {

				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_ERROR));				
			}

			return vRespuestaDto;

		} catch (Exception e) {
			e.printStackTrace();
			vRespuestaDto.setOk(false);
			vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_ERROR));
		}
		return vRespuestaDto;
	}
	
	/** 
	 * Realiza el reinicio de la tabla Pruebas de Certificación de Sistemas, para comenzar nuevamente con las pruebas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 05/06/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RespuestaActualizacionGenericoDto.
	 */
	@Override
	public RespuestaActualizacionGenericoDto reiniciarPruebasCertificacionSistemas(
			SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud) {	

		Date vFecha = iCalendarioDomain.getTimeStamp();
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto.setOk(false);

		try {

			SrePruebasCertificacionSistemas vRespuesta = iPruebasCertificacionSistemasDomain.reiniciarPruebasCertificacionSistemas(pSolicitud.getPruebaCertificacionSistemaId(),vFecha);
						
			if (vRespuesta != null && vRespuesta.getPruebaCertificacionSistemaId()>0) {
				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.ACEPTACION_SOLICITUD_CERTIFICACION_ACTIVACION));
				vRespuestaDto.setOk(true);
			} else {

				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_ERROR));				
			}

			return vRespuestaDto;

		} catch (Exception e) {
			e.printStackTrace();
			vRespuestaDto.setOk(false);
			vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_ERROR));
		}
		return vRespuestaDto;
	}
	
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificación de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Información necesaria para registrar la etapa de prueba (detalle)
	* @return Devuelve un valor booleano que indica la transacción
	*/
	@Override
	public RespuestaActualizacionGenericoDto registroPruebaDetalleOpcional(
			SeguimientoDetalleCertificacionSistemasDto pSolicitud) {	

		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		vRespuestaDto.setOk(false);

		try {

			boolean vRespuesta = iPruebasCertificacionSistemasDomain.registroPruebaDetalleOpcional(pSolicitud);
						
			if (vRespuesta) {
				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.REGISTRO_RESULTADO_INSPECCIONES_EXITOSO));
				vRespuestaDto.setOk(true);
				vRespuestaDto.setEstaActualizado(vRespuesta);
			} else {

				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.REGISTRO_RESULTADO_INSPECCIONES_ERROR));				
			}

			return vRespuestaDto;

		} catch (Exception e) {
			e.printStackTrace();
			vRespuestaDto.setOk(false);
			vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.REGISTRO_RESULTADO_INSPECCIONES_ERROR));
		}
		return vRespuestaDto;
	}
}
