package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreSistemasModulos;


public interface ISistemasModulosDao extends GenericDao<SreSistemasModulos> {

	public List<SreSistemasModulos> obtenerListaModulosSistemas(Long pSolicitudCertificacionId);

	public List<SreSistemasModulos> obtenerListaModulosPorSistema(Long pSistemaId);
}
