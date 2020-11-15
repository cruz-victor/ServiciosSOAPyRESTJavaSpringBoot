package bo.gob.sin.sre.fac.dao.impl;



import java.io.Serializable;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.ICufdDao;
import bo.gob.sin.sre.fac.model.SreRegistrosCufd;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Repository
@Transactional
public class CufdDaoImpl extends AbstractGenericDao<SreRegistrosCufd> implements ICufdDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = LoggerFactory.getLogger(CufdDaoImpl.class);
	
	//FRRA
	public long verificaCufd(Long pSistemaId, String pCufd, Integer pSucursalId, int pEstadoId) {

		String vQuery = "SELECT COUNT(c) FROM SreRegistrosCufd c WHERE c.sistemaId = :pSistemaId and c.sucursalId = :pSucursalId and c.estadoId = 'AC' and c.cufd = :pCufd and estadoRegistroCufdId = :pEstadoId";
		long vCantidad = (long) getSession().createQuery(vQuery).setParameter("pSistemaId", pSistemaId).setParameter("pSucursalId", pSucursalId).setParameter("pCufd", pCufd).setParameter("pEstadoId", pEstadoId).getSingleResult();
		return vCantidad;
	}
	
	//IASC
	public long verificaCufdPorNitSucursal(Long pNit, String pCufd, Integer pSucursalId, int pEstadoId) {

		String vQuery = "SELECT COUNT(c) FROM SreRegistrosCufd c WHERE c.nitEmisor = :pNit and c.sucursalId = :pSucursalId and c.estadoId = 'AC' and c.cufd = :pCufd and estadoRegistroCufdId = :pEstadoId";
		long vCantidad = (long) getSession().createQuery(vQuery).setParameter("pNit", pNit).setParameter("pSucursalId", pSucursalId).setParameter("pCufd", pCufd).setParameter("pEstadoId", pEstadoId).getSingleResult();
		return vCantidad;
	}
	
	 /**
	    * Objetivo: Obtener ultimo registro cufd activo
	    * Creado por: Wilson Limachi.
	    * Fecha: 18/05/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 20/07/2018
	 */
	@Override
	public SreRegistrosCufd recuperCufdPorNitSucursalSistema(Long pNit, Integer pSucursalId, Long pSistemaId) 
	{
		SreRegistrosCufd vSreRegistrosCufd = new SreRegistrosCufd();
		LOG.info("Ingresando a recuperarCufdPorNitSucursalSistema");
		try
		{
			String vQuery = "SELECT c FROM SreRegistrosCufd c WHERE c.nitEmisor = :pNit and c.sucursalId = :pSucursalId and c.estadoRegistroCufdId = :pEstadoRegistroCufdId and c.sistemaId = :pSistemaId and c.cufdId=(SELECT max(d.cufdId) FROM SreRegistrosCufd d WHERE d.nitEmisor = :pNit and d.sucursalId = :pSucursalId and d.estadoRegistroCufdId = :pEstadoRegistroCufdId and d.sistemaId = :pSistemaId)";
			vSreRegistrosCufd = (SreRegistrosCufd) getSession().createQuery(vQuery).setParameter("pNit", pNit).setParameter("pSucursalId", pSucursalId).setParameter("pSistemaId", pSistemaId).setParameter("pEstadoRegistroCufdId", ConstFacturacion.ESTADO_REGISTRO_CUFD_VIGENTE).getSingleResult();
		    LOG.info("Informacion recuperada exitosamente");
		}
		catch (Exception e) {
			LOG.info("error al recuperar la informacion");
			vSreRegistrosCufd = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(pNit));
		}
		LOG.info("saliendo de recuperarCufdPorNitSucursalSistema");
		return vSreRegistrosCufd;
	}
	
	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene registro cufd vigente
	 * @param pNit        Nit del emisor
	 * @param pSucursalId sucursal del emisor
	 * @return registro de tipo SreRegistrosCufd
	 * @fecha 03/10/2018
	 */
	@Override
	public SreRegistrosCufd obtenerRegistroCufdVigente(Long pNit, Integer pSucursalId) {
		try {
			String vQuery = "SELECT c FROM SreRegistrosCufd c WHERE c.nitEmisor = :pNit and c.sucursalId = :pSucursalId and c.estadoRegistroCufdId = 672 and c.cufdId=(SELECT max(d.cufdId) FROM SreRegistrosCufd d WHERE d.nitEmisor = :pNit and d.sucursalId = :pSucursalId and d.estadoRegistroCufdId = 672)";
			return (SreRegistrosCufd) getSession().createQuery(vQuery).setParameter("pNit", pNit)
					.setParameter("pSucursalId", pSucursalId).getSingleResult();
		} catch (NoResultException e) {
			return new SreRegistrosCufd();
		} catch (Exception e) {
			LOG.error("error obtenerRegistroCufd");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
	
}
