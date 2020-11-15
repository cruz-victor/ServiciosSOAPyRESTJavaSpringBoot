package bo.gob.sin.sre.fac.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import bo.gob.sin.sre.fac.dao.ITiposDocumentosSistemasDao;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

//IASC
@Repository
@Transactional
public class TiposDocumentosSistemasDaoImpl extends AbstractGenericDao<SreTiposDocumentosSistemas> implements ITiposDocumentosSistemasDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<SreTiposDocumentosSistemas> obtieneDocumentosSectores(long pSistemaId, long pSolicitudCertificacionId) {
		List<SreTiposDocumentosSistemas> vResultado = new ArrayList<SreTiposDocumentosSistemas>(); 
		try {
			String vhql = "SELECT a "
					    + "FROM SreTiposDocumentosSistemas a "
					    + "WHERE a.sistemaId = :pSistemaId "
					    + "and a.solicitudCertificacionId = :pSolicitudCertificacionId "
					    + "and a.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pSistemaId", pSistemaId).setParameter("pSolicitudCertificacionId", pSolicitudCertificacionId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vResultado;
	}
	
	/**
	 * Objetivo: Obtener el tipo de documento de la factura a través del identificados del contribuyente. 
	 * Creado por: Peter Flores 
	 * Fecha: 27/12/2018
	 */
	@Override
	public List<Integer> obtieneTipoDocumentoFacturaPorIdContribuyente(long pContribuyenteId) {
		List<Integer> vResultado = new ArrayList<>(); 
		try {
			String vhql = "SELECT distinct a.tipoDocumentoFacturaId "
					    + "FROM SreTiposDocumentosSistemas a, SreSistemasContribuyentes b "
					    + "WHERE b.sistemaId = a.sistemaId "
					    + "and b.contribuyenteId = :pContribuyenteId "
					    + "and a.estadoId = 'AC'";
			vResultado = getSession().createQuery(vhql).setParameter("pContribuyenteId", pContribuyenteId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vResultado;
	}
}
