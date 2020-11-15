package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IRegistroPruebasManualesDao;
import bo.gob.sin.sre.fac.model.SreDetalleSolicitudesCertificaciones;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
@Repository
@Transactional
public class RegistrosPruebasManualesDaoImpl extends AbstractGenericDao<SreFacRegistrosPruebasManuales>
		implements IRegistroPruebasManualesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(RegistrosPruebasManualesDaoImpl.class);

	@Transactional
	@Override
	public SreFacRegistrosPruebasManuales modificaObservacionEstado(Long RegistroPruebaManualId, long pUsuario, int pEstado) 
	{		
		System.out.println("Entra al metodo de modificación");
		SreFacRegistrosPruebasManuales vEntidadRecuperado = new SreFacRegistrosPruebasManuales();
		
		try 
		{
			vEntidadRecuperado = this.findById(RegistroPruebaManualId);

			vEntidadRecuperado.setEstadoPruebaId(pEstado);
			vEntidadRecuperado.setUsuarioUltimaModficacionId(pUsuario);
			vEntidadRecuperado.setFechaUltimaModificacion(new Date());
			this.saveOrUpdate(vEntidadRecuperado);
		} 
		catch (Exception e) 
		{
			LOG.info("Informacion no recuperada.");
			e.printStackTrace();
			vEntidadRecuperado = null;
		}
		
		return vEntidadRecuperado;
	}
	
	@Transactional
	@Override
	public boolean modificaObservacionEstado(SreFacRegistrosPruebasManuales pPruebaManual) {

		System.out.println("Entra al metodo d emodificado");
		boolean vResp = true;
		try {

			SreFacRegistrosPruebasManuales vEntidadRecuperado = new SreFacRegistrosPruebasManuales();
			vEntidadRecuperado = this.findById(pPruebaManual.getRegistroPruebaManualId());

			vEntidadRecuperado.setEstadoPruebaId(pPruebaManual.getEstadoPruebaId());
			vEntidadRecuperado.setObservaciones(pPruebaManual.getObservaciones());
			vEntidadRecuperado.setUsuarioUltimaModficacionId(pPruebaManual.getUsuarioUltimaModficacionId());
			vEntidadRecuperado.setFechaUltimaModificacion(pPruebaManual.getFechaUltimaModificacion());
			this.saveOrUpdate(vEntidadRecuperado);

//			String vhql = "update SreFacRegistrosPruebasManuales a set  a.observaciones =:pObservaciones, a.estadoPruebaId =:pEstadoPruebaId"
//					+ " where a.registroPruebaManualId =:pRegistroPruebaManualId";

			System.out.println("pRegistroPruebaManualId::" + pPruebaManual.getPruebaManualId());
			System.out.println("pEstadoPruebaIda::" + pPruebaManual.getEstadoPruebaId());
			System.out.println("pObservaciones::" + pPruebaManual.getObservaciones());

//			int vResultado = (int) getSession().createQuery(vhql)
//					.setParameter("pRegistroPruebaManualId", pPruebaManual.getPruebaManualId())
//					.setParameter("pEstadoPruebaId", pPruebaManual.getEstadoPruebaId())
//					.setParameter("pObservaciones", pPruebaManual.getObservaciones()).executeUpdate();

			System.out.println("Resultado Consulta::" + vResp);
			/*
			 * vListadoPruebasManu = (List<SreFacRegistrosPruebasManuDetalle>)
			 * getSession().createQuery(vhql) .setParameter("pSolicitudId",
			 * pSolicitudId).setParameter("pTramiteId", pTramiteId)
			 * .setParameter("pSistemaId", pSistemaId).getResultList();
			 * 
			 */

		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			e.printStackTrace();
		}
		System.out.println("vResp::" + vResp);
		return vResp;

	}
	
	/**
	 * Recuperar obtiene Lista de Registro de Pruebas de Manuales.
	 * @author: Wilson Limachi Choque
	 * @Fecha: 18/07/2019
	 * @param pSolicitudCertificacionId, número de identificacion de la tabla solicitud certificacion
	 * @return Devuelve objeto SreFacRegistrosPruebasManuales.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SreFacRegistrosPruebasManuales> obtieneListaRegistroPruebasManuales(long pSolicitudCertificacionId) 
	{
		List<SreFacRegistrosPruebasManuales> vEntidad = new ArrayList<>();
		try 
		{
			String vhql = "FROM SreFacRegistrosPruebasManuales p WHERE p.solicitudCertificacionId=:pSolicitudCertificacionId and p.estadoId= 'AC' ";
			vEntidad = getSession().createQuery(vhql).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
			LOG.info("Informacion recuperada.");
			return vEntidad;
		} 
		catch (NoResultException e) 
		{
			return new ArrayList<>();
		} 
		catch (Exception e) 
		{
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}
}
