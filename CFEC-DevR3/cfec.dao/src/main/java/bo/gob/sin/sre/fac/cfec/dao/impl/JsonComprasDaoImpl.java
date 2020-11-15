package bo.gob.sin.sre.fac.cfec.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.IJsonComprasDao;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlInvalido;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class JsonComprasDaoImpl extends AbstractGenericDao<SreFacArchivoXmlInvalido> implements IJsonComprasDao {
	private static final Logger LOG = LoggerFactory.getLogger(JsonComprasDaoImpl.class);

	@Override
	public String recepcionBdEtapa1(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_recepcion_compra(:pJson,:pEtapa)")
						.setParameter("pJson", pSolicitud).setParameter("pEtapa", "1");
				Object x = query.getSingleResult();
				return x.toString();
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public String recepcionBdEtapa2(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_recepcion_factura(:pJson,:pEtapa)")
						.setParameter("pJson", pSolicitud).setParameter("pEtapa", "2");
				Object x = query.getSingleResult();
				return x.toString();
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public String validacionRecepcionBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_validacion_recepcion_factura(:pJson)")
						.setParameter("pJson", pSolicitud);
				Object x = query.getSingleResult();
				return x.toString();
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public String validacionRecepcionPaqueteBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_validacion_recepcion_factura(:pJson)")
						.setParameter("pJson", pSolicitud);
				Object x = query.getSingleResult();
				return x.toString();
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void registrarErroresBd(String pFactura, String pListaErrores) {
		try {
			Query query0 = getSession()
					.createNativeQuery("select sre_recaudaciones.sre_fac_ope_limpiar_objeto_json(:pFact)")
					.setParameter("pFact", pFactura);
			Object json = query0.getSingleResult();
			Query query = getSession().createNativeQuery(
					"select sre_recaudaciones.sre_reg_factura_nota_erroneas(cast(:pJson as json),cast(:pJsonErrores as json))")
					.setParameter("pJson", json).setParameter("pJsonErrores", pListaErrores);
			query.getSingleResult();
		} catch (NoResultException e) {
			LOG.info("NoResultException || registrarErroresBd : " + e.getMessage());
		}
	}

	@Override
	public Object recepcionBdComprasIndividual(int pDocumentoSector, String pCuf, long pNumeroFactura,
			String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal,
			Integer pCodigoPuntoVenta, Integer pCodigoAmbiente) {

		try {
			if (pDocumentoSector > 0 && pCuf != null && pNumeroFactura > 0 && pNumeroDocumento != null) {
				Query query = getSession().createNativeQuery(
						"select fecha_emision_res as fechaEmision, modalidad_res as modalidadFacturacion, numero_factura_res as numeroFactura, cuf_res as cufAutorizacion, nit_emisor_res as nitEmisor, monto_total_res as montoTotal, estado_factura_res as estadoFactura from sre_recaudaciones.sre_fac_lec_compras_individual(:p_documento_sector,:p_cuf,:p_numero_factura,:p_numero_documento,:p_codigo_sistema,:p_cufd,:p_cuis,:p_codigo_sucursal,:p_codigo_punto_venta,:p_codigo_ambiente)")
						.setParameter("p_documento_sector", pDocumentoSector).setParameter("p_cuf", pCuf).setParameter("p_numero_factura", pNumeroFactura).setParameter("p_numero_documento", pNumeroDocumento)
						.setParameter("p_codigo_sistema", pCodigoSistema).setParameter("p_cufd", pCufd).setParameter("p_cuis", pCuis)
				.setParameter("p_codigo_sucursal", pCodigoSucursal).setParameter("p_codigo_punto_venta", pCodigoPuntoVenta).setParameter("p_codigo_ambiente", pCodigoAmbiente);
				//Object vObjetoRespuesta = query.getSingleResult();
				List<Object> vObjetoRespuesta = query.getResultList();
				if(vObjetoRespuesta.size() > 0)
				return vObjetoRespuesta.get(0);
				else 
					return null;
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Object> recepcionBdComprasPorDia(Date pFechaInicio, Date pFechaFin, String pNumeroDocumento,
			String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta,
			Integer pCodigoAmbiente) {
		try {
			if (pFechaInicio != null && pFechaFin != null && pNumeroDocumento != null) {
				Query query = getSession().createNativeQuery(
						"select fecha_emision_res as fechaEmision, modalidad_res as modalidadFacturacion, numero_factura_res as numeroFactura, cuf_res as cufAutorizacion, nit_emisor_res as nitEmisor, monto_total_res as montoTotal, estado_factura_res as estadoFactura from sre_recaudaciones.sre_fac_lec_compras_por_dia_electronica(:p_fecha_inicio,:p_fecha_fin,:p_numero_documento,:p_codigo_sistema,:p_cufd,:p_cuis,:p_codigo_sucursal,:p_codigo_punto_venta,:p_codigo_ambiente)")
						.setParameter("p_fecha_inicio", pFechaInicio).setParameter("p_fecha_fin", pFechaFin).setParameter("p_numero_documento", pNumeroDocumento)
						.setParameter("p_codigo_sistema", pCodigoSistema).setParameter("p_cufd", pCufd).setParameter("p_cuis", pCuis)
				.setParameter("p_codigo_sucursal", pCodigoSucursal).setParameter("p_codigo_punto_venta", pCodigoPuntoVenta).setParameter("p_codigo_ambiente", pCodigoAmbiente);
				
				List<Object> vRespuesta = query.getResultList();
				if(vRespuesta.size() > 0)
					return vRespuesta;
				else 
					return null;
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}
	// .createNativeQuery("select fecha_emision_res, modalidad_res,
	// numero_factura_res, cuf_res, nit_emisor_res, monto_total_res,
	// estado_factura_res from
	// sre_recaudaciones.sre_fac_lec_compras_por_dia_electronica(:p_fecha_inicio,:p_fecha_fin,:p_numero_documento)")
	// .setParameter("p_fecha_inicio", "2019-07-29").setParameter("p_fecha_fin",
	// "2019-07-29").setParameter("p_numero_documento", pNumeroDocumento);
}