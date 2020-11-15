package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IPruebasSistemasDao;
import bo.gob.sin.sre.fac.domain.IPruebasSistemasABMDomain;
import bo.gob.sin.sre.fac.model.SrePruebasSistemas;

@Service
@Transactional
public class PruebasSistemaABMDomainImpl implements IPruebasSistemasABMDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PruebasSistemaABMDomainImpl.class);
	
	@Autowired
	IPruebasSistemasDao iPruebasSistemasDao;
	
	/**
	 * Cambiar estado en Pruebas sistemas
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 27/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitudCertificacionId, numero de identificacion de solicitud certificacion
	 * @param pSistemasId, número de identificacion del sistema
	 * @param pUsuarioId, número de identificacion del usuario
	 * @param pEstado, número de identificacion del estado
	 * @return Devuelve un boolean true o false.
	 */
	
	@Override
	public boolean cambiarEstadoPruebasSistemas(Long pSolicitudCertificacionId, Long pSistemasId, Long pUsuarioId, Integer pEstado) {
		List<SrePruebasSistemas> vRegistro = new ArrayList<SrePruebasSistemas>();
		boolean vRespuesta = false;
		try {
			vRegistro = iPruebasSistemasDao.obtenerListaPruebasSistemasPorCertificacion(pSolicitudCertificacionId,pSistemasId);
			for (SrePruebasSistemas vDatos : vRegistro) {
				vDatos.setEstadoPruebaId(pEstado);
				vDatos.setFechaUltimaModificacion(new Date());
				vDatos.setUsuarioUltimaModificacionId(pUsuarioId);
				iPruebasSistemasDao.saveOrUpdate(vDatos);
			}
			vRespuesta = true;

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemasId));
		}
		return vRespuesta;
	}
}
