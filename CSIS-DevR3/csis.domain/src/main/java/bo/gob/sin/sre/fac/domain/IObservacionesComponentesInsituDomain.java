package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreBitacorasObservacionesComponentesInsitu;
import bo.gob.sin.sre.fac.model.SreObservacionesComponentesInsitu;

public interface IObservacionesComponentesInsituDomain {
	public List<SreObservacionesComponentesInsitu> obtenerObservacionesComponentesInsituPorCodigoCertificacion(
			long pCodigoCertificacion);

	public SreObservacionesComponentesInsitu modificarObservacionInSitu(
			SreObservacionesComponentesInsitu pObservacionInsitu);

	public SreObservacionesComponentesInsitu registrarObservacionInSitu(
			SreObservacionesComponentesInsitu pObservacionInsitu);

	public SreBitacorasObservacionesComponentesInsitu registrarBitacoraObservacionInSitu(
			SreBitacorasObservacionesComponentesInsitu pBitacoraObservacionesComponentesInsitu);

	public List<SreBitacorasObservacionesComponentesInsitu> obtenerDetalleObservacionesComponentesInsituPorCodigoObservacion(
			long pCodigoObservacionComponenteInSitu);

	public List<SreObservacionesComponentesInsitu> obtenerObservacionesInsituPorCodigoCertificacionNoAprobadas(
			long pCodigoCertificacion);

	public boolean verificarContribuyenteRequiereVisitaInsitu(Long pNit);
}
