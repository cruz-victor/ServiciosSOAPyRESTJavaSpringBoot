package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IDetalleCertificacionesSistemasDao;
import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;
import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;

//IASC
@Repository
@Transactional
public class DetalleCertificacionesSistemasDaoImpl extends AbstractGenericDao<SreDetalleCertificacionesSistemas>implements IDetalleCertificacionesSistemasDao, Serializable {
 
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DetalleCertificacionesSistemasDaoImpl.class);

	//RCR
	@SuppressWarnings("unchecked")
	@Override
	public List<SreDetalleCertificacionesSistemas> obtenerListaDetalleCertificacionSistemas(long pSolicitudCertificacionId, long pSistemaId) {
		List<SreDetalleCertificacionesSistemas> vListaEntidad = new ArrayList<SreDetalleCertificacionesSistemas>();
		try {
			String vhql = "select p, cl.descripcion FROM SreDetalleCertificacionesSistemas p "
					+ "inner join SrtClasificadores cl on (p.tipoEsquemaId = cl.clasificadorId and cl.estadoId = 'AC') "
					+ "WHERE p.sistemaId = :vSistemaId and "
					+ "p.solicitudCertificacionId=:vSolicitudCertificacionId and "
					+ "p.estadoId= 'AC' ";
			List<Object[]> vResultadoQuery = getSession().createQuery(vhql)
					.setParameter("vSolicitudCertificacionId", pSolicitudCertificacionId)
					.setParameter("vSistemaId", pSistemaId).getResultList();
			
			if (vResultadoQuery.size() > 0) 
			{
				for ( Object[] object : vResultadoQuery ) 
				{
					if(object[0] instanceof SreDetalleCertificacionesSistemas)
					{
						SreDetalleCertificacionesSistemas vSreDetalleCertificacionesSistemas= new SreDetalleCertificacionesSistemas();
						vSreDetalleCertificacionesSistemas = (SreDetalleCertificacionesSistemas)object[0];						
						vSreDetalleCertificacionesSistemas.setTipoEsquemaDescripcion((String)object[1]);
						vListaEntidad.add(vSreDetalleCertificacionesSistemas);
					}
		        }
			}
			
			LOG.info("Informacion recuperada.");
			return vListaEntidad; 
		} 
		catch (Exception e) 
		{
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
	
	//RCR
	@Override
	public SreDetalleCertificacionesSistemas obtenerDetalleCertificacionSistemas(long pDetalleCertificacionSistemaId) {
		SreDetalleCertificacionesSistemas vEntidad = new SreDetalleCertificacionesSistemas();
		try {
			String vhql = "FROM SreDetalleCertificacionesSistemas p "
					+ "WHERE p.detalleCertificacionSistemaId=:vDetalleCertificacionSistemaId and "
					+ "p.estadoId= 'AC' ";
			vEntidad = (SreDetalleCertificacionesSistemas) getSession().createQuery(vhql).setParameter("vDetalleCertificacionSistemaId", pDetalleCertificacionSistemaId).getSingleResult();					
			LOG.info("Informacion recuperada.");
			return vEntidad;
		} catch (NoResultException e) {
			return new SreDetalleCertificacionesSistemas();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
}
