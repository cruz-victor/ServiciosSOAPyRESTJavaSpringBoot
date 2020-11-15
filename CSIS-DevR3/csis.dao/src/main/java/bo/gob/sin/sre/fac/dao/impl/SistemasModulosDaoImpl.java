package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.ISistemasModulosDao;
import bo.gob.sin.sre.fac.model.SreSistemasModulos;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Repository
@Transactional
public class SistemasModulosDaoImpl extends AbstractGenericDao<SreSistemasModulos> implements ISistemasModulosDao, Serializable {

/**
 * 
 */
private static final long serialVersionUID = 1L;
	
private static final Logger LOG = LoggerFactory.getLogger(SistemasModulosDaoImpl.class);
	
	/**ELIMINAR
	 * Recuperar Datos Sistemas Modulos por SolicitacionCertificacionId
	 * 
	 * @author: Reynaldo Chambi
	 * @Fecha:
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 29/06/2018
	 * @param pSolicitud,código único solicitud de certificacion
	 * @return Devuelve una lista de datos sistemas módulos
	 */
//TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSistemasModulos> obtenerListaModulosSistemas(Long pSolicitudCertificacionId) {
		LOG.info("Ingresando obtenerListaModulosSistemas.");
		List<SreSistemasModulos> vEntidad = new ArrayList<SreSistemasModulos>();

		try {
			String vQuery = "FROM SreSistemasModulos p WHERE p.solicitudCertificacionId = :id and p.estadoId = 'AC'";
			vEntidad = getSession().createQuery(vQuery).setParameter("id", pSolicitudCertificacionId)
					.getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(vEntidad));
		}

		return vEntidad;
	}
	
	
	/**
	 * Recupera los modulos del sistema por sistemaId
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 16/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 09/08/2018
	 * @param pSistemaId, número de identificacion del sistema
	 * @return   Devuelve objeto de tipo List<SreSistemasModulos>.
	 */
	//TODO
	@SuppressWarnings("unchecked")
	@Override
	public List<SreSistemasModulos> obtenerListaModulosPorSistema(Long pSistemaId) {
		LOG.info("Ingresando obtenerListaModulosSistemas.");
		List<SreSistemasModulos> vEntidad = new ArrayList<SreSistemasModulos>();
		try {
			String vhql = "FROM SreSistemasModulos p WHERE p.sistemaId = :pSistemaid and estadoId = 'AC'";
			vEntidad = getSession().createQuery(vhql).setParameter("pSistemaid", pSistemaId).getResultList();
			LOG.info("Informacion recuperada.");
		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}
		return vEntidad;
	}
}
