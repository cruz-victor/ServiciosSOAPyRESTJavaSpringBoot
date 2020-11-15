package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasContribuyentesDao;
import bo.gob.sin.sre.fac.domain.ISistemasContribuyentesConsultaDomain;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;

@Service
@Transactional
public class SistemaContribuyenteConsultaDomainImpl implements ISistemasContribuyentesConsultaDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SistemaContribuyenteConsultaDomainImpl.class);
	
	@Autowired
	ISistemasContribuyentesDao iSistemasContribuyentesDao;
	
	/**
	 * Recuperar Sistema_Contribuyente por sistemaId, contribuyenteId
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 20/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSistemaId, id de la tabla sistema
	 * @param pContribuyenteId, numero de persona id
	 * @return Devuelve objeto SreSistemasContribuyentes.
	 */
	@Override
	public SreSistemasContribuyentes recuperarRegistroSistemaContribuyente(Long pSistemaId, Long pContribuyenteId, int pModalidad) {
		
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		try 
		{
			vEntidad = iSistemasContribuyentesDao.recuperarRegistroSistemaContribuyenteSistemasId(pSistemaId, pContribuyenteId, pModalidad);
		} 
		catch (Exception e) 
		{
			vEntidad = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}
		return vEntidad;
	}
	
	
	
	
}
