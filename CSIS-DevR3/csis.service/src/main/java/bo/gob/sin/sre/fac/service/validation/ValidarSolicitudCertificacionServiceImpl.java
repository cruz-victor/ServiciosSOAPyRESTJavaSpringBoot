package bo.gob.sin.sre.fac.service.validation;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

public class ValidarSolicitudCertificacionServiceImpl implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StrMensajeAplicacionDto> mensajes;
	IMensajeAplicacionDomain mensajesDomain;

	public ValidarSolicitudCertificacionServiceImpl(IMensajeAplicacionDomain pMensajesService) {
		mensajes = new ArrayList<>();
		this.mensajesDomain = pMensajesService;
	}

	public boolean isValido() {
		if (mensajes.isEmpty())
			return true;
		return false;
	}

	public List<StrMensajeAplicacionDto> getMensajes() {
		return mensajes;
	}
	
	/**
    * Objetivo: registrar solicitud de sistemas.
    * Creado por: Reynaldo Chambi.
    * Fecha: 13/04/2018
    * Modificado por: Gualberto Condori
    * Fecha de Modificacion: 18/06/2018
    * IASC - Refactorizacion - 21/11/2018
    */
	public void validarSolicitudCertificacion(Long pContribuyenteId, List<Integer> pTiposModulos, 
			List<SolicitudContactosCertificacionesDto> pListaContactos ) {
		
		if (pContribuyenteId <= 0) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_SISTEMAS));
		}
		
		if (pListaContactos.size() <= 0) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_CONTACTOS));
		}
	}
	
	/**
	    * Objetivo: registrar solicitud de sistemas.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	    * IASC - Refactorizacion - 21/11/2018
	    */
		public void validarSolicitudReCertificacion(Long pContribuyenteId, List<SolicitudContactosCertificacionesDto> pListaContactos ) 
		{
			if (pContribuyenteId <= 0) {
				mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_SISTEMAS));
			}
			
			if (pListaContactos.size() <= 0) {
				mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_CONTACTOS));
			}
		}
	
	/**
	 * Validar Cancelacion Solicitud Certificacion
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/11/2018
	 * @param pSolicitud, parametro de solicitud
	 * @return Devuelve mensajes
	 */
	public void validarCambioEstadoSolicitudCertificacion(SolicitudSolicitudCertificacionDto pSolicitud) {

		if (pSolicitud == null) 
		{
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
		else
		{
			if (pSolicitud.getSolicitudCertificacionId() <= 0 || pSolicitud.getUsuarioId() <= 0 ) 
			{
				mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		}
	}
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 15/06/2018
	    * IASC - Refactorizacion - 21/11/2018
	 */
	public void validarSistemaRegistrado(String pNombreSistema, List<Integer> pModalidad, Long pContribuyenteId) {
		if (pNombreSistema == null || pModalidad.size() <= 0 || pContribuyenteId == null) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_EXISTENCIA_SISTEMAS));
		}
		if (pNombreSistema.isEmpty() || pModalidad.isEmpty() || pContribuyenteId <= 0) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_EXISTENCIA_SISTEMAS));
		}
	}
}
