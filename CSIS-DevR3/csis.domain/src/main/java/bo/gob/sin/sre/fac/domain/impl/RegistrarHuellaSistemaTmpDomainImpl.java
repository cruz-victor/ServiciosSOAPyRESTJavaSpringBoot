package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IArchivosTmpDao;
import bo.gob.sin.sre.fac.dao.IComponenteArchivosTmpDao;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosTmpDao;
import bo.gob.sin.sre.fac.domain.IRegistrarHuellaSistemaTmpDomain;
import bo.gob.sin.sre.fac.model.SreArchivosTmp;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;


@Service
@Transactional

public class RegistrarHuellaSistemaTmpDomainImpl implements IRegistrarHuellaSistemaTmpDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarHuellaSistemaTmpDomainImpl.class);
	
	@Autowired
	IComponentesCertificadosTmpDao iComponentesCertificadosTmpDao;

	@Autowired
	IArchivosTmpDao iArchivosTmpDao;
	
	@Autowired
	IComponenteArchivosTmpDao iComponenteArchivosTmpDao;
		
	/**
	 * Registrar Componente Certificado
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/08/2018
	 * @modificadoPor: Peter Flores
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto SreComponentesCertificados
	 */
	@Override
	public SreComponentesCertificadosTmp registrarComponenteCertificado(Integer pTipoComponenteId,Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId){
		LOG.info("Ingresando registrarComponentesCertificados >"+pComponenteArchivoId+" >>"+pTipoComponenteId+" >>>"+pComponenteArchivoId);
		SreComponentesCertificadosTmp vRegistro = new SreComponentesCertificadosTmp();
		Date vFechaHoy = new Date();
		try {
			vRegistro.setTipoComponenteId(pTipoComponenteId);
			vRegistro.setUsuarioRegistroId(pUsuarioId);
			vRegistro.setUsuarioUltimaModificacionId(pUsuarioId);
			vRegistro.setComponenteArchivoTmpId(pComponenteArchivoId);
			vRegistro.setSistemaId(pSistemaId);			
			vRegistro.setFechaRegistro(vFechaHoy);
			vRegistro.setFechaUltimaModificacion(vFechaHoy);
			vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			iComponentesCertificadosTmpDao.save(vRegistro);
			LOG.info("Datos de componentes certificados registrados.");
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistro));
			LOG.info("Saliendo de recuperar registrarComponentesCertificados: " + vRegistro);
		}
		return vRegistro;
	}
	
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
			LOG.info("Saliendo de recuperar registrarArchivos: " + vRespuesta);
		}
		return vRespuesta;
	}
	
	
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

	/**
	 * Registrar Componente Certificado
	 * 
	 * @author: Peter Flores
	 * @Fecha: 15/11/2018	 
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve un valor booleano que representa el estado de los registros que se desarrollo.
	 */
	@Override
	public boolean registrarComponentesCertificados(List<Integer> pTipoComponenteId, Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId){
		LOG.info("Ingresando registrarComponentesCertificados ");
		boolean vResultado=false;	
		int vRespuestaVerificacion=0;
		SreComponentesCertificadosTmp vRespuesta = null;
		try {
			
			for (Integer tipoComponenteId : pTipoComponenteId) {
				vRespuesta = registrarComponenteCertificado(tipoComponenteId , pComponenteArchivoId, pUsuarioId, pSistemaId);
				if(vRespuesta.getComponenteArchivoTmpId()<=0)
					vRespuestaVerificacion++;
			}
			
			vResultado=vRespuestaVerificacion==0?true:false;
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de recuperar registrarComponentesCertificados: " + vRespuesta);
		}
		return vResultado;
	}
	
	/**
	 * Listado de los componentes de archivos de sistema.  
	 * 
	 * @author rosario.garcia
	 * @Fecha: 29/11/2018
	 * @param pSistemaId, Id de la entidad sistema.
	 * @return lista de componentes certificados.
	 */
	@Override
	public List<SreComponentesCertificadosTmp> obtieneComponenteCertificadoTmpPorSistemaId(Long pSistemaId){
		List<SreComponentesCertificadosTmp> vListaComponenteCertificado = new ArrayList<>(); 
		LOG.info("ingresando a obtieneComponenteCertificadoTmpPorSistemaId");
		try {	
			vListaComponenteCertificado = iComponentesCertificadosTmpDao.obtenerComponentesCertificadosTmp(pSistemaId);
		
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}
		LOG.info("Finalizando obtieneComponenteCertificadoTmpPorSistemaId");
		return vListaComponenteCertificado;
	}
	
	//=============================  ACTUALIZAR ESTADO DE LA ENTIDAD 	SreComponentesCertificadosTmp ======================
	/***
	 * Actualiza la entidad SreComponentesCertificadosTmp
	 * 
	 * @author rosario.garcia
	 * @return la entidad SreComponentesCertificadosTmp
	 * @fecha 29/11/2018
	 */
	 @Override
	 public SreComponentesCertificadosTmp actualizarEstadoComponenteCertificadoTmp(SreComponentesCertificadosTmp pSreComponentesCertificadosTmp) {
		 SreComponentesCertificadosTmp vRespuesta = new SreComponentesCertificadosTmp(); 
		 LOG.info("ingresando a actualizarEstadoComponenteCertificadoTmp");
		 try {
			iComponentesCertificadosTmpDao.saveOrUpdate(pSreComponentesCertificadosTmp);
			vRespuesta = iComponentesCertificadosTmpDao.findById(pSreComponentesCertificadosTmp.getComponenteCertificadoTmpId());
		 }catch (Exception e){
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarEstadoComponenteCertificadoTmp: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 

	 //================================== ACTUALIZAR ESTADO DE LA ENTIDAD SreComponentesArchivosTmp ==================
	/***
	 * Actualiza la entidad SreComponentesArchivosTmp
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
			 LOG.info("Saliendo de actualizarEstadoComponenteCertificadoTmp: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 
	 //================ OBTENER LISTA DE COMPONENTE ARCHIVO ==========================
	 /***
	  * Obtiene el
	  * 
	  * @param pComponenteArchivoTmpId
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
