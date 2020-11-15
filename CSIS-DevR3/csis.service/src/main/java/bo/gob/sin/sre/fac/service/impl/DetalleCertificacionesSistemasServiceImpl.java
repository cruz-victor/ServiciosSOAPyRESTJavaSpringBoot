package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IDetalleCertificacionesSistemasDomain;
import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.DetallesCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetalleCertificacionesSistemasDto;
import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;
import bo.gob.sin.sre.fac.service.IDetalleCertificacionesSistemasService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional(rollbackFor = Exception.class)
public class DetalleCertificacionesSistemasServiceImpl implements IDetalleCertificacionesSistemasService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(DetalleCertificacionesSistemasServiceImpl.class);
	
	@Autowired
	private IDetalleCertificacionesSistemasDomain iDetalleCertificacionesSistemasDomain;
	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public  DetallesCertificacionesSistemasDto registraDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitudSistema) {
		DetallesCertificacionesSistemasDto vRespuesta=new DetallesCertificacionesSistemasDto(); 
		SreDetalleCertificacionesSistemas vCertificaciones=null;
		vRespuesta.setOk(false);

		try {
			ModelMapper vMapper = new ModelMapper();
			vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			vCertificaciones = vMapper.map(pSolicitudSistema, SreDetalleCertificacionesSistemas.class);
			Date vFechaHoy = new Date();
			vCertificaciones.setFechaRegistro(vFechaHoy);
			vCertificaciones.setFechaUltimaModificacion(vFechaHoy);
			vCertificaciones.setEstadoId("AC");
			vCertificaciones = iDetalleCertificacionesSistemasDomain.registraDetalleCertificacionesSistemas(vCertificaciones);
						
			if (vCertificaciones!=null && vCertificaciones.getDetalleCertificacionSistemaId()>0L) {
				vRespuesta=vMapper.map(vCertificaciones, DetallesCertificacionesSistemasDto.class);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.CONFIRMACION_EXITOSA));
				vRespuesta.setOk(true);				
			} else {

				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));				
			}

			return vRespuesta;

		} catch (Exception e) {
			e.printStackTrace();
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));
		}
		return vRespuesta;
	}
	
	@Override
	public RespuestaListaDetalleCertificacionesSistemasDto obtenerListaDetalleCertificacionSistemas(long pSolicitudCertificacionId, long pSistemaId){
		RespuestaListaDetalleCertificacionesSistemasDto vRespuesta = new RespuestaListaDetalleCertificacionesSistemasDto();
		
		vRespuesta.setOk(false);
		try {
			List<SreDetalleCertificacionesSistemas> listaDetalles = iDetalleCertificacionesSistemasDomain.obtenerListaDetalleCertificacionSistemas(pSolicitudCertificacionId, pSistemaId);
			
			if(listaDetalles != null) 
			{				
				Type listType = new TypeToken<List<DetallesCertificacionesSistemasDto>>() {}.getType();
				ModelMapper vMapper = new ModelMapper();
				vMapper.getConfiguration().setAmbiguityIgnored(true);
				vRespuesta.setListaCertificacionesSistemasDto(vMapper.map(listaDetalles, listType));
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.CONFIRMACION_EXITOSA));
				vRespuesta.setOk(true);
			} else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));
			}			
		}catch (Exception e) {
			vRespuesta.setOk(false);
			LOG.info("Error en obtencion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));
		}
		return vRespuesta;
	}
	
	@Override
	public  DetallesCertificacionesSistemasDto anularDetalleCertificacionesSistemas(DetallesCertificacionesSistemasDto pSolicitud) {
		DetallesCertificacionesSistemasDto vRespuesta=new DetallesCertificacionesSistemasDto(); 
		
		vRespuesta.setOk(false);

		try {
			
			SreDetalleCertificacionesSistemas vCertificaciones = iDetalleCertificacionesSistemasDomain.obtenerDetalleCertificacionSistemas(pSolicitud.getDetalleCertificacionSistemaId());	
			
			if(vCertificaciones != null && vCertificaciones.getDetalleCertificacionSistemaId() > 0) {
				Date vFechaHoy = new Date();
				vCertificaciones.setFechaUltimaModificacion(vFechaHoy);
				vCertificaciones.setEstadoId("AN");
				vCertificaciones = iDetalleCertificacionesSistemasDomain.registraDetalleCertificacionesSistemas(vCertificaciones);	
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.CONFIRMACION_EXITOSA));
				vRespuesta.setOk(true);
			} else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));	
			}

			return vRespuesta;

		} catch (Exception e) {
			e.printStackTrace();
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));
		}
		return vRespuesta;
	}
	
}
