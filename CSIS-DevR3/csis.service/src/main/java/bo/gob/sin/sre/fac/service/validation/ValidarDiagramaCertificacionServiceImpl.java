package bo.gob.sin.sre.fac.service.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

public class ValidarDiagramaCertificacionServiceImpl implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StrMensajeAplicacionDto> mensajes;
	IMensajeAplicacionDomain mensajesDomain;

	public ValidarDiagramaCertificacionServiceImpl(IMensajeAplicacionDomain pMensajesService) {
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

	public void validarDiagramaCertificacion(ListaDiagramasCertificacionesDto pSolicitud) {
		if (pSolicitud == null) {
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}

		if (pSolicitud.getDatosEntradaEtapaId() == null || pSolicitud.getDatosEntradaEtapaId() <= 0 ||
			pSolicitud.getDatosEntradaSistemaId() == null || pSolicitud.getDatosEntradaSistemaId() <= 0 ||
			pSolicitud.getDatosEntradaSolicitudCertificacionId() == null || pSolicitud.getDatosEntradaSolicitudCertificacionId() < 0) 
		{
			mensajes.add(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
	}
}
