package bo.gob.sin.sre.fac.service.validation;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

public class ValRegistroHuellaServiceImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ValRegistroHuellaServiceImpl.class);

	private List<StrMensajeAplicacionDto> mensajes;
	IMensajeAplicacionDomain mensajesDomain;

	public ValRegistroHuellaServiceImpl(IMensajeAplicacionDomain pMensajesService) {
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

	public void validarSolicitudRegistroHuella(RegistrarHuellaSistemaDto pSolicitud) {
		if (pSolicitud == null) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}

		if (pSolicitud.getUsuarioId() == null || pSolicitud.getUsuarioId() <= 0 ||
				pSolicitud.getSistemaId() == null || pSolicitud.getSistemaId() <= 0 ||
				pSolicitud.getTipoComponenteId() == null || pSolicitud.getTipoComponenteId() < 0 ||
				pSolicitud.getMd5() == null || pSolicitud.getMd5().isEmpty() ||
				pSolicitud.getSha2() == null || pSolicitud.getSha2().isEmpty() ||
				pSolicitud.getCrc() == null || pSolicitud.getCrc().isEmpty() ||
				pSolicitud.getNombreArchivo() == null || pSolicitud.getNombreArchivo().isEmpty()
		) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
	}
	
	public void validarSolicitudRegistroHuellaDigital(RegistroHuellasDigitalesDto pSolicitud) {
		if (pSolicitud == null) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}

		if (pSolicitud.getUsuarioId() == null || pSolicitud.getUsuarioId() <= 0 ||
				pSolicitud.getSistemaId() == null || pSolicitud.getSistemaId() <= 0 ||
				pSolicitud.getArchivo() == null ||
				pSolicitud.getTipoComponenteId() == null || pSolicitud.getTipoComponenteId().size() < 0 ||
				pSolicitud.getMd5() == null || pSolicitud.getMd5().isEmpty() ||
				pSolicitud.getSha2() == null || pSolicitud.getSha2().isEmpty() ||
				pSolicitud.getCrc() == null || pSolicitud.getCrc().isEmpty() ||
				pSolicitud.getNombre() == null || pSolicitud.getNombre().isEmpty()
		) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
	}
	
	public void validarSolicitudRegistroHuellaDigital(RespuestaDetalleHuellaDto pSolicitud) 
	{
		if (pSolicitud == null) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}

		if (
				pSolicitud.getDatosEntradaUsuarioRegistro()== null || pSolicitud.getDatosEntradaUsuarioRegistro() <= 0 ||
				pSolicitud.getDatosEntradaSistemaId() == null || pSolicitud.getDatosEntradaSistemaId() <= 0 ||
				pSolicitud.getDatosEntradaSolicitudCertificacion() == null || pSolicitud.getDatosEntradaSolicitudCertificacion() <= 0
		) 
		{
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
	}
}
