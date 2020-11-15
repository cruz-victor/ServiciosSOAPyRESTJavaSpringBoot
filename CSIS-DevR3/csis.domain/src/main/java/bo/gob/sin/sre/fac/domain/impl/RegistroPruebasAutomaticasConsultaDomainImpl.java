package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IRegistroPruebasAutomaticasDao;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasAutomaticasConsultaDomain;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;

@Service
@Transactional
public class RegistroPruebasAutomaticasConsultaDomainImpl implements IRegistroPruebasAutomaticasConsultaDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RegistroPruebasAutomaticasConsultaDomainImpl.class);
	@Autowired
	IRegistroPruebasAutomaticasDao iRegistroPruebasAutomaticasDao;

	@Override
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListadoPruebasAutomaticas(long pSolicitudId, long pTramiteId,
			long pSistemaId) {

		List<SreFacRegistrosPruebasAutomaticas> lista = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		try {
			lista = iRegistroPruebasAutomaticasDao.obtieneListadoPruebasAutomaticas(pSolicitudId, pTramiteId,
					pSistemaId);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(lista));
			LOG.info("Datos pruebas automaticas no registrados.");
		}
		return lista;
	}
	
	/**
	 * Obtiene Listado de Registro de Pruebas Automaticas
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 18/07/2019
	 * @param  pSolicitudCertificacionId
	 * @return   Devuelve List<SreFacRegistrosPruebasAutomaticas>
	 */
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListadoRegistroPruebasAutomaticas(long pSolicitudCertificacionId)
	{
		List<SreFacRegistrosPruebasAutomaticas> vLista = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		
		try 
		{
			vLista = iRegistroPruebasAutomaticasDao.obtieneListadoRegistroPruebasAutomaticas(pSolicitudCertificacionId);
		} 
		catch (Exception e) 
		{
			vLista = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(vLista));
			LOG.info("Datos pruebas automaticas no registrados.");
		}
		
		return vLista;
	}
	
	/**
	 * Obtiene Listado de Registro de Pruebas Automaticas
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 18/07/2019
	 * @param  pRegistroPruebaAutomaticaId
	 * @param  pUsuario
  	 * @param  pEstadoId
	 * @return Devuelve objeto SreFacRegistrosPruebasAutomaticas
	 */
	@Override
	public SreFacRegistrosPruebasAutomaticas modificaRegistroPruebasAutomaticas(long pRegistroPruebaAutomaticaId, long pUsuario, int pEstadoId) 
	{
		LOG.info("Ingresando modificaRegistroPruebasAutomaticas pRegistroPruebaAutomaticaId ={} ", pRegistroPruebaAutomaticaId);
		SreFacRegistrosPruebasAutomaticas vResultado= new SreFacRegistrosPruebasAutomaticas();
		try 
		{
			vResultado = iRegistroPruebasAutomaticasDao.findById(pRegistroPruebaAutomaticaId);
			vResultado.setEstadoPruebaId(pEstadoId);
			vResultado.setFechaUltimaModificacion(new Date());
			vResultado.setUsuarioUltimaModficacionId(pUsuario);
			iRegistroPruebasAutomaticasDao.saveOrUpdate(vResultado);
		} 
		catch (Exception e) 
		{
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
			vResultado = null;
		}
		
		LOG.info("Saliendo de modificaRegistroPruebasAutomaticas", vResultado);
		return vResultado;
	}

	@Override
	public List<SreFacRegistrosPruebasAutomaticas> obtieneListaPaginadaPruebasAutomaticas(long pSolicitudId,
			long pTramiteId, long pSistemaId, int pPrimerRegistro, int pTamanioPagina, String pCampoOrden,
			boolean pAscendente, Map<String, Object> pFiltros) {

		List<SreFacRegistrosPruebasAutomaticas> lista = new ArrayList<SreFacRegistrosPruebasAutomaticas>();
		try {
			lista = iRegistroPruebasAutomaticasDao.obtieneListaPaginadaPruebasAutomaticas(pSolicitudId, pTramiteId,
					pSistemaId, pPrimerRegistro, pTamanioPagina, pCampoOrden, pAscendente, pFiltros);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(lista));
			LOG.info("Datos pruebas automaticas no registrados.");
		}
		return lista;
	}

	@Override
	public Long obtieneTotalRegistrosPruebasAutomaticas(long pSolicitudId, long pTramiteId, long pSistemaId, Map<String, Object> pFiltros) {
		Long vResp = null;

		try {
			vResp=iRegistroPruebasAutomaticasDao.obtieneTotalRegistrosPruebasAutomaticas(pSolicitudId, pTramiteId,
					pSistemaId, pFiltros);

		} catch (

		Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResp));
			LOG.info("Datos pruebas automaticas no registrados.");
		}

		return vResp;
	}

}
