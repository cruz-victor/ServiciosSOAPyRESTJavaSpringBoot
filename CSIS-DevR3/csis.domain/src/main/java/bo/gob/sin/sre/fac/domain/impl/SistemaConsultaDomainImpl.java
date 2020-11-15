package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasDao;
import bo.gob.sin.sre.fac.domain.ISistemasConsultaDomain;
import bo.gob.sin.sre.fac.model.SreSistemas;

@Service
@Transactional
public class SistemaConsultaDomainImpl implements ISistemasConsultaDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SistemaConsultaDomainImpl.class);
	
	@Autowired
	ISistemasDao iSistemasDao;
	
	/**
	 * Obtener Solicitud de Sistemas por sistemaId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 19/06/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 14/11/2018
	 * @param pSistemaId, número identificación de sistema
	 * @return   Devuelve objeto respuesta SreSistema.
	 */
	@Override
	public SreSistemas recuperarDatosSistemas(long pSistemaId) {
		LOG.info("Ingresando obtenerSolicitudSistemas ");
		SreSistemas vRespuesta = new SreSistemas();

		try {
			vRespuesta = iSistemasDao.obtenerSolicitudSistemaCertificado(pSistemaId);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
		}
		LOG.info("Saliendo de recuperar obtenerSolicitudSistemas: " + vRespuesta);
		return vRespuesta;
	}
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 15/06/2018
	    */
	@Override
	public List<SreSistemas> verificacionSistemaRegistrado(String pNombreSistema, Integer pModalidadFacturacionId, Long pContribuyenteId) {
		List<SreSistemas> respuestaRegistro = new ArrayList<>();
		LOG.info("ingresando verificacionSistemaRegistrado");
		try {
			if (!pContribuyenteId.equals(null) && pNombreSistema != null && pContribuyenteId > 0 && pModalidadFacturacionId > 0) {
				respuestaRegistro = iSistemasDao.verificacionSistemaRegistrado(pNombreSistema, pModalidadFacturacionId, pContribuyenteId);
				LOG.info("verificaicon de sistemas exitoso");
			} else {
				LOG.info("No se pudo verificar los sistemas por los datos de entrada");
			}
		} catch (Exception e) {
			LOG.info("No se pudo verificar existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", respuestaRegistro);
		return respuestaRegistro;
	}

}
