package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IPruebasAutomaticasDao;
import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
@Repository
@Transactional(rollbackFor = Exception.class)
public class PruebasAutomaticasDaoImpl extends AbstractGenericDao<SrePruebasAutomaticas>
		implements IPruebasAutomaticasDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasAutomaticasDaoImpl.class);

	// IASC - Recupera la lista de pruebas automaticas por documento fiscal -
	// 24/11/2018
	@SuppressWarnings("unchecked")
	@Override
	public List<SrePruebasAutomaticas> obtenerPruebasAutomaticasPorDocFiscal(Integer pTipoDocumentoFiscalId, Integer pModalidadId) {
		LOG.info("Ingresando obtenerPruebasAutomaticasPorDocFiscal.");
		List<SrePruebasAutomaticas> vEntidad = new ArrayList<SrePruebasAutomaticas>();
		try {
			String vhql = "FROM SrePruebasAutomaticas p WHERE p.tipoDocumentoSectorId = :pTipoDocumentoFiscalId and "
					+ "p.modalidadFacturacionId = :pModalidadId and "
					+ "p.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).setParameter("pTipoDocumentoFiscalId", pTipoDocumentoFiscalId).setParameter("pModalidadId", pModalidadId)
					.getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vEntidad;
	}

	
	
	@Override
	public SrePruebasAutomaticas obtenerPruebasAutomaticasPorId (long pPruebaAutomaticaId)
	{   SrePruebasAutomaticas   vPruebaAutomatica= new SrePruebasAutomaticas();
		try {
			    
			
			 vPruebaAutomatica=this.findById(pPruebaAutomaticaId);
			
			
		}
		catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vPruebaAutomatica));
		}
		
		return vPruebaAutomatica;
	}
	

}
