package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IArchivosDao;
import bo.gob.sin.sre.fac.dao.IArchivosTmpDao;
import bo.gob.sin.sre.fac.domain.IArchivoTmpDomain;
import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreArchivosTmp;

@Service
@Transactional
public class ArchivosTmpDomainImpl implements IArchivoTmpDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ArchivosTmpDomainImpl.class);
	@Autowired
	IArchivosTmpDao iArchivosTmpDao;

	@Autowired
	IArchivosDao iArchivosDao;
	
	
	/** 
	 * Registrar Archivos
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/08/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return    Devuelve objeto SreArchivos
	 */
	@Override
	public SreArchivosTmp registrarArchivos(byte[] pArchivo) {
		LOG.info("Ingresando registrarArchivos ");
		SreArchivosTmp vRespuesta = new SreArchivosTmp();
		try {
			vRespuesta.setArchivo(pArchivo);
			vRespuesta.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			iArchivosTmpDao.save(vRespuesta);
			LOG.info("Datos del archivo registrados.");
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de registrarArchivos: " + vRespuesta);
		}
		return vRespuesta;
	}
	
	 //========================= ACTUALIZAR ESTADO DE LA ENTIDAD SreArchivosTmp ============================
	 
	/***
	 * Actualiza la entidad SreComponentesArchivosTmp
	 * 
	 * @author rosario.garcia
	 * @return la entidad SreComponentesArchivosTmp
	 * @fecha 30/11/2018
	 */
	 @Override
	 public SreArchivosTmp actualizarEstadoArchivoTmp(Long pArchivoId) {
		 LOG.info("Ingresando a actualizarEstadoArchivoTmp");
		 SreArchivosTmp vSreArchivosTmp = new SreArchivosTmp(); 
		 SreArchivosTmp vRespuesta = new SreArchivosTmp();
		 try {
			 vSreArchivosTmp = iArchivosTmpDao.findById(pArchivoId);
			 vSreArchivosTmp.setEstadoId("IA");
			 iArchivosTmpDao.saveOrUpdate(vSreArchivosTmp);
			 vRespuesta = iArchivosTmpDao.findById(pArchivoId);
		 }catch (Exception e){
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarEstadoArchivoTmp: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 
		/***
		 * Actualiza la entidad SreComponentesArchivos
		 * 
		 * @author wilson.limachi
		 * @return la entidad SreComponentesArchivos
		 * @fecha 24/09/2019
		 */
		 @Override
		 public SreArchivos actualizarEstadoArchivo(Long pArchivoId) 
		 {
			 LOG.info("Ingresando a actualizarEstadoArchivo");
			 SreArchivos vSreArchivos = new SreArchivos(); 
			 SreArchivos vRespuesta = new SreArchivos();
			 try 
			 {
				 vSreArchivos = iArchivosDao.findById(pArchivoId);
				 vSreArchivos.setEstadoId("IA");
				 iArchivosDao.saveOrUpdate(vSreArchivos);
				 vRespuesta = iArchivosDao.findById(pArchivoId);
			 }
			 catch (Exception e)
			 {
				 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				 LOG.info("Saliendo de actualizarEstadoArchivoTmp: " + vRespuesta);
			}
			return vRespuesta;
		 }
}
