package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken; 

import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;
import bo.gob.sin.sre.fac.domain.IDetalleSolicitudesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISistemasConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionConsultaDomain;
import bo.gob.sin.sre.fac.dto.ObtenerListSistemaDto;
import bo.gob.sin.sre.fac.dto.ObtenerListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RecuperarListaSolicitudCertificacion;
import bo.gob.sin.sre.fac.dto.RespuestaListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.service.IEmpadronamientoService;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRecuperarListaSolicitudesCertificacionEnProcesoService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RecuperarListaSolicitudesCertificacionEnProcesoServiceImpl
		implements IRecuperarListaSolicitudesCertificacionEnProcesoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(RecuperarListaSolicitudesCertificacionEnProcesoServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private IParametricasDomain iClasificadorDomain;

	@Autowired
	private ISistemasConsultaDomain iSistemasConsultaDomain;

	@Autowired
	private IDetalleSolicitudesCertificacionesConsultaDomain iDetalleSolicitudesCertificacionesConsultaDomain;

	@Autowired
	private ISolicitudCertificacionConsultaDomain iSolicitudCertificacionConsultaDomain;
	
	@Autowired
    private IEmpadronamientoService iEmpadronamientoInterfacesService;
	
	

	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO, para
	 * Cancelacion de Solicitud de Certificacion
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pSolicitud,
	 *            objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo
	 *         RespuestaSistemasDeCertificacionFuncionarioDto.
	 */
	@Override
	public RecuperarListaSolicitudCertificacion recuperaSolicitudesCertificacionParaCancelar(
			SolicitudSolicitudCertificacionDto pSolicitud) {
		LOG.info("Ingresando recuperarListaPruebasSistemasDeCertificacionFuncionario ");
		//Integer vEstadoCertificacion = ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO;
		Integer vSinValor = 0;
		List<SreDetalleSolicitudesCertificaciones> vSreListaDetalleSolicitudesCertificaciones = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		List<SreSolicitudCertificacion> vSreListaSolCertificacionEntidad = new ArrayList<>();
		RecuperarListaSolicitudCertificacion vRespuesta = new RecuperarListaSolicitudCertificacion();
		List<SolicitudCertificacionDto> vListaDto = new ArrayList<>();
		vRespuesta.setOk(false);
		
		try  
		{
			if (pSolicitud.getPersonaContribuyenteId() > 0) 
			{
				vSreListaSolCertificacionEntidad = iSolicitudCertificacionConsultaDomain.recuperaSolicitudesCertificacionParaCancelar(pSolicitud.getPersonaContribuyenteId(), vSinValor);

				if (!vSreListaSolCertificacionEntidad.isEmpty()) 
				{					
					
					
					//TypeReference<List<SolicitudCertificacionDto>> typeRef = new TypeReference<List<SolicitudCertificacionDto>>() {
					//};
					
					Type listType = new TypeToken<List<SolicitudCertificacionDto>>() {
					}.getType();
					
					ModelMapper vMapper = new ModelMapper();
					vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
					vListaDto = vMapper.map(vSreListaSolCertificacionEntidad, listType);
					
					vListaDto.forEach(item->
					{
						if(item.getEstadoSolicitudCertificacionId() == ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_AUTORIZADO)
						{
							item.setRecertificable(true);
						}
						
						if(item.getEstadoSolicitudCertificacionId() == ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO)
						{
							item.setCancelable(true);
						}
					});
					
					
					vRespuesta.setLista(vListaDto);
					vRespuesta.setOk(true);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));

				} 
				else 
				{
					vRespuesta.setOk(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
				}
			} 
			else 
			{
				LOG.info("No se pudo recuperar los datos incorrectos de envio.");
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
		}

		LOG.info("Saliendo recuperarListaPruebasSistemasDeCertificacionFuncionario vRespuesta={}", vRespuesta);
		return vRespuesta;
	}

	/**
	 * Recuperar Lista de sistemas que estan en proceso
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/09/2018
	 * @modificadoPor: rosario.garcia
	 * @FechaModificacion: 23/11/2018
	 * @param pSolicitud,
	 *            objeto de tipo solicitud
	 * @return lista de sistemas asociados al contribuyente con estado de solicitud
	 *         en proceso
	 * @FechaModificacion: 29/11/2018 se agrego la descripcion del tipo de sistema
	 */
	@Override
	public ObtenerListSistemaDto obtenerSistemasEstadoEnProceso(ObtenerListSistemaDto pSolicitud) {
		LOG.info("Ingresando recuperarListaPruebasSistemasDeCertificacionFuncionario ");
		int vEstadoCertificacion = ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO;
		String vDescripcionModalidad = "";
		String vDescripcionTipo = "";

		ClasificadorDto vClasificador = new ClasificadorDto();
		List<SreDetalleSolicitudesCertificaciones> vSreListaDetalleSolicitudesCertificaciones = new ArrayList<SreDetalleSolicitudesCertificaciones>();
		List<SreSolicitudCertificacion> vSreListaSolCertificacionEntidad = new ArrayList<SreSolicitudCertificacion>();
		ObtenerListSistemaDto vRespuesta = new ObtenerListSistemaDto();
		List<SistemasDto> vListaDto = new ArrayList<SistemasDto>();
		vRespuesta.setOk(false);
		try {
			if (pSolicitud.getPersonaContribuyenteId() > 0) {
				vSreListaSolCertificacionEntidad = iSolicitudCertificacionConsultaDomain
						.recuperaSolicitudesCertificacionParaCancelar(pSolicitud.getPersonaContribuyenteId(),
								vEstadoCertificacion);

				if (!vSreListaSolCertificacionEntidad.isEmpty()) {
					for (SreSolicitudCertificacion vSreSolCertificacion : vSreListaSolCertificacionEntidad) {
						SistemasDto dto = new SistemasDto();
						ModelMapper vMapper = new ModelMapper();
						vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
						dto = vMapper.map(vSreSolCertificacion, SistemasDto.class);

						vClasificador = iClasificadorDomain
								.obtenerDatosParametrica(vSreSolCertificacion.getEstadoSolicitudCertificacionId());
						vSreListaDetalleSolicitudesCertificaciones = iDetalleSolicitudesCertificacionesConsultaDomain
								.obtenerListaDetalleSolicitudCertificacionId(
										vSreSolCertificacion.getSolicitudCertificacionId());

						for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreListaDetalleSolicitudesCertificaciones) {
							SreSistemas recuperarDatosSistemas = iSistemasConsultaDomain
									.recuperarDatosSistemas(vSreDetalle.getSistemaId());
							dto.setVersion(recuperarDatosSistemas.getVersion());
							dto.setNombreSistema(recuperarDatosSistemas.getNombreSistema());
							dto.setSistemaId(vSreDetalle.getSistemaId());
							dto.setModalidadFacturacionId(vSreDetalle.getModalidadFacturacionId());
							vClasificador = iClasificadorDomain
									.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());
							if (vSreListaDetalleSolicitudesCertificaciones.size() > 1) {
								vDescripcionModalidad += vClasificador.getDescripcion() + "/";
							}
							dto.setDescripcionModalidadFacturacionId(vDescripcionModalidad);
							vClasificador = iClasificadorDomain
									.obtenerDatosParametrica(recuperarDatosSistemas.getTipoSistemaId());
							vDescripcionTipo = vClasificador.getDescripcion();
							dto.setDescripcionTipoSistema(vDescripcionTipo);
							dto.setTipoSistemaId(recuperarDatosSistemas.getTipoSistemaId());
							dto.setEstadoSistemaId(recuperarDatosSistemas.getEstadoSistemaId());
							vListaDto.add(dto);
						}
						LOG.info("vListaDto:" + vListaDto.toString());
					}

					vRespuesta.setListaSistemas(vListaDto);
					vRespuesta.setOk(true);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));

				} else {
					vRespuesta.setOk(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
				}
			} else {
				LOG.info("No se pudo recuperar los datos incorrectos de envio.");
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
		}

		LOG.info("Saliendo recuperarListaPruebasSistemasDeCertificacionFuncionario vRespuesta={}", vRespuesta);
		return vRespuesta;
	}
	
	/**
	 * Recuperar Lista de sistemas que estan en proceso
	 * 
	 * @author: Noemi Mamani
	 * @Fecha: 12/12/2018
	 * @modificadoPor: Noemi mamani
	 * @FechaModificacion: 18/12/2018
	 * @Descripcion se refactorizo el metodo
	 * @return lista de sistemas asociados al contribuyente con estado de solicitud
	 */
	
	@Override
	public ObtenerListaSistemasCertificacionDto recuperaListaSolicitudSistemasCertificacion()
	{
		LOG.info("Ingresando recuperaListaSolicitudSistemasCertificacion ");
		
		ObtenerListaSistemasCertificacionDto vRespuesta = new ObtenerListaSistemasCertificacionDto();
		List<RecuperaListaSistemasCertificacionDto> vListaSistemasCertificadosDto = new ArrayList<>();		
		vRespuesta.setOk(false);
		int vCont = 0;
		String vDescripcionModalidad = " ";
		ClasificadorDto vClasificador= new ClasificadorDto();
		try {
			   List<SreSolicitudCertificacion> vListaSistemasCertificados = iSolicitudCertificacionConsultaDomain.ListaSolicitudesCertificacion();
			
				if(!vListaSistemasCertificados.isEmpty())
				{
					for(SreSolicitudCertificacion vSreSolicitud : vListaSistemasCertificados)
					{					 
						ModelMapper vMapper = new ModelMapper();
						vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
						RecuperaListaSistemasCertificacionDto dto = vMapper.map(vSreSolicitud, RecuperaListaSistemasCertificacionDto.class);
																		
						List<SreDetalleSolicitudesCertificaciones> vSreListaDetalleSolicitudesCertificaciones =  iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaDetalleSolicitudCertificacionId(vSreSolicitud.getSolicitudCertificacionId());
						if (vSreListaDetalleSolicitudesCertificaciones != null) {
							if (!vSreListaDetalleSolicitudesCertificaciones.isEmpty()) {
								SreSistemas recuperarDatosSistemas = iSistemasConsultaDomain.recuperarDatosSistemas(vSreListaDetalleSolicitudesCertificaciones.get(0).getSistemaId());
								
								ContribuyenteDto vDatosBasicos = iEmpadronamientoInterfacesService.obtenerDatosBasicosXIFC(vSreSolicitud.getPersonaContribuyenteId());
				                
								if(!vDatosBasicos.equals(null))
								{
									dto.setPersonaContribuyenteId(vSreSolicitud.getPersonaContribuyenteId());
									dto.setNit(vDatosBasicos.getNit());
									dto.setRazonSocial(vDatosBasicos.getNombreRazonSocial());
									dto.setEstadoSolicitudCertificacionId(vSreSolicitud.getEstadoSolicitudCertificacionId());
									dto.setSistemaId(vSreSolicitud.getSistemaId());
									dto.setNombreSistema(recuperarDatosSistemas.getNombreSistema());
									dto.setVersion(recuperarDatosSistemas.getVersion());
									dto.setDescripcionEstadoSolicitud(iClasificadorDomain.obtenerDatosParametrica(vSreSolicitud.getEstadoSolicitudCertificacionId()).getDescripcion());							
																
								
									for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreListaDetalleSolicitudesCertificaciones)
									{
										vClasificador = iClasificadorDomain.obtenerDatosParametrica(vSreDetalle.getModalidadFacturacionId());
										if(vCont == 0) {
											vDescripcionModalidad = vClasificador.getDescripcion();
											vCont = vCont + 1;
										}
										else {
											vDescripcionModalidad = vDescripcionModalidad + "/" + vClasificador.getDescripcion();	
										}	
									}
									dto.setDescripcionModalidad(vDescripcionModalidad);
									vListaSistemasCertificadosDto.add(dto);
									vCont = 0;
									vDescripcionModalidad = " ";
								
								}
							}
						}
					}
										
					vRespuesta.setListaSistemaDto(vListaSistemasCertificadosDto);					
					vRespuesta.setOk(true);				
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
					
				}else
				{	
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
				}
				
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));			
			
		}		
		LOG.info("Saliendo recuperaListaSolicitudSistemasCertificacion vRespuesta={}", vRespuesta);
		
		return vRespuesta;
	}

	@Override
	public RespuestaListadoSistemasDto obtenerSistemasCertificacionPaginado(SolicitudListadoSistemasDto pSolicitud) {
		RespuestaListadoSistemasDto vResultado = new RespuestaListadoSistemasDto();
		try {
			if (pSolicitud.isTotalRegistros()) {
				int vCantidadTotalRegistros = iSolicitudCertificacionConsultaDomain.obtenerSistemasCertificacionPaginadoTotal(pSolicitud.getFiltros());
				vResultado.setListaTamanio(vCantidadTotalRegistros);
			} else {
				List<RecuperaListaSistemasCertificacionDto> vListaDto = iSolicitudCertificacionConsultaDomain.obtenerSistemasCertificacionPaginado(pSolicitud.getPrimerRegistro(), pSolicitud.getTamanioPagina(), pSolicitud.getCampoOrden(), pSolicitud.isAscendente(), pSolicitud.getFiltros());
				vResultado.setLista(vListaDto);
			}
			vResultado.setOk(true);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
			vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
		}
		return vResultado;
	}
	
	
}
