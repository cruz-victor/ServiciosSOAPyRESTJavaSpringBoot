package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.domain.IObservacionesInsituDomain;
import bo.gob.sin.sre.fac.domain.IRegistroVerificacionPruebaInsituDomain;
import bo.gob.sin.sre.fac.domain.IRegistrosObservacionesInsituDomain;
import bo.gob.sin.sre.fac.dto.ComponentesArchivosDto;
import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;
import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreRegistroVerificacionPruebaInsitu;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRegistrosObservacionesInsituService;
import bo.gob.sin.str.ccs.cale.domain.ICalendarioDomain;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
public class RegistrosObservacionesInsituServiceImpl implements IRegistrosObservacionesInsituService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrosObservacionesInsituServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private IRegistrosObservacionesInsituDomain IRegistrosObservacionesInsituDomain;
	
	@Autowired
	private IRegistroVerificacionPruebaInsituDomain iRegistroVerificacionPruebaInsituDomain;
	
	@Autowired
	private IObservacionesInsituDomain iObservacionesInsituDomain;

	@Autowired
	private IParametricasDomain iClasificadorDomain;

	@Autowired
	private ICalendarioDomain iCalendarioDomain;

	/*
	 * @Autowired private IUsuarioDomain iUsuarioDomain;
	 */

	@SuppressWarnings("unused")
	@Override
	public RespuestaRegistrosObservacionesInsituDto obtieneListaVerificacionInsitu(long pSolicitudCertificacionId) {

		List<RegistrosObservacionesInsituDto> vListaDto = new ArrayList<RegistrosObservacionesInsituDto>();
		RespuestaRegistrosObservacionesInsituDto vRespListaDto = new RespuestaRegistrosObservacionesInsituDto();
		try {
			List<SreFacRegistrosObservacionesInsitu> vListadoObs = IRegistrosObservacionesInsituDomain
					.obtieneListaVerificacionInsitu(pSolicitudCertificacionId);

			List<ClasificadorDto> vTiposComponenteClasificador = iClasificadorDomain.recuperarClasificadorPorTipo("estado_observacion_insitu_id");

			if (!vListadoObs.isEmpty()) {
				for (SreFacRegistrosObservacionesInsitu vSreFacRegObs : vListadoObs) {

					RegistrosObservacionesInsituDto dto = new RegistrosObservacionesInsituDto();
					dto.setUsuarioRegistroId(vSreFacRegObs.getUsuarioRegistroId());
					dto.setUsuarioUltimaModificacionId(vSreFacRegObs.getUsuarioUltimaModificacionId());
					dto.setSolicitudCertificacionId(vSreFacRegObs.getSolicitudCertificacionId());
					dto.setEstadoOservacionInsituId(vSreFacRegObs.getEstadoOservacionInsituId());
					dto.setObservacion(vSreFacRegObs.getObservacion());
					dto.setFechaVerificacion(vSreFacRegObs.getFechaVerificacion());
					dto.setFechaRegistro(vSreFacRegObs.getFechaRegistro());
					dto.setFechaUltimaModificacion(vSreFacRegObs.getFechaUltimaModificacion());
					dto.setEstadoId(vSreFacRegObs.getEstadoId());
					// Debería ser la linea de abajo, pero entra en conflictos si se jala el pom del
					// equipo de transversales
					// SauUsuario usuario=iUsuarioDomain.recuperarUsuarioIdentidad(null,
					// vSreFacRegObs.getUsuarioRegistroId(), null);
					dto.setDescripcionUsuarioRegistro("Usuario de prueba");
					String vDescripcion = vTiposComponenteClasificador.stream()
							.filter(x -> x.getClasificadorId() == vSreFacRegObs.getEstadoOservacionInsituId())
							.findFirst().get().getDescripcion();
					dto.setDescripcionEstadoObservacionInsitu(vDescripcion);

					vListaDto.add(dto);

				}
				vRespListaDto.setLista(vListaDto);
				vRespListaDto.setOk(true);
				// se comentó porque generaba error
				vRespListaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.VERIFICACION_INSITU_RECUPERADAS));
			} else {
				if (vListadoObs == null) {
					vRespListaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_DATOS_NULOS_LISTADO_VERIFICACIONES_INSITU));
					vRespListaDto.setOk(false);
				} else {
					vRespListaDto.setOk(false);
					vRespListaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_RECUPERA_LISTADO_VERIFICACIONES_INSITU));
				}
			}

		}

		catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespListaDto));
			vRespListaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_RECUPERA_LISTADO_VERIFICACIONES_INSITU));
			vRespListaDto.setOk(false);
		}
		return vRespListaDto;
	}

	@Override
	public RespuestaRegistrosObservacionesInsituDto registroObservacionComponente(
			RegistrosObservacionesInsituDto pRegObservacionComponente) {

		RespuestaRegistrosObservacionesInsituDto vResp = new RespuestaRegistrosObservacionesInsituDto();

		try {
			SreFacRegistrosObservacionesInsitu vEntidadRegObservacionInsitu = new SreFacRegistrosObservacionesInsitu();
			SreFacRegistrosObservacionesInsitu vRespRegObservacionInsitu = new SreFacRegistrosObservacionesInsitu();

			List<RegistrosObservacionesInsituDto> vRespuestaregObservaccionInsituDto = new ArrayList<RegistrosObservacionesInsituDto>();
			Type type = new TypeToken<SreFacRegistrosObservacionesInsitu>() {
			}.getType();

			ModelMapper vMapperRegistroObservaciones = new ModelMapper();
			vEntidadRegObservacionInsitu = vMapperRegistroObservaciones.map(pRegObservacionComponente, type);

			Date vFecha = iCalendarioDomain.getTimeStamp();
			vEntidadRegObservacionInsitu
					.setUsuarioUltimaModificacionId(pRegObservacionComponente.getUsuarioRegistroId());
			vEntidadRegObservacionInsitu.setFechaRegistro(vFecha);
			vEntidadRegObservacionInsitu.setFechaUltimaModificacion(vFecha);
			vEntidadRegObservacionInsitu.setEstadoId("AC");

			vRespRegObservacionInsitu = IRegistrosObservacionesInsituDomain
					.registroObservacionComponente(vEntidadRegObservacionInsitu);

			if (vRespRegObservacionInsitu != null) {

				RegistrosObservacionesInsituDto dto = new RegistrosObservacionesInsituDto();
				dto.setRegistroObservacionInsituId(vRespRegObservacionInsitu.getRegistroObservacionInsituId());
				dto.setUsuarioRegistroId(vRespRegObservacionInsitu.getUsuarioRegistroId());
				dto.setUsuarioUltimaModificacionId(vRespRegObservacionInsitu.getUsuarioUltimaModificacionId());
				dto.setSolicitudCertificacionId(vRespRegObservacionInsitu.getSolicitudCertificacionId());
				dto.setEstadoOservacionInsituId(vRespRegObservacionInsitu.getEstadoOservacionInsituId());
				dto.setObservacion(vRespRegObservacionInsitu.getObservacion());
				dto.setFechaVerificacion(vRespRegObservacionInsitu.getFechaVerificacion());
				dto.setFechaRegistro(vRespRegObservacionInsitu.getFechaRegistro());

				dto.setFechaUltimaModificacion(vRespRegObservacionInsitu.getFechaUltimaModificacion());
				dto.setEstadoId("AC");
				vRespuestaregObservaccionInsituDto.add(dto);
				vResp.setLista(vRespuestaregObservaccionInsituDto);
				vResp.setOk(true);

				vResp.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.REGISTRO_VERIFICACION_INSITU_CORRECTO));

				vResp.setOk(true);
			} else {

				vResp.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.ERROR_REGISTRA_VERIFICACION_REALIZADA_INSITU));

				vResp.setOk(false);

			}

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResp));
			vResp.setOk(false);
			vResp.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_REGISTRA_VERIFICACION_REALIZADA_INSITU));
		}
		return vResp;
	}
	
	/**
	 * Devuelve obtiene Registro ListaRegistro de Observaciones Insitu
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 22/10/2019
	 * @param pSistemaId, pSolicitudCertificacionId
	 * @return Devuelve RespuestaRegistrosObservacionesInsituDto.
	 */
	@Override
	public RespuestaRegistroVerificacionInsituDto obtieneRegistroListaRegistroObservacionesInsitu(Long pSistemaId, Long pSolicitudCertificacionId, Long pUsuarioRegistro)
	{      
		RespuestaRegistroVerificacionInsituDto vRespuestaRegistrosObservacionesInsituDto = new RespuestaRegistroVerificacionInsituDto();
		List<SreFacObservacionesInsitu> listaObservacionesInSitu = new ArrayList<>();
		List<SreRegistroVerificacionPruebaInsitu> vListaSolicitudEntidad = new ArrayList<>();		
		
		try 
		{
			vListaSolicitudEntidad = iRegistroVerificacionPruebaInsituDomain.recuperarListaRegistroObservacionesInSitu(pSolicitudCertificacionId);
			
			if(vListaSolicitudEntidad != null)
			{
				if(vListaSolicitudEntidad.size() <= 0)
				{	
					listaObservacionesInSitu = iObservacionesInsituDomain.recuperaListaObservacionesInSitu(pSistemaId, pSolicitudCertificacionId);
					
					List<SreFacObservacionesInsitu> vListaSolicitudObligatorio = new ArrayList<>();
					List<SreFacObservacionesInsitu> vListaSolicitudOpcional = new ArrayList<>();
							
					vListaSolicitudObligatorio = listaObservacionesInSitu.stream().filter(lista -> lista.getTipoObligatorioId().equals(ConstFacturacion.TIPO_OBLIGATORIO)).collect(Collectors.toList());
					vListaSolicitudOpcional = listaObservacionesInSitu.stream().filter(lista -> lista.getTipoObligatorioId().equals(ConstFacturacion.TIPO_OPCIONAL)).collect(Collectors.toList());
					
					
					//CASO OBLIGATORIO
					
					for (SreFacObservacionesInsitu sreFacObservacionesInsitu : vListaSolicitudObligatorio) 
					{
						RegistroVerificacionInsituDto vSolicitud = new RegistroVerificacionInsituDto();
						
						vSolicitud.setEstadoPruebaDescripcion(ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_PENDIENTE_DESCRIPCION);
						vSolicitud.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_PENDIENTE);
						vSolicitud.setObservacion("");
						vSolicitud.setObservacionInsituId( Long.parseLong(sreFacObservacionesInsitu.getObservacionInsituId()+""));
						vSolicitud.setSolicitudCertificacionId(pSolicitudCertificacionId);
						vSolicitud.setUsuarioRegistroId(pUsuarioRegistro);	
						vSolicitud.setTipoEsquemaId(sreFacObservacionesInsitu.getTipoEsquemaId());
						vSolicitud.setTipoObligatorioId(sreFacObservacionesInsitu.getTipoObligatorioId());	
						
						iRegistroVerificacionPruebaInsituDomain.guardarVisitaInsitu(vSolicitud);
					}
					
					
					//CASO OPCIONAL aleatoriamente
					
					 Collections.shuffle(vListaSolicitudOpcional);
					
					int numeroMaximo = ConstFacturacion.NUMERO_PRUEBAS_OPCIONALES;	
					
					for (SreFacObservacionesInsitu sreFacObservacionesInsitu : vListaSolicitudOpcional) 
					{	
						if(numeroMaximo == 0)
						{
							break;
						}
						else
						{
							numeroMaximo--;
							
							RegistroVerificacionInsituDto vSolicitud = new RegistroVerificacionInsituDto();					
							
							vSolicitud.setEstadoPruebaDescripcion(ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_PENDIENTE_DESCRIPCION);
							vSolicitud.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_SOLICITUD_PENDIENTE);
							vSolicitud.setObservacion("");
							vSolicitud.setObservacionInsituId( Long.parseLong(sreFacObservacionesInsitu.getObservacionInsituId()+""));
							vSolicitud.setSolicitudCertificacionId(pSolicitudCertificacionId);
							vSolicitud.setUsuarioRegistroId(pUsuarioRegistro);	
							vSolicitud.setTipoEsquemaId(sreFacObservacionesInsitu.getTipoEsquemaId());
							vSolicitud.setTipoObligatorioId(sreFacObservacionesInsitu.getTipoObligatorioId());
							
							iRegistroVerificacionPruebaInsituDomain.guardarVisitaInsitu(vSolicitud);
						}
					}
					
					vListaSolicitudEntidad = iRegistroVerificacionPruebaInsituDomain.recuperarListaRegistroObservacionesInSitu(pSolicitudCertificacionId);
				}
				
				if(listaObservacionesInSitu.size()>0)
				{
					vRespuestaRegistrosObservacionesInsituDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.REGISTRO_VERIFICACION_INSITU_CORRECTO));
					vRespuestaRegistrosObservacionesInsituDto.setOk(true);
				}
				else
				{
					vRespuestaRegistrosObservacionesInsituDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.ERROR_REGISTRA_VERIFICACION_REALIZADA_INSITU));
					vRespuestaRegistrosObservacionesInsituDto.setOk(false);
				}
			}
			else
			{
				vRespuestaRegistrosObservacionesInsituDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.ERROR_REGISTRA_VERIFICACION_REALIZADA_INSITU));
				vRespuestaRegistrosObservacionesInsituDto.setLista(new ArrayList<>());
				vRespuestaRegistrosObservacionesInsituDto.setOk(false);
			}
			
			List<RegistroVerificacionInsituDto> vLista = new ArrayList<>();
			
			Type type = new TypeToken<List<RegistroVerificacionInsituDto>>() {}.getType();
			
			ModelMapper vMapper = new ModelMapper();
			vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			vLista = vMapper.map(vListaSolicitudEntidad, type);		
			vRespuestaRegistrosObservacionesInsituDto.setLista(vLista);
			
		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitudCertificacionId));
			vRespuestaRegistrosObservacionesInsituDto.setOk(false);
			vRespuestaRegistrosObservacionesInsituDto.setLista(new ArrayList<>());
			vRespuestaRegistrosObservacionesInsituDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.ERROR_REGISTRA_VERIFICACION_REALIZADA_INSITU));
		}
		
		return vRespuestaRegistrosObservacionesInsituDto;
	}
}
