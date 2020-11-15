package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IObservacionesInsituDao;
import bo.gob.sin.sre.fac.dao.IRegistrosObservacionesInsituDao;
import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
@Repository
@Transactional
public class ObservacionesInsituDaoImpl extends AbstractGenericDao<SreFacObservacionesInsitu> implements IObservacionesInsituDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ObservacionesInsituDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")	  
    @Override
	public List<SreFacObservacionesInsitu> recuperaListaObservacionesInSitu(long pSistemaId, long pSolicitudCertificacionId) 
 	{

    	List<SreFacObservacionesInsitu> vListaSolicitudEntidad = new ArrayList<>();
    	
		try 
		{
			String vHql = "select obs " + 
					"from SreFacObservacionesInsitu obs " + 
					"where obs.estadoOservacionInsituId = :pEstadoOservacionInsituId " +
					"and obs.estadoId='AC' " +
					"and obs.tipoEsquemaId = (select d.tipoEsquemaId " + 
					"from SreDetalleCertificacionesSistemas d " + 
					"where d.estadoId='AC' " + 
					"and d.sistemaId=:pSistemaId " + 
					"and d.solicitudCertificacionId = :pSolicitudCertificacionId) " + 
					"order by obs.observacionInsituId asc ";
			
			vListaSolicitudEntidad =  getSession().createQuery(vHql).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).setParameter("pSistemaId", pSistemaId).setParameter("pEstadoOservacionInsituId", ConstFacturacion.ESTADO_OBSERVACION_INSITU).getResultList();
			LOG.info("Informacion recuperada.");
			
		} 
		catch (Exception e) 
		{
			vListaSolicitudEntidad = null;
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaSolicitudEntidad));
		}
		
		return vListaSolicitudEntidad;
	}

}
