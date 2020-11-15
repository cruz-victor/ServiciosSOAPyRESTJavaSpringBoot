package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;

public interface ISistemasContribuyentesConsultaDomain {

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
	public SreSistemasContribuyentes recuperarRegistroSistemaContribuyente(Long pSistemaId, Long pContribuyenteId, int pModalidad);

}
