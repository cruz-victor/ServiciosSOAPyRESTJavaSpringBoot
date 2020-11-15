package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

//IASC
public interface ITiposDocumentosSistemasDao extends GenericDao<SreTiposDocumentosSistemas>{

	public List<SreTiposDocumentosSistemas> obtieneDocumentosSectores(long pSistemaId, long pSolicitudCertificacionId);
	
	/**
	 * Objetivo: Obtener el tipo de documento de la factura a través del identificados del contribuyente. 
	 * Creado por: Peter Flores 
	 * Fecha: 27/12/2018
	 */	
	public List<Integer> obtieneTipoDocumentoFacturaPorIdContribuyente(long pContribuyenteId); 
}
