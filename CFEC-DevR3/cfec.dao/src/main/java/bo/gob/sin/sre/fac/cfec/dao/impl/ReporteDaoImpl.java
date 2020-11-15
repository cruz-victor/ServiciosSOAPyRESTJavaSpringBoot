package bo.gob.sin.sre.fac.cfec.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.IReporteDao;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlInvalido;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional
public class ReporteDaoImpl extends AbstractGenericDao<SreFacArchivoXmlInvalido> implements IReporteDao {

	@Override
	public String obtenerArchivoXmlValido(String pSolicitud) {
		try {
			if (pSolicitud != null && !pSolicitud.isEmpty()) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_obtener_archivo_xml_valido(:pJsonString)")
						.setParameter("pJsonString", pSolicitud);
				Object x = query.getSingleResult();
				return x.toString();
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}

}