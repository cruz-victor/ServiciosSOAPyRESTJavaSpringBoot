package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;
import bo.gob.sin.str.ccs.dao.GenericDao;

//RCR
public interface IDiagramasCertificacionesDao extends GenericDao<SreDiagramasCertificaciones>{

	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha:  24/09/2019
	 * @param   pEtapaId, Código unico de la Etapa.
	 * 		    pSistemaId, Código unico del sistema.
	 * 		    pSolicitudCertificacionId, Código unico de la solicitud de certificación.	   		   
	 * @return  Devuelve la lista SreDiagramasCertificaciones.
	 */
	public List<SreDiagramasCertificaciones> recuperaListaDiagramasCertificaciones(long pEtapaId, long pSistemaId, long pSolicitudCertificacionId);
}
