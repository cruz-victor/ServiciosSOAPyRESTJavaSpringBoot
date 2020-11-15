package bo.gob.sin.sre.fac.dao;

import bo.gob.sin.sre.fac.model.SrePruebasEtapaCertificacion;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IPruebasEtapaCertificacionDao extends GenericDao<SrePruebasEtapaCertificacion> {

	/**
	 * Obtiene el código unico de la entidad Prueba Etapa Certificación a través del código Etapa Certificación Sistemas
	 * 
	 * @author: junior.flores
	 * @Fecha: 06/06/2019
	 * @param pEtapaCertificacionSistemasId, código de la etapa de Certificación de Sistemas 
	 * @return Devuelve código unico de la entidad SrePruebasEtapaCertificacion
	 */
	public Long obtenerPruebaEtapaCertificacionId(Long pEtapaCertificacionSistemasId);
}
