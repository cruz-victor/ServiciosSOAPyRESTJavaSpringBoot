package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasModulosDao;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.domain.IRegistrarModulosSistemasDomain;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasModulos;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegistrarModulosSistemasDomainImpl implements IRegistrarModulosSistemasDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarModulosSistemasDomainImpl.class);
	
	@Autowired
	ISistemasModulosDao iSistemasModulosDao;
	
	/**5
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	@Override
	public boolean registraModulosSistemaACertificar(SreSistemas pSolicitudSistema, Long pSolicitudCertificacionId, List<Integer> pTiposModulos) {
		boolean vRespuesta = false;
		Date vFechaHoy = new Date();
		try {
				for (Integer vModulo : pTiposModulos) {
					
					SreSistemasModulos vRegistro = new SreSistemasModulos();									
					
					vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
					vRegistro.setFechaRegistro(vFechaHoy);
					vRegistro.setFechaUltimaModificacion(vFechaHoy);
					vRegistro.setSistemaId(pSolicitudSistema.getSistemaId());
					
					vRegistro.setSolicitudCertificacionId(pSolicitudCertificacionId);
					vRegistro.setTipoModuloId(vModulo);
					vRegistro.setTramiteId(pSolicitudSistema.getTramiteId());
					vRegistro.setUsuarioRegistroId(pSolicitudSistema.getUsuarioRegistroId());
					vRegistro.setUsuarioUltimaModificacionId(pSolicitudSistema.getUsuarioRegistroId());

					iSistemasModulosDao.save(vRegistro);
					LOG.info("Modulos registrados.");
				}
				vRespuesta = true;
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Modulos no registrados.");
		}
		return vRespuesta;
	}
}
