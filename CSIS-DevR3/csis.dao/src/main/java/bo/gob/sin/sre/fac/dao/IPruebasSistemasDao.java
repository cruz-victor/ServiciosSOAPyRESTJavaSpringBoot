package bo.gob.sin.sre.fac.dao;

import java.util.Date;
import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SrePruebasSistemas;

public interface IPruebasSistemasDao extends GenericDao<SrePruebasSistemas> {

	/**
	 * Cambiar estado en Pruebas sistemas
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 15/11/2018
	 * @param pSolicitudCertificacionId, numero de identificacion de solicitud certificacion
	 * @param pSistemasId, número de identificacion del sistema
	 * @return Devuelve un boolean true o false.
	 */
	public List<SrePruebasSistemas> obtenerListaPruebasSistemasPorCertificacion(Long pSolicitudCertificacionId,Long pSistemasId);

	public SrePruebasSistemas actualizarListaPruebasSistema(Long pPruebaSistemaId, String pObservaciones,
			Integer pEstadoPruebaId, Date pFechaFin, Long pUsuarioId);

	public List<SrePruebasSistemas> obtenerListaPruebasSistemas(Long pSistemaId);

	public List<SrePruebasSistemas> obtenerPruebasSistemasDeCertificacion(Long pSistemaId, Long pSolicitudCertificacionId);

	//****************
	//TODO: viene del original
	public SrePruebasSistemas recuperaPruebasSistemasPorId(Long pPruebaSistemaId);
}
