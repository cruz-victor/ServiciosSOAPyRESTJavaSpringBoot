package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.ISolicitudesCertificacionesTramitesConsultaDomain;
import bo.gob.sin.sre.fac.dto.DatosTramiteSolCertificacionDto;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.service.IRecuperarDatosTramiteSolCertificacionService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RecuperarDatosTramiteSolCertificacionServiceImpl implements IRecuperarDatosTramiteSolCertificacionService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RecuperarDatosTramiteSolCertificacionServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	private ISolicitudesCertificacionesTramitesConsultaDomain iSolicitudesCertificacionesTramitesConsultaDomain;

	//IASC - Servicio de recuperacion de datos del tramite de solicitud de certificacion - 25/11/2018
	@Override
	public DatosTramiteSolCertificacionDto obtenerDatosTramiteCertificacion(long pTramiteId) {
		LOG.info("Ingresando obtenerDatosTramiteCertificacion ");
		
		DatosTramiteSolCertificacionDto vRespuesta = new DatosTramiteSolCertificacionDto();
		vRespuesta.setOk(false);
		
		if (pTramiteId > 0) {
			SreSolicitudCertificacion vEntidad = new SreSolicitudCertificacion(); 
			vEntidad = iSolicitudesCertificacionesTramitesConsultaDomain.obtenerDatosTramiteCertificacion(pTramiteId);
			
			if (vEntidad != null) {
				vRespuesta.setPersonaContribuyenteId(vEntidad.getPersonaContribuyenteId());
				vRespuesta.setSistemaId(vEntidad.getSistemaId());
				vRespuesta.setSolicitudCertificacionId(vEntidad.getSolicitudCertificacionId());
				vRespuesta.setEstadoSolicitudId(vEntidad.getEstadoSolicitudCertificacionId());
				vRespuesta.setOk(true);
			}
			else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_DATOS_TRAMITE_VACIO));
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}

		return vRespuesta;
	}
}
