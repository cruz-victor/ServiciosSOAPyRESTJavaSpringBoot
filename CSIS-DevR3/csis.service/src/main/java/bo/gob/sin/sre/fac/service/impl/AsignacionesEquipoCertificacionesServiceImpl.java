package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IAsignacionesCertificacionesABMDomain;
import bo.gob.sin.sre.fac.domain.IAsignacionesCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesDto;
import bo.gob.sin.sre.fac.dto.AsignacionesCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.EquiposCertificacionesDto;
import bo.gob.sin.sre.fac.dto.EquiposCertificacionesListaDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.model.SreAsignacionesCertificaciones;
import bo.gob.sin.sre.fac.model.SreEquiposCertificaciones;
import bo.gob.sin.sre.fac.service.IAsignacionesEquipoCertificacionesService;
import bo.gob.sin.str.ccs.cale.domain.ICalendarioDomain;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Transactional
@Service
public class AsignacionesEquipoCertificacionesServiceImpl implements IAsignacionesEquipoCertificacionesService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AsignacionesEquipoCertificacionesServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private ICalendarioDomain iCalendarioDomain;

	@Autowired
	private IAsignacionesCertificacionesABMDomain iAsignacionesCertificacionesABMDomain;

	@Autowired
	private IAsignacionesCertificacionesConsultaDomain iAsignacionesCertificacionesConsultaDomain;

	@Override
	public RespuestaEstaRegistradoGenericoDto registrarAsignacionEquipoCertificacion(
			AsignacionesCertificacionesListaDto pAsignaciones) {
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		boolean vRespuestaValidacion = false;
		Date vFecha = iCalendarioDomain.getTimeStamp();
		pAsignaciones.getAsignacionesCertificacionesListaDto().forEach(fila -> {
			fila.setFechaRegistro(vFecha);
			fila.setFechaAsignacion(vFecha);
			fila.setFechaUltimaModificacion(vFecha);
			fila.setEstadoAsignacionCertificacionId(ConstFacturacion.ESTADO_ASINGACION_EQUIPO_CERTIFICACION_ASIGNADO);
			fila.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
		});

		List<SreAsignacionesCertificaciones> vAsignacionesListaEntidad = new ArrayList<>();

		Type listType = new TypeToken<List<SreAsignacionesCertificaciones>>() {
		}.getType();
		ModelMapper vListaSolicitudCertificacion = new ModelMapper();
		vListaSolicitudCertificacion.getConfiguration().setAmbiguityIgnored(true);
		vAsignacionesListaEntidad = vListaSolicitudCertificacion
				.map(pAsignaciones.getAsignacionesCertificacionesListaDto(), listType);

		try {
			vRespuestaValidacion = iAsignacionesCertificacionesABMDomain
					.registrarAsignacionEquipoCertificacion(vAsignacionesListaEntidad);

			if (!vRespuestaValidacion) {
//				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
//						EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
			}

			vRespuesta.setOk(true);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pAsignaciones));
			vRespuesta.setOk(false);
		}

		return vRespuesta;
	}

	@Override
	public AsignacionesCertificacionesListaDto obtenerAsignacionEquipoCertificacionPorTramite(long pTramite) {
		AsignacionesCertificacionesListaDto vRespuesta = new AsignacionesCertificacionesListaDto();
		List<SreAsignacionesCertificaciones> vEntidadAsignacionesLista = new ArrayList<>();

		try {
			vEntidadAsignacionesLista = iAsignacionesCertificacionesConsultaDomain
					.obtenerAsignacionesEquipoCertificacionPorTramite(pTramite);
			if (vEntidadAsignacionesLista.isEmpty()) {
				vRespuesta.setOk(true);
//				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
//						EnumFacturacionTipoMensaje.RECUPERACION_DATOS_TRAMITE_VACIO));
				return vRespuesta;
			}

			Type listType = new TypeToken<List<AsignacionesCertificacionesDto>>() {
			}.getType();
			ModelMapper vListaSolicitudCertificacion = new ModelMapper();
			vListaSolicitudCertificacion.getConfiguration().setAmbiguityIgnored(true);
			vRespuesta.setAsignacionesCertificacionesListaDto(
					vListaSolicitudCertificacion.map(vEntidadAsignacionesLista, listType));
			vRespuesta.setOk(true);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
		}
		return vRespuesta;
	}

	@Override
	public RespuestaEstaRegistradoGenericoDto modificarAsignacionEquipoCertificacion(
			AsignacionesCertificacionesListaDto pAsignacionesCertificacionesDto) {
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		boolean vRespuestaValidacion = false;
		Date vFecha = iCalendarioDomain.getTimeStamp();
		pAsignacionesCertificacionesDto.getAsignacionesCertificacionesListaDto().forEach(fila -> {
			fila.setFechaRegistro(vFecha);
			fila.setFechaAsignacion(vFecha);
			fila.setFechaUltimaModificacion(vFecha);
			fila.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
		});

		List<SreAsignacionesCertificaciones> vAsignacionesListaEntidad = new ArrayList<>();

		Type listType = new TypeToken<List<SreAsignacionesCertificaciones>>() {
		}.getType();
		ModelMapper vListaSolicitudCertificacion = new ModelMapper();
		vListaSolicitudCertificacion.getConfiguration().setAmbiguityIgnored(true);
		vAsignacionesListaEntidad = vListaSolicitudCertificacion
				.map(pAsignacionesCertificacionesDto.getAsignacionesCertificacionesListaDto(), listType);

		try {
			vRespuestaValidacion = iAsignacionesCertificacionesABMDomain
					.modificarAsignacionEquipoCertificacion(vAsignacionesListaEntidad);

			if (!vRespuestaValidacion) {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_CERTIFICACION_ERROR));
			}

			vRespuesta.setOk(true);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pAsignacionesCertificacionesDto));
			vRespuesta.setOk(false);
		}

		return vRespuesta;
	}

	@Override
	public EquiposCertificacionesListaDto obtenerEquipoCertificacion(EquiposCertificacionesDto pEquipoCertificacion) {
		EquiposCertificacionesListaDto vRespuesta = new EquiposCertificacionesListaDto();

		try {

			Type listType = new TypeToken<SreEquiposCertificaciones>() {
			}.getType();
			ModelMapper vListaSolicitudCertificacion = new ModelMapper();
			vListaSolicitudCertificacion.getConfiguration().setAmbiguityIgnored(true);
			SreEquiposCertificaciones vEquipoCertificacion = vListaSolicitudCertificacion.map(pEquipoCertificacion,
					listType);

			List<SreEquiposCertificaciones> vEntidadAsignacionesLista = new ArrayList<>();
			vEntidadAsignacionesLista = iAsignacionesCertificacionesConsultaDomain
					.obtenerEquipoCertificacion(vEquipoCertificacion);

			if (vEntidadAsignacionesLista.isEmpty()) {
				vRespuesta.setOk(true);
//				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
//						EnumFacturacionTipoMensaje.RECUPERACION_DATOS_TRAMITE_VACIO));
				return vRespuesta;
			}

			listType = new TypeToken<List<EquiposCertificacionesDto>>() {
			}.getType();
			ModelMapper vListaEquipoCertificacion = new ModelMapper();
			vListaSolicitudCertificacion.getConfiguration().setAmbiguityIgnored(true);
			List<EquiposCertificacionesDto> vEquiposCertificacionListaDto = vListaEquipoCertificacion
					.map(vEntidadAsignacionesLista, listType);
			vRespuesta.setEquiposCertificacionesDto(vEquiposCertificacionListaDto);

			vRespuesta.setOk(true);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
		}
		return vRespuesta;
	}

}
