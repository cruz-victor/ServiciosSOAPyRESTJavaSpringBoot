package bo.gob.sin.sre.fac.dao.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import bo.gob.sin.sre.fac.dao.IComponenteArchivosDao;
import bo.gob.sin.sre.fac.model.SreComponentesArchivos;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class ComponenteArchivosDaoImpl extends AbstractGenericDao<SreComponentesArchivos> implements IComponenteArchivosDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ComponenteArchivosDaoImpl.class);
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author: Peter Flores
	 * @Fecha: 22/11/2018
	 * @param pSistemaId, Código unico de sistema
	 * @return Devuelve una lista de  SreComponentesArchivos.
	 */
	@Override
	public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pArchivoId){
		List<SreComponentesArchivos> vResultado =new ArrayList<SreComponentesArchivos>(); 
		try {
			String vhql = "SELECT a "
					    + "FROM SreComponentesArchivos a "
					    + "WHERE a.archivoId = :pArchivoId and a.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pArchivoId", pArchivoId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("finalizando obtener lista componente archivos");
		return vResultado;
	}
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por SolicitudCertificacionId.
	 * @author: Wilson Limachi
	 * @Fecha: 18/09/2019
	 * @param pSistemaId, pSolicitudCertificacionId
	 * @return Devuelve una lista de  SreComponentesArchivos.
	 */
	@Override
	public List<SreComponentesArchivos> obtieneComponentesArchivos(Long pSistemaId, Long pSolicitudCertificacionId)
	{
		List<SreComponentesArchivos> vResultado =new ArrayList<SreComponentesArchivos>(); 
		try {
			String vhql = "SELECT distinct(a) "
					    + "FROM SreComponentesArchivos a "
					    + "inner join SreComponentesCertificados c on (c.componenteArchivoId = a.componenteArchivoId) "
					    + "WHERE c.sistemaId = :pSistemaId and c.solicitudCertificacionId = :pSolicitudCertificacionId and a.estadoId = 'AC' and c.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vResultado = null;
		}
		
		LOG.info("finalizando obtener lista componente archivos");
		return vResultado;
	}
	
	/**
	 * Recuperar Lista Detalle de Solicitud Certificacion por ArchivoId.
	 * @author: Wilson Limachi
	 * @Fecha: 24/09/2019
	 * @param pSistemaId
	 * @return Devuelve una lista de  SreComponentesArchivos.
	 */
	@Override
	public List<SreComponentesArchivos> obtieneComponentesArchivosLista(Long pArchivoId)
	{
		List<SreComponentesArchivos> vResultado =new ArrayList<SreComponentesArchivos>(); 
		try {
			String vhql = "SELECT distinct(a) "
					    + "FROM SreComponentesArchivos a "
					    + "WHERE a.archivoId = :pArchivoId and a.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pArchivoId", pArchivoId).getResultList();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vResultado = null;
		}
		
		LOG.info("finalizando obtener lista componente archivos");
		return vResultado;
	}
}
