package bo.gob.sin.sre.fac.domain;

import java.util.Date;

import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.model.SrePruebasCertificacionSistemas;

public interface IPruebasCertificacionSistemasDomain {

	/***
	 * Actualiza la entidad SrePruebasCertificacionSistemas
	 * 
	 * @author junior.flores
	 * @return la entidad SrePruebasCertificacionSistemas
	 * @fecha 05/06/2019
	 */
	 public SrePruebasCertificacionSistemas actualizarPruebasCertificacionSistemas(SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud, Date pFechaActual);
	 
		/***
		 * Reiniciar la entidad SrePruebasCertificacionSistemas
		 * 
		 * @author junior.flores
		 * @return La entidad SrePruebasCertificacionSistemas
		 * @fecha 06/06/2019
		 */
		 public SrePruebasCertificacionSistemas reiniciarPruebasCertificacionSistemas(Long pPruebaCertificacionSistemaId, Date pFechaActual);
		 
		/**
		* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificación de Pruebas.
		* @author: Peter Flores.
		* @Fecha: 08/07/2019
		* @param pSolicitud: Información necesaria para registrar la etapa de prueba (detalle)
		* @return Devuelve un valor booleano que indica la transacción
		*/
		 public boolean registroPruebaDetalleOpcional(SeguimientoDetalleCertificacionSistemasDto pSolicitud);
}
