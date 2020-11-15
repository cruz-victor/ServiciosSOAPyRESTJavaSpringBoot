package bo.gob.sin.sre.fac.cfec.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.IJsonDao;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlInvalido;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Repository
@Transactional
public class JsonDaoImpl extends AbstractGenericDao<SreFacArchivoXmlInvalido> implements IJsonDao {
	private static final Logger LOG = LoggerFactory.getLogger(JsonDaoImpl.class);

	@Override
	public String recepcionBdEtapa1(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_recepcion_factura(:pJson,:pEtapa)")
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
	public String anulacionBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery("select sre_recaudaciones.sre_fac_servicio_anulacion_factura(:pJson)")
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
	public String validacionAnulacionBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_validacion_anulacion_factura(:pJson)")
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
	public Integer validarNitBd(long pNit) {
		try {
			if (pNit > 0) {
				Query query = getSession().createNativeQuery("select sre_recaudaciones.sre_fac_ope_val_nit(:p_nit)")
						.setParameter("p_nit", pNit);
				Object x = query.getSingleResult();
				return Integer.parseInt(x.toString());
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
	public String obtenerDescripcionCodigosRespuesta(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_ope_obtener_descripcion_codigo_error(:pJson)")
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
	public String registroLogCertificacionSistemasBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_reg_registrar_log_certificacion_sistemas(:pJson)")
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
	public void registrarLogEtapa6EnvioPaquetesBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery("select sre_recaudaciones.sre_fac_reg_log_etapa_6_envio_paquetes(:pJson)")
						.setParameter("pJson", pSolicitud);
				query.getSingleResult();
			}
		} catch (Exception e) {
			LOG.error("Exception || registrarLogEtapa6EnvioPaquetesBd");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
		}
	}

	@Override
	public String recepcionBdEtapa1Masivo(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery("select sre_recaudaciones.sre_fac_servicio_recepcion_masivo(:pJson,:pEtapa)")
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
	public String recepcionBdEtapa2Masivo(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery("select sre_recaudaciones.sre_fac_servicio_recepcion_masivo(:pJson,:pEtapa)")
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
	public Integer verificarExistenciaCodigoRecepcion(int pTipoDocSector, long pCodigoRecepcion) {
		try {
			if (pTipoDocSector > 0 && pCodigoRecepcion > 0) {
				// se reutiliza funcion debido a que cumple la funcionalidad requerida
				Query query = getSession().createNativeQuery(
						"select sre_recaudaciones.sre_fac_ope_obtener_estado_recepcion(:p_tipo_documento_sector_id,:p_codigo_recepcion)")
						.setParameter("p_tipo_documento_sector_id", pTipoDocSector)
						.setParameter("p_codigo_recepcion", pCodigoRecepcion);
				Object x = query.getSingleResult();
				return Integer.parseInt(x.toString());
			} else {
				return 0;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long obtenerCodigoRecIndBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession().createNativeQuery(
						"select sre_recaudaciones.sre_fac_servicio_validacion_recepcion_factura_por_cuf(:pJson)")
						.setParameter("pJson", pSolicitud);
				Object x = query.getSingleResult();
				return Long.parseLong(x.toString());
			} else {
				return 0L;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public String validacionRecepcionMasivoBd(String pSolicitud) {
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
	public int obtenerCantidadMaximoMasivo(long pNit, int pSucursal) {

		try {
			if (pNit > 0) {
				Query query = getSession().createNativeQuery(
						"select persona_contribuyente_id from scn_contribuyentes.scn_emp_contribuyentes where nit = :vNit and estado_id = 'AC'")
						.setParameter("vNit", pNit);
				Object x = query.getSingleResult();

				Query query2 = getSession().createNativeQuery(
						"select cantidad_maxima_paquete from sre_recaudaciones.sre_fac_solicitudes_procesos_masivos p WHERE p.persona_contribuyente_id = :vContribuyenteId and estado_solicitud_proceso_masivo_id = 2682 and p.codigo_sucursal_id = :vSucursal and p.estado_id = 'AC'")
						.setParameter("vContribuyenteId", x).setParameter("vSucursal", pSucursal);
				Object x2 = query2.getSingleResult();

				return Integer.parseInt(x2.toString());
			} else {
				return 0;
			}
		} catch (NoResultException e) {
			return 0;
		}
	}

	@Override
	public void registrarLogPruebaObligatoriaEtapa6(String pSolicitud) {
		try {
			Query query = getSession()
					.createNativeQuery(
							"select sre_recaudaciones.sre_fac_pru_captura_log_casos_prueba_etapa_6(:pJsonString)")
					.setParameter("pJsonString", pSolicitud);
			query.getSingleResult();
		} catch (NoResultException e) {
			LOG.error("NoResultException || registrarLogPruebaObligatoriaEtapa6 : " + e.getMessage());
		}
	}

	@Override
	public String obtenerFacturaDatosParcial(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_obtener_factura_datos_parcial(:pJsonString)")
						.setParameter("pJsonString", pSolicitud);
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
	public void registrarLogExcepcionBDErrores(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_reg_log_excepcion_bd_errores(:pJsonString)")
						.setParameter("pJsonString", pSolicitud);
				query.getSingleResult();
			}
		} catch (Exception e) {
			LOG.error("Exception || registrarLogExcepcionBDErrores");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
		}
	}

	@Override
	public String obtenerFacturaPush(int pCodigoDocumentoSector, long pRecepcionId, String pCuf) {
		try {
			if (pRecepcionId > 0) {
				Query query = getSession().createNativeQuery(
						"select sre_recaudaciones.sre_fac_ope_verificar_id_recepcion_push(:vCodigoDocumentoSector, :vRecepcionId)")
						.setParameter("vCodigoDocumentoSector", pCodigoDocumentoSector)
						.setParameter("vRecepcionId", pRecepcionId);
				Object x = query.getSingleResult();
				return x.toString();
			}
			if (pCuf != null && !pCuf.isEmpty()) {
				Query query = getSession()
						.createNativeQuery("select sre_recaudaciones.sre_fac_reg_log_excepcion_bd_errores(:vCuf)")
						.setParameter("vCodigoDocumentoSector", pCodigoDocumentoSector).setParameter("vCuf", pCuf);
				Object x = query.getSingleResult();
				return x.toString();
			}

		} catch (Exception e) {
			LOG.error("Exception || obtenerFacturaPush");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pCodigoDocumentoSector));
		}
		return null;
	}

	@Override
	public String modificarBdEtapaProcesada(int pTipoDocumentoFiscal, int pSector, long pCodigoRecepcion,
			int estadoPaquete) {
		try {
			if (pSector > 0 && pCodigoRecepcion > 0) {
				Query query = getSession().createNativeQuery(
						"select sre_recaudaciones.sre_fac_ope_actualizar_estado_recepcion_paquete(:vFiscal, :vSector, :vRecepcion, :vEstado)")
						.setParameter("vFiscal", pTipoDocumentoFiscal).setParameter("vSector", pSector)
						.setParameter("vRecepcion", pCodigoRecepcion).setParameter("vEstado", estadoPaquete);
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
	public long obtenerRecepcionAnulacion(String pCuf) {

		try {
			if (pCuf != null && !pCuf.isEmpty()) {
				Query query = getSession().createNativeQuery(
						"select sre_recaudaciones.sre_fac_obtener_recepcion_anulacion where p_cuf = :vcuft and estado_id = 'AC'")
						.setParameter("vCuf", pCuf);
				Object x = query.getSingleResult();

				return Long.parseLong(x.toString());
			} else {
				return 0;
			}
		} catch (NoResultException e) {
			return 0;
		}
	}

	@Override
	public String modificarRecepcionPaqueteCantidadArchivo(long pArchivoValidoId, long pRecepcionPaqueteId,
			int pCantidadPaquete, int pSector) {
		try {
			if (pArchivoValidoId > 0 && pRecepcionPaqueteId > 0 && pCantidadPaquete > 0 && pSector > 0) {

				String hql = null;

				switch (pSector) {
				case Parametros.DOCUMENTO_SECTOR_FACTURA_ESTANDAR:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_estandares set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_estandar_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_SECTORES_EDUCATIVOS:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_colegios set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_colegio_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_ALQUILER_DE_BIENES_INMUEBLES:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_alquileres set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_alquiler_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIALIZACION_DE_HIDROCARBUROS:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_combustibles set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_combustible_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_SERVICIOS_BASICOS:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_servicios set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_servicio_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_DE_EMBOTELLADORAS:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_embotelladoras set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_embotelladora_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_ENTIDADES_FINANCIERAS:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_bancos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_banco_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_HOTELES:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_hoteles set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_hotel_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_HOSPITALES_CLINICAS:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_hospitales set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_hospital_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_JUEGOS_DE_AZAR:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_juegos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_juego_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_ARTISTAS_INTERNACIONALES:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_espectaculos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_espectaculo_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_DE_EXPORTACION:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_exportaciones set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_exportacion_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_LIBRE_CONSIGNACION:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_libres_consignaciones set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_libre_consignacion_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_ZONA_FRANCA:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_zonas_francas set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_zona_franca_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_ARTISTAS_NACIONALES:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_espectaculos_nacionales set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_espectaculo_nacional_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_SEGURIDAD_ALIMENTARIA:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_seguridades_alimentarias set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_seguridad_alimentaria_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_COMPRA_VENTA_DE_MONEDA_EXTRANJERA:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_monedas_extranjeras set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_moneda_extranjera_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_notas_creditos_debitos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_nota_credito_debito_id =:vRecepcionPaqueteId";
					break;
//				case Parametros.DOCUMENTO_SECTOR_NOTA_CONCILIACION:
//					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_estandares set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_estandar_id =:vRecepcionPaqueteId";
//					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_boletos_aereos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_boleto_aereo_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_TURISMO_RECEPTIVO:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_turismos_receptivos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_turismo_receptivo_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_FISCAL_TASA_CERO:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_tasas_ceros set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_tasa_cero_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_HIDROCARBURO:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_hidrocarburos set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_hidrocarburo_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_DE_EXPORTACION_YPFB:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_exportaciones_ypfb set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_exportacion_ypfb_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_COMERCIAL_EXPORTACION_MINERA:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_exportaciones_mineras set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_exportacion_minera_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_LIBRE_CONSIGNACION_YPFB:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_libres_consignaciones_ypfb set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_libre_consignacion_ypfb_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_VENTA_INTERNA_MINERA:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_mineras_internas set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_minera_interna_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_PREVALORADA_DIGITAL:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_prevaloradas set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_prevalorada_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_FACTURA_TELECOMUNICACION:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_telecomunicaciones set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_telecomunicacion_id =:vRecepcionPaqueteId";
					break;
				case Parametros.DOCUMENTO_SECTOR_NOTA_CREDITO_DEBITO_SFV:
					hql = "update sre_recaudaciones.sre_fac_recepciones_paquetes_notas_creditos_debitos_sfv set total_documentos_fiscales =:vCantidadPaquete, archivo_xml_valido_id =:vArchivoValidoId where recepcion_paquete_nota_credito_debito_sfv_id =:vRecepcionPaqueteId";
					break;
				}

				if (hql != null) {
					@SuppressWarnings("deprecation")
					Query query = getSession().createSQLQuery(hql).setParameter("vArchivoValidoId", pArchivoValidoId)
							.setParameter("vRecepcionPaqueteId", pRecepcionPaqueteId)
							.setParameter("vCantidadPaquete", pCantidadPaquete);
					Object x = query.executeUpdate();
					return x.toString();
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Integer obtenerCantidadMaximaPaquete() {
		try {
			Query query = getSession().createNativeQuery(
					"select descripcion from str_transversales.str_cps_clasificadores where tipo_clasificador = 'cantidad_paquete' and estado_id = 'AC'");
			Object x = query.getSingleResult();
			return Integer.parseInt(x.toString());
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public String validacionRecepcionAnulacionBd(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_obtener_recepcion_anulacion(:pJson)")
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
}