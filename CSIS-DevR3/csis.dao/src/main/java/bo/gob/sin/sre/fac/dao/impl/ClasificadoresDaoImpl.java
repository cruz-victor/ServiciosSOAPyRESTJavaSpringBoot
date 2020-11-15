package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.sre.fac.dao.IClasificadoresDao;
import bo.gob.sin.sre.fac.model.SreClasificadores;

@Repository
@Transactional
public class ClasificadoresDaoImpl extends AbstractGenericDao<SreClasificadores> implements IClasificadoresDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SreClasificadores obtenerEntidadPorCodigoEquivalente(Integer pCodigoEquivalente, String pAgrupador) {
		SreClasificadores vEntidad = new SreClasificadores();
		try {
			String vQuery = "SELECT c FROM SreClasificadores c WHERE c.codigoClasificadorEquivalente = :pCodigo and c.agrupador = :pAgrupador and c.estadoId = 'AC'";
			vEntidad = (SreClasificadores) getSession().createQuery(vQuery).setParameter("pCodigo", pCodigoEquivalente).setParameter("pAgrupador", pAgrupador).getSingleResult();
		} catch (Exception e) {
			
		}
		return vEntidad;
	}
	
}
