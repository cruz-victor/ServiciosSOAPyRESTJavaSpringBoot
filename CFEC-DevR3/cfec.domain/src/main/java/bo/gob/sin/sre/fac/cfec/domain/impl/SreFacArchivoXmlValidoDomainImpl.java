package bo.gob.sin.sre.fac.cfec.domain.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.ISreFacArchivoXmlValidoDao;
import bo.gob.sin.sre.fac.cfec.domain.ISreFacArchivoXmlValidoDomain;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlValido;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional(rollbackFor = Exception.class)
public class SreFacArchivoXmlValidoDomainImpl implements ISreFacArchivoXmlValidoDomain {


	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SreFacArchivoXmlValidoDomainImpl.class);
	
	@Autowired
	ISreFacArchivoXmlValidoDao iSreFacArchivoXmlValidoDao;

	@Override
	public long registrarArchivoXmlValido(SreFacArchivoXmlValido pSolicitud) {
		long vRespuesta = 0L;
		try {
			
			pSolicitud.setEstadoId(Parametros.ESTADO_ACTIVO);
			pSolicitud.setMime(Parametros.MIME_ARCHIVO);
			pSolicitud.setExtension(Parametros.EXTENSION_ARCHIVO);
			iSreFacArchivoXmlValidoDao.save(pSolicitud);
			if (pSolicitud.getArchivoXmlValidoId() > 0)
			return pSolicitud.getArchivoXmlValidoId();

		}
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return vRespuesta;
	}


}