package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.dao.IEquiposCertificacionesDao;
import bo.gob.sin.sre.fac.model.SreEquiposCertificaciones;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
@Repository
@Transactional
public class EquiposCertificacionesDaoImpl extends AbstractGenericDao<SreEquiposCertificaciones>
		implements IEquiposCertificacionesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(EquiposCertificacionesDaoImpl.class);

	@Override
	public List<SreEquiposCertificaciones> obtenerEquipoCertificacion(SreEquiposCertificaciones pEquipoCertificacion) {
		List<SreEquiposCertificaciones> vListaRespuesta = new ArrayList<>();
		try {
			String vHqlSistemasCertificados = "FROM SreEquiposCertificaciones sc"
					+ "WHERE sc.oficinaId= :pOficinaId and sc.dependenciaId=:pDependenciaId " + "and sc.estadoId='AC'";

			vListaRespuesta = getSession().createQuery(vHqlSistemasCertificados)
					.setParameter("pOficinaId", pEquipoCertificacion.getOficinaId())
					.setParameter("pDependenciaId", pEquipoCertificacion.getDependenciaId()).getResultList();
			LOG.info("Informacion recuperada.");

		} catch (Exception e) {
			LOG.info("Informacion no recuperada.");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pEquipoCertificacion));
		}
		return vListaRespuesta;
	}

}
