package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IPruebasDao;
import bo.gob.sin.sre.fac.model.SrePruebas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
@Repository
@Transactional
public class PruebasDaoImpl extends AbstractGenericDao<SrePruebas> implements IPruebasDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(PruebasDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SrePruebas> obtenerListaPruebas(Integer pTipoPruebaId){
		LOG.info("Ingresando obtenerListaPruebas.");
		List<SrePruebas> vEntidad = new ArrayList<SrePruebas>();
		try {
			String vhql = "FROM SrePruebas p WHERE p.tipoPruebaId = :tipoPruebaId and p.estadoId = 'AC'";
			vEntidad =getSession().createQuery(vhql).setParameter("tipoPruebaId", pTipoPruebaId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacion no recuperada.");
		}
		return vEntidad;
	}
	/**
	 * Obtener nombre prueba del sistema
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 19/06/2018
	 * @param pruebaId, número identificador de prueba
	 * @return   Devuelve objeto SrePruebas.
	 */
	// TODO: viene del antiguo
	@Override
	public SrePruebas obtenerPrueba(Long pPruebaId){
		LOG.info("Ingresando obtenerPrueba.");
		SrePruebas vEntidad = new SrePruebas();
		try {
			String vhql = "FROM SrePruebas p WHERE p.pruebaId = :pruebaId and p.estadoId = 'AC'";
			vEntidad = (SrePruebas)getSession().createQuery(vhql).setParameter("pruebaId", pPruebaId).getSingleResult();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			e.printStackTrace();
		}
		return vEntidad;
	}
	// TODO: viene del antiguo
	@Override
	public SrePruebas obtenerListasPrueba(Integer pTipoPruebaId) {
		LOG.info("Ingresando a obtenerListasPrueba");
		SrePruebas vEntidad = new SrePruebas();
		try {
			String vhql = "FROM SrePruebas p WHERE p.tipoPruebaId = :tipoPruebaId and p.estadoId = 'AC'";
			vEntidad =(SrePruebas)getSession().createQuery(vhql).setParameter("tipoPruebaId", pTipoPruebaId).getResultList();
			LOG.info("Informacion recuperada.");
			
		} catch (Exception e) {
			LOG.info("Informacion no recuperada");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}
		return vEntidad;
	}	
}
