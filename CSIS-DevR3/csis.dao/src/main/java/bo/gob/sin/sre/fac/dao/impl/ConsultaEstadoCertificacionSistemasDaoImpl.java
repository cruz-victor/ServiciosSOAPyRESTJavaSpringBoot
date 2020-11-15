package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IConsultaEstadoCertificacionSistemasDao;
import bo.gob.sin.sre.fac.model.SreSistemasCertificacionEstado;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
@Repository
@Transactional
public class ConsultaEstadoCertificacionSistemasDaoImpl  extends AbstractGenericDao<SreSistemasCertificacionEstado> implements IConsultaEstadoCertificacionSistemasDao, Serializable {
	
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 static final Logger LOG = LoggerFactory.getLogger(ConsultaEstadoCertificacionSistemasDaoImpl.class);
	    
	 	@SuppressWarnings("unchecked")	  
	    @Override
		public List<SreSolicitudCertificacion> recuperaListaEstadoCertificacionSistemas(long personaContribuyenteId) {

	    	List<SreSolicitudCertificacion> vListaSolicitudEntidad = new ArrayList<>();
	    	
			try {
				String vHqlSistemasCertificados = "SELECT sc "
												+ "FROM SreSolicitudCertificacion as sc, SreSistemas as s "
												+ "WHERE sc.sistemaId=s.sistemaId "
												+ "and sc.personaContribuyenteId = :personaContribuyenteId "
												+ "and sc.estadoId = 'AC' ";
				vListaSolicitudEntidad =  getSession().createQuery(vHqlSistemasCertificados).setParameter("personaContribuyenteId", personaContribuyenteId).getResultList();
				LOG.info("Informacion recuperada.");
				
			} catch (Exception e) {
				LOG.info("Informacion no recuperada.");
				LogExcepcion.registrar(e, LOG, MethodSign.build(vListaSolicitudEntidad));
			}
			
			return vListaSolicitudEntidad;
		}
	 	

}
