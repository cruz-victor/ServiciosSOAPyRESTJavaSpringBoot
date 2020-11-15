package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IDetalleCertificacionesSistemasDao;
import bo.gob.sin.sre.fac.domain.IDetalleCertificacionesSistemasDomain;
import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;
import bo.gob.sin.str.cexc.LogExcepcion;

@Service
@Transactional(rollbackFor = Exception.class)

public class DetalleCertificacionesSistemasDomainImpl implements IDetalleCertificacionesSistemasDomain, Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DetalleCertificacionesSistemasDomainImpl.class);
	
	@Autowired
	private IDetalleCertificacionesSistemasDao iDetalleCertificacionesSistemasDao;

	@Override
	public SreDetalleCertificacionesSistemas registraDetalleCertificacionesSistemas(SreDetalleCertificacionesSistemas pSolicitudSistema) {
		
		try {
			iDetalleCertificacionesSistemasDao.saveOrUpdate(pSolicitudSistema);
			return pSolicitudSistema;
		} catch (NoResultException e) {
			return new SreDetalleCertificacionesSistemas();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
	
	@Override
	public List<SreDetalleCertificacionesSistemas> obtenerListaDetalleCertificacionSistemas(long pSolicitudCertificacionId, long pSistemaId){
		try {
			return iDetalleCertificacionesSistemasDao.obtenerListaDetalleCertificacionSistemas(pSolicitudCertificacionId, pSistemaId);
		
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	@Override
	public SreDetalleCertificacionesSistemas obtenerDetalleCertificacionSistemas(long pDetalleCertificacionSistemaId) {
		try {
			return iDetalleCertificacionesSistemasDao.obtenerDetalleCertificacionSistemas(pDetalleCertificacionSistemaId);
		} catch (NoResultException e) {
			return new SreDetalleCertificacionesSistemas();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
}
