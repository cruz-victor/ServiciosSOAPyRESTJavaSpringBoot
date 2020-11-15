package bo.gob.sin.sre.fac.dao;

import java.util.Date;
import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;

public interface ISistemasContribuyentesDao extends GenericDao<SreSistemasContribuyentes> {

	
	/**
	 * Recuperar Sistema_Contribuyente por sistemaId, contribuyenteId
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 20/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSistemaId, id de la tabla sistema
	 * @param pContribuyenteId, numero de persona id
	 * @return Devuelve objeto SreSistemasContribuyentes.
	 */
	public SreSistemasContribuyentes recuperarRegistroSistemaContribuyenteSistemasId(Long pSistemasId, Long pContribuyenteId, int pModalidad);
	
	
	public SreSistemasContribuyentes actualizarListaPruebasSistema(Long pSistemaContribuyenteId, String pObservacion,
			Integer pEstadoSistemaContribuyenteId, Date pFechaUltimaModificacion);

	public SreSistemasContribuyentes recuperarDatosSistemaContribuyente(Long pContribuyenteId,
			Integer pEstadoSistemaContribuyenteId);

	public List<SreSistemasContribuyentes> recuperarListaDatosSistemaContribuyente(Long pContribuyenteId,
			Integer pEstadoSistemaContribuyenteId);

	public SreSistemasContribuyentes actualizarEstadosSistemasContribuyentes(Long pSistemasContribuyentesId,
			Integer pEstadoId, int pModalidad);

	public SreSistemasContribuyentes recuperarRegistroSistemaContribuyente(Long pSistemasContribuyentesId);

	

	public List<SreSistemasContribuyentes> recuperarSistemasContribuyente(Long pContribuyenteId,
			Integer pEstadoSistemaContribuyenteId, Long pModalidad);

	public long verificaSistemaContribuyente(Long pSistemaId, Long pContribuyenteId, Integer pEstadoSistemaContribuyenteId);

	public Long existeAsociacionContribuyenteSistemaProveedor(Long pIdContribuyente, Long pIdContribuyenteProveedor,
			Long pIdSistema, Integer pEstadoSistemaContribuyente);

	public List<SreSistemasContribuyentes> recuperarSistemasAsociadosProveedor(Long pContribuyenteProveedorId,
			Integer pEstadoSistemaContribuyente);
}