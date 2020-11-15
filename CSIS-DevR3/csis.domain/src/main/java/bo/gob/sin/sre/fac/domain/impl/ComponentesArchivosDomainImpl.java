package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IComponenteArchivosDao;
import bo.gob.sin.sre.fac.domain.IComponentesArchivosDomain;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional
public class ComponentesArchivosDomainImpl implements IComponentesArchivosDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ComponentesArchivosDomainImpl.class);
	@Autowired
	IComponenteArchivosDao iComponenteArchivosDao;
	
	/***
	 * Actualiza estadoId de la entidad SreComponentesArchivosTmp
	 * 
	 * @author wilson.limachi
	 * @return la entidad List<SreComponentesArchivosTmp>
	 * @fecha 30/11/2018
	 */
	 @Override
	 public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId, Long pSolicitudCertificacionId) 
	 {
		 LOG.info("Ingresando a obtenerComponenteArchivoId");
		 List<SreComponentesArchivos> vRespuesta = new ArrayList<>();
		 
		 try 
		 {
			 vRespuesta = iComponenteArchivosDao.obtieneComponentesArchivos(pSistemaId, pSolicitudCertificacionId);
		 }
		 catch(Exception e) 
		 {
			 vRespuesta = null;
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de obtenerComponenteArchivoId: " + vRespuesta);
		 }
		 
		 return vRespuesta;
	 }
	 
		/**
		 * Recuperar Lista Detalle de Solicitud Certificacion por ArchivoId.
		 * @author: Wilson Limachi
		 * @Fecha: 24/09/2019
		 * @param pSistemaId
		 * @return Devuelve una lista de  SreComponentesArchivos.
		 */
		@Override
		public List<SreComponentesArchivos> obtieneComponentesArchivosLista(Long pArchivoId)
		 {
			 LOG.info("Ingresando a obtenerComponenteArchivoId");
			 List<SreComponentesArchivos> vRespuesta = new ArrayList<>();
			 
			 try 
			 {
				 vRespuesta = iComponenteArchivosDao.obtieneComponentesArchivos(pArchivoId);
			 }
			 catch(Exception e) 
			 {
				 vRespuesta = null;
				 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
				 LOG.info("Saliendo de obtenerComponenteArchivoId: " + vRespuesta);
			 }
			 
			 return vRespuesta;
		 }
	
}
