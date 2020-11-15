package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import bo.gob.sin.sre.fac.dao.IRutasServiciosDao;
import bo.gob.sin.sre.fac.model.SreRutasServicios;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class RutasServiciosDaoImpl extends AbstractGenericDao<SreRutasServicios> implements IRutasServiciosDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SreRutasServicios obtieneRutas(int pModalidadId, int pTipoDocumentoSectorId) {
		SreRutasServicios vResultado = new SreRutasServicios();
		try {
			String vhql = "SELECT a "
					    + "FROM SreRutasServicios a "
					    + "WHERE a.modalidadFacturacionId = :pModalidadId "
					    + "and a.tipoDocumentoSectorId = :pTipoDocumentoSectorId "
					    + "and a.estadoId = 'AC'";
			vResultado = (SreRutasServicios)getSession().createQuery(vhql).setParameter("pModalidadId", pModalidadId).setParameter("pTipoDocumentoSectorId", pTipoDocumentoSectorId).getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
			vResultado = null;
		}
		return vResultado;
	}
	
	@Override
	public List<SreRutasServicios> obtieneRutasComunes(int pModalidadId, int pTipoDocumentoSectorId) {
		List<SreRutasServicios> vResultado = new ArrayList<SreRutasServicios>();
		try {
			String vhql = "SELECT a "
					    + "FROM SreRutasServicios a "
					    + "WHERE a.modalidadFacturacionId = :pModalidadId "
					    + "and a.tipoDocumentoSectorId = :pTipoDocumentoSectorId "
					    + "and a.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pModalidadId", pModalidadId).setParameter("pTipoDocumentoSectorId", pTipoDocumentoSectorId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vResultado;
	}
}
