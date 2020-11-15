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
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosDao;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosTmpDao;
import bo.gob.sin.sre.fac.domain.IComponentesCertificadosTmpDomain;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

@Service
@Transactional
public class ComponentesCertificadosTmpDomainImpl implements IComponentesCertificadosTmpDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ComponentesCertificadosTmpDomainImpl.class);
	
	@Autowired
	IComponentesCertificadosTmpDao iComponentesCertificadosTmpDao;
	
	@Autowired
	IComponentesCertificadosDao iComponentesCertificadosDao;

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
		LOG.info("Ingresando registrarComponentesCertificados."+pComponenteArchivoId+" >>"+pTipoComponenteId+" >>>"+pComponenteArchivoId);
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
	public List<SreComponentesCertificadosTmp> obtenerComponenteCertificadoTmpPorSistemaId(Long pSistemaId){
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
		 LOG.info("ingresando a actualizarEstadoComponenteCertificadoTmp");
		 SreComponentesCertificadosTmp vRespuesta = new SreComponentesCertificadosTmp(); 
		 try {
			iComponentesCertificadosTmpDao.saveOrUpdate(pSreComponentesCertificadosTmp);
			vRespuesta = iComponentesCertificadosTmpDao.findById(pSreComponentesCertificadosTmp.getComponenteCertificadoTmpId());
		 }catch (Exception e){
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarEstadoComponenteCertificadoTmp: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 
	/***
	 * Actualiza la entidad SreComponentesCertificados
	 * 
	 * @author wilson.limachi
	 * @return la entidad SreComponentesCertificados
	 * @fecha 24/09/2019
	 */
	 @Override
	 public SreComponentesCertificados actualizarEstadoComponenteCertificado(SreComponentesCertificados pSreComponentesCertificados) 
	 {
		 LOG.info("ingresando a actualizarEstadoComponenteCertificado");
		 SreComponentesCertificados vRespuesta = new SreComponentesCertificados(); 
		 try {
			iComponentesCertificadosDao.saveOrUpdate(pSreComponentesCertificados);
			vRespuesta = iComponentesCertificadosDao.findById(pSreComponentesCertificados.getComponenteCertificadoId());
		 }
		 catch (Exception e)
		 {
			 vRespuesta = null;
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarEstadoComponenteCertificadoTmp: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 

}
