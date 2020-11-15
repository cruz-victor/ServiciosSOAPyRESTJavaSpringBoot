package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.domain.IDetalleSolicitudesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.domain.IDocumentosSolicitudesABMDomain;
import bo.gob.sin.sre.fac.domain.IPruebasManualesDomain;
import bo.gob.sin.sre.fac.domain.IPruebasSistemasABMDomain;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasAutomaticasConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISistemasABMDomain;
import bo.gob.sin.sre.fac.domain.ISistemasContribuyentesABMDomain;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionABMDomain;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionConsultaDomain;
import bo.gob.sin.sre.fac.domain.ITiposDocumentosSistemasConsultaDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;
import bo.gob.sin.sre.fac.service.ICambiarEstadoSolicitudCertificacionService;
import bo.gob.sin.sre.fac.service.validation.ValidarSolicitudCertificacionServiceImpl;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class CambiarEstadoSolicitudCertificacionServiceImpl implements ICambiarEstadoSolicitudCertificacionService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(CambiarEstadoSolicitudCertificacionServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private ISistemasABMDomain iSistemasABMDomain;
	
	@Autowired
	private ISolicitudCertificacionABMDomain iSolicitudCertificacionABMDomain;

	@Autowired
	private IDetalleSolicitudesCertificacionesConsultaDomain iDetalleSolicitudesCertificacionesConsultaDomain;
	
	@Autowired
	private ISistemasContribuyentesABMDomain iSistemasContribuyentesABMDomain;
	
	@Autowired
	private IPruebasSistemasABMDomain iPruebasSistemasABMDomain;
	
	@Autowired
	private IDocumentosSolicitudesABMDomain iDocumentosSolicitudesABMDomain;
	
	@Autowired
	private ITiposDocumentosSistemasConsultaDomain iTiposDocumentosSistemasConsultaDomain;
	
	@Autowired
	private IPruebasManualesDomain iPruebasManualesDomain;
	
	@Autowired
	IRegistroPruebasAutomaticasConsultaDomain iRegistroPruebasAutomaticasConsultaDomain;
	
	@Autowired
	ISolicitudCertificacionConsultaDomain iSolicitudCertificacionConsultaDomain;
	
	/**
	 * Registrar Documentos Solicitudes
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/11/2018
	 * @param pSolicitud, solicitud SolicitudSolicitudCertificacionDto
	 * @return Devuelve SreDocumentosSolicitudes.
	 */
	@Override
	public RespuestaActualizacionGenericoDto cancelarCertificacion(SolicitudSolicitudCertificacionDto pSolicitud) {
		int pEstadoSistema = ConstFacturacion.ESTADO_SISTEMA_CANCELADO;
		int pEstadoSolicitudCertificacion = ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_CANCELADO;
		int pEstadoSistemasContribuyentes = ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_CANCELADA;
		return cambioEstadoSolicitud(pSolicitud, pEstadoSistema, pEstadoSolicitudCertificacion, pEstadoSistemasContribuyentes);
	}
	
	@Override
	public RespuestaActualizacionGenericoDto aprobarCertificacion(SolicitudSolicitudCertificacionDto pSolicitud) {
		int pEstadoSistema = ConstFacturacion.ESTADO_SISTEMA_CERTIFICADO;
		int pEstadoSolicitudCertificacion = ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_AUTORIZADO;
		int pEstadoSistemasContribuyentes = ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_APROBADA;
		return cambioEstadoSolicitud(pSolicitud, pEstadoSistema, pEstadoSolicitudCertificacion, pEstadoSistemasContribuyentes);
	}	
	
	@Override
	public RespuestaActualizacionGenericoDto rechazarCertificacion(SolicitudSolicitudCertificacionDto pSolicitud) {
		int pEstadoSistema = ConstFacturacion.ESTADO_SISTEMA_BAJA;
		int pEstadoSolicitudCertificacion = ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_RECHAZADO;
		int pEstadoSistemasContribuyentes = ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_RECHAZADA;
		return cambioEstadoSolicitud(pSolicitud, pEstadoSistema, pEstadoSolicitudCertificacion, pEstadoSistemasContribuyentes);
	}	
	
	
	private RespuestaActualizacionGenericoDto cambioEstadoSolicitud(SolicitudSolicitudCertificacionDto pSolicitud, int pEstadoSistema, int pEstadoSolicitudCertificacion, int pEstadoSistemasContribuyentes) {
		RespuestaActualizacionGenericoDto vRespuesta = new RespuestaActualizacionGenericoDto();
		List<SreDetalleSolicitudesCertificaciones> vSreListaDetalleSolicitudesCertificaciones = new ArrayList<>();
		List<SreTiposDocumentosSistemas> vListaSreTiposDocumentosSistemas = new ArrayList<>();
	
		SreSistemas vSistemasModificada = new SreSistemas();
		SreSolicitudCertificacion vSolicitudCertificacionModificada = new SreSolicitudCertificacion();
		SreSistemasContribuyentes vSistemaContribuyenteModificada = new SreSistemasContribuyentes();
		vRespuesta.setOk(true);
		vRespuesta.setEstaActualizado(true);
		int i = 0;

		ValidarSolicitudCertificacionServiceImpl vValSolCertificacion = new ValidarSolicitudCertificacionServiceImpl(mensajesDomain);
		vValSolCertificacion.validarCambioEstadoSolicitudCertificacion(pSolicitud);
		if (vValSolCertificacion.isValido()) 
		{
			try 
			{
				SreSolicitudCertificacion  vSreSolicitudCertificacion  = iSolicitudCertificacionConsultaDomain.obtenerSolicitudCertificacion(pSolicitud.getSolicitudCertificacionId());
				
				if(vSreSolicitudCertificacion != null && vSreSolicitudCertificacion.getEstadoSolicitudCertificacionId() != pEstadoSolicitudCertificacion)
				{				
					vSolicitudCertificacionModificada = iSolicitudCertificacionABMDomain.cambiarEstadoSolicitudCertificacion(pSolicitud.getSolicitudCertificacionId(),pSolicitud.getUsuarioId(), pEstadoSolicitudCertificacion);							
					
					if (vSolicitudCertificacionModificada != null && vRespuesta.isEstaActualizado()) 
					{
						vSreListaDetalleSolicitudesCertificaciones = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaDetalleSolicitudCertificacionId(vSolicitudCertificacionModificada.getSolicitudCertificacionId());
						
						if(vSreListaDetalleSolicitudesCertificaciones != null && vRespuesta.isEstaActualizado())
						{
							for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreListaDetalleSolicitudesCertificaciones) 
							{
								vSistemasModificada = iSistemasABMDomain.cambiarEstadoSistema(vSreDetalle.getSistemaId(),pSolicitud.getUsuarioId(), pEstadoSistema);
								if (vSistemasModificada != null && vRespuesta.isEstaActualizado()) 
								{
									vSistemaContribuyenteModificada = iSistemasContribuyentesABMDomain.cambiarEstadoSistemaContribuyente(vSreDetalle.getSistemaId(),vSolicitudCertificacionModificada.getPersonaContribuyenteId(),pSolicitud.getUsuarioId(), pEstadoSistemasContribuyentes, vSreDetalle.getModalidadFacturacionId());
									
									if (vSistemaContribuyenteModificada == null && vRespuesta.isEstaActualizado()) 
									{									
										vRespuesta.setOk(true);
										vRespuesta.setEstaActualizado(false);
										vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));
									}
								} 
								else 
								{
									vRespuesta.setOk(true);
									vRespuesta.setEstaActualizado(false);
									vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));
									break;
								}
							}
						}
						
						if(vRespuesta.isEstaActualizado())
						{					
							vListaSreTiposDocumentosSistemas = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaTipoDocumentosSistema(vSolicitudCertificacionModificada.getSolicitudCertificacionId());
							
							if(vListaSreTiposDocumentosSistemas!=null)
							{
								vListaSreTiposDocumentosSistemas.forEach(item->
								{
									SreTiposDocumentosSistemas vSreTiposDocumentosSistemas  = iTiposDocumentosSistemasConsultaDomain.modificarTipoDocumentosSistemas(item.getTipoDocumentoSistemaId(), pSolicitud.getUsuarioId(), ConstFacturacion.ESTADO_TIPO_DOCUMENTO_SISTEMA_INVALIDO);
									
									if(vSreTiposDocumentosSistemas == null && vRespuesta.isEstaActualizado())
									{
										vRespuesta.setOk(true);
										vRespuesta.setEstaActualizado(false);
										vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));
										
									}
								});
							}
						}
						
						if(vRespuesta.isEstaActualizado())
						{
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.MODIFICACION_DATOS_EXITOSO));
						}
					} 
					else 
					{
						vRespuesta.setOk(true);
						vRespuesta.setEstaActualizado(false);
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_RESULTADO_INSPECCIONES_ERROR));
					}
				}
				else
				{
					vRespuesta.setOk(true);
					vRespuesta.setEstaActualizado(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.MODIFICACION_DATOS_ERROR));
				}
			} 
			catch (Exception e) 
			{
				vRespuesta.setOk(false);
				vRespuesta.setEstaActualizado(false);
				LogExcepcion.registrar(e, LOG, MethodSign.build(vSolicitudCertificacionModificada));
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_RESULTADO_INSPECCIONES_ERROR));
			}
		} 
		else 
		{
			vRespuesta.setOk(true);
			vRespuesta.setEstaActualizado(false);
			vRespuesta.setMensajes(vValSolCertificacion.getMensajes());
		}

		return vRespuesta;
	}
	
}
