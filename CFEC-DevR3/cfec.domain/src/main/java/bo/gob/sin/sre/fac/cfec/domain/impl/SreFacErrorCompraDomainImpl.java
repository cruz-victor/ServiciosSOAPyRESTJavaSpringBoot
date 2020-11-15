package bo.gob.sin.sre.fac.cfec.domain.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.cfec.dao.ISreFacErrorCompraDao;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacErrorCompraDomain;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.model.SreFacRecepcionesErroresCompras;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
public class SreFacErrorCompraDomainImpl implements ISreFacErrorCompraDomain {


	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SreFacErrorCompraDomainImpl.class);
	
	@Autowired
	ISreFacErrorCompraDao iSreFacErrorCompraDao;
	
	@Override
	public long registrarErrorCompra(SreFacRecepcionesErroresCompras pSolicitud, List<Integer> pListaErrores) {
		long vRespuesta = 0L;
		try {
			
			pSolicitud.setFechaRegistro(new Date());
			pSolicitud.setFechaUltimaModificacion(new Date());
			pSolicitud.setUsuarioRegistroId(1000L);
			pSolicitud.setUsuarioUltimaModificacionId(1000L);
			pSolicitud.setEstadoId(Parametros.ESTADO_ACTIVO);
			iSreFacErrorCompraDao.save(pSolicitud);
			if (pSolicitud.getRecepcionCompraId() > 0)
			return pSolicitud.getRecepcionCompraId();

		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return vRespuesta;
	}


}