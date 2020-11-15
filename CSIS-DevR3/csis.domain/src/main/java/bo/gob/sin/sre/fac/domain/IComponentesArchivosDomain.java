package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.sre.fac.model.SreComponentesArchivosTmp;

public interface IComponentesArchivosDomain {

	
	/***
	 * Actualiza estadoId de la entidad SreComponentesArchivosTmp
	 * 
	 * @author wilson.limachi
	 * @return la entidad List<SreComponentesArchivosTmp>
	 * @fecha 30/11/2018
	 */
	
	 public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId, Long pSolicitudCertificacionId); 
	 
		/**
		 * Recuperar Lista Detalle de Solicitud Certificacion por ArchivoId.
		 * @author: Wilson Limachi
		 * @Fecha: 24/09/2019
		 * @param pSistemaId
		 * @return Devuelve una lista de  SreComponentesArchivos.
		 */
		public List<SreComponentesArchivos> obtieneComponentesArchivosLista(Long pArchivoId);
	
}
