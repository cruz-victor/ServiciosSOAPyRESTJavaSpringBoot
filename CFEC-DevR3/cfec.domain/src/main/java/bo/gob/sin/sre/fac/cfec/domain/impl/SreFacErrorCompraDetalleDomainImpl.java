package bo.gob.sin.sre.fac.cfec.domain.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.ISreFacErrorCompraDetalleDao;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacErrorCompraDetalleDomain;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresComprasDetalles;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional(rollbackFor = Exception.class)
public class SreFacErrorCompraDetalleDomainImpl implements ISreFacErrorCompraDetalleDomain {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SreFacErrorCompraDetalleDomainImpl.class);
	
	@Autowired
	ISreFacErrorCompraDetalleDao iSreFacErrorCompraDetalleDao;
	
	@Override
	public long registrarErrorCompraDetalle(SreFacRecepcionesErroresComprasDetalles pSolicitud) {
		long vRespuesta = 0L;
		try {
			
			iSreFacErrorCompraDetalleDao.save(pSolicitud);
			if (pSolicitud.getRecepcionErrorCompraDetalleId() > 0)
			return pSolicitud.getRecepcionErrorCompraDetalleId();

		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return vRespuesta;
	}
}