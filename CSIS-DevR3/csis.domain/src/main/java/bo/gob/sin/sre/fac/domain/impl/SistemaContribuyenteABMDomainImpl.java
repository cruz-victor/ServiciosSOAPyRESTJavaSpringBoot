package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasContribuyentesDao;
import bo.gob.sin.sre.fac.domain.ISistemasContribuyentesABMDomain;
import bo.gob.sin.sre.fac.domain.ISistemasContribuyentesConsultaDomain;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;

@Service
@Transactional(rollbackFor = Exception.class)
public class SistemaContribuyenteABMDomainImpl implements ISistemasContribuyentesABMDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SistemaContribuyenteABMDomainImpl.class);
	
	@Autowired
	ISistemasContribuyentesDao iSistemasContribuyentesDao;
	
	@Autowired
	ISistemasContribuyentesConsultaDomain iSistemasContribuyentesConsultaDomain;
	
	/**
	 * Cambiar estado en Sistemas Contribuyentes, parametrizado
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 22/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSistemaId, id de la tabla sistema
	 * @param pContribuyenteId, numero de persona id
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de sistema
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
	@Override
	public SreSistemasContribuyentes cambiarEstadoSistemaContribuyente(Long pSistemaId, Long pContribuyenteId, Long pUsuario,int pEstado, int pModalidad) {
		
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		try {
			vEntidad = iSistemasContribuyentesConsultaDomain.recuperarRegistroSistemaContribuyente(pSistemaId, pContribuyenteId, pModalidad);
			
			if(vEntidad != null)
			{
				vEntidad.setEstadoSistemaContribuyenteId(pEstado);
				vEntidad.setFechaUltimaModificacion(new Date());
				vEntidad.setUsuarioUltimaModificacionId(pUsuario);
				iSistemasContribuyentesDao.saveOrUpdate(vEntidad);
			}
			else
			{
				vEntidad = null;
			}			
		} 
		catch (Exception e) 
		{
			vEntidad = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}
		return vEntidad;
	}
	
	
	
}
