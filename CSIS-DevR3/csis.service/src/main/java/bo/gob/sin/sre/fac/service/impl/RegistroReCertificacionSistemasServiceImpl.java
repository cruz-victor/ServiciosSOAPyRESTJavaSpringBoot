package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sap.cipr.dto.SolicitudDerivacionDto;
import bo.gob.sin.sre.fac.domain.IContribuyenteModalidadConsultaDomain;
import bo.gob.sin.sre.fac.domain.IGenerarCodigoCuisDomain;
import bo.gob.sin.sre.fac.domain.IParametricasFacturacionDomain;
import bo.gob.sin.sre.fac.domain.IPruebaAutomaticaConsultaDomain;
import bo.gob.sin.sre.fac.domain.IPruebaManualConsultaDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarContactoCertificacionDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarDetalleSolicitudesCertificacionDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarModulosSistemasDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarSistemaContribuyenteDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarSistemaDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarSolicitudCertificacionDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarTipoDocumentoSistemaDomain;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasAutomaticasABMDomain;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasManualesABMDomain;
import bo.gob.sin.sre.fac.domain.ISistemasConsultaDomain;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrarSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudGeneracionCuisDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarReCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudVerificacionSistemaRegistradoDto;
import bo.gob.sin.sre.fac.model.SreContribuyentesModalidades;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;
import bo.gob.sin.sre.fac.model.SrePruebasManuales;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.service.IRegistroCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.IRegistroReCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.external.ServiciosWorkFlowRest;
import bo.gob.sin.sre.fac.service.validation.ValidarSolicitudCertificacionServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
//@Transactional(rollbackFor = Exception.class)
public class RegistroReCertificacionSistemasServiceImpl implements IRegistroReCertificacionSistemasService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroReCertificacionSistemasServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	@Autowired
	private IRegistrarSistemaDomain iRegistrarSistemaDomain;

	@Autowired
	private IRegistrarSistemaContribuyenteDomain iRegistrarSistemaContribuyenteDomain;

	@Autowired
	private IRegistrarSolicitudCertificacionDomain iRegistrarSolicitudCertificacionDomain;

	@Autowired
	private IRegistrarModulosSistemasDomain iRegistrarModulosSistemasDomain;

	@Autowired
	private IRegistrarContactoCertificacionDomain iRegistrarContactoCertificacionDomain;

	@Autowired
	private IRegistrarTipoDocumentoSistemaDomain iRegistrarTipoDocumentoSistemaDomain;

	@Autowired
	private IRegistrarDetalleSolicitudesCertificacionDomain iRegistrarDetalleSolicitudesCertificacionDomain;

	@Autowired
	private IGenerarCodigoCuisDomain iGenerarCodigoCuisDomain;

	@Autowired
	private ISistemasConsultaDomain iSistemasConsultaDomain;

	@Autowired
	private IContribuyenteModalidadConsultaDomain iContribuyenteModalidadConsultaDomain;

	@Autowired
	private IPruebaManualConsultaDomain iPruebaManualConsultaDomain;

	@Autowired
	private IRegistroPruebasManualesABMDomain iRegistroPruebasManualesABMDomain;

	@Autowired
	private IPruebaAutomaticaConsultaDomain iPruebaAutomaticaConsultaDomain;

	@Autowired
	private IRegistroPruebasAutomaticasABMDomain iRegistroPruebasAutomaticasABMDomain;
	
	@Autowired
	private IParametricasFacturacionDomain iParametricasFacturacionDomain;

	/**
	 * @Objetivo: registrar solicitud de sistemas. Creado por: Reynaldo Chambi.
	 * @Fecha: 03/04/2018 
	 * @Modificado por: Gualberto Condori Fecha de Modificacion:
	 * @Fecha:04/07/2018 IASC - Refactorizacion y se añadio registro de contactos y tipos de facturas - 21/11/2018
	 */
	@Override
	public RespuestaRegistrarSolicitudCertificacionDto registrarRecertificacionSolicitudSistema(SolicitudRegistrarReCertificacionSistemasDto pSolicitud)
	{
		RespuestaRegistrarSolicitudCertificacionDto vResultado = new RespuestaRegistrarSolicitudCertificacionDto();
		vResultado.setOk(false);
		boolean vResultadoListaContactos = false;
		boolean vResultadoDocumentosSistemas = false;
		boolean vResultadoModalidad = false;
		long vTramite;

		List<Integer> vTiposModalidad = new ArrayList<>();
		List<Integer> vListaDocumentos = new ArrayList<>();
		
		pSolicitud.getListaModalidad().stream().forEach((c) -> vTiposModalidad.add(Integer.parseInt(c)));
		pSolicitud.getListaDocumentos().stream().forEach((c) -> vListaDocumentos.add(Integer.parseInt(c)));
		
		ValidarSolicitudCertificacionServiceImpl vValCertificacion = new ValidarSolicitudCertificacionServiceImpl(mensajesDomain);
		vValCertificacion.validarSolicitudReCertificacion(pSolicitud.getContribuyenteId(), pSolicitud.getListaContactos());

		if (vValCertificacion.isValido()) 
		{
			ServiciosWorkFlowRest vWorkFlowRest = new ServiciosWorkFlowRest();
			
			SolicitudDerivacionDto pSolicitudDerivacion = new SolicitudDerivacionDto();
			pSolicitudDerivacion.setProcesoAutomatico(true);
			pSolicitudDerivacion.setUsuarioRegistroId(pSolicitud.getUsuarioId());
			pSolicitudDerivacion.setUsuarioOrigenId(pSolicitud.getUsuarioId());
			pSolicitudDerivacion.setProcesoId(ConstFacturacion.PROCESO_DE_CERTIFICACION);
			pSolicitudDerivacion.setOficinaOrigenId(pSolicitud.getOficinaId());
			pSolicitudDerivacion.setInstruccionAutomatico(ConstFacturacion.INSTRUCCION_INICIO_TRAMITE_AUTOMATICO);
			
			vTramite = vWorkFlowRest.iniciarProcesoAutomaTico(pSolicitudDerivacion);
			
			if (vTramite != 0) 
			{
				LOG.info("Ingresando a recuperarDatosSistemas");
				try 
				{
					// 1.- SreSistemas vResultadoSistema = iRegistrarSistemaDomain.registrarSistemaACertificar(vSolicitudSistema);
					SreSistemas vResultadoSistema = iSistemasConsultaDomain.recuperarDatosSistemas(pSolicitud.getSistemaId());
					
					if (vResultadoSistema.getSistemaId() > 0) 
					{	
						LOG.info("Ingresando a registrarSolicitudcertificacion");
						SreSolicitudCertificacion vSreSolicitudCertificacion = new SreSolicitudCertificacion();
						
						// 2.- SE REGISTRA SOLICITUD DE CERTIFICACION
						vSreSolicitudCertificacion = iRegistrarSolicitudCertificacionDomain.registrarSolicitudRecertificacionCertificacion(pSolicitud.getContribuyenteId(), vTramite, pSolicitud.getUsuarioId(), vResultadoSistema.getSistemaId());
						
						SreSolicitudCertificacion vRegistroSolicitud = new SreSolicitudCertificacion();
						
						vRegistroSolicitud = iRegistrarSolicitudCertificacionDomain.actualizarEstadoHistoricoCertificadoSolicitudCertificacion(pSolicitud.getSolicitudCertificacionId(), pSolicitud.getUsuarioId());
	
						if (vSreSolicitudCertificacion.getSolicitudCertificacionId() > 0 && vRegistroSolicitud.getSolicitudCertificacionId() > 0) 
						{
							LOG.info("Ingresando a registrar lista contactos");
							
							// 3.- SE REGISTRA LOS CONTACTOS
							vResultadoListaContactos = iRegistrarContactoCertificacionDomain.registrarContactoCertificacion(vSreSolicitudCertificacion, pSolicitud.getListaContactos());
	
							if (vResultadoListaContactos) 
							{
									LOG.info("Ingresando a registrar detalle solicitud certificaciones");
									SreDetalleSolicitudesCertificaciones vSreDetalleSolicitudCertificacion = new SreDetalleSolicitudesCertificaciones();
									SolicitudGeneracionCuisDto pSolicitudCuis = new SolicitudGeneracionCuisDto();
	
									for (Integer vRegistroModalidad : vTiposModalidad) 
									{
										pSolicitudCuis.setNit(pSolicitud.getNit());
										pSolicitudCuis.setIdSistema(vResultadoSistema.getSistemaId());
										pSolicitudCuis.setSucursal(pSolicitud.getSucursal());
										pSolicitudCuis.setModalidad(vRegistroModalidad);
	
										String vCuis = iGenerarCodigoCuisDomain.generarCodigoCuis(pSolicitudCuis);
										
										if(!vCuis.equals("0"))
										{
											// 4.- REGISTRO DETALLE SOLICITUDES DE CERTIFICACION
											vSreDetalleSolicitudCertificacion = iRegistrarDetalleSolicitudesCertificacionDomain.registrarDetalleSolicitudCertificacion(vSreSolicitudCertificacion, vResultadoSistema.getSistemaId(), vRegistroModalidad, vCuis);
											
											if (vSreDetalleSolicitudCertificacion.getDetalleSolicitudCertificacionId() > 0) 
											{
												vResultadoModalidad = true;
											} 
											else 
											{
												vResultadoModalidad = false;
												break;
											}
										}
										else
										{
											vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.VALIDACION_CUIS_ERROR));
											vResultadoModalidad = false;
											break;
										}
									}
	
									if (vResultadoModalidad) 
									{
										LOG.info("Ingresando a registrar documentos sistemas");
										// 5.- tipos documentos sistemas
										vResultadoDocumentosSistemas = iRegistrarTipoDocumentoSistemaDomain.registrarTipoDocumentoSistema(vResultadoSistema, vSreSolicitudCertificacion.getSolicitudCertificacionId(), vListaDocumentos);
	
										if (vResultadoDocumentosSistemas) 
										{
												LOG.info("Registro existoso");
												
												vResultado.setOk(true);
												vResultado.setEstaRegistrado(true);
												vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_EXITOSO));
												vResultado.setSolicitudCertificacionId(vSreSolicitudCertificacion.getSolicitudCertificacionId());
												vResultado.setSistemaId(vResultadoSistema.getSistemaId());
										} 
										else 
										{
											vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_DOCUMENTOS));
										}
									} 
									else 
									{
										vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
												EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DETALLE_SOLICITUD));
									}
							} 
							else 
							{
								vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_CONTACTOS));
							} // Fin Contactos
						} 
						else 
						{
							vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_SOLICITUD));
						} // Fin Solicitud Certificacion
					} 
					else 
					{
						vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_DATOS_SISTEMAS));
					} // Fin Sistemas
	
				} 
				catch (Exception e) 
				{
					LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
					LOG.info("Registro Exitoso.");
					vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
				}
			}
			else 
			{
				vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_TRAMITE_ERROR));
			}
		} 
		else 
		{
			LOG.info("No se pudo registrar por datos incorrectos de envio.");
			vResultado.setMensajes(vValCertificacion.getMensajes());
		}
		
		return vResultado;
	}

	/**
	 * Objetivo: verificacion de sistemas registrados. Creado por: Reynaldo Chambi.
	 * Fecha: 13/04/2018 Modificado por: Gualberto Condori Fecha de Modificacion:
	 * 15/06/2018 IASC - Se añadio validacion de modalidad segun normativa -
	 * 23/11/2018
	 */
	@Override
	public RespuestaEstaRegistradoGenericoDto verificacionSistemaRegistrado(
			SolicitudVerificacionSistemaRegistradoDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		List<SreSistemas> vEntidad = new ArrayList<>();
		List<Integer> vTiposModalidad = new ArrayList<>();
		pSolicitud.getListaModalidad().stream().forEach((c) -> vTiposModalidad.add(Integer.parseInt(c)));
		//List<Integer> vTiposModalidad = pSolicitud.getListaModalidad();
		Integer vTipoModalidad = 0;

		ValidarSolicitudCertificacionServiceImpl vValSistema = new ValidarSolicitudCertificacionServiceImpl(
				mensajesDomain);
		vValSistema.validarSistemaRegistrado(pSolicitud.getNombreSistema(), vTiposModalidad,
				pSolicitud.getContribuyenteId());

		int vAux = 0;
		boolean vModalidad = false;
		if (vValSistema.isValido()) {
			LOG.info("Ingresando a verificacionSistemaRegistrado");
			try {
				if (pSolicitud != null) {
					LOG.info("ingresando a verificacionSistemaRegistrado");
					for (Integer vRegistroModalidad : vTiposModalidad) {
						vEntidad = iSistemasConsultaDomain.verificacionSistemaRegistrado(pSolicitud.getNombreSistema(),
								vRegistroModalidad, pSolicitud.getContribuyenteId());
						if (vEntidad == null) {
							vAux = 2; //Error
							break;
						}
						else {
							if (vEntidad.size() > 0 || !vEntidad.isEmpty() ) {
								vAux = 1;
							}
						}
					}
					// Sistema no esta registrado
					if (vAux == 0) {
						// Verificamos si le corresponde la modalidad al contribuyente para sistema
						// Propio
						if (pSolicitud.getTipoSistema() == ConstFacturacion.TIPO_SISTEMA_PROPIO) {
							if (pSolicitud.getListaModalidad().size() > 1) {
								vRespuesta.setOk(true);
								vRespuesta.setEstaRegistrado(true);
								vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_CANTIDAD_MODALIDAD_ERROR));
							}
							else {
								for (Integer vRegistroModalidad : vTiposModalidad) {
									long vRegistro = iContribuyenteModalidadConsultaDomain
											.obtieneModalidadContribuyenteNormativa(pSolicitud.getNit(),
													vRegistroModalidad);
									// Si corresponde la modalidad
									if (vRegistro > 0) {
										vModalidad = true;
									} else {
										vModalidad = false;
										vTipoModalidad = vRegistroModalidad;
										break;
									}
								}
								if (vModalidad) {
									vRespuesta.setOk(false);
									vRespuesta.setEstaRegistrado(false);
									vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
											EnumFacturacionTipoMensaje.ACEPTACION_SOLICITUD_CERTIFICACION_SISTEMA_NUEVO));
								} else {
									//Recupera valor de la modalidad solicitada del clasificador de facturacion
									Integer vModalidadSolicitada = iParametricasFacturacionDomain.obtenerEntidadPorCodigoEquivalente(vTipoModalidad, "TIPO MODALIDAD").getCodigoClasificador();
									//Recupera valor de la modalidad asignada en la normativa 
									SreContribuyentesModalidades vRegistro = iContribuyenteModalidadConsultaDomain.obtieneModalidadContribuyente(pSolicitud.getNit());
									//Recupera valor de la modalidad asignada del clasificador de facturacion
									Integer vModalidadAsignada = iParametricasFacturacionDomain.obtenerEntidadPorCodigoEquivalente(vRegistro.getModalidadFacturacionId(), "TIPO MODALIDAD").getCodigoClasificador();
								
									if (vModalidadSolicitada < vModalidadAsignada) {
										vRespuesta.setOk(false);
										vRespuesta.setEstaRegistrado(false);
										vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
												EnumFacturacionTipoMensaje.ACEPTACION_SOLICITUD_CERTIFICACION_SISTEMA_NUEVO));
									}
									else {
										vRespuesta.setOk(true);
										vRespuesta.setEstaRegistrado(true);
										vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
												EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_MODALIDAD_NORMATIVA_ERROR));
									}
								}
							}
						} else {
							vRespuesta.setOk(false);
							vRespuesta.setEstaRegistrado(false);
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumFacturacionTipoMensaje.ACEPTACION_SOLICITUD_CERTIFICACION_SISTEMA_NUEVO));
						}
					} else {
						if (vAux == 1) {
							vRespuesta.setOk(true);
							vRespuesta.setEstaRegistrado(true);
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumFacturacionTipoMensaje.VALIDACION_SOLICITUD_CERTIFICACION_EXISTENCIA_SISTEMAS));
						}
						else {
							vRespuesta.setOk(false);
							vRespuesta.setEstaRegistrado(false);
							vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));
						}
					}
				}
			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				LOG.info("No se pudo verificar.");
			}
		}
		LOG.info("Finalizacion:" + vRespuesta.toString());
		return vRespuesta;
	}

	@Override
	public RespuestaRegistrarSolicitudCertificacionDto asignarFechaAprobacion(
			SolicitudRegistrarCertificacionSistemasDto pSolicitud) {
		// TODO Auto-generated method stub
		return null;
	}

}
