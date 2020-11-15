package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.cps.clas.model.StrCpsClasificador;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;
import bo.gob.sin.sre.fac.domain.IConsultaEstadoCertificacionSistemasDomain;
import bo.gob.sin.sre.fac.domain.IDetalleSolicitudesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.domain.ISistemasConsultaDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemaCertificacion;
import bo.gob.sin.sre.fac.dto.SistemaSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.service.IConsultaEstadoCertificacionSistemasService;
import bo.gob.sin.sre.fac.service.IParametricasDomain;

@Service
@Transactional
public class ConsultaEstadoCertificacionSistemasServiceDomainImpl  implements  IConsultaEstadoCertificacionSistemasService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaEstadoCertificacionSistemasServiceDomainImpl.class);
	
	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	IConsultaEstadoCertificacionSistemasDomain iConsultaEstadoCertificacionSistemasDomain;
	
	@Autowired
	IDetalleSolicitudesCertificacionesConsultaDomain iDetalleSolicitudesCertificacionesConsultaDomain;
	
	@Autowired
	ISistemasConsultaDomain iSistemasConsultaDomain;
	
	@Autowired
	IParametricasDomain iClasificadorDomain;
	
	
	List<ClasificadorDto> vClasificadores = new ArrayList<>();
	
	public ClasificadorDto obtenerClasificador(int clasificadorId) {
		Optional<ClasificadorDto> match = this.vClasificadores.stream().filter(item -> item.getClasificadorId() == clasificadorId).findFirst();
		if (match.orElse(null) != null) {
			return match.get();
		} else {
			ClasificadorDto vClasificador = iClasificadorDomain.obtenerDatosParametrica(clasificadorId);
			this.vClasificadores.add(vClasificador);
			return vClasificador;
		}
	}
	
	public RecuperaListaSistemaCertificacion recuperaListaEstadoCertificacionSistemasService(long pPersonaContribuyenteId)
	{
		LOG.info("Ingresando recuperaListaEstadoCertificacionSistemasService ");		
		RecuperaListaSistemaCertificacion vRespuesta = new RecuperaListaSistemaCertificacion();
		List<SistemaSolicitudCertificacionDto> vListaSistemasCertificadosDto = new ArrayList<>();
		vRespuesta.setOk(false);
		
		try {
				if(pPersonaContribuyenteId >0)
				{
					List<SreSolicitudCertificacion> vListaSistemasCertificados = iConsultaEstadoCertificacionSistemasDomain.recuperaListaEstadoCertificacionSistemas(pPersonaContribuyenteId);
					
					if(!vListaSistemasCertificados.isEmpty())
					{
						
						for(SreSolicitudCertificacion vSreSolicitud : vListaSistemasCertificados)
						{
							ModelMapper vMapper = new ModelMapper();
							vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
							SistemaSolicitudCertificacionDto dtoDatos = vMapper.map(vSreSolicitud, SistemaSolicitudCertificacionDto.class);
							
							List<SreDetalleSolicitudesCertificaciones> vSreListaDetalleSolicitudesCertificaciones =  iDetalleSolicitudesCertificacionesConsultaDomain.obtenerListaDetalleSolicitudCertificacionId(vSreSolicitud.getSolicitudCertificacionId());
							
							for (SreDetalleSolicitudesCertificaciones vSreDetalle : vSreListaDetalleSolicitudesCertificaciones)
							{
								SreSistemas recuperarDatosSistemas = iSistemasConsultaDomain.recuperarDatosSistemas(vSreDetalle.getSistemaId());							
								dtoDatos.setPersonaContribuyenteId(vSreSolicitud.getPersonaContribuyenteId());
								dtoDatos.setEstadoSolicitudCertificacionId(vSreSolicitud.getEstadoSolicitudCertificacionId());
								dtoDatos.setFechaAprobacion(vSreSolicitud.getFechaAprobacion());
								dtoDatos.setTramiteId(vSreSolicitud.getTramiteId());
								dtoDatos.setSistemaId(vSreSolicitud.getSistemaId());
								dtoDatos.setNombreSistema(recuperarDatosSistemas.getNombreSistema());
								dtoDatos.setVersion(recuperarDatosSistemas.getVersion());
								dtoDatos.setDescripcionEstadoSolicitudCertificacion(obtenerClasificador(vSreSolicitud.getEstadoSolicitudCertificacionId()).getDescripcion());															
								String vDescripcionModalidad = " ";
								if (vSreListaDetalleSolicitudesCertificaciones.size() > 1) {
									vDescripcionModalidad += obtenerClasificador(vSreDetalle.getModalidadFacturacionId()).getDescripcion()+"/";
									
								}
								else{
									vDescripcionModalidad += obtenerClasificador(vSreDetalle.getModalidadFacturacionId()).getDescripcion();
								}
								dtoDatos.setDescripcionModalidad(vDescripcionModalidad);
								
								vListaSistemasCertificadosDto.add(dtoDatos);							
							}	
						
						}
						
						vRespuesta.setLista(vListaSistemasCertificadosDto);
						vRespuesta.setOk(true);						
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
					}
					else
					{
						LOG.info("No se puede recuperar los datos de la solicitud certificacion");
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_DATOS_NULOS));
					}
							
				}
				else {
					LOG.info("El NIT de la Persona Contribuyente es invalido");
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
				}
			
		}catch (Exception e) {
		
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_DATOS_NULOS));
			vRespuesta.setOk(false);	
		}
		
		LOG.info("Saliendo recuperaListaEstadoCertificacionSistemasService vRespuesta={}", vRespuesta);
		return vRespuesta;
	}

}
