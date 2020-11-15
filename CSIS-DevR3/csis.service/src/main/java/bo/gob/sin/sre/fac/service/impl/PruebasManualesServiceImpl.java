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

import bo.gob.sin.sre.fac.domain.IPruebasManualesDomain;
import bo.gob.sin.sre.fac.dto.RegistrosPruebasManualesDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaEstaRegistradoGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasManualesDto;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;
import bo.gob.sin.sre.fac.service.IPruebasManualesService;
import bo.gob.sin.str.ccs.cale.domain.ICalendarioDomain;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
public class PruebasManualesServiceImpl implements IPruebasManualesService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PruebasAutomaticasServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private IPruebasManualesDomain iPruebasManualesDomain;

	@Autowired
	private ICalendarioDomain iCalendarioDomain;

	@Override
	public RespuestaListaRegistroPruebasManualesDto obtieneListaPruebasManuales(long pSolicitudId, long pTramiteId,
			long pSistemaId) {
		RespuestaListaRegistroPruebasManualesDto vRespuesta = new RespuestaListaRegistroPruebasManualesDto();
		vRespuesta.setOk(false);

		try {

			List<SreFacRegistrosPruebasManuDetalle> vDetallePruebasEntidad = iPruebasManualesDomain
					.obtieneListaPruebasManuales(pSolicitudId, pTramiteId, pSistemaId);

			vRespuesta.setPruebasManuales(new ArrayList<>());
			if (!vDetallePruebasEntidad.isEmpty()) {
				for (SreFacRegistrosPruebasManuDetalle vFila : vDetallePruebasEntidad) {
					RegistrosPruebasManualesDto vRegistroDto = new RegistrosPruebasManualesDto();
					vRegistroDto.setRegistroPruebaManualId(vFila.getRegistroPruebaManualId());
					vRegistroDto.setPruebaManualId(vFila.getPruebaManualId());
					vRegistroDto.setNombre(vFila.getNombre());
					vRegistroDto.setEstadoPruebaId(vFila.getpEstadoPruebaId());
					vRegistroDto.setDescripcion(vFila.getDescripcion());
					vRegistroDto.setObservaciones(vFila.getObservaciones());
					vRespuesta.getPruebasManuales().add(vRegistroDto);

				}
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.LISTADO_PRUEBAS_MANUALES_DESPLEGADO_CORRECTAMENTE));
			} else {
				if (vDetallePruebasEntidad == null) {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_DATOS_NULOS_LISTADO_PRUEBAS_MANUALES));
					vRespuesta.setOk(false);
				} else {
					vRespuesta.setOk(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.NO_RECUPERA_REGISTROS_PRUEBAS_MANUALES));
				}
			}

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.NO_RECUPERA_REGISTROS_PRUEBAS_MANUALES));
		}

		return vRespuesta;
	}

	@Override
	public RespuestaActualizacionGenericoDto modificaObservacionEstado(
			RegistrosPruebasManualesDto pRegistroPruebaManualDto) {
		// COLOCAR EL DTO DE respuesta

		Date vFecha = iCalendarioDomain.getTimeStamp();
		RespuestaActualizacionGenericoDto vRespuestaDto = new RespuestaActualizacionGenericoDto();
		boolean resp = true;

		try {

			SreFacRegistrosPruebasManuales vEntidadPruebasManuales = new SreFacRegistrosPruebasManuales();

			Type type = new TypeToken<SreFacRegistrosPruebasManuales>() {
			}.getType();
			ModelMapper vMapperRegistroObservaciones = new ModelMapper();
			vEntidadPruebasManuales = vMapperRegistroObservaciones.map(pRegistroPruebaManualDto, type);
			vEntidadPruebasManuales.setFechaUltimaModificacion(vFecha);

			boolean vRespuesta = iPruebasManualesDomain.modificaObservacionEstado(vEntidadPruebasManuales);
			if (vRespuesta != true) {
				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.ERROR_PROCESO_MODIFICADO_OBSERVACION_ESTADO_PRUEBA_MANUAL));
				vRespuestaDto.setOk(false);
			} else {

				vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.MODIFICACION_EXITOSA_PRUEBA_MANUAL));
				vRespuestaDto.setOk(true);
			}

			vRespuestaDto.setEstaActualizado(vRespuesta);

			return vRespuestaDto;

		} catch (Exception e) {
			e.printStackTrace();
			vRespuestaDto.setOk(false);
			vRespuestaDto.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_PROCESO_MODIFICADO_OBSERVACION_ESTADO_PRUEBA_MANUAL));
		}
		return vRespuestaDto;

	}

	@Override
	public RespuestaEstaRegistradoGenericoDto verificarPruebasManualesFinalizadas(long pSolicitudId, long pTramiteId,
			long pSistemaId) {
		RespuestaEstaRegistradoGenericoDto vRespuesta = new RespuestaEstaRegistradoGenericoDto();
		vRespuesta.setOk(false);

		try {

			List<SreFacRegistrosPruebasManuDetalle> vDetallePruebasEntidad = iPruebasManualesDomain
					.obtieneListaPruebasManuales(pSolicitudId, pTramiteId, pSistemaId);

			long vFilasNoAprobadas = vDetallePruebasEntidad.stream()
					.filter(x -> x.getpEstadoPruebaId() != ConstFacturacion.ESTADO_PRUEBA_APROBADA).count();
			vRespuesta.setOk(true);
			vRespuesta.setEstaRegistrado(vFilasNoAprobadas == 0);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
			/*TODO (Sergio Ichaso)AGREGAR MENSAJE*/
//			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
//					EnumFacturacionTipoMensaje.RECUPERACION_DATOS_SISTEMAS_ERROR));
		}

		return vRespuesta;
	}
}
