package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasDao;
import bo.gob.sin.sre.fac.domain.ISistemasABMDomain;
import bo.gob.sin.sre.fac.model.SreSistemas;

@Service
@Transactional
public class SistemaABMDomainImpl implements ISistemasABMDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SistemaABMDomainImpl.class);
	
	@Autowired
	ISistemasDao iSreFacDaoSistemas;
	
	/**
	 * Cambiar estado en Sistema, parametrizado
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSistemaId, id de la tabla sistema
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de sistema
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
	@Override
	public SreSistemas cambiarEstadoSistema(long pSistemaId, long pUsuario, int pEstadoId) {
		LOG.info("Ingresando ActualizatEstadoSistema pSistemaId ={} ", pSistemaId);
		SreSistemas vResultado= new SreSistemas();
		try {
			vResultado = iSreFacDaoSistemas.findById(pSistemaId);
			vResultado.setEstadoSistemaId(pEstadoId);
			vResultado.setFechaUltimaModificacion(new Date());
			vResultado.setUsuarioUltimaModificacionId(pUsuario);
			iSreFacDaoSistemas.saveOrUpdate(vResultado);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
			vResultado = null;
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", vResultado);
		return vResultado;
	}

}
