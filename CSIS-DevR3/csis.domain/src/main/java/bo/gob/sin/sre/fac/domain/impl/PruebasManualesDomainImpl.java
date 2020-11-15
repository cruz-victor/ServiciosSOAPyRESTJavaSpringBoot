package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.dao.IPruebasManualesDao;
import bo.gob.sin.sre.fac.dao.IRegistroPruebasManualesDao;
import bo.gob.sin.sre.fac.domain.IPruebasManualesDomain;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuDetalle;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;

@Service
public class PruebasManualesDomainImpl implements IPruebasManualesDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPruebasManualesDao iPruebasManualesDao;

	@Autowired
	private IRegistroPruebasManualesDao iRegistroPruebasManualesDao;

	@Override
	public List<SreFacRegistrosPruebasManuDetalle> obtieneListaPruebasManuales(long pSolicitudId, long pTramiteId,
			long pSistemaId) {
		//
		List<SreFacRegistrosPruebasManuDetalle> vRespueta = new ArrayList<>();
		vRespueta = this.iPruebasManualesDao.obtieneListaPruebasManuales(pSolicitudId, pTramiteId, pSistemaId);
		return vRespueta;

	}

	@Override
	public SreFacRegistrosPruebasManuales modificaObservacionEstado(Long pRegistroPruebaManualId, long pUsuario, int pEstado) {

		SreFacRegistrosPruebasManuales resp = iRegistroPruebasManualesDao.modificaObservacionEstado(pRegistroPruebaManualId, pUsuario, pEstado);
		return resp;
	}
	
	@Override
	public boolean modificaObservacionEstado(SreFacRegistrosPruebasManuales pPruebaManual) {

		boolean resp = iRegistroPruebasManualesDao.modificaObservacionEstado(pPruebaManual);
		return resp;
	}
	
	@Override
	public List<SreFacRegistrosPruebasManuales> obtieneListaRegistroPruebasManuales(long pSolicitudId)
	{
		List<SreFacRegistrosPruebasManuales> vRespueta = new ArrayList<>();
		vRespueta = this.iRegistroPruebasManualesDao.obtieneListaRegistroPruebasManuales(pSolicitudId);
		return vRespueta;
	}

}
