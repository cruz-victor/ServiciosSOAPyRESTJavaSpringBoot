package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IRegistrosObservacionesInsituDao;
import bo.gob.sin.sre.fac.model.SreFacObservacionesInsitu;
import bo.gob.sin.sre.fac.model.SreFacRegistrosObservacionesInsitu;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
@Repository
@Transactional
public class RegistrosObservacionesInsituDaoImpl extends AbstractGenericDao<SreFacRegistrosObservacionesInsitu> implements IRegistrosObservacionesInsituDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasManualesDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<SreFacRegistrosObservacionesInsitu> obtieneListaVerificacionInsitu(long pSolicitudCertificacionId) {
		
		List<SreFacRegistrosObservacionesInsitu> vListadoObservaciones = new ArrayList<>();
		try {

			String vhql = "FROM SreFacRegistrosObservacionesInsitu p WHERE p.estadoId = 'AC' and p.solicitudCertificacionId =:pSolicitudCertificacionId"
					      + " order by p.fechaVerificacion desc";
			vListadoObservaciones = (List<SreFacRegistrosObservacionesInsitu>) getSession().createQuery(vhql)
										.setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			

			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vListadoObservaciones;
		
	}
	@Override
	public SreFacRegistrosObservacionesInsitu registroObservacionComponente(SreFacRegistrosObservacionesInsitu pRegObservacionComponente) {
		try {
	             getSession().save(pRegObservacionComponente);
	
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pRegObservacionComponente));
		}

	     return pRegObservacionComponente;
	
	
	
	}
}
