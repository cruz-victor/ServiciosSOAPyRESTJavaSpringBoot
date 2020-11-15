package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;
import bo.gob.sin.sre.fac.dao.IPruebasAutomaticasDao;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasAutomaticasConsultaDomain;
import bo.gob.sin.sre.fac.dto.RegistrosPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaTamanioGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;
import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRegistroPruebasAutomaticasConsultaService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RegistroPruebasAutomaticasConsultaServiceImpl implements IRegistroPruebasAutomaticasConsultaService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PruebasAutomaticasServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	@Autowired
	private IParametricasDomain iClasificadorDomain;
	@Autowired
	private IPruebasAutomaticasDao iPruebasAutomaticasDao;

	@Autowired
	private IRegistroPruebasAutomaticasConsultaDomain iRegistroConsultaPruebasAutomaticasDomain;

	@Override
	public RespuestaListaRegistroPruebasAutomaticasDto obtieneListadoPruebasAutomaticas(long pSolicitudId,
			long pTramiteId, long pSistemaId) {
		RespuestaListaRegistroPruebasAutomaticasDto vRespuesta = new RespuestaListaRegistroPruebasAutomaticasDto();
		List<RegistrosPruebasAutomaticasDto> listadoDTO = new ArrayList<RegistrosPruebasAutomaticasDto>();
		vRespuesta.setOk(false);

		try {
			List<SreFacRegistrosPruebasAutomaticas> listaRecuperadaentidad = iRegistroConsultaPruebasAutomaticasDomain
					.obtieneListadoPruebasAutomaticas(pSolicitudId, pTramiteId, pSistemaId);

			if (!listaRecuperadaentidad.isEmpty()) {
				for (SreFacRegistrosPruebasAutomaticas vSreFacReg : listaRecuperadaentidad) {

					RegistrosPruebasAutomaticasDto vRegistroDto = new RegistrosPruebasAutomaticasDto();

					vRegistroDto.setRegistroPruebaAutomaticaId(vSreFacReg.getRegistroPruebaAutomaticaId());
					vRegistroDto.setUsuarioRegistroId(vSreFacReg.getUsuarioRegistroId());
					vRegistroDto.setUsuarioUltimaModficacionId(vSreFacReg.getUsuarioUltimaModficacionId());
					vRegistroDto.setSolicitudCertificacionId(vSreFacReg.getSolicitudCertificacionId());
					vRegistroDto.setTramiteId(vSreFacReg.getTramiteId());
					vRegistroDto.setPruebaAutomaticaId(vSreFacReg.getPruebaAutomaticaId());
					vRegistroDto.setSistemaId(vSreFacReg.getSistemaId());
					vRegistroDto.setEstadoPruebaId(vSreFacReg.getEstadoPruebaId());
					vRegistroDto.setCantidadIntento(vSreFacReg.getCantidadIntento());
					vRegistroDto.setCantidadCorrectos(vSreFacReg.getCantidadCorrectos());
					vRegistroDto.setCantidadErrores(vSreFacReg.getCantidadErrores());
					vRegistroDto.setPorcentajeCorrectos(vSreFacReg.getPorcentajeCorrectos());
					vRegistroDto.setPorcentajeErrores(vSreFacReg.getPorcentajeErrores());
					vRegistroDto.setFechaInicio(vSreFacReg.getFechaInicio());
					vRegistroDto.setFechaFin(vSreFacReg.getFechaFin());
					vRegistroDto.setFechaRegistro(vSreFacReg.getFechaRegistro());
					vRegistroDto.setFechaUltimaModificacion(vSreFacReg.getFechaUltimaModificacion());
					vRegistroDto.setEstadoId(vSreFacReg.getEstadoId());
					SrePruebasAutomaticas vPruebaAutomatica = iPruebasAutomaticasDao
							.obtenerPruebasAutomaticasPorId(vSreFacReg.getPruebaAutomaticaId());
					vRegistroDto.setDescripcionPruebaAuto(vPruebaAutomatica.getDescripcion());

					ClasificadorDto vClasificadorModalidad = iClasificadorDomain.obtenerDatosParametrica(vSreFacReg.getEstadoPruebaId());
					vRegistroDto.setDescripcionEstadoPruebaAuto(vClasificadorModalidad.getDescripcion());

					listadoDTO.add(vRegistroDto);

				}
				vRespuesta.setPruebasAutomaticas(listadoDTO);
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.LISTADO_PRUEBAS_AUTOMATICAS_DESPLEGADO_CORRECTAMENTE));

			} else {

				if (listaRecuperadaentidad == null) {

					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_DATOS_NULOS_LISTADO_PRUEBAS_AUTOMATICAS));

					vRespuesta.setOk(false);
				} else {
					vRespuesta.setOk(false);

					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.NO_RECUPERA_REGISTROS_PRUEBAS_AUTOMATICAS));

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

	@SuppressWarnings("unused")
	@Override
	public RespuestaListaRegistroPruebasAutomaticasDto obtieneListaPaginadaPruebasAutomaticas(
			SolicitudPruebasSistemasDto pSolicitudPruebasAutomaticas) {
		RespuestaListaRegistroPruebasAutomaticasDto vRespuesta = new RespuestaListaRegistroPruebasAutomaticasDto();
		List<RegistrosPruebasAutomaticasDto> listadoDTO = new ArrayList<RegistrosPruebasAutomaticasDto>();
		vRespuesta.setOk(false);

		try {
			List<SreFacRegistrosPruebasAutomaticas> listaRecuperadaentidad = iRegistroConsultaPruebasAutomaticasDomain
					.obtieneListaPaginadaPruebasAutomaticas(pSolicitudPruebasAutomaticas.getSolicitudId(),
							pSolicitudPruebasAutomaticas.getTramiteId(), pSolicitudPruebasAutomaticas.getSistemaId(),
							pSolicitudPruebasAutomaticas.getPrimerRegistro(),
							pSolicitudPruebasAutomaticas.getTamanioPagina(),
							pSolicitudPruebasAutomaticas.getCampoOrden(), pSolicitudPruebasAutomaticas.getAscendente(),
							pSolicitudPruebasAutomaticas.getFiltros());

			List<ClasificadorDto> vClasificadorModalidad = iClasificadorDomain.recuperarClasificadorPorTipo("estado_prueba_id");

			if (!listaRecuperadaentidad.isEmpty()) {
				for (SreFacRegistrosPruebasAutomaticas vSreFacReg : listaRecuperadaentidad) {

					RegistrosPruebasAutomaticasDto vRegistroDto = new RegistrosPruebasAutomaticasDto();

					vRegistroDto.setRegistroPruebaAutomaticaId(vSreFacReg.getRegistroPruebaAutomaticaId());
					vRegistroDto.setUsuarioRegistroId(vSreFacReg.getUsuarioRegistroId());
					vRegistroDto.setUsuarioUltimaModficacionId(vSreFacReg.getUsuarioUltimaModficacionId());
					vRegistroDto.setSolicitudCertificacionId(vSreFacReg.getSolicitudCertificacionId());
					vRegistroDto.setTramiteId(vSreFacReg.getTramiteId());
					vRegistroDto.setPruebaAutomaticaId(vSreFacReg.getPruebaAutomaticaId());
					vRegistroDto.setSistemaId(vSreFacReg.getSistemaId());
					vRegistroDto.setEstadoPruebaId(vSreFacReg.getEstadoPruebaId());
					vRegistroDto.setCantidadIntento(vSreFacReg.getCantidadIntento());
					vRegistroDto.setCantidadCorrectos(vSreFacReg.getCantidadCorrectos());
					vRegistroDto.setCantidadErrores(vSreFacReg.getCantidadErrores());
					vRegistroDto.setPorcentajeCorrectos(vSreFacReg.getPorcentajeCorrectos());
					vRegistroDto.setPorcentajeErrores(vSreFacReg.getPorcentajeErrores());
					vRegistroDto.setFechaInicio(vSreFacReg.getFechaInicio());
					vRegistroDto.setFechaFin(vSreFacReg.getFechaFin());
					vRegistroDto.setFechaRegistro(vSreFacReg.getFechaRegistro());
					vRegistroDto.setFechaUltimaModificacion(vSreFacReg.getFechaUltimaModificacion());
					vRegistroDto.setEstadoId(vSreFacReg.getEstadoId());

					SrePruebasAutomaticas vPruebaAutomatica = iPruebasAutomaticasDao
							.obtenerPruebasAutomaticasPorId(vSreFacReg.getPruebaAutomaticaId());
					vRegistroDto.setDescripcionPruebaAuto(vPruebaAutomatica.getDescripcion());

					String vDescripcion = vClasificadorModalidad.stream()
							.filter(x -> x.getClasificadorId() == vSreFacReg.getEstadoPruebaId()).findFirst().get()
							.getDescripcion();
					vRegistroDto.setDescripcionEstadoPruebaAuto(vDescripcion);
					listadoDTO.add(vRegistroDto);
				}
				vRespuesta.setPruebasAutomaticas(listadoDTO);
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.LISTADO_PRUEBAS_AUTOMATICAS_DESPLEGADO_CORRECTAMENTE));

			} else {

				if (listaRecuperadaentidad == null) {

					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_DATOS_NULOS_LISTADO_PRUEBAS_AUTOMATICAS));

					vRespuesta.setOk(false);
				} else {
					vRespuesta.setOk(false);

					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.NO_RECUPERA_REGISTROS_PRUEBAS_AUTOMATICAS));

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
	public RespuestaTamanioGenericoDto obtieneTotalRegistrosPruebasAutomaticas(
			SolicitudPruebasSistemasDto pSolicitudPruebasAutomaticas) {
		RespuestaTamanioGenericoDto vResp = new RespuestaTamanioGenericoDto();
		Long total = null;
		try {
			total = iRegistroConsultaPruebasAutomaticasDomain.obtieneTotalRegistrosPruebasAutomaticas(
					pSolicitudPruebasAutomaticas.getSolicitudId(), pSolicitudPruebasAutomaticas.getTramiteId(),
					pSolicitudPruebasAutomaticas.getSistemaId(), pSolicitudPruebasAutomaticas.getFiltros());
			vResp.setTamanioLista(total);
			vResp.setOk(true);
			vResp.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.LISTADO_PRUEBAS_AUTOMATICAS_DESPLEGADO_CORRECTAMENTE));
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResp));
			vResp.setOk(false);

			vResp.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.NO_RECUPERA_REGISTROS_PRUEBAS_AUTOMATICAS));

		}
		return vResp;
	}

}
