package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreDetalleCertificacionesSistemas;
import bo.gob.sin.str.ccs.dao.GenericDao;

//RCR
public interface IDetalleCertificacionesSistemasDao extends GenericDao<SreDetalleCertificacionesSistemas>{

	public List<SreDetalleCertificacionesSistemas> obtenerListaDetalleCertificacionSistemas(long pSolicitudCertificacionId, long pSistemaId);
	
	public SreDetalleCertificacionesSistemas obtenerDetalleCertificacionSistemas(long pDetalleCertificacionSistemaId);
}
