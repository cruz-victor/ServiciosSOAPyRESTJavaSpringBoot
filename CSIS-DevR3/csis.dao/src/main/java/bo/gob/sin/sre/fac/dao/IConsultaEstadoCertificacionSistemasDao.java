package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

public interface IConsultaEstadoCertificacionSistemasDao {

	public List<SreSolicitudCertificacion> recuperaListaEstadoCertificacionSistemas(long pPersonaContribuyenteId);

}
