package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IContribuyentesModalidadesDao;
import bo.gob.sin.sre.fac.model.SreContribuyentesModalidades;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
//IASC
@Repository
@Transactional
public class ContribuyentesModalidadesDaoImpl extends AbstractGenericDao<SreContribuyentesModalidades>
		implements IContribuyentesModalidadesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ContribuyentesModalidadesDaoImpl.class);

	// IASC - Obtiene la modalidad del contribuyente - 23/11/2018
	@Override
	public long obtenerModalidadContribuyenteNormativa(Long pNit, Integer pModalidadId) {
		LOG.info("Ingresando obtener Modalidad Contribuyente.");
		String vhql = "select COUNT(p) FROM SreContribuyentesModalidades p WHERE p.nit = :pNit "
				+ "and p.estadoModalidadContribuyenteId = :pEstadoModalidad "
				+ "and p.modalidadFacturacionId = :pModalidadId and p.estadoId = 'AC'";
		long vCantidad = (long) getSession().createQuery(vhql).setParameter("pNit", pNit).setParameter("pEstadoModalidad", ConstFacturacion.ESTADO_MODALIDAD_CONTRIBUYENTE_ID_HABILITADO)
				.setParameter("pModalidadId", pModalidadId).getSingleResult();
		LOG.info("Informacion recuperada.");
		return vCantidad;
	}

	@Override
	public boolean verificarContribuyenteRequiereVisitaInsitu(Long pNit) {
		LOG.info("Ingresando verificar Contribuyente Requiere Visita Insitu.");
		String vhql = "select COUNT(p) FROM SreContribuyentesModalidades p WHERE p.nit = :pNit "
				+ "and p.estadoModalidadContribuyenteId = :pEstadoModalidad "
				+ "and p.inSitu == 1 and p.estadoId = 'AC'";
		long vCantidad = (long) getSession().createQuery(vhql).setParameter("pNit", pNit).setParameter("pEstadoModalidad", ConstFacturacion.ESTADO_MODALIDAD_CONTRIBUYENTE_ID_HABILITADO).
				uniqueResult();
		LOG.info("Informacion recuperada.");
		return vCantidad > 0;
	}
	
	// IASC - Obtiene la modalidad del contribuyente
	// WLC - MODIFICADO
	@Override
	public SreContribuyentesModalidades obtenerModalidadContribuyente(Long pNit) 
	{
		LOG.info("Ingresando obtener Modalidad Contribuyente.");
		
		SreContribuyentesModalidades vEntidad = new SreContribuyentesModalidades();
		
		String vhql = "select p FROM SreContribuyentesModalidades p WHERE p.nit = :pNit "
				+ "and p.estadoModalidadContribuyenteId = :pEstadoModalidad "
				+ "and p.estadoId = 'AC'";		
		try
		{
			vEntidad = (SreContribuyentesModalidades) getSession().createQuery(vhql).setParameter("pNit", pNit).setParameter("pEstadoModalidad", ConstFacturacion.ESTADO_MODALIDAD_CONTRIBUYENTE_ID_HABILITADO).getSingleResult();
		}
		catch (Exception e) 
		{
			vEntidad = null;
			e.printStackTrace();
		}
		
		return vEntidad;
	}
}
