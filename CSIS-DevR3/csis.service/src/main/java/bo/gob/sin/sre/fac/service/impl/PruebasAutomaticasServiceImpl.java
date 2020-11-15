package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionConsultaDomain;
import bo.gob.sin.sre.fac.dto.RespuestaVerificacionPruebasConcluidasDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.service.IPruebasAutomaticasService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
public class PruebasAutomaticasServiceImpl implements IPruebasAutomaticasService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private ISolicitudCertificacionConsultaDomain iSolicitudCertificacionConsultaDomain;


	@Override
	public RespuestaVerificacionPruebasConcluidasDto verificaPruebasConcluidas(
			SolicitudPruebasSistemasDto pSolicitudPruebasSistemasDto) {

		RespuestaVerificacionPruebasConcluidasDto vRespuestaDto = new RespuestaVerificacionPruebasConcluidasDto();
		vRespuestaDto.setOk(false);
		vRespuestaDto.setPruebasConcluidas(false);

		SreSolicitudCertificacion vSolicitudCertificacionEntidad = iSolicitudCertificacionConsultaDomain
				.obtenerSolicitudCertificacion(pSolicitudPruebasSistemasDto.getSolicitudId());

		vRespuestaDto.setPruebasConcluidas(vSolicitudCertificacionEntidad
					.getEstadoPruebaSolicitudId() == ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_APROBADA);

		if (!vRespuestaDto.isPruebasConcluidas()) {
			vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_RECHAZO));
		}

		vRespuestaDto.setOk(true);
		return vRespuestaDto;
	}

}
