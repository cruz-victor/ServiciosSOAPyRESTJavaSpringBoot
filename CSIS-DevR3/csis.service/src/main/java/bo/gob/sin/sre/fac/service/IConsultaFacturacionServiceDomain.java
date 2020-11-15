package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudConsultaFacturacionDto;

public interface IConsultaFacturacionServiceDomain {

	RespuestaConsultaFacturacionDto consultaFacturacion(SolicitudConsultaFacturacionDto solicitud);
}
