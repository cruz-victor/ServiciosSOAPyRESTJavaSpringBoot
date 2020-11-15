package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.DatosRecertificacion;
import bo.gob.sin.sre.fac.dto.ReporteDatosSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;

public interface IRecuperarDatosSistemasService {

	//IASC 
	public RespuestaDatosSistemasSolCertificacionDto obtenerDatosSistemaCertificacion(long pSistemaId, long pSolicitudCertificacionId);
	
	//IASC
	public ReporteDatosSolicitudCertificacionDto obtenerDatosReporteSolCertificacion(long pSistemaId, long pSolicitudCertificacionId);

	/**
	 * @author rosario.garcia
	 * @param pSistemaId
	 * @return los datos del sistema
	 */
	public SistemasDto obtenerDatosSistemaPorSistemaId(Long pSistemaId);
	
	public DatosRecertificacion obtenerDatosReSolCertificacion(DatosRecertificacion pDatosRecertificacion);
}
