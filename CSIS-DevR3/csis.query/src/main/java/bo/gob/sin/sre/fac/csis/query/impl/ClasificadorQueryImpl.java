package bo.gob.sin.sre.fac.csis.query.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.csis.query.IClasificadorQuery;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cps.clas.model.StrCpsClasificador;

@Service
@Repository
public class ClasificadorQueryImpl implements IClasificadorQuery, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(ClasificadorQueryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//EJEMPLO
	@Override
	public List<StrCpsClasificador> listarClasificadorPorTipo(String tipoClasificador) {

		List<StrCpsClasificador> vListaClasificador = new ArrayList<StrCpsClasificador>();
		try {
			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores c WHERE c.tipo_clasificador = ?"
					+ " AND c.estado_id = 'AC'";

			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
					StrCpsClasificador.class);

			vListaClasificador = jdbcTemplate.query(vSql, vRowMapper, tipoClasificador);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(tipoClasificador));
		}

		return vListaClasificador;
	}

//	EJEMPLOS ELIMINARLOS CON EL TIEMPO
	
	
	
//	@Override
//	public StrCpsClasificador recuperarClasificadorPorId(Integer idClasificador) {
//
//		StrCpsClasificador vClasificador = null;
//		try {
//			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores c WHERE c.clasificador_id = ?";
//
//			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
//					StrCpsClasificador.class);
//
//			if (idClasificador != null)
//				vClasificador = jdbcTemplate.queryForObject(vSql, vRowMapper, idClasificador);
//
//		} catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(idClasificador));
//		}
//		return vClasificador;
//	}
//
//	@Override
//	public List<StrCpsClasificador> recuperarClasificadorPorDescripcion(String pDescripcion) {
//		List<StrCpsClasificador> vListaClasificador = new ArrayList<>();
//		
//		try {
//			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores c WHERE c.descripcion = ? "
//					+ " AND c.estado_id = '" + ConstEstado.ESTADO_ACTIVO + "'";
//
//			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
//					StrCpsClasificador.class);
//			
//			if (!pDescripcion.trim().isEmpty()) {
//				vListaClasificador = jdbcTemplate.query(vSql, vRowMapper, pDescripcion);
//			}
//
//		} catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(pDescripcion));
//		}
//
//		return vListaClasificador;
//	}
//
//	@Override
//	public List<StrCpsClasificador> listarClasificadorPorEstado(String pEstado) {
//
//		List<StrCpsClasificador> vListaClasificadores = new ArrayList<>();
//		
//		try {
//			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores c WHERE c.estado_id = ?";
//
//			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
//					StrCpsClasificador.class);
//			
//			if (!pEstado.trim().isEmpty()) {
//				vListaClasificadores = jdbcTemplate.query(vSql, vRowMapper, pEstado);
//			}
//
//		} catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(pEstado));
//		}
//
//		return vListaClasificadores;
//	}
//	
//	
//	@Override
//	public List<StrCpsClasificador> recuperarClasificadorPorAbreviatura(String pAbreviatura) {
//
//		List<StrCpsClasificador> vListaClasificadores = new ArrayList<>();
//
//		try {
//			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores c WHERE c.abreviatura = ?"
//					+ " AND c.estado_id = '" + ConstEstado.ESTADO_ACTIVO + "'";
//
//			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
//					StrCpsClasificador.class);
//
//			if (!pAbreviatura.trim().isEmpty()) {
//				vListaClasificadores = jdbcTemplate.query(vSql, vRowMapper, pAbreviatura);
//			}
//
//		} catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(pAbreviatura));
//		}
//
//		return vListaClasificadores;
//	}
//	
//	@Override
//	public List<StrCpsClasificador> listarClasificadorPorGrupo(Integer pAgrupadorId) {
//
//		List<StrCpsClasificador> vListaClasificadores = new ArrayList<>();
//
//		try {
//			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores cls"
//						+ " INNER JOIN str_transversales.str_cps_grupos_clasificadores grup "
//						+ " ON grup.clasificador_agrupado_id = cls.clasificador_id "
//						+ " WHERE grup.clasificador_agrupador_id = ? "
//						+ " AND cls.estado_clasificador_id = '1' "
//						+ " AND cls.estado_id = '" + ConstEstado.ESTADO_ACTIVO + "'"
//						+ " AND grup.estado_grupo_clasificador_id = " + EnumClasificador.ESTADO_ACTIVADO.getValue() 
//						+ " AND grup.estado_id = '" + ConstEstado.ESTADO_ACTIVO + "'";
//			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
//					StrCpsClasificador.class);
//
//			if (pAgrupadorId != null) {
//				vListaClasificadores = jdbcTemplate.query(vSql, vRowMapper, pAgrupadorId);
//			}
//
//		} catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(pAgrupadorId));
//		}
//
//		return vListaClasificadores;
//	}		
//	
//	@Override
//	public List<StrCpsClasificador> listarClasificadorPorTipoDesde(String pTipoClasificador, Date pFechaDesde) {
//		
//		List<StrCpsClasificador> vListaClasificadores = new ArrayList<>();
//
//		try {
//			String vSql = "SELECT * FROM str_transversales.str_cps_clasificadores c WHERE c.tipo_clasificador = ?"
//					+ " AND c.estado_id = 'AC' "
//					+ " AND c.fecha_desde >= ? ";
//
//			RowMapper<StrCpsClasificador> vRowMapper = new BeanPropertyRowMapper<StrCpsClasificador>(
//					StrCpsClasificador.class);
//
//			if (!pTipoClasificador.trim().isEmpty()) {
//				vListaClasificadores = jdbcTemplate.query(vSql, vRowMapper, pTipoClasificador, pFechaDesde);
//			}
//
//		} catch (Exception e) {
//			LogExcepcion.registrar(e, LOG, MethodSign.build(pTipoClasificador, pFechaDesde));
//		}
//
//		return vListaClasificadores;
//	}
}
