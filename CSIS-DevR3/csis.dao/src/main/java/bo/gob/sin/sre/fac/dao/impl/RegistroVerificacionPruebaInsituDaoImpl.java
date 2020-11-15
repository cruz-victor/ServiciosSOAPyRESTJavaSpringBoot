package bo.gob.sin.sre.fac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IRegistroVerificacionPruebaInsituDao;
import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;
import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreRegistroVerificacionPruebaInsitu;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Repository
@Transactional
public class RegistroVerificacionPruebaInsituDaoImpl extends AbstractGenericDao<SreRegistroVerificacionPruebaInsitu> implements IRegistroVerificacionPruebaInsituDao 
{
	private static final long serialVersionUID = 1L;
	static final Logger LOG = LoggerFactory.getLogger(RegistroVerificacionPruebaInsituDaoImpl.class);
	
	@Override
	public List<SreRegistroVerificacionPruebaInsitu> recuperarListaRegistroObservacionesInSitu(long pSolicitudCertificacionId) 
 	{

    	List<SreRegistroVerificacionPruebaInsitu> vListaSolicitudEntidad = new ArrayList<>();
    	
    	
    	
		try 
		{
			String vHql = "select r, cl.descripcion,  (select s.observacion from SreFacObservacionesInsitu s where s.observacionInsituId = r.observacionInsituId and r.estadoId='AC') " + 
					"from SreRegistroVerificacionPruebaInsitu r " +
					"inner join SrtClasificadores cl on (r.estadoPruebaId = cl.clasificadorId and cl.estadoId = 'AC')" +
					"where r.estadoId='AC' " +					
					"and r.solicitudCertificacionId = :pSolicitudCertificacionId " + 
					"order by r.fechaUltimaModificacion desc ";
			
			List<Object[]> vResultadoQuery =   getSession().createQuery(vHql).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			
			
			if (vResultadoQuery.size() > 0) 
			{
				for ( Object[] object : vResultadoQuery ) 
				{
					if(object[0] instanceof SreRegistroVerificacionPruebaInsitu)
					{
						SreRegistroVerificacionPruebaInsitu vSreDetalleCertificacionesSistemas= new SreRegistroVerificacionPruebaInsitu();
						vSreDetalleCertificacionesSistemas = (SreRegistroVerificacionPruebaInsitu)object[0];	
						vSreDetalleCertificacionesSistemas.setEstadoPruebaDescripcion((String)object[1]);
						vSreDetalleCertificacionesSistemas.setDescripcionPrueba((String)object[2]);
						vListaSolicitudEntidad.add(vSreDetalleCertificacionesSistemas);
					}
		        }
			}
			
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
