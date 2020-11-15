package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IRutasServiciosDao;
import bo.gob.sin.sre.fac.domain.IRutasServiciosConsultaDomain;
import bo.gob.sin.sre.fac.model.SreRutasServicios;

@Service
@Transactional
public class RutasServiciosConsultaDomainImpl implements IRutasServiciosConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RutasServiciosConsultaDomainImpl.class);

	@Autowired
	IRutasServiciosDao iRutasServiciosDao;
	
	//IASC - Obtiene la lista de las rutas de los servicios - 27/11/2018 
	@Override
	public SreRutasServicios obtieneRutasServicios(int pModalidadId, int pTipoDocumentoSectorId) {
		SreRutasServicios respuestaRegistro = new SreRutasServicios();
		LOG.info("ingresando obtieneRutasServicios");
		try {
			respuestaRegistro = iRutasServiciosDao.obtieneRutas(pModalidadId, pTipoDocumentoSectorId);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar datos, existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtieneRutasServicios", respuestaRegistro);
		return respuestaRegistro;
	}
	
	//IASC - Obtiene la lista de las rutas de los servicios - 27/11/2018 
	@Override
	public List<SreRutasServicios> obtieneRutasServiciosComunes(int pModalidadId, int pTipoDocumentoSectorId) {
		List<SreRutasServicios> respuestaRegistro = new ArrayList<SreRutasServicios>();
		LOG.info("ingresando obtieneRutasServiciosComunes");
		try {
			respuestaRegistro = iRutasServiciosDao.obtieneRutasComunes(pModalidadId, pTipoDocumentoSectorId);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar datos, existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtieneRutasServiciosComunes", respuestaRegistro);
		return respuestaRegistro;
	}
	
}
