package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IDiagramasCertificacionesDao;
import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

//IASC
@Repository
@Transactional
public class DiagramasCertificacionesDaoImpl extends AbstractGenericDao<SreDiagramasCertificaciones>implements IDiagramasCertificacionesDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(DiagramasCertificacionesDaoImpl.class);

	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha:  24/09/2019
	 * @param   pEtapaId, C�digo unico de la Etapa.
	 * 		    pSistemaId, C�digo unico del sistema.
	 * 		    pSolicitudCertificacionId, C�digo unico de la solicitud de certificaci�n.	   		   
	 * @return  Devuelve la lista SreDiagramasCertificaciones.
	 */
	@SuppressWarnings("unchecked")
	public List<SreDiagramasCertificaciones> recuperaListaDiagramasCertificaciones(long pEtapaId, long pSistemaId, long pSolicitudCertificacionId){
				
		List<SreDiagramasCertificaciones> vDiagramasCertificaciones = new ArrayList<>();
		try {
			String vhql = " select p "
					    + "FROM SreDiagramasCertificaciones p "
						+ " WHERE p.estadoId = 'AC' "
						+ " and p.sistemaId =:pSistemaId"
						+ " and p.solicitudCertificacionId =:pSolicitudCertificacionId"
						+ " and p.etapaId =:pEtapaId"
					    + " order by p.fechaRegistro desc";
			
			vDiagramasCertificaciones = getSession().createQuery(vhql)
					.setParameter("pSistemaId", pSistemaId)
					.setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId)
					.setParameter("pEtapaId", pEtapaId).getResultList();			

		
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vDiagramasCertificaciones;		
	}
}
