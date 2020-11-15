package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;
import bo.gob.sin.sre.fac.domain.IDetalleSolicitudesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.domain.IRutasServiciosConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISistemasConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionConsultaDomain;
import bo.gob.sin.sre.fac.domain.ITiposDocumentosSistemasConsultaDomain;
import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.DatosReporteSolCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.DatosReporteSolCertificacionSistemasRutasDto;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreRutasServicios;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;
import bo.gob.sin.sre.fac.service.IEmpadronamientoService;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRecuperarDatosSistemasService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RecuperarDatosSistemasServiceImpl implements IRecuperarDatosSistemasService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RecuperarDatosSistemasServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	private ISistemasConsultaDomain iSistemasConsultaDomain;
	
	@Autowired
	private ISolicitudCertificacionConsultaDomain iSolicitudCertificacionConsultaDomain;
	
	@Autowired
	private IDetalleSolicitudesCertificacionesConsultaDomain iDetalleSolicitudesCertificacionesConsultaDomain;
	
	@Autowired
	private IParametricasDomain iClasificadorDomain;
	
	@Autowired
    private IEmpadronamientoService iEmpadronamientoInterfacesService;
	
	@Autowired
	private IRutasServiciosConsultaDomain iRutasServiciosConsultaDomain;
	
	@Autowired
	private ITiposDocumentosSistemasConsultaDomain iTiposDocumentosSistemasConsultaDomain;

	//IASC - Servicio de recuperacion de datos del sistema - 26/11/2018
	@Override
	public RespuestaDatosSistemasSolCertificacionDto obtenerDatosSistemaCertificacion(long pSistemaId, long pSolicitudCertificacionId) {
		LOG.info("Ingresando obtenerDatosSistemaCertificacion.");
		
		RespuestaDatosSistemasSolCertificacionDto vRespuesta = new RespuestaDatosSistemasSolCertificacionDto();
		ClasificadorDto vClasificador= new ClasificadorDto();
		String vModalidad = "";
		int vCont = 0;
		vRespuesta.setOk(false);
		
		if (pSistemaId > 0 && pSolicitudCertificacionId > 0) {
			SreSistemas vEntidad = new SreSistemas(); 
			vEntidad = iSistemasConsultaDomain.recuperarDatosSistemas(pSistemaId);
			if (vEntidad != null) {
				vRespuesta.setCodigoSistema(vEntidad.getCodigoSistema());
				vRespuesta.setNombreSistema(vEntidad.getNombreSistema());
				vRespuesta.setVersion(vEntidad.getVersion());
				
				SreSolicitudCertificacion vDatosSolicitud = new SreSolicitudCertificacion();
				vDatosSolicitud = iSolicitudCertificacionConsultaDomain.obtenerSolicitudCertificacion(pSolicitudCertificacionId);
				if (vDatosSolicitud != null) {
					vRespuesta.setFechaSolicitud(vDatosSolicitud.getFechaSolicitud());
					vRespuesta.setFechaAprobacion(vDatosSolicitud.getFechaAprobacion());
					
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vEntidad.getTipoSistemaId());
					vRespuesta.setTipoSistema(vClasificador.getDescripcion());
					
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vDatosSolicitud.getEstadoSolicitudCertificacionId());
					vRespuesta.setEstadoSolicitud(vClasificador.getDescripcion());
					
					ContribuyenteDto vDatosBasicos = iEmpadronamientoInterfacesService.obtenerDatosBasicosXIFC(vDatosSolicitud.getPersonaContribuyenteId());
	                vRespuesta.setNit(vDatosBasicos.getNit());
	                vRespuesta.setRazonSocial(vDatosBasicos.getNombreRazonSocial());
					
					List<SreDetalleSolicitudesCertificaciones> vSreListaDetalleSolicitudesCertificaciones = new ArrayList<SreDetalleSolicitudesCertificaciones>();
					vSreListaDetalleSolicitudesCertificaciones = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaDetalleSolicitudCertificacionId(pSolicitudCertificacionId);
					for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreListaDetalleSolicitudesCertificaciones) {
						vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());
						
						if(vCont == 0) {
							vModalidad = vClasificador.getDescripcion();
							vCont = vCont + 1;
						}
						else {
							vModalidad = vModalidad + "/" + vClasificador.getDescripcion();	
						}	
					}
					vRespuesta.setModalidad(vModalidad);
					vRespuesta.setSistemaId(pSistemaId);
					vRespuesta.setSolicitudCertificacionId(pSolicitudCertificacionId);
					vRespuesta.setOk(true);
				}
				else {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_DATOS_NULOS));
				}
			}
			else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.DATOS_SISTEMAS_VACIO));
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}

		return vRespuesta;
	}
	
	@Override
	public ReporteDatosSolicitudCertificacionDto obtenerDatosReporteSolCertificacion(long pSistemaId, long pSolicitudCertificacionId) {
		LOG.info("Ingresando obtenerDatosReporteSolCertificacion.");
		
		ReporteDatosSolicitudCertificacionDto vRespuesta = new ReporteDatosSolicitudCertificacionDto();
		ClasificadorDto vClasificador = new ClasificadorDto();
		ClasificadorDto vClasificadorDocSector = new ClasificadorDto();

		vRespuesta.setOk(false);
		
		if (pSistemaId > 0 && pSolicitudCertificacionId > 0) {
			RespuestaDatosSistemasSolCertificacionDto vDatosSistema = this.obtenerDatosSistemaCertificacion(pSistemaId, pSolicitudCertificacionId);
			//Datos del sistema
			vRespuesta.setSistema(vDatosSistema);
			List<DatosReporteSolCertificacionSistemasDto> vListaDto = new ArrayList<DatosReporteSolCertificacionSistemasDto>();
			
			List<SreDetalleSolicitudesCertificaciones> vDetalleSolicitud = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaDetalleSolicitudCertificacionId(pSolicitudCertificacionId);
			for (SreDetalleSolicitudesCertificaciones vSreDetalle : vDetalleSolicitud) {
				DatosReporteSolCertificacionSistemasDto vDatos = new DatosReporteSolCertificacionSistemasDto();
				vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());
				vDatos.setModalidad(vClasificador.getDescripcion());
				vDatos.setModalidadId(vSreDetalle.getModalidadFacturacionId());
				vDatos.setCuis(vSreDetalle.getCuis());
				
				List<DatosReporteSolCertificacionSistemasRutasDto> vListaRutasDto = new ArrayList<DatosReporteSolCertificacionSistemasRutasDto>();
				List<SreTiposDocumentosSistemas> vTiposDoc = iTiposDocumentosSistemasConsultaDomain.obtieneDocumentosSectores(pSistemaId, pSolicitudCertificacionId);
				for (SreTiposDocumentosSistemas vLista : vTiposDoc) {
					SreRutasServicios vRutas = iRutasServiciosConsultaDomain.obtieneRutasServicios(vSreDetalle.getModalidadFacturacionId(), vLista.getTipoDocumentoFacturaId());
					if (vRutas != null) {
						DatosReporteSolCertificacionSistemasRutasDto vDtoRutas = new DatosReporteSolCertificacionSistemasRutasDto();
						vClasificadorDocSector = iClasificadorDomain.obtenerDatosParametrica(vLista.getTipoDocumentoFacturaId());
					
						vDtoRutas.setRuta(vRutas.getRutaServicio());
						vDtoRutas.setTipoDocumento(vClasificadorDocSector.getDescripcion());
						vDtoRutas.setModalidadId(vSreDetalle.getModalidadFacturacionId());
						vListaRutasDto.add(vDtoRutas);
					}
				}
//				vDatos.setListaRutas(vListaRutasDto);
//				vListaDto.add(vDatos);
				
				//Obtiene rutas servicios comunes
				vClasificadorDocSector = iClasificadorDomain.obtenerDatosParametrica(ConstFacturacion.RUTA_SERVICIOS_COMUNES);
				List<SreRutasServicios> vRutas = iRutasServiciosConsultaDomain.obtieneRutasServiciosComunes(vSreDetalle.getModalidadFacturacionId(), ConstFacturacion.RUTA_SERVICIOS_COMUNES);
				for (SreRutasServicios vLista : vRutas) {
					DatosReporteSolCertificacionSistemasRutasDto vDtoRutas = new DatosReporteSolCertificacionSistemasRutasDto();
					vDtoRutas.setRuta(vLista.getRutaServicio());
					vDtoRutas.setTipoDocumento(vClasificadorDocSector.getDescripcion());
					vDtoRutas.setModalidadId(vSreDetalle.getModalidadFacturacionId());
					vListaRutasDto.add(vDtoRutas);
				}
				vDatos.setListaRutas(vListaRutasDto);
				vListaDto.add(vDatos);
				
			}
			//Datos de las rutas
			vRespuesta.setDocumentos(vListaDto);
			vRespuesta.setOk(true);
			
		} else {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
		return vRespuesta;
	}
	
	@Override
	public DatosRecertificacion obtenerDatosReSolCertificacion(DatosRecertificacion pDatosRecertificacion) 
	{
		LOG.info("Ingresando obtenerDatosReporteSolCertificacion.");
		
		pDatosRecertificacion.setOk(false);
		
		try
		{
		
			if (pDatosRecertificacion.getEntradaSistemaId() > 0 && pDatosRecertificacion.getEntradaSolicitudCertificacionId() > 0) 
			{
				List<SreDetalleSolicitudesCertificaciones> vDetalleSolicitud = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaDetalleSolicitudCertificacionId(pDatosRecertificacion.getEntradaSolicitudCertificacionId());
				
				for (SreDetalleSolicitudesCertificaciones vSreDetalle : vDetalleSolicitud) 
				{
					ClasificadorDto vClasificadorModalidad = new ClasificadorDto();
					vClasificadorModalidad = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());
					pDatosRecertificacion.getSalidaClasificadorModalidades().add(vClasificadorModalidad);
				}
				
				List<SreTiposDocumentosSistemas> vTiposDoc = iTiposDocumentosSistemasConsultaDomain.obtieneDocumentosSectores(pDatosRecertificacion.getEntradaSistemaId(), pDatosRecertificacion.getEntradaSolicitudCertificacionId());
				
				for (SreTiposDocumentosSistemas vLista : vTiposDoc) 
				{
					ClasificadorDto vClasificadorDocSector = new ClasificadorDto();
					vClasificadorDocSector = iClasificadorDomain.obtenerDatosParametrica(vLista.getTipoDocumentoFacturaId());
					pDatosRecertificacion.getSalidaClasificadorTipoDocSistemas().add(vClasificadorDocSector);
				}
				
				pDatosRecertificacion.setOk(true);
				
			} 
			else 
			{
				pDatosRecertificacion.setOk(false);
				pDatosRecertificacion.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		}
		catch (Exception e) 
		{
			pDatosRecertificacion.setOk(false);
			pDatosRecertificacion.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));			
		}
		return pDatosRecertificacion;
	}
	
	/**
	 * servicio que retorna los datos del sistema a traves del sistema_id
	 * @author rosario.garcia
	 * @fecha 27/11/2018
	 * @modificado 29/11/2018
	 */
	@Override
	public SistemasDto obtenerDatosSistemaPorSistemaId(Long pSistemaId) {
		LOG.info("Ingresando obtenerDatosSistemaId."+pSistemaId);
		
		SistemasDto vRespuesta = new SistemasDto();
		ClasificadorDto vClasificador= new ClasificadorDto();
		String vModalidad = "";
		String vTipoSistema = "";
		int vCont = 0;
		vRespuesta.setOk(false);
		
		if (pSistemaId != null && pSistemaId > 0) {
			List<SreDetalleSolicitudesCertificaciones> vSreDetalleSolicitudesCertificaciones= new ArrayList<SreDetalleSolicitudesCertificaciones>();
			vSreDetalleSolicitudesCertificaciones = iDetalleSolicitudesCertificacionesConsultaDomain.obtenerSolicitudesCertificacionIdSistema(pSistemaId);
			if (!vSreDetalleSolicitudesCertificaciones.isEmpty()) {
				SreSistemas vEntidad = new SreSistemas(); 
				vEntidad = iSistemasConsultaDomain.recuperarDatosSistemas(pSistemaId);				
				vRespuesta.setNombreSistema(vEntidad.getNombreSistema());
				vRespuesta.setVersion(vEntidad.getVersion());
				vRespuesta.setSistemaId(vEntidad.getSistemaId());
				vRespuesta.setEstadoId(vEntidad.getEstadoId());
				for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreDetalleSolicitudesCertificaciones) {
					vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());
					
					if(vCont == 0) {
						vModalidad = vClasificador.getDescripcion();
						vCont = vCont + 1;
					}
					else {
						vModalidad = vModalidad + "/" + vClasificador.getDescripcion();	
					}	
				}
				vClasificador = iClasificadorDomain.obtenerDatosParametrica(vEntidad.getTipoSistemaId());
				vTipoSistema = vClasificador.getDescripcion() + "";
				vRespuesta.setDescripcionTipoSistema(vTipoSistema);
				vRespuesta.setTipoSistemaId(vEntidad.getTipoSistemaId());
				vRespuesta.setDescripcionModalidadFacturacionId(vModalidad);
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
			}
			else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		} else {
			LOG.info("No se pudo recuperar los datos, solicitud incorrecta.");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
		return vRespuesta;
	}
	
}
