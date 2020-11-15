package bo.gob.sin.sre.fac.domain;

public interface IPruebasSistemasABMDomain {

	/**
	 * Cambiar estado en Pruebas sistemas
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 27/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitudCertificacionId, numero de identificacion de solicitud certificacion
	 * @param pSistemasId, número de identificacion del sistema
	 * @param pUsuarioId, número de identificacion del usuario
	 * @param pEstado, número de identificacion del estado
	 * @return Devuelve un boolean true o false.
	 */
	public boolean cambiarEstadoPruebasSistemas(Long pSolicitudCertificacionId, Long pSistemasId, Long pUsuarioId,Integer pEstado);

}
