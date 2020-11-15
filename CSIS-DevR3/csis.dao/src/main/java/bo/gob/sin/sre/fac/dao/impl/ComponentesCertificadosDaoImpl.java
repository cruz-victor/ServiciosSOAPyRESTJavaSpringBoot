package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosDao;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

//IASC
@Repository
@Transactional
public class ComponentesCertificadosDaoImpl extends AbstractGenericDao<SreComponentesCertificados> implements IComponentesCertificadosDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ComponentesCertificadosDaoImpl.class);
	/**
     * Objetivo: recuperar lista huella sistema
     * Creado por: Ivan Angel Salas.
     * Fecha: 14/05/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 08/08/2018
     */
	@Override
	public List<SreComponentesCertificados> obtenerListaComponentesCertificados(Long pSistemaId){
		List<SreComponentesCertificados> vEntidad = new ArrayList<SreComponentesCertificados>();
		LOG.info("ingresando a obtener lista componentes certificados");
		try {
			String vhql = "FROM SreComponentesCertificados p WHERE p.sistemaId = :pSistemaId and estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).getResultList();
			LOG.info("datos recuperados exitosamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("finalizando obtener lista componente certificados");
		return vEntidad;
	}
	
	@Override
	public Long verificaExisteComponente(Long pSistemaId, Integer pTipoComponenteId){
		Long vCantidad;
		try {
			String vhql = "SELECT COUNT(p) FROM SreComponentesCertificados p WHERE p.sistemaId = :pSistemaId and p.tipoComponenteId = :pTipoComponenteId and p.estadoId = 'AC'";
			vCantidad = (Long)getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).setParameter("pTipoComponenteId", pTipoComponenteId).getSingleResult();
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			vCantidad = 0L;
		}
		return vCantidad;
	}
	
	
	//IASC - Verifica si tiene registrado el componente - 10/08/2018
	@Override
	public Long verificarComponentesCertificados(Long pSistemaId){
		long vCantidad;
		try {
			String vhql = "SELECT COUNT(p) FROM SreComponentesCertificados p WHERE p.sistemaId = :pSistemaId and p.estadoId = 'AC'";
			vCantidad = (long) getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).getSingleResult();
		} catch (Exception e) {
			vCantidad = -1L;
		}
		return vCantidad;
	}
	
	//IASC - Recupera datos del componente del sistema - 16/08/2018
	@Override
	public SreComponentesCertificados recuperaDatosComponente(Long pSistemaId, Integer pComponenteId){
		SreComponentesCertificados vEntidad = new SreComponentesCertificados();
		try {
			String vhql = "SELECT p FROM SreComponentesCertificados p WHERE p.sistemaId = :pSistemaId and p.estadoId = 'AC' and p.tipoComponenteId = :pComponenteId";
			vEntidad = (SreComponentesCertificados) getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).setParameter("pComponenteId", pComponenteId).getSingleResult();
		} catch (Exception e) {
			vEntidad = null;
		}
		return vEntidad;
	}
	
	/**
     * Objetivo: obtener la lista de componentes de certificado a través de los tipos de componentes
     * Creado por: Peter Flores.
     * Fecha: 20/11/2018     
     */
	@Override
	public List<SreComponentesCertificados> obtenerComponentesCertificados(List<Long> pComponenteArchivoId) {
		List<SreComponentesCertificados> vListaComponentesCertificados;
		try {
			String vQuery = "SELECT p FROM SreComponentesCertificados p WHERE p.componenteArchivoId in (:pComponenteArchivoId)";
			vListaComponentesCertificados = getSession().createQuery(vQuery)
					.setParameter("pComponenteArchivoId", pComponenteArchivoId)
					.getResultList();
		} catch (Exception e) {
			LOG.error("Error en obtenerReporteCompras");
			LogExcepcion.registrar(e, LOG);
			vListaComponentesCertificados = null;
		}
		return vListaComponentesCertificados;
	}
	
	/**
     * Objetivo: obtener la lista de componentes de certificado por id componentearchivoid
     * Creado por: Wilson Limachi.
     * Fecha: 18/18/2019     
     */
	@Override
	public List<SreComponentesCertificados> obtenerComponentesCertificados(Long pComponenteArchivoId) 
	{
		List<SreComponentesCertificados> vListaComponentesCertificados = new ArrayList<>();
		
		try 
		{
			String vQuery = "SELECT p, cl.descripcion "
					+ "FROM SreComponentesCertificados p "
					+ "inner join SrtClasificadores cl on (p.tipoComponenteId = cl.clasificadorId and cl.estadoId = 'AC')"
					+ "WHERE p.componenteArchivoId = :pComponenteArchivoId and p.estadoId = 'AC'";
			
			List<Object[]> vResultadoQuery = getSession().createQuery(vQuery).setParameter("pComponenteArchivoId", pComponenteArchivoId).getResultList();
			
			if (vResultadoQuery.size() > 0) 
			{
				for ( Object[] object : vResultadoQuery ) 
				{
					if(object[0] instanceof SreComponentesCertificados)
					{
						SreComponentesCertificados vSreComponentesCertificados= new SreComponentesCertificados();
						vSreComponentesCertificados = (SreComponentesCertificados)object[0];						
						vSreComponentesCertificados.setTipoCompoonenteDescripcion((String)object[1]);
						vListaComponentesCertificados.add(vSreComponentesCertificados);
					}
		        }
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Error en obtenerComponentesCertificados");
			LogExcepcion.registrar(e, LOG);
			vListaComponentesCertificados = null;
		}
		
		return vListaComponentesCertificados;
	}
}
