package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

public interface ITiposDocumentosSistemasConsultaDomain {

	//IASC
	public List<SreTiposDocumentosSistemas> obtieneDocumentosSectores(long pSistemaId, long pSolicitudCertificacionId);
	
	//WLC
	public SreTiposDocumentosSistemas modificarTipoDocumentosSistemas(Long pTipoDocumentoSistemaId, Long pUsuario, Integer pEstadoId); 
}
