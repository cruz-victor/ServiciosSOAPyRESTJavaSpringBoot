package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

public interface IConsultaEstadoCertificacionSistemasDomain {

	public List<SreSolicitudCertificacion> recuperaListaEstadoCertificacionSistemas(long pPersonaContribuyenteId);

}
