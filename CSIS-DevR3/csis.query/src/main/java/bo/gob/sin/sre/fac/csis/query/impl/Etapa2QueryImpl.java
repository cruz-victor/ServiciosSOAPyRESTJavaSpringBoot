package bo.gob.sin.sre.fac.csis.query.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.csis.query.IEtapa2Query;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.ListaSistemasSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.ModalidadDto;
import bo.gob.sin.sre.fac.dto.ObservacionVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaCalcularPorcentajePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetallePruebasEtapas2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaMatchLogCasosPruebaEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebaSugeridaDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaResumenPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.ResumenPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SistemasSolicitudCertificacionDto;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Repository
public class Etapa2QueryImpl implements IEtapa2Query, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(Etapa2QueryImpl.class);

	private static final int ESTADO_PRUEBA_SOLICITUD_PENDIENTE = 1440;
	private static final String ESTADO_PRUEBA_SOLICITUD_PENDIENTE_DESCRIPCION = "PENDIENTE";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public RespuestaMatchLogCasosPruebaEtapa2Dto matchLogCasosPruebaEtapa2(long pSistemaId, int pDocumentoSectorId) {
		RespuestaMatchLogCasosPruebaEtapa2Dto vRespuestaDto=new RespuestaMatchLogCasosPruebaEtapa2Dto();
		vRespuestaDto.setOk(false);
		int vResultado=0;		
		try {
			String vSql="select sre_recaudaciones.sre_fac_pru_match_log_casos_prueba_etapa_2(:pSistemaId, :pDocumentoSectorId)";			
			SqlParameterSource vParametros=new MapSqlParameterSource().addValue("pSistemaId", pSistemaId).addValue("pDocumentoSectorId", pDocumentoSectorId);			
			vResultado= namedParameterJdbcTemplate.queryForObject(vSql,vParametros, Integer.class);
			vRespuestaDto.setEstado(vResultado);
			vRespuestaDto.setOk(true);
		} catch (Exception e) {
			vRespuestaDto.setEstado(vResultado);			
			vRespuestaDto.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId, pDocumentoSectorId));
		}
					
		return vRespuestaDto;
	}

	@Override
	public RespuestaCalcularPorcentajePruebasEtapa2Dto calcularPorcentajePruebasEtapa2(long pSistemaId, int pDocumentoSectorId) {
		RespuestaCalcularPorcentajePruebasEtapa2Dto vRespuestaDto=new RespuestaCalcularPorcentajePruebasEtapa2Dto();
		vRespuestaDto.setOk(false);
		float vResultado=0f;
		try {
			String vSql="select sre_recaudaciones.sre_fac_pru_calcular_porcentaje_pruebas_etapa_2(:pSistemaId,	:pDocumentoSectorId)";
			SqlParameterSource vParametros=new MapSqlParameterSource().addValue("pSistemaId", pSistemaId).addValue("pDocumentoSectorId", pDocumentoSectorId);
			vResultado= namedParameterJdbcTemplate.queryForObject(vSql, vParametros, Float.class);
			vRespuestaDto.setPorcentaje(vResultado);
			vRespuestaDto.setOk(true);			
		} catch (Exception e) {
			vRespuestaDto.setPorcentaje(vResultado);
			vRespuestaDto.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId, pDocumentoSectorId));
		}		
		return vRespuestaDto;
	}

	@Override
	public RespuestaListaDetallePruebasEtapas2Dto obtenerDetallePruebasEtapa2(long pSistemaId) {
		RespuestaListaDetallePruebasEtapas2Dto vRespuestaDto=new RespuestaListaDetallePruebasEtapas2Dto();
		vRespuestaDto.setOk(false);				
		try {
			List<DetallePruebasEtapa2Dto> vLista=new ArrayList<DetallePruebasEtapa2Dto>();
			String vSql="select logid, casopruebaid, sistemaid, estadomatch, fechaprueba, documentosectoridlog, idrecepcion, casoprueba, documentosectoridprueba, exlnro, exlparametrodeentrada, exlvalorparametro, exltipovalidacion, exlprueba, exlresultadoesperado, exlsolucion, exltipoemision, opcional, totalpruebasesperadas, totalpruebascorrectas, totalpruebasincorrectas, porcentajecasoprueba, checado, exltipoprueba from sre_recaudaciones.sre_fac_pru_obtener_detalle_pruebas_etapa_2(:pSistemaId)";
			RowMapper<DetallePruebasEtapa2Dto> vRowMapper=new BeanPropertyRowMapper<DetallePruebasEtapa2Dto>(DetallePruebasEtapa2Dto.class);
			SqlParameterSource vParametros=new MapSqlParameterSource().addValue("pSistemaId", pSistemaId);		
			vLista=namedParameterJdbcTemplate.query(vSql, vParametros, vRowMapper);			
			vRespuestaDto.setListaDetallePruebas(vLista);
			vRespuestaDto.setOk(true);			
		} catch (Exception e) {
			vRespuestaDto.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}		
		return vRespuestaDto;
	}

	@Override
	public RespuestaPruebaSugeridaDto registrarPruebaSugeridaEtapa2(
			long pSistemaId, 
			String pNro,
			String pTipoPrueba, 
			String pTipoEmision, 
			String pParametroEntrada, 
			String pDocumentoSector,
			String pAccion) {
		
		RespuestaPruebaSugeridaDto vRespuestaDto=new RespuestaPruebaSugeridaDto();
			System.out.println("pSistemaId="+pSistemaId);
			System.out.println("pNro="+pNro);
			System.out.println("pTipoPrueba="+pTipoPrueba);
			System.out.println("pTipoEmision="+pTipoEmision);
			System.out.println("pParametroEntrada="+pParametroEntrada);
			System.out.println("pDocumentoSector="+pDocumentoSector);
			System.out.println("pAccion="+pAccion);
		
		vRespuestaDto.setOk(false);
		int vResultado=0;		
		try {
			String vSql="select sre_recaudaciones.sre_fac_pru_captura_log_casos_prueba_etapa_2_sugerido(:pSistemaId, :pNro, :pTipoPrueba, :pTipoEmision, :pParametroEntrada, :pDocumentoSector, :pAccion)";			
			SqlParameterSource vParametros=new MapSqlParameterSource()
					.addValue("pSistemaId", pSistemaId)
					.addValue("pNro", pNro)
					.addValue("pTipoPrueba", pTipoPrueba)
					.addValue("pTipoEmision", pTipoEmision)
					.addValue("pParametroEntrada", pParametroEntrada)
					.addValue("pDocumentoSector", pDocumentoSector)
					.addValue("pAccion", pAccion);								
			vResultado= namedParameterJdbcTemplate.queryForObject(vSql,vParametros, Integer.class);
			vRespuestaDto.setResultado(vResultado);
			vRespuestaDto.setOk(true);
		} catch (Exception e) {
			vRespuestaDto.setResultado(vResultado);			
			vRespuestaDto.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId, pSistemaId));
		}
					
		return vRespuestaDto;
	}
	
	@Override
	public RespuestaRegistroVerificacionInsituDto obtenerListaVerificacionInsituPorSolicitudCertificacionId(long pSolicitudCertificacionId, long pTipoPrueba) {
		RespuestaRegistroVerificacionInsituDto vRespuesta=  new RespuestaRegistroVerificacionInsituDto();
		List<ObservacionVerificacionInsituDto> vLista1 = new ArrayList<>();
		List<RegistroVerificacionInsituDto> vLista2 = new ArrayList<>();
		try {
			
			String vSql1 = "select obs.observacion_insitu_id as observacionInsituId, obs.observacion as descripcionPrueba " + 
					"from sre_recaudaciones.sre_fac_observaciones_insitu obs where obs.estado_id='AC' order by obs.observacion_insitu_id asc " ;
					RowMapper<ObservacionVerificacionInsituDto> vRowMapper = new BeanPropertyRowMapper<>(ObservacionVerificacionInsituDto.class);
					vLista1 = jdbcTemplate.query(vSql1, vRowMapper);
			
			String vSql2 = "select sol.registro_observacion_insitu_id as registroObservacionInsituId, sol.observacion_insitu_id as observacionInsituId, sol.estado_prueba_id as estadoPruebaId, sol.observacion, " + 
					"(select c.descripcion from str_transversales.str_cps_clasificadores c where c.clasificador_id=sol.estado_prueba_id) as estadoPruebaDescripcion, sol.solicitud_certificacion_id as solicitudCertificacionId " + 
					"from sre_recaudaciones.sre_fac_registros_observaciones_insitu sol where sol.estado_id='AC' and sol.solicitud_certificacion_id= ? " ;
					RowMapper<RegistroVerificacionInsituDto> vRowMapper2 = new BeanPropertyRowMapper<>(RegistroVerificacionInsituDto.class);
					vLista2 = jdbcTemplate.query(vSql2, vRowMapper2, pSolicitudCertificacionId);
					
					List<RegistroVerificacionInsituDto> vLista3 = new ArrayList<>();
					for (ObservacionVerificacionInsituDto dtoOb : vLista1) {
						RegistroVerificacionInsituDto dto = new RegistroVerificacionInsituDto();
						dto.setObservacionInsituId(dtoOb.getObservacionInsituId());
						dto.setDescripcionPrueba(dtoOb.getDescripcionPrueba());
						Optional<RegistroVerificacionInsituDto> match = vLista2.stream().filter(item -> (long)item.getObservacionInsituId()==(long)dtoOb.getObservacionInsituId()).findFirst();
						if (match.isPresent()) {
							dto.setRegistroObservacionInsituId(match.get().getRegistroObservacionInsituId());
							dto.setSolicitudCertificacionId(match.get().getSolicitudCertificacionId());
							dto.setObservacion(match.get().getObservacion());
							dto.setEstadoPruebaId(match.get().getEstadoPruebaId());
							dto.setEstadoPruebaDescripcion(match.get().getEstadoPruebaDescripcion());
						}
						else {
							dto.setSolicitudCertificacionId(pSolicitudCertificacionId);
							dto.setEstadoPruebaId(ESTADO_PRUEBA_SOLICITUD_PENDIENTE);
							dto.setEstadoPruebaDescripcion(ESTADO_PRUEBA_SOLICITUD_PENDIENTE_DESCRIPCION);
						}
						vLista3.add(dto);
					}
					vRespuesta.setLista(vLista3);
					vRespuesta.setOk(true);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitudCertificacionId));
			vRespuesta.setOk(false);
		}
		return vRespuesta;
	}
	
	@Override
	public RespuestaResumenPruebaCertificacionSistemaDto obtenerResumenPruebaCertificacionSistema(long pSistemaId) {
		RespuestaResumenPruebaCertificacionSistemaDto vRespuesta=  new RespuestaResumenPruebaCertificacionSistemaDto();
		List<ResumenPruebaCertificacionSistemaDto> vLista = new ArrayList<>();
		try {
			
			String vSql1 = "select a.prueba_certificacion_sistema_id as pruebaCertificacionSistemaId, a.prueba_etapa_certificacion_id as pruebaEtapaCertificacionId, a.sistema_id as sistemaId, " + 
					"a.fecha_inicio_prueba as fechaInicioPrueba, a.fecha_fin_prueba as fechaFinPrueba, a.etapa_completada as etapaCompletada, b.etapa_certificacion_sistemas_id as etapaCertificacionSistemasId " + 
					"from sre_recaudaciones.sre_fac_pruebas_certificacion_sistemas a inner join sre_recaudaciones.sre_fac_pruebas_etapa_certificacion b on a.prueba_etapa_certificacion_id=b.prueba_etapa_certificacion_id " + 
					"where a.estado_id='AC' and b.estado_id='AC' and sistema_id=? " ;
					RowMapper<ResumenPruebaCertificacionSistemaDto> vRowMapper = new BeanPropertyRowMapper<>(ResumenPruebaCertificacionSistemaDto.class);
					vLista = jdbcTemplate.query(vSql1, vRowMapper, pSistemaId);

					vRespuesta.setLista(vLista);
					vRespuesta.setOk(true);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
			vRespuesta.setOk(false);
		}
		return vRespuesta;
	}
	
	@Override
	public ListaSistemasSolicitudCertificacionDto obtenerListadoSistemasFase2(long pContribuyenteId) 
	{
		ListaSistemasSolicitudCertificacionDto vRespuesta=  new ListaSistemasSolicitudCertificacionDto();
		List<SistemasSolicitudCertificacionDto> vLista = new ArrayList<>();
		
		try 
		{
			
			String vSql ="select	s.sistema_id as sistemaId, " 
								+  "s.nombre_sistema as nombreSistema, " 
								+  "c.solicitud_certificacion_id as solicitudCertificacionId, " 
								+  "c.persona_contribuyente_id as contribuyenteId, "
								+  "c.tramite_id as tramiteId, "
								+  "cl.descripcion as tipoSistema "
								+  "from sre_recaudaciones.sre_fac_sistemas s "
								+  "inner join sre_recaudaciones.sre_fac_solicitudes_certificaciones c on (s.sistema_id = c.sistema_id and (s.estado_id='AC') and (c.estado_id='AC')) "
								+  "inner join str_transversales.str_cps_clasificadores cl on (cl.clasificador_id = s.tipo_sistema_id and cl.estado_id='AC') "
								+  "where c.estado_solicitud_certificacion_id = ? and c.persona_contribuyente_id = ? and s.estado_sistema_id in (?,?) ";
								
			RowMapper<SistemasSolicitudCertificacionDto> vRowMapper = new BeanPropertyRowMapper<>(SistemasSolicitudCertificacionDto.class);
			vLista = jdbcTemplate.query(vSql, vRowMapper, ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO, pContribuyenteId, ConstFacturacion.ESTADO_SISTEMA_INICIADO, ConstFacturacion.ESTADO_SISTEMA_CERTIFICADO);
			
			if(vLista.size()>0)
			{
				for(int i=0; i< vLista.size(); i++)
				{
					SistemasSolicitudCertificacionDto vDato= new SistemasSolicitudCertificacionDto();
					vDato = vLista.get(i);
					
					vLista.set(i, obtenerListadoModalidad(vDato));
				}
				
				vRespuesta.setListaSistemasSolicitudCertificacionDto(vLista);
				vRespuesta.setOk(true);
			}
			else
			{
				vRespuesta.setListaSistemasSolicitudCertificacionDto(new ArrayList<>());
				vRespuesta.setOk(false);
			}
		} 
		catch (Exception e) 
		{
			vRespuesta.setListaSistemasSolicitudCertificacionDto(new ArrayList<>());
			LogExcepcion.registrar(e, LOG, MethodSign.build(pContribuyenteId));
			vRespuesta.setOk(false);
		}
		
		return vRespuesta;
	}
	
	public SistemasSolicitudCertificacionDto obtenerListadoModalidad(SistemasSolicitudCertificacionDto pSolicitud) 
	{
		SistemasSolicitudCertificacionDto vRespuesta = new SistemasSolicitudCertificacionDto();
		vRespuesta = pSolicitud;
		
		long pSistemaId =0L;
		try 
		{
				pSistemaId = pSolicitud.getSistemaId();
				
				List<ModalidadDto> vLista = new ArrayList<>();				
					
				String vSql ="select sc.modalidad_facturacion_id as modalidadId, " 
									+  "cl.descripcion as modalidad " 
									+  "from sre_recaudaciones.sre_fac_sistemas_contribuyentes sc " 
									+  "inner join str_transversales.str_cps_clasificadores cl on (cl.clasificador_id = sc.modalidad_facturacion_id and cl.estado_id='AC')  "							
									+  "where sc.sistema_id = ? ";
									
				RowMapper<ModalidadDto> vRowMapper = new BeanPropertyRowMapper<>(ModalidadDto.class);
				vLista = jdbcTemplate.query(vSql, vRowMapper, pSistemaId);
				
				String vModalidad ="";
				
				for (ModalidadDto modalidadDto : vLista) 
				{
					vModalidad = vModalidad + modalidadDto.getModalidad() + " - ";
				}	
				
				if(vLista.size() > 0)
				{
					vModalidad = vModalidad + "_";				
					vModalidad = vModalidad.replaceAll(" - _", "");
					
					vRespuesta.setModalidad(vModalidad);
					vRespuesta.setModalidadId(vLista.get(0).getModalidadId());
				}
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			vRespuesta = null;
		}
		
		return vRespuesta;
	}
}