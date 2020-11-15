package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreSistemas;

public interface ISistemasDao extends GenericDao<SreSistemas> {

	/**
	 * Recuperar Devolver los datos de un sistmas por sistemaId.
	 * 
	 * @author: Carmen Rosa Silva Paco
	 * @Fecha: 12/06/2018
	 * @param pSistemaId, número de identificacion de un sistema
	 * @return   Devuelve objeto SreSistemas.
	 */
	public SreSistemas obtenerSolicitudSistemaCertificado(Long pSistemaId);
	
	public List<SreSistemas> verificacionSistemaRegistrado(String pNombreSistema, Integer pModalidadFacturacionId,	Long pContribuyenteId);
	
	//-----------------------------

	public List<SreSistemas> obtenerListaSolicitudSistemasCertificados(Long pContribuyenteId);

	public List<SreSistemas> obtenerListaSistemasPorTipoSistema(Long pTipoSistemaId);

	public List<SreSistemas> obtenerListaSistemasPorModalidad(Long pModalidadFacturacionId);

	public SreSistemas actualizarEstadosSistemas(Long pSistemaId, Integer pEstadoSistemaId);

	

	public SreSistemas obtenerSistemaPorId(Long pSistemaId);

	public Long verificaExisteSistema(Long pSistemaId);
}