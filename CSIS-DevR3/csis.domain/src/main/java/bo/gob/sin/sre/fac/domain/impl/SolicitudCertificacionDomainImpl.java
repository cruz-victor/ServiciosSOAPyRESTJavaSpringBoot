package bo.gob.sin.sre.fac.domain.impl;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sau.ausu.dto.RespuestaServicioDto;
import bo.gob.sin.sre.fac.dao.IPruebasDao;
import bo.gob.sin.sre.fac.dao.IPruebasSistemasDao;
import bo.gob.sin.sre.fac.dao.IRegistrosResultadosSolicitudesDao;
import bo.gob.sin.sre.fac.dao.ISistemasContribuyentesDao;
import bo.gob.sin.sre.fac.dao.ISistemasDao;
import bo.gob.sin.sre.fac.dao.ISistemasDeCertificacionDao;
import bo.gob.sin.sre.fac.dao.ISistemasModulosDao;
import bo.gob.sin.sre.fac.dao.ISolicitudCertificacionDao;
import bo.gob.sin.sre.fac.domain.IGenerarCufdDomain;
import bo.gob.sin.sre.fac.domain.IPruebasDomain;
import bo.gob.sin.sre.fac.domain.ISistemasDomain;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionDomain;
import bo.gob.sin.sre.fac.dto.ConsultaSistemasCertificadosDto;
import bo.gob.sin.sre.fac.dto.DatosSistemasDto;
import bo.gob.sin.sre.fac.dto.GeneraCuisDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasDto;
import bo.gob.sin.sre.fac.dto.PruebasDto;
import bo.gob.sin.sre.fac.dto.PruebasSistemasDto;
import bo.gob.sin.sre.fac.dto.RegistroAutorizacionRechazoCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaPruebasDto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.SistemasCertificadosContribuyenteDto;
import bo.gob.sin.sre.fac.dto.SistemasCertificadosDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudListaPruebasDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemas;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarSistemaACertificarDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarSolicitudACertificarDto;
import bo.gob.sin.sre.fac.model.SrePruebasSistemas;
import bo.gob.sin.sre.fac.model.SreRegistrarSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreRegistrosCufd;
import bo.gob.sin.sre.fac.model.SreRegistrosResultadosSolicitudes;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.sre.fac.model.SreSistemasDeCertificacion;
import bo.gob.sin.sre.fac.model.SreSistemasModulos;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.model.StrCpsClasificador;
import bo.gob.sin.str.util.facturacion.Utiles;

@Service
@Transactional
public class SolicitudCertificacionDomainImpl implements ISolicitudCertificacionDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SolicitudCertificacionDomainImpl.class);
	
	String res = null;

	@Autowired
	ISolicitudCertificacionDao iSolicitudCertificacionDao;
	
	@Autowired
	ISistemasDeCertificacionDao iSreFacDaoSistemasDeCertificacion;
	
	@Autowired
	ISistemasDao iSreFacDaoSistemas;
	
	@Autowired
	IPruebasSistemasDao iPruebasSistemasDao;
	
	@Autowired
	IPruebasDomain iSreFacListaPruebasService;

	@Autowired
	ISistemasModulosDao iSreFacDaoSistemasModulos;
	
	@Autowired
	ISistemasContribuyentesDao iSistemasContribuyentesDao;
	
	@Autowired
	IPruebasDao iListaPruebasDao;
	
	@Autowired
	IRegistrosResultadosSolicitudesDao iRegistrosResultadosSolicitudesDao;
	
	@Autowired
	ISistemasDomain iSreSistemaService;
	
	@Autowired
	ISistemasDomain iSistemasService;
	
	@Autowired
	IGenerarCufdDomain iGenerarCufdService;
	
	/**
     * Objetivo: recuperar solicitudes de certificacion por solicitudCertificacionId.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 20/06/2018
     */
	

	@Override
	public SreSolicitudCertificacion recuperaSolicitudesCertificacion(Long pSolicitudCertificacionId) {
		LOG.info("ingresando a recuperarSolicitudesCertificacion");
		SreSolicitudCertificacion vRespuesta = new SreSolicitudCertificacion();
		try {
			Integer estado=ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO;
			vRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificaciones(pSolicitudCertificacionId,estado);
		} catch (Exception e){
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
		}
		LOG.info("Saliendo de recuperar obtenerSolicitudSistemas: " + vRespuesta);
		return vRespuesta;
	}
	


	
//	RCR
	@Override
	public RespuestaActualizacionDto autorizarSolicitudCertificacionSistema(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando autorizarSolicitudCertificacionSistema pSolicitudCertificacionId ={} ", pSolicitudCertificacionId);		
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		SreSolicitudCertificacion vEntidadModificada = new SreSolicitudCertificacion();
		try {
			if (pSolicitudCertificacionId > 0 && pSolicitudCertificacionId != null) {
				Date vFechaActual = new Date();
				vEntidadModificada = iSolicitudCertificacionDao.modificarEstadoSolicitudCertificacionSistema(pSolicitudCertificacionId, vFechaActual, ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_AUTORIZADO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				vResultadoActualizacion.setEstaActualizado(false);
				vResultadoActualizacion.setOk(false);
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidadModificada));
			vResultadoActualizacion.setOk(false);
		}
		LOG.info("Saliendo autorizarSolicitudCertificacionSistema vResultadoActualizacion={}", vResultadoActualizacion);
		return vResultadoActualizacion;
	}
	//RCR
	@Override
	public RespuestaActualizacionDto rechazarSolicitudCertificacionSistema(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando rechazarSolicitudCertificacionSistema pSolicitudCertificacionId ={} ", pSolicitudCertificacionId);
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		SreSolicitudCertificacion vEntidadModificada = new SreSolicitudCertificacion();
		try {
			if (pSolicitudCertificacionId > 0 && pSolicitudCertificacionId != null) {
				Date vFechaActual = new Date();
				iSolicitudCertificacionDao.modificarEstadoSolicitudCertificacionSistema(pSolicitudCertificacionId, vFechaActual, ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_RECHAZADO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				vResultadoActualizacion.setEstaActualizado(false);
				vResultadoActualizacion.setOk(false);
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidadModificada));
		}
		LOG.info("Saliendo rechazarSolicitudCertificacionSistema vResultadoActualizacion={}", vResultadoActualizacion);
		return vResultadoActualizacion;
	}
	//RCR
	@Override
	public RespuestaActualizacionDto cancelarSolicitudCertificacionSistema(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando cancelarSolicitudCertificacionSistema pSolicitudCertificacionId ={} ", pSolicitudCertificacionId);
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		SreSolicitudCertificacion vEntidadModificada = new SreSolicitudCertificacion();
		try {
			if (pSolicitudCertificacionId > 0 && pSolicitudCertificacionId != null) {
				Date vFechaActual = new Date();
				iSolicitudCertificacionDao.modificarEstadoSolicitudCertificacionSistema(pSolicitudCertificacionId, vFechaActual, ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_CANCELADO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				vResultadoActualizacion.setEstaActualizado(false);
				vResultadoActualizacion.setOk(false);
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidadModificada));
		}
		LOG.info("Saliendo cancelarSolicitudCertificacionSistema vResultadoActualizacion={}", vResultadoActualizacion);
		return vResultadoActualizacion;
	}
	//RCR
	@Override
	public RespuestaActualizacionDto activarSolicitudCertificacionSistema(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando cancelarSolicitudCertificacionSistema pSolicitudCertificacionId ={} ", pSolicitudCertificacionId);
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		SreSolicitudCertificacion vEntidadModificada = new SreSolicitudCertificacion();
		try {
			if (pSolicitudCertificacionId > 0 && pSolicitudCertificacionId != null) {
				Date vFechaActual = new Date();
				iSolicitudCertificacionDao.modificarEstadoSolicitudCertificacionSistema(pSolicitudCertificacionId, vFechaActual, ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				vResultadoActualizacion.setEstaActualizado(false);
				vResultadoActualizacion.setOk(false);
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidadModificada));
		}
		LOG.info("Saliendo cancelarSolicitudCertificacionSistema vResultadoActualizacion={}", vResultadoActualizacion);
		return vResultadoActualizacion;
	}


			
			
			/**1
		     * Objetivo: registrar solicitud de sistemas.
		     * Creado por: Reynaldo Chambi.
		     * Fecha: 03/04/2018
		     * Modificado por: Gualberto Condori
		     * Fecha de Modificacion: 03/07/2018
		     */
			@Override
			public SreRegistrarSolicitudCertificacion registrarSistemaACertificar(SolicitudRegistrarSistemaACertificarDto pSolicitud){
				SreRegistrarSolicitudCertificacion vRespuesta = new SreRegistrarSolicitudCertificacion();
				
				SreSistemas vRegistroSistema = new SreSistemas();
				SreRegistrarSolicitudCertificacion vResultado = new SreRegistrarSolicitudCertificacion();
				vResultado.setEstaRegistrado(false);
				vRespuesta.setOk(false);
				java.util.Date vFechaHoy = new Date();
				try {
						
						vRegistroSistema.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
						vRegistroSistema.setEstadoSistemaId(ConstFacturacion.ESTADO_SISTEMA_INICIADO);
						vRegistroSistema.setFechaRegistro(vFechaHoy);
						vRegistroSistema.setFechaUltimaModificacion(vFechaHoy);
						//vRegistroSistema.setModalidadFacturacionId(pSolicitud.getModalidadFacturacionId());
						vRegistroSistema.setNombreSistema(pSolicitud.getNombreSistema());
						
						vRegistroSistema.setTipoDepartamentoId(pSolicitud.getTipoDepartamentoId());
						vRegistroSistema.setTipoSistemaId(pSolicitud.getTipoSistemaId());
						vRegistroSistema.setTramiteId(pSolicitud.getTramiteId());
						vRegistroSistema.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
						vRegistroSistema.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
						vRegistroSistema.setVersion(pSolicitud.getVersion());
						
						iSreFacDaoSistemas.save(vRegistroSistema);
						vRespuesta.setSistemaACertificar(vRegistroSistema);
						
						vRespuesta.setEstaRegistrado(true);
						vRespuesta.setOk(true);
						LOG.info("Datos de sistema registrados.");
						
				}catch (Exception e) {
 					LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSistema));
					LOG.info("Datos sistema no registrados.");
				}
				return vRespuesta;
			}
			
			
			/**2
		     * Objetivo: registrar solicitud de sistemas.
		     * Creado por: Reynaldo Chambi.
		     * Fecha: 03/04/2018
		     * Modificado por: Gualberto Condori
		     * Fecha de Modificacion: 03/07/2018
		     */
					@Override
					public SreSistemasContribuyentes registrarSistemasContribuyentes(SreSistemas pSistemaACertificar, SolicitudRegistrarSolicitudACertificarDto pSolicitud){
						   
						SreRegistrarSolicitudCertificacion vRespuesta = new SreRegistrarSolicitudCertificacion();
						SreRegistrarSolicitudCertificacion vRegistroSolicitui= new SreRegistrarSolicitudCertificacion(); 
						SreSistemasContribuyentes vRegistroSolicitud = new SreSistemasContribuyentes();
						SreRegistrarSolicitudCertificacion vResultado = new SreRegistrarSolicitudCertificacion();
						vResultado.setEstaRegistrado(false);
						vRespuesta.setOk(false);
						Date fechaActual = new Date();

						try {
							
							vRegistroSolicitud.setUsuarioRegistroId(pSistemaACertificar.getUsuarioRegistroId());
							vRegistroSolicitud.setUsuarioUltimaModificacionId(pSistemaACertificar.getUsuarioRegistroId());
							vRegistroSolicitud.setContribuyenteId(pSolicitud.getContribuyenteId());
							vRegistroSolicitud.setContribuyenteProveedorId(pSolicitud.getContribuyenteId());
							vRegistroSolicitud.setSistemaId(pSistemaACertificar.getSistemaId());
							//vRegistroSolicitud.setModalidadFacturacionId(pSistemaACertificar.getModalidadFacturacionId());
							vRegistroSolicitud.setEstadoSistemaContribuyenteId(ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_PENDIENTE);
							vRegistroSolicitud.setFechaSolicitud(fechaActual);
							vRegistroSolicitud.setFechaRegistro(pSistemaACertificar.getFechaRegistro());
							vRegistroSolicitud.setFechaUltimaModificacion(pSistemaACertificar.getFechaUltimaModificacion());
							vRegistroSolicitud.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
								
								iSistemasContribuyentesDao.save(vRegistroSolicitud);
								vRespuesta.setSistemaACertificar(pSistemaACertificar);

								LOG.info("Datos contribuyentes registrados.");
								vRespuesta.setEstaRegistrado(true);
								vRespuesta.setOk(true);

						} catch (Exception e) {
							LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSolicitui));
							LOG.info("Datos contribuyentes no registrados.");
							return vRegistroSolicitud;
						}
						return vRegistroSolicitud;
					}
			
					
					/**3
				     * Objetivo: registrar solicitud de sistemas.
				     * Creado por: Reynaldo Chambi.
				     * Fecha: 03/04/2018
				     * Modificado por: Gualberto Condori
				     * Fecha de Modificacion: 03/07/2018
				     */
					@Override
					public SreSolicitudCertificacion registrarSolicitudACertificar(SreSistemas pSistemaACertificar, SolicitudRegistrarSolicitudACertificarDto pSolicitud, Long pSistemaContribuyenteId){

						SreRegistrarSolicitudCertificacion vRespuesta = new SreRegistrarSolicitudCertificacion();
						SreSolicitudCertificacion vRegistroSolicitud = new SreSolicitudCertificacion();
						SreRegistrarSolicitudCertificacion vResultado = new SreRegistrarSolicitudCertificacion();
						
						vResultado.setEstaRegistrado(false);
						vRespuesta.setOk(false);
						try {
								vRegistroSolicitud.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
								
								vRegistroSolicitud.setEstadoSolicitudCertificacionId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO);
								vRegistroSolicitud.setCiteCertificado(pSolicitud.getCiteCertificado());
								vRegistroSolicitud.setCodigoCertificado(pSolicitud.getCodigoCertificado());
								//vRegistroSolicitud.setContribuyenteId(pSolicitud.getContribuyenteId());
								//vRegistroSolicitud.setCorreoContacto(pSolicitud.getCorreoContacto());
								vRegistroSolicitud.setFechaEmisionCertificado(pSolicitud.getFechaEmisionCertificado());
								//vRegistroSolicitud.setOficinaId(pSolicitud.getOficinaId());
								//vRegistroSolicitud.setPersonaContacto(pSolicitud.getPersonaContacto());
								//vRegistroSolicitud.setTelefonoContacto(pSolicitud.getTelefonoContacto());
								//vRegistroSolicitud.setTipoSolicitudId(ConstFacturacion.TIPO_SOLICITUD_ID_NUEVA);
								vRegistroSolicitud.setFechaRegistro(pSistemaACertificar.getFechaRegistro());
								vRegistroSolicitud.setFechaUltimaModificacion(pSistemaACertificar.getFechaUltimaModificacion());
								//vRegistroSolicitud.setModalidadId(pSistemaACertificar.getModalidadFacturacionId());
								//vRegistroSolicitud.setSistemaId(pSistemaACertificar.getSistemaId());
								vRegistroSolicitud.setTramiteId(pSistemaACertificar.getTramiteId());
								vRegistroSolicitud.setUsuarioRegistroId(pSistemaACertificar.getUsuarioRegistroId());
								vRegistroSolicitud.setUsuarioUltimaModficacionId(pSistemaACertificar.getUsuarioRegistroId());
								//vRegistroSolicitud.setCuis(generarCodigoCuis(pSistemaACertificar, pSolicitud.getNitContribuyente(), 0, pSolicitud.getContribuyenteId(), pSistemaContribuyenteId));
								iSolicitudCertificacionDao.save(vRegistroSolicitud);

								vRespuesta.setSistemaACertificar(pSistemaACertificar);
								vRespuesta.setRegistroSolicitud(vRegistroSolicitud);
								
								LOG.info("Datos de solicitud certificacion registrados.");
								vRespuesta.setEstaRegistrado(true);
								vRespuesta.setOk(true);

						} catch (Exception e) {
							LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSolicitud));
							LOG.info("Datos solicitud certificacion no registrados.");
							vRegistroSolicitud.setSolicitudCertificacionId(0L); 
						}
						return vRegistroSolicitud;
					}
			
				
					/**4
				     * Objetivo: registrar solicitud de sistemas.
				     * Creado por: Reynaldo Chambi.
				     * Fecha: 03/04/2018
				     * Modificado por: Gualberto Condori
				     * Fecha de Modificacion: 03/07/2018
				     */
					@Override
					public SreRegistrarSolicitudCertificacion registrarPruebasSistemas(SreSistemas pSistemaACertificar, SreSolicitudCertificacion pRegistroSolicitud, SolicitudPruebasSistemas pSolicitud, List<Integer> pTiposPrueba) {
						SreRegistrarSolicitudCertificacion vRespuesta = new SreRegistrarSolicitudCertificacion();
						
						SolicitudListaPruebasDto vSolicitudListaPruebas = new SolicitudListaPruebasDto();
						
						RespuestaListaPruebasDto vResultadoListaPruebas = new RespuestaListaPruebasDto();
						
						vRespuesta.setEstaRegistrado(false);
						vRespuesta.setOk(false);
						
						Date vFecha = new Date();
						try {
								for (Integer vPrueba : pTiposPrueba) {
									
									vSolicitudListaPruebas.setTipoPrueba(vPrueba);
									vResultadoListaPruebas = iSreFacListaPruebasService.recuperaListaPruebasPorTipo(vSolicitudListaPruebas);
									for (PruebasDto vRegistroprueba : vResultadoListaPruebas.getLista()) {
										
										SrePruebasSistemas vRegistroPruebasSistemas = new SrePruebasSistemas();
										vRegistroPruebasSistemas.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
										vRegistroPruebasSistemas.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_PENDIENTE);
										
										vRegistroPruebasSistemas.setFechaFin(pSolicitud.getFechaFin());
										vRegistroPruebasSistemas.setFechaInicio(vFecha);
										vRegistroPruebasSistemas.setObservaciones(pSolicitud.getObservaciones());
										vRegistroPruebasSistemas.setPruebaId(vRegistroprueba.getpruebaId());
										
										vRegistroPruebasSistemas.setSolicitudCertificacionId(pRegistroSolicitud.getSolicitudCertificacionId());
										
										vRegistroPruebasSistemas.setSistemaId(pSistemaACertificar.getSistemaId());
										vRegistroPruebasSistemas.setFechaRegistro(pSistemaACertificar.getFechaRegistro());
										vRegistroPruebasSistemas.setFechaUltimaModificacion(pSistemaACertificar.getFechaUltimaModificacion());
										vRegistroPruebasSistemas.setTramiteId(pSistemaACertificar.getTramiteId());
										vRegistroPruebasSistemas.setUsuarioRegistroId(pSistemaACertificar.getUsuarioRegistroId());
										vRegistroPruebasSistemas.setUsuarioUltimaModificacionId(pSistemaACertificar.getUsuarioRegistroId());
										
										iPruebasSistemasDao.save(vRegistroPruebasSistemas);
										
										vRespuesta.setEstaRegistrado(true);
										
										vRespuesta.setEstaRegistrado(true);
										vRespuesta.setOk(true);
										LOG.info("Datos pruebas sistema registrados.");

									}
								}
								
								vRespuesta.setEstaRegistrado(true);
						}catch (Exception e) {
							LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
							LOG.info("Datos pruebas sistema no registrados.");
						}
						return vRespuesta;
					}
			
					
					/**5
				     * Objetivo: registrar solicitud de sistemas.
				     * Creado por: Reynaldo Chambi.
				     * Fecha: 03/04/2018
				     * Modificado por: Gualberto Condori
				     * Fecha de Modificacion: 03/07/2018
				     */
					@Override
					public SreRegistrarSolicitudCertificacion registraModulosSistemaACertificar(SreSistemas pSolicitudSistema, SreSolicitudCertificacion pSolicitudCertificacion, List<Integer> pTiposModulos) {
						SreRegistrarSolicitudCertificacion vRespuesta = new SreRegistrarSolicitudCertificacion();
						vRespuesta.setEstaRegistrado(false);
						vRespuesta.setOk(false);
						Date vFechaHoy = new Date();
						try {
								for (Integer vModulo : pTiposModulos) {
									
									SreSistemasModulos vRegistro = new SreSistemasModulos();									
									
									vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
									vRegistro.setFechaRegistro(vFechaHoy);
									vRegistro.setFechaUltimaModificacion(vFechaHoy);
									vRegistro.setSistemaId(pSolicitudSistema.getSistemaId());
									
									vRegistro.setSolicitudCertificacionId(pSolicitudCertificacion.getSolicitudCertificacionId());
									vRegistro.setTipoModuloId(vModulo);
									vRegistro.setTramiteId(pSolicitudSistema.getTramiteId());
									vRegistro.setUsuarioRegistroId(pSolicitudSistema.getUsuarioRegistroId());
									vRegistro.setUsuarioUltimaModificacionId(pSolicitudSistema.getUsuarioRegistroId());

									iSreFacDaoSistemasModulos.save(vRegistro);
									vRespuesta.setEstaRegistrado(true);
									vRespuesta.setOk(true);
									LOG.info("Modulos registrados.");
								}
								
								vRespuesta.setEstaRegistrado(true);
						}catch (Exception e) {
							LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
							LOG.info("Modulos no registrados.");
						}
						return vRespuesta;
					}
	
	
	
	// RCR
 	public String generarCodigoCuis(SreSistemas pSistemaACertificar, Long pNit, Integer pSucursal, Long pContribuyenteId, Long pSistemaContribuyenteId)
	{
 		GeneraCuisDto vSolicitudCuis = new GeneraCuisDto();
		RespuestaServicioDto vRespuestaCuis = new RespuestaServicioDto();

		try {
			if (pNit > 0 && pSistemaACertificar != null) {
				vSolicitudCuis.setIdSistema(pSistemaACertificar.getSistemaId());
				vSolicitudCuis.setNit(pNit);
				vSolicitudCuis.setSucursal(pSucursal);
				//vSolicitudCuis.setModalidad(pSistemaACertificar.getModalidadFacturacionId());
				vSolicitudCuis.setUsuario(pSistemaACertificar.getUsuarioRegistroId());
				vSolicitudCuis.setContribuyenteId(pContribuyenteId);
				vSolicitudCuis.setSistemaContribuyenteId(pSistemaContribuyenteId);

				vRespuestaCuis = this.generarCuis(vSolicitudCuis);
				vRespuestaCuis.setOk(true);
				LOG.info("Cuis generado correctamente");
			}
			else {
				vRespuestaCuis.setOk(false);
				LOG.info("Cuis no generado, existen datos nulos o ceros");
			}

		} catch (Exception e) {
			vRespuestaCuis.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuestaCuis));
			LOG.info("Error, Excepcion al momento de generar");
		}
		
		return vRespuestaCuis.getValor();

	}

	//FRRA
	@Override
	public RespuestaServicioDto generarCuis(GeneraCuisDto solicitud) {

		RespuestaServicioDto vRespuesta = new RespuestaServicioDto();
		Utiles utiles = new Utiles();
		String modalidad = String.valueOf(solicitud.getModalidad());
		String sucursal = String.valueOf(solicitud.getSucursal());
		String nit = String.valueOf(solicitud.getNit());
		String idSistema = String.valueOf(solicitud.getIdSistema());
		
		String input = utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_MODALIDAD, modalidad)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_SUCURSAL, sucursal)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_NIT, nit)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_IDSISTEMA, idSistema);		
		
		try {
			String hexa = utiles.algoritmoGeneraCuis(input);
			String cuis = utiles.invertirCadena(hexa.toUpperCase());
			if (hexa.length() > 0) {
				LOG.info("GENERADO DE CUIS CON EXITO");
				vRespuesta.setValor(cuis);
				vRespuesta.setOk(true);
			} else {
				LOG.info("NO se GENERÓ el CUIS ");
			}
		} catch (Exception e) {
			vRespuesta.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo, Cuis no generado");
		}
		return vRespuesta;
	}
	
	@Override
	public RespuestaServicioDto generarCuisContribuyente(GeneraCuisDto pSolicitud) {
		SreSistemas vSistemaACertificar = new SreSistemas(); 
		SolicitudRegistrarSolicitudACertificarDto vSolicitudRegistrarSolicitudACertificarDto = new SolicitudRegistrarSolicitudACertificarDto();
		vSistemaACertificar.setUsuarioRegistroId(pSolicitud.getUsuario());
		Date fechaActual = new Date();
		vSolicitudRegistrarSolicitudACertificarDto.setContribuyenteId(pSolicitud.getContribuyenteId());		
		vSistemaACertificar.setFechaRegistro(fechaActual);
		vSistemaACertificar.setFechaUltimaModificacion(fechaActual);
		vSistemaACertificar.setSistemaId(pSolicitud.getIdSistema());
		//vSistemaACertificar.setSistemaId(2545L);		
		//vSistemaACertificar.setModalidadFacturacionId(((int)(long)pSolicitud.getModalidad()));
		
		// Registra la información en el tabla sre_fac_sistemas_contribuyentes
		SreSistemasContribuyentes resultado = registrarSistemasContribuyentes(vSistemaACertificar, vSolicitudRegistrarSolicitudACertificarDto);		
		pSolicitud.setSistemaContribuyenteId(resultado.getSistemaContribuyenteId());		
		RespuestaServicioDto vRespuestaServicioDto = generarCuis(pSolicitud);
		
		// Registra la información en el tabla sre_fac_registros_cufd
		SreRegistrosCufd VRegistrosCufd=new SreRegistrosCufd();
		VRegistrosCufd.setSucursalId(pSolicitud.getSucursal());
		VRegistrosCufd.setNitEmisor(pSolicitud.getNit());
		VRegistrosCufd.setCuis(vRespuestaServicioDto.getValor());
		VRegistrosCufd.setUsuarioRegistroId(pSolicitud.getUsuario());
		VRegistrosCufd.setSistemaId(pSolicitud.getIdSistema());
		VRegistrosCufd.setSistemaId(pSolicitud.getIdSistema());
		VRegistrosCufd.setSistemaId(pSolicitud.getIdSistema());
		iGenerarCufdService.registrarCufdWeb(VRegistrosCufd);
		vSistemaACertificar=null;
		fechaActual=null;
		vSolicitudRegistrarSolicitudACertificarDto=null;
		VRegistrosCufd=null;
		return vRespuestaServicioDto;
	}

	
	/**
	 * Recuperar Sistema En Proceso por Contribuyente
	 * 
	 * @author: Fabio Ramos
	 * @Fecha: 27/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 18/06/2018
	 * @param pContribuyenteId, número de id identificador en Padron
	 * @return   Devuelve una lista del tipo SreSistemasCertificacion.
	 */
	@Override
	public List<SreSistemasDeCertificacion> recuperarSistemasEnProcesoPorContribuyente(Long pContribuyenteId) {
		LOG.info("Ingresando recuperarSistemasEnProcesoPorContribuyente ");
		List<SreSistemasDeCertificacion> vListaRespuesta = new ArrayList<SreSistemasDeCertificacion>();

		try {
			vListaRespuesta = iSreFacDaoSistemasDeCertificacion.recuperarSistemasEnProcesoPorContribuyente(pContribuyenteId);	

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar recuperarSistemasEnProcesoPorContribuyente: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}

	//FRA
	@Override
		public ConsultaSistemasCertificadosDto recuperarSistemasCertificadosPorProveedor (SistemasCertificadosContribuyenteDto solicitud) {
			ConsultaSistemasCertificadosDto respuesta = new ConsultaSistemasCertificadosDto();
			respuesta.setOk(false);
			List<SistemasCertificadosDto> listaRespuesta =new ArrayList<>();
			try {
				if (solicitud.getContribuyenteId()!=null) {
				
					List<SreSistemasDeCertificacion> listaSistemas = new ArrayList<>();
					listaSistemas = iSreFacDaoSistemasDeCertificacion.recuperarSistemasCertificadosPorProveedor(solicitud.getContribuyenteId());
										
					for (int i = 0; i < listaSistemas.size(); i++) {
						
						SistemasCertificadosDto objDto = new SistemasCertificadosDto();				
						Object dato = listaSistemas.get(i);
						Object[] registro2 = (Object[]) dato;
						int j=0;
						for (Object item : registro2) {
							if(j==0) objDto.setSistemaId(Long.parseLong(item.toString()));
							if(j==1) objDto.setNombreSistema(item.toString());
							if(j==2) objDto.setModalidadFacturacionId(Long.parseLong(item.toString()));
							if(j==3) objDto.setCorreoContacto(item.toString());
							j++;
						}
						
						listaRespuesta.add(objDto);	
					}
					
					LOG.info("idContribuyente null");
					respuesta.setSistemasCertificados(listaRespuesta);
					respuesta.setOk(true);
				}
				else {
					
					respuesta.setSistemasCertificados(null);
					respuesta.setOk(false);
					
				}
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(respuesta));
				LOG.info("Saliendo de recuperar Sistemas Certificados Por Proveedor");
			}
			return respuesta;
		}
	
	/**
	 * Eliminar método
	 * Recuperar Grilla de Pruebas
	 * 
	 * @author: Reynaldo Chambi
	 * @Fecha: 
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 19/06/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto respuesta Dto de tipo Respuesta Pruebas Sistemas de Certificacion.
	 */
	
	@Override
	public RespuestaPruebasSistemasDeCertificacionDto recuperarListaPruebasSistemasDeCertificacion(SolicitudPruebasSistemasDeCertificacionDto pSolicitud) {

		RespuestaPruebasSistemasDeCertificacionDto respuesta = new RespuestaPruebasSistemasDeCertificacionDto(); 
		List<PruebasSistemasDto> listaDto= new ArrayList<PruebasSistemasDto>();
			
		SreSistemas vEntidadSistemas = iSreFacDaoSistemas.obtenerSolicitudSistemaCertificado(pSolicitud.getSistemaId());
		Integer vEstadoSistema = vEntidadSistemas.getEstadoSistemaId();
		if(vEstadoSistema.equals(ConstFacturacion.ESTADO_SISTEMA_CERTIFICADO)) {
			respuesta.setEsEditable(true);
		}else {
			respuesta.setEsEditable(false);
		}
			
		List<SrePruebasSistemas> listaEndidad = this.obtenerPruebasSistemasDeCertificacion(pSolicitud.getSistemaId(), pSolicitud.getSolicitudCertificacionId());
		try {
			for (SrePruebasSistemas registro : listaEndidad) {
				PruebasSistemasDto dto = new PruebasSistemasDto();
				dto.setPruebaSistemaId(registro.getPruebaSistemaId());
				
				dto.setEstadoPruebaId(registro.getEstadoPruebaId());
				//StrCpsClasificador vClasificador = iClasificadorDomain.recuperarClasificadorPorId(registro.getEstadoPruebaId());
				//dto.setDescripcionEstadoPrueba(vClasificador.getDescripcion());
				dto.setPruebaId(registro.getPruebaId());
				String vRegistroPrueba = iSreFacListaPruebasService.obtenerNombrePrueba(registro.getPruebaId());
				dto.setNombrePrueba(vRegistroPrueba);
				dto.setFechaFin(registro.getFechaFin());
				dto.setFechaInicio(registro.getFechaInicio());
				dto.setObservaciones(registro.getObservaciones());
				dto.setSistemaId(registro.getSistemaId());
				dto.setSolicitudCertificacionId(registro.getSolicitudCertificacionId());
				dto.setTramiteId(registro.getTramiteId());
				dto.setEstadoId(registro.getEstadoId());
					
				listaDto.add(dto);
			}
			respuesta.setLista(listaDto);
			respuesta.setOk(true);
			LOG.info("Exito.");
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(respuesta));
				LOG.info("Saliendo de recuperar lista de pruebas de sistemas");
				respuesta.setOk(false);
			}
			return respuesta;
		}
	

	
	/**
	 * Obtener Lista de Datos de Pruebas de Sistemas de Certificacion
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 19/06/2018
	 * @param pSistemaId, número identificación de sistema
	 * @param pSolicitudCertificacionId, número identificación de solicitud certificación
	 * @return   Devuelve una lista de objetos SrePruebasSistemas
	 */
	@Override
	public List<SrePruebasSistemas> obtenerPruebasSistemasDeCertificacion(Long pSistemaId, Long pSolicitudCertificacionId) {
		LOG.info("Ingresando obtenerPruebasSistemasDeCertificacion ");
		List<SrePruebasSistemas> vListaRespuesta = new ArrayList<SrePruebasSistemas>();
		try {
			vListaRespuesta = iPruebasSistemasDao.obtenerPruebasSistemasDeCertificacion(pSistemaId, pSolicitudCertificacionId);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar obtenerPruebasSistemasDeCertificacion: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}
	
	//actualizarPruebasSistemasDeCertificacion modificado
	//IASC - Se añadio actualizacion del campo usuarioUltimaModificacion - 20/09/2018
	@Override
	public SrePruebasSistemas actualizarPruebaSistemasDeCertificacion( SrePruebasSistemas pSolicitud) {
		SolicitudPruebasSistemasDeCertificacionDto vSolicitudListaPruebas = new SolicitudPruebasSistemasDeCertificacionDto();
		SrePruebasSistemas vRespuesta = new SrePruebasSistemas();
		vSolicitudListaPruebas.setSistemaId(pSolicitud.getSistemaId());
		vSolicitudListaPruebas.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
		Date vFechaActual = new Date();
		
		try {
			RespuestaPruebasSistemasDeCertificacionDto vListaPruebas = recuperarListaPruebasSistemasDeCertificacion(vSolicitudListaPruebas);
			for(PruebasSistemasDto dto : vListaPruebas.getLista()) {
				if(dto.getPruebaSistemaId().equals(pSolicitud.getPruebaSistemaId())) {
					if(!pSolicitud.getEstadoPruebaId().equals(ConstFacturacion.ESTADO_PRUEBA_APROBADA))
						vFechaActual = null;
					iPruebasSistemasDao.actualizarListaPruebasSistema(dto.getPruebaSistemaId(), 
							pSolicitud.getObservaciones(), pSolicitud.getEstadoPruebaId(), vFechaActual, pSolicitud.getUsuarioUltimaModificacionId());
					
				}
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de recuperar lista de pruebas de sistemas");
			
		}
		
		return vRespuesta;
	}
	
	//RCR
	//IASC - Se añadio actualizacion del campo usuarioUltimaModificacion - 20/09/2018
	@Override
	public RespuestaActualizacionDto actualizarPruebasSistemasDeCertificacion(SolicitudPruebasSistemas pSolicitud) {
		LOG.info("Iniciando de actualizarPruebasSistemasDeCertificacion");
		SolicitudPruebasSistemasDeCertificacionDto vSolicitudListaPruebas = new SolicitudPruebasSistemasDeCertificacionDto();
		RespuestaActualizacionDto vRespuesta = new RespuestaActualizacionDto();
		Date vFechaActual = new Date();
		vSolicitudListaPruebas.setSistemaId(pSolicitud.getSistemaId());
		vSolicitudListaPruebas.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
		
		try {
			RespuestaPruebasSistemasDeCertificacionDto vListaPruebas = recuperarListaPruebasSistemasDeCertificacion(vSolicitudListaPruebas);
			for (PruebasSistemasDto dto : vListaPruebas.getLista()) {
				if (dto.getPruebaSistemaId().equals(pSolicitud.getPruebaSistemaId())){
					if (!pSolicitud.getEstadoPruebaId().equals(ConstFacturacion.ESTADO_PRUEBA_APROBADA))
					vFechaActual = null;

					iPruebasSistemasDao.actualizarListaPruebasSistema(
							dto.getPruebaSistemaId(), 
							pSolicitud.getObservaciones(), 
							pSolicitud.getEstadoPruebaId(), 
							vFechaActual, pSolicitud.getUsuarioRegistroId());
					vRespuesta.setEstaActualizado(true);
					vRespuesta.setOk(true);
				}
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de recuperar lista de pruebas de sistemas");
			vRespuesta.setEstaActualizado(false);
			vRespuesta.setOk(false);
		}
		
		return vRespuesta;
	}
		
	@Override
	public SreSolicitudCertificacion actualizarSolicitudSistemaCertificado(Long pSolicitudCertificacionId) {
	
		SreSolicitudCertificacion vResultado = new SreSolicitudCertificacion();
		java.util.Date vFecha = new Date();
		try {
			vResultado = iSolicitudCertificacionDao.findById(pSolicitudCertificacionId);
			vResultado.setFechaUltimaModificacion(vFecha);
			vResultado.setEstadoSolicitudCertificacionId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO);

			iSolicitudCertificacionDao.saveOrUpdate(vResultado);
			
		} catch (Exception e) {
			return null;
		}
		return vResultado;
	}
		
		//RCR
		@Override
		public ListaSistemasDto listarSistemasPorIdContribuyente(SolicitudActualizaSolicitudCertificacionDto pSolicitud){
			ListaSistemasDto vListaDto = new ListaSistemasDto();
			List<SreSistemas> vListaFiltrada = new ArrayList<SreSistemas>();
			if(pSolicitud.getContribuyenteId() > 0 && pSolicitud.getContribuyenteId() != null) {
				try {
					List<SreSolicitudCertificacion> vResultadoEntidad = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionIdContribuyente(pSolicitud.getContribuyenteId());
					//TODO
//					for (SreSolicitudCertificacion item : vResultadoEntidad) {
//						SreSistemas vEntidad = iSreFacDaoSistemas.obtenerSolicitudSistemaCertificado(item.getSistemaId());
//						vListaFiltrada.add(vEntidad);
//					}
					vListaDto.setListaSistemas(vListaFiltrada);
					
				}catch (Exception e) {
					LogExcepcion.registrar(e, LOG, MethodSign.build(vListaDto));
					LOG.info("False");
				}
			}
			else {
				LOG.info("No recupera lista de sistemas");
				LOG.info("False");
			}
			
			return vListaDto;
		}
		
		//GCA
		@Override
		public ListaSistemasDto listarSistemasEstadoCertificacion(SolicitudActualizaSolicitudCertificacionDto pSolicitud) {

			ListaSistemasDto vListaDto = new ListaSistemasDto();
			List<SreSistemas> vListaFiltrada = new ArrayList<SreSistemas>();
			List<SreSolicitudCertificacion> vSolicitudFiltrada = new ArrayList<SreSolicitudCertificacion>();
			if(pSolicitud.getContribuyenteId() > 0 && pSolicitud.getContribuyenteId() != null) {
				try {
					List<SreSolicitudCertificacion> vResultadoEntidad = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionIdContribuyente(pSolicitud.getContribuyenteId());
					for (SreSolicitudCertificacion item : vResultadoEntidad) {
						//TODO
//						SreSistemas vEntidad = iSreFacDaoSistemas.obtenerSolicitudSistemaCertificado(item.getSistemaId());
//							if(vEntidad.getEstadoSistemaId().equals(ConstFacturacion.ESTADO_SISTEMA_CERTIFICADO)) {
//								vListaFiltrada.add(vEntidad);
//								vSolicitudFiltrada.add(item);
//							}							
							
					}
					vListaDto.setListaSistemas(vListaFiltrada);
					vListaDto.setListaSolicitudesCertificacion(vSolicitudFiltrada);
					
				}catch (Exception e) {
					LogExcepcion.registrar(e, LOG, MethodSign.build(vListaDto));
					LOG.info("False");
				}
			}
			else {
				LOG.info("No recupera lista de sistemas");
				LOG.info("False");
			}
			
			return vListaDto;
			
		}
			
		/**
		 * Recupera datos de sistema para registro de huella
		 * 
		 * @author: Ivan Salas
		 * @Fecha: 16/04/2018
		 * @modificadoPor: Carmen Rosa Silva
		 * @FechaModificacion: 14/08/2018
		 * @param pSistemaId, número de identificacion del sistema
		 * @return   Devuelve objeto Dto de tipo DatosSistemasDto.
		 */
		public DatosSistemasDto recuperarSistemaParaRegistroDeHuella(Long pSistemaId) {
			LOG.info("Ingresando recuperarSistemaParaRegistroDeHuella ");
			DatosSistemasDto vRespuesta = new DatosSistemasDto();
			try {
				SreSistemas vSistema = iSreFacDaoSistemas.obtenerSolicitudSistemaCertificado(pSistemaId);
				if (vSistema != null) { 
					SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
					Date vFechaHoy = new Date();
					Date vFecha = formateador.parse(formateador.format(vFechaHoy));
					  
					//String vTipoSistema = iClasificadorDomain.recuperarClasificadorPorId(vSistema.getTipoSistemaId()).getDescripcion();
					vRespuesta.setSistemaId(vSistema.getSistemaId());
					vRespuesta.setNombreSistema(vSistema.getNombreSistema());
					vRespuesta.setFechaSolicitud(vFecha);
					//vRespuesta.setTipoSistema(vTipoSistema);	
					vRespuesta.setOk(true);
				}
				else {
					vRespuesta.setOk(false);
				}
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
				vRespuesta.setOk(false);
			}
			LOG.info("Saliendo de recuperar recuperarSistemaParaRegistroDeHuella: " + vRespuesta.toString());
			return vRespuesta;
		}
		
//		public SreSistemas recuperarSistemaParaRegistroDeHuella(Long pSistemaId) {
//			
//			LOG.info("Ingresando recuperaSolicitudesCertificacion pSolicitudCertificacionId ={} ", pSistemaId);
//			SreSistemas vResultadoEntidad = new SreSistemas();
//			DatosSistemasDto vRespuesta = new DatosSistemasDto();
//			if(pSistemaId > 0 && pSistemaId != null) {
//				try {
//					vResultadoEntidad = iSreFacDaoSistemas.obtenerSolicitudSistemaCertificado(pSistemaId);
//					vRespuesta.setOk(true);
//					
//					SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//					Date vFechaHoy = new Date();
//					Date vFecha = formateador.parse(formateador.format(vFechaHoy));
//					
//					String vModalidad = iClasificadorDomain.recuperarClasificadorPorId(vResultadoEntidad.getModalidadFacturacionId()).getDescripcion();  
//					String vTipoSistema = iClasificadorDomain.recuperarClasificadorPorId(vResultadoEntidad.getTipoSistemaId()).getDescripcion();
//					vRespuesta.setSistemaId(vResultadoEntidad.getSistemaId());
//					vRespuesta.setNombreSistema(vResultadoEntidad.getNombreSistema());
//					vRespuesta.setFechaSolicitud(vFecha);
//					vRespuesta.setModalidad(vModalidad);
//					vRespuesta.setTipoSistema(vTipoSistema);	
//					vRespuesta.setOk(true);
//					
//				} catch (Exception e) {
//					LogExcepcion.registrar(e, LOG, MethodSign.build(vResultadoEntidad));
//					vRespuesta.setOk(false);
//					LOG.info("No se pudo recuperar la informacion");
//				}
//			}else {
//				LOG.info("No se pudo recuperar la informacion");
//			}
//			LOG.info("Saliendo recuperaSolicitudesCertificacion vResultadoActualizacion= ", vResultadoEntidad);
//			return vResultadoEntidad;
//		}
		
		//IASC - Registra los datos de la Autorizacion o Rechazo de la certificacion - 28/04/2018 
		public RegistroAutorizacionRechazoCertificacionDto registrarAprobacionRechazoCertificacion(RegistroAutorizacionRechazoCertificacionDto pSolicitud){
			RegistroAutorizacionRechazoCertificacionDto vRespuesta = new RegistroAutorizacionRechazoCertificacionDto();
			
			SreRegistrosResultadosSolicitudes vRegistro = new SreRegistrosResultadosSolicitudes();
			Date vFechaHoy = new Date();
			
			try {
				vRegistro.setUsuarioRegistroId(pSolicitud.getUsuarioId());
				vRegistro.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioId());
				vRegistro.setSistemaId(pSolicitud.getSistemaId());
				vRegistro.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
				vRegistro.setTramiteId(pSolicitud.getTramiteId());
				vRegistro.setTipoResultadoCertificacionId(pSolicitud.getTipoResultadoCertificacionId());
				vRegistro.setMotivoRechazo(pSolicitud.getMotivoRechazo());
				vRegistro.setFechaRegistro(vFechaHoy);
				vRegistro.setFechaUltimaModificacion(vFechaHoy);
				vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				iRegistrosResultadosSolicitudesDao.save(vRegistro);
				
				vRespuesta.setEstaRegistrado(true);
				vRespuesta.setOk(true);
				LOG.info("Datos registrados.");
				
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistro));
				LOG.info("Datos no registrados.");
				vRespuesta.setEstaRegistrado(false);
				vRespuesta.setOk(false);
			}
			return vRespuesta;
		}
	
		//	IASC - Registra los datos de la Autorizacion o Rechazo de la certificacion - 28/04/2018 refactorizado
		@Override
		public SreRegistrosResultadosSolicitudes registrarAprobacionRechazoCertificacion(SreRegistrosResultadosSolicitudes pSolicitud) {
			LOG.info("Iniciando registrarAprobacionRechazoCertificacion"+ pSolicitud);
			
			SreRegistrosResultadosSolicitudes vRegistro = new SreRegistrosResultadosSolicitudes();
			RegistroAutorizacionRechazoCertificacionDto vRegistro2 = new RegistroAutorizacionRechazoCertificacionDto();
			Date vFechaHoy = new Date();
			if(pSolicitud != null) {
				try {
					vRegistro.setUsuarioRegistroId(vRegistro2.getUsuarioId());
					vRegistro.setUsuarioUltimaModificacionId(vRegistro2.getUsuarioId());
					
					vRegistro.setSistemaId(pSolicitud.getSistemaId());
					vRegistro.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
					vRegistro.setTramiteId(pSolicitud.getTramiteId());
					vRegistro.setTipoResultadoCertificacionId(pSolicitud.getTipoResultadoCertificacionId());
					vRegistro.setMotivoRechazo(pSolicitud.getMotivoRechazo());
					vRegistro.setFechaRegistro(vFechaHoy);
					vRegistro.setFechaUltimaModificacion(vFechaHoy);
					vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
					iRegistrosResultadosSolicitudesDao.save(vRegistro);
					LOG.info("Datos de sistema registrados.");
				} catch (Exception e) {
					LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistro));
					LOG.info("Datos sistema no registrados.");
				}
			}
			else {
				LOG.info("Datos sistemas  no registrados");
			}
			LOG.info("saliendo de registrarSistemasACertificar");
			return vRegistro;
		}
		

		/**
		 * Recuperar Lista Solicitud Certificación por pContribuyenteId y oficinaId
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 20/06/2018
		 * @param pContribuyenteId, número de identificación del contribuyente
		 * @param pOficinaId, número de identificación de oficina
		 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
		 */
		@Override
		public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionContribuyenteOficina(Long pContribuyenteId, Integer pOficinaId) {
			LOG.info("Ingresando recuperaSolicitudesCertificacionContribuyenteOficina ");
			List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();

			try {
				vListaRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionContribuyenteOficina(pContribuyenteId, pOficinaId);	

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
			}
			LOG.info("Saliendo de recuperar recuperaSolicitudesCertificacionContribuyenteOficina: " + vListaRespuesta.toString());
			return vListaRespuesta;
		}
		
		/**
		 * Recuperar cuis
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 27/06/2018
		 * @param pSistemaId, número de identificación del sistema
		 * @param pContribuyenteId, número de identificación del contribuyente
		 * @param pTipoSolicitudId, número de identificación del tipo solicitud
		 * @return   Devuelve el cuis
		 */
		@Override
		public String obtenerCuis(Long pSolicitudCertificacionId) {
			LOG.info("Ingresando obtenerCuis ");
			SreSolicitudCertificacion vRespuesta = new SreSolicitudCertificacion();
			String vResultado="";
			try {
				vRespuesta = iSolicitudCertificacionDao.obtenerSolicitudCertificacion(pSolicitudCertificacionId);
				if(vRespuesta!=null) {
					//vResultado= vRespuesta.getCuis();
				}else {
					vResultado="";
				}
				
			} catch (Exception e) {
				LOG.info("Informacion cuis no recuperada.");
			}
			LOG.info("Saliendo de recuperar obtenerCuis: " + vResultado);
			return vResultado;
		}
		
		/**
		 * Recuperar numero de solicitudes certificacion de un sistema
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 06/07/2018
		 * @modificadoPor: 
		 * @FechaModificacion: 
		 * @param pSistemaId, número de identificacion del Sistema
		 * @return   Devuelve contador.
		 */
		@Override
		public boolean recuperarSolicitudCertificacionDeSistema(Long pSistemaId) {
			LOG.info("Ingresando recuperarSolicitudCertificacionDeSistema ");
			List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();
			int count=-1;
			boolean vRespuesta=false;
			try {
				vListaRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionSistemaId(pSistemaId);
				count=vListaRespuesta.size();
				if(count==1) {
					for(SreSolicitudCertificacion vSolicitud:vListaRespuesta) {
//						if(vSolicitud.getEstadoSolicitudCertificacionId().equals(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_RECHAZADO)  || vSolicitud.getEstadoSolicitudCertificacionId().equals(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_CANCELADO)){
//							vRespuesta=true;
//						}
					}
				}
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
			}
			LOG.info("Saliendo de recuperar recuperarSolicitudCertificacionDeSistema: " + vListaRespuesta.toString());
			return vRespuesta;
		}
		
	
		/**
		 * Recuperar Lista SolicitudCertificacion por contribuyenteId
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 02/08/2018
		 * @param pContribuyenteId, número de identificación del contribuyente
		 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
		 */
		@Override
		public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyente(Long pContribuyenteId) {
			LOG.info("Ingresando recuperaSolicitudesCertificacionIdContribuyente ");
			List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();
			try {
				vListaRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionIdContribuyente(pContribuyenteId);	

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
			}
			LOG.info("Saliendo de recuperar recuperaSolicitudesCertificacionIdContribuyente: " + vListaRespuesta.toString());
			return vListaRespuesta;
		}
		
		
		/**
		 * Recuperar Lista SolicitudCertificacion por contribuyenteId y estado
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 10/08/2018
		 * @param pContribuyenteId, número de identificación del contribuyente
		 * @param pEstadoSolicitudCertificacionId, número de identificación del estado de solicitud de certificacion
		 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
		 */
		@Override
		public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyenteEstado(Long pContribuyenteId, Integer pEstadoSolicitudCertificacionId) {
			LOG.info("Ingresando recuperaSolicitudesCertificacionIdContribuyenteEstado ");
			List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();
			try {
				vListaRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionIdContribuyenteEstado(pContribuyenteId,pEstadoSolicitudCertificacionId);	

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
			}
			LOG.info("Saliendo de recuperar recuperaSolicitudesCertificacionIdContribuyenteEstado: " + vListaRespuesta.toString());
			return vListaRespuesta;
		}
		
		/**
		 * Registrar Verificacion In Situ
		 * @author: Carmen Rosa Silva
		 * @Fecha: 31/08/2018
		 * @param pSolicitudCertificacionId,número de identificacion solicitud certificacion id
		 * @param pEstadoSolicitudCertificacionId, estadi
		 * @return   Devuelve objeto SreSolicitudCertificacion.
		 */
		@Override
		public boolean modificarEstadoSolicitudCertificacion(Long pSolicitudCertificacionId, Integer pEstadoSolicitudCertificacionId) {
			LOG.info("Ingresando modificarEstadoSolicitudCertificacion ");
			boolean vResultado=false;
			SreSolicitudCertificacion vEntidad = new SreSolicitudCertificacion();
			try {
				vEntidad = iSolicitudCertificacionDao.findById(pSolicitudCertificacionId);
				vEntidad.setFechaUltimaModificacion(new Date());
				vEntidad.setEstadoSolicitudCertificacionId(pEstadoSolicitudCertificacionId);
				iSolicitudCertificacionDao.saveOrUpdate(vEntidad);
				vResultado=true;
			} catch (Exception e) {
				vResultado = false;	
			}
			LOG.info("Saliendo de recuperar modificarEstadoImprentaObservado: " + vResultado);
			return vResultado;
		}
	
	
	/**
	 * Cambiar estado en Solicitud Certificacion 
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 19/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitudCertificacionId, id de la tabla solicitud certificacion
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de solicitud certificacion
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
		//TODO BORRAR LLEVADO A SOLICITUDCERTIFICACION AMB DOMAIN,
	@Override
	public SreSolicitudCertificacion habilitarEstadoCertificacion(Long pSolicitudCertificacionId, Long pUsuario, Integer pEstadoId) {
	
		SreSolicitudCertificacion vResultado = new SreSolicitudCertificacion();
		java.util.Date vFecha = new Date();
		try {
			vResultado = iSolicitudCertificacionDao.findById(pSolicitudCertificacionId);
			vResultado.setFechaUltimaModificacion(vFecha);
			vResultado.setUsuarioUltimaModficacionId(pUsuario);
			vResultado.setEstadoSolicitudCertificacionId(pEstadoId);

			iSolicitudCertificacionDao.saveOrUpdate(vResultado);
			
		} catch (Exception e) {
			return null;
		}
		return vResultado;
	}
	
	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pContribuyenteId,número de identificacion del contribuyente
	 * @param pEstadoSolicitudCertificacionId, estado de certificacion
	 * @return   Devuelve objeto SreSolicitudCertificacion.
	 */
	@Override
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(Long pContribuyenteId, Integer pEstadoCertificacion) {
		LOG.info("Ingresando recuperaSolicitudesCertificacionParaCancelar ");
		List<SreSolicitudCertificacion> vListaRespuesta = new ArrayList<SreSolicitudCertificacion>();
		try {
			vListaRespuesta = iSolicitudCertificacionDao.recuperaSolicitudesCertificacionParaCancelar(pContribuyenteId, pEstadoCertificacion);	

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar recuperaSolicitudesCertificacionParaCancelar: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}
}
