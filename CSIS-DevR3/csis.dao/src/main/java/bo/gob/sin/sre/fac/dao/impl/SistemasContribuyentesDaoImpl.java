package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.ISistemasContribuyentesDao;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
@Repository
@Transactional
public class SistemasContribuyentesDaoImpl extends AbstractGenericDao<SreSistemasContribuyentes> implements ISistemasContribuyentesDao, Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private static final Logger LOG = LoggerFactory.getLogger(SistemasContribuyentesDaoImpl.class);
	
	
//TODO

@Override
	public SreSistemasContribuyentes actualizarListaPruebasSistema(Long pSistemaContribuyenteId, String pObservacion, Integer pEstadoSistemaContribuyenteId, Date pFechaUltimaModificacion) {
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		
//		try {
//			String vhql = "UPDATE SreSistemasContribuyentes SET estadoSistemaContribuyenteId = :estadoSistemaContribuyenteId, observacion = :observacion, fechaUltimaModificacion = :fechaUltimaModificacion "
//					+ "WHERE sistemaContribuyenteId = :sistemaContribuyenteId and estadoId = 'AC' ";
//			vEntidad = (SreSistemasContribuyentes) getSession().createQuery(vhql).setParameter("sistemaContribuyenteId", pSistemaContribuyenteId)
//					.setParameter("observacion", pObservacion)
//					.setParameter("estadoSistemaContribuyenteId", pEstadoSistemaContribuyenteId)
//					.setParameter("fechaUltimaModificacion", pFechaUltimaModificacion)
//					.getSingleResult();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return vEntidad;
	}
	/**
	    * Objetivo: Recuperar Datos Sistema Contribuyente.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	 */
//TODO
	@Override
	public SreSistemasContribuyentes recuperarDatosSistemaContribuyente(Long pContribuyenteId, Integer pEstadoSistemaContribuyenteId) {
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		LOG.info("Ingresando a recuperarDatosSistemaContribuyente ");
		try {
			String vhql = "SELECT c FROM SreSistemasContribuyentes c "
					+ "WHERE c.contribuyenteId = :pContribuyenteId and c.estadoId = 'AC' and c.estadoSistemaContribuyenteId = :pEstadoSistemaContribuyenteId ";
			vEntidad = (SreSistemasContribuyentes) getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId).setParameter("pEstadoSistemaContribuyenteId", pEstadoSistemaContribuyenteId).getSingleResult();
			LOG.info("Informacion Recuperada");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return vEntidad;
	}
	
	/**
	    * Objetivo: Recuperar Lista Datos Sistema Contribuyente.
	    * Creado por: Wilson Limachi.
	    * Fecha: 14/04/2018
	 */
	//TODO
	@Override
	public List<SreSistemasContribuyentes> recuperarListaDatosSistemaContribuyente(Long pContribuyenteId, Integer pEstadoSistemaContribuyenteId) {
		List<SreSistemasContribuyentes> vEntidad = new ArrayList<>();
		LOG.info("Ingresando a recuperarDatosSistemaContribuyente ");
		try {
			String vhql = "SELECT c FROM SreSistemasContribuyentes c "
					+ "WHERE c.contribuyenteId = :pContribuyenteId and c.estadoId = 'AC' and c.estadoSistemaContribuyenteId = :pEstadoSistemaContribuyenteId ";
			vEntidad = getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId).setParameter("pEstadoSistemaContribuyenteId", pEstadoSistemaContribuyenteId).getResultList();
			LOG.info("Informacion Recuperada");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return vEntidad;
	}
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	 */
	//TODO
	@Override
	public SreSistemasContribuyentes actualizarEstadosSistemasContribuyentes(Long pSistemasContribuyentesId, Integer pEstadoId, int pModalidad) {
		SreSistemasContribuyentes vResultadoActualizacion = new SreSistemasContribuyentes();
		SreSistemasContribuyentes vResultadoEntidad = this.recuperarRegistroSistemaContribuyente(pSistemasContribuyentesId);
		Date vFechaActual = new Date();
		LOG.info("Ingresando ActualizatEstadosSistemas.");
		if(pSistemasContribuyentesId > 0 && pSistemasContribuyentesId != null && pEstadoId > 0 && pEstadoId != null) {
			try {
				vResultadoEntidad.setFechaUltimaModificacion(vFechaActual);
				vResultadoEntidad.setEstadoSistemaContribuyenteId(pEstadoId);
				getSession().save(vResultadoEntidad);
				LOG.info("Guardando ActualizatEstadosSistemas.");
				
			} catch (Exception e) {
				LOG.info("Informacion no recuperada.");
				LogExcepcion.registrar(e, LOG, MethodSign.build(vResultadoEntidad));
			}
		}
		else {
			LOG.info("Informacion no recuperada.");
			LOG.info("False");
		}
		
		return vResultadoActualizacion;
	}
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	 */
	//TODO
	@Override
	public SreSistemasContribuyentes recuperarRegistroSistemaContribuyente(Long pSistemasContribuyentesId) {
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		LOG.info("Ingresando a recuperarRegistroSistemaContribuyente.");
		try {
			String vhql = "FROM SreSistemasContribuyentes p WHERE p.sistemaContribuyenteId=:id";
			vEntidad = (SreSistemasContribuyentes)getSession().createQuery(vhql).setParameter("id", pSistemasContribuyentesId).getSingleResult();
			LOG.info("Informacion Recuperada.");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Informacio no recuperada.");
		}
		return vEntidad;

	}
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	 */
	@Override
	public SreSistemasContribuyentes recuperarRegistroSistemaContribuyenteSistemasId(Long pSistemasId, Long pContribuyenteId, int pModalidad) 
	{
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		LOG.info("Ingresando recuperarRegistroSistemaContribuyenteSistemasId.");
		try {
			String vhql = "FROM SreSistemasContribuyentes p WHERE p.sistemaId=:sistemaId and p.contribuyenteId=:contribuyenteId and p.modalidadFacturacionId = :pModalidad";
			vEntidad = (SreSistemasContribuyentes)getSession().createQuery(vhql).setParameter("sistemaId", pSistemasId).setParameter("contribuyenteId", pContribuyenteId).setParameter("pModalidad", pModalidad).getSingleResult();

			LOG.info("Estado Actualizado.");
		} 
		catch (Exception e) 
		{
			vEntidad =  null;
			e.printStackTrace();
		}
		return vEntidad;

	}
	
	/**
     * Objetivo: devolver los sistemas contribuyentes.
     * Creado por: Ivan Angel Salas.
     * Fecha: 
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 26/07/2018
     */
	//TODO
		@Override
		public List<SreSistemasContribuyentes> recuperarSistemasContribuyente(Long pContribuyenteId, Integer pEstadoSistemaContribuyenteId, Long pModalidad) {
			List<SreSistemasContribuyentes> vEntidad = new ArrayList<SreSistemasContribuyentes>();
			LOG.info("ingresando a recuperar sistemas contribuyentes");
			try {
				String vhql = "SELECT c FROM SreSistemasContribuyentes c "
						+ "WHERE c.contribuyenteId = :pContribuyenteId and c.estadoId = 'AC' and c.estadoSistemaContribuyenteId = :pEstadoSistemaContribuyenteId and c.modalidadFacturacionId =:pModalidad ";
				vEntidad = getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId).setParameter("pEstadoSistemaContribuyenteId", pEstadoSistemaContribuyenteId).setParameter("pModalidad", pModalidad).getResultList();
			LOG.info("datos recuperados exitosamente");
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return vEntidad;
		}
		
		/**
		 * Verificar SistemaId y ContribuyenteId
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 25/06/2018
		 * @modificadoPor: 
		 * @FechaModificacion: 
		 * @param pSistemaId, número de identificación de sistema
		 * @param pContribuyenteId, Número de identificacion , nit
		 * @return   Devuelve valor mayor a 0 si existe registros y menor a 0 si no hay registros.
		 */
		//TODO
		@Override
		public long verificaSistemaContribuyente(Long pSistemaId, Long pContribuyenteId, Integer pEstadoSistemaContribuyenteId) {
			LOG.info("Ingresando verificaSistemaContribuyente.");
			long vCantidad=0;
			try {
				String vQuery = "SELECT COUNT(c) FROM SreSistemasContribuyentes c WHERE c.sistemaId = :pSistemaId and c.contribuyenteId = :pContribuyenteId and c.estadoId = 'AC' and c.estadoSistemaContribuyenteId = :pEstadoSistemaContribuyenteId";
				vCantidad = (long) getSession().createQuery(vQuery).setParameter("pSistemaId", pSistemaId).setParameter("pContribuyenteId", pContribuyenteId).setParameter("pEstadoSistemaContribuyenteId", pEstadoSistemaContribuyenteId).getSingleResult();
			} catch (Exception e) {
				LOG.info("Informacion no recuperada.");
				e.printStackTrace();
			}
			return vCantidad;
		}
		
		/**
		 * Objetivo:Verificar Guardado Registro Proveedor
		 * 
		 * @author: Wilson Limachi
		 * @Fecha: 07/08/2018
		 * @param pSreSistemasContribuyentes: objeto de tipo solicitud
		 * @return   Devuelve un número 0 error, 1 existeAsociacion, 2 noExisteAsociacion
		 */
		//TODO
		@Override
		public Long existeAsociacionContribuyenteSistemaProveedor(Long pIdContribuyente, Long pIdContribuyenteProveedor, Long pIdSistema, Integer pEstadoSistemaContribuyente) 
		{
			LOG.info("Ingresando existeAsociacionContribuyenteSistemaProveedor pIdContribuyenteProveedor={}", pIdContribuyenteProveedor);
			Long vCantidad=0L;
			
			try 
			{
				String vQuery = "SELECT COUNT(c) FROM SreSistemasContribuyentes c WHERE c.sistemaId = :vSistemaId and c.contribuyenteId = :vContribuyenteId and c.contribuyenteProveedorId = :vContribuyenteProveedorId and c.estadoId = 'AC' and c.estadoSistemaContribuyenteId = :vEstadoSistemaContribuyenteId";
				vCantidad = (Long) getSession().createQuery(vQuery).setParameter("vSistemaId", pIdSistema).setParameter("vContribuyenteId", pIdContribuyente).setParameter("vContribuyenteProveedorId", pIdContribuyenteProveedor).setParameter("vEstadoSistemaContribuyenteId", pEstadoSistemaContribuyente).getSingleResult();
				
				vCantidad = vCantidad > 0? 1L:2L;
				
			} 
			catch (Exception e) 
			{
				vCantidad=0L;
				LOG.info("Informacion no recuperada.");
				e.printStackTrace();
			}
			LOG.info("Saliendo existeAsociacionContribuyenteSistemaProveedor pIdContribuyenteProveedor={}", pIdContribuyenteProveedor);
			
			return vCantidad;							
		}
		
		//TODO
		//IASC - Recupera la lista de sistemas asociados del proveedor - 24/09/2018 
		@Override
		public List<SreSistemasContribuyentes> recuperarSistemasAsociadosProveedor(Long pContribuyenteProveedorId, Integer pEstadoSistemaContribuyente) 
		{
			List<SreSistemasContribuyentes> vEntidad = new ArrayList<SreSistemasContribuyentes>();
			try 
			{
				String vQuery = "SELECT c FROM SreSistemasContribuyentes c WHERE c.contribuyenteProveedorId = :pContribuyenteProveedorId and c.estadoId = 'AC' and c.estadoSistemaContribuyenteId = :pEstadoSistemaContribuyente";
				vEntidad = getSession().createQuery(vQuery).setParameter("pContribuyenteProveedorId", pContribuyenteProveedorId).setParameter("pEstadoSistemaContribuyente", pEstadoSistemaContribuyente).getResultList();
			} 
			catch (Exception e) 
			{
				vEntidad = null;
				e.printStackTrace();
			}
			LOG.info("Saliendo recuperaListaSistemasAsociacionaProveedor pContribuyenteProveedorId={}", pContribuyenteProveedorId);
			return vEntidad;							
		}
}
