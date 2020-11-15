package bo.gob.sin.sre.fac.cfec.service.reporte;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudFacturaParcialDto;

public interface IReporteService {
	public RespuestaJsonDto obtenerReporte(SolicitudFacturaParcialDto pSolicitud);

}