package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IRegistroPruebasManualesDao;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasManualesABMDomain;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasManuales;
import bo.gob.sin.sre.fac.model.SrePruebasManuales;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional

public class RegistroPruebasManualesABMDomainImpl implements IRegistroPruebasManualesABMDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroPruebasManualesABMDomainImpl.class);
	
	@Autowired
	IRegistroPruebasManualesDao iRegistroPruebasManualesDao;
	
	//IASC - Registro de las pruebas manuales de la solicitud de certificacion - 28/11/2018
	@Override
	public boolean registrarPruebasManuales(SreSistemas pSistema, long pSolicitudCertificacionId, List<SrePruebasManuales> pPruebas) {
		boolean vRespuesta = false;
		try {
			for (SrePruebasManuales vRegistro : pPruebas) {
				SreFacRegistrosPruebasManuales vDatos = new SreFacRegistrosPruebasManuales();
				vDatos.setUsuarioRegistroId(pSistema.getUsuarioRegistroId());
				vDatos.setUsuarioUltimaModficacionId(pSistema.getUsuarioUltimaModificacionId());
				vDatos.setSolicitudCertificacionId(pSolicitudCertificacionId);
				vDatos.setTramiteId(pSistema.getTramiteId());
				vDatos.setPruebaManualId(vRegistro.getPruebaManualId());
				vDatos.setSistemaId(pSistema.getSistemaId());
				vDatos.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_PENDIENTE);
				vDatos.setFechaInicio(pSistema.getFechaRegistro());
				vDatos.setFechaRegistro(pSistema.getFechaRegistro());
				vDatos.setFechaUltimaModificacion(pSistema.getFechaUltimaModificacion());
				vDatos.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				iRegistroPruebasManualesDao.save(vDatos);

				LOG.info("Datos pruebas manuales registrados.");
			}
			vRespuesta = true;
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos pruebas manuales no registrados.");
		}
		return vRespuesta;
	}

}
