package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IObservacionesComponentesInsituDomain;
import bo.gob.sin.sre.fac.dto.BitacoraObservacionComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.BitacorasObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituListaDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.model.SreBitacorasObservacionesComponentesInsitu;
import bo.gob.sin.sre.fac.model.SreObservacionesComponentesInsitu;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRegistroVerificacionInsituService;
import bo.gob.sin.str.ccs.cale.domain.ICalendarioDomain;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RegistroVerificacionInsituServiceImpl implements IRegistroVerificacionInsituService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroVerificacionInsituServiceImpl.class);

	@Autowired
	private IObservacionesComponentesInsituDomain iObservacionesComponentesInsituDomain;

	@Autowired
	private IParametricasDomain iClasificadorDomain;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private ICalendarioDomain iCalendarioDomain;

	@Override
	public RegistroObservacionesComponentesInsituListaDto obtenerObservacionesComponentesInsituPorCodigoCertificacion(
			long pCodigoCertificacion) {

		RegistroObservacionesComponentesInsituListaDto vRespuesta = new RegistroObservacionesComponentesInsituListaDto();
		vRespuesta.setOk(false);
		List<ClasificadorDto> vTiposComponenteClasificador = iClasificadorDomain.recuperarClasificadorPorTipo("tipo_componente_id");

		List<ClasificadorDto> vEstadosPruebasClasificador = iClasificadorDomain
				.recuperarClasificadorPorTipo("estado_prueba_id");

		try {
			List<SreObservacionesComponentesInsitu> vSreObservacionesComponentesInsituLista = iObservacionesComponentesInsituDomain
					.obtenerObservacionesComponentesInsituPorCodigoCertificacion(pCodigoCertificacion);

			Type listType = new TypeToken<List<RegistroObservacionesComponentesInsituDto>>() {
			}.getType();
			ModelMapper vListaSolicitudCertificacion = new ModelMapper();
			vListaSolicitudCertificacion.getConfiguration().setAmbiguityIgnored(true);
			vRespuesta.setRegistrosObservacionesComponentesInsituDto(
					vListaSolicitudCertificacion.map(vSreObservacionesComponentesInsituLista, listType));

			if (!vRespuesta.getRegistrosObservacionesComponentesInsituDto().isEmpty()) {
				vRespuesta.getRegistrosObservacionesComponentesInsituDto().forEach(fila -> {
					fila.getBitacorasObservacionesComponentesInsitu().sort(
							Comparator.comparing(BitacoraObservacionComponentesInsituDto::getFechaRegistro).reversed());

					String vDescripcion = vTiposComponenteClasificador.stream()
							.filter(x -> x.getClasificadorId() == fila.getTipoComponenteId()).findFirst().get()
							.getDescripcion();
					fila.setTipoComponenteDescripcion(vDescripcion);

					vDescripcion = vEstadosPruebasClasificador.stream()
							.filter(x -> x.getClasificadorId() == fila.getEstadoPruebaId()).findFirst().get()
							.getDescripcion();

					fila.setEstadoPruebaDescripcion(vDescripcion);
				});

				vRespuesta.getRegistrosObservacionesComponentesInsituDto().sort(
						Comparator.comparing(RegistroObservacionesComponentesInsituDto::getTipoComponenteDescripcion));
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.VERIFICACION_INSITU_RECUPERADAS));

			} else {
				/* TODO DEFINIR MENSAJE */
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.ERROR_RECUPERAR_OBSERVACIONES_INSITU_SOLICITUD_CERTIFICACION));
			}
		} catch (Exception e) {
			vRespuesta.setOk(false);
			LOG.info("Error al realizar la obtencion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_RECUPERAR_OBSERVACIONES_INSITU_SOLICITUD_CERTIFICACION));
		}

		vRespuesta.setOk(true);
		return vRespuesta;
	}

	@Override
	public RegistroObservacionesComponentesInsituDto modificarObservacionInSitu(
			RegistroObservacionesComponentesInsituDto pObservacionInsitu) {

		RegistroObservacionesComponentesInsituDto vRespuesta = new RegistroObservacionesComponentesInsituDto();
		vRespuesta.setOk(false);

		SreObservacionesComponentesInsitu vEntidad = new SreObservacionesComponentesInsitu();
		Type type = new TypeToken<SreObservacionesComponentesInsitu>() {
		}.getType();
		ModelMapper vMapperRegistroObservaciones = new ModelMapper();
		vEntidad = vMapperRegistroObservaciones.map(pObservacionInsitu, type);
		try {
			Date vFecha = iCalendarioDomain.getTimeStamp();
			vEntidad.setFechaUltimaModificacion(vFecha);
			iObservacionesComponentesInsituDomain.modificarObservacionInSitu(vEntidad);
			pObservacionInsitu.setObservacionComponenteInsituId(vEntidad.getObservacionComponenteInsituId());

			Type type2 = new TypeToken<RegistroObservacionesComponentesInsituDto>() {
			}.getType();
			ModelMapper vMapperRegistroObservacionesDto = new ModelMapper();
			vRespuesta = vMapperRegistroObservacionesDto.map(vEntidad, type2);

			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.REALIZACION_MODIFICACION_VERIFICACION_INSITU));
			vRespuesta.setOk(true);
		} catch (Exception e) {
			LOG.info("Error al modificar datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_MODIFICAR_VERIFICACION_INSITU_CODIGO_CERTIFICACION));
		}

		return vRespuesta;
	}

	@Override
	public RegistroObservacionesComponentesInsituDto registrarObservacionInSitu(
			RegistroObservacionesComponentesInsituDto pObservacionInsitu) {

		RegistroObservacionesComponentesInsituDto vRespuesta = new RegistroObservacionesComponentesInsituDto();
		vRespuesta.setOk(false);
		SreObservacionesComponentesInsitu vEntidad = new SreObservacionesComponentesInsitu();
		Type type = new TypeToken<SreObservacionesComponentesInsitu>() {
		}.getType();
		ModelMapper vMapperRegistroObservaciones = new ModelMapper();
		vEntidad = vMapperRegistroObservaciones.map(pObservacionInsitu, type);
		try {
			iObservacionesComponentesInsituDomain.registrarObservacionInSitu(vEntidad);
			pObservacionInsitu.setObservacionComponenteInsituId(vEntidad.getObservacionComponenteInsituId());
			vRespuesta.setOk(true);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.REGISTRO_VERIFICACION_INSITU_CORRECTO));
		} catch (Exception e) {
			LOG.info("Error en el guardado de datos");
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_REGISTRA_VERIFICACION_INSITU_CODIGO_CERTIFICACION));
		}

		return vRespuesta;
	}

	@Override
	public BitacoraObservacionComponentesInsituDto registrarBitacoraObservacionInSitu(
			BitacoraObservacionComponentesInsituDto pBitacoraObservacionesComponentesInsitu) {
		Date vFecha = iCalendarioDomain.getTimeStamp();
		BitacoraObservacionComponentesInsituDto vRespuesta = new BitacoraObservacionComponentesInsituDto();
		vRespuesta.setOk(false);
		SreBitacorasObservacionesComponentesInsitu vEntidad = new SreBitacorasObservacionesComponentesInsitu();
		try {
			Type listType = new TypeToken<SreBitacorasObservacionesComponentesInsitu>() {
			}.getType();
			ModelMapper vBitacoraMapper = new ModelMapper();
			vBitacoraMapper.getConfiguration().setAmbiguityIgnored(true);
			vEntidad = vBitacoraMapper.map(pBitacoraObservacionesComponentesInsitu, listType);
			vEntidad.setFechaRegistro(vFecha);
			vEntidad.setFechaUltimaModificacion(vFecha);
			vEntidad.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);

			iObservacionesComponentesInsituDomain.registrarBitacoraObservacionInSitu(vEntidad);
			vRespuesta = pBitacoraObservacionesComponentesInsitu;
			vRespuesta.setBitacoraObservacionComponenteInsituId(vEntidad.getBitacoraObservacionComponenteInsituId());
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.REGISTRO_VERIFICACION_INSITU_CORRECTO));
			vRespuesta.setOk(true);
		} catch (Exception e) {
			/* TODO Mensaje error de no guardado de bitacora */
			LOG.info("Error en el guardado de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_REGISTRAR_BITACORA_VERIFICACION_INSITU_CODIGO_CERTIFICACION));
		}

		return vRespuesta;
	}

	@Override
	public BitacorasObservacionesComponentesInsituDto obtenerDetalleObservacionesComponentesInsituPorCodigoObservacion(
			long pCodigoObservacionComponenteInSitu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroObservacionesComponentesInsituListaDto registrarDatosInicioObservaciones(
			RegistroObservacionesComponentesInsituDto pSolicitudRegistroObservaciones) {

		RegistroObservacionesComponentesInsituListaDto vRespuesta = new RegistroObservacionesComponentesInsituListaDto();
		vRespuesta.setOk(false);

		Date vFecha = iCalendarioDomain.getTimeStamp();
		try {
			vRespuesta = this.obtenerObservacionesComponentesInsituPorCodigoCertificacion(
					pSolicitudRegistroObservaciones.getSolicitudCertificacionId());
			if (vRespuesta.getRegistrosObservacionesComponentesInsituDto().isEmpty()) {

				List<ClasificadorDto> vTiposComponenteClasificador = iClasificadorDomain
						.recuperarClasificadorPorTipo("tipo_componente_id");

				vTiposComponenteClasificador.forEach(filaClasificador -> {
					SreObservacionesComponentesInsitu vEntidadObservacionInsitu = new SreObservacionesComponentesInsitu();
					vEntidadObservacionInsitu.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
					vEntidadObservacionInsitu.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_PENDIENTE);
					vEntidadObservacionInsitu.setTipoComponenteId(filaClasificador.getClasificadorId());
					vEntidadObservacionInsitu
							.setSolicitudCertificacionId(pSolicitudRegistroObservaciones.getSolicitudCertificacionId());
					vEntidadObservacionInsitu
							.setUsuarioRegistroId(pSolicitudRegistroObservaciones.getUsuarioRegistroId());
					vEntidadObservacionInsitu.setUsuarioUltimaModificacionId(
							pSolicitudRegistroObservaciones.getUsuarioUltimaModificacionId());
					vEntidadObservacionInsitu.setFechaRegistro(vFecha);
					vEntidadObservacionInsitu.setFechaUltimaModificacion(vFecha);
					iObservacionesComponentesInsituDomain.registrarObservacionInSitu(vEntidadObservacionInsitu);

				});

				vRespuesta = this.obtenerObservacionesComponentesInsituPorCodigoCertificacion(
						pSolicitudRegistroObservaciones.getSolicitudCertificacionId());
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.CREACION_INICIAL_COMPONENTES_CORRECTA));
			}
			/* TODO agregar mensaje */
			vRespuesta.setOk(true);
		} catch (Exception e) {
			LOG.info("Error en la verificacion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_REGISTRAR_COMPONENTES_INICIALES_INSITU_SOLICITUD_CERTIFICACION));
		}

		return vRespuesta;
	}

	@Override
	public RespuestaEstaRegistradoGenericoDto verificarObservacionComponenteInSituAprobadas(long pCodigoCertificacion) {
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		vRespuesta.setOk(false);
		try {

			List<SreObservacionesComponentesInsitu> vSreObservacionesComponentesInsituLista = iObservacionesComponentesInsituDomain
					.obtenerObservacionesComponentesInsituPorCodigoCertificacion(pCodigoCertificacion);

			int cantidadTodosComponentes = vSreObservacionesComponentesInsituLista.size();
			vSreObservacionesComponentesInsituLista = vSreObservacionesComponentesInsituLista.stream()
					.filter(x -> x.getEstadoPruebaId() == ConstFacturacion.ESTADO_PRUEBA_APROBADA)
					.collect(Collectors.toList());
			int cantidadAprobados = vSreObservacionesComponentesInsituLista.size();

			if (cantidadTodosComponentes > 0) {
				vRespuesta.setEstaRegistrado(cantidadTodosComponentes == cantidadAprobados);
			} else {
				/* TODO agregar mensaje */
				// vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
				vRespuesta.setEstaRegistrado(false);
			}

			vRespuesta.setOk(true);

		} catch (Exception e) {
			vRespuesta.setOk(false);
			LOG.info("Error al realizar la obtencion de datos.");
			/* TODO agregar mensaje */
			// vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.REGISTRO_HUELLA_SISTEMA_ERROR));
		}

		vRespuesta.setOk(true);
		return vRespuesta;
	}

	@Override
	public RespuestaEstaRegistradoGenericoDto verificarContribuyenteRequiereVisitaInsitu(Long pNit) {
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		vRespuesta.setOk(false);
		try {
			vRespuesta.setEstaRegistrado(
					iObservacionesComponentesInsituDomain.verificarContribuyenteRequiereVisitaInsitu(pNit));
			vRespuesta.setOk(true);
		} catch (Exception e) {
			vRespuesta.setOk(false);
			LOG.info("Error al verificar contirbuyente requiere visita insitu.");
		}
		return vRespuesta;
	}

}
