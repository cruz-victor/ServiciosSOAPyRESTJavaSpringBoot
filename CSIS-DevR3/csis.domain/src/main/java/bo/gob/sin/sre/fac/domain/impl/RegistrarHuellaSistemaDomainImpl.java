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
import bo.gob.sin.sre.fac.dao.IArchivosDao;
import bo.gob.sin.sre.fac.dao.IComponenteArchivosDao;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosDao;
import bo.gob.sin.sre.fac.domain.IRegistrarHuellaSistemaDomain;
import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;


@Service
@Transactional

public class RegistrarHuellaSistemaDomainImpl implements IRegistrarHuellaSistemaDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarHuellaSistemaDomainImpl.class);
	
	@Autowired
	IComponentesCertificadosDao iComponentesCertificadosDao;

	@Autowired
	IArchivosDao iArchivosDao;
	
	@Autowired
	IComponenteArchivosDao iComponentesArchivosDao;
		
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
	public SreComponentesCertificados registrarComponenteCertificado(Integer pTipoComponenteId,Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId, Long pSolicitudCertificacionId){
		LOG.info("Ingresando registrarComponentesCertificados ");
		SreComponentesCertificados vRegistro = new SreComponentesCertificados();
		Date vFechaHoy = new Date();
		try {
			vRegistro.setTipoComponenteId(pTipoComponenteId);
			vRegistro.setUsuarioRegistroId(pUsuarioId);
			vRegistro.setUsuarioUltimaModificacionId(pUsuarioId);
			vRegistro.setComponenteArchivoId(pComponenteArchivoId);
			vRegistro.setSistemaId(pSistemaId);			
			vRegistro.setFechaRegistro(vFechaHoy);
			vRegistro.setFechaUltimaModificacion(vFechaHoy);
			vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			vRegistro.setSolicitudCertificacionId(pSolicitudCertificacionId);
			iComponentesCertificadosDao.save(vRegistro);
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
	public SreArchivos registrarArchivos(byte[] pArchivo) 
	{
		LOG.info("Ingresando registrarArchivos ");
		SreArchivos vRespuesta = new SreArchivos();
		
		try 
		{
			vRespuesta.setArchivo(pArchivo);
			vRespuesta.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			iArchivosDao.save(vRespuesta);
			LOG.info("Datos del archivo registrados.");
		} 
		catch (Exception e) 
		{
			vRespuesta = null;
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
	public SreComponentesArchivos registrarComponentesArchivos(Long pUsuarioId,Long pArchivoId, String pMd5, String pSha2,String pCrc,String pRuta,String pNombre ,String pMime) {
		LOG.info("Ingresando registrarComponentesArchivos ");
		SreComponentesArchivos vRespuesta = new SreComponentesArchivos();
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
			iComponentesArchivosDao.save(vRespuesta);			
			LOG.info("Datos de componentes del archivo registrados.");
		} 
		catch (Exception e) 
		{
			vRespuesta = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de recuperar registrarComponentesArchivos: " + vRespuesta);
		}
		return vRespuesta;
	}
	
	/** 
	 * Registrar Componente Archivo
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 20/09/2019 
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve entidad o null
	 */
	@Override
	//public boolean registrarComponentesArchivos(Long pComponenteCertificadoId, Long pArchivoId,Long pUsuarioId,String pNombreArchivo) {
	public SreComponentesArchivos registrarComponentesArchivos(Long pUsuarioId,Long pArchivoId, String pMd5, String pSha2,String pCrc,String pRuta,String pNombre ,String pMime, String pExtension) 
	{
		LOG.info("Ingresando registrarComponentesArchivos ");
		SreComponentesArchivos vRespuesta = new SreComponentesArchivos();
		Date vFechaHoy = new Date();
		try {
			vRespuesta.setUsuarioRegistroId(pUsuarioId);
			vRespuesta.setUsuarioUltimaModificacionId(pUsuarioId);
			vRespuesta.setArchivoId(pArchivoId);
			vRespuesta.setMd5(pMd5);
			vRespuesta.setSha2(pSha2);
			vRespuesta.setCrc(pCrc);
			vRespuesta.setExtension(pExtension);
			vRespuesta.setMime(pMime);
			vRespuesta.setRuta(pRuta);
			vRespuesta.setNombre(pNombre);			
			vRespuesta.setFechaUltimaModificacion(vFechaHoy);
			vRespuesta.setFechaRegistro(vFechaHoy);
			vRespuesta.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);			
			iComponentesArchivosDao.save(vRespuesta);			
			LOG.info("Datos de componentes del archivo registrados.");
		} 
		catch (Exception e) 
		{
			vRespuesta = null;
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
	 * @author: Peter Flores
	 * @Fecha: 30/11/2018
	 * @Descripción: Se corrigió el método	 
	 * @return   Devuelve un valor booleano que representa el estado de los registros que se desarrollo.
	 */
	@Override
	public boolean registrarComponentesCertificados(List<Integer> pTipoComponenteId, Long pComponenteArchivoId, Long pUsuarioId,Long pSistemaId, Long pSolicitudCertificacionId)
	{
		LOG.info("Ingresando registrarComponentesCertificados ");
		boolean vResultado=false;	
		int vRespuestaVerificacion=0;
		SreComponentesCertificados vRespuesta = null;
		try 
		{
			
			for (Integer tipoComponenteId : pTipoComponenteId) 
			{
				vRespuesta = registrarComponenteCertificado(tipoComponenteId , pComponenteArchivoId, pUsuarioId,pSistemaId,pSolicitudCertificacionId);
				
				if(vRespuesta.getComponenteCertificadoId()==null)
				{
					vRespuestaVerificacion++;
				}
			}
			
			vResultado=vRespuestaVerificacion==0 && pTipoComponenteId.size()>0 ?true:false;
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo de recuperar registrarComponentesCertificados: " + vRespuesta);
		}
		
		return vResultado;
	}
	
	/**
	 * Listado de los componentes de archivos de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 20/11/2018
	 * @param pSistemaId, Código único de la entidad sistema.
	 * @return   Devuelve un listado de Dto de tipo Componentes archivos.
	 */
	@Override
	public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId){
		List<SreComponentesArchivos> listaComponentesArchivos=new ArrayList<>(); 
		LOG.info("ingresando a obtieneComponentesArchivos");
		try {
			
			listaComponentesArchivos = iComponentesArchivosDao.obtieneComponentesArchivos(pSistemaId);
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}
		LOG.info("Finalizando Obtiene Componentes Archivos");
		return listaComponentesArchivos;
	}
}
