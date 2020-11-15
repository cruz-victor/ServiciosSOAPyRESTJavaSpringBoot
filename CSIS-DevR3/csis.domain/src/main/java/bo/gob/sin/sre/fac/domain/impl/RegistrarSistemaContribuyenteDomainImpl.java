package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasContribuyentesDao;
import bo.gob.sin.sre.fac.domain.IRegistrarSistemaContribuyenteDomain;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegistrarSistemaContribuyenteDomainImpl implements IRegistrarSistemaContribuyenteDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarSistemaContribuyenteDomainImpl.class);
	
	@Autowired
	ISistemasContribuyentesDao iSistemasContribuyentesDao;
	
	/**2
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     * IASC - Refactorizacion - 21/11/2018
     */
	@Override
	public SreSistemasContribuyentes registrarSistemasContribuyente(SreSistemas pSistemaACertificar, Long pContribuyenteId, Integer pModalidad){
		   
		SreSistemasContribuyentes vRegistroSolicitud = new SreSistemasContribuyentes();

		try {
			vRegistroSolicitud.setUsuarioRegistroId(pSistemaACertificar.getUsuarioRegistroId());
			vRegistroSolicitud.setUsuarioUltimaModificacionId(pSistemaACertificar.getUsuarioRegistroId());
			vRegistroSolicitud.setContribuyenteId(pContribuyenteId);
			vRegistroSolicitud.setContribuyenteProveedorId(pContribuyenteId);
			vRegistroSolicitud.setSistemaId(pSistemaACertificar.getSistemaId());
			vRegistroSolicitud.setModalidadFacturacionId(pModalidad);
			vRegistroSolicitud.setEstadoSistemaContribuyenteId(ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_APROBADA);
			vRegistroSolicitud.setFechaSolicitud(pSistemaACertificar.getFechaRegistro());
			vRegistroSolicitud.setFechaRegistro(pSistemaACertificar.getFechaRegistro());
			vRegistroSolicitud.setFechaUltimaModificacion(pSistemaACertificar.getFechaUltimaModificacion());
			vRegistroSolicitud.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				
			iSistemasContribuyentesDao.save(vRegistroSolicitud);
			LOG.info("Datos contribuyentes registrados.");
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSolicitud));
			LOG.info("Datos contribuyentes no registrados.");
			vRegistroSolicitud.setSistemaContribuyenteId(0L);
		}
		return vRegistroSolicitud;
	}
}
