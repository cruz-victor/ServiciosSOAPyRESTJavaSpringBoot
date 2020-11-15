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
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IComponenteArchivosDao;
import bo.gob.sin.sre.fac.dao.IComponenteArchivosTmpDao;
import bo.gob.sin.sre.fac.domain.IComponentesArchivosTmpDomain;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;

@Service
@Transactional
public class ComponentesArchivosTmpDomainImpl implements IComponentesArchivosTmpDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ComponentesArchivosTmpDomainImpl.class);
	@Autowired
	IComponenteArchivosTmpDao iComponenteArchivosTmpDao;
	
	@Autowired
	IComponenteArchivosDao iComponenteArchivosDao;
	/** 
	 * Registrar Componente Archivo
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/08/2018
	 * @modificadoPor: Peter Flores
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve true y false
	 */
	@Override
	//public boolean registrarComponentesArchivos(Long pComponenteCertificadoId, Long pArchivoId,Long pUsuarioId,String pNombreArchivo) {
	public SreComponentesArchivosTmp registrarComponentesArchivos(Long pUsuarioId,Long pArchivoId, String pMd5, String pSha2,String pCrc,String pRuta,String pNombre ,String pMime) {
		LOG.info("Ingresando registrarComponentesArchivos ");
		SreComponentesArchivosTmp vRespuesta = new SreComponentesArchivosTmp();
		String vNombre = "";
		String vExtension = "";
		Date vFechaHoy = new Date();
		try {
			vRespuesta.setUsuarioRegistroId(pUsuarioId);
			vRespuesta.setUsuarioUltimaModificacionId(pUsuarioId);
			vRespuesta.setArchivoId(pArchivoId);
			vRespuesta.setMd5(pMd5);
			vRespuesta.setSha2(pSha2);
			vRespuesta.setCrc(pCrc);
			vExtension = pNombre.substring(pNombre.length() - 3);
			vRespuesta.setExtension(vExtension);
			vRespuesta.setMime(pMime);
			vRespuesta.setRuta(pRuta);
			vNombre = pNombre.substring(0, pNombre.length() - 4);
			vRespuesta.setNombre(vNombre);			
			vRespuesta.setFechaUltimaModificacion(vFechaHoy);
			vRespuesta.setFechaRegistro(vFechaHoy);
			vRespuesta.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);			
			iComponenteArchivosTmpDao.save(vRespuesta);			
			LOG.info("Datos de componentes del archivo registrados.");
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de recuperar registrarComponentesArchivos: " + vRespuesta);
		}
		return vRespuesta;
	}
	
	 //================================== ACTUALIZAR ESTADO DE LA ENTIDAD SreComponentesArchivosTmp ==================
	/***
	 * Actualiza estadoId de la entidad SreComponentesArchivosTmp
	 * 
	 * @author rosario.garcia
	 * @return la entidad SreComponentesArchivosTmp
	 * @fecha 30/11/2018
	 */
	 @Override
	 public SreComponentesArchivosTmp actualizarEstadoComponenteArchivoTmp(Long pComponenteArchivoTmpId) {
		 LOG.info("Ingresando a actualizarEstadoComponenteArchivoTmp");
		 SreComponentesArchivosTmp vSreComponentesArchivosTmp = new SreComponentesArchivosTmp(); 
		 SreComponentesArchivosTmp vRespuesta = new SreComponentesArchivosTmp(); 
		 Date vFechaHoy = new Date();
		 try {
			 vSreComponentesArchivosTmp = iComponenteArchivosTmpDao.findById(pComponenteArchivoTmpId);
			 vSreComponentesArchivosTmp.setEstadoId("IA");
			 vSreComponentesArchivosTmp.setFechaUltimaModificacion(vFechaHoy);
			 iComponenteArchivosTmpDao.saveOrUpdate(vSreComponentesArchivosTmp);
			 vRespuesta = iComponenteArchivosTmpDao.findById(pComponenteArchivoTmpId);		 
		 }catch (Exception e){
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarEstadoComponenteCertificadoTmp: " + vRespuesta);
		}
		return vRespuesta;
	 }	
	 
	 /***
		 * Actualiza estadoId de la entidad SreComponentesArchivos
		 * 
		 * @author wilson.limachi
		 * @return la entidad SreComponentesArchivos
		 * @fecha 24/09/2019
		 */
		 @Override
		 public SreComponentesArchivos actualizarEstadoComponenteArchivo(Long pComponenteArchivoId) 
		 {
			 LOG.info("Ingresando a actualizarEstadoComponenteArchivoTmp");
			 SreComponentesArchivos vSreComponentesArchivosTmp = new SreComponentesArchivos(); 
			 SreComponentesArchivos vRespuesta = new SreComponentesArchivos(); 
			 Date vFechaHoy = new Date();
			 try {
				 vSreComponentesArchivosTmp = iComponenteArchivosDao.findById(pComponenteArchivoId);
				 vSreComponentesArchivosTmp.setEstadoId("IA");
				 vSreComponentesArchivosTmp.setFechaUltimaModificacion(vFechaHoy);
				 iComponenteArchivosDao.saveOrUpdate(vSreComponentesArchivosTmp);
				 vRespuesta = iComponenteArchivosDao.findById(pComponenteArchivoId);		 
			 }
			 catch (Exception e)
			 {
				 vSreComponentesArchivosTmp = null;
				 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				 LOG.info("Saliendo de actualizarEstadoComponenteCertificadoTmp: " + vRespuesta);
			}
			return vRespuesta;
		 }	
	 
	 //================ OBTENER REGISTRO DEL COMPONENTE ARCHIVO ==========================
	 /***
	  * Obtiene el registro de la entidad de acuerdo al Id
	  * 
	  * @param pComponenteArchivoTmpId, id de la entidad
	  * @return
	  */
	 @Override
	 public SreComponentesArchivosTmp obtenerComponenteArchivoId(Long pComponenteArchivoTmpId) {
		 LOG.info("Ingresando a obtenerComponenteArchivoId");
		 SreComponentesArchivosTmp vRespuesta = new SreComponentesArchivosTmp();
		 try {
			 vRespuesta = iComponenteArchivosTmpDao.obtenerComponentesArchivoTmp(pComponenteArchivoTmpId);
		 }catch(Exception e) {
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de obtenerComponenteArchivoId: " + vRespuesta);
		 }
		 return vRespuesta;
	 }
}
