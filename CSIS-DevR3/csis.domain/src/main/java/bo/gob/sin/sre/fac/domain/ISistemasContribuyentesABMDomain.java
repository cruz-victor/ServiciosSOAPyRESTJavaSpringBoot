package bo.gob.sin.sre.fac.domain;

import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;

public interface ISistemasContribuyentesABMDomain {

	
	/**
	 * Cambiar estado en Sistemas Contribuyentes, parametrizado
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 22/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSistemaId, id de la tabla sistema
	 * @param pContribuyenteId, numero de persona id
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de sistema
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
	public SreSistemasContribuyentes cambiarEstadoSistemaContribuyente(Long pSistemaId, Long pContribuyenteId, Long pUsuario,int pEstado, int pModalidad);


}
